import Action.HibernateUtil;
import Tables.Admin;
import Tables.Feedback;
import Tables.Question;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SubmitFeedbackAnswer extends HttpServlet {

    Session session = null;
    Transaction transaction = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Integer adminId = Integer.parseInt(request.getParameter("admin_id"));
            Integer queId = Integer.parseInt(request.getParameter("que_id"));
            float score = Float.parseFloat(request.getParameter("f_rate"));
            String comment = request.getParameter("f_comment");
            out.print(adminId + " "+ comment + " "+ queId + " "+ score);
           try{
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Feedback fbk = new Feedback(new Admin(adminId),new Question(queId),score, comment);
                session.save(fbk);
                session.getTransaction().commit();
                session.close();
                out.print("Success");
            }catch(Exception e){
                session.getTransaction().rollback();
                out.print(e.toString());
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
