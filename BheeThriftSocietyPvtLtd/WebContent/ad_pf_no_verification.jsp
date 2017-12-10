<%@page import="Model.Bean.BankBean"%>
<%@page import="Model.Dao.BankDao"%>
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
			<li><a href="#">PF No Verification</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
<div class="col-md-12">
<!-- Containt Start -->
<div id="ErMessage"></div><!-- End ErMessage -->
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
	   <div class="caption">PF No Verification Check</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmPfNumber" action="AdMemberRegistration?action=newMember" method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
					<td width="20%">PF Number : <span class="red">*</span></td>
					<td width="80%"><input class="form-control input-medium" type="text" click="verify_pf" name="ad_pf_no" id="ad_pf_no" /><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
					  <input class="btn btn blue" type="submit" id="submit" name="Submit" value="submit"/>
					  <input class="btn btn green" type="reset" name="Clear"/>
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
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script>
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	
	jQuery.validator.addMethod("Alphanumspacedot", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z.\s]*$/.test(value);
		}, "Please enter character, dot(.) and space only.");
	
	jQuery( "#frmPfNumber" ).validate({
		  rules: {
			  ad_pf_no: {
		      required: true,
		      digits:true
		    }
		  }
		});
});
</script>
<script type="text/javascript">

	$("#submit").click(function(){
		if ($("#frmPfNumber").valid()) {
			var ad_pf_no=$("#ad_pf_no").val();	
			$.ajax({
				   type: "POST",
				   url: "AdMemberRegistration?action=verifypfno&ad_pf_no="+ad_pf_no,
				   async:false,
				   success: function(data)
				   {	//alert(data);
				   		if(data==0){
				   			$("#ErMessage").html('<div class="alert alert-info alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button><strong>Info!</strong> Member Has Not Registred in Society......!!.</div>');
				   			$("#ad_pf_no").val(0);
				   			return true;
				   		}else{
				   			$("#ErMessage").html('<div class="alert alert-info alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button><strong>Info!</strong> Member '+data+' Has Already Registred in Society......!!</div>');
					   		return true;  			  				
				   		}
					}
				});
		}else{
			$("#ErMessage").html('<div class="alert alert-warning alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button><strong>Warning!</strong> Something went wrong. Please check.</div>');
		}
	});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>