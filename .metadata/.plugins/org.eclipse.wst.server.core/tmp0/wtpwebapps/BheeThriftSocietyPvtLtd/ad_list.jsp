
<%@page import="Model.Dao.UserDao"%>
<%@page import="Model.Bean.ListBean"%>
<%@page import="Model.Dao.ListDao"%>
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
			<li><a href="#">List</a><i class="fa fa-angle-right"></i>Add and view</li>
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
<div class="caption">Add List</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<form id="frmList" action="AdList?action=insert" method="post" autocomplete="off" >
<table class="table table-bordered">	

 <tr>
 <td>Name : <span class="red">*</span></td>
 <td><input type="text" name="name"  class="form-control input-medium"/></td>
 </tr>
 <tr>
	<td></td>
	<td>
	  <input class="btn blue" type="submit" name="Submit" value="submit"/>
	  
	   <input class="btn  green" type="reset" name="Clear"/>
	</td>
 </tr>						
 </table>
 </form>	
 </div><!-- End portlet-body -->
</div>
<!-- BEGIN BORDERED TABLE PORTLET-->
<div class="portlet box purple">
<div class="portlet-title">
<div class="caption">View List </div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a>
	</div>
	</div>
	<div class="portlet-body" style="height: 450px;overflow-y:auto;">
	<table id="dataTable1" class="table table-striped table-bordered table-hover">
		<thead>
		   <tr>
			<th>Sno</th>
			<th>Name</th>
			<th>Created</th>
			<th>CreatedBy</th>
			<th>Status</th>
			<th>Action</th>
		  </tr>
		</thead>
		<tbody>
		    <% ListDao sdao=new ListDao();
			   ArrayList<ListBean> slist=sdao.getAllList();
			   int i=0;
			   if(slist.isEmpty()!=true){
			     for(ListBean bean:slist){
			 %> 
		<tr>
			<td><%=++i %></td>
			<td><%=bean.getName() %></td>
			<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getCreated().getTime()) %></td>
			<td><%=new UserDao().getUserById(bean.getCreatedby()).getName() %></td>
			<td><% if(bean.isIsactive()==true){ %>
				<span class="badge badge-primary">Active</span>
				<% }else{ %>
				<span class="badge badge-warning">Inactive</span>
				<%} %>
			</td>
			<td>
				<a class="btn btn-xs blue" href="AdList?action=ad_list_item&ad_list_id=<%=bean.getAd_list_id()%>"><i class="fa fa-edit"></i> Add Item</a>
			  	<a class="btn btn-xs green" href="AdList?action=edit&ad_list_id=<%=bean.getAd_list_id()%>"><i class="fa fa-edit"></i> Edit</a>
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
<!--------------------------------------------------------------------->
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
	jQuery('#dataTable1').DataTable({"scrollY":"200px","bPaginate": false});
	
	jQuery.validator.addMethod("AlphaSpace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z&\s]*$/.test(value);
		}, "Please enter latter, special symbol(&) and space only.");
	
	jQuery("#frmList").validate({
		  rules: {
			  
			  name: {
		          required: true,
		          AlphaSpace:true,
		          maxlength:50
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
