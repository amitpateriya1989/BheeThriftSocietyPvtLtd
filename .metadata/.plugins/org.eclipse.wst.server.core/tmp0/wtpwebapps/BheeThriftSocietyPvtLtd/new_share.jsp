<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Bean.BankBean"%>
<%@page import="Model.Dao.BankDao"%>
<%@page import="Model.Dao.MemberShareDao"%>
<%@page import="Model.Bean.MemberShareBean"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.ShareBean"%>
<%@page import="Model.Dao.ShareDao"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2-bootstrap.css"/>
<link rel="stylesheet" href="assets/plugins/data-tables/DT_bootstrap.css"/>
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
				<a href="#">Setup</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Share</a><i class="fa fa-angle-right"></i> Add and View</li>
			
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
<div class="col-md-12">
<!-- Containt Start -->
<div id="ErMessage"></div><!-- End ErMessage -->
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
	   <div class="caption">Personal Information</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Name : <span class="red">*</span></td>
					<td width="35%">
					<select class="form-control input-large " name="ad_member_id" id="ad_member_id">
						<option value="">--Select Member--</option>
						<%MemberRegistrationDao dao=new MemberRegistrationDao();
						ArrayList<MemberRegistrationBean> alist=dao.getAllApprovedMemberlist();
						if(alist!=null){
						for(MemberRegistrationBean bean:alist){%>
						<option value="<%=bean.getAd_member_id()%>"><%=bean.getAd_society_no()+" | " %><%=bean.getName() %></option>
						<%} 
						 }%>
					</select>
					<label class="error"></label>
					</td>
					<td width="15%">PFNo : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium" type="text" id="ad_pf_no" name="ad_pf_no" readonly="readonly" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Mem.No : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="ad_society_no" name="ad_society_no" readonly="readonly"/><label class="error"></label></td>
					<td>Type : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="ad_member_type_id" name="ad_member_type_id" readonly="readonly" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Status : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="ad_member_status_id" name="ad_member_status_id" readonly="readonly" /><label class="error"></label></td>
					<td>Father : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="father" name="father" readonly="readonly" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Husband : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="husband" name="husband" readonly="readonly" /><label class="error"></label></td>
					<td>Gender : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="ad_gender_id" name="ad_gender_id" readonly="readonly" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Category : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="ad_category_id" name="ad_category_id" readonly="readonly" /><label class="error"></label></td>
					<td>DOB : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text"  id="dob" name="dob" readonly="readonly" /><label class="error"></label></td>
				</tr>
			</table>
	    </div><!-- End portlet-body -->
	</div>
	
<!------------------------------------------------------------------- -->	
	
	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		Allocated Share Detail
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<form id="frmNewShare" action="AdMemberShare?action=insert" method="post" autocomplete="off">
			<input type="hidden" name="ad_member_id_1" id="ad_member_id_1" value="0" />	
			<table class="table table-bordered">
				<tr>
					<td width="15%">Date : <span class="red">*</span></td>
					<td width="35%">
					<%
						SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
						String date1=sdf.format((Date)session.getAttribute("openday"));
						%>
					<input class="form-control input-medium date-picker" type="text" id="date_of_allocation" name="date_of_allocation"  value="<%=date1%>" /><label class="error"></label></td>
					<td width="15%">No Of Share : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium" type="text" id="no_of_share" name="no_of_share" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Per Share Rate : <span class="red">*</span></td>
					<td>
					<%ShareDao sdao=new ShareDao();
						ShareBean bean=sdao.getShareRate();%>
					<input class="form-control input-medium" type="text" id="per_share_rate" name="per_share_rate" value="<%=bean.getPer_share_rate()%>" readonly="readonly"/><label class="error"></label></td>
					<td>Share Amount : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="share_amt" name="share_amt" readonly="readonly" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Batch No : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="batch_no" name="batch_no" value="Pending" readonly="readonly" /><label class="error"></label></td>
					<td>Amt in Wrds : <span class="red">*</span></td>
					<td><input class="form-control input-large" type="text" id="amt_in_words" name="amt_in_words" readonly="readonly" /><label class="error"></label></td>
				</tr>
				<tr>
				<td>Allocated No From : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="share_no_form" name="share_no_form" value="Pending" readonly="readonly" /><label class="error"></label></td>
					<td>Allocated No To : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="share_no_to" name="share_no_to" value="Pending" readonly="readonly" /><label class="error"></label></td>
					
				</tr>
				<tr>
					<td>Trx Type : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium" name="vtype" id="vtype">
									<option value="">---Select---</option>
									<option value="Adjustment">Adjustment</option>
									<option value="Cheque">Cheque</option>
									<option value="DD">Demand Draft</option>
									<option value="Online">Online</option>
									
					</select>
					</td>
					<td>Status : <span class="red">*</span></td>
					<td ><input class="form-control input-medium" type="text" id="status" name="status" value="Pending" readonly="readonly" /><label class="error"></label></td>
					
				</tr>
				<tr>
				<td>Cheque/DD Date : <span class="red">*</span></td>
					<td><input class="form-control input-medium date-picker" type="text" id="chk_dd_date" name="chk_dd_date" value="<%=date1%>" /><label class="error"></label></td>
				
				<td>Cheque/DD No : <span class="red">*</span></td>
					<td ><input class="form-control input-medium" type="text" id="chk_dd_no" name="chk_dd_no"  /><label class="error"></label></td>
				</tr>
				<tr>
					
					<td>Bank : <span class="red">*</span></td>
					<td ><input type="text" class="form-control input-medium" id="chk_bank" name="bank" value="Central Bank of India"/>
					<label class="error"></label></td>
					<td>Branch : <span class="red">*</span></td>
					<td ><select class="form-control input-medium" id="chk_branch" name="branch" >
					<option value="">--Select Branch--</option>
								<%BankBranchDao bankdao=new BankBranchDao();
								  ArrayList <BankBranchBean> banklist=bankdao.getAllBankBranchName();
								  if(banklist.isEmpty()!=true){
								  for(BankBranchBean bean1:banklist){
								  %>
								  <option value="<%=bean1.getBranch()%>"><%=bean1.getBranch().trim() %></option>
								 <%} }%>
						</select><label class="error"></label>
					
					</td>
				</tr>
				<tr>
					
					<td colspan="4" align="center">
					  <input class="btn btn blue" type="submit" name="Submit" value="Submit"/>
					  <input class="btn btn green" type="reset" name="Clear "/>
					</td>
				</tr>
			</table>
		 </form>
	    </div><!-- End portlet-body -->
	</div>
<!------------------------------------------------------------------- -->
<!-- -------------------------------------------------------------- -->
	<div class="portlet box green">
	<div class="portlet-title">
	<div class="caption">Allocated Share Detail</div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	</div><!-- end portlet-title -->
	<div class="portlet-body">
		    <table class="table table-bordered">
		    <thead>
		    	<tr>
		    	  <th>Allocation</th>
		    	 <th>Inst. Date </th>
		    	  <th>Share Amount</th>
		    	  <th>Qunt Of Share</th>
		    	  <th>Batch No</th>
		    	  <th>Share From</th>
		    	  <th>Share To</th>
		    	  <th>Status</th>
		    	 
		    	</tr>
		    </thead>
		    <tbody class="fillRecord">
		    	
		    </tbody>
		    </table>
		<div class="clearfix"></div>
	</div><!-- End portlet-body -->
	</div><!-- En portlet box -->

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
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="assets/scripts/custom/components-pickers.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<script>

jQuery(function() { 
	 
	$('#chk_branch').select2();
	$('#vtype').select2();
	jQuery('#dataTable1').DataTable();
	$('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate : '<%=date1%>',autoclose: true});
	
	jQuery.validator.addMethod("Alphanumspacedot", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z.,\s]*$/.test(value);
		}, "Please enter latter and space only.");
	
	jQuery( "#frmNewShare" ).validate({
		  rules: {
			  ad_member_id: {
		      required: true
		      
		    },
		    date_of_allocation: {
			      required: true
			      
			    
			    },
			    per_share_rate: {
				      required: true
				      
				    
				    },
				    no_of_share: {
				    	 digits: true,
					      required: true
					      
					    
					    },
		    chk_dd_date: {
			      required: true
			      
			    
			    },
			    chk_dd_no: {
				      required: true,
				      digits: true,
				      maxlength:6,
				      minlength:6
				    },
				    branch: {
					      required: true,
					      Alphanumspacedot:true
					     
					    },
					    vtype:{
					    	required: true
					    }
	
		  }
		});
});
</script>
<script type="text/javascript">

$(document).ready(function(e) {
	$("#ad_member_id").select2();
	$("#ad_member_id").change(function(e) {
		
		loading_show();
		var id = $(this).val();
		
		 $("#ad_member_id_1").val(id);
		
		 if(id!=""){
		 var dataString = "action=view&ad_member_id="+id;
		 $.ajax({
             type: "POST",
             url: "AdMemberRegistration",
             data: dataString,
             dataType: "json",
            
             //if received a response from the server
             success: function( data, textStatus, jqXHR) {
                 //our country code was correct so we have some information to display
                 
                  if(data.success){
                	 //alert(data.MemberInfo.branch.bank_region.region);
                	  $("#ad_pf_no").val(data.MemberInfo.ad_pf_no);
                	  $("#ad_society_no").val(data.MemberInfo.ad_society_no);
                	  $("#father").val(data.MemberInfo.father);
                	  $("#husband").val(data.MemberInfo.husband);
                	  $("#dob").val(data.MemberInfo.dob);
                	  $("#ad_gender_id").val(data.MemberInfo.gender.gender);
                	  $("#ad_category_id").val(data.MemberInfo.category.category);
                	  $("#ad_member_type_id").val(data.MemberInfo.member_type.member_type);
                	  $("#ad_member_status_id").val(data.MemberInfo.member_status.member_status);
                	  $("#pan_no").val(data.MemberInfo.pan_no);
                	  $("#aadhar_no").val(data.MemberInfo.aadhar_no);
                	  loading_hide();
                  }
             },
            
             //If there was no resonse from the server
             error: function(jqXHR, textStatus, errorThrown){
                  console.log("Something really bad happened " + textStatus);
                   $("#ajaxResponse").html(jqXHR.responseText);
             }
  
         });   
		
		
		 //Fill share table according to member id
		var tblRecord ="";
		 var status="";
		$.ajax({
			type: "POST",
			dataType: "json",
			url: "AdMemberShare",
			data:{'action':'list','ad_member_id':id},
			success:function(json){
				if(jQuery.isEmptyObject(json)){
					$('.fillRecord').html("<tr><td colspan='9' class='text-center'>Record Not Found</td></tr>");
				}else{
					$.each(json, function(key, val) {
				
						if(val.isactive==true){
							status="Approved";
						}else{
							status="Pending"
						}
						
					tblRecord = tblRecord + "<tr>"+
			    	 "<td>"+ val.date_of_allocation +"</td>"+
			    	 "<td>"+ val.chk_dd_date +"</td>"+
			    	 "<td>"+ val.share_amt +"</td>"+
			    	 "<td>"+ val.qnt_of_share +"</td>"+
			    	 "<td>"+ val.batch_no +"</td>"+
			    	 "<td>"+ val.share_no_form +"</td>"+
			    	 "<td>"+ val.share_no_to +"</td>"+
			    	 "<td>"+ status +"</td>"+
			    	
			    	"</tr>"; 
					});//end each loop
					
					$('.fillRecord').html(tblRecord);
					
				}//end if else
				
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log(textStatus);
			}
		});	//end ajax
		 
   }//end check member id
		 
 });//end member id change event
	
	function loading_show(){
        $('#modelLoad').removeClass('hide').addClass('show');
     }
     function loading_hide(){
       $('#modelLoad').addClass('hide').removeClass('show');
     } 
     
     
	$("#no_of_share").blur(function (e) {
		
		loading_show();
		
		if($(this).val()!=0  && $(this).val()!=""){
			var no_of_share =parseFloat($(this).val());
		var per_share_rate=parseFloat($("#per_share_rate").val());
	
		if(!isNaN(no_of_share)){
			
			$("#share_amt").val(per_share_rate*no_of_share);
			var amt=number2text(per_share_rate*no_of_share);
			$('#amt_in_words').val(amt);
		}else{
			$("#share_amt").val('');
			no_of_share=0;
		}
		
		
		 var dataString = "action=getBatchShareno&no_of_share="+no_of_share;
		 
		 $.ajax({
             type: "POST",
             url: "AdMemberShare",
             data: dataString,
             dataType: "json",
            
             //if received a response from the server
             success: function( data, textStatus, jqXHR) {
                 //our country code was correct so we have some information to display
                 
                  if(data.success){
                	   $("#batch_no").val(data.ShareInfo.batch_no+1);
                       $("#share_no_form").val(data.ShareInfo.share_no_to+1);
                       $("#share_no_to").val(data.ShareInfo.share_no_to+no_of_share);
                       loading_hide();
                  } 
                  //display error message
                  else {
                      $("#ajaxResponse").html("<div><b>Country code in Invalid!</b></div>");
                  }
             },
            
             //If there was no resonse from the server
             error: function(jqXHR, textStatus, errorThrown){
                  console.log("Something really bad happened " + textStatus);
                   $("#ajaxResponse").html(jqXHR.responseText);
             }
  
         });     
		}
		
	});//end number of share blur
 
 
	$("#date_of_allocation").change(function (e){
		//alert();
	});

});//end dom

function membre_edit(){
	var ad_member_id=$("#ad_member_id").val();
	//alert(ad_member_id);
	window.location.replace("AdMemberRegistration?action=edit&ad_member_id="+ad_member_id);
}	
</script>
<script type="text/javascript">
$(function(){
	var V_Trx_No = $("#V_Trx_No").val();
	
	if(V_Trx_No.trim()!=""){
		$('.custom-messageBox').modal('show');
		$('#custom-page-message').html("Transaction number is "+V_Trx_No);
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
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>