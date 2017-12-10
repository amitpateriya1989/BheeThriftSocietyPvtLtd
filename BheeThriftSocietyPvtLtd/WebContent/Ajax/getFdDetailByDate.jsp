<%@page import="Model.Dao.FdTrxDao"%>
<%@page import="Model.Bean.FdViewBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%

String str_from=request.getParameter("from");
String str_to=request.getParameter("to");
DateFormat formatter ; 
Date from =null; 
Date to =null;
if(str_from!=null && str_to!=null){
formatter = new SimpleDateFormat("dd/MM/yyyy");
try {
from = formatter.parse(str_from);
to = formatter.parse(str_to);
} catch (ParseException e) {
	e.printStackTrace();
}

%>
<table id="dataTable1"
	class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>Sno.</th>
			<th>Staff No.</th>
			<th>Name</th>
			<th>FDR No.</th>
			<th>FD Name</th>
			<th>Category</th>
			<th>FD Amt</th>
			<th>ROI</th>
			<th>Start</th>
			<th>End</th>
			<th>Maturity Amt</th>
			<th>Status</th>


		</tr>
	</thead>
	<tbody>
		<%
									    ArrayList<FdViewBean> list=new FdTrxDao().getFdDetailByDate(from, to);
													int i=0;
									     if(list.isEmpty()!=true){
									    	 for(FdViewBean bean:list){
									    		 %>

		<tr>

			<td><%=++i %></td>
			<td><%=bean.getSociety_no() %></td>
			<td><%=bean.getMember_name() %></td>
			<td><%=bean.getFd_no() %></td>
			<td><%=bean.getFd_name() %></td>
			<td><%=bean.getFd_category() %></td>
			<td><%=bean.getFd_amt()%></td>
			<td><%=bean.getRoi() %></td>
			<td>
				<% if(bean.getOpening_date()!=null){out.print(new SimpleDateFormat("dd/MM/yyyy").format(bean.getOpening_date().getTime()));}else{out.print("-");}
																						%>
			</td>

			<td>
				<%if(bean.getMaturity_date()!=null){out.print(new SimpleDateFormat("dd/MM/yyyy").format(bean.getMaturity_date().getTime()));}else{out.print("-");} %>
			</td>
			<td><%=bean.getMaturityamt() %></td>
			<td><%=bean.getRemark() %></td>
		</tr>
		<%
									}
									     }
									%>


	</tbody>
</table>
<%
											}
											%>

<script type="text/javascript"
	src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript">
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	});
</script>
