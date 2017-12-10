<%@page import="Model.Dao.VoucherDao"%>
<%@page import="Model.Bean.VoucherBean"%>
<%@page import="Model.Bean.BankBean"%>
<%@page import="Model.Dao.BankDao"%>
<%@page import="Model.Bean.TempTranxBean"%>
<%@page import="Model.Dao.TempTranxDao"%>
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
	 <div class="portlet box blue">
	 
	  <div class="portlet-title">
	    <!-- <div class="caption"> <input type="button" id="showModalVoucher" class="btn btn-md green" value="Add Voucher" /> </div> -->
<!-- <div class="caption"> <input type="button" id="showModalBilling" class="btn btn-md green" value="Add Voucher Trx" /> </div> -->
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
				int id=0;
				String loan_id=request.getParameter("id");
						if(loan_id!=null){
							id=Integer.parseInt(loan_id);
							session.setAttribute("loan_id", id);
						}else{
							id=(Integer)session.getAttribute("loan_id");
						}
						
					LoanTrxBean bean=dao1.getLoanTrxByPrmryId(id);
					int i=0;
					int ad_member_id=bean.getAd_member_id();
							
							
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
				
				</tbody>
			
			
				
			</table>
		 
	    </div><!-- End portlet-body -->
	</div>
	
	<!-- BEGIN BORDERED TABLE PORTLET-->
	<!-- ---------------------------------statrt modal--------------------------------- -->	
<!-- BEGIN BORDERED TABLE PORTLET-->
	   <div class="portlet box purple" id="ad_voucher">
	  <div class="portlet-title">
	   <div class="caption">
		Voucher Transaction
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
				<!-- modal header -->
				  <div class="portlet-body">
					<div id="custom-page-message2"></div>
					<!-- End custom-page-message -->
<form id="frmVoucher" autocomplete="off" action="AdVoucher?action=loan_trx_forword" method="post">
			<table class="table table-bordered">
				 
					
				
				<tr>
					
					<td>Voucher Date : <span class="red">*</span></td>
					<td>
					<%SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
					String voucher_date=sdf.format(request.getSession().getAttribute("openday"));
				    %>
					<input type="text" class="form-control input-medium date-picker" name="voucher_date" id="voucher_date" value="<%=voucher_date%>">
					<label class="error"></label>
					</td>
					<td>Voucher Type : <span class="red">*</span></td>
					<td >
					<select class="form-control input-medium" name="voucher_type" id="voucher_type">
								 	
									<option value="Payment">Payment</option>
									
					</select><label class="error"></label>
					<input class="form-control input-medium"  type="hidden" id="voucher_no" name="voucher_no" value="pending" readonly="readonly" />
					<input type="hidden" class="form-control input-medium" name="voucher_status" id="voucher_status" value="Pending" readonly="readonly">
					</td>
					
									
				</tr>
				<tr>
					
					<td>Voucher Amount : <span class="red">*</span></td>
					<td><input type="text" class="form-control input-medium" name="vamt" id="vamt"></td>					
					<td>Amt in Words : <span class="red">*</span></td>
					<td><input type="text" class="form-control input-large" name="amt_in_words" id="amt_in_words" readonly="readonly"></td>					
				
					
					
				</tr>
				<tr>
				<td colspan="4" align="center">
					  <button class="btn btn-md green input-medium"  name="generate_voucher" id="generate_voucher" >Generate Voucher</button>
					</td>
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
	<!-- BEGIN BORDERED TABLE PORTLET-->
	   <div class="portlet box purple" id="voucher_trx">
	  <div class="portlet-title">
	   <div class="caption">
		Voucher Transaction
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			 <table class="table table-bordered">
<%
int ad_voucher_id=0;
ad_voucher_id=new VoucherDao().getMaxPendingVoucherId();
VoucherBean vbean=new VoucherDao().getmanualVoucherById(ad_voucher_id);


double dr=0.0;
double cr=0.0;

/* if(request.getParameter("ad_voucher_id")!=null){
	ad_voucher_id=Integer.parseInt(request.getParameter("ad_voucher_id"));
	
}	 */	
if(vbean!=null){
%>			 
  <tr align="left" style="background-color:#666;color:#FC0"><th colspan="8" >Debit(Dr)</th></tr>

						<tr id="d" >
		    			<th >Acc Head</th>
		    			<th><select id="ad_account_id_d"  class="form-control input-medium" >
		    			 
		    			
    				<%
    				
    				
    				ArrayList<LedgerAccountBean> ledgerList=new LedgerAccountDao().getLedgerAccountByid(134,197);
    				if(ledgerList!=null){
    				for(LedgerAccountBean bean1:ledgerList){
    				%>
    				 <option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_name() %></option>	
    				    
    				<%} }
    				
    				
        				%>
        				
    				
    				</select>
                                                                </th>
                                                                 <th >Amount</th>
				 
				 <%
				 TempTranxDao beand = new TempTranxDao();
				 int dramt =beand.getDrTempTranx(ad_voucher_id);
				 
				 %>
				 
				 <th><input  type="text" id="amt_d" class="form-control input-medium amt" value="<%=vbean.getVamt()-dramt%>"  /> </th>
                
                  <th >Narration</th>
                  <th><input type="text" id="narration_d" class=" form-control input-medium" value="TO:- "  />  </th>
                  <th><input type="button" class="btn btn blue" value="Ok" onclick="slct('d')" style="width: 70px; height: 30px;"  /> </th>
</tr>
   
   
   <tr align="left" style="background-color:#666;color:#FC0"><th colspan="8" >Credit(Cr)</th></tr>
	<tr align="left" id="c">
    			<th >Acc Head</th>
    			<th>
	    			<select id="ad_account_id_c"  class="form-control input-medium" >
	    				<option value="0">--Select--</option>
	    				<%
	    				if(vbean.getVoucher_type().equals("Payment")){
	        				
	    			
            			ArrayList<LedgerAccountBean> ledgerList1=new LedgerAccountDao().getLedgerAccountByid(131, 132, 201);
            			if(ledgerList1!=null){
            			for(LedgerAccountBean bean1:ledgerList1){	
        				%>	
        					<option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_name() %></option>
        					
        					<%
        						}
        					}
	    				}
	    				%>
	    			</select>
	    			<input type="hidden" name="ad_member_id" id="ad_member_id" value="<%=ad_member_id %>"/>
                </th>
                <th  >Amount</th>
				 <%
				 TempTranxDao beanc = new TempTranxDao();
				 int cramt =beanc.getCrTempTranx(ad_voucher_id);
				 
				 %>
				 
				 <th><input class=" form-control input-medium amt"  type="text" id="amt_c"  value="<%=vbean.getVamt()-cramt %>" />
				  </th>
				  <th >Narration</th>
                  <th><input type="text" id="narration_c"  class=" form-control input-medium" value="By:- " /> 
                   </th>
				  <th>
                 <input type="button" class="btn btn blue" value="Ok" onclick="slct('c')" style="width: 70px; height: 30px;"   /> 
                 </th>
				</tr>
				
   
</table>
			<div class="clearfix"></div>
	    </div><!-- End portlet-body -->
	</div>
		<!------------------------------------------------------------------- -->
	<!-- END BORDERED TABLE PORTLET-->					
<!-- Containt Stop -->
<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box blue" id="pay_detail">
	  <div class="portlet-title">
	   <div class="caption">
		Cheque Detail
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	   <form id="payDetailFrm">
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
				   <td>Chq Amt.<span class="red">*</span></td>
					<td><input type="text"  id="chk_amt" name="chk_amt" class="form-control input-medium" />
						<label class="error"></label>
					</td>
				  <td>  Branch<span class="red">*</span></td>
				  <td > 
				      <select id="chkbank" name="chkbank"  class="form-control input-medium" > 
				  				
								<%BankBranchDao bankdao1=new BankBranchDao();
								  ArrayList <BankBranchBean> banklist1=bankdao1.getAllBankBranchName();
								  if(banklist1.isEmpty()!=true){
								  for(BankBranchBean bean1:banklist1){
								  %>
								  <option value="<%=bean1.getBranch_code()+" | "+bean1.getBranch()%>"><%=bean1.getBranch_code()+" "+bean1.getBranch().trim() %></option>
								 <%} }%>
						</select>
						<label class="error"></label>
						</td>
				 
							
					<td >
					  <input type="hidden"  id="ad_voucher_id" name="ad_voucher_id" value="<%=ad_voucher_id %>" class="form-control input-medium" />
					  <input class="btn btn blue" type="button" name="pay_amt_btn"  id="pay_amt_btn" value="Submit"/>
					 
					</td>
				</tr>
				 
				</tbody>
			</table>
			</form>
			<div class="clearfix"></div>
	    </div><!-- End portlet-body -->
	</div>
	<!------------------------------------------------------------------- -->
	
	 <div class="portlet box purple" id="share_table">
	  <div class="portlet-title">
	   <div class="caption">
		Share Detail
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<div id=share_dynamic_table></div>
 			<div class="clearfix"></div>
	   </div><!-- End portlet-body -->
	</div> 
<!------------------------------------------------------------------- -->
	
	 <div class="portlet box purple" id="trx_table">
	  <div class="portlet-title">
	   <div class="caption">
		Transaction Detail
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<form id="frmTrx" action="AdTransaction?action=insertloantrx&loan_trx_id=<%=id %>" method="post">
			
<div id=dynamic_table>
<table class="table table-bordered" >
<tr style="background-color: #06C; color: white; text-align: center; font-size: 14px;">
<th>S.No</th>
<th>LF No</th>
<th>Account</th>
<th>Narration</th>
<th>Dabit(Dr.)</th>
<th>Credit(Cr.)</th>
<th>OPT</th>
</tr>
<%

TempTranxDao tempdao1=new TempTranxDao();

 ArrayList<TempTranxBean> templist=tempdao1.getAllTempTranxByVoucherId(ad_voucher_id);
 if(templist!=null){
	 int k=0;
	 
	 for(TempTranxBean tempbean:templist){%>
		<tr>
		 <td><%=++k %></td>
		 <td><%=tempbean.getLedger().getAc_no() %></td>
		  <td><%=tempbean.getLedger().getAc_name() %></td>
		  <td><%=tempbean.getNarration() %></td>
		  <td><%=tempbean.getDramt()%></td>
		  <td><%=tempbean.getCramt()%></td>
		  <td><input type="button" class="btn btn-sm red" onclick="delete_trx(<%=tempbean.getAd_trx_temp_id()%>,<%=ad_voucher_id%>)" value="Delete"/> 
		  </tr>
	 <%
	 dr=dr+tempbean.getDramt();
	 cr=cr+tempbean.getCramt();
	 }
 }

%>
    <tr style="background-color:#white;color:red;font-size:14px" >
    <th colspan="4" align="right">Total :</th>
    <th align="right">
    <label style="display:inline-block;text-align:left;"><%=dr %> </label> 
    </th>
    <th align="right">
    <label style="display:inline-block;text-align:left;"><%=cr %></label>
    <input type="hidden" id=dr value="<%=dr %>>" />
	<input type="hidden" id=cr value="<%=cr %>>" />
	<input type="hidden" id=v_amt value="<%=vbean.getVamt() %>>" />
     </th>
    
     </tr>
    
</table> 
 </div>
 
 <table class="table table-bordered">
  <tr>
					<td  align="center">
						<input class="btn btn blue"  type="button" name="save_voucher" id="save_voucher" disabled="disabled" value="Save Voucher" onclick="return finl(<%=dr %>,<%=cr %>,<%=vbean.getVamt()%>) " />&nbsp;&nbsp;
						<input class="btn btn green" type="button" name="cancle_voucher" value="Cancle Voucher" onclick="finl_cancle(<%=ad_voucher_id%>)" />&nbsp;&nbsp;
					</td>
					</tr>
 </table>
 			
 		</form>
 	
			
			<div class="clearfix"></div>
	    </div><!-- End portlet-body -->
	</div>  
	<%} %>
<!------------------------------------------------------------------- -->
	

	
	<!------------------------------------------------------------------- -->
	<!-- END BORDERED TABLE PORTLET-->					
<!-- Containt Stop -->

<!------------------------------------------------------------------- -->
	<!-- END BORDERED TABLE PORTLET-->					
<!-- Containt Stop -->

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
String today=sdf.format(d);

%>
<%@ include file= "Layout/footer.jsp" %>
<script type="text/javascript" src="assets/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script type="text/javascript">
/* function disableBack() {
	
	window.history.forward();
	
	
}

window.onload = disableBack();
window.onpageshow = function (evt) {
	if (evt.persisted){ 
		$('#custom-page-message').html("<div class='note note-info'>You cannot go back..!! </div>");
		   $('.custom-messageBox').modal('show');
		disableBack();
		
	}
	} */
$( document ).ready(function() {
	

   
    	
    
	var ad_voucher_id = getUrlParameter('ad_voucher_id');
	if(typeof ad_voucher_id != "undefined"){
		
		$('#voucher_trx').show();
		$('#ad_voucher').hide();
	}else{
		$('#voucher_trx').hide();
		$('#trx_table').hide();
		$('#ad_voucher').show();
	}
});
jQuery(function() { 
	//FormWizardMember.init();
	$('#chkbank').select2();
	$('#online_bank').select2();
	$('#dd_bank').select2();
	$('#ad_account_id_d').select2();
	$('#ad_account_id_c').select2();
	$('#pay_detail').hide();
	$('#share_table').hide();
	
	
	$('#ad_account_id_c').change(function(e){
		var acc_id=$(this).val();
		
		if(acc_id==131){
			$('#pay_detail').show();
			$('#share_table').hide();
		}else{
			$('#pay_detail').hide();
		}
		
		if(acc_id==132){
			var loan_trx_id=<%=id%>;
			$.ajax({
				   type: "POST",
				   url: "Ajax/getShareDetailByLoanId.jsp?loan_trx_id="+loan_trx_id,
				   async:false,
				   success: function(data)
				   {	
					   $('#share_dynamic_table').html(data);
					   $('#share_table').show();	
					  
			  		} ,error: function(jqXHR, textStatus, errorThrown){
		            console.log("Something really bad happened " + textStatus);
		            $('#pay_detail').show();
		            $("#ajaxResponse").html(jqXHR.responseText);
		            
		      } 
		});	
		
		}else{
			$('#share_table').hide();
		}
	});
	
$("#save_voucher").click(function(e){
	e.preventDefault();
	
	$('form#frmTrx').submit();
	
	var loan_trx_id=<%=id%>
	$.ajax({
		   type: "POST",
		   url: "Ajax/getShareDetailByLoanId.jsp?loan_trx_id="+loan_trx_id,
		   async:false,
		   success: function(data)
		   {	
			   $('#share_dynamic_table').html(data);
			   $('#share_table').show();
			   
			 $.ajax({
				 type: "POST",
				   url: "AdTempVoucher?action=shareVarification&?ad_account_id="+132,
				   async:false,
				   success: function(data)
				 {
					  // alert(data);
					   
				 } ,error: function(jqXHR, textStatus, errorThrown){
			         console.log("Something really bad happened " + textStatus);
			         $('#pay_detail').show();
			         $("#ajaxResponse").html(jqXHR.responseText);
				 
				 }
				 
			 }) ; 
			   
			   
			   
			   
			   
			   
			   
			  
	  		} ,error: function(jqXHR, textStatus, errorThrown){
         console.log("Something really bad happened " + textStatus);
         $('#pay_detail').show();
         $("#ajaxResponse").html(jqXHR.responseText);
         
   } 
});	
	
	
});

$("#vamt").change(function(e){
	var voucher_amt=$(this).val();
	var amt=number2text(voucher_amt);
	$("#amt_in_words").val(amt);
});

$('#generate_voucher').click(function(e){
	
	var loan_amt=<%=bean.getLoan_amt()%>;
	var vamt=$('#vamt').val();
	if(vamt<=loan_amt){
	$('form#frmVoucher').submit();
	//alert();
	}else{
		//alert("Please Enter Valid Voucher Amount...!!");
		$('#custom-page-message').html("<div class='note note-info'>Please Enter Valid Voucher Amount...!! </div>");
		   $('.custom-messageBox').modal('show');
		   e.preventDefault();
	}
	});
	
	
  $('#pay_amt_btn').click(function(e){
	var loan_amt=<%=bean.getLoan_amt()%>;
	var chk_no=$('#chkno').val();
	var chk_date=$('#chkdate').val();
	var chk_bank=$('#chkbank').val();
	var ad_voucher_id=$('#ad_voucher_id').val();
	var chk_amt=$('#chk_amt').val();
	
	if($("#payDetailFrm"). valid()==false){
		$('#custom-page-message').html("<div class='note note-info'>Please Enter Cheque Detail...!! </div>");
		   $('.custom-messageBox').modal('show');
	}if(chk_amt<=loan_amt){
		$.ajax({
			   type: "POST",
			   url: "AdVoucher?action=updateVoucher&chk_no="+chk_no+"&chk_date="+ chk_date+"&chk_bank="+ chk_bank+"&chk_amt="+ chk_amt+"&ad_voucher_id="+ ad_voucher_id,
			   async:false,
			   dataType: "text",
			   success: function(data)
			   {	
				   $('#custom-page-message').html("<div class='note note-info'>  <b>"+data+"</div>");
				   $('.custom-messageBox').modal('show');
				   $('#pay_detail').hide();
				   $('#save_voucher').prop('disabled', false);
		  		} ,error: function(jqXHR, textStatus, errorThrown){
	            console.log("Something really bad happened " + textStatus);
	            $('#pay_detail').show();
	            $("#ajaxResponse").html(jqXHR.responseText);
	            
	      } 
	});
		
	
	
	}else{
			$('#custom-page-message').html("<div class='note note-info'>Please Enter Valid Cheque Amount...!! </div>");
			   $('.custom-messageBox').modal('show');
			   e.preventDefault();
		}
	
}); 
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate :'<%=today%>',autoclose: true});
	
	jQuery.validator.addMethod("Alphanumspacedot", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z.\s]*$/.test(value);
		}, "Please enter latter and space only.");
	
	jQuery.validator.addMethod("validDate", function (value, element) {
        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	
	
	jQuery( "#payDetailFrm" ).validate({
		  rules: {
			  chkno: {
				  digits:true,
        		  required:true,
        		  maxlength:6,
        		  minlength:6
		        },
		       chkdate : {
		          required: true,
		          validDate:true
		       },
		       chkbank : {
			      required: true
			    },
			    chk_amt:{
			    	required: true,
			    	number:true
			    }
			    
		      }
		});
});

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};
</script>

 <script type="text/javascript">

$(document).ready(function(e){
	$("#showModalVoucher").click(function(){
		
		$(".ModalAddVoucher").modal('show');
	});
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
<script type="text/javascript">
jQuery(function() {  
	jQuery('#voucher_type').select2();
	jQuery('#vtype').select2();
	jQuery('#cheque_bank').select2();
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate:'<%=today%>',autoclose: true});
	
	jQuery.validator.addMethod("alphabet", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z]*$/.test(value);
		}, "Please enter character only.");
	
	jQuery.validator.addMethod("alphanumsapce", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z.\s]*$/.test(value);
		}, "Please enter character and space only.");
	
	jQuery.validator.addMethod("validDate", function (value, element) {
		 return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	jQuery( "#frmVoucher" ).validate({
		  rules: {
			
		    voucher_type:{
		    	required:true,
		    	alphabet:true
		    },
		    vtype : {
		      required: true,
		      alphabet: true
		    },
		    voucher_date:{
		    	required: true,
		    	validDate:true
		    },
		    vamt:{
		    	required: true,
		    	number:true
		    }
		    
		  }
		});
	
	
});
</script>
  
    

<script type="text/javascript">
$(document).ready(function(e) {
	
	//$("#")
	
var v_f_f_m_s_f_dr=0;	
var v_f_f_m_s_f_cr=0;	
	
var amt_d=$("#amt_d").val();	
var amt_c=$("#amt_c").val();	
if(amt_d==0)
{
	$("#d").css("display","none");
	$("#d1").css("display","none");
}
if(amt_c==0)
{
	$("#c").css("display","none");
	$("#c1").css("display","none");
	$('#pay_detail').show();
	
}
	

});
function finl(t_dr,t_cr,v_amt)
{
	if(t_dr!=t_cr)
	{
		
		$('#custom-page-message').html("Debit And Credit Amounts Are Not Equal......!!");
		$('.custom-messageBox').modal('show');
		return false;
	}
	else
	{
		if(v_amt!=t_dr)
		{
			$('#custom-page-message').html("Voucher Amount Does Not Match Equally With Dr And Cr Amount.......!!");
			$('.custom-messageBox').modal('show');
			
			return false;
		}
	}
	
	
		
}


function slct(sts)
{	
	var dr=$("#dr").val();
	var cr=$("#cr").val();
	var v_amt=$("#v_amt").val();
	
	

	if(sts=='d')
	{	var ad_account_id=$("#ad_account_id_d").val();
	    var ad_member_id=$("#ad_member_id").val();
		var narration =$("#narration_d").val();
		var trx_type="Debit";
		var dramt=$("#amt_d").val();
		var cramt=0.0;
		var t_chk=parseFloat(dr)+parseFloat(dramt);
		v_amt=parseFloat(v_amt);
		
		if(t_chk>v_amt)
		{
			alert("Amount Exceed..........!!");
			event.stop();
		}
		
		if(dramt==0)
		{
			alert("Zero Can Not Enter.....!!");
			event.stop();
		}
		
		if(ad_account_id==0)
		{
			alert("Select Account  !!");
			
			event.stop();
		}
	
		$.ajax({
			  	type: "POST",
			  	dataType: "text",
			  	url: "AdTempVoucher?action=insert&ad_account_id="+ad_account_id+"&ad_member_id="+ ad_member_id+"&trx_type="+ trx_type+"&dramt="+ dramt+"&cramt="+ cramt+"&narration="+ narration,
			   	async:false,
			   	success: function(data)
			   	{	
				   	var url="new_loan_disbursement.jsp?ad_voucher_id="+data;
					window.location.href=url;
					
		  		} 
		}); 
		
		
	}
	if(sts=='c')
	{	
		var ad_account_id=$("#ad_account_id_c").val();
		var ad_member_id=$("#ad_member_id").val();
		var narration =$("#narration_c").val();;
		var trx_type="Credit";
		var cramt=$("#amt_c").val();
		var dramt=0.0;
		var t_amt=parseFloat(cr)+parseFloat(cramt);
		v_amt=parseFloat(v_amt);
		if(t_amt > v_amt)
		{
			alert("Amount Exceed..........!!");
			
			event.stop();
		}
		if(cramt==0)
		{
			alert("Zero Can Not Enter.....!!");
			
			event.stop();
		}
		
		if(ad_account_id==0)
		{
			alert("Select Account  !!");
			
			event.stop();
		}
		
		if(ad_account_id==131){
			$('#chk_amt').val(cramt);
		}
		$.ajax({
			   type: "POST",
			   dataType: "text",
			   url: "AdTempVoucher?action=insert&ad_account_id="+ad_account_id+"&ad_member_id="+ ad_member_id+"&trx_type="+ trx_type+"&dramt="+ dramt+"&cramt="+ cramt+"&narration="+ narration,
			   async:false,
			   success: function(data)
			   {	
				   var url="new_loan_disbursement.jsp?ad_voucher_id="+data;
					window.location.href=url;
					
		  	} }); 
	}
	
	
	
	
}

function finl_cancle(ad_voucher_id){
	//alert(ad_voucher_id);
	
	window.location.href="AdVoucher?action=deleteloanvoucher&ad_voucher_id="+ad_voucher_id;
	
}

function delete_trx(ad_temp_trx_id,ad_voucher_id){
	alert(ad_temp_trx_id);
	alert(ad_voucher_id);
	loading_show();
	
	$.get('AdTransaction?action=deletetrx&ad_voucher_id='+ad_voucher_id+'&ad_trx_temp_id='+ad_temp_trx_id,
			function(data,status){
			if(status=="success")
			{
						
				loading_hide();
				location.reload();
			}
	 });
};
//end
function loading_show(){
        $('#modelLoad').removeClass('hide').addClass('show');
     }
     function loading_hide(){
       $('#modelLoad').addClass('hide').removeClass('show');
     };
     
     function number2text(value) {
    	    var fraction = Math.round(frac(value)*100);
    	    var f_text  = "";

    	    if(fraction > 0) {
    	        f_text = "AND "+convert_number(fraction)+" PAISE";
    	    }

    	    return convert_number(value)+" RUPEE "+f_text+" ONLY";
    	}

    	function frac(f) {
    	    return f % 1;
    	}

    	function convert_number(number)
    	{
    	    if ((number < 0) || (number > 999999999)) 
    	    { 
    	        return "NUMBER OUT OF RANGE!";
    	    }
    	    var Gn = Math.floor(number / 10000000);  /* Crore */ 
    	    number -= Gn * 10000000; 
    	    var kn = Math.floor(number / 100000);     /* lakhs */ 
    	    number -= kn * 100000; 
    	    var Hn = Math.floor(number / 1000);      /* thousand */ 
    	    number -= Hn * 1000; 
    	    var Dn = Math.floor(number / 100);       /* Tens (deca) */ 
    	    number = number % 100;               /* Ones */ 
    	    var tn= Math.floor(number / 10); 
    	    var one=Math.floor(number % 10); 
    	    var res = ""; 

    	    if (Gn>0) 
    	    { 
    	        res += (convert_number(Gn) + " CRORE"); 
    	    } 
    	    if (kn>0) 
    	    { 
    	            res += (((res=="") ? "" : " ") + 
    	            convert_number(kn) + " LAKH"); 
    	    } 
    	    if (Hn>0) 
    	    { 
    	        res += (((res=="") ? "" : " ") +
    	            convert_number(Hn) + " THOUSAND"); 
    	    } 

    	    if (Dn) 
    	    { 
    	        res += (((res=="") ? "" : " ") + 
    	            convert_number(Dn) + " HUNDRED"); 
    	    } 


    	    var ones = Array("", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX","SEVEN", "EIGHT", "NINE", "TEN", "ELEVEN", "TWELVE", "THIRTEEN","FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN","NINETEEN"); 
    	var tens = Array("", "", "TWENTY", "THIRTY", "FOURTY", "FIFTY", "SIXTY","SEVENTY", "EIGHTY", "NINETY"); 

    	    if (tn>0 || one>0) 
    	    { 
    	        if (!(res=="")) 
    	        { 
    	            res += " AND "; 
    	        } 
    	        if (tn < 2) 
    	        { 
    	            res += ones[tn * 10 + one]; 
    	        } 
    	        else 
    	        { 

    	            res += tens[tn];
    	            if (one>0) 
    	            { 
    	                res += ("-" + ones[one]); 
    	            } 
    	        } 
    	    }

    	    if (res=="")
    	    { 
    	        res = "zero"; 
    	    } 
    	    return res;
    	}
</script>		
</body>
<%}catch(Exception e){
	e.printStackTrace();
} %>



</html>