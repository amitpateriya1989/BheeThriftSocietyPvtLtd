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
<th>Loan Id </th>
<th>Loan Type</th>
<th>Loan Category </th>
<th>Loan Amt </th>
<th>Int Rate</th>
<th>Loan Id </th>
<th>Period(M)</th>
<th>Issue Date</th>
<th>End Date</th>
<th>Emi</th>
<th>Purpose </th>
</tr>
<%
ArrayList<LoanTrxBean> list = new LoanTrxDao().getAllLoanTrxBymemId(user1.getMember().getAd_member_id());
if(list.isEmpty()!=true){
	for(LoanTrxBean ltb:list){
		LoanCategoryBean lcb = new LoanCategoryDao().getLoanCategoryById(ltb.getLoan_cataegory());
		TypeOfLoanBean tolb= new TypeOfLoanDao().gettypeofloanById(ltb.getLoan_trx_id());
		
%>		
		<tr>
		<td><%=ltb.getLoan_trx_id() %></td>
<td><%=lcb.getName() %></td>
<td><%=tolb.getName() %></td>
<td><%=ltb.getLoan_amt() %></td>
<td><%=ltb.getIntrest_rate() %></td>
<td><%=ltb.getPeriod_month() %></td>
<td><%=ltb.getissue_date() %></td>
<td><%=ltb.getEnd_date() %></td>
<td><%=ltb.getEmi() %></td>
<td><%=ltb.getLoan_purpose() %></td>
		</tr>
		
		
		<%
	}
}
%>



</table>