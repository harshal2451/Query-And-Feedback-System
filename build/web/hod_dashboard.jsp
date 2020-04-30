<%-- 
    Document   : hod_dashboard.jsp
    Created on : 11 Apr, 2020, 8:15:23 PM
    Author     : Sagar Naik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    table{
			font-size:16px;
		}
    .pull-left, #main{
        background: #f8f8f8;
    }
    body{ background: #f8f8f8; }
        #main11{
            padding: 10px 15px;
            background: #fff;
            transition: opacity 2s;
            box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -moz-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -webkit-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -o-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
          -ms-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);
            border-radius: 20px;
            top: 20%;
            left: 27%;
            position: fixed;
          -moz-border-radius: 20px;
            -webkit-border-radius: 20px;
          -o-border-radius: 20px;
          -ms-border-radius: 20px;
            border-radius: 20px;
            z-index: 20;
        }
</style>
    <div class="container-fluid" id="content">
		
		<div id="main11" style="margin:1px">
			<div class="container-fluid">
				<center><div id="facultyList" style=" width:100%;padding-top:20px;padding-bottom:20px;background: #fff; z-index:22;"></div></center>
			</div>
            
         </div>
          
    </div>
            <script>
                //alert("called");
                var xmlhttp = new XMLHttpRequest();
				xmlhttp.open("POST","HodDashboard",false);
				xmlhttp.send(null);
                //alert(xmlhttp.responseText);
				document.getElementById("facultyList").innerHTML = xmlhttp.responseText;
    
                </script>


