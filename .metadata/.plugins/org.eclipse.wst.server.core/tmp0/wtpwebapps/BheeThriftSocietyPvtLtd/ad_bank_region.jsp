<%@page import="Model.Bean.BankRegionBean"%>
<%@page import="Model.Dao.BankRegionDao"%>
<%@page import="Model.Bean.ReligionBean"%>
<%@page import="Model.Dao.ReligionDao"%>
<%@page import="Model.Bean.GenderBean"%>
<%@page import="Model.Dao.GenderDao"%>
<%@page import="Model.Bean.StateBean"%>
<%@page import="Model.Dao.StateDao"%>
<%@page import="Model.Bean.SalutationBean"%>
<%@page import="Model.Dao.SalutationDao"%>
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
			<li><a href="#">Bank Region</a><i class="fa fa-angle-right"></i>Add and view</li>
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
	   <div class="caption">Add Bank Region</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmBankRegion" action="AdBankRegion?action=insert" method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
					<td>Bank Region : <span class="red">*</span></td>
					<td ><input type="text" name="region" class="form-control input-medium" style="text-transform: uppercase;" tabindex="1" autofocus="autofocus" /><label class="error"></label>
					</td>
					<td >Region Code : <span class="red">*</span></td>
					<td ><input type="text" name="region_code" class="form-control input-medium" style="text-transform: uppercase;" tabindex="2"/><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
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
		View Bank Region
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th>Sno.</th>
					<th>Bank Region</th>
					<th>Region Code</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
				</thead>
				<tbody>
				<%BankRegionDao dao=new BankRegionDao();
					ArrayList<BankRegionBean> slist=dao.getAllBankRegion();
					int i=0;
					if(slist!=null){
						for(BankRegionBean bean:slist){
				%> 
				 <tr>
				   <td><%=++i %></td>
				   <td><%=bean.getRegion() %></td>
				    <td><%=bean.getRegion_code() %></td>
				   <td><% if(bean.isIsactive()==true){ %>
						<span class="badge badge-primary">Active</span>
						<% }else{ %>
						<span class="badge badge-warning">Inactive</span>
						<%} %>
				  </td>
				   <td>
				   <a class="btn btn-xs green" href="AdBankRegion?action=edit&ad_bank_region_id=<%=bean.getAd_bank_region_id()%>">
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
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script>
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	
	jQuery.validator.addMethod("Alphanumspace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z0-9\s]*$/.test(value);
		}, "Please enter character,Number and space only.");
	
	jQuery( "#frmBankRegion" ).validate({
		  rules: {
			  region: {
		      required: true,
		      Alphanumspace:true,
		      maxlength:30
		    },region_code: {
			      required: true,
			      Alphanumspace:true,
			      maxlength:30
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