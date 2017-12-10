<%@page import="java.util.Date"%>
<%@page import="Model.Bean.NominationBean"%>
<%@page import="Model.Bean.MemberAddressBean"%>
<%@page import="java.text.SimpleDateFormat"%>
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


$("#same").on("click", function() {
    if($(this).is(':checked')){
    	
    	$('#mobile1').val($('#mobile').val());
    	
    	$('#phone1').val($('#phone').val());
    	$('#email1').val($('#email').val());
    	$('#line1_1').val($('#line1').val());
    	$('#line2_1').val($('#line2').val());
    	var country=$('#country').val();
    	var state=$('#state').val();
    	var district=$('#district').val();
    	var city=$('#city').val();
    	$('#pincode1').val($('#pincode').val());
    	
    }else{
    	
    	$('#mobile1').val('');
    	$('#phone1').val('');
    	$('#email1').val('');
    	$('#line1_1').val('');
    	$('#line2_1').val('');
    	var country=$('#country').val();
    	var state=$('#state').val();
    	var district=$('#district').val();
    	var city=$('#city').val();
    	$('#pincode1').val('');
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
            	  $("#bank_pincode").val(data.BranchInfo.pincode);
            	  $("#contact_no").val(data.BranchInfo.contact_no);
            	  $("#contact_person").val(data.BranchInfo.contact_person);
            	  $("#email_id").val(data.BranchInfo.email_id);
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

     });   });  
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
			<table height="100%" border="0" cellpadding="0" cellspacing="0" width="100%">
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
<form action="AdMemberRegistration?action=insert" method="post" enctype="multipart/form-data">
<table height="100%"  width="100%" style="border:1px solid black">
	<tbody>
		 
		<tr> 	
			<td colspan="2">
			
				<div id="tabs">
  <ul>
    <li><a href="#tabs-1">Personal</a></li>
    <li><a href="#tabs-2">Contact</a></li>
    <li><a href="#tabs-3">Service</a></li>
    <li><a href="#tabs-4">Nominee</a></li>
    <li><a href="#tabs-5">Witness</a></li>
    
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
					
						<%
						
						MemberRegistrationBean member=(MemberRegistrationBean)session.getAttribute("member");
						
						%>
						
						<td>&nbsp;PFNo</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_pf_id" id="ad_pf_id" value="<%=member.getAd_pf_no() %>" style="width: 170px;">
								 
						</td>
						<td>&nbsp;Mem.No</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_society_id" id="ad_society_id" value="<%=member.getAd_society_no() %>" readonly="readonly" style="width: 170px;">
								 
						</td>
						</tr>
						
						<tr >
						<td>&nbsp;Type</td>
						<td class="" >:</td>
						<td>	<select name="ad_member_type_id" id="ad_member_type_id" style="width: 170px;" data-placeholder="Type" class="chosen-select" style="width:250px;" tabindex="2">
									<option value="<%=member.getMember_type().getAd_member_type_id()%>"><%=member.getMember_type().getMember_type() %></option>
									<%MemberTypeDao type=new MemberTypeDao();
									  ArrayList<MemberTypeBean> membertypelist=type.getAllMemberType();
									  if(membertypelist!=null){
										  for(MemberTypeBean bean:membertypelist){%>
											  <option value="<%=bean.getAd_member_type_id()%>"><%=bean.getMember_type() %></option>
									<%		  
										  }
									  }
									  
									  %>
								</select>
								 
						</td>
						<td>&nbsp;Status</td>
						<td class="" >:</td>
						<td>	<select name="ad_member_status_id" id="ad_member_satus_id" style="width: 170px;" data-placeholder="Status" class="chosen-select" style="width:250px;" tabindex="2">
									<option value="<%=member.getMember_status().getAd_member_status_id()%>"><%=member.getMember_status().getMember_status() %></option>
									<%MemberStatusDao status=new MemberStatusDao();
									  ArrayList<MemberStatusBean> statuslist=status.getAllMemberStatus();
									  if(statuslist!=null){
										  for(MemberStatusBean bean:statuslist){%>
											  <option value="<%=bean.getAd_member_status_id()%>"><%=bean.getMember_status() %></option>
									<%		  
										  }
									  }
									  
									  %>
								</select>
								 
						</td>
						</tr>
						
						<tr>
						
						
					
						<td class="" >&nbsp;Name</td>
						<td class="" >:</td>
						<td colspan="4">
								<select  name="ad_salutation_id" id="ad_salutation_id" style="width: 80px;" data-placeholder="Mr." class="chosen-select" style="width:250px;" tabindex="2">
								<option value="<%=member.getSalutation().getAd_salutation_id()%>"><%=member.getSalutation().getName() %></option>
								 <%SalutationDao dao=new SalutationDao();
								 	ArrayList<SalutationBean> alist=dao.getAllSalutation();
								 	for(SalutationBean bean:alist){%>
								 <option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
								 <%} %>
								</select>	
								<input type="text"  name="name" id="name" value="<%=member.getName() %>" style="width: 375px;">
						</td>
						</tr><tr>
						<td class="" >&nbsp;Father</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="father" id="father" value="<%=member.getFather() %>" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Husband</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="husband" id="husband" value="<%=member.getHusband() %>" style="width: 170px;">
						</td>
						<td class="" >&nbsp;DOB</td>
						<td class="" >:</td>
						<td>	<input type="date"  name="dob" id="dob" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(member.getDob().getTime()) %>" style="width: 170px;">
						</td>
					</tr>
					<tr >
						
			
						<td>&nbsp;Gender</td>
						<td class="" >:</td>
						<td>	<select  name="ad_gender_id" id="ad_gender_id" style="width: 170px;" data-placeholder="Choose a Gender..." class="chosen-select" style="width:250px;" tabindex="2">
								  <option value="<%=member.getGender().getAd_gender_id()%>"><%=member.getGender().getGender() %></option>
								  <%GenderDao dao1=new GenderDao();
								 	ArrayList<GenderBean> alist1=dao1.getAllGender();
								 	for(GenderBean bean:alist1){%>
								 <option value="<%=bean.getAd_gender_id()%>"><%=bean.getGender() %></option>
								 <%} %>
								
						</select>
						</td>
						<td>&nbsp;Category</td>
						<td class="" >:</td>
						<td>	<select  name="ad_category_id" id="ad_category_id" style="width: 170px;" data-placeholder="Choose a Category..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="<%=member.getCategory().getAd_category_id()%>"><%=member.getCategory().getCategory() %></option>
								 <%CategoryDao dao2=new CategoryDao();
								 	ArrayList<CategoryBean> alist2=dao2.getAllCategory();
								 	for(CategoryBean bean:alist2){%>
								 <option value="<%=bean.getAd_category_id()%>"><%=bean.getCategory() %></option>
								 <%} %>
						</select>
						</td>
					
						</tr>
						<tr height="100px" >
  
    	
        <th  style="color:#363" align="center" width="150px" valign="top"  colspan="3">
        <table>
        	<tr>
        		<th><img src="" height="100" width="100" id='photo_view' style="background-size:cover;border: 1px solid gray;" /> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>Photo</th>
        	</tr>
        	<tr>
        	<th><input type="file" name=photo id=photo style="width:100px;" required="required"/></th>
        	</tr>
        </table>
        </th>
       	
        <th  style="color:#363" align="center" width="150px" valign="top"   colspan="3">
         <table>
        	<tr>
        		<th><img src="" height="100" width="100" id="id_proof_view" style="background-size:cover;border: 1px solid gray;" /> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>ID Proof
        		</th>
        		</tr>
        		<tr>
        		<th><input type="file" name=id id=id_proof style="width:100px;" required="required"/></th>
        	</tr>
        </table>
        
        </th>
      
   
    	
        <th  style="color:#363" align="center" width="150px" valign="top" colspan="3">
        
         <table>
        	<tr>
        		<th><img src="" height="100" width="100"  id='sign_view' style="background-size:cover;border: 1px solid gray;"/> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>Sign
        		</th>
        		</tr>
        		<tr>
        		<th>
        		<input type="file" name=sign id=sign style="width:100px;" required="required" /></th>
        	</tr>
        </table>
        </th>
       
        
        
 </tr>
<tr>					
					<td colspan="9" align="center">
						<input type="Button" name="next1" value="Next" style="width: 70px;height: 25px;"/>&nbsp;&nbsp;
						<input type="reset" name="Clear" style="width: 70px;height: 25px;"/>
					</td>
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
						
						
						<% 
						
						 String line1="" ;
						 String line2 ="";
						String email ="";
						 String state ="";
						 String country="";
						 String pincode ="";
						int districtid =0;
						String districtname="";
						int cityid=0;
						String cityname ="";
						int contryid =0;
						String stateid ="";
						String mobile ="";
						 String phone ="";
						
						if(member.getAddress().size()!=0){
							MemberAddressBean contact1=(MemberAddressBean)member.getAddress().get(0); 
							mobile=contact1.getMobile() ;
							email=contact1.getEmail();
							  line1 =contact1.getLine1();
							  line2 =contact1.getLine2();				
							  state=contact1.getState().getState();
							  contryid=contact1.getCountry().getAd_country_id();
							  country =contact1.getCountry().getCountry();
							  districtid=contact1.getDistrict().getAd_district_id(); 
							  districtname =contact1.getDistrict().getDistrict();
							  cityid=contact1.getCity().getAd_city_id();
							  cityname=contact1.getCity().getCity();
							  pincode =contact1.getPincode();					  
							 stateid=contact1.getState().getState();
							  phone =contact1.getPhone();
							
						}
						%>
						<td>	<input type="text"  name="mobile" id="mobile" value="<%=mobile%>" maxlength="10" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Phone</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="phone" id="phone" value="<%=phone %>" maxlength="10" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Email</td>
						<td class="" >:</td>
						<td>	<input type="email"  name="email" id="email" value="<%=email %>" style="width: 170px;">
						</td>
					</tr>
					<tr >
						<td>&nbsp;Lane 1</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="line1" id="line1" value="<%=line1 %>" style="width: 170px;">
								 
						</td>
						<td>&nbsp;Lane 2</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="line2" id="line2" value="<%= line2%> style="width: 170px;">
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Country</td>
						<td class="" >:</td>
						<td>	<select  name="ad_country_id" id="ad_country_id" style="width: 170px;" data-placeholder="Choose a Country..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="<%=contryid %>"><%=country %></option>
								 <%
								 CountryDao dao3=new CountryDao();
								 ArrayList<CountryBean> alist3=dao3.getAllCountryName();
								 for(CountryBean bean:alist3){%>
									 <option value="<%=bean.getAd_country_id()%>"><%=bean.getCountry() %></option>
									 
									 <%
								 }
								 %>
								</select>
						</td>
						<td>&nbsp;State</td>
						<td class="" >:</td>
						<td>	<select  name="ad_state_id" id="ad_state_id" style="width: 170px;" data-placeholder="Choose a State..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="<%= stateid%>"><%=state %></option>
								<%StateDao sdao=new StateDao();
								  ArrayList <StateBean> slist=sdao.getAllStateName();
								  if(alist2!=null){
								  for(StateBean bean:slist){%>
								  <option value="<%=bean.getAd_state_id()%>"><%=bean.getState() %></option>
									  
								 <%} }%>
						</select>
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;District</td>
						<td class="" >:</td>
						<td>	<select  name="ad_district_id" id="ad_district_id" style="width: 170px;" data-placeholder="Choose a District..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="<%=districtid%>"><%=districtname %></option>
								</select>
						</td>
						<td class="" >&nbsp;City</td>
						<td class="" >:</td>
						<td>	<select  name="ad_city_id" id="ad_city_id" style="width: 170px;" data-placeholder="Choose a City..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="<%=cityid%>"><%=cityname%></option>
								</select>
						</td>
						<td class="" >&nbsp;Pincode</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="pincode" id="pincode" value="<%= pincode%>" maxlength="6" style="width: 170px;">
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
					
					<%
					  line1="" ;
					  line2 ="";
					 email ="";
					  state ="";
					  country="";
					  pincode ="";
					 districtid =0;
					 districtname="";
					 cityid=0;
					 cityname ="";
					 contryid =0;
					 stateid ="";
					 mobile ="";
					  phone ="";
					
					 if(member.getAddress().size()>1) {
						MemberAddressBean contact1=(MemberAddressBean)member.getAddress().get(1); 
						mobile=contact1.getMobile() ;
						email=contact1.getEmail();
						  line1 =contact1.getLine1();
						  line2 =contact1.getLine2();				
						  state=contact1.getState().getState();
						  contryid=contact1.getCountry().getAd_country_id();
						  country =contact1.getCountry().getCountry();
						  districtid=contact1.getDistrict().getAd_district_id(); 
						  districtname =contact1.getDistrict().getDistrict();
						  cityid=contact1.getCity().getAd_city_id();
						  cityname=contact1.getCity().getCity();
						  pincode =contact1.getPincode();					  
						 stateid=contact1.getState().getState();
						  phone =contact1.getPhone();
					 }
					
					%>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
						<td class="" >&nbsp;Mobile</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="mobile1" id="mobile1" value="<%=mobile %>" maxlength="10" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Phone</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="phone1" id="phone1" value="<%=phone %>" maxlength="10" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Email</td>
						<td class="" >:</td>
						<td>	<input type="email"  name="email1" id="email1" value="<%=email %>" style="width: 170px;">
						</td>
					</tr>
					<tr >
						<td>&nbsp;Lane 1</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="line1_1" id="line1_1" value="<%=line1 %>" style="width: 170px;">
								 
						</td>
						<td>&nbsp;Lane 2</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="line2_1" id="line2_1" value="<%=line2 %>" style="width: 170px;">
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Country</td>
						<td class="" >:</td>
						<td>	<select  name="ad_country_id_1" id="ad_country_id_1" style="width: 170px;" data-placeholder="Choose a Country..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="<%=contryid%>"><%=country %></option>
								 <%
								 CountryDao countrydao=new CountryDao();
								 ArrayList<CountryBean> countrylist=countrydao.getAllCountryName();
								 for(CountryBean bean:countrylist){%>
									 <option value="<%=bean.getAd_country_id()%>"><%=bean.getCountry() %></option>
									 
									 <%
								 }
								 %>
								</select>
						</td>
						<td>&nbsp;State</td>
						<td class="" >:</td>
						<td>	<select  name="ad_state_id_1" id="ad_state_id_1" style="width: 170px;" data-placeholder="Choose a State..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="<%=stateid%>"><%=state %></option>
								<%StateDao statedao=new StateDao();
								  ArrayList <StateBean> statelist=statedao.getAllStateName();
								  if(statelist!=null){
								  for(StateBean bean:statelist){%>
								  <option value="<%=bean.getAd_state_id()%>"><%=bean.getState() %></option>
									  
								 <%} }%>
						</select>
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;District</td>
						<td class="" >:</td>
						<td>	<select  name="ad_district_id_1" id="ad_district_id_1" style="width: 170px;" data-placeholder="Choose a District..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="<%=districtid%>"><%=districtname %></option>
								</select>
						</td>
						<td class="" >&nbsp;City</td>
						<td class="" >:</td>
						<td>	<select  name="ad_city_id_1" id="ad_city_id_1" style="width: 170px;" data-placeholder="Choose a City..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="<%=cityid %>"><%=cityname %></option>
								</select>
						</td>
						<td class="" >&nbsp;Pincode</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="pincode1" id="pincode1" value="<%=pincode %>" maxlength="6" style="width: 170px;">
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
						<select  name="ad_branch_id" id="ad_branch_id" style="width: 370px;" data-placeholder="Choose a City..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="<%=member.getBranch().getAd_branch_id()%>"><%=member.getBranch().getBranch() %></option>
								<%BankBranchDao bankdao=new BankBranchDao();
								  ArrayList <BankBranchBean> banklist=bankdao.getAllBankBranchName();
								  if(banklist.isEmpty()!=true){
								  for(BankBranchBean bean:banklist){
								//  String city=new CityDao().getCityById(bean.getCity().getAd_city_id()).getCity().trim();
								 // String state1=new StateDao().getStateById(bean.getState().getAd_state_id()).getState().trim();
								  %>
								  
								  <option value="<%=bean.getAd_branch_id()%>"><%=bean.getBranch_code()+"/"+bean.getBranch().trim() %></option>
									  
								 <%} }%>
						</select>
						</td>
						
					
						
					
						
						
					</tr>
					<tr >
											
						<td class="" >&nbsp;Region</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_bank_region_id" id="ad_bank_region_id" value="<%=member.getBranch().getBank_region().getRegion() %>" style="width: 170px;" readonly="readonly" >
						</td>
					
						<td>&nbsp;Code</td>
						<td class="" >:</td>
						<td>	<input type="text" name="branch_code" id="branch_code" value="<%=member.getBranch().getBranch_code() %>" style="width: 170px;" readonly="readonly">
								 
						</td>
						<td>&nbsp;IFSC</td>
						<td class="" >:</td>
						<td>	<input type="text" name="ifsc_code" id="ifsc_code" value="<%=member.getBranch().getIfsc_code() %>" style="width: 170px;" readonly="readonly">
							
					</tr>
					<tr >
						<td>&nbsp;State</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_bank_state_id" id="ad_bank_state_id" value="<%=member.getBranch().getState().getState() %>" style="width: 170px;" readonly="readonly">
					
						</td>
						
					
						
					
						<td class="" >&nbsp;District</td>
						<td class="" >:</td>
						<td>	<input type="text" name="ad_bank_district_id" id="ad_bank_district_id" value="<%=member.getBranch().getDistrict().getDistrict() %>" style="width: 170px;" readonly="readonly" >
								
						</td>
						
						<td class="" >&nbsp;City</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_bank_city_id" id="ad_bank_city_id" value="<%=member.getBranch().getCity().getCity() %>" readonly="readonly" style="width: 170px;" >
								
						</td>
						
					</tr>
					
					<tr>
							 
						</td>
						<td class="" >&nbsp;Pincode</td>
						<td class="" >:</td>
						<td>	<input type="text" name="bank_pincode" value="<%=member.getBranch().getPincode() %>" id="bank_pincode" readonly="readonly" style="width: 170px;">
						</td>
						
					
						<td>&nbsp;Email</td>
						<td class="" >:</td>
						<td>	<input type="text" name="email_id" id="email_id" value="<%=member.getBranch().getEmail_id() %>" readonly="readonly" style="width: 170px;">
							 
						</td>
					</tr>
					<tr>
						<td class="" >&nbsp;Phone</td>
						<td class="" >:</td>
						<td>	<input type="text" name="phone_no" id="phone_no" value="<%=member.getBranch().getPhone_no() %>" readonly="readonly" style="width: 170px;">
						</td>
						
					
						<td>&nbsp;Person</td>
						<td class="" >:</td>
						<td>	<input type="text" name="contact_person" id="contact_person" value="<%=member.getBranch().getContact_person() %>" readonly="readonly" style="width: 170px;">
								 
						</td>
						<td class="" >&nbsp;Contact</td>
						<td class="" >:</td>
						<td>	<input type="text" name="contact_no" id="contact_no" value="<%=member.getBranch().getContact_person() %>" readonly="readonly" style="width: 170px;">
						</td>
					</tr>
					<tr>
						<td class="" >&nbsp;Address</td>
						<td class="" >:</td>
						<td colspan="7">	<input type="text" name="address" id="address" value=<%=member.getBranch().getAddress() %> readonly="readonly" style="width: 765px;">
						</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
							 
						</td>
						<td class="" >&nbsp;Pan No</td>
						<td class="" >:</td>
						<td>	<input type="text" name="pan_no" id="pan_no" value="<%=member.getPan_no() %>" style="width: 170px;">
						</td>
						
					
						<td>&nbsp;Aadhar</td>
						<td class="" >:</td>
						<td>	<input type="text" name="aadhar_no" id="aadhar_no" value="<%=member.getAadhar_no() %>" style="width: 170px;">
							 
						</td>
						
						<td class="" >&nbsp;Department</td>
						<td class="" >:</td>
						<td>	<select  name="ad_department_id" id="ad_department_id"  style="width: 170px;" data-placeholder="Choose a Department..." class="chosen-select"  tabindex="2">
									<option value="<%=member.getDepartment().getAd_department_id()%>"><%=member.getDepartment().getName() %></option>
									<%
									DepartmentDao deptdao=new DepartmentDao();
									ArrayList<DepartmentBean> deptlist=deptdao.getAllDepartmentName();
									if(deptlist.isEmpty()!=true){
										for(DepartmentBean bean:deptlist){%>
											<option value="<%=bean.getAd_department_id()%>"><%=bean.getName() %></option>
										<%}
									}
									%>
								</select>
						</td>
					</tr>
					<tr>
						<td class="" >&nbsp;Designation</td>
						<td class="" >:</td>
						<td>	<select  name="ad_designation_id" id="ad_designation_id"  style="width: 170px;" data-placeholder="Choose a Designation..." class="chosen-select"  tabindex="2">
									<option value="<%=member.getDesignation().getAd_designation_id()%>"><%=member.getDesignation().getDesignation() %></option>
									<%
									DesignationDao desigdao=new DesignationDao();
									ArrayList<DesignationBean> desglist=desigdao.getAllDesignationName();
									if(desglist.isEmpty()!=true){
										for(DesignationBean bean:desglist){%>
											<option value="<%=bean.getAd_designation_id()%>"><%=bean.getDesignation() %></option>
										<%}
									}
									%>
								</select>
						</td>
						
						<td class="" >&nbsp;Level</td>
						<td class="" >:</td>
						<td>	<select  name="ad_designation_level_id" id="ad_designation_level_id"  style="width: 170px;" data-placeholder="Choose a Level..." class="chosen-select"  tabindex="2">
									<option value="<%=member.getDesignation_level().getAd_designation_level_id()%>"><%=member.getDesignation_level().getDesignation_level() %></option>
									<%
									DesignationLevelDao leveldao=new DesignationLevelDao();
									ArrayList<DesignationLevelBean> levellist=leveldao.getAllDesignationLevel();
									if(desglist.isEmpty()!=true){
										for(DesignationLevelBean bean:levellist){%>
											<option value="<%=bean.getAd_designation_level_id()%>"><%=bean.getDesignation_level() %></option>
										<%}
									}
									%>
								</select>
						</td>
						<td class="" >&nbsp;Type</td>
						<td class="" >:</td>
						<td>	<select  name="ad_designation_type_id" id="ad_designation_type_id"  style="width: 170px;" data-placeholder="Choose a Type..." class="chosen-select"  tabindex="2">
									<option value="<%=member.getDesignation_type().getAd_designation_type_id()%>"><%=member.getDesignation_type().getDesignation_type() %></option>
									<%
									DesignationTypeDao typedao=new DesignationTypeDao();
									ArrayList<DesignationTypeBean> typelist=typedao.getAllDesignationType();
									if(desglist.isEmpty()!=true){
										for(DesignationTypeBean bean:typelist){%>
											<option value="<%=bean.getAd_designation_type_id()%>"><%=bean.getDesignation_type() %></option>
										<%}
									}
									%>
								</select>
						</td>
						
						</tr>
						<tr>
						<td class="" >&nbsp;Appointment</td>
						<td class="" >:</td>
						<% SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
           					String doa=sdf1.format(member.getDoa().getTime());
           					String doj=sdf1.format(member.getDoj().getTime());
           					String dor=sdf1.format(member.getDor().getTime());
           					
       					 %>
						<td>	<input type="date" name="doa" id="doa" value="<%=doa %>"  style="width: 170px;">
						</td>
						<td class="" >&nbsp;Joining</td>
						<td class="" >:</td>
						<td>	<input type="date" name="doj" id="doj" value="<%=doj %>"  style="width: 170px;">
						</td>
						<td class="" >&nbsp;Retirement</td>
						<td class="" >:</td>
						<td>	<input type="date" name="dor" id="dor" value="<%=dor %>"  style="width: 170px;">
						</td>
					</tr>
					
					<tr>
						<td class="" >&nbsp;Service Duration</td>
						<td class="" >:</td>
						<td>	<input type="text" name="service_duration" value="<%=member.getService_duration() %>" id="service_duration"  style="width: 170px;">
						</td>
						<td class="" >&nbsp;A/C No</td>
						<td class="" >:</td>
						<td>	<input type="text" name="saving_ac_no" value="<%=member.getSaving_ac_no() %>" id="saving_ac_no"  style="width: 170px;">
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
  </div>
  <div id="tabs-4">
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
						<%
						String name="";
						Date dob =null;
						String age ="";
						int nrid=0;
						String nrelation="";
						int ngid=0;
						String ngname="";
						int sailutationid=0;
						String sailutationname="";
						
						if(member.getNominee().size()>0){
							NominationBean nominee1=member.getNominee().get(0); 
							name=nominee1.getName();
							nrid=nominee1.getRelation().getAd_relation_id();
							nrelation=nominee1.getRelation().getRelation();
							ngid=nominee1.getGender().getAd_gender_id();
							ngname=nominee1.getGender().getGender() ;
							if(nominee1.getDob()!=null){
								dob=nominee1.getDob();
							}
							age=nominee1.getAge();
							sailutationid=nominee1.getSalutation().getAd_salutation_id();
							sailutationname=nominee1.getSalutation().getName();
						}
						%>
						
					
						<td class="" >&nbsp;Name</td>
						<td class="" >:</td>
						<td colspan="7">
						<select  name="nominee_ad_salutation_id_1" id="nominee_ad_salutation_id_1" style="width: 80px;" data-placeholder="Mr." class="chosen-select"  tabindex="2">
								 <option value="<%=sailutationid %>"><%=sailutationname %></option>
								 <%
								 SalutationDao salutationdao=new SalutationDao();
								 ArrayList<SalutationBean> salutationlist=salutationdao.getAllSalutation();
								 if(salutationlist.isEmpty()!=true){
									 for(SalutationBean bean:salutationlist){%>
									 <option value="<%=bean.getAd_salutation_id()%>"><%=name %></option>
																		 
									 <%	 }
								 }%>
								</select>	<input type="text"  name="nominee_name_1" value="<%=name %>" id="nominee_name_1" style="width: 420px;">
						</td>
						
						
						</tr>
						<tr>
							<td class="" >&nbsp;Relation</td>
						<td class="" >:</td>
						<td>	<select  name="nominee_ad_relation_id_1" id="nominee_ad_relation_id_1" style="width: 170px;" data-placeholder="Relation" class="chosen-select"  tabindex="2">
								 <option value="<%=nrid%>"><%=nrelation %></option>
								  <%RelationDao rdao=new RelationDao();
								 	ArrayList<RelationBean> rlist=rdao.getAllRelation();
								 	if(rlist.isEmpty()!=true){
								 	for(RelationBean bean:rlist){%>
								 <option value="<%=bean.getAd_relation_id()%>"><%=bean.getRelation() %></option>
								 <%}
								 	}%>
								
						</select>
						</td>
						
						<td>&nbsp;Gender</td>
						<td class="" >:</td>
						<td>	<select  name="nominee_ad_gender_id_1" id="nominee_ad_gender_id_1" style="width: 170px;" data-placeholder="Gender" class="chosen-select"  tabindex="2">
								 <option value="<%= ngid%>"><%=ngname %></option>
								  <%GenderDao gdao=new GenderDao();
								 	ArrayList<GenderBean> glist=gdao.getAllGender();
								 	if(glist.isEmpty()!=true){
								 	for(GenderBean bean:glist){%>
								 <option value="<%=bean.getAd_gender_id()%>"><%=bean.getGender() %></option>
								 <%}
								 	}%>
								
						</select>
						</td>
						</tr>
					<tr >
						<td class="" >&nbsp;DOB</td>
						<td class="" >:</td>
						<% 
						String nominee_dob="";
						
						if(dob!=null){
          				    nominee_dob=new SimpleDateFormat("yyyy-MM-dd").format(dob.getTime());
          				  
          				    
						}
       					 %>
						<td>	<input type="date"  name="nominee_dob_1" value="<%=nominee_dob %>" id="nominee_dob_1" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Age</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="nominee_age_1" value="<%=age %>" id="nominee_age_1" readonly="readonly" style="width: 170px;">
						</td>
						
						
					
						</tr>
						
  
    	
        <tr height="100px" >
  
    	
        <th  style="color:#363" align="center" width="150px" valign="top"  colspan="3">
        <table>
        	<tr>
        		<th><img height="100" width="100" id='nominee_photo_view_1' style="background-size:cover;border: 1px gray;" /> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>Photo</th>
        	</tr>
        	<tr>
        	<th><input type="file" name="nominee_photo_1" id="nominee_photo_1" style="width:100px;" /></th>
        	</tr>
        </table>
        </th>
       	
        <th  style="color:#363" align="center" width="150px" valign="top"   colspan="3">
         <table>
        	<tr>
        		<th><img height="100" width="100" id="nominee_id_proof_view_1" style="background-size:cover;border: 1px gray;" /> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>ID Proof
        		</th>
        		</tr>
        		<tr>
        		<th><input type="file" name="nominee_id_proof_1" id="nominee_id_proof_1" style="width:100px;" /></th>
        	</tr>
  
  
        </table>
        
        </th>
      
   
    	
        <th  style="color:#363" align="center" width="150px" valign="top" colspan="3">
        
         <table>
        	<tr>
        		<th><img height="100" width="100"  id="nominee_sign_view_1" style="background-size:cover;border: 1px gray;"/> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>Sign
        		</th>
        		</tr>
        		<tr>
        		<th>
        		<input type="file" name="nominee_sign_1" id="nominee_sign_1" style="width:100px;" required="required" /></th>
        	</tr>
        </table>
        </th>
       
        
        
 </tr>
  		<tr><%
  		
  		  name="";
		  dob =null;
		  age ="";
		 nrid=0;
		 nrelation="";
		 ngid=0;
		 ngname="";
		 sailutationid=0;
		 sailutationname="";
		
		if(member.getNominee().size()>1){
			NominationBean nominee1=member.getNominee().get(1); 
			name=nominee1.getName();
			nrid=nominee1.getRelation().getAd_relation_id();
			nrelation=nominee1.getRelation().getRelation();
			ngid=nominee1.getGender().getAd_gender_id();
			ngname=nominee1.getGender().getGender() ;
			if(nominee1.getDob()!=null){
				dob=nominee1.getDob();
			}
			age=nominee1.getAge();
			sailutationid=nominee1.getSalutation().getAd_salutation_id();
			sailutationname=nominee1.getSalutation().getName();
		}
  		
  		
  		
  		
  		%>
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
						<select  name="nominee_ad_salutation_id_2" id="nominee_ad_salutation_id_2" style="width: 80px;" data-placeholder="Mr." class="chosen-select"  tabindex="2">
								 <option value="<%= sailutationid%>"><%=sailutationname%></option>
								  <%
								 SalutationDao sdao2=new SalutationDao();
								 ArrayList<SalutationBean> slist2=sdao2.getAllSalutation();
								 if(slist2.isEmpty()!=true){
									 for(SalutationBean bean:slist2){%>
									 <option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
																		 
									 <%	 }
								 }%>
								</select>
								<input type="text"  name="nominee_name_2" value="<%=name%>" id="nominee_name_2" style="width: 420px;">
						</td>
						</tr>
									<tr>
						<td class="" >&nbsp;Relation</td>
						<td class="" >:</td>
						<td>	<select  name="nominee_ad_relation_id_2" id="nominee_ad_relation_id_2" style="width: 170px;" data-placeholder="Choose a Level..." class="chosen-select"  tabindex="2">
						 		<option value="<%=nrid%>"><%=nrelation %></option>
								  <%RelationDao rdao2=new RelationDao();
								 	ArrayList<RelationBean> rlist2=rdao2.getAllRelation();
									 if(rlist2.isEmpty()!=true){
								 	for(RelationBean bean:rlist2){%>
								 <option value="<%=bean.getAd_relation_id()%>"><%=bean.getRelation() %></option>
								 <%}
								 	}%>
								
						</select>
						</td>
						
						<td>&nbsp;Gender</td>
						<td class="" >:</td>
						<td>	<select  name="nominee_ad_gender_id_2" id="nominee_ad_gender_id_2" style="width: 170px;" data-placeholder="Choose a Level..." class="chosen-select"  tabindex="2">
						 <option value="<%=ngid%>"><%=ngname%></option>
								 <%GenderDao gdao1=new GenderDao();
								 	ArrayList<GenderBean> glist1=gdao1.getAllGender();
									 if(glist1.isEmpty()!=true){
								 	for(GenderBean bean:glist1){%>
								 <option value="<%=bean.getAd_gender_id()%>"><%=bean.getGender() %></option>
								 <%}
								 	}%>
								
						</select>
						</td>
						
					
						</tr>
					<tr >
						<td class="" >&nbsp;DOB</td>
						<td class="" >:</td>
						<%
					
						
						if(dob!=null){
          				    nominee_dob=new SimpleDateFormat("yyyy-MM-dd").format(dob.getTime());
          				  
          				    
						}
						
						%>
						<td>	<input type="date"  name="nominee_dob_2" value="<%=nominee_dob %>" id="nominee_dob_2" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Age</td>
						<td class="" >:</td>
						<td>	<input type="number"  name="nominee_age_2" value="<%=age %>" id="nominee_age_2" readonly="readonly" style="width: 170px;">
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
        	<tr>
        	<th><input type="file" name="nominee_photo_2" id="nominee_photo_2" style="width:100px;" required="required"/></th>
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
        		<tr>
        		<th><input type="file" name="nominee_id_proof_2" id="nominee_id_proof_2" style="width:100px;" required="required"/></th>
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
        		<tr>
        		<th>
        		<input type="file" name="nominee_sign_2" id="nominee_sign_2" style="width:100px;" required="required" /></th>
        	</tr>
        </table>
        </th>
       
        
        
 </tr>
<tr>					
					<td colspan="9" align="center">
						<input type="Button" name="next4" value="Next" style="width: 70px;height: 25px;"/>&nbsp;&nbsp;
						<input type="reset" name="Clear" style="width: 70px;height: 25px;"/>
					</td>
					</tr>						
					
					<tr>
					<td colspan="7" class=""  >&nbsp;</td>
					</tr>
	              
	            </table>	
  </div>
  <div id="tabs-5">
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
						<select name="witness_ad_member_id" id="witness_ad_member_id" style="width: 170px;" data-placeholder="Choose a Level..." class="chosen-select"  tabindex="2">
							<%
							MemberRegistrationDao memberdao=new MemberRegistrationDao();
							MemberRegistrationBean witness=memberdao.getMemberRegistrationById(member.getAd_witness_id());%>
							<option value="<%=witness.getAd_member_id()%>"><%=witness.getName() %></option>
							<%
							ArrayList<MemberRegistrationBean> memberlist=memberdao.getAllMemberRegistration();
							if(memberlist!=null){
								for(MemberRegistrationBean bean:memberlist){%>
									<option value="<%=bean.getAd_member_id()%>"><%=bean.getName() %></option>
								<%}
							}%>
						</select>
								 
						</td>
						<td>&nbsp;Mem.No</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="witness_ad_society_id" value="<%=witness.getAd_society_no() %>" id="witness_ad_society_id" style="width: 170px;">
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Salutation</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="witness_ad_salutation_id" value="<%=witness.getSalutation().getName() %>" id="witness_ad_salutation_id" style="width: 170px;">
								 
						</td>
						
					
						<td class="" >&nbsp;Name</td>
						<td class="" >:</td>
						<td>	
						<input type="text"  name="witness_name"  id="witness_name" value="<%=witness.getName() %>" style="width: 170px;">
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Mobile</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="witness_mobile" value="<%=witness.getAddress().get(0).getMobile() %>" id="witness_mobile" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Address</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="witness_address" value="<%= witness.getAddress().get(0).getLine1()+witness.getAddress().get(0).getLine2()+witness.getAddress().get(0).getCity().getCity()+witness.getAddress().get(0).getState().getState() %>" id="witness_address" style="width: 170px;">
						</td>
					</tr>
					

<tr>					
					<td colspan="9" align="center">
						<input type="submit" name="Submit" value="Update" style="width: 70px;height: 25px;"/>&nbsp;&nbsp;
						<input type="reset" name="Clear" style="width: 70px;height: 25px;"/>
					</td>
					</tr>						
					
					<tr>
					<td colspan="7" class=""  >&nbsp;</td>
					</tr>
	              
	            </table>	
	            
  </div>
   			
</div>	
	            
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
