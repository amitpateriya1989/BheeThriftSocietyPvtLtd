<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Dao.DividendMasterDao"%>
<%@page import="Model.Bean.DividendMasterBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
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
			<li><a href="#">Dividend Master</a><i class="fa fa-angle-right"></i>Add and View</li>
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
	   <div class="caption">Add Dividend Master</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmDividendMaster" action="AdDividendMaster?action=insert" method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
				    <td width="20%">Year From : <span class="red">*</span></td>
					<td width="30%"><input class="form-control input-medium date-picker" type="text" name="year_from" /></td>
					<td width="20%">Year To : <span class="red">*</span></td>
					<td width="30%"><input class="form-control input-medium date-picker" type="text" name="year_to" value="" /><label class="error"></label></td>			
				</tr>
				<tr>
					<td>Conveyance Amount : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="convence_amt" value="0.0" /><label class="error"></label></td>
					<td>Dividend Percentage (%): <span class="red">*</span></td>
				    <td><input class="form-control input-medium" type="text" name="dividend_per" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Year : <span class="red">*</span></td>
					<td>
					<select  name="div_year" id="div_year"  class="input-medium">
					<option value="">-- Select Year --</option>
					<option value="2015-16">2015-16</option>
					<option value="2016-17">2016-17</option>
					<option value="2017-18">2017-18</option>
		 			<option value="2018-19">2018-19</option>
					<option value="2019-20">2019-20</option>
					<option value="2020-21">2020-21</option>
					<option value="2021-22">2021-22</option>
					<option value="2022-23">2022-23</option>
					<option value="2023-24">2023-24</option>
					<option value="2024-25">2024-25</option>
					<option value="2025-26">2025-26</option>
					<option value="2026-27">2026-27</option>
					<option value="2027-28">2027-28</option>
					<option value="2028-29">2028-29</option>
					<option value="2029-30">2029-30</option>
					
								 			
		 			</select>
					<label id="" class="error "></label>
					</td>				
					<td colspan="2">
					  <input class="btn btn blue" type="submit" name="Submit" value="submit"/>
					  <input class="btn btn green" type="reset" name="Clear"/>
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
		View Divident Master
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th>S. no.</th>
					<th>From Year </th>
					<th>Year To </th>
					<th>Conveyance</th>
					<th>ROI</th>
					<th>Year</th>
					<th>Status</th>
					<th>Action</th>
					
				</tr>
				</thead>
				<tbody>
				<%DividendMasterDao dao1=new DividendMasterDao();
					ArrayList<DividendMasterBean> slist=dao1.getAllDividendMaster();
					int i=0;
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String year_from = "";
					String year_to ="";
					
					if(slist!=null){
						for(DividendMasterBean bean:slist){
						
						if(bean.getYear_from()!=null || bean.getYear_from().equals("")!=false){
							year_from = sdf.format(bean.getYear_from());
						}
						
						if(bean.getYear_to()!=null || bean.getYear_to().equals("")!=false){
							year_to = sdf.format(bean.getYear_to());
						}
							
				%>
				 <tr>
				   <td><%=++i %></td>
				   <td><%=year_from%></td>
				   <td><%=year_to%></td>
				   <td><%=bean.getAd_convence_amt()%></td>
				   <td><%=bean.getAd_dividend_per()%></td>
				    <td><%=bean.getYear()%></td>
				   <td><% if(bean.isIsactive()==true){ %>
						<span class="badge badge-primary">Active</span>
						<% }else{ %>
						<span class="badge badge-warning">Inactive</span>
						<%} %>
				  </td>
				   <td>
				   <a class="btn btn-xs green" href="AdDividendMaster?action=edit&id=<%=bean.getAd_divident_master_id()%>">
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
<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script type="text/javascript">
jQuery(function() {       
	
	$('#div_year').select2();
	jQuery('#dataTable1').DataTable();
	
	jQuery.validator.addMethod("Alphanumdash", function(value, element) {
		  return this.optional(element) || /^[0-9\-]*$/.test(value);
		}, "Please enter valid dividend year (Ex:- 2015-16)");
	
	$('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true});
	
	jQuery( "#frmDividendMaster" ).validate({
		  rules: {
			  dividend_year: {
			      required: true,
			      Alphanumdash:true,
			      minlength:7,
			      maxlength:7
		    },
		    convence_amt : {
		      required: true,
		      number:true
		    },
		    dividend_per:{
		      required: true,
		      number:true
		    }
		  }
		});
	
});//end dom
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>