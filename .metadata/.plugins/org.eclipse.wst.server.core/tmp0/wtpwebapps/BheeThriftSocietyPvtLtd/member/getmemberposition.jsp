<%@page import="Model.Dao.LoanTrxDetailDao"%>
<%@page import="Model.Bean.LoanTrxDetailBean"%>
<%@page import="Model.Dao.MemberShareDao"%>
<%@page import="Model.Bean.MemberShareBean"%>
<%@page import="Model.Dao.TransactionDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="Model.Dao.DividentDao"%>
<%@page import="Model.Bean.DividentBean"%>
<%@page import="Model.Dao.TypeOfLoanDao"%>
<%@page import="Model.Bean.TypeOfLoanBean"%>
<%@page import="Model.Dao.LoanCategoryDao"%>
<%@page import="Model.Bean.LoanCategoryBean"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="Model.Bean.LoanTrxBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Member.Bean.MemberLoginBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%
 HttpSession session1=request.getSession(false);
 MemberLoginBean user1=null;
 user1=(MemberLoginBean)session1.getAttribute("MemberLoginBean"); 
 MemberShareBean msbean = new MemberShareDao().getAllShareAmtByMemberId(user1.getMember().getAd_member_id());  
	LoanTrxDetailBean ltdbean = new LoanTrxDetailDao().getOpenLoanBal(user1.getMember().getAd_member_id());
	
	double thriftcr_amt=0;
	double thriftdr_amt=0;
	double thrift_amt=0;
	double loan_int=0;
	
	
	
	 thriftcr_amt=new TransactionDao().getTotalCrbyMemAndAcID(user1.getMember().getAd_member_id(), 3);
	 thriftdr_amt=new TransactionDao().getTotalDrbyMemAndAcID(user1.getMember().getAd_member_id(), 3);
	 thrift_amt=thriftcr_amt-thriftdr_amt;
	
	LoanTrxBean lbean =new LoanTrxDao().getLoanTrxById1(user1.getMember().getAd_member_id());
	
	
	DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
 Date today =  new Date();

//  System.out.print(ltdbean.getBalance_amt());
 if(ltdbean!=null){

 	
	   Date trxdate=ltdbean.getTrx_date();

 if(trxdate!=null){
	  long diff = trxdate.getTime() - today.getTime();
	 long diffDays = diff / (24 * 60 * 60 * 1000);

	
		 loan_int = (ltdbean.getBalance_amt()*lbean.getIntrest_rate()*diffDays)/36500;
 }
 }
%>
<table id="loan" id="currentposition" width="100%" id="fd" border="1" cellspacing="0" width="100%" style="border:1px solid black; margin-top:0px; font-family:'Times New Roman', Times, serif;" align="center">

<tr style="background-color: #06C; color: white; text-align: center; font-size: 14px;">
		 <td colspan="2">
		 Thrift AND Share Detail
		 </td>
		 
		</tr>
		<tr>
		<td>Thrift Amount</td><td>Rs:<input value="<%=thrift_amt %>"  readonly="readonly" id="thrift_amt" name='thrift_amt' style="background: none;border: none" /></td>
		</tr>
		<tr>
		<td>Interest On Thrift Amount</td><td>Rs:<input value='0.00'  readonly="readonly" id="thrift_int" name='thrift_int' style="background: none;border: none" /></td>	
		</tr>
		
		<tr>
		<td>Amount of Total <lable style="color: #2E64FE "><%=msbean.getQnt_of_share() %></lable> Share</td><td>Rs:<input value='<%=msbean.getShare_amt()  %>'  readonly="readonly" id="share_amt" name='share_amt' style="background: none;border: none" /> </td>	
		</tr>
		
		
		 
		<td colspan="2" align="center">
			<hr>
		</td>
		</tr>
		<tr>
		<td align="Right">Total Payable Amount &nbsp;&nbsp;&nbsp;&nbsp;</td><td>Rs:<input value='<%=thrift_amt+msbean.getShare_amt() %>'  readonly="readonly" id="payale_amt" name='payale_amt' style="background: none;border: none" /> </td>	
		</tr>
		<tr style="background:buttonface; ">
		 <td colspan="2">
		 Loan Detail
		 </td>
		 
		</tr>
		<tr>
		<td>Loan Amount</td><td>Rs:<input value='<%=ltdbean.getBalance_amt() %>'  readonly="readonly" id="loan_amt" name='loan_amt' style="background: none;border: none"/></td>
		</tr>
		<tr>
		<td>Interest On Loan Amount</td><td>Rs:<input value='<%=loan_int %>'  readonly="readonly" id="loan_int" name='loan_int' style="background: none;border: none"/></td>	
		</tr>
		<td colspan="2" align="center">
			<hr>
		</td>
		</tr>
		
		<tr>
		
		<td align="Right">Total Recovery Amount &nbsp;&nbsp;&nbsp;&nbsp;</td><td>Rs:<input value='<%=ltdbean.getBalance_amt()+loan_int %>'  readonly="readonly" id="recovery_amt" name='recovery_amt' style="background: none;border: none"/></td>	
		</tr>
		
		<tr style="background:buttonface; ">
		 <td colspan="2">
		&nbsp;
		 </td>
		 
		</tr>
		
		<tr>
		<td colspan="2">
			<table width="100%">
			<tr>
			<td>Net Payable Amt </td><td>:</td><td><input value="<%=(thrift_amt+msbean.getShare_amt())-(ltdbean.getBalance_amt()+loan_int) %>" name="net_payable" id="net_payable" readonly="readonly"  style="background: none;border: none"/></td>
			

</table>