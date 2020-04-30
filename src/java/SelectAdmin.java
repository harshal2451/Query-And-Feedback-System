/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Action.HibernateUtil;
import Tables.Admin;
import Tables.Department;
import Tables.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;

public class SelectAdmin extends HttpServlet {

    Session session1 = null;
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String uname = request.getParameter("user_name");
            String response1 = "<select name='dept' class='select-css' id='admin_id' style='margin-top: -10px;margin-left:15px;width: 645px;' onchange='facultySelected()'>"
                    + "<option value='select faculty'>Select Faculty</option>";
            
            session1 = HibernateUtil.getSessionFactory().openSession();
            Query query  = session1.createQuery("FROM User WHERE user_name = '"+uname+"'");
            List<User> result = query.list();
            Integer deptId = result.get(0).getDepartment().getDeptId();
            session1.close();
            
            session1 = HibernateUtil.getSessionFactory().openSession();
            query  = session1.createQuery("FROM Admin WHERE admin_type='FACULTY' AND dept_id = "+deptId);
            List<Admin> result1 = query.list();
           
            for(int i = 0; i<result1.size();i++){
                response1 += "<option value='"+result1.get(i).getAdminId()+"'>"+result1.get(i).getAdminId()+" - "+result1.get(i).getAdminName()+"</option>";
            }
            response1 += "</select>";
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
