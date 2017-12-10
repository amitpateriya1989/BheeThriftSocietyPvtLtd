<%@page import="Model.Bean.VoucherTrxDetailBean"%>
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
System.out.println(request.getParameter("from"));
String str_from=request.getParameter("from");
String str_to=request.getParameter("to");
int ad_account_id=Integer.parseInt(request.getParameter("ad_account_id"));
int ad_member_id=Integer.parseInt(request.getParameter("ad_member_id"));
DateFormat formatter ; 
Date from =null; 
Date to =null; 
formatter = new SimpleDateFormat("dd/MM/yyyy");
int i=0;
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
<table id="ledger" name="ledger"  style="font-size: 14px;font-weight: bold;">
<thead>
<tr>
<td>


Ledger FNo :</td><td><%=lbean.getAc_no() %></td> <td>Account :</td><td><%=lbean.getAc_name() + member %></td>
</tr>
<tr>
 <td>As On Date From :</td><td>  <%=new SimpleDateFormat("dd/MM/yyyy").format(from.getTime()) %></td><td> To :</td>
 <td> 
<%=new SimpleDateFormat("dd/MM/yyyy").format(to.getTime()) %>

</td>
</tr>
</thead>
</table>

<table>
<thead>
<tr>


<th>Sno.</th>
<th>Trx Date</th>
<th>AC.No</th>
<th>Particular</th>
<th>Party Name</th>
<th>Instrument No.</th>
<th>Dr.</th>
<th>Cr.</th>
<th>Balance</th>
<th>Mkr</th>
<th>Ckr</th>
</tr>
</thead>
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
<tbody>
<tr>
<td><%=++i %></td>
<td><%=new SimpleDateFormat("dd/MM/yyyy").format(from.getTime()) %></td>
<td><%=lbean.getAc_no() %></td>
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
	type="";
}else if(balance>0){
	bal=Math.abs(balance);
	type="";
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
<tr style="font-size: 14px; text-align: center;">
<td><%=i %></td>
<td><%=new SimpleDateFormat("dd/MM/yyyy").format(b.getTrx_date().getTime()) %></td>
<td><%=b.getLedger().getAc_no()  %></td>
<td> <% 	
//out.print(b.getAd_voucher_id());
if(b.getAd_voucher_id()!=1){
//ArrayList<TransactionBean> trbn = new TransactionDao().getTransactionByVoucherId(b.getAd_voucher_id(), b.getLedger().getAd_account_id());
ArrayList<VoucherTrxDetailBean> trbn=new TransactionDao().getAllVoucherTrxDetailByCash(from,to);
if(trbn.isEmpty()!=true){
	
	for(VoucherTrxDetailBean tb:trbn){
		
	
	out.print(tb.getAc_name());
	if(tb.getMember_name()!=null){
		out.print("["+tb.getMember_name()+"]" );
	}
	
	}
}}else{
	if(b.getNarration()!=null){out.print(b.getNarration());}
}

%> </td>

<td></td>
<td style="text-align: right;"><%=new BigDecimal(b.getDramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
<td style="text-align: right;"><%=new BigDecimal(b.getCramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
<%
 type="";
if(balance==0){
	type="";
	bal=00;
}else if(balance<0){
	bal=Math.abs(balance);
	type="";
}else if(balance>0){
	type="";	
	bal=balance;

}

//System.out.println("balance="+balance);
%>
<td style="text-align: right;"><%=new BigDecimal(bal).setScale(2, BigDecimal.ROUND_HALF_EVEN) %><%=type %></td>
<td></td>
<td></td>
</tr>
<%}

%>
<tr style="text-align: right;color: black;font-weight: bold; ">
<td colspan="5">Total</td>
<td><%=new BigDecimal(tempdramt).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
<td><%=new BigDecimal(tempcramt).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
<%
 type="";

if(balance==0){
	type="";
	bal=00;
}else if(balance<0){
	bal=Math.abs(balance);
	type="";
} else if(balance>0){
	bal=balance;
	type="";
	
}
//System.out.println("balance="+balance);
	%>

<td><%=new BigDecimal(bal).setScale(2, BigDecimal.ROUND_HALF_EVEN)%> <%=type %></td>
<td></td>
<td></td>

</tr>
</tbody>
</table>


