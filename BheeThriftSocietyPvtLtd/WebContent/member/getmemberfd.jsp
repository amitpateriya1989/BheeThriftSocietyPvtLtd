<%@page import="Model.Dao.TypeOfFdDao"%>
<%@page import="Model.Bean.TypeOfFdBean"%>
<%@page import="Model.Dao.FdCategoryDao"%>
<%@page import="Model.Bean.FdCategoryBean"%>
<%@page import="Model.Dao.FdRoiDao"%>
<%@page import="Model.Bean.FdRoiBean"%>
<%@page import="Model.Member.Bean.MemberLoginBean"%>
<%@page import="Model.Bean.FdTrxBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.FdTrxDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String fdno = request.getParameter("fdno");
HttpSession session1=request.getSession(false);
MemberLoginBean user1=null;
user1=(MemberLoginBean)session1.getAttribute("MemberLoginBean");
ArrayList<FdTrxBean> list= new FdTrxDao().getFdTrxByMemIdFdno(user1.getMember().getAd_member_id(), Integer.parseInt(fdno));
%>

<table width="100%" id="fd" border="1" cellspacing="0" width="100%" style="border:1px solid black; margin-top:0px; font-family:'Times New Roman', Times, serif;" align="center">
<tr style="background-color: #06C; color: white; text-align: center; font-size: 14px;">
<th>FD No</th>
<th>Opening Date</th>
<th>Maturity Date</th>
<th>Fd Amt</th>
<th>Time Period(M)</th>
<th>Int Rate</th>
<th>int amt</th>
<th>Maturity Amt</th>
<th>Status</th>
</tr>
<%

if(list.isEmpty()!=true){
	for(FdTrxBean fdb:list){	
		
		FdRoiBean fdrb = new FdRoiDao().getFdRoiById(fdb.getAd_fd_roi_id());
		//FdCategoryBean fdcb =new FdCategoryDao().getFdCategoryById(fdrb.getAd_fd_category_id());
		//TypeOfFdBean tofdb= new TypeOfFdDao().gettypeoffdById(fdrb.getAd_type_of_fd_id());
		
		%><tr>
		<td><%=fdb.getFd_no() %></td>
<td><%=fdb.getOpening_date() %></td>
<td><%=fdb.getMaturity_date() %></td>
<td><%=fdb.getFd_amt() %></td>
<td><%=fdrb.getTime_period() %></td>
<td><%=fdrb.getRoi() %></td>
<td><%=fdb.getFd_amt()*((fdb.getFd_amt()*fdrb.getRoi()/100)/12)*fdrb.getTime_period() %></td>
<td><%=fdb.getFd_amt()+((fdrb.getRoi()/100)/12)*fdrb.getTime_period() %></td>
<td><%=fdb.getRemark() %></td>
		</tr>
	<%	
	}
}


%>

</table>