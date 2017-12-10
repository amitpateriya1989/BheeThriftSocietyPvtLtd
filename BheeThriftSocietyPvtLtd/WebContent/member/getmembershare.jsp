<%@page import="java.util.ArrayList"%>
<%@page import="Model.Member.Bean.MemberLoginBean"%>
<%@page import="Model.Dao.MemberShareDao"%>
<%@page import="Model.Bean.MemberShareBean"%>
<%@page import="Model.Dao.ShareDao"%>
<%@page import="Model.Bean.ShareBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%HttpSession session1=request.getSession(false);
MemberLoginBean user1=null;
user1=(MemberLoginBean)session1.getAttribute("MemberLoginBean"); %>
 <table id="share" width="100%" id="fd" border="1" cellspacing="0" width="100%" style="border:1px solid black; margin-top:0px; font-family:'Times New Roman', Times, serif;" align="center">
		 <tr style="background-color: #06C; color: white; text-align: center; font-size: 14px;"><td>
		 <th>Betch No</th>
		  <th>Share No From</th>
		  <th>Share No To</th>
		  <th>Qnt Of Share</th>
		   <th>Share Amt</th>
		 </td>
		 </tr>
		 <%
		 
		 ArrayList<MemberShareBean> list = new MemberShareDao().getMemberShareByMemberId(user1.getMember().getAd_member_id());
		 if(list.isEmpty()!=true){			 
			for(MemberShareBean msb:list){
				
				%>
				<tr>
				<th><%=msb.getBatch_no() %></th>
		  <th><%=msb.getShare_no_form() %></th>
		  <th><%=msb.getShare_no_to() %></th>
		  <th><%=msb.getQnt_of_share() %></th>
		   <th><%=msb.getShare_amt() %></th>
				</tr>
				<%
			}
		 }
		 
		 %>
		 
		 
		 <tr>
		 
		 
		 </tr>
		 
		 </table>