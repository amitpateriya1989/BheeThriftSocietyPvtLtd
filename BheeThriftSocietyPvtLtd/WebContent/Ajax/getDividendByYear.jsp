<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Dao.MemberStatusDao"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Dao.DividentDao"%>
<%@page import="Model.Bean.DividentBean"%>
<%@page import="java.util.ArrayList"%>

<%
String div_year=request.getParameter("div_year");
String mem_id=request.getParameter("ad_member_id");
int ad_member_id=Integer.parseInt(mem_id);
%>
<table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th>Sno.</th>
					<th>Mem. No</th>
					<th>Name</th>
					<th>Mem Status</th>
					<th>Year</th>
					<th>ROI</th>
					<th>Share Qty</th>
					<th>Share Amt</th>
					<th>Div Amt</th>
					<th>Conv. Amt</th>
					<th>Total Amt</th>
					<th>Status</th>
					
					
				</tr>
				</thead>
				<tbody>
				<%
					int i=0;
				double total_pay=0.0;
				ArrayList<DividentBean> list2=null;
				MemberRegistrationBean member=null;
				if(div_year.equals("0")==true){
				    if(ad_member_id>0){
				    	list2=new DividentDao().getAllDividentByMemberId(ad_member_id);
				    }
				}else{
					if(ad_member_id==0){
					       list2=new DividentDao().getAllDividentByYear(div_year);
				    }else{
				    	list2=new DividentDao().getAllDividentByMemberIdAndYear(ad_member_id, div_year);
				    }
				}
				    if(list2.isEmpty()!=true && list2!=null){
				    	
				    	for(DividentBean bean:list2){
				    		member=new MemberRegistrationDao().getMemberName(bean.getAd_member_id());
				    		total_pay+=bean.getTotal_amt();
				    	%>	
				    	
				 <tr>
				   <td><%=++i %></td>
				   <td><%=member.getAd_society_no() %></td>
				   <td><%=member.getName() %></td>
				   <td><%=new MemberStatusDao().getMemberStatusById(member.getAd_member_status_id()).getMember_status() %></td>
				   <td><%=bean.getDiv_year() %></td>
				   <td><%=bean.getRoi() %></td>
				   <td><%=bean.getShare_qty() %></td>
				   <td><%=bean.getTotalshare_amt() %></td>
				   <td><%=bean.getTotal_intamt() %></td>
				   <td><%=bean.getConv_amt()%></td>
				   <td align="right"><%=bean.getTotal_amt()%> </td>
				   <td><%=bean.getPay_status()%> </td>
				   
				 </tr>
				<%
				}
				    }
				
				
				%>
				<tr><th colspan="10">Total Dividend</th><th><%=total_pay %></th><th></th></tr>
				</tbody>
			</table>
			
			<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
			<script type="text/javascript">
jQuery(function() {       
	jQuery('#dataTable1').DataTable( {
		"bPaginate": false
	} );
	});
</script>
	