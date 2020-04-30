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
	<!-- Favicon -->
	<link rel="shortcut icon" href="img/favicon.ico" />
	<!-- Apple devices Homescreen icon -->
	<link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-precomposed.png" />
	
     
    <style>
		body {font-family: Arial, Helvetica, sans-serif;}
       
		table{
			font-size:16px;
		}
        tr{
            border-bottom: 1px solid lightgrey;
        }
        #main{
            display: none;
            box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -moz-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -webkit-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -o-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -ms-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
            border-radius: 20px;
            top: 10%;
            left: 10%;
            position: absolute;
          -moz-border-radius: 20px;
            -webkit-border-radius: 20px;
          -o-border-radius: 20px;
          -ms-border-radius: 20px;
            border-radius: 20px;
            z-index: 20;
            width: auto;
        }
        
        .model1{
            display: none;
            text-align: center;
            background: #fff;
            margin-top: 17px;
            box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -moz-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -webkit-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -o-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -ms-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
            border-radius: 20px;
            top: 13%;
            left: 55%;
            position: absolute;
          -moz-border-radius: 20px;
            -webkit-border-radius: 20px;
          -o-border-radius: 20px;
          -ms-border-radius: 20px;
            border-radius: 20px;
            z-index: 20;
            width: auto;
            padding: 10px 15px 20px 15px;
        }
        
        .note{
            text-align: center;
            color: lightgray;
            margin-top: 7px;
        }
        
        .model1-header{
            text-align: center;
            padding: 10px 150px;
            border-bottom: 1px solid lightgrey;
        }
	</style>
</head>

<body style="background:#f8f8f8;" onLoad="fetchReport()">
	
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

                int dept_id = result_admin_id.get(0).getDepartment().getDeptId();
                

            %>
	</div><%@ include file="scroll.html" %>
    
	<div class="container-fluid" id="content">
		
        <input type="hidden" id="dept_id" value="<%= dept_id %>"/>
                
		<div id="main" style="margin:1px;margin-top:40px;">
			<div class="container-fluid">
				<center><div id="performence" style=" width:100%; background: #fff; z-index:22;"></div></center>
                <div class="note">*This table is based on the feedback(s) you have got from students.</div>  
			</div>
            
         </div>
    </div>
       
    <div class="model1" id="model1">
        <div class="container-fluid"><center>
        <div class="model1-body" id="model1-body" style=" width:100%; background: #fff; z-index:22;"></div>
        <div class="model1-footer"></div>
            </center>
        </div>
    </div>
     
										
		<script type="text/javascript">
           
			$("#link_my_performance").addClass('active');
			var _gaq = _gaq || [];
			_gaq.push(['_setAccount', 'UA-38620714-4']);
			_gaq.push(['_trackPageview']);
	
			(function() {
				var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
				ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
				var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
			})();
			
			function fetchReport(){
				//Score
				var dept_id = document.getElementById("dept_id").value;
				var xmlhttp = new XMLHttpRequest();
				xmlhttp.open("POST","FetchFacultyMyReport?dept_id="+dept_id,false);
				xmlhttp.send(null);
                                //alert(xmlhttp.responseText);
				document.getElementById("performence").innerHTML = xmlhttp.responseText;
                                document.getElementById("main").style.display = "block";
                                //QNA
                                var xmlhttp = new XMLHttpRequest();
                                xmlhttp.open("POST","FetchFacultyQna",false);
				xmlhttp.send(null);
                                //alert(xmlhttp.responseText);
				document.getElementById("model1-body").innerHTML = xmlhttp.responseText;
                                document.getElementById("model1").style.display="block";
			}
		</script>

    </body>
<!-- Mirrored from www.eakroko.de/flat/ by HTTrack Website Copier/3.x [XR&CO'2010], Fri, 24 Jan 2014 12:47:08 GMT -->
</html>

