import Action.HibernateUtil;
import Tables.Answer;
import Tables.Question;
import Tables.User;
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

public class FetchAllQuestions extends HttpServlet {
    static Session session = null;
    HttpSession sess; 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            sess = request.getSession();
            String name = (String)sess.getAttribute("user_student");
            session = HibernateUtil.getSessionFactory().openSession();
            
            //fetch all questions for student to see answer from my_questions.php
                if(request.getParameterMap().containsKey("student_id")){
                    String response1 = "";
                    Integer userId = Integer.parseInt(request.getParameter("student_id"));

                    session = HibernateUtil.getSessionFactory().openSession();
                    Query query = session.createQuery("FROM Question WHERE user_id="+userId+" ORDER BY que_date desc");
                    List<Question> result1 = query.list();
                    session.close();
                    String subName;
                    
                    if(result1.size() != 0){
                        for(int i = 0; i<result1.size(); i++){
                            session = HibernateUtil.getSessionFactory().openSession();
                            Query sub = session.createSQLQuery("SELECT sub_name FROM subject WHERE sub_id="+result1.get(i).getSubject().getSubId());
                            subName = sub.uniqueResult().toString();
                            response1 += "<div class='modal' style='margin-top:15px;'>"
                                        + "<div class='modal-content' style='height:auto;'>"
                                            + "<div class='modal-header'> "                    
                                                + "<h6 style='width:auto;'>&nbsp;&nbsp;"+ name +"</h6><h6 style='margin-top:-30px; margin-left:340px;'>Date:"+ result1.get(i).getQueDate()+"&nbsp;&nbsp; Time:"+result1.get(i).getQueTime()+"</h6>"
                                            +  "</div>"
                                            + "<div class='modal-body' style='height:auto; overflow:auto; word-wrap: break-word;'>"
                                                + "<div><b>Subject: "+subName+"</b></div>"  
                                                + "<div><p>Question:-&nbsp;&nbsp;"+ result1.get(i).getQueText() +"</p></div>"
                                                + "<div id='"+result1.get(i).getQueId()+"'></div><br/>";
                                                if(result1.get(i).getQueStatus().equals("Answered"))
                                                    response1 += "<div><button id = btn_answer value ="+result1.get(i).getQueId()+" onClick = getAnswer(this.value) style='width:60px; height:auto;'>Answer</button></div>";
                                            response1 += "</div>"
                                            + "<div class='modal-footer'>"
                                                + "<h6 id='sub_name' style='float:left; margin:-1px 5px;'></h6><h6 style='margin:-2px 10px;'>"+result1.get(i).getQueStatus()+"</h6>"
                                            + "</div>"
                                        + "</div>"
                                       + "</div>";
                        }		
                    }
                    else{
                            response1 += "<center><h3 style='color:red; border:solid; padding:10px; margin:200px -50px;'>You have not submited any question yet</h3></center>";
                    }
                    out.print(response1);
                }
           
                    else if(request.getParameterMap().containsKey("all_id")){
                    //fetch all questions and answers for student
                    //out.print(request.getParameter("all_id"));
                    String response1 = "";
                    Integer userId = Integer.parseInt(request.getParameter("all_id"));
                    //fetch dept_id
                    session = HibernateUtil.getSessionFactory().openSession();
                    Query query = session.createQuery("FROM User WHERE user_id="+userId);
                    List<User> result1 = query.list();
                    session.close();

                    session = HibernateUtil.getSessionFactory().openSession();
                    query = session.createQuery("FROM Question WHERE dept_id="+ result1.get(0).getDepartment().getDeptId() +" ORDER BY que_date desc");
                    List<Question> result = query.list();
                    session.close();
                    String subName = "";
                    if(result.size() != 0){
                        for(int i = 0; i<result.size(); i++){
                             session = HibernateUtil.getSessionFactory().openSession();
                            Query sub = session.createSQLQuery("SELECT sub_name FROM subject WHERE sub_id="+result.get(i).getSubject().getSubId());
                            subName = sub.uniqueResult().toString();
                            //fetch usename of owner of question
                            session = HibernateUtil.getSessionFactory().openSession();
                            query = session.createQuery("FROM User WHERE user_id="+ result.get(i).getUser().getUserId());
                            List<User> fetch_username = query.list();
                            session.close();
                            response1 += "<div class='modal_all' style='margin-top:15px;'>"
                                        + "<div class='modal-content' style='height:auto;'>"
                                          + "<div class='modal-header'>"
                                            + "<h6 style='width:auto;'>&nbsp;&nbsp;"+fetch_username.get(0).getUserName()+"</h6><h6 style='margin-top:-30px; margin-left:340px;'>Date:"+result.get(i).getQueDate()+"&nbsp;&nbsp; Time:"+result.get(i).getQueTime()+"</h6>"
                                          + "</div>"
                                          + "<div class='modal-body' style='height:auto; overflow:auto; word-wrap: break-word;'>"
                                            + " <!----- Display Question -->"
                                            + "<div><b>Subject: "+subName+"</b></div>"  
                                            + "<div><p>Question:-&nbsp;&nbsp;"+result.get(i).getQueText()+"</p></div>"
                                            + "<!----- Display answer -->"
                                            + "<div id='all"+result.get(i).getQueId()+"'></div><br />";
                                        if(result.get(i).getQueStatus().equals("Answered"))
                                            response1 += "<div><button id = all_btn_answer value ="+result.get(i).getQueId()+" onClick = getAllAnswer(this.value) style='width:60px; height:auto;'>Answer</button></div>";
                                        response1 += "</div>"
                                          + "<div class='modal-footer'>"
                                            + "<h6 id='sub_name' style='float:left; margin:-1px 5px;'></h6><h6 style='margin:-2px 10px;'>"+result.get(i).getQueStatus()+"</h6>"
                                          + "</div>"
                                        + "</div>"
                                       + "</div>";      
                        }
                    }
                    else{
                        response1 += "<h3 style='color:red; border:solid; padding-left:160px; margin:200px 300px;text-align:center;' >You have not submited any question yet</h3>";
                    }
                    out.print(response1);
                }
            //fetch all questions of students for faculty to give answer
            else if(request.getParameterMap().containsKey("dept_id")){
                
                String dept_id = request.getParameter("dept_id");
                
                String fetch_question = "FROM Question Where dept_id ="+dept_id+" order by que_date desc";
                Query query_fetch_question = session.createQuery(fetch_question);
                
                List<Question> result_question = query_fetch_question.list();
               
                String responseMsg = "";
                //display all question
                if(!result_question.isEmpty()){
                    
                   for( int i=0; i< result_question.size() ; i++){
                      
                      //session = HibernateUtil.getSessionFactory().openSession();
                      
                      User user = result_question.get(i).getUser();
                      String fetch_username = "FROM User WHERE user_id ="+user.getUserId();
                      Query query_username = session.createQuery(fetch_username);
                      
                      List<User> result_username = query_username.list();
                      
                     
                      String userName = result_username.get(0).getUserName();
                      
                      Query sub = session.createSQLQuery("SELECT sub_name FROM subject WHERE sub_id="+result_question.get(i).getSubject().getSubId());
                      String subName = sub.uniqueResult().toString(); 
                      
                      responseMsg += "<div class='modal' style='margin-top:15px;'><div class='modal-content' style='height:auto;'><div class='modal-header'>";
                      responseMsg += "<h6 style='width:auto;'>&nbsp;&nbsp;"+userName+"</h6><h6 style=' margin-top:-30px; margin-left:340px;'>"+result_question.get(i).getQueDate()+"&nbsp;&nbsp; Time:"+result_question.get(i).getQueTime()+"</h6>" +
"					</div>\n" +
"					<div class='modal-body' style='height:auto; overflow:auto; word-wrap: break-word;'>";
                      
                      //diaplay Subject name
                      responseMsg += "<div><b>Subject: "+subName+"</b></div>";
                      //display question
                      responseMsg += "<div><p>Question:-&nbsp;&nbsp;"+result_question.get(i).getQueText()+"</p></div>";
                          
                      //response message     
                      responseMsg += "<div id='"+result_question.get(i).getQueId()+"'></div><br />";
                      
                      if(result_question.get(i).getQueStatus().equals("Answered")){
                          responseMsg += "<div><button id='give_answer_btn"+result_question.get(i).getQueId()+"' value ='"+result_question.get(i).getQueId()+"' onClick='giveAnswer(this.value)' style='width:105px; height:auto;'>Edit Answer</button></div>";
                      }else{
			  responseMsg += "<div><button id='give_answer_btn"+result_question.get(i).getQueId()+"' value ='"+result_question.get(i).getQueId()+"' onClick='giveAnswer(this.value)' style='width:95px; height:auto;'>Give Answer</button></div>";
                      }
                      
                      responseMsg += "</div><div class='modal-footer'>";
                      
                      
                      String fetch_anwer = "FROM Answer WHERE que_id ="+result_question.get(i).getQueId()+" and admin_id = "+request.getParameter("faculty_id");
                      Query query_aanswer = session.createQuery(fetch_anwer);
                      
                      List<Answer> result_answer = query_aanswer.list();
                      
                      if(!result_answer.isEmpty()){
                          
                          responseMsg += "<a id='"+userName+"' style='float:left; margin:-1px 5px;' onclick='blockUser(this.id)'>block "+userName+"</a><h6 id = 'update_status' style='margin:-2px 10px;'>Answered By You</h6>";
							;
                      }else{
                          
                           responseMsg += "<a id='"+userName+"' style='float:left; margin:-1px 5px;' onclick='blockUser(this.id)'>block "+userName+"</a><h6 id = 'update_status' style='margin:-2px 10px;'>Not Answered By You</h6>";
                      }  
                      responseMsg += "</div></div></div>";
                   }
                    out.print(responseMsg);
                
                   
                }else{
                    out.print("<h3 style='color:red; border:solid; padding-left:240px; margin:200px 300px;' >No Question Found</h3>");
                }
                
                
            }
            
            //fetch all questions of students for faculty to give answer
            else if(request.getParameterMap().containsKey("admin_id")){
                
                String admin_id = request.getParameter("admin_id");
                
                String fetch_question = "FROM Question WHERE que_id in (SELECT question FROM Answer WHERE admin_id = "+admin_id+") order by que_date desc";
                //out.print(fetch_question);
                Query query_fetch_question = session.createQuery(fetch_question);
                
                List<Question> result_question = query_fetch_question.list();
                
                
                
                String responseMsg = "";
                //display all question
                if(!result_question.isEmpty()){
                   
                   for( int i=0; i< result_question.size() ; i++){
                       
                      User user = result_question.get(i).getUser();
                      String fetch_username = "FROM User WHERE user_id ="+user.getUserId();
                      Query query_username = session.createQuery(fetch_username);
                      
                      List<User> result_username = query_username.list();
                      
                      Query sub = session.createSQLQuery("SELECT sub_name FROM subject WHERE sub_id="+result_question.get(i).getSubject().getSubId());
                      String subName = sub.uniqueResult().toString(); 
                      
                      String userName = result_username.get(0).getUserName();
                      
                      
                      responseMsg += "<div class='modal' style='margin-top:15px;'><div class='modal-content' style='height:auto;'><div class='modal-header'>";
                      responseMsg += "<h6 style='width:auto;'>&nbsp;&nbsp;"+userName+"</h6><h6 style=' margin-top:-30px; margin-left:340px;'>"+result_question.get(i).getQueDate()+"&nbsp;&nbsp; Time:"+result_question.get(i).getQueTime()+"</h6>" +
"					</div>\n" +
"					<div class='modal-body' style='height:auto; overflow:auto; word-wrap: break-word;'>";
                      
                      //diaplay Subject name
                      responseMsg += "<div><b>Subject: "+subName+"</b></div>";
                      
                      //display question
                      responseMsg += "<div><p>Question:-&nbsp;&nbsp;"+result_question.get(i).getQueText()+"</p></div>";
                          
                      //response message     
                      responseMsg += "<div id='"+result_question.get(i).getQueId()+"'></div><br />";
                      
                      responseMsg += "<div><button id='btn_answer"+result_question.get(i).getQueId()+"' value ='"+result_question.get(i).getQueId()+"' onClick='getAnswer(this.value)' style='width:95px; height:auto;'>Answer</button></div>";
                      
                      
                      responseMsg += "</div><div class='modal-footer'>";
                      
                      responseMsg += "<h6 id='date_time' style='float:left; margin:-1px 5px;'></h6><h6 style='margin:-2px 10px;'>"+result_question.get(i).getQueStatus()+"</h6>";
                      responseMsg += "</div></div></div>";
                   }
                    out.print(responseMsg);
                
                   
                }else{
                    out.print("<center><h3 style='color:red; border:solid; padding:10px; margin:200px -50px;;'>You have not submited any answer yet!</h3></center>");
                }
                
                
            }
            
            //fetch all questions and answers for faculty
            else if(request.getParameterMap().containsKey("all_for_faculty")){
                
                String dept_id = request.getParameter("faculty_dept");
                
                String fetch_question = "FROM Question Where dept_id ="+dept_id+" order by que_date desc";
                Query query_fetch_question = session.createQuery(fetch_question);
                
                List<Question> result_question = query_fetch_question.list();
               
                String responseMsg = "";
                //display all question
                if(!result_question.isEmpty()){
                    
                   for( int i=0; i< result_question.size() ; i++){
                       
                      User user = result_question.get(i).getUser();
                      String fetch_username = "FROM User WHERE user_id ="+user.getUserId();
                      Query query_username = session.createQuery(fetch_username);
                      
                      List<User> result_username = query_username.list();
                      String userName = result_username.get(0).getUserName();
                      
                      
                      responseMsg += "<div class='modal' style='margin-top:15px;'><div class='modal-content' style='height:auto;'><div class='modal-header'>";
                      responseMsg += "<h6 style='width:auto;'>&nbsp;&nbsp;"+userName+"</h6><h6 style=' margin-top:-30px; margin-left:340px;'>"+result_question.get(i).getQueDate()+"&nbsp;&nbsp; Time:"+result_question.get(i).getQueTime()+"</h6>" +
"					</div>\n" +
"					<div class='modal-body' style='height:auto; overflow:auto; word-wrap: break-word;'>";
                      
                      //display question
                      responseMsg += "<div><p>Question:-&nbsp;&nbsp;"+result_question.get(i).getQueText()+"</p></div>";
                          
                      //response message     
                      responseMsg += "<div id='all"+result_question.get(i).getQueId()+"'></div><br />";
                      
                      if(result_question.get(i).getQueStatus().equals("Answered")){
                           responseMsg += "<div><button id='all_btn_answer' value ='"+result_question.get(i).getQueId()+"' onClick='getAllAnswer(this.value)' style='width:95px; height:auto;'>Answer</button></div>";
                      }
                      
                      responseMsg += "</div><div class='modal-footer'>";
                      
                      
                      String fetch_anwer = "FROM Answer WHERE que_id ="+result_question.get(i).getQueId()+" and admin_id = "+request.getParameter("faculty_id");
                      Query query_aanswer = session.createQuery(fetch_anwer);
                      
                      List<Answer> result_answer = query_aanswer.list();
                      
                      if(!result_answer.isEmpty()){
                          
                          responseMsg += "<h6 id = 'update_status' style='margin:-2px 10px;'>Answered By You</h6>";
							;
                      }else{
                          
                           responseMsg += "<h6 id = 'update_status' style='margin:-2px 10px;'>Not Answered By You</h6>";
                      }  
                      responseMsg += "</div></div></div>";
                   }
                    out.print(responseMsg);
                
                   
                }else{
                    out.print("<h3 style='color:red; border:solid; padding-left:240px; margin:200px 300px;' >No Question Found</h3>");
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
