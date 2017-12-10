<%@page import="Model.Bean.PostingDetailBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.PostingDetailDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
try{
	String ad_member_id=request.getParameter("ad_member_id");

%>
<table class="table table-bordered" id="dataTable1">
<thead>
<tr>
<th>Branch code</th>
<th>Desig. Type</th>
<th>Desig. </th>
<th>Dept. </th>
<th>Form</th>
<th>To</th>
<th>Status</th>
<th>Action</th>
</tr>
</thead>
<tbody>
<%
if(ad_member_id.equals("0")!=true && ad_member_id.equals("")!=true){	
	int amid=0;
	try{
		amid=Integer.parseInt(ad_member_id);
	}catch(NumberFormatException n){
		n.printStackTrace();
		
	}
	ArrayList<PostingDetailBean> alist = new PostingDetailDao().getAllPostingDetailWithJoin(amid);	
	
	if(alist!=null){
	 	for(PostingDetailBean bean:alist){%>
	 	<tr class="tr" id="<%= bean.getAd_posting_detail_id() %>" align="center" >
			<td><%=bean.getBranch_code()%> </td>
			<td><%=bean.getDesignation_type() %></td>
			<td><%=bean.getDesignation() %> </td>
			<td><%=bean.getDepartment() %> </td>
			<td><% if(bean.getFormdate()!=null)%><%=bean.getFormdate() %></td>
			<td><% if(bean.getTodate()!=null)%><%=bean.getTodate() %></td>
			<td><% if(bean.isIsactive()==true){ %>
				<span class="badge badge-primary">Active</span>
				<% }else{ %>
				<span class="badge badge-warning">Inactive</span>
				<%} %>
			</td>
			<td><a class="btn btn-xs green" target="_blank" href="edit_posting_detail.jsp?ad_posting_detail_id=<%=bean.getAd_posting_detail_id()%>&ad_member_id=<%=ad_member_id%>">
				  <i class="fa fa-edit"></i> Edit</a>
			</td>
		</tr>
<%}}}%>
</tbody>
</table>
<table class="table table-bordered">
<tr>
<td></td>
<td colspan="7">
<a class="btn btn-md blue" href="adpostingdetail.jsp?ad_member_id=<%=ad_member_id%>">
<i class="fa fa-pencil"></i> Add</a>
</td>
</tr>
</table>
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript">
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>