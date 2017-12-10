<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%@page import="Model.Bean.BankBranchViewBeen"%>
<%@page import="Model.Dao.BankBranchDao"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2-bootstrap.css"/>
<head>
<%@ include file= "Layout/header.jsp" %>
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
			<li><a href="#">Update Member Branch</a><i class="fa fa-angle-right"></i>Edit</li>
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
	
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Edit Member Branch</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmMemberBranch" autocomplete="off" action="AdMemberRegistration?action=branchupdate" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="20%">Select Member : <span class="red">*</span></td>
					<td width="30%"><select  class="input-large form-control" name="ad_member_id" id="ad_member_id" >
								<option value="">----Select Member----</option>
								<%MemberRegistrationDao dao =new MemberRegistrationDao();
					   ArrayList<MemberRegistrationBean> alist=dao.getAllMemberlist();
					   if(alist!=null){
					   for(MemberRegistrationBean bean:alist){%>
					   <option value="<%=bean.getAd_member_id()%>"> <%=bean.getAd_society_no() %> | <%=bean.getName() %></option>
					   <%} 
					  }%>
						</select>
						<label class="error"></label>
					
					</td>
					 <td width="20%"> Member Branch : <span class="red">*</span></td>
					<td width="30%">
					<select  class="input-large form-control" name="ad_branch_id" id="ad_branch_id" >								
								<option value="">----Select Branch ----</option>
								<%BankBranchDao bankdao=new BankBranchDao();
								  ArrayList <BankBranchBean> banklist=bankdao.getAllBankBranchName();
								  if(banklist.isEmpty()!=true){
								  for(BankBranchBean bean:banklist){
								  %>
								  <option value="<%=bean.getAd_branch_id()%>"><%=bean.getBranch_code()+" | "+bean.getBranch().trim() %></option>
								 <%} }%>
						
								
						</select>
					<label class="error"></label>
					</td>		 	
				
			   </tr>
				 
				 <tr>
					<td></td>
					<td colspan="3">
					  <input class="btn  blue" type="submit" name="Update" value="submit"/>
					  <input class="btn btn-md green" type="reset" name="Clear"/>
					</td>
				 </tr>
			</table>
			
			<table class="table table-bordered">
	<thead>
			<tr><th colspan="4">Service Detail</th></tr>
	</thead>
	<tbody>
		<tr>
			<td width="15%">Branch :</td>
			<td width="35%"><label for="branch" id="branch" ></label></td>
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
<%@ include file= "Layout/footer.jsp" %>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>

<script>
jQuery(function() {       

	$('#ad_member_id').select2();
	$('#ad_branch_id').select2();
	jQuery.validator.addMethod("alphanumspace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter character and space only.");
	
	jQuery( "#frmMemberStatus" ).validate({
		  rules: {
			  member_status: {
		      required: true,
		      alphanumspace:true,
		      maxlength:30
		    },
		    status: {
		    	 required: true,
			      alphanumspace:true,
		    }
		  }
		});
	
	
	$("#ad_member_id").change(function(e) {
		loading_show();
		var id = $(this).val();
		$("#il_ad_member_id").val(id);
		
		
		
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
	            	
	            	  $("#branch").html(data.MemberInfo.branch.branch);
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
	            	  
	            	  
	            	
	            	  loading_hide();
	              }
	         },error: function(jqXHR, textStatus, errorThrown){
	              console.log("Something really bad happened " + textStatus);
	               $("#ajaxResponse").html(jqXHR.responseText);
	         }

	     });//end ajax
	     
		
	     
		}else{
			console.log('please select valid member id');
		}
		 
	});//end get information from member id

	
	function loading_show(){
        $('#modelLoad').removeClass('hide').addClass('show');
     }
     function loading_hide(){
       $('#modelLoad').addClass('hide').removeClass('show');
     } 
	
});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>