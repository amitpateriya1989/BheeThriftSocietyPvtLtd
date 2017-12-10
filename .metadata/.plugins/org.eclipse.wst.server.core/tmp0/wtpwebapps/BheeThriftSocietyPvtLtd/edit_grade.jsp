<%@page import="Model.Bean.GradeBean"%>
<%@page import="Model.Dao.GradeDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<% int ad_grade_id= Integer.parseInt(request.getParameter("ad_grade_id"));%>
<% GradeBean bean1= new GradeDao().getGradeById(ad_grade_id);%>
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
			<li><a href="#">HR</a><i class="fa fa-angle-right"></i></li>
			<li><a href="#">Grade Details</a><i class="fa fa-angle-right"></i>Edit</li>
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
	   <div class="caption">Edit Grade Details</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmGrade" action="AdGrade?action=update&ad_grade_id=<%=ad_grade_id%>" method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Grade  : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium date-picker" type="text" name="grade_name" value="<%=bean1.getGrade_name()%>" /><label class="error"></label></td>
					<td width="15%">Basics:  <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium" type="text" name="basics" id="basics" value="<%=bean1.getBasics()%>"  /><label class="error"></label></td>
				</tr>
				<tr>
					<td>DA (%) : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="da" id="da" value="<%=bean1.getDa()%>"  /><label class="error"></label></td>
					<td>Conveyance (%) : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="convey" id="convey" value="<%=bean1.getConvey()%>" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>HRA (%) : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="hra" value="<%=bean1.getHra()%>" /><label class="error"></label></td>
					<td>Staff Welfare (%) : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="sw" value="<%=bean1.getSw()%>" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Medical (%) : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="mdcl" value="<%=bean1.getMdcl()%>" /><label class="error"></label></td>
					<td> Allowance (%) : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="alwnc" value="<%=bean1.getAlwnc()%>" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Status : <span class="red">*</span></td>
					<td colspan="3">
					    <select name="status" class="form-control input-medium">
					    <option >-- Select Status --</option>
						<option value="true" <% if(bean1.isIsactive()==true){%> selected="selected" <%}%>>Active</option>
						<option value="false" <% if(bean1.isIsactive()==false){%> selected="selected" <%}%>>Inactive</option>
					   </select><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
					  <input class="btn btn-md blue" type="submit" name="Submit" value="submit"/>
					 <a class="btn btn-md purple" href="ad_grade.jsp">Back</a>
					</td>
				</tr>
			</table>
		 </form>	
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
<script type="text/javascript">
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	
	jQuery.validator.addMethod("AlphaNum", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z0-9]*$/.test(value);
		}, "Please enter latter, number only.");
	
	jQuery("#frmGrade").validate({
		  rules: {
			  grade_name1: {
			      required: true,
			      AlphaNum:true
		      },
		      basics:{
		    	  required: true,
		    	  number:true
		      },
		      da:{
		    	  required: true,
		    	  number:true
		      },
		      convey:{
		    	  required: true,
		    	  number:true
		      },
		      hra:{
		    	  required: true,
		    	  number:true
		      },
		      sw:{
		    	  required: true,
		    	  number:true
		      },
		      mdcl:{
		    	  required: true,
		    	  number:true
		      },
		      alwnc:{
		    	  required: true,
		    	  number:true
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