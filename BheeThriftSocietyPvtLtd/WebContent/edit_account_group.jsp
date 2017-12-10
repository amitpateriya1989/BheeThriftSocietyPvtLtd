<%@page import="Model.Bean.AccountGroupBean"%>
<%@page import="Model.Dao.AccountGroupDao"%>
<%@page import="Model.Bean.AccountTypeBean"%>
<%@page import="Model.Dao.AccountTypeDao"%>
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
			<li><a href="#">Account</a><i class="fa fa-angle-right"></i> Account Group Edit</li>
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
	   <div class="caption">Edit Account Group</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <%
	   String id=request.getParameter("id");
	   int gid=Integer.parseInt(id);
	   AccountGroupDao dao=new AccountGroupDao();
	   AccountGroupBean bean=dao.getAccountGroupById(gid);
	   %>
	   <div class="portlet-body">
	    <form id="frmAccountGroup" action="AdAccountGroup?action=update&ad_ac_group_id=<%=bean.getAd_ac_group_id() %>" method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
					<td width="12%">Account Type <span class="red">*</span></td>
					<td width="38%">
						<select class="form-control input-medium" name="ad_ac_type_id" id="ad_ac_type_id" >
						 <option value="">--Select Account Type --</option>
						 <%AccountTypeDao dao1=new AccountTypeDao();
						 ArrayList <AccountTypeBean> alist=dao1.getAllAccountType();
						 if(alist!=null){
						 for(AccountTypeBean bean1:alist){%>
						 <option value="<%=bean1.getAd_ac_type_id()%>"><%=bean1.getName() %></option>
						 <%} }%>
					   </select><label class="error"></label>
					</td>
					<td width="12%">Account Group : <span class="red">*</span></td>
					<td width="38%"><input class="form-control input-medium" type="text" name="name" value="<%=bean.getName()%>"/><label class="error"></label>
					</td>
				</tr>
				<tr>
				<td>Status :</td>
				<td colspan="3">
				<select class="form-control input-medium" name="status">
					    <option>-- Select Status --</option>
						<option value="true" <% if(bean.isIsactive()==true){%> selected="selected" <%}%>>Active</option>
						<option value="false" <% if(bean.isIsactive()==false){%> selected="selected" <%}%>>Inactive</option>
					</select><label class="error"></label>
				</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn blue" type="submit" name="Submit" value="submit"/>
					  <a class="btn purple" href="ad_account_group.jsp">Back</a>
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
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script>
jQuery(function() {       
	
	
	jQuery.validator.addMethod("Alphanumspace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z&\s]*$/.test(value);
		}, "Please enter latter, special symbol(&) and space only.");
	
	jQuery( "#frmAccountGroup" ).validate({
		  rules: {
			  ad_ac_type_id:{
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
	
	jQuery('#ad_ac_type_id option[value='+<%=bean.getAd_ac_type_id()%>+']').attr('selected','selected');
});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>