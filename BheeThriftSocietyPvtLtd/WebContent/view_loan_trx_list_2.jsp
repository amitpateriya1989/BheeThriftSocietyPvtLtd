<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Dao.LoanTrxDetailDao"%>
<%@page import="Model.Bean.LoanTrxDetailBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="${pageContext.request.contextPath}/assets/css/style-color.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>

</head>


<body>
 <!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		View loan detail
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   </div>
	   <div class="portlet-body" style="height:450px;overflow-y:auto;">
			<table id="dataTable1" class="table table-striped table-bordered table-hover">
			<thead>
					<tr>
						<th>Sno</th>
						<th>Trx Date</th>
						<th>Deposit Amt/EMI</th>
						<th>Interest Amt</th>
						<th>Balance Amt </th>
						<th>Option </th>
					</tr>
</thead>
<tbody >
<%
try{
int loan_trx_id= Integer.parseInt(request.getParameter("loan_trx_id"));

ArrayList<LoanTrxDetailBean> lst= new LoanTrxDetailDao().getLoanTrxDetailByLoantrxId(loan_trx_id);
int i=0;
double deposit_amt=0.0,int_amt=0.0;
if(lst!=null){
	for(LoanTrxDetailBean bean:lst){
		deposit_amt+=bean.getDeposit_amt();
		int_amt+=bean.getInterest_amt();
	%>
	
	<tr>
						 <td><%=++i %></td>
						 <td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getTrx_date()) %></td>
						 <td><%=bean.getDeposit_amt() %></td>
						 <td><%=bean.getInterest_amt()%></td>
						 <td><%=bean.getBalance_amt()%> </td>
						  <td><a href="loan_detail_view_2.jsp?loan_trx_id=<%=bean.getLoan_trx_id()%>" class="btn btn-xs green"><i class="fa fa-search "></i>Edit</a>
							<a  class="btn btn-xs blue" target="blank"><i class="fa fa-dustbin "></i>Delete</a>
							<a href="#" id="showModalLoanTrx"  class="btn btn-xs blue" ><i class="fa fa-dustbin "></i>Trnx</a>						
						  </td>
						 </tr>
		
	<%}
}
%>
</tbody>
<tr style="font-weight: bold;">
						 
						 <td colspan="2">Total</td>
						 <td><%=deposit_amt %></td>
						 <td><%=int_amt%></td>
						 <td></td>
						  <td></td>
						 </tr>
</table>
</div>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>