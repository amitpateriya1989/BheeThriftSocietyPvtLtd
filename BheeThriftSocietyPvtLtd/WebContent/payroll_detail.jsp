<%@page import="Model.Dao.PayrollAdviceDao"%>
<%@page import="Model.Bean.PayrollAdviseBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<%
try{
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 		String from = null;
 		String to = null;
 		try {
 			Date date = (Date)session.getAttribute("openday");
 			
 			
 			
 			from=sdf.format(date).toString();
 			to= sdf.format(date).toString();
 			
 			
 			
 		} catch (Exception i) {
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

<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2-bootstrap.css"/>

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
				<a href="#">Payroll</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Member</a><i class="fa fa-angle-right"></i>Payroll Detail </li>
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
	   <div class="caption">Member Payroll Detail</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	   <form id="frmSearchDetail" name="frmPostingDetail" action="#">
			<table class="table table-bordered">
								 <tr>
					<td>Branch : </td>
					<td> <select class="form-control input-large"  name="ad_branch_id" id="ad_branch_id" >
					<option value="">--Select--</option>
					<%
					   ArrayList<BankBranchBean> list=new BankBranchDao().getAllBankBranchName();
					if(list.isEmpty()!=true){
						for(BankBranchBean bean:list){
							%>
							<option value="<%=bean.getAd_branch_id()%>">M<%=bean.getBranch_code() %>|<%=bean.getBranch() %></option>
							
							<%
						}
					}
					
					%>
					</select>
					<label class="error"></label>
					</td>
								
					<td>
					  <input class="btn btn blue btn-search" type="button" name="search" value="Search"/>
					  <input id="clear" class="btn btn green" type="reset" name="Clear"/>
					</td>
				</tr>
			</table>
			</form>
	    </div><!-- End portlet-body -->
	</div>
	
	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		View Member Payroll Detail
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	    <div class="portlet-body">
	    <table class="table table-bordered">
	<tr>
		<td>
			<div class="btn-group pull-right">
			<button class="btn red btn-md dropdown-toggle" type="button" data-toggle="dropdown">
				<i class="fa fa-bar-chart-o"></i> Print Report <i class="fa fa-angle-down"></i>
			</button>
			<ul class="dropdown-menu" role="menu">
			<li><a href="#" onclick="tableToExcel('ledger', 'Ledger Book')"><img src="Image/excel-icon.png" height="25" width="25" /> Excel</a></li>
			<li><a onclick="prnts()" href="#"><img  src="Image/print-icon.png" height="25" width="25" />PDF</a></li>
			</ul>
			</div>
		</td>
	</tr>
	</table>
	   <!--  class="scroller" style="height:600px" data-always-visible="1" data-rail-visible="0" -->
		 <div id="display">
		 <table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				
				<tr>
					<th width="10%">Sno</th>
					<th width="10%">PF No</th>
					<th width="10%">MEM No.</th>
					<th width="10%">Name</th>
					<th width="10%">Branch</th>
					<th width="10%">Thrift</th>
					<th width="10%">Loan</th>
					<th width="10%">Emergency</th>
					<th width="10%">Total</th>
					<th width="10%">Status</th>
					
					
				</tr>
				</thead>
				<tbody>
		 <%
	   		ArrayList<PayrollAdviseBean> list1=new PayrollAdviceDao().getAllPayrollAdvice();
		 int i=0;
	   if(list1.isEmpty()!=true){
		   for(PayrollAdviseBean bean:list1){%>
			   <tr>
			   <td><%=++i %></td>
			   <td><%=bean.getAd_pf_no() %></td>
			   <td><%=bean.getAd_society_no() %></td>
			   <td><%=bean.getName() %></td>
			   <td><%=bean.getBranch() %></td>
			   <td><%=bean.getFgds_fund() %></td>
			   <td><%=bean.getMainloan_emi() %></td>
			   <td><%=bean.getFestivalloan_emi() %></td>
			    <td><%=bean.getFgds_fund()+bean.getMainloan_emi()+bean.getFestivalloan_emi() %></td>
			     <td><%=bean.getMember_status() %></td>
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

<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<script>
jQuery(function() {       
	$('#ad_branch_id').select2();
	
	
	
	
	$(".btn-search").click(function(e){	
		
	var id = $(this).val();
	if($("#frmSearchDetail").valid()==false){
		return false;
	}else{
		var ad_branch_id = $('#ad_branch_id').val().trim();
		
		$.ajax({
			  type:"post",
			  url:"Ajax/get_payroll_detail_by_branch_id.jsp",
			  data:{"ad_branch_id":ad_branch_id},
			  success: function(data){
				  $('#display').html(data);
			  },
			  error: function(dataXhr, status, errorElement){
				  console.log(status);
			  }
			});//end ajax
	}//end check validation
		
	});//end member serach
	
});//end dom

function prnts()
{

var divElements = document.getElementById('display').innerHTML;
	 var printWindow = window.open("", "_blank", "");            
        printWindow.document.open();          
        printWindow.document.write('<html><body border="1" ><center> <h3 style="margin-bottom:-0px "> Central Bank Employees Co-Operative Credit Society Ltd. Bhopal </h3><h4 style=" margin-top: 0px";margin-bottom:-0px>AR/BPL/57</h4></center>');
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
<script type="text/javascript">
var tableToExcel = (function() {
	
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><center> <h3 style="margin-bottom:0px;margin-top:0px">Central Bank Employees Co-Operative Credit Society Ltd. Bhopal </h3><h4 style="margin-bottom:0px;margin-top:0px">AR/BPL/57<br/></h4></center><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  
    return function(table, name) {
    if (!table.nodeType) table = document.getElementById('display')
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
});
</script>
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