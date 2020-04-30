<%-- 
    Document   : index.jsp
    Created on : Apr 13, 2020, 11:34:02 PM
    Author     : Harshal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  out.print("<h1>"+session.getAttribute("user_id")+"</h1>");
    //check session is exist or not
    if(session.getAttribute("user_hod") != null){
        response.sendRedirect("home_page.jsp");
    }
    
    else if(session.getAttribute("user_faculty") != null){
        response.sendRedirect("home_page.jsp");
    }
    
    else if(session.getAttribute("user_student") != null && session.getAttribute("user_id") != null){
        response.sendRedirect("home_page.jsp");
    }
    
%>
<html class="no-js"> <!--<![endif]-->
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

    <title>Query Handler | Feedback System</title>
    
  <link rel="stylesheet" href="plugins/bootstrap/bootstrap.min.css">
  <link rel="stylesheet" href="plugins/animate-css/animate.css">
  <link rel="stylesheet" href="css/style.css">
    
    <style>
      
        body {font-family: Arial, Helvetica, sans-serif;}
        
        .navigation{
            padding-top: 10px;
            padding-bottom: 10px;
        }
        
        p{
            font-size: 18px;
        }
    </style>

</head>

<body id="body">

<!-- Header Start -->
<div id="navigation"  style="position:fixed;width:100%;">

    <%@ include file="welcome_header.html" %>
    
</div>
<%@ include file="scroll.html" %>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<!-- header Nav Start -->
				<nav class="navbar">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							</button>
							
						</div>
			
						</nav>
					</div>
				</div>
			</div>
			</header>

<!-- Slider Start -->
<section class="slider">
	<div class="">
		<div class="row">
			<div class="col-md-12">
				<div class="block">
				
					
				</div>
			</div>
		</div>
	</div>
</section>

<section id="introduction" class="about section" >
	<div class="container">
		<div class="row">
			<div class="col-md-7 col-sm-12">
				<div class="block">
					<div class="section-title">
                        <br><br><br>
						<h2 >Introduction</h2>
                        
                        <p>To better improve student knowledge Query Handler & Feedback System is introduced.A class of 60 minutes would not
be enough to answer every question.<br><br> “What if a students can ask a question outside the class ?” or
“What if a student can get answer even after class ?” or “What if a faculty can give answers for
appropriate question outside the class and also could improve one’s skill ?” or “How about
HOD(Head Of Department) view staff performance based on student reviews ?”. The Query and
Feedback System would provide improvement in student knowledge and ease in studies , this
system would have question and answer portal for student queries and faculty could review one’s
self and improve his/her teaching skills.</p>
					</div>
                    
                    
				</div>
			</div><!-- .col-md-7 close -->
			<div class="col-md-5 col-sm-12">
				<div class="block">
                  
					<img src="images/query.jpg" alt="Query Handler" style="width:250px;  margin:100px 80px;">
				</div>
			</div>
		</div>
	</div>
</section>
    <hr>
<section id="aboutus" class="about section ">
	<div class="container">
		<div class="row"> 
			<div class="col-md-7 col-sm-12">
				<div class="block">
					<div class="section-title">
                        <br><br><br>
						<h2 >About Us</h2>
						<p>Prepared By :-</p>
					</div>
                    <h5> Aamir Gagan (180423107003)</h5>
                    <h5> Rushabh Ghoghari (180423107004)</h5>
                    <h5> Ridham Mangukiya (180423107008)</h5>
                    <h5> Sagar Naik (180423107009)</h5>
                    <h5> Harshal Patil (180423107011)</h5>
                    
				</div>
			</div><!-- .col-md-7 close -->
			<div class="col-md-5 col-sm-12">
				<div class="block">
					<img src="images/wrapper-img.png" alt="Img">
				</div>
			</div>
		</div>
	</div>
</section>



    
    </body>

    </html>
   