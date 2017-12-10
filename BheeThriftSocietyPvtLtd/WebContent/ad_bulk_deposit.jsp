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
	    <form action="#" method="post">
			<table class="table table-bordered">
				<tr>
					<td>Branch <span class="red">*</span></td><td>: </td><td>
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
					<td>Member </td><td>:</td><td>
		 			<select name="ad_member_id" id="ad_member_id" class="form-control input-large" tabindex="0">
		 			<option value="0">--select--</option>
		 			<%MemberRegistrationDao mdao =new MemberRegistrationDao();
								 	ArrayList<MemberRegistrationBean> malist=mdao.getAllApprovedMemberlist();
								 	if(malist!=null){
								 	for(MemberRegistrationBean bean:malist){%>
								 <option value="<%=bean.getAd_member_id()%>"><%=bean.getAd_society_no()+" | "+bean.getName() %></option>
								 <%} 
								 }%>
											 			
		 			</select>		
		 			
					<label class="error"></label>
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
	   <div id="view_member"></div>
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
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@ include file= "Layout/footer.jsp" %>
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>

<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>

<script type="text/javascript">




$(document).ready(function(e){
	$('#ad_branch_id').select2({allowClear: true});
	$('#ad_member_id').select2({allowClear: true});
	
	function loading_show(){
        $('#modelLoad').removeClass('hide').addClass('show');
     }
     function loading_hide(){
       $('#modelLoad').addClass('hide').removeClass('show');
     } 
	
	
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
				   $("#ad_member_id").trigger("chosen:updated");
				
				loading_hide();
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log(textStatus);
			}
			
		 });
		
		
		
		 $.ajax({
			type:"POST",
			url:"getbulkmember.jsp",
			data:dataString,
			success: function(data){
				//alert(data);
				$("#view_member").html(data);
				
				loading_hide();
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log(textStatus);
			}
			
		 });
		}else{
			$("#view_member").html("");
			return false;
		}
	});//end showLadger event
	
	$("#ad_member_id").change(function(e){
		
		
		
		var ad_member_id  =$("#ad_member_id").val();
		
		if(ad_member_id!=0){
			 loading_show();
		var dataString = {"ad_member_id":ad_member_id};
		
		 $.ajax({
			type:"POST",
			url:"getbulkmemberbyid.jsp",
			data:dataString,
			success: function(data){
				//alert(data);
				$("#view_member").html(data);
				loading_hide();
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log(textStatus);
			}
			
		 });
		}else{
			$("#view_member").html("");
			return false;
		}
	});//end showLadger event
});
</script>

<script type="text/javascript">

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
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>