<%-- 
    Document   : give_answer.jsp
    Created on : Apr 10, 2020, 11:28:22 AM
    Author     : Harshal
--%>

<%@page import="Tables.Department"%>
<%@page import="java.util.List"%>
<%@page import="Tables.Admin"%>
<%@page import="org.hibernate.Query"%>
<%@page import="Action.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String student = "STUDENT",faculty = "FACULTY",hod = "HOD";
    
    if(session.getAttribute("user_type") != null){
    	// login for hod	
	if(session.getAttribute("user_type").equals(hod)){
            if(session.getAttribute("user_hod") == null)
		response.sendRedirect("login.jsp");
	}
		
	// login for faculty	
	else if(session.getAttribute("user_type").equals(faculty)){
            if(session.getAttribute("user_faculty") == null)
		response.sendRedirect("login.jsp");
	}
			
	else if(session.getAttribute("user_type").equals(student)){
            if(session.getAttribute("user_student") == null){
		response.sendRedirect("login.jsp");
            }
         
	}
    }else{
        
        if(session.getAttribute("user_hod") == null)
            response.sendRedirect("login.jsp");
        
        else if(session.getAttribute("user_faculty") == null)
            response.sendRedirect("login.jsp");
        
        else if(session.getAttribute("user_student") == null)
            response.sendRedirect("login.jsp");
        
    }
%>


<html>

<!-- Mirrored from www.eakroko.de/flat/ by HTTrack Website Copier/3.x [XR&CO'2010], Fri, 24 Jan 2014 12:45:42 GMT -->
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<!-- Apple devices fullscreen -->
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<!-- Apple devices fullscreen -->
	<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
	
	<title>Admin - Dashboard</title>

	<!-- Bootstrap -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<!-- Bootstrap responsive -->
	<link rel="stylesheet" href="css/bootstrap-responsive.min.css">
	<!-- jQuery UI -->
	<link rel="stylesheet" href="css/plugins/jquery-ui/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="css/plugins/jquery-ui/smoothness/jquery.ui.theme.css">
	<!-- PageGuide -->
	<link rel="stylesheet" href="css/plugins/pageguide/pageguide.css">
	<!-- Fullcalendar -->
	<link rel="stylesheet" href="css/plugins/fullcalendar/fullcalendar.css">
	<link rel="stylesheet" href="css/plugins/fullcalendar/fullcalendar.print.css" media="print">
	<!-- chosen -->
	<link rel="stylesheet" href="css/plugins/chosen/chosen.css">
	<!-- select2 -->
	<link rel="stylesheet" href="css/plugins/select2/select2.css">
	<!-- icheck -->
	<link rel="stylesheet" href="css/plugins/icheck/all.css">
	<!-- Theme CSS -->
	<link rel="stylesheet" href="css/style.css">
	<!-- Color CSS -->
	<link rel="stylesheet" href="css/themes.css">


	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	
	
	<!-- Nice Scroll -->
	<script src="js/plugins/nicescroll/jquery.nicescroll.min.js"></script>
	<!-- jQuery UI -->
	<script src="js/plugins/jquery-ui/jquery.ui.core.min.js"></script>
	<script src="js/plugins/jquery-ui/jquery.ui.widget.min.js"></script>
	<script src="js/plugins/jquery-ui/jquery.ui.mouse.min.js"></script>
	<script src="js/plugins/jquery-ui/jquery.ui.draggable.min.js"></script>
	<script src="js/plugins/jquery-ui/jquery.ui.resizable.min.js"></script>
	<script src="js/plugins/jquery-ui/jquery.ui.sortable.min.js"></script>
	<!-- Touch enable for jquery UI -->
	<script src="js/plugins/touch-punch/jquery.touch-punch.min.js"></script>
	<!-- slimScroll -->
	<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- vmap -->
	<script src="js/plugins/vmap/jquery.vmap.min.js"></script>
	<script src="js/plugins/vmap/jquery.vmap.world.js"></script>
	<script src="js/plugins/vmap/jquery.vmap.sampledata.js"></script>
	<!-- Bootbox -->
	<script src="js/plugins/bootbox/jquery.bootbox.js"></script>
	<!-- Flot -->
	<script src="js/plugins/flot/jquery.flot.min.js"></script>
	<script src="js/plugins/flot/jquery.flot.bar.order.min.js"></script>
	<script src="js/plugins/flot/jquery.flot.pie.min.js"></script>
	<script src="js/plugins/flot/jquery.flot.resize.min.js"></script>
	<!-- imagesLoaded -->
	<script src="js/plugins/imagesLoaded/jquery.imagesloaded.min.js"></script>
	<!-- PageGuide -->
	<script src="js/plugins/pageguide/jquery.pageguide.js"></script>
	<!-- FullCalendar -->
	<script src="js/plugins/fullcalendar/fullcalendar.min.js"></script>
	<!-- Chosen -->
	<script src="js/plugins/chosen/chosen.jquery.min.js"></script>
	<!-- select2 -->
	<script src="js/plugins/select2/select2.min.js"></script>
	<!-- icheck -->
	<script src="js/plugins/icheck/jquery.icheck.min.js"></script>

	<!-- Theme framework -->
	<script src="js/eakroko.min.js"></script>
	<!-- Theme scripts -->
	<script src="js/application.min.js"></script>
	<!-- Just for demonstration -->
	<script src="js/demonstration.min.js"></script>
	
	<!--[if lte IE 9]>
		<script src="js/plugins/placeholder/jquery.placeholder.min.js"></script>
		<script>
			$(document).ready(function() {
				$('input, textarea').placeholder();
			});
		</script>
	<![endif]-->

	<!-- Favicon -->
	<link rel="shortcut icon" href="img/favicon.ico" />
	<!-- Apple devices Homescreen icon -->
	<link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-precomposed.png" />

	<style>
		body {font-family: Arial, Helvetica, sans-serif;}
	
		/* The Modal (background) */
		.modal {
		  display: block; /* Hidden by default */
		  z-index:0;
		  position:relative;
		  width: 550px; /* Full width */
		  height: auto; /* Full height */
		}
		
		/* Modal Content */
		.modal-content {
		  position: relative;
		  background-color: #fefefe;
		  border: 1px solid #888;
		  width: 100%;
		  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
		}
		
		.modal-header {
		  background-color: #CCC;
		  color: black;
		  padding:0px;
		  height:18px;;
		  margin-top:-10px;
		}
		
		.modal-footer {
		  background-color: #CCC;
		  color: black;
		  padding:0px;
		  height:18px;
		  margin-top:2px;
		}
		
		
		
		/*Model for type answer from faculty */
		
		/* The Modal (background) */
		.modal_answer {
		  display: none; /* Hidden by default */
		  position: fixed; /* Stay in place */
		  z-index: 1; /* Sit on top */
		  padding-top: 100px; /* Location of the box */
		  left: 0;
		  top: 0;
		  width: 100%; /* Full width */
		  height: 100%; /* Full height */
		  overflow: auto; /* Enable scroll if needed */
		  background-color: rgb(0,0,0); /* Fallback color */
		  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
		  border:groove;
		
		}
		
		/* Modal Content */
		.modal-content_answer {
		  position: relative;
		  background-color: #fefefe;
		  margin: auto;
		  padding: 0;
		  border: 1px solid #888;
		  width: 320px;
		  height:270px;
		  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
		  -webkit-animation-name: animatetop;
		  -webkit-animation-duration: 0.4s;
		  animation-name: animatetop;
		  animation-duration: 0.4s
		}
		
		/* Add Animation */
		@-webkit-keyframes animatetop {
		  from {top:-300px; opacity:0} 
		  to {top:0; opacity:1}
		}
		
		@keyframes animatetop {
		  from {top:-300px; opacity:0}
		  to {top:0; opacity:1}
		}
		
		/* The Close Button */
		.close_answer {
		  color: white;
		  float: right;
		  font-size: 28px;
		  font-weight: bold;
		}
		
		.close_answer:hover,
		.close_answer:focus {
		  color: #000;
		  text-decoration: none;
		  cursor: pointer;
		}
		
		.modal-header_answer {
		  background-color: #C1C1C1;
		  color: black;
		  margin-top:-20px;
		  padding-left:5px;
		}
		
		.modal-body_answer {padding: 2px 16px;}
	</style>

</head>

<body onLoad="fetchAllQuestion()">
	
	<div id="navigation" style="position:fixed;width:100%;">
      
            <%
                //String student = "STUDENT",faculty = "FACULTY",hod = "HOD";
                
		if(session.getAttribute("user_type") != null){
		
			if(session.getAttribute("user_type").equals(hod)){
				%><jsp:include page="hod_header.jsp" />
                      <%}                  
			else if(session.getAttribute("user_type").equals(faculty)){
				%><jsp:include page="faculty_header.jsp" />
                      <%}       
			else if(session.getAttribute("user_type").equals(student)){
				%><jsp:include page="student_header.jsp" />
                       <%}
		}
           
                Session session1 = null;

                session1 = HibernateUtil.getSessionFactory().openSession();

                String fetch_admin_id = "FROM Admin WHERE admin_uname = '"+session.getAttribute("user_faculty")+"'";

                Query query_admin_id = session1.createQuery(fetch_admin_id);

                List<Admin> result_admin_id = query_admin_id.list();

                int admin_id = result_admin_id.get(0).getAdminId();
                
                int dept_id = result_admin_id.get(0).getDepartment().getDeptId();
                

            %>
	</div><%@ include file="scroll.html" %>
   
	<div class="container-fluid" id="content">
		
		<div id="main" style="margin:40px">
		<div class="container-fluid">
				
                    <!---------- send faculty id  to javascript-->
                    <input type="hidden" id="faculty_id" value="<%= admin_id %>"/>

                    <!---------- send dept id  to javascript-->
                    <input type="hidden" id="dept_id" value="<%= dept_id %>"/>


                    <!---------- Fetch All Questions of the Student -->
                    <div id="my_questions"></div>
						
                </div>
            
         </div>
          
    </div>
				
   
   	<!-- The Modal  to submit answer by faculty-->
        <div id="myModal_answer" class="modal_answer">
        
          <!-- Modal content -->
          <div class="modal-content_answer">
            <div class="modal-header_answer">
              <span class="close_answer">&times;</span>
              <h5>Enter Answer</h5>
            </div>
            <div class="modal-body_answer">
              <textarea id="answer" rows="7" placeholder="Type Your Answer"style="width:256px; background-color:white;" ></textarea>
              <div id="promptAnswer" style="margin-top:-13px; height:15px;"></div><br>
              <button id="model_btn_submit" type="button" style="margin:10px 30px;" onClick="submitAnswer()">Submit Answer</button>
              <button type="button" onClick="clearAnswer()">Clear Answer</button>
            </div>
          </div>
        
        </div>
        
									
									
		<script type="text/javascript">
			$("#link_give_answer").addClass('active');
	
			var _gaq = _gaq || [];
			_gaq.push(['_setAccount', 'UA-38620714-4']);
			_gaq.push(['_trackPageview']);
	
			(function() {
				var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
				ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
				var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
			})();
			
		</script>
        
                <script type="text/javascript">
			//this function fetch all questions of user
			var faculty_id;
			
			function fetchAllQuestion(){
					
				var dept_id = document.getElementById("dept_id").value;
				faculty_id = document.getElementById("faculty_id").value;
				//alert(student_id);
				
				var xmlhttp = new XMLHttpRequest();
                                xmlhttp.open("POST","FetchAllQuestions?faculty_id="+faculty_id+"&dept_id="+dept_id,false);
                                xmlhttp.send(null);

                                //alert(xmlhttp.responseText);
				
				
				document.getElementById("my_questions").innerHTML = xmlhttp.responseText;
		
			}
			
                        //This function popup one model for entering answer by faculty
				//fetch question id for submitting answer
				var que_id;
				
				var modal;
				
			function giveAnswer(value){
				que_id = value;
				
				document.getElementById("promptAnswer").innerHTML = "";
				//document.getElementById(que_id).innerHTML = "";
				document.getElementById("answer").value = "";
				
				// Get the modal
				modal = document.getElementById("myModal_answer");
				
				//set update answer button for model
				var btn_name = document.getElementById("give_answer_btn"+que_id).innerHTML;
				//alert(btn_name);
				if(btn_name == "Edit Answer"){
					//alert("hello");
					document.getElementById("model_btn_submit").innerHTML = "Update Answer";
					//alert(que_id+" "+faculty_id);
					var xmlhttp = new XMLHttpRequest();
					xmlhttp.open("POST","FetchAnswer?que_id="+que_id+"&admin_id="+faculty_id,false);
					xmlhttp.send(null);
					
					document.getElementById("answer").value = xmlhttp.responseText;
					
					document.getElementById("model_btn_submit").setAttribute("onClick","updateAnswer("+que_id+")" );
					
				}
				
				
				// Get the <span> element that closes the modal
				var span = document.getElementsByClassName("close_answer")[0];
				
				// When the user clicks the button, open the modal 
				modal.style.display = "block";
				
				// When the user clicks on <span> (x), close the modal
				span.onclick = function() {
				  modal.style.display = "none";
				}
				
				// When the user clicks anywhere outside of the modal, close it
				window.onclick = function(event) {
				  if (event.target == modal) {
					modal.style.display = "none";
				  }
				}
			}
                        
                        //this function submit answer into database
			function submitAnswer(){
				var dept_id = document.getElementById("dept_id").value;
				var faculty_id = document.getElementById("faculty_id").value;
				var answer_text = document.getElementById("answer").value;
				
				var answer_length = answer_text.trim().length;
				
				//alert(answer_length);
				
				if(answer_text == ""){
				  document.getElementById("promptAnswer").innerHTML = "<h6 style='color:red;'>Answer Can not be empty</h6>";	
				}else if(answer_length < 5){
				  document.getElementById("promptAnswer").innerHTML = "<h6 style='color:red;'>Answer length must be greater than 4 characters</h6>";	
				}else{
				
					if (confirm("Are You Sure?")) {
						
						modal.style.display = "none";
						var xmlhttp = new XMLHttpRequest();
						xmlhttp.open("POST","SubmitAnswer?answer="+answer_text+"&que_id="+que_id+"&admin_id="+faculty_id,false);
						xmlhttp.send(null);
						//alert(que_id);
						fetchAllQuestion();
						document.getElementById(que_id).innerHTML = xmlhttp.responseText;
					}
				}
						
			}
                        
                        var question_id;
			//this function update answer into database
			function updateAnswer(que_id){
				question_id = que_id;
				var dept_id = document.getElementById("dept_id").value;
				var faculty_id = document.getElementById("faculty_id").value;
				var answer_text = document.getElementById("answer").value;
				
				var answer_length = answer_text.trim().length;
				
				//alert(answer_length);
				
				if(answer_text == ""){
				  document.getElementById("promptAnswer").innerHTML = "<h6 style='color:red;'>Answer Can not be empty</h6>";	
				}else if(answer_length < 5){
				  document.getElementById("promptAnswer").innerHTML = "<h6 style='color:red;'>Answer length must be greater than 4 characters</h6>";	
				}else{
				
					if (confirm("Are You Sure?")) {
					
						modal.style.display = "none";
						var xmlhttp = new XMLHttpRequest();
						xmlhttp.open("POST","UpdateAnswer?answer="+answer_text+"&que_id="+que_id+"&admin_id="+faculty_id,false);
						xmlhttp.send(null);
						//alert(que_id);
						//alert(xmlhttp.responseText);
						fetchAllQuestion();
						document.getElementById(que_id).innerHTML = xmlhttp.responseText;
					}
				}
						
			}
			
                        
                        //This function block ustudent
			function blockUser(user_name){
				//alert(user_name);	
				
				if (confirm("Are You Sure?")) {
					
					
					//alert(user_name);
					var xmlhttp = new XMLHttpRequest();
					xmlhttp.open("POST","BlockUserServlet?user_name="+user_name,false);
					xmlhttp.send(null);
					//alert(user_name_and_que_id[0]);
					
                                        alert(xmlhttp.responseText);
					
					fetchAllQuestion();
					
				}
			}
                        
                        
			//Ths function clear answer from popup model
			function clearAnswer(){
				//alert(document.getElementById("answer").value);
				document.getElementById("answer").value = "";
			}
			
		</script>
	</body>
</html>
