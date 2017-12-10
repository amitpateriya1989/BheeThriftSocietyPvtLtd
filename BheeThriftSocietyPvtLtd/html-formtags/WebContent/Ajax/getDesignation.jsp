<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.DesignationDao"%>
<%@page import="Model.Bean.DesignationBean"%>
<option value="">-- Select Designation --</option>
<%
int ad_designation_type_id=Integer.parseInt(request.getParameter("desigType"));

DesignationDao desigdao=new DesignationDao();
ArrayList<DesignationBean> desglist=desigdao.getDesignationByType(ad_designation_type_id);
if(desglist!=null){
for(DesignationBean bean:desglist){%>
<option value="<%=bean.getAd_designation_id()%>"><%=bean.getDesignation() %></option>
<%}
}
%>