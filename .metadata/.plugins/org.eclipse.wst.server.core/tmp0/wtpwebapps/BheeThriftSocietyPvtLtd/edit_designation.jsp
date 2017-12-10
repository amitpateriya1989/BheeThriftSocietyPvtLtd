<%@page import="Model.Bean.DesignationTypeBean"%>
<%@page import="Model.Dao.DesignationTypeDao"%>
<%@page import="Model.Bean.DesignationBean"%>
<%@page import="Model.Dao.DesignationDao"%>
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
			<li><a href="#">Designation</a><i class="fa fa-angle-right"></i>Edit</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
<div class="col-md-12">
<!-- Containt Start -->
<div class="alert alert-info alert-dismissable">
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
	<strong>Warning!</strong> Something went wrong. Please check.
</div>
<!------------------------------------------------------------------- -->
	<!-- BEGIN BORDERED TABLE PORTLET-->
	<%
	try{
	int ad_designation_id=0;
	if(request.getParameter("ad_designation_id")!=null){
		try{
			ad_designation_id=Integer.parseInt(request.getParameter("ad_designation_id"));
		}catch(NumberFormatException n){
			n.printStackTrace();
		}
	}
	DesignationDao dao=new DesignationDao();
	DesignationBean bean=dao.getDesignationById(ad_designation_id);
	if(bean!=null){
	%>		
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Edit Designation</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmDesignation" autocomplete="off" action="AdDesignation?action=update&ad_designation_id=<%=bean.getAd_designation_id() %>" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Designation : <span class="red">*</span></td>
					<td width="35%"><input type="text" name="designation" id="designation" class="form-control input-medium" value="<%=bean.getDesignation()%>" /><label class="error"></label>
					</td>
					<td width="15%">Type : <span class="red">*</span></td>
					<td width="35%"><select class="form-control input-medium" id="ad_designation_type_id" name="ad_designation_type_id"  >
					<option value="">-- Designation Type --</option>
					<%
					DesignationTypeDao dao1=new DesignationTypeDao();
					ArrayList<DesignationTypeBean> desglist=dao1.getAllDesignationType();
					if(desglist.isEmpty()!=true){
						for(DesignationTypeBean bean1:desglist){
							%>
							<option value="<%=bean1.getAd_designation_type_id()%>"><%=bean1.getDesignation_type() %></option>
							<%
						}
					}
					%>
					
					</select>
					<label class="error"></label>
					</td>
					</tr>
					<tr>
					<td >Status : <span class="red">*</span> </td>
					<td>
					<select name="status" class="form-control input-medium"  id="status">
					    <option >-- Select Status --</option>
						<option value="true" <% if(bean.isIsactive()==true){%> selected="selected" <%}%>>Active</option>
						<option value="false" <% if(bean.isIsactive()==false){%> selected="selected" <%}%>>Inactive</option>
					</select><label class="error"></label></td>
				
					<td colspan="2">
					  <input class="btn btn-md blue" type="submit" name="Submit" value="Submit"/>
					  <a class="btn btn-md purple" href="ad_designation.jsp">Back</a>
					</td>
				</tr>
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div>
	<%
}%>	
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
<script src="assets/scripts/custom/components-pickers.js"></script>
<script>
jQuery(function() {       
	$('#ad_designation_type_id').select2();
	$('#status').select2();
	jQuery.validator.addMethod("alphanumspace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter character, dot(.) and space only.");
	
	jQuery( "#frmDesignation" ).validate({
		  rules: {
			  designation: {
		      required: true,
		      alphanumspace:true,
		      maxlength:150
		    }
		  }
		});
	
});


</script>
<script type="text/javascript">

$(document).ready(function(e){
	//alert();
	$("#ad_designation_type_id").val("<%=bean.getAd_designation_type_id()%>");
});

</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>