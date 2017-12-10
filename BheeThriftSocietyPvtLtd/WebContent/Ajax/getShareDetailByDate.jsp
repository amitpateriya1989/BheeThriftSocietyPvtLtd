
<%@page import="Model.Dao.MemberShareDao"%>
<%@page import="Model.Bean.ShareViewBean"%>
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


<table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th width="10%">Date</th>
					<th width="10%">Staff No.</th>
					<th width="20%">Name</th>
					<th width="10%">Batch No</th>
					<th width="10%">From</th>
					<th width="10%">To</th>
					<th width="10%">Qty</th>
					<th width="10%">Amt</th>
					<th width="10%">Status</th>
					
				</tr>
				</thead>
				<tbody>
		 <%
	   		ArrayList<ShareViewBean> list=new MemberShareDao().getAllMemberShareDetail(from, to);
	   if(list.isEmpty()!=true){
		   for(ShareViewBean bean:list){%>
			   <tr>
			   <td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getDate_of_allocation().getTime()) %></td>
			   <td><%=bean.getAd_society_no() %></td>
			   <td><%=bean.getName() %></td>
			   <td><%=bean.getBatch_no() %></td>
			   <td><%=bean.getShare_no_form() %></td>
			   <td><%=bean.getShare_no_to() %></td>
			   <td><%=bean.getQnt_of_share() %></td>
			   <td><%=bean.getShare_amt() %></td>
			   <td><%=bean.getStatus() %></td>
			 </tr>  
		   <%}
	   }
	   
	   %>		
				
				
				</tbody>
			</table>
			
			<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
			<script type="text/javascript">
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	});
</script>

<%}else{
	out.print("Date Not Found");
} %>