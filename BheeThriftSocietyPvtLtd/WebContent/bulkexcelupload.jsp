<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Dao.BulkExcelFileDao"%>
<%@page import="Model.Bean.BulkExcelFileBean"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="Model.Bean.LoanTrxBean"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>

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
<!-- <script src="js/jquery-1.8.3.js" type="text/javascript"></script> -->
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
			<li><a href="#">bulk Excel </a><i class="fa fa-angle-right"></i>Upload</li>
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
	   <div class="caption">Select Excel File</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    
			<table class="table table-striped table-bordered table-hover">
	<tbody>
	
	
	<tr valign="top"> 
		 <td  > 
			Select Excel File</td><td>:</td><td><input type="file" name="file" id="file"></td>
				
				
 		</tr>
 		
 		<tr>
 		
 		<td colspan="6" align="center"><input type="submit" class="btn btn green"  value="submit"></td>
 		</tr>
 		
 		
	
		

<!-- END CONTENT -->
</tbody></table></div></div></div></div></div></div></div></div></div>
</body>
<!-- BEGIN FOOTER -->
<%-- <%@ include file= "Layout/footer.jsp" %> --%>
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
			<script type="text/javascript">
jQuery(function() {       
	
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
	    ad_branch_id:{
	    	required: true,
	    	number:true
	    }
	    
	  }
	});


$(document).ready(function(e){
	
	$('#selectall').click(function(event) {  //on click 
		 var all_ttl =0;
		        if(this.checked) { // check select status
		            $('.checkbox').each(function() { //loop through each checkbox
		                this.checked = true;  //select all checkboxes with class "checkbox1"   
						var idd=$(this).val();
		                
						all_ttl=all_ttl+parseFloat($("#total_"+idd).val()) ;  
						
		            });
		        }else{
		            $('.checkbox').each(function() { //loop through each checkbox
		                this.checked = false; //deselect all checkboxes with class "checkbox1"                       
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
	
});

function cal(i){
	$("#total_"+i).val(parseFloat($("#thrift_"+i).val())+parseFloat($("#loan_"+i).val())+parseFloat($("#excess_"+i).val()));
	 var all_ttl =0;
	$('.checkbox').each(function() { //loop through each checkbox
        if(this.checked){  
				var idd=$(this).val();
          
				all_ttl=all_ttl+parseFloat($("#total_"+idd).val()) ;  
        }
      });
		 $("#total_amt").val(all_ttl);
	
	
}


function valided(){
	
	if($("#total_amt").val()<=0){
		alert("plese select mamber and thrift/loan amt...");
		return false;
		
	}
	if($("#enter_amt").val()<=0){
		alert("plese enter  amt...");
		return false;
		
	}if(parseFloat($("#enter_amt").val())<parseFloat($("#total_amt").val())){
		alert("Recived Amt invalid ...");
		return false;
		
	}
	
	if(parseFloat($("#enter_amt").val())>parseFloat($("#total_amt").val())){
		
		if(parseInt($("#ad_account_id").val())==0){
			alert("Please Select Acc Head...");
			return false;
			
		}
		
	}
	
	if($("#trx_mode").val()==0){
		alert("Please Select Trx Mode...");
		return false;
	}
	
	if($("#chq_no").val()==0){
		alert("Please Enter  No...");
		return false;
	}
	if($("#chk_date").val()==0){
		alert("Please Enter  Date...");
		return false;
	}
	if($("#ad_branch_id").val()==0){
		alert("Please Enter  branch code...");
		return false;
	}
	
	
}

</script>

<%}catch(Exception e){
	e.printStackTrace();
} %>

</html>