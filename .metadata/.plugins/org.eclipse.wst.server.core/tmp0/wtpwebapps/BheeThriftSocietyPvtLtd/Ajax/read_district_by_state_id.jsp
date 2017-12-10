<%@page import="Model.Bean.DistrictBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.DistrictDao"%>


<option value="">--select District--</option>
<%
	int ad_state_id=Integer.parseInt(request.getParameter("ad_state_id"));
	DistrictDao dao=new DistrictDao();
	ArrayList<DistrictBean> alist=dao.getAllDistrictNameByStateId(ad_state_id);
	if(alist!=null){
	for(DistrictBean bean:alist){%>
		 <option value="<%=bean.getAd_district_id()%>"><%=bean.getDistrict() %></option>
	<%}
}
%>


