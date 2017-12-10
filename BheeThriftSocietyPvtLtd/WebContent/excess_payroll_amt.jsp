<%@page import="Model.Dao.PayrollTempDao"%>
<%@page import="Model.Bean.PayrollTempBean"%>
<%@page import="Model.Bean.CountryBean"%>
<%@page import="Model.Dao.CountryDao"%>
<%@page import="Model.Bean.CountryBean"%>
<%@page import="Model.Dao.CountryDao"%>
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
			<li><a href="#">Excess Payroll</a><i class="fa fa-angle-right"></i>Add and view</li>
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

	
	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		View Excess Payroll
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<div class="table-responsive table-scrollable" >

					<table id="dataTable1" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								 
								
								<th>Sno</th>
								<th>Memno</th>
								<th>Thrift </th>
								<th>DCF </th>
								<th>MainLoan </th>
								<th>Emergency </th>
								<th>Excess </th>
								<th>Total </th>
								<th>Narration</th>
								<!-- <th>Action </th> -->
							</tr>
							</thead>
						<tbody>
						<%
try{
	int i=0;
  ArrayList<PayrollTempBean> payroll=new PayrollTempDao().getAllExcessPayrollTemp();
  double thrift_total=0.0,loan_total=0.0,excess_total=0.0,dcf_total=0.0,festival_total=0.0,grand_total=0.0;
if(payroll.isEmpty()!=true){
	
	
	for(PayrollTempBean temp:payroll){
	
	         
	
                                   
								 	
								 	i++;
								 	double thrift=temp.getThrift_amt();
								 	double loan = temp.getMain_loan_amt();
								 	double festival=temp.getFestival_loan_amt();
								 	double dcf=temp.getDcf_amt();
								 	
								 	double excess=temp.getExcess_amt();
								 	thrift_total+=thrift;
								 	loan_total+=loan;
								 	dcf_total+=dcf;
								 	festival_total+=festival;
								 	
								 	excess_total+=excess;
								 	%>
								 	
								 	<tr>
								 	<td><%=i %></td>
								 	<td><%=temp.getAd_society_no() %></td>
								 	<td><%=thrift %></td>
								 	<td><%=dcf %></td>								 	
								 	<td><%=loan%></td>
									<td><%=festival%></td>
									<td><%=excess%></td>
								 	<td><%=thrift+dcf+loan+festival+excess %></td>
								 	<td><%=temp.getNarration()%></td>
								 	<%-- <td><a href="AdBulkDeposit?action=deletePayroll&ad_payroll_temp_id=<%=temp.getAd_payroll_temp_id()%>" class="btn btn-xs  red">Delete</a></td> --%>
								 	</tr>
								 	
								 <%
								 grand_total+=thrift+dcf+loan+festival+excess;
                                   } 
									
									}
								}catch(Exception e){
									e.printStackTrace();
								}

								 %>


								 </tbody>
								
</table>
</div>
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
<script>
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	
	jQuery.validator.addMethod("alphanumspace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter character and space only.");
	
	
});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>