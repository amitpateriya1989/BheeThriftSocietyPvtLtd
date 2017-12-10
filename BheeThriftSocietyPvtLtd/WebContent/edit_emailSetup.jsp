<%@page import="Model.Bean.EmailUtilityBean"%>
<%@page import="Model.Dao.EmailUtilityDao"%>
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
			<li><a href="#">Email</a><i class="fa fa-angle-right"></i>Email Setup</li>
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
<%

String id=request.getParameter("ad_email_id");
int ad_email_id=0;
if(id!=null){
	ad_email_id=Integer.parseInt(id);
}
EmailUtilityBean bean=new EmailUtilityDao().getEmailUtilityById(ad_email_id);


%>
<!------------------------------------------------------------------- -->
	<!-- BEGIN BORDERED TABLE PORTLET-->	
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Email Setup</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmEmail" autocomplete="off" action="AdEmailUtility?action=update&ad_email_id=<%=bean.getAd_email_id() %>" method="post">
			<table class="table table-bordered">
				<tr>
					<td>Email : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="email" id="email" value="<%=bean.getEmail_id() %>" /><label class="error"></label></td>
					<td>Password : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="password" name="pwd" id="pwd" value="<%=bean.getPwd() %>" /><label class="error"></label></td>
					
				</tr>
				<tr>
					<td>Host : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="host_name" id="host_name" value="<%=bean.getHost_name() %>" /><label class="error"></label></td>
					<td>Port : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="port_no" id="port_no" value="<%=bean.getPort_no() %>" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Status : <span class="red">*</span></td>
					<td><select class="form-control input-medium"  name="status" id="status" >
					<option value="<%=bean.isIsactive() %>"><%=String.valueOf(bean.isIsactive()).toUpperCase()%></option>
					<option value="true">TRUE</option>
					<option value="false">FALSE</option>
					</select>
					
					<label class="error"></label></td>
					
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
<!------------------------------------------------------------------- -->			
<!-- Containt Stop -->

	<!-- END BORDERED TABLE PORTLET-->					
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
	$('#status').select2();
	jQuery.validator.addMethod("alphanumsapce", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z0-9._\s]*$/.test(value);
		}, "Please enter character,numbers, dot,underscore and space only.");
	
	jQuery( "#frmEmail" ).validate({
		  rules: {
			  email:{
				  required: true,
				  email:true
			  },
			  pwd:{
				  required: true,
				  alphanumsapce:true
			  },
			  host_name:{
				  required: true 
			  },
			   port_no: {
		       required: true,
		       digit: true
		       
		     },
		     status:{
		    	 required: true
		    	 
		     }
		  }
		});
});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>