<%-- 
    Document   : registration
    Created on : 10 Apr, 2020, 12:06:26 PM
    Author     : Sagar Naik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
    
    <!-- Select department -->
    <link rel="stylesheet" href="css/select_dept.css">
    
    <!-- Main css -->
    <link rel="stylesheet" href="css/style_login.css">
    <style>
        #myDIV, #email_err, #myDIV1, #pass_err, #uname_err, #dept_err{
            color: red;
        }
    </style>
</head>
<body onload="getdept()">

    <div class="main">
        <!-- Sign up form -->
        <section class="signup">
            <div class="container" style="margin-top:-100px;">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Sign up</h2>
                        <form method="POST" class="register-form" id="register-form">
                            <div id="fnameDIV"></div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text"  id="fname" placeholder="Your Full Name" />
                            </div>
                            <div id="errorDIV"></div>
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text"  id="name" placeholder="User-Name" />
                            </div>
                            <div id="uname_err"></div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="email" id="email" placeholder="Your Email" required="true"/>
                            </div>
                            <div id="email_err"></div>
                            <div class="form-group" style="margin-top:30px;">
                                <label for="name" id="icon"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <div id="selectDept"></div>        
                            </div>
                            <div id="dept_err"></div>
                            <div class="form-group" style="margin-top:-10px;">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" id="pass" placeholder="Password"/>
                            </div>
                            <div id="pass_err"></div>
                            <div class="form-group">
                                <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" id="re_pass" placeholder="Confirm your password"/>
                            </div>
                            <div id="myDIV"></div>
                           
                            <div id="myDIV1"></div>
                            <div class="form-group form-button">
                                <input type="button" id="signup" class="form-submit" value="Register" onclick="validate()"/>
                                <input type="reset" id="signup" class="form-submit" value="Reset" style="margin-left:40px;"/>
                            </div>
                        </form>
                    </div>
                    <div class="signup-image">
                        <figure><img src="images/signup-image.jpg" alt="sing up image"></figure>
                        <a href="login.jsp" class="signup-image-link">I already have account</a>
                    </div>
                </div>
            </div>
        </section>
    </div>
    
    <script>
        var pass;
        
        function getdept(){
            //alert("SAGAR");
            var a = "register";
            var http = new XMLHttpRequest();
            http.open("POST","getSelectDept?register="+a,false);
            http.send(null);
            //alert(http.responseText);
            document.getElementById("selectDept").innerHTML = http.responseText;
        }
        
        function validate(){
            var fname = document.getElementById("fname").value; 
            var deptId = document.getElementById("dept_id").value;
            var uname = document.getElementById("name").value;
            var email = document.getElementById("email").value;
            var pwd = document.getElementById("pass").value;
            var pwd1 = document.getElementById("re_pass").value;
            //alert(uname + email + pwd + pwd1 + deptId);
                if(fname.length != 0){
                    if(isValidUname()){
                        if(isValidEmail()){
                            if(isValidDept()){
                                if(isValidPass()){
                                    if(pwd == pwd1){
                                        document.getElementById("myDIV").innerHTML = ""; 
                                        var xmlhttp = new XMLHttpRequest();
                                        xmlhttp.open("POST","Register?user_name="+uname+"&user_email="+email+"&user_pwd="+pwd+"&dept_id="+deptId+"&user_fname="+fname,false);
                                        xmlhttp.send(null);
                                        document.getElementById("myDIV1").innerHTML = "<h6>"+ xmlhttp.responseText +"</h6>";    
                                        if(xmlhttp.responseText == "Success"){
                                            alert("Registration Success");
                                            window.location.replace("login.jsp");
                                        }
                                        else{
                                            alert(xmlhttp.responseText);
                                            document.getElementById("myDIV1").innerHTML = "<h6>"+ xmlhttp.responseText +"</h6>";    
                                        }
                                    }
                                    else{
                                       document.getElementById("myDIV").innerHTML = "<h6>Password dosen't match</h6>";
                                        document.getElementById("re_pass").focus;
                                    }
                                    
                                }else{document.getElementById("pass").focus;}    
                            }
                            else{document.getElementById("dept_id").focus;}
                        }
                        else{document.getElementById("email").focus;}
                    }
                    else{document.getElementById("name").focus;}
                }
                else{
                    document.getElementById("fnameDIV").innerHTML = "<h6>Full name required!</h6>";
                }
            //var uname = isValidUname();
        }
        
        function isValidEmail(){
            var email = document.getElementById("email").value;
            var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            if(email.length == 0){
                document.getElementById("email_err").innerHTML = "<h6>Email address required!</h6>";
                return false;
            }
            else if(email.match(mailformat)){
                document.getElementById("email_err").innerHTML = "";
                return true;
            }
            else{
                document.getElementById("email_err").innerHTML = "<h6>Invalid email address!</h6>";
                return false;
            }
        }
        
        function isValidUname(){
            var uname = document.getElementById("name").value;
            if(uname.length < 3 || uname.length > 10){
                document.getElementById("uname_err").innerHTML = "<h6>User-name length must be of 3 to 10</h6>";
                return false;
            }
            else {
                document.getElementById("uname_err").innerHTML = "";
                return true;
            }
        }
        
        function isValidDept(){
            var deptId = document.getElementById("dept_id").value;
            if(deptId.length == 0){
                document.getElementById("dept_err").innerHTML = "<h6>Department id required!</h6>";
                return false;
            }
            else{
                document.getElementById("dept_err").innerHTML = "";
                return true;
            }
                
        }
        
        function isValidPass(){
            var lowerCaseLetters = /[a-z]/g;
            var upperCaseLetters = /[A-Z]/g;
            var digits = /[0-9]/g;
            var pwd = document.getElementById("pass").value;
            if(pwd.length < 6 || pwd.length > 12){
                document.getElementById("pass_err").innerHTML="<h6>Password length mush be of 6 to 12</h6>";
                return false;
            }
            else if(pwd.match(upperCaseLetters)){
                if(pwd.match(lowerCaseLetters)){
                    if(pwd.match(digits)){
                        if(pwd.indexOf("#") == -1 && pwd.indexOf("&") == -1){
                            document.getElementById("pass_err").innerHTML="";
                            return true;            
                        }
                        else{
                            document.getElementById("pass_err").innerHTML="<h6>Password should not contain # or &   </h6>";
                            return false;
                        }
                    }        
                }
            }    
            else{
                document.getElementById("pass_err").innerHTML="<h6>Password mush contain atleast 1 capital & small letter,1 digit </h6>";
                return false;
            }
        }
    </script>
  </body>
</html>        