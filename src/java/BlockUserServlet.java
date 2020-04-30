import Action.HibernateUtil;
import Tables.BlockUser;
import Tables.Department;
import Tables.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class BlockUserServlet extends HttpServlet {
    static Session session = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            String user_name = request.getParameter("user_name");
            
            //fetch user_id;
            String fetch_userid = "FROM User WHERE user_name = '"+user_name+"'";
            Query query_userid = session.createQuery(fetch_userid);
            
            List<User> result_userid = query_userid.list();
            
            int user_id = result_userid.get(0).getUserId();
            
           
            
            try {
                tx = session.beginTransaction();
                
                //delete all questions of blocked user
	
                //but first we have to delete answer of question those are asked by blocked student
                String delete_answer = "DELETE FROM Answer WHERE que_id in (SELECT queId FROM Question WHERE user_id ="+user_id +")";
                Query query_delete_answer = session.createQuery(delete_answer);
                query_delete_answer.executeUpdate();
                
                
                //first delete feedback of blocked student
                String delete_feedback = "DELETE FROM Feedback WHERE que_id in (SELECT queId FROM Question WHERE user_id ="+user_id +")";
                Query query_delete_feedback = session.createQuery(delete_feedback);
                query_delete_feedback.executeUpdate();
                
                //Now we can delete question
                String delete_question = "DELETE FROM Question WHERE user_id = "+user_id;
                Query query_delete_question = session.createQuery(delete_question);
                query_delete_question.executeUpdate();
                
                //insert block student into block user
                BlockUser insert = new BlockUser(new Department(result_userid.get(0).getDepartment().getDeptId()),new User(user_id),result_userid.get(0).getUserFname(),user_name);
                session.save(insert);
                
                //update username as null to block student
                String update_username = "UPDATE User set userName = null WHERE user_id = "+user_id;
                Query query_update = session.createQuery(update_username);
                
                int rows = query_update.executeUpdate();
                
                if(rows == 1)
                    out.print(user_name+" blocked Successfully");
                
                tx.commit();
                
            } catch (HibernateException e) {
                if (tx!=null) 
                    tx.rollback();
                out.print(e);
            } finally {
                session.close(); 
                
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

