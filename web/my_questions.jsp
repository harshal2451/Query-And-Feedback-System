<%-- 
    Document   : my_questions
    Created on : 11 Apr, 2020, 1:20:21 AM
    Author     : Sagar Naik
--%>

<%@page import="Tables.User"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="Action.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String student = "STUDENT",faculty = "FACULTY",hod = "HOD";
    
    if(session.getAttribute("user_type") != null){
    	// login for hod	
	if(session.getAttribute("user_type").equals(hod)){
            if(session.getAttribute("user_hod").equals(null))
		response.sendRedirect("login.jsp");
	}
		
	// login for faculty	
	else if(session.getAttribute("user_type").equals(faculty)){
            if(session.getAttribute("user_faculty").equals(null))
		response.sendRedirect("login.jsp");
	}
			
	else if(session.getAttribute("user_type").equals(student)){
            if(session.getAttribute("user_student").equals(null)){
                out.print("student");
		response.sendRedirect("login.jsp");
            }
              //out.print("stud");
	}
    }else{
       // out.print("HELLO");
        if(session.getAttribute("user_hod") == null)
            response.sendRedirect("login.jsp");
        
        else if(session.getAttribute("user_faculty") == null)
            response.sendRedirect("login.jsp");
        
        else if(session.getAttribute("user_student") == null){
            response.sendRedirect("login.jsp");
        }
    }
%>

<html>
    <head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<!-- Apple devices fullscreen -->
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<!-- Apple devices fullscreen -->
	<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />
	
	<title>Student - Dashboard</title>

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
    <!-- feedback css -->
    <link rel="stylesheet" type="text/css" href="css/feedback.css">

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
    <!-- feedback js -->
    <script src="js/feedback.js"></script>
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
        
        .my_questions{
          transition: margin-left 2s;
          width: 550px;
		  height: auto;
        }
        
        .all{
            
            background-color: black;
            width: auto;
		    height: auto;
        }
        
        .modal_all{
            
		  display: block; /* Hidden by default */
		  z-index:1;
		  width: 550px; /* Full width */
		  height: auto; /* Full height */
		}
        
		/* The Modal (background) */
		.modal {
		  display: block; /* Hidden by default */
		  z-index:1;
          position: relative;
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
        .form-control{
            width: 645  px;
        }
        .ratingDIV, .commentDIV{
            padding: 10px 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid;
        }
	</style>
        <script type="text/javascript">
             <!---------- send student id  to javascript-->
                <% Session session1 = HibernateUtil.getSessionFactory().openSession();
                    String name = (String)session.getAttribute("user_student");
                    Query q = session1.createQuery("FROM User WHERE user_name='"+name+"'");
                    List<User> result = q.list();
                    Integer std_id = result.get(0).getUserId();
                %>
        </script>
</head>

<body onLoad="fetchAllQuestion()">
        <input type="hidden" id="student_id" value="<% out.print(std_id); %>"/>
	<div id="noclickOverlay">
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
            %>
	   </div><%@ include file="scroll.html" %>
	<div class="container-fluid" id="content">
		
		<div id="main" style="margin:1px;margin-top:40px;">
			<div class="container-fluid" >
				<p style="margin-top:30px; text-decoration:underline; font-weight:900;" id="link_all"></p>
                
                
                <!---------- Fetch All Questions of the Student -->
                <div id="adjust1" style="float:left; margin-left:600px;">
                    <div id = "my"></div>
                    <div id="my_questions"></div>
                </div>
                
                <!---------- Fetch All Questions -->
                <div id="adjust2" style=" width: 550px;">
                    <div id="all_question_answer"></div>    
                    <div id="all"></div>
                </div>
                    
                
						
			</div>
            
         </div>
          
    </div>	
    </div>
									
		<script type="text/javascript">
			$("#link_my_questions").addClass('active');
	
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
			var student_id;
            function fetchAllQuestion(){
                                
				student_id = document.getElementById("student_id").value;
				//alert(student_id);
				
				var xmlhttp = new XMLHttpRequest();
				xmlhttp.open("POST","FetchAllQuestions?student_id="+student_id,false);
				xmlhttp.send(null);
				//alert(xmlhttp.responseText);
				document.getElementById("my_questions").innerHTML = xmlhttp.responseText;
                
                                document.getElementById("link_all").innerHTML = "<a id = 'link_all' onclick='fetchAllQA()'>All Questions and Answers</a>";
				
			}
            
            //this function fetch all the question and answer for the student
            function fetchAllQA(){
                document.getElementById("adjust1").style.marginLeft="180px";
                document.getElementById("link_all").style.display = "none";
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST","FetchAllQuestions?all_id="+student_id,false);
                xmlhttp.send(null);
                //alert(xmlhttp.responseText);
                document.getElementById("all").innerHTML = xmlhttp.responseText;
                document.getElementById("adjust2").style.margin = "10px 800px";
                
                document.getElementById("my").innerHTML = "<h3 style='text-align:center; margin-top:32px; '>My Questions</h3>";
                document.getElementById("all_question_answer").innerHTML = "<h3 style='text-align:center; margin-top:32px;'>All Questions</h3>";
            }
			
			//This function fetch answer for particular question
			function getAnswer(que_id){
				var xmlhttp = new XMLHttpRequest();
				xmlhttp.open("POST","FetchAnswer?que_id="+que_id,false);
				xmlhttp.send(null);
				var answers;
                //alert(xmlhttp.responseText);
                document.getElementById(que_id).innerHTML = xmlhttp.responseText;
				document.getElementById("btn_answer").style.pointerEvents = "none";
			}
            
            //This function fetch answer for particular question from all questions panel
			function getAllAnswer(que_id){
				
	
				var xmlhttp = new XMLHttpRequest();
				xmlhttp.open("POST","FetchAnswer?all_que_id="+que_id,false);
				xmlhttp.send(null);
				var answers;
                //alert(xmlhttp.responseText);
                document.getElementById("all"+que_id).innerHTML = xmlhttp.responseText;
				
				//After Getting answer Answer button is removed
				
				//document.getElementById("btn_answer").style.display = "none";*/
				document.getElementById("all_btn_answer").style.pointerEvents = "none";
				//document.getElementById("btn_answer").innerHTML = "Give Feedback";
				//document.getElementById("btn_answer").style.width = "150px";
				//document.getElementById("btn_answer").setAttribute( "onClick", "giveFeedback("+que_id+")" );
			}
			
            var admin_id;
            function giveFeedback(id){
                admin_id = id;
                var overlay = document.getElementById("noclickOverlay");
                var overlay1 = document.getElementById("content");
                overlay.style.opacity = "0.5";
                overlay1.style.opacity = "0.5";
                overlay.style.pointerEvents = "none";
                overlay1.style.pointerEvents = "none";
                
		var formdiv = document.getElementById("model1");
                formdiv.style.display="block";
                document.getElementById("on-click").style.pointerEvents = "none";
                document.getElementById("my_answer").style.pointerEvents = "none";
			}
            function closeClick(){
		document.getElementById("model1").style.display="none";
                var overlay = document.getElementById("noclickOverlay");
                var overlay1 = document.getElementById("content");
                overlay.style.opacity = "1";
                overlay1.style.opacity = "1";
                overlay.style.pointerEvents = "auto";
                overlay1.style.pointerEvents = "auto";
				}
            function submitFeedback1(){
                var que = document.getElementById("btn_answer").value;
                //alert(que);
                submitFeedback(que,admin_id);
            }
            function textin(){
                document.getElementById("comment_err").innerHTML = "";
            }
            function resetclick(){
                document.getElementById("comment_err").innerHTML = "";
                document.getElementById("rating_err").innerHTML = "";
            }
		</script>
    
    <div class="model1" id="model1">
        <div class="model-header1">
            <div class="title"><h2>Feedback</h2> </div>
            <button class="close-btn" id="close_btn" onclick="closeClick()">&times;</button>
        </div>
        <div class="model-body">
        <div style="margin-left: -30px;">Please provide your feedback below: </div>
        <form role="form" method="post" id="reused_form">
            <div class="row">
                <div class="ratingDIV">
                    <label style="font-weight: bold;">How do you rate your overall experience?</label>                
                        <label class="radio-inline">
                            <input type="radio" name="experience" id="r1" value="bad" onclick="clicked()" style="margin-top: -2px;">
                            Bad 
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="experience" id="r2" value="average" onclick="clicked()" style="margin-top: -2px;">
                            Average 
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="experience" id="r3" value="good" onclick="clicked()" style="margin-top: -2px;">
                            Good 
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="experience" id="r4" value="excellent" onclick="clicked()" style="margin-top: -2px;">
                            Excellent 
                        </label>
                    <div id="rating_err"></div>
                </div>
            </div>
            <div class="row">
                <div id="comment_err" style="color:red;"></div>
                <div class="commentDIV">
                    <label for="comments"> Comments:</label>
                <textarea class="form-control" type="textarea" name="comments" id="comments" maxlength="6000" rows="7" draggable="false" style="width: 500px;" oninput="textin()"></textarea>
                </div>
            </div>
            <div class="row">
                <div style="margin-top: 5px; margin-left:200px">
                    <input type="button" class="btn1" value="Submit" onclick="submitFeedback1()">
                    <input type="reset" class="btn1" id="reset" onclick="resetclick()"/>
                </div>
            </div>
        </form>
        </div>
    </div>
	</body>
</html>

