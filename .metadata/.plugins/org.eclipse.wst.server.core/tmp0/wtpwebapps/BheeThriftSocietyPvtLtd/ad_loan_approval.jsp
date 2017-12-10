<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Dao.LoanCategoryDao"%>
<%@page import="Model.Bean.LoanCategoryBean"%>
<%@page import="Model.Dao.TypeOfLoanDao"%>
<%@page import="Model.Bean.TypeOfLoanBean"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Bean.LoanTrxBean"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="Model.Bean.SalutationBean"%>
<%@page import="Model.Bean.GenderBean"%>
<%@page import="Model.Dao.SalutationDao"%>
<%@page import="Model.Dao.GenderDao"%>
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
<%@ include file="Layout/header.jsp"%>
<link rel="stylesheet"
	href="assets/plugins/data-tables/DT_bootstrap.css" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-full-width">
	<%@ include file="Layout/headtitle.jsp"%>
	<!-- BEGIN HEADER -->
	<div class="header navbar mega-menu">
		<!-- BEGIN TOP NAVIGATION BAR -->
		<%@ include file="Layout/navbar.jsp"%>
		<!-- END TOP NAVIGATION BAR -->
	</div>
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN EMPTY PAGE SIDEBAR -->
		<%@ include file="Layout/emptynavbar.jsp"%>
		<!-- END EMPTY PAGE SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN PAGE CONTENT-->
				<div class="row">
					<div class="col-md-2 sidebar-content ">
						<%@ include file="Layout/sidebar.jsp"%>
					</div>
					<div class="col-md-10">
						<!-- BEGIN PAGE HEADER-->
						<div class="row">
							<div class="col-md-12">
								<ul class="page-breadcrumb breadcrumb">
									<li><i class="fa fa-home"></i> <a href="#">Setup</a><i
										class="fa fa-angle-right"></i></li>
									<li><a href="#">Loan</a><i class="fa fa-angle-right"></i>Approvals</li>
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
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true"></button>
									<%=AppMessage[1] %>
								</div>
								<%
}
%>
								<!------------------------------------------------------------------- -->
								<!------------------------------------------------------------------- -->
								<div class="hidden">
									<%
String  V_Trx_No= "";
V_Trx_No= (String)request.getParameter("no");
if(V_Trx_No==null){
	V_Trx_No = "";
}
%>
									<input type="hidden" name="V_Trx_No" id="V_Trx_No"
										value="<%=V_Trx_No%>" />
									<!-- for display message after submit -->
								</div>
								<!------------------------------------------------------------------- -->


								<!-- BEGIN BORDERED TABLE PORTLET-->
								<div class="portlet box purple">
									<div class="portlet-title">
										<div class="caption">Loan Approvals</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"> </a>
										</div>
									</div>
									<div class="portlet-body">
										<table id="dataTable1"
											class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>Sno.</th>
													<th>Mem.No.</th>
													<th>Name</th>
													<th>Type</th>
													<th>Category</th>
													<th>AMT</th>
													<th>ROI</th>
													<th>Period(M)</th>
													<th>S.Date</th>
													<th>E.Date</th>
													<th>EMI</th>
													<th>OPT</th>
												</tr>
											</thead>
											<tbody>
												<%LoanTrxDao dao1=new LoanTrxDao();
					ArrayList<LoanTrxBean> slist=dao1.getAllLoanTrxPending();
					int i=0;
					if(slist.isEmpty()!=true){
						for(LoanTrxBean bean:slist){
							
							
							MemberRegistrationBean mbean= new MemberRegistrationDao().getMemberName(bean.getAd_member_id());
							TypeOfLoanBean ltbean =new TypeOfLoanDao().gettypeofloanById(bean.getLoan_type());
							LoanCategoryBean lcbean = new LoanCategoryDao().getLoanCategoryById(bean.getCreatedby());
				%>
												<tr>
													<td><%=++i %></td>
													<td><%=mbean.getAd_society_no()%></td>
													<td><%=mbean.getName()%></td>
													<td><%=ltbean.getName()%></td>
													<td><%=lcbean.getName()%></td>
													<td><%=bean.getLoan_amt()%></td>
													<td><%=bean.getIntrest_rate()%></td>
													<td><%=bean.getPeriod_month()%></td>
													<td><%= new SimpleDateFormat("dd/MM/yyyy").format(bean.getissue_date()) %></td>
													<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getEnd_date()) %></td>
													<td><%=bean.getEmi()%></td>
													<td><a class="btn btn-xs green"
														href="new_loan_disbursement.jsp?ad_member_id=<%=mbean.getAd_member_id() %>&id=<%=bean.getLoan_trx_id()%>">
															<i class="fa fa-edit"></i> VIEW</a> 
				  											<a class="btn btn-xs red"
														href="AdLoanTrx?action=deletePendingLoan&ad_member_id=<%=mbean.getAd_member_id() %>&loan_trx_id=<%=bean.getLoan_trx_id()%>">
															<i class="fa fa-times"></i> DELETE</a></td>
												</tr>
												<%	}
					}
				  %>
											</tbody>
										</table>
										<div class="clearfix"></div>
									</div>
									<!-- End portlet-body -->
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
	<%@ include file="Layout/footer.jsp"%>
	<script type="text/javascript"
		src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery.validate.min.js"></script>
	<script type="text/javascript">
		jQuery(function() {
			jQuery('#dataTable1').DataTable();

			jQuery.validator.addMethod("Alphanumspacedot", function(value,
					element) {
				return this.optional(element) || /^[a-zA-Z.\s]*$/.test(value);
			}, "Please enter latter and space only.");

			jQuery("#frmSalutation").validate({
				rules : {
					name : {
						required : true,
						Alphanumspacedot : true,
						maxlength : 8
					},
					getAd_gender_id : {
						required : true,
					}
				}
			});
		});
	</script>
	<script type="text/javascript">
$(function(){
	var V_Trx_No = $("#V_Trx_No").val();
	
	if(V_Trx_No.trim()!=""){
		$('.custom-messageBox').modal('show');
		$('#cont').hide();
		$('#custom-page-message').html("Transaction number is "+V_Trx_No);
	}
	
});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>