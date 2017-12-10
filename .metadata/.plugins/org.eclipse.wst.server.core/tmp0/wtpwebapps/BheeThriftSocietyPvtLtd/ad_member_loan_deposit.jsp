<%@page import="Model.Bean.LoanTrxDetailBean"%>
<%@page import="Model.Dao.LoanTrxDetailDao"%>
<%@page import="Model.Bean.LoanPurposeBean"%>
<%@page import="Model.Dao.LoanPurposeDao"%>
<%@page import="Model.Bean.MemberRegistrationMasterBean"%>
<%@page import="Model.Dao.MemberRegistrationMasterDao"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Bean.RelationBean"%>
<%@page import="Model.Dao.RelationDao"%>
<%@page import="Model.Dao.CityDao"%>
<%@page import="Model.Bean.DesignationTypeBean"%>
<%@page import="Model.Dao.DesignationTypeDao"%>
<%@page import="Model.Bean.DesignationLevelBean"%>
<%@page import="Model.Dao.DesignationLevelDao"%>
<%@page import="Model.Bean.DesignationBean"%>
<%@page import="Model.Dao.DesignationDao"%>
<%@page import="Model.Bean.DepartmentBean"%>
<%@page import="Model.Dao.DepartmentDao"%>
<%@page import="Model.Bean.BankRegionBean"%>
<%@page import="Model.Dao.BankRegionDao"%>
<%@page import="Model.Bean.CategoryBean"%>
<%@page import="Model.Dao.CategoryDao"%>
<%@page import="Model.Bean.CountryBean"%>
<%@page import="Model.Dao.CountryDao"%>
<%@page import="Model.Bean.CastBean"%>
<%@page import="Model.Dao.CastDao"%>
<%@page import="Model.Bean.GenderBean"%>
<%@page import="Model.Dao.GenderDao"%>
<%@page import="Model.Bean.SalutationBean"%>
<%@page import="Model.Dao.SalutationDao"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Dao.BankDao"%>
<%@page import="Model.Bean.BankBean"%>
<%@page import="Model.Bean.StateBean"%>
<%@page import="Model.Dao.StateDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%@page import="Model.Bean.TypeOfLoanBean"%>
<%@page import="Model.Dao.TypeOfLoanDao"%>
<%@page import="Model.Bean.LoanCategoryBean"%>
<%@page import="Model.Dao.LoanCategoryDao"%>
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
			<li><a href="#">Loan </a><i class="fa fa-angle-right"></i>Loan Deposit</li>
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
try{
Object AppObj = request.getSession().getAttribute("AppMessage");
String[] AppMessage = (String[])AppObj;
if(AppMessage[1].isEmpty()!=true && AppMessage[1].equals("welcome")!=true){ %>
<div class="alert <%=AppMessage[0] %> alert-dismissable">
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
	<%=AppMessage[1] %>
</div>
<%
}
%>
<!------------------------------------------------------------------- -->
<div class="hidden">
<%
String  V_Trx_No= "";
V_Trx_No= (String)request.getParameter("no");
if(V_Trx_No==null){
	V_Trx_No = "";
}
%>
<input type="hidden" name="V_Trx_No" id="V_Trx_No" value="<%=V_Trx_No%>" /><!-- for display message after submit -->
</div>
<!-- BEGIN BORDERED TABLE PORTLET-->	
<div class="portlet box purple" id="form_wizard_1">
<div class="portlet-title">
<div class="caption"><i class="fa fa-reorder"></i> Loan Transaction to member -
<span class="step-title">Step 1 of 8</span>
</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<form id="frmLoanTransaction" autocomplete="off" action="AdLoanTrx?action=deposit" method="post">

<!-- -----------------------------------------Start Form----------------------------------------------- -->	
<div class="form-wizard">
<div class="form-body">
<ul class="nav nav-pills steps">
	<li class="active"><a href="#tab1" data-toggle="tab" class="step">Personal</a></li>
	<li class=""><a href="#tab2" data-toggle="tab" class="step">Contact</a></li>
	<li class=""><a href="#tab3" data-toggle="tab" class="step">Service</a></li>
	<li class=""><a href="#tab4" data-toggle="tab" class="step">Loan Details</a></li>
	<li class=""><a href="#tab5" data-toggle="tab" class="step">Loan Deposit/Close</a></li>
	
</ul>
<div id="bar" class="progress progress-striped" role="progressbar">
	<div class="progress-bar progress-bar-success" style="width: 0%;"></div>
</div>
<div class="tab-content">
	   <div class="alert alert-danger display-none">
		   <button class="close" data-dismiss="alert"></button>
			 You have some form errors. Please check below.
	  </div>
	  <div class="alert alert-success display-none">
		    <button class="close" data-dismiss="alert"></button>
			 Your form validation is successful!
	  </div>
<!-- -----------------------------Start Tab Content------------------------------------------------- -->
	<div class="tab-pane fade active in" id="tab1">
		<table class="table table-bordered">
			<tr>
				<td width="15%">Name : <span class="red">*</span></td>
				<td width="35%">
				  <select name="ad_member_id" id="ad_member_id" class="form-control input-large" style="width:270px;">
					 <option value="">--Select Member--</option>
					   <%MemberRegistrationDao dao =new MemberRegistrationDao();
					   ArrayList<MemberRegistrationBean> alist=dao.getAllMemberHavingLoanAccount();
					   if(alist!=null){
					   for(MemberRegistrationBean bean:alist){%>
					   <option value="<%=bean.getAd_member_id()%>"> <%=bean.getAd_society_no() %> | <%=bean.getName() %></option>
					   <%} 
					  }%>
				 </select><label class="error"></label>
				</td>
				<td width="15%">PF No. :</td>
				<td width="35%"><label name="ad_pf_no" id="ad_pf_no"></label></td>
			</tr>
			<tr>
				<td>Mem.No :</td>
				<td><label for="ad_society_no" id="ad_society_no" ></label></td>
				<td>Type :</td>
				<td><label for="ad_member_type_id" id="ad_member_type_id" ></label></td>
			</tr>
			<tr>
				<td>Status :</td>
				<td><label for="ad_member_status_id" id="ad_member_status_id" ></label></td>
				<td>Father :</td>
				<td><label for="father" id="father" ></label></td>
			</tr>
			<tr>
				<td>Husband :</td>
				<td><label for="husband" id="husband" ></label></td>
				<td>DOB</td>
				<td><label for="dob" id="dob" ></label></td>
			</tr>
			<tr>
				<td>Gender :</td>
				<td><label for="ad_gender_id" id="ad_gender_id" ></label></td>
				<td>Category :</td>
				<td><label for="ad_category_id" id="ad_category_id" ></label></td>
			</tr>
			</table>
			<table class="table table-bordered">
			<tr>
			<td width="25%">
			<div class="userimg-block">
				<div class="user-pic">
				    <img class="uimg" id="photo_view" name="photo_view" src="Image/emp.png" alt="Employee Photo" />
				</div>
				<div class="user-input">
				  <span class="text-center">Photo :</span>
				</div>
			   </div><!-- End userimg-block -->
             </td>
			  <td width="25%">
			  <div class="userimg-block">
			  <div class="user-pic">
				    <img class="uimg" id="id_proof_view" name="id_proof_view" src="Image/id.png" alt="Employee Id Card"  />
				</div>
				<div class="user-input">
				  <span class="text-center">ID Proof :</span>
				</div>
			   </div><!-- End userimg-block -->
            </td>
				<td colspan="2" width="25%">
				<div class="userimg-block">
			     <div class="user-pic">
				    <img class="uimg" id="sign_view" name"sign_view" src="Image/sign.png" alt="Employee Sign"  />
				 </div>
				 <div class="user-input">
				  <span class="text-center">Sign :</span>
				</div>
			   </div><!-- End userimg-block -->
				</td>
			</tr>
		</table>	
	</div><!-- End Tab Personal -->
<!-- ------------------------------------------------------End Personal----------------------------- -->
	<div class="tab-pane fade" id="tab2">
		<!-- --------------------------------------------------------------- -->
<!-- BEGIN BORDERED TABLE PORTLET-->
<div class="portlet box purple">
<div class="portlet-title">
<div class="caption">Contact Detail</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<table class="table table-bordered">
  <thead>
  	<tr><th colspan="6">Present Address</th></tr>
  </thead>
  <tbody>
		<tr>
	   	 <td>Mobile</td>
	   	 <td><input type="text" class="form-control input-medium" name="mobile" id="mobile" disabled="disabled" /></td>
	   	 <td>Phone</td>
	   	 <td><input type="text" class="form-control input-medium" name="phone" id="phone" disabled="disabled" /></td>
	   	 <td>Email</td>
	   	 <td><input type="text" class="form-control input-medium" name="email" id="email" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Street 1</td>
	   	 <td><input type="text" class="form-control input-medium" name="line1" id="line1" disabled="disabled" /></td>
	   	 <td>Street 2</td>
	   	 <td><input type="text" class="form-control input-medium" name="line2" id="line2" disabled="disabled" /></td>
	   	 <td>Country</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_country_id" id="ad_country_id" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>State</td>
	   	 <td colspan="2"><input type="text" class="form-control" name="ad_state_id" id="ad_state_id" disabled="disabled" /></td>
	   	 <td>District</td>
	   	 <td colspan="2"><input type="text" class="form-control" name="ad_district_id" id="ad_district_id" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>City</td>
	   	 <td colspan="2"><input type="text" class="form-control" name="ad_city_id" id="ad_city_id" disabled="disabled" /></td>
	   	 <td>Pincode</td>
	   	 <td colspan="2"><input type="text" class="form-control" name="pincode" id="pincode" disabled="disabled" /></td>
	   </tr>
	</tbody>
</table>
<table class="table table-bordered">
  <thead>
  	<tr><th colspan="6">Permanent Address</th></tr>
  </thead>
  <tbody>
		<tr>
	   	 <td>Mobile</td>
	   	 <td><input type="text" class="form-control input-medium" name="mobile1" id="mobile1" disabled="disabled" /></td>
	   	 <td>Phone</td>
	   	 <td><input type="text" class="form-control input-medium" name="phone1" id="phone1" disabled="disabled" /></td>
	   	 <td>Email</td>
	   	 <td><input type="text" class="form-control input-medium" name="email1" id="email1" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Street 1</td>
	   	 <td><input type="text" class="form-control input-medium" name="line1_1" id="line1_1" disabled="disabled" /></td>
	   	 <td>Street 2</td>
	   	 <td><input type="text" class="form-control input-medium" name="line2_1" id="line2_1" disabled="disabled" /></td>
	   	 <td>Country</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_country_id_1" id="ad_country_id_1" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>State</td>
	   	 <td colspan="2"><input type="text" class="form-control" name="ad_state_id_1" id="ad_state_id_1" disabled="disabled" /></td>
	   	 <td>District</td>
	   	 <td colspan="2"><input type="text" class="form-control" name="ad_district_id_1" id="ad_district_id_1" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>City</td>
	   	 <td colspan="2"><input type="text" class="form-control" name="ad_city_id_1" id="ad_city_id_1" disabled="disabled" /></td>
	   	 <td>Pincode</td>
	   	 <td colspan="2"><input type="text" class="form-control" name="pincode1" id="pincode1" disabled="disabled" /></td>
	   </tr>
	</tbody>
</table>
</div><!-- End portlet-body -->
</div>
    </div><!-- End Tab Contact -->
<!-- ------------------------------------------------------End Contact----------------------------- -->
<div class="tab-pane fade" id="tab3">
	<table class="table table-bordered">
	<thead>
			<tr><th colspan="4">Service Detail</th></tr>
	</thead>
	<tbody>
		<tr>
			<td width="15%">Branch :</td>
			<td width="35%"><label for="ad_branch_id" id="ad_branch_id" ></label></td>
			<td width="15%">Region :</td>
			<td width="35%"><label for="ad_bank_region_id" id="ad_bank_region_id" ></label></td>
		</tr>
		<tr>
			<td>Code :</td>
			<td><label for="branch_code" id="branch_code" ></label></td>
			<td>IFSC :</td>
			<td><label for="ifsc_code" id="ifsc_code" ></label></td>
		</tr>
		<tr>
			<td>State :</td>
			<td><label for="ad_bank_state_id" id="ad_bank_state_id" ></label></td>
			<td>District :</td>
			<td><label for="ad_bank_district_id" id="ad_bank_district_id" ></label></td>
		</tr>
		<tr>
			<td>City :</td>
			<td><label for="ad_bank_city_id" id="ad_bank_city_id" ></label></td>
			<td>Pincode :</td>
			<td><label for="bank_pincode" id="bank_pincode" ></label></td>
		</tr>
		<tr>
			<td>Email :</td>
			<td><label for="email_id" id="email_id" ></label></td>
			<td>Phone :</td>
			<td><label for="phone_no" id="phone_no" ></label></td>
		</tr>
		<tr>
			<td>Person :</td>
			<td><label for="contact_person" id="contact_person" ></label></td>
			<td>Contact :</td>
			<td><label for="contact_no" id="contact_no" ></label></td>
		</tr>
		<tr>
			<td>Address :</td>
			<td colspan="3"><label for="address" id="address" ></label></td>
		</tr>
	</tbody>
	</table>
	<table class="table table-bordered">
	  <thead>
			<tr><th colspan="4">Other Information</th></tr>
	 </thead>
	 <tbody>
		  <tr>
			<td>Pan No :</td>
			<td><label for="pan_no" id="pan_no" ></label></td>
			<td>Aadhar :</td>
			<td><label for="aadhar_no" id="aadhar_no" ></label></td>
		</tr>
		<tr>
			<td>Department :</td>
			<td><label for="ad_department_id" id="ad_department_id" ></label></td>
			<td>Designation :</td>
			<td><label for="ad_designation_id" id="ad_designation_id" ></label></td>
		</tr>
		<tr>
			<td>Level :</td>
			<td><label for="ad_designation_level_id" id="ad_designation_level_id" ></label></td>
			<td>Type :</td>
			<td><label for="ad_designation_type_id" id="ad_designation_type_id" ></label></td>
		</tr>
		<tr>
			<td>Appointment :</td>
			<td><label for="doa" id="doa" ></label></td>
			<td>Retirement :</td>
			<td><label for="dor" id="dor" ></label></td>
		</tr>
		<tr>
			
			<td>Service Duration</td>
			<td><label for="service_duration" id="service_duration" ></label></td>
			<td>Account No. :</td>
			<td colspan="3"><label for="saving_ac_no" id="saving_ac_no" ></label></td>
		</tr>
		<tr>
			
		</tr>
	 </tbody>
	</table>
</div><!-- End Tab Service -->
<!-- ------------------------------------------------------End Service----------------------------- -->
<div class="tab-pane fade" id="tab4">
	<div class="row">
		<div class="col-md-12">
			<div id="show_loan_detail"></div>
			<table class="table table-bordered" id="tblIntEMiVerifySalary">
		<tr>
			
			<td align="center"><input class="btn btn-block red input-medium" onclick="loadLoanDeposit()" type="button" name="caldepositamt" id="caldepositamt" value="Select loan & Click Here"/></td>
			
		</tr>
	</table>
		</div><!-- End column -->
	</div><!-- End row -->
</div><!-- End Tab Loan Details -->
<!-- ------------------------------------------------------End Loan Details----------------------------- -->
<div class="tab-pane fade" id="tab5">
	
   <div id="loan_deposit_div">
   
   
   </div>
  


</div><!-- End Tab loan deposit Info -->
<!-- ------------------------------------------------------End deposit---------------------------->


<!-- -----------------------------End Tab Content -------------------------------------------------- -->		
</div><!--End tab-content-->	
</div><!-- End form-body -->
</div><!-- End form-wizard -->
<div class="form-actions fluid">
	 <div class="row">
		 <div class="col-md-12">
			<div class="col-md-offset-3 col-md-9">
			<a href="javascript:;" class="btn default button-previous disabled" style="display: none;">
				<i class="m-icon-swapleft"></i> Back
			</a>
			<a id="btnContinue" href="javascript:;" class="btn blue button-next">
				Continue <i class="m-icon-swapright m-icon-white"></i>
			</a>
			<a href="#"  class="btn green button-submit" id="btnloanSubmit"  style="display: none;">Submit <i class="m-icon-swapright m-icon-white"></i></a>
		   </div>
		 </div>
	 </div>
</div><!--End form-actions fluid -->
<!-- -----------------------------------------End  Form ----------------------------------------------- -->
</form>	
</div><!-- End portlet-body -->
</div>
<!------------------------------------------------------------------- -->			
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
<%@ include file= "Layout/footer.jsp" %>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script type="text/javascript" src="assets/scripts/custom/form-wizard-loan-deposit.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script type="text/javascript">
jQuery(document).ready(function() {       
	FormWizardMember.init();
	$('#ad_member_id').select2();

	$('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true});
	$('.date-picker1').datepicker({format: 'dd/mm/yyyy',autoclose: true,endDate:'+0d'});
	
	
});
</script>
<script type="text/javascript">
<% String imagePath = request.getContextPath(); %>
jQuery(document).ready(function() {  
$("#ad_member_id").change(function(e) {
	
	var id = $(this).val();
	$("#il_ad_member_id").val(id);
	var imagePath = "<%=imagePath%>"+"/member_images/";
	var imagePhotoPath = "<%=imagePath%>"+"/Image/emp.png";
	var imageIdPath = "<%=imagePath%>"+"/Image/id.png";
	var imageSignPath = "<%=imagePath%>"+"/Image/sign.png";
	if(id.trim()!=""){
	var dataString = {"action":"view","ad_member_id":id};
	 $.ajax({
         type: "POST",
         url: "AdMemberRegistration",
         data: dataString,
         dataType: "json",
         success: function( data) {
             //our country code was correct so we have some information to display
             
              if(data.success){
            	  $("#ad_pf_no").html(data.MemberInfo.ad_pf_no);
            	  $("#ad_society_no").html(data.MemberInfo.ad_society_no);
            	  $("#father").html(data.MemberInfo.father);
            	  $("#husband").html(data.MemberInfo.husband);
            	  $("#dob").html(data.MemberInfo.dob);
            	  $("#ad_gender_id").html(data.MemberInfo.gender.gender);
            	  $("#ad_category_id").html(data.MemberInfo.category.category);
            	  $("#ad_member_type_id").html(data.MemberInfo.member_type.member_type);
            	  $("#ad_member_status_id").html(data.MemberInfo.member_status.member_status);
            	  $("#pan_no").html(data.MemberInfo.pan_no);
            	  $("#aadhar_no").html(data.MemberInfo.aadhar_no);
            	  $("#ad_branch_id").html(data.MemberInfo.branch.branch);
            	  //$("#ad_bank_region_id").html(data.MemberInfo.branch.bank_region.region);
            	  $("#branch_code").html(data.MemberInfo.branch.branch_code);
            	  $("#ifsc_code").html(data.MemberInfo.branch.ifsc_code);
            	  //$("#ad_bank_state_id").html(data.MemberInfo.branch.state.state);
            	  //$("#ad_bank_district_id").html(data.MemberInfo.branch.district.district);
            	  //$("#ad_bank_city_id").html(data.MemberInfo.branch.city.city);
            	  $("#bank_pincode").html(data.MemberInfo.branch.pincode);
            	  $("#email_id").html(data.MemberInfo.branch.email_id);
            	  $("#phone_no").html(data.MemberInfo.branch.phone_no);
            	  $("#contact_person").html(data.MemberInfo.branch.contact_person);
            	  $("#contact_no").html(data.MemberInfo.branch.contact_no);
            	  $("#address").html(data.MemberInfo.branch.address);
            	  $("#ad_department_id").html(data.MemberInfo.department.name);
            	  $("#ad_designation_id").html(data.MemberInfo.designation.designation);
            	  $("#ad_designation_level_id").html(data.MemberInfo.designation_level.designation_level);
            	  $("#ad_designation_type_id").html(data.MemberInfo.designation_type.designation_type);
            	  $("#doa").html(data.MemberInfo.doa);
            	  $("#doj").html(data.MemberInfo.doj);
            	  $("#dor").html(data.MemberInfo.dor);
            	  $("#service_duration").html(data.MemberInfo.service_duration);
            	  $("#saving_ac_no").html(data.MemberInfo.saving_ac_no);
            	  $("#witness_ad_member_id").val(data.MemberInfo.ad_witness_id);
            	  $("#mobile").html(data.MemberInfo.address[0].mobile);
            	  $("#phone").html(data.MemberInfo.address[0].phone);
            	  $("#email").html(data.MemberInfo.address[0].email);
            	  $("#line1").html(data.MemberInfo.address[0].line1);
            	  $("#line2").html(data.MemberInfo.address[0].line2);
            	  $("#ad_country_id").val(data.MemberInfo.address[0].country.country);
            	  $("#ad_state_id").val(data.MemberInfo.address[0].state.state);
            	  $("#ad_district_id").val(data.MemberInfo.address[0].district.district);
            	  $("#ad_city_id").val(data.MemberInfo.address[0].city.city);
            	  $("#pincode").html(data.MemberInfo.address[0].pincode);
            	  
            	  $("#mobile").val(data.MemberInfo.address[0].mobile);
            	  $("#phone").val(data.MemberInfo.address[0].phone);
            	  $("#email").val(data.MemberInfo.address[0].email); 
            	  $("#line1").val(data.MemberInfo.address[0].line1);
            	  $("#line2").val(data.MemberInfo.address[0].line2);
            	  $("#ad_country_id").val(data.MemberInfo.address[0].country.country);
            	  $("#ad_state_id").val(data.MemberInfo.address[0].state.state);
            	  $("#ad_district_id").val(data.MemberInfo.address[0].district.district);
            	  $("#ad_city_id").val(data.MemberInfo.address[0].city.city);
            	  $("#pincode").val(data.MemberInfo.address[0].pincode);
            	  
            	  $("#mobile1").val(data.MemberInfo.address[1].mobile);
            	  $("#phone1").val(data.MemberInfo.address[1].phone);
            	  $("#email1").val(data.MemberInfo.address[1].email);
            	  $("#line1_1").val(data.MemberInfo.address[1].line1);
            	  $("#line2_1").val(data.MemberInfo.address[1].line2);
            	  $("#ad_country_id_1").val(data.MemberInfo.address[1].country.country);
            	  $("#ad_state_id_1").val(data.MemberInfo.address[1].state.state);
            	  $("#ad_district_id_1").val(data.MemberInfo.address[1].district.district);
            	  $("#ad_city_id_1").val(data.MemberInfo.address[1].city.city);
            	  $("#pincode1").val(data.MemberInfo.address[1].pincode);
            	  
            	  
            	  if(data.MemberInfo.photo!="" && data.MemberInfo.photo!=="undefined" ){
            		  
            		  $("#photo_view").attr("src",imagePath+data.MemberInfo.photo);
            	  }else{
            		  $("#photo_view").attr("src",imagePhotoPath);
            	  }
            	  
            	  if(data.MemberInfo.id_proof!=null){
            		  $("#id_proof_view").attr("src",imagePath+data.MemberInfo.id_proof);
            	  }else{
            		  $("#id_proof_view").attr("src",imageIdPath);
            	  }
            	  console.log("chk image"+data.MemberInfo.id_proof);
            	  
            	  if(data.MemberInfo.signature!=""){
            		  $("#sign_view").attr("src",imagePath+data.MemberInfo.signature);
            	  }else{
            		  $("#sign_view").attr("src",imageSignPath);
            	  }
              }
         },error: function(jqXHR, textStatus, errorThrown){
              console.log("Something really bad happened " + textStatus);
               $("#ajaxResponse").html(jqXHR.responseText);
         }

     });//end ajax
     
	 loadLoanDetail(id);
	// loadLoanDeposit(id);
     
	}else{
		console.log('please select valid member id');
	}
	 
});//end get information from member id




function loadLoanDetail(id){
	var dataString = {"ad_member_id":id};
	 $.ajax({
        type: "POST",
        url: "Ajax/openloandetail.jsp",
        data: dataString,
        success: function(data) {
             	$("#show_loan_detail").html(data); 
             	
             	
        },error: function(jqXHR, textStatus, errorThrown){
             console.log("Something really bad happened " + textStatus);
              $("#ajaxResponse").html(jqXHR.responseText);
        }

    });
}//end loadLoanDetail function



});//end dom


function loadLoanDeposit(){
	//alert();
	var loanid=parseInt($("input[name='loanchk']:checked").val());
	var id = $("#ad_member_id").val();
	//alert(loanid+" "+id);
	if(loanid!=0 && isNaN(loanid)!=true){
		
	var dataString = {"ad_member_id":id,"loan_trx_id":loanid};
	 $.ajax({
        type: "POST",
        url: "Ajax/openloandetailfordeposit1.jsp",
        data: dataString,
        success: function( data, textStatus, jqXHR) {
             	$("#loan_deposit_div").html(data);
             	$("#btnContinue").removeAttr("disabled");
             	
         
        },
        error: function(jqXHR, textStatus, errorThrown){
        
             console.log("Something really bad happened " + textStatus);
              $("#ajaxResponse").html(jqXHR.responseText);
        }
    }); 
}else{
	
	$('#custom-page-message').html("<div class='note note-warning'>Please select loan </div>");
	    $('.custom-messageBox').modal('show');
}
}//end loadLoanDeposit function


</script>


<script type="text/javascript">
$(document).ready(function(e){
	
	$("#loan_date").change(function(e){
			var loan_date    =	$(this).val();
			var period_month =  $("#period_month option:selected").val();
			
			if(period_month==""){
				$('#custom-page-message').html("<div class='note note-info'>please select month!</div>");
				$('.custom-messageBox').modal('show');
			}else if(loan_date.trim()==''){
				$('#custom-page-message').html("<div class='note note-info'>please select loan Date!</div>");
				$('.custom-messageBox').modal('show');
			}else{
				
			var dataString = "action=ad_date&loan_date="+loan_date+"&period_month="+period_month;
				$.ajax({
					type: "POST",
					url: "AdLoanTrx",
					data: dataString,
					success: function(data) {             
						$("#end_date").val(data);                 
				},		            
				error: function(jqXHR, textStatus, errorThrown){
					console.log("Something really bad happened " + textStatus);
				 }
							  
				});//end ajax  
		
	        }//end check if
			
	  });//end loan date change function
		
	  
	$('#btnloanSubmit').click(function(e){
		
		var total=$('#total_amt').val();
		var deposit_amt=$('#total_pay_amt1').val();
		
		if(parseInt(total)==0 || parseInt(total)<0){
			
			$('#custom-page-message').html("<div class='note note-info'>Please Enter Valid Deposit Amt..!!</div>");
			$('.custom-messageBox').modal('show');
		}else if(parseInt(deposit_amt)==0 || parseInt(deposit_amt)<0){
			
			$('#custom-page-message').html("<div class='note note-info'>Please Enter Valid Adjustment Amt..!!</div>");
			$('.custom-messageBox').modal('show');
		}else if(total==deposit_amt && total!=0 && total>0 && deposit_amt!=0 && deposit_amt>0){
			
			$('#frmLoanTransaction').submit();
		}else{
			
			$('#custom-page-message').html("<div class='note note-info'>Please Enter Valid Adjustment Amt..!!</div>");
			$('.custom-messageBox').modal('show');
		}
	});
	
	});//end dom

</script>
<script type="text/javascript">
$(function(){
	var V_Trx_No = $("#V_Trx_No").val();
	
	if(V_Trx_No.trim()!=""){
		$('.custom-messageBox').modal('show');
		$('#custom-page-message').html("Transaction number is "+V_Trx_No);
	}
	
});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>		
		
</body>
</html>