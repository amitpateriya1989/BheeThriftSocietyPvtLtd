<%@page import="Model.Bean.BankBean"%>
<%@page import="Model.Dao.BankDao"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Bean.AccountTypeBean"%>
<%@page import="Model.Dao.AccountTypeDao"%>
<%@page import="Model.Dao.AccountGroupDao"%>
<%@page import="Model.Bean.AccountSubGroupBean"%>
<%@page import="Model.Dao.AccountSubGroupDao"%>
<%@page import="Model.Bean.AccountGroupBean"%>
<%@page import="Model.Dao.AccountGroupDao"%>
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
			<li><a href="#">Account</a><i class="fa fa-angle-right"></i>Ledger Add</li>
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
	   <div class="caption">Open Ledger Account</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmAccount" autocomplete="off" action="AdLedgerAccount?action=insert" method="post">
			<table class="table table-bordered">
				<tr>
				<td>Name : <span class="red">*</span></td>
					<td >
						<input class="form-control input-medium" type="text" name="ac_name" tabindex="1"/>
						<label class="error"></label>
					</td>
					
					<td>Ac. Type : <span class="red">*</span></td>
					<td >
						<select  name="type" id="type" class="form-control input-medium"   tabindex="2">
								 <option value="">--Select Type--</option>
								 <option value="Real Account">Real Account</option>
								 <option value="Nominal Account">Nominal Account</option>
								 <option value="Personal Account">Personal Account</option>
								 
								 </select>
						<label class="error"></label>
					</td>
					
					</tr>
					<tr>
					
					<td>Type : <span class="red">*</span></td>
					<td >
					<select  name="ad_ac_type_id" id="ad_ac_type_id" class="form-control input-medium"   tabindex="3">
								 <option value="">--Select Type--</option>
								<%AccountTypeDao dao1=new AccountTypeDao();
								  ArrayList <AccountTypeBean> aclist=dao1.getAllAccountType();
								  if(aclist!=null){
								  for(AccountTypeBean bean:aclist){%>
								  <option value="<%=bean.getAd_ac_type_id()%>"><%=bean.getName() %></option>
									  
								 <%} }%>
						</select><label id="form_type_error"  class="error "></label>
					</td>
					<td >Group : <span class="red">*</span></td>
					<td >
					<select  name="ad_ac_group_id" id="ad_ac_group_id" class="form-control input-medium"  tabindex="4">
								 <option value="">--Select Group--</option>
								
						</select><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td>Sub Group : <span class="red">*</span></td>
					<td>
						<select  name="ad_ac_subgroup_id" id="ad_ac_subgroup_id" class="form-control input-medium"  tabindex="5">
						  <option value="">--Select Sub Group --</option>
						</select><label class="error"></label>
					</td>
					
				
					<td>Account For : <span class="red">*</span></td>
					<td>
						<select  name="ac_for" id="ac_for" class="form-control input-medium"  tabindex="6">
							<option value="">--Select Account For--</option>
							<option value="Member">Member</option>
							<option value="Staff">Staff</option>
							<option value="Normal">Normal</option>
							<option value="Bank OD">Bank OD</option>
							<option value="Cash A/C">Cash A/C</option>
						</select><label class="error"></label>
					</td>
				</tr>
				<tr>
				
				<td>LF No. : <span class="red">*</span></td>
					<td >
						<input class="form-control input-medium" type="text" name="ac_no" id="ac_no" tabindex="7" readonly="readonly"/>
						<label class="error"></label>
					</td>
				</tr>
				<tr>	
				<td>Opening Amt : <span class="red">*</span></td>
					<td >
						<input class="form-control input-medium" type="text" name="opening_amt" value="0.0" tabindex="8"/>
						
						<label class="error"></label>
					</td>
					
					<td>Opening Type : <span class="red">*</span></td>
					<td>
					<select  name="opening_type" id="opening_type" class="form-control input-medium"  tabindex="6">
							<option value="">Opening Type</option>
							<option value="Debit">Debit</option>
							<option value="Credit">Credit</option>
							
						</select><label class="error"></label>
					</td>
					
					
					</tr>
					<tr id="row11" class="hide">
		<td colspan="6">	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		Account Detail
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			 <table class="table table-bordered">
		 <tr>
			 <td>Mobile : </td>
	<td><input class="form-control input-medium" name="mobile" id="mobile" 	tabindex="9" /></td>
				
		<td>Phone no : </td>
		<td><input class="form-control input-medium" type="text"  name="phone" id="phone" tabindex="10" /></td>
		<td>Account no : </td>
		<td><input class="form-control input-medium" type="text"  name="bank_ac_no" id="bank_ac_no" tabindex="11" /></td>
	</tr>
			 
	<tr>
		<td>Bank  : </td>
		<td><select class="form-control input-medium"  name="ad_bank_id" id="ad_bank_id" tabindex="12">
			<option value="0">--Select Bank--</option>
								<%BankDao bankdao=new BankDao();
								  ArrayList <BankBean> banklist=bankdao.getAllBank();
								  if(banklist.isEmpty()!=true){
								  for(BankBean bean:banklist){
								  %>
								  <option value="<%=bean.getAd_bank_id()%>"><%=bean.getBank().trim() %></option>
								 <%} }%>
						</select><label class="error"></label>
		</td>
		<td>Branch : </td>
		<td><select class="form-control input-medium"  name="ad_branch_id" id="ad_branch_id" tabindex="13">
			<option value="0">--Select Branch--</option>
								<%BankBranchDao branchdao=new BankBranchDao();
								  ArrayList <BankBranchBean> branchlist=branchdao.getAllBankBranchName();
								  if(branchlist.isEmpty()!=true){
								  for(BankBranchBean bean:branchlist){
								  %>
								  <option value="<%=bean.getBranch_code()+" | "+ bean.getBranch()%>"><%=bean.getBranch_code()+" "+bean.getBranch().trim() %></option>
								 <%} }%>
						</select><label class="error"></label>
		</td>
		<td>IFSC Code : </td>
	<td><input class="form-control input-medium" name="ifsc_code" id="ifsc_code" 	tabindex="14" /></td>
			
		</tr>
		
	<tr>
			 <td>Pan No : </td>
	<td><input class="form-control input-medium" name="pan_no" id="pan_no" 	tabindex="15"/></td>
				
		<td>Sale Tax no : </td>
		<td><input class="form-control input-medium" type="text"  name="sale_tax_no" id="sale_tax_no" tabindex="16" /></td>
		<td>CST no : </td>
		<td><input class="form-control input-medium" type="text"  name="cst_no" id="cst_no" tabindex="17"/></td>
	</tr>
	<tr>
			 <td>Address : </td>
	<td colspan="5"><textarea class="form-control" name="address" id="address" cols="50" tabindex="18"	></textarea></td>
				
		
	</tr>
	
		</table>
		</div>
		</div>
		</td>
	</tr>
	
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn blue" type="submit" name="Submit" value="submit" tabindex="19" />
					  <input class="btn btn green" type="reset" name="Clear" tabindex="20"/>
					</td>
				</tr>
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
<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
<script>
jQuery(function() {       
	
	$('select#type').change(function(){
		var type = $(this).val();
		type = type.toLowerCase();
		
		if(type=="personal account"){
			$('#row11').removeClass('hide');
		}else{
			$('#row11').addClass('hide');
		}
		
		
	});
	
	jQuery.validator.addMethod("alphanumsapce", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter character and space only.");
	
	 jQuery.validator.addMethod("validPancard", function (value, element) {
         return this.optional(element) || /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/i.test(value);
     }, "Please enter valid Pan Card No.");
	 
	 jQuery.validator.addMethod("validIFSC", function (value, element) {
         return this.optional(element) || /^([a-zA-Z]){4}([0-9]){7}?$/i.test(value);
     }, "Please enter valid IFSC Code.");
     
	jQuery( "#frmAccount" ).validate({
		  rules: {
			  ad_ac_type_id:{
				  required: true,
				  digits:true
			  },
			  ad_ac_group_id:{
				  required: true,
				  digits:true
			  },
			  ad_ac_subgroup_id:{
				  required: true,
				  digits:true 
			  },
			  ad_bank_id:{
				 digits:true 
			  },
			  ad_branch_id:{
				 digits:true 
			  },
			  ac_name: {
		       required: true,
		       alphanumsapce: true,
		       maxlength:60
		     },
		     ac_for:{
		    	required:true
		     },
		     mobile:{
             	
             	digits:true,
             	minlength:10,
             	maxlength:12
             	
             },
             phone:{
             	digits:true,
             	maxlength:15
             },
             ad_branch_id:{
             	digits:true
             },
             ad_bank_id:{
             	digits:true
            },
            pan_no:{
            	validPancard:true
            },
            ifsc_code:{
            	validIFSC:true
            },
            bank_ac_no:{
            	digits:true,
            	minlength:10,
            	maxlength:15
            },
            opening_type:{
            	required:true
            },
            type:{
            	required:true
            }
		  }
		});
});
</script>
<script type="text/javascript">
$(document).ready(function(e) {
	$("#ad_bank_id").change(function(e){
		if($(this).val()!=""){
			
			var ad_bank_id=$(this).val();
			$.ajax({
				type:"POST",
				url:"Ajax/getBranchByBankId.jsp?ad_bank_id="+ad_bank_id,
				async:false,
				success:function(data){
					$('#ad_branch_id').html(data);
					$('#ad_branch_id').trigger("chosen:updated");
				}
			});
		}
	});
$("#ad_ac_type_id").change(function(e) {
	
		if($(this).val()!="")
		{
    		var ad_ac_type_id=$(this).val();
    		
    		$.ajax({
    			   type: "POST",
    			   url: "Ajax/read_account_group_by_account_type_id.jsp?ad_ac_type_id="+ad_ac_type_id,
    			   async:false,
    			   success: function(data)
    			   {	
    				   $('#ad_ac_group_id').html(data); 
    				   $("#ad_ac_group_id").trigger("chosen:updated");
    		  	   } 
    		});
		}
		
	});

$("#ad_ac_group_id").change(function(e) {
	
	if($(this).val()!="")
	{
	    var ad_ac_group_id=$(this).val();
		
		$.ajax({
			   type: "POST",
			   url: "Ajax/read_account_sub_group_by_account_group_id.jsp?ad_ac_group_id="+ad_ac_group_id,
			   async:false,
			   success: function(data)
			   {	//alert(data);
				   $('#ad_ac_subgroup_id').html(data); 
				   $("#ad_ac_subgroup_id").trigger("chosen:updated");
		  	   } 
		}); 
	}
	
});
$("#ad_ac_subgroup_id").change(function(e) {
	
	if($(this).val()!="")
	{
	    var ad_ac_subgroup_id=$(this).val();
		
		$.ajax({
			   type: "POST",
			   url: "Ajax/getMaxLFNo.jsp?ad_ac_subgroup_id="+ad_ac_subgroup_id,
			   async:false,
			   success: function(data)
			   {
					
				   $('#ac_no').val(data); 
				  
		  	   } 
		}); 
	}
	
});
 
});	
</script>

<script type="text/javascript">
$("#type").select2({placeholder: "Select",allowClear: true});
$("#ad_ac_type_id").select2({placeholder: "Select",allowClear: true});
$("#ad_ac_group_id").select2({placeholder: "Select",allowClear: true});
$("#ad_ac_subgroup_id").select2({placeholder: "Select",allowClear: true});
$("#ac_for").select2({placeholder: "Select",allowClear: true});
$("#ad_bank_id").select2({placeholder: "Select",allowClear: true});
$("#ad_branch_id").select2({placeholder: "Select",allowClear: true});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>