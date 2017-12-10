<%@page import="Model.Bean.SubMenuBean"%>
<%@page import="Model.Member.Bean.MemberLoginBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%
MemberLoginBean user4=null;
try{
	if(request.getSession(false).getAttribute("MemberLoginBean")==null){
		response.sendRedirect("logout.jsp");
	}
	user4=(MemberLoginBean)session.getAttribute("MemberLoginBean");
	if(user4==null){
		response.sendRedirect("logout.jsp");
	}else{
	%>
		<%
		ArrayList<MenuBean> list2=(ArrayList<MenuBean>) session.getAttribute("membermenubean");
		if(list2!=null){
		%>
		<div class="page-sidebar navbar-collapse collapse">
		<ul class="page-sidebar-menu" data-auto-scroll="true" data-slide-speed="200">
		<%for(MenuBean menu2:list2){%>
		<li><a href="<%=menu2.getLink()%>"><%=menu2.getName() %></a>
		<ul>
		<%
	 	 ArrayList<SubMenuBean> sublist2=(ArrayList<SubMenuBean>) session.getAttribute("membersubmenubean");
	  	if(sublist2!=null){
		for(SubMenuBean menu3:sublist2){
	    if(menu3.getAd_menu_id()==menu2.getAd_menu_id()){
	  %>
	 		<li><a   href="<%=menu3.getLink()%>" style="text-decoration: none;color:black;"><%=menu3.getName() %></a></li>
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