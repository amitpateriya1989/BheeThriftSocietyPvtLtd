<%@page import="Model.Bean.VoucherTrxDetailBean"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Bean.VoucherBean"%>
<%@page import="Model.Dao.VoucherDao"%>
<%@page import="Model.Bean.TransactionBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.TransactionDao"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%
String str_from=request.getParameter("from");
String str_to=request.getParameter("to");
DateFormat formatter ; 
Date from =null; 
Date to =null; 
formatter = new SimpleDateFormat("dd/MM/yyyy");
try {
from = formatter.parse(str_from);
to = formatter.parse(str_to);
} catch (ParseException e) {
	e.printStackTrace();
}
System.out.println(from+"__"+to);
%>
<div class="row">
	<div class="col-md-12 text-center">
	Cash Book  as on Date From <%=new SimpleDateFormat("dd/MM/yyyy").format(from) %>&nbsp; To  &nbsp; <%=new SimpleDateFormat("dd/MM/yyyy").format(to) %>
	</div>
</div>
<table id="cash_book" name="cash_book" class="table table-bordered">
			
<tr style=" text-align: center; font-size: 14px;">
	<th width="50">S.No</th>
	<th>Date</th>
	<th width="100">A/C No</th>
	<th>A/c Name</th>
	<th>Party Name</th>
	<th>Trx Type</th>
	<th>Voucher No</th>
	<th>Particulars</th>
	<th>Debit(Dr)</th>
	<th>Credit(Cr)</th>
</tr>
					
<%int i=0;
Double total_deb=0.0;
Double total_cre=0.0;
TransactionDao vtd=new TransactionDao();
ArrayList<VoucherTrxDetailBean> vtb=vtd.getAllVoucherTrxDetailByCash(from,to);
for(VoucherTrxDetailBean b:vtb){
i++;
/* System.out.println(b.getVoucher().getStatus().trim());
if(b.getVoucher().getStatus().trim().equals("Approved")){ */
%>
			<tr style="font-size: 12px; text-align: center;background-color: white;">
			
		
			<td ><%=i %></td>
			<td ><%=new SimpleDateFormat("dd/MM/yyyy").format(b.getTrx_date()) %></td>
			<td ><%=b.getAc_no() %></td>
			<td align="left"><%=b.getAc_name() %></td>
			<td align="left"> <%if(b.getMember_name()!=null){out.println(b.getMember_name());} %></td>
			<td ><%=b.getVoucher_type()%></td>
			<td ><%=b.getVno() %></td>
			<td ><% if(b.getNarration()!=null){
				out.println(b.getNarration());
			} %></td>
			<% 
			total_deb+=b.getDramt();
			total_cre+=b.getCramt();
			%>
			<td ><%=new BigDecimal(b.getDramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
			<td ><%=new BigDecimal(b.getCramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
			</tr>
<%//} 
}%>	
<tr style=" "><td colspan="8" style="text-align: center;">Total</td><td><%=new BigDecimal(total_deb).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td><td><%=new BigDecimal(total_cre).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td></tr>	
</table>