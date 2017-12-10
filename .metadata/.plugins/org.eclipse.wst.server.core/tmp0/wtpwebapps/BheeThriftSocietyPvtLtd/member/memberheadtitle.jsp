<%@page import="Model.Bean.UserBean"%>
<%@page import="Model.Member.Bean.MemberLoginBean"%>
<%
MemberLoginBean user=null;
try{
	if(request.getSession(false).getAttribute("MemberLoginBean")==null){
		response.sendRedirect("logout.jsp");
	}
	user=(MemberLoginBean)session.getAttribute("MemberLoginBean");
	if(user==null){
		response.sendRedirect("logout.jsp");

	}else{
	
UserBean userSession=(UserBean)session.getAttribute("userbean");
%>
<div class="container-fluid">
<div class="row light bg-grey">
	<div class="col-md-7 ">
		<h3 class="top-head-title">Central Bank Employees Co-Operative Credit Society Ltd. Bhopal</h3>
	</div>
	<div class="col-md-5">
	<div class="top-user-lbl">Welcome: Mr. <%=user.getUsername()%> <i class="fa fa-user"></i></div>
	</div>
</div>
</div>
<%}
}catch(Exception e){
	e.printStackTrace();
}
%>