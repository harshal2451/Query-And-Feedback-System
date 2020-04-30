package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Action.HibernateUtil;
import org.hibernate.Session;
import Tables.User;
import java.util.List;
import org.hibernate.Query;

public final class home_005fpage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    
    Session session_db = null;
    session_db = HibernateUtil.getSessionFactory().openSession();
    
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
            
            if(!session.getAttribute("user_id").equals(null)){
                out.print("<br><br>"+session.getAttribute("user_id"));
		if(session.getAttribute("user_student").equals(null)){
                    
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "login.jsp", out, false);
      out.write("\n");
      out.write("             ");
 }else{
                    //for blocked student
                    //out.print("<br><br>"+session.getAttribute("user_id"));
                    String fetch_user_name = "FROM User WHERE user_id = "+session.getAttribute("user_id");
                    Query query_result = session_db.createQuery(fetch_user_name);
                    
                    List<User> result = query_result.list();
                    int flag =0;
                        try{
                            if(result.get(0).getUserName().equals(null)){
                                if(session.getAttribute("user_id").equals(result.get(0).getUserId()))
                                    flag = 1;
                            }
                        }
                        catch(NullPointerException e){
                            if(flag == 1)
                                session.removeAttribute("user_id"); 
                        }
                    }
                }
            }else{
                
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "login.jsp", out, false);
      out.write("\n");
      out.write("          ");
}

        }else{
        
            if(session.getAttribute("user_hod") == null)
                response.sendRedirect("login.jsp");

            else if(session.getAttribute("user_faculty") == null)
                response.sendRedirect("login.jsp");

            else if(session.getAttribute("user_student") == null)
                response.sendRedirect("login.jsp");

        }

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
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
      out.write("\n");
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
      out.write("\n");
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
      out.write("\t\n");
      out.write("    <style>\n");
      out.write("        #content{\n");
      out.write("            top:10%;\n");
      out.write("            left:10%;\n");
      out.write("        }\n");
      out.write("        body{\n");
      out.write("            background: #F8F8F8;\n");
      out.write("        }\n");
      out.write("    \n");
      out.write("\t\t#background{\n");
      out.write("            background: white;\n");
      out.write("            text-align: center;\n");
      out.write("            box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);\n");
      out.write("          -moz-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);\n");
      out.write("          -webkit-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);\n");
      out.write("          -o-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);\n");
      out.write("          -ms-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);\n");
      out.write("            border-radius: 20px;\n");
      out.write("          -moz-border-radius: 20px;\n");
      out.write("            -webkit-border-radius: 20px;\n");
      out.write("          -o-border-radius: 20px;\n");
      out.write("          -ms-border-radius: 20px;\n");
      out.write("            z-index: 18;\n");
      out.write("            width: 300px;\n");
      out.write("            height: 170px;\n");
      out.write("            margin-top: 200px;\n");
      out.write("            margin-left: 70px;\n");
      out.write("            display: inline-table;\n");
      out.write("            position: relative;\n");
      out.write("\t\t}\n");
      out.write("\t\t\n");
      out.write("\t\t#background1, #background2{\n");
      out.write("\t\t\tbackground: white;\n");
      out.write("            text-align: center;\n");
      out.write("            box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);\n");
      out.write("          -moz-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);\n");
      out.write("          -webkit-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);\n");
      out.write("          -o-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);\n");
      out.write("          -ms-box-shadow: 0px 15px 16.83px 0.17px rgba(0, 0, 0, 0.05);\n");
      out.write("            border-radius: 20px;\n");
      out.write("          -moz-border-radius: 20px;\n");
      out.write("            -webkit-border-radius: 20px;\n");
      out.write("          -o-border-radius: 20px;\n");
      out.write("          -ms-border-radius: 20px;\n");
      out.write("            z-index: 18;\n");
      out.write("            display: inline-table;\n");
      out.write("            width: 300px;\n");
      out.write("            margin-left: 30px;\n");
      out.write("            position: relative;\n");
      out.write("\t\t}\n");
      out.write("\t\t\n");
      out.write("\t\t#first, #second, #third{\n");
      out.write("\t\t\theight:140px;\n");
      out.write("\t\t\twidth:120px;\n");
      out.write("            margin-top: -60px;\n");
      out.write("\t\t}\n");
      out.write("\t\t\n");
      out.write("        #question{\n");
      out.write("            margin-top: -18px;\n");
      out.write("        }\n");
      out.write("\t</style>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body onLoad=\"fetchDashboardDetail()\">\n");
      out.write("\t\n");
      out.write("\t<div id=\"navigation\" style=\"position:fixed;width:100%;margin-top:-20px;\">\n");
      out.write("            ");

                //String student = "STUDENT",faculty = "FACULTY",hod = "HOD";
                
		if(session.getAttribute("user_type") != null){
		
			if(session.getAttribute("user_type").equals(hod)){
				
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "hod_header.jsp", out, false);
      out.write("\n");
      out.write("                      ");
}                  
			else if(session.getAttribute("user_type").equals(faculty)){
				
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "faculty_header.jsp", out, false);
      out.write("\n");
      out.write("                      ");
}       
			else if(session.getAttribute("user_type").equals(student)){
				
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "student_header.jsp", out, false);
      out.write("\n");
      out.write("                       ");
}
		}
            
      out.write("\n");
      out.write("\t</div>\n");
      out.write("        ");
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
      out.write("\t\n");
      out.write("                <div class=\"page-header\">\n");
      out.write("                        <div class=\"pull-left\"  style=\"margin-top:20px;\">\n");
      out.write("                            <h1>Dashboard</h1>\n");
      out.write("                        </div>\n");
      out.write("                </div>\n");
      out.write("\t\t");

                    //String student = "STUDENT",faculty = "FACULTY",hod = "HOD";

                    if(session.getAttribute("user_type") != null){

                            if(session.getAttribute("user_type").equals(hod)){
                                    
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "hod_dashboard.jsp", out, false);
      out.write("\n");
      out.write("                          ");
}                  
                            else if(session.getAttribute("user_type").equals(faculty)){
                                    
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "faculty_dashboard.jsp", out, false);
      out.write("\n");
      out.write("                          ");
}       
                            else if(session.getAttribute("user_type").equals(student)){
                                    
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "student_dashboard.jsp", out, false);
      out.write("\n");
      out.write("                           ");
}
                    }
                
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t<script type=\"text/javascript\">\n");
      out.write("\t\t\t$(\"#link_dashboard\").addClass('active');\n");
      out.write("\t\n");
      out.write("\t\t\tvar _gaq = _gaq || [];\n");
      out.write("\t\t\t_gaq.push(['_setAccount', 'UA-38620714-4']);\n");
      out.write("\t\t\t_gaq.push(['_trackPageview']);\n");
      out.write("\t\n");
      out.write("\t\t\t(function() {\n");
      out.write("\t\t\t\tvar ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;\n");
      out.write("\t\t\t\tga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';\n");
      out.write("\t\t\t\tvar s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);\n");
      out.write("\t\t\t})();\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\n");
      out.write("\t\t</script>\n");
      out.write("\t</body>\n");
      out.write("</html>\n");
      out.write("\n");
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
