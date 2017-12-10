<%@page import="Model.Dao.TypeOfLoanDao"%>
<%@page import="Model.Dao.LoanCategoryDao"%>
<%@page import="Model.Bean.LoanTrxBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<table class="table table-bordered">
<thead>
<tr>
<th>Select</th><th>SNO</th><th>Loan Id</th> <th>Loan Type</th><th>Loan Category</th><th>Loan Amt </th>
</tr>
</thead>
<tbody>
<%
String mid=request.getParameter("ad_member_id");

int i=0;
int ad_member_id=Integer.parseInt(mid);

LoanTrxDao dao =new LoanTrxDao();
	ArrayList<LoanTrxBean> alist=dao.getLoanTrxById(ad_member_id);
	if(alist!=null){
	for(LoanTrxBean bean:alist){
		
%>
<tr>
<td><input type="radio" name="loanchk" value="<%=bean.getLoan_trx_id() %>"/></td>
<td><%=++i %> </td>
</td><td><%=bean.getLoan_trx_id() %></td>
<td><%=new TypeOfLoanDao().gettypeofloanById(bean.getLoan_type()).getName() %></td>
<td><%=new LoanCategoryDao().getLoanCategoryById(bean.getLoan_cataegory()).getName() %></td>
<td><%=bean.getLoan_amt() %></td>


</tr>
<%} }%>
</tbody>
</table>