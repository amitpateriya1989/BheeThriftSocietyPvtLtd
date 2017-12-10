<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Bean.MemberRegistrationMasterBean"%>
<%@page import="Model.Dao.MemberRegistrationMasterDao"%>
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
			<li><a href="#">Member Registration</a><i class="fa fa-angle-right"></i>Edit</li>
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
	String id=request.getParameter("ad_member_registration_master_id");
	MemberRegistrationMasterBean bean=null;
if(id!=null){
	try{
	int ad_member_registration_master_id=Integer.parseInt(id);
	bean=new MemberRegistrationMasterDao().getMemberRegistrationMasterById(ad_member_registration_master_id);
	}catch(NumberFormatException n){
		n.printStackTrace();
	}
}
%>	
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Member Registration</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmMemberReg" autocomplete="off" action="AdMemberRegistrationMaster?action=update&ad_member_registration_master_id=<%=bean.getAd_member_registration_master_id()%>" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Membership Fees : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium" type="text" name="membership_fees" value="<%=bean.getMembership_fees()%>" /><label class="error"></label></td>
					<td width="15%">FGDS Fund : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium" type="text" name="fgds_fund" value="<%=bean.getFgds_fund()%>" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>DCF : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="dcf" value="<%=bean.getDcf()%>" /><label class="error"></label></td>
					<td>Share : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="share" value="<%=bean.getShare()%>" /><label class="error"></label></td>
				</tr>
				<tr>
				    <td>Status : <span class="red">*</span></td>
					<td colspan="3">
					<select class="form-control input-medium" name="status">
					    <option>-- Select Status --</option>
						<option value="true" <% if(bean.getIsactive()==true){%> selected="selected" <%}%>>Active</option>
						<option value="false" <% if(bean.getIsactive()==false){%> selected="selected" <%}%>>Inactive</option>
					</select><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn blue" type="submit" name="Submit" value="Update"/>
					  <a class="btn btn purple" href="ad_member_registration_master.jsp">Back</a>
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
	
	jQuery.validator.addMethod("Alphanumspacedot", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z.\s]*$/.test(value);
		}, "Please enter character, dot(.) and space only.");
	
	jQuery( "#frmMemberReg" ).validate({
		  rules: {
			membership_fees: {
		      required: true,
		      number:true
		    },
		    fgds_fund: {
			      required: true,
			      number:true
			},
			dcf: {
			      required: true,
			      number:true
			},
			 share: {
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