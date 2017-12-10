<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="Model.Bean.VoucherBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.VoucherDao"%>
<%
try{
String from=request.getParameter("from");
String to=request.getParameter("to");
Date fdate=null;
Date tdate=null;

try{
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	fdate=sdf.parse(from);
	tdate=sdf.parse(to);	
}catch(Exception e){
	e.printStackTrace();
	
}

%>
<center> Voucher List From <%=new SimpleDateFormat("dd/MM/yyyy").format(fdate) %> &nbsp; To <%=new SimpleDateFormat("dd/MM/yyyy").format(tdate) %>  </center>

<table class="table table-bordered">
	<tr>
		<th>S.No</th>
		<th>Trx No</th>
		<th>Voucher No </th>
		<th>Voucher Date</th>
		<th>Voucher Type</th>
		<th>Trx Type</th>
		<th>Trx From</th>
		<th>Amount</th>
		<th>Status</th>
		
	</tr>
	
	<%
	   VoucherDao dao=new VoucherDao();
		ArrayList<VoucherBean> voucherlist=dao.getAllVoucherByDate(fdate,tdate);
		if(voucherlist!=null){
			int i=0;
			for(VoucherBean bean:voucherlist){%>
		<tr class="tr" id="<%=bean.getAd_voucher_id() %>">		
		<td><%=++i %></td>
		<td><a  target="_blank" href="view_voucher1.jsp?ad_voucher_id=<%=bean.getAd_voucher_id() %>" ><%=bean.getTrx_no() %></a></td>
		<td><%=bean.getVno() %></td>
		<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getTrx_date()) %></td>
		<td><%=bean.getVtype() %></td>
		<td><%=bean.getVfrom() %></td>
		<td><%=bean.getDescription() %></td>
		<td><%=bean.getVamt() %></td>
		<td><%=bean.getStatus() %></td>
		
		</tr>		
			<%
			}
		}
	%>
</table>	
	<%}catch(Exception e){
	e.printStackTrace();
} %>
					