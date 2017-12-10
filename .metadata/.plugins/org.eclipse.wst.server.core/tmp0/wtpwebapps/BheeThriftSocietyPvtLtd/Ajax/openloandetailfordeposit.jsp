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
		<td><input class="form-control input-medium" name="loan_bal" id="loan_bal" 	value="0" /></td>
		<td>Loan Interest : <span class="red">*</span></td>
		<td><input class="form-control input-medium" name="intrest" id="intrest" 	value="0" /></td>
		<td>Total Amount : <span class="red">*</span></td>
		<td><label  id="total_amt">0</label ></td>
		
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
			 <td>Amount : <span class="red">*</span></td>
	<td><input class="form-control input-medium" name="total_pay_amt" id="total_pay_amt" readonly="readonly"	value="0" /></td>
				
		<td>Instrument no : <span class="red">*</span></td>
		<td><input class="form-control input-medium" type="text"  name="chk_no" id="chk_no" /></td>
	</tr>
	<tr>
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
			 
  <tr align="left" ><th colspan="6" ><u>Debit(Dr)</u></th></tr>

						<tr>
		    			<td >Acc Head :</td>
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
                    <td>Select Member : <span class="red"></span></td>
					<td>
					<select id="ad_member_id2" name="ad_member_id2" class="form-control input-medium" >
				 	<option value="0" >---Select Member---</option>
				 	
					 </select>
					<label class="error"></label></td>
					</tr>
					<tr>
                    
               

				 <td>Amount : <span class="red">*</span></td>
	<td><input class="form-control input-medium" name="total_pay_amt" id="total_pay_amt1" readonly="readonly"	value="0" /></td>
				
				 
				
                  <td >Narration :</td>
                  <td><input type="text" id="narration_d" class=" form-control input-medium" value="TO:- "  />  </td>
                 
   </tr>
   
   
  
				
   
</table>
			<div class="clearfix"></div>
	    </div><!-- End portlet-body -->
	</div> 
<!------------------------------------------------------------------- --></td>
	</tr>
</tbody>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

<script src="${pageContext.request.contextPath}/assets/plugins/select2/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/custom/components-pickers.js"></script>
<script type="text/javascript">
$(function(){
	$('select#ad_branch_id').select2();
	$('select#ad_account_id').select2();
	$('select#trx_by').select2();
	$('select#ad_member_id2').select2();
	 $('#ad_member_id2').select2('disable');
	 
	$('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate :'new Date(<%=(Date)session.getAttribute("openday")%>)' ,autoclose: true});
	
	$('select#trx_by').change(function(){
		var trx_val = $(this).val();
		trx_val = trx_val.toLowerCase();
		console.log(trx_val);
		if(trx_val=="cheque" || trx_val=="dd" || trx_val=="online"  ){
			$('#row11').removeClass('hide');
		}else{
			$('#row11').addClass('hide');
		}
		if(trx_val=="adjustment"){
			$('#row22').removeClass('hide');
		}else{
			$('#row22').addClass('hide');
		}
		
	});
	
	$('select#ad_member_id2').change(function(){
		
		loading_show();
		var ad_member_id =$(this).val();
		$.get('Ajax/getThriftBalanceByMemberId.jsp?ad_member_id='+ad_member_id,
				function(data,status){
				if(status=="success")
				{
					console.log(data);
					$('#custom-page-message').html("<div class='note note-info'>Total Available Thrift balance is"+data+"</div>");
					$('.custom-messageBox').modal('show');
					
					loading_hide();
				}
		 });
		
	});
	
	  $("#intrest").change(function(e){
			var interest=$(this).val();
			var loan_amt=$('#loan_bal').val();
			if(interest!="" && loan_amt!=""){
				console.log(parseFloat(loan_amt)+parseFloat(interest));
			$("#total_amt").html(parseFloat(loan_amt)+parseFloat(interest));
			$("#total_pay_amt").val(parseFloat(loan_amt)+parseFloat(interest));
			$("#total_pay_amt1").val(parseFloat(loan_amt)+parseFloat(interest));
			}else{
				alert("Please Enter amt");
			}
			
	  });//end of interest change
	  $("#loan_bal").change(function(e){
			var interest=$('#intrest').val();
			var loan_amt=$(this).val();
			if(interest!="" && loan_amt!=""){
				console.log(parseFloat(loan_amt)+parseFloat(interest));
			$("#total_amt").html(parseFloat(loan_amt)+parseFloat(interest));
			$("#total_pay_amt").val(parseFloat(loan_amt)+parseFloat(interest));
			$("#total_pay_amt1").val(parseFloat(loan_amt)+parseFloat(interest));
			}else{
				alert("Please Enter amt");
			}
			
	  });//end of interest change
	  
	    $("#ad_account_id").change(function(e){
		
		loading_show();
		var ad_account_id =$(this).val();
		$.get('Ajax/chk_acc_accessibiliti.jsp?ad_account_id='+ad_account_id,
				function(data,status){
				if(status=="success")
				{
					
						data=data.trim();
						$('#ad_member_id2').select2('enable');
						 $('#ad_member_id2').html(data); 
 				  		 $("#ad_member_id2").trigger("chosen:updated");
 				  		 
						/* $("#ad_member_id").select2("val", '0');
						$('#ad_member_id').select2('enable'); */
						
		 			
					
					loading_hide();
				}
		 });
	});//end  account id change event
	
	    function loading_show(){
	        $('#modelLoad').removeClass('hide').addClass('show');
	     }
	     function loading_hide(){
	       $('#modelLoad').addClass('hide').removeClass('show');
	     } 
});
</script>