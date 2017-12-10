

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<%@page import="Model.Bean.MemberWitnessChkBean"%>
<%@page import="Model.Dao.MemberWitnessChkDao"%>
<%@page import="Model.Dao.LoanWitnessDao"%>
<%@page import="Model.Bean.LoanwitnessBean"%>
<%@page import="Model.Bean.LoanDetailViewBean"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="Model.Bean.LoanTrxBean"%>
<%@page import="Model.Dao.LoanTrxDetailDao"%>
<%@page import="Model.Bean.LoanTrxDetailBean"%>
<%@page import="Model.Dao.CustomMemberInfoDao"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%@ include file= "Layout/header.jsp" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-datepicker/css/datepicker.css"/>
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
			<li><a href="#">Loan </a><i class="fa fa-angle-right"></i>Loan Detail</li>
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
<!-- BEGIN BORDERED TABLE PORTLET-->	
<div class="portlet box purple" id="form_wizard_1">
<div class="portlet-title">
<div class="caption"><i class="fa fa-reorder"></i> Loan Detail 

</div>


<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<form id="frmLoanTransaction" autocomplete="off" action="AdLoanTrx?action=insert" method="post">
<input type="hidden" name="il_ad_member_id" id="il_ad_member_id" value='0' />
<!-- -----------------------------------------Start Form----------------------------------------------- -->	
<div class="form-wizard">
<div class="form-body">

<div id="bar" class="progress progress-striped" role="progressbar">
	<div class="progress-bar progress-bar-success" style="width: 0%;"></div>
</div>
<div class="tab-content">
	   <div class="alert alert-danger display-none">
		   <button class="close" data-dismiss="alert"></button>
			 You have some form errors. Please check below.
	  </div>
	  <div class="alert alert-success display-none">
		    <button class="close" data-dismiss="alert"></button>
			 Your form validation is successful!
	  </div>
	  
	  <%
	  		
	  		String loan_id=request.getParameter("loan_trx_id");
	       
	  		int loan_trx_id=0;
	        if(loan_id!=null){
	        	
	        	try{
	        		 loan_trx_id=Integer.parseInt(loan_id);
	        		 
	        	}catch(NumberFormatException n){
	        		n.printStackTrace();
	        	}
	        	LoanTrxBean loanBean=new LoanTrxDao().getLoanTrxByDetail(loan_trx_id);
	        	List<Map<String, Object>> memberlist=new CustomMemberInfoDao().getMemberDetailsById(loanBean.getAd_member_id());
	        	if(memberlist.isEmpty()!=true){
	        	Map<String, Object> member=memberlist.get(0);
	        	
	  %>
<!-- -----------------------------Start Tab Content------------------------------------------------- -->
	
		<table class="table table-bordered">
			<tr>
				<td width="15%">Name : </td>
				<td width="35%"><%=member.get("MemberName")%> </td>
				<td width="15%">PF No. :</td>
				<td width="35%"><%=member.get("MemberPfNo")%></td>
			</tr>
			<tr>
				<td>Mem.No :</td>
				<td><%=member.get("MemberSocietyNo")%></td>
				<td>Type :</td>
				<td><%=member.get("MemberType")%></td>
			</tr>
			<tr>
				<td>Status :</td>
				<td><%=member.get("MemberStatus")%></td>
				<td>Father :</td>
				<td><%=member.get("MemberFather")%></td>
			</tr>
			<tr>
				<td>Husband :</td>
				<td><%=member.get("MemberHusband")%></td>
				<td>DOB</td>
				<td><%=new SimpleDateFormat("dd/MM/yyyy").format(member.get("MemberDob"))%></td>
			</tr>
			<tr>
				<td>Gender :</td>
				<td><%=member.get("MemberGender")%></td>
				<td>Category :</td>
				<td><%=member.get("MemberCategory")%></td>
			</tr>
			</table>
			<table class="table table-bordered">
			<tr>
			<td width="25%">
			<div class="userimg-block">
				<div class="user-pic">
				    <img class="uimg" id="photo_view" name="photo_view" src="<%=request.getContextPath()+"/member_images/"+member.get("MemberPhoto") %>" alt="Employee Photo" />
				</div>
				<div class="user-input">
				  <span class="text-center">Photo :</span>
				</div>
			   </div><!-- End userimg-block -->
             </td>
			  <td width="25%">
			  <div class="userimg-block">
			  <div class="user-pic">
				    <img class="uimg" id="id_proof_view" name="id_proof_view" src="<%=request.getContextPath()+"/member_images/"+member.get("MemberIdProof") %>" alt="Employee Id Card"  />
				</div>
				<div class="user-input">
				  <span class="text-center">ID Proof :</span>
				</div>
			   </div><!-- End userimg-block -->
            </td>
				<td colspan="2" width="25%">
				<div class="userimg-block">
			     <div class="user-pic">
				    <img class="uimg" id="sign_view" name"sign_view" src="<%=request.getContextPath()+"/member_images/"+member.get("MemberSignature") %>" alt="Employee Sign"  />
				 </div>
				 <div class="user-input">
				  <span class="text-center">Sign :</span>
				</div>
			   </div><!-- End userimg-block -->
				</td>
			</tr>
		</table><!-- End Tab Personal -->

		

<%

LoanDetailViewBean ldb=new LoanTrxDao().getAllLoanDetailByLoanId(loan_trx_id);

%>

<input type="hidden" id="il_ad_member_id" name="il_ad_member_id" />
	<table class="table table-bordered">
	  <thead>
			<tr><th colspan="4">Loan Detail</th></tr>
	 </thead>
	 <tbody>
	 <tr>
	 		<td>Loan ID : </td>
	 		<td>
	 			<%=ldb.getLoan_trx_id() %>
	 		</td>																																																																																																																																
	 		<td>Voucher No : </td>
	 		<td>
	 			<%=ldb.getVno() %>
	 		</td>
	 	</tr>
	 	<tr>
	 		<td>Loan Type : </td>
	 		<td>
	 			<%=ldb.getLoan_type() %>
	 		</td>																																																																																																																																
	 		<td>Loan Category : </td>
	 		<td>
	 			<%=ldb.getLoan_category_name() %>
	 		</td>
	 	</tr>
	 	<tr>
	 		<td>Loan Purpose : </td>
	 		<td>
	 			<%=ldb.getLoan_purpose() %>
	 		</td>																																																																																																																																
	 		<td>
	 		Requested Loan Amt : </td>
	 		<td>
	 		<%=ldb.getLoan_amt() %>
           </td>
	 	</tr>
	 	
	 
		<tr>
			<td>Interest Rate : </td>
			<td><%=ldb.getIntrest_rate() %></td>
			<td>Period Month : </td>
			<td>
			<%=ldb.getPeriod_month() %>
			</td>
		</tr>
		<tr>
			<td>Start Loan Date : </td>
			<td><%=new SimpleDateFormat("dd/MM/yyyy").format(ldb.getIssue_date()) %></td>
			<td>End Loan Date : </td>
			<td><%=new SimpleDateFormat("dd/MM/yyyy").format(ldb.getEnd_date()) %></td>
		</tr>
		<tr>
		<td>Status : <span class="red">*</span></td>
			<td><%=ldb.getStatus() %></td>
			
			<td>IsActive : <span class="red">*</span></td>
			<td> <%= ldb.isIsactive() %>
			</td>
			</tr>
			
		<tr>
			
			<td>EMI : <span class="red">*</span></td>
			<td><%=ldb.getEmi() %>
			</td>
			<td colspan="2" align="right">
				<a href="edit_loan_trx.jsp?loan_trx_id=<%=ldb.getLoan_trx_id()%>" class='btn btn-xm blue'>Edit</a>
				<a href="AdLoanTrx?action=deleteLoan&loan_trx_id=<%=ldb.getLoan_trx_id()%>" class='btn btn-xm red'>Delete</a>
				<a href="view_loan_detail_all.jsp" class='btn btn-xm purple'>Back</a> 
				<a href="#" id="showModalLoanTrx"  class="btn btn-xm green" ><i class="fa fa-search "></i>Trnx</a>
			</td>
		</tr>
	</tbody>
	</table><!-- End Tab Issue Loan -->
<!-- ------------------------------------------------------End Issue Loan------------------------------>
<%  LoanwitnessBean witness=new LoanWitnessDao().getLoanWitnessById(ldb.getWitnes()); %>
    <table class="table table-bordered" id="tblGurntrDtl">
	<thead>
		<tr>
			<th colspan="4">Guarantor Detail</th>
		</tr>
	</thead>
	<tbody>
		
		<tr>
			<td>Name : </td>
			<td><%=witness.getName() %></td>
			<td>Mem.No :</td>
			<td><%=witness.getMemno()%></td>
		</tr>
		<tr>
			<td>PF No : </td>
			<td><%=witness.getPfno() %></td>
			<td>Mobile : </td>
			<td><%=witness.getMobile() %></td>
		</tr>
		<tr>
			<td>Address : </td>
			<td colspan="4"><%=witness.getAddress() %></td>
		</tr> 
		<tr>
		
		<td colspan="4" align="right">
				<input type="button" class='btn btn-sm red' name="edit_gurrantor" value="Edit"/>
				<input type="button" class='btn btn-sm red' name="delete_gurrantor" value="Delete"/> 
				
			</td>
		</tr>
	</tbody>
	</table>
	
<!-- ------------------------------------------------------End Witness Details---------------------------->

	
	<table class="table table-bordered" id="tblGurntrDtlPayment">
	<thead><tr><th colspan="4">Security Cheque Detail</th></tr></thead>
	
	 <tbody>
	 <%
	    ArrayList<MemberWitnessChkBean>  chklist=new MemberWitnessChkDao().getAllMemberWitnessChkChkByLoanTrxId(loan_trx_id);
	 
	 if(chklist.isEmpty()!=true){
		 int i=0;
		 for(MemberWitnessChkBean bean:chklist){
	 %>
	  <tr><th colspan="4">Cheque Details <%=++i %></th></tr>
		<tr>
			<td>Cheque No : </td>
			<td><%=bean.getChkno() %></td>
			<td>Bank Name : </td>
			<td><%=bean.getBankname() %></td>
		</tr>
		<tr>
		    <td>Branch Name : </td>
			<td colspan="3">
			<%=bean.getBranchname() %>
			</td>
		</tr>
		<%} 
		}%>
	</tbody>
	</table>
	<input type="hidden" name="totalChkInfo" id="totalChkInfo" value="1"/>
	<div id="cstm-dynamic-column"></div><!-- End Tab Salary Info -->
<!-- ------------------------------------------------------End Cheque Details---------------------------->

<!-- -----------------------------End Tab Content -------------------------------------------------- -->		
</div><!--End tab-content-->	
</div><!-- End form-body -->
</div><!-- End form-wizard -->

<!-- -----------------------------------------End  Form ----------------------------------------------- -->
</form>	
</div><!-- End portlet-body -->
</div>
<!------------------------------------------------------------------- -->			
<!-- Containt Stop -->
<!-- ---------------------------------statrt modal--------------------------------- -->	
<div class="modal fade ModalAddPOItem" id="large" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header bg-blue">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">
						<i class="fa fa-cogs"></i> Loan Trx Detail
					</h4>
				</div>
				<!-- modal header -->
				<div class="modal-body">
					<div id="custom-page-message2"></div>
					<!-- End custom-page-message -->
<form action="#" method="post" id="loantrxfrm" autocomplete="off">

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


ArrayList<LoanTrxDetailBean> lst= new LoanTrxDetailDao().getLoanTrxDetailByLoantrxId(loan_trx_id);
int j=0;
double deposit_amt=0.0,int_amt=0.0;
if(lst!=null){
	for(LoanTrxDetailBean bean:lst){
		deposit_amt+=bean.getDeposit_amt();
		int_amt+=bean.getInterest_amt();
	%>
	
	<tr>
						 <td><%=++j %></td>
						 <td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getTrx_date()) %></td>
						 <td><%=bean.getDeposit_amt() %></td>
						 <td><%=bean.getInterest_amt()%></td>
						 <td><%=bean.getBalance_amt()%> </td>
						  <td><a href="edit_loan_detail_view.jsp?ad_loan_trx_id=<%=bean.getAd_loan_trx_id()%>" class="btn btn-xs green"><i class="fa fa-search "></i>Edit</a>
							<a href="AdLoanTrx?action=deleteLoanTrx&ad_loan_trx_id=<%=bean.getAd_loan_trx_id()%>&loan_trx_id=<%=bean.getLoan_trx_id() %>" class="btn btn-xs blue" target="blank"><i class="fa fa-dustbin "></i>Delete</a>
													
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
		</form>
	</div>
	<!-- modal-body -->
	<!-- modeal-footer -->
	</div>
	<!-- /.modal-content -->
	</div>
<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- ---------------------------------end modal---------------------------------- -->
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
  	
	    <%    }
	  
	        }
	  
	  %>

<%@ include file= "Layout/footer.jsp" %>
<script type="text/javascript"
		src="assets/plugins/select2/select2.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script src="assets/scripts/custom/components-pickers.js"></script>
	<script>
jQuery(function() {  
	
$("#showModalLoanTrx").click(function(){
				$(".ModalAddPOItem").modal('show');
			});
});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>