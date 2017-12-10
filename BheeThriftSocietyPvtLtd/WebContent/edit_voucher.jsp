<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Bean.VoucherBean"%>
<%@page import="Model.Dao.VoucherDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="Model.Bean.UserBean"%>
<%@page import="Model.Bean.TransactionBean"%>
<%@page import="Model.Dao.TransactionDao"%>





<%
try{
	int ad_voucher_id = 0;

	try {
		ad_voucher_id = Integer.parseInt(request
				.getParameter("ad_voucher_id"));

	} catch (Exception e) {
		e.printStackTrace();
	}

	ArrayList<TransactionBean> lst = new TransactionDao()
			.getTransactionByType(ad_voucher_id);

	VoucherBean vbean = new VoucherDao()
			.getVoucherByVoucherId(ad_voucher_id);
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
<link rel="stylesheet" type="text/css"
	href="assets/plugins/bootstrap-datepicker/css/datepicker.css" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-full-width">

	<!-- BEGIN HEADER -->

	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN EMPTY PAGE SIDEBAR -->

		<!-- END EMPTY PAGE SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">

				<div class="row">
					<div class="col-md-12">
						<!-- Containt Start -->
						<%
							Object AppObj = request.getSession().getAttribute("AppMessage");
							String[] AppMessage = (String[]) AppObj;
							if (AppMessage[1].isEmpty() != true
									&& AppMessage[1].equals("welcome") != true) {
						%>
						<div class="alert <%=AppMessage[0]%> alert-dismissable">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true"></button>
							<%=AppMessage[1]%>
						</div>
						<%
							}
						%>


						<!------------------------------------------------------------------- -->
						<!-- BEGIN BORDERED TABLE PORTLET-->

						<div class="portlet box purple">
							<div class="portlet-title">
								<div class="caption">Voucher Edit</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"> </a>
								</div>
							</div>
							<div class="portlet-body">

								<form
									action="AdVoucher?action=voucheredit&ad_voucher_id=<%=ad_voucher_id%>"
									method="post">

									<table id="tblContainer" class="table table-bordered">



										<tr>
											<td>Voucher NO</td>
											<td>:</td>
											<td><%=vbean.getTrx_no()%></td>
											<td>Trx Date</td>
											<td>:</td>
											<td><%=new SimpleDateFormat("dd/MM/yyyy").format(vbean.getTrx_date().getTime())%></td>
										</tr>
										<tr>
											<td>Trx Type</td>
											<td>:</td>
											<td><select name="vtype" id="vtype"
												style="width: 170px;" data-placeholder=""
												class="chosen-select" style="width:250px;" tabindex="2">
													<option value="<%=vbean.getVtype()%>"><%=vbean.getVtype()%></option>
													<option value="Adjustment">Adjustment</option>
													<option value="Cheque">Cheque</option>
													<option value="DD">Demand Draft</option>
													<option value="Online">Online</option>
													<option value="Cash">Cash</option>
											</select></td>
											<td>Voucher Form</td>
											<td>:</td>
											<td><%=vbean.getVfrom()%></td>



										</tr>
										<tr>
											<td>Description</td>
											<td>:</td>
											<td><%=vbean.getDescription()%></td>
											<td>Instrument Form</td>
											<td>:</td>
											<td><input type="text" name="ins_form" id="ins_form"
												value="<%=vbean.getInstrument_from().trim()%>" /></td>
										</tr>
										<tr>
											<td>Instrument No</td>
											<td>:</td>
											<td><input type="text" name="ins_no" id="ins_no"
												value="<%=vbean.getInstrument_no()%>" /></td>
											<td>Instrument Date</td>
											<td>:</td>
											<td><input class="date-picker" type="text"
												name="ins_date" id="ins_date"
												value="<%=new SimpleDateFormat("dd/MM/yyyy").format(vbean.getInstrument_date())%>" /></td>

										</tr>
										<tr>
											<td>Voucher Amt</td>
											<td>:</td>
											<td><input class="amt" type="text" name="v_amt"
												id="v_amt" value="<%=vbean.getVamt()%>" /></td>
											<td>Voucher Type</td>
											<td>:</td>
											<td><select name="voucher_type" id="voucher_type"
												style="width: 170px;" data-placeholder="Type"
												class="chosen-select" style="width:250px;" tabindex="2">
													<option value="<%=vbean.getVoucher_type()%>"><%=vbean.getVoucher_type()%></option>
													<option value="Received">Received</option>
													<option value="Payment">Payment</option>
													<option value="Adjustment">Adjustment</option>
											</select></td>

										</tr>


										<tr>
											<td colspan="6" align="center"><input type="submit"
												class="btn btn blue" value="Update Voucher" /> <input
												type="button"
												onclick="admoretrx('<%=ad_voucher_id%>','<%=vbean.getTrx_date()%>')"
												value="Ad More Trx" name="ad_trx" id="ad_trx"
												class="btn btn green" />
											</td>
										</tr>



									</table>

								</form>

								<table class="table table-bordered">
									<thead>
										<tr style="background: teal; color: white">
											<th style="width: 10%">Sno</th>
											<th style="width: 10%">Trx Date</th>
											<th style="width: 30%">A/c Name</th>
											<th style="width: 30%">Narration</th>
											<th style="width: 10%">Dr</th>
											<th style="width: 10%">Cr</th>
											<th>OPT</th>

										</tr>
									</thead>

									<tbody>
										<%
											double totalcr = 0;
											double totaldr = 0;

											int i = 1;
											if (lst != null) {
												for (TransactionBean bean : lst) {

													String name = "";
													String narration = "";

													if (bean.getMember().getName() != null) {
														name = "[ " + bean.getMember().getName() + "]";
													}
													if (bean.getNarration() != null) {
														narration = bean.getNarration();
													}
										%>



										<tr>
											<td><%=i%></td>

											<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getTrx_date())%></td>
											<td><label style="color: olive;"><%=bean.getLedger().getAc_name()%></label><%=name%>
											</td>
											<td><%=narration%></td>
											<td><%=bean.getDramt()%></td>
											<td><%=bean.getCramt()%></td>
											<td><a
												href="edit_voucher_trx.jsp?ad_trx_id=<%=bean.getAd_trx_id()%>&ad_voucher_id=<%=vbean.getAd_voucher_id()%>">Edit</a>/<a
												href="delete_voucher_trx.jsp?ad_trx_id=<%=bean.getAd_trx_id()%>&ad_voucher_id=<%=vbean.getAd_voucher_id()%>">Delete</a>
											</td>

										</tr>

										<%
											totaldr += bean.getDramt();
													totalcr += bean.getCramt();
													i++;

												}
											}
										%>
									</tbody>
									<tfoot>
										<tr>

											<th colspan="4" align="right">Total</th>
											<th><%=totaldr%></th>
											<th><%=totalcr%></th>

										</tr>

									</tfoot>
								</table>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script src="js/jquery-1.10.2.js"></script>

	<script src="js/jquery-ui.js"></script>

	<script type="text/javascript"
		src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script src="assets/scripts/custom/components-pickers.js"></script>
	<script type="text/javascript">
jQuery(document).ready(function() {       
	FormWizardMember.init();
	
	$('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true});
	$('.date-picker1').datepicker({format: 'dd/mm/yyyy',autoclose: true,endDate:'+0d'});
});
</script>
	<script>

$(document).ready(function(e) {
	
});
function admoretrx(ad_voucher_id , trx_date){
	window.location.href="ad_voucher_trx.jsp?ad_voucher_id="+ad_voucher_id+"&trx_date="+trx_date;
}
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>