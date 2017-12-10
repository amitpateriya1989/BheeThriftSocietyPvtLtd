<%@page import="Model.Bean.EmployeeBean"%>
<%@page import="Model.Dao.EmployeeDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Bean.TransactionBean"%>
<%@page import="Model.Dao.TransactionDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
try{
String ad_trx_id=request.getParameter("ad_trx_id");
String ad_voucher_id = request.getParameter("ad_voucher_id");
TransactionBean tbean=null;
if(ad_trx_id!="" && ad_trx_id!="0"){
 tbean=	new TransactionDao().getTransactionById1(Integer.parseInt(ad_trx_id));
}

%>

<form action="AdTransaction?action=edittrx&ad_voucher_id=<%=ad_voucher_id %>&ad_trx_id=<%=ad_trx_id %>"  method="post">

<table id="tblContainer" border="0" cellpadding="2" cellspacing="0" width="100%">

<tr>
					<td colspan="6" class=""  ><hr style="color: black;" /></td>
					</tr>

<tr>
<td>A/c Head</td><td>:</td><td><select name="ad_account_id" id="ad_account_id" readonly="readonly"><option value="0">--select--</option>
<%LedgerAccountDao ldao=new LedgerAccountDao();
    				ArrayList<LedgerAccountBean> ledgerList=ldao.getAllLedgerAccount();
    				if(ledgerList!=null){
    				for(LedgerAccountBean bean1:ledgerList){
    					
    					if(tbean.getAd_account_id()==bean1.getAd_account_id()){
    						%>
    	    				 <option value="<%=bean1.getAd_account_id()%>" selected="selected"><%=bean1.getAc_name() %></option>	
    	    				    
    	    				<%	
    					}else{
    				%>
    				 <option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_name() %></option>	
    				    
    				<%}}} %></td>
<td>Member </td><td>:</td><td> <select name="ad_member_id" id="ad_member_id"><option>--select--</option>

		 			<%MemberRegistrationDao mdao =new MemberRegistrationDao();
								 	ArrayList<MemberRegistrationBean> malist=mdao.getAllMemberlist();
								 	if(malist!=null){
								 	for(MemberRegistrationBean bean:malist){
								 	
								 	if(bean.getAd_member_id()==tbean.getAd_member_id()){
								 		%>
									 	
										 <option value="<%=bean.getAd_member_id()%>" selected="selected"><%=bean.getName() %></option>
										 <%
								 	}else{
								 	%>
								 	
								 <option value="<%=bean.getAd_member_id()%>"><%=bean.getName() %></option>
								 <%} }
								 }%> </td>
</tr>
<tr>
<td>Staff </td><td>:</td><td> <select name="ad_employee_id" id="ad_employee_id"><option>--select--</option>

		 			<%EmployeeDao edao =new EmployeeDao();
								 	ArrayList<EmployeeBean> ealist=edao.getAllEmployeeName();
								 	if(malist!=null){
								 	for(EmployeeBean bean:ealist){
								 	
								 	if(bean.getAd_employee_id()==tbean.getAd_employee_id()){
								 		%>
									 	
										 <option value="<%=bean.getAd_employee_id()%>" selected="selected"><%=bean.getName() %></option>
										 <%
								 	}else{
								 	%>
								 	
								 <option value="<%=bean.getAd_employee_id()%>"><%=bean.getName() %></option>
								 <%} }
								 }%> </td>


<td>Trx Date</td><td>:</td><td> <input type="date" readonly="readonly" value="<%=tbean.getTrx_date() %>" name="trx_date"/> </td>



</tr>
<tr>
<td>Dr Amt</td><td>:</td><td><input type="text" name="dr_amt" id="dr_amt" value="<%=tbean.getDramt() %>" /></td>
<td>Cr Amt</td><td>:</td><td><input type="text" name="cr_amt" id="cr_amt" value="<%=tbean.getCramt() %>" /></td>
</tr>
<tr>
<td>Narration </td><td>:</td><td><input type="text" name="narration" id="narration" value="<%=tbean.getNarration() %>" /></td>


</tr><tr>


</tr>

<tr>
					<td colspan="6" class=""  ><hr style="color: black;" /></td>
					</tr>

	<tr><td colspan="9" align="center" height="5"><input type="submit" value="Update"/></td></tr>
	<tr><td colspan="6" class=""  ><hr style="color: black;" /></td></tr>
	</tbody>
</table>
</form>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>