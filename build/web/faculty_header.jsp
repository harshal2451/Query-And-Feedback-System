<%-- 
    Document   : faculty_header
    Created on : Apr 10, 2020, 9:08:40 AM
    Author     : Harshal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container-fluid">
			<a href="#" id="brand">Faculty</a>
			
			<ul class='main-nav'>
				<li id="link_dashboard">
					<a href="home_page.jsp">
						<span>Dashboard</span>
					</a>
				</li>
				<li id="link_give_answer">
				<a href="#" data-toggle="dropdown" class='dropdown-toggle'>
						<span>Questions</span>
						<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
						
						<li>
							<a href="give_answer.jsp">Give Answer</a>
						</li>
        													
				</ul>
				</li>
				
                <li id="link_my_answers">
					<a href="my_answers.jsp">
						<span>My Answers</span>
					</a>
				</li>
                
                <li id="link_my_performance">
					<a href="my_performance.jsp">
						<span>My Performance</span>
					</a>
				</li>
                
                 <li id="link_unblock_user">
					<a href="unblock_user.jsp">
						<span>Unblock User</span>
					</a>
				</li>
                
				<li>
					<a href="logout.jsp">
						<span>Log out</span>
					</a>
				</li>
		
			</ul>
			
		</div>
	</div>
