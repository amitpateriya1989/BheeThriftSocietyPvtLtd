<%@page import="Model.Bean.TempTranxBean"%>
<%@page import="Model.Dao.TempTranxDao"%>
<%@page import="Model.Dao.TransactionDao"%>
<%
try{
String ad_trx_id=request.getParameter("ad_trx_id");


String ad_voucher_id = request.getParameter("ad_voucher_id");
if(ad_trx_id!="" && ad_trx_id!="0"){
	new TransactionDao().deleteTransaction(Integer.parseInt(ad_trx_id));
	response.sendRedirect("ad_voucher_edit.jsp?ad_voucher_id="+ad_voucher_id);
}
}catch(Exception e){
	e.printStackTrace();
} %>
