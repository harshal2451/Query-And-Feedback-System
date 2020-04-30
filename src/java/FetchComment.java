import Action.HibernateUtil;
import Tables.Admin;
import Tables.FacultyFeedback;
import Tables.Feedback;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;

public class FetchComment extends HttpServlet {
    Session sess = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          if(request.getParameterMap().containsKey("select")){
              String responseMsg = "";
              String select = request.getParameter("select");
              Integer adminId = Integer.parseInt(request.getParameter("admin_id"));
              
              //get Admin name...
              sess = HibernateUtil.getSessionFactory().openSession();
              Query q = sess.createQuery("FROM Admin WHERE admin_id="+adminId);
              List<Admin> ad = q.list();
              String adminName = ad.get(0).getAdminName();
              sess.close();
              
              if(select.equals("ans")){
                //Fetch feedback
                sess = HibernateUtil.getSessionFactory().openSession();
                Query q1 = sess.createQuery("FROM Feedback WHERE admin_id="+adminId);
                List<Feedback> f1 = q1.list();
                int c = 1;
                sess.close();
                responseMsg += "<div id='cmtd'>"
                                    + "<h4 id='gotocomment'>Comments for:&nbsp;"+adminName+"</h4>";
                for(int i = 0; i < f1.size(); i++){
                    responseMsg += "<div id='commentText'>"+c+":"+f1.get(i).getFComment()+"</div>";
                    c++;
                }
                out.print(responseMsg+"</div>");
              }
              else {
                  //Fetch feedback
                    sess = HibernateUtil.getSessionFactory().openSession();
                    Query q1 = sess.createQuery("FROM FacultyFeedback WHERE admin_id="+adminId);
                    List<FacultyFeedback> f1 = q1.list();
                    int c = 1;
                    sess.close();
                    responseMsg += "<div id='cmtd'>"
                                        + "<h4 id='gotocomment'>Comments for:&nbsp;"+adminName+"</h4>";
                    for(int i = 0; i < f1.size(); i++){
                        responseMsg += "<div id='commentText'>"+c+":"+f1.get(i).getComment()+"</div>";
                        c++;
                    }
                    out.print(responseMsg+"</div>");
              }
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
