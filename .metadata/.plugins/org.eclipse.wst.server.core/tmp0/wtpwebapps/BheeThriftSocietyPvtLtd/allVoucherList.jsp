<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Bean.VoucherBean"%>
<%@page import="Model.Dao.VoucherDao"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
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
				<a href="#">Transaction</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Vouchers Detail</a><i class="fa fa-angle-right"></i>Search</li>
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
	   <div class="caption">Vouchers Detail</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmVouchersDetail" autocomplete="off" action="#" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">From : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium date-picker" type="text" placeholder="dd/mm/yyyy" type="text" id="from" name="from" value='<%=new SimpleDateFormat("dd/MM/yyyy").format(session.getAttribute("openday")) %>' /><label class="error"></label>
					</td>
					<td width="15%">To  : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium date-picker" type="text" placeholder="dd/mm/yyyy" type="text" id="to" name="to" value='<%=new SimpleDateFormat("dd/MM/yyyy").format(session.getAttribute("openday")) %>' /><label class="error"></label></td>
				</tr>				
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn blue" type="button" id="search" name="search" value="Search"/>
					</td>
				</tr>
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div>
	
	<!-- -------------------------------------------------------------- -->
	<div class="portlet box green">
	<div class="portlet-title">
	<div class="caption">Voucher List View</div>
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
			<li><a onclick="prnts()" href="#"><img  src="Image/print-icon.png" height="25" width="25" />PDF</a></li>
			</ul>
			</div>
		</td>
	</tr>
	</table>
	    <div class="row">
		<div class="col-md-12">
			<div id="voucher_window">
			<table class="table table-bordered">
	<tr>
		<th>S.No</th>
		<th>Trx No</th>
		<th>Voucher No </th>
		<th>Voucher Date</th>
		<th>Voucher Type</th>
		<th>Trx Type</th>
		<th>Trx From</th>
		<th>Amount</th>
		<th>Status</th>
		<th>View</th>
		
	</tr>
	
	<%
	   VoucherDao dao=new VoucherDao();
		ArrayList<VoucherBean> voucherlist=dao.getAllVoucherList();
		if(voucherlist!=null){
			int i=0;
			for(VoucherBean bean:voucherlist){%>
		<tr class="tr" id="<%=bean.getAd_voucher_id() %>">		
		<td><%=++i %></td>
		<td><a  target="_blank" href="view_voucher1.jsp?ad_voucher_id=<%=bean.getAd_voucher_id() %>" ><%=bean.getTrx_no() %></a></td>
		<td><%=bean.getVno() %></td>
		<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getTrx_date()) %></td>
		<td><%=bean.getVtype() %></td>
		<td><%=bean.getVfrom() %></td>
		<td><%=bean.getDescription() %></td>
		<td><%=bean.getVamt() %></td>
		<td><%=bean.getStatus() %></td>
		<td><a  target="_blank" href="view_voucher1.jsp?ad_voucher_id=<%=bean.getAd_voucher_id() %>" class="btn btn-xs blue" ><i class="fa fa-edit"></i>View</a></td>
		
		</tr>		
			<%
			}
		}
	%>
</table>	
			</div>
		</div><!-- End column -->
		</div><!-- End row -->
	</div><!-- End portlet-body -->
	</div><!-- En portlet box -->

<!------------------------------------------------------------------- -->	
	
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
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script>
jQuery(function() {       
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate : new Date('<%=session.getAttribute("openday")%>'),autoclose: true});
	jQuery.validator.addMethod("validDate", function (value, element) {
        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	jQuery("#frmVouchersDetail").validate({
		  rules: {
			  from: {
		        required: true,
		        validDate: true,
		    },
		    to:{
		    	required:true,
		    	validDate:true
		    }
		  }
		});
	
	$('#search').click(function(){
		if(jQuery("#frmVouchersDetail").valid()==true){
			var from = $('#from').val();
			var to   = $('#to').val();
			$.ajax({
				type:"post",
				url:"getvoucherbydate.jsp",
				data:{"from":from,"to":to},
				success: function(data){
					$('#voucher_window').html(data)
				},
				error: function(jqXhr, testStatus, errorThrown){
					//console.log(stauts);
				}
			});
		}
	});
	
	
});
</script>
<script type="text/javascript">

function prnts()
{
	
	var divElements = document.getElementById('voucher_window').innerHTML//contentWindow.document.body.innerHTML;
	 var printWindow = window.open("", "_blank", "");            
       printWindow.document.open();          
       printWindow.document.write('<html><body><center> <h3 style="margin-bottom:0px"> Central Bank Employees Co-Operative Credit Society Ltd. Bhopal </h3><h4 style="margin-top:0px">AR/BPL/57<br/></h4></center><hr>');
      
	   printWindow.document.write(divElements);
       printWindow.document.write('</body></html>');
       printWindow.document.close();
       printWindow.focus();       
       printWindow.print();
       printWindow.close();
       
}
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>