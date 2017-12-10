
<%@page import="Model.Dao.MemberShareDao"%>
<%@page import="Model.Bean.ShareViewBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%

String id=request.getParameter("loan_trx_id");
int loan_trx_id=0;
if(id!=null ){
try {
loan_trx_id=Integer.parseInt(id);

} catch (NumberFormatException e) {
	e.printStackTrace();
}

%>


 <table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th width="10%">Date</th>
					<th width="10%">Staff No.</th>
					<th width="10%">Name</th>
					<th width="10%">Batch No</th>
					<th width="10%">From</th>
					<th width="10%">To</th>
					<th width="10%">Qty</th>
					<th width="10%">Amt</th>
					<th width="20%">Certificate</th>
					
				</tr>
				</thead>
				<tbody>
		 <%
	   		ArrayList<ShareViewBean> list=new MemberShareDao().getAllMemberShareDetailByLoanId(loan_trx_id);
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
			   <td><a href="AdReport?action=share_certificate&ad_member_share_id=<%=bean.getAd_member_share_id()%>" class="btn btn-sm green" target="blank">Print</a></td>
			 </tr>  
		   <%}
	   }
	   
	   %>		
				
				
				</tbody>
			</table> 
			
			

<%}else{
	out.print("Date Not Found");
} %>