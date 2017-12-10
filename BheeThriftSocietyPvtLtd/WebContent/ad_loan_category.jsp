<%@page import="Model.Bean.LoanCategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%@page import="Model.Dao.LoanCategoryDao"%>
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
			<li><a href="#">Loan Category</a><i class="fa fa-angle-right"></i>Add and view</li>
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
	   <div class="caption">Add Loan Category</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
       <form  action="AdLoanCategory?action=insert" method="post" id="frmLoanCategory" autocomplete="off">
		<table class="table table-bordered">
				
					<tr>
						<td width="20%" >Loan Category : <span class="red">*</span></td>
					    <td width="80%">	<input class="form-control input-medium" type="text" name="name"/></td>					
					</tr>					
					<tr>
						<td></td>
				    	<td >
						<input class="btn btn-xs blue" type="submit" name="Submit" value="submit"/>
					    <input class="btn btn-xs green" type="reset" name="Clear"/>
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
		View Loan Category
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"></a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<!-- <div id="SalutationTableContainer"></div> -->
						<table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th>Sno.</th>
					<th>Loan Type</th>
					<th>Update By</th>
					<th>Created</th>
					<th>Created By</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
				</thead>
				<tbody>
				<%LoanCategoryDao dao=new LoanCategoryDao();
					ArrayList<LoanCategoryBean> slist=dao.getAllLoanCategory();
					int i=0;
					if(slist!=null){
						for(LoanCategoryBean bean:slist){
				%>
				 <tr>
				   <td><%=++i %></td>
				   <td><%=bean.getName() %></td>
				   <td><%=bean.getUpdatedby() %></td>
				   <td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getCreated()) %></td>
				   <td><%=bean.getCreatedby() %></td>
				   <td><% if(bean.isIsactive()==true){ %>
						<span class="badge badge-primary">Active</span>
						<% }else{ %>
						<span class="badge badge-warning">Inactive</span>
						<%} %>
				  </td>
				   <td>
				   <a class="btn btn-xs green" href="AdLoanCategory?action=edit&id=<%=bean.getAd_loan_category_id()%>">
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
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter character and space only.");
	
	jQuery( "#frmLoanCategory" ).validate({
		  rules: {
			 name: {
		      required: true,
		      maxlength:40,
		      Alphanumspace:true
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