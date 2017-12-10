<%@page import="java.util.Locale"%>
<%@page import="Model.Dao.ThriftRoiDao"%>
<%@page import="Model.Bean.ThriftRoiBean"%>
<%@page import="Model.Bean.ThriftViewBean"%>
<%@page import="Model.Dao.ThriftTrxDao"%>
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
									<li><i class="fa fa-home"></i> <a href="#">Thrift</a><i
										class="fa fa-angle-right"></i>
									</li>
									<li><a href="#">Thrift Amount Detail</a><i
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
										<div class="caption">View Thrift Detail</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"> </a>
										</div>
									</div>

									<div class="portlet-body">
									<div class="table-toolbar">
									<div class="btn-group">
										<a href="AdThriftTrx?action=add" class="btn btn-md green">Add New <i class="fa fa-plus"></i></a> 
										
									</div>
									</div>
										<div id="view_share">
											<%
String id=request.getParameter("ad_member_id");
double total=0.0,thrift_total=0.0,int_total=0.0;

ArrayList<ThriftViewBean> list=new ThriftTrxDao().getAllThriftViewDetail();
%>
<table id="dataTable1"
												class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>Sno</th>
														<th>Staff no</th>
														<th>Name</th>
														<th>Date</th>
														<th>Month</th>
														<th>Deposit</th>
														<th>Interest</th>
														<th>Balance</th>
														
													</tr>
												</thead>
												<tbody>
												<%
												ThriftRoiBean thrift=new ThriftRoiDao().getThriftRoiMaxId();
													
										   	    double thrift_int=thrift.getRoi();
										   	    thrift_int=thrift_int/100.0;
										   	 	double interest=0.0;
												
												int i=0;
												
												
												if(list.isEmpty()!=true){
													for(ThriftViewBean bean:list){
													thrift_total+=bean.getThrift_amt();
													int_total+=bean.getThrift_int();
														  
												
												%>
												<tr>
												<td><%=++i %>
												<td><%=bean.getSociety_no() %></td>
												<td><%=bean.getName() %></td>
												<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getTrx_date()) %></td>
												<%
													Calendar cal = Calendar.getInstance();
										 		    cal.setTime(bean.getTrx_date());
													%>
												<td><%=cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) %></td>
												<td><%=bean.getThrift_amt() %></td>
												<td><%=bean.getThrift_int() %></td>
												<td><%=bean.getBalance() %></td>
												
													</tr>
												
											<%
}	
												}
											
											%>
											
											</tbody>
											<tfoot>
													<tr style="font-size: 14px;font-weight: bold;">
														<td colspan="5" align="right">Total</td>
														<td><%=thrift_total %></td><td><%=int_total %></td><td></td>
													</tr>
												</tfoot>
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