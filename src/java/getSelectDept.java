import Action.HibernateUtil;
import Tables.Department;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;

@WebServlet(urlPatterns = {"/getSelectDept"})
public class getSelectDept extends HttpServlet {
    static Session session = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if(request.getParameterMap().containsKey("register")){
                session = HibernateUtil.getSessionFactory().openSession();
                Query query  = session.createQuery("FROM Department");
                List<Department> result = query.list();

                String response1 = "<select name='dept' class='select-css' id='dept_id' style='margin-left: 27px;'>"
                        + "<option value='select department'>Select Department</option>";
                for(int i = 0; i<result.size();i++){
                    response1 += "<option value='"+result.get(i).getDeptId()+"'>"+result.get(i).getDeptId()+" - "+result.get(i).getDeptName()+"</option>";
                }
                response1 += "</select>";

                out.print(response1);
            }
            else if(request.getParameterMap().containsKey("ask_que")){
                String response1 = "<select id='selected_department' class='input-xlarge' onChange='fetchSubject()'>"
                                    + "<option value=''>Select</option>";
                session = HibernateUtil.getSessionFactory().openSession();
                Query query  = session.createQuery("FROM Department");
                List<Department> result = query.list();
		for(int i = 0; i<result.size();i++){
                    response1 += "<option value='"+result.get(i).getDeptId()+"'>"+result.get(i).getDeptId()+" - "+result.get(i).getDeptName()+"</option>";
                }
                response1 += "</select>";

                out.print(response1);
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
