<%@page import="Model.Bean.ShareBean"%>
<%@page import="Model.Dao.ShareDao"%>
<%@page import="java.util.Calendar"%>
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
<%
try{
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 		String from = null;
 		String to = null;
 		try {
 			Date date = (Date)session.getAttribute("openday");
 			
 			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
 			
 			Calendar cal = Calendar.getInstance();
 		    cal.setTime(date);
 		    int year = cal.get(Calendar.YEAR);
 		    
 			Date dt=sdf.parse("03/31/"+year);
 			
 			
 			if(date.after(dt)){
 			
 				Date fdate = sdf.parse("01/04/"+year);
 				from=sdf.format(fdate).toString();
 				
 				
 			}else{
 				Date tdate = sdf.parse("01/04/"+(year-1));
 				from=sdf.format(tdate).toString();
 				
 			}
 			
 			to= sdf.format(date).toString();
 			
 			
 			
 		} catch (IllegalArgumentException i) {
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
			<li><a href="#">Account </a><i class="fa fa-angle-right"></i>Loan Transaction</li>
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
<div class="caption"><i class="fa fa-reorder"></i> Loan Transaction to member -
<span class="step-title">Step 1 of 8</span>
</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<form id="frmLoanTransaction" autocomplete="off" action="AdLoanTrx?action=insert" method="post">
<input type="hidden" name="il_ad_member_id" id="il_ad_member_id" value='0' />
<!-- -----------------------------------------Start Form----------------------------------------------- -->	
<div class="form-wizard">
<div class="form-body">
<ul class="nav nav-pills steps">
	<li class="active"><a href="#tab1" data-toggle="tab" class="step">Personal</a></li>
	<li class=""><a href="#tab2" data-toggle="tab" class="step">Contact</a></li>
	<li class=""><a href="#tab3" data-toggle="tab" class="step">Service</a></li>
	<li class=""><a href="#tab4" data-toggle="tab" class="step">Loan Details</a></li>
	<li class=""><a href="#tab5" data-toggle="tab" class="step">Salary Info</a></li>
	<li class=""><a href="#tab6" data-toggle="tab" class="step">Issue Loan</a></li>
	<li class=""><a href="#tab7" data-toggle="tab" class="step">Guarantor</a></li>
	<li class=""><a href="#tab8" data-toggle="tab" class="step">Security Cheques</a></li>
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
					   if(alist.isEmpty()!=true){
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
		</div><!-- End column -->
	</div><!-- End row -->
</div><!-- End Tab Loan Details -->
<!-- ------------------------------------------------------End Loan Details----------------------------- -->
<div class="tab-pane fade" id="tab5">
	<table class="table table-bordered">
		<tr>
			<td>Net Salary : <span class="red">*</span></td>
			<td><input class="form-control input-medium" type="text" name="gross_sel" id="gross_sel" /><label class="error"></label></td>
			<td>Total Deduction : <span class="red">*</span></td>
			<td><input class="form-control input-medium" type="text" name="total_deduction" id="total_deduction" /><label class="error"></label></td>
		</tr>
	</table>
</div><!-- End Tab Salary Info -->
<!-- ------------------------------------------------------End Salary Info---------------------------->
<div class="tab-pane fade" id="tab6">
<input type="hidden" id="il_ad_member_id" name="il_ad_member_id" />
	<table class="table table-bordered">
	  <thead>
			<tr><th colspan="4">Issue New Loan</th></tr>
	 </thead>
	 <tbody>
	 	<tr>
	 		<td>Loan Type : <span class="red">*</span></td>
	 		<td>
	 			<select class="form-control input-medium" name='ad_type_of_loan_id' id="ad_type_of_loan_id">
					 <%
					 TypeOfLoanDao daoa=new TypeOfLoanDao();
					 ArrayList<TypeOfLoanBean> alista=daoa.getAlltypeofloan();
					 if(alista!=null){
					 for(TypeOfLoanBean bean:alista){%>
					 <option value="<%=bean.getAd_type_of_loan_id()%>"><%=bean.getName() %></option>
					 <%} 
					 }%>	
				</select><label class="error"></label>
	 		</td>																																																																																																																																
	 		<td>Loan Category : <span class="red">*</span></td>
	 		<td>
	 			<select class="form-control input-medium" name='ad_loan_category_id' id="ad_loan_category_id">
					<%
					 LoanCategoryDao dao1=new LoanCategoryDao();
					 ArrayList<LoanCategoryBean> alist1=dao1.getSpecificLoanCategory("member");
					 if(alist1!=null){
					 	for(LoanCategoryBean bean:alist1){%>
					 <option value="<%=bean.getAd_loan_category_id()%>"><%=bean.getName() %></option>
					 <%} 
					 }%>
				</select><label class="error"></label>
	 		</td>
	 	</tr>
	 	<tr>
	 		<td>Loan Purpose : <span class="red">*</span></td>
	 		<td>
	 			<select class="form-control input-medium" name="loan_purpose" id="loan_purpose">
					
					 <%
					 LoanPurposeDao daolp=new LoanPurposeDao();
					 ArrayList<LoanPurposeBean> alistlp=daolp.getAllLoanPurpose();
					 if(alistlp!=null){
					 for(LoanPurposeBean beanlp:alistlp){%>
					 <option value="<%=beanlp.getPurpose()%>"><%=beanlp.getPurpose() %></option>
					 <%} 
					 }%>	
				</select><label class="error"></label>
	 		</td>																																																																																																																																
	 		<td>Requested Loan Amt : <span class="red">*</span></td>
	 		<td><input class="form-control input-medium" type="text" name="req_loan_amt" id="req_loan_amt" /><label class="error"></label></td>
	 	</tr>
	 	<tr>
	 		<td>Max Limit Of Loan : <span class="red">*</span></td>
	 		<td><input class="form-control input-medium" type="text" name="show_max" readonly="readonly" id="show_max" /><label class="error"></label></td>
	 		<td colspan="2"><input class="btn btn-block red" type="button" name="verify" id="verify"  value="Verify Infromation"/></td>																																																																																																																																
	 	</tr>
	 	<tr>
	 	<td>Loan Amt : <span class="red">*</span></td>
	 		<td><input class="form-control input-medium" type="text" name="loan_amt" id="loan_amt" readonly="readonly"/><label class="error"></label></td>
	 	<td>Amt in Words : <span class="red">*</span></td>
	 		<td><input class="form-control input-large" type="text" name="loan_amt_in_words" id="loan_amt_in_words" readonly="readonly"/><label class="error"></label></td>
	 	
	 	</tr>
	 </tbody>
	 </table>
	 
	 <table class="table table-bordered" id="tblIntEmiCalculate">
	
	 <tbody>
	 <tr><th colspan="4">Share Calculation</th></tr>
	 <tr>
					<td width="15%">Allocation Date : <span class="red">*</span></td>
					<td width="35%">
					<%
						
						String date1=sdf.format((Date)session.getAttribute("openday"));
						%>
					<input class="form-control input-medium " type="text" id="date_of_allocation" name="date_of_allocation"  value="<%=date1%>" readonly="readonly" /><label class="error"></label></td>
					<td width="15%">No Of Share : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium" type="text" id="no_of_share" name="no_of_share"  /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Per Share Rate : <span class="red">*</span></td>
					<td>
					<%ShareDao sdao=new ShareDao();
						ShareBean shareBean=sdao.getShareRate();%>
					<input class="form-control input-medium" type="text" id="per_share_rate" name="per_share_rate" value="<%=shareBean.getPer_share_rate()%>" readonly="readonly" /><label class="error"></label></td>
					<td>Share Amount : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="share_amt" name="share_amt" readonly="readonly"/><label class="error"></label></td>
				</tr>
				<tr>
				<td>Amt in words : <span class="red">*</span></td>
				<td colspan="3"><input class="form-control" type="text" id="share_amt_in_words" name="share_amt_in_words" readonly="readonly"/><label class="error"></label></td>
				</tr>
				<tr>
					<td>Batch No : <span class="red">*</span></td>
					<td ><input class="form-control input-medium" type="text" id="batch_no" name="batch_no" value="Pending" readonly="readonly"/><label class="error"></label></td>
					<td>Trx Type : <span class="red">*</span></td>
					<td>
					<input type="text" readonly="readonly" class="form-control input-medium" name="vtype" id="vtype" value="Adjustment">
									
					</td>
				</tr>
				<tr>
				<td>No From : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="share_no_form" name="share_no_form" value="Pending" readonly="readonly" /><label class="error"></label></td>
					<td>No To : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="share_no_to" name="share_no_to" value="Pending" readonly="readonly" /><label class="error"></label></td>
					
				</tr>
				<tr><th colspan="4">LSF Calculation</th></tr>
				<tr>
			<td>LSF From : <span class="red">*</span></td>
			<td><input class="form-control input-medium" type="text" name="lsf_from" id="lsf_from" value"NA" readonly="readonly"/>
				<label class="error"></label>
			</td>
			<td>LSF To : <span class="red">*</span></td>
			<td><input class="form-control input-medium" type="text" name="lsf_to" id="lsf_to" value"NA" readonly="readonly"/>
				<label class="error"></label>
			</td>
			</tr>
			<tr>
			<td>LSF Rate : <span class="red">*</span></td>
			<td><input class="form-control input-medium" type="text" name="lsf_rate" id="lsf_rate" value"NA" readonly="readonly"/>
				<label class="error"></label>
			</td>
			<td>LSF Amt : <span class="red">*</span></td>
			<td><input class="form-control input-medium" type="text" name="lsf_amt" id="lsf_amt" value"NA"  readonly="readonly"/>
				<label class="error"></label>
			</td>
			</tr>
				<tr><th colspan="4">Intrest Rate & EMI Calculation</th></tr>
		<tr>
			<td>Interest Rate : <span class="red">*</span></td>
			<td><input class="form-control input-medium" type="text" name="int_rate" id="int_rate"  readonly="readonly"/><label class="error"></label></td>
			<td>Period Month : <span class="red">*</span></td>
			<td>
			<select class="form-control input-medium" name="period_month" id="period_month" >
							<option value="">--Select Loan Period--</option>
							<option value="10">10 Month</option>
							<option value="12">12 Month</option>
							<option value="18">18 Month</option>
		                    <option value="24">24 Month</option>
		                    <option value="30">30 Month</option>
		                    <option value="36">36 Month</option>
		                    <option value="42">42 Month</option>
		                    <option value="48">48 Month</option>
		                    <option value="54">54 Month</option>
		                    <option value="60">60 Month</option>
		                    <option value="66">66 Month</option>
		                    <option value="72">72 Month</option>
		                    <option value="78">78 Month</option>
		                    <option value="84">84 Month</option>
		                    <option value="90">90 Month</option>
		                    <option value="96">96 Month</option>
		                    <option value="102">102 Month</option>
		                    <option value="108">108 Month</option>
		                    <option value="114">114 Month</option>
		                    <option value="120">120 Month</option>
	          </select><label class="error"></label>
			</td>
		</tr>
		<tr>
			<td>Start Loan Date : <span class="red">*</span></td>
			<td><input class="form-control input-medium date-picker1" type="text" name="loan_date" id="loan_date"  placeholder="DD/MM/YYYY" /><label class="error"></label></td>
			<td>End Loan Date : </td>
			<td><input class="form-control input-medium" type="text" name="end_date" id="end_date" placeholder="DD/MM/YYYY" readonly="readonly" /><label class="error"></label></td>
		</tr>
		<tr>
			<td>Calculate EMI : <span class="red">*</span></td>
			<td><input class="btn btn-block red" type="button" name="calculateEmi" id="calculateEmi" value="Calculate EMI"/></td>
			<td>EMI : <span class="red">*</span></td>
			<td><div id="cstm-emi" class="has-warning"><input class="form-control input-medium" type="text" name="emi" id="emi" readonly="readonly" /></div><label class="error"></label>
			</td>
		</tr>
	</tbody>
	</table>
</div><!-- End Tab Issue Loan -->
<!-- ------------------------------------------------------End Issue Loan------------------------------>
<div class="tab-pane fade" id="tab7">
    <table class="table table-bordered" id="tblGurntrDtl">
	<thead>
		<tr>
			<th colspan="4">Guarantor Detail</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>Member : </td>
	   		<td>
	   			<select class="form-control input-large" name="witness_ad_member_id" id="witness_ad_member_id" >
							<option value="">--Select Member--</option>
							<%
							MemberRegistrationDao memberdao=new MemberRegistrationDao();
							ArrayList<MemberRegistrationBean> memberlist=memberdao.getAllMemberlist();
							if(memberlist!=null){
								for(MemberRegistrationBean bean:memberlist){%>
									<option value="<%=bean.getAd_member_id()%>"><%=bean.getAd_society_no()+" | "+bean.getName() %></option>
								<%}
							}%>
				</select><label class="error"></label>
	   		</td>
		</tr>
		<tr>
			<td>Name : <span class="red">*</span></td>
			<td><input class="form-control input-medium" type="text" name="guar_name" id="guar_name" /><label class="error"></label></td>
			<td>Mem.No :</td>
			<td><input class="form-control input-medium" type="text" name="guar_ad_society_id" id="guar_ad_society_id" /><label class="error"></label></td>
		</tr>
		<tr>
			<td>PF No : <span class="red">*</span></td>
			<td><input class="form-control input-medium" type="text" name="pf_no" id="pf_no" /><label class="error"></label></td>
			<td>Mobile : <span class="red">*</span></td>
			<td><input class="form-control input-medium" type="text" name="guar_mobile" id="guar_mobile" /><label class="error"></label></td>
		</tr>
		<tr>
			<td>Address : <span class="red">*</span></td>
			<td colspan="4"><textarea class="form-control" name="guar_address" id="guar_address"></textarea><label id="guar_address-error" class="error" for="guar_address"></label></td>
		</tr> 
	</tbody>
	</table>
	<!-- <table class="table table-bordered" id="tblIntEMiVerifySalary">
		<tr>
			<td width="32%"></td>
			<td width="32%"><input class="btn btn-block red" type="button" name="verifySalary" id="checkUserSalary" value="Verify Salary"/></td>
			<td width="32%"></td>
		</tr>
	</table> -->
</div><!-- End Tab Salary Info -->
<!-- ------------------------------------------------------End Witness Details---------------------------->
<div class="tab-pane fade" id="tab8">
	<table class="table table-bordered" id="tblWitnessChqQunt">
	 <thead><tr><th>Select Quentity First</th></tr></thead>
	 <tbody>
	 
		<tr>
			<td> Cheque Qnt : <span class="red">*</span></td>
			<td>
				<select class="form-control input-medium" name="chk_qnt" id="chk_qnt">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
					<option>6</option>
					<option>7</option>
					<option>8</option>
					<option>9</option>
					<option>10</option>
				</select><label class="error"></label>
			</td>
		</tr>
	</tbody>
	</table>
	<table class="table table-bordered" id="tblGurntrDtlPayment">
	 <thead><tr><th>Cheque Details 1</th></tr></thead>
	 <tbody>
		<tr>
			<td>Cheque No : <span class="red">*</span></td>
			<td><input class="form-control" type="text" name="guar_cheque_no1" id="guar_cheque_no1" /><label class="error"></label></td>
			<td>Bank Name : <span class="red">*</span></td>
			<td><input class="form-control" type="text" name="guar_bank_name1" id="guar_bank_name1" value="Central Bank of India" /><label class="error"></label></td>
		</tr>
		<tr>
		    <td>Branch Name : <span class="red">*</span></td>
			<td colspan="3">
			<select class="form-control"  name="guar_branch_name1" id="guar_branch_name1" >
			 <option value="">--Select Branch--</option>
								<%BankBranchDao bankdao=new BankBranchDao();
								  ArrayList <BankBranchBean> banklist=bankdao.getAllBankBranchName();
								  if(banklist.isEmpty()!=true){
								  for(BankBranchBean bean:banklist){
								  %>
								  <option value="<%=bean.getBranch_code()+" | "+ bean.getBranch()%>"><%=bean.getBranch_code()+" "+bean.getBranch().trim() %></option>
								 <%} }%>
						</select><label class="error"></label>
			</td>
		</tr>
	</tbody>
	</table>
	<input type="hidden" name="totalChkInfo" id="totalChkInfo" value="1"/>
	<div id="cstm-dynamic-column"></div>
</div><!-- End Tab Salary Info -->
<!-- ------------------------------------------------------End Cheque Details---------------------------->

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
<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
<div class="modal fade custom-messageBox1" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Information</h4>
			</div>
			<div class="modal-body">
							 <div id="custom-page-message1"></div><!-- End custom-page-message -->
			</div>
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
							<button type="button" class="btn green" id="cont">Continue</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->
<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
<%@ include file= "Layout/footer.jsp" %>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script type="text/javascript" src="assets/scripts/custom/form-wizard-loan-member.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script type="text/javascript">
jQuery(document).ready(function() {       
	FormWizardMember.init();
	$('#ad_member_id').select2();
	$('#ad_type_of_loan_id').select2();
	$('#ad_loan_category_id').select2();
	$('#period_month').select2();
	$('#loan_purpose').select2();
	
	$('#witness_ad_member_id').select2();
	$('#guar_branch_name1').select2();
	$('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true,endDate : '<%=to%>'});
	$('.date-picker1').datepicker({format: 'dd/mm/yyyy',autoclose: true,endDate : '<%=to%>'});
});
</script>
<% String imagePath = request.getContextPath(); %>
<script type="text/javascript">
jQuery(document).ready(function() {  
$("#ad_member_id").change(function(e) {
	loading_show();
	var id = $(this).val();
	$("#il_ad_member_id").val(id);
	var imagePath = "<%=imagePath%>"+"/member_images/";
	var imagePhotoPath = "<%=imagePath%>"+"/Image/emp.png";
	var imageIdPath = "<%=imagePath%>"+"/Image/id.png";
	var imageSignPath = "<%=imagePath%>"+"/Image/sign.png";
	console.log(imageSignPath);
	
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
         },error: function(jqXHR, textStatus, errorThrown){
              console.log("Something really bad happened " + textStatus);
               $("#ajaxResponse").html(jqXHR.responseText);
         }

     });//end ajax
     
	 loadLoanDetail(id);
	 //loadLoanDeposit(id);
     
	}else{
		console.log('please select valid member id');
	}
	 
});//end get information from member id

$("#witness_ad_member_id").change(function(e) {
	
	loading_show();
	var id = $(this).val();
	var mem_id=$('#ad_member_id').val();
	if(mem_id==id){
		$('#custom-page-message').html("<div class='note note-info'>Member cannot own your guarantee....!</div>");
		$('.custom-messageBox').modal('show');
		 loading_hide();
	}else{
	var dataString = "action=view&ad_member_id="+id;
	 $.ajax({
         type: "POST",
         url: "AdMemberRegistration",
         data: dataString,
         dataType: "json",
         success: function( data, textStatus, jqXHR) {
             //our country code was correct so we have some information to display
              if(data.success){
            	 //alert(data.MemberInfo.branch.bank_region.region);
            	  $("#guar_ad_society_id").val(data.MemberInfo.ad_society_no);
            	  $("#pf_no").val(data.MemberInfo.ad_pf_no);
            	  $("#guar_name").val(data.MemberInfo.name);
            	  $("#guar_mobile").val(data.MemberInfo.address[0].mobile);;
            	  $("#guar_address").html(data.MemberInfo.address[0].line1+" "+data.MemberInfo.address[0].line2+" "+data.MemberInfo.address[0].city.city+" "+data.MemberInfo.address[0].district.district+" "+data.MemberInfo.address[0].city.city);
            	  loading_hide();
              }else {
                  $("#ajaxResponse").html("<div><b>Country code in Invalid!</b></div>");
              }
         },error: function(jqXHR, textStatus, errorThrown){
              console.log("Something really bad happened " + textStatus);
               $("#ajaxResponse").html(jqXHR.responseText);
         }

     });
	}
});//end guar_ad_member_id change event


function loadLoanDetail(id){
	loading_show();
	var dataString = {"ad_member_id":id};
	 $.ajax({
        type: "POST",
        url: "Ajax/openloandetail.jsp",
        data: dataString,
        success: function(data) {
             	$("#show_loan_detail").html(data);
             	loading_hide();
        },error: function(jqXHR, textStatus, errorThrown){
             console.log("Something really bad happened " + textStatus);
              $("#ajaxResponse").html(jqXHR.responseText);
        }

    });
}//end loadLoanDetail function

function loadLoanDeposit(id){
	loading_show();
	var dataString = {"ad_member_id":id};
	 $.ajax({
        type: "POST",
        url: "Ajax/openloandetailfordeposit.jsp",
        data: dataString,
        success: function( data, textStatus, jqXHR) {
             	$("#loan_deposit_div").html(data);
             	loading_hide();
        },
        error: function(jqXHR, textStatus, errorThrown){
             console.log("Something really bad happened " + textStatus);
              $("#ajaxResponse").html(jqXHR.responseText);
        }
    }); 
}//end loadLoanDeposit function

});//end dom
</script>
<script type="text/javascript">
	$(document).ready(function(e){
		loading_show();			
		var ad_type_of_loan_id= $("#ad_type_of_loan_id").val();
		var ad_loan_category_id= $("#ad_loan_category_id").val();

		if(parseInt(ad_type_of_loan_id)!=0 && parseInt(ad_loan_category_id)!=0){
								
			 var dataString = {"action":"getroirate","ad_type_of_loan_id":ad_type_of_loan_id,"ad_loan_category_id":ad_loan_category_id};
			 $.ajax({
			   type: "POST",
			   url: "AdLoanRoi",
		       data: dataString,
			   dataType: "json",
			   success: function( data, textStatus, jqXHR) {
					  if(data.success){
					     $("#int_rate").val(data.ShareInfo.roi);
					     $("#show_max").val(data.ShareInfo.max_limit);
					  }else {
					      $("#int_rate").val('0.0');
						  $("#show_max").val("0.0");
					   }
					  loading_hide();
				},error: function(jqXHR, textStatus, errorThrown){
					console.log("Something really bad happened " + textStatus);
					$("#ajaxResponse").html(jqXHR.responseText);
				}
					  
			});//end ajax
							 
		}//end check if
							
		$("#ad_loan_category_id").change(function(e){
			loading_show();
			var ad_type_of_loan_id= $("#ad_type_of_loan_id option:selected").val();
			var ad_loan_category_id= $("#ad_loan_category_id option:selected").val();
								
			if(parseInt(ad_type_of_loan_id)!=0 && parseInt(ad_loan_category_id)!=0){
									
			var dataString = {"action":"getroirate","ad_type_of_loan_id":ad_type_of_loan_id,"ad_loan_category_id":ad_loan_category_id};
			 $.ajax({
					 type: "POST",
					 url: "AdLoanRoi",
					 data: dataString,
					 dataType: "json",
					 success: function( data, textStatus, jqXHR) {                
						if(data.success){
							$("#int_rate").val(data.ShareInfo.roi);
							$("#show_max").val(data.ShareInfo.max_limit);                      
						}else {
							 $("#int_rate").val('');	
							 $("#show_max").val('');
						}
						loading_hide();
			      },error: function(jqXHR, textStatus, errorThrown){
			        console.log("Something really bad happened " + textStatus);
			        $("#ajaxResponse").html(jqXHR.responseText);
			      }
						  
			});
								 
			}//end check if
								
		});//end ad_loan_category_id change event
							
		$("#ad_type_of_loan_id").change(function(e){
			loading_show();
			var ad_type_of_loan_id= $("#ad_type_of_loan_id option:selected").val();
			var ad_loan_category_id= $("#ad_loan_category_id option:selected").val();
			
			if(parseInt(ad_type_of_loan_id)!=0 && parseInt(ad_loan_category_id)!=0){
				
			var dataString = {"action":"getroirate","ad_type_of_loan_id":ad_type_of_loan_id,"ad_loan_category_id":ad_loan_category_id};
			$.ajax({
				type: "POST",
				url: "AdLoanRoi",
				data: dataString,
				dataType: "json",
				success: function( data, textStatus, jqXHR) {
					if(data.success){
						$("#int_rate").val(data.ShareInfo.roi);
						$("#show_max").val(data.ShareInfo.max_limit);
					}else {
						 $("#int_rate").val('');
						 $("#show_max").val('');
					}
					
					loading_hide();
			},
			error: function(jqXHR, textStatus, errorThrown){
			console.log("Something really bad happened " + textStatus);
			$("#ajaxResponse").html(jqXHR.responseText);
		 }			  
		});
								 
		}//end if
								
	});//end ad_type_of_loan_id change event

});//end dom				
</script>
<script type="text/javascript">
					
$(document).ready(function(e){
	$("#tblIntEmiCalculate").hide();
	$("#tblIntEMiVerifySalary").hide();
	$("#tblGurntrDtl").hide();
	$("#tblGurntrDtlPayment").hide();
	$("#tblWitnessChqQunt").hide();
	
	$("#verify").click(function(e){
						
	if($("#il_ad_member_id").val()==0){
		$('#custom-page-message').html("<div class='note note-info'>Please Select Member....!</div>");
		$('.custom-messageBox').modal('show');
		//event.stop();
	}						
	/* if($('#loan_purpose').val()==''){
		$('#custom-page-message1').html("<div class='note note-info'>Please Select Loan Purpose......!</div>");
		$('.custom-messageBox1').modal('show');
		$("#tblIntEmiCalculate").hide();
		$("#tblIntEMiVerifySalary").hide();
		$("#tblGurntrDtl").hide();
		$("#tblGurntrDtlPayment").hide();
		
	}	 */					
	if($("#req_loan_amt").val()==''){
		$('#custom-page-message').html("<div class='note note-info'>Please Enter Loan Amount......!</div>");
		$('#cont').hide();
		$('.custom-messageBox').modal('show');
		$("#tblIntEmiCalculate").hide();
		$("#tblIntEMiVerifySalary").hide();
		$("#tblGurntrDtl").hide();
		$("#tblGurntrDtlPayment").hide();
	}else{
							
		if(parseFloat($("#req_loan_amt").val())>parseFloat($("#show_max").val())){
			$('#custom-page-message').html("<div class='note note-warning'>Invalid Loan Amount</div>");
			$('.custom-messageBox').modal('show');	
			$('#period_month option[value=""]').attr('selected','selected');
			$("#emi").val("");
			$("#tblIntEmiCalculate").hide();
			$("#tblIntEMiVerifySalary").hide();
			$("#tblGurntrDtl").hide();
			$("#tblGurntrDtlPayment").hide();
		}else{
			
			loading_show();
			var ad_type_of_loan_id= $("#ad_type_of_loan_id option:selected").val();
			var ad_loan_category_id= $("#ad_loan_category_id option:selected").val();		
			var yes="yes";
			var loan_amt=$("#req_loan_amt").val();
			var dataString = "action=loanvalidation&ad_member_id="+$("#ad_member_id").val()+"&ad_type_of_loan_id="+ad_type_of_loan_id+"&ad_loan_category_id="+ad_loan_category_id+"&loan_amt="+loan_amt;
			$.ajax({
				type: "POST",
				url: "AdLoanTrx",
				data: dataString,
				//dataType: "json",						            
				success: function( data) {
					
					if(data!=""){
						var prv_balance=data.prv_balance;
						var loan_limit=data.loan_limit;
						var status=data.status;
						
						if(status=='Pass'){
							var sanction_amt=0.0;
							if(parseFloat(prv_balance)==0){
								sanction_amt=loan_amt;
							}else{
								sanction_amt=parseFloat(loan_limit)-parseFloat(prv_balance);
								if(loan_amt<=sanction_amt)
								{
									sanction_amt=loan_amt;
									alert("Loan Sanction Amount is "+sanction_amt);
									$("#loan_amt").val(sanction_amt);
									var amt=number2text(sanction_amt);
									$('#loan_amt_in_words').val(amt);
								}else{	
								
								alert("Loan Sanction Amount is "+sanction_amt);
								$("#loan_amt").val(sanction_amt);
								var amt=number2text(sanction_amt);
								$('#loan_amt_in_words').val(amt);
								}
							}
							 
							
							
							 
							if(sanction_amt>0){
								
								$("#loan_amt").val(sanction_amt);
								var amt=number2text(sanction_amt);
								$('#loan_amt_in_words').val(amt);
								//check share
								
							 	var dataString = "action=sharevalidation&ad_member_id="+$("#il_ad_member_id").val()+"&loan_amt="+$("#req_loan_amt").val()+"&ad_type_of_loan_id="+ad_type_of_loan_id+"&ad_loan_category_id="+ad_loan_category_id;
								
								$.ajax({
									type: "POST",
									url: "AdLoanTrx",
									data: dataString,
									dataType: "json",
									success: function( data, textStatus, jqXHR) {
										//console.log(data.shareNeed);
										if(data!=""){
											var sharerate=$("#per_share_rate").val();
											var req_share=parseInt(data.shareNeed)/parseInt(sharerate);
											$("#no_of_share").val(Math.round(req_share));
											shareCalculation();
											$('#custom-page-message1').html(data.msg);
											$('#cont').show();
											$('.custom-messageBox1').modal('show');	
											
											$("#tblIntEmiCalculate").hide();
										}else{
											if(yes=="yes"){
												$("#tblIntEmiCalculate").show();
												
												
											}
										}
									},
									error: function(jqXHR, textStatus, errorThrown){
										console.log("Something really bad happened " + textStatus);
										//$("#ajaxResponse").html(jqXHR.responseText);
									}
											  
								});
								
							}else{
								alert("Sorry Member does not have sufficient loan limit...!");
							}					
								
							
						}else{
							
							$('#custom-page-message').html(data.msg);
							$('.custom-messageBox').modal('show');	
							
						}
						
						
					}else{
						$('#custom-page-message1').html("<div class='note note-warning'>Please Try Again ....!!</div>");
						$('.custom-messageBox1').modal('show');
					}
					
					
					
					loading_show();
						                 
				},
				error: function(jqXHR, textStatus, errorThrown){
					console.log("Something really bad happened " + textStatus);
					//$("#ajaxResponse").html(jqXHR.responseText);
					loading_hide();
				}
						  
			});        
								
			        
				 			
			}
			}
		});
	
	
	$("#no_of_share").change(function(){
		shareCalculation();
	});
	$("#cont").click(function(){
		$("#tblIntEmiCalculate").show();
		$('.custom-messageBox1').modal('hide');	
	});
});				
</script>
<script type="text/javascript">
function shareCalculation (){
	loading_show();
	var _share=$("#no_of_share").val();
	
	if(_share!=0  && _share!=""){
		var no_of_share =parseFloat(_share);
	var per_share_rate=parseFloat($("#per_share_rate").val());

	if(!isNaN(no_of_share)){
		
		$("#share_amt").val(per_share_rate*no_of_share);
		var amt=number2text(per_share_rate*no_of_share)
		$("#share_amt_in_words").val(amt);
	}else{
		$("#share_amt").val('');
		no_of_share=0;
	}
	
	
	 var dataString = "action=getBatchShareno&no_of_share="+no_of_share;
	 
	 $.ajax({
         type: "POST",
         url: "AdMemberShare",
         data: dataString,
         dataType: "json",
        
         //if received a response from the server
         success: function( data, textStatus, jqXHR) {
             //our country code was correct so we have some information to display
             
              if(data.success){
            	   $("#batch_no").val(data.ShareInfo.batch_no+1);
                   $("#share_no_form").val(data.ShareInfo.share_no_to+1);
                   $("#share_no_to").val(data.ShareInfo.share_no_to+no_of_share);
                   loading_hide();
              } 
              //display error message
             /*  else {
                  $("#ajaxResponse").html("<div><b>Country code in Invalid!</b></div>");
              } */
         },
        
         //If there was no resonse from the server
         error: function(jqXHR, textStatus, errorThrown){
              console.log("Something really bad happened " + textStatus);
               $("#ajaxResponse").html(jqXHR.responseText);
         }

     });     
	}
}
</script>
<script type="text/javascript">
$(document).ready(function(e){
	/* $("#period_month").change(function(e){
		$("#loan_date").change();
	}); */
	
	$("#loan_date").change(function(e){
		loading_show();
			var loan_date    =	$(this).val();
			var period_month =  $("#period_month option:selected").val();
			
			if(period_month==""){
				$('#custom-page-message1').html("<div class='note note-info'>please select month!</div>");
				$('#cont').hide();
				$('.custom-messageBox1').modal('show');
			}else if(loan_date.trim()==''){
				$('#custom-page-message1').html("<div class='note note-info'>please select loan Date!</div>");
				$('#cont').hide();
				$('.custom-messageBox1').modal('show');
			}else{
				
			var dataString = "action=ad_date&loan_date="+loan_date+"&period_month="+period_month;
				$.ajax({
					type: "POST",
					url: "AdLoanTrx",
					data: dataString,
					success: function(data) {             
						$("#end_date").val(data);
						loading_hide();
				},		            
				error: function(jqXHR, textStatus, errorThrown){
					console.log("Something really bad happened " + textStatus);
				 }
							  
				});//end ajax  
		
	        }//end check if
			
	  });//end loan date change function
							
	});//end dom

</script>
<script type="text/javascript">
	$(document).ready(function(e){
		
		$('#calculateEmi').click(function(){
			var loanAmt     = $("#loan_amt").val().trim();
			var periodMonth = $("#period_month").val().trim();
			var intRate     = $("#int_rate").val().trim();
			
			if(loanAmt!="" && periodMonth!="" && intRate!=""){
				var totalEmi=emi(loanAmt,intRate,periodMonth);
				$('#cstm-emi').removeClass('has-warning').addClass('has-success');
				if(totalEmi!=0){
					$("#emi").val(totalEmi);
					$("#tblGurntrDtl").show();
					$("#tblIntEMiVerifySalary").show();
					checkUserSalary();
					//$("#btnContinue").removeAttr("disabled");
				}else{
					$("#emi").val("");
					$("#tblGurntrDtl").hide();
					$("#tblIntEMiVerifySalary").hide();
				}
				
			}else{
				$('#cstm-emi').removeClass('has-success').addClass('has-warning');
				$("#emi").val(0.0);
				$('#custom-page-message1').html("<div class='note note-info'>  <b>Requested Loan Amount, Interest Rate , Period Month is requird for EMI Calculataion. </b></div>");
				$('#cont').hide();
				$('.custom-messageBox1').modal('show');
			}
			
		});
		
		function emi(l,i,p){
			   	var r=(i/100)/12;
			 	var tempip=Math.pow((1+r), p);
			   	var emi=((l*r)*tempip)/(tempip-1);
			   	return Math.ceil(emi);
		}

		$('#chk_qnt').change(function(){
			var id = 0;
			var countTable = 1;
			id = parseInt($(this).val());
			if(id>1){
				var str = "";
				var i;
				for(i=2; i<=id;i++){
					countTable = countTable+1;
					
					str += '<table class="table table-bordered">'+
						   '<thead><tr><th>Cheque Details '+i+'</th></tr></thead>'+
	 					   '<tbody>'+
						   '<tr>'+
						   '<td>Cheque No : <span class="red">*</span></td>'+
						   '<td><input class="form-control" type="text" name="guar_cheque_no'+i+'" id="guar_cheque_no'+i+'" required /><label class="error"></label></td>'+
						   '<td>Bank Name : <span class="red">*</span></td>'+
						   '<td colspan="3"><input class="form-control" type="text" name="guar_bank_name'+i+'" id="guar_bank_name'+i+'" value="Central Bank of India" required /><label class="error"></label></td>'+
				 		   '</tr>'+
						   '<tr>'+
						   '<td>Branch Name : <span class="red">*</span></td>'+
						   '<td><select class="form-control"  name="guar_branch_name'+i+'" id="guar_branch_name'+i+'" required >'+
						   '<option value="0"> --Select Branch--</option>'+
							<%
							  if(banklist.isEmpty()!=true){
							  for(BankBranchBean bean:banklist){
								  out.print("'<option value="+bean.getBranch()+">"+bean.getBranch()+"</option>'+");
							  %>
							 <%} }%>
					       '</select><label class="error"></label></td>'+
						   '</tr>'+
						   '</tbody>'+
						   '</table>';
				}//end for	
				$('#totalChkInfo').val(countTable);
				$('#cstm-dynamic-column').html(str);
			}else{
				$('#cstm-dynamic-column').html("");
				$('#totalChkInfo').val(1);
			}//end if
			
		});//end create dynamic input box according to quentity
		
		function checkUserSalary(){
			var TD  = $('#total_deduction').val().trim();
			var EMI = $('#emi').val().trim();
			var GS  = $('#gross_sel').val().trim();
			var loanCategory = $("#ad_loan_category_id option:selected").val();
			var loanType = $("#ad_type_of_loan_id option:selected").val();
			var total = 0;
			
			if(TD=="" && EMI=="" && GS=="" && loanCategory=="" && loanType==""){
				$('#custom-page-message1').html("<div class='note note-info'>  <p>Total Deduction, EMI, Net Salary, Loan Category, Loan Type field is required for this process.</p>"+
				"</div>");
				$('#cont').hide();
				$('.custom-messageBox1').modal('show');
			}else{
				loading_show();
				$.ajax({
					type: "POST",
					dataType: "json",
					url: "AdLoanTrx",
					data: {"action":"verifyvalidsalary","td":TD,"emi":EMI,"gs":GS,"loanCategory":loanCategory,"loanType":loanType},
					success: function(data) { 
						console.log(data);
						if(data.message=="error"){
							$('#custom-page-message1').html("<div class='note note-info'>  <p>Total Deduction, EMI, Net Salary, Loan Category, Loan Type field is required for this process.</p>"+
							"</div>");
							$('#cont').hide();
							$('.custom-messageBox1').modal('show');
						}else{
							if(data.message=="true"){
								
								$('#custom-page-message1').html("<div class='note note-info'>  Thank you so much , you can go for next process.</div>");
								$('#cont').hide();
								$('.custom-messageBox1').modal('show');
								$("#btnContinue").removeAttr("disabled");
								$("#tblGurntrDtl").show();
								$("#tblGurntrDtlPayment").show();
								$("#tblWitnessChqQunt").show();
							}else{
								$('#custom-page-message1').html("<div class='note note-warning'>  Sorry Min Salay Percentage should be greater than or equal to Total value.<br/>"+
										"Gross Salary = "+GS+"<br/>"+
										"Total Deduction = "+TD+"<br/>"+
										"EMI = "+EMI+"<br/>"+
										"Min Salary Percentage = "+data.salaryPer+"<br/>"+
										"Total = "+data.total+"<br/>"+
										"</div>");
								$('#cont').hide();
								$('.custom-messageBox1').modal('show');
							} 
							
							
						}
						loading_hide();     
					},		            
					error: function(jqXHR, textStatus, errorThrown){
						console.log("Something really bad happened " + textStatus);
					 }
				});//end ajax

			}
			
		};//end verifySalary
		
	});//end dom
	
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
		$('.custom-messageBox1').modal('show');
		$('#cont').hide();
		$('#custom-page-message1').html("Transaction number is "+V_Trx_No);
	}
	
});

function number2text(value) {
    var fraction = Math.round(frac(value)*100);
    var f_text  = "";

    if(fraction > 0) {
        f_text = "AND "+convert_number(fraction)+" PAISE";
    }

    return convert_number(value)+" RUPEE "+f_text+" ONLY";
}

function frac(f) {
    return f % 1;
}

function convert_number(number)
{
    if ((number < 0) || (number > 999999999)) 
    { 
        return "NUMBER OUT OF RANGE!";
    }
    var Gn = Math.floor(number / 10000000);  /* Crore */ 
    number -= Gn * 10000000; 
    var kn = Math.floor(number / 100000);     /* lakhs */ 
    number -= kn * 100000; 
    var Hn = Math.floor(number / 1000);      /* thousand */ 
    number -= Hn * 1000; 
    var Dn = Math.floor(number / 100);       /* Tens (deca) */ 
    number = number % 100;               /* Ones */ 
    var tn= Math.floor(number / 10); 
    var one=Math.floor(number % 10); 
    var res = ""; 

    if (Gn>0) 
    { 
        res += (convert_number(Gn) + " CRORE"); 
    } 
    if (kn>0) 
    { 
            res += (((res=="") ? "" : " ") + 
            convert_number(kn) + " LAKH"); 
    } 
    if (Hn>0) 
    { 
        res += (((res=="") ? "" : " ") +
            convert_number(Hn) + " THOUSAND"); 
    } 

    if (Dn) 
    { 
        res += (((res=="") ? "" : " ") + 
            convert_number(Dn) + " HUNDRED"); 
    } 


    var ones = Array("", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX","SEVEN", "EIGHT", "NINE", "TEN", "ELEVEN", "TWELVE", "THIRTEEN","FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN","NINETEEN"); 
var tens = Array("", "", "TWENTY", "THIRTY", "FOURTY", "FIFTY", "SIXTY","SEVENTY", "EIGHTY", "NINETY"); 

    if (tn>0 || one>0) 
    { 
        if (!(res=="")) 
        { 
            res += " AND "; 
        } 
        if (tn < 2) 
        { 
            res += ones[tn * 10 + one]; 
        } 
        else 
        { 

            res += tens[tn];
            if (one>0) 
            { 
                res += ("-" + ones[one]); 
            } 
        } 
    }

    if (res=="")
    { 
        res = "zero"; 
    } 
    return res;
}
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>