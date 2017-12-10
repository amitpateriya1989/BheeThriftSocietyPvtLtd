
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberShareBean"%>
<%@page import="Model.Dao.MemberShareDao"%>
<%@page import="Model.Bean.ShareViewBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%

String id=request.getParameter("ad_member_id");


if(id!=null){
int ad_member_id =0;

try {
ad_member_id = Integer.parseInt(id);

} catch (NumberFormatException e) {
	e.printStackTrace();
}

%>


<table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th width="10%">Date</th>
					<th width="10%">Mem No.</th>
					<th width="10%">Name</th>
					<th width="10%">Batch No</th>
					<th width="10%">From</th>
					<th width="10%">To</th>
					<th width="10%">Qty</th>
					<th width="10%">Amt</th>
					<th width="10%">Trx By</th>
					<th width="10%">Status</th>
					
					
				</tr>
				</thead>
				<tbody>
		 <%
	   		ArrayList<MemberShareBean> list=new MemberShareDao().getMemberShareByMemberId(ad_member_id);
	   if(list.isEmpty()!=true){
		   for(MemberShareBean bean:list){%>
			   <tr>
			   <td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getDate_of_allocation().getTime()) %></td>
			   <td><%=new MemberRegistrationDao().getMemberName(bean.getAd_member_id()).getAd_society_no() %></td>
			   <td><%=new MemberRegistrationDao().getMemberName(bean.getAd_member_id()).getName() %></td>
			   <td><%=bean.getBatch_no() %></td>
			   <td><%=bean.getShare_no_form() %></td>
			   <td><%=bean.getShare_no_to() %></td>
			   <td><%=bean.getQnt_of_share() %></td>
			   <td><%=bean.getShare_amt() %></td>
			    <td><%=bean.getTrx_by() %></td>
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