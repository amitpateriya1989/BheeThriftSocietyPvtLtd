<%@page import="Model.Bean.PayrollTempView"%>
<%@page import="Model.Bean.PayrollTempBean"%>
<%@page import="Model.Dao.PayrollTempDao"%>
<%@page import="Model.Dao.PayrollDao"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Bean.LedgerAccountBean"%>


<%@page import="Model.Dao.PayrollAdviceDao"%>
<%@page import="Model.Bean.PayrollAdviseBean"%>

<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
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
<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-datepicker/css/datepicker.css"/>

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
				<a href="#">Payroll</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Bulk Deposit</a><i class="fa fa-angle-right"></i>Add and View</li>
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

<!-- -------------------------------------------------------------------- -->

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
	   <div class="caption">Payroll</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	   </div><!-- end portlet-title -->
	   <div class="portlet-body">
	    <form id="frmPayroll" action="AdBulkDeposit?action=insertPayroll" method="post">
			<table class="table table-bordered">
				<tr>
				    <%
				    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
				    int batch_no=new PayrollDao().getPayrollMaxBatchNo();
				    Date date = (Date)session.getAttribute("openday");
				    if(batch_no==0){
				    	batch_no=1;
				    }else{
				    	batch_no+=1;
				    }
				    
				    %>
					<td>Batch No <span class="red">*</span></td><td>
		 			<input type="text" name="batch_no" id="batch_no"  class="form-control input-medium" readonly="readonly" value=<%=batch_no %> tabindex="1">
		 				
		 			
					<label id="" class="error "></label>
					</td>
					<td>Date <span class="red">*</span></td><td>
		 			<input type="text" name="payroll_date" id="payroll_date"  class="form-control input-medium " readonly="readonly" value="<%=sdf.format(date) %>" tabindex="2">
		 				
		 			
					<label id="" class="error "></label>
					</td>
					</tr>
					<tr>
					 <td>Branch <span class="red">*</span></td>
					<td>
		 			<select name="ad_branch_id" id="ad_branch_id"  class="form-control input-large" tabindex="0">
		 			<option value="0">--select--</option>
		 			<%BankBranchDao dao =new BankBranchDao();
								 	ArrayList<BankBranchBean> alist=dao.getAllBankBranchName();
								 	if(alist!=null){
								 	for(BankBranchBean bean:alist){%>
								 <option value="<%=bean.getAd_branch_id()%>"> <%=bean.getBranch_code()+" | "+bean.getBranch() %></option>
								 <%} 
								 }%>
											 			
		 			</select>		
		 			
					<label id="" class="error "></label>
					</td> 
					<td>Member </td><td>
		 			<select name="ad_member_id" id="ad_member_id" class="form-control input-large" tabindex="3">
		 			<option value="0">--select--</option>
		 			 <%MemberRegistrationDao mdao =new MemberRegistrationDao();
								 	ArrayList<MemberRegistrationBean> malist=mdao.getAllApprovedMemberlist();
								 	if(malist.isEmpty()!=true){
								 	for(MemberRegistrationBean bean:malist){%>
								 <option value="<%=bean.getAd_member_id()%>"><%=bean.getAd_society_no()+" | "+bean.getName() %></option>
								 <%} 
								 }%> 
											 			
		 			</select>		
		 			
					<label class="error"></label>
					</td>
					</tr>
					<tr>
					<td colspan="2"></td>
					<td colspan="2">
					  <input class="btn btn blue" type="submit" id="btnSubmit" name="Submit" value="submit" tabindex="4"/>
					  <input class="btn btn green" type="reset" name="Clear" tabindex="5"/>
					</td>
				</tr>
				
				
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div><!-- En portlet box -->

	
	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		Bulk Deposit
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   
	   </div>
	   </div>
	   <div class="portlet-body">
	   <div class="table-responsive">
	   <div id="view_payroll">
	   
	  
<form id="frmSubmitPayroll" action="AdBulkDeposit?action=insert" method="post">
<div class="table-responsive table-scrollable" >

					<table id="dataTable1" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								 
								<th><input type="checkbox" name="selectall" id="selectall" ></th>
								<th>Sno</th>
								<th>Branch Name</th>
                                <th>Branch Code</th>
								<th>Pf No</th>
								<th>Memno</th>
								<th>Name</th>
								<th>Thrift </th>
								<!-- <th>DCF </th> -->
								<th>MainLoan </th>
								<th>Emergency </th>
								<!-- <th>GstHouse </th> -->
								<th>Excess </th>
								<th>Total </th>
								<th>Narration</th>
								<th>Action </th>
							</tr>
							</thead>
						<tbody>
						<%
try{
	int i=0;
  ArrayList<PayrollTempView> payroll=new PayrollTempDao().getAllTempPayrollViewByBatchNo(batch_no,0,400);
  double thrift_total=0.0,loan_total=0.0,excess_total=0.0,dcf_total=0.0,festival_total=0.0,guesthouse_total=0.0,grand_total=0.0;
if(payroll.isEmpty()!=true){
	
	
	for(PayrollTempView temp:payroll){
	
                                  						 	
								 	i++;
								 	double thrift=temp.getThrift_amt();
								 	double loan = temp.getMain_loan_amt();
								 	double festival=temp.getFestival_loan_amt();
								 	/* double dcf=temp.getDcf_amt(); */
								 	double guesthouse=temp.getGuest_house_amt();
								 	double excess=temp.getExcess_amt();
								 	thrift_total+=thrift;
								 	loan_total+=loan;
								 	/* dcf_total+=dcf; */
								 	festival_total+=festival;
								 	/* guesthouse_total+=guesthouse; */
								 	excess_total+=excess;
								 	%>
								 	
								 	<tr>
								 	<td style="text-align: center;">
								 	<input type="hidden" name="ad_member_id_<%=i %>" value="<%=temp.getAd_member_id() %>" /> 
								 	 <input class="checkbox" type="checkbox" name="chk_<%=i %>"  id="chk_<%=i %>" value="<%=i%>" onclick='handleClick(this);'></td>
								 	<td><%=i %></td>
								 	<td><%=temp.getBranch() %></td>
								 	<td><%=temp.getBranch_code() %></td>
								 	<td><%=temp.getAd_pf_no() %></td>
								 	<td><%=temp.getAd_society_no() %></td>
								 	<td><%=temp.getName() %></td>
								 	<td><input onblur="cal(<%=i %>)" class="form-control input-small amt setReadOnly" readonly="readonly" name="thrift_<%=i %>" id="thrift_<%=i %>" value="<%=thrift %>"></td>
								 	<%-- <td><input onblur="cal(<%=i %>)" class="form-control input-small amt setReadOnly" readonly="readonly" name="dcf_<%=i %>" id="dcf_<%=i %>" value="<%=dcf %>"></td> --%>								 	
								 	<td><input onblur="cal(<%=i %>)" class="form-control input-small amt setReadOnly" id="loan_<%=i %>" readonly="readonly" name="loan_<%=i %>" value="<%=loan%>"></td>
									<td><input onblur="cal(<%=i %>)" class="form-control input-small amt setReadOnly" id="festival_<%=i %>" readonly="readonly" name="festival_<%=i %>" value="<%=festival%>"></td>
									<%-- <td><input onblur="cal(<%=i %>)" class="form-control input-small amt setReadOnly" id="guesthouse_<%=i %>" readonly="readonly" name="guesthouse_<%=i %>" value="<%=guesthouse%>"></td> --%>
								 	<td><input onblur="cal(<%=i %>)" class="form-control input-small amt setReadOnly" id="excess_<%=i %>" readonly="readonly" name="excess_<%=i %>" value="<%=excess%>"></td>
								 	<td>
								 	    <input id="total_<%=i %>" class="form-control input-small amt setReadOnly" name="total_<%=i %>"   value="<%=thrift+loan+festival+excess %>" readonly="readonly">
								 		
								 	</td>
								 	<td><input  class="form-control input-small amt setReadOnly" id="narration_<%=i %>" readonly="readonly" name="narration_<%=i %>" value="<%=temp.getNarration()%>"></td>
								 	<td><a href="AdBulkDeposit?action=deletePayroll&ad_payroll_temp_id=<%=temp.getAd_payroll_temp_id()%>" class="btn btn-xs  red">Delete</a></td>
								 	</tr>
								 	
								 <%
								 grand_total+=thrift+loan+festival+excess;
                                   } 
									
									}
								

								 %>


								 </tbody>
								 <tfoot>
								 	
								 	<tr style="font-size: 14px;font-weight: bold;">
								 		<td colspan="7" align="right">Total</td>
								 		<td ><%=thrift_total %></td>
								 		<%-- <td><%=dcf_total %></td> --%>
								 		<td><%=loan_total %></td>
								 		<td><%=festival_total %></td>
								 		<%-- <td><%=guesthouse_total %></td> --%>
								 		<td><%=excess_total %></td>
								 		<td><%=grand_total %></td>
								 
								 	</tr>
								 	</tfoot>
</table>
</div>
	<table class="table table-striped table-bordered table-hover">
				<tr>
					<td colspan="5" align="right"><input class="form-control" type="text" id="amt_in_words" name="amt_in_words" readonly="readonly" /><label class="error"></label></td></td>
					<td><input id="total_amt" class="form-control input-medium" readonly="readonly"><label class="error"></label> </td>
				</tr>
				<tr>
					 <td>   Excess </td>
				     <td><input id="excess_amt_other" name="excess_amt_other" class="form-control input-medium " value="0"><label class="error"></label> </td>
				     <td>Narration</td>
				     <td><input  class="form-control input-medium " id="excess_amt_narration"  name="excess_amt_narration" ><label class="error"></label></td>
				     <td  align="right">   Enter Received Amt <span class="red">*</span></td>
				     <td><input id="enter_amt" name="enter_amt" class="form-control input-medium amt"> 
				     <input type="hidden" value="<%=i %>" name="counter"  /><label class="error"></label>
				     </td>
				</tr>
				





<tr>
	
	<td >Inst. Type <span class="red">*</span></td>
	<td>
			<select class="form-control input-medium" name="trx_mode" id="trx_mode"  >
			<option value=''>--select--</option>
			<option>Online</option>
			<option>Cheque</option>
			<option>DD</option>
			<option>NEFT</option>
			<option>RTGS</option>
			<option>Cash</option>
			</select><label class="error"></label>
	</td>
<td>Inst. No <span class="red">*</span></td>
<td><input class="form-control input-medium" type='text' name="chq_no" id="chq_no">
<label class="error"></label></td>

<td> Inst. Date <span class="red">*</span></td>
<td><input type="text" class="form-control input-medium date-picker" name="chk_date" id="chk_date" />
<label class="error"></label>
</td>
</tr>
<tr>
<td >Branch Name <span class="red">*</span></td>
<td>

<select name="ad_branch_id" id="ad_branch_id"  class="form-control input-medium" tabindex="2">

		 			<%BankBranchDao dao1 =new BankBranchDao();
								 	ArrayList<BankBranchBean> alist1=dao1.getAllBankBranchName();
								 	if(alist1!=null){
								 	for(BankBranchBean bean1:alist1){%>
								 <option value="<%=bean1.getAd_branch_id()%>"> <%=bean1.getBranch_code() %> <%=bean1.getBranch() %></option>
								 <%} 
								 }%>
											 			
		 			</select>		
		 			
					<label id="" class="error "></label>
<td >Debit Account <span class="red">*</span></td>
<td><select id="ad_account_id_d" name="ad_account_id_d"  class="form-control input-medium" >
		    			 <option value="">--Select--</option>
		    			
    				<%
    				
    				ArrayList<LedgerAccountBean> ledgerList=new LedgerAccountDao().getAllLedgerAccountName();
    				if(ledgerList!=null){
    				for(LedgerAccountBean bean1:ledgerList){
    				%>
    				 <option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_name() %></option>	
    				    
    				<%} }
    				
    				
        				%>
        				
    				
    				</select><label class="error"></label>
    				</td>

<td style="text-align: center;" colspan="2">
<input type="hidden" name="batch_no" value="<%=batch_no%>" />
<input id="btnPayrollSubmit"  type="submit" value="SubmitBatch"  class="btn btn-sm blue">

<input id="btnPayrollClear"  type="button" value="DeleteBatch"  class="btn btn-sm red">
</td>
</tr >


</table>
</form>
      </div>
	   </div>
	   </div>
		 
	    </div><!-- End portlet-body -->
	</div>
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
<%

}catch(Exception e){
	e.printStackTrace();
}%>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@ include file= "Layout/footer.jsp" %>
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/select2/select2.min.js"></script>


<script type="text/javascript">

$(document).ready(function(e){
	
	$('#ad_member_id').select2({allowClear: true});
	$('#ad_branch_id').select2();
	$('#trx_mode').select2();
	$('#ad_account_id_d').select2();
	
	
     $('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true});
     
     jQuery.validator.addMethod("validDate", function (value, element) {
         return this.optional(element) || /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/i.test(value);
     }, "Please enter valid Date.");
     

     jQuery( "#frmSubmitPayroll" ).validate({
   	  rules: {
   		  enter_amt: {
   		     required: true,
   		     number:true
   	    },
   	    ad_account_id_d :{
   	    	digits:true,
   	    	required:true
   	    },
   	    trx_mode:{
   	    	required:true,
   	    	alphabet:true
   	    },
   	    chq_no:{
   	    	required: true,
   	    	minlength:6,
   	    	maxlength:6
   	    },
   	    chk_date:{
   	    	required: true,
   	    	validDate:true
   	    },
   	    ad_branch_id:{
   	    	required: true,
   	    	number:true
   	    },
   	 excess_amt_other:{
   		number:true
   	 }
   	    
   	  }
   	});
	
});

$(function(){
	var V_Trx_No = $("#V_Trx_No").val();
	
	if(V_Trx_No.trim()!=""){
		$('.custom-messageBox').modal('show');
		$('#custom-page-message').html("Transaction number is "+V_Trx_No);
	}
	
});
</script>
<script type="text/javascript">
$(function(e){
	
	
	// add multiple select / deselect functionality
	   $('#selectall').on('click',function(event) {  //on click
		   var all_ttl =0;
	    	if(this.checked) { // check select status
	            $('.checkbox').each(function() { //loop through each checkbox
	          	this.checked = true;  //select all checkboxes with class "checkbox"   
	          	$(this).attr('checked');
	          	$(this).closest("span").addClass("checked");
	          	
	          	var idd=$(this).val();
                
				all_ttl=all_ttl+parseFloat($("#total_"+idd).val()) ;  
				$(' .setReadOnly').removeAttr('readonly');
	     	  });
	     	}else{
	            $('.checkbox').each(function() { //loop through each checkbox
	           	this.checked = false; //deselect all checkboxes with class "checkbox"
	           	$(this).removeAttr('checked');
	           	$(this).closest("span").removeClass("checked");
	           	$(' .setReadOnly').attr('readonly','readonly');
	     	   });        
	    	}
	    	$("#total_amt").val(all_ttl);
	    	var amt=number2text(parseFloat(all_ttl));
			$('#amt_in_words').val('Rs. '+amt);
		});
	
	$(".checkbox").click(function(e){
		 var all_ttl =0;
		 $('.checkbox').each(function() { //loop through each checkbox
           if(this.checked){  
				var idd=$(this).val();
             
				all_ttl=all_ttl+parseFloat($("#total_"+idd).val()) ;  
           }
         });
		 $("#total_amt").val(all_ttl);
		 var amt=number2text(parseFloat(all_ttl));
			$('#amt_in_words').val('Rs. '+amt);
	});
	
	$('#enter_amt').validate({
        
        rules: {
           
        	enter_amt: {
                required: true
             
                
        	
            }}});
	
	
	 $("#enter_amt").change(function(e){
		if(parseFloat($(this).val())>parseFloat($("#total_amt").val())){
			$("#head_amt").val(parseFloat($(this).val())-parseFloat($("#total_amt").val()));
			
		}else{
			$("#head_amt").val("");
		}
	}); 
	 
	 
	 $("#ad_branch_id").change(function(e){
			
		 
			
			var ad_branch_id  =$("#ad_branch_id").val()
			
			if(ad_branch_id!=0){
				loading_show();
			var dataString = {"ad_branch_id":ad_branch_id};
			
			
			$.ajax({
				type:"POST",
				url:"Ajax/getAllMemberByBranchId.jsp",
				data:dataString,
				success: function(data){
					 $('#ad_member_id').html(data);
					 //alert(data);
					  // $("#ad_member_id").trigger("chosen:updated");
					
					loading_hide();
				},
				error: function(jqXHR, textStatus, errorThrown){
					console.log(textStatus);
				}
				
			 });
			}else{
				
				return false;
			}
	 });
	
});//end dom

function cal(i){
	var thrift=$("#thrift_"+i).val();
	var loan=$("#loan_"+i).val();
	var excess=$("#excess_"+i).val();
	/* var dcf=$("#dcf_"+i).val(); */
	var festival=$("#festival_"+i).val();
	/* var guesthouse=$("#guesthouse_"+i).val(); */
 var total=0;
	if(isNaN(thrift.trim())==true || thrift.trim()==""){
		$("#thrift_"+i).val(0);
		
	}else if(isNaN(loan.trim())==true || loan.trim()==""){
		$("#loan_"+i).val(0);
	}else if(isNaN(excess.trim())==true || excess.trim()==""){
		$("#excess_"+i).val(0);
	}/* else if(isNaN(dcf.trim())==true || dcf.trim()==""){
		$("#dcf_"+i).val(0);
	} */else if(isNaN(festival.trim())==true || festival.trim()==""){
		$("#festival_"+i).val(0);
	}/* else if(isNaN(guesthouse.trim())==true || guesthouse.trim()==""){
		$("#guesthouse_"+i).val(0);
	} */
	
		 thrift=$("#thrift_"+i).val();
		 loan=$("#loan_"+i).val();
	 	 excess=$("#excess_"+i).val();
	 	 /* dcf=$("#dcf_"+i).val(); */
		 festival=$("#festival_"+i).val();
		/*  guesthouse=$("#guesthouse_"+i).val(); */
		 total=parseFloat(thrift)+parseFloat(loan)+parseFloat(festival)+parseFloat(excess);
		 
		$("#total_"+i).val(total);
	 var all_ttl =0;
	$('.checkbox').each(function() { //loop through each checkbox
		
        if(this.checked){  
				var idd=$(this).val();
				
				all_ttl=all_ttl+parseFloat($("#total_"+idd).val()) ; 
				
        }
      });
	
		 $("#total_amt").val(all_ttl);
		
		 var amt=number2text(parseFloat(all_ttl));
			$('#amt_in_words').val('Rs. '+amt);
	
}//end cal function


function handleClick(cb) {
	var i = $(cb).val();
	
	 if(cb.checked){ 
		 $("#thrift_"+i).removeAttr('readonly');
		 $("#loan_"+i).removeAttr('readonly');
		 $("#excess_"+i).removeAttr('readonly');
		/*  $("#dcf_"+i).removeAttr('readonly'); */
		 $("#festival_"+i).removeAttr('readonly');
		/*  $("#guesthouse_"+i).removeAttr('readonly'); */
		 $("#narration_"+i).removeAttr('readonly');
	 }else{
		 $("#thrift_"+i).attr('readonly','readonly');
		 $("#loan_"+i).attr('readonly','readonly');
		 $("#excess_"+i).attr('readonly','readonly'); 
		/*  $("#dcf_"+i).attr('readonly','readonly');  */
		 $("#festival_"+i).attr('readonly','readonly'); 
		/*  $("#guesthouse_"+i).attr('readonly','readonly'); */
		 $("#narration_"+i).attr('readonly','readonly');
	 }
	
}
function loading_show(){
    $('#modelLoad').removeClass('hide').addClass('show');
 }
 function loading_hide(){
   $('#modelLoad').addClass('hide').removeClass('show');
 }
 
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
<%}catch(Exception e){
	e.printStackTrace();
	
} %>
</body>
</html>