<%-- 
    Document   : hod_dashboard
    Created on : 11 Apr, 2020, 7:46:44 PM
    Author     : Sagar Naik
--%>
<%@page import="Tables.User"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="Action.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%      
    //fetch student id for dashboard details
    String name = (String)session.getAttribute("user_student");
    Session session1 = HibernateUtil.getSessionFactory().openSession();
    Query q = session1.createQuery("FROM User WHERE user_name='"+session.getAttribute("user_student")+"'");
    List<User> result = q.list();
    Integer userId = result.get(0).getUserId();
%>  
<style>
    body{
        background: #f8f8f8;
    }
    #background,#background1,#background2{
        opacity: 0;
    }
</style>
                <!---------- send faculty id  to javascript-->
                <input type="hidden" id="student_id" />
				<div id="background"><center>
                	<img id="first" src="img/question.png">
                    <div id="question"></div>
                    </center>
                </div>
                
                <div id="background1"><center>
                	<img id="second" src="img/answer1.png">
                    <div id="answer"></div>
                    </center>
                </div>
                
                <div id="background2"><center>
                	<img id="third" src="img/customer-feedback.png">
                    <div id="feedback"></div>
                    </center>
                </div>
                <script type="text/javascript">
					document.getElementById("student_id").value = <%=userId%>;
					//fetch dashboard detail
					function fetchDashboardDetail(){
						//alert(document.getElementById("student_id").value);
						var student_id = document.getElementById("student_id").value;
						var xmlhttp = new XMLHttpRequest();
						xmlhttp.open("POST","FetchStudentDashboard?student_id="+student_id,false);
						xmlhttp.send(null);
						//alert(xmlhttp.responseText);
						
						var dashboard_detail = xmlhttp.responseText.split(",");
						//alert(xmlhttp.responseText);
						
						document.getElementById("question").innerHTML = "<h3>Submit Questions</h3><h2>"+dashboard_detail[0]+"</h2>";
						document.getElementById("answer").innerHTML = "<h3 style='margin-top:-9px;'>Faculty Answers</h3><h2>"+dashboard_detail[1]+"</h2>";
						document.getElementById("feedback").innerHTML = "<h3 style='margin-top:-9px;'>Submited Feedback</h3><h2>"+dashboard_detail[2]+"</h2>";
						document.getElementById("background").style.opacity="1";
                        document.getElementById("background1").style.opacity="1";
                        document.getElementById("background2").style.opacity="1";
					}
					
				</script>