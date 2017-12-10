<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>


<%
String id=request.getParameter("ad_branch_id");
int ad_branch_id=0;
if(id!=null){
	
	ad_branch_id=Integer.parseInt(id);
}

%>


<select name="ad_member_id" id="ad_member_id" class="form-control input-large" tabindex="0">
		 			<option value="0">--select--</option>
		 			<%MemberRegistrationDao mdao =new MemberRegistrationDao();
								 	ArrayList<MemberRegistrationBean> malist=mdao.getMemberByBranch(ad_branch_id);
								 	if(malist!=null){
								 	for(MemberRegistrationBean bean:malist){%>
								 <option value="<%=bean.getAd_member_id()%>"><%=bean.getAd_society_no()+" | "+bean.getName() %></option>
								 <%} 
								 }%>
											 			
		 			</select>	