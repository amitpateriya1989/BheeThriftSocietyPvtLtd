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
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%@ include file= "Layout/header.jsp" %>
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
			<li><a href="#">Member</a><i class="fa fa-angle-right"></i> Search Member</li>
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
	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple" id="member_window">
	  <div class="portlet-title">
	   <div class="caption">Personal Detail</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	   <table class="table table-bordered">
	     <tr>
	   		<td>Name</td>
	   		<td colspan="3">
	   		<select class="input-large" name="ad_member_id" id="ad_member_id">
			  <option value="">--Select Member--</option>
				<%MemberRegistrationDao dao=new MemberRegistrationDao();
				ArrayList<MemberRegistrationBean> alist=dao.getAllMemberlistName();
				if(alist!=null){
				for(MemberRegistrationBean bean:alist){%>
				<option value="<%=bean.getAd_member_id()%>">M<%=bean.getAd_society_no() %> <%=bean.getName() %>
				</option>
				<%}
				}%>
			</select>
	   		</td>
	   	 </tr>
	   <tr>
	   	 <td>PF No.</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_pf_no" id="ad_pf_no" disabled="disabled" /></td>
	   	 <td>Mem. No.</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_society_no" id="ad_society_no" disabled="disabled" /></td>
	   	 <td>Type</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_member_type_id" id="ad_member_type_id" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Status</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_member_status_id" id="ad_member_status_id" disabled="disabled" /></td>
	   	 <td>Father</td>
	   	 <td><input type="text" class="form-control input-medium" name="father" id="father" disabled="disabled" /></td>
	   	 <td>Husband</td>
	   	 <td><input type="text" class="form-control input-medium" name="husband" id="husband" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>DOB</td>
	   	 <td><input type="text" class="form-control input-medium" name="dob" id="dob" disabled="disabled" /></td>
	   	 <td>Gender</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_gender_id" id="ad_gender_id" disabled="disabled" /></td>
	   	 <td>Category</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_category_id" id="ad_category_id" disabled="disabled" /></td>
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
				    <img class="uimg" id="sign_view" name="sign_view" src="Image/sign.png" alt="Employee Sign"  />
				 </div>
				 <div class="user-input">
				  <span class="text-center">Sign :</span>
				</div>
			   </div><!-- End userimg-block -->
				</td>
			</tr>
		</table>
			
	    </div><!-- End portlet-body -->
	</div>
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
	   	 <td colspan="2"><input type="text" class="form-control input-medium" name="ad_state_id" id="ad_state_id" disabled="disabled" /></td>
	   	 <td>District</td>
	   	 <td colspan="2"><input type="text" class="form-control input-medium" name="ad_district_id" id="ad_district_id" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>City</td>
	   	 <td colspan="2"><input type="text" class="form-control input-medium" name="ad_city_id" id="ad_city_id" disabled="disabled" /></td>
	   	 <td>Pincode</td>
	   	 <td colspan="2"><input type="text" class="form-control input-medium" name="pincode" id="pincode" disabled="disabled" /></td>
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
	   	 <td colspan="2"><input type="text" class="form-control input-medium" name="ad_state_id_1" id="ad_state_id_1" disabled="disabled" /></td>
	   	 <td>District</td>
	   	 <td colspan="2"><input type="text" class="form-control input-medium" name="ad_district_id_1" id="ad_district_id_1" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>City</td>
	   	 <td colspan="2"><input type="text" class="form-control input-medium" name="ad_city_id_1" id="ad_city_id_1" disabled="disabled" /></td>
	   	 <td>Pincode</td>
	   	 <td colspan="2"><input type="text" class="form-control input-medium" name="pincode1" id="pincode1" disabled="disabled" /></td>
	   </tr>
	</tbody>
</table>
</div><!-- End portlet-body -->
</div>
<!------------------------------------------------------------------- -->
<!-- BEGIN BORDERED TABLE PORTLET-->
<div class="portlet box purple">
<div class="portlet-title">
<div class="caption">Service Detail</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<table class="table table-bordered">
		<tr>
	   	 <td>Branch</td>
	   	 <td colspan="5"><input type="text" class="form-control input-medium" name="ad_branch_id" id="ad_branch_id" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Region</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_bank_region_id" id="ad_bank_region_id" disabled="disabled" /></td>
	   	 <td>Code</td>
	   	 <td><input type="text" class="form-control input-medium" name="branch_code" id="linbranch_codee2" disabled="disabled" /></td>
	   	 <td>IFSC</td>
	   	 <td><input type="text" class="form-control input-medium" name="ifsc_code" id="ifsc_code" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>State</td>
	   	 <td colspan="2"><input type="text" class="form-control input-medium" name="ad_bank_state_id" id="ad_bank_state_id" disabled="disabled" /></td>
	   	 <td>District</td>
	   	 <td colspan="2"><input type="text" class="form-control input-medium" name="ad_bank_district_id" id="ad_bank_district_id" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>City</td>
	   	 <td colspan="2"><input type="text" class="form-control input-medium" name="ad_bank_city_id" id="ad_bank_city_id" disabled="disabled" /></td>
	   	 <td>Pincode</td>
	   	 <td colspan="2"><input type="text" class="form-control input-medium" name="bank_pincode" id="bank_pincode" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Email</td>
	   	 <td><input type="text" class="form-control input-medium" name="email_id" id="email_id" disabled="disabled" /></td>
	   	 <td>Phone</td>
	   	 <td><input type="text" class="form-control input-medium" name="phone_no" id="phone_no" disabled="disabled" /></td>
	   	 <td>Person</td>
	   	 <td><input type="text" class="form-control input-medium" name="contact_person" id="contact_person" disabled="disabled" /></td>
	   </tr>
	    <tr>
	   	 <td>Contact</td>
	   	 <td><input type="text" class="form-control input-medium" name="contact_no" id="contact_no" disabled="disabled" /></td>
	   	 <td>Address</td>
	   	 <td colspan="4"><input type="text" class="form-control input-extra large" name="address"  id="address" disabled="disabled" /></td>
	   </tr>
</table>
<table class="table table-bordered">
		<tr>
	   	 <td>Pan No</td>
	   	 <td><input type="text" class="form-control input-medium" name="pan_no" id="pan_no" disabled="disabled" /></td>
	   	 <td>Aadhar</td>
	   	 <td><input type="text" class="form-control input-medium" name="aadhar_no" id="aadhar_no" disabled="disabled" /></td>
	   	 <td>Department</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_department_id" id="ad_department_id" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Designation</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_designation_id" id="ad_designation_id" disabled="disabled" /></td>
	   	 <td>Level</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_designation_level_id" id="ad_designation_level_id" disabled="disabled" /></td>
	   	 <td>Type</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_designation_type_id" id="ad_designation_type_id" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Appointment</td>
	   	 <td><input type="text" class="form-control input-medium" name="doa" id="doa" disabled="disabled" /></td>
	   	 <td>Joining</td>
	   	 <td><input type="text" class="form-control input-medium" name="doj" id="doj" disabled="disabled" /></td>
	   	 <td>Retirement</td>
	   	 <td><input type="text" class="form-control input-medium" name="dor" id="dor" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Service Duration</td>
	   	 <td><input type="text" class="form-control input-medium" name="service_duration" id="service_duration" disabled="disabled" /></td>
	   	 <td>A/C No</td>
	   	 <td colspan="3"><input type="text" class="form-control input-medium" name="saving_ac_no" id="saving_ac_no" disabled="disabled" /></td>
	   </tr>
</table>
</div><!-- End portlet-body -->
</div>
<!------------------------------------------------------------------- -->
<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Nominee Detail</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	   <table class="table table-bordered">
	   <thead>
	   <tr><th colspan="6">First Nominee</th></tr>
	   </thead>
	   <tbody>
	     <tr>
	   		<td>Name</td>
	   		<td colspan="3"><input type="text" class="form-control" name="nominee_name_1" id="nominee_name_1" disabled="disabled" /></td>
	   	 </tr>
	   <tr>
	   	 <td>Relation</td>
	   	 <td><input type="text" class="form-control input-medium" name="nominee_ad_relation_id_1" id="nominee_ad_relation_id_1" disabled="disabled" /></td>
	   	 <td>Gender</td>
	   	 <td><input type="text" class="form-control input-medium" name="nominee_ad_gender_id_1" id="nominee_ad_gender_id_1" disabled="disabled" /></td>
	   	 <td>Dob</td>
	   	 <td><input type="text" class="form-control input-medium" name="nominee_dob_1" id="nominee_dob_1" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Age</td>
	   	 <td colspan="5"><input type="text" class="form-control input-medium" name="nominee_age_1" id="nominee_age_1" disabled="disabled" /></td>
	   </tr>
	   </table>
	   <table class="table table-bordered">
			<tr>
			<td width="25%">
			<div class="userimg-block">
				<div class="user-pic">
				    <img class="uimg" id="nominee_photo_view_1" name="nominee_photo_view_1" src="Image/emp.png" alt="Employee Photo" />
				</div>
				<div class="user-input">
				  <span class="text-center">Photo :</span>
				</div>
			   </div><!-- End userimg-block -->
             </td>
			  <td width="25%">
			  <div class="userimg-block">
			  <div class="user-pic">
				    <img class="uimg" id="nominee_id_proof_view_1" name="nominee_id_proof_view_1" src="Image/id.png" alt="Employee Id Card"  />
				</div>
				<div class="user-input">
				  <span class="text-center">ID Proof :</span>
				</div>
			   </div><!-- End userimg-block -->
            </td>
				<td colspan="2" width="25%">
				<div class="userimg-block">
			     <div class="user-pic">
				    <img class="uimg" id="nominee_sign_view_1" name="nominee_sign_view_1" src="Image/sign.png" alt="Employee Sign"  />
				 </div>
				 <div class="user-input">
				  <span class="text-center">Sign :</span>
				</div>
			   </div><!-- End userimg-block -->
				</td>
			</tr>
			</tbody>
		</table>
		
		<table class="table table-bordered">
	   <thead>
	   <tr><th colspan="6">Second Nominee</th></tr>
	   </thead>
	   <tbody>
	     <tr>
	   		<td>Name</td>
	   		<td colspan="3"><input type="text" class="form-control" name="nominee_name_2" id="nominee_name_2" disabled="disabled" /></td>
	   	 </tr>
	   <tr>
	   	 <td>Relation</td>
	   	 <td><input type="text" class="form-control input-medium" name="nominee_ad_relation_id_2" id="nominee_ad_relation_id_2" disabled="disabled" /></td>
	   	 <td>Gender</td>
	   	 <td><input type="text" class="form-control input-medium" name="nominee_ad_gender_id_2" id="nominee_ad_gender_id_2" disabled="disabled" /></td>
	   	 <td>Dob</td>
	   	 <td><input type="text" class="form-control input-medium" name="nominee_dob_2" id="nominee_dob_2" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Age</td>
	   	 <td colspan="5"><input type="text" class="form-control input-medium" name="nominee_age_2" id="nominee_age_2" disabled="disabled" /></td>
	   </tr>
	   </table>
	   <table class="table table-bordered">
			<tr>
			<td width="25%">
			<div class="userimg-block">
				<div class="user-pic">
				    <img class="uimg" id="nominee_photo_view_2" name="nominee_photo_view_2" src="Image/emp.png" alt="Employee Photo" />
				</div>
				<div class="user-input">
				  <span class="text-center">Photo :</span>
				</div>
			   </div><!-- End userimg-block -->
             </td>
			  <td width="25%">
			  <div class="userimg-block">
			  <div class="user-pic">
				    <img class="uimg" id="nominee_id_proof_view_2" name="nominee_id_proof_view_2" src="Image/id.png" alt="Employee Id Card"  />
				</div>
				<div class="user-input">
				  <span class="text-center">ID Proof :</span>
				</div>
			   </div><!-- End userimg-block -->
            </td>
				<td colspan="2" width="25%">
				<div class="userimg-block">
			     <div class="user-pic">
				    <img class="uimg" id="nominee_sign_view_2" name="nominee_sign_view_2" src="Image/sign.png" alt="Employee Sign"  />
				 </div>
				 <div class="user-input">
				  <span class="text-center">Sign :</span>
				</div>
			   </div><!-- End userimg-block -->
				</td>
			</tr>
			</tbody>
		</table>
			
	    </div><!-- End portlet-body -->
	</div>
<!-- --------------------------------------------------------------- -->	
<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Witness Detail</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
		<table class="table table-bordered">
		   <tr>
			   	 <td>Witness Name</td>
			   	 <td><input type="text" class="form-control input-medium" name="witness_name" id="witness_name" disabled="disabled" /></td>
			   	 <td>Witness.No</td>
			   	 <td><input type="text" class="form-control input-medium" name="wno" id="wno" disabled="disabled" /></td>
			</tr>
			
			<tr>
			   	 <td>Mobile</td>
			   	 <td><input type="text" class="form-control input-medium" name="wmobile" id="wmobile" disabled="disabled" /></td>
			   	 <td>Address</td>
			   	 <td><input type="text" class="form-control input-medium" name="waddress" id="waddress" disabled="disabled" /></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="3">
					<input class="btn btn blue" type="submit" id="memberEdit" name="edit" value="Edit"/>
					<!-- <input class="btn btn green" type="button" name="print" value="Print" id="print"/> -->
				</td>
			</tr>
		</table>
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
<% String imagePath = request.getContextPath(); %>
<!-- BEGIN FOOTER -->
<%@ include file= "Layout/footer.jsp" %>
<script src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript">
$(document).ready(function(e) {
	
	$("#ad_member_id").select2();
	
	$("#ad_member_id").change(function(e) {
		var id = $(this).val();
		
		
		var imagePath = "<%=imagePath%>"+"/member_images/";
		var imagePhotoPath = "<%=imagePath%>"+"/Image/emp.png";
		var imageIdPath = "<%=imagePath%>"+"/Image/id.png";
		var imageSignPath = "<%=imagePath%>"+"/Image/sign.png";
		console.log(imageSignPath);
		
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
                	  // alert(data.MemberInfo.address[0].line1);
                	  $("#ad_pf_no").val(data.MemberInfo.ad_pf_no);
                	  $("#ad_society_no").val(data.MemberInfo.ad_society_no);
                	  $("#father").val(data.MemberInfo.father);
                	  $("#husband").val(data.MemberInfo.husband);
                	  $("#dob").val(data.MemberInfo.dob);
                	  $("#ad_gender_id").val(data.MemberInfo.gender.gender);
                	  $("#ad_category_id").val(data.MemberInfo.category.category);
               	  	  $("#ad_member_type_id").val(data.MemberInfo.member_type.member_type);
                	  $("#ad_member_status_id").val(data.MemberInfo.member_status.member_status);
                	  
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
                	  
              	 	  $("#pan_no").val(data.MemberInfo.pan_no);
                	  $("#aadhar_no").val(data.MemberInfo.aadhar_no);
                	  $("#ad_branch_id").val(data.MemberInfo.branch.branch);
                	  $("#ad_bank_region_id").val(data.MemberInfo.branch.bank_region.region);
              	      $("#branch_code").val(data.MemberInfo.branch.branch_code);
                	  $("#ifsc_code").val(data.MemberInfo.branch.ifsc_code);
                      $("#ad_bank_state_id").val(data.MemberInfo.branch.state.state);
                	  $("#ad_bank_district_id").val(data.MemberInfo.branch.district.district);
               	      $("#ad_bank_city_id").val(data.MemberInfo.branch.city.city);
                	  $("#bank_pincode").val(data.MemberInfo.branch.pincode);
                	  $("#email_id").val(data.MemberInfo.branch.email_id);
                	  $("#phone_no").val(data.MemberInfo.branch.phone_no);
                	  $("#contact_person").val(data.MemberInfo.branch.contact_person);
                	  $("#contact_no").val(data.MemberInfo.branch.contact_no);
                	  $("#address").val(data.MemberInfo.branch.address);
                	  $("#ad_department_id").val(data.MemberInfo.department.name);
                	  $("#ad_designation_id").val(data.MemberInfo.designation.designation);
                	  $("#ad_designation_level_id").val(data.MemberInfo.designation_level.designation_level);
                	  $("#ad_designation_type_id").val(data.MemberInfo.designation_type.designation_type);
                	  $("#doa").val(data.MemberInfo.doa);
                	  $("#doj").val(data.MemberInfo.doj);
                	  $("#dor").val(data.MemberInfo.dor);
                	  $("#service_duration").val(data.MemberInfo.service_duration);
                	  $("#saving_ac_no").val(data.MemberInfo.saving_ac_no);
                	  $("#witness_ad_member_id").val(data.MemberInfo.ad_witness_id);
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
                	 
                	  $("#nominee_name_1").val(data.MemberInfo.nominee[0].name);
                	  $("#nominee_ad_relation_id_1").val(data.MemberInfo.nominee[0].relation.relation);
                	  $("#nominee_ad_gender_id_1").val(data.MemberInfo.nominee[0].gender.gender);
                	  $("#nominee_dob_1").val(data.MemberInfo.nominee[0].dob);
                	  $("#nominee_age_1").val(data.MemberInfo.nominee[0].age);
                	  
                	  if(data.MemberInfo.nominee[0].photo!="" && data.MemberInfo.nominee[0].photo!=="undefined"){
                		  $("#nominee_photo_view_1").attr("src",imagePath+data.MemberInfo.nominee[0].photo);
                	  }else{
                	  
                	  $("#nominee_photo_view_1").attr(data.MemberInfo.nominee[0].photo);
                	  }
                	  
                	  if(data.MemberInfo.nominee[0].id_proof!="" && data.MemberInfo.nominee[0].id_proof!=="undefined"){
                		  $("#nominee_id_proof_view_1").attr("src",imagePath+data.MemberInfo.nominee[0].id_proof);
                	  }else{
                	  
                	  $("#nominee_id_proof_view_1").attr(data.MemberInfo.nominee[0].id_proof);
                	  }
                	  
                	  if(data.MemberInfo.nominee[0].sign!="" && data.MemberInfo.nominee[0].sign!=="undefined"){
                		  $("#nominee_sign_view_1").attr("src",imagePath+data.MemberInfo.nominee[0].sign);
                	  }else{
                	  $("#nominee_sign_view_1").attr(data.MemberInfo.nominee[0].sign);
                	  }
                	  $("#nominee_name_2").val(data.MemberInfo.nominee[1].name);
                	  $("#nominee_ad_relation_id_2").val(data.MemberInfo.nominee[1].relation.relation);
                	  $("#nominee_ad_gender_id_2").val(data.MemberInfo.nominee[1].gender.gender);
                	  $("#nominee_dob_2").val(data.MemberInfo.nominee[1].dob);
                	  $("#nominee_age_2").val(data.MemberInfo.nominee[1].age);
                	  
                	  if(data.MemberInfo.nominee[1].photo!="" && data.MemberInfo.nominee[1].photo!="undefined"){
                		  $("#nominee_photo_view_2").attr("src",imagePath+data.MemberInfo.nominee[1].photo);
                		  //alert( $("#nominee_photo_view_2").val());
                	  }{
                	  
                	  $("#nominee_photo_view_2").attr(data.MemberInfo.nominee[1].photo);
                	  //alert( $("#nominee_photo_view_2").val());
                	  }
                	  
                	  if(data.MemberInfo.nominee[1].id_proof!="" && data.MemberInfo.nominee[1].id_proof!="undefined"){
                		  $("#nominee_id_proof_view_2").attr("src",imagePath+data.MemberInfo.nominee[1].id_proof);
                	  }else{
                	  
                	  $("#nominee_id_proof_view_2").attr(data.MemberInfo.nominee[1].id_proof);
                	  }
                	  if(data.MemberInfo.nominee[1].sign!="" && data.MemberInfo.nominee[1].sign!="undefined"){
                		  $("#nominee_sign_view_2").attr("src",imagePath+data.MemberInfo.nominee[1].sign);
                	  }{
                	  $("#nominee_sign_view_2").attr(data.MemberInfo.nominee[1].sign); 
                	  }
                	  $("#witness_name").val(data.MemberInfo.witness.ad_witness_name);
                	  $("#wno").val(data.MemberInfo.witness.ad_witness_mem_no);
                	  $("#wmobile").val(data.MemberInfo.witness.ad_witness_mobile);
                	  $("#waddress").val(data.MemberInfo.witness.ad_witness_address);
                	  
                	  
                	  
                      
                  } 
                  //display error message
                  else {
                      $("#ajaxResponse").html("<div><b>Country code in Invalid!</b></div>");
                  }
             },
            
             //If there was no resonse from the server
             error: function(jqXHR, textStatus, errorThrown){
                  console.log("Something really bad happened " + textStatus);
                   $("#ajaxResponse").html(jqXHR.responseText);
             }
  
         });        
 });
	
	$('#memberEdit').click(function(){
		var ad_member_id = $("#ad_member_id option:selected").val();
		window.location.replace("AdMemberRegistration?action=edit&ad_member_id="+ad_member_id);
	});
	
	
	
});//end dom
	

</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>