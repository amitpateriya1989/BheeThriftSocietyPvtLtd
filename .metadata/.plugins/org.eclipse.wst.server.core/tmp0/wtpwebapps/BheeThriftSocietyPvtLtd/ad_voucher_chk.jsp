
<%@ page import="Model.Dao.TempTranxDao" %>
<%@ page import="Model.Bean.TempTranxBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
<%
try{
TempTranxDao bean = new TempTranxDao();
int id =bean.CountTempTranx();
if(id>0){
	TempTranxDao bean1 = new TempTranxDao();
	int ad_voucher_id =bean1.getTempTranxid();
	
	
	//response.sendRedirect("ad_transaction.jsp?ad_voucher_id="+ad_voucher_id);
	
}else{
	
	response.sendRedirect("ad_voucher.jsp");
}

%>
<input value ="<%=id%>"/>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>