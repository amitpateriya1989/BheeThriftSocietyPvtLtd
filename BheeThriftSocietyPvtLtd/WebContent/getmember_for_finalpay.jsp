<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Dao.ExcessTrxDao"%>
<%@page import="Model.Bean.ExcessTrxBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="Model.Bean.JournalLedgerBean"%>
<%@page import="Model.Dao.FdTrxDao"%>
<%@page import="Model.Bean.FdViewBean"%>
<%@page import="Model.Bean.FdTrxBean"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="Model.Dao.ThriftTrxDao"%>
<%@page import="Model.Bean.ThriftViewBean"%>
<%@page import="Model.Dao.ThriftRoiDao"%>
<%@page import="Model.Bean.ThriftRoiBean"%>
<%@page import="Model.Dao.TypeOfLoanDao"%>
<%@page import="Model.Bean.TypeOfLoanBean"%>
<%@page import="Model.Dao.LoanCategoryDao"%>
<%@page import="Model.Bean.LoanCategoryBean"%>
<%@page import="Model.Dao.TypeOfFdDao"%>
<%@page import="Model.Bean.TypeOfFdBean"%>
<%@page import="Model.Dao.CompulsationDao"%>
<%@page import="Model.Bean.BankBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.BankDao"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="Model.Bean.LoanTrxDetailBean"%>
<%@page import="Model.Dao.TransactionDao"%>
<%@page import="Model.Dao.LoanTrxDetailDao"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="Model.Bean.LoanTrxBean"%>
<%@page import="Model.Bean.MemberShareBean"%>
<%@page import="Model.Dao.MemberShareDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>

   <link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2-bootstrap.css"/> 
<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-datepicker/css/datepicker.css"/>
<%
try{
Date date=null;
try{	
		 date= (Date)session.getAttribute("openday");
			
     
  
	int ad_member_id=Integer.parseInt(request.getParameter("ad_member_id"));
    // System.out.print(ad_member_id);
     
     MemberRegistrationBean meberbean = new MemberRegistrationDao().getMemberRegistrationById(ad_member_id);    
     
     	MemberShareBean msbean = new MemberShareDao().getAllShareAmtByMemberId(ad_member_id);  
   	
   		
   		double thriftcr_amt=0;
   		double thriftdr_amt=0;
   		double thrift_amt=0;
   		double loan_int=0;
   		double interest=0.0;
   		double loan_int_total=0.0;
   		double loan_total=0.0;
   		double loan_gtotal=0.0;
   		double total=0.0,thrift_total=0.0,int_total=0.0;
   		double fd_int_total=0.0,fd_total=0.0,fd_amt=0.0;
   		double thrift_int=0;
   		ThriftRoiBean thrift=null;
	    ArrayList<ThriftViewBean> listtrx=null;
   		
			
   		
 %>
<form id="frmFinalPay" name="frmFinalPay" autocomplete="off" action="AdFinalPay?action=insert&ad_member_id=<%=ad_member_id %>" method="post" >
<table  class="table table-bordered">
	<tbody>
		<tr>
			<td colspan="5">
				
						<table class="table table-bordered">
							<tr>
								<td>Name</td><td><%=meberbean.getName().toUpperCase() %></td>
							<td>Fname</td><td><%=meberbean.getFather() %></td>
							<td rowspan="7" align="right">
							<div style="height: 152px;width: 152px;border: 1px solid;">
								<img  width="150" height="150" alt="" src="<%=request.getContextPath()+"/member_images/"+meberbean.getPhoto() %>"  style="border:1px solid"/>
							</div>	
			
							</td>
							</tr>
							<tr>
								<td>PF NO</td><td><%=meberbean.getAd_pf_no() %></td>
								<td> M. NO.</td><td><%=meberbean.getAd_society_no() %></td>
							</tr>
							<tr>
								<td>DOB</td><td><%= new SimpleDateFormat("dd/MM/yyyy").format(meberbean.getDob()) %></td>
								<td>DOJ</td><td><%=new SimpleDateFormat("dd/MM/yyyy").format(meberbean.getDoj()) %></td>
							</tr>
							<tr>
								<td>Gender</td><td><%=meberbean.getGender().getGender()%></td>
								<td>Category</td><td><%=meberbean.getCategory().getCategory() %></td>
							</tr>
							<tr>
								<td>Branch</td><td><%=meberbean.getBranch().getBranch() %></td>
								<td>Branch Code</td><td><%=meberbean.getBranch().getBranch_code() %></td>
							</tr>
							<tr>
								<td>Final Pay Date </td><td><%=request.getParameter("final_pay_date") %>
									<input type="hidden" value="<%=request.getParameter("final_pay_date") %>" name="final_pay_date" id="final_pay_date" />
								</td>
								<td>Reason Of Final Payment</td>
								<td><%=request.getParameter("reson") %>
									<input type="hidden" value="<%=request.getParameter("reson") %>" name="reason" id="reason"  readonly="readonly" class="input" form-control input-medium></td>
                            </tr>
                            <tr>   
                                <td>Reason Date </td><td><%=request.getParameter("reson_date") %>
		 							<input type="hidden" value="<%=request.getParameter("reson_date") %>" readonly="readonly" name="reson_date" id="reson_date" readonly="readonly" form-control input-medium/>
		 						</td>
		 					</tr>
					</table>
					
				
						
			</td>
			
		</tr>
<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->		
		<tr>
		<td style="width:50%;">
		<div class="portlet box blue">
	<div class="portlet-title">
	<div class="caption">Thrift Detail</div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	</div><!-- end portlet-title -->
	<div class="portlet-body">											
		<div style="height:250px;overflow-y:auto;">
		<table id="dataTable1" class="table table-striped table-bordered table-hover">
												<thead>
												<tr>
												<th colspan="4" >
												
												</tr>
													<tr>
														
														<th>Date</th>
														<th>Month</th>
														<th>Thrift</th>
														<th>Int.</th>
														<th>Balance</th>
														
													</tr>
												</thead>
												<tbody>
												<%
												thrift=new ThriftRoiDao().getThriftRoiMaxId();
												MemberRegistrationBean member=new MemberRegistrationDao().getActiveMemberName(ad_member_id);
												
												if(member!=null){
													
														
														ThriftViewBean tvb=new ThriftTrxDao().getMaxThriftInterestDate(ad_member_id);
														if(tvb==null){
															tvb=new ThriftTrxDao().getMaxThriftOpeningDate(ad_member_id);
														}
										   		
												double balance=0.0,prv_balance=0.0; 
												int i=0;
												interest=0.0;
												int_total=0.0;
												int tot_days=0;
												Date first=null,second=null;
												
												
												if(tvb.getTrx_date()!=null){
													first=tvb.getTrx_date();
													prv_balance=tvb.getBalance();
												}
												
												double tot_balance=0.0;
												ArrayList<ThriftViewBean> thriftlist=new ThriftTrxDao().getAllThriftDetailByMemberIdAndDate(ad_member_id,first);
												if(thriftlist.isEmpty()!=true){
													for(ThriftViewBean bean:thriftlist){
														second=bean.getTrx_date();
														//prv_balance=bean.getBalance_amt();
														long diff = second.getTime() - first.getTime();
														long diffDays = diff / (24 * 60 * 60 * 1000);
														tot_days+=diffDays;
														tot_balance=prv_balance*diffDays;
														balance=bean.getBalance();
														interest =(tot_balance*thrift.getRoi())/36500.0;
														first=second;
														prv_balance=balance;
														int_total+=interest;
														
													interest=0;
													}
													
													}
												
												if(date!=null ){

													second=date;
													
													long diff = second.getTime() - first.getTime();
													long diffDays = diff / (24 * 60 * 60 * 1000);
													tot_days+=diffDays;
													tot_balance=prv_balance*diffDays;
													//balance=bean.getBalance_amt();
													interest =(tot_balance*thrift.getRoi())/36500.0;
													int_total+=interest;
													first=second;
													prv_balance=balance;
													
												}
												int_total=new BigDecimal(int_total).setScale(0, RoundingMode.CEILING).doubleValue();
												//System.out.println("Member : "+mbean.getName()+" total interest"+int_total);
												ArrayList<ThriftViewBean> tvflist=new ThriftTrxDao().getAllThriftDetailByMemberId(ad_member_id);
												if(tvflist.isEmpty()!=true){
													for(ThriftViewBean bean:tvflist){
														thrift_total=bean.getBalance();
												%>
												<tr>
												
												<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getTrx_date()) %></td>
												<%
													Calendar cal = Calendar.getInstance();
										 		    cal.setTime(bean.getTrx_date());
													%>
												<td><%=cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) %></td>
												<td><%=bean.getThrift_amt() %></td>
												<td><%=bean.getThrift_int() %></td>
												<td><%=bean.getBalance() %></td>
												
													</tr>
												
											<%
}	}
												}
											
											%>
											
											</tbody>
											<tfoot>
													<tr style="font-size: 14px;font-weight: bold;">
														<td colspan="5" align="right">
														
														<input type="hidden" value="<%=thrift_total %>"  readonly="readonly" id="thrift_amt" name='thrift_amt' style="background: none;border: none" />
														
														<input type="hidden" value=<%=int_total %>  readonly="readonly" id="thrift_int" name='thrift_int' style="background: none;border: none" />
														</td>
													</tr>
												</tfoot>
											</table>
			</div>		</div>						</div>
		</td>
		<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
		<td style="width: 50%">
		<div class="portlet box green">
	<div class="portlet-title">
	<div class="caption">Share Detail</div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	</div><!-- end portlet-title -->
	<div class="portlet-body">	
		<div style="height:250px;overflow-y:auto;">
		
		<table class="table table-bordered">
		    <thead>
		  <tr>
		    	  <th>Date</th>
		    	  <th>Batch No</th>
		    	  <th>From</th>
		    	  <th>To</th>
		    	  <th>Amount</th>
		    	  <th>Qty</th>
		    	 
		    	</tr>
		    </thead>
		    <tbody>
		    	<%
		    	
		    	ArrayList<MemberShareBean> lst=new ArrayList<MemberShareBean>();
				lst=new MemberShareDao().getMemberShareByMemberId(ad_member_id);
				double share_total_amt=0.0;
				int share_qty_total=0;
				if(lst.isEmpty()!=true){
					for(MemberShareBean bean:lst){
						share_total_amt+=bean.getShare_amt();
						share_qty_total+=bean.getQnt_of_share();
						%>
						<tr>
				  <td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getDate_of_allocation()) %></td>
		    	  
		    	  <td><%=bean.getBatch_no() %></td>
		    	  <td><%=bean.getShare_no_form() %></td>
		    	  <td><%=bean.getShare_no_to() %></td>
		    	  <td><%=bean.getShare_amt() %></td>
		    	  <td><%=bean.getQnt_of_share() %></td>
					</tr>	
						<%
					}
				}
		    	%>
		    </tbody>
		    <tfoot>
													<tr style="font-size: 14px;font-weight: bold;">
														<td align="right" colspan="4">Total</td>
														<td><%=share_total_amt %>
														
														
														
														</td><td><%=share_qty_total %></td>
													</tr>
												</tfoot>
		    </table>
		
		</div>
		</div></div>
		<%
		share_total_amt=0;
		double dr_amt=new TransactionDao().getTotalDrbyMemAndAcID(ad_member_id, 132);
		double cr_amt=new TransactionDao().getTotalCrbyMemAndAcID(ad_member_id, 132);
		share_total_amt=cr_amt-dr_amt;
		%>
		<input type="hidden" value='<%=share_total_amt %>'  readonly="readonly" id="share_amt" name='share_amt' style="background: none;border: none" />
		</td>
		<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
		</tr>
<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
		<tr>
		
		<td width="50%">
		<div class="portlet box purple">
	<div class="portlet-title">
	<div class="caption">Loan Detail</div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	</div><!-- end portlet-title -->
	<div class="portlet-body">		
		<div style="height:250px;overflow-y:auto;">
		 <table class="table table-bordered">
		 <thead>
		 <tr>
		 <th>Loan ID</th>
		 <th>Type</th>
		 <th>Balance</th>
		<!--  <th>Interest</th> -->
		
		 </tr>
		 </thead>
		 <tbody>	
	
   		   			<%
		ArrayList<LoanTrxBean> ltlist =new LoanTrxDao().getAllLoanTrxBymemId(ad_member_id);
   		
   		DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
   	  	Date today =  (Date)session.getAttribute("openday");
   	  	
   	  	double lgtotal=0.0;
   	  	
   		
   		if(ltlist.isEmpty()!=true){
   			for(LoanTrxBean ltbean:ltlist){
   				
   			
   			LoanTrxDetailBean lntbn = new LoanTrxDetailDao().getOpenLoanBal(ad_member_id, ltbean.getLoan_trx_id());
   			TypeOfLoanBean tfdbean = new TypeOfLoanDao().gettypeofloanById(ltbean.getLoan_type());
   				
   		   	loan_total+=lntbn.getBalance_amt();
   		   	loan_int_total=+loan_int;
   		   	
   		   		%>
   		   		<tr>
   		   		<td><%=ltbean.getLoan_trx_id() %></td>
   		   		<td><%=tfdbean.getName() %>	</td>
   		   		<td><%=lntbn.getBalance_amt() %></td>
   		   		
   		   		</tr>
   		   		
   		   		
   		   		
   		   		<% 
   		   			 
   		   		  
   		   		 
   			     
   			    
   			}
   			
   		}
   		
	  
	  %>
	  
		<tr style="font-size: 14px;font-weight: bold;">
		<td colspan="2">Total Balance</td>
		<td> <%=loan_total %>
		    <input type="hidden" value='<%=loan_total %>'  readonly="readonly" id="loan_amt" name='loan_amt' style="background: none;border: none"/></td>
		
		<%-- <td><%=loan_int_total %>
		<input type="hidden" value='<%=loan_int_total %>'  readonly="readonly" id="loan_int" name='loan_int' style="background: none;border: none"/></td>	
		<input type="hidden" id="noofloan" name="noofloan" value="<%=i%>" />
		 --%></tr>
		
		</tbody>
		 </table>
		 
		 </div>
		 </div>
		 </div>
		 </td>
<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
			<td style="width: 50%">
		<div class="portlet box green">
	<div class="portlet-title">
	<div class="caption">FD Detail</div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	</div><!-- end portlet-title -->
	<div class="portlet-body">	
		<div style="height:250px;overflow-y:auto;">
		
		<table class="table table-bordered">
	  <tr>
		
		<!-- <th>FD Name</th> -->
		<th>FD No</th>
		<th>ROI</th>
		<th>Period</th>
		<th>FD Amt</th>
		<th>Int. Amt</th>
		<th>Maturity Amt</th>
		<!-- <th>Opening Date </th> -->
		<th>Maturity Date</th>
		
	  </tr>
	  <%
	    ArrayList<FdViewBean> listfd=new FdTrxDao().getFdDetailByMemId(ad_member_id);
		
		if(listfd.isEmpty()!=true){
			for(FdViewBean bean:listfd){
				fd_total+=bean.getMaturityamt();
				fd_int_total+=bean.getInterest_amt();
				fd_amt+=bean.getFd_amt();
			%>
		<tr>
			
			<%-- <td><%=bean.getFd_name() %></td> --%>
			<td><%=bean.getFd_no() %></td>
			<td><%=bean.getRoi() %></td>
			<td><%=bean.getTime_period() %></td>
			<td><%=bean.getFd_amt() %></td>
			<td><%=bean.getInterest_amt() %></td>
			<td><%=bean.getMaturityamt() %></td>
			<%-- <td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getOpening_date()) %> </td> --%>
			<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getMaturity_date())%></td>
			 
	   </tr>
	  <%} 
	  }%>	
	  
	  <tr style="font-size: 14px;font-weight: bold;">
		<td colspan="3">Total Balance</td>
		<td><%=fd_amt %>
		<input type="hidden" value='<%=fd_amt %>'  readonly="readonly" id="fd_amt" name='fd_amt' style="background: none;border: none"/></td>	
		
		<td><%=fd_int_total %>
		<input type="hidden" value='<%=fd_int_total %>'  readonly="readonly" id="fd_int_total" name='fd_int_total' style="background: none;border: none"/></td>	
		<td> <%=fd_total %>
		    <input type="hidden" value='<%=fd_total %>'  readonly="readonly" id="fd_total" name='fd_total' style="background: none;border: none"/></td>
		<td></td>
		</tr>
	</table>
		
		</div>
		</div></div>
		</td>
<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
		</tr>
<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->		
		<tr>
		
		<td width="50%">
		<div class="portlet box purple">
	<div class="portlet-title">
	<div class="caption">Excess Account</div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	</div><!-- end portlet-title -->
	<div class="portlet-body">		
		<div style="height:250px;overflow-y:auto;">
		 <table class="table table-bordered">
		 <thead>
		 <tr>
		 <th>Sno.</th>
		  <th>Date</th>
		 <th>Detail</th>
		 <th>Deposit</th>
		 <th>Withdrawal</th>
		<th>Balance</th>
		
		 </tr>
		 </thead>
		 <tbody>	
	
   		   			<%
		ArrayList<ExcessTrxBean> excesslist =new ExcessTrxDao().getAllExcessBymemId(ad_member_id);
   		
   		 df1 = new SimpleDateFormat("dd/MM/yyyy");
   	  	 today =  (Date)session.getAttribute("openday");
   	  	
   	  	double exAmtTotal=0.0,exWidTotal=0.0;
   	  	double excBalance=0.0;
   	   int  i=0;
   		if(excesslist.isEmpty()!=true){
   			for(ExcessTrxBean excess:excesslist){
   				
   				exAmtTotal+=excess.getExcess_amt();
   				exWidTotal+=excess.getWithdrawal();
   				excBalance=excess.getBalance();
   		   		%>
   		   		<tr>
   		   		<td><%=++i %></td>
   		   		<td><%=excess.getTrx_date() %></td>
   		   		<td><%=excess.getDetail() %>	</td>
   		   		<td><%=excess.getExcess_amt() %></td>
   		   		<td><%=excess.getWithdrawal() %></td>
   		   		<td><%=excess.getBalance() %></td>
   		   		</tr>
   		   		<% 
   		   		 
   			}
   			
   		}
   		
	  
	  %>
	  
		<tr style="font-size: 14px;font-weight: bold;">
		<td colspan="3">Total Excess Amt</td>
		<td> <%=exAmtTotal %>
		    	<input type="hidden" value='<%=exAmtTotal %>'  readonly="readonly" id="excess_dr_amt" name='excess_dr_amt' style="background: none;border: none"/>
		</td>
		<td> <%=exWidTotal %>
		    	<input type="hidden" value='<%=exWidTotal %>'  readonly="readonly" id="excess_cr_amt" name='excess_cr_amt' style="background: none;border: none"/>
		</td>
		<td><input type="hidden" value='<%=excBalance %>'  readonly="readonly" id="excBalance" name='excBalance' style="background: none;border: none"/></td>
		</tr>
		
		</tbody>
		 </table>
		 
		 </div>
		 </div>
		 </div>
		 </td>
		 </tr>
		
		
		
		
<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->		 
		 <tr>
		<td colspan="2">
		<div class="portlet box red">
	<div class="portlet-title">
	<div class="caption">Account Summary</div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	</div><!-- end portlet-title -->
	<div class="portlet-body">	
			<table class="table table-bordered" width="100%">
			<tr>
			<td>Thrift A/C </td>
			<td>Thrift Int A/C </td>
			<td>Share A/C </td>
			<td>Excess A/C </td>
			<td>Fd A/C </td>
			<td>Loan A/C </td>
			<!-- <td>Loan Int A/C </td> -->
			
			</tr>
			
			<tr>
			<td><%=thrift_total %> </td>
			<td><%=int_total %> </td>
			<td><%=share_total_amt%> </td>
			<td><%=excBalance %> </td>
			<td><%=fd_total %> </td>
			<td><%=loan_total %> </td>
			<%-- <td><%=loan_int_total %> </td> --%>
			
			
			</tr>
			
			</table>
		</div></div>
		</td>
		</tr>
		
	<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->	
		<tr>
		<td colspan="2">
		<div class="portlet box green">
	<div class="portlet-title">
	<div class="caption">Payment Detail</div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	</div><!-- end portlet-title -->
	<div class="portlet-body">	
			<table class="table table-bordered" width="100%">
			<tr>
			<%double comamt=new CompulsationDao().getCompulsationAmt(request.getParameter("reson").trim()); %>
		   <td>Compensation</td><td><input type="text" class="form-control input-medium" value='<%=comamt  %>'   id="compensation_amt" name='compensation_amt' readonly="readonly" /></td>	
		
			<%
			double net_total=(thrift_total+int_total+share_total_amt+comamt+excBalance)-(loan_total);
			
			%>
			<td>Net Payable Amt <span class="red">*</span></td><td><input class="form-control input-medium" value="<%=net_total %>" name="net_payable" id="net_payable" readonly="readonly"  /></td>
			
			<td>Trx By <span class="red">*</span></td>
			<td><select class="form-control input-medium" name="trx_by" id="trx_by" >
				<option>Cheque</option>
				<option>DD</option>
				<option>Cash</option>
			</select>
			</td>
			</tr>
			
			<tr>
			
			
			<td>Bank <span class="red">*</span></td>
			<td><select class="form-control input-medium" name="ad_bank_id" id="ad_bank_id"  tabindex="2">
								 
								<%
								  BankDao dao=new BankDao();
								  ArrayList <BankBean> alist=dao.getAllBank();
								  if(alist.isEmpty()!=true){
								  for(BankBean bbean:alist){
									  %>
								  <option value="<%=bbean.getAd_bank_id()%>"><%=bbean.getBank() %></option>
									  
								 <%} }%>
						</select></td>
						<td>Cheque No <span class="red">*</span></td><td><input class="form-control input-medium" value="" name="cheque_dd_no" id="cheque_dd_no"/></td>
			<td>Cheque Date <span class="red">*</span></td>
			<td><input class="form-control input-medium date-picker2" type="text" placeholder="dd/mm/yyyy"  name="cheque_dd_date" id="cheque_dd_date"/></td>
			
			</tr>
			<tr>
			<td>Voucher Type <span class="red">*</span></td>
						<td>	<input type="text" class="form-control input-medium"  name="voucher_type" id="voucher_type" value="Payment" readonly="readonly" />
						</td>
			<td>Narration <span class="red">*</span></td>
						<td colspan="3">	<input type="text" class="form-control"  name="narration" id="narration"  />
						</td>
						</tr>
						<tr>
		<td>Amt in Words <span class="red">*</span></td>
		<td colspan="3">	<input type="text" class="form-control"  name="amt_in_words" id="amt_in_words" readonly="readonly" />				
		<td colspan="2" align="center">
			<a href="#" class="btn btn-md red" type="submit" name="submit" id="close_member"  >Close Member Account</a>
		</td>
		</tr>
			</table>
		</div></div>
		</td>
		</tr>
		
<!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
	</tbody>
</table>
</form>


<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

<script src="assets/plugins/select2/select2.min.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script type="text/javascript">
	$(function(){
		$('#trx_by').select2();
		$('#ad_bank_id').select2();
		var netpayamt=$('#net_payable').val();
		if(parseFloat(netpayamt)>0){
		var amt=number2text(netpayamt);
		$('#amt_in_words').val(amt);
		}
		
		jQuery('.date-picker2').datepicker({format: 'dd/mm/yyyy',endDate : new Date('<%=date%>'),autoclose: true});
		
		jQuery.validator.addMethod("alphabet", function(value, element) {
			  return this.optional(element) || /^[a-zA-Z]*$/.test(value);
		}, "Please enter character only.");
		
		jQuery.validator.addMethod("validDate", function (value, element) {
	        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
	    }, "Please enter valid Date.");
		
		jQuery( "#frmFinalPay" ).validate({
			  rules: {
				  trx_by:{
					  required: true,
					  alphabet:true
				  },
				  ad_bank_id:{
					  required: true,
					  digits:true
				  },
				  cheque_dd_no:{
					  required: true,
					  maxlength:6,
					  minlength:6,
					  digits:true
				  },
				  cheque_dd_date:{
					  required: true,
					  validDate:true
				  },
				  voucher_type:{
					  required: true,
					  alphabet:true
				  },
				  net_payable:{
					  number:true,
					  required:true
				  }
			  }
			});
		
		/* $("#checkAdjustment").click(function(){
			
			if($(this).is(':checked')){
				 $("#submit").removeAttr('disabled');
			}else{
				 $("#submit").attr('disabled','disabled');
			}
			
		}); */
		
		
		$('#close_member').click(function(e){
			
			var loan_bal=<%=loan_total%>;
			
			  if(loan_bal>0){
				  $('#custom-page-message').html("<div class='note note-warning'>Please Close Loan From Loan Deposit Section..!! </div>");
				    $('.custom-messageBox').modal('show'); 
				    
			  }else{
				  //$('#frmFinalPay').submit(); 
				  $( "#frmFinalPay" ).submit();
			  }
			
			
		});
	});//end dom
	
	function number2text(value) {
	    var fraction = Math.round(frac(value)*100);
	    var f_text  = "";

	    if(fraction > 0) {
	        f_text = "AND "+convert_number(fraction)+" PAISE";
	    }

	    return convert_number(value)+" RUPEE "+f_text+" ONLY";
	}

	function frac(f) {
	    return f % 1;
	}

	function convert_number(number)
	{
	    if ((number < 0) || (number > 999999999)) 
	    { 
	        return "NUMBER OUT OF RANGE!";
	    }
	    var Gn = Math.floor(number / 10000000);  /* Crore */ 
	    number -= Gn * 10000000; 
	    var kn = Math.floor(number / 100000);     /* lakhs */ 
	    number -= kn * 100000; 
	    var Hn = Math.floor(number / 1000);      /* thousand */ 
	    number -= Hn * 1000; 
	    var Dn = Math.floor(number / 100);       /* Tens (deca) */ 
	    number = number % 100;               /* Ones */ 
	    var tn= Math.floor(number / 10); 
	    var one=Math.floor(number % 10); 
	    var res = ""; 

	    if (Gn>0) 
	    { 
	        res += (convert_number(Gn) + " CRORE"); 
	    } 
	    if (kn>0) 
	    { 
	            res += (((res=="") ? "" : " ") + 
	            convert_number(kn) + " LAKH"); 
	    } 
	    if (Hn>0) 
	    { 
	        res += (((res=="") ? "" : " ") +
	            convert_number(Hn) + " THOUSAND"); 
	    } 

	    if (Dn) 
	    { 
	        res += (((res=="") ? "" : " ") + 
	            convert_number(Dn) + " HUNDRED"); 
	    } 


	    var ones = Array("", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX","SEVEN", "EIGHT", "NINE", "TEN", "ELEVEN", "TWELVE", "THIRTEEN","FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN","NINETEEN"); 
	var tens = Array("", "", "TWENTY", "THIRTY", "FOURTY", "FIFTY", "SIXTY","SEVENTY", "EIGHTY", "NINETY"); 

	    if (tn>0 || one>0) 
	    { 
	        if (!(res=="")) 
	        { 
	            res += " AND "; 
	        } 
	        if (tn < 2) 
	        { 
	            res += ones[tn * 10 + one]; 
	        } 
	        else 
	        { 

	            res += tens[tn];
	            if (one>0) 
	            { 
	                res += ("-" + ones[one]); 
	            } 
	        } 
	    }

	    if (res=="")
	    { 
	        res = "zero"; 
	    } 
	    return res;
	}
</script>
<% //}

   		}catch(Exception ex){
   			ex.printStackTrace();
}
%>
<%}catch(Exception e){
	e.printStackTrace();
} %>