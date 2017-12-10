<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Dao.VoucherDao"%>
<%@page import="Model.Bean.VoucherBean"%>
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
				<a href="#">Approvals</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Approval</a><i class="fa fa-angle-right"></i> view</li>
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
	   <div class="caption">
		View Approval
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	   <!-- ---------------------------------------------------------------------------- -->
	   <!-- ------------------- -------------- Start Tab ---------------------------------------------->
		<ul class="nav nav-pills">
			<li class="active"><a href="#tab1" data-toggle="tab">New Share</a></li>
	
			
	</ul>
	  <div class="tab-content">
	  
	 <!-- ------------------------------------------------------------------- -->
	 <div class="tab-pane fade active in" id="tab1">
		<table id="dataTable1" class="table table-striped table-bordered table-hover">
		   <thead>
            <tr>
                <th>Sno</th>
                <th>Trx no</th>
                <th>Trx Date</th>
                 <th>VoucherType</th>
                 <th>Narration</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
            <tbody align="center">
        <%
        ArrayList<VoucherBean> lst3 =new VoucherDao().getAllVoucherByType("new_share");
       
		int i3=1;
		if(lst3!=null){
			for(VoucherBean bean3:lst3){
        
        %>
            <tr>
                <td><%=i3 %></td>
                <td><%=bean3.getTrx_no() %></td>
               <td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean3.getTrx_date().getTime()) %></td>
                <td><%= bean3.getVtype() %></td>
                <td><%=bean3.getVoucher_type() %></td>
                <td><%=bean3.getVamt() %></td>
                <td><%=bean3.getStatus() %></td>
                <td><a href="#" class="btn btn-xs blue" onclick="viewshare(<%=bean3.getAd_voucher_id()%>)">View</a></td>
                
            </tr>
            <%
            i3++;
			}
		   }
            %>
            </tbody>
		</table>	
	</div>
	
	
	<!-- ------------------------------------------------------------------- -->
	</div><!-- End tab content -->
	<!-- --------------------------------------------------------------------------- -->
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
<script type="text/javascript">
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	
});
</script>
<script type="text/javascript">
function view(ad_voucher_id){
	 var popup;
popup=	window.open ("view_voucher.jsp?ad_voucher_id="+ad_voucher_id, "mywindow","location=1,status=1,scrollbars=1,  width=1000,height=500, left=150,top=100");
popup.focus();
}
function viewshare(ad_voucher_id){
	 var popup;
popup=	window.open ("view_sharevoucher.jsp?ad_voucher_id="+ad_voucher_id, "mywindow","location=1,status=1,scrollbars=1,  width=1000,height=500, left=150,top=100");
popup.focus();
}

</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>