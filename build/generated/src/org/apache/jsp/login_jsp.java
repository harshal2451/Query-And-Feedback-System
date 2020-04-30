package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write(" \n");
 
    //check session is exist or not
    if(session.getAttribute("user_hod") != null){
        response.sendRedirect("home_page.jsp");
    }
    
    else if(session.getAttribute("user_faculty") != null){
        response.sendRedirect("home_page.jsp");
    }
    
    else if(session.getAttribute("user_student") != null){
        response.sendRedirect("home_page.jsp");
    }
    

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n");
      out.write("    <title>Login</title>\n");
      out.write("\n");
      out.write("    <!-- Font Icon -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"fonts/material-icon/css/material-design-iconic-font.min.css\">\n");
      out.write("\n");
      out.write("    <!-- Main css -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/style_login.css\">\n");
      out.write("    <style>\n");
      out.write("        #myDIV, #uname_err, #pwd_err{\n");
      out.write("            color: red;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"main\">\n");
      out.write("        <!-- Sing in  Form -->\n");
      out.write("        <section class=\"sign-in\">\n");
      out.write("            <div class=\"container\" style=\"margin-top:-100px;\">\n");
      out.write("                <div class=\"signin-content\">\n");
      out.write("                    <div class=\"signin-image\">\n");
      out.write("                        <figure><img src=\"img/signin-image.jpg\" alt=\"sing up image\"></figure>\n");
      out.write("                        <a href=\"registration.jsp\" class=\"signup-image-link\">Create an account</a>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"signin-form\">\n");
      out.write("                        <h2 class=\"form-title\">Sign in</h2>\n");
      out.write("                        <form method=\"POST\" class=\"register-form\" id=\"login-form\">\n");
      out.write("                            <div id=\"myDIV\"></div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label for=\"your_name\"><i class=\"zmdi zmdi-account material-icons-name\"></i></label>\n");
      out.write("                                <input type=\"text\" id=\"your_name\" placeholder=\"Your Name\" oninput=\"unamein()\"/>\n");
      out.write("                            </div>\n");
      out.write("                            <div id=\"uname_err\"></div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label for=\"your_pass\"><i class=\"zmdi zmdi-lock\"></i></label>\n");
      out.write("                                <input type=\"password\"  id=\"your_pass\" placeholder=\"Password\" oninput=\"pwdin()\"/>\n");
      out.write("                            </div>\n");
      out.write("                            <div id=\"pwd_err\"></div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <input type=\"checkbox\" name=\"remember-me\" id=\"remember-me\" class=\"agree-term\" />\n");
      out.write("                                <label for=\"remember-me\" class=\"label-agree-term\"><span><span></span></span>Remember me</label>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group form-button\">\n");
      out.write("                                <input type=\"button\" name=\"login\" id=\"signin\" class=\"form-submit\" value=\"Log in\" onClick=\"validate()\"/>\n");
      out.write("                                <input type=\"reset\" name=\"login\" id=\"signin\" class=\"form-submit\" value=\"Reset\" style=\"margin-left:40px;\"/>\n");
      out.write("                            </div>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </section>\n");
      out.write("    </div>\n");
      out.write("    <script type=\"text/javascript\">\n");
      out.write("        function unamein(){\n");
      out.write("            document.getElementById(\"uname_err\").innerHTML = \"\";\n");
      out.write("        }\n");
      out.write("        function pwdin(){\n");
      out.write("            document.getElementById(\"pwd_err\").innerHTML = \"\";\n");
      out.write("        }\n");
      out.write("        function validate(){\n");
      out.write("            var textres = \"\";\n");
      out.write("            var uname = document.getElementById(\"your_name\").value;\n");
      out.write("            var pwd = document.getElementById(\"your_pass\").value;\n");
      out.write("            if(uname.length == 0){\n");
      out.write("                document.getElementById(\"uname_err\").innerHTML = \"<h6>Username Required!</h6>\";\n");
      out.write("            }\n");
      out.write("            else if(pwd.length == 0){\n");
      out.write("                document.getElementById(\"pwd_err\").innerHTML = \"<h6>Password Required!</h6>\";\n");
      out.write("            }\n");
      out.write("            else{\n");
      out.write("                //alert(uname);\n");
      out.write("                var xmlhttp = new XMLHttpRequest();\n");
      out.write("                xmlhttp.open(\"POST\",\"checkUser?user_name=\"+uname+\"&user_pwd=\"+pwd,false);\n");
      out.write("                xmlhttp.send(null);\n");
      out.write("                alert(xmlhttp.responseText);\n");
      out.write("                textres = xmlhttp.responseText;\n");
      out.write("                if(textres.match(\"success\")){\n");
      out.write("                    window.location.replace(\"home_page.jsp\");\n");
      out.write("                }else{\n");
      out.write("                    document.getElementById(\"myDIV\").innerHTML = \"<h5>\"+xmlhttp.responseText+\"</h5>\";   \n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
