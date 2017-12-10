<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%@page import="Model.Bean.UserBean"%>
<%

%>
<div class="page-sidebar navbar-collapse collapse">
   <%try{
		if(session.getAttribute("menubean")==null){
			 response.sendRedirect("logout.jsp");
		}else{
		ArrayList<MenuBean> menulist=(ArrayList<MenuBean>) session.getAttribute("menubean");
		if(menulist.isEmpty()!=true){
		%>   
		<ul class="page-sidebar-menu" data-auto-scroll="true" data-slide-speed="200">
			<%
			int i = 0;
			for(MenuBean menu:menulist){ i++;
			//classic-menu-dropdown active
			%>
	 <li>
		 <a href="AdMenu?menu=<%=menu.getAd_menu_id() %>"> <%=menu.getName() %></a>
	 </li>
	 <%}
		}%>
  </ul>
</div>
<%
	}
	}catch(Exception e){
		e.printStackTrace();
	}
		
%>