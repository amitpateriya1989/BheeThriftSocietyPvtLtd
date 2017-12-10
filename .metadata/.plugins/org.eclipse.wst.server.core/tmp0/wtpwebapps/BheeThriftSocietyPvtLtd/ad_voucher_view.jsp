<%@page import="Model.Bean.VoucherTrxDetailBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="Model.Bean.VoucherBean"%>
<%@page import="Model.Dao.VoucherDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="Model.Bean.UserBean"%>
<%@page import="Model.Bean.TransactionBean"%>
<%@page import="Model.Dao.TransactionDao"%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%@ include file="Layout/header.jsp"%>
<link rel="stylesheet"
	href="assets/plugins/data-tables/DT_bootstrap.css" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-full-width">
	<%@ include file="Layout/headtitle.jsp"%>
	<!-- BEGIN HEADER -->
	<div class="header navbar mega-menu">
		<!-- BEGIN TOP NAVIGATION BAR -->
		<%@ include file="Layout/navbar.jsp"%>
		<!-- END TOP NAVIGATION BAR -->
	</div>
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN EMPTY PAGE SIDEBAR -->
		<%@ include file="Layout/emptynavbar.jsp"%>
		<!-- END EMPTY PAGE SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN PAGE CONTENT-->
				<div class="row">
					<div class="col-md-2 sidebar-content ">
						<%@ include file="Layout/sidebar.jsp"%>
					</div>
					<div class="col-md-10">
						<!-- BEGIN PAGE HEADER-->
						<div class="row">
							<div class="col-md-12">
								<!-- Containt Start -->
								<%
								try{
Object AppObj = request.getSession().getAttribute("AppMessage");
String[] AppMessage = (String[])AppObj;
if(AppMessage[1].isEmpty()!=true && AppMessage[1].equals("welcome")!=true){ %>
								<div class="alert <%=AppMessage[0] %> alert-dismissable">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true"></button>
									<%=AppMessage[1] %>
								</div>
								<%
}
%>
								<!------------------------------------------------------------------- -->
								<%
int ad_voucher_id=0;
UserBean user=null;
String vid=request.getParameter("ad_voucher_id");
if(vid!=null){
try{
	 ad_voucher_id = Integer.parseInt(vid);
	if(request.getSession(false).getAttribute("userbean")==null){
	response.sendRedirect("logout.jsp");
	}
}catch(Exception e){
	e.printStackTrace();
}
}	
//ArrayList<TransactionBean> lst =new TransactionDao().getTransactionByType(ad_voucher_id);
ArrayList<VoucherTrxDetailBean> lst=new VoucherDao().getAllVoucherTrxDetail(ad_voucher_id);
VoucherBean vbean = new VoucherDao().getVoucherByVoucherId(ad_voucher_id);

%>

								<!-- BEGIN BORDERED TABLE PORTLET-->
								<div class="portlet box purple">
									<div class="portlet-title">
										<div class="caption">Voucher Detail</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"> </a>
										</div>
									</div>
									<div class="portlet-body">
									
									<table id="data1">
											<tr><td>
										<div id="voucher">
											<table id="tblContainer" class="table table-striped table-bordered table-hover">

												<tr>
													<td>Trx No.</td>
													
													<td><%=vbean.getTrx_no() %></td>
													<td>Voucher Date</td>
													
													<%
											String date1=null;
											if(vbean.getTrx_date()!=null){
												date1=new SimpleDateFormat("dd/MM/yyyy").format(vbean.getTrx_date());
											}
											%>
													<td><%=date1 %></td>
												</tr>
												<tr>
													<td>Voucher Type</td>
													
													<td><%=vbean.getVtype() %></td>
													<td>Voucher Form</td>
													
													<td><%=vbean.getVfrom() %></td>



												</tr>
												<tr>
													<td>Description</td>
													
													<td><%=vbean.getDescription() %> <input type="hidden"
														name="description" value="<%=vbean.getDescription() %>" />
													</td>
													<td>Instrument Form</td>
													
													<td><%=vbean.getInstrument_from() %></td>
												</tr>
												<tr>
													<td>Instrument No</td>
													
													<td><%=vbean.getInstrument_no() %></td>
													<td>Instrument Date</td>
													
													<%
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
String date="";
if(vbean.getInstrument_date()==null){
	date="dd/MM/yyyy";
}else{
	date=sdf.format(vbean.getInstrument_date());
} %>

													<td><%=date %></td>

												</tr>
												<tr>
													<td>Voucher Amt</td>
													
													<td><input name="vamt" id="vamt"
														style="background: none; border: none; text-align: left;"
														readonly="readonly" value="<%=vbean.getVamt() %>" /></td>
													<td>Voucher Type</td>
													
													<td><%=vbean.getVoucher_type() %></td>

												</tr>

												</tbody>
											</table>
											
											<table id="dataTable1" class="table table-striped table-bordered table-hover">
												<thead>
													<tr style="background: teal; color: white">
														<th>Sno</th>
														<th>Trx Date</th>
														<th>A/c Name</th>
														<th>Party Name</th>
														<th>Narration</th>
														<th>Dr</th>
														<th>Cr</th>

													</tr>
												</thead>

												<tbody>
													<%
        
												       double totalcr=0;
												        double totaldr=0;
												        
														int i=1;
														if(lst.isEmpty()!=true){
															for(VoucherTrxDetailBean bean:lst){
																
																String name="";
																String narration="";
															
																if(bean.getMember_name()!=null){
																	 name=""+bean.getAd_society_no()+" | "+ bean.getMember_name();
																}
																if(bean.getNarration()!=null){
																	narration=bean.getNarration();
																}
																
													    date="";
														if(bean.getTrx_date()==null){
															date="dd/MM/yyyy";
														}else{
															date=sdf.format(bean.getTrx_date());
														}
													%>

													<tr>
														<td><%=i %></td>
														<td><%= date %></td>
														<td><%=bean.getAc_name() %></td>
														<td><%=name %></td>
														<td><%=narration %></td>
														<td align="right"><%=bean.getDramt() %></td>
														<td align="right"><%=bean.getCramt() %></td>
													</tr>

													<%
											            totaldr+=bean.getDramt();
											            totalcr+=bean.getCramt();
											            i++;
											            
														}
													}
           										 %>
												</tbody>
												<tfoot>
													<tr>

														<th colspan="5">Total</th>
														<th><%=new BigDecimal(Math.abs(Math.abs(totaldr))).setScale(2, BigDecimal.ROUND_HALF_EVEN) %>
														</th>
														<th><%=new BigDecimal(Math.abs(Math.abs(totalcr))).setScale(2, BigDecimal.ROUND_HALF_EVEN) %> 
														</th>

													</tr>
												</tfoot>
											</table>



										</div>
										</td></tr>
										</table>
										<div>
										<table class="table table-bordered">
	<tr>
		<td align="center">
			
			
			
			<a href="#" onclick="tableToExcel('data1', 'Voucher Book')" class="btn green btn-md "> Excel</a>
			<a onclick="prnts()" href="#" class="btn red btn-md ">Print</a>
			 <a href="view_voucher_detail.jsp"
														class="btn btn-xm blue">Back</a>
			
			
		</td>
	</tr>
	</table>
											
										</div>

									</div>
									<!-- End portlet-body -->
								</div>
								<!------------------------------------------------------------------- -->
								<!-- END BORDERED TABLE PORTLET-->
								<!-- Containt Stop -->
							</div>
						</div>
					</div>
				</div>
				<!-- END PAGE CONTENT-->
			</div>
		</div>
		<!-- END CONTENT -->
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<%@ include file="Layout/footer.jsp"%>
	<%
int loan_trx_id=0;
if(session.getAttribute("loan_id")!=null){
 loan_trx_id=(Integer)session.getAttribute("loan_id");
}
%>

	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>

	<script type="text/javascript">
		$(document).ready(function(e) {
			//alert();
			window.onunload = refreshParent;
		});

		function refreshParent() {
			window.opener.location.reload();
		}

		function approve(ad_voucher_id) {

			if (parseFloat($("#totaldr").val()) == parseFloat($("#totalcr")
					.val())) {
				//alert();

				//if(parseFloat($("#totaldr").val())==parseFloat($("#vamt").val())){

				$
						.ajax({
							type : "GET",
							dataType : 'text',
							url : "AdVoucher?action=approve&ad_voucher_id="
									+ ad_voucher_id + "&loan_trx_id="
									+
	<%=loan_trx_id%>
		,
							async : false,
							success : function(data) {

								alert(" Voucher successfully approved  & voucher no is  : "
										+ data);
								window.close();
							}
						});

				/* }else{
					
					alert(" Total Dr , Cr and Voucher Amount  Not Match Please Correct Then Try....");
					event.stop();
					
					
				} */

			} else {

				alert(" Total Dr And Cr  Not Match Please Correct Then Try....");
				event.stop();
			}

			//	window.location.href="AdVoucher?action=approve&ad_voucher_id="+ad_voucher_id;

		}
		function delete_voucher(ad_voucher_id) {
			$
					.ajax({
						type : "POST",
						dataType : 'text',
						url : "AdVoucher?action=deletevoucherbeforeapprovel&ad_voucher_id="
								+ ad_voucher_id,
						async : false,
						success : function(data) {
							alert(data);

							window.close();
						}
					});

			//window.location.href="AdVoucher?action=deletevoucherbeforeapprovel&ad_voucher_id="+ad_voucher_id;
		}

		function editvoucher(ad_voucher_id) {
			window.location.href = "edit_voucher.jsp?ad_voucher_id="
					+ ad_voucher_id;

		}
		function prnts() {

			var divElements = document.getElementById('voucher').innerHTML;
			var printWindow = window.open("", "_blank", "");
			printWindow.document.open();
			printWindow.document
					.write('<html><body border="1" colspan="0" ><center> <h3 style="margin-bottom:0px;margin-top:0px"> Central Bank Employees Co-Operative Credit Society Ltd. Bhopal </h3><h4 style="margin-bottom:0px;margin-top:0px">AR/BPL/57<br/></h4></center>');

			printWindow.document.write(divElements);
			printWindow.document.write('</body></html>');
			printWindow.document.close();
			printWindow.focus();
			printWindow.print();
			printWindow.close();

		}
	</script>
	<script type="text/javascript">
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	});
	
</script>
<script type="text/javascript">
var tableToExcel = (function() {
	  var uri = 'data:application/vnd.ms-excel;base64,'
	    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><center> <h3 style="margin-bottom:0px;margin-top:0px"> Central Bank Employees Co-Operative Credit Society Ltd. Bhopal </h3><h4 style="margin-bottom:0px;margin-top:0px">AR/BPL/57<br/></h4></center><table>{table}</table></body></html>'
	    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
	    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	  return function(table, name) {
	    if (!table.nodeType) table = document.getElementById('data1')
	    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
	    window.location.href = uri + base64(format(template, ctx))
	  }
	})()
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>