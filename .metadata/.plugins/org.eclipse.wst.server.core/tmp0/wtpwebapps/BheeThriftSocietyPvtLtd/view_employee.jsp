<%@page import="Model.Bean.EmployeeBean"%>
<%@page import="Model.Dao.EmployeeDao"%>
<%@page import="Model.Bean.ReligionBean"%>
<%@page import="Model.Dao.ReligionDao"%>
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
			<li><a href="#">Employee</a><i class="fa fa-angle-right"></i> Search Employee</li>
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
	   		<select class="input-large" name="ad_employee_id" id="ad_employee_id">
			  <option value="">--Select Member--</option>
				 <%EmployeeDao dao=new EmployeeDao();
								 	ArrayList<EmployeeBean> alist=dao.getAllEmployeeName();
								 	if(alist.isEmpty()!=true){
								 	for(EmployeeBean bean:alist){%>
								 <option value="<%=bean.getAd_employee_id()%>"><%=bean.getName() %></option>
								 <%} 
								 }%>
				
			</select>
	   		</td>
	   	 </tr>
	     <tr >
					
						
						
						<td>Emp No</td>
						
						<td>	<input type="text"  name="employee_id" id="employee_id" readonly="readonly" class="form-control input-medium">
								 
						</td>
						<td>Status</td>
						
						<td>	<input type="text"  name="emp_status" id="emp_status" readonly="readonly" class="form-control input-medium">
								
						</td>
						
						<td>Type</td>
						
						<td>	<input type="text"  name="emp_category" id="emp_category"  readonly="readonly" class="form-control input-medium">
									
								 
						</td>
						
						
						</tr>
						
						
						<tr>
						<td>Father</td>
						
						<td>	<input type="text"  name="fname" id="fname" class="form-control input-medium" readonly="readonly">
						</td>
						
						<td>DOB</td>
						
						<td>	<input type="text"  name="dob" id="dob" class="form-control input-medium" readonly="readonly">
						</td>
					</tr>
					<tr >
						
			
						<td>Gender</td>
						
						<td>	<input type="text"  name="ad_gender_id" id="ad_gender_id" readonly="readonly" class="form-control input-medium">
								 
								
						
						</td>
						<td>Category</td>
						
						<td>	<input type="text"  name="ad_category_id" id="ad_category_id" readonly="readonly" class="form-control input-medium">
								
						</td>
						
						<td>Marital Status</td>
						
						<td>	<input type="text" name="marital_status" id="marital_status" readonly="readonly" class="form-control input-medium">
								
								 
						</td>
						</tr>
						
						<tr >
						
			
						<td>Religion</td>
						
						<td>	<input type="text"  name="ad_religion_id" id="ad_religion_id" class="form-control input-medium" readonly="readonly">
								  
						</td>
						<td>Blood Group</td>
						
						<td>	<input type="text"  name="blood_group" id="blood_group" class="form-control input-medium" readonly="readonly">
						
						</td>
						
						<td>Identity Marks</td>
						
						<td>	<input type="text"  name="identity_marks" id="identity_marks"   class="form-control input-medium" readonly="readonly">
									
								 
						</td>
						</tr>
						<tr>
						
						<td>Height</td>
						
						<td>	<input type="text"  name="height" id="height"   class="form-control input-medium" readonly="readonly">
									
								 
						</td>
						
						<td>ReMarks</td>
						
						<td>	<input type="text"  name="remarks" id="remarks"   class="form-control input-medium" readonly="readonly">
									
								 
						</td>
						
						
						
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
   <!-- BEGIN BORDERED TABLE PORTLET-->
<div class="portlet box purple">
<div class="portlet-title">
<div class="caption">Contact Detail</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<table class="table table-bordered">				
					
					
					<tr>
						<td>Mobile</td>
						
						<td>	<input type="text"  name="mobile" id="mobile" maxlength="10" class="form-control input-medium" readonly="readonly">
						</td>
						<td>Alt Mobile</td>
						
						<td>	<input type="text"  name="alt_mobile" id="alt_mobile" maxlength="10" class="form-control input-medium" readonly="readonly">
						</td>
						<td>Phone</td>
						
						<td>	<input type="text"  name="phone" id="phone" maxlength="10" class="form-control input-medium" readonly="readonly">
						</td>
						</tr>
						
					<tr >
						<td>Email</td>
						
						<td>	<input type="text"  name="email" id="email" class="form-control input-medium" readonly="readonly">
						</td>
						<td>C_Address</td>
						
						<td>	<input type="text" name="c_address" id="c_address" class="form-control input-medium" readonly="readonly">
						
						<td>P_Address</td>
						
						<td>	<input type="text"  name="p_address" id="p_address" class="form-control input-medium" readonly="readonly">
								 
						
						</tr>
						<tr>
						<td>Country</td>
						
						<td>	<input type="text"  name="ad_country_id" id="ad_country_id" class="form-control input-medium" readonly="readonly" >
								
						</td>
						<td>State</td>
						
						<td>	<input type="text"  name="ad_state_id" id="ad_state_id" class="form-control input-medium" readonly="readonly">
								 
								
						
						</td>
						
						<td>District</td>
						
						<td>	<input type="text"  name="ad_district_id" id="ad_district_id" class="form-control input-medium" readonly="readonly">
								
						</td>
						</tr>
						<tr>
						<td>City</td>
						
						<td>	<input type="text" name="ad_city_id" id="ad_city_id" class="form-control input-medium" readonly="readonly">
								 
								
						</td>
						
						<td>Pincode</td>
						
						<td>	<input type="text"  name="pincode" id="pincode"  class="form-control input-medium" readonly="readonly">
						
						</td>
						</tr>
						
	              
	            </table>	
	           </div><!-- End portlet-body -->
</div> 
	            
<!-- BEGIN BORDERED TABLE PORTLET-->
<div class="portlet box purple">
<div class="portlet-title">
<div class="caption">Educational Detail</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<table class="table table-bordered">
 <tr>
			
			<th>Education</th>
			<th>Subjects</th><th>Stream</th>
			<th >Board/University</th>
            <th  >Passing Year</th>
          <!--  <th >Enroll No</th>-->
            <th >Percentage</th>
</tr>
<tr>
          	
            
            
            <th align="left" > 10<sup>TH</sup></th>
            <th><input type="text" name="10_sub" id="10_sub"  class="form-control input-small" readonly="readonly" /></th>
            <th><input type="text" name="10_stream" id="10_stream" class="form-control input-small" readonly="readonly" /></th>
            <th  ><input type="text" name="10_board" id="10_board" class="form-control input-small" readonly="readonly" /></th>
            <th ><input type="text" name="10_pass_year" id="10_pass_year" class="form-control input-small" readonly="readonly" ></th>
           <!-- <th><input type="text" name="10_enroll" id="10_enroll" class="form-control input-small" readonly="readonly" /></th>-->
            <th><input type="text" name="10_per" id="10_per" value="0" class="form-control input-small" readonly="readonly" />  </th>
            
</tr>
<tr>
          	
            <th align="left" >12<sup>TH</sup></th>
            <td><input type="text" name="12_sub" id="12_sub" class="form-control input-small" readonly="readonly" /></td>
            <th><input type="text" name="12_stream" id="12_stream" class="form-control input-small" readonly="readonly" /></th>
            <th><input type="text" name="12_board"  id="12_board" class="form-control input-small" readonly="readonly" /></th>
            <th><input type="text" name="12_pass_year" id="12_pass_year" class="form-control input-small"  readonly="readonly" ></th>
           <!-- <th><input type="text" name="12_enroll" id="12_enroll" class="form-control input-small" readonly="readonly" /></th>-->
            <th><input type="text" name="12_per"  id="12_per" value="0" class="form-control input-small" readonly="readonly" /> </th>
           
</tr>
<tr>
          	
            <th align="left" >Graduation<sup></sup></th>
            <td><input type="text" name="g_sub"  id="g_sub" class="form-control input-small" readonly="readonly" /></td>
            <th><input type="text" name="g_stream" id="g_stream" class="form-control input-small" readonly="readonly" /></th>
            <th><input type="text" name="g_board" id="g_board" class="form-control input-small" readonly="readonly" /></th>
            <th><input type="text" name="g_pass_year" id="g_pass_year" class="form-control input-small" readonly="readonly" ></th>
           <!-- <th><input type="text" name="g_enroll" id="g_enroll" class="form-control input-small" readonly="readonly" /></th>-->
            <th><input type="text" name="g_per" id="g_per" value="0" class="form-control input-small" readonly="readonly" /> </th>
           
</tr>
<tr>
          	
            <th align="left">PG</th>
            <td><input type="text" name="pg_sub" id="pg_sub" class="form-control input-small" readonly="readonly"/></td>
            <th><input type="text" name="pg_stream" id="pg_stream" class="form-control input-small" readonly="readonly"/></th>
            <th><input type="text" name="pg_board" id="pg_board" class="form-control input-small" readonly="readonly"/></th>
            <th><input type="text" name="pg_pass_year" id="pg_pass_year" class="form-control input-small" readonly="readonly"/></th>
           <!-- <th><input type="text" name="pg_enroll" id="pg_enroll" class="form-control input-small" readonly="readonly"/></th>-->
            <th><input type="text" name="pg_per" id="pg_per" value="0" class="form-control input-small" readonly="readonly"/> </th>
          
</tr>
<tr>
          	
            <th align="left">Training</th>
            <td><input type="text" name="tr_sub" id="tr_sub" class="form-control input-small" readonly="readonly"/></td>
            <th><input type="text" name="tr_stream" id="tr_stream" class="form-control input-small" readonly="readonly"/></th>
            <th><input type="text" name="tr_board" id="tr_board" class="form-control input-small" readonly="readonly"/></th>
            <th><input type="text" name="tr_pass_year" id="tr_pass_year" class="form-control input-small" readonly="readonly"/></th>
         <!--   <th><input type="text" name="tr_enroll" id="tr_enroll" class="form-control input-small" readonly="readonly"/></th>-->
            <th><input type="text" name="tr_per" id="tr_per" value="0"  class="form-control input-small" readonly="readonly"/> </th>
          
</tr>

</table>      
 </div>
 </div> 
 <!-- BEGIN BORDERED TABLE PORTLET-->
<div class="portlet box purple">
<div class="portlet-title">
<div class="caption">Educational Detail</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<table class="table table-bordered">
					
					<tr>
							 
						
						
						
						<td>Department</td>
						
						<td>	<input type="text"  name="ad_department_id" id="ad_department_id"  class="form-control input-medium"readonly="readonly">
									
									
									
									
								
						</td>
					
						<td>Designation</td>
						
						<td>	<input type="text"  name="ad_designation_id" id="ad_designation_id"  class="form-control input-medium" readonly="readonly">
									
						</td>
						
						<td>PF No</td>
						
						<td>	<input type="text" name="pf_no" id="pf_no"  class="form-control input-medium" readonly="readonly">
							 
						</td>
						</tr>
						<tr>
						<td>Appointment</td>
						
						<td>	<input type="text" name="doa" id="doa"  class="form-control input-medium" readonly="readonly">
						</td>
						<td>Joining</td>
						
						<td>	<input type="text" name="doj" id="doj"  class="form-control input-medium" readonly="readonly">
						</td>
						
						<td>Week Off</td>
						
						<td>	<input type="text"  name="week_off" id="week_off"  class="form-control input-medium"readonly="readonly">
								
						</td>
					</tr>
					
					
					<tr>
						<td>Bank</td>
						
						<td>	<input type="text" name="ad_bank_id" id="ad_bank_id"  class="form-control input-medium" readonly="readonly">
									
						</td>
						
						<td>Branch</td>
						
						<td>	<input type="text"  name="ad_branch_id" id="ad_branch_id"  class="form-control input-medium" readonly="readonly">
									
						</td>
						
						<td>A/C No</td>
						
						<td>	<input type="text" name="saving_ac_no" id="saving_ac_no"  class="form-control input-medium" readonly="readonly">
						</td>
						</tr>
					<tr>
					<td>Grade</td>
						
						<td>	<input type="text" name="ad_grade_id" id="ad_grade_id"  class="form-control input-medium" readonly="readonly">
						</td>
					<td>Monthly Pay</td>
						
						<td>	<input type="text" name="monthly_pay" id="monthly_pay"  class="form-control input-medium" readonly="readonly">
						</td>	
					
					
					<td colspan="4" align="center">
						<input type="submit" name="Edit" id="edit"  value="Edit" class="btn btn-medium blue"/>
						<input type="button" name="Print" value="Print" class="btn btn-medium green"/>
						
					</td>
					</tr>						
					
					
	              
	            </table>		
  </div>
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
	
	$("#ad_employee_id").select2();
	
	
	$("#ad_employee_id").change(function(e) {
		var id = $(this).val();
		
		var imagePath = "<%=imagePath%>"+"/employee_images/";
		var imagePhotoPath = "<%=imagePath%>"+"/Image/emp.png";
		var imageIdPath = "<%=imagePath%>"+"/Image/id.png";
		var imageSignPath = "<%=imagePath%>"+"/Image/sign.png";
		
		var dataString = "action=view&ad_employee_id="+id;
		 $.ajax({
             type: "POST",
             url: "AdEmployee",
             data: dataString,
             dataType: "json",
            
             //if received a response from the server
             success: function( data, textStatus, jqXHR) {
                 //our country code was correct so we have some information to display
                 
                  if(data.success){
                	  
                	//alert(data.EmployeeInfo.branch.bank_region.region);
                	  $("#ad_employee_id").val(data.EmployeeInfo.ad_employee_id);
                	  $("#employee_id").val(data.EmployeeInfo.employee_id);
                	  $("#fname").val(data.EmployeeInfo.fname);
                	  $("#emp_status").val(data.EmployeeInfo.emp_status);
                	  $("#dob").val(data.EmployeeInfo.dob);
                	  $("#ad_gender_id").val(data.EmployeeInfo.gender.gender);
                	  $("#ad_category_id").val(data.EmployeeInfo.category);
                	  $("#marital_status").val(data.EmployeeInfo.marital_sts);
                	  $("#ad_religion_id").val(data.EmployeeInfo.religion.religion);
           			  $("#blood_group").val(data.EmployeeInfo.blood_group);
           			  $("#identity_marks").val(data.EmployeeInfo.id_mark);
                	  $("#height").val(data.EmployeeInfo.height);
                	  $("#emp_category").val(data.EmployeeInfo.emp_category);
                	  $("#ad_salutation").val(data.EmployeeInfo.salutation);
                      $("#remarks").val(data.EmployeeInfo.remark);
                	  $("#mobile").val(data.EmployeeInfo.mobile);
                	  $("#alt_mobile").val(data.EmployeeInfo.alt_mobile);
                	  $("#email").val(data.EmployeeInfo.email);
                	  $("#phone").val(data.EmployeeInfo.phone);
               		  $("#p_address").val(data.EmployeeInfo.p_address);
                	  $("#c_address").val(data.EmployeeInfo.c_address);
                	  $("#ad_country_id").val(data.EmployeeInfo.country.country);
                	  $("#ad_state_id").val(data.EmployeeInfo.state.state);
                	  $("#ad_city_id").val(data.EmployeeInfo.city.city);
                	  $("#ad_district_id").val(data.EmployeeInfo.district.district);
                	  $("#pincode").val(data.EmployeeInfo.pin);
                	  $("#10_stram").val(data.EmployeeInfo.stream_10);
                	  $("#10_sub").val(data.EmployeeInfo.sub_10);
                	  $("#10_board").val(data.EmployeeInfo.board_10);
                	  $("#10_pass_year").val(data.EmployeeInfo.pass_year_10);
                	  $("#10_per").val(data.EmployeeInfo.per_10);
                	  $("#12_stram").val(data.EmployeeInfo.stream_12);
                	  $("#12_sub").val(data.EmployeeInfo.sub_12);
                	  $("#12_board").val(data.EmployeeInfo.board_12);
                	  $("#12_pass_year").val(data.EmployeeInfo.pass_year_12);
                	  $("#12_per").val(data.EmployeeInfo.per_12);
                	  $("#g_stram").val(data.EmployeeInfo.stream_g);
                	  $("#g_sub").val(data.EmployeeInfo.sub_g);
                	  $("#g_board").val(data.EmployeeInfo.board_g);
                	  $("#g_pass_year").val(data.EmployeeInfo.pass_year_g);
                	  $("#g_per").val(data.EmployeeInfo.per_g);
                	  $("#pg_stram").val(data.EmployeeInfo.stream_pg);
                	  $("#pg_sub").val(data.EmployeeInfo.sub_pg);
                	  $("#pg_board").val(data.EmployeeInfo.board_pg);
                	  $("#pg_pass_year").val(data.EmployeeInfo.pass_year_pg);
                	  $("#pg_per").val(data.EmployeeInfo.per_pg);
                	  $("#tr_stram").val(data.EmployeeInfo.tr_stream);
                	  $("#tr_sub").val(data.EmployeeInfo.tr_sub);
                	  $("#tr_board_").val(data.EmployeeInfo.tr_board);
                	  $("#tr_pass_year").val(data.EmployeeInfo.tr_pass_year);
                	  $("#tr_per").val(data.EmployeeInfo.tr_per);
                	  $("#ad_department_id").val(data.EmployeeInfo.department.department);
                	  $("#ad_designation_id").val(data.EmployeeInfo.designation.designation);
                	  $("#doa").val(data.EmployeeInfo.doa);
                	  $("#doj").val(data.EmployeeInfo.doj);
                	  $("#week_off").val(data.EmployeeInfo.weakly_off);
                	  $("#ad_bank_id").val(data.EmployeeInfo.bank.bank);
                	  $("#ad_branch_id").val(data.EmployeeInfo.branch.branch);
                	  $("#saving_ac_no").val(data.EmployeeInfo.acc_no);
                	  $("#monthly_pay").val(data.EmployeeInfo.month_pay);
                	  $("#pf_no").val(data.EmployeeInfo.pf_acc_no);
                	  $("#ad_grade_id").val(data.EmployeeInfo.grade.grade_name);
 					if(data.EmployeeInfo.photo!="" && data.EmployeeInfo.photo!=="undefined" ){
                		  
                		  $("#photo_view").attr("src",imagePath+data.EmployeeInfo.photo);
                	  }else{
                		  $("#photo_view").attr("src",imagePhotoPath);
                	  }
                	  
                	  if(data.EmployeeInfo.id!=null){
                		  $("#id_proof_view").attr("src",imagePath+data.EmployeeInfo.id);
                	  }else{
                		  $("#id_proof_view").attr("src",imageIdPath);
                	  }
                	  console.log("chk image"+data.EmployeeInfo.id);
                	  
                	  if(data.EmployeeInfo.sign!=""){
                		  $("#sign_view").attr("src",imagePath+data.EmployeeInfo.sign);
                	  }else{
                		  $("#sign_view").attr("src",imageSignPath);
                	  }
                	 
                	  
                	  
                	  
                	  
                  
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
	
	
	$("#edit").click(function(e){
		var ad_employee_id= $("#ad_employee_id").val();
		window.location.href="AdEmployee?action=edit&ad_employee_id="+ad_employee_id;
		
	});
	
});

	
	
	
	
	
	
	

</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>