<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Bean.DepartmentBean"%>
<%@page import="Model.Dao.DepartmentDao"%>
<%@page import="Model.Bean.DesignationBean"%>
<%@page import="Model.Dao.DesignationDao"%>
<%@page import="Model.Bean.DesignationLevelBean"%>
<%@page import="Model.Dao.DesignationLevelDao"%>
<%@page import="Model.Bean.DesignationTypeBean"%>
<%@page import="Model.Dao.DesignationTypeDao"%>
<%@page import="Model.Bean.PostingDetailBean"%>
<%@page import="Model.Dao.PostingDetailDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<li><a href="#">Master</a><i class="fa fa-angle-right"></i>Edit Posting Details</li>
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

<% String ad_posting_detail_id=request.getParameter("ad_posting_detail_id"); 
   String ad_member_id=request.getParameter("ad_member_id"); 
   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   
    PostingDetailBean pdbean=null;
    
  if(ad_posting_detail_id.equals("")!=true && ad_member_id.equals("")!=true){
    int adpdid=0;
    	if(ad_posting_detail_id!=""){
    		try{
    			adpdid = Integer.parseInt(ad_posting_detail_id);
    		}catch(NumberFormatException n){
    			n.printStackTrace();
    		}
    		//out.print(adpdid);
    		 pdbean = new PostingDetailDao().getPostingDetailById(adpdid);
    		 int designation_id = pdbean.getAd_designation_id();
    		 int dtype_id = pdbean.getAd_designation_type_id();
    		 int department_id = pdbean.getAd_department_id();
    		 
    		 System.out.println(designation_id);
    		 
    	// out.print(pdbean.getAd_branch_id());
    	
%>
<!------------------------------------------------------------------- -->
	<!-- BEGIN BORDERED TABLE PORTLET-->	
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Edit Posting Details</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmPostingDetails" autocomplete="off" action="AdPostingDetail?action=edit&ad_posting_detail_id=<%=ad_posting_detail_id%>&ad_member_id=<%=ad_member_id%>" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Branch : <span class="red">*</span></td>
					<td width="35%">
					<select class="form-control input-medium"  name="branch_id" id="ad_branch_id">
						<option value="">--Select Branch--</option>
						<%BankBranchDao bankdao=new BankBranchDao();
						 ArrayList <BankBranchBean> banklist=bankdao.getAllBankBranchName();
						 if(banklist.isEmpty()!=true){
						 for(BankBranchBean bean:banklist){
						 %>
						<option value="<%=bean.getAd_branch_id()%>"><%=bean.getBranch_code()+" "+bean.getBranch().trim() %></option>
						<%} }%>
					</select>
					<label class="error"></label>
					</td>
					<td width="15%">Region : </td>
					<td width="35%"><input class="form-control input-medium" type="text"  name="ad_bank_region_id" id="ad_bank_region_id" readonly="readonly" ></td>
				</tr>
				<tr>
					<td>IFSC : </td>
					<td><input class="form-control input-medium" type="text" name="ifsc_code" id="ifsc_code" readonly="readonly"></td>
					<td>State : </td>
					<td><input class="form-control input-medium" type="text"  name="ad_bank_state_id" id="ad_bank_state_id" readonly="readonly"></td>					
				</tr>
				<tr>
					<td>District : </td>
					<td><input class="form-control input-medium" type="text" name="ad_bank_district_id" id="ad_bank_district_id" readonly="readonly" ></td>
					<td>City : </td>
					<td><input class="form-control input-medium" type="text"  name="ad_bank_city_id" id="ad_bank_city_id" readonly="readonly" /></td>					
				</tr>
				<tr>
					<td>Pincode : </td>
					<td><input class="form-control input-medium" type="text" name="bank_pincode" id="bank_pincode" readonly="readonly"></td>
					<td>Email : </td>
					<td><input class="form-control input-medium" type="text" name="email_id" id="email_id" readonly="readonly" /></td>					
				</tr>
				<tr>
					<td>Phone : </td>
					<td><input class="form-control input-medium" type="text" name="phone_no" id="phone_no" readonly="readonly" /></td>
					<td>Person : </td>
					<td><input class="form-control input-medium" type="text" name="contact_person" id="contact_person" readonly="readonly" /></td>					
				</tr>
				<tr>
					<td>Contact : </td>
					<td><input class="form-control" type="text" name="contact_no" id="contact_no" readonly="readonly" /></td>
					<td>Address : </td>
					<td> <input class="form-control" type="text" name="address" id="address" readonly="readonly" /></td>					
				</tr>
				<tr>
					<td>Department : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium"  name="ad_department_id" id="ad_department_id"  >
						<option value="">--Select Department--</option>
						<%
						DepartmentDao deptdao=new DepartmentDao();
						ArrayList<DepartmentBean> deptlist=deptdao.getAllDepartmentName();
						if(deptlist!=null){
							for(DepartmentBean bean:deptlist){%>
							<option value="<%=bean.getAd_department_id()%>" <% if(department_id==bean.getAd_department_id()){%> selected="selected" <%}%> ><%=bean.getName()%></option>
							<%}
							}
						%>
					</select>
					</td>
					<td>Type : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium" name="ad_designation_type_id" id="ad_designation_type_id">
							<option value="">--Select Designation Type--</option>
							<%
							DesignationTypeDao typedao=new DesignationTypeDao();
							ArrayList<DesignationTypeBean> typelist=typedao.getAllDesignationType();
							if(typelist!=null){
							for(DesignationTypeBean bean:typelist){%>
								<option value="<%=bean.getAd_designation_type_id()%>" <% if(dtype_id==bean.getAd_designation_type_id()){%> selected="selected" <%}%> ><%=bean.getDesignation_type()%></option>
							<%}
							}
							%>
						</select>
					
					
					</td>					
				</tr>
				<tr>
					<td>Designation Level : <span class="red">*</span></td>
					<td>
				    <select class="form-control input-medium" name="ad_designation_level_id" id="ad_designation_level_id"  >
					 <option value="">--Select Designation Level--</option>
				   <%
				   DesignationLevelDao desiglvldao=new DesignationLevelDao();
				   ArrayList<DesignationLevelBean> desglvllist=desiglvldao.getAllDesignationLevel();
				   if(desglvllist!=null){
				   for(DesignationLevelBean bean:desglvllist){%>
						<option value="<%=bean.getAd_designation_level_id()%>"><%=bean.getDesignation_level() %></option>
					<%}
					}
					%>
					</select>
					<label class="error"></label>
					</td>		
					<td>Designation : <span class="red">*</span></td>
					<td colspan="3">
						<select class="form-control input-medium"  name="ad_designation_id" id="ad_designation_id"  >
						<option value="">--Select Designation--</option>
						<%
						DesignationDao desigdao=new DesignationDao();
						ArrayList<DesignationBean> desglist=desigdao.getDesignationByType(dtype_id);
						if(desglist!=null){
						for(DesignationBean bean:desglist){%>
							<option value="<%=bean.getAd_designation_id()%>"  <% if(designation_id==bean.getAd_designation_id()){%> selected="selected" <%}%> ><%=bean.getDesignation()%></option>
						<%}
						}
						%>
					</select>
					</td>				
				</tr>
				<tr>
					<td>FormDate : <span class="red">*</span></td>
					<td><input class="form-control input-medium date-picker" type="text" name="fdate" id="fdate"  value="<% if(pdbean.getFormdate()!=null)%><%=sdf.format(pdbean.getFormdate())%>" /></td>
					<td>Todate : <span class="red">*</span></td>
					<td><input class="form-control input-medium date-picker2" type="text" name="tdate" id="tdate" value="<% if(pdbean.getTodate()!=null)%><%=sdf.format(pdbean.getTodate())%>" /></td>					
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn blue" type="submit" name="Submit" value="Update"/>
					</td>
				</tr>
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div>
<!------------------------------------------------------------------- -->			
<!-- Containt Stop -->
<%}}%>
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
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script type="text/javascript">
jQuery(function() { 
	jQuery('#ad_branch_id').select2();
	$('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true});
	$('.date-picker2').datepicker({format: 'dd/mm/yyyy',autoclose: true});
	
});
</script>
<script>
jQuery(function() {       
	
	jQuery.validator.addMethod("alphanumsapcedot", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z.\s]*$/.test(value);
		}, "Please enter character, dot(.) and space only.");
	
	jQuery.validator.addMethod("alphanumsapce", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter character and space only.");
	
	jQuery.validator.addMethod("validDate", function (value, element) {
        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	jQuery("#frmPostingDetails").validate({
		  rules: {
			branch_id: {
		        required: true,
		        digits: true,
		    },
		    branch_id: {
			    required: true,
			    digits: true,
			},
			ad_department_id:{
				required: true,
			    digits: true,
			},
			ad_designation_type_id:{
				required: true,
			    digits: true,
			},
			ad_designation_id:{
				required: true,
			    digits: true,
			},
			fdate:{
				required: true,
				validDate:true
			},
			tdate:{
				required: true,
				validDate:true
			}
			
		  }//end rules
		});
	
	$('#ad_designation_type_id').change(function(){
		  var dataString = "desigType="+$(this).val();
			 $.ajax({
		         type: "POST",
		         url: "Ajax/getDesignation.jsp",
		         data: dataString,      
		         success: function(data) {
		             $("#ad_designation_id").html(data);
		         },
		         error: function(jqXHR, textStatus, errorThrown){
		               $("#ajaxResponse").html(jqXHR.responseText);
		         }

		     });
	  });//end designation change type id
	
});//end dom
</script>
<script type="text/javascript">

$(document).ready(function(e){
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
	
	
	
	$("#ad_branch_id").val(<%= pdbean.getAd_branch_id()%>);
	$("#ad_branch_id").trigger("chosen:updated");
	$("#ad_branch_id").change();
	$("#ad_department_id").val(<%=pdbean.getAd_department_id()%>);
	$("#ad_department_id").trigger("chosen:updated");
	$("#ad_designation_id").val(<%=pdbean.getAd_designation_id()%>);
	$("#ad_designation_id").trigger("chosen:updated");
	$("#ad_designation_level_id").val(<%=pdbean.getAd_designation_level_id()%>);
	$("#ad_designation_level_id").trigger("chosen:updated");
	$("#ad_designation_type_id").val(<%=pdbean.getAd_designation_type_id()%>);
	$("#ad_designation_type_id").trigger("chosen:updated");
	
});

function validet(){
	if($("#ad_branch_id").val()=="0"){
		alert("Please select Branch");
		return false;
	}else if($("#ad_department_id").val()=="0"){
		alert("Please select Department");
		return false;
	}else if($("#ad_designation_id").val()=="0"){
		alert("Please select Designation");
		return false;
	}else if($("#ad_designation_level_id").val()=="0"){
		alert("Please select Designation level");
		return false;
	}else if($("#ad_designation_type_id").val()=="0"){
		alert("Please select Designation type");
		return false;
	}else if($("#fdate").val()==""){
		alert("Please select Form Date");
		return false;
	}
	
}

</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>