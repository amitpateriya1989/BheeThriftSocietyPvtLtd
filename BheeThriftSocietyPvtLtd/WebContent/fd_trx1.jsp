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
	$("#ad_member_id").change(function(e) {
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
                	  $("#pincode").html(data.MemberInfo.address[0].pincode);
                	  
                	  $("#mobile1").html(data.MemberInfo.address[1].mobile);
                	  $("#phone1").html(data.MemberInfo.address[1].phone);
                	  $("#email1").html(data.MemberInfo.address[1].email);
                	  $("#line1_1").html(data.MemberInfo.address[1].line1);
                	  $("#line2_1").html(data.MemberInfo.address[1].line2);
                	  $("#ad_country_id_1").val(data.MemberInfo.address[1].country.country);
                	  $("#ad_state_id_1").val(data.MemberInfo.address[1].state.state);
                	  $("#ad_district_id_1").val(data.MemberInfo.address[1].district.district);
                	  $("#ad_city_id_1").val(data.MemberInfo.address[1].city.city);
                	  $("#pincode1").val(data.MemberInfo.address[1].pincode);
                	 
                	  $("#nominee_name_1").html(data.MemberInfo.nominee[0].name);
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
                   $("#ajaxResponse").html(jqXHR.responseText);
             }
  
         });
		 
		 
		 
		 
		 
		 
		 
		 
 });
	
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
                	 //alert(data.MemberInfo.branch.bank_region.region);
                	  $("#guar_ad_society_id").html(data.MemberInfo.ad_society_no);
                	  $("#guar_pf_no").html(data.MemberInfo.ad_pf_no);
                	  $("#guar_name").html(data.MemberInfo.name);
                	  $("#guar_mobile").html(data.MemberInfo.address[0].mobile);
                	  $("#guar_address").html(data.MemberInfo.address[0].line1);
                	  


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
	
	

});

function membre_edit(){
	var ad_member_id=$("#ad_member_id").val();
	//alert(ad_member_id);
	window.location.replace("AdMemberRegistration?action=edit&ad_member_id="+ad_member_id);
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
<div id='frm'   style="border:1px solid red">



<table height="100%"  width="100%" style="border:1px solid black">
	<tbody>
		 
		<tr> 	
			<td colspan="2">
			
				<div id="tabs">
  <ul>
    <li><a href="#tabs-1">Personal</a></li>
    <li><a href="#tabs-2">Contact</a></li>
    <li><a href="#tabs-3">Service</a></li>
    <li><a href="#tabs-4">Issue New FD</a></li>
    <li><a href="#tabs-5">Renew Existing FD</a></li>
    <li><a href="#tabs-6">Redeption of FD</a></li>
    <li><a id="view_ex_fd"  href="#tabs-7">View Existing FD</a></li>
  </ul>
  <div id="tabs-1">
  
    <table  cellpadding="5" cellspacing="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Personal Detail</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr >
						<td class="" >&nbsp;Name</td>
						<td class="" >:</td>
						<td colspan="4">
						<select  name="ad_member_id" id="ad_member_id" style="width: 380px; height: 50px" data-placeholder="Select Member" class="chosen-select"  tabindex="2">
								<option value="0">--Select Member--</option>
								 <%MemberRegistrationDao dao=new MemberRegistrationDao();
								 	ArrayList<MemberRegistrationBean> alist=dao.getAllMemberlist();
								 	if(alist!=null){
								 	for(MemberRegistrationBean bean:alist){%>
								 <option value="<%=bean.getAd_member_id()%>"><%=bean.getName() %></option>
								 <%} 
								 }%>
								</select>	
								
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
								<!-- <input type="Button" name="next2" value="Next" style="width: 70px;height: 25px;"/>&nbsp;&nbsp;
								<input type="reset" name="Clear" style="width: 70px;height: 25px;"/> -->
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
						<!-- <input type="button" name="next3" value="Next"/>&nbsp;&nbsp;
						<input type="reset" name="Clear"/> -->
					</td>
					</tr>						
					
					<tr>
					<td colspan="7" class=""  >&nbsp;</td>
					</tr>
	              
	            </table>		
  </div>
  <div id="tabs-4">
    <form action="AdFdTrx?action=insert" method="post">
    
    <input type="hidden" name="ad_member_id_nfd" id="ad_member_id_nfd"/>
    <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >-:New FD Application:-</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					
					
					
				
					
					<tr>
					<td colspan="7" class=""  >&nbsp;</td>
					</tr>
					<tr >
						<td class="" >&nbsp; Fd Type</td>
						<td class="" >:</td>
						<td>	<select name='ad_type_of_fd_id' id="ad_type_of_fd_id">
							<option value='0'>--select--</option>
						<%
						 TypeOfFdDao daoa=new TypeOfFdDao();
					 	ArrayList<TypeOfFdBean> alista=daoa.getAlltypeoffd();
					 	if(alista!=null){
					 	for(TypeOfFdBean bean:alista){%>
					 <option value="<%=bean.getAd_type_of_fd_id()%>"><%=bean.getName() %></option>
					 <%} 
					 }%>	
						
												</select>
						<td class="" >&nbsp; Fd Category</td>
						<td class="" >:</td>
						<td>	<select name='ad_fd_category_id' id="ad_fd_category_id">
						<option value='0'>--select--</option>
						<%
						 FdCategoryDao dao1 =new FdCategoryDao();
					 	ArrayList<FdCategoryBean> alist1=dao1.getAllFdCategory();
					 	if(alist1!=null){
					 	for(FdCategoryBean bean:alist1){%>
					 <option value="<%=bean.getAd_fd_category_id()%>"><%=bean.getName() %></option>
					 <%} 
					 }%>
						
						
						</select>
						</td>
						
						</tr>
					<tr>
					<td class="" >&nbsp;Fd AMT</td>
						<td class="" >:</td>
						<td>	<input type="text" class="amt" name="fd_amt" onkeyup="validatenum()" id="fd_amt" style="width: 170px;" required="required">						
						</td>					
					<td class="" >&nbsp;Fd Opening Date</td>
						<td class="" >:</td>
						<td>	<input type="date" name="opening_date" id="opening_date" value="<%=session.getAttribute("openday") %>" style="width: 170px;" required="required" readonly="readonly">						
						</td>
						
					
					</tr>
					<tr>
										
					<td class="" >&nbsp;Period in Month's</td>
						<td class="" >:</td>
						
						
						
						
						<td>	<select name="time_period" id="time_period"><option value="0">--Select--</option>
						
						
						
						
						
						</select>						
						</td>
						<td class="" >&nbsp;Maturity Date</td>
						<td class="" >:</td>
						<td>	<input type="date" name="maturity_date" id="maturity_date" style="width: 170px;" required="required">							
						</td>
					
					</tr>
					<tr>
					<td class="" >&nbsp;Intrest Rate</td>
						<td class="" >:</td>
						<td>	<input type="text" class="amt"  name="interest_rate" id="interest_rate" style="width: 170px;" required="required">						
						</td>					
					
						<td class="" >&nbsp;Interest Amount</td>
						<td class="" >:</td>
						<td>	<input type="text" class="amt" name="intrest_amt" id="intrest_amt" style="width: 170px;" required="required">							
						</td>
					
					</tr>
					
					<tr>
						<td class="" >&nbsp;Maturity Amount</td>
						<td class="" >:</td>
						<td>	<input type="text" class="amt" name="maturity_amt" id="maturity_amt" style="width: 170px;" required="required" readonly="readonly">							
						</td>
					
					</tr>
	              <tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					
				
					
					<tr>
					<td colspan="7" class="" align="center"  >
					<input type="submit" onclick="return valid()" value ="Submit" />
					
					</td>
					</tr>
	            </table>  
	            
	            <script>
	           $(document).ready(function(e){
	        	   $("#ad_fd_category_id").change(function(e){
	            		//$("#time_period").val(0);
	        		   var ad_type_of_fd_id= $("#ad_type_of_fd_id").val();
						var ad_fd_category_id= $("#ad_fd_category_id").val();
						
					
						if(parseInt(ad_type_of_fd_id)!=0 && parseInt(ad_fd_category_id)!=0){
							
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
				                        	  
				                        	  //alert(value.ad_fd_roi_id);
				                        	  $("#time_period").append("<option value='"+value.ad_fd_roi_id+"'>"+value.time_period+" 'Month'</option>'");
				                         	 
				                        	  
				                          });
				                	  });
				                	  
				                      
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
	            		
	            		if($("#ad_type_of_fd_id").val()==0){
	            			alert("Please Select Fd Type First...!");
	            			$("#time_period").val(0);
	            			event.stop();
	            		}else if($("#ad_fd_category_id").val()==0){
	            			alert("Please Select FD Category First...!");
	            			$("#time_period").val(0);
	            			event.stop();
	            		}else if($("#fd_amt").val()==''){
	            			alert("Please Enter FD Amt First...!");
	            			$("#time_period").val(0);
	            			event.stop();
	            		}else if($("#opening_date").val()==''){
	            			
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
					                	  $("#interest_rate").val(intrate);
					                	  var time_period=data.FdInfo.time_period;
					                	  var intamt=parseFloat(fd_amt)*parseFloat(parseFloat(intrate))*(parseInt(time_period)/12)/100
					                      $("#intrest_amt").val(intamt);
					                	  
					                	  $("#maturity_amt").val(parseFloat(fd_amt)+parseFloat(intamt));
					                  } 
					                 
					                 
					                  
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
	           function validatenum()
	           {

	               var z = document.getElementById("fd_amt").value;
	               if(!z.match(/^\d+/))
	                   {
	                   alert("Please only enter numeric characters only for fd amt ! (Allowed input:0-9)");
	                   document.getElementById("fd_amt").value='';
	                   }
	           }
	            </script>
	            
	   </form>         
  </div>
  <div id="tabs-5">
   
    <form action="AdFdTrx?action=reinsert" method="post">
    
    <input type="hidden" name="ad_member_id_renfd" id="ad_member_id_renfd"/>
    <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >-:ReNew FD Application:-</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					
				<tr>
				<td class="" >&nbsp;Fd Renew Date</td>
						<td class="" >:</td>
						<td>	<input type="date" name="re_opening_date" id="re_opening_date" style="width: 170px;">						
						</td>
				
					<td class="" >&nbsp; Select Fd No</td>
						<td class="" >:</td>
						<td>	<select name='fd_no' id="fd_no"></select></td>
												
						<script>
						$(document).ready(function(e){
							 $("#re_ad_type_of_fd_id").prop("disabled",true);
		                	$("#re_ad_fd_category_id").prop("disabled",true);
							
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
							            	// alert(data[2]);
							               
							                 // if(data.success){
							                	 
							                	 var intr=(parseFloat(data[0].fd_amt)*parseFloat(data[1].roi)*(parseInt(data[1].time_period)/12)/100);
							                	 var one_month_int=parseFloat(intr)/parseInt(data[1].time_period);
							                	 var one_day_int=parseFloat(one_month_int)/30;
							                	
							                	 $("#fd_content").html("<table border='1' width='100%'><tr><th>FD No</th><th>FD Amt</th><th>Opening Date</th><th>Time Period</th><th>Maturity Date</th><th>Int Rate</th><th>Int Amt</th><th>Total Amt</th>"+
							                			  "<tr><td>"+data[0].fd_no+"</td><td>"+data[0].fd_amt+"</td><td>"+data[0].opening_date+"</td><td>"+data[1].time_period+"</td><td>"+data[0].maturity_date+"</td><td>"+data[1].roi+"%</td><td>"+intr+"</td><td>"+(parseFloat(data[0].fd_amt)+parseInt(intr))+"</td>"+
							                			  "</table>");
							                	  
							                	  $("#extra_day_int").val(Math.round(parseFloat(data[2])*parseFloat(one_day_int)));
							                	 
							                	  var amt=Math.round(parseFloat(data[2])*parseFloat(one_day_int))+parseFloat(data[0].fd_amt)+parseInt(intr);
							                	
							                	  $("#re_fd_amt").val(amt);
							                	  $("#re_ad_type_of_fd_id").prop("disabled",false);
							                	  $("#re_ad_fd_category_id").prop("disabled",false);
							                	  $("#re_ad_type_of_fd_id").val(data[1].ad_type_of_fd_id);
							                	  $("#re_ad_fd_category_id").val(data[1].ad_fd_category_id);
							                	  $("#re_ad_type_of_fd_id").prop("disabled",true);
							                	 $("#re_ad_fd_category_id").prop("disabled",true);
							                	   
													 $("#re_ad_type_of_fd_id").change();
							                	  
							                 // } 
							                 
							                 // else {
							                	
								                	
								                	
							                //  }
							             },
							            
							             
							             error: function(jqXHR, textStatus, errorThrown){
							                  console.log("Something really bad happened " + textStatus);
							                   $("#ajaxResponse").html(jqXHR.responseText);
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
							                   $("#ajaxResponse").html(jqXHR.responseText);
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
												
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="9" class=""  id="fd_content">
					</td>
					
					</tr>
					<tr>
					<td colspan="7" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr >
						<td class="" >&nbsp; Fd Type</td>
						<td class="" >:</td>
						<td>	<select name='re_ad_type_of_fd_id' id="re_ad_type_of_fd_id">
							<option value='0'>--select--</option>
						<%
						 TypeOfFdDao daoar=new TypeOfFdDao();
					 	ArrayList<TypeOfFdBean> alistar=daoar.getAlltypeoffd();
					 	if(alistar!=null){
					 	for(TypeOfFdBean bean:alistar){%>
					 <option value="<%=bean.getAd_type_of_fd_id()%>"><%=bean.getName() %></option>
					 <%} 
					 }%>	
						
												</select>
						<td class="" >&nbsp; Fd Category</td>
						<td class="" >:</td>
						<td>	<select name='re_ad_fd_category_id' id="re_ad_fd_category_id">
						<option value='0'>--select--</option>
						<%
						 FdCategoryDao dao1r =new FdCategoryDao();
					 	ArrayList<FdCategoryBean> alist1r=dao1r.getAllFdCategory();
					 	if(alist1r!=null){
					 	for(FdCategoryBean bean:alist1r){%>
					 <option value="<%=bean.getAd_fd_category_id()%>"><%=bean.getName() %></option>
					 <%} 
					 }%>
						
						
						</select>
						</td>
						
						</tr>
					
					<tr >
						<td class="" >Extra Days Intrest</td>
						<td class="" >:</td>
						<td> <input type="text" name="extra_day_int" onkeyup="" id="extra_day_int" style="width: 170px;" readonly="readonly">	</td>

						<td class="" >Total Fd Amt</td>
						<td class="" >:</td>
						<td>	<input type="text" name="re_fd_amt" class="amt" id="re_fd_amt" style="width: 170px;" required="required">	
						</td>
						
						</tr>
					
					<tr>
										
					<td class="" >&nbsp;Period in Month's</td>
						<td class="" >:</td>
						
						
						<td>	<select name="re_time_period" id="re_time_period"><option value="0">--Select--</option>
						
						
						
						
						
						</select>						
						</td>
						<td class="" >&nbsp;Maturity Date</td>
						<td class="" >:</td>
						<td>	<input type="date" name="re_maturity_date" id="re_maturity_date" style="width: 170px;" required="required" readonly="readonly"	>							
						</td>
					
					</tr>
					<tr>
					<td class="" >&nbsp;Intrest Rate</td>
						<td class="" >:</td>
						<td>	<input type="text" class="amt" name="re_interest_rate" id="re_interest_rate" style="width: 170px;" required="required">						
						</td>					
					
						<td class="" >&nbsp;Interest Amount</td>
						<td class="" >:</td>
						<td>	<input type="text" class="amt" name="re_intrest_amt" id="re_intrest_amt" style="width: 170px;" required="required">							
						</td>
					
					</tr>
					
					<tr>
						<td class="" >&nbsp;Maturity Amount</td>
						<td class="" >:</td>
						<td>	<input type="text" name="re_maturity_amt" id="re_maturity_amt" style="width: 170px;" required="required">							
						</td>
					
					</tr>
	              <tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					
				
					
					<tr>
					<td colspan="7" class="" align="center"  >
					<input type="submit" value ="Submit" />
					
					</td>
					</tr>
	            </table>  
	            
	            <script>
	           $(document).ready(function(e){
	            	
	            	$("#re_time_period").change(function(e){
	            		
	            		
	            	
	            		if($("#re_ad_type_of_fd_id").val()==0){
	            			alert("Please Select Fd  First...!");
	            			$("#re_time_period").val(0);
	            			event.stop();
	            		}else if($("#re_ad_fd_category_id").val()==0){
	            			alert("Please Select FD  First...!");
	            			$("#re_time_period").val(0);
	            			event.stop();
	            		}else if($("#re_fd_amt").val()==''){
	            			alert("Please Enter FD Amt First...!");
	            			$("#re_time_period").val(0);
	            			event.stop();
	            		}else if($("#re_opening_date").val()==''){
	            			
	            			alert("Please Select Opning Date...!");
	            			$("#re_time_period").val(0);
	            			event.stop();
	            			//var fd_amt=$("#fd_amt").val();
	            			
	            		            			
	            		}else{
	            			var ad_fd_roi_id=$(this).val();
	            			var fd_amt=$("#re_fd_amt").val();
	            			var dataString = "action=getroiratebytime&ad_fd_roi_id="+ad_fd_roi_id;
							 $.ajax({
					             type: "POST",
					             url: "AdFdRoi",
					             data: dataString,
					             dataType: "json",
					            
					             //if received a response from the server
					             success: function( data, textStatus, jqXHR) {
					                 //our country code was correct so we have some information to display
					                 
					                  if(data.success){
					                	
					                	
					                //  alert(data.FdInfo.ad_fd_roi_id);
					                	 
					                	  var intrate= data.FdInfo.roi;
					                	  $("#re_interest_rate").val(intrate);
					                	  var time_period=data.FdInfo.time_period;
					                	  var intamt=parseFloat(fd_amt)*parseFloat(parseFloat(intrate))*(parseInt(time_period)/12)/100
					                      $("#re_intrest_amt").val(intamt);
					                	  
					                	  $("#re_maturity_amt").val(parseFloat(fd_amt)+parseFloat(intamt));
					                  } 
					                 
					                  //display error message
					                  else {
					                	 
						                	
						                	
					                  }
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
	           function validatenum()
	           {

	               var z = document.getElementById("fd_amt").value;
	               if(!z.match(/^\d+/))
	                   {
	                   alert("Please only enter numeric characters only for fd amt ! (Allowed input:0-9)");
	                   document.getElementById("fd_amt").value='';
	                   }
	           }
	            </script>
	            
	   </form>           </div>
   <div id="tabs-6">
   
    <form action="AdFdTrx?action=" method="post">
    
    <input type="hidden" name="ad_member_id_redefd" id="ad_member_id_redefd"/>
    <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >-:Redeption FD Application:-</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					
				<tr>
				<td class="" >&nbsp;Fd Redeption Date</td>
						<td class="" >:</td>
						<td>	<input type="date" name="rede_date" id="rede_date" style="width: 170px;">						
						</td>
				
					<td class="" >&nbsp; Select Fd No</td>
						<td class="" >:</td>
						<td>	<select name='fd_no_rede' id="fd_no_rede"></select></td>
												
						<script>
						$(document).ready(function(e){
							 $("#rede_ad_type_of_fd_id").prop("disabled",true);
		                	  $("#rede_ad_fd_category_id").prop("disabled",true);
							
							
							$("#fd_no_rede").change(function(e){
								
								var ad_fd_trx_id= $(this).val();
								
								var re_opening_date=$("#rede_date").val();
								
								if(re_opening_date==""){
									alert("Please Select Renew Date First....!");
									$(this).val(0);
									event.stop();
								}
								
								if(parseInt(ad_fd_trx_id)!=0 && re_opening_date!=''){
									
									
									var dataString = "action=getfdbyid1&ad_fd_trx_id="+ad_fd_trx_id+"&maturity_date="+re_opening_date;
									 $.ajax({
							             type: "POST",
							             url: "AdFdTrx",
							             data: dataString,
							             dataType: "json",
							            
							             
							             success: function( data, textStatus, jqXHR) {
							            	// alert(data[2]);
							               
							                 // if(data.success){
							                	 
							                	 var intr=(parseFloat(data[0].fd_amt)*parseFloat(data[1].roi)*(parseInt(data[1].time_period)/12)/100);
							                	 var one_month_int=parseFloat(intr)/parseInt(data[1].time_period);
							                	 var one_day_int=parseFloat(one_month_int)/30;
							                	
							                	 $("#fd_content_rede").html("<table border='1' width='100%'><tr><th>FD No</th><th>FD Amt</th><th>Opening Date</th><th>Time Period</th><th>Maturity Date</th><th>Int Rate</th><th>Int Amt</th><th>Total Amt</th>"+
							                			  "<tr><td>"+data[0].fd_no+"</td><td>"+data[0].fd_amt+"</td><td>"+data[0].opening_date+"</td><td>"+data[1].time_period+"</td><td>"+data[0].maturity_date+"</td><td>"+data[1].roi+"%</td><td>"+intr+"</td><td>"+(parseFloat(data[0].fd_amt)+parseInt(intr))+"</td>"+
							                			  "</table>");
							                	  
							                	  $("#extra_day_int").val(Math.round(parseFloat(data[2])*parseFloat(one_day_int)));
							                	 
							                	  var amt=Math.round(parseFloat(data[2])*parseFloat(one_day_int))+parseFloat(data[0].fd_amt)+parseInt(intr);
							                	
							                	  $("#re_fd_amt").val(amt);
							                	  $("#rede_ad_type_of_fd_id").prop("disabled",false);
							                	  $("#rede_ad_fd_category_id").prop("disabled",false);
							                	  $("#rede_ad_type_of_fd_id").val(data[1].ad_type_of_fd_id);
							                	  $("#rede_ad_fd_category_id").val(data[1].ad_fd_category_id);
							                	  $("#rede_ad_type_of_fd_id").prop("disabled",true);
							                	  $("#rede_ad_fd_category_id").prop("disabled",true);
							                	  $("#rede_fd_amt").val(data[0].fd_amt);
							                	  
							                 // } 
							                 
							                 // else {
							                	
								                	
								                	
							                //  }
							             },
							            
							             
							             error: function(jqXHR, textStatus, errorThrown){
							                  console.log("Something really bad happened " + textStatus);
							                   $("#ajaxResponse").html(jqXHR.responseText);
							             }
							  
							         });
									
									
								}else{
									$("#fd_content_rede").empty();
									
								}
								
								
								if($(this).val()==0){
			            			event.stop();
			            		}
			            		
			            		else{
			            			
			            			
									var rede_date= $("#rede_date").val();
									var fd_no_rede=$("#fd_no_rede").val();
									
								
									if(rede_date!='' && parseInt(fd_no_rede)!=0 ){
										
									var dataString = "action=fdrediption&rede_date="+rede_date+"&fd_no_rede="+fd_no_rede;
									 $.ajax({
							             type: "POST",
							             url: "AdFdTrx",
							             data: dataString,
							             dataType: "json",
							            
							             //if received a response from the server
							             success: function( data, textStatus, jqXHR) {
							                 //our country code was correct so we have some information to display
							                 
							                  
							                	$("#redeption_type").val(data[0]);
							                 	$("#rede_fd_amt").val(data[1]);
							                	$("#rede_int_rate").val(data[2]);
							                	$("#total_int").val(data[3]);
							                	$("#total_fd_amt").val(data[4]);
							              	
							                  
							             },
							            
							             //If there was no resonse from the server
							             error: function(jqXHR, textStatus, errorThrown){
							                  console.log("Something really bad happened " + textStatus);
							                   $("#ajaxResponse").html(jqXHR.responseText);
							             }
							  
							         });
								}
			            			
			            		}
								
								
								
								
								
							});
							
						});
						</script>								
												
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="9" class=""  id="fd_content_rede">
					</td>
					
					</tr>
					<tr>
					<td colspan="7" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr >
						<td class="" >&nbsp; Fd Type</td>
						<td class="" >:</td>
						<td>	<select name='rede_ad_type_of_fd_id' id="rede_ad_type_of_fd_id">
							<option value='0'>--select--</option>
						<%
						 TypeOfFdDao daoard=new TypeOfFdDao();
					 	ArrayList<TypeOfFdBean> alistard=daoard.getAlltypeoffd();
					 	if(alistard!=null){
					 	for(TypeOfFdBean bean:alistard){%>
					 <option value="<%=bean.getAd_type_of_fd_id()%>"><%=bean.getName() %></option>
					 <%} 
					 }%>	
						
												</select></td>
						<td class="" >&nbsp; Fd Category</td>
						<td class="" >:</td>
						<td>	<select name='rede_ad_fd_category_id' id="rede_ad_fd_category_id">
						<option value='0'>--select--</option>
						<%
						 FdCategoryDao dao1rd =new FdCategoryDao();
					 	ArrayList<FdCategoryBean> alist1rd=dao1rd.getAllFdCategory();
					 	if(alist1rd!=null){
					 	for(FdCategoryBean bean:alist1rd){%>
					 <option value="<%=bean.getAd_fd_category_id()%>"><%=bean.getName() %></option>
					 <%} 
					 }%>
						
						
						</select>
						</td>
						
						</tr>
						<tr >
						<td class="" >Redeption Type</td>
						<td class="" >:</td><td> 
						<select id="redeption_type" name="redeption_type"><option value='0'>--select--</option><option>Pre Maturity</option><option value='Maturity'> Maturity</option><option value='Post Maturity'>Post Maturity</option></select>
						
						</td>
						
						
					<td class="" >&nbsp;Fd AMT</td>
						<td class="" >:</td>
						<td>	<input type="text" class="amt" name="rede_fd_amt" onkeyup="validatenum()" id="rede_fd_amt" style="width: 170px;" required="required">						
						</td>	
					</tr>
					
					
					<tr>	
						<td class="" >Applicable Intrest rate on Redeption Date</td>
						<td class="" >:</td>
						<td>	<input type="text" class="amt" name="rede_int_rate" onkeyup="" id="rede_int_rate" style="width: 170px;" required="required">						
						</td>	
						
						</tr>
					<tr >
						<td class="" >Intrest on Redeption Date</td>
						<td class="" >:</td>
						<td> <input type="text" name="total_int" onkeyup="" id="total_int" style="width: 170px;" readonly="readonly" required="required">	</td>

						<td class="" >Total Fd Amt</td>
						<td class="" >:</td>
						<td>	<input type="text" class=amt name="total_fd_amt" onkeyup="" id="total_fd_amt" style="width: 170px; " required="required">	
						</td>
						
						</tr>
					
					
	              <tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					
				
					
					<tr>
					<td colspan="7" class="" align="center"  >
					<input type="submit" value ="Submit" />
					
					</td>
					</tr>
	            </table>  
	            
	            <script>
	           $(document).ready(function(e){
	        	  
	            	$("#redeption_type").change(function(e){
	            		
	            		
	            	});
	            	
	            });
	           function validatenum()
	           {

	               var z = document.getElementById("fd_amt").value;
	               if(!z.match(/^\d+/))
	                   {
	                   alert("Please only enter numeric characters only for fd amt ! (Allowed input:0-9)");
	                   document.getElementById("fd_amt").value='';
	                   }
	           }
	            </script>
	            
	   </form>           </div>
	   
	   <div id="tabs-6">
	   
	    <script>
	           $(document).ready(function(e){
	        	  
	            	$("#view_ex_fd").click(function(e){
	            		
	            		var ad_member_id = $("#ad_member_id").val();
	            		
	            		if(ad_member_id!=0){
	            			
	            			var dataString = "action=getfdbymember&ad_member_id="+ad_member_id;
							 $.ajax({
					             type: "POST",
					             url: "AdFdTrx",
					             data: dataString,
					             dataType: "json",
					            
					             
					             success: function( data, textStatus, jqXHR) {
					            	// alert(data[0].ad_fd_trx_id);
					            	 //alert(data[1].ad_fd_trx_id);
					                 // if(data.success){
					                	 
					                	 var intr=(parseFloat(data[0].fd_amt)*parseFloat(data[1].roi)*(parseInt(data[1].time_period)/12)/100);
					                	 var one_month_int=parseFloat(intr)/parseInt(data[1].time_period);
					                	 var one_day_int=parseFloat(one_month_int)/30;
					                	
					                	 $("#vfdcontent").html("<table border='1' width='100%'><tr><th>FD No</th><th>FD Amt</th><th>Opening Date</th><th>Time Period</th><th>Maturity Date</th><th>Int Rate</th><th>Int Amt</th><th>Total Amt</th>"+
					                			  "<tr><td>"+data[0].fd_no+"</td><td>"+data[0].fd_amt+"</td><td>"+data[0].opening_date+"</td><td>"+data[1].time_period+"</td><td>"+data[0].maturity_date+"</td><td>"+data[1].roi+"%</td><td>"+intr+"</td><td>"+(parseFloat(data[0].fd_amt)+parseInt(intr))+"</td>"+
					                			  "</table>");
					                	  
					                	 
					                	 
					                 // } 
					                 
					                 // else {
					                	
						                	
						                	
					                //  }
					             },
					            
					             
					             error: function(jqXHR, textStatus, errorThrown){
					                  console.log("Something really bad happened " + textStatus);
					                   $("#ajaxResponse").html(jqXHR.responseText);
					             }
					  
					         });
	            			
	            			
	            			
	            		}else{
	            			alert("Please Select Member ");
	            			event.stop();
	            		}
	            	});
	            	
	            });
	           </script>
	           
	           
	           <div id="vfdcontent">
	           </div>
	   
	   </div>
	   
  
</div>	
	            
			</td>
		</tr> 
	</tbody></table>
  </div>
 
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
  

    function valid(){
    	if($("#ad_type_of_fd_id").val()=="0"){
    		alert("please select FD Type");
    		return false;
    	}else if($("#ad_fd_category_id").val()=="0"){
    		alert("please select Fd Category");
    		return false;
    	}
    	
    	
    }
</script>   
