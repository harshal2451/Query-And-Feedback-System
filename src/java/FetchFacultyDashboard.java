
import Action.HibernateUtil;
import Tables.Answer;
import Tables.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

public class FetchFacultyDashboard extends HttpServlet {
    static Session session = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //fetch parameter from jsp
            Integer admin_id = Integer.parseInt(request.getParameter("faculty_id"));
            Integer dept_id = Integer.parseInt(request.getParameter("dept_id"));

            session = HibernateUtil.getSessionFactory().openSession();
            //fetch total questions asked by student
            String total_questions = "FROM Question where dept_id = "+dept_id;
            Query query_total_questions = session.createQuery(total_questions);
            List<Question> result_total_questions = query_total_questions.list();
            int questions = result_total_questions.size();
            //fetch total faculty answers for student questions
            String total_answers = "FROM Answer where admin_id = "+admin_id;
            Query query_total_answers = session.createQuery(total_answers);

            List<Answer> result_total_answers = query_total_answers.list();

            int answers = result_total_answers.size();


            //fetch total block student in department
            String total_blockuser = "FROM BlockUser where dept_id = "+dept_id;
            Query query_total_blockuser = session.createQuery(total_blockuser);

            List<Answer> result_total_blockuser = query_total_blockuser.list();

            int block_user = result_total_blockuser.size();
            
            //send data to the jsp home_page.jsp
            out.print(questions+","+answers+","+block_user);
        
        }catch(Exception e){
            out.print("check"+e);
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
