<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="Model.Dao.MemberShareDao"%>
<%@page import="Model.Bean.MemberShareBean"%>
<%@page import="Model.Dao.TransactionDao"%>
<%@page import="Model.Bean.TransactionBean"%>
<%@page import="Model.Dao.FdTrxDao"%>
<%@page import="Model.Bean.FdTrxBean"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="Model.Bean.LoanTrxBean"%>
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
	
		
		var id = '<%=request.getParameter("ad_member_id") %>';
		$("#il_ad_member_id").val(id);
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
                	 $("#name").html(data.MemberInfo.name);
                	  $("#ad_pf_no").html(data.MemberInfo.ad_pf_no);
                	  $("#ad_society_no").html(data.MemberInfo.ad_society_no);
                	  $("#father").html(data.MemberInfo.father);
                	  $("#husband").html(data.MemberInfo.husband);
                	  $("#dob").html(data.MemberInfo.dob);
                	  $("#ad_gender_id").html(data.MemberInfo.gender.gender);
                	  $("#ad_category_id").html(data.MemberInfo.category.category);
                	  $("#ad_member_type_id").html(data.MemberInfo.member_type.member_type);
                	  $("#ad_member_status_id").html(data.MemberInfo.member_status.member_status);
                	 /*  $("#pan_no").html(data.MemberInfo.pan_no);
                	  $("#aadhar_no").html(data.MemberInfo.aadhar_no);
                	  $("#ad_branch_id").html(data.MemberInfo.branch.branch);
                	  $("#ad_bank_region_id").html(data.MemberInfo.branch.bank_region.region);
                	  $("#branch_code").html(data.MemberInfo.branch.branch_code);
                	  $("#ifsc_code").html(data.MemberInfo.branch.ifsc_code);
                	  $("#ad_bank_state_id").html(data.MemberInfo.branch.state.state);
                	  $("#ad_bank_district_id").html(data.MemberInfo.branch.district.district);
                	  $("#ad_bank_city_id").html(data.MemberInfo.branch.city.city);
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
                	  $("#pincode").html(data.MemberInfo.address[0].pincode); */
                	 /*  
                	  $("#mobile1").html(data.MemberInfo.address[1].mobile);
                	  $("#phone1").html(data.MemberInfo.address[1].phone);
                	  $("#email1").html(data.MemberInfo.address[1].email);
                	  $("#line1_1").html(data.MemberInfo.address[1].line1);
                	  $("#line2_1").html(data.MemberInfo.address[1].line2);
                	  $("#ad_country_id_1").val(data.MemberInfo.address[1].country.country);
                	  $("#ad_state_id_1").val(data.MemberInfo.address[1].state.state);
                	  $("#ad_district_id_1").val(data.MemberInfo.address[1].district.district);
                	  $("#ad_city_id_1").val(data.MemberInfo.address[1].city.city);
                	  $("#pincode1").val(data.MemberInfo.address[1].pincode); */
                	 
                	/*   $("#nominee_name_1").html(data.MemberInfo.nominee[0].name);
                	  $("#nominee_ad_relation_id_1").html(data.MemberInfo.nominee[0].relation.relation);
                	  $("#nominee_ad_gender_id_1").html(data.MemberInfo.nominee[0].gender.gender);
                	  $("#nominee_dob_1").html(data.MemberInfo.nominee[0].dob);
                	  $("#nominee_age_1").html(data.MemberInfo.nominee[0].age);
                	  $("#nominee_photo_view_1").html(data.MemberInfo.nominee[0].photo);
                	  $("#nominee_id_proof_view_1").val(data.MemberInfo.nominee[0].id_proof);
                	  $("#nominee_sign_view_1").html(data.MemberInfo.nominee[0].sign);
                	  $("#nominee_name_2").html(data.MemberInfo.nominee[1].name);
                	  $("#nominee_ad_relation_id_2").html(data.MemberInfo.nominee[1].relation.relation);
                	  $("#nominee_ad_gender_id_2").html(data.MemberInfo.nominee[1].gender.gender);
                	  $("#nominee_dob_2").html(data.MemberInfo.nominee[1].dob);
                	  $("#nominee_age_2").html(data.MemberInfo.nominee[1].age);
                	  $("#nominee_photo_view_2").val(data.MemberInfo.nominee[1].photo);
                	  $("#nominee_id_proof_view_2").val(data.MemberInfo.nominee[1].id_proof);
                	  $("#nominee_sign_view_2").val(data.MemberInfo.nominee[1].sign);
                	   */
                      
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
    <!-- <li><a href="#tabs-2">Contact</a></li>
    <li><a href="#tabs-3">Service</a></li> -->
    <li><a id="loan_detail" href="#tabs-4">Loan Detail</a></li>
    <li><a  href="#tabs-5">FD Detail</a></li>
    <li><a id="" href="#tabs-6">Thrift Detail</a></li>
     <li><a  href="#tabs-7">Share Detail</a></li>
      <li><a  href="#tabs-8">Current Position</a></li>
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
						<label id="name"></label>
								
						</td>
						</tr>
					<tr >
						<td>&nbsp;PFNo</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="ad_pf_no" id="ad_pf_no" style="width: 170px;"></label>
								 
						</td>
						<td>&nbsp;Mem.No</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="ad_society_no" id="ad_society_no" style="width: 170px;"></label>
								 
						</td>
						</tr>
						
						<tr >
						<td>&nbsp;Type</td>
						<td class="" >:</td>
						<td>	<label type="text" name="ad_member_type_id" id="ad_member_type_id" style="width: 170px;" readonly="readonly">
									
						</td>
						<td>&nbsp;Status</td>
						<td class="" >:</td>
						<td>	<label type="text" name="ad_member_status_id" id="ad_member_status_id" style="width: 170px;" readonly="readonly">
									
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Father</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="father" id="father" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Husband</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="husband" id="husband" style="width: 170px;">
						</td>
						<td class="" >&nbsp;DOB</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="dob" id="dob" style="width: 170px;">
						</td>
					</tr>
					<tr >
						
			
						<td>&nbsp;Gender</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="ad_gender_id" id="ad_gender_id" style="width: 170px;" >
								 
						</td>
						<td>&nbsp;Category</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="ad_category_id" id="ad_category_id" style="width: 170px;" >
								 
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
  <!-- <div id="tabs-2">
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
						<td>	<label type="text"  name="mobile" id="mobile" maxlength="10" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Phone</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="phone" id="phone" maxlength="10" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Email</td>
						<td class="" >:</td>
						<td>	<label type="email"  name="email" id="email" style="width: 170px;">
						</td>
					</tr>
					<tr >
						<td>&nbsp;Lane 1</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="line1" id="line1" style="width: 170px;">
								 
						</td>
						<td>&nbsp;Lane 2</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="line2" id="line2" style="width: 170px;">
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Country</td>
						<td class="" >:</td>
						<td>	<label  name="ad_country_id" id="ad_country_id"  />
								
						</td>
						<td>&nbsp;State</td>
						<td class="" >:</td>
						<td>	<label  name="ad_state_id" id="ad_state_id"  >
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;District</td>
						<td class="" >:</td>
						<td>	<label  name="ad_district_id" id="ad_district_id" >
								 
						</td>
						<td class="" >&nbsp;City</td>
						<td class="" >:</td>
						<td>	<label  name="ad_city_id" id="ad_city_id" >
								 
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
					<tr>
					<td colspan="3" style="text-align: left;font-weight: bold;font-size: 14px;" >Permanent Address</td>
					<td colspan="6" align="right">Same As Above
					<input type="checkbox" name="same" id="same"></td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
						<td class="" >&nbsp;Mobile</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="mobile1" id="mobile1" maxlength="10" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Phone</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="phone1" id="phone1" maxlength="10" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Email</td>
						<td class="" >:</td>
						<td>	<label type="email"  name="email1" id="email1" style="width: 170px;">
						</td>
					</tr>
					<tr >
						<td>&nbsp;Lane 1</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="line1_1" id="line1_1" style="width: 170px;">
								 
						</td>
						<td>&nbsp;Lane 2</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="line2_1" id="line2_1" style="width: 170px;">
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Country</td>
						<td class="" >:</td>
						<td>	<label  name="ad_country_id_1" id="ad_country_id_1" >
								 
						</td>
						<td>&nbsp;State</td>
						<td class="" >:</td>
						<td>	<label  name="ad_state_id_1" id="ad_state_id_1" >
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;District</td>
						<td class="" >:</td>
						<td>	<label  name="ad_district_id_1" id="ad_district_id_1" >
								
						</td>
						<td class="" >&nbsp;City</td>
						<td class="" >:</td>
						<td>	<label  name="ad_city_id_1" id="ad_city_id_1">
								
						</td>
						<td class="" >&nbsp;Pincode</td>
						<td class="" >:</td>
						<td>	<label type="text"  name="pincode1" id="pincode1"  maxlength="6" style="width: 170px;">
						<input type="hidden"  name="parmanent" id="parmanent" value="parmanent">
						</td>
						</tr>
						
						<tr>					
							<td colspan="9" align="center">
								<input type="Button" name="next2" value="Next" style="width: 70px;height: 25px;"/>&nbsp;&nbsp;
								<input type="reset" name="Clear" style="width: 70px;height: 25px;"/>
							</td>
						</tr>						
					
					<tr>
					<td colspan="7" class=""  >&nbsp;</td>
					</tr>
	              
	            </table>	
  </div>
  <div id="tabs-3">
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
					<td colspan="9" class=""  ><div id="show_loan_detail">
					
					<table width="100%" border="1">
					<tr>
						<th>Sno</th><th>Loan Type</th><th>Loan Category</th><th>Loan Amt </th><th>Interest Rate</th><th>Time Period</th><th>issue Date</th>
						<th>End Date</th><th>Emi</th> <th>Purpose</th>
					
					</tr>
					
					
					
					<%
					LoanTrxBean ltb = new LoanTrxBean();
					ltb.setAd_member_id(Integer.parseInt(request.getParameter("ad_member_id")));
					 ArrayList<LoanTrxBean> listlt=new LoanTrxDao().getAllLoanTrxBymemId(ltb);
					int i=0;
					 if(listlt!=null){
						 	for(LoanTrxBean bean:listlt){%>
						 <tr onclick="showloantrx(<%=bean.getLoan_trx_id() %>)" style="cursor: pointer;" >
						 <td><%=++i %></td><td><%=bean.getLoan_type() %></td><td><%=bean.getLoan_cataegory() %></td>
						 <td><%=bean.getLoan_amt() %> </td><td><%=bean.getIntrest_rate() %></td><td><%=bean.getPeriod_month() %></td>
						 <td><%=bean.getissue_date() %></td>
						<td><%=bean.getEnd_date() %></td><td><%=bean.getEmi() %></td> <td><%=bean.getLoan_purpose() %></td>
						 </tr>
						 <%} 
						 }%>	
					</table>
					
					
					
					
					
					</div></td>
					
					</tr>
										
				
				
					
					<tr>
					<td colspan="7" class=""  >&nbsp;</td>
					</tr>
	              
	            </table>  
  </div>
  <div id="tabs-5">
   
    <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="9">
						<table width="100%" border="1">
					<tr>
						<th>Sno</th><th>FD No</th><th>FD Amt</th><th>Opening Date </th><th>Maturity Date</th><th>Remark</th>
					
					</tr>
					
					
					
					<%
					FdTrxBean fdb = new FdTrxBean();
					fdb.setAd_member_id(Integer.parseInt(request.getParameter("ad_member_id")));
					 ArrayList<FdTrxBean> listfd=new FdTrxDao().getFdTrxByMemId(fdb);
					 i=0;
					 if(listfd!=null){
						 	for(FdTrxBean bean:listfd){%>
						 <tr>
						 <td><%=++i %></td><td><%=bean.getFd_no() %></td><td><%=bean.getFd_amt() %></td>
						 <td><%=bean.getOpening_date() %> </td><td><%=bean.getMaturity_date()%></td><td></td>
						 
						 </tr>
						 <%} 
						 }%>	
					</table>
					
					
					</td>
					
					
					
					</tr>
						           
	           		
	           	
	           	</table>
	           
  </div>
   <div id="tabs-6">
   
   <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="9">
						<table width="100%" border="1">
					<tr>
						<th>Sno</th><th>Trx Date</th><th>Cr amt</th><th>Dr amt </th>
					
					</tr>
					
					
					
					<%
					
					
					 ArrayList<TransactionBean> listtrx=new TransactionDao().getAllMemberTransactionByMemIdAccId(Integer.parseInt(request.getParameter("ad_member_id")),3);
					 i=0;
					 if(listtrx!=null){
						 	for(TransactionBean bean:listtrx){%>
						 <tr>
						 <td><%=++i %></td><td><%=bean.getTrx_date() %></td><td><%=bean.getCramt()%></td>
						 <td><%=bean.getDramt() %> </td>
						 
						 </tr>
						 <%} 
						 }%>	
					</table>
					
					
					</td>
					
					
					
					</tr>
						           
	           		
	           	
	           	</table>
	           
   
   
   </div>
   <div id="tabs-7">
   
   <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="9">
						<table width="100%" border="1">
					<tr>
						<th>Sno</th><th>Date of Allocation</th><th>Batch No</th><th>Share No Form</th><th>Share No To </th><th>Qnt Of Share</th><th>Share Amt</th>
					
					</tr>
					
					
					
					<%
					
					//(ad_mem_id);
					 ArrayList<MemberShareBean> listms=new MemberShareDao().getMemberShareByMemberId(Integer.parseInt(request.getParameter("ad_member_id")));
					 i=0;
					 if(listms!=null){
						 	for(MemberShareBean bean:listms){%>
						 <tr>
						 <td><%=++i %></td><td></td><%=bean.getDate_of_allocation() %></td><td><%=bean.getBatch_no() %></td><td><%=bean.getShare_no_form()%></td>
						 <td><%=bean.getShare_no_to() %> </td><td><%=bean.getQnt_of_share() %> </td><td><%=bean.getShare_amt() %> </td>
						 
						 </tr>
						 <%} 
						 }%>	
					</table>
					
					
					</td>
					
					
					
					</tr>
						           
	           		
	           	
	           	</table>
	           
   
   
   </div>
   
   <div id="tabs-8">
   
   <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="9">
						<table width="100%" border="1">
					<tr>
						<td>
					<!-- 	-------------- -->
						
						
    
     <%
	int ad_member_id=Integer.parseInt(request.getParameter("ad_member_id"));
    
     
     	MemberShareBean msbean = new MemberShareDao().getAllShareAmtByMemberId(ad_member_id);  
   		LoanTrxDetailBean ltdbean = new LoanTrxDetailDao().getOpenLoanBal(ad_member_id);
   		
   		double thriftcr_amt=new TransactionDao().getTotalCrbyMemAndAcID(ad_member_id, 3);
   		double thriftdr_amt=new TransactionDao().getTotalDrbyMemAndAcID(ad_member_id, 3);
   		double thrift_amt=thriftcr_amt-thriftdr_amt;
   		
   		LoanTrxBean lbean =new LoanTrxDao().getLoanTrxById1(ad_member_id);
   		
   		
   		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
	    Date today =  (Date)session.getAttribute("openday");

	    Date trxdate=ltdbean.getTrx_date();
	   
	    
	    long diff = trxdate.getTime() - today.getTime();
	    long diffDays = diff / (24 * 60 * 60 * 1000);
	 
   		
   		double loan_int = (ltdbean.getBalance_amt()*lbean.getIntrest_rate()*diffDays)/36500;
 %>

<table width="100%" border="0">
	<tbody>
	
			
		</tr>
		<tr style="background:buttonface; ">
		 <td colspan="2">
		 Thrift AND Share Detail
		 </td>
		 
		</tr>
		<tr>
		<td>Thrift Amount</td><td>Rs:<input value="<%=thrift_amt %>"  readonly="readonly" id="thrift_amt" name='thrift_amt' style="background: none;border: none" /></td>
		</tr>
		<tr>
		<td>Interest On Thrift Amount</td><td>Rs:<input value='0.00'  readonly="readonly" id="thrift_int" name='thrift_int' style="background: none;border: none" /></td>	
		</tr>
		<tr>
		<td>Amount of Total <lable style="color: #2E64FE "><%=msbean.getQnt_of_share() %></lable> Share</td><td>Rs:<input value='<%=msbean.getShare_amt()  %>'  readonly="readonly" id="share_amt" name='share_amt' style="background: none;border: none" /> </td>	
		</tr>
		<!-- <tr>
		<td>Other Amount</td><td>Rs: 200</td>	
		</tr> -->
		<tr>
		<td colspan="2" align="center">
			<hr>
		</td>
		</tr>
		<tr>
		<td align="Right">Total Payable Amount &nbsp;&nbsp;&nbsp;&nbsp;</td><td>Rs:<input value='<%=thrift_amt+msbean.getShare_amt() %>'  readonly="readonly" id="payale_amt" name='payale_amt' style="background: none;border: none" /> </td>	
		</tr>
		<tr style="background:buttonface; ">
		 <td colspan="2">
		 Loan Detail
		 </td>
		 
		</tr>
		<tr>
		<td>Loan Amount</td><td>Rs:<input value='<%=ltdbean.getBalance_amt() %>'  readonly="readonly" id="loan_amt" name='loan_amt' style="background: none;border: none"/></td>
		</tr>
		<tr>
		<td>Interest On Loan Amount</td><td>Rs:<input value='<%=loan_int %>'  readonly="readonly" id="loan_int" name='loan_int' style="background: none;border: none"/></td>	
		</tr>
		<td colspan="2" align="center">
			<hr>
		</td>
		</tr>
		
		<tr>
		
		<td align="Right">Total Recovery Amount &nbsp;&nbsp;&nbsp;&nbsp;</td><td>Rs:<input value='<%=ltdbean.getBalance_amt()+loan_int %>'  readonly="readonly" id="recovery_amt" name='recovery_amt' style="background: none;border: none"/></td>	
		</tr>
		
		<tr style="background:buttonface; ">
		 <td colspan="2">
		&nbsp;
		 </td>
		 
		</tr>
		
		<tr>
		
		<tr>
		<td colspan="2" align="center">
			<hr>
		</td>
		</tr>
		<tr>
		
		</tr>
		
		
		
		
	</tbody>
</table>	<!-- =------------ -->
						</td>	
							
							
							
					</tr>			
						</table>
					
					
					</td>
					
					
					
					</tr>
						           
	           		
	           	
	           	</table>
	           
   
   
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
    
   function showloantrx(loan_trx_id){
	   var popup;
	   popup=	window.open ("view_loantrx_list.jsp?loan_trx_id="+loan_trx_id, "mywindow","location=1,status=1,scrollbars=1, width=1000,height=500, left=150,top=100");
	   popup.focus();

  
    }
  
</script>   
