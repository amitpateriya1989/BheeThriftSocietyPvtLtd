<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<%@page import="Model.Dao.DividentDao"%>
<%@page import="Model.Bean.DividentBean"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Bean.DividendMasterBean"%>
<%@page import="Model.Dao.DividendMasterDao"%>

<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-datepicker/css/datepicker.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
<%@ include file= "Layout/header.jsp" %>
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
			<li><a href="#">Pay Dividend</a><i class="fa fa-angle-right"></i>Add Dividend Interest</li>
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
	   <div class="caption">Generate Dividend</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   
	   <%

 		
 		String to = null;
	   SimpleDateFormat sdf=null;
	   String id=request.getParameter("ad_dividend_id");
	   int ad_dividend_id=Integer.parseInt(id);
	   DividentBean bean=new DividentDao().getAllDividentByDividendId(ad_dividend_id);
 		try {
 			sdf=new SimpleDateFormat("dd/MM/yyyy");
 			Date date = (Date)session.getAttribute("openday");
 			to=sdf.format(date);
 			
 			
 			}catch(Exception e){
 				e.printStackTrace();
 			}%>
					
					
	   <div class="portlet-body">
	  
	    <form id="frmDivident" action="AdDividendMaster?action=updateDividend&ad_dividend_id=<%=bean.getAd_divident_id() %>" method="post" autocomplete="off">
			<table class="table table-bordered">
				
				
					
					<tr>
					<td>Mem.No. : <span class="red">*</span></td>
					<td><input class="form-control input-medium " type="text" name="ad_society_id" id="ad_society_id" value="<%=new MemberRegistrationDao().getMemberName(bean.getAd_member_id()).getAd_society_no() %>" readonly="readonly" /><label class="error"></label></td>
					<td>Name : <span class="red">*</span></td>
					<td><input class="form-control input-medium " type="text" name="name" id="name" value="<%=new MemberRegistrationDao().getMemberName(bean.getAd_member_id()).getName() %>" readonly="readonly" /><label class="error"></label></td>
					<td>Date : <span class="red">*</span></td>
					<td><input class="form-control input-medium date-picker" type="text" name="date" id="date" value="<%=to %>"  /><label class="error"></label></td>
					</tr>
					<tr>
					
					<td>Date : <span class="red">*</span></td>
					<td><input class="form-control input-medium date-picker" type="text" name="fromdate" id="fromdate" value="<%=sdf.format(bean.getFromdate()) %>"  /><label class="error"></label></td>
					<td>Date : <span class="red">*</span></td>
					<td><input class="form-control input-medium date-picker" type="text" name="todate" id="todate" value="<%=sdf.format(bean.getTodate()) %>"  /><label class="error"></label></td>
					<td>Ac.No. : <span class="red">*</span></td>
					<td><input class="form-control input-medium " type="text" name="name" id="name" value="<%=new MemberRegistrationDao().getMemberName(bean.getAd_member_id()).getSaving_ac_no() %>" readonly="readonly" /><label class="error"></label></td>
					</tr>
					<tr>
					
					<td>Year : <span class="red">*</span></td>
					<td>
					<select  name="div_year" id="div_year"  class="input-medium">
					<option value="<%=bean.getDiv_year()%>"><%=bean.getDiv_year()%></option>
					<option value="2015-16">2015-16</option>
					<option value="2016-17">2016-17</option>
					<option value="2017-18">2017-18</option>
		 			<option value="2018-19">2018-19</option>
					<option value="2019-20">2019-20</option>
					<option value="2020-21">2020-21</option>
					<option value="2021-22">2021-22</option>
					<option value="2022-23">2022-23</option>
					<option value="2023-24">2023-24</option>
					<option value="2024-25">2024-25</option>
					<option value="2025-26">2025-26</option>
					<option value="2026-27">2026-27</option>
					<option value="2027-28">2027-28</option>
					<option value="2028-29">2028-29</option>
					<option value="2029-30">2029-30</option>
					
								 			
		 			</select>
					<label id="" class="error "></label>
					</td>
					<td>Roi : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="dividend_per" id="dividend_per" readonly="readonly" value="<%=bean.getRoi() %>" /><label class="error"></label></td>
					<td>Status : <span class="red">*</span></td>
					<td><select class="form-control input-medium"  name="pay_status" id="pay_status"  >
						<option value="<%=bean.getPay_status() %>"><%=bean.getPay_status() %></option>
						<option value="Pending">Pending</option>
						<option value="Paid">Paid</option>
			 			<option value="Cancel">Cancel</option>
					</select>
					<label class="error"></label></td>
					
				</tr>
				<tr>
					<td>Share Qty : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="share_qty" id="share_qty" readonly="readonly" value="<%=bean.getShare_qty() %>" /><label class="error"></label></td>
					<td>Share Amt :</td>
					<td ><input class="form-control input-medium" type="text" name="share_amt" id="share_amt" readonly="readonly" value="<%=bean.getTotalshare_amt() %>" /><label class="error"></label></td>
				<td colspan="2" align="center">
					  <input class="btn btn red" type="button" name="Attendance" id="Attendance" value="Mark Attendance"/>
					  
					</td>
				</tr>
				<tr>
					
					<td>Div Amt : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="dividend_amt" id="dividend_amt" value="<%=bean.getTotal_intamt() %>" /><label class="error"></label></td>
					
					<td>Conveyance Amount :</td>
					<td ><input class="form-control input-medium" type="text" name="convence_amt" id="convence_amt" value="<%=bean.getConv_amt() %>" /><label class="error"></label></td>
					<td>Total Amount :</td>
					<td ><input class="form-control input-medium" type="text" name="total_amt" id="total_amt" value="<%=bean.getTotal_amt() %>" /><label class="error"></label></td>
				</tr>
				<tr>
				<td colspan="6">Payment Detail</td>
				</tr>
				<tr>
					
					<td>Instrument Type : <span class="red">*</span></td>
					<td><select class="form-control input-medium"  name="instrument_type" id="instrument_type"  >
					    <option value="Bank Transfer">Bank Transfer</option>
						<option value="Cheque">Cheque</option>
			 			<option value="Cash">Cash</option>
						</select>
					<label class="error"></label></td>
					
					<td>Instrument No :</td>
					<td ><input class="form-control input-medium" type="text" name="instrument_no" id="instrument_no"  /><label class="error"></label></td>
					<td>Instrument From :</td>
					<td ><input class="form-control input-medium" type="text" name="instrument_from" id="instrument_from"  /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Discription :</td>
					<td colspan="5" >
					  <textarea rows="5" cols="100" name="desc"><%=bean.getDiscription() %></textarea>
					</td>
				</tr>
				<tr>
					
					<td colspan="6" align="center">
					  <input class="btn btn green" type="submit" name="Submit" value="Submit"/>
					  <input class="btn btn blue" type="reset" name="Reset" value="Reset "/>
					</td>
				</tr>
			</table>
		 </form>	
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
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>

<script>
jQuery(function() {   
	$('#div_year').select2();
	$('#ad_member_id').select2();
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate :' <%=to%>',autoclose: true});
	
	jQuery.validator.addMethod("Alphanumspace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter latter and space only.");
	
	jQuery.validator.addMethod("validDate", function (value, element) {
        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	jQuery( "#frmDividend" ).validate({
		  rules: {
			  date: {
			     	required: true,
			      	validDate: true
		    	},
		    	div_year:{
		    		 required: true
				      
		    	},
		    	dividend_per:{
		    		required:true,
		    		number:true
		    	},
		    	convence_amt:{
		    		number:true
		    	}
		  }
		});
	
	
	jQuery('#Attendance').click(function(){
		
		 var div_amt=parseFloat($('#dividend_amt').val());
		var con_amt=parseFloat($('#convence_amt').val());
		var total=div_amt+con_amt;
	
		$('#total_amt').val(total); 
		
	});
	
	jQuery('#div_year').change(function(){
		loading_show();
		var div_year = $('#div_year').val();
		
		if(div_year!=""){
			var dataString = {"action":"div_year_detail","div_year":div_year};
			$.ajax({
				method:"post",
				url:"AdDividendMaster",
				data:dataString,
				success: function(data){
					//alert(data);
					 $('#dividend_per').val(data.OBJ.ad_dividend_per);
					$('#convence_amt').val(data.OBJ.ad_convence_amt); 
					loading_hide();
				},
				error: function(xhrerror, status, error){
					console.log(status);
				}
			});
			
		}//end check member id
		
	});
	
	function loading_show(){
        $('#modelLoad').removeClass('hide').addClass('show');
     }
     function loading_hide(){
       $('#modelLoad').addClass('hide').removeClass('show');
     } 
	
});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>