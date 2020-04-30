<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	
    String student = "STUDENT",faculty = "FACULTY",hod = "HOD";
                
    if(session.getAttribute("user_type") != null){
       // out.println("Harsh"+(String)session.getAttribute("user_type"));
        //log out for hod
	if(session.getAttribute("user_type").equals(hod))
            session.removeAttribute("user_hod");
        
        //log out for faculty
        else if(session.getAttribute("user_type").equals(faculty))
            session.removeAttribute("user_faculty");
        
        //log out for student
        else if(session.getAttribute("user_type").equals(student))
            session.removeAttribute("user_student");
	
        session.removeAttribute("user_type");
        
        if(session.getAttribute("user_student") == null)
            out.print("hr");
        else
            out.print("hl");
        //out.println("Harshal"+(String)session.getAttribute("user_student"));
        response.sendRedirect("login.jsp");
    }
	
%>
