<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Dao.ExcessTrxDao"%>
<%@page import="Model.Bean.ExcessTrxBean"%>
<%@page import="Model.Dao.LoanTrxDetailDao"%>
<%@page import="Model.Bean.LoanTrxDetailBean"%>
<%@page import="Model.Bean.LoanDetailViewBean"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="java.util.Calendar"%>
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
									<li><i class="fa fa-home"></i> <a href="#">Excess</a><i
										class="fa fa-angle-right"></i>
									</li>
									<li><a href="#">Excess Amount Detail</a><i
										class="fa fa-angle-right"></i>View</li>
								</ul>
								<!-- END PAGE TITLE & BREADCRUMB-->
							</div>
						</div>
						<!-- END PAGE HEADER-->
						<!-- BEGIN PAGE CONTENT-->
						<div class="row">
							<div class="col-md-12">
								<!-- Containt Start -->
								<!-- BEGIN BORDERED TABLE PORTLET-->
							<%

try{
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 		
 		String to=sdf.format((Date)session.getAttribute("openday"));
 		
 		
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
										<div class="caption">View Excess Detail</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"> </a>
										</div>
									</div>

									<div class="portlet-body">
									<div class="table-toolbar">
									<div class="btn-group">
										<a href="AdExcess?action=add" class="btn btn-md green">Add New <i class="fa fa-plus"></i></a> 
										
									</div>
									</div>
										<div id="view_share">
											<table id="dataTable1"
												class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>Sno.</th>
														<th>Date</th>
														<th>Mem No.</th>
														<th width="15%">Name</th>
														<th>Deposit</th>
														<th>Withdrawal</th>
														<th>Balance</th>
														<th>Status</th>
														<th>Detail</th>
														<th>Voucher ID</th>
														<th>IsActive</th>
														<th>Opt</th>

													</tr>
												</thead>
												<tbody>
													<%
										sdf=new SimpleDateFormat("dd/MM/yyyy");			
									    ArrayList<ExcessTrxBean> excesslist=new ExcessTrxDao().getAllExcess();
													int i=0;
									     if(excesslist.isEmpty()!=true){
									    	 for(ExcessTrxBean bean:excesslist){
									    		 MemberRegistrationBean member=new MemberRegistrationDao().getMemberName(bean.getAd_member_id());
									    		 %>

													<tr>

														<td><%=++i %></td>
														<td><%=sdf.format(bean.getTrx_date()) %></td>
														<td><%=member.getAd_society_no() %></td>
														<td><%=member.getName() %></td>
														<td><%=bean.getExcess_amt() %></td>
														<td><%=bean.getWithdrawal() %></td>
														<td><%=bean.getBalance() %></td>
														<td><%=bean.getStatus() %></td>
														<td><%=bean.getDetail() %></td>
														<td><%=bean.getAd_voucher_id() %></td>
														<td><%=bean.isIsactive() %></td>
														<td>
														<table>
	<tr>
		<td>
			<div class="btn-group pull-right">
			<button class="btn red btn-md dropdown-toggle" type="button" data-toggle="dropdown">
				<i class="fa fa-bar-chart-o"></i>OPT <i class="fa fa-angle-down"></i>
			</button>
			<ul class="dropdown-menu" role="menu">
			<li><a href="AdExcess?action=edit&ad_excess_trx_id=<%=bean.getAd_excess_trx_id()%>" class="btn btn-xs green">
														<i class="fa fa-search "></i>Edit</a></li>
			<li><a href="AdExcess?action=delete&ad_excess_trx_id=<%=bean.getAd_excess_trx_id()%>" class="btn btn-xs red">
														<i class="fa fa-table "></i>Delete</a></li>
			</ul>
			</div>
		</td>
	</tr>
	</table>
														
														
														
														</td>
													</tr>
													<%
									}
									     }
									%>


												</tbody>
											</table>

										</div>
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
		src="assets/plugins/select2/select2.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script src="assets/scripts/custom/components-pickers.js"></script>
	<script>
jQuery(function() {  
	
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate : new Date('<%=to%>'),
				autoclose : true});

			jQuery.validator.addMethod("validDate", function(value, element) {
				return this.optional(element)
						|| /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
			}, "Please enter valid Date.");

			jQuery("#frmLoanDetail").validate({
				rules : {
					from_date : {
						required : true,
						validDate : true
					},
					to_date : {
						required : true,
						validDate : true
					}
				}
			});
			
			
		});
	</script>


	<script type="text/javascript">
jQuery(function() {       
	
$('#btnShowView').click(function(){
		
	var from          = $('#from_date').val();
	var to            = $('#to_date').val();
		
		if(jQuery( "#frmLoanDetail" ).valid()==true){
		
			var dataString = {'from':from,'to':to};
		
		 $.ajax({
			type:"POST",
			url:"Ajax/getLoanDetailByDate.jsp",
			data:dataString,
			success: function(data){
				//alert(data);
				$("#view_share").html(data);
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log(textStatus);
			}
			
		 });
		}else{
			return false;
		}
	});//end showLadger event
	
});
</script>
	<script type="text/javascript"
		src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	});
	

</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>