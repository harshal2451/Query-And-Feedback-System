import Action.HibernateUtil;
import Tables.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;

public class FetchStudentDashboard extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Integer total_que = 0;
            Integer total_ans = 0;
            Integer total_feed = 0;
            
            Session session = null;
            //out.print(request.getParameter("student_id"));
            //Fetch total que...
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM Question WHERE user_id="+request.getParameter("student_id"));
            List<Question> t_que = query.list();
            total_que = t_que.size();
            session.close();
            //out.print(total_que);
            //fetch total faculty answers for student questions
            session = HibernateUtil.getSessionFactory().openSession();
            query = session.createSQLQuery("select count(ans_id) FROM answer WHERE que_id in (select que_id from question where user_id ="+request.getParameter("student_id")+")");
            total_ans = Integer.parseInt(query.uniqueResult().toString());
            
            
            session.close();
            
            //fetch total feedbacks submited by students
            session = HibernateUtil.getSessionFactory().openSession();
            query = session.createSQLQuery("select count(que_id ) from feedback where que_id in (select que_id from question where user_id ="+request.getParameter("student_id")+")");
            total_feed = Integer.parseInt(query.uniqueResult().toString());
            session.close();
            out.print(total_que+","+total_ans+","+total_feed);
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
