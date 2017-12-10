<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%@ include file= "Layout/header.jsp" %>
<link rel="stylesheet" href="assets/plugins/data-tables/DT_bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-datepicker/css/datepicker.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2-bootstrap.css"/>

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
				<a href="#">Home</a><i class="fa fa-angle-right"></i>
			</li>
			
			<li><a href="#">Thrift</a><i class="fa fa-angle-right"></i>Add</li>
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
	   <div class="caption">Add Thrift</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmThrift" action="AdThriftTrx?action=insert" method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Member<span class="red">*</span></td>
					<td width="35%">
					<select name="ad_member_id" id="ad_member_id" class="form-control input-medium">
					<option value="">--Select--</option>
					<%
						ArrayList<MemberRegistrationBean> list=new MemberRegistrationDao().getAllMemberlistName();
					    if(list.isEmpty()!=true){
					    	for(MemberRegistrationBean bean:list){
					    		%>
					    		<option value="<%=bean.getAd_member_id()%>"><%=bean.getAd_society_no()+" | "+bean.getName() %></option>
					    		<%
					    	}
					    }
					
					%>
					</select><label class="error"></label>
					</td>
					<td width="15%">Date <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium date-picker" placeholder="dd/MM/yyyy" type="text" name="trx_date" /><label class="error"></label></td>
					
				</tr>
				<tr>
					<td>Thrift Amt <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="thrift_amt" id="excess_amt" value="0.0" /><label class="error"></label></td>
					<td>Withdrawal Amt <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="withdrawal_amt" id="withdrawal_amt" value="0.0" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Balance Amt <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="balance" id="balance" value="0.0" /><label class="error"></label></td>
					<td>Status <span class="red">*</span></td>
					<td><select class="form-control input-medium"  name="status" id="status">
						<option value="">Select</option>
						<option value="Approved">Approved</option>
						<option value="Pending">Pending</option>
						<option value="Cancel">Cancel</option>
					</select><label class="error"></label></td>
				</tr>
				<tr>
					<td>Isactive <span class="red">*</span></td>
					<td><select class="form-control input-medium"  name="isactive" id="isactive" >
					<option value="">Select</option>
						<option value="true">True</option>
						<option value="false">False</option>
					</select><label class="error"></label></td>
					<td>Voucher Id <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="ad_voucher_id" id="ad_voucher_id" />
					<label class="error"></label></td>
				</tr>
				<tr>
					<td>Detail <span class="red">*</span></td>
					<td><input type="text" class="form-control input-medium"  name="discription" id="discription" >
					</td>
					<td colspan="2" align="center">
					  <input class="btn btn-md blue" type="submit" name="Submit" value="Submit"/>
					  <input class="btn btn-md green" type="reset" name="Clear" value="reset"/>
					</td>
				</tr>
				
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div>
	
	
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
<%
				Date date = (Date)session.getAttribute("openday");
				String to=new SimpleDateFormat("dd/MM/yyyy").format(date);
				
				%>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@ include file= "Layout/footer.jsp" %>
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<script>
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	jQuery('#ad_member_id').select2();
	jQuery('#status').select2();
	jQuery('#isactive').select2();
	
	
	$('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate : '<%=to%>',autoclose: true});
	
	jQuery.validator.addMethod("validDate", function (value, element) {
        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	jQuery( "#frmThrift" ).validate({
		  rules: {
			  trx_date: {
			      required: true,
			      validDate:true
		      },
		      thrift_amt:{
		    	  required: true,
		    	  number:true
		      },withdrawal_amt:{
		    	  required: true,
		    	  number:true
		      },balance:{
		    	  required: true,
		    	  number:true
		      },status:{
		    	  required: true
		      },
		      ad_member_id:{
		    	  required: true,
		    	  digit:true
		      },ad_voucher_id:{
		    	  required: true,
		    	  digit:true
		      },
		      isactive:{
		    	  required: true
		      },
		      discription:{
		    	  required: true
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