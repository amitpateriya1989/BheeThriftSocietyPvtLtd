<%@page import="java.text.SimpleDateFormat"%>
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

SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
String trxDate = sdf.format(ltbn.getTrx_date());
%>
<input type="hidden" value="<%=ad_employee_id %>"  name="admid"/>
<table  class="table table-bordered">
	<thead>
	   <tr><th colspan="4"><div class="text-center"> Staff Loan Deposite/ Close</div></th></tr>
	</thead>
	<tbody>
		<tr>
			<tr>
				<td>Loan Balance Amt :</td>
				<td><input class="form-control input-medium" name="loan_bal"	value="<%=ltbn.getBalance_amt() %>" /></td>
				<td>Last Deposit Date</td>
				<td><%=trxDate%></td>
			</tr>
			<tr>
				<td>Till Date Interest</td>
				<td><input class="form-control input-medium" name="intrest"	value="<%=intrest %>" /></td>
				<td>Total Payable Amount</td>
				<td><input class="form-control input-medium" name="total_pay_amt"	value="<%=ltbn.getBalance_amt()+intrest %>" /></td>
			</tr>
			<tr>
				<td>Transaction By</td>
				<td>
				<select class="form-control input-medium" name="trx_by" >
					<option value="cash">cash</option>
					<option value="DD">DD</option>
					<option value="Cheque">Cheque</option>
					<option value="Adjustment">Adjustment</option>
				</select>
				</td>
				<td>Cheque DD No.</td>
				<td><input class="form-control input-medium" type="text"  name="chk_no" id="chk_no" /></td>
			</tr>
			<tr>
				<td>Cheque DD Date</td>
				<td><input class="form-control input-medium date-picker" type="date"  name="chk_date" id="chk_date" /></td>
				<td>Cheque/DD Bank Code</td>
				<td><input class="form-control input-medium" type="text"  name="ad_branch_id" id="ad_branch_id" /></td>
			</tr>
	</tbody>
</table>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script type="text/javascript">
jQuery(function(){
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true});
});
</script>