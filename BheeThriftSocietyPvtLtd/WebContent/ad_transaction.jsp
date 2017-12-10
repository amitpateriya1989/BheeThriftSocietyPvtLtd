<%@page import="Model.Dao.EmployeeDao"%>
<%@page import="Model.Bean.EmployeeBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Bean.TempTranxBean"%>
<%@page import="Model.Dao.TempTranxDao"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Bean.VoucherBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%@page import="Model.Dao.VoucherDao"%>
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
			<li><a href="#">Voucher</a><i class="fa fa-angle-right"></i>Create Voucher</li>
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
<%
	

//VoucherBean bean=(VoucherBean)session.getAttribute("voucher");
VoucherBean bean12 = new VoucherBean();
int ad_voucher_id=0;
try{
	ad_voucher_id=Integer.parseInt(request.getParameter("ad_voucher_id"));
}catch(NumberFormatException n){
	n.printStackTrace();
	
}

bean12.setAd_voucher_id(ad_voucher_id);
VoucherBean bean=new VoucherDao().getmanualVoucherById(bean12);


double dr=0.0;
double cr=0.0;

if(request.getParameter("ad_voucher_id")!=null){
	ad_voucher_id=Integer.parseInt(request.getParameter("ad_voucher_id"));
}			
%>


<!------------------------------------------------------------------- -->
	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption"> Voucher Entry</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	   
			<table class="table table-bordered">
				<tr>
					<td width="15%">Trx No. : <span class="red"></span></td>
					<td width="15%"><label name="voucher_no" id="voucher_no" style="color: black;font-weight: bold;" ><%=bean.getTrx_no() %> </label><label class="error"></label>
					</td>
					<td width="15%">Type : <span class="red"></span></td>
				    <td width="15%"><label name="voucher_type" id="voucher_type" style="color: black;font-weight: bold;" ><%=bean.getVoucher_type() %></label><label class="error"></label></td>	
				    <td  width="15%">Status :</td>
						
						<td width="15%" >	<lable name="voucher_status" id="voucher_status" style="color: black;font-weight: bold;"><% if(bean.getStatus()!=null){out.print(bean.getStatus());}else{out.print("Pending");} %></lable>
								
						</td>			
				</tr>
				
				<tr>
					<td width="15%">Date. : <span class="red"></span></td>
					<%
				String v_date=null;
				try{
				v_date=new SimpleDateFormat("dd/MM/yyyy").format(bean.getTrx_date().getTime());
				}catch(Exception e){
					e.printStackTrace();
				}
				%>
					<td width="15%"><label name="voucher_no" id="voucher_no" style="color: black;font-weight: bold;" ><%=v_date %> </label><label class="error"></label>
					</td>
					<td width="15%">Trx.By : <span class="red"></span></td>
				    <td width="15%"><label name="voucher_type" id="voucher_type" style="color: black;font-weight: bold;" ><%=bean.getVtype()  %></label><label class="error"></label></td>	
				    <td  width="15%">V.Amt :</td>
						
						<td width="15%" >	<lable name="voucher_status" id="voucher_status" style="color: black;font-weight: bold;"><%=bean.getVamt() %></lable>
								
						</td>			
				</tr>
				
				<tr>
					<td width="15%">Description : <span class="red"></span></td>
					<td width="15%"><label name="voucher_no" id="voucher_no" style="color: black;font-weight: bold;" ><%=bean.getDescription() %> </label><label class="error"></label>
					</td>
					</tr>
				
				<%if(bean.getVtype().equals("Cheque")){ 
				String cheque_date=null;
				cheque_date=new SimpleDateFormat("dd/MM/yyyyy").format(bean.getInstrument_date().getTime()).toString();%>	
			<tr id="cheque_detail">
			<td>Cheque No. :</td>
				
				<td><label id="cheque_no" name="cheque_no" style="font-weight: bold;"><%=bean.getInstrument_no() %></label></td>
				<td>Date :</td>
				
				
				<td><label id="cheque_date" name="cheque_date" style="font-weight: bold;"><%=cheque_date %></label></td>
				<td>Bank :</td>
				
				<td><label id="cheque_bank" name="cheque_bank" style="font-weight: bold;"><%=bean.getInstrument_from() %></label>
				
				</td>
			
			</tr>	
			<%}if(bean.getVtype().equals("DD")){ 
				String dd_date=null;
				dd_date=new SimpleDateFormat("dd/MM/yyyyy").format(bean.getInstrument_date().getTime()).toString();%>
				
			<tr id="dd_detail">
			<td>DD No. :</td>
				
				<td><input type="text" id="dd_no" name="dd_no" value="<%=bean.getInstrument_no() %>" readonly="readonly"  style="width: 170px;"></td>
				<td>Date :</td>
				
				<td><input type="date" id="dd_date" name="dd_date" value="<%=dd_date %>" readonly="readonly"  style="width: 170px;"></td>
				<td>Bank :</td>
				
				<td><input type="text"  id="dd_bank" name="dd_bank" value="<%=bean.getInstrument_from() %>" readonly="readonly"  style="width: 170px;" >
				
			
			</tr>
			<%}if(bean.getVtype().equals("Online")){
				v_date=new SimpleDateFormat("dd/MM/yyyyy").format(bean.getInstrument_date().getTime()).toString();%>
			<tr id="online_detail">
			<td>Trx. Ref. No. :</td>
				
				<td><input type="text" id="online_ref_no" name="online_ref_no" value="<%=bean.getInstrument_no() %>"   style="width: 170px;"></td>
				<td>Date :</td>
				
				<td><input text="text" id="online_trx_date" name="online_trx_date" value="<%=v_date %>"   style="width: 170px;"></td>
				<td>Bank :</td>
				
				<td><input type="text"  id="online_bank" name="online_bank"   style="width: 170px;" data-placeholder="Type" class="chosen-select" style="width:250px;" tabindex="2">
				
			
			</tr>	
				<%} %>							
					
					<tr>
				
				
				
				
				
				
				</tr>
			</table>
		
	    </div><!-- End portlet-body -->
	</div>
	
	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		Voucher Transaction
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			 <table class="table table-bordered">
			 
  <tr align="left" style="background-color:#666;color:#FC0"><th colspan="13" ><u>Debit(Dr)</u></th></tr>

						<tr id="d" >
		    			<th >Acc Head</th>
		    			<th><select id="ad_account_id_d"  nae="ad_account_id_d" class="form-control input-medium" >
		    			 <option value="0">--Select--</option>
		    			
    				<%
    				
    					ArrayList<LedgerAccountBean> ledgerList1=new LedgerAccountDao().getAllLedgerAccountName();
            			if(ledgerList1.isEmpty()!=true){
            			for(LedgerAccountBean bean1:ledgerList1){	
        				%>	
        					<option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_name() %></option>
        					
        					<%
        						}
        					}
    					
    				%>
        				
    				
    				</select>
                                                                </th>
               <th id="m1"  >Member</th>
                
                <th><select id="ad_member_id_d" name="ad_member_id_d" class="form-control input-medium" >
    						<option value="0">--Select--</option>
                                                   
                    </select>
                    
                    
                </th> 
                 <th id="m1"  >Employee</th>
                
                <th><select id="ad_employee_id_d" name="ad_employee_id_d" class="form-control input-medium" >
    						<option value="0">--Select--</option>
                            <%
	    			ArrayList<EmployeeBean> emplist=new EmployeeDao().getAllEmployee();
                	if(emplist.isEmpty()!=true){
                		for(EmployeeBean empbean:emplist){
                			%>
                			<option value="<%=empbean.getAd_employee_id()%>"><%=empbean.getEmployee_id()+" | "+empbean.getName() %></option>
                			<%
                		}
                	}
                	
	    				
	    				%>	                       
                    </select>
                    
                    
                </th> 
			</tr><tr align="left" id="d1">
				 <th >Amount</th>
				 
				 <%
				 TempTranxDao beand = new TempTranxDao();
				 int dramt =beand.getDrTempTranx(ad_voucher_id);
				 
				 %>
				 
				 <th><input  type="text" id="amt_d" class="form-control input-medium amt" value="<%=bean.getVamt()-dramt%>"  /> </th>
                  <th >Narration</th>
                  <th><input type="text" id="narration_d" class=" form-control input-large" value="TO:- "  />  </th>
                 <th colspan="2" align="right">
                 <input type="button" class="btn btn-xm blue" value="Ok" onclick="slct('d')" style="width: 70px; height: 30px;"  />
                  </th>
   </tr>
   
   
   <tr align="left" style="background-color:#666;color:#FC0"><th colspan="13" ><u>Credit(Cr)</u></th></tr>
	<tr align="left" id="c">
    			<th >Acc Head</th>
    			<th>
	    			<select id="ad_account_id_c"  name="ad_account_id_c" class="form-control input-medium">
	    				<option value="0">--Select--</option>
	    				<%
	    				
	        				
	    			
            			ArrayList<LedgerAccountBean> ledgerList2=new LedgerAccountDao().getAllLedgerAccountName();
            			if(ledgerList2.isEmpty()!=true){
            			for(LedgerAccountBean bean1:ledgerList2){	
        				%>	
        					<option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_name() %></option>
        					
        					<%
        						}
        					}
	    				
	    				%>
	    			</select>
                </th>
                <th id="m2">Member</th>
	                <th>
	                <select id="ad_member_id_c" name="ad_member_id_c" class="form-control input-medium">
	    				<option value="0">--Select--</option>
	                                                 
	                </select>
	                
                </th> 
               
                <th id="m2">Employee</th>
	                <th>
	                <select id="ad_employee_id_c" name="ad_employee_id_c" class="form-control input-medium">
	    				<option value="0">--Select--</option>
	    		 <%
	    			emplist=new EmployeeDao().getAllEmployee();
                	if(emplist.isEmpty()!=true){
                		for(EmployeeBean empbean:emplist){
                			%>
                			<option value="<%=empbean.getAd_employee_id()%>"><%=empbean.getEmployee_id()+" | "+empbean.getName() %></option>
                			<%
                		}
                	}
                	
	    				
	    				%>		
	                                                 
	                </select>
	                
                </th> 
				</tr>
				<tr align="left" id="c1">
				 <th  >Amount</th>
				 <%
				 TempTranxDao beanc = new TempTranxDao();
				 int cramt =beanc.getCrTempTranx(ad_voucher_id);
				 
				 %>
				 
				 <th><input class=" form-control input-medium amt"  type="text" id="amt_c"  value="<%=bean.getVamt()-cramt %>" />
				  </th>
                  <th >Narration</th>
                  <th><input type="text" id="narration_c"  class=" form-control input-large" value="By:- " /> 
                   </th>
                 <th colspan="2" align="right">
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
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		Transaction Detail
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<form action="AdTransaction?action=insert" method="post">
			
<div id=dynamic_table>
<table class="table table-bordered" >
<tr style="background-color: #06C; color: white; text-align: center; font-size: 14px;">
<th>S.No</th>
<th>LF No</th>
<th>Account</th>
<th>Party Name</th>
<th>Narration</th>
<th>Dabit(Dr.)</th>
<th>Credit(Cr.)</th>
<th>OPT</th>
</tr>
<%

TempTranxDao tempdao1=new TempTranxDao();

 ArrayList<TempTranxBean> templist=tempdao1.getAllTempTranxByVoucherId(ad_voucher_id);
 if(templist!=null){
	 int i=0;
	 String member="";
	 for(TempTranxBean tempbean:templist){%>
		<tr>
		 <td><%=++i %></td>
		 <td><%=tempbean.getLedger().getAc_no() %></td>
		  <td><%=tempbean.getLedger().getAc_name() %></td>
		  <%if(tempbean.getMember().getName()!=null ){ %>
		  <td><%=tempbean.getMember().getName() %></td>
		  <%}else{ %>
		   <td><%=member %></td>
		   <%} %>
		  <td><%=tempbean.getNarration() %></td>
		  <td><%=tempbean.getDramt()%></td>
		  <td><%=tempbean.getCramt()%></td>
		  <td><input type="button" class="btn btn-sm red" onclick="delete_trx(<%=tempbean.getAd_trx_temp_id()%>,<%=ad_voucher_id%>)" value="Delete"/>
		  </td>
		  </tr>
	 <%
	 dr=dr+tempbean.getDramt();
	 cr=cr+tempbean.getCramt();
	 }
 }

%>
    <tr style="background-color:#white;color:red;font-size:14px" >
    <th colspan="5" align="right">Total :</th>
    <th align="right">
    <label style="display:inline-block;text-align:left;"><%=dr %> </label> 
    </th>
    <th align="right">
    <label style="display:inline-block;text-align:left;"><%=cr %></label>
    <input type="hidden" id=dr value="<%=dr %>>" />
	<input type="hidden" id=cr value="<%=cr %>>" />
	<input type="hidden" id=v_amt value="<%=bean.getVamt() %>>" />
     </th>
    
     </tr>
    
</table> 
 </div>
 
 <table class="table table-bordered">
  <tr>
					<td  align="center">
						<input class="btn btn blue"  type="submit" name="save_voucher" value="Save Voucher" onclick="return finl(<%=dr %>,<%=cr %>,<%=bean.getVamt()%>) " />&nbsp;&nbsp;
						<input class="btn btn green" type="button" name="cancle_voucher" value="Cancle Voucher" onclick="finl_cancle(<%=ad_voucher_id%>)" />&nbsp;&nbsp;
					</td>
					</tr>
 </table>
 		</form>
 	
			
			<div class="clearfix"></div>
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
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@ include file= "Layout/footer.jsp" %>
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>

</body>

<script type="text/javascript">
$(document).ready(function(e) {
	
	 $("#ad_account_id_c").select2({placeholder: "Select",allowClear: true});
	 $("#ad_account_id_d").select2({placeholder: "Select",allowClear: true});
	 $("#ad_member_id_c").select2({placeholder: "Select",allowClear: true});
	 $('#ad_member_id_d').select2({placeholder: "Select",allowClear: true});
	 $("#ad_employee_id_c").select2();
	 $('#ad_employee_id_d').select2();
	 $('#ad_member_id_c').select2('disable');
	 $('#ad_member_id_d').select2('disable'); 
	 
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
}
	
	
$("#amt_d").keyup(function(e) {
    if(isNaN($(this).val()))
	{
		alert("Enter only Numeric Value.......!!");
		$(this).val("");
	}
});
$("#amt_c").keyup(function(e) {
    if(isNaN($(this).val()))
	{
		alert("Enter only Numeric Value.......!!");
		$(this).val("");
	}
});


//  account id change event


$("#ad_account_id_c").change(function(e){
	
	loading_show();
	var ad_account_id =$(this).val();
	$.get('Ajax/chk_acc_accessibiliti.jsp?ad_account_id='+ad_account_id,
			function(data,status){
			if(status=="success")
			{
				//alert(data);
					data=data.trim();
					$('#ad_member_id_c').select2('enable');
					 $('#ad_member_id_c').html(data); 
				  		 $("#ad_member_id_c").trigger("chosen:updated");
				  		 
					/*  $("#ad_member_id").select2("val", '0');
					$('#ad_member_id').select2('enable');  */
					
	 			
				
				loading_hide();
			}
	 });
});//end  account id change event
$("#ad_account_id_d").change(function(e) {
	loading_show();
 var ad_account_id=$(this).val();
 $.get('Ajax/chk_acc_accessibiliti.jsp?ad_account_id='+ad_account_id,
		 function(data,status){
		if(status=="success")
		{ //  alert(data);
			data=data.trim();
			$('#ad_member_id_d').select2('enable');
			 $('#ad_member_id_d').html(data); 
		  		 $("#ad_member_id_d").trigger("chosen:updated");
		  		loading_hide();
		}
 });
    
});



});

function finl(t_dr,t_cr,v_amt)
{
	if(t_dr!=t_cr)
	{
		alert("Debit And Credit Amounts Are Not Equal......!!");
		return false;
	}
	else
	{
		if(v_amt!=t_dr)
		{
			alert("Voucher Amount Does Not Match Equally With Dr And Cr Amount.......!!");
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
		var ad_member_id=$("#ad_member_id_d").val();
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
	/* 	 if(ad_member_id==0)
		{
			if(v_f_f_m_s_f_dr==0)
			{
		
				alert("Select Member !!");
				
				event.stop();
			}
			
		} 
		if(dramt.trim()=="")
		{
			alert("Enter Amount !!");
			
			event.stop();
		} */
		$.ajax({
			  	type: "POST",
			  	url: "AdTempVoucher?action=insert&ad_account_id="+ad_account_id+"&ad_member_id="+ ad_member_id+"&trx_type="+ trx_type+"&dramt="+ dramt+"&cramt="+ cramt+"&narration="+ narration,
			   	async:false,
			   	success: function(data)
			   	{	
				   	var url="ad_transaction.jsp?ad_voucher_id="+data;
					window.location.href=url;
		  		} 
		}); 
		
		
	}
	if(sts=='c')
	{	
		var ad_account_id=$("#ad_account_id_c").val();
		var ad_member_id=$("#ad_member_id_c").val();
		var narration =$("#narration_c").val();
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
		/* if(ad_member_id==0)
		{
			if(v_f_f_m_s_f_cr==0)
			{
		
				alert("Select Member !!");
				
				event.stop();
			}
		}
		if(cramt.trim()=="")
		{
			alert("Enter Amount !!");
						
			event.stop();
		}	 */		
		$.ajax({
			   type: "POST",
			   url: "AdTempVoucher?action=insert&ad_account_id="+ad_account_id+"&ad_member_id="+ ad_member_id+"&trx_type="+ trx_type+"&dramt="+ dramt+"&cramt="+ cramt+"&narration="+ narration,
			   async:false,
			   success: function(data)
			   {	
				   var url="ad_transaction.jsp?ad_voucher_id="+data;
					window.location.href=url;
		  	} }); 
	}
	
	
	
	
}

function finl_cancle(ad_voucher_id){
	//alert(ad_voucher_id);
	
	window.location.href="AdVoucher?action=deletevoucher&ad_voucher_id="+ad_voucher_id;
	
}
function loading_show(){
    $('#modelLoad').removeClass('hide').addClass('show');
 }
 function loading_hide(){
   $('#modelLoad').addClass('hide').removeClass('show');
 } 
 
 function delete_trx(ad_temp_trx_id,ad_voucher_id){
		
		loading_show();
		
		$.get('AdTransaction?action=deletetrx&ad_voucher_id='+ad_voucher_id+'&ad_trx_temp_id='+ad_temp_trx_id,
				function(data,status){
				if(status=="success")
				{
							
					loading_hide();
					location.reload();
				}
		 });
	}//end
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>		
</html>