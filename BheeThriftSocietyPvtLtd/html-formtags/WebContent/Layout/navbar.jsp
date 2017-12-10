<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%@page import="Model.Bean.UserBean"%>
<%
UserBean user=null;
try{
	if(request.getSession(false).getAttribute("userbean")==null){
	response.sendRedirect("logout.jsp");
}

	
user=(UserBean)session.getAttribute("userbean");

if(user==null){
	response.sendRedirect("logout.jsp");
}else{
	
	
	/* if(session.getAttribute("openday")==null){

	response.sendRedirect("ad_open_days.jsp");
	} */
%>
	<!-- BEGIN TOP NAVIGATION BAR -->
	<div class="header-inner">

		<!-- BEGIN HORIZANTAL MENU -->
		<div class="hor-menu hidden-sm hidden-xs">
		<%
		ArrayList<MenuBean> list=(ArrayList<MenuBean>) session.getAttribute("menubean");
		if(list!=null){
		%>   
			<ul class="nav navbar-nav">
			<%
			int i = 0;
			for(MenuBean menu:list){ i++;
			//classic-menu-dropdown active
			%>
			<li>
					<a href="AdMenu?menu=<%=menu.getAd_menu_id() %>"><%=menu.getName() %> 	<span class="selected">	</span>	</a>
			</li>
			<%}} %>
				
			</ul>
		</div>
		<!-- END HORIZANTAL MENU -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
			<img src="assets/img/menu-toggler.png" alt=""/>
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN TOP NAVIGATION MENU -->
		<ul class="nav navbar-nav pull-right">
			<!-- BEGIN USER LOGIN DROPDOWN -->
			<li class="dropdown user">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<i class="fa fa-user"></i>
					<span class="username hidden-1024">
						 Setting
					</span>
					<i class="fa fa-angle-down"></i>
				</a>
				<ul class="dropdown-menu">
					<li>
						<a href="#">
							<i class="fa fa-user"></i> My Profile
						</a>
					</li>
					<li class="divider">
					</li>
					<li>
						<a href="javascript:;" id="trigger_fullscreen">
							<i class="fa fa-arrows"></i> Full Screen
						</a>
					</li>
					<li>
						<a href="logout.jsp">
							<i class="fa fa-key"></i> Log Out
						</a>
					</li>
				</ul>
			</li>
			<!-- END USER LOGIN DROPDOWN -->
		</ul>
		<!-- END TOP NAVIGATION MENU -->
	</div>
	<!-- END TOP NAVIGATION BAR -->
<% }}catch(Exception e){
	e.printStackTrace();
}  %>
<div class="clearfix">
</div>