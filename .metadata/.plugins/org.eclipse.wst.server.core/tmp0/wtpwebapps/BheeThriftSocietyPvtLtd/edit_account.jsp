<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Bean.AccountTypeBean"%>
<%@page import="Model.Dao.AccountTypeDao"%>
<%@page import="Model.Dao.AccountGroupDao"%>
<%@page import="Model.Bean.AccountSubGroupBean"%>
<%@page import="Model.Dao.AccountSubGroupDao"%>
<%@page import="Model.Bean.AccountGroupBean"%>
<%@page import="Model.Dao.AccountGroupDao"%>
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
<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-datepicker/css/datepicker.css"/>
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
			<li><a href="#">Account</a><i class="fa fa-angle-right"></i>Day Book</li>
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
	   <div class="caption">Account List</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	   </div><!-- end portlet-title -->
	   <div class="portlet-body">
	    <form id="frmAccount" autocomplete="off" action="#" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Ledger Type: : <span class="red">*</span></td>
					<td width="35%">
					<select class="form-control input-medium" name="ad_ac_type_id" id="ad_ac_type_id">
					<option value="">--Select Ledger Type--</option>
					<%AccountTypeDao dao1=new AccountTypeDao();
					 ArrayList <AccountTypeBean> aclist=dao1.getAllAccountType();
					if(aclist!=null){
					for(AccountTypeBean bean:aclist){%>
					<option value="<%=bean.getAd_ac_type_id()%>"><%=bean.getName() %></option>
					<%} }%>
					</select>
					<label id="form_type_error" class="error "></label>
					</td>
				</tr>
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div><!-- En portlet box -->
	<!-- -------------------------------------------------------------- -->
	<div class="portlet box green">
	<div class="portlet-title">
	<div class="caption">Ledger View</div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	</div><!-- end portlet-title -->
	<div class="portlet-body">
	<table class="table table-bordered">
	<tr>
		<td>
			<div class="btn-group pull-right">
			<button class="btn red btn-md dropdown-toggle" type="button" data-toggle="dropdown">
				<i class="fa fa-bar-chart-o"></i> Print Report <i class="fa fa-angle-down"></i>
			</button>
			<ul class="dropdown-menu" role="menu">
			<!-- <li><a href="#" onclick="tableToExcel('day_book', 'Day Book')"><img src="Image/excel-icon.png" height="25" width="25" /> Excel</a></li> -->
			<li><a onclick="prnts()" href="#"><img  src="Image/print-icon.png" height="25" width="25" />PDF</a></li>
			</ul>
			</div>
		</td>
	</tr>
	</table>
	    <div class="row">
		<div class="col-md-12">
			<div id="ledger_window" class="scroller" style="height:300px" data-always-visible="1" data-rail-visible="0"></div>
		</div><!-- End column -->
		</div><!-- End row -->
	</div><!-- End portlet-body -->
	</div><!-- En portlet box -->

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
<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script>
jQuery(function() {  
	
	jQuery("#frmAccount").validate({
		  rules: {
			  ad_ac_type_id:{
				  required: true,
				  digits:true
			  }
		  }
		});
});
</script>
<script type="text/javascript">
$(document).ready(function(e){
	
	$('#ad_ac_type_id').change(function(){
		
		if(jQuery("#frmAccount").valid()==true){

		var ad_ac_type_id  = $(this).val();
		
		var dataString = {'ad_ac_type_id':ad_ac_type_id};
		
		 $.ajax({
			type:"POST",
			url:"getaccount.jsp",
			data:dataString,
			success: function(data){
				$('#ledger_window').html(data);
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log(textStatus);
			}
			
		 });
		}else{
			return false;
		}
	});//end showLadger eventAR/BPL/57
	
	
});//end dom
</script>
<script type="text/javascript">
   
    function prnts()
    {
    
    var divElements = document.getElementById('ledger_window').innerHTML//contentWindow.document.body.innerHTML;
    	 var printWindow = window.open("", "_blank", "");            
            printWindow.document.open();          
            printWindow.document.write('<html><body><center> <h3 style="margin-bottom:0px;margin-top:0px"> Central Bank Employees Co-Operative Credit Society Ltd. Bhopal </h3><h4 style="margin-bottom:0px;margin-top:0px">AR/BPL/57<br/></h4></center>');
          
    		printWindow.document.write(divElements);
            printWindow.document.write('</body></html>');
            printWindow.document.close();
            printWindow.focus();
    		 setTimeout(function() {
                printWindow.print();
                printWindow.close();
            }, 100);
    }
</script>   
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>