
import Action.HibernateUtil;
import Tables.BlockUser;
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

public class FetchBlockUser extends HttpServlet {
    static Session session = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            session = HibernateUtil.getSessionFactory().openSession();
            
            String fetch_dept_id = "FROM BlockUser where dept_id = "+request.getParameter("dept_id");
            Query query_dept_id = session.createQuery(fetch_dept_id);
            
            List<BlockUser> result = query_dept_id.list();
            
            String responseMsg = "";
            
            responseMsg += "<h4 style='border-bottom: 1px solid grey;'>Blocked Students</h4><table border='0' style='text-align:center;' cellpadding='30'><tr style='border-bottom: 1px solid lightgrey;'><th>No</th><th>NAME</th><th>Action</th></tr>";

            int no = 1;

            if(!result.isEmpty()){

                for(int i=0; i<result.size() ; i++){
                    responseMsg += "<tr style='border-bottom: 1px solid lightgrey;'><td> "+String.valueOf(no)+" </td><td>"+result.get(i).getUserFname()+"</td><td> <button value='"+result.get(i).getUser().getUserId()+"' class='form-submit' onclick='unblockUser(this.value)'>Unblock</button></td></td></tr>";
                    no++;
                }
                responseMsg += "</table>";
                out.print(responseMsg);
            }else
                out.print("<h3 style='color:red'>No One Blocked till now</h3>");
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
