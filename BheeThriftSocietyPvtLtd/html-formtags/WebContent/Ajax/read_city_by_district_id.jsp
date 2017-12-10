<%@page import="Model.Bean.CityBean"%>
<%@page import="Model.Dao.CityDao"%>
<%@page import="Model.Bean.DistrictBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.DistrictDao"%>

<option value="">--Select City--</option>
<%
	int ad_district_id=Integer.parseInt(request.getParameter("ad_district_id"));
	CityDao dao=new CityDao();
	ArrayList<CityBean> alist=dao.getAllCityNameByDistrictId(ad_district_id);
	if(alist!=null){
	for(CityBean bean:alist){%>
		 <option value="<%=bean.getAd_city_id()%>"><%=bean.getCity() %></option>
	<%}
}
%>


