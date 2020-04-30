<%-- 
    Document   : faculty_dashboard
    Created on : Apr 10, 2020, 9:15:21 AM
    Author     : Harshal
--%>

<%@page import="Tables.Department"%>
<%@page import="Tables.Admin"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="Action.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	//fetch admin id for dashboard details
	Session session1 = null;
        session1 = HibernateUtil.getSessionFactory().openSession();
        String fetch_admin_id = "FROM Admin WHERE admin_uname = '"+session.getAttribute("user_faculty")+"'";
        Query query_admin_id = session1.createQuery(fetch_admin_id);
        List<Admin> result_admin_id = query_admin_id.list();
        Integer admin_id = result_admin_id.get(0).getAdminId();
        Integer dept_id = result_admin_id.get(0).getDepartment().getDeptId();
%> 

<style>
     #background,#background1,#background2{
        opacity: 0;
    }
</style>
	<!---------- send faculty id  to javascript-->
        <input type="hidden" id="faculty_id" value="<%= admin_id %>"/>
                
        <!---------- send dept id  to javascript-->
        <input type="hidden" id="dept_id" value="<%= dept_id %>"/>
               
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
            <img id="third" src="img/blocked.png">
            <div id="block_user"></div>
            </center>
        </div>
                
        <script type="text/javascript">
					
            //fetch dashboard detail
            function fetchDashboardDetail(){
						
                var faculty_id = document.getElementById("faculty_id").value;
		var dept_id = document.getElementById("dept_id").value;
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("POST","FetchFacultyDashboard?faculty_id="+faculty_id+"&dept_id="+dept_id,false);
		xmlhttp.send(null);
		
		var dashboard_detail = xmlhttp.responseText.split(",");
		
						
		document.getElementById("question").innerHTML = "<h3>Student Questions</h3><h2>"+dashboard_detail[0]+"</h2>";
		document.getElementById("answer").innerHTML = "<h3 style='margin-top:-9px;'>Submited Answers</h3><h2>"+dashboard_detail[1]+"</h2>";
		document.getElementById("block_user").innerHTML = "<h3 style='margin-top:-9px;'>Block Users</h3><h2>"+dashboard_detail[2]+"</h2>";
		document.getElementById("background").style.opacity="1";
                document.getElementById("background1").style.opacity="1";
                document.getElementById("background2").style.opacity="1";
            }
					
    </script>
