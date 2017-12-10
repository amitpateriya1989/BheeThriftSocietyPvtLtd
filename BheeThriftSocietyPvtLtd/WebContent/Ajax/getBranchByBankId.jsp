<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="java.util.ArrayList"%>
<%
String id=request.getParameter("ad_bank_id");
if(id!=null){
	int ad_bank_id=0;
	ArrayList<BankBranchBean> branchlist=null;
	try{
		ad_bank_id=Integer.parseInt(id);
		branchlist=new BankBranchDao().getAllBankBranchByBankId(ad_bank_id);
		if(branchlist.isEmpty()!=true){
			for(BankBranchBean bean:branchlist){%>
				<option value="<%=bean.getAd_branch_id()%>"><%=bean.getBranch_code()+"-"+bean.getBranch() %></option>
			<%}
		}
		
	}catch(NumberFormatException n){
		n.printStackTrace();
	}
}
%>