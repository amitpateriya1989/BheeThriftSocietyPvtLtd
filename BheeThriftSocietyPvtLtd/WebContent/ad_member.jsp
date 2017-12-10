<%@page import="Model.Bean.ShareBean"%>
<%@page import="Model.Dao.ShareDao"%>
<%@page import="Model.Bean.MemberStatusBean"%>
<%@page import="Model.Dao.MemberStatusDao"%>
<%@page import="Model.Bean.MemberTypeBean"%>
<%@page import="Model.Dao.MemberTypeDao"%>
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
<%@page import="Model.Bean.SalutationBean"%>
<%@page import="Model.Dao.SalutationDao"%>
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
			<li><a href="#">Member</a><i class="fa fa-angle-right"></i>Add</li>
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

<!-- BEGIN PAGE CONTENT-->
<div class="row">
<div class="col-md-12">
<!-- Containt Start -->
<!------------------------------------------------------------------- -->
<!-- BEGIN BORDERED TABLE PORTLET-->
<div class="portlet box purple" id="form_wizard_1">
<div class="portlet-title">
<div class="caption"><i class="fa fa-reorder"></i> Add Member Information -
<span class="step-title">Step 1 of 7</span>
</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body form">
<form id="frmMemberReg" action="AdMemberRegistration?action=insert" enctype="multipart/form-data" method="post" autocomplete="off">
<div class="form-wizard">
<div class="form-body">
<ul class="nav nav-pills steps">
	<li class="active"><a href="#tab1" data-toggle="tab" class="step">Personal</a></li>
	<li class=""><a href="#tab2" data-toggle="tab" class="step">Contact</a></li>
	<li class=""><a href="#tab3" data-toggle="tab" class="step">Service</a></li>
	<li class=""><a href="#tab4" data-toggle="tab" class="step">Nominee</a></li>
	<li class=""><a href="#tab5" data-toggle="tab" class="step">Guarantor</a></li>
	<li class=""><a href="#tab6" data-toggle="tab" class="step">Fees</a></li>
	<li class=""><a href="#tab7" data-toggle="tab" class="step">Payment</a></li>
</ul>
<div id="bar" class="progress progress-striped" role="progressbar">
	<div class="progress-bar progress-bar-success" style="width: 16.6%;"></div>
</div>

<div class="tab-content">
		<div  id="custommessage" class="alert alert-danger display-none">
		   <button class="close" data-dismiss="alert"></button>
			 <div id="mymsg"></div>
	  </div>
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
				<td width="12%">Staff No : <span class="red">*</span> </td>
				<%
					
					
					int ad_society_no=new MemberRegistrationDao().getMemberSocietyNo();
					ad_society_no++;
				%>
				<td width="38%"><input class="form-control input-medium" type="text" id="ad_pf_id"  name="ad_pf_id" placeholder="Staff No." tabindex="1" value="" /><label class="error"></label></td>
				<td width="12%">Membership No.</td>
				<td width="38%"><input class="form-control input-medium " style="font-weight: bold;font-size: 14px;" type="text" id="ad_society_id" tabindex="2" name="ad_society_id" value="<%=ad_society_no%>" readonly="readonly" /><label class="error"></label>
				<input type="hidden" id="h_ad_society_id" name="h_ad_society_id" value="<%=ad_society_no%>" />
				</td>
			</tr>
			<tr>
				<td>Type : <span class="red">*</span>
				</td>
				<td><select class="form-control input-medium" name="ad_member_type_id" id="ad_member_type_id" tabindex="3">
				
					<%MemberTypeDao type=new MemberTypeDao();
					 ArrayList<MemberTypeBean> membertypelist=type.getAllMemberType();
					  if(membertypelist.isEmpty()!=true){
						for(MemberTypeBean bean:membertypelist){%>
						<option value="<%=bean.getAd_member_type_id()%>"><%=bean.getMember_type() %></option>
					<%		  
					}
					}
					%>
					</select>
					<label class="error"></label>					
				</td>
				<td>Status : <span class="red">*</span></td>
				<td>
					<select class="form-control input-medium" name="ad_member_status_id" id="ad_member_status_id" tabindex="4">
					
						<%MemberStatusDao status=new MemberStatusDao();
						ArrayList<MemberStatusBean> statuslist=status.getAllMemberStatus();
						if(statuslist.isEmpty()!=true){
						for(MemberStatusBean bean:statuslist){%>
						<option value="<%=bean.getAd_member_status_id()%>" ><%=bean.getMember_status() %></option>
						 <%	
						}
						}
						 %>
					</select>
					<label class="error"></label>
				</td>
			</tr>
			<tr>
				<td>Gender : <span class="red">*</span></td>
				<td>
				<select class="form-control input-medium"  name="ad_gender_id" id="ad_gender_id" tabindex="5" onChange="getSalutationData(this.value,'ad_salutation_id');" >
				<option value="">--Select--</option>
					<%GenderDao dao1=new GenderDao();
						ArrayList<GenderBean> alist1=dao1.getAllGender();
						if(alist1.isEmpty()!=true){
						for(GenderBean bean:alist1){%>
						<option value="<%=bean.getAd_gender_id()%>"><%=bean.getGender() %></option>
					 <%} 
					 }%>	
				</select>
				<label class="error"></label>
				</td>
				<td>Category : <span class="red">*</span></td>
				<td>
				<select class="form-control input-medium" name="ad_category_id" id="ad_category_id" tabindex="6">
				
					<%CategoryDao dao2=new CategoryDao();
					ArrayList<CategoryBean> alist2=dao2.getAllCategory();
					if(alist2.isEmpty()!=true){
					for(CategoryBean bean:alist2){%>
					<option value="<%=bean.getAd_category_id()%>"><%=bean.getCategory() %></option>
					 <%}
					}%>
				</select>
				<label class="error"></label>
				</td>
			</tr>
			<tr>
				<td>Salutation : <span class="red">*</span></td>
				<td>
				<select class="form-control input-medium" name="ad_salutation_id" id="ad_salutation_id" tabindex="7">
				
					<%SalutationDao dao=new SalutationDao();
					ArrayList<SalutationBean> alist=dao.getAllSalutation();
					if(alist.isEmpty()!=true){
					for(SalutationBean bean:alist){%>
					<option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
					<%}
					}%>
				</select>
				<label class="error"></label>
				</td>
				<td>Name : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text"  name="name" id="name" style="text-transform: uppercase;" tabindex="8"/><label class="error"></label></td>
			</tr>
			<tr>
				<td>Father : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text"  name="father" id="father" style="text-transform: uppercase;" tabindex="9" /><label class="error"></label></td>
				<td>Husband : <span  class="red husband">*</span></td>
				<td><input class="form-control input-medium" type="text"  name="husband" id="husband" style="text-transform: uppercase;" tabindex="10" /><label class="error"></label></td>
			</tr>
			<tr>
			<td>Marital Status : <span class="red">*</span></td>
				<td>
				<select class="form-control input-medium" name="marital_status" id="marital_status" tabindex="11">
				<option value="Y">Married</option>
				<option value="N">Unmarried</option>
				</select>
				<label class="error"></label>
				</td>
				<td>DOB : <span class="red">*</span></td>
				<td colspan="3"><input class="form-control input-medium date-picker" type="text" name="dob" id="dob" tabindex="12" data-date-format="dd/mm/yyyy" placeholder="dd/mm/yyyy" /><label class="error"></label></td>
			</tr>
		</table>
		
	</div><!-- End Tab Personal -->
	<!-- ------------------------------------------------------End Personal---------------------------------------------------- -->
	
	<div class="tab-pane fade" id="tab2">
	<div  id="custommessage1" class="alert alert-danger display-none">
		   <button class="close" data-dismiss="alert"></button>
			 <div id="mymsg1"></div>
	  </div>
		<table class="table table-bordered">
			<thead>
			<tr><th colspan="4">Present Address</th></tr>
			</thead>
			<tbody>
				<tr>
					<td>Mobile : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="mobile" id="mobile" tabindex="13"/><label class="error" ></label></td>
					<td>Phone :</td>
					<td><input class="form-control input-medium" type="text" name="phone" id="phone" tabindex="14"/><label class="error"></label></td>
				</tr>
				<tr>
					<td>Email : </td>
					<td><input class="form-control input-medium" type="text"  name="email" id="email" tabindex="15"/><label class="error"></label></td>
					<td>Street1 : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="line1" id="line1" tabindex="16" style="text-transform: uppercase;"/><label class="error"></label></td>
				</tr>
				<tr>
					<td>Street2 : </td>
					<td><input class="form-control input-medium" type="text" name="line2" id="line2" tabindex="17" style="text-transform: uppercase;"/><label class="error"></label></td>
					<td>Country : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium"  name="ad_country_id" id="ad_country_id" tabindex="18" >
								 <%
								 CountryDao dao3=new CountryDao();
								 ArrayList<CountryBean> alist3=dao3.getAllCountryName();
								 if(alist3.isEmpty()!=true){
								 for(CountryBean bean:alist3){%>
									 <option value="<%=bean.getAd_country_id()%>"><%=bean.getCountry() %></option>
									 
									 <%
								 }
								 }
								 %>
					</select><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td>State : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium"  name="ad_state_id" id="ad_state_id" tabindex="19" >
								 <option value="0">--Select--</option>
								<%StateDao sdao=new StateDao();
								  ArrayList <StateBean> slist=sdao.getAllStateName();
								  if(slist.isEmpty()!=true){
								  for(StateBean bean:slist){%>
								  <option value="<%=bean.getAd_state_id()%>"><%=bean.getState() %></option>
									  
								 <%} }%>
						</select><label class="error"></label>
					</td>
					<td>District : <span class="red">*</span></td>
					<td>
					<select  class="form-control input-medium" name="ad_district_id" id="ad_district_id" tabindex="20" style="width: 170px;" data-placeholder="Choose a District..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="0">--Select--</option>
					</select><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td>City : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium"  name="ad_city_id" id="ad_city_id" tabindex="21" style="width: 170px;" data-placeholder="Choose a City..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="0">--Select--</option>
					</select><label class="error"></label>
					</td>
					<td>Pin Code :</td>
					<td>
					<input class="form-control input-medium" type="text" name="pincode" id="pincode" tabindex="22" /><label class="error"></label>
					</td>
				</tr>
			</tbody>
		</table>
		<table class="table table-bordered">
			<tr><th width="70%"></th><th width="30%">Same as above : <input type="checkbox" tabindex="23"  name="same" id="same" style="width: 15px; height: 15px"></th></tr>
		</table>
		<table class="table table-bordered">
			<thead>
			<tr>
			<th colspan="4">Permanent Address</th>
			</tr>
			</thead>
			<tbody>
				<tr>
					<td>Mobile : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="mobile1" id="mobile1" tabindex="24"  /><label class="error"></label></td>
					<td>Phone :</td>
					<td><input class="form-control input-medium" type="text" name="phone1" id="phone1" tabindex="25"  /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Email :</td>
					<td><input class="form-control input-medium" type="email" name="email1" id="email1" tabindex="26"  /><label class="error"></label></td>
					<td>Street1 : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="line1_1" id="line1_1" tabindex="27"  style="text-transform: uppercase;"/><label class="error"></label></td>
				</tr>
				<tr>
					<td>Street2 : </td>
					<td><input class="form-control input-medium" type="text" name="line2_1" id="line2_1" tabindex="28"  style="text-transform: uppercase;"/><label class="error"></label></td>
					<td>Country : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium"  name="ad_country_id_1" id="ad_country_id_1" tabindex="29" >
								 <%
								 CountryDao countrydao=new CountryDao();
								 ArrayList<CountryBean> countrylist=countrydao.getAllCountryName();
								 if(countrylist.isEmpty()!=true){
								 for(CountryBean bean:countrylist){%>
									 <option value="<%=bean.getAd_country_id()%>"><%=bean.getCountry() %></option>
									 
									 <%
								 }
								 }
								 %>
					</select><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td>State : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium"  name="ad_state_id_1" id="ad_state_id_1" tabindex="30" >
								 <option value="">--Select--</option>
								<%StateDao statedao=new StateDao();
								  ArrayList <StateBean> statelist=statedao.getAllStateName();
								  if(statelist.isEmpty()!=true){
								  for(StateBean bean:statelist){%>
								  <option value="<%=bean.getAd_state_id()%>"><%=bean.getState() %></option>
									  
								 <%} }%>
					</select><label class="error"></label>
					</td>
					<td>District : <span class="red">*</span></td>
					<td>
					<select  class="form-control input-medium" name="ad_district_id_1" id="ad_district_id_1" tabindex="31" >
								 <option value="">--Select--</option>
					</select><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td>City : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium"  name="ad_city_id_1" id="ad_city_id_1" tabindex="32" >
								 <option value="">--Select--</option>
					</select><label class="error"></label>
					</td>
					<td>Pin Code :</td>
					<td>
					<input class="form-control input-medium" type="text" name="pincode1" id="pincode1" tabindex="33"  /><label class="error"></label>
					</td>
				</tr>
			</tbody>
		</table>
	</div><!-- End Tab Contact -->
	<!-- ------------------------------------------------------End Contact------------------------------------------------------------- -->
	<div class="tab-pane fade" id="tab3">
		<table class="table table-bordered">
			<thead>
			<tr><th colspan="4">Bank Detail</th></tr>
			</thead>
			<tbody>
				<tr>
					<td>Bank : <span class="red">*</span></td>
					<td>
					<select  name="bank_id" id="ad_bank_id" class="form-control input-large" tabindex="34" >
								 <option value="">--Select Bank--</option>
								<%BankDao bankdao=new BankDao();
								  ArrayList <BankBean> banklist=bankdao.getAllBank();
								  if(banklist.isEmpty()!=true){
								  for(BankBean bean:banklist){
								  %>
								  <option value="<%=bean.getAd_bank_id()%>"><%=bean.getBank().trim() %></option>
								 <%} }%>
						</select><label class="error"></label>
					</td>
					<td>Branch : <span class="red">*</span></td>
					<td>
					<select  name="branch_id" id="ad_branch_id" class="form-control input-large" tabindex="35" >
								 <option value="">--Select Branch--</option>
								
					</select><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td>Region</td>
					<td><input class="form-control input-medium" type="text"    name="ad_bank_region_id" id="ad_bank_region_id" readonly="readonly" ></td>
					<td>Code</td>
					<td><input class="form-control input-medium" type="text"   name="branch_code" id="branch_code"  readonly="readonly" /></td>
				</tr>
				<tr>
					<td>IFSC</td>
					<td><input class="form-control input-medium" type="text"   name="ifsc_code" id="ifsc_code" readonly="readonly" /></td>
					<td>State</td>
					<td><input class="form-control input-medium" type="text"   name="ad_bank_state_id" id="ad_bank_state_id" readonly="readonly" style="text-transform: uppercase;" /></td>
				</tr>
				<tr>
					<td>District</td>
					<td><input class="form-control input-medium" type="text" name="ad_bank_district_id" id="ad_bank_district_id"  readonly="readonly" /></td>
					<td>City</td>
					<td><input class="form-control input-medium" type="text"  name="ad_bank_city_id" id="ad_bank_city_id" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>Pincode</td>
					<td><input class="form-control input-medium" type="text" name="bank_pincode" id="bank_pincode" readonly="readonly" /></td>
					<td>Email</td>
					<td><input class="form-control input-medium" type="text" name="email_id" id="email_id" readonly="readonly" style="text-transform: uppercase;" /></td>
				</tr>
				<tr>
					<td>Phone</td>
					<td><input class="form-control input-medium" type="text" name="phone_no" id="phone_no" readonly="readonly" /></td>
					<td>Person</td>
					<td><input class="form-control input-medium" type="text" name="contact_person" id="contact_person" readonly="readonly" style="text-transform: uppercase;" /></td>
				</tr>
				<tr>
					<td>Mobile</td>
					<td colspan="3"><input class="form-control input-medium" type="text" name="contact_no" id="contact_no" readonly="readonly" /></td>
				</tr>
				<tr>
				<td>Adress</td>
				<td colspan="3">
				<textarea class="form-control" name="address" id="address" readonly="readonly" style="text-transform: uppercase;"></textarea>
				</td>
				</tr>
			</tbody>
		</table>
		<table class="table table-bordered">
		  <thead>
			<tr><th colspan="4">Other Information</th></tr>
		  </thead>
		  <tbody>
		  <tr>
		  	<td>Pan No</td>
		  	<td><input class="form-control input-medium" type="text" tabindex="35"  name="pan_no" id="pan_no" style="text-transform: uppercase;" /> <label class="error"></label></td>
		  	<td>Aadhar</td>
		  	<td><input class="form-control input-medium" type="text" name="aadhar_no" tabindex="36"  id="aadhar_no" /><label class="error"></label></td>
		  </tr>
		  <tr>
		  	<td>Department : <span class="red">*</span></td>
		  	<td colspan="3">
		  	  <select  name="ad_department_id" id="ad_department_id" tabindex="37"  class="form-control input-medium">
				 <option value="">--Select Department--</option>
				  <%
				  DepartmentDao deptdao=new DepartmentDao();
				  ArrayList<DepartmentBean> deptlist=deptdao.getAllDepartmentName();
				  if(deptlist.isEmpty()!=true){
				  for(DepartmentBean bean:deptlist){%>
				  <option value="<%=bean.getAd_department_id()%>"><%=bean.getName() %></option>
				  <%}
				  }
				  %>
			</select><label class="error"></label>
		  	</td>
		  	
		  </tr>
		  <tr>
		  	<td>Type : <span class="red">*</span></td>
		  	<td>
		  	 <select  name="ad_designation_type_id" id="ad_designation_type_id" tabindex="38"   class="form-control input-medium"  tabindex="2">
			<option value="">--Select Type--</option>
			<%
			DesignationTypeDao typedao=new DesignationTypeDao();
			ArrayList<DesignationTypeBean> typelist=typedao.getAllDesignationType();
			if(typelist.isEmpty()!=true){
			for(DesignationTypeBean bean:typelist){%>
				<option value="<%=bean.getAd_designation_type_id()%>"><%=bean.getDesignation_type() %></option>
			<%}
			}
			%>
			</select>
		  	</td>
		  	<td>Designation : <span class="red">*</span></td>
		  	<td>
		  	   <select class="form-control input-medium"  name="ad_designation_id" tabindex="39"  id="ad_designation_id">
				<option value="">--Select Designation--</option>
			  </select><label class="error"></label>
		  	</td>
		  </tr>
		  <tr>
		  	<td>Appointment : <span class="red">*</span></td>
		  	<td><input class="form-control input-medium date-picker2" type="text" tabindex="40"  name="doa" id="doa" placeholder="dd/mm/yyyy" /><label class="error"></label></td>
		  	<td>Retirement</td>
		  	<td><input class="form-control input-medium date-picker" type="text" tabindex="41"  name="dor" id="dor"  disabled="disabled" />
		  	<input type="hidden" name="h_dor" id="h_dor" />
		  	<label class="error"></label></td>
		  </tr>
		  <tr>
		  	<td>Service Duration</td>
		  	<td><input class="form-control input-medium" type="text" readonly="readonly" tabindex="42"  name="service_duration" id="service_duration"/> Year<label class="error"></label>
		  	<input type="hidden"  name="h_service_duration" id="h_service_duration"/>
		  	</td>
		  	<td> A/C No : <span class="red">*</span></td>
		  	<td colspan="3"><input class="form-control input-medium" type="text" name="saving_ac_no" tabindex="43"  id="saving_ac_no"><label class="error"></label></td>
		  </tr>		  	
		  </tbody>
		</table>
	</div><!-- End Tab Service -->
	<!-- ------------------------------------------------------End Service------------------------------------------------------------- -->
	<div class="tab-pane fade" id="tab4">
		<table class="table table-bordered">
		 <thead>
			<tr><th colspan="4">Nominee Detail</th></tr>
		  </thead>
		  <tbody>
		  	<tr>
		  		<td>Relation : <span class="red">*</span></td>
		  		<td>
		  		       <select  name="nominee_ad_relation_id_1" id="nominee_ad_relation_id_1" tabindex="44"  class="form-control input-medium">
								 <option value="">--Select Relation--</option>
								  <%RelationDao rdao=new RelationDao();
								 	ArrayList<RelationBean> rlist=rdao.getAllRelation();
								 	if(rlist.isEmpty()!=true){
								 	for(RelationBean bean:rlist){%>
								 <option value="<%=bean.getAd_relation_id()%>"><%=bean.getRelation() %></option>
								 <%} 
								 }%>
								
						</select>
						<label class="error"></label>
		  		</td>
		  		<td>Gender : <span class="red">*</span></td>
		  		<td>
		  		       <select class="form-control input-medium"  name="nominee_ad_gender_id_1" tabindex="45"  id="nominee_ad_gender_id_1" onChange="getSalutationData(this.value,'nominee_ad_salutation_id_1');" >
		  		       <option value="">--Select Gender--</option>
								  <%GenderDao gdao=new GenderDao();
								 	ArrayList<GenderBean> glist=gdao.getAllGender();
								 	if(glist.isEmpty()!=true){
								 	for(GenderBean bean:glist){%>
								 <option value="<%=bean.getAd_gender_id()%>"><%=bean.getGender() %></option>
								 <%}
								 	}%>
								
						</select>
						<label class="error"></label>
		  		</td>
		  	</tr>
		  	<tr>
		  		<td>Salutation : <span class="red">*</span></td>
		  		<td>
		  		  <select class="form-control input-medium" name="nominee_ad_salutation_id_1" tabindex="46"  id="nominee_ad_salutation_id_1" >
		  		  				<option value="">--Select Salutation--</option>
								 <%
								 SalutationDao salutationdao=new SalutationDao();
								 ArrayList<SalutationBean> salutationlist=salutationdao.getAllSalutation();
								 if(salutationlist.isEmpty()!=true){
									 for(SalutationBean bean:salutationlist){%>
									 <option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
																		 
									 <%	 }
								 }%>
				   </select>
				   <label class="error"></label>
		  		</td>
		  		<td>Name : <span class="red">*</span></td>
		  		<td><input class="form-control input-medium" type="text"  name="nominee_name_1" tabindex="47"  id="nominee_name_1" style="text-transform: uppercase;"/><label class="error"></label></td>
		  	</tr>
		  	<tr>
		  		<td>DOB : <span class="red">*</span></td>
		  		<td><input class="form-control input-medium date-picker2" type="text" tabindex="48"  name="nominee_dob_1" id="nominee_dob_1" placeholder="DD/MM/YYYY" /><label class="error"></label></td>
		  		<td>Age :</td>
		  		<td><input class="form-control input-medium" type="text"  name="nominee_age_1" tabindex="49"  id="nominee_age_1" /><label class="error"></label></td>
		  	</tr>
		  	<tr>
		  	  <td>Guardian : </td>
		  	  <td colspan="3"><input class="form-control input-medium" type="text"  name="guardian1" tabindex="50"  id="guardian1" disabled="disabled" style="text-transform: uppercase;"/><label class="error"></label></td>
		  	</tr>
		  </tbody>
		</table>
		
		<table class="table table-bordered">
			<tr><th width="70%"></th><th width="30%">Second Nominee : <input type="checkbox" tabindex="51"  name="second_nominee" id="second_nominee" value="second" style="width: 15px; height: 15px;text-transform: uppercase;"></th></tr>
		</table>
		<table id="tblSecondNomineeInfo" class="table table-bordered">
		 <thead>
			<tr><th colspan="3">Second Nominee Detail</th>
				<th></th>
			</tr>
		  </thead>
		  <tbody>
		  	<tr>
		  		<td>Relation : <span class="red">*</span></td>
		  		<td>
		  		<select  name="nominee_ad_relation_id_2" id="nominee_ad_relation_id_2" tabindex="52" class="input-medium">
						          <option value="">--Select Relation--</option>
								  <%RelationDao rdao2=new RelationDao();
								 	ArrayList<RelationBean> rlist2=rdao2.getAllRelation();
								 	 if(rlist2.isEmpty()!=true){
								 	for(RelationBean bean:rlist2){%>
								 <option value="<%=bean.getAd_relation_id()%>"><%=bean.getRelation() %></option>
								 <%}
								 	}%>
				</select><label class="error"></label>
		  		</td>
		  		<td>Gender : <span class="red">*</span></td>
		  		<td>
		  		<select class="form-control input-medium" name="nominee_ad_gender_id_2" tabindex="53" id="nominee_ad_gender_id_2" onChange="getSalutationData(this.value,'nominee_ad_salutation_id_2');" >
						        <option value="">--Select Gender--</option>
								 <%GenderDao gdao1=new GenderDao();
								 	ArrayList<GenderBean> glist1=gdao1.getAllGender();
								 	 if(glist1.isEmpty()!=true){
								 	for(GenderBean bean:glist1){%>
								 <option value="<%=bean.getAd_gender_id()%>"><%=bean.getGender() %></option>
								 <%} 
								 }%>
								
				</select><label class="error"></label>
		  		</td>
		  	</tr>
		  	<tr>
		  		<td>Salutation : <span class="red">*</span></td>
		  		<td>
		  		<select class="form-control input-medium" name="nominee_ad_salutation_id_2" tabindex="54" id="nominee_ad_salutation_id_2" >
								 <option value="">--Select Salutation--</option>
								  <%
								 SalutationDao sdao2=new SalutationDao();
								 ArrayList<SalutationBean> slist2=sdao2.getAllSalutation();
								 if(slist2.isEmpty()!=true){
									 for(SalutationBean bean:slist2){%>
									 <option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
																		 
									 <%	 }
								 }%>
				</select><label class="error"></label>
		  		</td>
		  		<td>Name : <span class="red">*</span></td>
		  		<td><input class="form-control input-medium" type="text" name="nominee_name_2" tabindex="55" id="nominee_name_2" style="text-transform: uppercase;"/><label class="error"></label></td>
		  	</tr>
		  	<tr>
		  		<td>DOB :</td>
		  		<td><input class="form-control input-medium date-picker2" type="text" tabindex="56" name="nominee_dob_2" id="nominee_dob_2" placeholder="DD/MM/YYYY" /><label class="error"></label></td>
		  		<td>Age :</td>
		  		<td><input class="form-control input-medium" type="text"  name="nominee_age_2" tabindex="57" id="nominee_age_2"  /></td>
		  	</tr>
		  	<tr>
		  	  <td>Guardian : </td>
		  	  <td colspan="3"><input class="form-control input-medium" type="text"  name="guardian2" tabindex="58" id="guardian2" disabled="disabled" style="text-transform: uppercase;"/><label class="error"></label></td>
		  	</tr>
		  </tbody>
		</table>
	</div><!-- End Tab Nominee -->
	<!-- ------------------------------------------------------End Nominee--------------------------------------------------- -->
	<div class="tab-pane fade" id="tab5">
	<table class="table table-bordered">
	   <tr>
	   		<td>Member : </td>
	   		<td>
	   			<select class="form-control input-medium" name="witness_ad_member_id" id="witness_ad_member_id" tabindex="59">
							<option value="">--Select Member--</option>
							<%
							MemberRegistrationDao memberdao=new MemberRegistrationDao();
							ArrayList<MemberRegistrationBean> memberlist=memberdao.getAllMemberlist();
							if(memberlist.isEmpty()!=true){
								for(MemberRegistrationBean bean:memberlist){%>
									<option value="<%=bean.getAd_member_id()%>"><%=bean.getName() %></option>
								<%}
							}%>
				</select><label class="error"></label>
	   		</td>
	   		<td>Staff.No : <span class="red">*</span></td>
	   		<td><input class="form-control input-medium" type="text"  tabindex="60"  name="witness_ad_society_id" id="witness_ad_society_id" /></td>
	   </tr>
	   <tr>
	   		<td>Salutation : </td>
	   		<td>
	   			<select class="form-control input-medium" name="witness_ad_salutation_id" tabindex="61" id="witness_ad_salutation_id" >
					 <%
						 SalutationDao sdao3=new SalutationDao();
						 ArrayList<SalutationBean> slist3=sdao3.getAllSalutation();
						 if(slist3.isEmpty()!=true){
						 for(SalutationBean bean:slist3){%>
						 <option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
					<%}
					}%>
			    </select><label class="error"></label>
	   		</td>
	   		<td>Name : <span class="red">*</span> </td>
	   		<td><input class="form-control input-medium" type="text"  name="witness_name" tabindex="62" id="witness_name" style="text-transform: uppercase;"/><label class="error"></label></td>
	   </tr>
	   <tr>
	   		<td>Mobile <span class="red">*</span></td>
	   		<td><input class="form-control input-medium" type="text" name="witness_mobile" tabindex="63" id="witness_mobile" /><label class="error"></label></td>
	   		<td></td>
	   		<td></td>
	   </tr>
	   <tr>
	   		<td>Address</td>
	   		<td colspan="3">
	   		<textarea class="form-control"  name="witness_address" id="witness_address" tabindex="64" style="text-transform: uppercase;"></textarea>
	   		<label class="error"></label>
	   		</td>
	   </tr>
	</table>
	<table class="table table-bordered">
			<tr><th width="70%"></th><th width="30%">Second Guarantor : <input type="checkbox" tabindex="51"  name="second_witness" id="second_witness" value="second" style="width: 15px; height: 15px;text-transform: uppercase;"></th></tr>
	</table>
	<table class="table table-bordered" id="tblSecondGaurentarInfo">
	   <tr>
	   		<td>Member : </td>
	   		<td>
	   			<select class="form-control input-medium" name="witness_ad_member_id2" id="witness_ad_member_id2" tabindex="59">
							<option value="">--Select Member--</option>
							<%
							memberdao=new MemberRegistrationDao();
							 memberlist=memberdao.getAllMemberlist();
							if(memberlist.isEmpty()!=true){
								for(MemberRegistrationBean bean:memberlist){%>
									<option value="<%=bean.getAd_member_id()%>"><%=bean.getName() %></option>
								<%}
							}%>
				</select><label class="error"></label>
	   		</td>
	   		<td>Staff.No : <span class="red">*</span></td>
	   		<td><input class="form-control input-medium" type="text"  tabindex="60"  name="witness_ad_society_id2" id="witness_ad_society_id2" /></td>
	   </tr>
	   <tr>
	   		<td>Salutation : </td>
	   		<td>
	   			<select class="form-control input-medium" name="witness_ad_salutation_id2" tabindex="61" id="witness_ad_salutation_id2" >
					 <%
						  sdao3=new SalutationDao();
						  slist3=sdao3.getAllSalutation();
						 if(slist3.isEmpty()!=true){
						 for(SalutationBean bean:slist3){%>
						 <option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
					<%}
					}%>
			    </select><label class="error"></label>
	   		</td>
	   		<td>Name : <span class="red">*</span> </td>
	   		<td><input class="form-control input-medium" type="text"  name="witness_name2" tabindex="62" id="witness_name2" style="text-transform: uppercase;"/><label class="error"></label></td>
	   </tr>
	   <tr>
	   		<td>Mobile <span class="red">*</span></td>
	   		<td><input class="form-control input-medium" type="text" name="witness_mobile2" tabindex="63" id="witness_mobile2" /><label class="error"></label></td>
	   		<td></td>
	   		<td></td>
	   </tr>
	   <tr>
	   		<td>Address</td>
	   		<td colspan="3">
	   		<textarea class="form-control"  name="witness_address2" id="witness_address2" tabindex="64" style="text-transform: uppercase;"></textarea>
	   		<label class="error"></label>
	   		</td>
	   </tr>
	</table>
	</div><!-- End Tab Witness -->
	<!-- ------------------------------------------------------End Withness-------------------------------------------------------- -->
	<div class="tab-pane fade" id="tab6">
		<table class="table table-bordered">
		<% MemberRegistrationMasterBean bean=new MemberRegistrationMasterDao().getMemberRegistrationMasterByMaxId();%>
		 <tr>
		 	<td>Membership Fess</td>
		 	<td><input class="form-control input-medium" type="text" disabled="disabled" tabindex="65" name="memfees" id="membership_fees" value="<%=bean.getMembership_fees() %>"  />
		 	<input class="form-control input-medium" type="hidden" name="h_membership_fees" id="h_membership_fees" value="<%=bean.getMembership_fees()%>"  />
		 	</td>
		 	<td>THRIFT Fund</td>
		 	<td><input class="form-control input-medium" type="text" disabled="disabled" tabindex="66" name="fgds_fund" id="fgds_fund" value="<%=bean.getFgds_fund()%>" />
		 	<input class="form-control input-medium" type="hidden" name="h_fgds_fund" id="h_fgds_fund" value="<%=bean.getFgds_fund()%>" />
		 	</td>
		 </tr>
		 <tr>
		 	<!-- <td>DCF</td> -->
		 	<input class="form-control input-medium" type="hidden" disabled="disabled" name="dcf" id="dcf" value="0" />
		 	<input class="form-control input-medium" type="hidden" name="h_dcf" id="h_dcf" value="0" />
		 	
		 	<td>Share Qty</td>
		 	<td><input class="form-control input-medium" type="text" tabindex="67" name="share_qty" id="share_qty" value="<%=bean.getShare()%>" />
		 	<input class="form-control input-medium" type="hidden" name="h_share_qty" id="h_share_qty" value="<%=bean.getShare()%>" />
		 	</td>
		 	<td>Share Amt</td>
		 	<%ShareBean sbean=new ShareDao().getShareRate();%>
		 	<td>
		 	<input type="hidden" name="share_rate" id="share_rate" tabindex="68" value="<%=sbean.getPer_share_rate()%>"/>
		 	<input class="form-control input-medium" type="text" disabled="disabled"  name="share_amt" id="share_amt" value="<%=bean.getShare()*sbean.getPer_share_rate()%>" />
		 	<input class="form-control input-medium" type="hidden" name="h_share_amt" tabindex="69" id="h_share_amt" value="<%=bean.getShare()*sbean.getPer_share_rate() %>" />
			<% double fess=bean.getMembership_fees()+bean.getFgds_fund()+bean.getDcf()+(bean.getShare()*sbean.getPer_share_rate());%>
			</td>
		 </tr>
		 <tr>
		 	
		 	<%-- <td>Admission Fees</td>--%>
		 	<input class="form-control input-medium" type="hidden" disabled="disabled" name="admission_fees" id="admission_fees" value="0"   />
		 	<input class="form-control input-medium" type="hidden" name="h_admission_fees" id="h_admission_fees" value="0"  />
		 	 
		 </tr>
		</table>
	</div><!-- End Tab Fees -->
	<!-- ------------------------------------------------------End Fees------------------------------------------------ -->
	<div class="tab-pane fade" id="tab7">
		<table class="table table-bordered">
	    <tr>
	   		<td>Trx Type :<span class="red">*</span> </td>
	   		<td>
	   			<select class="form-control input-medium" name="vtype" id="vtype" tabindex="70">
					<option value="">--Select Transaction Type--</option>
					<option value="Cheque">Cheque</option>
					<option value="Online">Online</option>
					<option value="DD">Demand Draft</option>
				</select><label class="error"></label>
	   		</td>
	   		<td>Instrument From : <span class="red">*</span></td>
	   		<td><select class="form-control input-medium"  name="ins_form" id="ins_form" tabindex="71">
	   		<option value="">--Select Branch--</option>
								<%
								 
								  if(banklist.isEmpty()!=true){
								  for(BankBean bean1:banklist){
								  %>
								  <option value="<%=bean1.getAd_bank_id()%>"><%=bean1.getBank().trim() %></option>
								 <%} }%>
						</select><label class="error"></label>
	   		</td>
	   </tr>
	    <tr>
	   		<td>Instrument No :<span class="red">*</span> </td>
	   		<td><input class="form-control input-medium" type="text"  name="ins_no" id="ins_no" tabindex="72" required="required"/><label class="error"></label>
	   		</td>
	   		<td>Instrument Date :<span class="red">*</span></td>
	   		<td><input class="form-control input-medium date-picker2" type="text"  name="ins_date" tabindex="73" required="required" id="ins_date" /><label class="error"></label></td>
	   </tr>
	    <tr>
	   		<td>Voucher Amt :<span class="red">*</span> </td>
	   		<td><input class="form-control input-medium" type="text"  name="v_amt" id="v_amt" tabindex="74" disabled="disabled" />
	   		<input class="form-control input-medium" type="hidden"  name="h_v_amt" id="h_v_amt" />
	   		<label class="error"></label>
	   		</td>
	   		<td>Voucher Type :<span class="red">*</span></td>
	   		<td>
	   		<input type="text" class="form-control input-medium" name="voucher_type" id="voucher_type" tabindex="75" value="Received" readonly="readonly">
					
	   	   <label class="error"></label></td>
	   </tr>
	   </table>
	</div><!-- End Tab Payment -->
	<!-- ------------------------------------------------------End payment--------------------------------------------- -->
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
			<a href="javascript:;" class="btn blue button-next">
				Continue <i class="m-icon-swapright m-icon-white"></i>
			</a>
			<button  class="btn green button-submit" style="display: none;" tabindex="76">Submit <i class="m-icon-swapright m-icon-white"></i></button>
			<button type="button" class="btn blue button-show-modal" style="display: none;" tabindex="77">Upload Image </button>
		   </div>
		 </div>
	 </div>
</div><!--End form-actions fluid -->
</form>	
<form id="frmMemberFileUpload" action="AdMemberRegistration?action=uploadimage" method="post" enctype="multipart/form-data">
<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
	<div class="modal fade memberRegModel" id="large" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-blue">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title"><i class="fa fa-cogs"></i> Member Registration</h4>
				</div><!-- modal header -->
				<div class="modal-body">
				   <div id="custom-page-message1"></div><!-- End custom-page-message -->
				   <div id="member-RgImage">
				   <input type="hidden" id="h_member_id_up" name="h_member_id_up" value="" />
				   <input type="hidden" id="h_member_name_up" name="h_member_name_up" value="" />
				   <table class="table table-bordered">
				    <tr>
				     	<td width="20%">Transaction no  :</td>
				     	<td width="30%"><div id="show_trx_no"></div></td>
				     </tr>
				     <tr>
				     	<td width="20%">Share Batch Number :</td>
				     	<td width="30%"><div id="show_share_batch"></div></td>
				     </tr>
				      <tr>
				     	<td>Share Form Number :</td>
				     	<td><div id="show_share_from"></div></td>
				     </tr>
				      <tr>
				     	<td>Share to Number :</td>
				     	<td><div id="show_share_to"></div></td>
				     </tr>
				   </table>
				   	<table class="table">
					<thead>
						<tr>
						<th>Member Image Upload</th>
						<th colspan="2">Note :- Use only JPG or PNG image with 100px * 100px height and width.</th></tr>
					</thead>
					<tbody>
						<tr>
						<td width="25%">
						<div class="userimg-block">
							<div class="user-pic">
							    <img class="uimg" id='photo_view' src="Image/emp.png" alt="Employee Photo" />
							</div>
							<div class="user-input">
							  <span class="text-center">Photo</span>
							<input type="file" name="photo" id="photo" accept="image/*" />
							</div>
							<label class="error"></label>
						   </div><!-- End userimg-block -->
			             </td>
						  <td width="25%">
						  <div class="userimg-block">
						  <div class="user-pic">
							    <img class="uimg" id='id_proof_view' src="Image/id.png" alt="Employee Id Card"  />
							</div>
							<div class="user-input">
							  <span class="text-center">ID Proof</span>
							<input type="file" name="id" id="id_proof" accept="image/*" />
							</div>
							<label class="error"></label>
						   </div><!-- End userimg-block -->
			            </td>
							<td colspan="2" width="25%">
							<div class="userimg-block">
						     <div class="user-pic">
							    <img class="uimg" id='sign_view' src="Image/sign.png" alt="Employee Sign"  />
							 </div>
							 <div class="user-input">
							  <span class="text-center">Sign</span>
							  <input type="file" name="sign" id="sign" accept="image/*" />
							</div>
							<label class="error"></label>
						   </div><!-- End userimg-block -->
							</td>
						</tr>
						</tbody>
					</table><!-- End member Image upload -->
					
			<table class="table">
			<thead><tr><th>Nominee 1 Image Upload</th></tr></thead>
			<tbody>
			<tr>
				<td>
					<div class="userimg-block">
				     <div class="user-pic">
					    <img class="uimg" id='nominee_photo_view_1' src="Image/emp.png" alt="Employee Sign"  />
					 </div>
					 <div class="user-input">
					  <span class="text-center">Sign</span>
					  <input type="file" name="nominee_photo_1" id="nominee_photo_1" accept="image/*" />
					</div>
					<label class="error"></label>
				   </div><!-- End userimg-block -->
				</td>
				<td>
					<div class="userimg-block">
				     <div class="user-pic">
					    <img class="uimg" id='nominee_id_proof_view_1' src="Image/id.png" alt="Employee Sign" />
					 </div>
					 <div class="user-input">
					  <span class="text-center">Sign</span>
					  <input type="file" name="nominee_id_proof_1" id="nominee_id_proof_1" accept="image/*"  />
					</div>
					<label class="error"></label>
				   </div><!-- End userimg-block -->
				</td>
				<td>
					<div class="userimg-block">
				     <div class="user-pic">
					    <img class="uimg" id='nominee_sign_view_1' src="Image/sign.png" alt="Employee Sign" />
					 </div>
					 <div class="user-input">
					  <span class="text-center">Sign</span>
					  <input type="file" name="nominee_sign_1" id="nominee_sign_1" accept="image/*"  />
					</div>
					<label class="error"></label>
				   </div><!-- End userimg-block -->
				</td>
			</tr>
			</tbody>
		</table><!-- End table nominee first image upload -->
		
		<table id="tblSecondNomineeImageModal" class="table">
		<thead><tr><th>Second Nominee Image Upload</th></tr></thead>
			<tr>
				<td>
					<input type="hidden" name="second_nominee" />
					<div class="userimg-block">
				     <div class="user-pic">
					    <img class="uimg" id='nominee_photo_view_2' src="Image/emp.png" alt="Employee Sign" />
					 </div>
					 <div class="user-input">
					  <span class="text-center">Sign</span>
					  <input type="file" name="nominee_photo_2" id="nominee_photo_2"  accept="image/*" />
					</div>
					<label class="error"></label>
				   </div><!-- End userimg-block -->
				</td>
				<td>
					<div class="userimg-block">
				     <div class="user-pic">
					    <img class="uimg" id='nominee_id_proof_view_2' src="Image/id.png" alt="Employee Sign"  />
					 </div>
					 <div class="user-input">
					  <span class="text-center">Sign</span>
					  <input type="file" name="nominee_id_proof_2" id="nominee_id_proof_2"  accept="image/*"/>
					</div>
					<label class="error"></label>
				   </div><!-- End userimg-block -->
				</td>
				<td>
					<div class="userimg-block">
				     <div class="user-pic">
					    <img class="uimg" id='nominee_sign_view_2' src="Image/sign.png" alt="Employee Sign"  />
					 </div>
					 <div class="user-input">
					  <span class="text-center">Sign</span>
					  <input type="file" name="nominee_sign_2" id="nominee_sign_2" accept="image/*" />
					</div>
					<label class="error"></label>
				   </div><!-- End userimg-block -->
				</td>
			</tr>
		</table><!-- End nominee 2 image upload -->
				   
		</div><!-- End member-RgImage -->
										
		</div><!-- modal-body -->
		<div class="modal-footer">
					<button type="submit" class="btn btn-md blue">Upload</button>
					<button type="reset" name="reset" id="reset" class="btn btn-md red" >Clear All</button>
					<button type="button" class="btn btn-md blue" data-dismiss="modal">Close</button>
					
		</div><!-- modeal-footer -->
		</div>
			<!-- /.modal-content -->
		</div>
			<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- ------------------------------End image upload model----------------------------- -->

</form>


</div><!-- End portlet-body -->
</div><!-- End portlet -->
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
<script type="text/javascript" src="assets/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script type="text/javascript" src="assets/scripts/custom/form-wizard-app.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<%
						SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
						String date1=sdf.format((Date)session.getAttribute("openday"));
						%>
<script type="text/javascript">
jQuery(document).ready(function() {       
	FormWizardMember.init();
	
	$('#tblSecondNomineeInfo').hide();
	$('#tblSecondGaurentarInfo').hide();
	$('#tblSecondNomineeImage').hide();
	$('#tblSecondNomineeImageModal').hide();
	
	$('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true,endDate:'<%=date1%>'});
	$('.date-picker2').datepicker({format: 'dd/mm/yyyy',endDate:'<%=date1%>',autoclose: true});
	$('#ad_member_type_id').select2();
	$('#ad_member_status_id').select2();
	$('#ad_gender_id').select2();
	$('#ad_category_id').select2();
	$('#ad_salutation_id').select2();
	
	$('#ad_state_id').select2();
	$('#ad_district_id').select2();
	$('#ad_city_id').select2();
	$('#ad_state_id_1').select2();
	$('#ad_district_id_1').select2();
	$('#ad_country_id_1').select2();
	$('#ad_country_id').select2();
	$('#ad_city_id_1').select2();
	$('#ad_bank_id').select2();
	$('#ad_branch_id').select2();
	$('#ins_form').select2();
	$('#ad_department_id').select2();
	
	$('#ad_designation_id').select2();
	$('#ad_designation_type_id').select2();
	$('#nominee_ad_relation_id_1').select2();
	$('#nominee_ad_gender_id_1').select2();
	$('#nominee_ad_gender_id_2').select2();
	$('#nominee_ad_salutation_id_1').select2();
	$('#nominee_ad_salutation_id_1').select2();
	$('#nominee_ad_relation_id_2').select2();
	$('#witness_ad_member_id').select2();
	$('#witness_ad_salutation_id').select2();
	$('#witness_ad_member_id2').select2();
	$('#witness_ad_salutation_id2').select2();
	$('#vtype').select2();
	$('#marital_status').select2();
});
</script>
<script type="text/javascript">
	jQuery(function(){
		jQuery('#witness_ad_member_id').change(function(){
			loading_show();
			var w_memeber_id = parseInt($(this).val());
			
			if(w_memeber_id!=""){
				var dataString = {"action":"view","ad_member_id":w_memeber_id};
				$.ajax({
					method:"post",
					url:"AdMemberRegistration",
					data:dataString,
					success: function(data){
						$('#witness_ad_society_id').val(data.MemberInfo.ad_society_no);
						$('#witness_ad_salutation_id').val(data.MemberInfo.salutation.ad_salutation_id);
						$('#witness_name').val(data.MemberInfo.name);
						$('#witness_mobile').val(data.MemberInfo.address[0].mobile);
						$('#witness_address').html(data.MemberInfo.address[0].line1+" "+data.MemberInfo.address[0].line2+" "+data.MemberInfo.address[0].city.city+" "+data.MemberInfo.address[0].district.district+" "+data.MemberInfo.address[0].city.city);
					},
					error: function(xhrerror, status, error){
						console.log(status);
					}
				});
				
			}//end check member id
			
		});
		
		function loading_show(){
	        $('#modelLoad').removeClass('hide').addClass('show');
	     }
	     function loading_hide(){
	       $('#modelLoad').addClass('hide').removeClass('show');
	     } 
	});
</script>
<script type="text/javascript">
$(document).ready(function(e) {
	
	var share_rate 			= parseFloat($('#share_rate').val());
	var h_membership_fees   = parseFloat($('#h_membership_fees').val());
	var h_fgds_fund 		= parseFloat($('#h_fgds_fund').val());
	var h_dcf 				= parseFloat($('#h_dcf').val());
	var h_share_amt 		= parseFloat($('#h_share_amt').val());
	
	console.log(share_rate+" "+$('#share_qty').val());
	
	$('#share_qty').keyup(function(){
		var qty = parseFloat($(this).val());
		
		if(qty!="" && qty>0){
			var totalAmt = qty * share_rate;
			var totalAdmFee = totalAmt + h_membership_fees + h_dcf + h_share_amt;
			$('#h_share_qty').val(qty);
			$('#share_amt').val(totalAmt);
			$('#h_share_amt').val(totalAmt);
			$('#admission_fees').val(0);
			$('#h_admission_fees').val(totalAdmFee);
			
			getPayamount();
		}
		
	});
	
	getPayamount();
	
	function getPayamount(){
		var memFee = parseFloat($('#h_membership_fees').val());
		var fgdsFee = parseFloat($('#h_fgds_fund').val());
		var dcfFee = parseFloat($('#h_dcf').val());
		var sqtFee = parseFloat($('#h_share_qty').val());
		var samountFee = parseFloat($('#share_amt').val());
	//	var addmissionFee = parseFloat($('#admission_fees').val());
		var totalAmount = memFee + fgdsFee + dcfFee  + samountFee ;//+ addmissionFee;
		//console.log(memFee + fgdsFee + dcfFee  + samountFee + addmissionFee);
		
		$('#v_amt').val(totalAmount);
		$('#h_v_amt').val(totalAmount);
	}
	
	$('#nominee_dob_1').change(function (ev) {
		var date = $(this).val();
		var age = calculateAgeByDob(date);
		if(age<0){age=0;}
		$('#nominee_age_1').val(age);
		
		if(age<18){
			$('#guardian1').removeAttr('disabled');
			$('#custom-page-message').html("<div class='note note-info'>Nominee is "+ age +" year old. Please fill Guardian Name Below.</div>");
			$('.custom-messageBox').modal('show');
		}else{
			$('#guardian1').val("");
			$('#guardian1').attr("disabled","disabled");
		}
		
	});//end chanage nominee dob for calculate age 
	
	
	
	$('#nominee_dob_2').change(function (ev) {
		var date = $(this).val();
		var age = parseInt(calculateAgeByDob(date));
		if(age<0){age=0;}
		$('#nominee_age_2').val(age);
		
		if(age<18){
			$('#guardian2').removeAttr('disabled');
			$('#custom-page-message').html("<div class='note note-info'>Nominee is "+ age +" year old. Please fill Guardian Name Below.</div>");
			$('.custom-messageBox').modal('show');
		}else{
			$('#guardian2').attr("disabled","disabled");
		}
	});//end chanage nominee dob for calculate age
	
	function calculateAgeByDob(date){
		var newDate = date.split("/").reverse().join("/");
		var dob = new Date(newDate);
		var today = new Date();
		var diff = today-dob;
		var age = Math.floor(diff / (31536000000));// Divide by 1000*60*60*24*365
		
		return  age;
	}//end calculateAgeByDob
	
});
</script>

<script type="text/javascript">
$(document).ready(function(e) {
		
	$("#photo").change(function(){
	    readURL_photo(this);
	});
	$("#id_proof").change(function(){
	    readURL_id_proof(this);
	});
	$("#sign").change(function(){
	    readURL_sign(this);
	});
	
	$("#nominee_photo_1").change(function(){
	    readURL_nominee_photo_1(this);
	});
	$("#nominee_id_proof_1").change(function(){
	    readURL_nominee_id_proof_1(this);
	});
	$("#nominee_sign_1").change(function(){
	    readURL_nominee_sign_1(this);
	});
	
	$("#nominee_photo_2").change(function(){
	    readURL_nominee_photo_2(this);
	});
	$("#nominee_id_proof_2").change(function(){
	    readURL_nominee_id_proof_2(this);
	});
	$("#nominee_sign_2").change(function(){
	    readURL_nominee_sign_2(this);
	});
	
	
$("#ad_state_id").change(function(e) {
	
		if($(this).val()==0)
		{
			alert("Please Select State......!!");
			return false;
		}
		else
		{
        		var ad_state_id=$(this).val();
		$.ajax({
			   type: "POST",
			   url: "Ajax/read_district_by_state_id.jsp?ad_state_id="+ad_state_id,
			   async:false,
			   success: function(data)
			   {	
				   $('#ad_district_id').html(data);
				   $('#ad_district_id_1').html(data);
		  	} }); 
		}
		
	});

	
$("#ad_district_id").change(function(e) {
	
	if($(this).val()==0)
	{
		alert("Please Select State......!!");
		return false;
	}
	else
	{
    		var ad_district_id=$(this).val();
    		//alert(group_id);
	$.ajax({
		   type: "POST",
		   url: "Ajax/read_city_by_district_id.jsp?ad_district_id="+ad_district_id,
		   async:false,
		   success: function(data)
		   {	
			   $('#ad_city_id').html(data);
			   $('#ad_city_id_1').html(data);
	  	} }); 
	}
	
});  

$("#ad_state_id_1").change(function(e) {
	
	if($(this).val()==0)
	{
		alert("Please Select State......!!");
		return false;
	}
	else
	{
    		var ad_state_id=$(this).val();
    		//alert(group_id);
	$.ajax({
		   type: "POST",
		   url: "Ajax/read_district_by_state_id.jsp?ad_state_id="+ad_state_id,
		   async:false,
		   success: function(data)
		   {	
			   $('#ad_district_id_1').html(data);
	  	} }); 
	}
	
});


$("#ad_district_id_1").change(function(e) {

if($(this).val()==0)
{
	alert("Please Select State......!!");
	return false;
}
else
{
		var ad_district_id=$(this).val();
$.ajax({
	   type: "POST",
	   url: "Ajax/read_city_by_district_id.jsp?ad_district_id="+ad_district_id,
	   async:false,
	   success: function(data)
	   {	
		   $('#ad_city_id_1').html(data);
		   $('#ad_city_id_1').trigger("chosen:updated");
  	} }); 
}

});  


$("#same").on("click", function() {
    if($(this).is(':checked')){
    	
    	$('#mobile1').val($('#mobile').val());
    	$('#phone1').val($('#phone').val());
    	$('#email1').val($('#email').val());
    	$('#line1_1').val($('#line1').val());
    	$('#line2_1').val($('#line2').val());
    	var country  = $('#ad_country_id option:selected').val();
    	var state    = $('#ad_state_id option:selected').val();
    	var district = $('#ad_district_id option:selected').val();
    	var city     = $('#ad_city_id option:selected').val();
    	
    	$('#ad_country_id_1 option[value='+country+']').attr('selected','selected');
    	$("#ad_state_id_1").select2("val", state);
    	$("#ad_district_id_1").select2("val", district);
    	$("#ad_city_id_1").select2("val", city);
    	$('#pincode1').val($('#pincode').val());
    	
    }else{
    	
    	$('#mobile1').val('');
    	$('#phone1').val('');
    	$('#email1').val('');
    	$('#line1_1').val('');
    	$('#line2_1').val('');
    	var country=$('#country').val();
    	$("#ad_state_id_1").select2("val", "");
    	$("#ad_district_id_1").select2("val", "");
    	$("#ad_city_id_1").select2("val", "");
    	$('#pincode1').val('');
    }
  });
  
  //check same nominee
  $("#second_nominee").click(function(){
	  if($(this).is(':checked')){
		  $('#tblSecondNomineeInfo').show();
		  $('#tblSecondNomineeImage').show();
		  $('#tblSecondNomineeImageModal').show();
	  }else{
		  $('#tblSecondNomineeInfo').hide();
		  $('#tblSecondNomineeImage').hide();
		  $('#tblSecondNomineeImageModal').hide();
	  }
  });
  
  //check same nominee
  $("#second_witness").click(function(){
	  if($(this).is(':checked')){
		  $('#tblSecondGaurentarInfo').show();
		  
	  }else{
		  $('#tblSecondGaurentarInfo').hide();
		  
	  }
  });
  //branch display
$("#ad_branch_id").change(function(e) {
	var id = $(this).val();
	var dataString = "ad_branch_id="+id;
	 $.ajax({
         type: "POST",
         url: "Ajax/getBranchById.jsp",
         data: dataString,
         dataType: "json",
        
         //if received a response from the server
         success: function( data, textStatus, jqXHR) {
             //our country code was correct so we have some information to display
             
              if(data.success){
            	  //alert(data.BranchInfo.bank.bank);
            	            	         	  
            	  $("#ad_bank_region_id").val(data.BranchInfo.bank_region.region);
            	  $("#ad_bank_state_id").val(data.BranchInfo.state.state);
            	  $("#ad_bank_district_id").val(data.BranchInfo.district.district);
            	  $('#ad_bank_city_id').val(data.BranchInfo.city.city); 
            	  $("#branch_code").val(data.BranchInfo.branch_code);
            	  $("#ifsc_code").val(data.BranchInfo.ifsc_code);
            	  $("#address").val(data.BranchInfo.address);
            	  $("#bank_pincode").val(data.BranchInfo.pincode.trim());
            	  $("#contact_no").val(data.BranchInfo.contact_no);
            	  $("#contact_person").val(data.BranchInfo.contact_person);
            	  $("#email_id").val(data.BranchInfo.email_id.trim());
            	  $("#phone_no").val(data.BranchInfo.phone_no);
              } 
              //display error message
              else {
                  $("#ajaxResponse").html("<div><b>Branch id in Invalid!</b></div>");
              }
         },
        
         //If there was no resonse from the server
         error: function(jqXHR, textStatus, errorThrown){
              console.log("Something really bad happened " + textStatus);
               $("#ajaxResponse").html(jqXHR.responseText);
         }

     });
	 
	 
	 });  
  
});//end dom

function readURL_photo(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#photo_view').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
function readURL_id_proof(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#id_proof_view').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
function readURL_sign(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#sign_view').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}

function readURL_nominee_photo_1(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#nominee_photo_view_1').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
function readURL_nominee_id_proof_1(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#nominee_id_proof_view_1').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
function readURL_nominee_sign_1(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#nominee_sign_view_1').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}

function readURL_nominee_photo_2(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#nominee_photo_view_2').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
function readURL_nominee_id_proof_2(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#nominee_id_proof_view_2').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
function readURL_nominee_sign_2(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#nominee_sign_view_2').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
</script>
<script type="text/javascript">
$(function(){
	
	 $('#doa').datepicker().on('changeDate', function (ev) {
		var doa = $(this).val();
		var dor = $("#dor").val();
		var dataString = {"doa":doa,"dor":dor};
		 $.ajax({
			  type: "POST",
			  url: "Ajax/getServiceDuration.jsp",
			  data: dataString,
			  success: function(data){
				  $("#service_duration").removeAttr('readonly');
				  $('#service_duration').val(data.trim());
				  $('#h_service_duration').val(data.trim());
				  $("#service_duration").attr('readonly', 'readonly');
			  },
			  error:function(jqXHR, textStatus, errorThrown){
				  console.log("error");
			  }
		  });
	    });	//end datepick change doa
	 
	  $('#ad_designation_type_id').change(function(){
		  var dataString = "desigType="+$(this).val();
			 $.ajax({
		         type: "POST",
		         url: "Ajax/getDesignation.jsp",
		         data: dataString,      
		         success: function(data) {
		             $("#ad_designation_id").html(data);
		         },
		         error: function(jqXHR, textStatus, errorThrown){
		               $("#ajaxResponse").html(jqXHR.responseText);
		         }

		     });
	  });//end designation change type id
	  
	  
	  $('#ad_pf_id').change(function(){
		
		  var dataString = $(this).val();
			 $.ajax({
		         type: "POST",
		         url: "AdMemberRegistration?action=verifypfno",
		         data: "ad_pf_no="+dataString,      
		         success: function(data) {
		        	 if(data.message=="error")
		        	 {
		        		 $('#custommessage').removeClass('display-none');
		        		 $('#mymsg').html(data.errorMessage);
		        	 }else{
		        		 
		        		 $('#custommessage').addClass('display-none');
		        		 $('#mymsg').html("");
		        	 }
		         },
		         error: function(jqXHR, textStatus, errorThrown){
		               $("#ajaxResponse").html(jqXHR.responseText);
		         }

		     });
	  });//end designation change type id
	  
	  
	  
	  $('#mobile').change(function(){
			
		  var dataString = $(this).val();
			 $.ajax({
		         type: "POST",
		         url: "AdMemberRegistration?action=verifymobileno",
		         data: "mobile_no="+dataString,      
		         success: function(data) {
		        	 if(data.message=="error")
		        	 {
		        		
		        		 $('#custom-page-message').html("<div class='note note-info'>"+ data.errorMessage +" </div>");
		     			$('.custom-messageBox').modal('show');
		        	 }else{
		        		 
		        		 $('#custommessage1').addClass('display-none');
		        		 $('#mymsg1').html("");
		        	 }
		         },
		         error: function(jqXHR, textStatus, errorThrown){
		               $("#ajaxResponse").html(jqXHR.responseText);
		         }

		     });
	  });//end mobile
	  
	  $('#mobile1').change(function(){
			
		  var dataString = $(this).val();
			 $.ajax({
		         type: "POST",
		         url: "AdMemberRegistration?action=verifymobileno",
		         data: "mobile_no="+dataString,      
		         success: function(data) {
		        	 if(data.message=="error")
		        	 {
		        		 $('#custom-page-message').html("<div class='note note-info'>"+ data.errorMessage +" </div>");
			     			$('.custom-messageBox').modal('show');
		        	 }else{
		        		 
		        		 $('#custommessage1').addClass('display-none');
		        		 $('#mymsg1').html("");
		        	 }
		         },
		         error: function(jqXHR, textStatus, errorThrown){
		               $("#ajaxResponse").html(jqXHR.responseText);
		         }

		     });
	  });//end mobile 1
	  
	  $('#phone').change(function(){
			
		  var dataString = $(this).val();
			 $.ajax({
		         type: "POST",
		         url: "AdMemberRegistration?action=verifyphoneno",
		         data: "phone_no="+dataString,      
		         success: function(data) {
		        	 if(data.message=="error")
		        	 {
		        		 $('#custom-page-message').html("<div class='note note-info'>"+ data.errorMessage +" </div>");
			     			$('.custom-messageBox').modal('show');
		        	 }else{
		        		 
		        		 $('#custommessage1').addClass('display-none');
		        		 $('#mymsg1').html("");
		        	 }
		         },
		         error: function(jqXHR, textStatus, errorThrown){
		               $("#ajaxResponse").html(jqXHR.responseText);
		         }

		     });
	  });//end phone
	  
	  $('#phone1').change(function(){
			
		  var dataString = $(this).val();
			 $.ajax({
		         type: "POST",
		         url: "AdMemberRegistration?action=verifyphoneno",
		         data: "phone_no="+dataString,      
		         success: function(data) {
		        	 if(data.message=="error")
		        	 {
		        		 $('#custom-page-message').html("<div class='note note-info'>"+ data.errorMessage +" </div>");
			     			$('.custom-messageBox').modal('show');
		        	 }else{
		        		 
		        		 $('#custommessage1').addClass('display-none');
		        		 $('#mymsg1').html("");
		        	 }
		         },
		         error: function(jqXHR, textStatus, errorThrown){
		               $("#ajaxResponse").html(jqXHR.responseText);
		         }

		     });
	  });//end phone 1
	  
	  
	  $('#email').change(function(){
			
		  var dataString = $(this).val();
			 $.ajax({
		         type: "POST",
		         url: "AdMemberRegistration?action=verifyemail",
		         data: "email="+dataString,      
		         success: function(data) {
		        	 if(data.message=="error")
		        	 {
		        		 $('#custom-page-message').html("<div class='note note-info'>"+ data.errorMessage +" </div>");
			     			$('.custom-messageBox').modal('show');
		        	 }else{
		        		 
		        		 $('#custommessage1').addClass('display-none');
		        		 $('#mymsg1').html("");
		        	 }
		         },
		         error: function(jqXHR, textStatus, errorThrown){
		               $("#ajaxResponse").html(jqXHR.responseText);
		         }

		     });
	  });//end email 1
	  
	  $('#email1').change(function(){
			
		  var dataString = $(this).val();
			 $.ajax({
		         type: "POST",
		         url: "AdMemberRegistration?action=verifyemail",
		         data: "email="+dataString,      
		         success: function(data) {
		        	 if(data.message=="error")
		        	 {
		        		 $('#custom-page-message').html("<div class='note note-info'>"+ data.errorMessage +" </div>");
			     			$('.custom-messageBox').modal('show');
		        	 }else{
		        		 
		        		 $('#custommessage1').addClass('display-none');
		        		 $('#mymsg1').html("");
		        	 }
		         },
		         error: function(jqXHR, textStatus, errorThrown){
		               $("#ajaxResponse").html(jqXHR.responseText);
		         }

		     });
	  });//end email 1
	  
	  
	  $('#ad_bank_id').change(function(){
			
		  var dataString = $(this).val();
			 $.ajax({
		         type: "POST",
		         url: "Ajax/getBranchByBankId.jsp",
		         data: "ad_bank_id="+dataString,      
		         success: function(data) {
		        	$('#ad_branch_id').html(data);
		         },
		         error: function(jqXHR, textStatus, errorThrown){
		               $("#ajaxResponse").html(jqXHR.responseText);
		         }

		     });
	  });//end email 1
	  
	  $("#dob").change(function(e){
		  var dataString = "date="+$("#dob").val();
			 $.ajax({
		         type: "POST",
		         url: "Ajax/calculate_retirement_date.jsp",
		         data: dataString,      
		         success: function( data, textStatus, jqXHR) {
		             $("#dor").val(data.trim());
		             $("#h_dor").val(data.trim());
		         },
		         error: function(jqXHR, textStatus, errorThrown){
		               $("#ajaxResponse").html(jqXHR.responseText);
		         }

		     }); 
			 
	  });//end dob change 
	  
});//end dom


function getSalutationData(val,senderID){
	   $.ajax({
		  type: "POST",
		  url: "Ajax/getSalutation.jsp",
		  data: "gender="+parseInt(val),
		  success: function(data){
			  $('#'+senderID).html(data);
		  },
		  error:function(jqXHR, textStatus, errorThrown){
			  console.log("error");
		  }
	  });
}//getSalutation function

</script>
<script type="text/javascript">
$(function(){
		
	$.validator.addMethod("extension", function(value, element, param) {
    	param = typeof param === "string" ? param.replace(/,/g, "|") : "png|jpe?g|gif";
    	return this.optional(element) || value.match(new RegExp(".(" + param + ")$", "i"));
    }, $.validator.format("Please enter a value with a valid extension."));
    
    jQuery( "#frmMemberFileUpload" ).validate({
		  rules: {
				photo:{
		        	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
		        },
		        id:{
		        	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
		        },
		        sign:{
		        	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
		        },
		        nominee_photo_1 :{
		        	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
		        },
		        nominee_id_proof_1:{
		        	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
		        },
		        nominee_sign_1:{
		        	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
		        },
		        nominee_photo_2:{
		        	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
		        },
		        nominee_id_proof_2:{
		        	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
		        },
		        nominee_sign_2:{
		        	extension: "jpg|jpeg|JPG|JPEG|png|PNG",
		        },
		  }
		});
	
});//end dom
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>