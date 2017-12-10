




<%@page import="Model.Bean.AccountSubGroupBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.AccountSubGroupDao"%>
<option value="">--Select Sub Group--</option>
<%
	int ad_ac_group_id=Integer.parseInt(request.getParameter("ad_ac_group_id"));

	AccountSubGroupDao dao=new AccountSubGroupDao();
	ArrayList<AccountSubGroupBean> alist=dao.getAllAccountSubGroupByGroupId(ad_ac_group_id);
	if(alist!=null){
		
	for(AccountSubGroupBean bean:alist){%>
		 <option value="<%=bean.getAd_ac_subgroup_id()%>"><%=bean.getName() %></option>
	<%}
}
%>
