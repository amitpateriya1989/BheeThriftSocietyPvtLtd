<%@page import="java.io.PrintWriter"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%
int ad_account_id=Integer.parseInt(request.getParameter("ad_account_id"));
LedgerAccountDao dao=new LedgerAccountDao();
LedgerAccountBean access=dao.getLedgerAccountById(ad_account_id);


if(access.getAc_for().equals("Member"))
out.print("Yes");
else
out.print("No");
%>
