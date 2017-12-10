<%@page import="Model.Dao.VoucherDao"%>
<%@page import="Model.Bean.VoucherBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<%try{ %>
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%@ include file= "Layout/header.jsp" %>
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
				<a href="#">Home</a><i class="fa fa-angle-right"></i>
			</li>
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
 <div class="portlet-body">
			<div class="row">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat blue">
						<div class="visual">
							<i class="fa fa-user"></i>
						</div>
						<div class="details">
							<div class="number">
								 <%
        ArrayList<VoucherBean> lst2 =new VoucherDao().getAllVoucherByType("new_member");
       
			if(lst2.isEmpty()!=true){
			out.print(lst2.size());
			
			}
        %>
							</div>
							<div class="desc">
								 Membership Voucher
							</div>
						</div>
						<a class="more" href="Membership Approval.jsp">
							 View more <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat green">
						<div class="visual">
							<i class="fa fa-inr"></i>
						</div>
						<div class="details">
							<div class="number">
								<%
        lst2 =new VoucherDao().getAllVoucherByType("new_loan");
       
			if(lst2.isEmpty()!=true){
			out.print(lst2.size());
			
			}
        %>
							</div>
							<div class="desc">
								 Loan Voucher 
							</div>
						</div>
						<a class="more" href="loanapproval.jsp">
							 View more <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat purple">
						<div class="visual">
							<i class="fa fa-signal"></i>
						</div>
						<div class="details">
							<div class="number">
								<%
        lst2 =new VoucherDao().getAllVoucherByType("new_share");
       
			if(lst2.isEmpty()!=true){
			out.print(lst2.size());
			
			}
        %>
							</div>
							<div class="desc">
								 Share Voucher
							</div>
						</div>
						<a class="more" href="shareapproval.jsp">
							 View more <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat yellow">
						<div class="visual">
							<i class="fa fa-bar-chart-o"></i>
						</div>
						<div class="details">
							<div class="number">
								<%
        lst2 =new VoucherDao().getAllVoucherByType("final_pay");
       
			if(lst2.isEmpty()!=true){
			out.print(lst2.size());
			
			}
        %>
							</div>
							<div class="desc">
								 Final Payment
							</div>
						</div>
						<a class="more" href="finalpayapproval.jsp">
							 View more <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat red">
						<div class="visual">
							<i class="fa fa-user"></i>
						</div>
						<div class="details">
							<div class="number">
								<%
        lst2 =new VoucherDao().getAllVoucherByType("new_fd");
       
			if(lst2.isEmpty()!=true){
			out.print(lst2.size());
			
			}
        %>
							</div>
							<div class="desc">
								 Fixed Deposit
							</div>
						</div>
						<a class="more" href="fdapproval.jsp">
							 View more <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat blue">
						<div class="visual">
							<i class="fa fa-inr"></i>
						</div>
						<div class="details">
							<div class="number">
								<%
        lst2 =new VoucherDao().getAllVoucherByType("Payroll");
       
			if(lst2.isEmpty()!=true){
			out.print(lst2.size());
			
			}
        %>
							</div>
							<div class="desc">
								 Payroll Voucher
							</div>
						</div>
						<a class="more" href="payroll_approval.jsp">
							 View more <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat green">
						<div class="visual">
							<i class="fa fa-signal"></i>
						</div>
						<div class="details">
							<div class="number">
								<%
        lst2 =new VoucherDao().getAllVoucherByType("Manual Voucher");
       
			if(lst2.isEmpty()!=true){
			out.print(lst2.size());
			
			}
        %>
							</div>
							<div class="desc">
								 Manual Voucher
							</div>
						</div>
						<a class="more" href="voucherapproval.jsp">
							 View more <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				<!-- <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat yellow">
						<div class="visual">
							<i class="fa fa-bar-chart-o"></i>
						</div>
						<div class="details">
							<div class="number">
								 12,5M$
							</div>
							<div class="desc">
								 Total Profit
							</div>
						</div>
						<a class="more" href="#">
							 View more <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div> -->
			</div>
	    </div><!-- End portlet-body -->
<!------------------------------------------------------------------- -->			
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
</body>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</html>