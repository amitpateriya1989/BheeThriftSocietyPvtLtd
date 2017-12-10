<%@page import="Model.Dao.AccountSubGroupDao"%>
<%@page import="Model.Bean.AccountSubGroupBean"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="Model.Dao.AccountGroupDao"%>
<%@page import="Model.Bean.AccountGroupBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Dao.TransactionDao"%>
<%@page import="Model.Bean.TransactionBean"%>
<%@page import="Model.Dao.AccountTypeDao"%>
<%@page import="Model.Bean.AccountTypeBean"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<table class="table table-bordered" id="balance_sheet" width="100%">
<tr>
<td>

<center>
Balance Sheet As on Date  From  <%=new SimpleDateFormat("dd/MM/yyyy").format(fdate) %>&nbsp; To &nbsp; <%=new SimpleDateFormat("dd/MM/yyyy").format(tdate) %>
</center>
</td>
</tr>
<tr>
<td>
<table class="table table-bordered" width="100%" >

<tr valign="top">
<td width="50%">
<table class="table table-bordered" width="100%">
		<tr>
		<th width="12%">Sch No</th>
		<th width="58%">Liabilities</th>
		<th width="30%">Balance</th>
		
		</tr>
		</table>
 <%


double t1=0;
double t2=0;
int Scheduleno=0;


TreeMap group = new TreeMap();

 
// expenses ac
		  
		
			ArrayList<TransactionBean> tb1 = new TransactionDao().getLedgerBalByTypeBetweendates(4, fdate, tdate);
			
			if(tb1!=null){	
				double bal=0;
				for(TransactionBean tbean1:tb1){	
					
						if(tbean1.getDramt()!=0.00)
						{
							
								bal=bal-tbean1.getDramt();
						}
						if(tbean1.getCramt()!=0.00)
						{
							
								bal=bal-tbean1.getCramt();
							
						}	
		 			t1=t1+bal; 
		 		}

		}
	

//income
		  
			ArrayList<TransactionBean> tb2 = new TransactionDao().getLedgerBalByTypeBetweendates(3, fdate, tdate);
			if(tb2!=null){
	
				double bal=0;
				for(TransactionBean tbean1:tb2){	
					
					if(tbean1.getDramt()!=0.00)		{
				
						bal=bal-tbean1.getDramt();
					}
					if(tbean1.getCramt()!=0.00)		{
				
						bal=bal+tbean1.getCramt();
					}
			
			
					 t2=t2+bal; 
				}

			}


//------------calculate net profit -----------

double totalnetprofit=Math.abs(t2)-Math.abs(t1);
double netprofit=0.0;
double netloss=0.0;
if(totalnetprofit>0){
	netprofit=totalnetprofit;
}else if(totalnetprofit<0){
	netloss=Math.abs(totalnetprofit);
}

//out.print(netprofit);
//out.print(netloss);

double totalliablities=0.0;
double totalassets=0.0;
double totalex=0.0;
double totalincome=0.0;


ArrayList<AccountGroupBean> aglist = new AccountGroupDao().getAllAccountGroupByType(2);

if(aglist!=null){
	
	for(AccountGroupBean agb:aglist){
		
		double totalgroup=0.0;
		
				//opening amt
					double bal=0.0;
				
						ArrayList<TransactionBean> tblist = new TransactionDao().getLedgerOpeningBalByGroup(agb.getAd_ac_group_id(), fdate);
						if(tblist!=null){
							
							for(TransactionBean tb:tblist){
								
								if(tb.getDramt()!=0.00){									
										bal=bal-tb.getDramt();									
								}
								if(tb.getCramt()!=0.00){									
										bal=bal+tb.getCramt();									
								}
							}
							
							
						}
						
										
				
				//corrent amt
				
				
						ArrayList<TransactionBean> tblist1 = new TransactionDao().getLedgerBalByGroupBetweendates(agb.getAd_ac_group_id(), fdate ,tdate);
						if(tblist1!=null){						
							
							for(TransactionBean tb3:tblist1){	
								
								if(tb3.getDramt()!=0.00){						
										bal=bal-tb3.getDramt();									
								}
								if(tb3.getCramt()!=0.00){									
										bal=bal+tb3.getCramt();									
								}				
								
								
							}						
						
						}				
				
						totalgroup+=bal;					
			
						
		String type="";
		double	gbal=0.0;
			if(totalgroup>0){
				type="Cr";
				gbal=totalgroup;
			}else if(totalgroup<0){
				gbal=Math.abs(totalgroup);
				type="Dr";
			}
			
			totalliablities=totalliablities+totalgroup;
			
			group.put(++Scheduleno , agb.getAd_ac_group_id() );
		%>
		<table class="table table-bordered" width="100%">
		<tr>
		<td width="12%"><%=Scheduleno %></td>
		<td width="58%"><%=agb.getName() %></td>
		<td width="30%" align="right"><%=new BigDecimal(gbal).setScale(2, BigDecimal.ROUND_HALF_EVEN) + type %></td>
		</tr>			
		</table>
				
	<%
	}
	
}
		if(netprofit!=0){
			totalliablities=totalliablities+netprofit;
			%>
			
			<table class="table table-bordered" width="100%">
		<tr>
		<td width="12%"></td>
		<td width="58%">Net Profit</td>
		<td width="30%" align="right"><%=new BigDecimal(netprofit).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Cr" %></td>
		</tr>			
		</table>
		<%	
		}
		
		
		
		%>   
	
	
	
		
</td>
<td>
<table class="table table-bordered" width="100%">
		<tr>
		<th width="12%">Sch No</th>
		<th width="58%">Assets</th>
		<th width="30%">Balance</th>
		
		</tr>
		</table>

<%
ArrayList<AccountGroupBean> aglist1 = new AccountGroupDao().getAllAccountGroupByType(1);

if(aglist1!=null){
	
	for(AccountGroupBean agb:aglist1){
		
		double totalgroup=0.0;
		
				
			
				//opening amt
					double bal=0.0;
				
						ArrayList<TransactionBean> tblist = new TransactionDao().getLedgerOpeningBalByGroup(agb.getAd_ac_group_id(), fdate);
						if(tblist!=null){
							
							for(TransactionBean tb:tblist){
								
								if(tb.getDramt()!=0.00){									
									bal=bal+tb.getDramt();								
								}
								if(tb.getCramt()!=0.00){									
									bal=bal-tb.getCramt();							
								}
							}
							
							
						}
						
										
				
				//corrent amt
				
				
						ArrayList<TransactionBean> tblist1 = new TransactionDao().getLedgerBalByGroupBetweendates(agb.getAd_ac_group_id(), fdate ,tdate);
						if(tblist1!=null){						
							
							for(TransactionBean tb4:tblist1){	
								
								if(tb4.getDramt()!=0.00){						
									bal=bal+tb4.getDramt();								
								}
								if(tb4.getCramt()!=0.00){									
									bal=bal-tb4.getCramt();								
								}				
								
								
							}						
						
						}				
				
						totalgroup+=bal;					
			
		
		String type="";
	double	gbal=0.0;
		if(totalgroup>0){
			type="Dr";
			gbal=totalgroup;
		}else if(totalgroup<0){
			gbal=Math.abs(totalgroup);
			type="Cr";
		}
		
		totalassets=totalassets+totalgroup;
		
		group.put(++Scheduleno , agb.getAd_ac_group_id());
		%>
		<table class="table table-bordered" width="100%">
		<tr>
		<td width="12%"><%=Scheduleno %></td>
		<td width="58%"><%=agb.getName() %></td>
		<td width="30%" align="right"><%=new BigDecimal(gbal).setScale(2, BigDecimal.ROUND_HALF_EVEN) +type%></td>
		</tr>			
		</table>
				
	<%
	}
	
}
		if(netloss!=0){
			totalassets=totalassets+netloss;
			%>
			 
			<table width="100%" >
		<tr>
		<td width="12%"></td>
		<td width="58%">Net loss</td>
		<td width="30%" align="right"><%=new BigDecimal(netloss).setScale(2, BigDecimal.ROUND_HALF_EVEN) + "Dr"%></td>
		</tr>			
		</table>
		<%	
		}	
		
		
		
		%>   
	
</td>
</tr>
<tr>
<th>
<%
String type="";
double	totalliablitiesbal=0.0;
	if(totalliablities>0){
		type="Cr";
		totalliablitiesbal=totalliablities;
	}else if(totalliablities<0){
		totalliablitiesbal=Math.abs(totalliablities);
		type="Dr";
}
	

%>
<table class="table table-bordered" width="100%">
		<tr>		
		<td width="58%" colspan="2">Total</td>
		<td width="30%" align="right"><%=new BigDecimal(totalliablitiesbal).setScale(2, BigDecimal.ROUND_HALF_EVEN) +  type %></td>
		</tr>			
		</table>	

</th>
<th>

<%
type="";
double	tassestbal=0.0;
	if(totalassets>0){
		type="Dr";
		tassestbal=totalassets;
	}else if(totalassets<0){
		tassestbal=Math.abs(totalassets);
		type="Cr";
}

%>

<table class="table table-bordered" width="100%">
		<tr>		
		<td width="58%" colspan="2">Total</td>
		<td width="30%" align="right"><%=new BigDecimal(tassestbal).setScale(2, BigDecimal.ROUND_HALF_EVEN) + type%></td>
		</tr>			
		</table>
</th>
</tr>

</table>
<br><br>
<table class="table table-bordered" width="100%">
<tr valign="top">
<td width="50%">
<table class="table table-bordered" width="100%">
<tr>
		<td width="12%">Sch No</td>
		<td width="58%">Expenses</td>
		<td width="30%">Balance</td>
		
		</tr>
</table>



<%
ArrayList<AccountGroupBean> aglist3 = new AccountGroupDao().getAllAccountGroupByType(4);

if(aglist3!=null){
	
	for(AccountGroupBean agb:aglist3){
		double bal=0.0;
		double totalgroup=0.0;
				
				//corrent amt
				
				
						ArrayList<TransactionBean> tblist1 = new TransactionDao().getLedgerBalByGroupBetweendates(agb.getAd_ac_group_id(), fdate ,tdate);
						if(tblist1!=null){						
							
							for(TransactionBean tb5:tblist1){	
								
								if(tb5.getDramt()!=0.00)
								{
									
										bal=bal-tb5.getDramt();
								}
								if(tb5.getCramt()!=0.00)
								{
									
										bal=bal-tb5.getCramt();
									
								}		
								
								
							}						
						
						}				
				
						totalgroup+=bal;					
			
		
		 type="";
	double	gbal=0.0;
		if(totalgroup>0){
			type="Cr";
			gbal=totalgroup;
			totalex=totalex+totalgroup;
		}else if(totalgroup<0){
			gbal=Math.abs(totalgroup);
			totalex=totalex+Math.abs(totalgroup);
			type="Dr";
		}
		
		
		group.put(++Scheduleno , agb.getAd_ac_group_id());
		
		%>
		<table class="table table-bordered" width="100%">
		<tr>
		<td width="12%"><%=Scheduleno %></td>
		<td width="58%"><%=agb.getName() %></td>
		<td width="30%" align="right"><%=new BigDecimal(gbal).setScale(2, BigDecimal.ROUND_HALF_EVEN) +type%></td>
		</tr>			
		</table>
				
	<%
	}
	
}

if(netprofit!=0){
	totalex=totalex+netprofit;
			%>
			
			<table class="table table-bordered" width="100%">
		<tr>
		<td width="12%"></td>
		<td width="58%">Net Profit</td>
		<td width="30%" align="right"><%=new BigDecimal(netprofit).setScale(2, BigDecimal.ROUND_HALF_EVEN) +"Cr" %></td>
		</tr>			
		</table>
		<%	
		}
		
		
		
		%>   


</td>
<td width="50%">
<table class="table table-bordered" width="100%">
<tr>
		<td width="12%">Sch No</td>
		<td width="58%">Income</td>
		<td width="30%">Balance</td>
		
		</tr>
</table>

<%
ArrayList<AccountGroupBean> aglist4 = new AccountGroupDao().getAllAccountGroupByType(3);

if(aglist3!=null){
	
	for(AccountGroupBean agb:aglist4){
		double bal=0.0;
		double totalgroup=0.0;
				
				//corrent amt
				
				
						ArrayList<TransactionBean> tblist1 = new TransactionDao().getLedgerBalByGroupBetweendates(agb.getAd_ac_group_id(), fdate ,tdate);
						if(tblist1!=null){						
							
							for(TransactionBean tb6:tblist1){	
								
								if(tb6.getDramt()!=0.00)		{
									
									bal=bal-tb6.getDramt();
								}
								if(tb6.getCramt()!=0.00)		{
							
									bal=bal+tb6.getCramt();
								}
								
								
							}						
						
						}				
				
						totalgroup+=bal;					
			
		
		 type="";
	double	gbal=0.0;
		if(totalgroup>0){
			type="Cr";
			gbal=totalgroup;
		}else if(totalgroup<0){
			gbal=Math.abs(totalgroup);
			type="Dr";
		}
		
		totalincome=totalincome+totalgroup;
		
		group.put(++Scheduleno , agb.getAd_ac_group_id());
		%>
		<table class="table table-bordered" width="100%">
		<tr>
		<td width="12%"><%=Scheduleno %></td>
		<td width="58%"><%=agb.getName() %></td>
		<td width="30%" align="right"><%=new BigDecimal(gbal).setScale(2, BigDecimal.ROUND_HALF_EVEN) +type%></td>
		</tr>			
		</table>
				
	<%
	}
	
}
if(netloss!=0){
	totalincome=totalincome+netloss;
			%>
			 
			<table class="table table-bordered" width="100%">
		<tr>
		<td width="12%"></td>
		<td width="58%">Net loss</td>
		<td width="30%" align="right"><%=new BigDecimal(netloss).setScale(2, BigDecimal.ROUND_HALF_EVEN) + "Dr"%></td>
		</tr>			
		</table>
		<%	
		}	
		
		
		
		%>   


</td>
</tr>
<tr>
<th>
<%
 type="";
double	totalexbal=0.0;
	if(totalex>0){
		//type="Cr";
		totalexbal=totalex;
	}else if(totalex<0){
		totalexbal=Math.abs(totalex);
		//type="Dr";
}
	

%>
<table class="table table-bordered" width="100%">
		<tr>		
		<td width="58%" colspan="2">Total</td>
		<td width="30%" align="right"><%=new BigDecimal(totalexbal).setScale(2, BigDecimal.ROUND_HALF_EVEN) +  type %></td>
		</tr>			
		</table>	

</th>
<th>

<%
type="";
double	tinbal=0.0;
	if(totalincome>0){
		//type="Dr";
		tinbal=totalincome;
	}else if(totalincome<0){
		tinbal=Math.abs(totalincome);
		//type="Cr";
}

%>

<table class="table table-bordered" width="100%">
		<tr>		
		<td width="58%" colspan="2">Total</td>
		<td width="30%" align="right"><%=new BigDecimal(tinbal).setScale(2, BigDecimal.ROUND_HALF_EVEN) + type%></td>
		</tr>			
		</table>
</th>
</table> 

<br><Hr>
<table class="table table-bordered" width="100%"><tr>
<th>Particular</th><th>Sch No</th><th>Dr Bal</th><th>Cr Bal</th>
</tr>
<tr>
<td colspan="4"><hr></td></tr>

<%
for(int i=1; i<=group.size();i++){	
	
	double totalgcramt=0.0;
	double totalgdramt=0.0;
	// System.out.println(  "id - " + group.get(i));
	 
	 String name = new AccountGroupDao().getAccountGroupNameById((Integer)group.get(i));
	 out.print("<tr><td >"+name+"</td><td align=center>"+i+"</td></tr>");
	 ArrayList<AccountSubGroupBean> asglist = new AccountSubGroupDao().getAllAccountSubGroupByGroupId((Integer)group.get(i));
	 if(asglist.isEmpty()!=true){		 
		 for(AccountSubGroupBean asgb:asglist){
			 double totalsubgroup=0.0;
				
				//opening amt
					double bal=0.0;
				
				if(asgb.getAd_ac_type_id()==1 || asgb.getAd_ac_type_id()==2){
				
						ArrayList<TransactionBean> tblist = new TransactionDao().getLedgerOpeningBalBySubGroup(asgb.getAd_ac_subgroup_id(), fdate);
						if(tblist!=null){
							
							for(TransactionBean tb:tblist){
								
								if(tb.getDramt()!=0.00){	
									
									if(asgb.getAd_ac_type_id()==1)
										bal=bal+tb.getDramt();
									else if(asgb.getAd_ac_type_id()==2)
										bal=bal-tb.getDramt();
									//if(asgb.getAd_ac_type_id()==4)
									//	bal=bal+tb.getDramt();
									//if(asgb.getAd_ac_type_id()==3)
									//	bal=bal-tb.getDramt();								
								
								}
								if(tb.getCramt()!=0.00){	
									
									if(asgb.getAd_ac_type_id()==1)
										bal=bal-tb.getCramt();
									else if(asgb.getAd_ac_type_id()==2)
										bal=bal+tb.getCramt();
									//if(asgb.getAd_ac_type_id()==4)
									//	bal=bal-tb.getCramt();
									//if(asgb.getAd_ac_type_id()==3)
										//bal=bal+tb.getCramt();								
								}
							}
									
						}
						
				}				
				
				//corrent amt
				
				
						ArrayList<TransactionBean> tblist1 = new TransactionDao().getLedgerBalBySubGroupBetweendates(asgb.getAd_ac_subgroup_id(), fdate ,tdate);
						if(tblist1!=null){						
							
							for(TransactionBean tb3:tblist1){	
								
								if(tb3.getDramt()!=0.00){						
									if(asgb.getAd_ac_type_id()==1)
										bal=bal+tb3.getDramt();
									else if(asgb.getAd_ac_type_id()==2)
										bal=bal-tb3.getDramt();
									else if(asgb.getAd_ac_type_id()==4)
										bal=bal+tb3.getDramt();
									else if(asgb.getAd_ac_type_id()==3)
										bal=bal-tb3.getDramt();											
								}
								if(tb3.getCramt()!=0.00){									
									if(asgb.getAd_ac_type_id()==1)
										bal=bal-tb3.getCramt();
									else if(asgb.getAd_ac_type_id()==2)
										bal=bal+tb3.getCramt();
									else if(asgb.getAd_ac_type_id()==4)
										bal=bal-tb3.getCramt();
									else if(asgb.getAd_ac_type_id()==3)
										bal=bal+tb3.getCramt();								
								}				
								
								
							}						
						
						}				
				
						totalsubgroup+=bal;				
						//out.print("<tr><td><br></td></tr>");
			
			if(totalsubgroup>0){
				if(asgb.getAd_ac_type_id()==1){				
					out.print("<tr><td>&nbsp;-"+asgb.getName()+"</td><td></td><td align=right>"+new BigDecimal(Math.abs(totalsubgroup)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td><td></td></tr>");
				}else if(asgb.getAd_ac_type_id()==2){					
					out.print("<tr><td>&nbsp;-"+asgb.getName()+"</td><td></td><td></td><td align=right>"+new BigDecimal(Math.abs(totalsubgroup)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td></tr>");
				}else if(asgb.getAd_ac_type_id()==4){
					out.print("<tr><td>&nbsp;-"+asgb.getName()+"</td><td></td><td align=right>"+new BigDecimal(Math.abs(totalsubgroup)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td><td></td></tr>");
				}else if(asgb.getAd_ac_type_id()==3){
					out.print("<tr><td>&nbsp;-"+asgb.getName()+"</td><td></td><td></td><td align=right>"+new BigDecimal(Math.abs(totalsubgroup)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td></tr>");	
				}
				
			}else if(totalsubgroup<0){
				if(asgb.getAd_ac_type_id()==1){
					out.print("<tr><td>&nbsp;-"+asgb.getName()+"</td><td></td><td></td><td align=right>"+new BigDecimal(Math.abs(totalsubgroup)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td></tr>");
				}else if(asgb.getAd_ac_type_id()==2){				
					out.print("<tr><td>&nbsp;-"+asgb.getName()+"</td><td></td><td align=right>"+new BigDecimal(Math.abs(totalsubgroup)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td><td></td></tr>");
				}else if(asgb.getAd_ac_type_id()==4){
					out.print("<tr><td>&nbsp;-"+asgb.getName()+"</td><td></td><td></td><td align=right>"+new BigDecimal(Math.abs(totalsubgroup)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td></tr>");
				}else if(asgb.getAd_ac_type_id()==3){
					out.print("<tr><td>&nbsp;-"+asgb.getName()+"</td><td></td><td align=right>"+new BigDecimal(Math.abs(totalsubgroup)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td><td></td></tr>");
				}
						
				
			}
			
			//ledger balance
			
			ArrayList<LedgerAccountBean> lalist = new LedgerAccountDao().getLedgerAccountBySubGroupId(asgb.getAd_ac_subgroup_id());
			double lbal=0.0;
			double totalledgrbal=0.0;
			
			if(lalist.isEmpty()!=true){
				
				for(LedgerAccountBean lab:lalist){
					//opening amt
					double ledgrbal=0.0;
					if(asgb.getAd_ac_type_id()==1 || asgb.getAd_ac_type_id()==2){
						ArrayList<TransactionBean> tbllist = new TransactionDao().getLedgerOpeningBal(lab.getAd_account_id(), fdate);
						if(tbllist!=null){
							
							for(TransactionBean tb:tbllist){
								
								
								if(tb.getDramt()!=0.00){	
									
									if(asgb.getAd_ac_type_id()==1)
										ledgrbal=ledgrbal+tb.getDramt();
									else if(asgb.getAd_ac_type_id()==2)
										ledgrbal=ledgrbal-tb.getDramt();
									//else if(asgb.getAd_ac_type_id()==4)
									//	ledgrbal=ledgrbal+tb.getDramt();
									//else if(asgb.getAd_ac_type_id()==3)
									//	ledgrbal=ledgrbal-tb.getDramt();								
								
								}
								if(tb.getCramt()!=0.00){	
									
									if(asgb.getAd_ac_type_id()==1)
										ledgrbal=ledgrbal-tb.getCramt();
									else if(asgb.getAd_ac_type_id()==2)
										ledgrbal=ledgrbal+tb.getCramt();
								//	else if(asgb.getAd_ac_type_id()==4)
									//	ledgrbal=ledgrbal-tb.getCramt();
									//else if(asgb.getAd_ac_type_id()==3)
									//	ledgrbal=ledgrbal+tb.getCramt();								
								}
							}
									
						}
					}	
										
				
				//corrent amt
				
				
						ArrayList<TransactionBean> tbllist1 = new TransactionDao().getLedgerBalBetweendates(lab.getAd_account_id(), fdate ,tdate);
						if(tbllist1!=null){						
							
							for(TransactionBean tb3:tbllist1){	
								
								if(tb3.getDramt()!=0.00){						
									if(asgb.getAd_ac_type_id()==1)
										ledgrbal=ledgrbal+tb3.getDramt();
									else if(asgb.getAd_ac_type_id()==2)
										ledgrbal=ledgrbal-tb3.getDramt();
									else if(asgb.getAd_ac_type_id()==4)
										ledgrbal=ledgrbal+tb3.getDramt();
									else if(asgb.getAd_ac_type_id()==3)
										ledgrbal=ledgrbal-tb3.getDramt();											
								}
								if(tb3.getCramt()!=0.00){									
									if(asgb.getAd_ac_type_id()==1)
										ledgrbal=ledgrbal-tb3.getCramt();
									else if(asgb.getAd_ac_type_id()==2)
										ledgrbal=ledgrbal+tb3.getCramt();
									else if(asgb.getAd_ac_type_id()==4)
										ledgrbal=ledgrbal-tb3.getCramt();
									else if(asgb.getAd_ac_type_id()==3)
										ledgrbal=ledgrbal+tb3.getCramt();								
								}				
								
								
							}						
						
						}				
				
						lbal=ledgrbal;				
						totalledgrbal+=ledgrbal;			
						if(lbal>0){
							if(asgb.getAd_ac_type_id()==1){				
								out.print("<tr><td>&nbsp;&nbsp;--"+lab.getAc_name()+"</td><td></td><td align=right>"+new BigDecimal(Math.abs(lbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td><td></td></tr>");
							}else if(asgb.getAd_ac_type_id()==2){					
								out.print("<tr><td>&nbsp;&nbsp;--"+lab.getAc_name()+"</td><td></td><td></td><td align=right>"+new BigDecimal(Math.abs(lbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td></tr>");
							}else if(asgb.getAd_ac_type_id()==4){
								out.print("<tr><td>&nbsp;&nbsp;--"+lab.getAc_name()+"</td><td></td><td align=right>"+new BigDecimal(Math.abs(lbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td><td></td></tr>");
							}else if(asgb.getAd_ac_type_id()==3){
								out.print("<tr><td>&nbsp;&nbsp;--"+lab.getAc_name()+"</td><td></td><td></td><td align=right>"+new BigDecimal(Math.abs(lbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td></tr>");	
							}
							
						}else if(lbal<0){
							if(asgb.getAd_ac_type_id()==1){
								out.print("<tr><td>&nbsp;&nbsp;--"+lab.getAc_name()+"</td><td></td><td></td><td align=right>"+new BigDecimal(Math.abs(lbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td></tr>");
							}else if(asgb.getAd_ac_type_id()==2){				
								out.print("<tr><td>&nbsp;&nbsp;--"+lab.getAc_name()+"</td><td></td><td align=right>"+new BigDecimal(Math.abs(lbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td><td></td></tr>");
							}else if(asgb.getAd_ac_type_id()==4){
								out.print("<tr><td>&nbsp;&nbsp;--"+lab.getAc_name()+"</td><td></td><td></td><td align=right>"+new BigDecimal(Math.abs(lbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td></tr>");
							}else if(asgb.getAd_ac_type_id()==3){
								out.print("<tr><td>&nbsp;&nbsp;--"+lab.getAc_name()+"</td><td></td><td align=right>"+new BigDecimal(Math.abs(lbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td><td></td></tr>");
							}
									
							
						}
						
						
						
					
				}
			}
			
			
			if(totalledgrbal>0){
				if(asgb.getAd_ac_type_id()==1){	
					totalgdramt+=totalledgrbal;
					out.print("<tr><td>&nbsp;&nbsp;--Total For A/c</td><td></td><td align=right>"+new BigDecimal(Math.abs(totalledgrbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td><td></td></tr>");
				}else if(asgb.getAd_ac_type_id()==2){	
					totalgcramt+=totalledgrbal;
					out.print("<tr><td>&nbsp;&nbsp;--Total For A/c</td><td></td><td></td><td align=right>"+new BigDecimal(Math.abs(totalledgrbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td></tr>");
				}else if(asgb.getAd_ac_type_id()==4){
					totalgdramt+=totalledgrbal;
					out.print("<tr><td>&nbsp;&nbsp;--Total For A/c</td><td></td><td align=right>"+new BigDecimal(Math.abs(totalledgrbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td><td></td></tr>");
				}else if(asgb.getAd_ac_type_id()==3){
					totalgcramt+=totalledgrbal;
					out.print("<tr><td>&nbsp;&nbsp;--Total For A/c</td><td></td><td></td><td align=right>"+new BigDecimal(Math.abs(totalledgrbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td></tr>");	
				}
				
			}else if(totalledgrbal<0){
				if(asgb.getAd_ac_type_id()==1){
					totalgcramt+=totalledgrbal;
					out.print("<tr><td>&nbsp;&nbsp;--Total For A/c</td><td></td><td></td><td align=right>"+new BigDecimal(Math.abs(totalledgrbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td></tr>");
				}else if(asgb.getAd_ac_type_id()==2){
					totalgdramt+=totalledgrbal;
					out.print("<tr><td>&nbsp;&nbsp;--Total For A/c</td><td></td><td align=right>"+new BigDecimal(Math.abs(totalledgrbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td><td></td></tr>");
				}else if(asgb.getAd_ac_type_id()==4){
					totalgcramt+=totalledgrbal;
					out.print("<tr><td>&nbsp;&nbsp;--Total For A/c</td><td></td><td></td><td align=right>"+new BigDecimal(Math.abs(totalledgrbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td></tr>");
				}else if(asgb.getAd_ac_type_id()==3){
					totalgdramt+=totalledgrbal;
					out.print("<tr><td>&nbsp;&nbsp;--Total For A/c</td><td></td><td align=right>"+new BigDecimal(Math.abs(totalledgrbal)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td><td></td></tr>");
				}
						
				
			}
						
			//out.print("<tr><td><br></td></tr>");
			 
						
		 }
	 }
	 if(totalgcramt!=0){
	 	out.print("<tr><td></td><td></td><td></td><td><hr></td></tr>");
		out.print("<tr><td>Total For A/c Head</td><td></td><td></td><td align=right>"+new BigDecimal(Math.abs(totalgcramt)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td></tr>");
	 }else if(totalgdramt!=0){
		 out.print("<tr><td></td><td></td><td><hr></td><td></td></tr>");
			out.print("<tr><td>Total For A/c Head</td><td></td><td align=right>"+new BigDecimal(Math.abs(totalgdramt)).setScale(2, BigDecimal.ROUND_HALF_EVEN)+"</td><td align=right></td></tr>");
	 }
		
		out.print("<tr><td colspan=4><hr></td></tr>");
}


%>


</table>

</td>
</tr>
</table>
<%}catch(Exception e){
	e.printStackTrace();
} %>

</body>
</html>