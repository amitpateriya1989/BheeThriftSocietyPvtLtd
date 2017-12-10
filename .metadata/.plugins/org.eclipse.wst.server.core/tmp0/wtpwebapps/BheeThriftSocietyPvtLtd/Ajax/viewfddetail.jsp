
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Bean.FdViewBean"%>
<%@page import="Model.Dao.FdCategoryDao"%>
<%@page import="Model.Bean.FdCategoryBean"%>
<%@page import="Model.Dao.TypeOfFdDao"%>
<%@page import="Model.Bean.TypeOfFdBean"%>
<%@page import="Model.Dao.FdRoiDao"%>
<%@page import="Model.Bean.FdRoiBean"%>
<%@page import="Model.Dao.FdTrxDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.FdTrxBean"%>




	
	<table class="table table-bordered">
	  <tr>
		<th>Sno</th>
		<th>FD Name</th>
		<th>FD No</th>
		<th>FD Amt</th>
		<th>ROI</th>
		<th>Period</th>
		<th>Int. Amt</th>
		<th>Maturity Amt</th>
		<th>Opening Date </th>
		<th>Maturity Date</th>
		<th>Remark</th>	
	  </tr>
	  <%
	    
	    int	ad_member_id=Integer.parseInt(request.getParameter("ad_member_id"));
		FdTrxBean fdb = new FdTrxBean();
		fdb.setAd_member_id(ad_member_id);
		ArrayList<FdViewBean> listfd=new FdTrxDao().getAllFdDetailByMemId(fdb.getAd_member_id());
		int i=0;
		if(listfd.isEmpty()!=true){
			for(FdViewBean bean:listfd){%>
		<tr>
			<td><%=++i %></td>
			<td><%=bean.getFd_name() %></td>
			<td><%=bean.getFd_no() %></td>
			<td><%=bean.getFd_amt() %></td>
			<td><%=bean.getRoi() %></td>
			<td><%=bean.getTime_period() %></td>
			<td><%=bean.getInterest_amt() %></td>
			<td><%=bean.getMaturityamt() %></td>
			<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getOpening_date()) %> </td>
			<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getMaturity_date())%></td>
			<td><%=bean.getRemark() %></td>	 
	   </tr>
	  <%} 
	  }%>	
	</table>
