
<%@page import="Model.Dao.MemberRegistrationDao"%>
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
int ad_account_id=Integer.parseInt(request.getParameter("ad_account_id"));
int ad_member_id=Integer.parseInt(request.getParameter("ad_member_id"));
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
LedgerAccountBean bean=new LedgerAccountDao().getLedgerAccountById(ad_account_id); 
%>
<div class="row">
	<div class="col-md-12 text-center">
	Ledger  <%=bean.getAc_name() %> [<%if(ad_member_id!=0){out.print(new MemberRegistrationDao().getMemberName(ad_member_id).getName());} %>] as on Date From <%=new SimpleDateFormat("dd/MM/yyyy").format(from) %>&nbsp; To  &nbsp; <%=new SimpleDateFormat("dd/MM/yyyy").format(to) %>
	</div>
</div> 
<div>
<table id="ledger" name="ledger" class="table table-striped table-bordered table-hover">
			
<tr style=" text-align: center; font-size: 14px;">
	<th width="50">S.No</th>
	<th>Date</th>
	<th>VNo.</th>
	<th>Br.Code</th>
	<th>Inst.No.</th>
	<th>Party Name</th>
	<th>Particulars</th>
	<th>Trx Type</th>
	<th>Debit(Dr)</th>
	<th>Credit(Cr)</th>
	<th>Balance</th>
</tr>
					
<%
int i=0;
Double total_deb=0.0;
Double total_cre=0.0;
TransactionDao vtd=new TransactionDao();



ArrayList<VoucherTrxDetailBean> vtb=vtd.getAllVoucherOpeningDetailByAccountId(from,ad_account_id,ad_member_id);
for(VoucherTrxDetailBean b:vtb){
i++;
/* System.out.println(b.getVoucher().getStatus().trim());
if(b.getVoucher().getStatus().trim().equals("Approved")){ */
%>
			<tr style="font-size: 12px; text-align: center;">
			
		
			<td ><%=i %></td>
			<td ><%=new SimpleDateFormat("dd/MM/yyyy").format(b.getTrx_date()) %></td>
			<td ><%=b.getVno() %></td>
			<td ><%=b.getBranch_code() %></td>
			<td ><%=b.getAd_society_no()+" | "+b.getMember_name() %></td>
			<td ><% if(b.getInstrument_no()!=null){out.print( b.getInstrument_no()); }else{out.print("--");} %></td>
			<td ><% if(b.getNarration()!=null){
				out.println(b.getNarration());
			} %></td>
			<td ><%=b.getVoucher_type()%></td>
			
			
			<% 
			total_deb+=b.getDramt();
			total_cre+=b.getCramt();
			%>
			<td ><%=new BigDecimal(b.getDramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
			<td ><%=new BigDecimal(b.getCramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
			<td ><%=new BigDecimal(Math.abs(total_deb-total_cre)).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
			</tr>
<%//} 
}

vtd=new TransactionDao();
vtb=vtd.getAllVoucherTrxDetailByAccountId(from,to,ad_account_id,ad_member_id,"Approved");
for(VoucherTrxDetailBean b:vtb){
i++;
/* System.out.println(b.getVoucher().getStatus().trim());
if(b.getVoucher().getStatus().trim().equals("Approved")){ */
%>
			<tr style="font-size: 12px; text-align: center;">
			
		
			<td ><%=i %></td>
			<td ><%=new SimpleDateFormat("dd/MM/yyyy").format(b.getTrx_date()) %></td>
			<td ><%=b.getVno() %></td>
			<td ><%=b.getBranch_code() %></td>
			<td ><%=b.getInstrument_no() %></td>
			<td ><%=b.getAd_society_no()+" | "+b.getMember_name() %></td>     
			<td ><% if(b.getNarration()!=null){
				out.println(b.getNarration());
			} %></td>
			<td ><%=b.getVoucher_type()%></td>
			
			
			<% 
			total_deb+=b.getDramt();
			total_cre+=b.getCramt();
			%>
			<td ><%=new BigDecimal(b.getDramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
			<td ><%=new BigDecimal(b.getCramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
			<%
			
			if(total_deb-total_cre>0){
			%>
			<td ><%=new BigDecimal(Math.abs(total_deb-total_cre)).setScale(2, BigDecimal.ROUND_HALF_EVEN) %> Dr.</td>
			<%}else{
				%>
				<td ><%=new BigDecimal(Math.abs(total_deb-total_cre)).setScale(2, BigDecimal.ROUND_HALF_EVEN) %> Cr.</td>
				<%		} %>
			</tr>
<%//} 
}%>	
<tr style=" "><td colspan="8" style="text-align: center;">Total</td><td><%=new BigDecimal(total_deb).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td><td><%=new BigDecimal(total_cre).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td></tr>	
</table>
</div>
