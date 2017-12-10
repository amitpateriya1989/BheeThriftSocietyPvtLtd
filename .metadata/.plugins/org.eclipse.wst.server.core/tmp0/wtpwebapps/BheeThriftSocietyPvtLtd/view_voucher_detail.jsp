<%@page import="Model.Dao.VoucherDao"%>
<%@page import="Model.Bean.VoucherBean"%>
<%@page import="Model.Dao.PayrollAdviceDao"%>
<%@page import="Model.Bean.PayrollAdviseBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<%
try{
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 		String from = null;
 		String to = null;
 		try {
 			Date date = (Date)session.getAttribute("openday");
 			from=sdf.format(date).toString();
 			to= sdf.format(date).toString();
 			
 		} catch (Exception i) {
 			i.printStackTrace();
 		}
 	%>

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

<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-datepicker/css/datepicker.css"/>


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
								<ul class="page-breadcrumb breadcrumb">
									<li><i class="fa fa-home"></i> <a href="#">Transaction</a><i
										class="fa fa-angle-right"></i></li>
									<li><a href="#">Transaction</a><i
										class="fa fa-angle-right"></i>Voucher Detail</li>
								</ul>
								<!-- END PAGE TITLE & BREADCRUMB-->
							</div>
						</div>
						<!-- END PAGE HEADER-->
						<!-- BEGIN PAGE CONTENT-->
						<div class="row">
							<div class="col-md-12">
								<!-- Containt Start -->
								<%
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
								<!-- BEGIN BORDERED TABLE PORTLET-->
								<div class="portlet box purple">
									<div class="portlet-title">
										<div class="caption">Voucher Detail</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"> </a>
										</div>
									</div>
									<div class="portlet-body">
										<form id="frmVoucherDetail" name="frmVoucherDetail" action="#">
											<table class="table table-bordered">
												<tr>
													<td>Voucher Date :</td>
													<td><Input type="text"
														class="form-control input-medium date-picker"
														name="trx_date" id="trx_date" /> <label class="error"></label>
													</td>

													<td><input class="btn btn blue btn-search"
														type="button" name="search" value="Search" /> <input
														id="clear" class="btn btn green" type="reset" name="Clear" />
													</td>
												</tr>
											</table>
										</form>
									</div>
									<!-- End portlet-body -->
								</div>

								<!-- BEGIN BORDERED TABLE PORTLET-->
								<div class="portlet box purple">
									<div class="portlet-title">
										<div class="caption">View Voucher Detail</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"> </a>
										</div>
									</div>
									<div class="portlet-body">
										<table class="table table-bordered">
											<tr>
												<td>
													<div class="btn-group pull-right">
														<button class="btn red btn-md dropdown-toggle"
															type="button" data-toggle="dropdown">
															<i class="fa fa-bar-chart-o"></i> Print Report <i
																class="fa fa-angle-down"></i>
														</button>
														<ul class="dropdown-menu" role="menu">
															<li><a href="#"
																onclick="tableToExcel('ledger', 'Ledger Book')"><img
																	src="Image/excel-icon.png" height="25" width="25" />
																	Excel</a>
															</li>
															<li><a onclick="prnts()" href="#"><img
																	src="Image/print-icon.png" height="25" width="25" />PDF</a>
															</li>
														</ul>
													</div></td>
											</tr>
										</table>
										<!--  class="scroller" style="height:600px" data-always-visible="1" data-rail-visible="0" -->
										<div id="display">
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
														<th width="15%">OPT</th>


													</tr>
												</thead>
												<tbody>
													<%
	   													ArrayList<VoucherBean> list1=new VoucherDao().getAllVoucher();
															
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
															
														</td>
													</tr>
													<%}
	   }
	   
	   %>


												</tbody>
											</table>


										</div>
										<div class="clearfix"></div>
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

	<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
	<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script src="assets/scripts/custom/components-pickers.js"></script>
	<script src="assets/plugins/select2/select2.min.js"></script>
	<script>
		jQuery(function() {
			$('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true});
			
			
			
			jQuery.validator.addMethod("validDate", function (value, element) {
		        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
		    }, "Please enter valid Date.");
			
			jQuery( "#frmVoucherDetail" ).validate({
				  rules: {
					  
				     trx_date:{
				    	  required: true,
				    	  validDate:true
				     }
				  }
				});

			$(".btn-search").click(function(e) {

				var id = $(this).val();
				if ($("#frmVoucherDetail").valid() == false) {
					return false;
				} else {
					var trx_date = $('#trx_date').val().trim();

					$.ajax({
						type : "post",
						url : "Ajax/get_voucher_detail_by_date.jsp",
						data : {
							"trx_date" : trx_date
						},
						success : function(data) {
							$('#display').html(data);
						},
						error : function(dataXhr, status, errorElement) {
							console.log(status);
						}
					});//end ajax
				}//end check validation

			});//end member serach

		});//end dom

		function prnts() {

			var divElements = document.getElementById('display').innerHTML;
			var printWindow = window.open("", "_blank", "");
			printWindow.document.open();
			printWindow.document
					.write('<html><body border="1" ><center> <h3 style="margin-bottom:-0px "> Central Bank Employees Co-Operative Credit Society Ltd. Bhopal </h3><h4 style=" margin-top: 0px";margin-bottom:-0px>AR/BPL/57</h4></center>');
			printWindow.document.write(divElements);
			printWindow.document.write('</body></html>');
			printWindow.document.close();
			printWindow.focus();
			setTimeout(function() {
				printWindow.print();
				printWindow.close();
			}, 100);
		}
	</script>
	<script type="text/javascript">
		var tableToExcel = (function() {

			var uri = 'data:application/vnd.ms-excel;base64,', template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><center> <h3 style="margin-bottom:0px;margin-top:0px">Central Bank Employees Co-Operative Credit Society Ltd. Bhopal </h3><h4 style="margin-bottom:0px;margin-top:0px">AR/BPL/57<br/></h4></center><table>{table}</table></body></html>', base64 = function(
					s) {
				return window.btoa(unescape(encodeURIComponent(s)))
			}, format = function(s, c) {
				return s.replace(/{(\w+)}/g, function(m, p) {
					return c[p];
				})
			}

			return function(table, name) {
				if (!table.nodeType)
					table = document.getElementById('display')
				var ctx = {
					worksheet : name || 'Worksheet',
					table : table.innerHTML
				}
				window.location.href = uri + base64(format(template, ctx))
			}
		});
	</script>
	<script type="text/javascript"
		src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>