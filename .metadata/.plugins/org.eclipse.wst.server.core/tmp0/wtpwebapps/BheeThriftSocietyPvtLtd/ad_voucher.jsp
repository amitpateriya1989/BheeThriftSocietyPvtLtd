<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Bean.BankBean"%>
<%@page import="Model.Dao.BankDao"%>
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
				<a href="#">Transaction</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Voucher</a><i class="fa fa-angle-right"></i>add</li>
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
	
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Manual Voucher</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmVoucher" autocomplete="off" action="AdVoucher?action=trx_forword" method="post">
			<table class="table table-bordered">
				 
					
				
				<tr>
					
					
					<td>Voucher Type : <span class="red">*</span></td>
					<td >
					<select class="form-control input-medium" name="voucher_type" id="voucher_type">
								 	<option value="">--Select Type--</option>
								 	<option value="Received">Received</option>
									<option value="Payment">Payment</option>
									<option value="Adjustment">Adjustment</option>
					</select><label class="error"></label>
					<input class="form-control input-medium"  type="hidden" id="voucher_no" name="voucher_no" value="pending" readonly="readonly" />
					<input type="hidden" class="form-control input-medium" name="voucher_status" id="voucher_status" value="Pending" readonly="readonly">
					</td>
					<td>Trx Type : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium" name="vtype" id="vtype">
									<option value="">---Select---</option>
									
					</select>
					</td>					
				</tr>
				<tr>
					<td>Voucher Date : <span class="red">*</span></td>
					<td>
					<%SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
					String voucher_date=sdf.format(request.getSession().getAttribute("openday"));
				    %>
					<input type="text" class="form-control input-medium date-picker" name="voucher_date" id="voucher_date" value="<%=voucher_date%>">
					<label class="error"></label>
					</td>
					<td>Voucher Amount : <span class="red">*</span></td>
					<td><input type="text" class="form-control input-medium" name="vamt" id="vamt"></td>					
				</tr>
				<tr>
				<td>Amt in words : <span class="red">*</span></td>
					<td colspan="3"><input type="text" class="form-control" name="amt_in_words" id="amt_in_words" readonly="readonly"></td>					
				</tr>
				<tr id="cheque_detail1">
					<td>Cheque Amt. : <span class="red">*</span></td>
					<td><input type="text" id="cheque_amt" name="cheque_amt"   class="form-control input-medium"></td>
				    <td>Cheque No. : <span class="red">*</span></td>
					<td><input type="text" id="cheque_no" name="cheque_no"   class="form-control input-medium"></td>
									
				</tr>
				<tr id="cheque_detail2">
				<td>Date : <span class="red">*</span></td>
					<td><input type="text" id="cheque_date" name="cheque_date" placeholder="dd/mm/yyyy"  class="form-control input-medium date-picker"></td>
					<td>Bank : <span class="red">*</span></td>
					<td>
					<select  id="cheque_bank" name="cheque_bank" class="form-control input-large" >
						
						<%
							BankDao bankdao=new BankDao();
							ArrayList<BankBean> banklist=bankdao.getAllBank();
							if(banklist!=null){
								for(BankBean bean:banklist){%>
								<option value="<%=bean.getBank()%>"><%=bean.getBank()%></option>
								<%	}
								}
							%>
					</select>
					</td>
				</tr>
				<tr id="dd_detail1">
					<td>DD Amt. : <span class="red">*</span></td>
					<td><input type="text" id="dd_amt" name="dd_amt"   class="form-control input-medium"></td>
					<td>DD No. : <span class="red">*</span></td>
					<td><input type="text" id="dd_no" name="dd_no" class="form-control input-medium"></td>
					
				</tr>
				<tr id="dd_detail2">
				<td>Date : <span class="red">*</span></td>
								<td><input type="text" id="dd_date" name="dd_date" class="form-control input-medium date-picker" placeholder="dd/mm/yyyy"></td>
								<td>Bank : <span class="red">*</span></td>
								<td><select  id="dd_bank" name="dd_bank" class="form-control input-large"  >
								
								<%
									if(banklist!=null){
										for(BankBean bean:banklist){%>
											<option value="<%=bean.getBank()%>"><%=bean.getBank() %></option>
									<%	}
									}
								%>
								</select>
							</td>
				</tr>
				<tr id="online_detail1">
								<td>Online Amt. : <span class="red">*</span></td>
					<td><input type="text" id="online_amt" name="online_amt"   class="form-control input-medium"></td>
								<td>Trx. Ref. No. : <span class="red">*</span></td>
								<td><input type="text" id="online_ref_no" name="online_ref_no" class="form-control input-medium"></td>
								
				</tr>
				<tr id="online_detail2">
					<td>Date : <span class="red">*</span></td>
								<td><input type="text" id="online_trx_date" name="online_trx_date" class="form-control input-medium date-picker" placeholder="dd/mm/yyyy"></td>
					<td>Bank : <span class="red">*</span></td>
					<td>
					<select  id="online_bank" name="online_bank"  class="form-control input-large" >
								
								<%
									
									if(banklist!=null){
										for(BankBean bean:banklist){%>
											<option value="<%=bean.getBank()%>"><%=bean.getBank() %></option>
									<%	}
									}
								%>
					</select>
					</td>
				</tr>
				<tr>
					
					<td colspan="4" align="center">
					  <input class="btn btn-md green input-medium" type="submit" name="generate_voucher" value="Generate Voucher"/>
					</td>
				</tr>
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div>

<!------------------------------------------------------------------- -->			
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
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<script>
<%

String date1=sdf.format((Date)session.getAttribute("openday"));
%>
jQuery(function() {  
	jQuery('#voucher_type').select2();
	jQuery('#vtype').select2();
	jQuery('#cheque_bank').select2();
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate:'<%=date1%>',autoclose: true});
	
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
			 /*  voucher_no: {
			     required: true,
			     
			     maxlength:8
		    }, */
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
		    },
		    cheque_no:{
		    	required: true,
		    	minlength:6,
		    	maxlength:6
		    },
		    cheque_date:{
		    	required: true,
		    	validDate:true
		    },
		    cheque_bank:{
		    	required: true,
		    	alphanumsapce:true
		    },
		    dd_no:{
		    	required: true,
		    	minlength:6,
		    	maxlength:6
		    },
		    dd_date:{
		    	required: true,
		    	validDate:true
		    },
		    dd_bank:{
		    	required: true,
		    	alphanumsapce:true
		    },
		    online_ref_no:{
		    	required: true,
		    	minlength:6,
		    	maxlength:6
		    },
		    online_trx_date:{
		    	required: true,
		    	validDate:true
		    },
		    online_bank:{
		    	required: true,
		    	alphanumsapce:true
		    }
		    
		  }
		});
});
</script>
<script type="text/javascript">

$(document).ready(function(){
	 $("#cheque_detail1").hide();
	 $("#cheque_detail2").hide();
	 $("#dd_detail1").hide();
	 $("#dd_detail2").hide();
	 $("#online_detail1").hide();
	 $("#online_detail2").hide();
	
	 
	 $('#vamt').change(function(e){
		var voucher_amt=$(this).val();
		var amt=number2text(voucher_amt);
		$("#amt_in_words").val(amt);
	 });
    $("#vtype").change(function(){
    	
    	var voucher_type = $("#voucher_type option:selected").val();    	
    	var trx_type=$("#vtype option:selected").val();
    	
    	if(voucher_type=="Payment"    &&  trx_type=="Cheque"   ){
       		$("#cheque_detail1").show();
       		$("#cheque_detail2").show();
    	}else if(voucher_type=="Received"  &&  trx_type=="Cheque"){
    		
    		$("#cheque_detail1").show();
       		$("#cheque_detail2").show();
       		
    	}else {
    		$("#cheque_detail1").hide();
    		$("#cheque_detail2").hide();
    	}
    	if(voucher_type=="Payment"  && trx_type=="DD"  ){
            $("#dd_detail1").show();
            $("#dd_detail2").show();
        }else if(voucher_type=="Received"  &&  trx_type=="DD"){
        	
        	 $("#dd_detail1").show();
             $("#dd_detail2").show();
        	
        }else{
        	$("#dd_detail1").hide();
        	$("#dd_detail2").hide();
        }
		if( voucher_type=="Payment" && trx_type=="Online"){
            $("#online_detail1").show();
            $("#online_detail2").show();
        }else if(voucher_type=="Received" && trx_type=="Online"){
        	
        	 $("#online_detail1").show();
             $("#online_detail2").show();
        }else{
        	$("#online_detail1").hide();
        	$("#online_detail2").hide();
        }
			
    });
    
     $("#voucher_type").change(function(){
    	
		var voucher_type = $("#voucher_type").val();    	
    	
		$("#vtype option[value='Cheque']").remove();
		$("#vtype option[value='DD']").remove();
		$("#vtype option[value='Online']").remove();
		$("#vtype option[value='Adjustment']").remove();
		
    	if(voucher_type=="Received"){
    		$("#vtype").append('<option value="Cheque">Cheque</option>');
    		$("#vtype").append('<option value="DD">Demand Draft</option>');
    		$("#vtype").append('<option value="Online">Online</option>');
			
    	}
    	
    	if(voucher_type=="Payment"){
    		$("#vtype").append('<option value="Cheque">Cheque</option>');
    		$("#vtype").append('<option value="DD">Demand Draft</option>');
    		$("#vtype").append('<option value="Online">Online</option>');
        }
    	
    	if( voucher_type=="Adjustment"){
			
			$("#vtype").append('<option value="Adjustment">Adjustment</option>');
			
        }
    	
    }); 
 
});//end dom  
    
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
	//response.sendRedirect("logout.jsp");
} %>
</body>
</html>