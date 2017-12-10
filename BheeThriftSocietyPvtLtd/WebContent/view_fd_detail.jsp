<%@page import="Model.Dao.FdTrxDao"%>
<%@page import="Model.Bean.FdViewBean"%>
<%@page import="java.util.Calendar"%>
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
									<li><i class="fa fa-home"></i> <a href="#">Fixed Deposit</a><i
										class="fa fa-angle-right"></i>
									</li>
									<li><a href="#">FD Detail</a><i
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
										<div class="caption">FD Detail</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"> </a>
										</div>
									</div>
									<!-- end portlet-title -->
									<div class="portlet-body">
										<form id="frmFdl" action="#" method="post">
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
										<div class="caption">View FD Detail</div>
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
														<th>StNo.</th>
														<th>Name</th>
														<th>No.</th>
														<th>Type</th>
														<th>Category</th>
														<th>Amt</th>
														<th>ROI</th>
														<th>Start</th>
														<th>End</th>
														<th>Int.</th>
														<th>M.Amt</th>
														<th>Status</th>
														<th>OP</th>


													</tr>
												</thead>
												<tbody>
													<%
									    ArrayList<FdViewBean> fdlist=new FdTrxDao().getAllFdDetail();
													int i=0;
									     if(fdlist.isEmpty()!=true){
									    	 for(FdViewBean bean:fdlist){
									    		 %>

													<tr>

														<td><%=++i %></td>
														<td><%=bean.getSociety_no() %></td>
														<td><%=bean.getMember_name() %></td>
														<td><%=bean.getFd_no() %></td>
														<td><%=bean.getFd_name() %></td>
														<td><%=bean.getFd_category() %></td>
														<td><%=bean.getFd_amt()%></td>
														<td><%=bean.getRoi() %></td>
														<td><% if(bean.getOpening_date()!=null){out.print(new SimpleDateFormat("dd/MM/yyyy").format(bean.getOpening_date().getTime()));}else{out.print("-");}
																						%></td>
														
														<td><%if(bean.getMaturity_date()!=null){out.print(new SimpleDateFormat("dd/MM/yyyy").format(bean.getMaturity_date().getTime()));}else{out.print("-");} %></td>
														<td><%=bean.getInterest_amt() %></td>
														<td><%=bean.getMaturityamt() %></td>
														<td><%=bean.getRemark() %></td>
														<td><a target="_blank" href="view_voucher.jsp?ad_voucher_id=<%=bean.getAd_voucher_id() %>" class="btn btn-xs blue" ><i class="fa fa-search"></i>View</a></td>
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

			jQuery("#frmFdl").validate({
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
		
		if(jQuery( "#frmFdl" ).valid()==true){
		
			var dataString = {'from':from,'to':to};
		
		 $.ajax({
			type:"POST",
			url:"Ajax/getFdDetailByDate.jsp",
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