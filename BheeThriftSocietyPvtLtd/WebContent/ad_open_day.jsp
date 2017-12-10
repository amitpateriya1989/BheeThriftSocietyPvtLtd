<%@page import="Model.Dao.DayOpenDao"%>
<%@page import="Model.Bean.DayOpenBean"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-datepicker/css/datepicker.css"/>

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-full-width">

<!------------------------------------------------------------------- -->
<div class="hidden">
<%
try{
String  V_Trx_No= "";
V_Trx_No= (String)request.getParameter("no");
if(V_Trx_No==null){
	V_Trx_No = "";
}
%>
<input type="hidden" name="V_Trx_No" id="V_Trx_No" value="<%=V_Trx_No%>" /><!-- for display message after submit -->
</div>
<!------------------------------------------------------------------- -->

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
			<li><a href="#">Open Days</a><i class="fa fa-angle-right"></i>Day Open Add and View</li>
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
	   <div class="caption">Add Opening Days</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmOpenDays" action="AdDayOpen?action=insert" method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Date : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium date-picker" type="text" name="open_days" id="open_days" /><label class="error"></label></td>
					<td width="15%">Status : <span class="red">*</span></td>
					<td width="35%">
					<select class="form-control input-medium" name="status" id="status">
						<option value="">--Select Status--</option>
						<option value="CLOSE">CLOSE</option>
					</select>
					<label class="error"></label></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn-md blue" type="submit" name="Submit" value="submit"/>
					  <input class="btn btn-md green" type="reset" name="Clear"/>
					</td>
				</tr>
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div>
<!-- BEGIN BORDERED TABLE PORTLET-->
	 	
	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		View Days Detail
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body" style="height:450px;overflow-y:auto;">
			<table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th>Sno.</th>
					<th>Date</th>
					<th>Opening Status</th>
					<th>Ending Status</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
				</thead>
				<tbody>
				<%
				DayOpenDao dao=new DayOpenDao();
					ArrayList<DayOpenBean> slist=dao.getAllDayOpen();
					int i=0;
					if(slist.isEmpty()!=true){
						for(DayOpenBean bean:slist){
							
					
				%> 
				 <tr>
				   <td><%=++i %></td>
				   <td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getOpen_days()) %></td>
				    <td><%=bean.getOpening_status() %></td>
				     <td><%=bean.getClosing_status() %></td>
				   <td><% if(bean.isIsactive()==true){ %>
						<span class="badge badge-primary">Active</span>
						<% }else{ %>
						<span class="badge badge-warning">Inactive</span>
						<%} %>
				   <td>
				   <a class="btn btn-xs green" href="AdDayOpen?action=edit&ad_open_days_id=<%=bean.getAd_open_days_id()%>">
				  <i class="fa fa-edit"></i> edit</a>
				   </td>
				 </tr>
				<%	}
					}
					 %>
				</tbody>
			</table>
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

<%
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
String date1=null;
if(session.getAttribute("openday")!=null){
	
date1=sdf.format((Date)session.getAttribute("openday"));
System.out.print("if");
}else if(date1==null){
	System.out.print("else");
	DayOpenBean bean=new DayOpenDao().getLastDayOpen();
	System.out.print(bean.getOpen_days());
	date1=sdf.format(bean.getOpen_days().getTime());
	System.out.print(date1);
}
%>
<script>
jQuery(function() {       
	jQuery('#dataTable1').DataTable({"scrollY":"200px","bPaginate": false});
	
	
	$('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true,endDate:'<%=date1%>'});
	
	jQuery.validator.addMethod("validDate", function(value, element) {
		  return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/.test(value);
		}, "Please enter valid (DD/MM/YYYY) Date formate.");
	
	jQuery.validator.addMethod("Alphabet", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z]*$/.test(value);
		}, "Please enter alphabet only.");
	
	jQuery( "#frmOpenDays" ).validate({
		  rules: {
			  open_days: {
		      required: true,
		      validDate:true
		    },
		    status:{
		       required: true,
		       Alphabet:true,
		       maxlength:8
		    }
		  }
	});//end validation form
	
	
});//end dom
</script>
<script type="text/javascript">
$(function(){
	var V_Trx_No = $("#V_Trx_No").val();
	
	if(V_Trx_No.trim()!=""){
		$('.custom-messageBox').modal('show');
		$('#custom-page-message').html(V_Trx_No);
	}
	
});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>