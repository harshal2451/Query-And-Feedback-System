/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Action.HibernateUtil;
import Tables.Department;
import Tables.Question;
import Tables.Subject;
import Tables.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author Sagar Naik
 */
public class SubmitQuestion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Session session = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");     
        try (PrintWriter out = response.getWriter()) {
            
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String date = simpleDateFormat.format(new Date());

            simpleDateFormat = new SimpleDateFormat("hh:mm:ss aa");
            String time = simpleDateFormat.format(new Date());
            //out.print(date + time);
            String que = request.getParameter("question");
            Integer deptId = Integer.parseInt(request.getParameter("dept_id"));
            Integer subId = Integer.parseInt(request.getParameter("selected_subject"));
            Integer userId = Integer.parseInt(request.getParameter("user_id"));
            //out.print(que +""+ deptId +""+ subId +""+ userId +""+ date +""+ time);
            try{
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Question q = new Question(new Department(deptId), new Subject(subId), new User(userId), que, date, time, "Unanswered");
                session.save(q);
                session.getTransaction().commit();
                session.close();
                out.print("<h4 style = color:green; >Question Submitted Successfully</h4>");
            }catch(Exception e){
                session.getTransaction().rollback();
                out.print(e.toString());
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
