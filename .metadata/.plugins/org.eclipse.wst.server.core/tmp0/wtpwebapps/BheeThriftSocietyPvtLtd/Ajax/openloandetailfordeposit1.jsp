<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.text.ParseException"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="Model.Bean.LoanTrxBean"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="Model.Dao.LoanTrxDetailDao"%>
<%@page import="Model.Bean.LoanTrxDetailBean"%>
<%
String mid=request.getParameter("ad_member_id");
String loantrxid = request.getParameter("loan_trx_id");
Date d =(Date)session.getAttribute("openday");
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
int i=0;
double intrest=0.0,balance=0.0,prv_balance=0.0;
int ad_member_id=Integer.parseInt(mid);
int loan_trx_id=Integer.parseInt(loantrxid);

LoanTrxDetailBean lntbn = new LoanTrxDetailDao().getLastInterestPostingDate(ad_member_id, loan_trx_id);
if(lntbn==null){
	lntbn=new LoanTrxDetailDao().getLoanOpeningDate(ad_member_id, loan_trx_id);
}

LoanTrxDetailBean ltbean=new LoanTrxDetailBean();
LoanTrxBean ltb= new LoanTrxDao().getLoanTrxByPrmryId(loan_trx_id);
Date first=null,second=null;
if(lntbn.getTrx_date()!=null){
	first=lntbn.getTrx_date();
	
}else{
	
	first=ltb.getissue_date();
	
}
double tot_balance=0.0;
int tot_days=0;

double one_day_int=(ltb.getIntrest_rate());
ArrayList<LoanTrxDetailBean> ltbn = new LoanTrxDetailDao().getLoanTrxDetailByLoantrxId(loan_trx_id,lntbn.getTrx_date());
if(ltbn.isEmpty()!=true){
	for(LoanTrxDetailBean bean:ltbn){
		second=bean.getTrx_date();
		//prv_balance=bean.getBalance_amt();
		long diff = second.getTime() - first.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		tot_days+=diffDays;
		tot_balance=prv_balance*diffDays;
		balance=bean.getBalance_amt();
		intrest +=(tot_balance*one_day_int)/36500.0;
		first=second;
		prv_balance=balance;
	}
}



if(d!=null ){

	second=d;
	if (second.compareTo(first) >= 0) {
		long diff =  second.getTime()-first.getTime() ;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		//diffDays++;
		tot_days+=diffDays;
		tot_balance=balance*diffDays;
		intrest += (tot_balance*one_day_int)/36500.0;
        
    } 
	
}
intrest=new BigDecimal(intrest).setScale(0, RoundingMode.HALF_UP).doubleValue();


%>
<input type="hidden" value="<%=ad_member_id %>"  name="admid"/>
<input type="hidden" value="<%=loantrxid %>"  name="loantrxid"/>
<table  class="table table-bordered">

<tbody>
<tr>
		<td>Balance Amt :</td>
		<td><label><%=prv_balance %> </label></td>
		<td>Loan Interest : <span class="red">*</span></td>
		<td><label><%=intrest %> </label></td>
		<td>Total Amount : <span class="red">*</span></td>
		<td><label><%=prv_balance+intrest %></label ></td>
		
	</tr>
	
	
	<tr>
	<td>Last Trx Date</td>
		<td><%=sdf.format(first) %></td>
		<td>Int Cal. Days :</td>
		<td><label  name="days"><%=tot_days %></label></td>
	<td>Trx Type : <span class="red">*</span> </td>
		<td>
		<select class="form-control input-medium" name="trx_by" id="trx_by" >
					<option value="">--Select--</option>
					<option value="Cheque">Cheque</option>
					<option value="DD">DD</option>
					<option value="Online">Online</option>
					<option value="Adjustment">Adjustment</option>
		</select>
		</td>	
	</tr>
	<tr>
		<td>Deposit Amt :</td>
		<td><input class="form-control input-medium" name="loan_bal" id="loan_bal" 	value="0" disabled="disabled"/></td>
		<td>Loan Interest : <span class="red">*</span></td>
		<td><input class="form-control input-medium" name="intrest" id="intrest" 	value="0" disabled="disabled"/></td>
		<td>Total Amount : <span class="red">*</span></td>
		<td><input class="form-control input-medium"  id="total_amt" value="0" readonly="readonly"/></td>
		
	</tr>
	<tr >
	<tr id="row11" class="hide">
		<td colspan="6">	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		Voucher Detail
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			 <table class="table table-bordered">
			 <tr>
			 
				
		<td>Instrument no : <span class="red">*</span></td>
		<td><input class="form-control input-medium" type="text"  name="chk_no" id="chk_no" /></td>
		<td>Instrument Date : <span class="red">*</span></td>
		<td><input class="form-control input-medium date-picker" placeholder="dd/mm/yyyy" type="text" value="<%=new SimpleDateFormat("dd/MM/yyyy").format(session.getAttribute("openday"))%>"  name="chk_date" id="chk_date" /></td>
		<td>Branch : <span class="red">*</span></td>
		<td><select class="form-control input-medium"  name="ad_branch_id" id="ad_branch_id" >
			<option value="">--Select Branch--</option>
								<%BankBranchDao bankdao=new BankBranchDao();
								  ArrayList <BankBranchBean> banklist=bankdao.getAllBankBranchName();
								  if(banklist.isEmpty()!=true){
								  for(BankBranchBean bean:banklist){
								  %>
								  <option value="<%=bean.getBranch_code()+" | "+ bean.getBranch()%>"><%=bean.getBranch_code()+" "+bean.getBranch().trim() %></option>
								 <%} }%>
						</select><label class="error"></label>
		</td>
		</tr>
		</table>
		</div>
		</div>
		</td>
	</tr>
	<tr id="row22" class="hide">
	<td colspan="6">	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		Voucher Adjustment
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			 <table class="table table-bordered">
			 
  <tr align="left" ><th colspan="4" ><u>Debit(Dr)</u></th></tr>

						<tr>
						<td><label class="form-control input-medium"> <input type="checkbox" name="thrift" id="thrift" value="thrift" > Thrift A/C</label><label class="error"></label></td>
		    			
		    			<td><select id="ad_account_id" name="ad_account_id" class="form-control input-medium" >
		    			 <option value="0">--Select--</option>
		    			
    					<%
    					ArrayList<LedgerAccountBean> ledgerList1=new LedgerAccountDao().getAllLedgerAccount();
            			if(ledgerList1!=null){
            			for(LedgerAccountBean bean1:ledgerList1){	
        				%>	
        					<option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_name() %></option>
        					
        					<%
        						}
        					}
    					%>
    				</select>
                      </td>
                      <td >Balance</td>
                    <td><input class="form-control input-medium" name="thrift_bal" id="thrift_bal" 	value="0" /></td>
					</tr>
					<tr>
					<td><label class="form-control input-medium"> <input type="checkbox" name="share" id="share" value="share" > Share A/c</label><label class="error"></label></td>
		    			
		    			<td><select id="ad_account_id2" name="ad_account_id2" class="form-control input-medium" >
		    			 <option value="0">--Select--</option>
		    			
    					<%
    					 ledgerList1=new LedgerAccountDao().getAllLedgerAccount();
            			if(ledgerList1!=null){
            			for(LedgerAccountBean bean1:ledgerList1){	
        				%>	
        					<option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_name() %></option>
        					
        					<%
        						}
        					}
    					%>
    				</select>
                      </td>
                      <td >Balance</td>
                    <td><input class="form-control input-medium" name="share_bal" id="share_bal" 	value="0" /></td>
					</tr>
					<tr>
						<td><label class="form-control input-medium"> <input type="checkbox" name="excess" id="excess" value="excess" > Excess A/c</label><label class="error"></label></td>
		    			
		    			<td><select id="ad_account_id3" name="ad_account_id3" class="form-control input-medium" >
		    			 <option value="0">--Select--</option>
		    			
    					<%
    					 ledgerList1=new LedgerAccountDao().getAllLedgerAccount();
            			if(ledgerList1!=null){
            			for(LedgerAccountBean bean1:ledgerList1){	
        				%>	
        					<option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_name() %></option>
        					
        					<%
        						}
        					}
    					%>
    				</select>
                      </td>
                      <td >Balance</td>
                    <td><input class="form-control input-medium" name="excess_bal" id="excess_bal" 	value="0" /></td>
					</tr>
					<tr>
						<td><label class="form-control input-medium"> <input type="checkbox" name="sundary" id="sundary" value="sundary" > System Suspence A/c</label><label class="error"></label></td>
		    			
		    			<td><select id="ad_account_id4" name="ad_account_id4" class="form-control input-medium" >
		    			 <option value="0">--Select--</option>
		    			
    					<%
    					 ledgerList1=new LedgerAccountDao().getAllLedgerAccount();
            			if(ledgerList1!=null){
            			for(LedgerAccountBean bean1:ledgerList1){	
        				%>	
        					<option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_name() %></option>
        					
        					<%
        						}
        					}
    					%>
    				</select>
                      </td>
                      <td >Balance</td>
                    <td><input class="form-control input-medium" name="sundary_bal" id="sundary_bal" 	value="0" /></td>
					</tr>
   
  
				
   
</table>
			<div class="clearfix"></div>
	    </div><!-- End portlet-body -->
	</div> 
<!------------------------------------------------------------------- --></td>
	</tr>
	<tr>
                    
               

				 <td>Amount <span class="red">*</span></td>
	<td><input class="form-control input-medium" name="total_pay_amt" id="total_pay_amt1" readonly="readonly"	value="0" /></td>
				
				 
				
                  <td>Amt in Words</td>
                  <td  colspan="3"><input type="text" id="amt_in_words" name="amt_in_words" class=" form-control" readonly="readonly" />  </td>
                 
   </tr>
   <tr>
   <td>Narration</td>
   <td  colspan="5"><input type="text" id="narration_d" name="narration" class=" form-control" value="TO:- "  />  </td>
                 
   </tr>
   
</tbody>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

<script src="${pageContext.request.contextPath}/assets/plugins/select2/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/custom/components-pickers.js"></script>
<script type="text/javascript">
$(function(){
	$('select#ad_branch_id').select2();
	$('select#trx_by').select2();
	$('select#ad_account_id').select2();
	
	$('select#ad_account_id2').select2();
	
	$('select#ad_account_id3').select2();
	$('select#ad_account_id4').select2();
	
    $('#ad_account_id').select2('disable');
	
	$('#ad_account_id2').select2('disable');
	
	$('#ad_account_id3').select2('disable');
	
	$('#ad_account_id4').select2('disable');
	
	
	 $("#thrift").on("click", function() {
		 
		  if($(this).is(':checked')){
			  $('#ad_account_id').select2('enable');
		  }else{
			  $('#ad_account_id').select2('disable');
		  }
	  });
	 $("#share").on("click", function() {
		
		  if($(this).is(':checked')){
			  $('#ad_account_id2').select2('enable');
		  }else{
			  $('#ad_account_id2').select2('disable');
		  }
	  });
	 $("#excess").on("click", function() {
		 
		  if($(this).is(':checked')){
			  $('#ad_account_id3').select2('enable');
		  }else{
			  $('#ad_account_id3').select2('disable');
		  }
	  });
	 
	 $("#sundary").on("click", function() {
		 
		  if($(this).is(':checked')){
			  $('#ad_account_id4').select2('enable');
		  }else{
			  $('#ad_account_id4').select2('disable');
		  }
	  });
	 
	$('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate :'new Date(<%=(Date)session.getAttribute("openday")%>)' ,autoclose: true});
	
	$('select#trx_by').change(function(){
		var trx_val = $(this).val();
		trx_val = trx_val.toLowerCase();
		console.log(trx_val);
		if(trx_val=="cheque" || trx_val=="dd" || trx_val=="online"  ){
			$('#row11').removeClass('hide');
			$('#loan_bal').prop('disabled',false);
			$('#intrest').prop('disabled',false);
		}else{
			$('#row11').addClass('hide');
			
		}
		if(trx_val=="adjustment"){
			$('#row22').removeClass('hide');
			$('#loan_bal').prop('disabled',false);
			$('#intrest').prop('disabled',false);
		}else{
			$('#row22').addClass('hide');
			
		}
		if(trx_val==""){
			$('#loan_bal').prop('disabled',true);
			$('#intrest').prop('disabled',true);
		}
	});
	
	$('#ad_account_id').change(function(){
		
		
		var ad_member_id =$('#ad_member_id').val();
		var acc_id=$('#ad_account_id').val();
		if(acc_id==133){
			loading_show();
			$.get('Ajax/getAccountBalanceByMemberId.jsp?ad_member_id='+ad_member_id+'&acc_type=thrift',
				function(data,status){
				if(status=="success")
				{
					console.log(data);
					$('#custom-page-message').html("<div class='note note-info'>Total Available Thrift balance is"+data+"</div>");
					$('.custom-messageBox').modal('show');
					$('#thrift_bal').val(data);
					$("#total_pay_amt1").val(parseFloat($('#total_pay_amt1').val())+parseFloat(data));
					
					
					loading_hide();
				}
		 });
		}else{
			
			loading_show();
			$.get('Ajax/getAccountBalanceByMemberId.jsp?ad_member_id='+ad_member_id+'&acc_type=thrift',
				function(data,status){
				if(status=="success")
				{
					$('#custom-page-message').html("<div class='note note-info'>Please Select Thrift Account Only..!!</div>");
					$('.custom-messageBox').modal('show');
					$('#thrift_bal').val(0);
					$("#total_pay_amt1").val(parseFloat($('#total_pay_amt1').val())-parseFloat(data));
					
					
					loading_hide();
				}
		 });
			
		}
	});
$('#ad_account_id2').change(function(){
		
		
	var ad_member_id =$('#ad_member_id').val();
		var acc_id=$('#ad_account_id2').val();
		if(acc_id==132){
			loading_show();
			$.get('Ajax/getAccountBalanceByMemberId.jsp?ad_member_id='+ad_member_id+'&acc_type=share',
				function(data,status){
				if(status=="success")
				{
					console.log(data);
					$('#custom-page-message').html("<div class='note note-info'>Total Available Share balance is"+data+"</div>");
					$('.custom-messageBox').modal('show');
					$('#share_bal').val(data);
					$("#total_pay_amt1").val(parseFloat($('#total_pay_amt1').val())+parseFloat(data));
					loading_hide();
				}
		 });
		}else{
			
			loading_show();
			$.get('Ajax/getAccountBalanceByMemberId.jsp?ad_member_id='+ad_member_id+'&acc_type=share',
				function(data,status){
				if(status=="success")
				{
					$('#custom-page-message').html("<div class='note note-info'>Please Select Share Account Only..!!</div>");
					$('.custom-messageBox').modal('show');	
					$('#share_bal').val(0);
					$("#total_pay_amt1").val(parseFloat($('#total_pay_amt1').val())-parseFloat(data));
					loading_hide();
				}
			
			});
		}
	});
$('#ad_account_id3').change(function(){
	
	
	var ad_member_id =$('#ad_member_id').val();
	var acc_id=$('#ad_account_id3').val();
	if(acc_id==139){
		loading_show();
		$.get('Ajax/getAccountBalanceByMemberId.jsp?ad_member_id='+ad_member_id+'&acc_type=excess',
			function(data,status){
			if(status=="success")
			{
				console.log(data);
				$('#custom-page-message').html("<div class='note note-info'>Total Available Excess balance is"+data+"</div>");
				$('.custom-messageBox').modal('show');
				$('#excess_bal').val(data);
				$("#total_pay_amt1").val(parseFloat($('#total_pay_amt1').val())+parseFloat(data));
				loading_hide();
			}
	 });
	}else{
		
		loading_show();
		$.get('Ajax/getAccountBalanceByMemberId.jsp?ad_member_id='+ad_member_id+'&acc_type=excess',
			function(data,status){
			if(status=="success")
			{
				$('#custom-page-message').html("<div class='note note-info'>Please Select Excess Account Only..!!</div>");
				$('.custom-messageBox').modal('show');
				$('#excess_bal').val(data);
				$("#total_pay_amt1").val(parseFloat($('#total_pay_amt1').val())-parseFloat(data));
				loading_hide();
			}
	 });
		
	}
});



	  $("#intrest").change(function(e){
			var interest=$(this).val();
			var loan_amt=$('#loan_bal').val();
			var trx_val = $('#trx_by').val();
			trx_val = trx_val.toLowerCase();
			if(interest!="" && loan_amt!=""){
				if(trx_val=="cheque" || trx_val=="dd" || trx_val=="online"  ){
			$("#total_amt").val(parseFloat(loan_amt)+parseFloat(interest));
			$("#total_pay_amt1").val(parseFloat(loan_amt)+parseFloat(interest));
			var amt = number2text(parseFloat(loan_amt)+parseFloat(interest));
			
			$('#amt_in_words').val(amt);
				}else{
					$("#total_amt").val(parseFloat(loan_amt)+parseFloat(interest));
					var amt = number2text(parseFloat(loan_amt)+parseFloat(interest));
					
					$('#amt_in_words').val(amt);
					
				}
			
			}else{
				alert("Please Enter amt");
			}
			
			
	  });//end of interest change
	  $("#loan_bal").change(function(e){
			var interest=$('#intrest').val();
			var loan_amt=$(this).val();
			var trx_val = $('#trx_by').val();
			trx_val = trx_val.toLowerCase();
			if(interest!="" && loan_amt!=""){
				if(trx_val=="cheque" || trx_val=="dd" || trx_val=="online"  ){
			$("#total_amt").val(parseFloat(loan_amt)+parseFloat(interest));
			$("#total_pay_amt1").val(parseFloat(loan_amt)+parseFloat(interest));
			var amt = number2text(parseFloat(loan_amt)+parseFloat(interest));
			
			$('#amt_in_words').val(amt);
				}else{
					$("#total_amt").val(parseFloat(loan_amt)+parseFloat(interest));
					var amt = number2text(parseFloat(loan_amt)+parseFloat(interest));
					
					$('#amt_in_words').val(amt);
					
				}
			
			}else{
				alert("Please Enter amt");
			}
			
	  });//end of interest change
	  
	  
	$('#thrift_bal').change(function(e){
		var thrift=$(this).val();
		var share=$('#share_bal').val();
		var excess=$('#excess_bal').val();
		var sundary=$('#sundary_bal').val();
		$("#total_pay_amt1").val(parseInt(thrift)+parseInt(share)+parseInt(excess)+parseInt(sundary));
		
	})	;  
	
$('#share_bal').change(function(e){
	var share=$(this).val();
	var thrift=$('#thrift_bal').val();
	var excess=$('#excess_bal').val();
	var sundary=$('#sundary_bal').val();
	$("#total_pay_amt1").val(parseInt(thrift)+parseInt(share)+parseInt(excess)+parseInt(sundary));
	})	; 
	
$('#excess_bal').change(function(e){
	var excess=$(this).val();
	var share=$('#share_bal').val();
	var thrift=$('#thrift_bal').val();
	var sundary=$('#sundary_bal').val();
	$("#total_pay_amt1").val(parseInt(thrift)+parseInt(share)+parseInt(excess)+parseInt(sundary));
})	;

$('#sundary_bal').change(function(e){
	var sundary=$(this).val();
	var share=$('#share_bal').val();
	var thrift=$('#thrift_bal').val();
	var excess=$('#excess_bal').val();
	$("#total_pay_amt1").val(parseInt(thrift)+parseInt(share)+parseInt(excess)+parseInt(sundary));
})	; 

	    function loading_show(){
	        $('#modelLoad').removeClass('hide').addClass('show');
	     }
	     function loading_hide(){
	       $('#modelLoad').addClass('hide').removeClass('show');
	     } 
});

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