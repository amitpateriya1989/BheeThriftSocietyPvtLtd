<%@page import="Model.Bean.DesignationTypeBean"%>
<%@page import="Model.Dao.DesignationTypeDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->s
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
			<li><a href="#">Designation Type</a><i class="fa fa-angle-right"></i>Edit</li>
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
	String id=request.getParameter("ad_designation_type_id");
	DesignationTypeBean bean=null;
if(id!=null){
	try{
	int ad_designation_type_id=Integer.parseInt(id);
	bean=new DesignationTypeDao().getDesignationTypeById(ad_designation_type_id);
	}catch(NumberFormatException n){
		n.printStackTrace();
	}
}

%>
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Edit Designation Type</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmDesignation" autocomplete="off" action="AdDesignationType?action=update&ad_designation_type_id=<%=bean.getAd_designation_type_id()%>" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="20%">Designation Type : <span class="red">*</span></td>
					<td width="30%"><input id="designation_type" class="form-control input-medium" type="text" name="designation_type" value="<%=bean.getDesignation_type()%>" /><label class="error"></label>
					</td>
					<td width="20%">Status : <span class="red">*</span></td>
					<td width="30%">
					<select class="form-control input-medium" name="status">
					    <option>-- Select Status --</option>
						<option value="true" <% if(bean.isIsactive()==true){%> selected="selected" <%}%>>Active</option>
						<option value="false" <% if(bean.isIsactive()==false){%> selected="selected" <%}%>>Inactive</option>
					</select><label class="error"></label></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn-md blue" type="submit" name="designation_type" value="submit"/>
					  <a class="btn btn-md purple" href="ad_designation_type.jsp">Back</a>
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

	
	jQuery.validator.addMethod("alphanumspace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter character and space only.");
	
	jQuery.validator.addMethod("alpha", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter character  only.");
	
	jQuery( "#frmDesignation Type" ).validate({
		  rules: {
			  designation_type: {
		      required: true,
		      alphanumspace:true,
		      maxlength:30
		    },
		    status:{
		    	required:true,
		    	alpha:true
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