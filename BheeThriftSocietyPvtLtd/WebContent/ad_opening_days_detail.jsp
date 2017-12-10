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
<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-datepicker/css/datepicker.css"/>
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
			<li><a href="#">Open Days</a><i class="fa fa-angle-right"></i>Opening Day Details Add and view</li>
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
	   <div class="caption">Add Opening Day Details</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmOpenDays" action="Ajax/view_opening_days_detail.jsp" method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
					<td width="15%">From Date : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium date-picker" type="text" name="fdate" id="fdate" /><label class="error"></label></td>
					<td width="15%">To Date : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium date-picker" type="text" name="tdate" id="tdate" /><label class="error"></label></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn-md blue" type="button" name="Submit" id="displayView" value="submit"/>
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
	   <div class="caption">Add Opening Day Details</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	   	<div id="displayDetails" style="height:450px;overflow-y:auto;"></div>
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
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script>
jQuery(function() {       
	jQuery('#dataTable1').DataTable({"scrollY":"200px","bPaginate": false});
	$('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true});
	
	jQuery.validator.addMethod("validDate", function(value, element) {
		  return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/.test(value);
		}, "Please enter valid (DD/MM/YYYY) Date formate.");
	
	jQuery( "#frmOpenDays" ).validate({
		  rules: {
			  fdate: {
		      required: true,
		      validDate:true
		    },
		    tdate:{
		       required: true,
			   validDate:true
		    }
		  }
	});//end validation form
	
	jQuery("#displayView").click(function(){
		var fdate = jQuery("#fdate").val();
		var tdate = jQuery("#tdate").val();
		
		if(jQuery( "#frmOpenDays" ).valid()==true){
			$.ajax({
				type:"post",
				url:$("#frmOpenDays").attr("action"),
				data:{"action":"listdetails","fdate":fdate,"tdate":tdate},
				success: function(data){
					if(data!="error"){
						jQuery('#displayDetails').html(data);
					}else{
						console.log(data);
					}
					
				},
				error: function(txtXhr, txtStatus, error){
					console.log(txtStatus);
				}
			});
		}else{
			return false;
		}
		
	});//end displayView click function
	
	
});//end dom
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>