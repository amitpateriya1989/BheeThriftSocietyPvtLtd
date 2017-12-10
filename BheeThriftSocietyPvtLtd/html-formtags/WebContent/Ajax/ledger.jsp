<%@page import="java.math.BigDecimal"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Bean.VoucherBean"%>
<%@page import="Model.Dao.VoucherDao"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Bean.TransactionBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="Model.Dao.TransactionDao"%>

<%
String str_from=request.getParameter("from");
String str_to=request.getParameter("to");
int ad_account_id=Integer.parseInt(request.getParameter("ad_account_id"));
int ad_member_id=Integer.parseInt(request.getParameter("ad_member_id"));
DateFormat formatter ; 
Date from =null; 
Date to =null; 
formatter = new SimpleDateFormat("yyyy-MM-dd");
try {
from = formatter.parse(str_from);
to = formatter.parse(str_to);
} catch (ParseException e) {
	e.printStackTrace();
}

//TransactionDao vtd=new TransactionDao();

Double deb_amount=0.0,cre_amount=0.0,opening_balance=0.0, balance=0.0;
LedgerAccountBean lbean = new LedgerAccountDao().getLedgerAccountById(ad_account_id);
String member="";
if(ad_member_id!=0){
	MemberRegistrationBean mrbean = new MemberRegistrationDao().getMemberName(ad_member_id);
	member="[ "+ mrbean.getAd_society_no()+" "+ mrbean.getName()+" ]";
}
%>
<table id="ledger" width="100%">
<tr>
<td>

<center>
Ledger FNo :<%=lbean.getAc_no() %>,<%=lbean.getAc_name() + member %></center><center> As On Date From   <%=new SimpleDateFormat("dd-MM-yyyy").format(from.getTime()) %> To 
<%=new SimpleDateFormat("dd-MM-yyyy").format(to.getTime()) %>
</center>
</td>
</tr>
<tr>
<td>
<table border="0" cellspacing="0" cellpadding="0" 
style="border-radius:0px; margin-top: 0px; border: 0px solid black; width: 100%; text-align: left;background-color: white;"
align="center">
<tr><td colspan="6"><hr></td></tr>

<td colspan="6">
<table border="1" cellspacing="0" width="100%" style="border:1px solid black; margin-top:0px; font-family:'Times New Roman', Times, serif;" align="center">
<tr style="background-color: #06C; color: white; text-align: center; font-size: 14px;">
<th>Trx Date</th>
<th>Acc/No</th>
<th>Particular/Narration</th>
<th>Cheque No.</th>
<th>Dr.</th>
<th>Cr.</th>
<th>Balance</th>
<th>Mkr</th>
<th>Ckr</th>
</tr>

<%
if(lbean.getAd_ac_type_id()==1 || lbean.getAd_ac_type_id()==2){
TransactionBean opentrxbean =new TransactionDao().getAllLedgerTransactionBefourFromDate(ad_account_id, from, ad_member_id);

deb_amount+=opentrxbean.getDramt();
cre_amount+=opentrxbean.getCramt();



//assets
 if(lbean.getAd_ac_type_id()==1){
	balance=(deb_amount+opening_balance)-cre_amount;
	//balance=(deb_amount+opening_balance);
}
//liablities
else if(lbean.getAd_ac_type_id()==2){
	balance=(cre_amount+opening_balance)-deb_amount;
}
//income
else if(lbean.getAd_ac_type_id()==3){
	balance=cre_amount-deb_amount;
}
//expenses
else if(lbean.getAd_ac_type_id()==4){
	balance=deb_amount-cre_amount;
}
 
}
%>
<tr>
<td><%=new SimpleDateFormat("dd-MM-yyyy").format(from.getTime()) %></td>
<td></td>
<td>Opening Balance</td>
<td></td>
<td></td>
<td></td>
<td align="right">
<%
double bal=00;
String type="";
if(balance==0){
	type="";
}else if(balance<0){
	bal=Math.abs(balance);
	type="(Cr)";
}else if(balance>0){
	bal=Math.abs(balance);
	type="(Dr)";
}
out.print(new BigDecimal(bal).setScale(2, BigDecimal.ROUND_HALF_EVEN) +" "+type);
%>


</td>
<td></td>
<td></td>
</tr>





<%
double tempdramt=0.00;
double tempcramt=0.00;

ArrayList<TransactionBean> vtb=new TransactionDao().getAllLedgerTransactionByFromToDate(ad_account_id, from, to,ad_member_id );
int i=0;
for(TransactionBean b:vtb){
	i++;
	deb_amount+=b.getDramt();
	cre_amount+=b.getCramt();
	tempdramt+=b.getDramt();
	 tempcramt+=b.getCramt();
	
	 //assets
	if(vtb.get(0).getLedger().getAd_ac_type_id()==1){
		balance=(deb_amount+opening_balance)-cre_amount;
		//balance=(deb_amount+opening_balance);
	}
	//liablities
	else if(vtb.get(0).getLedger().getAd_ac_type_id()==2){
		balance=(cre_amount+opening_balance)-deb_amount;
		//balance=(cre_amount+opening_balance);
	}
	//income
	else if(vtb.get(0).getLedger().getAd_ac_type_id()==3){
		balance=cre_amount-deb_amount;
	}
	//expenses
	else if(vtb.get(0).getLedger().getAd_ac_type_id()==4){
		balance=deb_amount-cre_amount;
	}
	 
//	balance=opening_balance+deb_amount-cre_amount;
%>
<tr style="font-size: 12px; text-align: center;">

<td><%=new SimpleDateFormat("dd-MM-yyyy").format(b.getTrx_date().getTime()) %></td>
<td> <% 	
//out.print(b.getAd_voucher_id());
if(b.getAd_voucher_id()!=1){
ArrayList<TransactionBean> trbn = new TransactionDao().getTransactionByVoucherId(b.getAd_voucher_id(), b.getLedger().getAd_account_id());

if(trbn!=null){
	
	for(TransactionBean tb:trbn){
		
	
	out.print(tb.getLedger().getAc_name());
	if(tb.getMember().getName()!=null){
		out.print("["+tb.getMember().getName()+"]" );
	}
	
	}
}}else{
	out.print("S/w Opening");
}

%> </td>
<td></td>
<td><% if(b.getNarration()!=null){out.print(b.getNarration());} %></td>
<td style="text-align: right;"><%=new BigDecimal(b.getDramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
<td style="text-align: right;"><%=new BigDecimal(b.getCramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
<%
 type="";
if(balance==0){
	type="";
	bal=00;
}else if(balance<0){
	bal=Math.abs(balance);
	type="Cr";
}else if(balance>0){
	type="Dr";	
	bal=balance;

}

//System.out.println("balance="+balance);
%>
<td style="text-align: right;"><%=new BigDecimal(bal).setScale(2, BigDecimal.ROUND_HALF_EVEN) %>(<%=type %>)</td>
<td></td>
<td></td>
</tr>
<%}

%>
<tr style="text-align: right;color: black;font-weight: bold; ">
<td colspan="4">Total</td>
<td><%=new BigDecimal(tempdramt).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
<td><%=new BigDecimal(tempcramt).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
<%
 type="";

if(balance==0){
	type="";
	bal=00;
}else if(balance<0){
	bal=Math.abs(balance);
	type="(Cr)";
} else if(balance>0){
	bal=balance;
	type="(Dr)";
	
}
//System.out.println("balance="+balance);
	%>

<td><%=new BigDecimal(bal).setScale(2, BigDecimal.ROUND_HALF_EVEN)%> <%=type %></td>
<td></td>
<td></td>

</tr>

</table>
</td>
</tr>
</table>


