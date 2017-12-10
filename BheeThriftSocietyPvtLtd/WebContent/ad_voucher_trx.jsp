<%@page import="Model.Bean.EmployeeBean"%>
<%@page import="Model.Dao.EmployeeDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Bean.TransactionBean"%>
<%@page import="Model.Dao.TransactionDao"%>

<%@include file= "validation.html"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  
<link rel="stylesheet" href="00/chosen.css">
  <script src="js/jquery-1.8.3.js" type="text/javascript"></script>
  
  <script type="text/javascript">

$(document).ready(function(e){
	$("#ad_member_id").change(function(e){
		if($("#ad_member_id").val()!="--select--"){
			$("#ad_employee_id").val('--select--');
			  $('#ad_employee_id').trigger("chosen:updated");
		}
		
		
		
		
	});
	
	$("#ad_employee_id").change(function(e){
		
		if($("#ad_employee_id").val()!="--select--"){
			$("#ad_member_id").val('--select--');

			  $('#ad_member_id').trigger("chosen:updated");
		}
	});
	
	
});

<%try{%>
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String ad_voucher_id = request.getParameter("ad_voucher_id");
TransactionBean tbean=null;


%>


<form action="AdTransaction?action=inserttrx&ad_voucher_id=<%=ad_voucher_id %>"  method="post">

<table id="tblContainer" border="0" cellpadding="2" cellspacing="0" width="100%">

<tr>
					<td colspan="6" class=""  ><hr style="color: black;" /></td>
					</tr>

<tr>
<td>A/c Head</td><td>:</td><td><select name="ad_account_id" id="ad_account_id" readonly="readonly"  tabindex="2"><option value="0">--select--</option>
<%LedgerAccountDao ldao=new LedgerAccountDao();
    				ArrayList<LedgerAccountBean> ledgerList=ldao.getAllLedgerAccount();
    				if(ledgerList!=null){
    				for(LedgerAccountBean bean1:ledgerList){
    					
    					
    				%>
    				 <option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_name() %></option>	
    				    
    				<%}} %></td>
<td>Member </td><td>:</td><td> <select name="ad_member_id" id="ad_member_id"  tabindex="2"><option value="--select--">--select--</option>

		 			<%MemberRegistrationDao mdao =new MemberRegistrationDao();
								 	ArrayList<MemberRegistrationBean> malist=mdao.getAllMemberlist();
								 	if(malist!=null){
								 	for(MemberRegistrationBean bean:malist){
								 	
								 	
								 	%>
								 	
								 <option value="<%=bean.getAd_member_id()%>"><%=bean.getName() %></option>
								 <%} }
								 %> </td>
</tr>
<tr>
<td>Staff </td><td>:</td><td> <select name="ad_employee_id" id="ad_employee_id"   tabindex="2"><option value="--select--">--select--</option>

		 			<%EmployeeDao edao =new EmployeeDao();
								 	ArrayList<EmployeeBean> ealist=edao.getAllEmployeeName();
								 	if(malist!=null){
								 	for(EmployeeBean bean:ealist){
								 	
								 	
								 	%>
								 	
								 <option value="<%=bean.getAd_employee_id()%>"><%=bean.getName() %></option>
								 <%} }
								 %> </td>


<td>Trx Date</td><td>:</td><td> <input type="date" readonly="readonly" value="<%=request.getParameter("trx_date") %>" name="trx_date" readonly="readonly"/> </td>



</tr>
<tr>
<td>Dr Amt</td><td>:</td><td><input class="amt" type="text" name="dr_amt" id="dr_amt" value="" /></td>
<td>Cr Amt</td><td>:</td><td><input class="amt" type="text" name="cr_amt" id="cr_amt" value="" /></td>
</tr>
<tr>
<td>Narration </td><td>:</td><td><input maxlength="50" type="text" name="narration" id="narration" value="" /></td>


</tr><tr>


</tr>

<tr>
					<td colspan="6" class=""  ><hr style="color: black;" /></td>
					</tr>

	<tr><td colspan="9" align="center" height="5"><input type="submit" onclick="return valid()" value="Submit"/></td></tr>
	<tr><td colspan="6" class=""  ><hr style="color: black;" /></td></tr>
	</tbody>
</table>
</form>
</body>
</html>

<script src="00/chosen.jquery.js" type="text/javascript"></script>
<script src="00/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
   


function valid(){
	if($("#ad_account_id").val()=="0"){
		alert("please select account Head");
		return false;
	}else if($("#ad_member_id").val()=="--select--" && $("#ad_employee_id").val()=="--select--" ){
		alert("please select Member/employee ");
		return false;
	}else if($("#dr_amt").val()=="" && $("#cr_amt").val()=="" ){
		alert("please Enter Dr/Cr Balance ");
		return false;
	}
	
	
}

</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>