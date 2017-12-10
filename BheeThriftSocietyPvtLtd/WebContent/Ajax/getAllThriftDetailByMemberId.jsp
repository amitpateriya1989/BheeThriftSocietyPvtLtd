<%@page import="java.math.RoundingMode"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Date"%>
<%@page import="Model.Dao.ThriftRoiDao"%>
<%@page import="Model.Bean.ThriftRoiBean"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Dao.ThriftTrxDao"%>
<%@page import="Model.Bean.ThriftViewBean"%>
<%@page import="java.util.ArrayList"%>
<%
String id=request.getParameter("ad_member_id");
double total=0.0,thrift_total=0.0,int_total=0.0;
if(id!=null){
int ad_member_id =0;

try {
ad_member_id = Integer.parseInt(id);

} catch (NumberFormatException e) {
	e.printStackTrace();
}
ArrayList<ThriftViewBean> list=new ThriftTrxDao().getAllThriftDetailByMemberId(ad_member_id);
%>
<table id="dataTable1"
												class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>Sno</th>
														<th>Staff no</th>
														<th>Date</th>
														<th>Month</th>
														<th>Deposit</th>
														<th>Interest</th>
														<th>Balance</th>
														<th>OPT</th>
													</tr>
												</thead>
												<tbody>
												<%
												ThriftRoiBean thrift=new ThriftRoiDao().getThriftRoiMaxId();
												ArrayList<ThriftViewBean> listtrx=new ThriftTrxDao().getAllThriftDetailByMemberId(ad_member_id);
										   		
										   	    double thrift_int=thrift.getRoi();
										   	    thrift_int=thrift_int/100.0;
										   	 	double interest=0.0;
												
												int i=0;
												
												
												if(listtrx.isEmpty()!=true){
													for(ThriftViewBean bean:listtrx){
													thrift_total+=bean.getThrift_amt();
													int_total+=bean.getThrift_int();
														  
												
												%>
												<tr>
												<td><%=++i %>
												<td><%=bean.getSociety_no() %></td>
												<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getTrx_date()) %></td>
												<%
													Calendar cal = Calendar.getInstance();
										 		    cal.setTime(bean.getTrx_date());
													%>
												<td><%=cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) %></td>
												<td><%=bean.getThrift_amt() %></td>
												<td><%=bean.getThrift_int() %></td>
												<td><%=bean.getBalance() %></td>
												<td><a href="AdThriftTrx?action=edit&ad_thrift_trx_id=<%=bean.getAd_thrift_trx_id() %>" class="btn btn-sm blue">Edit</a>
												<a href="AdThriftTrx?action=delete&ad_thrift_trx_id=<%=bean.getAd_thrift_trx_id() %>" class="btn btn-sm red">Delete</a></td>
													</tr>
												
											<%
}	
												}
											}
											%>
											
											</tbody>
											<tfoot>
													<tr style="font-size: 14px;font-weight: bold;">
														<td colspan="4" align="right">Total</td>
														<td><%=thrift_total %></td><td><%=int_total %></td><td></td>
													</tr>
												</tfoot>
											</table>