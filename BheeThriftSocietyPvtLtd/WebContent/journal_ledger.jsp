<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Bean.AccountTypeBean"%>
<%@page import="Model.Dao.AccountTypeDao"%>
<%@page import="Model.Dao.AccountGroupDao"%>
<%@page import="Model.Bean.AccountSubGroupBean"%>
<%@page import="Model.Dao.AccountSubGroupDao"%>
<%@page import="Model.Bean.AccountGroupBean"%>
<%@page import="Model.Dao.AccountGroupDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%
try{
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 		String from = null;
 		String to = null;
 		try {
 			Date date = (Date)session.getAttribute("openday");
 			
 			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
 			
 			Calendar cal = Calendar.getInstance();
 		    cal.setTime(date);
 		    int year = cal.get(Calendar.YEAR);
 		    
 			Date dt=sdf.parse("03/31/"+year);
 			
 			
 			if(date.after(dt)){
 			
 				Date fdate = sdf.parse("01/04/"+year);
 				from=sdf.format(fdate).toString();
 				
 				
 			}else{
 				Date tdate = sdf.parse("01/04/"+(year-1));
 				from=sdf.format(tdate).toString();
 				
 			}
 			
 			to= sdf.format(date).toString();
 			
 			
 			
 		} catch (IllegalArgumentException i) {
 			i.printStackTrace();
 		}
 	%>
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
			<li><a href="#">Account</a><i class="fa fa-angle-right"></i>Journal Ledger</li>
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
	   <div class="caption">Journal Ledger</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	   </div><!-- end portlet-title -->
	   <div class="portlet-body">
	  <form id="frmLedger" autocomplete="off" action="" method="post">  
			<table class="table table-bordered">
				<tr>
					<td width="15%">From Date: : <span class="red">*</span></td>
					<td width="35%"> <input class="form-control input-medium date-picker" type="text" placeholder="dd/mm/yyyy" name="from_date" id="from_date" value="<%=from%>" />
					<label id="form_type_error" class="error "></label>
					</td>
					<td width="15%">To Date : <span class="red">*</span></td>
					<td width="35%"><input class="form-control input-medium date-picker" type="text" placeholder="dd/mm/yyyy" name="to_date" id="to_date" value="<%=to%>"/>
					<label class="error"></label>
					</td>
				</tr>
				<%-- <tr>
					<td>Ledger A/c : <span class="red">*</span></td>
					<td>
					<select id="ad_account_id" name="ad_account_id" class="input-large">
				 	<option value="0" >---Select Account---</option>
				 	<%
				 		ArrayList<LedgerAccountBean> ledgerlist = new LedgerAccountDao().getAllLedgerAccount();
				 		if (ledgerlist != null) {
				 		for (LedgerAccountBean bean : ledgerlist) {
				 	 %>
				 		<option value="<%=bean.getAd_account_id()%>" ><%=bean.getAc_no()+" | "+bean.getAc_name()%></option>	
				 	<%
					    }
					 	}
					%>
					 </select>
					<label class="error"></label>
					</td>
					<td>Select Member : <span class="red"></span></td>
					<td>
					<select id="ad_member_id" name="ad_member_id" class="input-large" >
				 	<option value="0" >---Select Member---</option>
				 	<%
				 		ArrayList<MemberRegistrationBean> memberlist = new MemberRegistrationDao().getAllActiveMemberRegistration();
				 		if (memberlist != null) {
				 		for (MemberRegistrationBean bean : memberlist) {
				 	%>
				 		<option value="<%=bean.getAd_member_id()%>" ><%=bean.getAd_society_no()+" | "+bean.getName()%></option>	
				      <%} } %>
					 </select>
					<label class="error"></label></td>
				</tr> --%>
				<tr>
					<td></td>
					<td colspan="3">
					  <input type="button" class="btn btn blue" id="btnShowLadger" name="Submit"  value="submit"/>
					  <input class="btn btn green" type="reset" name="Clear"/>
					</td>
				</tr>
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div><!-- En portlet box -->
	<!-- -------------------------------------------------------------- -->
	<div class="portlet box green">
	<div class="portlet-title">
	<div class="caption">Journal Ledger View</div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	</div><!-- end portlet-title -->
	<div class="portlet-body">
	<table class="table table-bordered">
	<tr>
		<td>
			<div class="btn-group pull-right">
			<button class="btn red btn-md dropdown-toggle" type="button" data-toggle="dropdown">
				<i class="fa fa-bar-chart-o"></i> Print Report <i class="fa fa-angle-down"></i>
			</button>
			<ul class="dropdown-menu" role="menu">
			<li><a href="#" onclick="tableToExcel('ledger', 'Ledger Book')"><img src="Image/excel-icon.png" height="25" width="25" /> Excel</a></li>
			<li><a onclick="prnts()" href="#"><img  src="Image/print-icon.png" height="25" width="25" />PDF</a></li>
			</ul>
			</div>
		</td>
	</tr>
	</table>
	    <div class="row">
		<div class="col-md-12">
			<div id="ledger_window"></div>
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
<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script>
jQuery(function() {  
	
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate : '<%=to%>',autoclose: true});
	
	jQuery.validator.addMethod("alphanumsapce", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter character and space only.");
	
	jQuery.validator.addMethod("validDate", function (value, element) {
        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	jQuery("#frmLedger").validate({
		  rules: {
			  from_date:{
				  required: true,
				  validDate:true
			  },
			  to_date:{
				  required: true,
				  validDate:true
			  }/* ,
			  ad_account_id:{
				  required: true,
				  digits:true
			  },
			  ad_member_id:{
				 
				  digits:true
			  } */
		  }
		});
});
</script>

<script type="text/javascript">
 $(document).ready(function(e){

	 /*	 $("#ad_account_id").select2({placeholder: "Select",allowClear: true});
	 $("#ad_member_id").select2({placeholder: "Select",allowClear: true});
	 $('#ad_member_id').select2('disable');
	 
	$("#ad_account_id").change(function(e){
		
		
		// $('#ad_member_id').val(0);
		
		
		loading_show();
		var ad_account_id =$(this).val();
		$.get('Ajax/chk_acc_accessibiliti.jsp?ad_account_id='+ad_account_id,
				function(data,status){
				if(status=="success")
				{
					
					data=data.trim();
					
					if(data=='Yes')
					{	
						$("#ad_member_id").select2("val", '0');
						$('#ad_member_id').select2('enable');
		 			}
					 else
					{	
						 $("#ad_member_id").select2("val", '0');
						 $('#ad_member_id').select2('disable');
		 			} 
					
					loading_hide();
				}
		 });
	});//end  account id change event
	
	*/
	function loading_show(){
        $('#modelLoad').removeClass('hide').addClass('show');
     }
     function loading_hide(){
       $('#modelLoad').addClass('hide').removeClass('show');
     }  
     
	$('#btnShowLadger').click(function(){
		
		
		if(jQuery("#frmLedger").valid()==true){
			
			loading_show();
			
		var from          = $('#from_date').val();
		var to            = $('#to_date').val();
		var ad_account_id = $('#ad_account_id').val();
		var ad_member_id  = $('#ad_member_id').val();
		
		//alert(ad_member_id);
		//alert(ad_account_id);
		var dataString = {'from':from,'to':to,'ad_account_id':ad_account_id,'ad_member_id':ad_member_id};
		
		$.ajax({
			type:"POST",
			url:"Ajax/journal_ledger.jsp",
			data:dataString,
			success: function(data){
				//alert(data);
				$('#ledger_window').html(data);
				//alert();
				loading_hide();
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log(textStatus);
			}
			
		});
		}else{
			return false;
		}//end validation
	});//end showLadger event
	
	
});//end dom

function prnts()
{

var divElements = document.getElementById('ledger_window').innerHTML;
	 var printWindow = window.open("", "_blank", "");            
        printWindow.document.open();          
        printWindow.document.write('<html><body><center> <h3 style="margin-bottom:-0px "> Central Bank Employees Co-Operative Credit Society Ltd. Bhopal </h3><h4 style=" margin-top: 0px";margin-bottom:-0px>AR/BPL/57</h4></center>');
      	printWindow.document.write(divElements);
        printWindow.document.write('</body></html>');
        printWindow.document.close();
        printWindow.focus();
		 setTimeout(function() {
            printWindow.print();
            printWindow.close();
        }, 100);
}
</script>
<script type="text/javascript">
var tableToExcel = (function() {
	
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><center> <h3 style="margin-bottom:0px;margin-top:0px">Central Bank Employees Co-Operative Credit Society Ltd. Bhopal </h3><h4 style="margin-bottom:0px;margin-top:0px">AR/BPL/57<br/></h4></center><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  
    return function(table, name) {
    if (!table.nodeType) table = document.getElementById('ledger')
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>