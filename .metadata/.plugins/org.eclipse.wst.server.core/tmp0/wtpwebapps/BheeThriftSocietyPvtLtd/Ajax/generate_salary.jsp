<%@page import="java.text.DateFormatSymbols"%>
<%@page import="Model.Dao.EmployeeDao"%>
<%@page import="Model.Bean.GradeBean"%>
<%@page import="Model.Dao.GradeDao"%>
<%@page import="Model.Bean.SalaryBean"%>
<%@page import="Model.Dao.SalaryDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@include file= "../validation.html"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<table border="1" cellpadding="5" cellspacing="0" align="center" width="100%" style="background-color: white;margin-top: 20px; overflow: scroll; ">
	<tr>
		<td style="background-color: blue;color:white;text-align: center;" colspan="25">Generated Salary For The Month of <%=request.getParameter("month") %></td>
	</tr>
	<tr style="background-color: green;color:white;text-align: center;">
		<td >Empid</td><td >Month/Year</td><td >Name</td><td >Monthly</td><td >Wrk Day</td><td >Wrd Day</td><td >Holiday</td><td >Absent</td><td >Payble Days</td>
		<td >Basic</td><td >DA</td><td >HRA</td><td >Conv.</td><td >Med</td><td >Allow.</td><td >Other</td>
		<td >Gross Sal</td><td >PF</td><td >PT</td><td >ESI</td><td >Tds</td><td >Other_ded</td><td >Gross Ded</td><td >Net Salary</td>
		<td>OPT</td>
	</tr>
    <% 
    	String month=request.getParameter("month");
    	String status=request.getParameter("status");
    	DateFormat format;
	 	format=new SimpleDateFormat("yyyy-MM");
	  	Date parse;
	  	String month_name=null;
	  try{
		  parse=format.parse(month);
		  Calendar c=Calendar.getInstance();
		  c.setTime(parse);
		  int month_no= c.get(Calendar.MONTH);		
		  month_name=  new DateFormatSymbols().getMonths()[month_no-1];
	  }catch(Exception e){
		  e.printStackTrace();
		  
	  }
    	SalaryDao saldao=new SalaryDao();
    	ArrayList<SalaryBean> list=saldao.getAllSalary(month,status);
    	int i=0;
    	for(SalaryBean bean:list){
    	GradeDao grddao=new GradeDao();
			GradeBean grdbean=grddao.getGradeById(bean.getAd_grade_id());
			%>
    	<tr>
    	
    	<input type="hidden" name="phra_<%=i%>" id="phra_<%=i%>" value="<%=grdbean.getHra() %>" />
    	<input type="hidden" name="pconv_<%=i%>" id="pconv_<%=i%>" value="<%=grdbean.getConvey() %>" />
    	<input type="hidden" name="pmed_<%=i%>" id="pmed_<%=i%>" value="<%=grdbean.getMdcl() %>" />
    	<input type="hidden" name="pallow_<%=i%>" id="pallow_<%=i%>" value="<%=grdbean.getAlwnc() %>" />
    	<input type="hidden" name="ad_employee_id_<%=i%>" id="ad_employee_id_<%=i%>" value="<%=bean.getAd_employee_id() %>" />
    		
    	<td ><input type="text" name="emp_id_<%=i%>" id="emp_id_<%=i%>" value="<%=new EmployeeDao().getEmployeeById(bean.getAd_employee_id()).getEmployee_id() %>" style="width:70px; height: 20px;"/ readonly></td>
    	<td ><input type="text" name="month_<%=i%>" id="month_<%=i%>" value="<%=bean.getMonth_name() %>" style="width:70px; height: 20px; " readonly/ ></td>
    	<td ><label name="emp_name_<%=i%>" id="emp_name_<%=i%>"><%=new EmployeeDao().getEmployeeById(bean.getAd_employee_id()).getName() %></label></td>
    	<td ><input type="text" name="monthly_sal_<%=i%>" id="monthly_sal_<%=i%>" value="<%=bean.getMonthly_pay() %>" style="width:70px; height: 20px;background-color:#FF9" readonly/></td>
    	<td ><input type="text" name="wkgdays_<%=i%>" id="wkgdays_<%=i%>" value="<%=bean.getWorking_days() %>" style="width:70px; height: 20px;background-color:#FF9" readonly/></td>
    	<td ><input type="text" name="wkdays_<%=i%>" id="wkdays_<%=i%>"  value="<%=bean.getWorked_days() %>" style="width:70px; height: 20px;background-color:#FF9" readonly/></td>
    	<td ><input class="amt" type="text" name="holidays_<%=i%>" id="holidays_<%=i%>" onblur="cal(<%=i %>)" value="<%=bean.getHolidays() %>" style="width:70px; height: 20px;"/></td>
    	<td ><input  class="amt" type="text" name="absent_<%=i%>" id="absent_<%=i%>" onblur="cal(<%=i %>)"value="<%=bean.getTot_absent() %>" style="width:70px; height: 20px;"/></td>
    	<td><input type="text" name="payble_<%=i%>" id="payble_<%=i%>" value="<%=bean.getPayble_days() %>" style="width:70px; height: 20px;background-color:#FF9" readonly/></td>
    	<td ><input type="text" name="basic_<%=i%>" id="basic_<%=i%>" value="<%=bean.getBasic() %>" style="width:70px; height: 20px;background-color:#FF9" readonly="readonly"/></td>
    	<td ><input type="text" name="da_<%=i%>" id="da_<%=i%>" value="<%=bean.getDa() %>" style="width:70px; height: 20px;background-color:#FF9" readonly="readonly"/></td>
    	<td ><input type="text" name="hra_<%=i%>" id="hra_<%=i%>" value="<%=bean.getHra() %>" style="width:70px; height: 20px;background-color:#FF9" readonly="readonly"/></td>
    	<td ><input type="text" name="conv_<%=i%>" id="conv_<%=i%>" value="<%=bean.getConvey() %>" style="width:70px; height: 20px;background-color:#FF9" readonly="readonly"/></td>
    	<td ><input type="text" name="med_<%=i%>" id="med_<%=i%>" value="<%=bean.getMdcl() %>" style="width:70px; height: 20px;background-color:#FF9" readonly="readonly"/></td>
    	<td ><input type="text" name="allow_<%=i%>" id="allow_<%=i%>" value="<%=bean.getAlwnc() %>" style="width:70px; height: 20px;background-color:#FF9" readonly="readonly"/></td>
    	<td ><input type="text" name="other_<%=i%>" id="other_<%=i%>" onblur="cal(<%=i %>)"value="<%=bean.getOther_amt() %>" style="width:70px; height: 20px;"/></td>
    	<td ><input type="text" name="gross_<%=i%>" id="gross_<%=i%>" value="<%=bean.getGross_amt() %>" style="width:70px; height: 20px;background-color:#FF9" readonly="readonly"/></td>
    	<td ><input type="text" name="pf_<%=i%>" id="pf_<%=i%>" value="<%=bean.getPf_amt() %>" style="width:70px; height: 20px;background-color:#FF9" readonly="readonly"/></td>
    	<td ><input type="text" name="pt_<%=i%>" id="pt_<%=i%>" value="<%=bean.getPt_amt() %>" style="width:70px; height: 20px;background-color:#FF9" readonly="readonly"/></td>
    	<td ><input class="amt" type="text" name="esi_<%=i%>" id="esi_<%=i%>" onblur="cal(<%=i %>)" value="<%=bean.getEsi_amt() %>" style="width:70px; height: 20px;" /></td>
    	<td ><input class="amt" type="text" name="tds_<%=i%>" id="tds_<%=i%>" onblur="cal(<%=i %>)" value="<%=bean.getTds_amt() %>" style="width:70px; height: 20px;" /></td>
    	<td ><input class="amt" type="text" name="otherdeduction_<%=i%>" id="otherdeduction_<%=i%>" onblur="cal(<%=i %>)" value="<%=bean.getOther_deduction() %>" style="width:70px; height: 20px;"/></td>
    	<td ><input type="text" name="grossdeduction_<%=i%>" id="grossdeduction_<%=i%>" value="<%=bean.getGross_deduction() %>" style="width:70px; height: 20px;background-color:#FF9" readonly="readonly"/></td>
    	<td ><input type="text" name="netsal_<%=i%>" id="netsal_<%=i%>" value="<%=bean.getNet_salary() %>" style="width:70px; height: 20px;background-color:#FF9" readonly="readonly"/></td>
    	<td ><a onclick="delSalary(<%=bean.getAd_employee_id()%>)" style="cursor: pointer;">Delete</a></td>
    	
    	</tr>	
    	<%
    	++i;}
    	
    %>
    <input type="hidden" name="tot_records" id="tot_record" value="<%=i%>"/>
   <tr><td colspan="24" align="center"><input type="submit" value="Submit" name="Submit" style="width: 70px; height: 30px;"></td></tr>
</table>

