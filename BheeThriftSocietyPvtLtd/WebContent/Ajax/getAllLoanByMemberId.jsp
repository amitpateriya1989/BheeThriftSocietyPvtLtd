<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="Model.Bean.LoanDetailViewBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%

String id=request.getParameter("ad_member_id");

if(id!=null){
int ad_member_id =0;
try {
ad_member_id = Integer.parseInt(id);

} catch (NumberFormatException e) {
	e.printStackTrace();
}
ArrayList<LoanDetailViewBean> list=new LoanTrxDao().getAllLoanDetailByMemberId(ad_member_id);
%>
<select id="loan_trx_id" name="loan_trx_id">
	<%
									   
		if(list.isEmpty()!=true){
		 for(LoanDetailViewBean bean:list){
			 %>
	<option value="<%=bean.getLoan_trx_id()%>"><%=bean.getLoan_trx_id()+" - " + bean.getLoan_type() %></option>
</select>

<%}}} %>
