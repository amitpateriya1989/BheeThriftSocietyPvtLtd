<%@page import="Model.Bean.DesignationTypeBean"%>
<%@page import="Model.Dao.DesignationTypeDao"%>
<%@page import="Model.Bean.DesignationBean"%>
<%@page import="Model.Dao.DesignationDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%@include file= "validation.html"%>

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
			<li><a href="#">Designation</a><i class="fa fa-angle-right"></i>Add and view</li>
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
	   <div class="caption">Add Designation</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmDesignation" action="AdDesignation?action=insert" method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
				<td width="15%">Type : <span class="red">*</span></td>
					<td width="35%"><select class=" input-medium" name="ad_designation_type_id" id="ad_designation_type_id" >
					<option value="">-- Designation Type --</option>
					<%
					DesignationTypeDao dao=new DesignationTypeDao();
					ArrayList<DesignationTypeBean> desglist=dao.getAllDesignationType();
					if(desglist.isEmpty()!=true){
						for(DesignationTypeBean bean:desglist){
							%>
							<option value="<%=bean.getAd_designation_type_id()%>"><%=bean.getDesignation_type() %></option>
							<%
						}
					}
					%>
					
					</select>
					<label class="error"></label>
					</td>
					<td width="15%">Designation : <span class="red">*</span></td>
					<td width="35%" ><input class="form-control input-medium" type="text" name="designation" /><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn-md blue" type="submit" name="submit" value="Submit"/>
					  <input class="btn btn-md green" type="reset" name="Clear"/>
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
		View Designation
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
			<tr>
			<th>Sno.</th>
			<th>Designation Type</th>
			<th>Designation</th>
			<th>Is Active</th>
			<th>Action</th>
				</tr>
				</thead>
				<tbody>
					<%DesignationDao dao1=new DesignationDao();
					ArrayList<DesignationBean> slist=dao1.getAllDesignation();
					int i=0;
					if(slist.isEmpty()!=true){
						for(DesignationBean bean:slist){
							%> 
				 <tr>
				   <td><%=++i %></td>
				    <td><%=new DesignationTypeDao().getDesignationTypeById(bean.getAd_designation_type_id()).getDesignation_type() %></td>
				   <td><%=bean.getDesignation() %></td>
				   <td>
					<% if(bean.isIsactive()==true){ %>
						<span class="badge badge-primary">Active</span>
						<% }else{ %>
						<span class="badge badge-warning">Inactive</span>
					<%} %>
					</td>
				   <td>
				     <a class="btn btn-xs green" href="AdDesignation?action=edit&ad_designation_id=<%=bean.getAd_designation_id() %>">
				  <i class="fa fa-edit"></i> edit</a>
				    </td>
				 </tr>
				 <%	}
					}
				  %>
				</tbody>
			</table>
			<div class="clearfix"></div>
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
<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script>
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	$('#ad_designation_type_id').select2();
	jQuery.validator.addMethod("alphanumspace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter character and space only.");
	
	jQuery( "#frmDesignation" ).validate({
		  rules: {
			  designation: {
		      required: true,
		    
		      maxlength:50
		    },
		    ad_designation_type_id:{
		    	required: true,
		    	digits:true
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