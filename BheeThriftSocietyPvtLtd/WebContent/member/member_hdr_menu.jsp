<%@page import="Model.Bean.SubMenuBean"%>
<%@page import="Model.Member.Bean.MemberLoginBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%
MemberLoginBean user3=null;
try{
	if(request.getSession(false).getAttribute("MemberLoginBean")==null){
		response.sendRedirect("logout.jsp");
	}
	user3=(MemberLoginBean)session.getAttribute("MemberLoginBean");
	if(user3==null){
		response.sendRedirect("logout.jsp");
	}else{
	%>
		<%
		ArrayList<MenuBean> list=(ArrayList<MenuBean>) session.getAttribute("membermenubean");
		if(list!=null){
		%>
		<!-- BEGIN TOP NAVIGATION BAR -->
		<div class="header-inner">
		<!-- BEGIN HORIZANTAL MENU -->
		<div class="hor-menu hidden-sm hidden-xs">
		<ul class="nav navbar-nav">
		<%for(MenuBean menu:list){%>
		<li><a href="<%=menu.getLink()%>"><%=menu.getName() %></a>
		<ul>
		<%
	 	 ArrayList<SubMenuBean> sublist=(ArrayList<SubMenuBean>) session.getAttribute("membersubmenubean");
	  	if(sublist!=null){
		for(SubMenuBean menu1:sublist){
	    if(menu1.getAd_menu_id()==menu.getAd_menu_id()){
	  %>
	 		<li><a   href="<%=menu1.getLink()%>" style="text-decoration: none;color:black;"><%=menu1.getName() %></a></li>
	  <%}
			  }
		  }%>
		</ul>				
		</li>
		<%}}
	}
}catch(Exception e){
	e.printStackTrace();
} %>
	</ul>
	</div>
		<!-- END HORIZANTAL MENU -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
			<img src="${pageContext.request.contextPath}/assets/img/menu-toggler.png" alt=""/>
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
<div class="clearfix"></div>