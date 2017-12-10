<%@page import="Model.Bean.TypeOfLoanBean"%>
<%@page import="Model.Dao.TypeOfLoanDao"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberStatusBean"%>
<%@page import="Model.Dao.MemberStatusDao"%>
<%@page import="Model.Bean.TypeOfFdBean"%>
<%@page import="Model.Dao.TypeOfFdDao"%>
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
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2-bootstrap.css"/>
</head>
<!-- END HEAD -->
<%
try{%>
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
				<a href="#">Report</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Reports</a><i class="fa fa-angle-right"></i>View</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
<div class="col-md-12">
<!-- Containt Start -->
<!------------------------------------------------------------------- -->
<!-- BEGIN BORDERED TABLE PORTLET-->
<div class="portlet box purple">
<div class="portlet-title">
	<div class="caption">View Reports</div>
	 <div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
</div>
<div class="portlet-body">
<div class="scroller" style="height:350px" data-always-visible="1" data-rail-visible="0">

<ul class="nav nav-pills steps">
	<li class="active"><a href="#tab1" data-toggle="tab" class="step">Member</a></li>
	<li class=""><a href="#tab2" data-toggle="tab" class="step">Loan</a></li>
	<li class=""><a href="#tab3" data-toggle="tab" class="step">Share</a></li>
	<li class=""><a href="#tab4" data-toggle="tab" class="step">FD</a></li>
	<li class=""><a href="#tab5" data-toggle="tab" class="step">Accounts</a></li>
	<li class=""><a href="#tab6" data-toggle="tab" class="step">Dividend</a></li>
	<li class=""><a href="#tab7" data-toggle="tab" class="step">Other</a></li>
</ul>
<div class="tab-content">
	<div class="tab-pane fade active in" id="tab1">
	<button id="showAllMemberList" type="button" class="btn green">Membership Certificate</button>	
	</div><!-- end tab 1 -->
<!-- ------------------------------------------------------End Report 1----------------------------- -->
<div class="tab-pane fade" id="tab2">
<button id="showAllLoanBetweenDates" type="button" class="btn green">Loan Report</button>
	<button id="showLoanDayBook" type="button" class="btn blue">Loan DayBook</button>
	<button id="showInterestCertificate" type="button" class="btn blue">Loan Interest Certificate</button>
</div><!-- end tab 2 -->

<div class="tab-pane fade" id="tab3">
<button id="showMember" type="button" class="btn blue">Share DayBook</button>
<button id="showMemberShareByDate" type="button" class="btn green">Share Detail By Date</button>
<button id="showMemberShareByStatus" type="button" class="btn red">Share Detail By Status</button>
<button id="showMemberShare" type="button" class="btn purple">Share Detail By Member</button>
<button id="showMemberShareSummary" type="button" class="btn yellow">Share Summary Report</button>	
</div><!-- end tab 3 -->

<div class="tab-pane fade" id="tab4">
	<button id="showAllFDByType" type="button" class="btn green">FD Report By Type</button>
	<button id="showAllOpenFD" type="button" class="btn blue">FD Opening Report</button>
	<button id="showAllMaturedFD" type="button" class="btn green">FD Maturity Report</button>
	<button id="showAllPaidFD" type="button" class="btn blue">FD Payment Report</button>
</div><!-- end tab 4 -->

<div class="tab-pane fade" id="tab5">
<button id="showAllLoanBetweenDates" type="button" class="btn green">Loan Report</button>
	<button id="showLoanDayBook" type="button" class="btn blue">LoanDayBook</button>
</div><!-- end tab 5 -->

<div class="tab-pane fade" id="tab6">
	<button id="showDividendReport" type="button" class="btn blue">Dividend Report</button>
	<button id="showDividendBankReport" type="button" class="btn purple">Dividend Bank Report</button>
	
</div><!-- end tab 6 -->

<div class="tab-pane fade" id="tab7">
<button id="showRetirementReport" type="button" class="btn green">Retirement Report</button>
<button id="showDemandListReport" type="button" class="btn blue">Demand List Report</button>	
</div><!-- end tab 7 -->
<!-- ------------------------------------------------------End Report 2----------------------------- -->
</div><!-- End scroller -->
</div><!-- End tab contain -->		
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

<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
<div class="modal fade member-veiw" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmMemberview"  autocomplete="off" action="AdReport?action=share_day_book" target="_blank" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Date : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="fdate" id="fdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>	
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<div class="modal fade member-veiw1" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmMemberLoanDaybookview"  autocomplete="off" action="AdReport?action=loan_day_book" target="_blank" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Date : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="fdate" id="fdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>	
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->


<div class="modal fade member-veiw2" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmMemberLoanDetailview"  autocomplete="off" action="AdReport?action=loan_detail" target="_blank" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">From : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="fdate" id="fdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>	
				    <td width="15%">To : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="tdate" id="fdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>
				    </tr>
				    <tr>
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->

<div class="modal fade member-veiw3" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmRetirementview"  autocomplete="off" action="AdReport?action=retirement_list" target="_blank" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Date : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="fdate" id="fdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>	
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->

<div class="modal fade member-veiw4" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmDividendview"  autocomplete="off" action="AdReport?action=dividend_list" target="_blank" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Date : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="fdate" id="fdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>	
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->

<div class="modal fade member-veiw5" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmDividendBankview"  autocomplete="off" action="AdReport?action=dividend_bank_report" target="_blank" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Date : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="fdate" id="fdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>	
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->




<div class="modal fade member-veiw6" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmFDReportByType"  autocomplete="off" action="AdReport?action=fd_report_by_type" target="_blank" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">From : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="fdate" id="fdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>
				    <td width="15%">To : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="tdate" id="tdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>
				    
				 </tr>
				 <tr>
				 <td>FD Type</td>
				 <td>
				     <select name="ad_type_of_fd_id" id="ad_type_of_fd_id" class="form-control input-medium">
				     <option value="">--Select--</option>
				    
				     <%
				         TypeOfFdDao fdtypedao=new TypeOfFdDao();
				     	 ArrayList<TypeOfFdBean> fdtypelist=fdtypedao.getAlltypeoffd();
				     	 if(fdtypelist.isEmpty()!=true){
				     		 for(TypeOfFdBean bean:fdtypelist){%>
				     			  <option value="<%=bean.getAd_type_of_fd_id()%>"><%=bean.getName() %></option>
				     		 <%}
				     	 }
				     
				     
				     %>  
				      </select>
				    </td> 	
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->



<div class="modal fade member-veiw7" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmFDOpeningReport"  autocomplete="off" action="AdReport?action=fd_opening_report" target="_blank" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">From : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="fdate" id="fdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>
				    <td width="15%">To : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="tdate" id="tdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>
				    
				 </tr>
				 <tr>
				 
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->

<div class="modal fade member-veiw8" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmFDMaturityReport"  autocomplete="off" action="AdReport?action=fd_maturity_report" target="_blank" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">From : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="fdate" id="fdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>
				    <td width="15%">To : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="tdate" id="tdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>
				    
				 </tr>
				 <tr>
				 
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->

<div class="modal fade member-veiw9" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmFDPaymentReport"  autocomplete="off" action="AdReport?action=fd_payment_report" target="_blank" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">From : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="fdate" id="fdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>
				    <td width="15%">To : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="tdate" id="tdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>
				    
				 </tr>
				 <tr>
				 
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->


<div class="modal fade member-veiw10" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmShareReportByDate"  autocomplete="off" action="AdReport?action=share_report_by_date" target="_blank" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">From : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="fdate" id="fdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>
				    <td width="15%">To : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="tdate" id="tdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>
				    
				 </tr>
				 <tr>
				
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->

<div class="modal fade member-veiw11" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmShareReportByStatus"  autocomplete="off" action="AdReport?action=share_report_by_status" target="_blank" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">From : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="fdate" id="fdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>
				    <td width="15%">To : <span class="red">*</span></td>
				    <td width="35%">
				    <input class="form-control input-medium date-picker" type="text" name="tdate" id="tdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>
				    
				 </tr>
				 <tr>
				  <td>Status</td>
				 
				 <td>
				     <select name="ad_member_status_id" id="ad_member_status_id" class="form-control input-medium">
				     <option value="">--Select--</option>
				    
				      <%
				         MemberStatusDao statusdao=new MemberStatusDao();
				     	 ArrayList<MemberStatusBean> statuslist=statusdao.getAllMemberStatus();
				     	 if(statuslist.isEmpty()!=true){
				     		 for(MemberStatusBean bean:statuslist){%>
				     			  <option value="<%=bean.getAd_member_status_id()%>"><%=bean.getMember_status() %></option>
				     		 <%}
				     	 }
				     
				     
				     %>  
				      </select> <label class="error"></label>
				    </td> 	
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->
<div class="modal fade member-veiw12" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmShareReportByMember"  autocomplete="off" action="AdReport?action=share_report_by_member" target="_blank" method="post">
			<table class="table table-bordered">
				
				 <tr>
				  <td>Name</td>
				 <td>
				     <select name="ad_member_id" id="ad_member_id" class="form-control input-large">
				     <option value="">--Select--</option>
				    
				      <%
				         MemberRegistrationDao memberDao=new MemberRegistrationDao();
				     	 ArrayList<MemberRegistrationBean> memberlist=memberDao.getAllMemberlist();
				     	 if(memberlist.isEmpty()!=true){
				     		 for(MemberRegistrationBean bean:memberlist){%>
				     			  <option value="<%=bean.getAd_member_id()%>"><%=bean.getAd_society_no()+" "+bean.getName() %></option>
				     		 <%}
				     	 }
				     
				     
				     %>   
				      </select> <label class="error"></label>
				    </td> 	
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->
<div class="modal fade member-veiw13" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmShareReportsummary"  autocomplete="off" action="AdReport?action=share_summary_report" target="_blank" method="post">
			<table class="table table-bordered">
				
				 <tr>
				
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->

<div class="modal fade member-veiw14" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmDemandList"  autocomplete="off" action="AdReport?action=demandList" target="_blank" method="post">
			<table class="table table-bordered">
				
				 <tr>
				  <td>Branch</td>
				 <td>
				     <select name="ad_branch_id" id="ad_branch_id" class="form-control input-large">
				     <option value="">--Select--</option>
				    
				      <%
				         BankBranchDao branchDao=new BankBranchDao();
				     	 ArrayList<BankBranchBean> branchlist=branchDao.getAllBankBranchName();
				     	 if(branchlist.isEmpty()!=true){
				     		 for(BankBranchBean bean:branchlist){%>
				     			  <option value="<%=bean.getAd_branch_id()%>"><%=bean.getBranch_code()+" | "+bean.getBranch() %></option>
				     		 <%}
				     	 }
				     
				     
				     %>   
				      </select> <label class="error"></label>
				    </td> 	
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->

<div class="modal fade membershipcard-view" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmMembershipcard"  autocomplete="off" action="AdReport?action=membership_card_report" target="_blank" method="post">
			<table class="table table-bordered">
				
				 <tr>
				  <td>Name</td>
				 <td>
				     <select name="ad_member_id" id="ad_member_id_card" class="form-control input-large">
				     <option value="">--Select--</option>
				    
				      <%
				         memberDao=new MemberRegistrationDao();
				     	 memberlist=memberDao.getAllApprovedMemberlist();
				     	 if(memberlist.isEmpty()!=true){
				     		 for(MemberRegistrationBean bean:memberlist){%>
				     			  <option value="<%=bean.getAd_member_id()%>"><%=bean.getAd_society_no()+" "+bean.getName() %></option>
				     		 <%}
				     	 }
				     
				     
				     %>   
				      </select> <label class="error"></label>
				    </td> 	
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->

<div class="modal fade interestcertificate-view" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-blue">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><i class="fa fa-cogs"></i> Report</h4>
			</div>
			<div class="modal-body">
				
			<form id="frmMembershipcard"  autocomplete="off" action="AdReport?action=interestcertificate" target="_blank" method="post">
			<table class="table table-bordered">
				
				 <tr>
				  <td>Name</td>
				 <td>
				     <select name="ad_member_id" id="ad_member_id_certificate" class="form-control input-large">
				     <option value="">--Select--</option>
				    
				      <%
				         memberDao=new MemberRegistrationDao();
				     	 memberlist=memberDao.getAllMemberHavingLoanAccount();
				     	 if(memberlist.isEmpty()!=true){
				     		 for(MemberRegistrationBean bean:memberlist){%>
				     			  <option value="<%=bean.getAd_member_id()%>"><%=bean.getAd_society_no()+" "+bean.getName() %></option>
				     		 <%}
				     	 }
				     
				     
				     %>   
				      </select> <label class="error"></label>
				    </td> 	
				     <td>Loan Type</td>
					 <td>
				     <select name="type_of_loan_id" id="type_of_loan_id" class="form-control input-medium">
				     <option value="">--Select--</option>
				    
				      <%
				         TypeOfLoanDao tdao=new TypeOfLoanDao();
				     	 ArrayList<TypeOfLoanBean> typelist=tdao.getAlltypeofloan();
				     	 if(typelist.isEmpty()!=true){
				     		 for(TypeOfLoanBean bean:typelist){%>
				     			  <option value="<%=bean.getAd_type_of_loan_id()%>"><%=bean.getName() %></option>
				     		 <%}
				     	 }
				     
				     
				     %>   
				      </select> <label class="error"></label>
				    </td> 	
				</tr>
				<tr>
					<td>From : <span class="red">*</span></td>
				    <td>
				    <input class="form-control input-medium date-picker" type="text" name="fdate" id="fdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>
				    <td>To : <span class="red">*</span></td>
				    <td>
				    <input class="form-control input-medium date-picker" type="text" name="tdate" id="tdate" placeholder="dd/MM/yyyy" />
				    <label class="error"></label>
				    </td>
				    
				 </tr>
				<tr>
					<td>Report Type</td>
					<td>
						<label><input type="radio" name="reportype" checked="checked" value="pdf" />PDF</label>
						<label><input type="radio" name="reportype" value="xls" />XLS</label>
					</td>
					<td><button type="submit" name="Submit" class="btn blue"><i class="fa fa-bar-chart-o"></i> Generate Report</button></td>
				</tr>
			</table>
			
		</form>
				
			</div><!-- end modal-body -->
			<div class="modal-footer">
							<button type="button" class="btn blue" data-dismiss="modal">Close</button>
			</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->
<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

<%@ include file= "Layout/footer.jsp" %>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<script>
	$(function(){
		
		$('#ad_type_of_fd_id').select2();
		$('#ad_member_id').select2();
		$('#ad_member_id_card').select2();
		$('#ad_branch_id').select2();
		$('#ad_member_status_id').select2();
		$('#ad_member_id_certificate').select2();
		$('#type_of_loan_id').select2();
		$('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true});
		jQuery.validator.addMethod("Alpha", function(value, element) {
			  return this.optional(element) || /^[a-zA-Z]*$/.test(value);
			}, "Please enter latter and space only.");
		
		jQuery( "#frmMemberview" ).validate({
			  rules: {
				  rpt_name: {
			      required: true,
			      Alpha:true
			    },
			    reportype : {
			      required: true
			    }
			  }
			});
		
		jQuery( "#frmMembershipcard" ).validate({
			  rules: {
				  ad_member_id: {
			      required: true
			    },
			    reportype : {
			      required: true
			    }
			  }
			});
		jQuery( "#frmMemberLoanDaybookview" ).validate({
			  rules: {
				  rpt_name: {
			      required: true,
			      Alpha:true
			    },
			    reportype : {
			      required: true
			    }
			  }
			});
		
		jQuery( "#frmMemberLoanDetailview" ).validate({
			  rules: {
				  rpt_name: {
			      required: true,
			      Alpha:true
			    },
			    reportype : {
			      required: true
			    }
			  }
			});
		jQuery( "#frmRetirementview" ).validate({
			  rules: {
				  rpt_name: {
			      required: true,
			      Alpha:true
			    },
			    reportype : {
			      required: true
			    }
			  }
			});
		jQuery( "#frmDividendview" ).validate({
			  rules: {
				  rpt_name: {
			      required: true,
			      Alpha:true
			    },
			    reportype : {
			      required: true
			    }
			  }
			});
		
		jQuery( "#frmDividendBankview" ).validate({
			  rules: {
				  rpt_name: {
			      required: true,
			      Alpha:true
			    },
			    reportype : {
			      required: true
			    }
			  }
			});
		
		jQuery( "#frmFDReportByType" ).validate({
			  rules: {
				  fdate: {
			      required: true
			     
			    },tdate: {
			      required: true
			     
			    },
			    ad_type_of_fd_id : {
			      required: true
			    }
			  }
			});
		jQuery( "#frmFDOpeningReport" ).validate({
				  rules: {
					  fdate: {
				      required: true
				     
				    },tdate: {
				      required: true
				     
				    }
				  }
				});
		
		jQuery( "#frmFDMaturityReport" ).validate({
			  rules: {
				  fdate: {
			      required: true
			     
			    },tdate: {
			      required: true
			     
			    }
			  }
			});
		
		jQuery( "#frmFDPaymentReport" ).validate({
			  rules: {
				  fdate: {
			      required: true
			     
			    },tdate: {
			      required: true
			     
			    }
			  }
			});
		
		jQuery( "#frmShareReportByDate" ).validate({
			  rules: {
				  fdate: {
			      required: true
			     
			    },tdate: {
			      required: true
			     
			    }
			  }
			});
		
		jQuery( "#frmShareReportByStatus" ).validate({
			  rules: {
				  fdate: {
			      required: true
			     
			    },tdate: {
			      required: true
			     
			    },ad_member_status_id:{
			    	required:true
			    }
			  }
			});
		jQuery( "#frmShareReportByMember" ).validate({
			  rules: {
				  ad_member_id:{
			    	required:true
			    }
			  }
			});
		
		jQuery( "#frmDemandList" ).validate({
			  rules: {
				  ad_branch_id:{
			    	required:true
			    }
			  }
			});
		
		
		$('#showAllMemberList').click(function(e){
			
			$('.membershipcard-view').modal('show');
		});
		
        $('#showInterestCertificate').click(function(e){
			
			$('.interestcertificate-view').modal('show');
		});
		$('#showMember').click(function(){
			$('.member-veiw').modal('show');
			
		});
		
		$('#showLoanDayBook').click(function(){
			$('.member-veiw1').modal('show');
			
		});
		
		$('#showAllLoanBetweenDates').click(function(){
			$('.member-veiw2').modal('show');
			
		});
		
		$('#showRetirementReport').click(function(){
			$('.member-veiw3').modal('show');
			
		});
		
		$('#showDividendReport').click(function(){
			$('.member-veiw4').modal('show');
			
		});
		
		$('#showDividendBankReport').click(function(){
			$('.member-veiw5').modal('show');
			
		});
		$('#showAllFDByType').click(function(){
			$('.member-veiw6').modal('show');
			
		});
		$('#showAllOpenFD').click(function(){
			$('.member-veiw7').modal('show');
			
		});
		$('#showAllMaturedFD').click(function(){
			$('.member-veiw8').modal('show');
			
		});
		$('#showAllPaidFD').click(function(){
			$('.member-veiw9').modal('show');
			
		});
		 $('#showMemberShareByDate').click(function(){
			$('.member-veiw10').modal('show');
			
		}); 
          
		 $('#showMemberShareByStatus').click(function(){
				$('.member-veiw11').modal('show');
				
			});
		 $('#showMemberShare').click(function(){
				$('.member-veiw12').modal('show');
				
			});
		 $('#showMemberShareSummary').click(function(){
				$('.member-veiw13').modal('show');
				
			});
		 
		 $('#showDemandListReport').click(function(){
				$('.member-veiw14').modal('show');
				
			});
		 
	});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>