<%@page import="Model.Bean.LoanTrxDetailBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.StaffLoanTrxDetailDao"%>
<table width="100%" border="1">
<thead>
<tr>
<th>SNO</th><th>Loan Id</th> <th>Deposit Amt</th><th>Interest Amt</th><th>Balance Amt</th>
</tr>

</thead>
<tbody>



<%
String mid=request.getParameter("ad_employee_id");

int i=0;
int ad_employee_id=Integer.parseInt(mid);

StaffLoanTrxDetailDao dao =new StaffLoanTrxDetailDao();
								 	ArrayList<LoanTrxDetailBean> alist=dao.getOpenLoanTrxDetailById(ad_employee_id);
								 	
								 	if(alist!=null){
								 	for(LoanTrxDetailBean bean:alist){
								 		
								 	%>
								 	<tr>
								 	
								 	<td><%=++i %></td><td><%=bean.getLoan_trx_id() %></td><td><%=bean.getDeposit_amt() %></td><td><%=bean.getInterest_amt() %></td><td><%=bean.getBalance_amt() %></td>
								 	
								 	</tr>
								 		
								 
							<%} }%>
</tbody>
</table>
