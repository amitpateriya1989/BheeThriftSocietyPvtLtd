<%@page import="java.text.SimpleDateFormat"%>
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
				<a href="#">Final Payment</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Final Payment</a><i class="fa fa-angle-right"></i></li>
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
				Date date = (Date)session.getAttribute("openday");
				String to=new SimpleDateFormat("dd/MM/yyyy").format(date);
				
				%>
<!------------------------------------------------------------------- -->

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
	   <div class="caption">Final Payment</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	   </div><!-- end portlet-title -->
	   <div class="portlet-body">
	    <form id="frmFinalPayment" action="#" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="20%">Member : <span class="red">*</span></td>
					<td width="30%">
					<select  name="ad_member_id" id="ad_member_id" class="form-control input-large" tabindex="1">
					<option value="">--Select Member--</option>
		 			<%MemberRegistrationDao mdao =new MemberRegistrationDao();
					ArrayList<MemberRegistrationBean> malist=mdao.getAllApprovedMemberlist();
					if(malist!=null){
					for(MemberRegistrationBean bean:malist){%>
					<option value="<%=bean.getAd_member_id()%>"> <%=bean.getAd_society_no() +" | "+bean.getName() %></option>
					<%} 
					}%>				 			
		 			</select>
					<label id="form_type_error" class="error "></label>
					</td>
					<td>Final Pay Date : <span class="red">*</span></td>
					<td><input class="form-control input-medium date-picker" type="text" placeholder="dd/MM/yyyy" value="<%=to%>" name="final_pay_date" tabindex="4" id="final_pay_date"/>
					<label class="error"></label>
					</td>
					</tr>
					<tr>
					<td width="20%">Reason Of Final Payment : <span class="red">*</span></td>
					<td width="30%">
					<select class="form-control input-medium" name="reason" id="reason" tabindex="2">
					<option value="">--Select Reason--</option>
					    <option value="Retirement">Retirement</option>
            			<option value="Death">Death</option>
                        <option value="Transfer">Transfer</option>
                        <option value="Resignation">Resignation</option>
                        <option value="Termination">Termination</option>
                        <option value="Withdrawal">Withdrawal</option>
                      </select>
					<label class="error"></label>
					</td>
				
					<td>Reason Date: : <span class="red">*</span></td>
					<td> <input class="form-control input-medium date-picker" type="text" placeholder="dd/MM/yyyy" value="<%=to%>" tabindex="3"  name="reson_date" id="reson_date"  />
					<label id="form_type_error" class="error "></label>
					</td>
					
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn blue" type="button" id="btnShowView" name="Submit" value="submit" tabindex="5"/>
					  <input class="btn btn green" type="reset" name="Clear" tabindex="6"/>
					</td>
				</tr>
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div><!-- En portlet box -->
	<!-- -------------------------------------------------------------- -->
	<div class="portlet box green">
	<div class="portlet-title">
	<div class="caption">Final Payment Detail</div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	</div><!-- end portlet-title -->
	<div class="portlet-body">
	    <div class="row">
		<div class="col-md-12">
		<div id="bulk_frame"></div>
		</div><!-- End column -->
		</div><!-- End row -->
	</div><!-- End portlet-body -->
	</div><!-- En portlet box -->

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
<script type="text/javascript" src="assets/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<script>
jQuery(function() {  
	
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate : '<%=to%>',autoclose: true});
	jQuery('#ad_member_id').select2();
	jQuery('#reason').select2();
	
	jQuery.validator.addMethod("alphabet", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z]*$/.test(value);
	}, "Please enter character only.");
	
	jQuery.validator.addMethod("validDate", function (value, element) {
        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	jQuery( "#frmFinalPayment" ).validate({
		  rules: {
			  ad_member_id:{
				  required: true,
				  digits:true
			  },
			  reason:{
				  required: true,
				  alphabet:true
			  },
			  reson_date:{
				  required: true,
				  validDate:true
			  },
			  final_pay_date:{
				  required: true,
				  validDate:true
			  }
		  }
		});
	
	$('#btnShowView').click(function(){
		
		if(jQuery( "#frmFinalPayment" ).valid()){
		
		var ad_member_id  = $("#ad_member_id option:selected").val();
		var reson         = $("#reason option:selected").val();
		var reson_date    = $("#reson_date").val();
		var final_pay_date= $("#final_pay_date").val();
		
		var dataString = {"ad_member_id":ad_member_id,"reson":reson,"reson_date":reson_date,"final_pay_date":final_pay_date};
		
		 $.ajax({
			type:"POST",
			url:"getmember_for_finalpay.jsp",
			data:dataString,
			success: function(data){
				//alert(data);
				$("#bulk_frame").html(data);
				//CheckNetPayable();
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log(textStatus);
			}
			
		 });
		
		}else{
			return false;
		}//end validation
		
		
	});//end showLadger event
	
	
	/* function CheckNetPayable(){
		  var netPyyableId = parseInt($("#net_payable").val());
		  if(netPyyableId<0){
			   
			  $("#submit").attr('disabled','disabled');
		  }
	  }
	 */
	
	
});//end dom
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