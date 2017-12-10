<%@page import="Model.Bean.BankBranchViewBeen"%>
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
<link rel="stylesheet" href="assets/plugins/data-tables/DT_bootstrap.css"/>
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
			<li><a href="#">Branch</a><i class="fa fa-angle-right"></i>Add and view</li>
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
	   <div class="caption">Add Branch</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmBranch" action="AdBankBranch?action=insert" method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
					<td width="12%">Bank : <span class="red">*</span></td>
					<td width="38%">
					<select class="form-control input-medium"  name="ad_bank_id" id="ad_bank_id"  tabindex="1" autofocus="autofocus" >
						 <option value="">--Select Bank--</option>
						  <%BankDao dao=new BankDao();
						   ArrayList <BankBean> alist=dao.getAllBank();
						   if(alist!=null){
						   for(BankBean bean:alist){%>
						   <option value="<%=bean.getAd_bank_id()%>"><%=bean.getBank() %></option>
						   <%} }%>
					</select><label class="error"></label>
					</td>
					<td width="12%">Region : <span class="red">*</span></td>
					<td width="38%">
					<select class="form-control input-medium" name="ad_bank_region_id" id="ad_bank_region_id" tabindex="2" >
						  <option value="">--Select Region--</option>
						   <%BankRegionDao regiondao=new BankRegionDao();
						   ArrayList <BankRegionBean> regionlist=regiondao.getAllBankRegion();
					       if(regionlist!=null){
						   for(BankRegionBean bean:regionlist){%>
						   <option value="<%=bean.getAd_bank_region_id()%>"><%=bean.getRegion()%></option>
						  <%} }%>
					</select>
					</td>
				</tr>
				<tr>
					<td>State : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium"  name="ad_state_id" id="ad_state_id"  tabindex="3">
								 <option value="">--Select State--</option>
								<%StateDao sdao=new StateDao();
								  ArrayList <StateBean> alist2=sdao.getAllState();
								  if(alist2!=null){
								  for(StateBean bean:alist2){%>
								  <option value="<%=bean.getAd_state_id()%>"><%=bean.getState() %></option>
									  
								 <%} }%>
						</select>
					</td>
					<td>District : <span class="red">*</span></td>
					<td>
					   <select class="form-control input-medium"  name="ad_district_id" id="ad_district_id" tabindex="4" >
						 <option value="">--Select District--</option>
					   </select>
					</td>
				</tr>
				<tr>
					<td>City : <span class="red">*</span></td>
					<td>
					  <select class="form-control input-medium"  name="ad_city_id" id="ad_city_id" tabindex="5" >
					     <option value="">--Select City--</option>
			          </select>
					</td>
					<td>Branch : <span class="red">*</span></td>
					<td><input  type="text" class="form-control input-medium"  name="branch" id="branch" style="text-transform: uppercase;" tabindex="6"/></td>
				</tr>
				<tr>
					<td>Branch Code : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="branch_code" id="branch_code"  tabindex="7"/></td>
					<td>IFSC : <span class="red">*</span></td>
					<td><input class='form-control input-medium' type="text" name="ifsc_code" id="ifsc_code" style="text-transform: uppercase;" tabindex="8"/></td>
				</tr>
				<tr>
					<td>Pin Code :</td>
					<td><input class="form-control input-medium" type="text" name="pincode" id="pincode" tabindex="9"/></td>
					<td>Email :</td>
					<td><input class="form-control input-medium" type="text" name="email_id" id="email_id" style="text-transform: lowercase;;" tabindex="9"/></td>
				</tr>
				<tr>
					<td>Phone No.</td>
					<td colspan="3"><input class="form-control input-medium" type="text" name="phone_no" id="phone_no" tabindex="10"/></td>
				</tr>
				<tr>
					<td>Contact Person</td>
					<td><input type="text" class="form-control input-medium"  name="contact_person" id="contact_person" style="text-transform: uppercase;" tabindex="11" /></td>
					<td>Phone No.</td>
					<td><input type="text"  class="form-control input-medium" name="contact_no" id="contact_no" tabindex="12"/></td>
				</tr>
				<tr>
				  <td>Address :</td>
				  <td colspan="3"> <textarea placeholder="Enter Address here" class="form-control" name="address" id="address" style="text-transform: uppercase;" tabindex="13"></textarea></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn blue" type="submit" name="Submit" value="submit"/>
					  <input class="btn btn green" type="reset" name="Clear"/>
					</td>
				</tr>
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div>
	
	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		View Branch
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	   <div class="table-scrollable">
			<table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th>Sno.</th>
					<th>Branch</th>
					<th>Region</th>
					<th>Bank</th>
					<th>State</th>
					<th>District</th>
					<th>City</th>
					<th>Code</th>
					<th>IFSC</th>
					<!-- <th>Pin Code</th> -->
					<!-- <th>Email</th> -->
					<th>Phone</th>
					<!-- <th>Person</th>
					<th>Mobile</th>
					<th>Address</th> -->
					<th>Status</th>
					<th>Action</th>
				</tr>
				</thead>
				<tbody>
				<%BankBranchDao Branch=new BankBranchDao();
					ArrayList<BankBranchViewBeen> slist=Branch.getBankBranchView();
					int i=0;
					if(slist.isEmpty()!=true){
						for(BankBranchViewBeen bean:slist){
				%>
				 <tr>
				   <td><%=++i %></td>
				   <td><%=bean.getBranch()%></td>
				   <td><%=bean.getRegion()%></td>
				   <td><%=bean.getBank()%></td>
				   <td><%=bean.getState() %></td>
				   <td><%=bean.getDistrict() %></td>
				   <td><%=bean.getCity() %></td>
				   <td><%=bean.getBranch_code() %></td>
				   <td><%=bean.getIfsc_code() %></td>
				  <%--  <td><%=bean.getPincode() %></td> --%>
				   <%-- <td><%=bean.getEmail_id() %></td> --%>
				   <td><%=bean.getPhone_no() %></td>
				  <%--  <td><%=bean.getContact_person() %></td>
				   <td><%=bean.getContact_no() %></td>
				   <td><%=bean.getAddress() %></td> --%>
				   <td><% if(bean.isIsactive()==true){ %>
						<span class="badge badge-primary">Active</span>
						<% }else{ %>
						<span class="badge badge-warning">Inactive</span>
						<%} %>
				  </td>
				   <td>
				   <a class="btn btn-xs green" href="AdBankBranch?action=edit&ad_branch_id=<%=bean.getAd_branch_id()%>">
				  <i class="fa fa-edit"></i> edit</a>
				   </td>
				 </tr>
				 <%	}
					}
				  %>
				</tbody>
			</table>
			<div class="clearfix"></div>
			</div>
	    </div><!-- End portlet-body -->
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
<!-- BEGIN FOOTER -->
<%@ include file= "Layout/footer.jsp" %>
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<script>
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	jQuery('#ad_state_id').select2();
	jQuery('#ad_district_id').select2();
	jQuery('#ad_city_id').select2();
	jQuery('#ad_bank_id').select2();
	jQuery('#ad_bank_region_id').select2();
	
	jQuery.validator.addMethod("Alphanumspacedot", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z.\s()]*$/.test(value);
		}, "Please enter character, dot(.) and space only.");
	
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
		     	digits:true
		    },
		    ad_bank_region_id:{
		    	required: true,
			    digits:true
		    },
		    ad_state_id:{
		    	required: true,
		     	digits:true
		    },
		    ad_district_id:{
		    	required: true,
		     	digits:true
		    },
		    ad_city_id:{
		    	required: true,
		     	digits:true
		    },
		    branch:{
		    	required: true,
		    	Alphanumspacedot:true
		    },
		    branch_code:{
		    	digits:true,
		    	required: true,
		    	maxlength:5
		    },
		    ifsc_code:{
		    	required: true,
		    	validIFSC:true
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