<%@page import="Model.Bean.LoanDetailViewBean"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="java.util.Calendar"%>
<%@page import="Model.Bean.SalutationBean"%>
<%@page import="Model.Bean.GenderBean"%>
<%@page import="Model.Dao.SalutationDao"%>
<%@page import="Model.Dao.GenderDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%
try{
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 		String from = null;
 		String to = null;
 		try {
 			Date date = (Date)session.getAttribute("openday");
 			
 			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
 			
 			Calendar cal = Calendar.getInstance();
 		    cal.setTime(date);
 		    int year = cal.get(Calendar.YEAR);
 		    
 			Date dt=sdf.parse("03/31/"+year);
 			
 			
 			if(date.after(dt)){
 			
 				Date fdate = sdf.parse("01/04/"+year);
 				from=sdf.format(fdate).toString();
 				
 				
 			}else{
 				Date tdate = sdf.parse("01/04/"+(year-1));
 				from=sdf.format(tdate).toString();
 				
 			}
 			
 			to= sdf.format(date).toString();
 			
 			
 			
 		} catch (IllegalArgumentException i) {
 			i.printStackTrace();
 		}
 	%>
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
									<li><i class="fa fa-home"></i> <a href="#">Laon</a><i
										class="fa fa-angle-right"></i>
									</li>
									<li><a href="#">Loan Detail</a><i
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
								<div class="portlet box purple">
									<div class="portlet-title">
										<div class="caption">Loan Detail</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"> </a>
										</div>
									</div>
									<!-- end portlet-title -->
									<div class="portlet-body">
										<form id="frmLoanDetail" action="#" method="post">
											<table class="table table-bordered">
												<tr>
													<td width="15%">From Date: : <span class="red">*</span>
													</td>
													<td width="35%"><input
														class="form-control input-medium date-picker" type="text"
														placeholder="dd/mm/yyyy" name="from_date" id="from_date"
														value="<%=from%>" /> <label id="form_type_error"
														class="error "></label>
													</td>
													<td width="15%">To Date : <span class="red">*</span></td>
													<td width="35%"><input
														class="form-control input-medium date-picker" type="text"
														placeholder="dd/mm/yyyy" name="to_date" id="to_date"
														value="<%=to%>" /> <label class="error"></label>
													</td>
												</tr>

												<tr>
													<td></td>
													<td colspan="3"><input type="button"
														class="btn btn blue" id="btnShowView" name="Submit"
														value="submit" /> <input class="btn btn green"
														type="reset" name="Clear" />
													</td>
												</tr>
											</table>
										</form>
									</div>
									<!-- End portlet-body -->
								</div>
								<!-- En portlet box -->
								<!------------------------------------------------------------------- -->

								<!-- BEGIN BORDERED TABLE PORTLET-->
								<div class="portlet box purple">
									<div class="portlet-title">
										<div class="caption">View Loan Detail</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"> </a>
										</div>
									</div>

									<div class="portlet-body">
										<div id="view_share">
											<table id="dataTable1"
												class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>Sno.</th>
														<th>Staff No.</th>
														<th width="15%">Name</th>
														<th>Type</th>
														<th>Category</th>
														<th width="10%">Loan Amt</th>
														<th>ROI</th>
														<th>Start</th>
														<th>End</th>
														<th width="10%">Emi</th>
														<th>Status</th>
														<th>Op</th>

													</tr>
												</thead>
												<tbody>
													<%
									    ArrayList<LoanDetailViewBean> loanlist=new LoanTrxDao().getAllLoanDetail();
													int i=0;
									     if(loanlist.isEmpty()!=true){
									    	 for(LoanDetailViewBean bean:loanlist){
									    		 %>

													<tr>

														<td><%=++i %></td>
														<td><%=bean.getAd_society_no() %></td>
														<td><%=bean.getMember_name() %></td>
														<td><%=bean.getLoan_type() %></td>
														<td><%=bean.getLoan_category_name() %></td>
														<td><%=bean.getLoan_amt() %></td>
														<td><%=bean.getIntrest_rate() %></td>
														<td><% if(bean.getIssue_date()!=null){out.print(new SimpleDateFormat("dd/MM/yyyy").format(bean.getIssue_date().getTime()));}else{out.print("-");}
																						%></td>
														
														<td><%if(bean.getIssue_date()!=null){out.print(new SimpleDateFormat("dd/MM/yyyy").format(bean.getEnd_date().getTime()));}else{out.print("-");} %></td>
														<td><%=bean.getEmi() %></td>
														<td><%=bean.getStatus() %></td>
														<td><a href="loan_detail_view_2.jsp?loan_trx_id=<%=bean.getLoan_trx_id()%>" class="btn btn-xs green"><i class="fa fa-search "></i>View</a></td>
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