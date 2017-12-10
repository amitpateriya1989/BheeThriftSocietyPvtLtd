<%@page import="Model.Bean.JournalLedgerBean"%>
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
<table id="day_book" width="100%">
<tr>
<td>

<center>
Journal Ledger  as on Date From <%=new SimpleDateFormat("dd/MM/yyyy").format(from) %>&nbsp; To  &nbsp; <%=new SimpleDateFormat("dd/MM/yyyy").format(to) %>
</center>
</td>
</tr>
<td>
<table id="day_book" name="day_book" border="1"  width="100%" style="border:0px solid black;background-image:url(images/tab_background.png); margin-top:0px;margin-bottom:0px; font-family:'Times New Roman', Times, serif;" align="center">
			
			<tr style="font-size: 14px;">
				<th>S.No</th>
				<th >Date</th>
				<th>Voucher No</th>
				<th>A/C No</th>
				<th>A/c Name</th>
				<th>Party Name</th>
				<th>Trx Type</th>
				<!-- <th>Particulars</th> -->
				<th>Debit(Dr)</th>
				<th>Credit(Cr)</th>
			</tr>
					
<%int i=0;
Double total_deb=0.0;
Double total_cre=0.0;
TransactionDao vtd=new TransactionDao();
ArrayList<JournalLedgerBean> vtb=vtd.getAllJournalEnteries(from,to);
for(JournalLedgerBean b:vtb){
i++;

%>
			<tr style="font-size: 12px;">
			
		
			<td ><%=i %></td>
			<td ><%=new SimpleDateFormat("dd/MM/yyyy").format(b.getTrx_date()) %></td>
			<td><%=b.getVno() %></td>
			<td ><%=b.getAc_no() %></td>
			<td ><%=b.getAc_name() %></td>
			<td><%if(b.getMember_name()!=null){out.println(b.getMember_name());} %></td>
			<td ><%=b.getVtype() %></td>
			
			<%-- <td ><%=b.getNarration() %></td> --%>
			<% 
			total_deb+=b.getDramt();
			total_cre+=b.getCramt();
			%>
			<td ><%=new BigDecimal(b.getDramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
			<td ><%=new BigDecimal(b.getCramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
			</tr>
<%//} 
}%>	
<tr style=" "><td colspan="7" style="text-align: center;">Total</td><td><%=new BigDecimal(total_deb).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td><td><%=new BigDecimal(total_cre).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td></tr>	
			</table>
			</td>
			</table>
			