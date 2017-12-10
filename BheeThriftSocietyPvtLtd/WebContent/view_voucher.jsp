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



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/assets/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN THEME STYLES -->
<link
	href="${pageContext.request.contextPath}/assets/css/style-color.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/assets/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/assets/css/style-responsive.css"
	rel="stylesheet" type="text/css" />

</head>


<%
try{
int ad_voucher_id=0;
UserBean user=null;
String vid=request.getParameter("ad_voucher_id");
if(vid!=null){
try{
	 ad_voucher_id = Integer.parseInt(vid);
	if(request.getSession(false).getAttribute("userbean")==null){
	response.sendRedirect("logout.jsp");
	}
}catch(Exception e){
	e.printStackTrace();
}
}	
//ArrayList<TransactionBean> lst =new TransactionDao().getTransactionByType(ad_voucher_id);
ArrayList<VoucherTrxDetailBean> lst=new VoucherDao().getAllVoucherTrxDetail(ad_voucher_id);
VoucherBean vbean = new VoucherDao().getVoucherByVoucherId(ad_voucher_id);

%>


<body>


	<div id="voucher">
		<table id="tblContainer" border="0" cellpadding="0" cellspacing="0"
			width="100%">

			<tr>
				<td colspan="6" class=""><hr style="color: black;" />
				</td>
			</tr>

			<tr>
				<td>Trx No.</td>
				<td>:</td>
				<td><%=vbean.getTrx_no() %></td>
				<td>Voucher Date</td>
				<td>:</td>
				<td><%=new SimpleDateFormat("dd/MM/yyyy").format(vbean.getTrx_date()) %></td>
			</tr>
			<tr>
				<td>Voucher Type</td>
				<td>:</td>
				<td><%=vbean.getVtype() %></td>
				<td>Voucher Form</td>
				<td>:</td>
				<td><%=vbean.getVfrom() %></td>



			</tr>
			<tr>
				<td>Description</td>
				<td>:</td>
				<td><%=vbean.getDescription() %> <input type="hidden"
					name="description" value="<%=vbean.getDescription() %>" />
				</td>
				<td>Instrument Form</td>
				<td>:</td>
				<td><%=vbean.getInstrument_from() %></td>
			</tr>
			<tr>
				<td>Instrument No</td>
				<td>:</td>
				<td><%=vbean.getInstrument_no() %></td>
				<td>Instrument Date</td>
				<td>:</td>
				<%
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
String date="";
if(vbean.getInstrument_date()==null){
	date="dd/MM/yyyy";
}else{
	date=sdf.format(vbean.getInstrument_date());
} %>
				
				<td><%=date %></td>

			</tr>
			<tr>
				<td>Voucher Amt</td>
				<td>:</td>
				<td><input name="vamt" id="vamt"
					style="background: none; border: none; text-align: left;"
					readonly="readonly" value="<%=vbean.getVamt() %>" />
				</td>
				<td>Voucher Type</td>
				<td>:</td>
				<td><%=vbean.getVoucher_type() %></td>

			</tr>

			<tr>
				<td colspan="6" class=""><hr style="color: black;" />
				</td>
			</tr>

			<tr>
				<td colspan="2" height="5"></td>
			</tr>
			</tbody>
		</table>
		<table border="1" cellspacing="0" width="100%">
			<thead>
				<tr style="background: teal; color: white">
					<th style="width: 10%">Sno</th>
					<th style="width: 10%">Trx Date</th>
					<th style="width: 30%">A/c Name</th>
					<th style="width: 30%">Narration</th>
					<th style="width: 10%">Dr</th>
					<th style="width: 10%">Cr</th>

				</tr>
			</thead>

			<tbody>
				<%
        
       double totalcr=0;
        double totaldr=0;
        
		int i=1;
		if(lst.isEmpty()!=true){
			for(VoucherTrxDetailBean bean:lst){
				
				String name="";
				String narration="";
			
				if(bean.getMember_name()!=null){
					 name="[ "+bean.getMember_name()+ "]";
				}
				if(bean.getNarration()!=null){
					narration=bean.getNarration();
				}
				
				
        
        %>

<%

 date="";
if(bean.getTrx_date()==null){
	date="dd/MM/yyyy";
}else{
	date=sdf.format(bean.getTrx_date());
} %>

				<tr>
					<td><%=i %></td>
					<td style="font-size: 12px"><%= date %></td>
					<td><label style="color: olive;"><%=bean.getAc_name() %></label><%=name %>
					</td>
					<td><%=narration %></td>
					<td align="right"><%=bean.getDramt() %></td>
					<td align="right"><%=bean.getCramt() %></td>
				</tr>

				<%
            totaldr+=bean.getDramt();
            totalcr+=bean.getCramt();
            i++;
            
			}
		}
            %>
			</tbody>
			<tfoot>
				<tr>

					<th colspan="4" align="center">Rs. <%=vbean.getAmt_in_words() %></th>
					<th><input name="totaldr" id="totaldr"
						style="background: none; border: none; text-align: right;"
						readonly="readonly"
						value="<%=new BigDecimal(Math.abs(Math.abs(totaldr))).setScale(2, BigDecimal.ROUND_HALF_EVEN) %>" />
					</th>
					<th><input name="totalcr" id="totalcr"
						style="background: none; border: none; text-align: right;"
						readonly="readonly"
						value="<%=new BigDecimal(Math.abs(Math.abs(totalcr))).setScale(2, BigDecimal.ROUND_HALF_EVEN) %>" />
					</th>

				</tr>
			</tfoot>
		</table>

		<table width="100%">
			<tr>
				<td>&nbsp;</td>
			</tr>

		</table>

	</div>
	<div>
		<table width="100%" align="center">
			<tr>
				<td align="center"><%-- <input type="button"
					onclick="editvoucher('<%=ad_voucher_id %>')" class="btn btn blue"
					value="Edit" /> --%> <input type="button"
					onclick="return approve('<%=ad_voucher_id %>')"
					class="btn btn green" value="Approved" /> <input
					onclick="delete_voucher('<%=ad_voucher_id %>')" class="btn btn red"
					type="button" value="Delete" /> <input type="button"
					onclick="prnts()" value="Print" class="btn btn blue" /></td>
			</tr>
		</table>
	</div>

<%
int loan_trx_id=0;
if(session.getAttribute("loan_id")!=null){
 loan_trx_id=(Integer)session.getAttribute("loan_id");
}
%>

	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>

	<script type="text/javascript">

$(document).ready(function(e) {
	//alert();
	window.onunload = refreshParent;	
});

function refreshParent() {
    window.opener.location.reload();
}

function approve(ad_voucher_id){
	
	if(parseFloat($("#totaldr").val()) ==parseFloat($("#totalcr").val())  ){
		//alert();
		
		//if(parseFloat($("#totaldr").val())==parseFloat($("#vamt").val())){
			
			$.ajax({
				   type: "GET",
				   dataType: 'text',
				   url: "AdVoucher?action=approve&ad_voucher_id="+ad_voucher_id+"&loan_trx_id="+<%=loan_trx_id%>,
				   async:false,
				   success: function(data)
				   {	
					   
					   alert(" Voucher successfully approved  & voucher no is  : "+ data);					   
					   window.close();
			  	} 
			}); 
			
		/* }else{
			
			alert(" Total Dr , Cr and Voucher Amount  Not Match Please Correct Then Try....");
			event.stop();
			
			
		} */
		
	
		
		
 	}else{
		
		alert(" Total Dr And Cr  Not Match Please Correct Then Try....");
		event.stop();
	}
	
	
	
	
		
		
	

//	window.location.href="AdVoucher?action=approve&ad_voucher_id="+ad_voucher_id;
	
}
function delete_voucher(ad_voucher_id){	
	$.ajax({
		   type: "POST",
		   dataType: 'text',
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
	window.location.href="ad_voucher_edit.jsp?ad_voucher_id="+ad_voucher_id;

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
<%}catch(Exception e){
	e.printStackTrace();
} %>