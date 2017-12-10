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
<table id="bank_book" width="100%">
<tr>
<td>

<center>
BankBook as on Date From <%=new SimpleDateFormat("dd/MM/yyyy").format(from) %>&nbsp; To  &nbsp; <%=new SimpleDateFormat("dd/MM/yyyy").format(to) %>
</center>
</td>
</tr>
<tr>
<td>
<table id="bank_book" name="bank_book" border="1" cellpadding="0" cellspacing="0" width="100%" style="border:0px solid black;background-image:url(images/tab_background.png); margin-top:0px;margin-bottom:0px; font-family:'Times New Roman', Times, serif;" align="center">
			
			<tr style=" text-align: center; font-size: 14px;">
				<th width="50">S.No</th>
				<th width="100">A/C No</th>
				<th>A/c Name</th>
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
ArrayList<TransactionBean> vtb=vtd.getAllBankTransactionByDate(from,to);

TransactionBean trxbean = new TransactionDao().getAllBankTransactionByfromDate(from);

%>
<tr>
<td></td>
<td></td>
<td>Opening Balance</td>
<td></td>
<td></td>
<td></td> 
<% 
double opn=trxbean.getDramt()-trxbean.getCramt();
if(opn>0){
	out.print("<td>"+opn +"</td><td></td>");
}else{
	out.print("<td></td><td>"+Math.abs(opn) +"</td>");
}

%>

</tr>
<%
for(TransactionBean b:vtb){
i++;
/* System.out.println(b.getVoucher().getStatus().trim());
if(b.getVoucher().getStatus().trim().equals("Approved")){ */
%>
			<tr style="font-size: 12px; text-align: center;background-color: white;" valign="top">
			
		
			<td ><%=i %></td>
			<td ><%=b.getLedger().getAc_no() %></td>
			<td ><%out.print(b.getLedger().getAc_name()); if(b.getMember().getName()!=null){out.print("[ "+b.getMember().getName()+" ]");}  %></td>
			<td ><%=b.getVoucher().getVoucher_type()%></td>
			<td ><%=b.getVoucher().getVno() %></td>
			<td ><%=b.getNarration() %></td>
			<% 
			total_deb+=b.getDramt();
			total_cre+=b.getCramt();
			%>
			<td ><%=new BigDecimal(b.getDramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
			<td ><%=new BigDecimal(b.getCramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
			</tr>
<%//} 
}%>	
<tr style=" "><td colspan="6" style="text-align: center;">Total</td><td><%=new BigDecimal(total_deb).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td><td><%=new BigDecimal(total_cre).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td></tr>	
			</table>
			</td>
			</tr>
			</table>