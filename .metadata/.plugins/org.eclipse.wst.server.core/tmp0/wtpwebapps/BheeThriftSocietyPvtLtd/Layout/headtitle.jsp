<%@page import="Model.Bean.ClientBean"%>
<%@page import="Model.Dao.ClientDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="Model.Bean.UserBean"%>
<%try{
	if(session.getAttribute("userbean")==null){
		 response.sendRedirect("logout.jsp");
	}else{
		
	
UserBean userSession=(UserBean)session.getAttribute("userbean");
Date date=(Date)session.getAttribute("openday");
ClientBean clientbean=new ClientDao().getMaxClient();
%>
<div class="container-fluid">
<div class="row light bg-grey">
	<div class="col-md-7 ">
		<!--  <h3 class="top-head-title">Central Bank Employees Co.Op. Credit Society</h3> --> 
		<h3 class="top-head-title"><%=clientbean.getName() %></h3>
	</div>
	
	<div class="col-md-5">
	
	<div class="top-user-lbl">Welcome: Mr. <%=userSession.getName()%> <i class="fa fa-user"></i><br>
	<%=new SimpleDateFormat("dd/MM/yyyy").format(date)%>
	</div>
	</div>
</div>
</div>
<%
	}
	}catch(Exception e){
		e.printStackTrace();
	}
		
%>