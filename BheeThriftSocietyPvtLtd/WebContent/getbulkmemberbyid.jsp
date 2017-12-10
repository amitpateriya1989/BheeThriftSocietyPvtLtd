<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="Model.Bean.LoanTrxBean"%>
<%@page import="Model.Dao.MemberRegistrationMasterDao"%>
<%@page import="Model.Bean.MemberRegistrationMasterBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.BankBranchDao"%>


 <link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2-bootstrap.css"/>
 <%
 try{
int ad_member_id=Integer.parseInt(request.getParameter("ad_member_id"));
 
 %>
    



<script type="text/javascript">
$(function(e){
	$('#selectall').click(function(event) {  //on click 
		 var all_ttl =0;
		        if(this.checked) { // check select status
		            $('.checkbox').each(function() { //loop through each checkbox
		                this.checked = true;  //select all checkboxes with class "checkbox1"   
						var idd=$(this).val();
		                
						all_ttl=all_ttl+parseFloat($("#total_"+idd).val()) ;  
						$(' .setReadOnly').removeAttr('readonly');
						
		            });
		        }else{
		            $('.checkbox').each(function() { //loop through each checkbox
		                this.checked = false; //deselect all checkboxes with class "checkbox1"   
		                $(' .setReadOnly').attr('readonly','readonly');
		            });         
		        }
				$("#total_amt").val(all_ttl);
				
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
	});
	
	 $("#enter_amt").change(function(e){
		if(parseFloat($(this).val())>parseFloat($("#total_amt").val())){
			$("#head_amt").val(parseFloat($(this).val())-parseFloat($("#total_amt").val()));
			
		}else{
			$("#head_amt").val("");
		}
	}); 
	 
	 
	
	
});//end dom

function cal(i){
	var thrift=$("#thrift_"+i).val();
	var loan=$("#loan_"+i).val();
	var excess=$("#excess_"+i).val();
 var total=0;
	if(isNaN(thrift.trim())==true || thrift.trim()==""){
		$("#thrift_"+i).val(0);
		
	}else if(isNaN(loan.trim())==true || loan.trim()==""){
		$("#loan_"+i).val(0);
	}else if(isNaN(excess.trim())==true || excess.trim()==""){
		$("#excess_"+i).val(0);
	}
	
		 thrift=$("#thrift_"+i).val();
		 loan=$("#loan_"+i).val();
	 	 excess=$("#excess_"+i).val();
		 total=parseFloat(thrift)+parseFloat(loan)+parseFloat(excess);
		 
		$("#total_"+i).val(total);
	 var all_ttl =0;
	$('.checkbox').each(function() { //loop through each checkbox
		
        if(this.checked){  
				var idd=$(this).val();
				
				all_ttl=all_ttl+parseFloat($("#total_"+idd).val()) ;  
        }
      });
	
		 $("#total_amt").val(all_ttl);
	
}//end cal function


function handleClick(cb) {
	var i = $(cb).val();
	
	 if(cb.checked){ 
		 $("#thrift_"+i).removeAttr('readonly');
		 $("#loan_"+i).removeAttr('readonly');
		 $("#excess_"+i).removeAttr('readonly');
	 }else{
		 $("#thrift_"+i).attr('readonly','readonly');
		 $("#loan_"+i).attr('readonly','readonly');
		 $("#excess_"+i).attr('readonly','readonly'); 
	 }
	
}

</script>

<form id="frmpayroll" action="AdBulkDeposit?action=insert" method="post">

<table class="table table-striped table-bordered table-hover">

<tr>


<%

MemberRegistrationDao mrdao =new MemberRegistrationDao();

	MemberRegistrationBean bean=mrdao.getMemberRegistrationById(ad_member_id);
	
BankBranchBean bbbean = new BankBranchDao().getBankBranchById(bean.getBranch());


MemberRegistrationMasterBean  mrmbean = new  MemberRegistrationMasterDao().getMemberRegistrationMasterByMaxId();

double thrift=0.00;
if(mrmbean.getFgds_fund()!=0.00){
	thrift=mrmbean.getFgds_fund();
}

%>


</tr>
<tr>
<td>Branch Name</td><td><%=bbbean.getBranch() %></td>
<td>Branch Code</td><td><%=bbbean.getBranch_code() %></td>
</tr>
<tr>
<td>Branch IFSC</td><td><%=bbbean.getIfsc_code() %></td>
<td>Branch Address</td><td><%=bbbean.getAddress() %></td>
</tr>
</table>

<div class="table-responsive table-scrollable">
					<table id="dataTable1" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>Sno</th> 
								<th><input type="checkbox" name="selectall" id="selectall" >Select</th>
								<th>Pf No</th>
								<th>Staffno</th>
								<th>Name</th>
								<th>Thrift Amt</th>
								<th>Loan Amt</th>
								<th>Excess Amt</th>
								<th>Total </th>
							</tr>
							</thead>
						<tbody>
						
									<%
									
									int i=0;
								 	if(bean!=null){
								 	i=0;
								 	i++;%>
								 	
								 	<tr>
								 	<td><%=i %></td>
								 	<td style="text-align: center;">
								 	<input type="hidden" name="ad_member_id_<%=i %>" value="<%=bean.getAd_member_id() %>" /> 
								 	 <input class="checkbox" type="checkbox" name="chk_<%=i %>"  id="chk_<%=i %>" value="<%=i%>" onclick='handleClick(this);'></td>
								 	<td><%=bean.getAd_pf_no() %></td>
								 	<td><%=bean.getAd_society_no() %></td>
								 	<td><%=bean.getName() %></td>
								 	<td><input onblur="cal(<%=i %>)" class="form-control input-small amt setReadOnly" readonly="readonly" name="thrift_<%=i %>" id="thrift_<%=i %>" value="<%=thrift %>"></td>
								 	
								 	<%
								 	
								 	LoanTrxBean ltbean = new LoanTrxDao().getLoanTrxById1(bean.getAd_member_id());
								 	
								 	double loan = new LoanTrxDao().getLoanEmiForPayrollByMemberId(bean.getAd_member_id());
								 	
								 	
								 	%>
								 	
								 	<td><input onblur="cal(<%=i %>)" class="form-control input-small amt setReadOnly" id="loan_<%=i %>" readonly="readonly" name="loan_<%=i %>" value="<%=loan%>"></td>
								
								
								 	<td><input onblur="cal(<%=i %>)" class="form-control input-small amt setReadOnly" id="excess_<%=i %>" readonly="readonly" name="excess_<%=i %>" value="0.00"></td>
								 	<td>
								 	    <input id="total_<%=i %>" class="form-control input-small amt setReadOnly" name="total_<%=i %>"   value="<%=thrift+loan %>" readonly="readonly">
								 		<input type="hidden" value="<%=i %>" name="counter"  />
								 	</td>
								 	</tr>
								 	
								 <%} 
								 %>
								 
								
								 </tbody>
</table>
</div>
	<table class="table table-striped table-bordered table-hover">
				<tr>
					<td colspan="6" align="right">   Total</td>
					<td><input id="total_amt" class="form-control input-medium" readonly="readonly"> </td>
				</tr>
				<tr>
				     <td colspan="6" align="right">   Enter Received Amt</td>
				     <td><input id="enter_amt" name="enter_amt" class="form-control input-medium amt"> </td>
				</tr>
				<%-- <tr>
				
<td colspan="5" align="right">   Enter Amt</td>
<td><select name="ad_account_id" id="ad_account_id" readonly="readonly" class="form-control input-medium">
<option value="0">--select--</option>
<%LedgerAccountDao ldao=new LedgerAccountDao();
    				ArrayList<LedgerAccountBean> ledgerList=ldao.getAllLedgerAccount();
    				if(ledgerList!=null){
    				for(LedgerAccountBean bean1:ledgerList){
    				%>
    				 <option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_name() %></option>	
    				    
    				<%} }%>


</select> </td>
<td><input  class="form-control input-medium amt" name="head_amt" id="head_amt" readonly="readonly"></td>
</tr> --%>





<tr>
	
	<td colspan="3">Instrument Type <span class="red">*</span></td>
	<td>
			<select class="form-control input-medium" name="trx_mode" id="trx_mode"  >
			<option value=''>--select--</option>
			<option>Cheque</option>
			<option>DD</option>
			<option>NEFT</option>
			<option>RTGS</option>
			<option>Cash</option>
			</select>
	</td>
<td colspan="2">Instrument No <span class="red">*</span></td>
<td><input class="form-control input-medium" type='text' name="chq_no" id="chq_no"></td>
</tr>
<tr>
<td colspan="3"> Instrument Date <span class="red">*</span></td>
<td><input type="text" class="form-control input-medium date-picker" name="chk_date" id="chk_date" /></td>
<td colspan="2">Branch Name <span class="red">*</span></td><td>

<select name="ad_branch_id" id="ad_branch_id_2"  class="form-control input-medium" tabindex="2">

		 			<%BankBranchDao dao =new BankBranchDao();
								 	ArrayList<BankBranchBean> alist=dao.getAllBankBranchName();
								 	if(alist!=null){
								 	for(BankBranchBean bean1:alist){%>
								 <option value="<%=bean1.getAd_branch_id()%>"> <%=bean1.getBranch_code() %> <%=bean1.getBranch() %></option>
								 <%} 
								 }%>
											 			
		 			</select>		
		 			
					<label id="" class="error "></label>



</tr>

<tr>
<td colspan="7" style="text-align: center;" >
<input id="btnPayrollSubmit"  type="submit" value="Submit"  class="btn btn blue">


</td>
</tr >
</table>

</form>
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
			<script type="text/javascript">
jQuery(function() {       
	$('#ad_branch_id_2').select2();
	$('#trx_mode').select2();
jQuery('#dataTable1').DataTable({"scrollY":"200px","bPaginate": false});

jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate:new Date('<%=session.getAttribute("openday")%>'),autoclose: true});

jQuery.validator.addMethod("alphabet", function(value, element) {
	  return this.optional(element) || /^[a-zA-Z]*$/.test(value);
	}, "Please enter character only.");

jQuery.validator.addMethod("alphanumsapce", function(value, element) {
	  return this.optional(element) || /^[a-zA-Z.\s]*$/.test(value);
	}, "Please enter character and space only.");

jQuery.validator.addMethod("validDate", function (value, element) {
	 return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
}, "Please enter valid Date.");

jQuery( "#frmpayroll" ).validate({
	  rules: {
		  enter_amt: {
		     required: true,
		     number:true
	    },
	    ad_account_id :{
	    	digits:true
	    },
	    head_amt:{
	    	number:true
	    },
	    trx_mode:{
	    	required:true,
	    	alphabet:true
	    },
	    vtype : {
	      required: true,
	      alphabet: true
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
	    ad_branch_id_2:{
	    	required: true
	    }
	    
	  }
	});

$('#btnPayrollSubmit').click(function(){
	if(jQuery( "#frmpayroll" ).valid()==false){
		return false;
	}
});


$("#enter_amt").change(function(e){
	if(parseFloat($(this).val())!=parseFloat($("#total_amt").val())){
		
		$('.custom-messageBox').modal('show');
		$('#custom-page-message').html("Please Enter Valid Amount");
		$('#btnPayrollSubmit').attr('disabled','disabled');
	}else{
		$('#btnPayrollSubmit').removeAttr('disabled');;
	}
});




	});
</script>

<script type="text/javascript">
	$(function(){
		jQuery('.date-picker2').datepicker({format: 'dd/mm/yyyy',autoclose: true});
	});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>