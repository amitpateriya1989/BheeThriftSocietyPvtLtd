<%@page import="Model.Dao.AccountTypeDao"%>
<%@page import="Model.Dao.AccountSubGroupDao"%>
<%@page import="Model.Bean.AccountSubGroupBean"%>
<%@page import="Model.Dao.AccountGroupDao"%>
<%@page import="Model.Bean.AccountGroupBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Bean.AccountTypeBean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<center>
Ledger List Of  
<% 
try{
String ad_ac_type_id =request.getParameter("ad_ac_type_id");
AccountTypeBean atb=new AccountTypeDao().getAccountTypeById(Integer.parseInt(ad_ac_type_id));  out.print(atb.getName());%>

</center>
<table width="100%" border="1">
<tr>
<th>Group</th><th>Sub Group</th><th>Ledger</th><th>OPT</th>
</tr>





     <%
  
     
     ArrayList<LedgerAccountBean> llist = new LedgerAccountDao().getAllLedgerAccount(Integer.parseInt(ad_ac_type_id));
    if(llist.isEmpty()!=true){
    	for(LedgerAccountBean bean:llist ){
    		
  		AccountGroupBean gb = new AccountGroupDao().getAccountGroupById(bean.getAd_ac_group_id());
  		AccountSubGroupBean sgb = new AccountSubGroupDao().getAccountSubGroupById(bean.getAd_ac_subgroup_id());
    	
    	
    	%>
    	
    	<tr>
<td><%=gb.getName() %></td><td><%=sgb.getName() %></td><td><%= bean.getAc_name() %></td><td><a href="edit_account1.jsp?ad_account_id=<%=bean.getAd_account_id()%>">edit</a></td>
</tr>
    	
    <%		
    		
    	}
    	
    }
    
    %>
    
    </table>
    <%}catch(Exception e){
	e.printStackTrace();
} %>