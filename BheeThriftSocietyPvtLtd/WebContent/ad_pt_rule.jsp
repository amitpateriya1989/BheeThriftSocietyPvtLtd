<%@page import="Model.Dao.PTRuleDao"%>
<%@page import="Model.Bean.BankRegionBean"%>
<%@page import="Model.Dao.BankRegionDao"%>
<%@page import="Model.Bean.StateBean"%>
<%@page import="Model.Dao.StateDao"%>
<%@page import="Model.Bean.BankBean"%>
<%@page import="Model.Dao.BankDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%@page import="Model.Bean.PTRuleBean"%>
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
			<li><a href="#">HR</a><i class="fa fa-angle-right"></i></li>
			<li><a href="#">PT Rules</a><i class="fa fa-angle-right"></i>Add and view</li>
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
	   <div class="caption">Add PT Rules</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmPtRule" action="AdPTRule?action=insert" method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Effective From : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium date-picker" type="text" name="effective_from" /><label class="error"></label></td>
					<td width="15%"></td>
					<td width="35%"></td>
				</tr>
				<tr>
					<td>Minimum Amount : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="min_amt" id="min_amt"  /><label class="error"></label></td>
					<td>Maximum Amount : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="max_amt" id="max_amt" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Regular Charge : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="r_c" /><label class="error"></label></td>
					<td>March Specific Charge : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="march_c" /><label class="error"></label></td>
				</tr>
				<tr>
					<td></td>
					<td>
					  <input class="btn btn-md blue" type="submit" name="Submit" value="submit"/>
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
	   <div class="caption">
		View PT Rules
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					  <th>S.No</th>
					  <th>Effective From</th>
					  <th>Effective to</th>
					  <th>Min Amount</th>
					  <th>Max Amount</th>
					  <th>Regular Charge</th>
					  <th>March Specific</th>
					  <th>Status</th>
				</tr>
				</thead>
				<tbody>
				<%
					PTRuleDao sdao=new PTRuleDao();
					ArrayList<PTRuleBean> slist=sdao.getAllPTRule();
					int i=0;
					if(slist!=null){
						for(PTRuleBean bean:slist){
				%>
				<tr>
					
				<td id="S.No"><%=++i %></td>
				<td id="Effective_from"><%=bean.getEffective_from() %></td>
				<td id="Effective_to">
					<%
						if(bean.getEffective_to()==null){
							out.print("Till Now");
						}else{
							out.print(bean.getEffective_to());
						}
					%>
				<td id="min_amount"><%=bean.getMin_amt() %></td>
				<td id="max_amounte"><%=bean.getMax_amt()%></td>
				<td id="Regular_charges"><%=bean.getRegular_charges() %></td>
				<td id="March_specified"><%=bean.getMarch_specific_charges() %></td>
				<td><% if(bean.isIsactive()==true){ %>
						<span class="badge badge-primary">Active</span>
						<% }else{ %>
						<span class="badge badge-warning">Inactive</span>
						<%} %>
				</td>
				</tr> 
				<%	
				}
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
<script>
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	$('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true});
	
	jQuery.validator.addMethod("validDate", function (value, element) {
        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	jQuery.validator.addMethod("greaterThan", function (value, element, param) {
		var target = $(param);
		if ( this.settings.onfocusout ) {
			target.unbind(".validate-greaterThan").bind("blur.validate-greaterThan", function() {
				$(element).valid();
			});
		}
		return parseFloat(value) > parseFloat(target.val());
    }, "Max must be greater than min.");
	
	jQuery("#frmPtRule").validate({
		  rules: {
			  effective_from: {
			      required: true,
			      validDate:true
		      },
		      min_amt:{
		    	  required: true,
		    	  number:true
		      },
		      max_amt:{
		    	  required: true,
		    	  number:true,
		    	  greaterThan:"#min_amt"
		      },
		      r_c:{
		    	  required: true,
		    	  number:true
		      },
		      march_c:{
		    	  required: true,
		    	  number:true
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