package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/welcome_header.html");
    _jspx_dependants.add("/scroll.html");
  }

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
      out.write("\n");
  out.print("<h1>"+session.getAttribute("user_id")+"</h1>");
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
      out.write("<html class=\"no-js\"> <!--<![endif]-->\n");
      out.write("  <head>\n");
      out.write("\t<meta charset=\"utf-8\">\n");
      out.write("\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\" />\n");
      out.write("\t<!-- Apple devices fullscreen -->\n");
      out.write("\t<meta name=\"apple-mobile-web-app-capable\" content=\"yes\" />\n");
      out.write("\t<!-- Apple devices fullscreen -->\n");
      out.write("\t<meta names=\"apple-mobile-web-app-status-bar-style\" content=\"black-translucent\" />\n");
      out.write("\t\n");
      out.write("\t<title>Admin - Dashboard</title>\n");
      out.write("\n");
      out.write("\t<!-- Bootstrap -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\n");
      out.write("\t<!-- Bootstrap responsive -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/bootstrap-responsive.min.css\">\n");
      out.write("\t<!-- jQuery UI -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/plugins/jquery-ui/smoothness/jquery-ui.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/plugins/jquery-ui/smoothness/jquery.ui.theme.css\">\n");
      out.write("\t<!-- PageGuide -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/plugins/pageguide/pageguide.css\">\n");
      out.write("\t<!-- Fullcalendar -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/plugins/fullcalendar/fullcalendar.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/plugins/fullcalendar/fullcalendar.print.css\" media=\"print\">\n");
      out.write("\t<!-- chosen -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/plugins/chosen/chosen.css\">\n");
      out.write("\t<!-- select2 -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/plugins/select2/select2.css\">\n");
      out.write("\t<!-- icheck -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/plugins/icheck/all.css\">\n");
      out.write("\t<!-- Theme CSS -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("\t<!-- Color CSS -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/themes.css\">\n");
      out.write("    <!-- feedback css -->\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/feedback.css\">\n");
      out.write("\n");
      out.write("\t<!-- jQuery -->\n");
      out.write("\t<script src=\"js/jquery.min.js\"></script>\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t<!-- Nice Scroll -->\n");
      out.write("\t<script src=\"js/plugins/nicescroll/jquery.nicescroll.min.js\"></script>\n");
      out.write("\t<!-- jQuery UI -->\n");
      out.write("\t<script src=\"js/plugins/jquery-ui/jquery.ui.core.min.js\"></script>\n");
      out.write("\t<script src=\"js/plugins/jquery-ui/jquery.ui.widget.min.js\"></script>\n");
      out.write("\t<script src=\"js/plugins/jquery-ui/jquery.ui.mouse.min.js\"></script>\n");
      out.write("\t<script src=\"js/plugins/jquery-ui/jquery.ui.draggable.min.js\"></script>\n");
      out.write("\t<script src=\"js/plugins/jquery-ui/jquery.ui.resizable.min.js\"></script>\n");
      out.write("\t<script src=\"js/plugins/jquery-ui/jquery.ui.sortable.min.js\"></script>\n");
      out.write("\t<!-- Touch enable for jquery UI -->\n");
      out.write("\t<script src=\"js/plugins/touch-punch/jquery.touch-punch.min.js\"></script>\n");
      out.write("\t<!-- slimScroll -->\n");
      out.write("\t<script src=\"js/plugins/slimscroll/jquery.slimscroll.min.js\"></script>\n");
      out.write("\t<!-- Bootstrap -->\n");
      out.write("\t<script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("\t<!-- vmap -->\n");
      out.write("\t<script src=\"js/plugins/vmap/jquery.vmap.min.js\"></script>\n");
      out.write("\t<script src=\"js/plugins/vmap/jquery.vmap.world.js\"></script>\n");
      out.write("\t<script src=\"js/plugins/vmap/jquery.vmap.sampledata.js\"></script>\n");
      out.write("\t<!-- Bootbox -->\n");
      out.write("\t<script src=\"js/plugins/bootbox/jquery.bootbox.js\"></script>\n");
      out.write("\t<!-- Flot -->\n");
      out.write("\t<script src=\"js/plugins/flot/jquery.flot.min.js\"></script>\n");
      out.write("\t<script src=\"js/plugins/flot/jquery.flot.bar.order.min.js\"></script>\n");
      out.write("\t<script src=\"js/plugins/flot/jquery.flot.pie.min.js\"></script>\n");
      out.write("\t<script src=\"js/plugins/flot/jquery.flot.resize.min.js\"></script>\n");
      out.write("\t<!-- imagesLoaded -->\n");
      out.write("\t<script src=\"js/plugins/imagesLoaded/jquery.imagesloaded.min.js\"></script>\n");
      out.write("\t<!-- PageGuide -->\n");
      out.write("\t<script src=\"js/plugins/pageguide/jquery.pageguide.js\"></script>\n");
      out.write("\t<!-- FullCalendar -->\n");
      out.write("\t<script src=\"js/plugins/fullcalendar/fullcalendar.min.js\"></script>\n");
      out.write("\t<!-- Chosen -->\n");
      out.write("\t<script src=\"js/plugins/chosen/chosen.jquery.min.js\"></script>\n");
      out.write("\t<!-- select2 -->\n");
      out.write("\t<script src=\"js/plugins/select2/select2.min.js\"></script>\n");
      out.write("\t<!-- icheck -->\n");
      out.write("\t<script src=\"js/plugins/icheck/jquery.icheck.min.js\"></script>\n");
      out.write("    <!-- feedback js -->\n");
      out.write("    <script src=\"js/feedback.js\"></script>\n");
      out.write("\t<!-- Theme framework -->\n");
      out.write("\t<script src=\"js/eakroko.min.js\"></script>\n");
      out.write("\t<!-- Theme scripts -->\n");
      out.write("\t<script src=\"js/application.min.js\"></script>\n");
      out.write("\t<!-- Just for demonstration -->\n");
      out.write("\t<script src=\"js/demonstration.min.js\"></script>\n");
      out.write("\t\n");
      out.write("\t<!--[if lte IE 9]>\n");
      out.write("\t\t<script src=\"js/plugins/placeholder/jquery.placeholder.min.js\"></script>\n");
      out.write("\t\t<script>\n");
      out.write("\t\t\t$(document).ready(function() {\n");
      out.write("\t\t\t\t$('input, textarea').placeholder();\n");
      out.write("\t\t\t});\n");
      out.write("\t\t</script>\n");
      out.write("\t<![endif]-->\n");
      out.write("\n");
      out.write("\t<!-- Favicon -->\n");
      out.write("\t<link rel=\"shortcut icon\" href=\"img/favicon.ico\" />\n");
      out.write("\t<!-- Apple devices Homescreen icon -->\n");
      out.write("\t<link rel=\"apple-touch-icon-precomposed\" href=\"img/apple-touch-icon-precomposed.png\" />\n");
      out.write("\n");
      out.write("    <title>Query Handler | Feedback System</title>\n");
      out.write("    \n");
      out.write("  <link rel=\"stylesheet\" href=\"plugins/bootstrap/bootstrap.min.css\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"plugins/animate-css/animate.css\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("    \n");
      out.write("    <style>\n");
      out.write("      \n");
      out.write("        body {font-family: Arial, Helvetica, sans-serif;}\n");
      out.write("        \n");
      out.write("        .navigation{\n");
      out.write("            padding-top: 10px;\n");
      out.write("            padding-bottom: 10px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        p{\n");
      out.write("            font-size: 18px;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body id=\"body\">\n");
      out.write("\n");
      out.write("<!-- Header Start -->\n");
      out.write("<div id=\"navigation\"  style=\"position:fixed;width:100%;\">\n");
      out.write("\n");
      out.write("    ");
      out.write("<div class=\"container-fluid\" >\n");
      out.write("\t\t\t<a href=\"#\" id=\"brand\">Query Handler <br> & Feedback System</a>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<ul class='main-nav' style=\"padding-top:25px; margin-left:50px; font-size:18px;\">\n");
      out.write("\t\t\t\t<li id=\"link_dashboard\">\n");
      out.write("\t\t\t\t\t<a href=\"#introduction\">\n");
      out.write("\t\t\t\t\t\t<span>Home</span>\n");
      out.write("\t\t\t\t\t</a>\n");
      out.write("\t\t\t\t</li>\n");
      out.write("\t\t\t\n");
      out.write("                <li id=\"link_faculty_performance\">\n");
      out.write("\t\t\t\t\t<a href=\"#aboutus\">\n");
      out.write("\t\t\t\t\t\t<span>About Us</span>\n");
      out.write("\t\t\t\t\t</a>\n");
      out.write("\t\t\t\t</li>\n");
      out.write("                \n");
      out.write("                 <li>\n");
      out.write("\t\t\t\t\t<a href=\"login.jsp\">\n");
      out.write("\t\t\t\t\t\t<span>Sign In</span>\n");
      out.write("\t\t\t\t\t</a>\n");
      out.write("\t\t\t\t</li>\n");
      out.write("     \n");
      out.write("\t\t\t</ul>\n");
      out.write("\t\t\t\n");
      out.write("</div>");
      out.write("\n");
      out.write("    \n");
      out.write("</div>\n");
      out.write("<style>\n");
      out.write("    #myBtn {\n");
      out.write("      display: none;\n");
      out.write("      position: fixed;\n");
      out.write("      bottom: 20px;\n");
      out.write("      right: 30px;\n");
      out.write("      z-index: 99;\n");
      out.write("      font-size: 18px;\n");
      out.write("      border: none;\n");
      out.write("      outline: none;\n");
      out.write("      background-color: darkgray;\n");
      out.write("      color: white;\n");
      out.write("      cursor: pointer;\n");
      out.write("      padding: 15px;\n");
      out.write("      border-radius: 4px;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    #myBtn:hover {\n");
      out.write("      background-color: grey;\n");
      out.write("    }\n");
      out.write("</style>\n");
      out.write("\n");
      out.write("<button onclick=\"topFunction()\" id=\"myBtn\" title=\"Go to top\">Top</button>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("//Get the button\n");
      out.write("var mybutton = document.getElementById(\"myBtn\");\n");
      out.write("\n");
      out.write("// When the user scrolls down 20px from the top of the document, show the button\n");
      out.write("window.onscroll = function() {scrollFunction()};\n");
      out.write("\n");
      out.write("function scrollFunction() {\n");
      out.write("  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {\n");
      out.write("    mybutton.style.display = \"block\";\n");
      out.write("  } else {\n");
      out.write("    mybutton.style.display = \"none\";\n");
      out.write("  }\n");
      out.write("}\n");
      out.write("\n");
      out.write("// When the user clicks on the button, scroll to the top of the document\n");
      out.write("function topFunction() {\n");
      out.write("  document.body.scrollTop = 0;\n");
      out.write("  document.documentElement.scrollTop = 0;\n");
      out.write("}\n");
      out.write("</script>");
      out.write("\n");
      out.write("\t<div class=\"container\">\n");
      out.write("\t\t<div class=\"row\">\n");
      out.write("\t\t\t<div class=\"col-md-12\">\n");
      out.write("\t\t\t\t<!-- header Nav Start -->\n");
      out.write("\t\t\t\t<nav class=\"navbar\">\n");
      out.write("\t\t\t\t\t<div class=\"container-fluid\">\n");
      out.write("\t\t\t\t\t\t<!-- Brand and toggle get grouped for better mobile display -->\n");
      out.write("\t\t\t\t\t\t<div class=\"navbar-header\">\n");
      out.write("\t\t\t\t\t\t\t<button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">\n");
      out.write("\t\t\t\t\t\t\t<span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("\t\t\t\t\t\t\t<span class=\"icon-bar\"></span>\n");
      out.write("\t\t\t\t\t\t\t<span class=\"icon-bar\"></span>\n");
      out.write("\t\t\t\t\t\t\t<span class=\"icon-bar\"></span>\n");
      out.write("\t\t\t\t\t\t\t</button>\n");
      out.write("\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\t\t\t</nav>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t</header>\n");
      out.write("\n");
      out.write("<!-- Slider Start -->\n");
      out.write("<section class=\"slider\">\n");
      out.write("\t<div class=\"\">\n");
      out.write("\t\t<div class=\"row\">\n");
      out.write("\t\t\t<div class=\"col-md-12\">\n");
      out.write("\t\t\t\t<div class=\"block\">\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</section>\n");
      out.write("\n");
      out.write("<section id=\"introduction\" class=\"about section\" >\n");
      out.write("\t<div class=\"container\">\n");
      out.write("\t\t<div class=\"row\">\n");
      out.write("\t\t\t<div class=\"col-md-7 col-sm-12\">\n");
      out.write("\t\t\t\t<div class=\"block\">\n");
      out.write("\t\t\t\t\t<div class=\"section-title\">\n");
      out.write("                        <br><br><br>\n");
      out.write("\t\t\t\t\t\t<h2 >Introduction</h2>\n");
      out.write("                        \n");
      out.write("                        <p>To better improve student knowledge Query Handler & Feedback System is introduced.A class of 60 minutes would not\n");
      out.write("be enough to answer every question.<br><br> “What if a students can ask a question outside the class ?” or\n");
      out.write("“What if a student can get answer even after class ?” or “What if a faculty can give answers for\n");
      out.write("appropriate question outside the class and also could improve one’s skill ?” or “How about\n");
      out.write("HOD(Head Of Department) view staff performance based on student reviews ?”. The Query and\n");
      out.write("Feedback System would provide improvement in student knowledge and ease in studies , this\n");
      out.write("system would have question and answer portal for student queries and faculty could review one’s\n");
      out.write("self and improve his/her teaching skills.</p>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div><!-- .col-md-7 close -->\n");
      out.write("\t\t\t<div class=\"col-md-5 col-sm-12\">\n");
      out.write("\t\t\t\t<div class=\"block\">\n");
      out.write("                  \n");
      out.write("\t\t\t\t\t<img src=\"images/query.jpg\" alt=\"Query Handler\" style=\"width:250px;  margin:100px 80px;\">\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</section>\n");
      out.write("    <hr>\n");
      out.write("<section id=\"aboutus\" class=\"about section \">\n");
      out.write("\t<div class=\"container\">\n");
      out.write("\t\t<div class=\"row\"> \n");
      out.write("\t\t\t<div class=\"col-md-7 col-sm-12\">\n");
      out.write("\t\t\t\t<div class=\"block\">\n");
      out.write("\t\t\t\t\t<div class=\"section-title\">\n");
      out.write("                        <br><br><br>\n");
      out.write("\t\t\t\t\t\t<h2 >About Us</h2>\n");
      out.write("\t\t\t\t\t\t<p>Prepared By :-</p>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("                    <h5> Aamir Gagan (180423107003)</h5>\n");
      out.write("                    <h5> Rushabh Ghoghari (180423107004)</h5>\n");
      out.write("                    <h5> Ridham Mangukiya (180423107008)</h5>\n");
      out.write("                    <h5> Sagar Naik (180423107009)</h5>\n");
      out.write("                    <h5> Harshal Patil (180423107011)</h5>\n");
      out.write("                    \n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div><!-- .col-md-7 close -->\n");
      out.write("\t\t\t<div class=\"col-md-5 col-sm-12\">\n");
      out.write("\t\t\t\t<div class=\"block\">\n");
      out.write("\t\t\t\t\t<img src=\"images/wrapper-img.png\" alt=\"Img\">\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</section>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    \n");
      out.write("    </body>\n");
      out.write("\n");
      out.write("    </html>\n");
      out.write("   ");
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
