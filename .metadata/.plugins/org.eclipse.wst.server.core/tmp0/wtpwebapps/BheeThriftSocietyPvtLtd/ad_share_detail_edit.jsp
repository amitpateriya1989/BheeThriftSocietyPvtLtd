
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Bean.ShareBean"%>
<%@page import="Model.Dao.ShareDao"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Bean.MemberShareBean"%>
<%@page import="Model.Dao.MemberShareDao"%>
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
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2-bootstrap.css"/>
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
				<a href="#">Share</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Share Detail</a><i class="fa fa-angle-right"></i>Edit</li>
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
	String[] AppMessage = (String[]) AppObj;
	if (AppMessage[1].isEmpty() != true
			&& AppMessage[1].equals("welcome") != true) {
%>
<div class="alert <%=AppMessage[0]%> alert-dismissable">
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
	<%=AppMessage[1]%>
</div>
<%
	}
%>
<!------------------------------------------------------------------- -->
	<!-- BEGIN BORDERED TABLE PORTLET-->
<%
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	String date1 = sdf.format((Date) session.getAttribute("openday"));
	String share_id = request.getParameter("ad_member_share_id");
	int ad_member_share_id = 0;
	if (share_id != null) {

		try {
			ad_member_share_id = Integer.parseInt(share_id);
		} catch (NumberFormatException n) {
			n.printStackTrace();
		}

		MemberShareDao sdao = new MemberShareDao();
		MemberShareBean sbean = sdao
				.getMemberShareById(ad_member_share_id);
		if (sbean != null) {
			MemberRegistrationBean member = new MemberRegistrationDao()
					.getMemberName(sbean.getAd_member_id());
%>	
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Edit Share</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmShare"  action="AdMemberShare?action=update&ad_member_share_id=<%=sbean.getAd_member_share_id()%>" method="post">
			<table class="table table-bordered">
			    <tr>
					<td width="15%">Member : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium" type="text" readonly="readonly" id="name" name="name" value="<%=member.getName()%>" /><label class="error"></label></td>
				
					<td width="15%">Member Id : <span class="red">*</span></td>
					<td width="35%">
					<input class="form-control input-medium " type="text" readonly="readonly" id="ad_member_id_1" name="ad_member_id_1"  value="<%=member.getAd_member_id()%>" /><label class="error"></label></td>
					</tr>
				<tr>
					<td width="15%">Allocation Date : <span class="red">*</span></td>
					<td width="35%">
					
					<input class="form-control input-medium date-picker" type="text" id="date_of_allocation" name="date_of_allocation"  value="<%=sdf.format(sbean.getDate_of_allocation())%>" /><label class="error"></label></td>
					<td width="15%">Qty : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium" type="text" id="no_of_share" name="no_of_share" value="<%=sbean.getQnt_of_share()%>" /><label class="error"></label></td>
				</tr>
				<tr>
					<td width="15%">Batch No : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium" type="text" id="batch_no" name="batch_no" value="<%=sbean.getBatch_no()%>"  /><label class="error"></label></td>
					<td width="15%">Share Amount : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium" type="text" id="share_amt" name="share_amt" value="<%=sbean.getShare_amt()%>" /><label class="error"></label></td>
				
				</tr>
				<tr>
				<td width="15%"> No From : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium" type="text" id="share_no_form" name="share_no_form" value="<%=sbean.getShare_no_form()%>"  /><label class="error"></label></td>
					<td width="15%"> No To : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium" type="text" id="share_no_to" name="share_no_to" value="<%=sbean.getShare_no_to()%>"  /><label class="error"></label></td>
					
				</tr>
				
				
				<tr>
					<td width="15%">Trx Type : <span class="red">*</span></td>
					<td width="35%">
					<select class="form-control input-medium" name="vtype" id="vtype">
					
									<option value="<%=sbean.getTrx_by()%>"><%=sbean.getTrx_by()%></option>
									<option value="Adjustment">Adjustment</option>
									<option value="Cheque">Cheque</option>
									<option value="DD">Demand Draft</option>
									<option value="Online">Online</option>
									
					</select>
					</td>
					<td width="15%">Status : <span class="red">*</span></td>
					<td width="35%"><select class="form-control input-medium" name="status" id="status">
					
									<option value="<%=sbean.getStatus()%>"><%=sbean.getStatus()%></option>
									<option value="Pending">Pending</option>
									<option value="Approved">Approved</option>
									
									
					</select>
					<label class="error"></label></td>
					
				</tr>
				<tr>
					<td width="15%">Loan Id : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium" type="text" id="loan_trx_id" name="loan_trx_id" value="<%=sbean.getLoan_trx_id()%>"  /><label class="error"></label></td>
					<td width="15%">Voucher Id : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium" type="text" id="ad_voucher_id" name="ad_voucher_id" value="<%=sbean.getAd_voucher_id()%>"  /><label class="error"></label></td>
					
				</tr>
					<tr>
					<td width="15%">IsActive : <span class="red">*</span></td>
					<td width="35%"><select class="form-control input-medium" name="isactive" id="isactive">
					
									<option value="<%=sbean.isIsactive()%>"><%=sbean.isIsactive()%></option>
									<option value="true">True</option>
									<option value="false">False</option>
									
									
					</select>
					<label class="error"></label></td>
					
					<td colspan="2" align="center">
					  <input class="btn btn-md blue" type="submit" name="Submit" value="submit"/>
					  <a class="btn btn-md purple" href="ad_share_detail_view.jsp">Back</a>
					</td>
				</tr>
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div>
	<%
		}
		}
	%>
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
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="assets/scripts/custom/components-pickers.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<script>
jQuery(function() {       

	$('#vtype').select2();
	$('#status').select2();
	$('#isactive').select2();
	jQuery.validator.addMethod("alphanumspace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter character and space only.");
	$('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true});
	jQuery( "#frmShare" ).validate({
		  rules: {
			  Gender: {
		      required: true,
		      alphanumspace:true,
		      maxlength:8
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