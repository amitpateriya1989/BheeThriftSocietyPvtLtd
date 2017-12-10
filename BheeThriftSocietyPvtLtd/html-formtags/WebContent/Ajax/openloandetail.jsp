<%@page import="Model.Bean.LoanTrxDetailBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.LoanTrxDetailDao"%>
<table class="table table-bordered">
<thead>
<tr>
<th>SNO</th><th>Loan Id</th> <th>Deposit Amt</th><th>Interest Amt</th><th>Balance Amt</th>
</tr>
</thead>
<tbody>
<%
String mid=request.getParameter("ad_member_id");

int i=0;
int ad_member_id=Integer.parseInt(mid);

LoanTrxDetailDao dao =new LoanTrxDetailDao();
	ArrayList<LoanTrxDetailBean> alist=dao.getOpenLoanTrxDetailById(ad_member_id);
	if(alist!=null){
	for(LoanTrxDetailBean bean:alist){
%>
<tr>
<td><%=++i %></td><td><%=bean.getLoan_trx_id() %></td><td><%=bean.getDeposit_amt() %></td><td><%=bean.getInterest_amt() %></td><td><%=bean.getBalance_amt() %></td>
</tr>
<%} }%>
</tbody>
</table>