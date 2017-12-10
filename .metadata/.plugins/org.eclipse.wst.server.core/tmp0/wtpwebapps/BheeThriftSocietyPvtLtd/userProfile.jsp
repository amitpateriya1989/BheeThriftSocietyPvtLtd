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

<div class="col-md-12">
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i>
				<a href="#">Setup</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Profile</a><i class="fa fa-angle-right"></i>View</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<!-- END PAGE HEADER-->
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
	   <div class="caption">View Profile</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmBank" action="AdBank?action=insert" method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
					<td>Name : <span class="red">*</span></td>
					<td><input id="name" type="text" name="name" class="form-control input-medium" /><label class="error"></label>
					</td>
					</tr>
					<tr>
					<td>User Name : <span class="red">*</span></td>
					<td><input id="uname" type="text" name="uname" class="form-control input-medium" /><label class="error"></label>
					</td>
					<td>Password : <span class="red">*</span></td>
					<td><input id="pwd" type="password" name="pwd" class="form-control input-medium" readonly="readonly" /><label class="error"></label>
					</td>
				</tr>
				<tr>
						<td width="25%">
						<div class="userimg-block">
							<div class="user-pic">
							    <img class="uimg" id='photo_view' src="Image/emp.png" alt="Employee Photo" />
							</div>
							<div class="user-input">
							  <span class="text-center">Photo</span>
							<input type="file" name="photo" id="photo" accept="image/*" />
							</div>
							<label class="error"></label>
						   </div><!-- End userimg-block -->
			             </td>
						  <td width="25%">
						  <div class="userimg-block">
						  <div class="user-pic">
							    <img class="uimg" id='id_proof_view' src="Image/id.png" alt="Employee Id Card"  />
							</div>
							<div class="user-input">
							  <span class="text-center">ID Proof</span>
							<input type="file" name="id" id="id_proof" accept="image/*" />
							</div>
							<label class="error"></label>
						   </div><!-- End userimg-block -->
			            </td>
							<td colspan="2" width="25%">
							<div class="userimg-block">
						     <div class="user-pic">
							    <img class="uimg" id='sign_view' src="Image/sign.png" alt="Employee Sign"  />
							 </div>
							 <div class="user-input">
							  <span class="text-center">Sign</span>
							  <input type="file" name="sign" id="sign" accept="image/*" />
							</div>
							<label class="error"></label>
						   </div><!-- End userimg-block -->
							</td>
						</tr>
				<tr>
					<td></td>
					<td>
					  <input class="btn btn blue" type="submit" name="Submit" value="submit"/>
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
	
	jQuery.validator.addMethod("name", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z.\s]*$/.test(value);
		}, "Please enter character, dot(.) and space only.");
	
	jQuery( "#frmBank" ).validate({
		  rules: {
			  bank: {
		      required: true,
		      maxlength:100
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