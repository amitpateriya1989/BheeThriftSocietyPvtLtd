<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%
int ad_account_id=Integer.parseInt(request.getParameter("ad_account_id"));

   
	ArrayList<MemberRegistrationBean> memberlist = new MemberRegistrationDao().getAllMemberHavingAcccount(ad_account_id);
	if (memberlist.isEmpty()!=true) {%>
		<option value="0" >---Select Member---</option>
	<%for (MemberRegistrationBean bean : memberlist) {
%>
	<option value="<%=bean.getAd_member_id()%>" ><%=bean.getAd_society_no()+" | "+bean.getName()%></option>	
<%} } %>

