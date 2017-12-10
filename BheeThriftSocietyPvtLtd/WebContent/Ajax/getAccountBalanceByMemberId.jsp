

<%@page import="Model.Dao.ExcessTrxDao"%>
<%@page import="Model.Dao.MemberShareDao"%>

<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Dao.ThriftTrxDao"%>
<%@page import="Model.Bean.ThriftViewBean"%>
<%@page import="java.util.ArrayList"%>
<%
String id=request.getParameter("ad_member_id");
String acc_type=request.getParameter("acc_type");
double bal_total=0.0;
if(id!=null){
int ad_member_id =0;

try {
ad_member_id = Integer.parseInt(id);

} catch (NumberFormatException e) {
	e.printStackTrace();
}
if(acc_type.equals("thrift")){
bal_total=new ThriftTrxDao().getTotalThriftAmountByMemberId(ad_member_id);
}else if(acc_type.equals("share")){
	bal_total=new MemberShareDao().getAllShareBalanceByMemberId(ad_member_id);	
}else if(acc_type.equals("excess")){
	bal_total=new ExcessTrxDao().getAllExcessBalanceByMemberId(ad_member_id);		
}
out.print(bal_total);

}
%>
