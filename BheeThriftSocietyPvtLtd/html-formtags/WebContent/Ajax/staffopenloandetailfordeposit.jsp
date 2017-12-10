<%@page import="java.util.Date"%>
<%@page import="Model.Bean.LoanTrxBean"%>
<%@page import="Model.Dao.StaffLoanTrxDao"%>
<%@page import="Model.Dao.StaffLoanTrxDetailDao"%>
<%@page import="Model.Bean.LoanTrxDetailBean"%>
<%
String mid=request.getParameter("ad_employee_id");

int i=0;
int ad_employee_id=Integer.parseInt(mid);

LoanTrxDetailBean ltbean=new LoanTrxDetailBean();
LoanTrxDetailBean ltbn = new StaffLoanTrxDetailDao().getOpenLoanBal(ad_employee_id);
LoanTrxBean ltb = new StaffLoanTrxDao().getLoanTrxByPrmryId(ltbn.getLoan_trx_id());

double one_day_int=(ltb.getIntrest_rate()/100)/365;

Date d =(Date)session.getAttribute("openday");
long diff = d.getTime() - ltbn.getTrx_date().getTime();
long diffDays = diff / (24 * 60 * 60 * 1000);

double intrest=(ltbn.getBalance_amt()*one_day_int*diffDays)/100;
intrest=Math.ceil(intrest);
%>
<input type="hidden" value="<%=ad_employee_id %>"  name="admid"/>
<table  cellpadding="5" cellspacing="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Loan Deposit</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr >
						<td class="" >&nbsp;Loan Balance Amt</td>
						<td class="" >:</td>
						<td colspan="">
						<input name="loan_bal"	value="<%=ltbn.getBalance_amt() %>" style="border: none" />
								
						</td>
						<td>&nbsp;Last Deposit Date</td>
						<td class="" >:</td>
						<td>	<%=ltbn.getTrx_date() %>
								 
						</td>
						</tr>
						
						<tr >
						<td>&nbsp;Till Date Interest</td>
						<td class="" >:</td>
						<td>	<input name="intrest"	value="<%=intrest %>" style="border: none" />
									
						</td>
						<td>&nbsp;Total Payble Amount</td>
						<td class="" >:</td>
						<td> <input name="total_pay_amt"	value="<%=ltbn.getBalance_amt()+intrest %>" style="border: none" />
									
						</td>
						</tr>
						<tr>
						</tr>
											<tr >
						<td>&nbsp;Trx By</td>
						<td class="" >:</td>
						<td>	<select name="trx_by" ><option>cash</option><option>DD</option><option>Cheque</option><option>Adjustment</option></select>
								 
						</td>	
						<td>&nbsp;Cheque DD No</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="chk_no" id="chk_no" style="width: 170px;" >
								 
						</td>
						</tr>
					<tr >
						<td>&nbsp;Cheque DD Date</td>
						<td class="" >:</td>
						<td>	<input type="date"  name="chk_date" id="chk_date" style="width: 170px;" >
								 
						</td>
			
						<td>&nbsp;Cheque/DD Bank Code </td>
						<td class="" >:</td>
						<td>	<input type="text"  name="ad_branch_id" id="ad_branch_id" style="width: 170px;" >
								 
						</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="9" class=""  align="center" ><input type="Submit" value="submit"/></td>
					</tr>
        	
        </table>