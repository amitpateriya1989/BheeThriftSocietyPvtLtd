<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Dao.AccountTypeDao"%>
<%@page import="Model.Bean.AccountTypeBean"%>
<%@page import="Model.Dao.TransactionDao"%>
<%@page import="Model.Bean.TransactionBean"%>
<%@page import="java.util.ArrayList"%>


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
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />



<body >
<table id="profitloss" width="100%">
<tr>
<td>
<center>Profit & Loss as on Date From <%=new SimpleDateFormat("dd/MM/yyyy").format(fdate) %>&nbsp; To  &nbsp; <%=new SimpleDateFormat("dd/MM/yyyy").format(tdate) %>


</center>
</td>
</tr>
	<tr>
	<td>
<div id=result >
<table border="1" width="100%" cellpadding="0" cellspacing="0">
<tr>
	<th align="right" valign="top">
		<table align="center"  border="1" width="100%" cellpadding="0" cellspacing="0" >
			
			<tr style="background-color:#FC9;color:#039" align="center">
				
                <th>Acc No</th>
                <th>Expences A/c</th>                
                <th>Balance</th>
            </tr>



 <%
double t1=0;
double t2=0;

 

ArrayList<LedgerAccountBean> llist=new LedgerAccountDao().getLedgerAccountType(4);

double total_opniing_bal=0.0;
if(llist.isEmpty()!=true){
	   
	   for(LedgerAccountBean bean:llist){
		   double t_cr=0.00;
			double t_dr=0.00;
		   AccountTypeBean atb= new AccountTypeDao().getAccountTypeById(bean.getAd_ac_type_id());
			ArrayList<TransactionBean> tb1 = new TransactionDao().getLedgerBalBetweendates(bean.getAd_account_id(), fdate ,tdate);
			if(tb1!=null){
	
				double bal=0;
		for(TransactionBean tbean1:tb1){	
		 bal=0;
		if(tbean1.getDramt()!=0.00)
		{
			
				bal=bal-tbean1.getDramt();
		}
		if(tbean1.getCramt()!=0.00)
		{
			
				bal=bal-tbean1.getCramt();
			
		}
		
		 
		 
		 t1=t1+bal; 
	
		 
		 if(bal!=0){
		 %>
		 <tr>
         
            <td><%=bean.getAc_no() %></td>
            <td><%=bean.getAc_name() %></td>
            
            <td align="right"><%
            if(bal<0){
            	out.print(new BigDecimal(Math.abs(Math.abs(bal))).setScale(2, BigDecimal.ROUND_HALF_EVEN) + " Dr");
            }else if(bal>0){
            	out.print(new BigDecimal(Math.abs(Math.abs(bal))).setScale(2, BigDecimal.ROUND_HALF_EVEN)+ " Cr");
            }
           
            
            %></td>
		</tr>
		 <%
		 }
	}

			}
}
}


 %>




	</table> 
</th>
<th align="left" valign="top" >

	<table align="center"  border="1" width="100%" cellpadding="0" cellspacing="0" >
			
			<tr style="background-color:#FC9;color:#039" align="center">
				
                <th>Acc No</th>
                <th>Income A/c</th>
                
                <th>Balance</th>
            </tr>



 <%

ArrayList<LedgerAccountBean> llist1=new LedgerAccountDao().getLedgerAccountType(3);

double total_opniing_bal1=0.0;
if(llist1.isEmpty()!=true){
	   
	   for(LedgerAccountBean bean:llist1){
		   double t_cr=0.00;
			double t_dr=0.00;
		 //  AccountTypeBean atb= new AccountTypeDao().getAccountTypeById(bean.getAd_ac_type_id());

			ArrayList<TransactionBean> tb1 = new TransactionDao().getLedgerBalBetweendates(bean.getAd_account_id(), fdate ,tdate);
			if(tb1!=null){
	
				double bal=0;
	for(TransactionBean tbean1:tb1){	
		 bal=0;
		if(tbean1.getDramt()!=0.00)		{
			
				bal=bal-tbean1.getDramt();
		}
		if(tbean1.getCramt()!=0.00)		{
			
				bal=bal+tbean1.getCramt();
		}
		
		 t_dr=t_dr+tbean1.getDramt();
		 t_cr=t_cr+tbean1.getCramt();
		 
	if(bal!=0){
		 %>
		 <tr>
            
            <td><%=bean.getAc_no() %></td>
            <td><%=bean.getAc_name() %></td>
            
            <td align="right"><%
            if(bal<0){
            	
            	out.print(new BigDecimal(Math.abs(bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+ "Dr");
            }else if(bal>0){
            	out.print(new BigDecimal(Math.abs(bal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+ "Cr");
            	
            }
             %></td>
		</tr>
		 <%
	}
		 t2=t2+bal; 
			
			 
		
	}

			}
}
}



%>



</th></tr>
</table>

 
<tr style="background-color:#999;color:#030;font-size:16px"><th align="right">Total : &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  
  <% if(t1<0){
	  
	 out.print(new BigDecimal(Math.abs(t1)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+" Dr"); 
	  
	  }else if(t1>0){
		  
		  out.print(new BigDecimal(Math.abs(t1)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+" Cr"); 
		  
	  }%> </th><th align="right">Total : &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;    <%
	  
	  if(t2<0){
		  out.print(new BigDecimal(Math.abs(t2)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+ "Dr");
		  
	  }else if(t2>0){
		  out.print(new BigDecimal(Math.abs(t2)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+ "Cr");
		  
	  }
	
	  double tbal =Math.abs(t2)-Math.abs(t1);
	  
	//  System.out.println(tbal);
	  double extotal=0.0;
		double incometotal=0.0;
		
		if(tbal<0){
		//System.out.print("-"+tbal);
			//tbal=Math.abs(t2)-Math.abs(t1);
			incometotal=Math.abs(tbal);
			
		}else if(tbal>0){
			//System.out.print("+"+tbal);
			//tbal=Math.abs(t1)-Math.abs(t2);
			extotal=Math.abs(tbal);
		}
	  %></th></tr>
	   <tr><th align="right">Net Profit &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  <%=new BigDecimal(extotal).setScale(2, BigDecimal.ROUND_HALF_EVEN) %> </th>
	   <th align="right"> Net Loss &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <%=new BigDecimal(incometotal).setScale(2, BigDecimal.ROUND_HALF_EVEN) %>  </th></tr>
		  
	 <tr style="background-color:#999;color:#030;font-size:16px"><th align="right">Gross  Total : &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  
  <%=new BigDecimal(extotal+Math.abs(t1)).setScale(2, BigDecimal.ROUND_HALF_EVEN) %> </th><th align="right">Gross Total : &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;    <%=new BigDecimal(Math.abs(t2)+Math.abs(incometotal)).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></th></tr>
  
</table>

</div><br />
</td>
</tr>
</table>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>

