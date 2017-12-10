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
			<li><a href="#">Issue New FD</a><i class="fa fa-angle-right"></i>Issue</li>
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
<div class="caption"><i class="fa fa-reorder"></i> Issue New FD -
<span class="step-title">Step 1 of 5</span>
</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<form id="frmNewIssueFD" autocomplete="off" action="AdFdTrx?action=insert" method="post">
<!-- -----------------------------------------Start Form----------------------------------------------- -->	
<div class="form-wizard">
<div class="form-body">
<ul class="nav nav-pills steps">
	<li class="active"><a href="#tab1" data-toggle="tab" class="step">Personal</a></li>
	<li class=""><a href="#tab2" data-toggle="tab" class="step">Contact</a></li>
	<li class=""><a href="#tab3" data-toggle="tab" class="step">Service</a></li>
	<li class=""><a href="#tab4" data-toggle="tab" class="step">Issue New FD</a></li>
	<li class=""><a href="#tab5" data-toggle="tab" class="step">Warrant Cheque Detail</a></li>
	 <li class=""><a href="#tab6" data-toggle="tab" class="step">Recieved Cheque Detail</a></li>
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
					   ArrayList<MemberRegistrationBean> alist=dao.getAllApprovedMemberlist();
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
			<table class="table table-bordered">
			<tr>
				<td width="15%">Fd Type : <span class="red">*</span></td>
				<td width="35%">
				<select class="form-control input-medium" name='ad_type_of_fd_id' id="ad_type_of_fd_id">
					<option value=''>--Select FD Type--</option>
						<%
						 TypeOfFdDao daoa=new TypeOfFdDao();
					 	 ArrayList<TypeOfFdBean> alista=daoa.getAlltypeoffd();
					 	 if(alista.isEmpty()!=true){
					 	   for(TypeOfFdBean bean:alista){%>
					 <option value="<%=bean.getAd_type_of_fd_id()%>"><%=bean.getName() %></option>
					 <%} 
					 }%>	
						
				</select>
				<label class="error"></label>
				</td>
				<td width="15%">Fd Category <span class="red">*</span></td>
				<td width="35%">
				<select class="form-control input-medium" name='ad_fd_category_id' id="ad_fd_category_id">
					<option value=''>--Select FD Category--</option>
						<%
						 FdCategoryDao dao1 =new FdCategoryDao();
					 	 ArrayList<FdCategoryBean> alist1=dao1.getAllFdCategory();
					 	 if(alist1.isEmpty()!=true){
					 	 for(FdCategoryBean bean:alist1){%>
					      <option value="<%=bean.getAd_fd_category_id()%>"><%=bean.getName() %></option>
					    <%} 
					    }%>
				</select>
			   </td>
			</tr>
			<tr>
				<td>Fd AMT : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text" id="fd_amt"  name="fd_amt" /><label class="error"></label></td>
				<td>Opening Date : <span class="red">*</span></td>
				<td><input class="form-control input-medium date-picker" type="text" placeholder="dd/mm/yyyy" id="opening_date" name="opening_date" value="<%=new SimpleDateFormat("dd/MM/yyyy").format(session.getAttribute("openday")) %>" /><label class="error"></label>
				</td>
			</tr>
			<tr>
				<td>Period in Month's : <span class="red">*</span></td>
				<td><select class="form-control input-medium" name="time_period"  id="time_period"><option value="">--Select--</option></select>
				    <label class="error"></label></td>
				<td>Maturity Date : <span class="red">*</span></td>
				<td><input class="form-control input-medium " type="text" placeholder="dd/mm/yyyy" id="maturity_date" name="maturity_date" readonly="readonly" /><label class="error"></label>
				</td>
			</tr>
			<tr>
				<td>Intrest Rate : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text"  name="interest_rate" id="interest_rate"/><label class="error"></label></td>					
				<td>Interest Amount : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text" name="intrest_amt" id="intrest_amt" /><label class="error"></label></td>	
			</tr>
			<tr>
				<td>Maturity Amount : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text" name="maturity_amt" id="maturity_amt" /><label class="error"></label></td>
				<td>Option : <span class="red">*</span></td>
				<td><select class="form-control input-medium" id="cheque_option" name="cheque_option">
					<option value="">--Select Option--</option>
					<option value="0">FIXED</option>
					<option value="1">MIDR</option>
					<option value="3">QIDR</option>
					
				</select><label class="error"></label></td>
			</tr>
			</table>
			<div class="portlet box green" id="intrest_warrant">
	<div class="portlet-title">
	<div class="caption">Interest Warrant Details</div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	</div><!-- end portlet-title -->
	<div class="portlet-body">	
	
			<table class="table table-bordered"  >
	        
	        <tbody>
			  <tr>
				<td>Cheque No From : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text" name="cheque_no_from" id="cheque_no_from" /><label class="error"></label></td>
				<td>Cheque No To : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text" name="cheque_no_to" id="cheque_no_to" /><label class="error"></label></td>
				
			  </tr>
			  <tr>
			    <td>Branch Name : <span class="red">*</span></td>
				<td>
				
				<select class="form-control input-medium"  name="cheque_branch_name" id="cheque_branch_name" >
				 <option value=" ">--Select Branch--</option>
									<%BankBranchDao bankdao=new BankBranchDao();
									  ArrayList <BankBranchBean> banklist=bankdao.getAllBankBranchName();
									  if(banklist.isEmpty()!=true){
									  for(BankBranchBean bean:banklist){
									  %>
									  <option value="<%=bean.getBranch()%>"><%=bean.getBranch_code()+" "+bean.getBranch().trim() %></option>
									 <%} }%>
							</select><label class="error"></label>
				</td>
				<td colspan="2" align="center">
				<input type="button" id="process_warrant" name="process_warrant" value="Process Warrant" class="btn btn-md green"/> 
				</td>
			</tr>
	</tbody>
	</table>
	
	</div></div>
		</div><!-- End column -->
	</div><!-- End row -->
</div><!-- End Tab Loan Details -->
<!-- ----------------------------------------------End Issue New FD----------------------------- -->
  <div class="tab-pane fade" id="tab5">
  
 	<input type="hidden" name="totalChkInfo" id="totalChkInfo" value="1"/>
	<div id="cstm-dynamic-column"></div>
  
  </div>
  <!-- ---------------------------------------------------------------------------------------------------------------------- -->
 <div class="tab-pane fade" id="tab6">
		<table class="table table-bordered" id="tblGurntrDtlPayment">
	    <thead><tr><th>Cheque Details </th></tr></thead>
	    <tbody>
		  <tr>
			<td>Cheque No : <span class="red">*</span></td>
			<td><input class="form-control input-medium" type="text" name="cheque_no" id="cheque_no" /><label class="error"></label></td>
			<td>Cheque Date : <span class="red">*</span></td>
			<td><input class="form-control input-medium date-picker" type="text" name="cheque_date" id="cheque_date" placeholder="dd/MM/yyyy" value="<%=new SimpleDateFormat("dd/MM/yyyy").format(session.getAttribute("openday"))%>" /><label class="error"></label></td>
		  </tr>
		  <tr>
		    <td>Branch Name : <span class="red">*</span></td>
			<td colspan="3">
			
			<select class="form-control input-medium"  name="branch_name" id="branch_name" >
			 <option value="">--Select Branch--</option>
								<%BankBranchDao bankdao1=new BankBranchDao();
								  ArrayList <BankBranchBean> banklist1=bankdao1.getAllBankBranchName();
								  if(banklist1.isEmpty()!=true){
								  for(BankBranchBean bean:banklist1){
								  %>
								  <option value="<%=bean.getBranch_code()+" | "+ bean.getBranch()%>"><%=bean.getBranch_code()+" "+bean.getBranch().trim() %></option>
								 <%} }%>
						</select><label class="error"></label>
			</td>
		</tr>
	</tbody>
	</table>
</div> <!-- End Deposit / close Loan -->
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
<script type="text/javascript" src="assets/scripts/custom/form-wizard-new-fd.js"></script>
<script type="text/javascript" src="assets/scripts/custom/moment.js"></script>
<script type="text/javascript">
jQuery(function(){
	FormWizardNewFD.init();
	jQuery('#ad_member_id').select2();
	jQuery('#branch_name').select2();
	jQuery('#cheque_branch_name').select2();
	
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true,endDate : new Date('<%=session.getAttribute("openday")%>')});
	
	

});
</script>
<% String imagePath = request.getContextPath(); %>
<script type="text/javascript">
$(document).ready(function(e) {
	
	
	$('#intrest_warrant').hide();
	$("#ad_member_id").change(function(e) {
		
		loading_show();
		
		var imagePath = "<%=imagePath%>"+"/member_images/";
		var imagePhotoPath = "<%=imagePath%>"+"/Image/emp.png";
		var imageIdPath = "<%=imagePath%>"+"/Image/id.png";
		var imageSignPath = "<%=imagePath%>"+"/Image/sign.png";
		console.log(imageSignPath);
		
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
                	//console.log(data.MemberInfo.branch.bank_region.region);
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
                      $("#ajaxResponse").html("<div><b>Country code in Invalid!</b></div>");
                  }
             },
            
             //If there was no resonse from the server
             error: function(jqXHR, textStatus, errorThrown){
               console.log("Something really bad happened " + textStatus);
                   //$("#ajaxResponse").html(jqXHR.responseText);
             }
  
         });  
		 
		
 	});//end change member id
 	
 	

});//end dom

</script>
<script>
	           $(document).ready(function(e){
	        	  
	        	   $("#ad_fd_category_id").change(function(e){
	        		   //loading_show();
	            		//$("#time_period").val(0);
	        		    var ad_type_of_fd_id= $("#ad_type_of_fd_id").val();
						var ad_fd_category_id= $("#ad_fd_category_id").val();
						
					
						if(ad_type_of_fd_id!="" && ad_fd_category_id!=""){
							
						var dataString = "action=getroirate&ad_type_of_fd_id="+ad_type_of_fd_id+"&ad_fd_category_id="+ad_fd_category_id;
						 $.ajax({
				             type: "POST",
				             url: "AdFdRoi",
				             data: dataString,
				             dataType: "json",
				            
				             //if received a response from the server
				             success: function( data, textStatus, jqXHR) {
				                 //our country code was correct so we have some information to display
				                 
				                  if(data.success){
				                	
				                	  $("#time_period").html("<option value='0' >--Select--</option>");
				                	//  alert(data.FdInfo.);
				                	  $.each(data, function() { 
				                          $.each(this, function(key, value){
				                          $("#time_period").append("<option value='"+value.ad_fd_roi_id+"'>"+value.time_period+"</option>'");
				                        	  
				                          });
				                	  });
				                	  
				                	 // loading_hide();
				                  } 
				                  //display error message
				                  else {
				                	  $("#time_period").html("<option value='0' >--Select--</option>");
					                	
					                	
				                  }
				             },
				            
				             //If there was no resonse from the server
				             error: function(jqXHR, textStatus, errorThrown){
				                  console.log("Something really bad happened " + textStatus);
				                   $("#ajaxResponse").html(jqXHR.responseText);
				             }
				  
				         });
					}else{
						
						$("#time_period").html("<option value='0' >--Select--</option>");
					}

	            	});
	            	
	            	
	            	$("#ad_type_of_fd_id").change(function(e){
	            		 $("#ad_fd_category_id").change();
	            	});

	            	$("#time_period").change(function(e){
	            		loading_show();
	            		if($("#ad_type_of_fd_id").val()==""){
	            			alert("Please Select Fd Type First...!");
	            			$("#time_period").val(0);
	            			loading_hide();
	            			event.stop();
	            		}else if($("#ad_fd_category_id").val()==""){
	            			alert("Please Select FD Category First...!");
	            			$("#time_period").val(0);
	            			loading_hide();
	            			event.stop();
	            		}else if($("#fd_amt").val()==""){
	            			alert("Please Enter FD Amt First...!");
	            			$("#time_period").val(0);
	            			loading_hide();
	            			event.stop();
	            		}else if($("#opening_date").val()==""){
	            			
	            			alert("Please Select Opning Date...!");
	            			$("#time_period").val(0);
	            			loading_hide();
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
					                	  $("#interest_rate").val(intrate);
					                	  var time_period=data.FdInfo.time_period;
					                	  var frequency=data.FdInfo.compound_frequency;
					                	  var maturity_amt=parseFloat(fd_amt)*Math.pow((1+ parseFloat((parseFloat(intrate)/100)/frequency)),(time_period/12)*frequency);
					                      $("#intrest_amt").val(Math.round(parseFloat(maturity_amt-fd_amt)));
					                	  
					                	  $("#maturity_amt").val(Math.round(parseFloat(maturity_amt)));
					                  } 
					                  loading_hide();
					                 
					                  
					             },
					            
					             //If there was no resonse from the server
					             error: function(jqXHR, textStatus, errorThrown){
					                  console.log("Something really bad happened " + textStatus);
					                   $("#ajaxResponse").html(jqXHR.responseText);
					             }
					  
					         });
	            			
								 var opening_date=$("#opening_date").val();
		            			var time_period=$("#time_period").val();
		            			
		            			var dataString = "action=ad_date&opening_date="+opening_date+"&time_period="+time_period;
								 $.ajax({
						             type: "POST",
						             url: "AdFdTrx",
						             data: dataString,
						             
						             success: function( data, textStatus, jqXHR) {
						                 
						            	 $("#maturity_date").val(data);
						               // alert(data);
						                 
						                  
						             },
						            
						             //If there was no resonse from the server
						             error: function(jqXHR, textStatus, errorThrown){
						                  console.log("Something really bad happened " + textStatus);
						                   $("#ajaxResponse").html(jqXHR.responseText);
						             }
						  
						         });
							 
	            			
	            		}
	            	});
	            	
	            });
	           
	           function loading_show(){
	               $('#modelLoad').removeClass('hide').addClass('show');
	            }
	            function loading_hide(){
	              $('#modelLoad').addClass('hide').removeClass('show');
	            } 

	            $('#cheque_option').change(function(){
	            	var option=$(this).val();
	            	if(option!=0 && option!=""){
	            		$('#intrest_warrant').show();
	            	}else{
	            		$('#intrest_warrant').hide();
	            	}
	            });
	            
	        $('#process_warrant').click(function(){
	        	$('#cstm-dynamic-column').html("");
	        	
	        	var cheque_no_frm=parseInt($('#cheque_no_from').val());
				var cheque_no_to=parseInt($('#cheque_no_to').val());
				var branch=$('#cheque_branch_name').val();
				var chk_date=$('#opening_date').datepicker({dateFormat: 'dd-mm-yy'}).val();
				
				
				if(isNaN(parseInt(cheque_no_frm))){
					$('.custom-messageBox').modal('show');
					$('#custom-page-message').html("Please Enter Warrant Cheque No From..!!");
					
				}else if(isNaN(parseInt(cheque_no_to))){
					$('.custom-messageBox').modal('show');
					$('#custom-page-message').html("Please Enter Warrant Cheque No To..!!");
					
				}else if(branch==''){
					$('.custom-messageBox').modal('show');
					$('#custom-page-message').html("Please Select Warrant Cheque Branch..!!");
					
				}else{
					
					var cheque_no=cheque_no_frm;
					var time_period = 0;
	    			var countTable = 1;
	    			var option=$('#cheque_option').val();
					var interest = $("#intrest_amt").val();//12119
					var intrest_amt =interest;	
					
	    			time_period = $("#time_period option:selected").text();
	    			
	    			option = parseInt(option);
	    			time_period = parseInt(time_period);
	    			time_period = time_period/option;
	    			
	    			var count_chk=(cheque_no_to-cheque_no_frm)+1;
	    			
	    			if(count_chk!=time_period){
	    				
						//alert(count_chk);
	    				$('.custom-messageBox').modal('show');
	    				$('#custom-page-message').html("Please Enter Valid Range of Cheque..!!");
	    			}else{
	    			
	    				if(option!=0 && option!=""){
                    
					
					
					intrest_amt = (intrest_amt/time_period).toFixed(0);//1515
					var tot_chk_int=parseInt(intrest_amt)*time_period;
					var int_diff_amt=0;
					
					
					
	    				var str = "";
	    				var i;
	    				var tot_int=0;
	    				
	    				for(i=1; i<=time_period;i++){
	    					
	    					countTable = countTable+1;
	    					
	    						if(parseInt(cheque_no)<=parseInt(cheque_no_to) ){
	    							
	    							
	    							if( i==time_period){
	    								if(tot_chk_int==interest){
	    									int_diff_amt=0;
	    								}else if(tot_chk_int>interest){
	    									int_diff_amt=parseInt(tot_chk_int)-parseInt(interest);
	    									intrest_amt=parseInt(intrest_amt)-parseInt(int_diff_amt);
	    								}else if(tot_chk_int<interest){
	    									int_diff_amt=parseInt(interest)-parseInt(tot_chk_int);
	    									intrest_amt=parseInt(intrest_amt)+parseInt(int_diff_amt);
	    								}
		    						}
	    							    							
	    						chk_date=addMonth(chk_date,option);
	    						//alert(chk_date);
	    						str += '<table class="table table-bordered">'+
	    						   '<thead><tr><th colspan="3">Cheque Details '+i+'</th></tr></thead>'+
	    	 					   '<tbody>'+
	    						   '<tr>'+
	    						   '<td>Cheque No : <span class="red">*</span></td>'+
	    						   '<td><input class="form-control input-medium" type="text" name="guar_cheque_no'+i+'" id="guar_cheque_no'+i+'" value='+cheque_no+' readonly="readonly" /><label class="error"></label></td>'+
	    						   '<td>Cheque Amt : <span class="red">*</span></td>'+
	    						   '<td><input class="form-control input-medium" type="text" name="guar_cheque_amt'+i+'" value="'+intrest_amt+'" id="guar_cheque_amt'+i+'" readonly="readonly" /><label class="error"></label></td>'+
	    						   '</tr>'+
	    						   '<tr>'+
	    						   '<td>Cheque Date : <span class="red">*</span></td>'+
	    						   '<td><input class="form-control input-medium" type="text" name="guar_cheque_date'+i+'" id="cheque_date'+i+'" required placeholder="dd/MM/yyyy" value='+chk_date+' readonly="readonly" /><label class="error"></label></td>'+
	    						   '<td>Branch Name : <span class="red">*</span></td>'+
	    						   '<td><input class="form-control input-medium" type="text"  name="guar_branch_name'+i+'" id="guar_branch_name'+i+'" value='+branch+' readonly="readonly" >'+
	    						   '<label class="error"></label></td>'+
	    						   '</tr>'+
	    						   '</tbody>'+
	    						   '</table>';
	    						}
	    						
	    					cheque_no=parseInt(cheque_no)+1;   
	    				}//end for	
	    				$('#totalChkInfo').val(countTable);
	    				$('#cstm-dynamic-column').html(str);
	    				$('.custom-messageBox').modal('show');
	    				$('#custom-page-message').html("Please Continue For Next Process..!!");
	    			}else{
	    				$('#cstm-dynamic-column').html("");
	    				$('#totalChkInfo').val(1);
	    			}//end if
	    			
	    			}
				}
	    		});//end create dynamic input box according to quentity
	            
	            
	            
	    		function addMonth(date,month){
	    			  return moment(date, 'DD/MM/YYYY', true).add(month, 'months').format('DD/MM/YYYY');
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