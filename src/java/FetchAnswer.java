/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Action.HibernateUtil;
import Tables.Admin;
import Tables.Answer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Harshal
 */
public class FetchAnswer extends HttpServlet {
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
            //fetch answer for faculty to update answer
            if(request.getParameterMap().containsKey("admin_id") && request.getParameterMap().containsKey("que_id")){

                String que_id = request.getParameter("que_id");
                String admin_id = request.getParameter("admin_id");
                //out.print("123456");    
                String fetch_answer = "FROM Answer WHERE que_id = "+que_id+" and admin_id = "+admin_id;
                Query query_answer = session.createQuery(fetch_answer);

                List<Answer> result_answer = query_answer.list();
                
                out.print(result_answer.get(0).getAnsText());
            }
            
            //fetch ans for student...
            else if(request.getParameterMap().containsKey("que_id")){
                Integer que_id = Integer.parseInt(request.getParameter("que_id"));
                String response1 = "";
                SQLQuery q = session.createSQLQuery("select A.ans_text,A.ans_date,A.ans_time,B.admin_name,B.admin_id from answer A NATURAL JOIN admin B where A.que_id = "+que_id);
                List<Object[]> result = q.list();
                for(Object[] obj : result){
                    response1 += "<hr><div style='float: left;'>Faculty:"+obj[3].toString()+"</div><div style='margin-left:300px;'>Date:"+obj[1].toString()+
                            "&nbsp;&nbsp;Time:"+obj[2].toString()+"</div><div>Answer:- "+obj[0].toString()+"<br><a onclick='giveFeedback("+obj[4]+")'>Give Feedback</a></div>";
                }
                out.print(response1);
            }
            
            //fetch answer for all questions for faculty panel
            else if(request.getParameterMap().containsKey("all_que_id")){
	
                String answer_detail = ""; 
                String fetch_answer = "select A.ans_text,A.ans_date,A.ans_time,B.admin_name,B.admin_id from Answer A INNER JOIN admin B where A.que_id = "+request.getParameter("all_que_id");
                SQLQuery query_answer = session.createSQLQuery(fetch_answer);
                List<Object[]> rows = query_answer.list();
                
                for(Object[] row : rows){
            
                    answer_detail = "<hr><div style='float: left;'>Faculty: "+row[3].toString()+"</div><div style='margin-left:300px;'>Date:"+row[1].toString()+"&nbsp;&nbsp;Time:"+row[2].toString()+"</div><div>Answer:- "+row[0].toString()+"</div>";
                }

                out.print(answer_detail);
            }
            
            
            //fetch answer for faculty my_answer.php
            else if(request.getParameterMap().containsKey("question_id")){
                
                    String que_id = request.getParameter("question_id");
                    
                    String fetch_answer = "FROM Answer WHERE que_id = "+que_id;
                    Query query_answer = session.createQuery(fetch_answer);

                    List<Answer> result_answer = query_answer.list();
                
                    
                    
                    //fetch faculty name 
                    String fetch_facultyname = "FROM Admin WHERE admin_id = "+result_answer.get(0).getAdmin().getAdminId();
                    Query query_facultyname = session.createQuery(fetch_facultyname);
                    
                    List<Admin> result_faculytname = query_facultyname.list();
                    
                    String answer_detail = "<div style='float: left;'>Faculty:"+result_faculytname.get(0).getAdminName()+"&nbsp;&nbsp;</div><div style='margin-left:300px;'>Date:"+result_answer.get(0).getAnsDate()+"&nbsp;&nbsp;Time:"+result_answer.get(0).getAnsTime()+"</div><div>Answer:- "+result_answer.get(0).getAnsText()+"<br></div>";
                    
                    out.print(answer_detail);
                    
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
