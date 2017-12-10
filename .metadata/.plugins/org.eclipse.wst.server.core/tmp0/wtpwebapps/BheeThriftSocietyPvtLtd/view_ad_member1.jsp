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
	
	$("#ad_member_id").change(function(e) {
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
                	  $("#nominee_photo_view_1").val(data.MemberInfo.nominee[0].photo);
                	  $("#nominee_id_proof_view_1").val(data.MemberInfo.nominee[0].id_proof);
                	  $("#nominee_sign_view_1").val(data.MemberInfo.nominee[0].sign);
                	  $("#nominee_name_2").val(data.MemberInfo.nominee[1].name);
                	  $("#nominee_ad_relation_id_2").val(data.MemberInfo.nominee[1].relation.relation);
                	  $("#nominee_ad_gender_id_2").val(data.MemberInfo.nominee[1].gender.gender);
                	  $("#nominee_dob_2").val(data.MemberInfo.nominee[1].dob);
                	  $("#nominee_age_2").val(data.MemberInfo.nominee[1].age);
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

<table height="100%"  width="100%" style="border:1px solid black">
	<tbody>
		 
		<tr> 	
			<td colspan="2">
			
	
    <table  cellpadding="5" cellspacing="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Personal Detail</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
						
						
					
						<td class="" >&nbsp;Name</td>
						<td class="" >:</td>
						<td colspan="4">
						<select  name="ad_member_id" id="ad_member_id" style="width: 380px; height: 50px" data-placeholder="Select Member" class="chosen-select"  tabindex="2">
								<option value="0">--Select Member--</option>
								 <%MemberRegistrationDao dao=new MemberRegistrationDao();
								 	ArrayList<MemberRegistrationBean> alist=dao.getAllMemberlist();
								 	if(alist!=null){
								 	for(MemberRegistrationBean bean:alist){%>
								 <option value="<%=bean.getAd_member_id()%>"><%=bean.getAd_member_id() %> <%=bean.getName() %></option>
								 <%} 
								 }%>
								</select>	
								
						</td>
						</tr>
					<tr >
						<td>&nbsp;PFNo</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_pf_no" id="ad_pf_no" style="width: 170px; border: none" readonly="readonly">
								 
						</td>
						<td>&nbsp;Mem.No</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_society_no" id="ad_society_no" style="width: 170px;border: none" readonly="readonly">
								 
						</td>
						</tr>
						
						<tr >
						<td>&nbsp;Type</td>
						<td class="" >:</td>
						<td>	<input type="text" name="ad_member_type_id" id="ad_member_type_id" style="width: 170px;border: none" readonly="readonly">
									
						</td>
						<td>&nbsp;Status</td>
						<td class="" >:</td>
						<td>	<input type="text" name="ad_member_status_id" id="ad_member_status_id" style="width: 170px;border: none" readonly="readonly">
									
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Father</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="father" id="father" style="width: 170px;border: none" readonly="readonly">
						</td>
						<td class="" >&nbsp;Husband</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="husband" id="husband" style="width: 170px;border: none" readonly="readonly">
						</td>
						<td class="" >&nbsp;DOB</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="dob" id="dob" style="width: 170px;border: none" readonly="readonly">
						</td>
					</tr>
					<tr >
						
			
						<td>&nbsp;Gender</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_gender_id" id="ad_gender_id" style="width: 170px; border: none" readonly="readonly" >
								 
						</td>
						<td>&nbsp;Category</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_category_id" id="ad_category_id" style="width: 170px;border: none" readonly="readonly" >
								 
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
						
					
					
	              
	            </table>	

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
						<td>	<input type="text"  name="mobile" id="mobile" maxlength="10" style="width: 170px;border: none" readonly="readonly">
						</td>
						<td class="" >&nbsp;Phone</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="phone" id="phone" maxlength="10" style="width: 170px;border: none" readonly="readonly">
						</td>
						<td class="" >&nbsp;Email</td>
						<td class="" >:</td>
						<td>	<input type="email"  name="email" id="email" style="width: 170px;border: none" readonly="readonly">
						</td>
					</tr>
					<tr >
						<td>&nbsp;Lane 1</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="line1" id="line1" style="width: 170px;border: none" readonly="readonly">
								 
						</td>
						<td>&nbsp;Lane 2</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="line2" id="line2" style="width: 170px;border: none" readonly="readonly">
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Country</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_country_id" id="ad_country_id" style="width: 170px;border: none" readonly="readonly" >
								
						</td>
						<td>&nbsp;State</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_state_id" id="ad_state_id" style="width: 170px;border: none" readonly="readonly" >
								
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;District</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_district_id" id="ad_district_id" style="width: 170px;border: none" readonly="readonly" >
								 
						</td>
						<td class="" >&nbsp;City</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_city_id" id="ad_city_id" style="width: 170px;border: none" readonly="readonly" >
								 
						</td>
						<td class="" >&nbsp;Pincode</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="pincode" id="pincode" maxlength="6" style="width: 170px;border: none" readonly="readonly">
						
						</td>
						</tr>
						
						<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="3" style="text-align: left;font-weight: bold;font-size: 14px;" >Permanent Address</td>
					
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
						<td class="" >&nbsp;Mobile</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="mobile1" id="mobile1" maxlength="10" style="width: 170px;border: none" readonly="readonly">
						</td>
						<td class="" >&nbsp;Phone</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="phone1" id="phone1" maxlength="10" style="width: 170px;border: none" readonly="readonly">
						</td>
						<td class="" >&nbsp;Email</td>
						<td class="" >:</td>
						<td>	<input type="email"  name="email1" id="email1" style="width: 170px;border: none" readonly="readonly">
						</td>
					</tr>
					<tr >
						<td>&nbsp;Lane 1</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="line1_1" id="line1_1" style="width: 170px;border: none" readonly="readonly">
								 
						</td>
						<td>&nbsp;Lane 2</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="line2_1" id="line2_1" style="width: 170px;border: none" readonly="readonly">
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Country</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_country_id_1" id="ad_country_id_1" style="width: 170px;border: none" readonly="readonly" >
								 
						</td>
						<td>&nbsp;State</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_state_id_1" id="ad_state_id_1" style="width: 170px;border: none" readonly="readonly" >
								
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;District</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_district_id_1" id="ad_district_id_1" style="width: 170px;border: none" readonly="readonly" >
								 
						</td>
						<td class="" >&nbsp;City</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_city_id_1" id="ad_city_id_1" style="width: 170px;border: none" readonly="readonly" >
								
						</td>
						<td class="" >&nbsp;Pincode</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="pincode1" id="pincode1"  maxlength="6" style="width: 170px;border: none" readonly="readonly">
						
						</td>
						</tr>
						
						
	            </table>	

 <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
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
						<input type="text"  name="ad_branch_id" id="ad_branch_id" style="width: 370px;border: none" readonly="readonly" >
								
						</td>
						
					
						
					
						
						
					</tr>
					<tr >
											
						<td class="" >&nbsp;Region</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_bank_region_id" id="ad_bank_region_id" style="width: 170px;border: none" readonly="readonly">
						</td>
					
						<td>&nbsp;Code</td>
						<td class="" >:</td>
						<td>	<input type="text" name="branch_code" id="branch_code" style="width: 170px;border: none" readonly="readonly">
								 
						</td>
						<td>&nbsp;IFSC</td>
						<td class="" >:</td>
						<td>	<input type="text" name="ifsc_code" id="ifsc_code" style="width: 170px;border: none" readonly="readonly">
							
					</tr>
					<tr >
						<td>&nbsp;State</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_bank_state_id" id="ad_bank_state_id" style="width: 170px;border: none" readonly="readonly">
					
						</td>
						
					
						
					
						<td class="" >&nbsp;District</td>
						<td class="" >:</td>
						<td>	<input type="text" name="ad_bank_district_id" id="ad_bank_district_id" style="width: 170px;border: none" readonly="readonly">
								
						</td>
						
						<td class="" >&nbsp;City</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_bank_city_id" id="ad_bank_city_id" readonly="readonly" style="width: 170px;border: none" readonly="readonly" >
								
						</td>
						
					</tr>
					
					<tr>
							 
						</td>
						<td class="" >&nbsp;Pincode</td>
						<td class="" >:</td>
						<td>	<input type="text" name="bank_pincode" id="bank_pincode" readonly="readonly" style="width: 170px;border: none" readonly="readonly">
						</td>
						
					
						<td>&nbsp;Email</td>
						<td class="" >:</td>
						<td>	<input type="text" name="email_id" id="email_id" readonly="readonly" style="width: 170px;border: none" readonly="readonly">
							 
						</td>
					</tr>
					<tr>
						<td class="" >&nbsp;Phone</td>
						<td class="" >:</td>
						<td>	<input type="text" name="phone_no" id="phone_no" readonly="readonly" style="width: 170px;border: none" readonly="readonly">
						</td>
						
					
						<td>&nbsp;Person</td>
						<td class="" >:</td>
						<td>	<input type="text" name="contact_person" id="contact_person" readonly="readonly" style="width: 170px;border: none" readonly="readonly">
								 
						</td>
						<td class="" >&nbsp;Contact</td>
						<td class="" >:</td>
						<td>	<input type="text" name="contact_no" id="contact_no" readonly="readonly" style="width: 170px;border: none" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td class="" >&nbsp;Address</td>
						<td class="" >:</td>
						<td colspan="7">	<input type="text" name="address" id="address" readonly="readonly" style="width: 765px;border: none" readonly="readonly">
						</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
							 
						</td>
						<td class="" >&nbsp;Pan No</td>
						<td class="" >:</td>
						<td>	<input type="text" name="pan_no" id="pan_no"  style="width: 170px;border: none" readonly="readonly">
						</td>
						
					
						<td>&nbsp;Aadhar</td>
						<td class="" >:</td>
						<td>	<input type="text" name="aadhar_no" id="aadhar_no"  style="width: 170px;border: none" readonly="readonly">
							 
						</td>
						
						<td class="" >&nbsp;Department</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_department_id" id="ad_department_id"  style="width: 170px;border: none" readonly="readonly" >
									
						</td>
					</tr>
					<tr>
						<td class="" >&nbsp;Designation</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_designation_id" id="ad_designation_id"  style="width: 170px;border: none" readonly="readonly" >
									
						</td>
						
						<td class="" >&nbsp;Level</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_designation_level_id" id="ad_designation_level_id"  style="width: 170px;border: none" readonly="readonly" >
									
						</td>
						<td class="" >&nbsp;Type</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_designation_type_id" id="ad_designation_type_id"  style="width: 170px;border: none" readonly="readonly" >
									
						</td>
						
						</tr>
						<tr>
						<td class="" >&nbsp;Appointment</td>
						<td class="" >:</td>
						<td>	<input type="text" name="doa" id="doa"  style="width: 170px;border: none" readonly="readonly">
						</td>
						<td class="" >&nbsp;Joining</td>
						<td class="" >:</td>
						<td>	<input type="text" name="doj" id="doj"  style="width: 170px;border: none" readonly="readonly">
						</td>
						<td class="" >&nbsp;Retirement</td>
						<td class="" >:</td>
						<td>	<input type="text" name="dor" id="dor"  style="width: 170px;border: none" readonly="readonly">
						</td>
					</tr>
					
					<tr>
						<td class="" >&nbsp;Service Duration</td>
						<td class="" >:</td>
						<td>	<input type="text" name="service_duration" id="service_duration"  style="width: 170px;border: none" readonly="readonly">
						</td>
						<td class="" >&nbsp;A/C No</td>
						<td class="" >:</td>
						<td>	<input type="text" name="saving_ac_no" id="saving_ac_no"  style="width: 170px;border: none" readonly="readonly">
						</td>
					
					
					</tr>
					
					
	              
	            </table>		

    <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Nominee Detail</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="9" style="font-size: 14px;font-weight: bold;" >First Nominee</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
						<tr>
						
						
					
						<td class="" >&nbsp;Name</td>
						<td class="" >:</td>
						<td colspan="7">
						<input type="text"  name="nominee_name_1" id="nominee_name_1" style="width: 420px;border: none" readonly="readonly">
						</td>
						
						
						</tr>
						<tr>
							<td class="" >&nbsp;Relation</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="nominee_ad_relation_id_1" id="nominee_ad_relation_id_1" style="width: 170px;border: none" readonly="readonly" >
								 
								  
						</td>
						
						<td>&nbsp;Gender</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="nominee_ad_gender_id_1" id="nominee_ad_gender_id_1" style="width: 170px;border: none" readonly="readonly" >
								 
								  
						</td>
						</tr>
					<tr >
						<td class="" >&nbsp;DOB</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="nominee_dob_1" id="nominee_dob_1" style="width: 170px;border: none" readonly="readonly">
						</td>
						<td class="" >&nbsp;Age</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="nominee_age_1" id="nominee_age_1" readonly="readonly" style="width: 170px;border: none" readonly="readonly">
						</td>
						
						
					
						</tr>
						
  
    	
        <tr height="100px" >
  
    	
        <th  style="color:#363" align="center" width="150px" valign="top"  colspan="3">
        <table>
        	<tr>
        		<th><img height="100" width="100" id='nominee_photo_view_1' style="background-image:url(Image/emp.png);background-size:cover;border: 1px gray;" /> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>Photo</th>
        	</tr>
        	
        </table>
        </th>
       	
        <th  style="color:#363" align="center" width="150px" valign="top"   colspan="3">
         <table>
        	<tr>
        		<th><img height="100" width="100" id="nominee_id_proof_view_1" style="background-image:url(Image/id.png);background-size:cover;border: 1px gray;" /> &nbsp;</th>
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
        		<th><img height="100" width="100"  id="nominee_sign_view_1" style="background-image:url(Image/sign.png);background-size:cover;border: 1px gray;"/> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>Sign
        		</th>
        		</tr>
        		
        </table>
        </th>
       
        
        
 </tr>
  		<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="9" style="font-size: 14px;font-weight: bold;" >Second Nominee</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
						<tr>
						
					
						<td class="" >&nbsp;Name</td>
						<td class="" >:</td>
						<td colspan="7">
						
								<input type="text"  name="nominee_name_2" id="nominee_name_2" style="width: 420px;border: none" readonly="readonly">
						</td>
						</tr>
									<tr>
						<td class="" >&nbsp;Relation</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="nominee_ad_relation_id_2" id="nominee_ad_relation_id_2" style="width: 170px;border: none" readonly="readonly" >
						
								 
						</td>
						
						<td>&nbsp;Gender</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="nominee_ad_gender_id_2" id="nominee_ad_gender_id_2" style="width: 170px;border: none" readonly="readonly" >
						
						</td>
						
					
						</tr>
					<tr >
						<td class="" >&nbsp;DOB</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="nominee_dob_2" id="nominee_dob_2" style="width: 170px;border: none" readonly="readonly">
						</td>
						<td class="" >&nbsp;Age</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="nominee_age_2" id="nominee_age_2" readonly="readonly" style="width: 170px;border: none" readonly="readonly">
						</td>
						</tr>
			
						<tr height="100px" >
  
    	
        <th  style="color:#363" align="center" width="150px" valign="top"  colspan="3">
        <table>
        	<tr>
        		<th><img height="100" width="100" id='nominee_photo_view_2' style="background-image:url(Image/emp.png);background-size:cover;border: 1px gray;" /> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>Photo</th>
        	</tr>
        	
        </table>
        </th>
       	
        <th  style="color:#363" align="center" width="150px" valign="top"   colspan="3">
         <table>
        	<tr>
        		<th><img height="100" width="100" id="nominee_id_proof_view_2" style="background-image:url(Image/id.png);background-size:cover;border: 1px gray;" /> &nbsp;</th>
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
        		<th><img height="100" width="100"  id='nominee_sign_view_2' style="background-image:url(Image/sign.png);background-size:cover;border: 1px gray;"/> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>Sign
        		</th>
        		</tr>
        		
        </table>
        </th>
       
        
        
 </tr>

	            </table>	

    <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Witness Detail</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					
					<tr >
						<td>&nbsp;Member</td>
						<td class="" >:</td>
						<td>	
						<input type="text" name="witness_ad_member_id" id="witness_ad_member_id" style="width: 170px;border: none" readonly="readonly" >
							
								 
						</td>
						<td>&nbsp;Mem.No</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="witness_ad_society_id" id="witness_ad_society_id" style="width: 170px;border: none" readonly="readonly">
								 
						</td>
						</tr>
						<tr>
										
					
						<td class="" >&nbsp;Name</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="witness_name" id="witness_name" style="width: 170px;border: none" readonly="readonly">
						</td>
						</tr><tr>
						<td class="" >&nbsp;Mobile</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="witness_mobile" id="witness_mobile" style="width: 170px;border: none" readonly="readonly">
						</td>
						<td class="" >&nbsp;Address</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="witness_address" id="witness_address" style="width: 170px;border: none" readonly="readonly">
						</td>
					</tr>
					
	            </table>	

    <!-- <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Registration Fees Detail</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					
					<tr >
						<td>&nbsp;Membership Fess</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="membership_fees" id="membership_fees" value="" readonly="readonly" style="width: 170px;">
								 
						</td>
						<td>&nbsp;FGDS Fund</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="fgds_fund" id="fgds_fund" value="" readonly="readonly" style="width: 170px;">
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;DCF</td>
						<td class="" >:</td>
						<td>	<input type="text" name="dcf" id="dcf" value="" readonly="readonly"style="width: 170px;">
								
						</td>
						<td class="" >&nbsp;No of Share</td>
						<td class="" >:</td>
						<td>	<input type="text" name="share_qty" id="share_qty" value="" readonly="readonly" style="width: 170px;">
								
						</td>
						
						</tr>
						<tr>
						<td class="" >&nbsp;Share Amt</td>
						<td class="" >:</td>
						<td>	<input type="text" name="share_amt" id="share_amt" value="" readonly="readonly" style="width: 170px;">
						
						</td>
						<td class="" >&nbsp;Admission Fees</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="admission_fees" id="admission_fees" value="" readonly="readonly"style="width: 170px;">
						</td>
						</tr>

	              
	            </table> -->

	            
			</td>
		</tr> 
		<tr >
		<td colspan="9" align="center" style="">
		<input type="button" name="edit" value="Edit" onclick="return membre_edit()" style="width: 80px; height: 30px;margin-bottom: 15px;">&nbsp;&nbsp;&nbsp;
		<input type="button" name="print" value="Print" style="width: 80px; height: 30px;margin-bottom: 15px;">
		</td>
		</tr>
	</tbody></table>
  </form>
 
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
</script>   
<script type="text/javascript">

</script>