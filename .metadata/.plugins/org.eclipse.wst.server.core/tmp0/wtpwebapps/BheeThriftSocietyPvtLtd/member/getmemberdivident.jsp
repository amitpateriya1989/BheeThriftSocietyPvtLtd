<%@page import="Model.Dao.DividentDao"%>
<%@page import="Model.Bean.DividentBean"%>
<%@page import="Model.Dao.TypeOfLoanDao"%>
<%@page import="Model.Bean.TypeOfLoanBean"%>
<%@page import="Model.Dao.LoanCategoryDao"%>
<%@page import="Model.Bean.LoanCategoryBean"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="Model.Bean.LoanTrxBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Member.Bean.MemberLoginBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%
 HttpSession session1=request.getSession(false);
 MemberLoginBean user1=null;
 user1=(MemberLoginBean)session1.getAttribute("MemberLoginBean"); 
 %>
<table id="loan" id="share" width="100%" id="fd" border="1" cellspacing="0" width="100%" style="border:1px solid black; margin-top:0px; font-family:'Times New Roman', Times, serif;" align="center">
<tr style="background-color: #06C; color: white; text-align: center; font-size: 14px;">
<th>Sq No. </th>
<th>From Date</th>
<th>To Date </th>
<th>Share Amt </th>
<th>Divident</th>

</tr>
<%
int i=0;
ArrayList<DividentBean> list = new DividentDao().getAllDividentByMemberId(user1.getMember().getAd_member_id());
if(list.isEmpty()!=true){
	for(DividentBean db:list){
		
		
%>		
		<tr>
		<td><%=i++ %></td>
<td><%=db.getFromdate() %></td>
<td><%=db.getTodate() %></td>
<td><%=db.getTotalshare_amt() %></td>
<td><%=db.getTotal_intamt() %></td>

		</tr>
		
		
		<%
	}
}
%>



</table>