<%@page import="Model.Bean.GradeBean"%>
<%@page import="Model.Dao.GradeDao"%>
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
			<li><a href="#">Grade Details</a><i class="fa fa-angle-right"></i>Add and view</li>
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
	   <div class="caption">Add Grade Details</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmGrade" action="AdGrade?action=insert" method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Grade  : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium date-picker" type="text" name="grade_name1" /><label class="error"></label></td>
					<td width="15%">Basics:  <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium" type="text" name="basics" id="basics"  /><label class="error"></label></td>
				</tr>
				<tr>
					<td>DA (%) : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="da" id="da"  /><label class="error"></label></td>
					<td>Conveyance (%) : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="convey" id="convey" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>HRA (%) : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="hra" /><label class="error"></label></td>
					<td>Staff Welfare (%) : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="sw" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Medical (%) : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="mdcl" /><label class="error"></label></td>
					<td> Allowance (%) : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="alwnc" /><label class="error"></label></td>
				</tr>
				<tr>
					<td></td>
					<td>
					  <input class="btn btn-md blue" type="submit" name="Submit" value="submit"/>
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
		View PT Rules
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					  <th>S.No</th>
					  <th>Grade Name</th>
					  <th>Basics</th>
					  <th>DA</th>
					  <th>Conveyance</th>
					  <th>HRA</th>
					  <th>Staff Welfare</th>
					  <th>Medical</th>
					  <th>Allowance</th>
					  <th>Status</th>
					  <th>Action</th>
				</tr>
				</thead>
				<tbody>
				<%
					GradeDao sdao=new GradeDao();
					ArrayList<GradeBean> slist=sdao.getAllGrade();
					int i=0;
					if(slist!=null){
						for(GradeBean bean:slist){
				%> 
				<tr>
					
				<td><%=++i %></td>
				<td><%=bean.getGrade_name()%></td>
				<td><%=bean.getBasics() %></td>
				<td><%=bean.getDa()%></td>
				<td><%=bean.getConvey() %></td>
				<td><%=bean.getHra() %></td>
				<td><%=bean.getSw()%></td>
				<td><%=bean.getMdcl()%></td>
				<td><%=bean.getAlwnc() %></td>
				<td><% if(bean.isIsactive()==true){ %>
						<span class="badge badge-primary">Active</span>
						<% }else{ %>
						<span class="badge badge-warning">Inactive</span>
						<%} %>
				</td>
				<td>
					  <a class="btn btn-xs green" href="AdGrade?action=edit&ad_grade_id=<%=bean.getAd_grade_id()%>">
				      <i class="fa fa-edit"></i> edit</a>
				</td>
				</tr> 
				<%	
				}
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