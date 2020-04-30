<%-- 
    Document   : faculty_performance
    Created on : Apr 11, 2020, 9:02:20 AM
    Author     : Harshal
--%>

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
	
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        
        /*download button*/
        .form-submit {
          display: inline-block;
          background: #6dabe4;
          color: #fff;
          border-bottom: none;
          width: auto;
          padding: 5px 10px;
          border-radius: 5px;
          -moz-border-radius: 5px;
          -webkit-border-radius: 5px;
          -o-border-radius: 5px;
          -ms-border-radius: 5px;
          margin-left:500px;
          margin-top:-35px;
          cursor: pointer; 
        }
        
        .form-submit:hover {
            background: #4292dc; 
        }

        
		body {font-family: Arial, Helvetica, sans-serif;}
        .see{
               display: inline-block;
              background: #6dabe4;
              color: #fff;
              border-bottom: none;
              width: auto;
              padding: 15px 39px;
              border-radius: 5px;
              -moz-border-radius: 5px;
              -webkit-border-radius: 5px;
              -o-border-radius: 5px;
              -ms-border-radius: 5px;
              margin-top: 25px;
            cursor: pointer; 
        }
        .see:hover {
            background: #4292dc; 
        }
		table{
			font-size:16px;
		}
        .selec-option{
            background: #fff;
          box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -moz-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -webkit-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -o-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -ms-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
            border-radius: 20px;
          -moz-border-radius: 20px;
            -webkit-border-radius: 20px;
          -o-border-radius: 20px;
          -ms-border-radius: 20px;
            top: 10%;
            left: 38%;
            position: absolute;
            max-width: 700px;
            border-radius: 20px;
            width: 400px;
            z-index: 21;
            transition: margin-left 2s;
        }
        #main{
            opacity: 0;
            transition: opacity 2s;
            box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -moz-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -webkit-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -o-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -ms-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
            border-radius: 20px;
            top: 10%;
            left: 35%;
            position: absolute;
          -moz-border-radius: 20px;
            -webkit-border-radius: 20px;
          -o-border-radius: 20px;
          -ms-border-radius: 20px;
            border-radius: 20px;
            z-index: 20;
        }
        .select-radio{
            margin-left: 20px;
        }
        #commentfetch{
          background: #fff;
            top: 50%;
            left: 20%;
            box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -moz-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -webkit-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -o-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -ms-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
            border-radius: 20px;
          -moz-border-radius: 20px;
            -webkit-border-radius: 20px;
          -o-border-radius: 20px;
          -ms-border-radius: 20px;
            z-index: 18;
            width: auto;
            margin: 0 auto;
            margin-top: 500px;
        }
        #cmtd{
            padding-left: 10px;
            padding-bottom: 10px;
        }
	</style>
</head>

<body style="background:#f8f8f8;" onload="hidecmt()">
	<div>
	<div id="navigation"  style="position:fixed;width:100%;">
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
                String fetch_admin_id = "FROM Admin WHERE admin_uname = '"+session.getAttribute("user_hod")+"'";
                Query query_admin_id = session1.createQuery(fetch_admin_id);
                List<Admin> result_admin_id = query_admin_id.list();
                int dept_id = result_admin_id.get(0).getDepartment().getDeptId();
            %>
	</div><%@ include file="scroll.html" %>
        
    </div>
    <div class="selec-option" id="select-option">
        <div class="select-radio">
            <label style="font-weight: bold;"><h3>Fetch performace according:</h3></label>               
            <div id="radio_err" style="color:red;"></div>
                <h4><label class="radio-inline">
                    <input type="radio" name="experience" id="r1" value="ans_fac" onclick="clickedans()" style="margin-top: -2px;">
                    Asnwer given by faculty. 
                </label>
                <label class="radio-inline">
                    <input type="radio" name="experience" id="r2" value="feedback" onclick="clickedfeed()" style="margin-top: -2px;">
                    Feedback gievn by student. 
                </label>
                </h4>
        </div>
    </div>
	<div class="container-fluid" id="content">
		
        <!---------- send dept id  to javascript-->
        <input type="hidden" id="dept_id" value="<%= dept_id %>"/>
                
		<div id="main" style="margin:1px">
			<div class="container-fluid">
				<center><div id = "report" style="width:auto; background: #fff;"></div></center>
			</div>
         </div>
    </div>							
		<script type="text/javascript">
			$("#link_faculty_performance").addClass('active');
	       var select;
			var _gaq = _gaq || [];
			_gaq.push(['_setAccount', 'UA-38620714-4']);
			_gaq.push(['_trackPageview']);
	
			(function() {
				var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
				ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
				var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
			})();
            
                        var download_select;
			function clickedans(){
                            document.getElementById("commentfetch").style.display = "none";
                            document.getElementById("hidediv").style.display = "none";
                            document.getElementById("commentfetch").innerHTML= "";
                            document.getElementById("radio_err").inngerHTML = "";
                            document.getElementById("select-option").style.marginLeft = "-500px";
                            document.getElementById("main").style.opacity = "1";
                            select = "ans";
                            download_select = select;
                            fetchReport(select);
                        }
                        
			function clickedfeed(){
                            document.getElementById("commentfetch").style.display = "none";
                            document.getElementById("hidediv").style.display = "none";
                            document.getElementById("commentfetch").innerHTML= "";
                            document.getElementById("radio_err").inngerHTML = "";
                            document.getElementById("select-option").style.marginLeft = "-500px";
                            document.getElementById("main").style.opacity = "1";
                            select = "feed";
                            download_select = select;
                            fetchReport(select);
                        }
                        
			function fetchReport(select){
                            var dept_id = document.getElementById("dept_id").value;
                            var xmlhttp = new XMLHttpRequest();
                            xmlhttp.open("POST","FetchReport?dept_id="+dept_id+"&select="+select,false);
                            xmlhttp.send(null);
                            document.getElementById("report").innerHTML = xmlhttp.responseText;
                
			}
                        function getComments(adminId){
                            var selectType = select;
                           // var adminId = document.getElementById("commentget").value;
                            //alert(selectType+adminId);
                            var xmlhttp = new XMLHttpRequest();
                            xmlhttp.open("POST","FetchComment?admin_id="+adminId+"&select="+select,false);
                            xmlhttp.send(null);
                            //alert(xmlhttp.responseText);
                            document.getElementById("commentfetch").style.display = "block";
                            document.getElementById("hidediv").style.display = "block";
                            //alert(xmlhttp.responseText);
                            document.getElementById("commentfetch").innerHTML= xmlhttp.responseText;
                            document.getElementById("gotocomment").scrollIntoView({behavior: "smooth"});
                        }
                        function hidecmt(){
                            document.getElementById("commentfetch").style.display = "none";
                            document.getElementById("hidediv").style.display = "none";
                        }


                        function downloadReport(){
                            var dept_id = document.getElementById("dept_id").value;
                            //alert(download_select);
                            var xmlhttp = new XMLHttpRequest();
                            xmlhttp.open("POST","FetchReport?dept_id="+dept_id+"&download_select="+download_select,false);
                            xmlhttp.send(null);
                            //alert(xmlhttp.responseText);

                        }
		</script>
    <div id="commentfetch" style="padding:50px 0px 50px 0px;">
    </div>
    <div id="hidediv" style="height:500px;"></div>
	</body>
</html>

