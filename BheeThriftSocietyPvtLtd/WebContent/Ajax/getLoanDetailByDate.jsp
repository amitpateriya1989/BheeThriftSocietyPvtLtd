
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="Model.Bean.LoanDetailViewBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%

String str_from=request.getParameter("from");
String str_to=request.getParameter("to");
DateFormat formatter ; 
Date from =null; 
Date to =null;
if(str_from!=null && str_to!=null){
formatter = new SimpleDateFormat("dd/MM/yyyy");
try {
from = formatter.parse(str_from);
to = formatter.parse(str_to);
} catch (ParseException e) {
	e.printStackTrace();
}

%>
<table id="dataTable1"	class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>Sno.</th>
														<th>Staff No.</th>
														<th>Name</th>
														<th>Type</th>
														<th>Category</th>
														<th>Loan Amt</th>
														<th>ROI</th>
														<th>Start</th>
														<th>End</th>
														<th>Emi</th>
														<th>Status</th>
														<th>Op</th>

													</tr>
												</thead>
												<tbody>
													<%
									    ArrayList<LoanDetailViewBean> list=new LoanTrxDao().getAllLoanDetail(from,to);
													int i=0;
									     if(list.isEmpty()!=true){
									    	 for(LoanDetailViewBean bean:list){
									    		 %>

													<tr>

														<td><%=++i %></td>
														<td><%=bean.getAd_society_no() %></td>
														<td><%=bean.getMember_name() %></td>
														<td><%=bean.getLoan_type() %></td>
														<td><%=bean.getLoan_category_name() %></td>
														<td><%=bean.getLoan_amt() %></td>
														<td><%=bean.getIntrest_rate() %></td>
														<td><% if(bean.getIssue_date()!=null){out.print(new SimpleDateFormat("dd/MM/yyyy").format(bean.getIssue_date().getTime()));}else{out.print("-");}
																						%></td>
														
														<td><%if(bean.getIssue_date()!=null){out.print(new SimpleDateFormat("dd/MM/yyyy").format(bean.getEnd_date().getTime()));}else{out.print("-");} %></td>
														<td><%=bean.getEmi() %></td>
														<td><%=bean.getStatus() %></td>
														<td><a href="loan_detail_view_2.jsp?loan_trx_id=<%=bean.getLoan_trx_id()%>" class="btn btn-xs green"><i class="fa fa-search "></i>View</a></td>
													</tr>
													<%
									}
									     }
									%>


												</tbody>
											</table>
											<%
											}
											%>
											
											<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
			<script type="text/javascript">
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	});
</script>
											