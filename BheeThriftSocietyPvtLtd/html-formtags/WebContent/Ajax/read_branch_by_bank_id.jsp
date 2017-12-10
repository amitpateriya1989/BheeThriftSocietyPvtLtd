<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Bean.AccountGroupBean"%>
<%@page import="Model.Dao.AccountGroupDao"%>
<%@page import="java.util.ArrayList"%>



<option value="">--Select Branch--</option>
<%
	int ad_bank_id=Integer.parseInt(request.getParameter("ad_bank_id"));
	BankBranchDao dao=new BankBranchDao();
	ArrayList<BankBranchBean> alist=dao.getAllBankBranchByBankId(ad_bank_id);
	if(alist!=null){
		
	for(BankBranchBean bean:alist){%>
		 <option value="<%=bean.getAd_branch_id()%>"><%=bean.getBranch() %></option>
	<%}
}
%>

