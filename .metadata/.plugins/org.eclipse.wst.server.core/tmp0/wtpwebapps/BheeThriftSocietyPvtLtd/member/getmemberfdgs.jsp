<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Member.Bean.MemberLoginBean"%>
<%@page import="Model.Dao.TransactionDao"%>
<%@page import="Model.Bean.TransactionBean"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String fdate=request.getParameter("fdate");
String tdate=request.getParameter("tdate");
DateFormat formatter ; 
Date from =null; 
Date to =null; 
formatter = new SimpleDateFormat("yyyy-MM-dd");
try {
from = formatter.parse(fdate);
to = formatter.parse(tdate);
} catch (ParseException e) {
	e.printStackTrace();
}
HttpSession session1=request.getSession(false);
MemberLoginBean user1=null;
user1=(MemberLoginBean)session1.getAttribute("MemberLoginBean");
TransactionBean opentrxbean =new TransactionDao().getAllLedgerTransactionBefourFromDate(83, from, user1.getMember().getAd_member_id());
ArrayList<TransactionBean> list=new TransactionDao().getAllLedgerTransactionByFromToDate(83, from, to,user1.getMember().getAd_member_id());
%>
<table id="fdgs" border="1" cellspacing="0" width="100%" style="border:1px solid black; margin-top:0px; font-family:'Times New Roman', Times, serif;" align="center">
<tr style="background-color: #06C; color: white; text-align: center; font-size: 14px;">
<th>Trx Date</th>
<th>Particular/Narration</th>
<th>Cheque No.</th>
<th>Dramt</th>
<th>Cramt</th>

<th>Balance</th>
</tr>
<tr>
<td><%=new SimpleDateFormat("dd-MM-yyyy").format(from.getTime()) %></td>
<td>Opening Balance</td>
<td></td><td></td><td></td>

<td align="right">
<%
out.print(new BigDecimal(opentrxbean.getCramt()-opentrxbean.getDramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) );
%>
</td>
</tr>
<%
double balance=opentrxbean.getCramt()-opentrxbean.getDramt();

if(list!=null){
	for(TransactionBean tb:list){
		
		balance=balance+(tb.getCramt()-tb.getDramt());
		%>
		<tr style="font-size: 12px; text-align: center;">

<td><%=new SimpleDateFormat("dd-MM-yyyy").format(tb.getTrx_date().getTime()) %></td>
		
<td><% if(tb.getNarration()!=null){out.print(tb.getNarration());} %></td>		
<td></td>
<td style="text-align: right;"><%=new BigDecimal(tb.getDramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
<td style="text-align: right;"><%=new BigDecimal(tb.getCramt()).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>	
<td style="text-align: right;"><%=new BigDecimal(balance).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>	</tr>
		<%
		
		
	}
}

%>
