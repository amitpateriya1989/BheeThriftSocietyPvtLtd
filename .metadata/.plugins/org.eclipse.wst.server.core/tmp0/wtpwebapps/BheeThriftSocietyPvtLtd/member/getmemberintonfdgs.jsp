<%@page import="Model.Member.Bean.MemberLoginBean"%>
<%@page import="Model.Dao.ThriftIntDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.ThriftIntBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <table id="fdgs" border="1" cellspacing="0" width="100%" style="border:1px solid black; margin-top:0px; font-family:'Times New Roman', Times, serif;" align="center">
<tr style="background-color: #06C; color: white; text-align: center; font-size: 14px;">
<th>From Date</th>
<th>To Date</th>
<th>Thrift Amt</th>
<th>Int Amt</th>
</tr>
    
    
<%

HttpSession session1=request.getSession(false);
MemberLoginBean user1=null;
user1=(MemberLoginBean)session1.getAttribute("MemberLoginBean");
ArrayList<ThriftIntBean> list = new ThriftIntDao().getAllThriftIntByMemberId(user1.getMember().getAd_member_id());
if(list.isEmpty()!=true){	
	for(ThriftIntBean tib:list){
		%>
		<tr>
		<td><%=tib.getFromdate() %></td>
		<td><%=tib.getTodate() %></td>
		<td><%=tib.getTotalthrift_amt()%></td>
		<td><%=tib.getTotal_intamt() %></td>
		</tr>
		
		
		<%
		
	}
}
%>
</table>
