<%@page import="Model.Bean.CityBean"%>
<%@page import="Model.Bean.BankBranchViewBeen"%>
<%@page import="Model.Bean.DistrictBean"%>
<%@page import="Model.Dao.DistrictDao"%>
<%@page import="Model.Dao.CityDao"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Bean.BankRegionBean"%>
<%@page import="Model.Dao.BankRegionDao"%>
<%@page import="Model.Bean.StateBean"%>
<%@page import="Model.Dao.StateDao"%>
<%@page import="Model.Bean.BankBean"%>
<%@page import="Model.Dao.BankDao"%>
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
			<li><a href="#">Branch</a><i class="fa fa-angle-right"></i>Edit</li>
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
	   <div class="caption">Edit Branch</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	   <%
	   String id = request.getParameter("ad_branch_id");
	   BankBranchViewBeen  slist= null;
		if(id!=null){
			try{
		    int ad_branch_id=Integer.parseInt(id);
			BankBranchDao Branch=new BankBranchDao();
			 slist=Branch.getBankBranchView(ad_branch_id);
			 System.out.println("ID = "+ad_branch_id);
			int i=0;
			
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
	   %>
	   
	    <form id="frmBranch" autocomplete="off" action="AdBankBranch?action=update&ad_branch_id=<%=slist.getAd_branch_id()%>" method="post">
			<table class="table table-bordered">
				
				<tr>
				<td width="12%">Bank : <span class="red">*</span></td>
					<td width="38%">
					<select  class="input-medium form-control" name="ad_bank_id" id="ad_bank_id" autofocus="autofocus"  tabindex="1">
								<option value="<%=slist.getAd_bank_id()%>"><%=slist.getBank() %></option>
								<%BankDao bankdao1=new BankDao();
								  ArrayList <BankBean> banklist=bankdao1.getAllBank();
								  if(banklist!=null){
								  for(BankBean bean:banklist){%>
								  <option value="<%=bean.getAd_bank_id()%>"><%=bean.getBank() %></option>
									  
								 <%} }%>
						</select><label class="error"></label>
					</td>
					<td width="12%">Region : <span class="red">*</span></td>
					<td width="38%">
					<select class="input-medium form-control" name="ad_bank_region_id" id="ad_bank_region_id" tabindex="2">
					            <option value="<%=slist.getAd_bank_region_id()%>"><%=slist.getRegion() %></option>
								<%BankRegionDao regionDao=new BankRegionDao();
								  ArrayList <BankRegionBean> regionList=regionDao.getAllBankRegion();
								  if(regionList.isEmpty()!=true){
								  for(BankRegionBean bean:regionList){%>
								  <option value="<%=bean.getAd_bank_region_id()%>"><%=bean.getRegion() %></option>
									  
					<%} }%>
					</select><label class="error"></label>
					</td>
					
				</tr>
				<tr>
					<td>State : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium"  name="ad_state_id" id="ad_state_id" tabindex="3">
								   <option value="<%=slist.getAd_state_id()%>"><%=slist.getState() %></option>
								<%StateDao sdao=new StateDao();
								  ArrayList <StateBean> alist2=sdao.getAllState();
								  if(alist2!=null){
								  for(StateBean bean:alist2){%>
								  <option value="<%=bean.getAd_state_id()%>"><%=bean.getState() %></option>
									  
								 <%} }%>
						</select><label class="error"></label>
					</td>
					<td>District : <span class="red">*</span></td>
					<td>
					 <select class="form-control input-medium"  name="ad_district_id" id="ad_district_id" tabindex="4" >
								  <option value="<%=slist.getAd_district_id()%>"><%=slist.getDistrict() %></option>
								 <%DistrictDao districtdao=new DistrictDao();
								  ArrayList <DistrictBean> dlist=districtdao.getAllDistrictByStateId(slist.getAd_state_id());
								  if(dlist!=null){
								  for(DistrictBean sbean:dlist){%>
								  <option value="<%=sbean.getAd_district_id()%>"><%=sbean.getDistrict() %></option>
									  
								 <%}}%>
								</select><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td>City : <span class="red">*</span></td>
					<td><select class="form-control input-medium"  name="ad_city_id" id="ad_city_id" tabindex="5">
						<option value="<%=slist.getAd_city_id()%>"><%=slist.getCity() %></option>
								 <%CityDao citydao=new CityDao();
								  ArrayList <CityBean> clist=citydao.getAllCityByDistrictId(slist.getAd_district_id());
								  if(clist!=null){
								  for(CityBean sbean:clist){%>
								  <option value="<%=sbean.getAd_city_id()%>"><%=sbean.getCity() %></option>
									  
								 <%}}%>
						</select><label class="error"></label>
					</td>
					<td>Branch : <span class="red">*</span></td>
				<td><input  type="text" class="form-control input-medium"  name="branch" id="branch" style="text-transform: uppercase;" tabindex="6" value="<%=slist.getBranch().trim()%>" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Branch Code : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="branch_code" id="branch_code" tabindex="7" value="<%=slist.getBranch_code()%>" /></td>
					<td>IFSC : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text"  name="ifsc_code" id="ifsc_code" value="<%=slist.getIfsc_code().trim()%>"></td>
				</tr>
				<tr>
					<td>Pin Code :</td>
					<td><input class="form-control input-medium" type="text" name="pincode" id="pincode" tabindex="8" value="<%=slist.getPincode()%>"/></td>
					<td>Email</td>
					<td><input class="form-control input-medium" type="text" name="email_id" id="email_id" tabindex="9" style="text-transform:lowercase;"  value="<%=slist.getEmail_id().trim()%>"/></td>
				</tr>
				<tr>
					<td>Phone No.</td>
					<td colspan="3"><input class="form-control input-medium" type="text" name="phone_no" id="phone_no" tabindex="10" value="<%=slist.getPhone_no().trim()%>" /></td>
				</tr>
				<tr>
					<td>Contact Person</td>
					<td><input type="text" class="form-control input-medium"  name="contact_person" id="contact_person" style="text-transform: uppercase;" tabindex="11" value="<%=slist.getContact_person().trim()%>" /></td>
					<td>Phone No.</td>
					<td><input type="text"  class="form-control input-medium" name="contact_no" id="contact_no" tabindex="12" value="<%=slist.getContact_no().trim()%>" /></td>
				</tr>
				<tr>
				  <td>Address :</td>
				  <td colspan="3"> <textarea placeholder="Enter Address here" class="form-control" name="address" id="address" style="text-transform: uppercase;" tabindex="13"  ><%=slist.getAddress().trim()%></textarea></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn blue" type="submit" name="Submit" value="Update"/>
					  <a class="btn btn purple" href="ad_branch.jsp">Back</a>
					</td>
				</tr>
			</table>
		 </form>	
		 <%} %>
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
	
	jQuery('#ad_state_id').select2();
	jQuery('#ad_district_id').select2();
	jQuery('#ad_city_id').select2();
	jQuery('#ad_bank_id').select2();
	jQuery('#ad_bank_region_id').select2();
	
	jQuery.validator.addMethod("alphanumsapcedot", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z.\s]*$/.test(value);
		}, "Please enter character, dot(.) and space only.");
	
	jQuery.validator.addMethod("alphanumsapce", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter character and space only.");
	
	jQuery.validator.addMethod("validAddress", function(value, element) {
        return this.optional(element) || /^[a-zA-Z0-9,-\/] ?([a-zA-Z0-9,-\/\s]|[a-zA-Z0-9,-\/\s] )*[a-zA-Z0-9-\/]$/.test(value);
      }, "Please enter Letters, dot(.) and space only.");
	
	jQuery.validator.addMethod("validIFSC", function (value, element) {
        return this.optional(element) || /^([a-zA-Z]){4}([0-9]){7}?$/i.test(value);
    }, "Please enter valid IFSC Code. (xxxx0000000)"); 
	
	jQuery( "#frmBranch" ).validate({
		  rules: {
			ad_bank_id: {
		      	required: true,
		     	digits:true,
		    },
		    ad_bank_region_id:{
		    	required: true,
			    digits:true,
		    },
		    ad_state_id:{
		    	required: true,
		     	digits:true,
		    },
		    ad_district_id:{
		    	required: true,
		     	digits:true,
		    },
		    ad_city_id:{
		    	required: true,
		     	digits:true,
		    },
		    branch:{
		    	required: true,
		    	Alphanumspacedot:true
		    },
		    branch_code:{
		    	digits:true,
		    	maxlength:5
		    },
		    ifsc_code:{
		    	required: true,
		    	validIFSC:true,
		    	maxlength:8
		    },
		    pincode:{
		    	digits:true,
		    	maxlength:6
		    },
		    email_id:{
		    	email:true
		    },
		    phone_no:{
		    	digits:true,
		    	maxlength:12
		    },
		    contact_person:{
		    	Alphanumspacedot:true
		    },
		    contact_no:{
		    	digits:true,
		    	maxlength:12
		    },
		    address:{
		    	
		    	maxlength:250
		    }
		  }
		});
});
</script>
<script type="text/javascript">
$(document).ready(function(e) {
	
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
                	  $("#ad_bank_id").val(data.BranchInfo.bank.ad_bank_id);
                	  $('#ad_bank_id').trigger("chosen:updated");
                	  
                	  $("#ad_bank_region_id").html('<option value="' + data.BranchInfo.ad_bank_region_id + '">' + data.BranchInfo.ad_bank_region_id + '</option>');
                	  $('#ad_bank_region_id').trigger("chosen:updated");
                	  $("#ad_state_id").html('<option value="' + data.BranchInfo.ad_state_id + '">' + data.BranchInfo.ad_state_id + '</option>');
                	  $('#ad_state_id').trigger("chosen:updated");
                	  $("#ad_district_id").html('<option value="' + data.BranchInfo.ad_district_id + '">' + data.BranchInfo.ad_district_id + '</option>');
                	  $('#ad_district_id').trigger("chosen:updated");
                	  $('#ad_city_id').html('<option value="' + data.BranchInfo.ad_city_id + '">' + data.BranchInfo.ad_city_id + '</option>');
                	  $('#ad_city_id').trigger("chosen:updated");
                	  $("#branch_code").val(data.BranchInfo.branch_code);
                	  $("#ifsc_code").val(data.BranchInfo.ifsc_code);
                	  $("#address").val(data.BranchInfo.address);
                	  $("#pincode").val(data.BranchInfo.pincode);
                	  $("#contact_no").val(data.BranchInfo.contact_no);
                	  $("#contact_person").val(data.BranchInfo.contact_person);
                	  $("#email_id").val(data.BranchInfo.email_id);
                	  $("#phone_no").val(data.BranchInfo.phone_no);
                	  
                      
                      
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
				   $('#ad_district_id').select2("val","");
				   $('#ad_city_id').select2("val","");
				   $('#ad_district_id').html(data);
				   $('#ad_city_id').html("<option value=''>--Select City--</option>");
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
			   $('#ad_city_id').select2("val","");
			   $('#ad_city_id').html(data);
			   $('#ad_city_id').trigger("chosen:updated");
	  	} }); 
	}
	
});  
});	
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>