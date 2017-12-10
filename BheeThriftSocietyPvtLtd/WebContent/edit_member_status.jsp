<%@page import="Model.Bean.MemberStatusBean"%>
<%@page import="Model.Dao.MemberStatusDao"%>
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
			<li><a href="#">Member Status</a><i class="fa fa-angle-right"></i>Edit</li>
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
	String id=request.getParameter("ad_member_status_id");
	MemberStatusBean bean=null;
if(id!=null){
	try{
	int ad_member_status_id=Integer.parseInt(id);
	bean=new MemberStatusDao().getMemberStatusById(ad_member_status_id);
	}catch(NumberFormatException n){
		n.printStackTrace();
	}
}


%>
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Edit Member Status</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmMemberStatus" autocomplete="off" action="AdMemberStatus?action=update&ad_member_status_id=<%=bean.getAd_member_status_id()%>" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="20%">Member Status : <span class="red">*</span></td>
					<td width="30%"><input class="form-control input-medium" type="text" name="member_status" value="<%=bean.getMember_status()%>" /><label class="error"></label>
					</td>
					<td width="20%">Status : <span class="red">*</span> </td>
					<td width="30%">
					<select class="form-control input-medium" name="status">
					    <option>-- Select Status --</option>
						<option value="true" <% if(bean.isIsactive()==true){%> selected="selected" <%}%>>Active</option>
						<option value="false" <% if(bean.isIsactive()==false){%> selected="selected" <%}%>>Inactive</option>
					</select><label class="error"></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn  blue" type="submit" name="Submit" value="submit"/>
					  <a class="btn  purple" href="ad_member_status.jsp">Back</a>
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
	
	jQuery( "#frmMemberStatus" ).validate({
		  rules: {
			  member_status: {
		      required: true,
		      alphanumspace:true,
		      maxlength:30
		    },
		    status: {
		    	 required: true,
			      alphanumspace:true,
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