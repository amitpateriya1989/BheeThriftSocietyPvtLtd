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
<link rel="stylesheet" href="assets/plugins/data-tables/DT_bootstrap.css"/>
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
	   <div class="portlet-body">
	  
	    <form id="frmDivident" action="AdDividendMaster?action=generate_dividend" method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
				<%

 		
 		String to = null;
 		try {
 			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
 			Date date = (Date)session.getAttribute("openday");
 			to=sdf.format(date);
 			}catch(Exception e){
 				e.printStackTrace();
 			}%>
					<td>Date : <span class="red">*</span></td>
					<td><input class="form-control input-medium date-picker" type="text" name="date" id="date" value="<%=to %>"  /><label class="error"></label></td>
					<td>Year : <span class="red">*</span></td>
					<td>
					<select  name="div_year" id="div_year"  class="input-medium">
					<option value="">-- Select Year --</option>
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
					</tr>
					
				<tr>
					<td>Roi : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="dividend_per" id="dividend_per" readonly="readonly" /><label class="error"></label></td>
					<td>Conveyance Amount :</td>
					<td><input class="form-control input-medium" type="text" name="convence_amt" id="convence_amt" readonly="readonly" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Member : <span class="red">*</span></td>
					<td>
					<select id="ad_member_id" name="ad_member_id" class="input-large" >
					    <option value="0">-- All Member --</option>
            		<%
				 		ArrayList<MemberRegistrationBean> memberlist = new MemberRegistrationDao().getAllMemberForDividend();
				 		if (memberlist != null) {
				 		for (MemberRegistrationBean bean : memberlist) {
				 	%>
				 		<option value="<%=bean.getAd_member_id()%>" ><%=bean.getAd_society_no()+" | "+bean.getName()%></option>	
				      <%} } %>
					 </select>
					<label class="error"></label></td>
				
					
					<td colspan="2" align="center">
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
	
	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		View Dividend Detail
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th>Sno.</th>
					<th>Mem. No</th>
					<th>Name</th>
					<th>Year</th>
					<th>ROI</th>
					<th>Share Qty</th>
					<th>Share Amt</th>
					<th>Div. Amt</th>
					<th>Conv. Amt</th>
					<th>Total Amt</th>
					<!-- <th>Status</th> -->
					<th>Action</th>
					
				</tr>
				</thead>
				<tbody>
				<%
					int i=0;
					ArrayList<DividentBean> list2=new DividentDao().getAllDivident();
				    if(list2.isEmpty()!=true){
				    	MemberRegistrationBean member=null;
				    	for(DividentBean bean:list2){
				    		member=new MemberRegistrationDao().getMemberName(bean.getAd_member_id());
				    	%>	
				    	
				 <tr>
				   <td><%=++i %></td>
				   <td><%=member.getAd_society_no() %></td>
				   <td><%=member.getName() %></td>
				   <td><%=bean.getDiv_year() %></td>
				   <td><%=bean.getRoi() %></td>
				   <td><%=bean.getShare_qty() %></td>
				   <td><%=bean.getTotalshare_amt() %></td>
				   <td><%=bean.getTotal_intamt() %></td>
				   <td><%=bean.getConv_amt()%></td>
				   <td><%=bean.getTotal_amt()%> </td>
				  <%--  <td><%=bean.getPay_status()%> </td> --%>
				   <td>
				  <%--  <a class="btn btn-xs green" href="edit_dividend.jsp?ad_dividend_id=<%=bean.getAd_divident_id()%>">
				  <i class="fa fa-edit"></i> edit</a> --%>
				  <a class="btn btn-xs red" href="AdDividendMaster?action=delete_dividend&ad_dividend_id=<%=bean.getAd_divident_id()%>">
				  <i class="fa fa-edit"></i> delete</a>
				   </td>
				 </tr>
				<%
				}
				    }
				
				
				%>
				</tbody>
			</table>
			<div class="clearfix"></div>
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
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>

<script>
jQuery(function() {   
	$('#div_year').select2();
	$('#ad_member_id').select2();
	jQuery('#dataTable1').DataTable();
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
					if(data.success==true){
					 $('#dividend_per').val(data.OBJ.ad_dividend_per);
					$('#convence_amt').val(data.OBJ.ad_convence_amt); 
					
					
					}
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