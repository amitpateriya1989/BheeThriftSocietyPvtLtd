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

<%
try{
int ad_branch_id=Integer.parseInt(request.getParameter("ad_branch_id"));
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
		 console.log($("#chk_"+i));
		 $("#chk_"+i).closest( "tr" ).addClass("green");
		 $("#thrift_"+i).removeAttr('readonly');
		 $("#loan_"+i).removeAttr('readonly');
		 $("#excess_"+i).removeAttr('readonly');
	 }else{
		 $("#chk_"+i).closest( "tr" ).removeClass("green");
		 $("#thrift_"+i).attr('readonly','readonly');
		 $("#loan_"+i).attr('readonly','readonly');
		 $("#excess_"+i).attr('readonly','readonly'); 
	 }
	
}

</script>

	<form id="frmpayroll" action="AdBulkDeposit?action=insert" method="post">

		<table class="table table-striped table-bordered table-hover">
		
			

				<%
BankBranchBean bbbean = new BankBranchDao().getBankBranchById(ad_branch_id);

MemberRegistrationMasterBean  mrmbean = new  MemberRegistrationMasterDao().getMemberRegistrationMasterByMaxId();

double thrift=0.00;
if(mrmbean.getFgds_fund()!=0.00){
	thrift=mrmbean.getFgds_fund();
	
}

%>


			
			<tr>
				<td>Branch Name</td>
				
				<td><%=bbbean.getBranch() %></td>
				<td>Branch Code</td>
				
				<td><%=bbbean.getBranch_code() %></td>
			</tr>
			<tr>
				<td>Branch IFSC</td>
				
				<td><%=bbbean.getIfsc_code() %></td>
				<td>Branch Address</td>
				
				<td><%=bbbean.getAddress() %></td>
			</tr>
			
			</table>
			
			<div class="table-responsive table-scrollable">
					<table id="dataTable1" class="table table-bordered ">
						<thead>
							<tr>
								
								<th><input type="checkbox" name="selectall" id="selectall">&nbsp;&nbsp;Select</th>
								<th>Staff No</th>
								<th>Name</th>
								<th>Thrift Amt</th>
								<th>Loan Amt</th>
								<th>Excess Amt</th>
								<th>Total</th>
							</tr>
						</thead>
						<tbody>

							<%

									int i=0;
							        double thrift_total=0.0,loan_total=0.0,excess_total=0.0;
									MemberRegistrationDao mrdao =new MemberRegistrationDao();
								 	ArrayList<MemberRegistrationBean> mralist=mrdao.getMemberByBranch(ad_branch_id);
								 	if(mralist!=null){
								 	i=0;
								 	for(MemberRegistrationBean bean:mralist){i++;
								 	thrift_total+=thrift;
								 	
								 	%>

							<tr id="tr_<%=i %>">
								
								<td style="text-align: center;"><input type="hidden"
									name="ad_member_id_<%=i %>" 
									value="<%=bean.getAd_member_id() %>" /> <input onclick='handleClick(this);' 
									class="checkbox" type="checkbox" name="chk_<%=i %>"
									id="chk_<%=i %>" value="<%=i%>"></td>
								
								<td><%=bean.getAd_society_no() %></td>
								<td><%=bean.getName() %></td>
								<td><input  readonly="readonly" onblur="cal(<%=i %>)" class="form-control input-small amt setReadOnly" 
									name="thrift_<%=i %>" id="thrift_<%=i %>" value="<%=thrift %>" ></td>

								<%
								 	
								double loan = new LoanTrxDao().getLoanEmiForPayrollByMemberId(bean.getAd_member_id());
								 	loan_total+=loan;
															 
								 	%>

								<td><input readonly="readonly" onblur="cal(<%=i %>)" class="form-control input-small amt setReadOnly"
									id="loan_<%=i %>" name="loan_<%=i %>" value="<%=loan%>" ></td>


								<td><input  readonly="readonly" onblur="cal(<%=i %>)" class="form-control input-small amt setReadOnly"
									id="excess_<%=i %>" name="excess_<%=i %>" value="0.00" ></td>
									
								<td><input  readonly="readonly" id="total_<%=i %>" class="form-control input-small amt setReadOnly"
									name="total_<%=i %>" value="<%=thrift+loan %>"
									readonly="readonly"></td>
							</tr>

							<%} 
		
								 	}%>
								 	</tbody>
								 	<tfoot>
								 	
								 	<tr style="font-size: 14px;font-weight: bold;">
								 		<td colspan="3" align="right">Total</td><td ><%=thrift_total %></td><td><%=loan_total %></td>
								 	<td></td><td></td>
								 	</tr>
								 	</tfoot>
</table>
</div>
					
					
		
						<table class="table table-striped table-bordered table-hover">
						<tr>
							
								<td align="right" colspan="5">Total</td>
								
								<td align="right"><input type="hidden" value="<%=i %>" name="counter" />
								<input id="total_amt" readonly="readonly"></td>
							</tr>
							<tr>
								<td  align="right" colspan="5">Enter Received Amount <span class="red">*</span></td>
									
								<td align="right"><input id="enter_amt" name="enter_amt" class="form-control input-medium amt">
								<label class="error"></label>
								</td>
							</tr>
							<%-- <tr>
								
								<td  align="right" colspan="4">Enter Amount </td>
								<td align="right"><select
									name="ad_account_id" id="ad_account_id" readonly="readonly" class="form-control input-medium">
									<option
											value="0">--select--</option>
										<%LedgerAccountDao ldao=new LedgerAccountDao();
    				ArrayList<LedgerAccountBean> ledgerList=ldao.getAllLedgerAccount();
    				if(ledgerList!=null){
    				for(LedgerAccountBean bean1:ledgerList){
    				%>
										<option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_name() %></option>

										<%} }%>


								</select>
								<label class="error"></label>
								</td>
								<td align="right"><input value="" name="head_amt" id="head_amt" class="form-control input-medium"
									readonly="readonly"></td>
							</tr> --%>
			<tr>
				<td colspan="2">Instrument Type <span class="red">*</span></td>
				
				<td><select name="trx_mode" id="trx_mode" class="form-control input-medium"><option
							value=''>--select--</option>
						<option>Cheque</option>
						<option>DD</option>
						<option>NEFT</option>
						<option>RTGS</option>
						<option>Cash</option>
						<option>Transfer</option>
				</select><label class="error"></label>
				</td>
				<td colspan="2">Instrument No <span class="red">*</span></td>
				
				<td><input type='text' name="chq_no" id="chq_no" class="form-control input-medium">
				<label class="error"></label>
				</td>
			</tr>
			<tr>
				<td colspan="2">Instrument Date <span class="red">*</span></td>
				
				<td><input  name="chk_date" id="chk_date" class="form-control input-medium date-picker" type="text" placeholder="dd/mm/yyyy"/>
				<label class="error"></label>
				</td>
				<td colspan="2">Branch Name <span class="red">*</span></td><td>
		 			<select name="ad_branch_id" id="ad_branch_id3"  class="form-control input-medium" tabindex="2">
		 			
		 			<%BankBranchDao dao =new BankBranchDao();
								 	ArrayList<BankBranchBean> alist=dao.getAllBankBranchName();
								 	if(alist!=null){
								 	for(BankBranchBean bean:alist){%>
								 <option value="<%=bean.getAd_branch_id()%>"> <%=bean.getBranch_code() +" | " + bean.getBranch() %></option>
								 <%} 
								 }%>
											 			
		 			</select>		
		 			
					<label id="" class="error "></label>
					</td>
				
				
			</tr>

			
			<tr>
				<td colspan="6" style="text-align: center;">
				<input id="btnPayrollSubmit" type="submit" value="Submit" class="btn btn blue"></td>
			</tr>
		</table>
 
	</form>
	
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#ad_branch_id3').select2();
		$('#trx_mode').select2();
	});
</script>
<script type="text/javascript">
$(function() {  
	 
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
	    ad_branch_id3:{
	    	required: true,
	    	number:true
	    }
	    
	  }
	});

$('#btnPayrollSubmit').click(function(){
	if(jQuery( "#frmpayroll" ).valid()==false){
		return false;
	}else{
		
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

<%}catch(Exception e){
	e.printStackTrace();
} %>