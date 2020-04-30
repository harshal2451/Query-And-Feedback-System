import Action.HibernateUtil;
import Tables.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
public class HodDashboard extends HttpServlet {

    Session session1 = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            String hod = session.getAttribute("user_hod").toString();
            //out.print(hod);
            session1 = HibernateUtil.getSessionFactory().openSession();
            Query q =session1.createQuery("FROM Admin WHERE admin_uname='"+hod+"'");
            List<Admin> res = q.list();
            Integer deptId = res.get(0).getDepartment().getDeptId();
            String deptName = res.get(0).getDepartment().getDeptName();
            //out.print(deptId + deptName);
            session1.close();
            String response1 = "<h4 style='border-bottom: 1px solid grey;'>Faculties of Department of "+deptName+"</h4><table border='0' style='text-align:center;' cellpadding='30'>"
                    + "<tr style='border-bottom: 1px solid lightgrey;'><th>ID</th><th>NAME</th><th>EMAIL</th><th>SUBJECT</th></tr>";
            session1 = HibernateUtil.getSessionFactory().openSession();
            Query query = session1.createQuery("FROM Admin WHERE admin_type='FACULTY' and dept_id="+deptId);
            List<Admin> result = query.list();
            for(int i = 0;i<result.size();i++){
                response1 += "<tr style='border-bottom: 1px solid lightgrey;'><td>"+result.get(i).getAdminId()+"</td><td>"+result.get(i).getAdminName()+"</td>"
                        + "<td>"+result.get(i).getAdminEmail()+"</td><td>"+result.get(i).getSubject().getSubName()+"</td></tr>";
            }
            response1 += "</table>";
            out.print(response1);
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
