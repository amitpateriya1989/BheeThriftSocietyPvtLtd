
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
double gtotalLiabilities =0.0;
double gtotalassest=0.0;
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
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Date fdate=sdf.parse(datefrom);
Date tdate=sdf.parse(dateto);

%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link  rel="stylesheet" href="menu/style.css" />
<link  rel="stylesheet" href="css/style_2.css" />
<title></title>
</head>

<style type="text/css">


</style>
<body >
<div id=result  >

<table border="1" width="100%" cellpadding="0" cellspacing="0" >

<tr style="background-color:#BEC9E7;color:#F41F1A">
	<th width="50%" align="center" valign="top">Liabilities Acc</th>
    <th width="50%" align="center" valign="top">Assets Acc</th>
 </tr>
 
 <tr valign="top">
 	<th width="50%" valign="top" >
 		
 		  <table width="100%" cellpadding="0" cellspacing="0">
           <%
           
           ArrayList<LedgerAccountBean> llist=new LedgerAccountDao().getLedgerAccountType(2);
           double total_opniing_bal=0.0;
           if(llist.isEmpty()!=true){
        	   
        	   for(LedgerAccountBean bean:llist){
        		   double t_cr=0.00;
        			double t_dr=0.00;
        		   AccountTypeBean atb= new AccountTypeDao().getAccountTypeById(bean.getAd_ac_type_id());
       			
   				
   				ArrayList<TransactionBean> tb = new TransactionDao().getLedgerOpeningBal(bean.getAd_account_id(), fdate);
   				
   				
   				if(tb!=null){
					double  bal=0;
					 total_opniing_bal=0.0;
					
					for(TransactionBean tbean:tb){	
						
						if(tbean.getDramt()!=0.00)
						{
							if(atb.getName().equals("Assets"))
								bal=bal+tbean.getDramt();
							if(atb.getName().equals("Liabilities"))
								bal=bal-tbean.getDramt();
							if(atb.getName().equals("Expense"))
								bal=bal+tbean.getDramt();
							if(atb.getName().equals("Income"))
								bal=bal-tbean.getDramt();
						}
						if(tbean.getCramt()!=0.00)
						{
							if(atb.getName().equals("Assets"))
								bal=bal-tbean.getCramt();
							if(atb.getName().equals("Liabilities"))
								bal=bal+tbean.getCramt();
							if(atb.getName().equals("Expense"))
								bal=bal-tbean.getCramt();
							if(atb.getName().equals("Income"))
								bal=bal+tbean.getCramt();
						}
						
						total_opniing_bal=total_opniing_bal+bal;
						
					}
					
				}
   				
   				
   				
   				ArrayList<TransactionBean> tb1 = new TransactionDao().getLedgerBalBetweendates(bean.getAd_account_id(), fdate ,tdate);
				if(tb1!=null){
					
				
					for(TransactionBean tbean1:tb1){	
						double bal=0;
						if(tbean1.getDramt()!=0.00)
						{
							if(atb.getName().equals("Assets"))
								bal=bal+tbean1.getDramt();
							if(atb.getName().equals("Liabilities"))
								bal=bal-tbean1.getDramt();
							if(atb.getName().equals("Expense"))
								bal=bal+tbean1.getDramt();
							if(atb.getName().equals("Income"))
								bal=bal-tbean1.getDramt();
						}
						if(tbean1.getCramt()!=0.00)
						{
							if(atb.getName().equals("Assets"))
								bal=bal-tbean1.getCramt();
							if(atb.getName().equals("Liabilities"))
								bal=bal+tbean1.getCramt();
							if(atb.getName().equals("Expense"))
								bal=bal-tbean1.getCramt();
							if(atb.getName().equals("Income"))
								bal=bal+tbean1.getCramt();
						}
						
						 t_dr=t_dr+tbean1.getDramt();
						 t_cr=t_cr+tbean1.getCramt();
						
					}
				
				
				}
   				
				gtotalLiabilities=gtotalLiabilities+total_opniing_bal+(t_cr-t_dr);
   				%>
   			<tr style='height:5px'><td width='60%' style='text-align: left'><%=bean.getAc_name() %></td><td  width='30%' align='right'> <%=new BigDecimal(total_opniing_bal).setScale(2, BigDecimal.ROUND_HALF_EVEN)%>  &nbsp; </td>
   				 <td align='right' width='30%'></td></tr>
				 <tr style='height:5px'><td width='20%' style=''></td><td width='30%' align='right'>  <%=new BigDecimal(t_cr).setScale(2, BigDecimal.ROUND_HALF_EVEN) %> &nbsp; </td> 
				 <td width='30%' align='right'></td></tr>
				 <tr style='height:5px'><td width='20%' style='border-bottom:1px solid gray'></td>
				 <td width='30%' align='right' style='border-bottom:1px solid gray'> - <%=new BigDecimal(t_dr).setScale(2, BigDecimal.ROUND_HALF_EVEN) %> &nbsp;</td> 
				 <td width='30%' align='right' style='border-bottom:1px solid gray'><%=new BigDecimal(total_opniing_bal+(t_cr-t_dr)).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td></tr>
   				
   				
   				<%
   				
   					
   				
        	   }
        	   
           
           }
           
           ArrayList<LedgerAccountBean> llist2=new LedgerAccountDao().getLedgerAccountType(3);
           double total_opniing_bal_income=0.0;
           if(llist2.isEmpty()!=true){
        	   
        	   for(LedgerAccountBean bean:llist2){
        		   double t_cr=0.00;
        			double t_dr=0.00;
        		   AccountTypeBean atb= new AccountTypeDao().getAccountTypeById(bean.getAd_ac_type_id());          		   
   				ArrayList<TransactionBean> tb = new TransactionDao().getLedgerOpeningBal(bean.getAd_account_id(), tdate);
   				
   				
   				if(tb!=null){
					double  bal=0;
					 total_opniing_bal_income=0.0;
					
					for(TransactionBean tbean:tb){	
					
						if(tbean.getDramt()!=0.00)
						{
							   
							if(atb.getName().equals("Assets"))
								bal=bal+tbean.getDramt();
							if(atb.getName().equals("Liabilities"))
								bal=bal-tbean.getDramt();
							if(atb.getName().equals("Expense"))
								bal=bal+tbean.getDramt();
							if(atb.getName().equals("Income"))
								bal=bal-tbean.getDramt();
						}
						if(tbean.getCramt()!=0.00)
						{
							
							if(atb.getName().equals("Assets"))
								bal=bal-tbean.getCramt();
							if(atb.getName().equals("Liabilities"))
								bal=bal+tbean.getCramt();
							if(atb.getName().equals("Expense"))
								bal=bal-tbean.getCramt();
							if(atb.getName().equals("Income"))
								bal=bal+tbean.getCramt();
						}
						
						total_opniing_bal_income=total_opniing_bal_income+bal;
						
					}
					
				}
   				
   				
					}
				
				
				}
				
				
       
   				
   			 ArrayList<LedgerAccountBean> llist3=new LedgerAccountDao().getLedgerAccountType(4);
             double total_opniing_bal_ex=0.0;
             if(llist3.isEmpty()!=true){
          	   
          	   for(LedgerAccountBean bean:llist3){
          		   double t_cr=0.00;
          			double t_dr=0.00;
          		   AccountTypeBean atb= new AccountTypeDao().getAccountTypeById(bean.getAd_ac_type_id());
         			
     				
     				ArrayList<TransactionBean> tb = new TransactionDao().getLedgerOpeningBal(bean.getAd_account_id(), tdate);
     				
     				
     				if(tb!=null){
  					double  bal=0;
  					 total_opniing_bal=0.0;
  					
  					for(TransactionBean tbean:tb){	
  						
  						if(tbean.getDramt()!=0.00)
  						{
  							if(atb.getName().equals("Assets"))
  								bal=bal+tbean.getDramt();
  							if(atb.getName().equals("Liabilities"))
  								bal=bal-tbean.getDramt();
  							if(atb.getName().equals("Expense"))
  								bal=bal+tbean.getDramt();
  							if(atb.getName().equals("Income"))
  								bal=bal-tbean.getDramt();
  						}
  						if(tbean.getCramt()!=0.00)
  						{
  							if(atb.getName().equals("Assets"))
  								bal=bal-tbean.getCramt();
  							if(atb.getName().equals("Liabilities"))
  								bal=bal+tbean.getCramt();
  							if(atb.getName().equals("Expense"))
  								bal=bal-tbean.getCramt();
  							if(atb.getName().equals("Income"))
  								bal=bal+tbean.getCramt();
  						}
  						
  						total_opniing_bal_ex=total_opniing_bal_ex+bal;
  						
  					}
  					
  				}
     				
   				
          	   }
				
             }
   				
            
           if(total_opniing_bal_income-total_opniing_bal_ex!=0){
            	 gtotalLiabilities=gtotalLiabilities+total_opniing_bal_income-(total_opniing_bal_ex);
   				%>
   			    <tr style='border-bottom:1px solid gray'><td width='60%' style='text-align: left'>Net profit</td><td  width='30%' align='right'> </td>
   			     <td align='right' width='30%'><%=new BigDecimal(total_opniing_bal_income-total_opniing_bal_ex).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td></tr>
				<%} %>
   			
 		</table> 		
          </th>
          
          
          
          
        <th width="50%"  valign="top">
        
        <table width="100%" cellpadding="0" cellspacing="0">
           <%
      
           ArrayList<LedgerAccountBean> llist1=new LedgerAccountDao().getLedgerAccountType(1);
           double total_opniing_bal1=0.0;
           if(llist1.isEmpty()!=true){
        	   
        	   for(LedgerAccountBean bean:llist1){
        		   double t_cr=0.00;
        			double t_dr=0.00;
        		   AccountTypeBean atb= new AccountTypeDao().getAccountTypeById(bean.getAd_ac_type_id());
       			
   				
   				ArrayList<TransactionBean> tb = new TransactionDao().getLedgerOpeningBal(bean.getAd_account_id(), fdate);
   				
   				
   				if(tb!=null){
					double  bal=0;
					 total_opniing_bal=0.0;
					
					for(TransactionBean tbean:tb){	
						
						if(tbean.getDramt()!=0.00)
						{
							if(atb.getName().equals("Assets"))
								bal=bal+tbean.getDramt();
							if(atb.getName().equals("Liabilities"))
								bal=bal-tbean.getDramt();
							if(atb.getName().equals("Expense"))
								bal=bal+tbean.getDramt();
							if(atb.getName().equals("Income"))
								bal=bal-tbean.getDramt();
						}
						if(tbean.getCramt()!=0.00)
						{
							if(atb.getName().equals("Assets"))
								bal=bal-tbean.getCramt();
							if(atb.getName().equals("Liabilities"))
								bal=bal+tbean.getCramt();
							if(atb.getName().equals("Expense"))
								bal=bal-tbean.getCramt();
							if(atb.getName().equals("Income"))
								bal=bal+tbean.getCramt();
						}
						
						total_opniing_bal1=total_opniing_bal1+bal;
						
					}
					
				}
   				
   				
   				
   				ArrayList<TransactionBean> tb1 = new TransactionDao().getLedgerBalBetweendates(bean.getAd_account_id(), fdate ,tdate);
				if(tb1!=null){
					
				
					for(TransactionBean tbean1:tb1){	
						double bal=0;
						if(tbean1.getDramt()!=0.00)
						{
							if(atb.getName().equals("Assets"))
								bal=bal+tbean1.getDramt();
							if(atb.getName().equals("Liabilities"))
								bal=bal-tbean1.getDramt();
							if(atb.getName().equals("Expense"))
								bal=bal+tbean1.getDramt();
							if(atb.getName().equals("Income"))
								bal=bal-tbean1.getDramt();
						}
						if(tbean1.getCramt()!=0.00)
						{
							if(atb.getName().equals("Assets"))
								bal=bal-tbean1.getCramt();
							if(atb.getName().equals("Liabilities"))
								bal=bal+tbean1.getCramt();
							if(atb.getName().equals("Expense"))
								bal=bal-tbean1.getCramt();
							if(atb.getName().equals("Income"))
								bal=bal+tbean1.getCramt();
						}
						
						 t_dr=t_dr+tbean1.getDramt();
						 t_cr=t_cr+tbean1.getCramt();
						
					}
				
				
				}
        	   
   				gtotalassest=gtotalassest+ (total_opniing_bal1+(t_dr-t_cr));
   				%>
   			<tr style='height:5px'><td width='60%' style='text-align: left'><%=bean.getAc_name() %></td><td  width='30%' align='right'> <%=new BigDecimal(total_opniing_bal).setScale(2, BigDecimal.ROUND_HALF_EVEN) %>  &nbsp; </td>
   			 <td align='right' width='30%'></td></tr>
				 <tr style='height:5px'><td width='20%' style=''></td><td width='30%' align='right'>  <%=new BigDecimal(t_dr).setScale(2, BigDecimal.ROUND_HALF_EVEN) %> &nbsp; </td> 
				 <td width='30%' align='right'></td></tr>
				 <tr style='height:5px'><td width='20%' style='border-bottom:1px solid gray'></td>
				 <td width='30%' align='right' style='border-bottom:1px solid gray'> - <%=new BigDecimal(t_cr).setScale(2, BigDecimal.ROUND_HALF_EVEN) %> &nbsp;</td> 
				 <td width='30%' align='right' style='border-bottom:1px solid gray'><%=new BigDecimal(total_opniing_bal1+(t_dr-t_cr)).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td></tr>
   				
   				
   				<%
   				
   					
   				
        	   }
        	   
           
           }
           %> 
    </table>    
          
	 </th>
 </tr>   

 <tr >
 <th>
 	<table width="100%">
    <tr>
    <th align="left" width="50%"> GRAND TOTAL
 
 	</th>
    <th align="right" width="50%"><%=new BigDecimal(gtotalLiabilities).setScale(2, BigDecimal.ROUND_HALF_EVEN) %></th>
 
    </tr>
    </table>
</th>
<th>
	<table width="100%">
    <tr>
    <th align="left" width="50%"> 
 	</th>
    <th align="right" width="50%">
    <%=new BigDecimal(gtotalassest).setScale(2, BigDecimal.ROUND_HALF_EVEN) %>
 	</th>
    
    </tr>
    </table> 
     
     
 </th>	
</tr>
   
 </table>

</div><br />
</div>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>


