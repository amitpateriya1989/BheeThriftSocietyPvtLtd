<%@page import="Model.Bean.ThriftRoiBean"%>
<%@page import="Model.Bean.VoucherBean"%>
<%@page import="Model.Dao.VoucherDao"%>
<%@page import="Model.Dao.ThriftRoiDao"%>
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
			<li><a href="#">Pay Interest</a><i class="fa fa-angle-right"></i>Calculate Thrift Interest Add and view</li>
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

<%
	   String id=request.getParameter("ad_thrift_roi_id");
	   int gid=Integer.parseInt(id);
	   ThriftRoiDao dao=new ThriftRoiDao();
	   ThriftRoiBean bean=dao.getThriftRoiById(gid);
	   %>
<!------------------------------------------------------------------- -->
	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Add  Thrift ROI</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmThrifRoi" action=AdThriftRoi?action=update&ad_thrift_roi_id=<%=bean.getAd_thrift_roi_id() %> method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Roi : <span class="red">*</span></td>
					<td width="30%"><input class="form-control input-medium" type="text" name="roi" id="roi" value=<%=bean.getRoi() %> /><label class="error"></label></td>
					<td width="15%">Compound Frequency : <span class="red">*</span></td>
					<td width="30%"><input class="form-control input-medium " type="text" name="ratio" id="ratio" value=<%=bean.getRatio() %> /><label class="error"></label></td>
					</tr>
					<tr>
					<td width="15%">Effective From Date : <span class="red">*</span></td>
					<td width="30%"><input class="form-control input-medium date-picker" value="<%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getEffectivefromdate()) %>" type="text" name="effectivefdate" id="effectivefdate" /><label class="error"></label></td>
					<td width="15%">Effective To Date : <span class="red">*</span></td>
					<td width="30%"><input class="form-control input-medium date-picker" value="<%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getEffectivetodate()) %>" type="text" name="effectiveto" id="effectiveto" /><label class="error"></label></td>
					
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn blue" type="submit" name="Submit" value="submit"/>
					  <input class="btn btn green" type="reset" name="Clear"/>
					</td>
				</tr>
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div>
<!------------------------------------------------------------------- -->

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
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>

<script>
jQuery(function() {       
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true});
	
	jQuery.validator.addMethod("Alphanumspace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter latter and space only.");
	
	jQuery.validator.addMethod("validDate", function (value, element) {
        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	jQuery( "#frmThrifRoi" ).validate({
		  rules: {
			 roi:{
		         required:true,
		    	 number:true
		    	 },
		      effectivefdate:{
		    	 required: true,
			     validDate: true
		      },
		      ratio:{
		    	  required:true,
		    	  number:true
		      },
		      effectiveto:{
			    	 required: true,
				     validDate: true
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