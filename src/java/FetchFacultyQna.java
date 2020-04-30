
import Action.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class FetchFacultyQna extends HttpServlet {
    static Session session = null;
    HttpSession sess;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            sess = request.getSession();
            session = HibernateUtil.getSessionFactory().openSession();
            
            String responseMsg = "";
            
         
            SQLQuery query_qna = session.createSQLQuery("SELECT Q.que_text,A.ans_text FROM question Q INNER JOIN answer A on Q.que_id = A.que_id WHERE admin_id in (select admin_id from admin where admin_uname = '"+sess.getAttribute("user_faculty")+"')");
            List<Object[]> result_qna = query_qna.list();
            
            responseMsg += "<table class='tbl' border='0' style='text-align:center;' cellpadding='30'><h4 style= margin-top:20px;><b>Question Answers</b></h4><hr>";
            int srno = 1;
            responseMsg += "<th>Sr.Number</th><th>Question</th><th>Answer</th>";
            
            for(Object[] row : result_qna){
                responseMsg += "<tr><td>"+String.valueOf(srno)+"</td><td>"+row[0].toString()+"</td><td>"+row[1].toString()+"</td></tr>";
                srno++;
            }
            responseMsg += "</table>";

            out.println(responseMsg);
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
