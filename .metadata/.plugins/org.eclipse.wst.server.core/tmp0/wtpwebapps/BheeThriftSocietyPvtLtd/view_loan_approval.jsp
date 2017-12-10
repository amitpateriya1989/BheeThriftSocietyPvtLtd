<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Dao.LoanCategoryDao"%>
<%@page import="Model.Dao.TypeOfLoanDao"%>
<%@page import="Model.Bean.LoanCategoryBean"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.TypeOfLoanBean"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Bean.LoanTrxBean"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="Model.Bean.SalutationBean"%>
<%@page import="Model.Bean.GenderBean"%>
<%@page import="Model.Dao.SalutationDao"%>
<%@page import="Model.Dao.GenderDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%@ include file= "Layout/header.jsp" %>
<link rel="stylesheet" href="assets/plugins/data-tables/DT_bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2-bootstrap.css"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-full-width">
<%@ include file= "Layout/headtitle.jsp" %>
<!-- BEGIN HEADER -->
<div class="header navbar mega-menu">
<!-- BEGIN TOP NAVIGATION BAR -->
<%@ include file= "Layout/navbar.jsp" %>
<!-- END TOP NAVIGATION BAR -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN EMPTY PAGE SIDEBAR -->
	<%@ include file= "Layout/emptynavbar.jsp" %>
	<!-- END EMPTY PAGE SIDEBAR -->
	<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
<div class="page-content">
<!-- BEGIN PAGE CONTENT-->
<div class="row">
<div class="col-md-2 sidebar-content ">
<%@ include file= "Layout/sidebar.jsp" %>
</div>
<div class="col-md-10">
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i>
				<a href="#">Setup</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">LOAN</a><i class="fa fa-angle-right"></i> view </li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
<div class="col-md-12">
<!-- Containt Start -->
<%
try{
Object AppObj = request.getSession().getAttribute("AppMessage");
String[] AppMessage = (String[])AppObj;
if(AppMessage[1].isEmpty()!=true && AppMessage[1].equals("welcome")!=true){ %>
<div class="alert <%=AppMessage[0] %> alert-dismissable">
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
	<%=AppMessage[1] %>
</div>
<%
}
%>
<!------------------------------------------------------------------- -->
<!------------------------------------------------------------------- -->
<div class="hidden">
<%
String  V_Trx_No= "";
V_Trx_No= (String)request.getParameter("no");
if(V_Trx_No==null){
	V_Trx_No = "";
}
%>
<input type="hidden" name="V_Trx_No" id="V_Trx_No" value="<%=V_Trx_No%>" /><!-- for display message after submit -->
</div>
<!------------------------------------------------------------------- -->
	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Approve Loan</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	   		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Sno.</th>
					<th>StaffNo</th>				
					<th>Name</th>
					<th>Loan Type</th>
					<th>Loan Category</th>
					<th>Loan AMT</th>
					<th>ROI</th>
					<th>Period(M)</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>EMI</th>
					
				</tr>
				</thead>
			<tbody>
				<%LoanTrxDao dao1=new LoanTrxDao();
					LoanTrxBean bean=dao1.getLoanTrxByPrmryId(Integer.parseInt(request.getParameter("id")));
					int i=0;
					
							
							
							MemberRegistrationBean mbean= new MemberRegistrationDao().getMemberName(bean.getAd_member_id());
							TypeOfLoanBean ltbean =new TypeOfLoanDao().gettypeofloanById(bean.getLoan_type());
							LoanCategoryBean lcbean = new LoanCategoryDao().getLoanCategoryById(bean.getCreatedby());
				%>
				 <tr>
				   <td><%=++i %></td>
				   <td><%=mbean.getAd_society_no()%></td>
				   <td><%=mbean.getName()%></td>
				   <td><%=ltbean.getName()%></td>
				   <td><%=lcbean.getName()%></td>
				   <td><%=bean.getLoan_amt()%></td>
				   <td><%=bean.getIntrest_rate()%></td>
				   <td><%=bean.getPeriod_month()%></td>
				   <td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getissue_date())%></td>
				   <td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getEnd_date())%></td>
				   <td><%=bean.getEmi()%></td>
				  
				 </tr>
				 <%	
					
				  %>
				</tbody>
			
			
				
			</table>
		 
	    </div><!-- End portlet-body -->
	</div>
	 <form id="frmviewloan" action="AdLoanTrx?action=loanapproval&loan_trx_id=<%=request.getParameter("id") %>&ad_member_id=<%=bean.getAd_member_id() %>" method="post" autocomplete="off">
	
	 
<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	 
	  <div class="portlet-title">
	   <div class="caption">
		A/c TRX
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	   	<table id="dataTable1" class="table table-striped table-bordered table-hover">
			
			
				<thead>
				<tr>
								<th>A/c Head</th>	
								<th>Member A/c</th>	
								<th>Dr Amt</th>
								<th>Cr Amt</th>
								
				</tr>
				</thead>
				<tbody>
				
				 <tr>
				   <td>Dr In  Mamber Loan Ac <span class="badge badge-primary">(<%=mbean.getName() %>)</span></td>
				  <td> </td>
				  <td> <input type="text" name="crmember" id="crmember" value="<%=bean.getLoan_amt()%>"></td><td> </td>
				 </tr>
				 <tr>
				  <td> Cr In Bank Ac</td><td> </td><td> </td>
				  <td> <input type="text" name="drbank" id="drbank"  value="<%=bean.getLoan_amt()%>" style="background: none;border:none" readonly="readonly"></td>
				 
				
				   
				   
				 
				 </tr>
				
				<%--  <tr>
				  <td> If Req. Other Head <select name="ad_account_id" id="ad_account_id" readonly="readonly" data-placeholder="Choose a Bank..." class="chosen-select" style="width:250px;" tabindex="2"><option value="0">--select--</option>
<%LedgerAccountDao ldao=new LedgerAccountDao();
    				ArrayList<LedgerAccountBean> ledgerList=ldao.getAllLedgerAccount();
    				if(ledgerList!=null){
    				for(LedgerAccountBean bean1:ledgerList){
    					
    					
    				%>
    				 <option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_name() %></option>	
    				    
    				<%}} %> </td>
    				<td><select name="ad_member_id_h" id="ad_member_id" data-placeholder="Choose a Bank..." class="chosen-select" style="width:250px;" tabindex="2"><option value="0">--select--</option>

		 			<%MemberRegistrationDao mdao =new MemberRegistrationDao();
								 	ArrayList<MemberRegistrationBean> malist=mdao.getAllMemberlist();
								 	if(malist!=null){
								 	for(MemberRegistrationBean bean2:malist){
								 	
								 	
								 	%>
								 	
								 <option value="<%=bean2.getAd_member_id()%>"><%=bean2.getName() %></option>
								 <%} }
								 %> </td> 
				  <td> --%> <input type="hidden" name="reqcrhead" id="reqcrhead" value="00.0"> <<!-- /td>
				 <td> --> <input type="hidden" name="reqdrhead" id="reqdrhead" value="00.0"><!-- </td> -->
				   
				   
				 
				 </tr>
				  <tr>
				  <th align="right">  </th>
				  <th>Total</th>
				   <td> <input type="text" id="drtotal" name="drtotal" value="<%=bean.getLoan_amt()%>" style="background: none;border:none" readonly="readonly"></td>
				  <td> <input type="text" id="crtotal" name="crtotal" value="<%=bean.getLoan_amt()%>" style="background: none;border:none" readonly="readonly"></td>
				
				   
				   
				 
				 </tr>
				 
				</tbody>
			</table>
			<div class="clearfix"></div>
	    </div><!-- End portlet-body -->
	</div>
	
<!------------------------------------------------------------------- -->
	<!-- END BORDERED TABLE PORTLET-->					
<!-- Containt Stop -->
<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		Cheque Detail
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<table id="dataTable1" class="table table-striped table-bordered table-hover">
			
			
				<thead>
				<tr>
					<td>Chq No.<span class="red">*</span></td>
					<td><input type="text"  id="chkno" name="chkno" class="form-control input-medium"/>
						<label class="error"></label>
					</td>
					<td>Chq Date<span class="red">*</span></td>
					<td><input type="text" class="form-control input-medium date-picker" placeholder="dd/mm/yyyy" id="chkdate" name="chkdate" value="<%=new SimpleDateFormat("dd/MM/yyyy").format(session.getAttribute("openday"))%>"/>
						<label class="error"></label>
					</td>
					
					
				</tr>
				</thead>
				<tbody>
				
				 <tr>
				   
				  <td>  Branch<span class="red">*</span></td>
				  <td colspan="3"> 
				      <select id="chkbank" name="chkbank"  class="form-control input-large" > 
				  				<option value="">--Select Branch--</option>
								<%BankBranchDao bankdao=new BankBranchDao();
								  ArrayList <BankBranchBean> banklist=bankdao.getAllBankBranchName();
								  if(banklist.isEmpty()!=true){
								  for(BankBranchBean bean1:banklist){
								  %>
								  <option value="<%=bean1.getBranch_code()+" | "+bean1.getBranch()%>"><%=bean1.getBranch_code()+" "+bean1.getBranch().trim() %></option>
								 <%} }%>
						</select>
						<label class="error"></label>
						</td>
				 
				 </tr>
				 <tr>
					<td></td>					
					<td colspan="3">
					  <input class="btn btn blue" type="submit" name="Submit" value="submit"/>
					  <input class="btn btn green" type="reset" name="Clear"/>
					</td>
				</tr>
				 
				</tbody>
			</table>
			<div class="clearfix"></div>
	    </div><!-- End portlet-body -->
	</div>
<!------------------------------------------------------------------- -->
	<!-- END BORDERED TABLE PORTLET-->					
<!-- Containt Stop -->
</form>	
</div>
</div>
</div>
</div>
<!-- END PAGE CONTENT-->
</div>
</div>
<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%
Date d=(Date)session.getAttribute("openday");
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
String today=sdf.format(d);

%>
<%@ include file= "Layout/footer.jsp" %>
<script type="text/javascript" src="assets/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script type="text/javascript">

jQuery(function() { 
	//FormWizardMember.init();
	$('#chkbank').select2();
	
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate :'<%=today%>',autoclose: true});
	
	jQuery.validator.addMethod("Alphanumspacedot", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z.\s]*$/.test(value);
		}, "Please enter latter and space only.");
	
	jQuery.validator.addMethod("validDate", function (value, element) {
        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	jQuery( "#frmviewloan" ).validate({
		  rules: {
			  chkno: {
				  digits:true,
          		required:true,
          		maxlength:6
		    },
		    chkdate : {
		      required: true,
		      validDate:true
		    },
		    chkbank : {
			      required: true
			    }
		  }
		});
});
</script>

 <script type="text/javascript">

$(document).ready(function(e){
	
	$("#drbank").blur(function(e){
	
		$("#crtotal").val(parseFloat($("#crmember").val()) + parseFloat($("#reqcrhead").val()));
		//alert(parseFloat($("#reqcrhead").val()));
		$("#drtotal").val(parseFloat($("#drbank").val()) + parseFloat($("#reqdrhead").val()));
	});
	$("#crmember").blur(function(e){
		$("#drbank").blur();
	});
	$("#reqcrhead").blur(function(e){
		$("#drbank").blur();
	});
	$("#reqdrhead").blur(function(e){
		$("#drbank").blur();
	});
	
});
</script>
<script type="text/javascript">
$(function(){
	var V_Trx_No = $("#V_Trx_No").val();
	
	if(V_Trx_No.trim()!=""){
		$('.custom-messageBox').modal('show');
		$('#custom-page-message').html("Transaction number is "+V_Trx_No);
	}
	
});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>




</html>