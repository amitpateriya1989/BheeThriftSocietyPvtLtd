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
<%@page import="Model.Bean.TypeOfFdBean"%>
<%@page import="Model.Dao.TypeOfFdDao"%>
<%@page import="Model.Bean.FdRoiBean"%>
<%@page import="Model.Dao.FdRoiDao"%>
<%@page import="Model.Bean.FdCategoryBean"%>
<%@page import="Model.Dao.FdCategoryDao"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%@ include file= "Layout/header.jsp" %>
<link rel="stylesheet" href="assets/plugins/data-tables/DT_bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2-bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-datepicker/css/datepicker.css"/>
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
			<li><i class="fa fa-home"></i><a href="#">Fd</a><i class="fa fa-angle-right"></i></li>
			<li><a href="#">Fixed Deposit Renewal</a><i class="fa fa-angle-right"></i>Renew</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB -->
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
<!------------------------------------------------------------------- -->
<!------------------------------------------------------------------- -->
<!-- BEGIN BORDERED TABLE PORTLET-->	
<div class="portlet box purple" id="form_wizard_1">
<div class="portlet-title">
<div class="caption"><i class="fa fa-reorder"></i> Fixed Deposit Renewal -
<span class="step-title">Step 1 of 4</span>
</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<form id="frmReNewIssueFD" autocomplete="off" action="AdFdTrx?action=reinsert" method="post">
<!-- -----------------------------------------Start Form----------------------------------------------- -->	
<div class="form-wizard">
<div class="form-body">
<ul class="nav nav-pills steps">
	<li class="active"><a href="#tab1" data-toggle="tab" class="step">Personal</a></li>
	<li class=""><a href="#tab2" data-toggle="tab" class="step">Contact</a></li>
	<li class=""><a href="#tab3" data-toggle="tab" class="step">Service</a></li>
	<li class=""><a href="#tab4" data-toggle="tab" class="step">Renew FD</a></li>
	<!-- <li class=""><a href="#tab5" data-toggle="tab" class="step">View Existing FD</a></li> -->
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
					   ArrayList<MemberRegistrationBean> alist=dao.getAllMemberFDlist();
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
    </div><!-- End Tab Contact -->
<!-- ------------------------------------------------------End Service----------------------------- -->
<div class="tab-pane fade" id="tab4">
	<div class="row">
		<div class="col-md-12">
			<input type="hidden" name="ad_member_id_nfd" id="ad_member_id_nfd"/>
			<input type="hidden" name="ad_member_id_renfd" id="ad_member_id_renfd"/>
			<table class="table table-bordered">
			<tr>
				<td width="15%">Fd Renew Date : <span class="red">*</span></td>
				<td width="35%">
				   <input class="form-control input-medium date-picker"  name="re_opening_date" id="re_opening_date" placeholder="DD/MM/YYYY" value="<%=new SimpleDateFormat("dd/MM/yyyy").format(session.getAttribute("openday")) %>" />
				<label class="error"></label>
				</td>
				<td width="15%">Select Fd No <span class="red">*</span></td>
				<td width="35%">
				   <select class="form-control input-medium" name='fd_no' id="fd_no"></select>
			   </td>
			</tr>
			<tr>
				<td colspan="4" id="fd_content" ></td>
			</tr>
			<tr>
				<td>Fd Type : <span class="red">*</span></td>
				<td>
				  <select class="form-control input-medium" name='re_ad_type_of_fd_id' id="re_ad_type_of_fd_id">
						<option value=''>--select--</option>
						<%
						 TypeOfFdDao daoar=new TypeOfFdDao();
					 	ArrayList<TypeOfFdBean> alistar=daoar.getAlltypeoffd();
					 	if(alistar!=null){
					 	for(TypeOfFdBean bean:alistar){%>
					    <option value="<%=bean.getAd_type_of_fd_id()%>"><%=bean.getName() %></option>
					 <%} 
					 }%>	
				</select>
				<label class="error"></label></td>
				<td>Fd Category : <span class="red">*</span></td>
				<td>
				<select class="form-control input-medium" name='re_ad_fd_category_id' id="re_ad_fd_category_id">
					<option value=''>--select--</option>
						<%
						 FdCategoryDao dao1r =new FdCategoryDao();
					 	ArrayList<FdCategoryBean> alist1r=dao1r.getAllFdCategory();
					 	if(alist1r!=null){
					 	for(FdCategoryBean bean:alist1r){%>
					 <option value="<%=bean.getAd_fd_category_id()%>"><%=bean.getName() %></option>
					 <%} 
					 }%>
				</select>
				<label class="error"></label>
				</td>
			</tr>
			<tr>
			<td>FD AMT : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text" id="fdamt"  name="fdamt" readonly="readonly" /><label class="error"></label></td>
				<td>FD Intrest : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text" id="fdint"  name="fdint" readonly="readonly" /><label class="error"></label></td>
				
				
			<tr>
			<td>Extra Days Intrest : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text" id="extra_day_int"  name="extra_day_int" readonly="readonly" /><label class="error"></label></td>
				
				<td>Total Fd Intrest : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text" id="tfdint"  name="tfdint" readonly="readonly"/><label class="error"></label>
				</td>
				
			</tr>
			<tr>
				
				<td>Part Payment <span class="red">*</span></td>
				<td><select class="form-control input-medium"  id="partpayment"  name="partpayment" ><option value="no">NO</option><option value="yes">Yes</option></select><label class="error"></label>
				</td>
				
				
			</tr>
			<tr>
			<td>Total Renew Fd Amt : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text" id="re_fd_amt"  name="re_fd_amt" readonly="readonly"/><label class="error"></label>
				</td>
				<td>Period in Month's : <span class="red">*</span></td>
				<td><select class="form-control input-medium" name="re_time_period"  id="re_time_period"><option value="0">--Select--</option></select>
				    <label class="error"></label></td>
				
			</tr>
			<tr>
			<td>Maturity Date : <span class="red">*</span></td>
				<td><input class="form-control input-medium " type="text" placeholder="DD/MM/YYYY" id="re_maturity_date" name="re_maturity_date"  /><label class="error"></label>
				</td>
				<td>Intrest Rate : <span class="red">*</span></td>
				<td><input class="form-control input-medium" readonly="readonly" type="text" readonly="readonly" name="re_interest_rate" id="re_interest_rate"/><label class="error"></label></td>					
				
			</tr>
			<tr>
			<td>Interest Amount : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text" name="re_intrest_amt" id="re_intrest_amt" readonly="readonly"/><label class="error"></label></td>	
				<td>Maturity Amount : <span class="red">*</span></td>
				<td colspan="3"><input class="form-control input-medium" type="text" name="re_maturity_amt" id="re_maturity_amt" readonly="readonly"/><label class="error"></label></td>
			</tr>
			</table>
			
		</div><!-- End column -->
	</div><!-- End row -->
</div><!-- End Tab Loan Details -->
<!-- ----------------------------------------------End ReNew FD Application----------------------------- -->
<div class="tab-pane fade" id="tab5">
		 <div id="vfdcontent"></div>
</div><!-- End Deposit / close Loan -->
<!-- ----------------------------------------------End View Existing FD ------------------------------- -->
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
			<button  class="btn green button-submit" style="display: none;">Submit <i class="m-icon-swapright m-icon-white"></i></button>
		   </div>
		 </div>
	 </div>
</div><!--End form-actions fluid -->
<!-- -----------------------------------------End  Form ----------------------------------------------- -->
</form>	
</div><!-- End portlet-body -->
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
<%@ include file= "Layout/footer.jsp" %>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script type="text/javascript" src="assets/scripts/custom/form-wizard-renew-fd.js"></script>
<% String imagePath = request.getContextPath();
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	String date1=sdf.format((Date)session.getAttribute("openday"));
%>
<script type="text/javascript">
jQuery(function(){
	FormWizardReNewFD.init();
	jQuery('#ad_member_id').select2();
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate:'<%=date1%>',  autoclose: true});
});
</script>
<script type="text/javascript">
$(document).ready(function(e) {

	$("#ad_member_id").change(function(e) {
		
        loading_show();
		
		var imagePath = "<%=imagePath%>"+"/member_images/";
		var imagePhotoPath = "<%=imagePath%>"+"/Image/emp.png";
		var imageIdPath = "<%=imagePath%>"+"/Image/id.png";
		var imageSignPath = "<%=imagePath%>"+"/Image/sign.png";
		
		var id = $(this).val();
		$("#ad_member_id_nfd").val(id);
		$("#ad_member_id_renfd").val(id);
		var dataString = "action=view&ad_member_id="+id;
		 $.ajax({
             type: "POST",
             url: "AdMemberRegistration",
             data: dataString,
             dataType: "json",
            
             //if received a response from the server
             success: function( data, textStatus, jqXHR) {
                 //our country code was correct so we have some information to display
                 
                  if(data.success){
                	//alert(data.MemberInfo.branch.bank_region.region);
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
                	  loading_hide();
                  } 
                  //display error message
                  else {
                      //$("#ajaxResponse").html("<div><b>Country code in Invalid!</b></div>");
                  }
             },
            
             //If there was no resonse from the server
             error: function(jqXHR, textStatus, errorThrown){
               console.log("Something really bad happened " + textStatus);
                   //$("#ajaxResponse").html(jqXHR.responseText);
             }
  
         });  
		 
		 //for only existing fd and redeption
		 var dataString = "action=getfdbymem&ad_member_id="+id;
		 $.ajax({
             type: "POST",
             url: "AdFdTrx",
             data: dataString,
             dataType: "json",
             //if received a response from the server
             success: function( data, textStatus, jqXHR) {
                 //our country code was correct so we have some information to display
                $("#fd_no").html("<option value='0'>--select--</option>'");
                $("#fd_no_rede").html("<option value='0'>--select--</option>'");
                  if(data.success){
                	 
                	  $.each(data, function() { 
                          $.each(this, function(key, value){
                        	  $("#fd_no").append("<option value='"+value.ad_fd_trx_id+"'>"+value.fd_no+"</option>'");
                        	  $("#fd_no_rede").append("<option value='"+value.ad_fd_trx_id+"'>"+value.fd_no+"</option>'");
                              
                              
                          });
                	  });
                	  
                  } 
                  //display error message
                  else {
                	  $("#int_rate").val('');
	                  $("#show_max").html("Max Limit Of Loan ");
                  }
             },
            
             //If there was no resonse from the server
             error: function(jqXHR, textStatus, errorThrown){
                  console.log("Something really bad happened " + textStatus);
                   //$("#ajaxResponse").html(jqXHR.responseText);
             }
  
         });
		 //end existing fd and redeption
	 
	
		 
 	});//end change member id
 	
 	
	
	
	

});//end dom

</script>
<script>
	$(document).ready(function(e){
		$("#re_ad_type_of_fd_id").attr("disabled","disabled");
		$("#re_ad_fd_category_id").attr("disabled","disabled");
							
		var re_ad_type_of_fd_id= '';
		var re_ad_fd_category_id= '';
		
		$("#fd_no").change(function(e){
		var ad_fd_trx_id= $(this).val();
		var re_opening_date=$("#re_opening_date").val();
								
		if(re_opening_date==""){
				alert("Please Select Renew Date First....!");
				$(this).val(0);
				event.stop();
		}
								
		if(parseInt(ad_fd_trx_id)!=0 && re_opening_date!=''){

			var dataString = "action=getfdbyid&ad_fd_trx_id="+ad_fd_trx_id+"&maturity_date="+re_opening_date;
			$.ajax({
			type: "POST",
			url: "AdFdTrx",
			data: dataString,
			dataType: "json",
			success: function( data, textStatus, jqXHR) {
				
			if(data[3]==0){	
				
				
				
			  var intrate= data[1].roi;
           	  var time_period=data[1].time_period;
           	  var frequency=data[1].compound_frequency;
           	  var maturity_amt=parseFloat(data[0].fd_amt)*Math.pow((1+ parseFloat((parseFloat(data[1].roi)/100)/frequency)),(time_period/12)*frequency);
               
           	  
           	  
           	  
				
			var intr=Math.round(parseFloat(maturity_amt-data[0].fd_amt));
			 var one_day_int=intr/(time_period*365);
						                	
			$("#fd_content").html("<table class='table table-bordered' border='1' width='100%'><tr><th>FD No</th><th>FD Amt</th><th>Opening Date</th><th>Time Period</th><th>Maturity Date</th><th>Int Rate</th><th>Int Amt</th><th>Total Amt</th>"+
				"<tr><td>"+data[0].fd_no+"</td><td>"+data[0].fd_amt+"</td><td>"+data[0].opening_date+"</td><td>"+data[1].time_period+"</td><td>"+data[0].maturity_date+"</td><td>"+data[1].roi+"%</td><td>"+intr+"</td><td>"+(Math.round(parseFloat(maturity_amt)))+"</td>"+
				"</table>");
			$("#fdamt").val(data[0].fd_amt);
			$("#fdint").val(intr);			                	  
			$("#extra_day_int").val(Math.round(parseFloat(data[2])*parseFloat(one_day_int)),2);
			$("#tfdint").val(Math.round(parseFloat(data[2])*parseFloat(one_day_int)+intr),2);				                	 
			var amt=Math.round(parseFloat(data[2])*parseFloat(one_day_int)+parseFloat(data[0].fd_amt)+parseInt(intr),2);
							                	
			$("#re_fd_amt").val(amt);
			//$("#re_ad_type_of_fd_id").removerAttr("disabled");
			//$("#re_ad_fd_category_id").removerAttr("disabled");
			$("#re_ad_type_of_fd_id").val(data[1].ad_type_of_fd_id);
			$("#re_ad_fd_category_id").val(data[1].ad_fd_category_id);
			//$("#re_ad_type_of_fd_id").removerAttr("disabled");
			//$("#re_ad_fd_category_id").removerAttr("disabled");
			$("#re_ad_type_of_fd_id").change();
			}else{
				alert("You not select Correct maturity date of this FD");
				
			}
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log("Something really bad happened " + textStatus);
				//$("#ajaxResponse").html(jqXHR.responseText);
			}
			});
									 
		}else{
			$("#fd_content").empty();
		}
								
	});
							
	$("#re_ad_type_of_fd_id").change(function(e){
		re_ad_type_of_fd_id=$(this).val();
		re_ad_fd_category_id=$("#re_ad_fd_category_id").val();
								
		if(parseInt(re_ad_type_of_fd_id)!=0 && parseInt(re_ad_fd_category_id)!=0){
									
		var dataString = "action=getroirate&ad_type_of_fd_id="+re_ad_type_of_fd_id+"&ad_fd_category_id="+re_ad_fd_category_id;
		$.ajax({
			type: "POST",
			url: "AdFdRoi",
			data: dataString,
			dataType: "json",
			//if received a response from the server
			success: function( data, textStatus, jqXHR) {
			//our country code was correct so we have some information to display
			if(data.success){
			$("#re_time_period").html("<option value='0' >--Select--</option>");
				//  alert(data.FdInfo.);
			    $.each(data, function() { 
				$.each(this, function(key, value){
					//alert(value.ad_fd_roi_id);
					$("#re_time_period").append("<option value='"+value.ad_fd_roi_id+"'>"+value.time_period+" 'Month'</option>'");
					});
				});
			} 
			//display error message
			else {
				$("#re_time_period").html("<option value='0' >--Select--</option>");
			}
			},
			//If there was no resonse from the server
			error: function(jqXHR, textStatus, errorThrown){
				console.log("Something really bad happened " + textStatus);
				//$("#ajaxResponse").html(jqXHR.responseText);
			}
			});
			}else{
				$("#re_time_period").html("<option value='0' >--Select--</option>");
			} 
		});
		$("#re_ad_fd_category_id").change(function(e){
			$("#re_ad_type_of_fd_id").change();
		});
							
	});
</script>
<script>
	$(document).ready(function(e){
	            	
	   $("#re_time_period").change(function(e){
		   
		   loading_show();
   		if($("#ad_type_of_fd_id").val()==""){
   			alert("Please Select Fd Type First...!");
   			$("#time_period").val(0);
   			event.stop();
   		}else if($("#ad_fd_category_id").val()==""){
   			alert("Please Select FD Category First...!");
   			$("#time_period").val(0);
   			event.stop();
   		}else if($("#fd_amt").val()==""){
   			alert("Please Enter FD Amt First...!");
   			$("#time_period").val(0);
   			event.stop();
   		}else if($("#opening_date").val()==""){
   			
   			alert("Please Select Opning Date...!");
   			$("#time_period").val(0);
   			event.stop();
   			//var fd_amt=$("#fd_amt").val();
   			
   		            			
   		}else{
   			var ad_fd_roi_id=$(this).val();
   			var fd_amt=$("#fd_amt").val();
   			var dataString = "action=getroiratebytime&ad_fd_roi_id="+ad_fd_roi_id;
				 $.ajax({
		             type: "POST",
		             url: "AdFdRoi",
		             data: dataString,
		             dataType: "json",					            
		             //if received a response from the server
		             success: function( data, textStatus, jqXHR) {
		                                 
		                  if(data.success){				                	
		            
		                	  var intrate= data.FdInfo.roi;
		                	  //alert(intrate);
		                	  $("#interest_rate").val(intrate);
		                	  var time_period=data.FdInfo.time_period;
		                	  //alert(time_period);
		                	  var frequency=data.FdInfo.compound_frequency;
		                	  //alert(frequency);
		                	  
		                	  var re_fd_amt=$("#re_fd_amt").val();
		                	  //alert(re_fd_amt); 
		                		  
		                	  var renewal_maturity=Math.round(parseFloat(re_fd_amt)*Math.pow((1+ parseFloat((parseFloat(intrate)/100)/frequency)),(time_period/12)*frequency));
		                	  var renewal_int=Math.round(renewal_maturity-re_fd_amt);
		                	 // alert(renewal_int);
		                	  //alert(renewal_maturity);
		                	  $("#re_interest_rate").val(intrate);
		                	  $("#re_intrest_amt").val(renewal_int);
		                	  $("#re_maturity_amt").val(renewal_maturity);
		                	  console.log(renewal_int);
		                	  console.log(renewal_maturity);
		                  } 
		                  loading_hide();
		                 
		                  
		             },
		            
		             //If there was no resonse from the server
		             error: function(jqXHR, textStatus, errorThrown){
		                  console.log("Something really bad happened " + textStatus);
		                   $("#ajaxResponse").html(jqXHR.responseText);
		             }
		  
		         });
	            			
		var opening_date=$("#re_opening_date").val();
		var time_period=$("#re_time_period").val();
		            			
		var dataString = "action=ad_date&opening_date="+opening_date+"&time_period="+time_period;
		$.ajax({
		 type: "POST",
		 url: "AdFdTrx",
		 data: dataString,
		 success: function( data, textStatus, jqXHR) {
						                 
			$("#re_maturity_date").val(data);   
		  },
						            
		 //If there was no resonse from the server
			error: function(jqXHR, textStatus, errorThrown){
			 console.log("Something really bad happened " + textStatus);
			 //$("#ajaxResponse").html(jqXHR.responseText);
			 }
						  
		});
	
	   }
	});
	          
	   $("#partpayment").change(function(e){
		   
		   if($(this).val()=="yes"){
			  // alert( );
			   $("#re_fd_amt").val(parseFloat($("#re_fd_amt").val())-parseFloat($("#tfdint").val()));
			  
		   }else if($(this).val()=="no"){
			   $("#re_fd_amt").val(parseFloat($("#re_fd_amt").val())+parseFloat($("#tfdint").val()));
			   
		   }
		   $("#re_time_period").val(0);
		   $("#re_maturity_date").val('');
		   $("#re_interest_rate").val(0);
		   $("#re_intrest_amt").val(0);
		   $("#re_maturity_amt").val(0);
	   });
	   
 });
	
	
	function loading_show(){
	    $('#modelLoad').removeClass('hide').addClass('show');
	 }
	 function loading_hide(){
	   $('#modelLoad').addClass('hide').removeClass('show');
	 } 

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