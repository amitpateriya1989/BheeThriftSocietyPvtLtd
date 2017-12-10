

<%@page import="java.math.BigDecimal"%>
<%@page import="Model.Dao.TransactionDao"%>
<%@page import="Model.Bean.TransactionBean"%>
<%@page import="Model.Dao.AccountTypeDao"%>
<%@page import="Model.Bean.AccountTypeBean"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.LedgerAccountDao"%>

<%
try{
String datefrom =request.getParameter("fdate");
String dateto = request.getParameter("tdate");
if(datefrom==""){
	out.print("<script>alert('please Select from date..... ')</script>");
	return;
}
if(dateto==""){
	out.print("<script>alert('please Select To date..... ')</script>");
	return;
}
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
Date fdate=sdf.parse(datefrom);
Date tdate=sdf.parse(dateto);

%>
<table id="trial_balance" width="100%">
<tr>
<td>
<center>
Trail Balance as on Date From <%=new SimpleDateFormat("dd/MM/yyyy").format(fdate) %>&nbsp; To  &nbsp; <%=new SimpleDateFormat("dd/MM/yyyy").format(tdate) %></center>
</td>
</tr>
<tr>
<td>
<table border="1" cellpadding="5" cellspacing="0"	style="width:100%; " align="center">


<tr valign="top">
<!-- <th>S.No</th> -->
<th>Acc No</th>
<th>Acc Name</th>
<th>Opening Balance</th> 
<th>Dabit(Dr.)</th>
<th>Credit(Cr.)</th>
<th> Closing Balance</th> 
</tr>

<%
int count=1;
double g_opn_bal=0;
double g_t_dr=0;
double g_t_cr=0;
double g_cl_bal=0;

double g_closing_bal=0.0;

ArrayList<AccountTypeBean> atlist = new AccountTypeDao().getAllAccountType();
if(atlist!=null){
	for(AccountTypeBean atb:atlist){
		
	
	out.print("<tr><td colspan=6>"+ atb.getName()+"</td></tr>");

LedgerAccountDao ldao=new LedgerAccountDao();
ArrayList<LedgerAccountBean> llist=ldao.getLedgerAccountType(atb.getAd_ac_type_id());
if(llist!=null){
	double total_opniing_bal=0;
	double total_closing_bal=0.0;
	for(LedgerAccountBean bean:llist){		
		double t_cr=0.00;
		double t_dr=0.00;
		double  bal=0;
		 total_opniing_bal=0.0;
		 total_closing_bal=0.0;
		//first get opening balance for each ac
		if(bean.getAd_ac_type_id()==1 || bean.getAd_ac_type_id()==2){
						
				
				ArrayList<TransactionBean> tb = new TransactionDao().getLedgerOpeningBal(bean.getAd_account_id(), fdate);
				
				
				if(tb!=null){
					
					
					
					
					for(TransactionBean tbean:tb){							
						 bal=0;
						if(tbean.getDramt()!=0.00)
						{
							
							if(bean.getAd_ac_type_id()==1)
								bal=bal+tbean.getDramt();
							else if(bean.getAd_ac_type_id()==2)
								bal=bal-tbean.getDramt();
							else if(bean.getAd_ac_type_id()==4)
								bal=bal+tbean.getDramt();
							else if(bean.getAd_ac_type_id()==3)
								bal=bal-tbean.getDramt();
						}
						if(tbean.getCramt()!=0.00)
						{
							if(bean.getAd_ac_type_id()==1)
								bal=bal-tbean.getCramt();
							else if(bean.getAd_ac_type_id()==2)
								bal=bal+tbean.getCramt();
							else if(bean.getAd_ac_type_id()==4)
								bal=bal-tbean.getCramt();
							else if(bean.getAd_ac_type_id()==3)
								bal=bal+tbean.getCramt();
						}
						
						total_opniing_bal=total_opniing_bal+bal;
						
					}
					
				}
		}				
					
					ArrayList<TransactionBean> tb1 = new TransactionDao().getLedgerBalBetweendates(bean.getAd_account_id(), fdate ,tdate);
					if(tb1!=null){
						
					
						for(TransactionBean tbean1:tb1){	
							 bal=0;
							if(tbean1.getDramt()!=0.00)
							{
								if(bean.getAd_ac_type_id()==1)
									bal=bal+tbean1.getDramt();
								else if(bean.getAd_ac_type_id()==2)
									bal=bal-tbean1.getDramt();
								else if(bean.getAd_ac_type_id()==4)
									bal=bal+tbean1.getDramt();
								else if(bean.getAd_ac_type_id()==3)
									bal=bal-tbean1.getDramt();
							}
							if(tbean1.getCramt()!=0.00)
							{
								if(bean.getAd_ac_type_id()==1)
									bal=bal-tbean1.getCramt();
								else if(bean.getAd_ac_type_id()==2)
									bal=bal+tbean1.getCramt();
								else if(bean.getAd_ac_type_id()==4)
									bal=bal-tbean1.getCramt();
								else if(bean.getAd_ac_type_id()==3)
									bal=bal+tbean1.getCramt();
							}
							
							
							if(bal>0){
								bal=Math.abs(bal);
								if(bean.getAd_ac_type_id()==1){
									 t_cr=0;
									 t_dr=bal;
								}else if(bean.getAd_ac_type_id()==2){
									 t_cr=bal;
									 t_dr=0;
								}else if(bean.getAd_ac_type_id()==4){
									 t_cr=0;
									 t_dr=bal;
								}else if(bean.getAd_ac_type_id()==3){
									 t_cr=bal;
									 t_dr=0;
								}
								
							}else if(bal<0){
								bal=Math.abs(bal);
								if(bean.getAd_ac_type_id()==1){
									t_dr=0;
									t_cr=bal;
								}else if(bean.getAd_ac_type_id()==2){
									 t_cr=0;
									 t_dr=bal;
								}else if(bean.getAd_ac_type_id()==4){
									 t_cr=bal;
									 t_dr=0;
								}else if(bean.getAd_ac_type_id()==3){
									 t_cr=0;
									 t_dr=bal;
								}
								
							}else{
								
								t_dr=0;
								t_cr=0;
							}
							
							
							
						}
					
					
				}
					
					total_closing_bal=total_opniing_bal+t_dr-t_cr;
					
					g_opn_bal=g_opn_bal+Math.abs(total_opniing_bal);
					g_t_dr=g_t_dr+t_dr;
					g_t_cr = g_t_cr+t_cr;
					g_closing_bal= g_closing_bal+Math.abs(total_closing_bal);
				
					if(total_opniing_bal!=0 || t_dr!=0 || t_cr!=0) {
						
						out.print("<tr><td>"+bean.getAc_no()+"</td><td>"+bean.getAc_name()+"</td>");
						
						String type="";
						if(bean.getAd_ac_type_id()==1){
							if(total_opniing_bal>0){
								out.print("<td align=right>"+new BigDecimal(Math.abs(total_opniing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Dr </td>");
							}else if(total_opniing_bal<0){
								out.print("<td align=right>"+new BigDecimal(Math.abs(total_opniing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Cr </td>");
							}else if(total_opniing_bal==0.0){
								out.print("<td align=right>"+new BigDecimal(Math.abs(total_opniing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"</td>");
							}
							out.print("<td align=right>"+new BigDecimal(t_dr).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"</td><td align=right>"+new BigDecimal(t_cr).setScale(2, BigDecimal.ROUND_HALF_EVEN)  +"</td> <td align=right>");
							
							if(total_closing_bal>0){
								out.print(new BigDecimal(total_closing_bal).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Dr"); 
							}else if(total_closing_bal<0){
								out.print(new BigDecimal(Math.abs(total_closing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Cr"); 
							}else if(total_closing_bal==0.0){
								out.print(new BigDecimal(Math.abs(total_closing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) ); 
							}
							
						}else if(bean.getAd_ac_type_id()==2){
							
							
							if(total_opniing_bal>0){
								out.print("<td align=right>"+new BigDecimal(Math.abs(total_opniing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Cr </td>");
							}else if(total_opniing_bal<0){
								out.print("<td align=right>"+new BigDecimal(Math.abs(total_opniing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Dr </td>");
							}else if(total_opniing_bal==0.0){
								out.print("<td align=right>"+new BigDecimal(Math.abs(total_opniing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"</td>");
							}
							out.print("<td align=right>"+new BigDecimal(t_dr).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"</td><td align=right>"+new BigDecimal(t_cr).setScale(2, BigDecimal.ROUND_HALF_EVEN)  +"</td> <td align=right>");
							
							if(total_closing_bal>0){
								out.print(new BigDecimal(total_closing_bal).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Cr"); 
							}else if(total_closing_bal<0){
								out.print(new BigDecimal(Math.abs(total_closing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Dr"); 
							}else if(total_closing_bal==0.0){
								out.print(new BigDecimal(Math.abs(total_closing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) ); 
							}
							
						}else if(bean.getAd_ac_type_id()==4){
							if(total_opniing_bal>0){
								out.print("<td align=right>"+new BigDecimal(Math.abs(total_opniing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Cr </td>");
							}else if(total_opniing_bal<0){
								out.print("<td align=right>"+new BigDecimal(Math.abs(total_opniing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Dr </td>");
							}else if(total_opniing_bal==0.0){
								out.print("<td align=right>"+new BigDecimal(Math.abs(total_opniing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"</td>");
							}
							out.print("<td align=right>"+new BigDecimal(t_dr).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"</td><td align=right>"+new BigDecimal(t_cr).setScale(2, BigDecimal.ROUND_HALF_EVEN)  +"</td> <td align=right>");
							
							if(total_closing_bal>0){
								out.print(new BigDecimal(total_closing_bal).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Cr"); 
							}else if(total_closing_bal<0){
								out.print(new BigDecimal(Math.abs(total_closing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Dr"); 
							}else if(total_closing_bal==0.0){
								out.print(new BigDecimal(Math.abs(total_closing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) ); 
							}
							
						}else if(bean.getAd_ac_type_id()==3){
							if(total_opniing_bal>0){
								out.print("<td align=right>"+new BigDecimal(Math.abs(total_opniing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Cr </td>");
							}else if(total_opniing_bal<0){
								out.print("<td align=right>"+new BigDecimal(Math.abs(total_opniing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Dr </td>");
							}else if(total_opniing_bal==0.0){
								out.print("<td align=right>"+new BigDecimal(Math.abs(total_opniing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"</td>");
							}
							out.print("<td align=right>"+new BigDecimal(t_dr).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"</td><td align=right>"+new BigDecimal(t_cr).setScale(2, BigDecimal.ROUND_HALF_EVEN)  +"</td> <td align=right>");
							
							if(total_closing_bal>0){
								out.print(new BigDecimal(total_closing_bal).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Cr"); 
							}else if(total_closing_bal<0){
								out.print(new BigDecimal(Math.abs(total_closing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Dr"); 
							}else if(total_closing_bal==0.0){
								out.print(new BigDecimal(Math.abs(total_closing_bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) ); 
							}
							
						}
						
						out.print("</td> </tr>");
	}}
}}

}
%>
<tr style="color: #0000FF; ; background-color: #CEF6F5;">
<td colspan="2" style="text-align: right">Total</td> <td>  <%=new BigDecimal(g_opn_bal).setScale(2, BigDecimal.ROUND_HALF_EVEN) %>  </td> <td><%=new BigDecimal(g_t_dr).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td>
		<td><%=new BigDecimal(g_t_cr).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td> <td><%=new BigDecimal(g_closing_bal).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td> 
</tr>

</table>
</td>
</tr>
</table>
<%}catch(Exception e){
	e.printStackTrace();
} %>