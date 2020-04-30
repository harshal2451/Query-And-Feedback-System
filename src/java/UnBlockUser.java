
import Action.HibernateUtil;
import Tables.BlockUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UnBlockUser extends HttpServlet {
    static Session session = null;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            
            PrintWriter out = response.getWriter();
             
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                //out.print(request.getParameter("user_id"));
                String fetch_user_name = "FROM BlockUser WHERE user = "+request.getParameter("user_id");
                Query user_name = session.createQuery(fetch_user_name);

                List<BlockUser> result_user_name = user_name.list();
                //out.print(result_user_name.get(0).getUserName());
                
                //set user_name to user table
                SQLQuery update_user_name = session.createSQLQuery("update user set user_name ='"+result_user_name.get(0).getUserName()+"' where user_id = "+request.getParameter("user_id"));
                
                int rows = update_user_name.executeUpdate();
                //out.print(update_user_name.toString());
                //now delete entry of blocked student
                Query delete_blocked_user = session.createQuery("DELETE FROM BlockUser where user = "+request.getParameter("user_id"));

                int rows_delete = delete_blocked_user.executeUpdate();
                tx.commit();
                
                if(rows_delete == 1)
                       out.print(result_user_name.get(0).getUserName()+" unblocked Successfully");	
                
            }catch(Exception e){
                if(tx != null)
                    tx.rollback();
                out.print(e);
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
