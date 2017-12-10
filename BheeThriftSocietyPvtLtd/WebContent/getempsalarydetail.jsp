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
    <th>Month Pay</th>
    <th>Grade</th>
    <th>Working Days</th>
    <th>WorkDay</th>
    <th>HoliDays</th>
    <th>Total Abs</th>
    <th>Payble Days</th>
    <th>Basic</th>
    <th>Da</th>
    <th>Convance</th>
    <th>Hra</th>
    <th>Medical</th>
    <th>Allowances</th>
    <th>Other</th>
    <th>Gross Amt</th>
    <th>Pf Amt</th>
    <th>Pt Amt</th>
    <th>Esi Amt</th>
    <th>TDS Amt</th>
    <th>Other Deduction</th>
    <th>Gross Deduction</th>
    <th>Net Salary</th>
    <th>Status</th>
    </tr>
   
    
<%
try{
String ad_employee_id= request.getParameter("ad_employee_id");
String month= request.getParameter("month");
int adempid=0;
ArrayList<SalaryBean> salarylist=null;
if(month!=""){	
	if(ad_employee_id.equals("All")!=true){		
		try{
			adempid=Integer.parseInt(ad_employee_id);
		}catch(NumberFormatException n){
			n.printStackTrace();
		}
		
		 salarylist = new SalaryDao().getsalary(month, adempid, "true");
		
		
	}else{
		
		 salarylist = new SalaryDao().getsalary(month, "true");
		
	}
}else{
		
	if(ad_employee_id.equals("All")!=true){
		try{
			adempid=Integer.parseInt(ad_employee_id);
		}catch(NumberFormatException n){
			n.printStackTrace();
		}
		
		salarylist = new SalaryDao().getsalary(adempid, "true");		
		
		
	}else{
		
		 salarylist = new SalaryDao().getsalary("true");
	}
	
}


if(salarylist!=null){
	int i =0;
	double tda=0;
	double convane=0;
	double hra=0;
	double mdcl=0;
	double alwnc=0;
	double otheramt=0;
	double grossamt=0;
	double pfamt=0;
	double ptamt=0;
	double esi=0;
	double tds=0;
	double otherdeduction=0;
	double grossdeduction=0;
	double natsalary=0;
	for(SalaryBean bean:salarylist){
		
		tda=bean.getDa()+tda;		
		convane=bean.getConvey()+convane;
		hra=bean.getHra()+hra;
		mdcl=bean.getMdcl()+mdcl;
		alwnc=bean.getAlwnc()+alwnc;
		otheramt=bean.getOther_amt()+otheramt;
		grossamt=bean.getGross_amt()+grossamt;
		pfamt=bean.getPf_amt()+pfamt;
		ptamt=bean.getPt_amt()+ptamt;
		esi=bean.getEsi_amt()+esi;
		tds=bean.getTds_amt()+tds;
		otherdeduction=bean.getOther_deduction()+otherdeduction;	
		grossdeduction=bean.getGross_deduction()+grossdeduction;	
		natsalary=bean.getNet_salary()+natsalary;
		
%>
<tr>
<td><%=++i %></td>
<% EmployeeBean ebean = new EmployeeDao().getEmployeeNameById(bean.getAd_employee_id()); %>
<td><%=ebean.getEmployee_id() %></td>
<td><%=ebean.getName() %></td>
<td><%=bean.getMonth_name() %></td> 
    <td><%=bean.getMonthly_pay() %></td>
    <td><%=bean.getAd_grade_id() %></td>
    <td><%=bean.getWorking_days() %></td>
    <td><%=bean.getWorked_days() %></td>
    <td><%=bean.getHolidays() %></td>
    <td><%=bean.getTot_absent() %></td>
    <td><%=bean.getPayble_days() %></td>
    <td><%=bean.getBasic() %></td>
    <td><%=bean.getDa() %></td>
    <td><%=bean.getConvey() %></td>
    <td><%=bean.getHra() %></td>
    <td><%=bean.getMdcl() %></td>
    <td><%=bean.getAlwnc() %></td>
    <td><%=bean.getOther_amt()%></td>
    <td><%=bean.getGross_amt() %></td>
    <td><%=bean.getPf_amt()%></td>
    <td><%=bean.getPt_amt() %></td>
    <td><%=bean.getEsi_amt() %></td>
    <td><%=bean.getTds_amt() %></td>
    <td><%=bean.getOther_deduction() %></td>
    <td><%=bean.getGross_deduction() %></td>
    <td><%=bean.getNet_salary() %></td>
    <td><%=bean.getStatus() %></td>
</tr>

<%		
		
	}
	%>
	<tr>
	<td align="left" colspan="12">Total</td>

    <td><%=tda %></td>
    <td><%=convane %></td>
    <td><%=hra %></td>
    <td><%=mdcl %></td>
    <td><%=alwnc %></td>
    <td><%=otheramt%></td>
    <td><%=grossamt %></td>
    <td><%=pfamt%></td>
    <td><%=ptamt %></td>
    <td><%=esi %></td>
    <td><%=tda %></td>
    <td><%=otherdeduction %></td>
    <td><%=grossdeduction %></td>
    <td><%=natsalary %></td>
    <td></td>
	</tr>	
	<%	
}

%>
</table>
<%}catch(Exception e){
	e.printStackTrace();
} %>