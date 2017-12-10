
<%@page import="org.apache.commons.beanutils.BeanAccessLanguageException"%>
<%@page import="Model.Dao.UserDao"%>
<%@page import="Model.Bean.ListItemBean"%>
<%@page import="Model.Dao.ListItemDao"%>
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
			<li><a href="#">List Item</a><i class="fa fa-angle-right"></i>Add and view</li>
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
<div class="caption">Add List Item</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<form id="frmListItem" action="AdListItem?action=insert" method="post" autocomplete="off" >
<table class="table table-bordered">

<%
							String list_id=request.getParameter("ad_list_id");
							int ad_list_id=0;
							ListBean lbean=null;
							if(list_id!=null){
								
								try{
								
									ad_list_id=Integer.parseInt(list_id);
									
									
								}catch(NumberFormatException n){
									n.printStackTrace();
								}
								ListDao listdao=new ListDao();
								lbean=listdao.getListById(ad_list_id);
							}	
								%>	
<tr>
   <td width="20%">List  : <span class="red">*</span></td>
   <td width="80%">	
       <select class="form-control input-medium" name="ad_list_id" id="ad_list_id" >
		 <option value="<%=lbean.getAd_list_id()%>"><%=lbean.getName() %></option>
		 
	   </select>
	</td>
 </tr>
 <tr>
 <td>Item : <span class="red">*</span></td>
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
<div class="caption">View List Item</div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a>
	</div>
	</div>
	<div class="portlet-body" style="height: 450px;overflow-y:auto;">
	<table id="dataTable1" class="table table-striped table-bordered table-hover">
		<thead>
		   <tr>
			<th>Sno</th>
			
			<th>Item</th>
			<th>Created</th>
			<th>CreatedBy</th>
			<th>Status</th>
			<th>Action</th>
		  </tr>
		</thead>
		<tbody>
		    <% ListItemDao sdao=new ListItemDao();
			   ArrayList<ListItemBean> slist=sdao.getListItemByListId(ad_list_id);
			   int i=0;
			   if(slist.isEmpty()!=true){
			     for(ListItemBean bean:slist){
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
			  <a class="btn btn-xs green" href="AdListItem?action=edit&ad_list_item_id=<%=bean.getAd_list_item_id()%>"><i class="fa fa-edit"></i> Edit</a>
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
		  return this.optional(element) || /^[a-zA-Z0-9&\s\-]*$/.test(value);
		}, "Please enter latter, special symbol(&) and space only.");
	
	jQuery("#frmListItem").validate({
		  rules: {
			  ad_list_id:{
				  required:true,
				  digits:true
			  },
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
