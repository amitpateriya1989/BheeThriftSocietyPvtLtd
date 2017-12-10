

<%@page import="Model.Dao.ThriftTrxDao"%>
<%@page import="Model.Bean.ThriftViewBean"%>
<%@page import="java.util.ArrayList"%>
<%
String id=request.getParameter("ad_member_id");
double thrift_total=0.0;
if(id!=null){
int ad_member_id =0;

try {
ad_member_id = Integer.parseInt(id);

} catch (NumberFormatException e) {
	e.printStackTrace();
}
thrift_total=new ThriftTrxDao().getTotalThriftAmountByMemberId(ad_member_id);
out.print(thrift_total);
}
%>

