<%@page import="Model.Bean.AccountGroupBean"%>
<%@page import="Model.Dao.AccountGroupDao"%>
<%@page import="Model.Bean.AccountSubGroupBean"%>
<%@page import="Model.Dao.AccountSubGroupDao"%>
<%@page import="Model.Bean.AccountTypeBean"%>
<%@page import="Model.Dao.AccountTypeDao"%>
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
			<li><a href="#">Account</a><i class="fa fa-angle-right"></i>Account Sub Group Edit</li>
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
<%
String ad_ac_subgroup_id = request.getParameter("ad_ac_subgroup_id");
AccountSubGroupBean sgb =null;
if(ad_ac_subgroup_id.equals("0")!=true && ad_ac_subgroup_id!=""){
	 sgb = new AccountSubGroupDao().getAccountSubGroupById(Integer.parseInt(ad_ac_subgroup_id));
}
%>	
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Edit Account Sub Group</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmAccountSubGroup" autocomplete="off" action="AdAccountSubGroup?action=edit&ad_ac_subgroup_id=<%=ad_ac_subgroup_id %>" method="post">
			<table class="table table-bordered">
			<tr>
					<td width="12%">Type : <span class="red">*</span></td>
					<td width="38%">
					    <select class="form-control input-medium" name="ad_ac_type_id" id="ad_ac_type_id">
								<option value="0">--Select--</option>
								<%AccountTypeDao dao=new AccountTypeDao();
								  ArrayList <AccountTypeBean> alist=dao.getAllAccountType();
								  if(alist!=null){
								  for(AccountTypeBean bean:alist){
								  if(sgb.getAd_ac_type_id()==bean.getAd_ac_type_id()){
								  %>  
								  <option value="<%=bean.getAd_ac_type_id()%>" selected="selected"><%=bean.getName() %></option>						  
								 <%}else{
								%>
								<option value="<%=bean.getAd_ac_type_id()%>"><%=bean.getName() %></option>
								<%
								  }
								  } }%>
						</select>
						<label class="error"></label>
					</td>
					<td  width="12%">Group <span class="red">*</span></td>
					<td  width="38%">
						<select class="form-control input-medium" name="ad_ac_group_id" id="ad_ac_group_id">
						<%
							AccountGroupBean gb = new AccountGroupDao().getAccountGroupById(sgb.getAd_ac_group_id());
						%>
						 <option value="<%=gb.getAd_ac_group_id() %>"><%=gb.getName() %></option>
						 </select><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td>Sub Group <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="name" value="<%=sgb.getName() %>" /><label class="error"></label></td>
					<td width="15%">Status : <span class="red">*</span></td>
					<td width="35%">
					<select class="form-control input-medium" name="status">
					    <option>-- Select Status --</option>
						<option value="true" <% if(sgb.isIsactive()==true){%> selected="selected" <%}%>>Active</option>
						<option value="false" <% if(sgb.isIsactive()==false){%> selected="selected" <%}%>>Inactive</option>
					</select><label class="error"></label></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn blue" type="submit" name="Submit" value="submit"/>
					  <a class="btn btn purple" href="ad_account_sub_group.jsp">Back</a>
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
<script>
jQuery(function() {       	
	jQuery.validator.addMethod("Alphanumspace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter latter and space only.");
	
	jQuery( "#frmAccountSubGroup" ).validate({
		  rules: {
			  ad_ac_type_id: {
		        required: true,
		        digits:true,
		    },
		    ad_ac_group_id:{
		    	required: true,
		        digits:true,
		    },
		    name:{
		    	required: true,
		        Alphanumspace:true,
		        maxlenght:60
		    }
		  }
		});
});
</script>
<script type="text/javascript">
$(document).ready(function(e) {
	$("#ad_ac_type_id").change(function(e) {
	
		if($(this).val()!=0)
		{
			var ad_ac_type_id=$(this).val();
			$.ajax({
				   type: "POST",
				   url: "Ajax/read_account_group_by_account_type_id.jsp?ad_ac_type_id="+ad_ac_type_id,
				   async:false,
				   success: function(data)
				   {	
					   $('#ad_ac_group_id').html(data);  			  				
			       } 
			});
		}		
	});   
});	
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>
