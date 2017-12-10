<%@page import="Model.Dao.VoucherDao"%>
<%@page import="Model.Bean.VoucherBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%

String date=request.getParameter("trx_date");


if(date!=null){
Date trx_date =null;

try {
	trx_date = new SimpleDateFormat("dd/MM/yyyy").parse(date);

} catch (ParseException e) {
	e.printStackTrace();
}

%>

											<table id="dataTable1"
												class="table table-striped table-bordered table-hover">
												<thead>

													<tr>
														
														<th width="5%">Trx No.</th>
														<th width="5%">VID</th>
														<th width="5%">Date</th>
														<th width="5%">VNo</th>
														<th width="10%">VType</th>
														<th width="10%">VFrom</th>
														<th width="10%">VAmt</th>
														<th width="10%">Desc.</th>
														<!-- <th width="5%">Ins. From</th>
														<th width="5%">Ins. No</th>
														<th width="5%">Ins. Amt</th>
														<th width="5%">Ins. Date</th>
														<th width="10%">Voucher Type</th> -->
														<th width="5%">Status</th>
														<th width="25%">OPT</th>


													</tr>
												</thead>
												<tbody>
													<%
	   													ArrayList<VoucherBean> list1=new VoucherDao().getAllVoucherByDate(trx_date, trx_date);
															
														   if(list1.isEmpty()!=true){
															   for(VoucherBean bean:list1){%>
													<tr>
														
														<td><%=bean.getTrx_no() %></td>
														<td><%=bean.getAd_voucher_id() %></td>
														<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getTrx_date()) %></td>
														<td><%=bean.getVno() %></td>
														<td><%=bean.getVtype() %></td>
														<td><%=bean.getVfrom() %></td>
														<td><%=bean.getVamt() %></td>
														<td><%=bean.getDescription() %></td>
														<%-- <td><%=bean.getInstrument_from() %></td>
														<td><%=bean.getInstrument_no() %></td>
														<td><%=bean.getInstrument_amt() %></td>
														<td><%=bean.getInstrument_date() %></td>
														<td><%=bean.getVoucher_type() %></td> --%>
														<td><%=bean.getStatus() %></td>
														<td>
														    <a href="ad_voucher_view.jsp?ad_voucher_id=<%=bean.getAd_voucher_id() %>" class="btn btn-sm purple">View</a>
														    <a href="ad_voucher_edit.jsp?ad_voucher_id=<%=bean.getAd_voucher_id() %>" class="btn btn-sm blue">Edit</a>
															<a href="AdVoucher?action=delete_voucher_main&ad_voucher_id=<%=bean.getAd_voucher_id() %>" class="btn btn-sm red">Delete</a>
															<a href="ad_voucher_trx_view?ad_voucher_id=<%=bean.getAd_voucher_id() %>" class="btn btn-sm green">Trx</a>
														</td>
													</tr>
													<%}
	   }
	   
	   %>


												</tbody>
											</table>
											<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
			<script type="text/javascript">
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	});
</script>

<%}else{
	out.print("Date Not Found");
} %>