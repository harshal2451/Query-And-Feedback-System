
<%@page import="Tables.Admin"%>
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
            
          width: 550px;
		  height: auto;
          transition: margin-left 2s;
        }
        
        .all{
            
            background-color: black;
            width: 750px;
		    height: auto;
            transition: margin 12s;
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

</head>

<body onLoad="fetchAllQuestion()">
	<div id="noclickOverlay">
	<div id="navigation"  style="position:fixed;width:100%;">
            <%
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
		
		<div id="main" style="margin:1px;margin-top:40px;">
			<div class="container-fluid">
				<p style="margin-top:30px; text-decoration:underline; font-weight:900;" id="link_all"></p>
                
                <!---------- send admin id  to javascript-->
                <input type="hidden" id="admin_id" value="<%= admin_id %>"/>
                
                <!---------- send dept id  to javascript-->
                <input type="hidden" id="dept_id" value="<%= dept_id %>"/>
                
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
			$("#link_my_answers").addClass('active');
	
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
			var admin_id;
                        function fetchAllQuestion(){
				
				admin_id = document.getElementById("admin_id").value;
				//alert(admin_id);
				
				var xmlhttp = new XMLHttpRequest();
				xmlhttp.open("POST","FetchAllQuestions?admin_id="+admin_id,false);
				xmlhttp.send(null);
				//alert(xmlhttp.responseText);
				document.getElementById("my_questions").innerHTML = xmlhttp.responseText;
                
                                document.getElementById("link_all").innerHTML = "<a id = 'link_all' onclick='fetchAllQA()'>All Questions and Answers</a>";
				
			}
			
			//This function fetch answer for particular question
			function getAnswer(que_id){
				var xmlhttp = new XMLHttpRequest();
                                xmlhttp.open("POST","FetchAnswer?question_id="+que_id,false);
				xmlhttp.send(null);
				document.getElementById("btn_answer"+que_id).style.display = "none";
				document.getElementById(que_id).innerHTML = xmlhttp.responseText;
			}
            
                        //this function fetch all the question and answer for the student
                        function fetchAllQA(){

                            document.getElementById("adjust1").style.marginLeft="180px";

                            document.getElementById("link_all").style.display = "none";

                            var dept_id = document.getElementById("dept_id").value;

                            var xmlhttp = new XMLHttpRequest();
                            xmlhttp.open("POST","FetchAllQuestions?all_for_faculty="+admin_id+"&faculty_dept="+dept_id,false);
                            xmlhttp.send(null);
                            //alert(xmlhttp.responseText);
                            document.getElementById("all").innerHTML = xmlhttp.responseText;
                            document.getElementById("adjust2").style.margin = "10px 800px";

                            document.getElementById("my").innerHTML = "<h3 style='text-align:center; margin-top:32px; '>My Answers</h3>";
                            document.getElementById("all_question_answer").innerHTML = "<h3 style='text-align:center; margin-top:32px;'>All Questions</h3>";
                        }
			
                        //This function fetch answer for particular question from all faculty panel
			function getAllAnswer(que_id){
				
                            //alert("que"+que_id);
                            var xmlhttp = new XMLHttpRequest();
                            xmlhttp.open("POST","FetchAnswer?all_que_id="+que_id,false);
                            xmlhttp.send(null);
                            var answers;
                            //alert(xmlhttp.responseText);
                            document.getElementById("all"+que_id).innerHTML = xmlhttp.responseText;
				
                            //After Getting answer Answer button is removed
				
                            document.getElementById("all_btn_answer").style.display = "none";
                            document.getElementById("all_btn_answer").style.pointerEvents = "none";
                            
			}
		</script>
    
    </body>
</html>