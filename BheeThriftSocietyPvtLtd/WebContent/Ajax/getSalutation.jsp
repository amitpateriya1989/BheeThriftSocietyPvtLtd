<%@page import="Model.Bean.SalutationBean"%>
<%@page import="Model.Dao.SalutationDao"%>
<%@page import="java.util.ArrayList"%>


<%
int gender_id = Integer.parseInt(request.getParameter("gender"));
SalutationDao dao=new SalutationDao();
ArrayList<SalutationBean> alist=dao.getSalutationByGenderId(gender_id);
for(SalutationBean bean:alist){
	if(alist!=null){
%>
<option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
<%} }%>