<%@page import="Model.Dao.EmployeeDao"%>
<%@page import="Model.Bean.GradeBean"%>
<%@page import="Model.Dao.GradeDao"%>
<%@page import="Model.Bean.SocietyDepartmentBean"%>
<%@page import="Model.Dao.SocietyDepartmentDao"%>
<%@page import="Model.Bean.ReligionBean"%>
<%@page import="Model.Dao.ReligionDao"%>
<%@page import="Model.Bean.MemberStatusBean"%>
<%@page import="Model.Dao.MemberStatusDao"%>
<%@page import="Model.Bean.MemberTypeBean"%>
<%@page import="Model.Dao.MemberTypeDao"%>

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
<%@include file= "validation.html"%>

 

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
			<li><a href="#">Employee</a><i class="fa fa-angle-right"></i>Add</li>
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
<div class="caption"><i class="fa fa-reorder"></i> Add Employee Information -
<span class="step-title">Step 1 of 5</span>
</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body form">
<form id="frmEmployeeReg" action="AdEmployee?action=insert" enctype="multipart/form-data" method="post" autocomplete="off">
<div class="form-wizard">
<div class="form-body">
<ul class="nav nav-pills steps">
	<li class="active"><a href="#tab1" data-toggle="tab" class="step">Personal</a></li>
	<li ><a href="#tab2" data-toggle="tab" class="step">Contact</a></li>
	<li ><a href="#tab3" data-toggle="tab" class="step">Service</a></li>
	<li ><a href="#tab4" data-toggle="tab" class="step">Education</a></li>
	<li ><a href="#tab5" data-toggle="tab" class="step">Photos</a></li>
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
<!-- Body Starts Here -->



<!-- -----------------------------Start Tab Content------------------------------------------------- -->
	<div class="tab-pane fade active in" id="tab1">
		
  
   	<table class="table table-bordered">
					
					
					<tr >
						<td>Emp No</td>
						<td> 
							<input type="text"  name="employee_id" id="employee_id" value="<%=new EmployeeDao().getMaxEmployeeId() %>"  class="form-control input-medium" tabindex="1">
							<label class="error"></label>
						</td>
						<td>Status</td>
						<td>	<select  name="emp_status" id="emp_status" class="form-control input-medium" tabindex="2">
									<option value="Active">Active</option>
									<option value="Halted">Halted</option>
									<option value="Terminate">Terminate</option>
									<option value="Leave">Leave</option>
								 </select>
								 <label class="error"></label>
						</td>
					</tr>
					<tr>	
						<td>Type</td>
						<td>	
						<select  name="emp_category" id="emp_category"  class="form-control input-medium" tabindex="3">
									<option value="Regular">Regular</option>
									<option value="Wages">Wages</option>
									<option value="Hourly">Hourly</option>
									<option value="Temprary">Temprary</option>
						</select>
						<label class="error"></label>
								 
						</td>
						<td colspan="2">
						</td>
						</tr>
						<tr>
						<td>Salutation</td>
						
						<td><select  name="ad_salutation_id" id="ad_salutation_id" class="form-control input-medium" tabindex="4">
								 <%SalutationDao dao=new SalutationDao();
								 	ArrayList<SalutationBean> alist=dao.getAllSalutation();
								 	for(SalutationBean bean:alist){%>
								 <option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
								 <%} %>
								</select>	<label class="error"></label>
								
						<td>Name</td>		
						<td>
								<input type="text"  name="name" id="name" class="form-control input-medium" tabindex="5">
								<label class="error"></label>
						</td>
						</tr>
						
						
						<tr>
						<td >Father</td>
						
						<td>	
							<input type="text"  name="fname" id="fname" class="form-control input-medium" tabindex="6">
							<label class="error"></label>
						</td>
						
						<td >DOB</td>
						
						<td>	
							<input type="text"  name="dob" id="dob" class="form-control input-medium date-picker" tabindex="7">
							<label class="error"></label>
						</td>
					</tr>
					<tr>
						<td>Gender</td>
						<td>	<select  name="ad_gender_id" id="ad_gender_id" class="form-control input-medium " tabindex="8">
								  <%GenderDao dao1=new GenderDao();
								 	ArrayList<GenderBean> alist1=dao1.getAllGender();
								 	for(GenderBean bean:alist1){%>
								 <option value="<%=bean.getAd_gender_id()%>"><%=bean.getGender() %></option>
								 <%} %>
								
						</select>
						<label class="error"></label>
						</td>
						<td>Category</td>
						<td>	<select  name="ad_category_id" id="ad_category_id" class="form-control input-medium " tabindex="9">
								 <%CategoryDao dao2=new CategoryDao();
								 	ArrayList<CategoryBean> alist2=dao2.getAllCategory();
								 	for(CategoryBean bean:alist2){%>
								 <option value="<%=bean.getAd_category_id()%>"><%=bean.getCategory() %></option>
								 <%} %>
						</select>
						<label class="error"></label>
						</td>
					</tr>
					<tr>
						<td>Marital Status</td>
						
						<td>	<select  name="marital_status" id="marital_status"  class="form-control input-medium " tabindex="10" >
									<option value="Married">Married</option>
									<option value="UnMarried">UnMarried</option>
									
								 </select>
								<label class="error"></label> 
						</td>
						
			
						<td>Religion</td>
						
						<td>	<select  name="ad_religion_id" id="ad_religion_id" class="form-control input-medium " tabindex="11" >
								  <%ReligionDao reldao=new ReligionDao();
								 	ArrayList<ReligionBean> relalist1=reldao.getAllReligion();
								 	for(ReligionBean bean:relalist1){%>
								 <option value="<%=bean.getAd_religion_id()%>"><%=bean.getReligion() %></option>
								 <%} %>
								
						</select><label class="error"></label> 
						</td>
						</tr>
						<tr>
						<td>Blood Group</td>
							<td>
							    <select  name="blood_group" id="blood_group" class="form-control input-medium " tabindex="12">
								 <option value="A+">A+</option>
								 <option value="B+">B+</option>
								 <option value="O+">O+</option>
								 <option value="AB+">AB+</option>
								 <option value="OB+">OB+</option>
								 <option value="A-">A-</option>
								 <option value="B-">B-</option>
								 <option value="AB-">AB-</option>
								 <option value="OB-">OB-</option>
								
						     </select><label class="error"></label>
						</td>
						
						<td>Identity Marks</td>
							<td>	
							<input type="text"  name="identity_marks" id="identity_marks" class="form-control input-medium " tabindex="13">
								<label class="error"></label>	
								 
						</td>
						</tr>
						<tr>
						
						<td>Height</td>
						
						<td>	<input type="text"   name="height" id="height"  class="form-control input-medium " tabindex="14">
									
								<label class="error"></label> 
						</td>
						
						<td>Remarks</td>
						
						<td>	<input type="text"  name="remarks" id="remarks"  class="form-control input-medium " tabindex="15">
									
								 <label class="error"></label>
						</td>
						
						
						
						</tr>
						

	              
	            </table>	
  </div>
  <div class="tab-pane fade" id="tab2">
		<table class="table table-bordered">				
					
					
					<tr>
						<td >Mobile</td>
						
						<td>	<input type="text"  name="mobile" id="mobile" class="form-control input-medium " tabindex="16">
						 <label class="error"></label>
						</td>
						<td >Alt Mobile</td>
						
						<td>	<input type="text"  name="alt_mobile" id="alt_mobile" class="form-control input-medium " tabindex="17">
						 <label class="error"></label>
						</td>
						</tr>
						<tr>
						<td >Phone</td>
						
						<td>	<input type="text"  name="phone" id="phone" class="form-control input-medium " tabindex="18" >
						 <label class="error"></label>
						</td>
						<td >Email</td>
						
						<td>	<input type="text"  name="email" id="email" class="form-control input-medium " tabindex="19">
						 <label class="error"></label>
						</td>
					</tr>
					
						<tr>
						<td >Country</td>
						
						<td>	<select  name="ad_country_id" id="ad_country_id"  class="form-control input-medium " tabindex="20">
						
								 <%
								 CountryDao dao3=new CountryDao();
								 ArrayList<CountryBean> alist3=dao3.getAllCountry();
								 for(CountryBean bean:alist3){%>
									 <option value="<%=bean.getAd_country_id()%>"><%=bean.getCountry() %></option>
									 
									 <%
								 }
								 %>
								</select> <label class="error"></label>
						</td>
						<td>State</td>
						
						<td>	<select  name="ad_state_id" id="ad_state_id"  class="form-control input-medium " tabindex="21">
								 <option value="">--Select--</option>
								<%StateDao sdao=new StateDao();
								  ArrayList <StateBean> slist=sdao.getAllState();
								  if(alist2!=null){
								  for(StateBean bean:slist){%>
								  <option value="<%=bean.getAd_state_id()%>"><%=bean.getState() %></option>
									  
								 <%} }%>
						</select> <label class="error"></label>
						</td>
						</tr>
						<tr>
						<td >District</td>
						
						<td>	<select  name="ad_district_id" id="ad_district_id"  class="form-control input-medium " tabindex="22">
								 <option value="">--Select--</option>
								</select> <label class="error"></label>
						</td>
						<td >City</td>
						
						<td>	<select  name="ad_city_id" id="ad_city_id"  class="form-control input-medium " tabindex="23">
								 <option value="">--Select--</option>
								</select> <label class="error"></label>
						</td>
						</tr>
						<tr >
						<td>C_Address</td>
						
						<td>	<input type="text" name="c_address" id="c_address" class="form-control" tabindex="24">
						 <label class="error"></label>
					</td>	
						<td>P_Address</td>
						
						<td>	<input type="text"  name="p_address" id="p_address" class="form-control" tabindex="25">
						 <label class="error"></label>
								 
						
						</tr>
						<tr>
						<td >Pincode</td>
						
						<td>	<input type="text"  name="pincode" id="pincode" class="form-control input-medium " tabindex="26" >
						 	<label class="error"></label>
						</td>
						</tr>
						
						
					
	              
	            </table>	
  </div>
  <div class="tab-pane fade" id="tab3">
		<table class="table table-bordered">				
					
					
					<tr>
							 
						
						
						
						<td >Department</td>
						
						<td>	<select  name="ad_department_id" id="ad_department_id"    class="form-control input-medium " tabindex="27">
									<option value="">--Select--</option>
									<%
									SocietyDepartmentDao deptdao=new SocietyDepartmentDao();
									ArrayList<SocietyDepartmentBean> deptlist=deptdao.getAllSocietyDepartment();
									if(deptlist!=null){
										for(SocietyDepartmentBean bean:deptlist){%>
											<option value="<%=bean.getAd_society_department_id()%>"><%=bean.getDepartment() %></option>
										<%}
									}
									%>
								</select> <label class="error"></label>
						</td>
					
						<td >Designation</td>
						
						<td>	<select  name="ad_designation_id" id="ad_designation_id"     class="form-control input-medium " tabindex="28">
									<option value="">--Select--</option>
									<%
									DesignationDao desigdao=new DesignationDao();
									ArrayList<DesignationBean> desglist=desigdao.getAllDesignation();
									if(desglist!=null){
										for(DesignationBean bean:desglist){%>
											<option value="<%=bean.getAd_designation_id()%>"><%=bean.getDesignation() %></option>
										<%}
									}
									%>
								</select> <label class="error"></label>
						</td>
						</tr>
						<tr>
						<td >Grade</td>
						<td>	
							<select name="ad_grade_id" id="ad_grade_id"   class="form-control input-medium " tabindex="29">
						<option value="">--Select--</option>
						<%
									GradeDao grddao=new GradeDao();
									ArrayList<GradeBean> grdlist=grddao.getAllGrade();
									if(grdlist!=null){
										for(GradeBean bean:grdlist){%>
											<option value="<%=bean.getAd_grade_id()%>"><%=bean.getGrade_name() %></option>
										<%}
									}
									%>
						</select> <label class="error"></label>
						</td>
						<td >Week Off</td>
						
						<td>	<select  name="week_off" id="week_off"  class="form-control input-medium " tabindex="30"  >
								<option value="Sunday">Sunday</option>
								<option value="Monday">Monday</option>
								<option value="Tuesday">Tuesday</option>
								<option value="Wednesday">Wednesday</option>
								<option value="Thursday">Thursday</option>
								<option value="Friday">Friday</option>
								<option value="Saturday">Saturday</option>
								</select> <label class="error"></label>
						</td>
						</tr>
						<tr>
						<td >Appointment</td>
						
						<td>	<input type="text" name="doa" id="doa"  class="form-control input-medium date-picker" tabindex="31" > <label class="error"></label>
						</td>
						<td >Joining</td>
						
						<td>	<input type="text" name="doj" id="doj"  class="form-control input-medium date-picker" tabindex="32" > <label class="error"></label>
						</td>
						
						
					</tr>
					
					
					<tr>
						<td >Bank</td>
						
						<td>	<select  name="ad_bank_id" id="ad_bank_id"    class="form-control input-medium " tabindex="33">
									<option value="">--Select--</option>
									<%
									BankDao bankdao=new BankDao();
									ArrayList<BankBean> banklist=bankdao.getAllBank();
									if(desglist!=null){
										for(BankBean bean:banklist){%>
											<option value="<%=bean.getAd_bank_id()%>"><%=bean.getBank() %></option>
										<%}
									}
									%>
								</select> <label class="error"></label>
						</td>
						
						<td >Branch</td>
						
						<td>	<select  name="ad_branch_id" id="ad_branch_id"   class="form-control input-medium " tabindex="34">
									<option value="">--Select--</option>
									
								</select> <label class="error"></label>
						</td>
						</tr>
						<tr>
						<td >A/C No</td>
						
						<td>	<input  type="text" name="saving_ac_no" id="saving_ac_no"  class="form-control input-medium " tabindex="35" >
								 <label class="error"></label>
						</td>
						<td>PF No</td>
						
						<td>	<input type="text"  name="pf_no" id="pf_no"  class="form-control input-medium " tabindex="36" >
						 <label class="error"></label>
							 
						</td>
						</tr>
						 <tr>
						  	<td>Pan No</td>
						  	<td><input class="form-control input-medium" type="text" tabindex="37"  name="pan_no" id="pan_no" style="text-transform: uppercase;" /> <label class="error"></label></td>
						  	<td>Aadhar</td>
						  	<td><input class="form-control input-medium" type="text" name="aadhar_no" tabindex="38"  id="aadhar_no" /><label class="error"></label></td>
		  			</tr>
					<tr>
					<td >Monthly Pay</td>
						
						<td>	<input type="text"  name="monthly_pay" id="monthly_pay"  class="form-control input-medium " tabindex="39" >
						 <label class="error"></label>
						</td>
						
					
						
					</tr>
					
					
										
					
					
	              
	            </table>		
  </div>
  <div class="tab-pane fade" id="tab4">
		<table class="table table-bordered">
 <tr>
			<th width="50px"> Select</th>
			<th width="150px" >Education</th>
			<th width="150px" >Subjects</th><th>Stream</th>
			<th  width="150px" >Board/University</th>
            <th  width="150px"  >Passing Year</th>
          <!--  <th  width="150px" >Enroll No</th>-->
            <th  width="150px" >Percentage</th>
</tr>
<tr>
          	
            
            <th><input type="checkbox" id=ch1   style="cursor:pointer" /></th>
            <th align="left" > 10<sup>TH</sup></th>
            <th><input type="text" name="10_sub" id="10_sub" class="form-control input-small " style="background-color:#CCC" readonly="readonly" /> <label class="error"></label></th>
            <th><input type="text" name="10_stream" id="10_stream" class="form-control input-small " style="background-color:#CCC" readonly="readonly" /> <label class="error"></label></th>
            <th  ><input type="text" name="10_board" id="10_board" class="form-control input-small " style="background-color:#CCC" readonly="readonly" /> <label class="error"></label></th>
            <th ><input type="text" name="10_pass_year" id="10_pass_year" class="form-control input-small " style="background-color:#CCC" readonly="readonly" > <label class="error"></label></th>
           <!-- <th><input type="text" name="10_enroll" id="10_enroll" class="form-control input-small " style="background-color:#CCC" readonly="readonly" /> <label class="error"></label></th>-->
            <th><input type="text" name="10_per" id="10_per" value="0" class="form-control input-small " style="background-color:#CCC" readonly="readonly" /> <label class="error"></label>  </th>
            
</tr>
<tr>
          	<th><input type="checkbox" id=ch2   style="cursor:pointer" /></th>
            <th align="left" >12<sup>TH</sup></th>
            <td><input type="text" name="12_sub" id="12_sub" class="form-control input-small " style="background-color:#CCC" readonly="readonly" /> <label class="error"></label></td>
            <th><input type="text" name="12_stream" id="12_stream" class="form-control input-small " style="background-color:#CCC" readonly="readonly" /> <label class="error"></label></th>
            <th><input type="text" name="12_board"  id="12_board" class="form-control input-small " style="background-color:#CCC" readonly="readonly" /> <label class="error"></label></th>
            <th><input type="text" name="12_pass_year" id="12_pass_year" class="form-control input-small " style="background-color:#CCC"  readonly="readonly" > <label class="error"></label></th>
           <!-- <th><input type="text" name="12_enroll" id="12_enroll" class="form-control input-small " style="background-color:#CCC" readonly="readonly" /> <label class="error"></label></th>-->
            <th><input type="text" name="12_per"  id="12_per" value="0" class="form-control input-small " style="background-color:#CCC" readonly="readonly" /> <label class="error"></label> </th>
           
</tr>
<tr>
          	<th><input type="checkbox" id=ch3   style="cursor:pointer" /></th>
            <th align="left" >Graduation<sup></sup></th>
            <td><input type="text" name="g_sub"  id="g_sub" class="form-control input-small " style="background-color:#CCC" readonly="readonly" /> <label class="error"></label></td>
            <th><input type="text" name="g_stream" id="g_stream" class="form-control input-small " style="background-color:#CCC" readonly="readonly" /> <label class="error"></label></th>
            <th><input type="text" name="g_board" id="g_board" class="form-control input-small " style="background-color:#CCC" readonly="readonly" /> <label class="error"></label></th>
            <th><input type="text" name="g_pass_year" id="g_pass_year" class="form-control input-small " style="background-color:#CCC" readonly="readonly" > <label class="error"></label></th>
           <!-- <th><input type="text" name="g_enroll" id="g_enroll" class="form-control input-small " style="background-color:#CCC" readonly="readonly" /> <label class="error"></label></th>-->
            <th><input type="text" name="g_per" id="g_per" value="0" class="form-control input-small " style="background-color:#CCC" readonly="readonly" /> <label class="error"></label> </th>
           
</tr>
<tr>
          	<th><input type="checkbox" id=ch4   style="cursor:pointer" /></th>
            <th align="left">PG</th>
            <td><input type="text" name="pg_sub" id="pg_sub" class="form-control input-small " style="background-color:#CCC" readonly="readonly"/> <label class="error"></label></td>
            <th><input type="text" name="pg_stream" id="pg_stream" class="form-control input-small " style="background-color:#CCC" readonly="readonly"/> <label class="error"></label></th>
            <th><input type="text" name="pg_board" id="pg_board" class="form-control input-small " style="background-color:#CCC" readonly="readonly"/> <label class="error"></label></th>
            <th><input type="text" name="pg_pass_year" id="pg_pass_year" class="form-control input-small " style="background-color:#CCC" readonly="readonly"/> <label class="error"></label></th>
           <!-- <th><input type="text" name="pg_enroll" id="pg_enroll" class="form-control input-small " style="background-color:#CCC" readonly="readonly"/> <label class="error"></label></th>-->
            <th><input type="text" name="pg_per" id="pg_per" value="0" class="form-control input-small " style="background-color:#CCC" readonly="readonly"/> <label class="error"></label> </th>
          
</tr>
<tr>
          	<th><input type="checkbox" id=ch5   style="cursor:pointer" /></th>
            <th align="left">Training</th>
            <td><input type="text" name="tr_sub" id="tr_sub" class="form-control input-small " style="background-color:#CCC" readonly="readonly"/> <label class="error"></label></td>
            <th><input type="text" name="tr_stream" id="tr_stream" class="form-control input-small " style="background-color:#CCC" readonly="readonly"/> <label class="error"></label></th>
            <th><input type="text" name="tr_board" id="tr_board" class="form-control input-small " style="background-color:#CCC" readonly="readonly"/> <label class="error"></label></th>
            <th><input type="text" name="tr_pass_year" id="tr_pass_year" class="form-control input-small " style="background-color:#CCC" readonly="readonly"/> <label class="error"></label></th>
         <!--   <th><input type="text" name="tr_enroll" id="tr_enroll" class="form-control input-small " style="background-color:#CCC" readonly="readonly"/> <label class="error"></label></th>-->
            <th><input type="text" name="tr_per" id="tr_per" value="0" class="form-control input-small "  style="background-color:#CCC" readonly="readonly"/> <label class="error"></label> </th>
          
</tr>

</table>
</div>

<div class="tab-pane fade" id="tab5">
		<table class="table table-bordered">
 <tr>
  
    	
        <th  style="color:#363" align="center" width="150px" valign="top"  colspan="3">
        <table>
        	<tr>
        		<th><img height="100" width="100" id='photo_view' style="background-image:url(Image/emp.png);background-size:cover;border: 1px solid gray;" /> </th>
        	</tr>
        	<tr>
        		<th>Photo</th>
        	</tr>
        	<tr>
        	<th><input type="file" name="photo" id="photo" /></th>
        	</tr>
        </table>
        </th>
       	
        <th  style="color:#363" align="center" width="150px" valign="top"   colspan="3">
         <table>
        	<tr>
        		<th><img height="100" width="100" id="id_proof_view" style="background-image:url(Image/id.png);background-size:cover;border: 1px solid gray;" /> </th>
        	</tr>
        	<tr>
        		<th>ID Proof
        		</th>
        		</tr>
        		<tr>
        		<th><input type="file" name="id" id="id_proof" /></th>
        	</tr>
        </table>
        
        </th>
      
   
    	
        <th  style="color:#363" align="center" width="150px" valign="top" colspan="3">
        
         <table>
        	<tr>
        		<th><img height="100" width="100"  id='sign_view' style="background-image:url(Image/sign.png);background-size:cover;border: 1px solid gray;"/> </th>
        	</tr>
        	<tr>
        		<th>Sign
        		</th>
        		</tr>
        		<tr>
        		<th>
        		<input type="file" name="sign" id="sign" /></th>
        	</tr>
        </table>
        </th>
       
        
        
 </tr>
 </table>
         
  </div>
  
         </div><!--End tab-content-->	

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
			
		   </div>
		 </div>
	 </div>
</div><!--End form-actions fluid --></tbody>
</div><!-- End portlet-body -->
</div><!-- End portlet -->


<!--------------------------------------------------------- -->
</form></div></div></div><!-- End form-body -->
</div><!-- End form-wizard -->	<!-- END BORDERED TABLE PORTLET-->					
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
<!-- footer-->
 
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script type="text/javascript" src="assets/scripts/custom/form-wizard-emp-app.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>

  <%
						SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
						String date1=sdf.format((Date)session.getAttribute("openday"));
						%>
<script type="text/javascript">
$(document).ready(function(e) {
	
	FormWizardMember.init();
	
	$('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true,endDate:'<%=date1%>'});  
	$('#emp_status').select2();
	$('#emp_category').select2();
	$('#ad_category_id').select2();
	$('#ad_gender_id').select2();
	$('#ad_salutation_id').select2();
	$('#marital_status').select2();
	$('#ad_religion_id').select2();
	$('#blood_group').select2();
	$('#ad_country_id').select2();
	$('#ad_state_id').select2();
	$('#ad_district_id').select2();
	$('#ad_city_id').select2();
	$('#ad_department_id').select2();
	$('#ad_designation_id').select2();
	$('#ad_grade_id').select2();
	$('#week_off').select2();
	$('#ad_bank_id').select2();
	$('#ad_branch_id').select2();
	
	
	
	$("#ch1").click(function(e) {   
		
		if($(this).prop("checked"))
		{	
			
			$("#10_sub").css("background","none").removeAttr("readonly");
			$("#10_stream").css("background","none").removeAttr("readonly");
			$("#10_enroll").css("background","none").removeAttr("readonly");
			$("#10_per").css("background","none").removeAttr("readonly");
			$("#10_pass_year").css("background","none").removeAttr("readonly");
			$("#10_board").css("background","none").removeAttr("readonly");
		}
		else
		{
			$("#10_sub").css("background","#CCC").attr("readonly","readonly").val("");
			$("#10_stream").css("background","#CCC").attr("readonly","readonly").val("");
			$("#10_enroll").css("background","#CCC").attr("readonly","readonly").val("");
			$("#10_per").css("background","#CCC").attr("readonly","readonly").val("");
			$("#10_pass_year").css("background","#CCC").attr("readonly","readonly").val("");
			$("#10_board").css("background","#CCC").attr("readonly","readonly").val("");
		}
		
	});

	$("#ch2").click(function(e) {    
		if($(this).prop("checked"))
		{	$("#12_sub").css("background","none").removeAttr("readonly");
			$("#12_stream").css("background","none").removeAttr("readonly");
			$("#12_enroll").css("background","none").removeAttr("readonly");
			$("#12_per").css("background","none").removeAttr("readonly");
			$("#12_pass_year").css("background","none").removeAttr("readonly");
			$("#12_board").css("background","none").removeAttr("readonly");
		}
		else
		{
			$("#12_sub").css("background","#CCC").attr("readonly","readonly").val("");
			$("#12_stream").css("background","#CCC").attr("readonly","readonly").val("");
			$("#12_enroll").css("background","#CCC").attr("readonly","readonly").val("");
			$("#12_per").css("background","#CCC").attr("readonly","readonly").val("50");
			$("#12_pass_year").css("background","#CCC").attr("readonly","readonly").val("");
			$("#12_board").css("background","#CCC").attr("readonly","readonly").val("");
		}
		
	});

	$("#ch3").click(function(e) {    
		if($(this).prop("checked"))
		{	$("#g_sub").css("background","none").removeAttr("readonly");
			$("#g_stream").css("background","none").removeAttr("readonly");
			$("#g_enroll").css("background","none").removeAttr("readonly");
			$("#g_per").css("background","none").removeAttr("readonly");
			$("#g_pass_year").css("background","none").removeAttr("readonly");
			$("#g_board").css("background","none").removeAttr("readonly");
		}
		else
		{
			$("#g_sub").css("background","#CCC").attr("readonly","readonly").val("");
			$("#g_stream").css("background","#CCC").attr("readonly","readonly").val("");
			$("#g_enroll").css("background","#CCC").attr("readonly","readonly").val("");
			$("#g_per").css("background","#CCC").attr("readonly","readonly").val("50");
			$("#g_pass_year").css("background","#CCC").attr("readonly","readonly").val("");
			$("#g_board").css("background","#CCC").attr("readonly","readonly").val("");
		}
		
	});

	$("#ch4").click(function(e) {    
		if($(this).prop("checked"))
		{	$("#pg_sub").css("background","none").removeAttr("readonly");
			$("#pg_stream").css("background","none").removeAttr("readonly");
			$("#pg_enroll").css("background","none").removeAttr("readonly");
			$("#pg_per").css("background","none").removeAttr("readonly");
			$("#pg_pass_year").css("background","none").removeAttr("readonly");
			$("#pg_board").css("background","none").removeAttr("readonly");
		}
		else
		{
			$("#pg_sub").css("background","#CCC").attr("readonly","readonly").val("");
			$("#pg_stream").css("background","#CCC").attr("readonly","readonly").val("");
			$("#pg_enroll").css("background","#CCC").attr("readonly","readonly").val("");
			$("#pg_per").css("background","#CCC").attr("readonly","readonly").val("50");
			$("#pg_pass_year").css("background","#CCC").attr("readonly","readonly").val("");
			$("#pg_board").css("background","#CCC").attr("readonly","readonly").val("");
		}
		
	});
	$("#ch5").click(function(e) {    
		if($(this).prop("checked"))
		{	$("#tr_sub").css("background","none").removeAttr("readonly");
			$("#tr_stream").css("background","none").removeAttr("readonly");
			$("#tr_enroll").css("background","none").removeAttr("readonly");
			$("#tr_per").css("background","none").removeAttr("readonly");
			$("#tr_pass_year").css("background","none").removeAttr("readonly");
			$("#tr_board").css("background","none").removeAttr("readonly");
		}
		else
		{
			$("#tr_sub").css("background","#CCC").attr("readonly","readonly").val("");
			$("#tr_stream").css("background","#CCC").attr("readonly","readonly").val("");
			$("#tr_enroll").css("background","#CCC").attr("readonly","readonly").val("");
			$("#tr_per").css("background","#CCC").attr("readonly","readonly").val("50");
			$("#tr_pass_year").css("background","#CCC").attr("readonly","readonly").val("");
			$("#tr_board").css("background","#CCC").attr("readonly","readonly").val("");
		}
		
	});
	
	
	
	
	$("#photo").change(function(){
	    readURL_photo(this);
	});
	$("#id_proof").change(function(){
	    readURL_id_proof(this);
	});
	$("#sign").change(function(){
	    readURL_sign(this);
	});
	
	
	
$("#ad_state_id").change(function(e) {
	
		if($(this).val()==0)
		{
			alert("Please Select State......!!");
			 //$('#subgroup_name').html(data); 
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
				   $('#ad_district_id').html(data);
				   $('#ad_district_id').trigger("chosen:updated");
		  	} }); 
		}
		
	});

	
$("#ad_district_id").change(function(e) {
	
	if($(this).val()==0)
	{
		alert("Please Select State......!!");
		 //$('#subgroup_name').html(data); 
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
			   $('#ad_city_id').trigger("chosen:updated");
	  	} }); 
	}
	
});  

$("#ad_state_id_1").change(function(e) {
	
	if($(this).val()==0)
	{
		alert("Please Select State......!!");
		 //$('#subgroup_name').html(data); 
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
			   $('#ad_district_id_1').trigger("chosen:updated");
	  	} }); 
	}
	
});


$("#ad_district_id_1").change(function(e) {

if($(this).val()==0)
{
	alert("Please Select State......!!");
	 //$('#subgroup_name').html(data); 
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
		   $('#ad_city_id_1').html(data);
		   $('#ad_city_id_1').trigger("chosen:updated");
  	} }); 
}

});  

$("#ad_bank_id").change(function(e) {
	
	if($(this).val()==0)
	{
		alert("Please Select Bank......!!");
		 //$('#subgroup_name').html(data); 
		return false;
		
		
	}
	else
	{
    		var ad_bank_id=$(this).val();
    		//alert(group_id);
	$.ajax({
		   type: "POST",
		   url: "Ajax/read_branch_by_bank_id.jsp?ad_bank_id="+ad_bank_id,
		   async:false,
		   success: function(data)
		   {	
			  // alert(data);
			   $('#ad_branch_id').html(data);
			   $('#ad_branch_id').trigger("chosen:updated");
	  	} }); 
	}
	
});

 
});	

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
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>