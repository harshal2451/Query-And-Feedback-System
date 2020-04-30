<%-- 
    Document   : student_header
    Created on : Apr 10, 2020, 12:26:07 AM
    Author     : Harshal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
                <div class="container-fluid">
			<a href="#" id="brand">Student Corner</a>
			
			<ul class='main-nav'>
				<li id="link_dashboard">
					<a href="home_page.jsp">
						<span>Dashboard</span>
					</a>
				</li>
				<li id="link_question">
				<a href="#" data-toggle="dropdown" class='dropdown-toggle'>
						<span>Questions</span>
						<span class="caret"></span>
				</a>
				<ul class="dropdown-menu" style="z-index:2">
						<li>
							<a href="ask_question.jsp">Ask Question</a>
						</li>									
				</ul>
				</li>
				
                                <li id="link_my_questions">
					<a href="my_questions.jsp">
						<span>My Questions</span>
					</a>
				</li>
                
                                <li id="link_give_feedback">
					<a href="facultyFeedback.jsp">
						<span>Give Feedback</span>
					</a>
				</li>
                
				<li id="link_logout">
					<a href="logout.jsp">
						<span>Log out</span>
					</a>
				</li>
		
			</ul>
			
		</div>