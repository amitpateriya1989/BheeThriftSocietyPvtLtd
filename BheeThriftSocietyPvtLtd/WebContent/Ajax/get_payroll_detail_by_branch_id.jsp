
<%@page import="Model.Dao.PayrollAdviceDao"%>
<%@page import="Model.Bean.PayrollAdviseBean"%>
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

String id=request.getParameter("ad_branch_id");


if(id!=null){
int ad_branch_id =0;

try {
	ad_branch_id = Integer.parseInt(id);

} catch (NumberFormatException e) {
	e.printStackTrace();
}

%>


<table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th width="10%">Sno</th>
					<th width="10%">PF No</th>
					<th width="10%">MEM No.</th>
					<th width="10%">Name</th>
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
	   		ArrayList<PayrollAdviseBean> list=new PayrollAdviceDao().getAllPayrollAdvice(ad_branch_id);
		 int i=0;
	   if(list.isEmpty()!=true){
		   for(PayrollAdviseBean bean:list){%>
			   <tr>
			   <td><%=++i %></td>
			   <td><%=bean.getAd_pf_no() %></td>
			   <td><%=bean.getAd_society_no() %></td>
			   <td><%=bean.getName() %></td>
			   <td><%=bean.getBranch() %></td>
			   <td><%=bean.getFgds_fund() %></td>
			   <td><%=bean.getMainloan_emi() %></td>
			   <td><%=bean.getFestivalloan_emi() %></td>
			    <td><%=bean.getFgds_fund()+bean.getMainloan_emi()+bean.getFestivalloan_emi() %></td>
			     <td><%=bean.getMember_status() %></td>
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