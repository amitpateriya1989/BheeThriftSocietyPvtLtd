<%@page import="Model.Dao.EmployeeDao"%>
<%@page import="Model.Bean.EmployeeBean"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.SalaryDao"%>
<%@page import="Model.Bean.SalaryBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <table width="100%" border="1">
    <tr>
	    <th>SNo</th>
	    <th>Emp ID/No</th>
	    <th>Emp Name</th>
	    <th>Month</th>
	    <th>Pt Amt</th>
    </tr>
    
<%
try{
String ad_employee_id= request.getParameter("ad_employee_id");
String month= request.getParameter("month");
int adempid=0;
ArrayList<SalaryBean> ptlist=null;
if(month!=""){	
	if(ad_employee_id.equals("All")!=true){		
		try{
			adempid=Integer.parseInt(ad_employee_id);
		}catch(NumberFormatException n){
			n.printStackTrace();
		}
		
		 ptlist = new SalaryDao().getpt(month, adempid, "true");
		
		
	}else{
		
		 ptlist = new SalaryDao().getpt(month, "true");
		
	}
}else{
		
	if(ad_employee_id.equals("All")!=true){
		try{
			adempid=Integer.parseInt(ad_employee_id);
		}catch(NumberFormatException n){
			n.printStackTrace();
		}
		
		ptlist = new SalaryDao().getpt(adempid, "true");		
		
		
	}else{
		
		 ptlist = new SalaryDao().getpt("true");
	}
	
}


if(ptlist!=null){
	int i =0;
	double tpt=0;
	for(SalaryBean bean:ptlist){
		
		tpt=bean.getPt_amt()+tpt;		
%>
<tr>
<td><%=++i %></td>
<% EmployeeBean ebean = new EmployeeDao().getEmployeeNameById(bean.getAd_employee_id()); %>
<td><%=ebean.getEmployee_id() %></td>
<td><%=ebean.getName() %></td>
<td><%=bean.getMonth_name() %></td>
<td><%=bean.getPt_amt() %></td>
</tr>

<%		
		
	}
	%>
	<tr>
	<td></td>
	<td colspan="3">Total</td>
	<td><%=tpt %></td>
	</tr>	
	<%	
}

%>
</table>
<%}catch(Exception e){
	e.printStackTrace();
} %>