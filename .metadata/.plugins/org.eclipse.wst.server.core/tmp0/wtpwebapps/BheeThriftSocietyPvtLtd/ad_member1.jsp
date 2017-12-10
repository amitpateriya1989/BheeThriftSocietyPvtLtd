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
	$("#nominee2").hide();
	$(function() {
	    $( "#tabs" ).tabs();
	  });
	
	$("#second_nominee").on("click", function() {
		
		if($(this).is(':checked')){
	    $("#nominee2").show();
		}
		else{
			$("#nominee2").hide();
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

     });   
	 
	 
	 });  
  $("#dob").change(function(e){
	  var dataString = "date="+$("#dob").val();
		 $.ajax({
	         type: "POST",
	         url: "Ajax/calculate_retirement_date.jsp",
	         data: dataString,      
	       
	         success: function( data, textStatus, jqXHR) {
	            
	             $("#dor").val(data.trim());
	          //  alert(data);
	         },
	        
	       
	         error: function(jqXHR, textStatus, errorThrown){
	              console.log("Something really bad happened " + textStatus);
	               $("#ajaxResponse").html(jqXHR.responseText);
	         }

	     }); 
		 
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
<form id='frm' action="AdMemberRegistration?action=insert" method="post" enctype="multipart/form-data" style="border:1px solid red">



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
    <li><a href="#tabs-6">Fees</a></li>  
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
						String ad_pf_no=request.getParameter("ad_pf_no");
						int ad_society_no=Integer.parseInt(request.getParameter("ad_society_no"));
						%>
						
						<td>&nbsp;PFNo</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_pf_id" id="ad_pf_id" value="<%=ad_pf_no%>" style="width: 170px;" >
								 
						</td>
						<td>&nbsp;Mem.No</td>
						<td class="" >:</td>
						<td>	<input type="text" class="number" name="ad_society_id" id="ad_society_id" value="<%=ad_society_no%>" readonly="readonly" style="width: 170px;" required="required">
								 
						</td>
						</tr>
						
						<tr >
						<td>&nbsp;Type</td>
						<td class="" >:</td>
						<td>	<select name="ad_member_type_id" id="ad_member_type_id" style="width: 170px;" data-placeholder="Type" class="chosen-select" style="width:250px;" tabindex="2">
									
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
						<td colspan="4"><select  name="ad_salutation_id" id="ad_salutation_id" style="width: 80px;" data-placeholder="Select" class="chosen-select" style="width:250px;" tabindex="2">
								 <%SalutationDao dao=new SalutationDao();
								 	ArrayList<SalutationBean> alist=dao.getAllSalutation();
								 	for(SalutationBean bean:alist){%>
								 <option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
								 <%} %>
								</select>	
								<input class="alpha" type="text"  name="name" id="name" style="width: 375px;" required="required">
						</td>
						</tr><tr>
						<td class="" >&nbsp;Father</td>
						<td class="" >:</td>
						<td>	<input class="alpha" type="text"  name="father" id="father" style="width: 170px;" >
						</td>
						<td class="" >&nbsp;Husband</td>
						<td class="" >:</td>
						<td>	<input type="text" class="alpha"  name="husband" id="husband" style="width: 170px;">
						</td>
						<td class="" >&nbsp;DOB</td>
						<td class="" >:</td>
						<td>	<input type="date"  name="dob" id="dob" style="width: 170px;" required="required">
						</td>
					</tr>
					<tr >
						
			
						<td>&nbsp;Gender</td>
						<td class="" >:</td>
						<td>	<select  name="ad_gender_id" id="ad_gender_id" style="width: 170px;" data-placeholder="Choose a Gender..." class="chosen-select" style="width:250px;" tabindex="2">
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
        		<th><img height="100" width="100" id='photo_view' style="background-image:url(Image/emp.png);background-size:cover;border: 1px solid gray;" /> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>Photo</th>
        	</tr>
        	<tr>
        	<th><input type="file" name="photo" id="photo" style="width:100px;" required="required"/></th>
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
        		<tr>
        		<th><input type="file" name="id" id="id_proof" style="width:100px;" required="required"/></th>
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
        		<tr>
        		<th>
        		<input type="file" name="sign" id="sign" style="width:100px;" required="required" /></th>
        	</tr>
        </table>
        </th>
       
        
        
 </tr>
<tr>					
					<td colspan="9" align="center">
						<!-- <input type="Button" name="next1" id='next1' value="Next" style="width: 70px;height: 25px;"/>&nbsp;&nbsp;
						<input type="reset" name="Clear" style="width: 70px;height: 25px;"/> -->
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
						<td>	<input type="text"  class="phone" name="mobile" id="mobile" maxlength="10" style="width: 170px;" required="required">
						</td>
						<td class="" >&nbsp;Phone</td>
						<td class="" >:</td>
						<td>	<input type="text" class="phone" name="phone" id="phone" maxlength="10" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Email</td>
						<td class="" >:</td>
						<td>	<input type="email" class="email"  name="email" id="email" style="width: 170px;">
						</td>
					</tr>
					<tr >
						<td>&nbsp;Lane 1</td>
						<td class="" >:</td>
						<td>	<input type="text" class="address" name="line1" id="line1" style="width: 170px;">
								 
						</td>
						<td>&nbsp;Lane 2</td>
						<td class="" >:</td>
						<td>	<input type="text" class="address"  name="line2" id="line2" style="width: 170px;">
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Country</td>
						<td class="" >:</td>
						<td>	<select  name="ad_country_id" id="ad_country_id" style="width: 170px;" data-placeholder="Choose a Country..." class="chosen-select" style="width:250px;" tabindex="2">
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
								 <option value="0">--Select--</option>
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
								 <option value="0">--Select--</option>
								</select>
						</td>
						<td class="" >&nbsp;City</td>
						<td class="" >:</td>
						<td>	<select  name="ad_city_id" id="ad_city_id" style="width: 170px;" data-placeholder="Choose a City..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="0">--Select--</option>
								</select>
						</td>
						<td class="" >&nbsp;Pincode</td>
						<td class="" >:</td>
						<td>	<input type="text" class="zipcode"  name="pincode" id="pincode" maxlength="6" style="width: 170px;">
						
						</td>
						</tr>
						
						<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="3" style="text-align: left;font-weight: bold;font-size: 14px;" >Permanent Address</td>
					<td colspan="6" align="right">Same As Above
					<input type="checkbox" name="same" id="same" style="width: 15px; height: 15px"></td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
						<td class="" >&nbsp;Mobile</td>
						<td class="" >:</td>
						<td>	<input type="text" class="phone"  name="mobile1" id="mobile1" maxlength="10" style="width: 170px;" required="required">
						</td>
						<td class="" >&nbsp;Phone</td>
						<td class="" >:</td>
						<td>	<input type="text" class="phone"  name="phone1" id="phone1" maxlength="10" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Email</td>
						<td class="" >:</td>
						<td>	<input type="email" class="email"  name="email1" id="email1" style="width: 170px;">
						</td>
					</tr>
					<tr >
						<td>&nbsp;Lane 1</td>
						<td class="" >:</td>
						<td>	<input type="text" class="address" name="line1_1" id="line1_1" style="width: 170px;">
								 
						</td>
						<td>&nbsp;Lane 2</td>
						<td class="" >:</td>
						<td>	<input type="text" class="address"  name="line2_1" id="line2_1" style="width: 170px;">
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Country</td>
						<td class="" >:</td>
						<td>	<select  name="ad_country_id_1" id="ad_country_id_1" style="width: 170px;" data-placeholder="Choose a Country..." class="chosen-select" style="width:250px;" tabindex="2">
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
								 <option value="0">--Select--</option>
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
								 <option value="0">--Select--</option>
								</select>
						</td>
						<td class="" >&nbsp;City</td>
						<td class="" >:</td>
						<td>	<select  name="ad_city_id_1" id="ad_city_id_1" style="width: 170px;" data-placeholder="Choose a City..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="0">--Select--</option>
								</select>
						</td>
						<td class="" >&nbsp;Pincode</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="pincode1" id="pincode1"  maxlength="6" style="width: 170px;">
						
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
						<select  name="branch_id" id="ad_branch_id" style="width: 370px;" data-placeholder="Choose a City..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="0">--Select--</option>
								<%BankBranchDao bankdao=new BankBranchDao();
								  ArrayList <BankBranchBean> banklist=bankdao.getAllBankBranchName();
								  if(banklist.isEmpty()!=true){
								  for(BankBranchBean bean:banklist){
								 // String city=new CityDao().getCityNameById(bean.getCity().getAd_city_id()).getCity().trim();
								//  String state=new StateDao().getStateNameById(bean.getState().getAd_state_id()).getState().trim();
								  %>
								  
								  <option value="<%=bean.getAd_branch_id()%>"><%=bean.getBranch_code()+" "+bean.getBranch().trim() %></option>
									  
								 <%} }%>
						</select>
						</td>
						
					
						
					
						
						
					</tr>
					<tr >
											
						<td class="" >&nbsp;Region</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_bank_region_id" id="ad_bank_region_id" style="width: 170px;" readonly="readonly" >
						</td>
					
						<td>&nbsp;Code</td>
						<td class="" >:</td>
						<td>	<input type="text" name="branch_code" id="branch_code" style="width: 170px;" readonly="readonly">
								 
						</td>
						<td>&nbsp;IFSC</td>
						<td class="" >:</td>
						<td>	<input type="text" name="ifsc_code" id="ifsc_code" style="width: 170px;" readonly="readonly">
							
					</tr>
					<tr >
						<td>&nbsp;State</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_bank_state_id" id="ad_bank_state_id" style="width: 170px;" readonly="readonly">
					
						</td>
						
					
						
					
						<td class="" >&nbsp;District</td>
						<td class="" >:</td>
						<td>	<input type="text" name="ad_bank_district_id" id="ad_bank_district_id" style="width: 170px;" readonly="readonly" >
								
						</td>
						
						<td class="" >&nbsp;City</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_bank_city_id" id="ad_bank_city_id" readonly="readonly" style="width: 170px;" >
								
						</td>
						
					</tr>
					
					<tr>
							 
						</td>
						<td class="" >&nbsp;Pincode</td>
						<td class="" >:</td>
						<td>	<input type="text" name="bank_pincode" id="bank_pincode" readonly="readonly" style="width: 170px;">
						</td>
						
					
						<td>&nbsp;Email</td>
						<td class="" >:</td>
						<td>	<input type="text" name="email_id" id="email_id" readonly="readonly" style="width: 170px;">
							 
						</td>
					</tr>
					<tr>
						<td class="" >&nbsp;Phone</td>
						<td class="" >:</td>
						<td>	<input type="text" name="phone_no" id="phone_no" readonly="readonly" style="width: 170px;">
						</td>
						
					
						<td>&nbsp;Person</td>
						<td class="" >:</td>
						<td>	<input type="text" name="contact_person" id="contact_person" readonly="readonly" style="width: 170px;">
								 
						</td>
						<td class="" >&nbsp;Contact</td>
						<td class="" >:</td>
						<td>	<input type="text" name="contact_no" id="contact_no" readonly="readonly" style="width: 170px;">
						</td>
					</tr>
					<tr>
						<td class="" >&nbsp;Address</td>
						<td class="" >:</td>
						<td colspan="7">	<input type="text" name="address" id="address" readonly="readonly" style="width: 765px;">
						</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
							 
						</td>
						<td class="" >&nbsp;Pan No</td>
						<td class="" >:</td>
						<td>	<input class="panno" type="text" name="pan_no" id="pan_no"  style="width: 170px;">
						</td>
						
					
						<td>&nbsp;Aadhar</td>
						<td class="" >:</td>
						<td>	<input type="text" class="addharno" name="aadhar_no" id="aadhar_no"  style="width: 170px;">
							 
						</td>
						
						<td class="" >&nbsp;Department</td>
						<td class="" >:</td>
						<td>	<select  name="ad_department_id" id="ad_department_id"  style="width: 170px;" data-placeholder="Choose a Department..." class="chosen-select"  tabindex="2">
									<option value="0">--Select--</option>
									<%
									DepartmentDao deptdao=new DepartmentDao();
									ArrayList<DepartmentBean> deptlist=deptdao.getAllDepartmentName();
									if(deptlist!=null){
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
									<option value="0">--Select--</option>
									<%
									DesignationDao desigdao=new DesignationDao();
									ArrayList<DesignationBean> desglist=desigdao.getAllDesignationName();
									if(desglist!=null){
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
									<option value="0">--Select--</option>
									<%
									DesignationLevelDao leveldao=new DesignationLevelDao();
									ArrayList<DesignationLevelBean> levellist=leveldao.getAllDesignationLevel();
									if(desglist!=null){
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
									<option value="0">--Select--</option>
									<%
									DesignationTypeDao typedao=new DesignationTypeDao();
									ArrayList<DesignationTypeBean> typelist=typedao.getAllDesignationType();
									if(desglist!=null){
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
						<td>	<input type="date" name="doa" id="doa"  style="width: 170px; " required="required">
						</td>
						<td class="" >&nbsp;Joining</td>
						<td class="" >:</td>
						<td>	<input type="date" name="doj" id="doj"  style="width: 170px;" required="required">
						</td>
						<td class="" >&nbsp;Retirement</td>
						<td class="" >:</td>
						<td>	<input type="date" name="dor" id="dor"  style="width: 170px;" required="required" readonly="readonly">
						</td>
					</tr>
					
					<tr>
						<td class="" >&nbsp;Service Duration</td>
						<td class="" >:</td>
						<td>	<input type="text" class="number" name="service_duration" id="service_duration"  style="width: 170px;" required="required">
						</td>
						<td class="" >&nbsp;A/C No</td>
						<td class="" >:</td>
						<td>	<input type="text" class="number" name="saving_ac_no" id="saving_ac_no"  style="width: 170px;" required="required">
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
						<select  name="nominee_ad_salutation_id_1" id="nominee_ad_salutation_id_1" style="width: 80px;" data-placeholder="Select" class="chosen-select"  tabindex="2">
								 
								 <%
								 SalutationDao salutationdao=new SalutationDao();
								 ArrayList<SalutationBean> salutationlist=salutationdao.getAllSalutation();
								 if(salutationlist!=null){
									 for(SalutationBean bean:salutationlist){%>
									 <option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
																		 
									 <%	 }
								 }%>
								</select>	<input type="text" class="alpha"  name="nominee_name_1" id="nominee_name_1" style="width: 420px;">
						</td>
						
						
						</tr>
						<tr>
							<td class="" >&nbsp;Relation</td>
						<td class="" >:</td>
						<td>	<select  name="nominee_ad_relation_id_1" id="nominee_ad_relation_id_1" style="width: 170px;" data-placeholder="Relation" class="chosen-select"  tabindex="2">
								 
								  <%RelationDao rdao=new RelationDao();
								 	ArrayList<RelationBean> rlist=rdao.getAllRelation();
								 	for(RelationBean bean:rlist){%>
								 <option value="<%=bean.getAd_relation_id()%>"><%=bean.getRelation() %></option>
								 <%} %>
								
						</select>
						</td>
						
						<td>&nbsp;Gender</td>
						<td class="" >:</td>
						<td>	<select  name="nominee_ad_gender_id_1" id="nominee_ad_gender_id_1" style="width: 170px;" data-placeholder="Gender" class="chosen-select"  tabindex="2">
								 
								  <%GenderDao gdao=new GenderDao();
								 	ArrayList<GenderBean> glist=gdao.getAllGender();
								 	for(GenderBean bean:glist){%>
								 <option value="<%=bean.getAd_gender_id()%>"><%=bean.getGender() %></option>
								 <%} %>
								
						</select>
						</td>
						</tr>
					<tr >
						<td class="" >&nbsp;DOB</td>
						<td class="" >:</td>
						<td>	<input type="date"  name="nominee_dob_1" id="nominee_dob_1" style="width: 170px;" required="required">
						</td>
						<td class="" >&nbsp;Age</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="nominee_age_1" id="nominee_age_1" readonly="readonly" style="width: 170px;" class="number" required="required">
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
        	<tr>
        	<th><input type="file" name="nominee_photo_1" id="nominee_photo_1" style="width:100px;" /></th>
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
        		<tr>
        		<th><input type="file" name="nominee_id_proof_1" id="nominee_id_proof_1" style="width:100px;" /></th>
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
        		<tr>
        		<th>
        		<input type="file" name="nominee_sign_1" id="nominee_sign_1" style="width:100px;"  /></th>
        	</tr>
        </table>
        </th>
       
        
        
 </tr>
  		<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="3" style="font-size: 14px;font-weight: bold;" >Second Nominee</td>
					<td colspan="6" align="right">
					<input type="checkbox" name="second_nominee" id="second_nominee" value="second" style="width: 15px; height: 15px">
					</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="9" width="100%">
					<div id="nominee2">
					  <table cellpadding="5"  width="100%" style="border: 0px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
						<tr>
						
					
						<td class="" >&nbsp;Name</td>
						<td class="" >:</td>
						<td colspan="7">
						<select  name="nominee_ad_salutation_id_2" id="nominee_ad_salutation_id_2" style="width: 80px;" data-placeholder="Select" class="chosen-select"  tabindex="2">
								 
								  <%
								 SalutationDao sdao2=new SalutationDao();
								 ArrayList<SalutationBean> slist2=sdao2.getAllSalutation();
								 if(slist2!=null){
									 for(SalutationBean bean:slist2){%>
									 <option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
																		 
									 <%	 }
								 }%>
								</select>
								<input type="text" class="alpha"  name="nominee_name_2" id="nominee_name_2" style="width: 420px;">
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Relation</td>
						<td class="" >:</td>
						<td>	<select  name="nominee_ad_relation_id_2" id="nominee_ad_relation_id_2" style="width: 170px;" data-placeholder="Choose a Level..." class="chosen-select"  tabindex="2">
						
								  <%RelationDao rdao2=new RelationDao();
								 	ArrayList<RelationBean> rlist2=rdao2.getAllRelation();
								 	for(RelationBean bean:rlist2){%>
								 <option value="<%=bean.getAd_relation_id()%>"><%=bean.getRelation() %></option>
								 <%} %>
								
						</select>
						</td>
						
						<td>&nbsp;Gender</td>
						<td class="" >:</td>
						<td>	<select  name="nominee_ad_gender_id_2" id="nominee_ad_gender_id_2" style="width: 170px;" data-placeholder="Choose a Level..." class="chosen-select"  tabindex="2">
						
								 <%GenderDao gdao1=new GenderDao();
								 	ArrayList<GenderBean> glist1=gdao1.getAllGender();
								 	for(GenderBean bean:glist1){%>
								 <option value="<%=bean.getAd_gender_id()%>"><%=bean.getGender() %></option>
								 <%} %>
								
						</select>
						</td>
						
					
						</tr>
						<tr>
						<td class="" >&nbsp;DOB</td>
						<td class="" >:</td>
						<td>	<input type="date"  name="nominee_dob_2" id="nominee_dob_2" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Age</td>
						<td class="" >:</td>
						<td>	<input type="number" class="number" name="nominee_age_2" id="nominee_age_2" style="width: 170px;">
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
        								<th><input type="file" name="nominee_photo_2" id="nominee_photo_2" style="width:100px;" /></th>
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
						        		<th><input type="file" name="nominee_id_proof_2" id="nominee_id_proof_2" style="width:100px;" />
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
        								<tr>
        									<th>
        										<input type="file" name="nominee_sign_2" id="nominee_sign_2" style="width:100px;" />
        									</th>
        								</tr>
        						</table>
        					</th>
       
        
        
 						</tr></table>
 					</div></td></tr>
						<tr>					
							<td colspan="9" align="center">
								<!-- <input type="Button" name="next4" value="Next" style="width: 70px;height: 25px;"/>&nbsp;&nbsp;
								<input type="reset" name="Clear" style="width: 70px;height: 25px;"/> -->
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
							<option value="0">--Select--</option>
							<%
							MemberRegistrationDao memberdao=new MemberRegistrationDao();
							ArrayList<MemberRegistrationBean> memberlist=memberdao.getAllMemberlist();
							if(memberlist!=null){
								for(MemberRegistrationBean bean:memberlist){%>
									<option value="<%=bean.getAd_member_id()%>"><%=bean.getName() %></option>
								<%}
							}%>
						</select>
								 
						</td>
						<td>&nbsp;Mem.No</td>
						<td class="" >:</td>
						<td>	<input type="text" class="number"  name="witness_ad_society_id" id="witness_ad_society_id" style="width: 170px;">
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Salutation</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="witness_ad_salutation_id" id="witness_ad_salutation_id" style="width: 170px;">
								 
						</td>
						
					
						<td class="" >&nbsp;Name</td>
						<td class="" >:</td>
						<td>	<input type="text" class="alpha"  name="witness_name" id="witness_name" style="width: 170px;">
						</td>
						</tr><tr>
						<td class="" >&nbsp;Mobile</td>
						<td class="" >:</td>
						<td>	<input type="text" class="phone"  name="witness_mobile" id="witness_mobile" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Address</td>
						<td class="" >:</td>
						<td>	<input type="text" class="address" name="witness_address" id="witness_address" style="width: 170px;">
						</td>
					</tr>
					

<tr>					
					<td colspan="9" align="center">
						<!-- <input type="Button" name="next5" value="Next" style="width: 70px;height: 25px;"/>&nbsp;&nbsp;
						<input type="reset" name="Clear" style="width: 70px;height: 25px;"/> -->
					</td>
					</tr>						
					
					<tr>
					<td colspan="7" class=""  >&nbsp;</td>
					</tr>
	              
	            </table>	
  </div>
    <div id="tabs-6">
    <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Registration Fees Detail</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<%
						MemberRegistrationMasterBean bean=new MemberRegistrationMasterDao().getMemberRegistrationMasterByMaxId();
					%>
					<tr >
						<td>&nbsp;Membership Fess</td>
						<td class="" >:</td>
						<td>	<input type="text" class="amt"  name="memfees" id="membership_fees" value="<%=bean.getMembership_fees() %>"  style="width: 170px;" required="required"/>
								 
						</td>
						<td>&nbsp;FGDS Fund</td>
						<td class="" >:</td>
						<td>	<input type="text" class="amt" name="fgds_fund" id="fgds_fund" value="<%=bean.getFgds_fund() %>"  style="width: 170px;" required="required"/>
								 
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;DCF</td>
						<td class="" >:</td>
						<td>	<input type="text" class="amt" name="dcf" id="dcf" value="<%=bean.getDcf() %>" style="width: 170px;" required="required"/>
								
						</td>
						<td class="" >&nbsp;No of Share</td>
						<td class="" >:</td>
						<td>	<input type="text" class="number" name="share_qty" id="share_qty" value="<%=bean.getShare() %>"  style="width: 170px;"  required="required"/>
								
						</td>
						
						</tr>
						<tr>
						<td class="" >&nbsp;Share Amt</td>
						<td class="" >:</td>
						<%
						ShareBean sbean=new ShareDao().getShareRate();%>
						<td>	<input type="text" class="amt" name="share_amt" id="share_amt" value="<%=bean.getShare()*sbean.getPer_share_rate() %>"  style="width: 170px;" required="required"/>
						<%
							double fess=bean.getMembership_fees()+bean.getFgds_fund()+bean.getDcf()+(bean.getShare()*10);
						
						%>	
						</td>
						<td class="" >&nbsp;Admission Fees</td>
						<td class="" >:</td>
						<td>	<input type="text" class="amt" name="admission_fees" id="admission_fees" value="<%=fess %>" style="width: 170px;" required="required"/> 
						</td>
						</tr>
<tr>					
					<td colspan="9" align="center">
					
						<button name="submit" id="submit" onclick="return form_submit()" value="submit" style="width: 70px;height: 25px;">Submit</button>
						&nbsp;&nbsp;
						
						
					</td>
					</tr>						
					
					<tr>
					<td colspan="7" class=""  >&nbsp;</td>
					</tr>
	              
	            </table>
	           	
  
  
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
   function form_submit(){
	   
	  if($("#name").val()==""){
		  
		  alert("Please Enter Member Name....");
		 return false;
	  } 
	   
if($("#father").val()==""){
		  
		  alert("Please Enter father Name....");
		 return false;
	  } 
if($("#dob").val()==""){
	  
	  alert("Please Enter Date Of Birht....");
	 return false;
} 
if($("#ad_gender_id").val()==""){
	  
	  alert("Please select Gender....");
	 return false;
} 
if($("#ad_category_id").val()==""){
	  
	  alert("Please select Category....");
	 return false;
} 
if($("#photo").val()==""){
	  
	  alert("Please select photo....");
	 return false;
} 
if($("#id_proof").val()==""){
	  
	  alert("Please select id....");
	 return false;
}  
if($("#sign").val()==""){
	  
	  alert("Please select Sign....");
	 return false;
}  
if($("#mobile").val()==""){
	  
	  alert("Please Enter Mobile No...");
	 return false;
}  
if($("#phone").val()==""){
	  
	  alert("Please Enter Phone No....");
	 return false;
}  
if($("#email").val()==""){
	  
	  alert("Please select email....");
	 return false;
}  if($("#ad_country_id").val()==""){
	  
	  alert("Please select country....");
	 return false;
}  
if($("#ad_state_id").val()=="0"){
	  
	  alert("Please select state....");
	 return false;
}  
if($("#ad_district_id").val()=="0"){
	  
	  alert("Please select District....");
	 return false;
}  
if($("#ad_city_id").val()=="0"){
	  
	  alert("Please select city....");
	 return false;
}  
if($("#pincode").val()==""){
	  
	  alert("Please Enter Pin Code....");
	 return false;
}  

if($("#phone1").val()==""){
	  
	  alert("Please Enter Permanent Phone No....");
	 return false;
}  
if($("#email1").val()==""){
	  
	  alert("Please select Permanent email....");
	 return false;
}  if($("#ad_country_id1").val()==""){
	  
	  alert("Please select Permanent country....");
	 return false;
}  
if($("#ad_state_id1").val()=="0"){
	  
	  alert("Please select Permanent state....");
	 return false;
}  
if($("#ad_district_id1").val()=="0"){
	  
	  alert("Please select Permanent District....");
	 return false;
}  
if($("#ad_city_id1").val()=="0"){
	  
	  alert("Please  select Permanent city....");
	 return false;
}  
if($("#pincode1").val()==""){
	  
	  alert("Please Enter Permanent Pin Code....");
	 return false;
}  
if($("#ad_branch_id").val()=="0"){
	  
	  alert("Please select Branch....");
	 return false;
}  
if($("#pan_no").val()==""){
	  
	  alert("Please enter pan no....");
	 return false;
}  
if($("#aadhar_no").val()==""){
	  
	  alert("Please enter Aadhar no....");
	 return false;
} 
if($("#ad_department_id").val()=="0"){
	  
	  alert("Please Select Department no....");
	 return false;
}  
if($("#ad_designation_id").val()=="0"){
	  
	  alert("Please Select Designation....");
	 return false;
}  
if($("#ad_designation_level_id").val()=="0"){
	  
	  alert("Please Select Designation Level ....");
	 return false;
} 
if($("#ad_designation_type_id").val()=="0"){
	  
	  alert("Please Select Designation Type ....");
	 return false;
}  
if($("#doj").val()==""){
	  
	  alert("Please Select Date of Joining ....");
	 return false;
}  
if($("#doa").val()==""){
	  
	  alert("Please Select Date of Appointment ....");
	 return false;
}  
if($("#dor").val()==""){
	  
	  alert("Please Select Date of Retirment ....");
	 return false;
}

if($("#service_duration").val()==""){
	  
	  alert("Please Enter Service Duration ....");
	 return false;
}  
if($("#saving_ac_no").val()==""){
	  
	  alert("Please Enter Saving A/c No  ....");
	 return false;
}  
if($("#nominee_name_1").val()==""){
	  
	  alert("Please Enter  Nominee Name  ....");
	 return false;
}  
if($("#nominee_dob_1").val()==""){
	  
	  alert("Please Enter  Nominee Date OF Birth  ....");
	 return false;
}  
if($("#witness_ad_member_id").val()==""){
	  
	  alert("Please Select Witness  ....");
	 return false;
}  


    document.getElementById("frm1").submit();
    }
</script>   
