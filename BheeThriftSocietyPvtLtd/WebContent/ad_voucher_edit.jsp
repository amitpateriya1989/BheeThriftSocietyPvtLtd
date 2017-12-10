<%@page import="Model.Bean.JournalLedgerBean"%>
<%@page import="Model.Bean.EmployeeBean"%>
<%@page import="Model.Dao.EmployeeDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
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

	ArrayList<JournalLedgerBean> lst = new TransactionDao()
			.getAllJournalEnteriesByVoucherId(ad_voucher_id);

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
<%@ include file= "Layout/header.jsp" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-datepicker/css/datepicker.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2-bootstrap.css"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-full-width">
<%@ include file= "Layout/headtitle.jsp" %>
<!-- BEGIN HEADER -->
<div class="header navbar mega-menu">
<!-- BEGIN TOP NAVIGATION BAR -->
<%@ include file= "Layout/navbar.jsp" %>
<!-- END TOP NAVIGATION BAR -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN EMPTY PAGE SIDEBAR -->
	<%@ include file= "Layout/emptynavbar.jsp" %>
	<!-- END EMPTY PAGE SIDEBAR -->
	<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
<div class="page-content">
<!-- BEGIN PAGE CONTENT-->
<div class="row">
<div class="col-md-2 sidebar-content ">
<%@ include file= "Layout/sidebar.jsp" %>
</div>
<div class="col-md-10">
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i>
				<a href="#">Setup</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Transaction</a><i class="fa fa-angle-right"></i>Edit</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<!-- END PAGE HEADER-->
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
<div class="col-md-12">
<!-- Containt Start -->
<%
if(request.getSession()!=null){
Object AppObj = request.getSession().getAttribute("AppMessage");
String[] AppMessage = (String[])AppObj;
if(AppMessage[1].isEmpty()!=true && AppMessage[1].equals("welcome")!=true){ %>
<div class="alert <%=AppMessage[0] %> alert-dismissable">
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
	<%=AppMessage[1] %>
</div>
<%
}
}else{
	response.sendRedirect("logout.jsp");
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

								<form id="frmEditVoucher"
									action="AdVoucher?action=voucheredit&ad_voucher_id=<%=ad_voucher_id%>"
									method="post">

									<table id="tblContainer" class="table table-bordered">
										<tr>
											<td>Trx No.</td>
											
											<td><input type="text" name="trx_no" id="trx_no" class="form-control input-medium"	value="<%=vbean.getTrx_no()%>" /></td>
											<td>Trx Date</td>
											<%
											String date=null;
											if(vbean.getTrx_date()!=null){
												date=new SimpleDateFormat("dd/MM/yyyy").format(vbean.getTrx_date());
											}
											%>
											<td><input class="form-control input-medium date-picker" type="text"	name="trx_date" id="trx_date" 
												value="<%=date%>"/></td>
										</tr>
										<tr>
											<td>Trx Type</td>
											
											<td><select name="vtype" id="vtype"	class="form-control input-medium"  >
													<option value="<%=vbean.getVtype()%>"><%=vbean.getVtype()%></option>
													<option value="Adjustment">Adjustment</option>
													<option value="Cheque">Cheque</option>
													<option value="DD">Demand Draft</option>
													<option value="Online">Online</option>
													<option value="Cash">Cash</option>
											</select></td>
											<td>Voucher From</td>
											
											<td><select name="vfrom" id="vfrom"	class="form-control input-medium"  >
													<option value="<%=vbean.getVfrom()%>"><%=vbean.getVfrom().toUpperCase()%></option>
													<option value="Payroll">Payroll</option>
													<option value="Payment">Payment</option>
													<option value="Recieved">Recieved</option>
													<option value="Loan Interest">Loan Interest</option>
													<option value="Thrift Interest">Thrift Interest</option>
													<option value="Final Payment">Final Payment</option>
													<option value="Adjustment">Adjustment</option>
											</select>
											</td>



										</tr>
										<tr>
											<td>Description</td>
											
											<td><select name="description" id="description"	class="form-control input-medium"  >
													<option value="<%=vbean.getDescription()%>"><%=vbean.getDescription().toUpperCase()%></option>
													<option value="Payroll">Payroll</option>
													<option value="Manual Voucher">Manual Voucher</option>
													<option value="new_share">New Share</option>
													<option value="new_loan">New Loan</option>
													<option value="new_member">New Member</option>
													<option value="loan_interest">Loan Interest</option>
													<option value="final_pay">Final Payment</option>
													<option value="new_fd">New Fixed Deposit</option>
													<option value="renew_fd">Renew Fixed Deposit</option>
													<option value="rediption_fd">Fixed Deposit Redemption</option>
											</select></td>
											<td>Ins.Form</td>
											
											<td><input type="text" name="ins_form" id="ins_form"	class="form-control input-medium"	value="<%=vbean.getInstrument_from().trim().toUpperCase()%>" /></td>
										</tr>
										<tr>
											<td>Ins. No</td>
											
											<td><input type="text" name="ins_no" id="ins_no" class="form-control input-medium"	value="<%=vbean.getInstrument_no()%>" /></td>
											<td>Ins. Date</td>
											
											<td><input class="form-control input-medium date-picker" type="text" name="ins_date" id="ins_date" 
												value="<%=new SimpleDateFormat("dd/MM/yyyy").format(vbean.getInstrument_date())%>" /></td>

										</tr>
										<tr>
											<td>Voucher Amt</td>
											
											<td><input  type="text" name="v_amt"
											class="form-control input-medium amt" id="v_amt" value="<%=vbean.getVamt()%>" /></td>
											<td>Voucher Type</td>
											
											<td><select name="voucher_type" id="voucher_type"	class="form-control input-medium">
													<option value="<%=vbean.getVoucher_type()%>"><%=vbean.getVoucher_type()%></option>
													<option value="Received">Received</option>
													<option value="Payment">Payment</option>
													<option value="Adjustment">Adjustment</option>
											</select></td>

										</tr>


										<tr>
										<td>Instrument Amt</td>
										<td><input  type="text" name="instrument_amt"
											class="form-control input-medium amt" id="instrument_amt" value="<%=vbean.getInstrument_amt()%>" /></td>
											<td>Voucher Status</td>
											
											<td><select name="status" id="status"	class="form-control input-medium">
													<option value="<%=vbean.getStatus()%>"><%=vbean.getStatus()%></option>
													<option value="Approved">Approved</option>
													<option value="Pending">Pending</option>
													<option value="Cancel">Cancel</option>
											</select></td>
										</tr>	
										<tr>
											<td colspan="4" align="center"><input type="submit"
												class="btn btn blue" value="Update Voucher" /> 
												<input type="button" value="Ad More Trx" name="ad_trx" id="ad_trx" class="btn btn green" />
												 <a href="view_voucher_detail.jsp"  class="btn btn-xm blue" >Back</a>
											</td>
										</tr>



									</table>

								</form>

								<table class="table table-bordered">
									<thead>
										<tr style="background: teal; color: white">
											<th style="width: 5%">Sno</th>
											<th style="width: 5%">Trx Date</th>
											<th style="width: 20%">A/c Name</th>
											<th style="width: 20%">Party Name</th>
											<th style="width: 20%">Narration</th>
											<th style="width: 10%">Dr</th>
											<th style="width: 10%">Cr</th>
											<th style="width: 10%">OPT</th>

										</tr>
									</thead>

									<tbody>
										<%
											double totalcr = 0;
											double totaldr = 0;

											int i = 1;
											if (lst.isEmpty() != true) {
												for (JournalLedgerBean bean : lst) {

													String name = "";
													String narration = "";

													if (bean.getMember_name() != null) {
														name = bean.getAd_society_no()+" | "+ bean.getMember_name() ;
													}
													if (bean.getNarration() != null) {
														narration = bean.getNarration();
													}
										%>



										<tr>
											<td><%=i%></td>
											<%
											String date_trx=null;
											if(bean.getTrx_date()!=null){
												date_trx=new SimpleDateFormat("dd/MM/yyyy").format(bean.getTrx_date());
											}
											
											%>
											<td><%=date_trx%></td>
											<td><label style="color: olive;"><%=bean.getAc_name()%></label></td>
											<td><%=name%></td>
											<td><%=narration%></td>
											<td><%=bean.getDramt()%></td>
											<td><%=bean.getCramt()%></td>
											<td><%-- <a
												href="edit_voucher_trx.jsp?ad_trx_id=<%=bean.getAd_trx_id()%>&ad_voucher_id=<%=vbean.getAd_voucher_id()%>" class="btn btn-sm green">Edit</a> --%>
												<a
												href="delete_voucher_trx.jsp?ad_trx_id=<%=bean.getAd_trx_id()%>&ad_voucher_id=<%=vbean.getAd_voucher_id()%>" class="btn btn-sm red">Delete</a>
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
							
							<!-- ---------------------------------statrt modal--------------------------------- -->	
<div class="modal fade ModalAddTrx" id="large"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-blue">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<i class="fa fa-cogs"></i> Add  Transaction
					</h4>
				</div>
				<!-- modal header -->
				<div class="modal-body">
					<div id="custom-page-message2"></div>
					<!-- End custom-page-message -->
<form action="AdTransaction?action=inserttrx&ad_voucher_id=<%=ad_voucher_id %>"  method="post">

<table class="table table-bordered">



<tr>
<td>A/c Head</td>
<td><select name="ad_account_id" id="ad_account_id" class="form-control input-medium" >
<option value="0">--select--</option>
<%LedgerAccountDao ldao=new LedgerAccountDao();
    				ArrayList<LedgerAccountBean> ledgerList=ldao.getAllLedgerAccount();
    				if(ledgerList.isEmpty()!=true){
    				for(LedgerAccountBean bean1:ledgerList){
    					
    					
    				%>
    				 <option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_no()+" | "+ bean1.getAc_name() %></option>	
    				    
    				<%}} %>
    				
    				</select></td>
<td>Member </td>
<td> <select name="ad_member_id" id="ad_member_id" class="form-control input-medium" >
<option value="0">--select--</option>

		 			<%MemberRegistrationDao mdao =new MemberRegistrationDao();
								 	ArrayList<MemberRegistrationBean> malist=mdao.getAllMemberlist();
								 	if(malist.isEmpty()!=true){
								 	for(MemberRegistrationBean bean:malist){
								 	
								 	
								 	%>
								 	
								 <option value="<%=bean.getAd_member_id()%>"><%=bean.getAd_society_no()+" | "+ bean.getName() %></option>
								 <%} }
								 %>
								 </select> </td>
</tr>
<tr>
<td>Staff </td>
<td> <select name="ad_employee_id" id="ad_employee_id" class="form-control input-medium">
<option value="0">--select--</option>

		 			<%EmployeeDao edao =new EmployeeDao();
								 	ArrayList<EmployeeBean> ealist=edao.getAllEmployeeName();
								 	if(malist.isEmpty()!=true){
								 	for(EmployeeBean bean:ealist){
								 	
								 	
								 	%>
								 	
								 <option value="<%=bean.getAd_employee_id()%>"><%=bean.getEmployee_id()+" | "+ bean.getName() %></option>
								 <%} }
								 %> 
								 </select></td>


<td>Trx Date</td>
<td> <input type="text"  value="<%=new SimpleDateFormat("dd/MM/yyyy").format(vbean.getTrx_date())%>" name="trx_date" readonly="readonly" class="form-control input-medium"/> </td>



</tr>
<tr>
<td>Dr Amt</td><td><input  type="text" name="dr_amt" id="dr_amt" value="0" class="form-control input-medium amt"/></td>
<td>Cr Amt</td><td><input  type="text" name="cr_amt" id="cr_amt" value="0" class="form-control input-medium amt"/></td>
</tr>
<tr>
<td>Narration </td><td><input  type="text" name="narration" id="narration" value="" class="form-control input-medium"/></td>


<td colspan="2" align="center" height="5">
<input type="submit" value="Submit" name="submit" class="btn btn-md blue"/>
<input type="button" value="Cancel" name="cancel" class="btn btn-md red"/></td></tr>
	
	</tbody>
</table>
</form>
	</div>
	<!-- modal-body -->
	<!-- modeal-footer -->
	</div>
	<!-- /.modal-content -->
	</div>
<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- ---------------------------------end modal---------------------------------- -->	

<!-- ---------------------------------statrt modal--------------------------------- -->	
<div class="modal fade ModalEditTrx" id="large"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-blue">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<i class="fa fa-cogs"></i> Edit Transaction
					</h4>
				</div>
				<!-- modal header -->
				<div class="modal-body">
					<div id="custom-page-message2"></div>
					<!-- End custom-page-message -->
<form action="AdTransaction?action=edittrx"  method="post">

<table class="table table-bordered">



<tr>
<td>A/c Head 
<input type="hidden" name="ad_trx_id" value=""/>
</td>
<td><select name="ad_account_id" id="ad_account_id" class="form-control input-medium" >
<option value="0">--select--</option>
<% ldao=new LedgerAccountDao();
    				 ledgerList=ldao.getAllLedgerAccount();
    				if(ledgerList.isEmpty()!=true){
    				for(LedgerAccountBean bean1:ledgerList){
    					
    					
    				%>
    				 <option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_no()+" | "+ bean1.getAc_name() %></option>	
    				    
    				<%}} %>
    				
    				</select></td>
<td>Member </td>
<td> <select name="ad_member_id" id="ad_member_id" class="form-control input-medium" >
<option value="0">--select--</option>

		 			<% mdao =new MemberRegistrationDao();
								 	 malist=mdao.getAllMemberlist();
								 	if(malist.isEmpty()!=true){
								 	for(MemberRegistrationBean bean:malist){
								 	
								 	
								 	%>
								 	
								 <option value="<%=bean.getAd_member_id()%>"><%=bean.getAd_society_no()+" | "+ bean.getName() %></option>
								 <%} }
								 %>
								 </select> </td>
</tr>
<tr>
<td>Staff </td>
<td> <select name="ad_employee_id" id="ad_employee_id" class="form-control input-medium">
<option value="0">--select--</option>

		 			<% edao =new EmployeeDao();
								 	 ealist=edao.getAllEmployeeName();
								 	if(malist.isEmpty()!=true){
								 	for(EmployeeBean bean:ealist){
								 	
								 	
								 	%>
								 	
								 <option value="<%=bean.getAd_employee_id()%>"><%=bean.getEmployee_id()+" | "+ bean.getName() %></option>
								 <%} }
								 %> 
								 </select></td>


<td>Trx Date</td>
<td> <input type="text"  value="<%=new SimpleDateFormat("dd/MM/yyyy").format(vbean.getTrx_date())%>" name="trx_date" readonly="readonly" class="form-control input-medium"/> </td>



</tr>
<tr>
<td>Dr Amt</td><td><input  type="text" name="dr_amt" id="dr_amt" value="0" class="form-control input-medium amt"/></td>
<td>Cr Amt</td><td><input  type="text" name="cr_amt" id="cr_amt" value="0" class="form-control input-medium amt"/></td>
</tr>
<tr>
<td>Narration </td><td><input  type="text" name="narration" id="narration" value="" class="form-control input-medium"/></td>


<td colspan="2" align="center" height="5">
<input type="submit" value="Submit" name="submit" class="btn btn-md blue"/>
<input type="button" value="Cancel" name="cancel" class="btn btn-md red"/></td></tr>
	
	</tbody>
</table>
</form>
	</div>
	<!-- modal-body -->
	<!-- modeal-footer -->
	</div>
	<!-- /.modal-content -->
	</div>
<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- ---------------------------------end modal---------------------------------- -->		
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
</div>

<!-- END PAGE CONTENT-->

<!-- END CONTENT -->

<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@ include file= "Layout/footer.jsp" %>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script type="text/javascript" src="assets/scripts/custom/form-wizard-app.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<%
						SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
						String to=sdf.format((Date)session.getAttribute("openday"));
						%>
	<script type="text/javascript">
jQuery(document).ready(function() { 
	$('#vtype').select2();
	$('#status').select2();
	$('#vfrom').select2();
	$('#description').select2();
	$('#voucher_type').select2();
	$('#ad_member_id').select2();
	$('#ad_account_id').select2();
	$('#ad_employee_id').select2();
	
	FormWizardMember.init();
	
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate : '<%=to%>',autoclose: true});
	
	jQuery.validator.addMethod("validDate", function (value, element) {
        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	
	jQuery("#frmEditVoucher").validate({
		  rules: {
			  trx_date:{
				  required: true,
				  validDate:true
			  },
			  to_date:{
				  required: true,
				  validDate:true
			  },
			  ad_account_id:{
				  required: true,
				  digits:true
			  },
			  trx_no:{
				 
				  digits:true
			  },
			  v_amt:{
				  required:true,
				  number:true
			  }
		  }
		});
	
});
</script>
	<script>


	$("#ad_trx").click(function(){
		$(".ModalAddTrx").modal('show');
	});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>