/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Action.HibernateUtil;
import Tables.Admin;
import Tables.Answer;
import Tables.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Harshal
 */
public class SubmitAnswer extends HttpServlet {
    static Session session = null;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
           session = HibernateUtil.getSessionFactory().openSession();
           
           int admin_id = Integer.parseInt(request.getParameter("admin_id"));
           int que_id = Integer.parseInt(request.getParameter("que_id"));
           String answer_text = request.getParameter("answer");
             
           Date date = new Date();  
           //set date
           SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");  
           String strDate = sf.format(date);  
           
           //set time
           sf = new SimpleDateFormat("HH:mm:ss aa");  
           String strTime = sf.format(date);  
           
            Transaction tx = session.beginTransaction();
           
            Answer insertAnswer = new Answer(new Admin(admin_id),new Question(que_id),answer_text,strDate,strTime);
            session.save(insertAnswer);
            
            
            //update question status
            String update_status = "UPDATE Question set queStatus ='Answered' WHERE que_id="+que_id;
            Query query_status = session.createQuery(update_status);
            
            int rows =  query_status.executeUpdate();

            if(rows == 1)
                out.print("<h6 style = color:green; >Answer Submited Successfully</h6>");
            
            tx.commit();
            session.close();
            

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
