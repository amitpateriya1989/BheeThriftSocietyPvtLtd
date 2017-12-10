<%@page import="Model.Bean.EmployeeBean"%>
<%@page import="Model.Dao.EmployeeDao"%>
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
<%@include file= "validation.html"%>
<link rel="stylesheet" href="css/styles.css"></link>
<link rel="stylesheet" href="css/mainstyle.css"></link>
<link rel="stylesheet" href="00/chosen.css">
<script src="TreeMenu/TreeMenu.js" type="text/javascript"></script>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
  <link rel="stylesheet" href="css/jquery-ui.css">
  <script src="js/jquery-1.10.2.js"></script>
  <script src="js/jquery-ui.js"></script>
 
  
<script type="text/javascript">
$(document).ready(function(e) {
	$(function() {
	    $( "#tabs" ).tabs();
	  });
	
	
	$("#ad_employee_id").change(function(e) {
		
		var id = $(this).val();
		$("#il_ad_employee_id").val(id);
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
                	  $("#employee_id").html(data.EmployeeInfo.employee_id);
                	  $("#father").html(data.EmployeeInfo.fname);
                	  $("#emp_category").html(data.EmployeeInfo.emp_category);
                	  $("#emp_status").html(data.EmployeeInfo.emp_status);
                	  $("#dob").html(data.EmployeeInfo.dob);
              		  $("#ad_gender_id").html(data.EmployeeInfo.gender.gender);           		   
                	  $("#mobile").html(data.EmployeeInfo.mobile);
                	  $("#phone").html(data.EmployeeInfo.phone);
                	  $("#email").html(data.EmployeeInfo.email);
                	  $("#ad_city_id").html(data.EmployeeInfo.city.city);
                	  $("#ad_country_id").html(data.EmployeeInfo.country.country);
                	  $("#ad_state_id").html(data.EmployeeInfo.state.state);
                	  $("#ad_district_id").html(data.EmployeeInfo.district.district); 
                	  $("#pincode").html(data.EmployeeInfo.pin);

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
 
       });//end ad_employeee change

	$("#guar_ad_member_id").change(function(e) {
		var id = $(this).val();
		
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
                	 //alert(data.EmployeeInfo.branch.bank_region.region);
                	  $("#guar_ad_society_id").html(data.EmployeeInfo.ad_society_no);
                	  $("#guar_pf_no").html(data.EmployeeInfo.ad_pf_no);
                	  $("#guar_name").html(data.EmployeeInfo.name);
                	  $("#guar_mobile").html(data.EmployeeInfo.address[0].mobile);
                	  $("#guar_address").html(data.EmployeeInfo.address[0].line1);
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
 	});//end guar_ad_member_id change
       
       
	$("#loan_detail").click(function(e){
		if($("#ad_employee_id").val()=="0"){
			alert("Please Select Employee Then Show Loan Detail.....");
			event.stop();
			
		}else{
			ad_employee_id=$("#ad_employee_id").val();
			var dataString = "ad_employee_id="+ad_employee_id;
			 $.ajax({
	             type: "POST",
	             url: "Ajax/staffopenloandetail.jsp",
	             data: dataString,         
	             //if received a response from the server
	             success: function( data, textStatus, jqXHR) {
	 	             	$("#show_loan_detail").html(data);
	             },
	            
	             //If there was no resonse from the server
	             error: function(jqXHR, textStatus, errorThrown){
	                  console.log("Something really bad happened " + textStatus);
	                   $("#ajaxResponse").html(jqXHR.responseText);
	             }
	  
	         });        
		}
				
	});//end  loan_detail click
 	
	$("#loan_deposit").click(function(e){
		
		if($("#ad_employee_id").val()=="0"){
			alert("Please Select Member Then Show Loan Detail.....");
			event.stop();
			
		}else{
			ad_employee_id=$("#ad_employee_id").val();
			var dataString = "ad_employee_id="+ad_employee_id;
			 $.ajax({
	             type: "POST",
	             url: "Ajax/staffopenloandetailfordeposit.jsp",
	             data: dataString,         
	            
	             //if received a response from the server
	             success: function( data, textStatus, jqXHR) {
	 	             	$("#loan_deposit_div").html(data);
	             },
	            
	             //If there was no resonse from the server
	             error: function(jqXHR, textStatus, errorThrown){
	                  console.log("Something really bad happened " + textStatus);
	                   $("#ajaxResponse").html(jqXHR.responseText);
	             }
	  
	         });        
			
		}
	});//end loan_deposit click
	

});//end dom

function membre_edit(){
	var ad_employee_id=$("#ad_employee_id").val();
	//alert(ad_employee_id);
	window.location.replace("AdMemberRegistration?action=edit&ad_employee_id="+ad_employee_id);
}	
</script>
<head>
		<title>State Bank of India</title>
</head>
	
<body class="">
	

	
	
		
<!--Main Table Starts Here-->
<table id="tblContainer" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
	<tr>
		<td>
		      <%@include file= "admin_hdr_menu.jsp"%>
      
		</td>  
	</tr>
	<tr><td colspan="2" height="5"></td></tr>
	</tbody>
</table>
		

<table id="tblContainer"  width="100%">
	<tbody><tr>
		<td> 
			<table height="100%" border="0" cellpadding="0" cellspacing="0" width="100%" style="min-height: 75vh;">
				<tbody><tr>
					<td valign="top" width="15%">
						<table id="tblSubMenu" align="right" border="0" cellpadding="3" cellspacing="0" width="95%">
							<tbody>												
								<tr>							
									<td colspan="2"> 
                               			
										<%@include file= "tree.jsp"%>					
									
									</td>
								</tr>
							</tbody>
						</table>
					</td>
                <td colspan="2" height="100%" valign="top" width="85%">

<!-- Body Starts Here -->




<table height="100%"  width="100%" style="border:1px solid black">
	<tbody>
		 
		<tr valign="top"> 	
			<td colspan="2">
			
				<div id="tabs">
  <ul>
    <li><a href="#tabs-1">Personal</a></li>
    <li><a href="#tabs-2">Contact</a></li>
   <!--  <li><a href="#tabs-3">Service</a></li> -->
    <li><a id="loan_detail" href="#tabs-4">Loan Detail</a></li>
    <li><a  href="#tabs-5">Issue Loan</a></li>
    <li><a id="loan_deposit" href="#tabs-6">Deposit/Close Loan</a></li>
  </ul>
  <div id="tabs-1">
  
    <table  cellpadding="5" cellspacing="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr valign="top">
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Personal Detail</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr >
						<td class="" >&nbsp;Name</td>
						<td class="" >:</td>
						<td colspan="4">
						<select  name="ad_employee_id" id="ad_employee_id" style="width: 380px; height: 50px" data-placeholder="Select Member" class="chosen-select"  tabindex="2">
								<option value="0">--Select Employee--</option>
								 <%EmployeeDao dao =new EmployeeDao();
								 	ArrayList<EmployeeBean> alist=dao.getEmployeeName();
								 	if(alist!=null){
								 	for(EmployeeBean bean:alist){%>
								 <option value="<%=bean.getAd_employee_id()%>"> <%=bean.getEmployee_id() %> | <%=bean.getName() %></option>
								 <%} 
								 }%>
								</select>	
								
						</td>
						</tr>
					<tr >
						
						<td>&nbsp;Emp.No</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="employee_id" id="employee_id" style="width: 170px;"></label>
								 
						</td>
						<td>&nbsp;Gender</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="ad_gender_id" id="ad_gender_id" style="width: 170px;" >
								 
						</label>
						</td>
						</tr>
						
						<tr >
						<td>&nbsp;Emp Category</td>
						<td class="" >:</td>
						<td>	<label type="text" name="emp_category" id="emp_category" style="width: 170px;" readonly="readonly">
									
						</td>
						<td>&nbsp;Status</td>
						<td class="" >:</td>
						<td>	<label type="text" name="emp_status" id="emp_status" style="width: 170px;" readonly="readonly">
									
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Father</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="father" id="father" style="width: 170px;">
						</td>
						
						<td class="" >&nbsp;DOB</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="dob" id="dob" style="width: 170px;">
						</td>
					</tr>
				
						<tr height="100px" >
  
    	
        <th  style="color:#363" align="center" width="150px" valign="top"  colspan="3">
        <table>
        	<tr>
        		<th><img height="100" width="100" id='photo_view' style="background-image:url(Image/emp.png);background-size:cover;border: 1px solid gray;" /> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>Photo</th>
        	</tr>
        	
        </table>
        </th>
       	
        <th  style="color:#363" align="center" width="150px" valign="top"   colspan="3">
         <table>
        	<tr>
        		<th><img height="100" width="100" id="id_proof_view" style="background-image:url(Image/id.png);background-size:cover;border: 1px solid gray;" /> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>ID Proof
        		</th>
        		</tr>
        		
        </table>
        
        </th>
      
   
    	
        <th  style="color:#363" align="center" width="150px" valign="top" colspan="3">
        
         <table>
        	<tr>
        		<th><img height="100" width="100"  id='sign_view' style="background-image:url(Image/sign.png);background-size:cover;border: 1px solid gray;"/> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>Sign
        		</th>
        		</tr>
        		
        </table>
        </th>
       
        
        
 </tr>
	   
			
					
					<tr>
					<td colspan="7" class=""  >&nbsp;</td>
					</tr>
	              
	            </table>	
	            
  </div>
  <div id="tabs-2">
    <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Contact Detail</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="9" style="text-align: left;font-weight: bold;font-size: 14px;" >Present Address</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
						<td class="" >&nbsp;Mobile</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="mobile" id="mobile" maxlength="10" style="width: 170px;"></label>
						</td>
						<td class="" >&nbsp;Phone</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="phone" id="phone" maxlength="10" style="width: 170px;"></label>
						</td>
						<td class="" >&nbsp;Email</td>
						<td class="" >:</td>
						<td>	<label type="email"  name="email" id="email" style="width: 170px;"></label>
						</td>
					</tr>
					
						<tr>
						<td class="" >&nbsp;Country</td>
						<td class="" >:</td>
						<td>	<label  name="ad_country_id" id="ad_country_id"  > </label>
								
						</td>
						<td>&nbsp;State</td>
						<td class="" >:</td>
						<td>	<label  name="ad_state_id" id="ad_state_id"  ></label>
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;District</td>
						<td class="" >:</td>
						<td>	<label  name="ad_district_id" id="ad_district_id" ></label>
								 
						</td>
						<td class="" >&nbsp;City</td>
						<td class="" >:</td>
						<td>	<label  name="ad_city_id" id="ad_city_id" ></label>
								 
						</td>
						<td class="" >&nbsp;Pincode</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="pincode" id="pincode" maxlength="6" style="width: 170px;">
						<input type="hidden"  name="present" id="present" value="present">
						</td>
						</tr>
						
						<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					
					<td colspan="7" class=""  >&nbsp;</td>
					</tr>
	              
	            </table>	
  </div>
 <!--  <div id="tabs-3">
 <table  cellpadding="5"  width="90%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Service Detail</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr >
						<td>&nbsp;Branch</td>
						<td class="" >:</td>
						<td colspan="4" align="left">	
						<label  name="ad_branch_id" id="ad_branch_id" >
								
						</td>
						
					
						
					
						
						
					</tr>
					<tr >
											
						<td class="" >&nbsp;Region</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="ad_bank_region_id" id="ad_bank_region_id" style="width: 170px;" readonly="readonly" >
						</td>
					
						<td>&nbsp;Code</td>
						<td class="" >:</td>
						<td>	<label type="text" name="branch_code" id="branch_code" style="width: 170px;" readonly="readonly">
								 
						</td>
						<td>&nbsp;IFSC</td>
						<td class="" >:</td>
						<td>	<label type="text" name="ifsc_code" id="ifsc_code" style="width: 170px;" readonly="readonly">
							
					</tr>
					<tr >
						<td>&nbsp;State</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="ad_bank_state_id" id="ad_bank_state_id" style="width: 170px;" readonly="readonly">
					
						</td>
						
					
						
					
						<td class="" >&nbsp;District</td>
						<td class="" >:</td>
						<td>	<label type="text" name="ad_bank_district_id" id="ad_bank_district_id" style="width: 170px;" readonly="readonly" >
								
						</td>
						
						<td class="" >&nbsp;City</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="ad_bank_city_id" id="ad_bank_city_id" readonly="readonly" style="width: 170px;" >
								
						</td>
						
					</tr>
					
					<tr>
							 
						</td>
						<td class="" >&nbsp;Pincode</td>
						<td class="" >:</td>
						<td>	<label type="text" name="bank_pincode" id="bank_pincode" readonly="readonly" style="width: 170px;">
						</td>
						
					
						<td>&nbsp;Email</td>
						<td class="" >:</td>
						<td>	<label type="text" name="email_id" id="email_id" readonly="readonly" style="width: 170px;">
							 
						</td>
					</tr>
					<tr>
						<td class="" >&nbsp;Phone</td>
						<td class="" >:</td>
						<td>	<label type="text" name="phone_no" id="phone_no" readonly="readonly" style="width: 170px;">
						</td>
						
					
						<td>&nbsp;Person</td>
						<td class="" >:</td>
						<td>	<label type="text" name="contact_person" id="contact_person" readonly="readonly" style="width: 170px;">
								 
						</td>
						<td class="" >&nbsp;Contact</td>
						<td class="" >:</td>
						<td>	<label type="text" name="contact_no" id="contact_no" readonly="readonly" style="width: 170px;">
						</td>
					</tr>
					<tr>
						<td class="" >&nbsp;Address</td>
						<td class="" >:</td>
						<td colspan="7">	<label type="text" name="address" id="address" readonly="readonly" style="width: 765px;">
						</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
							 
						</td>
						<td class="" >&nbsp;Pan No</td>
						<td class="" >:</td>
						<td>	<label type="text" name="pan_no" id="pan_no"  style="width: 170px;">
						</td>
						
					
						<td>&nbsp;Aadhar</td>
						<td class="" >:</td>
						<td>	<label type="text" name="aadhar_no" id="aadhar_no"  style="width: 170px;">
							 
						</td>
						
						<td class="" >&nbsp;Department</td>
						<td class="" >:</td>
						<td>	<label  name="ad_department_id" id="ad_department_id"  >
									
						</td>
					</tr>
					<tr>
						<td class="" >&nbsp;Designation</td>
						<td class="" >:</td>
						<td>	<label  name="ad_designation_id" id="ad_designation_id"  >
									
						</td>
						
						<td class="" >&nbsp;Level</td>
						<td class="" >:</td>
						<td>	<label  name="ad_designation_level_id" id="ad_designation_level_id" >
									
						</td>
						<td class="" >&nbsp;Type</td>
						<td class="" >:</td>
						<td>	<label  name="ad_designation_type_id" id="ad_designation_type_id"  >
									
						</td>
						
						</tr>
						<tr>
						<td class="" >&nbsp;Appointment</td>
						<td class="" >:</td>
						<td>	<label type="date" name="doa" id="doa"  style="width: 170px;">
						</td>
						<td class="" >&nbsp;Joining</td>
						<td class="" >:</td>
						<td>	<label type="date" name="doj" id="doj"  style="width: 170px;">
						</td>
						<td class="" >&nbsp;Retirement</td>
						<td class="" >:</td>
						<td>	<label type="date" name="dor" id="dor"  style="width: 170px;">
						</td>
					</tr>
					
					<tr>
						<td class="" >&nbsp;Service Duration</td>
						<td class="" >:</td>
						<td>	<label type="text" name="service_duration" id="service_duration"  style="width: 170px;">
						</td>
						<td class="" >&nbsp;A/C No</td>
						<td class="" >:</td>
						<td>	<label type="text" name="saving_ac_no" id="saving_ac_no"  style="width: 170px;">
						</td>
					
					
					</tr>
					
					<tr>
					<td colspan="9" align="center">
						<input type="button" name="next3" value="Next"/>&nbsp;&nbsp;
						<input type="reset" name="Clear"/>
					</td>
					</tr>						
					
					<tr>
					<td colspan="7" class=""  >&nbsp;</td>
					</tr>
	              
	            </table>		
  </div> -->
  <div id="tabs-4">
    
    <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr valign="top">
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Loan Detail</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><div id="show_loan_detail"></div></td>
					</tr>
				
					
					<tr>
					<td colspan="7" class=""  >&nbsp;</td>
					</tr>
	              
	            </table>  
  </div>
  <div id="tabs-5">
   <form id='frm' action="AdStaffLoanTrx?action=insert" method="post" >
   <input type="hidden" name="il_ad_employee_id" id="il_ad_employee_id" value='0' />
    <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr valign="top">
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Issue New Loan</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					
					<tr >
						<td class="" >&nbsp; Loan Type</td>
						<td class="" >:</td>
						<td>	<select name='ad_type_of_loan_id' id="ad_type_of_loan_id">
						<%
						 TypeOfLoanDao daoa=new TypeOfLoanDao();
					 	ArrayList<TypeOfLoanBean> alista=daoa.getAlltypeofloan();
					 	if(alista!=null){
					 	for(TypeOfLoanBean bean:alista){%>
					 <option value="<%=bean.getAd_type_of_loan_id()%>"><%=bean.getName() %></option>
					 <%} 
					 }%>	
						
												</select>
						<td class="" >&nbsp; Loan Category</td>
						<td class="" >:</td>
						<td>	<select name='ad_loan_category_id' id="ad_loan_category_id">
						<%
						 LoanCategoryDao dao1=new LoanCategoryDao();
					 	ArrayList<LoanCategoryBean> alist1=dao1.getAllLoanCategory();
					 	if(alist1!=null){
					 	for(LoanCategoryBean bean:alist1){%>
					 <option value="<%=bean.getAd_loan_category_id()%>"><%=bean.getName() %></option>
					 <%} 
					 }%>
						
						
						</select>
						</td>
						
						</tr>
						<script type="text/javascript">
						$(document).ready(function(e){
					
							var ad_type_of_loan_id= $("#ad_type_of_loan_id").val();
							var ad_loan_category_id= $("#ad_loan_category_id").val();
							
						
							if(parseInt(ad_type_of_loan_id)!=0 && parseInt(ad_loan_category_id)!=0){
								
							var dataString = "action=getroirate&ad_type_of_loan_id="+ad_type_of_loan_id+"&ad_loan_category_id="+ad_loan_category_id;
							 $.ajax({
					             type: "POST",
					             url: "AdLoanRoi",
					             data: dataString,
					             dataType: "json",
					            
					             //if received a response from the server
					             success: function( data, textStatus, jqXHR) {
					                 //our country code was correct so we have some information to display
					                 
					                  if(data.success){
					                	 
					             //   	 alert(data.ShareInfo.roi);
					                	 $("#int_rate").val(data.ShareInfo.roi);
					                	
					                	 $("#show_max").val(data.ShareInfo.max_limit);
					                      
					                  } 
					                  //display error message
					                  else {
					                	  $("#int_rate").val('');
						                	
						                	 $("#show_max").val("");
					                  }
					             },
					            
					             //If there was no resonse from the server
					             error: function(jqXHR, textStatus, errorThrown){
					                  console.log("Something really bad happened " + textStatus);
					                   $("#ajaxResponse").html(jqXHR.responseText);
					             }
					  
					         });
							 
							}
							
							$("#ad_loan_category_id").change(function(e){
							
								var ad_type_of_loan_id= $("#ad_type_of_loan_id").val();
								var ad_loan_category_id= $("#ad_loan_category_id").val();
								
							
								if(parseInt(ad_type_of_loan_id)!=0 && parseInt(ad_loan_category_id)!=0){
									
								var dataString = "action=getroirate&ad_type_of_loan_id="+ad_type_of_loan_id+"&ad_loan_category_id="+ad_loan_category_id;
								 $.ajax({
						             type: "POST",
						             url: "AdLoanRoi",
						             data: dataString,
						             dataType: "json",
						            
						             //if received a response from the server
						             success: function( data, textStatus, jqXHR) {
						                 //our country code was correct so we have some information to display
						                 
						                  if(data.success){
						                	 
						             //   	 alert(data.ShareInfo.roi);
						                	 $("#int_rate").val(data.ShareInfo.roi);
						                	
						                	 $("#show_max").val(data.ShareInfo.max_limit);
						                      
						                  } 
						                  //display error message
						                  else {
						                	  $("#int_rate").val('');
							                	
							                	 $("#show_max").val('');
						                  }
						             },
						            
						             //If there was no resonse from the server
						             error: function(jqXHR, textStatus, errorThrown){
						                  console.log("Something really bad happened " + textStatus);
						                   $("#ajaxResponse").html(jqXHR.responseText);
						             }
						  
						         });
								 
								}
								
							});
							$("#ad_type_of_loan_id").change(function(e){
								var ad_type_of_loan_id= $("#ad_type_of_loan_id").val();
								var ad_loan_category_id= $("#ad_loan_category_id").val();
								
							
								if(parseInt(ad_type_of_loan_id)!=0 && parseInt(ad_loan_category_id)!=0){
									
								var dataString = "action=getroirate&ad_type_of_loan_id="+ad_type_of_loan_id+"&ad_loan_category_id="+ad_loan_category_id;
								 $.ajax({
						             type: "POST",
						             url: "AdLoanRoi",
						             data: dataString,
						             dataType: "json",
						            
						             //if received a response from the server
						             success: function( data, textStatus, jqXHR) {
						                 //our country code was correct so we have some information to display
						                 
						                  if(data.success){
						                	 
						             //   	 alert(data.ShareInfo.roi);
						                	 $("#int_rate").val(data.ShareInfo.roi);
						                	
						                	 $("#show_max").val(data.ShareInfo.max_limit);
						                      
						                  } 
						                  //display error message
						                  else {
						                	  $("#int_rate").val('');
							                	
							                	 $("#show_max").val('');
						                  }
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
						
						</script>
						<tr>
						
						<td class="" >&nbsp;Loan Purpose</td>
						<td class="" >:</td>
						<td>	<select name='loan_purpose' id="'loan_purpose'">
								<option value="0" >--select--</option>
						<%
						
						 LoanPurposeDao daolp=new LoanPurposeDao();
					 	ArrayList<LoanPurposeBean> alistlp=daolp.getAllLoanPurpose();
					 	if(alistlp!=null){
					 	for(LoanPurposeBean beanlp:alistlp){%>
					 <option value="<%=beanlp.getPurpose()%>"><%=beanlp.getPurpose() %></option>
					 <%} 
					 }%>	
						
												</select>
								
						</td>
						
						
						<td class="" >&nbsp;Requested Loan Amt</td>
						<td class="" >:</td>
						<td>	<input class="amt" type="text" name="req_loan_amt" id="req_loan_amt" value=""  style="width: 170px;" required="required">
								
						</td>
						</tr>
						<tr>
						
					
					<script type="text/javascript">
					
					$(document).ready(function(e){
						$("#loan_issue_form").hide();
					
						$("#verify").click(function(e){
						
							if($("#il_ad_employee_id").val()==0){
								alert("Please Select Employee....!");
								event.stop();
						}
							
							
						if($("#req_loan_amt").val()==''){
							alert("Please Enter Loan Amt......!");
							$("#loan_issue_form").hide();
							event.stop();
						}else{
							
							if(parseFloat($("#req_loan_amt").val())>parseFloat($("#show_max").val())){
								alert("Invalid loan amount ");								
								$("#loan_issue_form").hide();
								event.stop();
							}else{
								
								var dataString = "action=loanvalidation&ad_employee_id="+$("#il_ad_employee_id").val();
								 $.ajax({
						             type: "POST",
						             url: "AdStaffLoanTrx",
						             data: dataString,
						             dataType: "json",						            
						           
						             success: function( data, textStatus, jqXHR) {
						                
						                 if(data!=0){
						                	 
						                	 alert("loan already open of This Member....! Close First Then Try ....!!");
						                	 $("#loan_issue_form").hide();
						                	 
						                	 event.stop();
						                	 
						                 }else{
						                	 
						                	 $("#loan_issue_form").show();
						                 }
						                 
						             },
						            
						             //If there was no resonse from the server
						             error: function(jqXHR, textStatus, errorThrown){
						                  console.log("Something really bad happened " + textStatus);
						                   $("#ajaxResponse").html(jqXHR.responseText);
						             }
						  
						         });        
								
								//check share
								
								
								
								
							}
							
							
							
						}
							
							
						}); 
					});
					
					</script>
						
						
						
						<td class="" >&nbsp;Max Limit Of Loan</td>
						<td class="" >:</td>
						<td>	<input id="show_max"  style="width: 170px; background: none;" readonly="readonly"/>
								
						</td>
						
						
						
						<td colspan="9" align="center">
					
						<input type="button" name="verify" id="verify" onclick="verify()" value="verify" style="width: 70px;height: 25px;"/>
						&nbsp;&nbsp;
						
						
					</td>
						
						<td>
						
						
						
						</td>
						</tr>
						
</table>
<table id="loan_issue_form"  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%; " align="center">
					

					<tr>
					<td colspan="7" class=""  ><hr> </td>
					</tr>
					<tr>
						<td class="" >&nbsp;Gross Salary</td>
						<td class="" >:</td>
						<td>	<input class="amt" type="text" name="gross_sel" id="gross_sel" value=""  style="width: 170px;" required="required">
								
						</td>
						<td class="" >&nbsp;Interest Rate</td>
						<td class="" >:</td>
						<td>	<input class="amt" type="text" name="int_rate" id="int_rate" value="" onchange=""  style="width: 170px;" required="required">
								
						</td>
						</tr>
					<tr>
						<td class="" >&nbsp;Period Month</td>
						<td class="" >:</td>
						<td>
					<select name="period_month" id="period_month" >
					<option value="0">Select</option>
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
                    </select>
							
							<script type="text/javascript">
							$(document).ready(function(e){
								$("#period_month").change(function(e){
									
									var l=parseInt($("#req_loan_amt").val());
									var p=parseInt($("#period_month").val());
									var i=parseFloat($("#int_rate").val());
									if(p!=0){
										var t=emi(l,i,p);
										$("#emi").val(t);
									}else{
										$("#emi").val(0.0);
									}
									
								});
								
							});
							
							
							</script>
							
								
						</td>
						<td class="" >&nbsp;Loan Date</td>
						<td class="" >:</td>
						<td>	
						
						<input type="date" name="loan_date" id="loan_date"  onchange="" value=""  style="width: 170px;" required="required">
								
						</td>
						</tr>
					<tr>
					
						<td class="" >&nbsp;End Date</td>
						<td class="" >:</td>
						<td>	<input type="text" name="end_date" id="end_date" value=""  style="width: 170px;" required="required">
								
						</td>
						
						
						<script type="text/javascript">
						$(document).ready(function(e){
							
							
							$("#loan_date").change(function(e){
								var loan_date=	$("#loan_date").val();
								var period_month =$("#period_month").val();
								
								if($("#period_month").val()==0){
									alert("please select month ");
									$("#loan_date").val("");
									event.stop();
								}
								if($("#loan_date").val()==''){
									alert("please select loan Date ");
									event.stop();
								}
								
								
								var dataString = "action=ad_date&loan_date="+loan_date+"&period_month="+period_month;
								 $.ajax({
						             type: "POST",
						             url: "AdLoanTrx",
						             data: dataString,
						           				            
						           
						             success: function( data, textStatus, jqXHR) {
							                
						                 if(data){
						                	
						                	 $("#end_date").val(data);
						                	
						                	 
						                 }
						                 
						             },
						            
						             //If there was no resonse from the server
						             error: function(jqXHR, textStatus, errorThrown){
						                  console.log("Something really bad happened " + textStatus);
						                   $("#ajaxResponse").html(jqXHR.responseText);
						             }
						  
						         });        
								
								
								
							});
							
					});

							
						
						
						
						</script>
						
						
						
						<td class="" >&nbsp;EMI</td>
						<td class="" >:</td>
						<td>	<input type="text" name="emi" id="emi" value=""  style="width: 170px;">
								
						</td>
						</tr>
	              
	           
	           	<%--   <tr>
	            <td colspan="6">
	            <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Guaranter Detail</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					
					<tr >
						<td>&nbsp;Name</td>
						<td class="" >:</td>
						<td>	
						<select name="guar_ad_member_id" id="guar_ad_member_id" style="width: 170px;" data-placeholder="Choose a Level..." class="chosen-select"  tabindex="2">
							<option value="0">--Select--</option>
							
							<%
							
							ArrayList<MemberRegistrationBean> memberlist=new MemberRegistrationDao().getAllMemberlist();
							if(memberlist!=null){
								for(MemberRegistrationBean bean:memberlist){%>
									<option value="<%=bean.getAd_member_id()%>"><%=bean.getName() %></option>
								<%}
							}%>
							
						</select>
								 
						</td>
						<td>&nbsp;Mem.No</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="guar_ad_society_id" id="guar_ad_society_id" style="width: 170px;">
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;PF No</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="pf_no" id="guar_pf_no" style="width: 170px;">
								 
						</td>
						
					
						<td class="" >&nbsp;Name</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="guar_name" id="guar_name" style="width: 170px;">
						</td>
						</tr><tr>
						<td class="" >&nbsp;Mobile</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="guar_mobile" id="guar_mobile" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Address</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="guar_address" id="guar_address" style="width: 170px;">
						</td>
						
						
					</tr>
					<tr>
						<td class="" >&nbsp;Cheque Qnt</td>
						<td class="" >:</td>
						<td>	<select name="chk_qnt"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option></select>
						</td>
						<td class="" >&nbsp;Cheque No Form</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="chk_no_form" id="chk_no_form" style="width: 170px;">
						</td>
						
						
					</tr>
					<tr>
						<td class="" >&nbsp;Cheque No To</td>
						<td class="">:</td>
						<td>	<input type="text"  name="chk_no_to" id="chk_no_to" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Cheque Date</td>
						<td class="" >:</td>
						<td>	<input type="date"  name="chk_date" id="chk_date" style="width: 170px;">
						</td>
						
						
					</tr>
					<tr>
						<td class="" >&nbsp;Cheque Bank Name</td>
						<td class="">:</td>
						<td>	<input type="text"  name="bank_name" id="bank_name" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Bank Code</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="bank_code" id="bank_code" style="width: 170px;">
						</td>
						
						
					</tr>
					

<tr>					
					<td colspan="9" align="center">
						<!-- <input type="Button" name="next5" value="Next" style="width: 70px;height: 25px;"/>&nbsp;&nbsp;
						<input type="reset" name="Clear" style="width: 70px;height: 25px;"/> -->
					</td>
					</tr>						
					
					
	              
	            </table>	
	            
	            
	           	</td>
	           	</tr> --%>
	           	<tr>
					<td colspan="7" class="" align="center"  >&nbsp;
					<input type="submit" value="Submit" />
					</td>
					</tr>
	           	
	           	</table>
	           	</form>
  </div>
   <div id="tabs-6">
   
   <form action="AdStaffLoanTrx?action=deposit" method="post"  >
   <div id="loan_deposit_div">
   
   
   </div>
   </form>
   
   </div>
   
   
  
</div>	
	            
			</td>
		</tr> 
	</tbody></table>
 
 			</td>
 		</tr>
 	</tbody>
 </table>

</td>
</tr></tbody>

<!-- footer-->
 

<!-- <table id="tblContainer" border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-top: 30%"> -->
	<tr><tbody>
		<td class="footerText" height="25" valign="middle">©&nbsp;Copyright Syphon Tech</td>
		
		<td class="footerText" height="25" valign="middle"><div align="right"><a href="#" onClick="window.open('/sbijava/Privacy_Statement.html','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')">Syphon</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" onclick="window.open('www.syphon.co.in','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')"> </div></td>
		
	</tbody>	
	</tr> 


	</table>
	
</body>
<script src="00/chosen.jquery.js" type="text/javascript"></script>
<script src="00/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
   function form_submit(){
    
    document.getElementById("frm1").submit();
    }
   function emi(l,i,p)
   {
	   	var r=(i/100)/12;
	 	var tempip=Math.pow((1+r), p);
	   	var emi=((l*r)*tempip)/(tempip-1);
	   	return Math.ceil(emi);
   }
</script>   
