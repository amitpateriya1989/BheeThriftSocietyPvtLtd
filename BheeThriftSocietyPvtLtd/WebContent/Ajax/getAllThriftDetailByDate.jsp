<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Dao.ThriftTrxDao"%>
<%@page import="Model.Bean.ThriftViewBean"%>
<%@page import="java.util.ArrayList"%>
<%


String str_from=request.getParameter("from");
String str_to=request.getParameter("to");
DateFormat formatter ; 
Date from =null; 
Date to =null;
double total=0.0;
if(str_from!=null && str_to!=null){
formatter = new SimpleDateFormat("dd/MM/yyyy");
try {
from = formatter.parse(str_from);
to = formatter.parse(str_to);
} catch (ParseException e) {
	e.printStackTrace();
}


ArrayList<ThriftViewBean> list=new ThriftTrxDao().getAllThriftDetail(from, to);
%>
<table id="dataTable1" class="table table-striped table-bordered table-hover" >
												<thead>
													<tr>
														<th>Sno</th>
														<th>Date</th>
														<th>Month</th>
														<th>Staff no</th>
														<th>Name</th>
														<th>Deposit</th>
														
														
													</tr>
												</thead>
												<tbody>
												<%
												int i=0;
												if(list.isEmpty()!=true){
													for(ThriftViewBean bean:list){
														total+=bean.getThrift_amt();
													
												
												%>
												<tr>
												<td><%=++i %>
												<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getTrx_date()) %></td>
												<%
													Calendar cal = Calendar.getInstance();
										 		    cal.setTime(bean.getTrx_date());
													%>
												<td><%=cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) %></td>
												<td><%=bean.getSociety_no() %></td>
												<td><%=bean.getName() %></td>
												<td><%=bean.getThrift_amt() %></td>
												
												
													</tr>
												
											<%
}	
												}
											}
											%>
											
											</tbody>
											<tfoot style="font-size: 14px;font-weight: bold;">
												<tr>
													<td colspan="5" align="right">Total</td><td><%=total %></td>
												</tr>
											</tfoot>
											</table>