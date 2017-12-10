<%@page import="Model.Bean.UserBean"%>
<%@page import="Model.Bean.SetAssignSubMenuBean"%>
<%@page import="Model.Dao.SetAssignSubMenuDao"%>
<%@page import="Model.Bean.SubMenuBean"%>
<%@page import="Model.Dao.SubMenuDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%

		

	
String m=(String)session.getAttribute("ad_menu_id");
int mid=0;
try{
mid=Integer.parseInt(m);
}catch(NumberFormatException n){
	n.printStackTrace();
}

try{
	if(session.getAttribute("menubean")==null){
		 response.sendRedirect("logout.jsp");
	}else{
ArrayList<MenuBean> list1=(ArrayList<MenuBean>) session.getAttribute("menubean");


if(list1.isEmpty()!=true){
for(MenuBean menu1:list1){

	if(menu1.getAd_menu_id()==mid){
	%>
   
  <a style="font-size: 20px" href="<%=menu1.getLink()%>"><%=menu1.getName() %></a>
  <ul class="ver-inline-menu tabbable margin-bottom-25">
  <%
  ArrayList<SubMenuBean> sublist=(ArrayList<SubMenuBean>) session.getAttribute("submenubean");
  if(sublist.isEmpty()!=true){
	  for(SubMenuBean menu:sublist){
		  if(menu1.getAd_menu_id()==menu.getAd_menu_id()){
  %>
  	<li>
	<a href="<%=menu.getLink()%>" >
	<i class="fa fa-hand-o-right"></i>
	  <%=menu.getName() %></a>
	</li>
  <%}
		  }
		  }%>
	  
	  </ul>
	  <%}
	} 
	}
	}
	}catch(Exception e){
		e.printStackTrace();
	}
		
%>