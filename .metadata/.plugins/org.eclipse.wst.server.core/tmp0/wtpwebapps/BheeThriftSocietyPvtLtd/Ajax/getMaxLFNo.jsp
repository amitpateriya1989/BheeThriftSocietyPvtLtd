<%@page import="Model.Dao.LedgerAccountDao"%>
<%
String id=request.getParameter("ad_ac_subgroup_id");
if(id!=null){
	int ad_ac_subgroup_id=0;
	int lf_no=0;
	try{
		ad_ac_subgroup_id=Integer.parseInt(id);
		lf_no=new LedgerAccountDao().getLedgerMaxLFNoBySubGroup(ad_ac_subgroup_id);
		System.out.print(lf_no);
		lf_no++;
		
		out.print(lf_no);
		
	}catch(NumberFormatException n){
		n.printStackTrace();
	}
}
%>