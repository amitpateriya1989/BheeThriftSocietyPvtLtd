<%@page import="Model.Dao.EmployeeDao"%>
<%@page import="Model.Bean.EmployeeBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.SalaryDao"%>
<%@page import="Model.Bean.SalaryBean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <table width="100%" border="1">
    <tr>
    <th>SNo</th>
    <th>Emp Id/No</th>
    <th>Name</th>
    
    <th>Month</th>
    <th>Pt Amt</th>
    </tr>
    
    
    
<%
try{
String ad_employee_id= request.getParameter("ad_employee_id");
String month= request.getParameter("month");
int adempid=0;
ArrayList<SalaryBean> pflist=null;
if(month!=""){	
	if(ad_employee_id.equals("All")!=true){		
		try{
			adempid=Integer.parseInt(ad_employee_id);
		}catch(NumberFormatException n){
			n.printStackTrace();
		}
		
		 pflist = new SalaryDao().getpf(month, adempid, "true");
		
		
	}else{
		
		 pflist = new SalaryDao().getpf(month, "true");
		
	}
}else{
		
	if(ad_employee_id.equals("All")!=true){
		try{
			adempid=Integer.parseInt(ad_employee_id);
		}catch(NumberFormatException n){
			n.printStackTrace();
		}
		
		pflist = new SalaryDao().getpf(adempid, "true");		
		
		
	}else{
		
		 pflist = new SalaryDao().getpf("true");
	}
	
}


if(pflist!=null){
	int i =0;
	double tpf=0;
	for(SalaryBean bean:pflist){
		
	tpf=bean.getPt_amt()+tpf;	
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
<td><%=tpf %></td>
</tr>	
<%
}

%>
</table>
<%}catch(Exception e){
	e.printStackTrace();
} %>