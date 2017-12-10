<%@page import="Model.Dao.ExcessTrxDao"%>
<%@page import="Model.Bean.ExcessTrxBean"%>
<%@page import="Model.Dao.PayrollAdviceDao"%>
<%@page import="Model.Bean.PayrollAdviseBean"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="Model.Bean.FdViewBean"%>
<%@page import="Model.Bean.ThriftRoiBean"%>
<%@page import="Model.Dao.ThriftRoiDao"%>
<%@page import="Model.Bean.ThriftViewBean"%>
<%@page import="Model.Dao.ThriftTrxDao"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="Model.Bean.LoanDetailViewBean"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="Model.Dao.MemberShareDao"%>
<%@page import="Model.Bean.MemberShareBean"%>
<%@page import="Model.Dao.TransactionDao"%>
<%@page import="Model.Bean.TransactionBean"%>
<%@page import="Model.Dao.FdTrxDao"%>
<%@page import="Model.Bean.FdTrxBean"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="Model.Bean.LoanTrxBean"%>
<%@page import="Model.Bean.LoanTrxDetailBean"%>
<%@page import="Model.Dao.LoanTrxDetailDao"%>
<%@page import="Model.Bean.LoanPurposeBean"%>
<%@page import="Model.Dao.LoanPurposeDao"%>
<%@page import="Model.Bean.MemberRegistrationMasterBean"%>
<%@page import="Model.Dao.MemberRegistrationMasterDao"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Bean.RelationBean"%>
<%@page import="Model.Dao.RelationDao"%>
<%@page import="Model.Dao.CityDao"%>
<%@page import="Model.Bean.DesignationTypeBean"%>
<%@page import="Model.Dao.DesignationTypeDao"%>
<%@page import="Model.Bean.DesignationLevelBean"%>
<%@page import="Model.Dao.DesignationLevelDao"%>
<%@page import="Model.Bean.DesignationBean"%>
<%@page import="Model.Dao.DesignationDao"%>
<%@page import="Model.Bean.DepartmentBean"%>
<%@page import="Model.Dao.DepartmentDao"%>
<%@page import="Model.Bean.BankRegionBean"%>
<%@page import="Model.Dao.BankRegionDao"%>
<%@page import="Model.Bean.CategoryBean"%>
<%@page import="Model.Dao.CategoryDao"%>
<%@page import="Model.Bean.CountryBean"%>
<%@page import="Model.Dao.CountryDao"%>
<%@page import="Model.Bean.CastBean"%>
<%@page import="Model.Dao.CastDao"%>
<%@page import="Model.Bean.GenderBean"%>
<%@page import="Model.Dao.GenderDao"%>
<%@page import="Model.Bean.SalutationBean"%>
<%@page import="Model.Dao.SalutationDao"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Dao.BankDao"%>
<%@page import="Model.Bean.BankBean"%>
<%@page import="Model.Bean.StateBean"%>
<%@page import="Model.Dao.StateDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%@page import="Model.Bean.TypeOfLoanBean"%>
<%@page import="Model.Dao.TypeOfLoanDao"%>
<%@page import="Model.Bean.LoanCategoryBean"%>
<%@page import="Model.Dao.LoanCategoryDao"%>

<%try{ %>

<!-- ------------------- -------------- Start Tab ---------------------------------------------->
<ul class="nav nav-pills">
			<li class="active">
				<a href="#tab1" data-toggle="tab">Personal</a>
			</li>
			<li class="">
				<a href="#tab2" data-toggle="tab">Payroll </a>
			</li>
			<li class="">
				<a href="#tab3" data-toggle="tab">Thrift</a>
			</li>
			<li class="">
				<a href="#tab4" data-toggle="tab">Share</a>
			</li>
			<li class="">
				<a href="#tab5" data-toggle="tab">Loan</a>
			</li>
			<li class="">
				<a href="#tab6" data-toggle="tab">Excess</a>
			</li>
			<li class="">
				<a href="#tab7" data-toggle="tab">FD</a>
			</li>
			
			
			<li class="">
				<a href="#tab8" data-toggle="tab">Current Position</a>
			</li>
</ul>
<div class="tab-content">
	<div class="tab-pane fade active in" id="tab1">
		<table class="table table-bordered">
			<tr>
				<td width="15%">Name :</td>
				<td width="35%"><label id="name" for="name"></label></td>
				<td width="15%">PFNo :</td>
				<td width="35%"><label id="ad_pf_no" for="ad_pf_no"></label></td>
			</tr>
			<tr>
				<td width="15%">Mem.No :</td>
				<td width="35%"><label id="ad_society_no" for="ad_society_no"></label></td>
				<td width="15%">Type :</td>
				<td width="35%"><label id="ad_member_type_id" for="ad_member_type_id"></label></td>
			</tr>
			<tr>
				<td width="15%">Father :</td>
				<td width="35%"><label id="father" for="father"></label></td>
				<td width="15%">Husband :</td>
				<td width="35%"><label id="Husband" for="Husband"></label></td>
			</tr>
			<tr>
				<td width="15%">DOB :</td>
				<td width="35%"><label id="dob" for="dob"></label></td>
				<td width="15%">Gender :</td>
				<td width="35%"><label id="ad_gender_id" for="ad_gender_id"></label></td>
			</tr>
			<tr>
				<td width="15%">Category :</td>
				<td width="35%"><label id="ad_category_id" for="ad_category_id"></label></td>
				<td width="15%">Status :</td>
				<td width="35%"><label id="ad_member_status_id" for="ad_member_status_id"></label></td>
			</tr>
		</table>
	</div>
	<!-- ------------------- -------------- End Personal---------------------------------------------->
	<div class="tab-pane fade" id="tab2">
	<table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				
				<tr>
					<th width="10%">Sno</th>
					<th width="10%">Branch</th>
					<th width="10%">Thrift</th>
					<th width="10%">Loan</th>
					<th width="10%">Emergency</th>
					<th width="10%">Total</th>
					<th width="10%">Status</th>
					
			
				</tr>
				</thead>
				<tbody>
		 <%
	   		PayrollAdviseBean bean1=new PayrollAdviceDao().getAllPayrollAdviceByMemberId(Integer.parseInt(request.getParameter("ad_member_id")));
		 int i=0;
	   if(bean1!=null){
		  %>
			   <tr>
			   <td><%=++i %></td>
			   <td><%=bean1.getBranch() %></td>
			   <td><%=bean1.getFgds_fund() %></td>
			   <td><%=bean1.getMainloan_emi() %></td>
			   <td><%=bean1.getFestivalloan_emi() %></td>
			    <td><%=bean1.getFgds_fund()+bean1.getMainloan_emi()+bean1.getFestivalloan_emi() %></td>
			     <td><%=bean1.getMember_status() %></td>
			 </tr>  
		   <%}
	   
	   
	   %>		
				
				
				</tbody>
			</table>
	</div>
	
	<!-- ------------------- -------------- End Payroll Advice---------------------------------------------->
	<div class="tab-pane fade" id="tab3">
	<table class="table table-bordered">
		<tr>
			<th>Sno</th>
			<th>Trx Date</th>
			<th>Payroll Month</th>
			<th>Thrift Amt</th>
			<th>Paid Int</th>
			<th>Balance Amt</th>
			<!-- <th>Total Amt </th>
			<th>Days </th>
			<th>ROI </th>
			<th>Interest</th> -->
					
		</tr>
		<%
		Calendar cal = Calendar.getInstance();
		Date d =(Date)session.getAttribute("openday");
		int ad_member_id=Integer.parseInt(request.getParameter("ad_member_id"));
		
		ThriftRoiBean thrift=new ThriftRoiDao().getThriftRoiMaxId();
		ThriftViewBean tvb=new ThriftTrxDao().getMaxThriftInterestDate(ad_member_id);
		if(tvb==null){
			tvb=new ThriftTrxDao().getMaxThriftOpeningDate(ad_member_id);
		}
   		
		double interest=0.0,balance=0.0,prv_balance=0.0; 
		i=0;
		double int_total=0.0;
		int tot_days=0;
		Date first=null,second=null;
		if(tvb.getTrx_date()!=null){
			first=tvb.getTrx_date();
			prv_balance=tvb.getBalance();
		}
		
		double tot_balance=0.0;
		ArrayList<ThriftViewBean> thriftlist=new ThriftTrxDao().getAllThriftDetailByMemberId(ad_member_id);
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
				%>
				
				<tr>
				<td><%=++i %></td>
				<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getTrx_date()) %></td>
				<%
				
	 		    cal.setTime(bean.getTrx_date());
				%>
				<td><%=cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) %></td>
				<td><%=bean.getThrift_amt()%></td>
				<td><%=bean.getThrift_int()%></td>
				<td><%=bean.getBalance()%></td>
			    <%-- <td><%=balance %></td>
			    <td><%=diffDays %></td>
			    <td><%=thrift.getRoi() %></td>
			    <td><%=new BigDecimal(interest).setScale(0, RoundingMode.HALF_UP).doubleValue() %></td>	
				 --%>
			</tr>
			<%
			interest=0;
			}
			
			}
		
		if(d!=null ){

			second=d;
			
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
		interest=new BigDecimal(interest).setScale(0, RoundingMode.HALF_UP).doubleValue();
		System.out.println("interest"+interest);
		
			%>
			
		
	</table>
	</div>
	
	<!-- ------------------- -------------- End Thrift Advice---------------------------------------------->
	
	<div class="tab-pane fade" id="tab4">
	 <table class="table table-bordered">
	 <tr>
		<th>Sno</th>
		<th>Date of Allocation</th>
		<th>Batch No</th>
		<th>Share No Form</th>
		<th>Share No To </th>
		<th>Qnt Of Share</th>
		<th>Share Amt</th>
		<th>Total Amt</th>
	  </tr>
	  <%
		//(ad_mem_id);
	  
		ArrayList<MemberShareBean> listms=new MemberShareDao().getMemberShareByMemberId(Integer.parseInt(request.getParameter("ad_member_id")));
		 i=0;
		double sharetotal=0.0;
		if(listms!=null){
			for(MemberShareBean bean:listms){
				sharetotal+=bean.getShare_amt();
			%>
			<tr>
				<td><%=++i %></td>
				<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getDate_of_allocation()) %></td>
				<td><%=bean.getBatch_no() %></td>
				<td><%=bean.getShare_no_form()%></td>
				<td><%=bean.getShare_no_to() %> </td>
				<td><%=bean.getQnt_of_share() %> </td>
				<td><%=bean.getShare_amt() %> </td>
				<td><%=sharetotal %></td>	 
			</tr>
		<%} 
		}%>
	 </table>
	</div>
	<!-- ------------------- -------------- End Share Detail---------------------------------------------->
	
		
	<div class="tab-pane fade" id="tab5">
		<table class="table table-bordered">
		<thead>
		  <tr>
			<th>Sno</th>
			<th>Staff_no</th>
			<th>Loan Type</th>
			<th>Loan Category</th>
			<th>Loan Amt </th>
			<th>Interest Rate</th>
			<th>Time Period</th>
			<th>issue Date</th>
			<th>End Date</th>
			<th>Emi</th>
			<th>OPT</th>
		  </tr>
		</thead>
		<tbody>
			<%
			
			ArrayList<LoanDetailViewBean> listlt=new LoanTrxDao().getAllLoanDetailByMemberId(Integer.parseInt(request.getParameter("ad_member_id")),"Open");
			 i=0;
			if(listlt.isEmpty()!=true){
			for(LoanDetailViewBean bean:listlt){%>
			<tr  style="cursor: pointer;" >
			  <td><%=++i %></td>
			   <td><%=bean.getAd_society_no()%></td>
			  <td><%=bean.getLoan_type() %></td>
			  <td><%=bean.getLoan_category_name() %></td>
			  <td><%=bean.getLoan_amt() %> </td>
			  <td><%=bean.getIntrest_rate() %></td>
			  <td><%=bean.getPeriod_month() %></td>
			  <td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getIssue_date()) %></td>
			  <td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getEnd_date()) %></td>
			  <td><%=bean.getEmi()%></td>
			   <td><%-- <a href="edit_loan_trx.jsp?loan_trx_id=<%=bean.getLoan_trx_id()%>" class='btn btn-xm blue'>Edit</a>
			  		<a href="AdLoanTrx?action=deleteLoan&loan_trx_id=<%=bean.getLoan_trx_id()%>" class='btn btn-xm red'>Delete</a>
					--%>
					<a href="#" id="showModalLoanTrx" onclick="showloantrx(<%=bean.getLoan_trx_id() %>)" class="btn btn-xm green" ><i class="fa fa-search "></i>Trnx</a>
			  </td> 
			  	
		    </tr>
			<%} 
		    }%>	
		</tbody>
		</table>
	</div>
	<!-- ------------------- -------------- End Loan Detail---------------------------------------------->
	<div class="tab-pane fade" id="tab6">
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
   		
   		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   	  	Date today =  (Date)session.getAttribute("openday");
   	  	
   	  	double exAmtTotal=0.0,exWidTotal=0.0;
   	  	double excBalance=0.0;
   	    i=0;
   		if(excesslist.isEmpty()!=true){
   			for(ExcessTrxBean excess:excesslist){
   				
   				exAmtTotal+=excess.getExcess_amt();
   				exWidTotal+=excess.getWithdrawal();
   				excBalance=excess.getBalance();
   		   		%>
   		   		<tr>
   		   		<td><%=++i %></td>
   		   		<td><%=sdf.format(excess.getTrx_date()) %></td>
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
	<!-- ------------------- -------------- End Excess Detail---------------------------------------------->
	
	
	<div class="tab-pane fade" id="tab7">
		<table class="table table-bordered">
		  <tr>
			<th>Sno</th>
			<th>FD Name</th>
			<th>FD No</th>
			<th>FD Amt</th>
			<th>ROI</th>
			<th>Period</th>
			<th>Int. Amt</th>
			<th>Maturity Amt</th>
			<th>Opening Date </th>
			<th>Maturity Date</th>
			<th>Remark</th>	
		  </tr>
		  <%
			FdTrxBean fdb = new FdTrxBean();
			fdb.setAd_member_id(Integer.parseInt(request.getParameter("ad_member_id")));
			ArrayList<FdViewBean> listfd=new FdTrxDao().getFdDetailByMemId(fdb.getAd_member_id());
			i=0;
			if(listfd.isEmpty()!=true){
				for(FdViewBean bean:listfd){%>
			<tr>
				<td><%=++i %></td>
				<td><%=bean.getFd_name() %></td>
				<td><%=bean.getFd_no() %></td>
				<td><%=bean.getFd_amt() %></td>
				<td><%=bean.getRoi() %></td>
				<td><%=bean.getTime_period() %></td>
				<td><%=bean.getInterest_amt() %></td>
				<td><%=bean.getMaturityamt() %></td>
				<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getOpening_date()) %> </td>
				<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getMaturity_date())%></td>
				<td><%=bean.getRemark() %></td>	 
		   </tr>
		  <%} 
		  }%>	
		</table>
	</div>
	<!-- ------------------- -------------- End FD Detail---------------------------------------------->
	
	
	<div class="tab-pane fade" id="tab8">
	    <%
	   // try{
		   
		   	

			
		     	MemberShareBean msbean = new MemberShareDao().getAllShareAmtByMemberId(Integer.parseInt(request.getParameter("ad_member_id")));  
		   		
		   		double thrift_amt=new ThriftTrxDao().getTotalThriftAmountByMemberId(Integer.parseInt(request.getParameter("ad_member_id")));
		   		
				 cal = Calendar.getInstance();
				 d =(Date)session.getAttribute("openday");
				 ad_member_id=Integer.parseInt(request.getParameter("ad_member_id"));
				
				 thrift=new ThriftRoiDao().getThriftRoiMaxId();
				 tvb=new ThriftTrxDao().getMaxThriftInterestDate(ad_member_id);
				if(tvb==null){
					tvb=new ThriftTrxDao().getMaxThriftOpeningDate(ad_member_id);
				}
		   		
				 interest=0.0;balance=0.0;prv_balance=0.0; 
				i=0;
				 int_total=0.0;
				 tot_days=0;
				 first=null;
				 second=null;
				if(tvb.getTrx_date()!=null){
					first=tvb.getTrx_date();
					prv_balance=tvb.getBalance();
				}
				
				 tot_balance=0.0;
				 thriftlist=new ThriftTrxDao().getAllThriftDetailByMemberIdAndDate(ad_member_id, first);
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
		
		if(d!=null ){

			second=d;
			
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
		interest=new BigDecimal(interest).setScale(0, RoundingMode.HALF_UP).doubleValue();
		System.out.println("interest"+interest);
		
			%>
		<table class="table table-bordered">
			<tr>
		        <td colspan="5" style="background:buttonface; "> Thrift | Share | Excess Detail </td>
		   </tr>
		   <tr>
		   		<td>1.</td>
				<td colspan="3">Thrift Amount</td>
				<td>Rs. <%=thrift_amt %>  </td>
		</tr>
		<tr>
				<td> 2.</td>
				<td colspan="3">Interest On Thrift Amount</td>
				<td>Rs: <%=new BigDecimal(int_total).setScale(0, RoundingMode.HALF_UP).doubleValue() %></td>	
		</tr>
		<tr>
				<td> 3.</td>
				<td colspan="3">Share Amt.</td>
				<td>Rs: <%=msbean.getShare_amt()%> </td>	
		</tr>
		<tr>
				<td> 4.</td>
				<td colspan="3">Excess Amt.</td>
				<td>Rs: <%=excBalance%> </td>	
		</tr>
		<tr>
			<td align="Right" colspan="4">Total Payable Amount </td>
			<td style="font-weight: bold;font-size: 14px;">Rs:<%=thrift_amt+msbean.getShare_amt()+interest+excBalance %> </td>	
		</tr>
		<!---------------------------------end of thrift and share total  -->
		
		<tr style="background:buttonface; ">
		 	<td colspan="5"> Fixed Deposit Detail </td>
		</tr>
			
		  <tr>
			<th>Sno</th>
			<th>FD Name</th>
			<th>FD Amt</th>
			<th>Int. Amt</th>
			<th>Maturity Amt</th>
		 </tr>
		  <%
			double fd_total_amt=0.0;
		    double fd_total_int=0.0;
			i=0;
			if(listfd.isEmpty()!=true){
				for(FdViewBean bean:listfd){%>
			<tr>
				<td><%=++i %></td>
				<td><%=bean.getFd_name() %></td>
				<td><%=bean.getFd_amt() %></td>
				<td><%=bean.getInterest_amt() %></td>
				<td><%=bean.getMaturityamt() %></td>
				
		   </tr>
		  <%
		  fd_total_amt+=bean.getMaturityamt();
		  fd_total_int=bean.getInterest_amt();
				} 
		  }%>	
		
		
		<tr>
		
		
		<td style="font-weight: bold;font-size: 14px;" colspan="3" align="right">Total</td>
		<td style="font-weight: bold;font-size: 14px;">Rs:<%=fd_total_int %></td>	
		<td style="font-weight: bold;font-size: 14px;">Rs:<%=fd_total_amt %>
		</tr>
		
		
	   
	    <!-------------------------------end of fd total  -->
		
		
		<tr style="background:buttonface; ">
		 	<td colspan="5"> Loan Detail </td>
		</tr>
			<%  ArrayList<LoanTrxDetailBean> ltdbean = new LoanTrxDetailDao().getOpenLoanTrxDetailById(Integer.parseInt(request.getParameter("ad_member_id")));
			    double tot_laon_amt=0.0;
			    double loan_interest=0.0;
			    if(ltdbean.isEmpty()!=true){
			    	for(LoanTrxDetailBean bean:ltdbean){
					    LoanTrxDetailBean ltbn =new LoanTrxDetailDao().getOpenLoanBal(Integer.parseInt(request.getParameter("ad_member_id")), bean.getLoan_trx_id());;
				   		LoanTrxBean ltb = new LoanTrxDao().getLoanTrxByPrmryId(ltbn.getLoan_trx_id());
				   		double one_day_int=(ltb.getIntrest_rate());
				   		long diff = d.getTime() - ltbn.getTrx_date().getTime();
				   		long diffDays = diff / (24 * 60 * 60 * 1000);
				   		
				   		loan_interest =new BigDecimal( (ltbn.getBalance_amt()*one_day_int*diffDays)/36500).setScale(0, RoundingMode.HALF_UP).doubleValue();
				   		   
						   
				   		//loan_interest=((ltbn.getBalance_amt()*one_day_int*(diffDays/365.0))/100.0);
		
				   		//loan_interest=Math.ceil(interest);
		   		
	    %>
		
		<tr>
		<td><%=new TypeOfLoanDao().gettypeofloanById(ltb.getLoan_type()).getName() %></td>
		<td>Rs:<%=ltbn.getBalance_amt() %></td>
		
		<td>Interest On Loan Amount</td>
		<td>Rs:<%=loan_interest %></td>	
		<td>Rs:<%=ltbn.getBalance_amt()+loan_interest %>
		</tr>
		
		<%  
	    tot_laon_amt=ltbn.getBalance_amt()+loan_interest;
			    }
			    }
	    %>
	    <tr>
	    <td colspan="4" align="right">Total Recovery Amount</td>
	    <td style="font-weight: bold;font-size: 14px;"><%=tot_laon_amt %></td> 
	    </tr>
	    <tbody>
	    </table>
	</div>
	<!-- ------------------- -------------- End Current Position---------------------------------------------->
</div>
<!-- ------------------- -------------- End Tab Contain---------------------------------------------->
<script type="text/javascript">
$(document).ready(function(e) {
		var id = '<%=request.getParameter("ad_member_id") %>';
		$("#il_ad_member_id").val(id);
		var dataString = "action=view&ad_member_id="+id;
		 $.ajax({
             type: "POST",
             url: "AdMemberRegistration",
             data: dataString,
             dataType: "json",
            
             //if received a response from the server
             success: function( data, textStatus, jqXHR) {
                 //our country code was correct so we have some information to display
                 
                  if(data.success){
                	 //alert(data.MemberInfo.branch.bank_region.region);
                	 $("#name").html(data.MemberInfo.name);
                	  $("#ad_pf_no").html(data.MemberInfo.ad_pf_no);
                	  $("#ad_society_no").html(data.MemberInfo.ad_society_no);
                	  $("#father").html(data.MemberInfo.father);
                	  $("#husband").html(data.MemberInfo.husband);
                	  $("#dob").html(data.MemberInfo.dob);
                	  $("#ad_gender_id").html(data.MemberInfo.gender.gender);
                	  $("#ad_category_id").html(data.MemberInfo.category.category);
                	  $("#ad_member_type_id").html(data.MemberInfo.member_type.member_type);
                	  $("#ad_member_status_id").html(data.MemberInfo.member_status.member_status);
                	 
                      
                  } 
                  //display error message
                  else {
                      //$("#ajaxResponse").html("<div><b>Country code in Invalid!</b></div>");
                  }
             },
            
             //If there was no resonse from the server
             error: function(jqXHR, textStatus, errorThrown){
                  console.log("Something really bad happened " + textStatus);
                   //$("#ajaxResponse").html(jqXHR.responseText);
             }
  
         });         

});//end dom

function showloantrx(loan_trx_id){
	   var popup;
	   popup=	window.open ("view_loantrx_list.jsp?loan_trx_id="+loan_trx_id, "mywindow","location=1,status=1,scrollbars=1, width=1450,height=900, left=0,top=0");
	   popup.focus();
 }

</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>