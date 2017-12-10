<%@page import="Model.Bean.VoucherTrxDetailBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="Model.Bean.VoucherBean"%>
<%@page import="Model.Dao.VoucherDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="Model.Bean.UserBean"%>
<%@page import="Model.Bean.TransactionBean"%>
<%@page import="Model.Dao.TransactionDao"%>


<%
	try{
		int ad_voucher_id = 0;
	
	UserBean user = null;
	try {
		ad_voucher_id = Integer.parseInt(request
				.getParameter("ad_voucher_id"));
		if (request.getSession(false).getAttribute("userbean") == null) {
			response.sendRedirect("logout.jsp");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}

	ArrayList<VoucherTrxDetailBean> lst=new VoucherDao().getAllVoucherTrxDetail(ad_voucher_id);

	VoucherBean vbean = new VoucherDao()
			.getVoucherByVoucherId(ad_voucher_id);
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
	<%-- <%@ include file= "Layout/headtitle.jsp" %> --%>
	<!-- BEGIN HEADER -->
	
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN EMPTY PAGE SIDEBAR -->
		<%-- 	<%@ include file= "Layout/emptynavbar.jsp" %> --%>
		<!-- END EMPTY PAGE SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN PAGE CONTENT-->
				<div class="row">

					<div class="col-md-12">


								<!------------------------------------------------------------------- -->
								<!-- BEGIN BORDERED TABLE PORTLET-->
								<div class="portlet box purple" >
									<div class="portlet-title">
										<div class="caption">Voucher Approval</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"> </a>
										</div>
									</div>
									</div>
									<table id="data1">
									<tr>
									<td>
									<div class="portlet-body" id="voucher">
									
									
										<table class="table table-bordered" >
											<tbody>
												<tr>
													<td>Trx No.</td>
													<td>:</td>
													<td><%=vbean.getTrx_no()%></td>
													<td>Voucher Date</td>
													<td>:</td>
													<td><%=new SimpleDateFormat("dd/MM/yyyy").format(vbean.getTrx_date())%></td>
												</tr>
												<tr>
													<td>Voucher Type</td>
													<td>:</td>
													<td><%=vbean.getVtype()%></td>
													<td>Voucher Form</td>
													<td>:</td>
													<td><%=vbean.getVfrom()%></td>



												</tr>
												<tr>
													<td>Description</td>
													<td>:</td>
													<td><%=vbean.getDescription()%></td>
													<td>Instrument Form</td>
													<td>:</td>
													<td><%=vbean.getInstrument_from()%></td>
												</tr>
												<tr>
													<td>Instrument No</td>
													<td>:</td>
													<td><%=vbean.getInstrument_no()%></td>
													<td>Instrument Date</td>
													<td>:</td>
													<td><%=new SimpleDateFormat("dd/MM/yyyy").format(vbean.getInstrument_date())%></td>

												</tr>
												<tr>
													<td>Voucher Amt</td>
													<td>:</td>
													<td><%=vbean.getVamt() %></td>
													<td>Voucher Type</td>
													<td>:</td>
													<td><%=vbean.getVoucher_type() %></td>

												</tr>

												
											</tbody>
										</table>


										<table class="table table-bordered">
											<thead>
												<tr style="background: teal; color: white">
													<th>Sno</th>

													<th>Trx Date</th>
													<th>A/c Name</th>
													<th>Narration</th>
													<th>Dr</th>
													<th>Cr</th>

												</tr>
											</thead>

											<tbody>
												<%
													double totalcr = 0;
													double totaldr = 0;

													int i = 1;
													if (lst.isEmpty() != true) {
														for (VoucherTrxDetailBean bean : lst) {

															String name = "";
															String narration = "";

															if (bean.getMember_name()!= null) {
																name = "[ " + bean.getMember_name() + " ]";
															}
															if (bean.getNarration() != null) {
																narration = bean.getNarration();
															}
												%>



												<tr>
													<td><%=i%></td>

													<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getTrx_date())%></td>
													<td><label style="color: olive;"><%=bean.getAc_name()%></label>&nbsp;&nbsp;<%=name%>
													</td>
													<td><%=narration%></td>
													<td align="right"><%=new BigDecimal(bean.getDramt()).setScale(2,
							BigDecimal.ROUND_HALF_EVEN)%></td>
													<td align="right"><%=new BigDecimal(bean.getCramt()).setScale(2,
							BigDecimal.ROUND_HALF_EVEN)%></td>


												</tr>

												<%
													totaldr += bean.getDramt();
															totalcr += bean.getCramt();
															i++;

														}
													}
												%>
											</tbody>
											<tbody>
												<tr>
													<th colspan="3"></th>
													<th  align="right">Total</th>
													 <th><%=new BigDecimal(Math.abs(Math.abs(totaldr))).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></th>
                <th><%=new BigDecimal(Math.abs(Math.abs(totalcr))).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></th>
												</tr>




											</tbody>
										</table>
										</div>
									</td>
								</tr>
							</table>
										 <table width="100%">

											<tr>
												<td align="center">
												<%-- <input type="button"
													onclick="editvoucher('<%=ad_voucher_id%>')"
													value="Edit Voucher" class="btn btn blue" /> <input
													type="button"
													onclick="return approve('<%=ad_voucher_id%>')"
													class="btn btn green" value="Approve" /> <input
													onclick="delete_voucher('<%=ad_voucher_id%>')"
													type="button" class="btn btn red" value="Delete" /> --%>
													<a href="#" onclick="tableToExcel('data1', 'Voucher Book')" class="btn green btn-md "> Excel</a>
													 <input	type="button" onclick="prnts()" value="Print" class="btn btn blue" /></td>
													 
											</tr>
										</table> 
										</div>
										</div>
										</div>
</div></div>
									
  
<script type="text/javascript">

$(document).ready(function(e) {
	
});
window.onunload = refreshParent;
function refreshParent() {
    window.opener.location.reload();
}

function approve(ad_voucher_id){
	
	
	if(parseFloat($("#totaldr").val())==parseFloat($("#totalcr").val())  ){
		
		if(parseFloat($("#totaldr").val())==parseFloat($("#vamt").val())){
			
			$.ajax({
				   type: "POST",
				   url: "AdVoucher?action=approve&ad_voucher_id="+ad_voucher_id,
				   async:false,
				   success: function(data)
				   {	
					   alert("Voucher approved successfully  , Voucher No is  : "+data);
					   
					   window.close();
			  	} }); 
			
		}else{
			
			alert(" Total Dr , Cr and Voucher Amount  Not Match Please Correct Then Try....");
			event.stop();
			
			
		}
		
	
		
		
	}else{
		
		alert(" Total Dr And Cr  Not Match Please Correct Then Try....");
		event.stop();
	}
	
	
	
	
		
		
	

//	window.location.href="AdVoucher?action=approve&ad_voucher_id="+ad_voucher_id;
	
}
function delete_voucher(ad_voucher_id){	
	$.ajax({
		   type: "POST",
		   url: "AdVoucher?action=deletevoucherbeforeapprovel&ad_voucher_id="+ad_voucher_id,
		   async:false,
		   success: function(data)
		   {	
			   alert(data);
			   
			   window.close();
	  	} }); 
	 
	
	//window.location.href="AdVoucher?action=deletevoucherbeforeapprovel&ad_voucher_id="+ad_voucher_id;
}

function editvoucher(ad_voucher_id){
	window.location.href="edit_voucher.jsp?ad_voucher_id="+ad_voucher_id;

}
function prnts()
{
	
	var divElements = document.getElementById('voucher').innerHTML;
	 var printWindow = window.open("", "_blank", "");            
        printWindow.document.open();          
        printWindow.document.write('<html><body><center> <h3 style="margin-bottom:0px;margin-top:0px"> Central Bank Employees Co-Operative Credit Society Ltd. Bhopal </h3><h4 style="margin-bottom:0px;margin-top:0px">AR/BPL/57<br/></h4></center>');
      
		printWindow.document.write(divElements);
        printWindow.document.write('</body></html>');
        printWindow.document.close();
       printWindow.focus();       
       printWindow.print();
        printWindow.close();
       
}
</script>
<script type="text/javascript">
var tableToExcel = (function() {
	  var uri = 'data:application/vnd.ms-excel;base64,'
	    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><center> <h3 style="margin-bottom:0px;margin-top:0px"> Central Bank Employees Co-Operative Credit Society Ltd. Bhopal </h3><h4 style="margin-bottom:0px;margin-top:0px">AR/BPL/57<br/></h4></center><table>{table}</table></body></html>'
	    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
	    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	  return function(table, name) {
	    if (!table.nodeType) table = document.getElementById('data1')
	    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
	    window.location.href = uri + base64(format(template, ctx))
	  }
	})()
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>



