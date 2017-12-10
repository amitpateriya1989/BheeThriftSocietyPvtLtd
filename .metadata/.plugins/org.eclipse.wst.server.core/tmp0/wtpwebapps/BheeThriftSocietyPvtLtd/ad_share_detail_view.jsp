<%@page import="Model.Dao.MemberShareDao"%>
<%@page import="Model.Dao.ShareDao"%>
<%@page import="Model.Bean.ShareViewBean"%>
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
				<a href="#">Share</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Share Detail</a><i class="fa fa-angle-right"></i>View</li>
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
		View Share Detail
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	  
	   <div class="portlet-body">
			<div id="view_share">
			<table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th width="10%">Share PK</th>
					<th width="10%">Date</th>
					<th width="5%">Staff No.</th>
					<th width="20%">Name</th>
					<th width="5%">Batch No</th>
					<th width="5%">From</th>
					<th width="5%">To</th>
					<th width="10%">Qty</th>
					<th width="10%">Amt</th>
					
					<th width="10%">Status</th>
					<th width="10%">OPT</th>
					
				</tr>
				</thead>
				<tbody>
		 <%
	   		ArrayList<ShareViewBean> sharelist=new MemberShareDao().getAllMemberShareDetail();
	   if(sharelist.isEmpty()!=true){
		   for(ShareViewBean bean:sharelist){%>
			   <tr>
			    <td><%=bean.getAd_member_share_id()%></td>
			   <td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getDate_of_allocation().getTime()) %></td>
			   <td><%=bean.getAd_society_no() %></td>
			   <td><%=bean.getName() %></td>
			   <td><%=bean.getBatch_no() %></td>
			   <td><%=bean.getShare_no_form() %></td>
			   <td><%=bean.getShare_no_to() %></td>
			   <td><%=bean.getQnt_of_share() %></td>
			   <td><%=bean.getShare_amt() %></td>
			  
			   <td><%=bean.getStatus() %></td>
			   <td><a href="AdMemberShare?action=edit&ad_member_share_id=<%=bean.getAd_member_share_id() %>" class="btn btn-xs blue">Edit</a>
			   	   <a href="AdMemberShare?action=delete&ad_member_share_id=<%=bean.getAd_member_share_id() %>" class="btn btn-xs red">Delete</a></td>
			 </tr>  
		   <%}
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


<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
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