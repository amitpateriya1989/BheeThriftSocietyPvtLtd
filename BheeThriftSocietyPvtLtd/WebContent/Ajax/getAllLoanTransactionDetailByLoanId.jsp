<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Bean.LoanTrxDetailBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.LoanTrxDetailDao"%>
<%

String id=request.getParameter("loan_trx_id");

if(id!=null){
int loan_trx_id =0;
try {
	loan_trx_id = Integer.parseInt(id);

} catch (NumberFormatException e) {
	e.printStackTrace();
}
ArrayList<LoanTrxDetailBean> list=new LoanTrxDetailDao().getLoanTrxDetailByLoantrxId(loan_trx_id);
%>
<table id="dataTable1"
												class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th width="10%">Date</th>
														<th width="10%">Deposit</th>
														<th width="10%">Interest</th>
														<th width="10%">Balance</th>
														
													</tr>
												</thead>
												<tbody>
												<%
												if(list.isEmpty()!=true){
													for(LoanTrxDetailBean bean:list){
														
													
												
												%>
												<tr>
												<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getTrx_date()) %></td>
												<td><%=bean.getDeposit_amt() %></td>
												<td><%=bean.getInterest_amt() %></td>
												<td><%=bean.getBalance_amt() %></td>
												
													</tr>
												
											<%
}	
												}
											}
											%>
											
											</tbody>
											</table>