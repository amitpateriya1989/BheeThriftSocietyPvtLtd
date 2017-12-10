<%@page import="Model.Bean.AccountGroupBean"%>
<%@page import="Model.Dao.AccountGroupDao"%>
<%@page import="java.util.ArrayList"%>



<option value="">--Select Group--</option>
<%
	int ad_ac_type_id=Integer.parseInt(request.getParameter("ad_ac_type_id"));
	AccountGroupDao dao=new AccountGroupDao();
	ArrayList<AccountGroupBean> alist=dao.getAllAccountGroupByType(ad_ac_type_id);
	if(alist!=null){
		
	for(AccountGroupBean bean:alist){%>
		 <option value="<%=bean.getAd_ac_group_id()%>"><%=bean.getName() %></option>
	<%}
}
%>

