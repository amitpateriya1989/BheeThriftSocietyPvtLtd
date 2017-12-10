<%@page import="Model.Bean.MemberAddressBean"%>
<%@page import="javax.mail.Session"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%

MemberLoginBean user1=null;
user1=(MemberLoginBean)session.getAttribute("MemberLoginBean");
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%@ include file= "../Layout/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/data-tables/DT_bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/plugins/bootstrap-datepicker/css/datepicker.css"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-full-width">
<%@ include file= "memberheadtitle.jsp" %>
<!-- BEGIN HEADER -->
<div class="header navbar mega-menu">
<!-- BEGIN TOP NAVIGATION BAR -->
<%@include file= "member_hdr_menu.jsp"%>
<!-- END TOP NAVIGATION BAR -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN EMPTY PAGE SIDEBAR -->
	<%@ include file= "memberemptynavbar.jsp" %>
	<!-- END EMPTY PAGE SIDEBAR -->
<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
<div class="page-content">
<!-- BEGIN PAGE CONTENT-->
<div class="row">
<div class="col-md-12">
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i>
				<a href="#">Home</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Member Profile</a><i class="fa fa-angle-right"></i>View</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
<div class="col-md-12">
<!-- Containt Start -->
<!------------------------------------------------------------------- -->
<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Personal Detail</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	   <table class="table table-bordered">
	     <tr>
	   		<td>Name</td>
	   		<td colspan="3"><%=user1.getMember().getName() %></td>
	   	 </tr>
	   <tr>
	   	 <td>PF No.</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_pf_no" id="ad_pf_no" value="<%=user1.getMember().getAd_pf_no() %>" disabled="disabled" /></td>
	   	 <td>Mem. No.</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_society_no" value="<%=user1.getMember().getAd_society_no() %>" id="ad_society_no" disabled="disabled" /></td>
	   	 <td>Type</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_member_type_id" id="ad_member_type_id" value="<%=user1.getMember().getMember_type().getMember_type()%>" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Status</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_member_status_id" id="ad_member_status_id" value="<%=user1.getMember().getMember_status().getMember_status() %>" disabled="disabled" /></td>
	   	 <td>Father</td>
	   	 <td><input type="text" class="form-control input-medium" name="father" id="father" value="<%=user1.getMember().getFather() %>" disabled="disabled" /></td>
	   	 <td>Husband</td>
	   	 <td><input type="text" class="form-control input-medium" name="husband" id="husband" value="<%=user1.getMember().getHusband() %>" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>DOB</td>
	   	 <td><input type="text" class="form-control input-medium" name="dob" id="dob" value="<%=user1.getMember().getDob() %>" disabled="disabled" /></td>
	   	 <td>Gender</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_gender_id" id="ad_gender_id" value="<%=user1.getMember().getGender().getGender() %>" disabled="disabled" /></td>
	   	 <td>Category</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_category_id" id="ad_category_id" value="<%=user1.getMember().getCategory().getCategory() %>" disabled="disabled" /></td>
	   </tr>
	   </table>
	   <table class="table table-bordered">
			<tr>
			<td width="25%">
			<div class="userimg-block">
				<div class="user-pic">
				    <img class="uimg" id="photo_view" name="photo_view" src="${pageContext.request.contextPath}/Image/emp.png" alt="Employee Photo" />
				</div>
				<div class="user-input">
				  <span class="text-center">Photo :</span>
				</div>
			   </div><!-- End userimg-block -->
             </td>
			  <td width="25%">
			  <div class="userimg-block">
			  <div class="user-pic">
				    <img class="uimg" id="id_proof_view" name="id_proof_view" src="${pageContext.request.contextPath}/Image/id.png" alt="Employee Id Card"  />
				</div>
				<div class="user-input">
				  <span class="text-center">ID Proof :</span>
				</div>
			   </div><!-- End userimg-block -->
            </td>
				<td colspan="2" width="25%">
				<div class="userimg-block">
			     <div class="user-pic">
				    <img class="uimg" id="sign_view" name="sign_view" src="${pageContext.request.contextPath}/Image/sign.png" alt="Employee Sign"  />
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
 <%
  //  ArrayList<MemberAddressBean>  mabean=  user1.getMember().getAddress();
 %>
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
	   	 <td><input type="text" class="form-control input-medium" value="<%=user1.getMember().getAddress() %>" name="mobile" id="mobile" disabled="disabled" /></td>
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
	   	 <td colspan="5"><input type="text" class="form-control input-medium" name="ad_branch_id" id="ad_branch_id" value="<%=user1.getMember().getBranch().getBranch() %>" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Region</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_bank_region_id" id="ad_bank_region_id" value="<%//user1.getMember().getBranch().getBank_region().getRegion() %>" disabled="disabled" /></td>
	   	 <td>Code</td>
	   	 <td><input type="text" class="form-control input-medium" name="branch_code" id="linbranch_codee2" value="<%=user1.getMember().getBranch().getBranch_code() %>" disabled="disabled" /></td>
	   	 <td>IFSC</td>
	   	 <td><input type="text" class="form-control input-medium" name="ifsc_code" id="ifsc_code" value="<%=user1.getMember().getBranch().getIfsc_code() %>" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>State</td>
	   	 <td colspan="2"><input type="text" class="form-control" name="ad_bank_state_id" id="ad_bank_state_id" value="<%-- <%=user1.getMember().getBranch().getState().getState()%> --%>" disabled="disabled" /></td>
	   	 <td>District</td>
	   	 <td colspan="2"><input type="text" class="form-control" name="ad_bank_district_id" id="ad_bank_district_id" value="<%-- <%=user1.getMember().getBranch().getDistrict().getDistrict() %> --%>" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>City</td>
	   	 <td colspan="2"><input type="text" class="form-control" name="ad_bank_city_id" id="ad_bank_city_id" value="<%-- <%=user1.getMember().getBranch().getCity().getCity() %> --%>" disabled="disabled" /></td>
	   	 <td>Pincode</td>
	   	 <td colspan="2"><input type="text" class="form-control" name="bank_pincode" id="bank_pincode" value="<%=user1.getMember().getBranch().getPincode() %>" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Email</td>
	   	 <td><input type="text" class="form-control input-medium" name="email_id" id="email_id" value="<%=user1.getMember().getBranch().getEmail_id() %>" disabled="disabled" /></td>
	   	 <td>Phone</td>
	   	 <td><input type="text" class="form-control input-medium" name="phone_no" id="phone_no" value="<%=user1.getMember().getBranch().getPhone_no() %>" disabled="disabled" /></td>
	   	 <td>Person</td>
	   	 <td><input type="text" class="form-control input-medium" name="contact_person" id="contact_person" value="<%=user1.getMember().getBranch().getContact_person() %>" disabled="disabled" /></td>
	   </tr>
	    <tr>
	   	 <td>Contact</td>
	   	 <td><input type="text" class="form-control input-medium" name="contact_no" id="contact_no" value="<%=user1.getMember().getBranch().getContact_no() %>" disabled="disabled" /></td>
	   	 <td>Address</td>
	   	 <td colspan="4"><input type="text" class="form-control input-medium" name="address" id="address"  value="<%=user1.getMember().getBranch().getAddress() %>" disabled="disabled" /></td>
	   </tr>
</table>
<table class="table table-bordered">
		<tr>
	   	 <td>Pan No</td>
	   	 <td><input type="text" class="form-control input-medium" name="pan_no" id="pan_no" value="<%=user1.getMember().getPan_no() %>" disabled="disabled" /></td>
	   	 <td>Aadhar</td>
	   	 <td><input type="text" class="form-control input-medium" name="aadhar_no" id="aadhar_no" value="<%=user1.getMember().getAadhar_no() %>" disabled="disabled" /></td>
	   	 <td>Department</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_department_id" id="ad_department_id" value="<%=user1.getMember().getDepartment().getName() %>" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Designation</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_designation_id" id="ad_designation_id" value="<%=user1.getMember().getDesignation().getDesignation() %>" disabled="disabled" /></td>
	   	 <td>Level</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_designation_level_id" id="ad_designation_level_id" value="<%=user1.getMember().getDesignation_level().getDesignation_level() %>" disabled="disabled" /></td>
	   	 <td>Type</td>
	   	 <td><input type="text" class="form-control input-medium" name="ad_designation_type_id" id="ad_designation_type_id" value="<%=user1.getMember().getDesignation_type().getDesignation_type() %>" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Appointment</td>
	   	 <td><input type="text" class="form-control input-medium" name="doa" id="doa" value="<%=user1.getMember().getDoa() %>" disabled="disabled" /></td>
	   	 <td>Joining</td>
	   	 <td><input type="text" class="form-control input-medium" name="doj" id="doj" value="<%=user1.getMember().getDoj() %>" disabled="disabled" /></td>
	   	 <td>Retirement</td>
	   	 <td><input type="text" class="form-control input-medium" name="dor" id="dor" value="<%=user1.getMember().getDor() %>" disabled="disabled" /></td>
	   </tr>
	   <tr>
	   	 <td>Service Duration</td>
	   	 <td><input type="text" class="form-control input-medium" name="service_duration" id="service_duration" value="<%=user1.getMember().getService_duration() %>" disabled="disabled" /></td>
	   	 <td>A/C No</td>
	   	 <td colspan="3"><input type="text" class="form-control input-medium" name="saving_ac_no" id="saving_ac_no" value="<%=user1.getMember().getSaving_ac_no() %>" disabled="disabled" /></td>
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
	   	 <td><input type="text" class="form-control" name="nominee_name_1" id="nominee_name_1" disabled="disabled" /></td>
	   	 <td>Relation</td>
	   	 <td><input type="text" class="form-control input-medium" name="nominee_ad_relation_id_1" id="nominee_ad_relation_id_1" disabled="disabled" /></td>
	   	 <td>Gender</td>
	   	 <td><input type="text" class="form-control input-medium" name="nominee_ad_gender_id_1" id="nominee_ad_gender_id_1" disabled="disabled" /></td>
	   	 </tr>
	   <tr>
	   	 <td>Dob</td>
	   	 <td><input type="text" class="form-control input-medium" name="nominee_dob_1" id="nominee_dob_1" disabled="disabled" /></td>
	   	 <td>Age</td>
	   	 <td colspan="3"><input type="text" class="form-control input-medium" name="nominee_age_1" id="nominee_age_1" disabled="disabled" /></td>
	   </tr>
	   </table>
	   <table class="table table-bordered">
			<tr>
			<td width="25%">
			<div class="userimg-block">
				<div class="user-pic">
				    <img class="uimg" id="nominee_photo_view_1" name="nominee_photo_view_1" src="${pageContext.request.contextPath}/Image/emp.png" alt="Employee Photo" />
				</div>
				<div class="user-input">
				  <span class="text-center">Photo :</span>
				</div>
			   </div><!-- End userimg-block -->
             </td>
			  <td width="25%">
			  <div class="userimg-block">
			  <div class="user-pic">
				    <img class="uimg" id="nominee_id_proof_view_1" name="nominee_id_proof_view_1" src="${pageContext.request.contextPath}/Image/id.png" alt="Employee Id Card"  />
				</div>
				<div class="user-input">
				  <span class="text-center">ID Proof :</span>
				</div>
			   </div><!-- End userimg-block -->
            </td>
				<td colspan="2" width="25%">
				<div class="userimg-block">
			     <div class="user-pic">
				    <img class="uimg" id="nominee_sign_view_1" name="nominee_sign_view_1" src="${pageContext.request.contextPath}/Image/sign.png" alt="Employee Sign"  />
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
	   		<td><input type="text" class="form-control" name="nominee_name_2" id="nominee_name_2" disabled="disabled" /></td>
	   	    <td>Relation</td>
	   	 	<td><input type="text" class="form-control input-medium" name="nominee_ad_relation_id_2" id="nominee_ad_relation_id_2" disabled="disabled" /></td>
	   	 	<td>Gender</td>
	   	 	<td><input type="text" class="form-control input-medium" name="nominee_ad_gender_id_2" id="nominee_ad_gender_id_2" disabled="disabled" /></td>
	   	 </tr>
	   <tr>
	   	 <td>Dob</td>
	   	 <td><input type="text" class="form-control input-medium" name="nominee_dob_2" id="nominee_dob_2" disabled="disabled" /></td>
	   	 <td>Age</td>
	   	 <td colspan="3"><input type="text" class="form-control input-medium" name="nominee_age_2" id="nominee_age_2" disabled="disabled" /></td>
	   </tr>
	   </table>
	   <table class="table table-bordered">
			<tr>
			<td width="25%">
			<div class="userimg-block">
				<div class="user-pic">
				    <img class="uimg" id="nominee_photo_view_1" name="nominee_photo_view_2" src="${pageContext.request.contextPath}/Image/emp.png" alt="Employee Photo" />
				</div>
				<div class="user-input">
				  <span class="text-center">Photo :</span>
				</div>
			   </div><!-- End userimg-block -->
             </td>
			  <td width="25%">
			  <div class="userimg-block">
			  <div class="user-pic">
				    <img class="uimg" id="nominee_id_proof_view_1" name="nominee_id_proof_view_2" src="${pageContext.request.contextPath}/Image/id.png" alt="Employee Id Card"  />
				</div>
				<div class="user-input">
				  <span class="text-center">ID Proof :</span>
				</div>
			   </div><!-- End userimg-block -->
            </td>
				<td colspan="2" width="25%">
				<div class="userimg-block">
			     <div class="user-pic">
				    <img class="uimg" id="nominee_sign_view_1" name="nominee_sign_view_2" src="${pageContext.request.contextPath}/Image/sign.png" alt="Employee Sign"  />
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
			   	 <td>Member</td>
			   	 <td><input type="text" class="form-control input-medium" name="nominee_ad_relation_id_2" id="nominee_ad_relation_id_2" disabled="disabled" /></td>
			   	 <td>Mem.No</td>
			   	 <td><input type="text" class="form-control input-medium" name="nominee_ad_gender_id_2" id="nominee_ad_gender_id_2" disabled="disabled" /></td>
			</tr>
			<tr>
			   	 <td>Name</td>
			   	 <td colspan="3"><input type="text" class="form-control" name="nominee_ad_relation_id_2" id="nominee_ad_relation_id_2" disabled="disabled" /></td>
			</tr>
			<tr>
			   	 <td>Mobile</td>
			   	 <td><input type="text" class="form-control input-medium" name="nominee_ad_relation_id_2" id="nominee_ad_relation_id_2" disabled="disabled" /></td>
			   	 <td>Address</td>
			   	 <td><input type="text" class="form-control input-medium" name="nominee_ad_gender_id_2" id="nominee_ad_gender_id_2" disabled="disabled" /></td>
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
<!-- BEGIN FOOTER -->
<%@ include file= "../Layout/footer.jsp" %>
</body>
</html>