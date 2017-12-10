<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Bean.SalutationBean"%>
<%@page import="Model.Bean.GenderBean"%>
<%@page import="Model.Dao.SalutationDao"%>
<%@page import="Model.Dao.GenderDao"%>
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
<link rel="stylesheet" href="assets/plugins/data-tables/DT_bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2.css"/>
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
				<a href="#">Pay Interest</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Dividend</a><i class="fa fa-angle-right"></i>Add and View</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
<div class="col-md-12">
<!-- Containt Start -->
<!-- BEGIN BORDERED TABLE PORTLET-->	
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Dividend</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a></div>
	   </div><!-- end portlet-title -->
	   <div class="portlet-body">
	    <form id="frmDividend" action="#" method="post">
			<table class="table table-bordered">
				<tr>
					<td>Year : <span class="red">*</span></td>
					<td>
					<select  name="div_year" id="div_year"  class="input-medium">
					<option value="0">Select Year</option>
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
					<td>Member : <span class="red">*</span></td>
					<td>
					<select id="ad_member_id" name="ad_member_id" class="input-large" >
					    <option value="0">-Select Member-</option>
            		<%
				 		ArrayList<MemberRegistrationBean> memberlist = new MemberRegistrationDao().getAllMemberlist();
				 		if (memberlist.isEmpty()!=true) {
				 		for (MemberRegistrationBean bean : memberlist) {
				 	%>
				 		<option value="<%=bean.getAd_member_id()%>" ><%=bean.getAd_society_no()+" | "+bean.getName()%></option>	
				      <%} } %>
					 </select>
					<label class="error"></label></td>
				</tr>
				
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn blue" type="button" id="btnShowView" name="Submit" value="submit"/>
					  <input class="btn btn green" type="reset" name="Clear"/>
					</td>
				</tr>
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div><!-- En portlet box -->
<!------------------------------------------------------------------- -->

	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		View Dividend
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<div id="view_dividend" style="overflow: scroll;height:450px"></div>
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
<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script type="text/javascript">
jQuery(function() {       
	
	 $("#ad_member_id").select2();
	 $("#div_year").select2();
	jQuery.validator.addMethod("Alphanumdas", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z0-9-]*$/.test(value);
		}, "Please enter latter and dash only.");
	
	jQuery( "#frmDividend" ).validate({
		  rules: {
			  div_year: {
		      required: true,
		      Alphanumdas:true
		    },
		      ad_member_id : {
		      digits: true
		    } 
		  }
		});
	
$('#btnShowView').click(function(){
		
		
	    loading_show();
		var ad_member_id  = $("#ad_member_id option:selected").val();
		var div_year         = $("#div_year option:selected").val();
		
		//if(jQuery( "#frmDividend" ).valid()==true){
		
		var dataString = {"ad_member_id":ad_member_id,"div_year":div_year};
		
		 $.ajax({
			type:"POST",
			url:"Ajax/getDividendByYear.jsp",
			data:dataString,
			success: function(data){
				//alert(data);
				$("#view_dividend").html(data);
				loading_hide();
			},
			error: function(jqXHR, textStatus, errorThrown){
				console.log(textStatus);
			}
			
		 });
		
	});//end showLadger event
	
});

function loading_show(){
    $('#modelLoad').removeClass('hide').addClass('show');
 }
 function loading_hide(){
   $('#modelLoad').addClass('hide').removeClass('show');
 } 
</script>

</body>
</html>