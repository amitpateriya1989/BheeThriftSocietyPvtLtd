<%@page import="Model.Bean.CityBean"%>
<%@page import="Model.Dao.CityDao"%>
<%@page import="Model.Bean.DistrictBean"%>
<%@page import="Model.Dao.DistrictDao"%>
<%@page import="Model.Bean.StateBean"%>
<%@page import="Model.Dao.StateDao"%>
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
			<li><a href="#">City</a><i class="fa fa-angle-right"></i>Add and view</li>
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
	   <div class="caption">Add City</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmCity" action="AdCity?action=insert" method="post" autocomplete="off">
			<table class="table table-bordered">
				<tr>
					<td width="12%">State : <span class="red">*</span></td>
					<td width="38%"><select class="input-medium"  name="ad_state_id" id="ad_state_id" data-placeholder="Choose a Bank..." class="chosen-select" style="width:250px;" tabindex="2">
								<option value="">--Select City--</option>
								<%StateDao dao=new StateDao();
								  ArrayList <StateBean> alist=dao.getAllState();
								  if(alist!=null){
								  for(StateBean bean:alist){%>
								  <option value="<%=bean.getAd_state_id()%>"><%=bean.getState() %></option>
									  
								 <%} }%>
						</select><label class="error"></label>
					</td>
					<td width="12%">District : <span class="red">*</span></td>
					<td width="38%"><select class="input-medium" name="ad_district_id" id="ad_district_id" data-placeholder="Choose a Bank..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="">--Select District--</option>
								</select><label class="error"></label></td>
				</tr>
				<tr>
				<td>City : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text" name="city"/><label class="error"></label></td>
				<td></td>
				<td></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn-xs blue" type="submit" name="Submit" value="submit"/>
					  <input class="btn btn-xs green" type="reset" name="Clear"/>
					</td>
				</tr>
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div>
	
	<!-- BEGIN BORDERED TABLE PORTLET-->
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">
		View City
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
			<!--  <table id="dataTable1" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th>Sno.</th>
					<th>City</th>
					<th>District</th>
					<th>State</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
				</thead>
				<tbody>
				<%
					//CityDao sdao=new CityDao();
					//ArrayList<CityBean> slist=sdao.getAllCity();
					ArrayList<CityBean> slist=null;
					int i=0;
					if(slist!=null){
						for(CityBean bean:slist){

				%> 
				 <tr>
				   <td><%=++i %></td>
				   <td><%=bean.getCity() %></td>
				   <td><%=new StateDao().getStateById(bean.getAd_state_id()).getState() %></td>
				   <td><%=new DistrictDao().getDistrictById(bean.getAd_district_id()).getDistrict() %></td>
				     <td><% if(bean.isIsactive()==true){ %>
						<span class="badge badge-primary">Active</span>
						<% }else{ %>
						<span class="badge badge-warning">Inactive</span>
						<%} %>
					</td>
				    <td><a class="btn btn-xs green" href="edit_city.jsp?ad_city_id=<%=bean.getAd_city_id()%>">
				  <i class="fa fa-edit"></i> edit</a>
				  </td>
				 </tr>
			<%}} %>
			
				</tbody>
			</table>
			-->
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
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<script>
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	jQuery('#ad_state_id').select2();
	jQuery('#ad_district_id').select2();
	
	jQuery.validator.addMethod("Alphanumspace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter character and space only.");
	
	jQuery( "#frmCity" ).validate({
		  rules: {
			  ad_state_id: {
				  required: true,
		      digits:true
		    },
		    ad_district_id: {
		    	required: true,
			      digits:true
			 },
			 city: {
				 required: true,
				      Alphanumspace:true,
				      maxlength:100
			}
		  }
		});
});
</script>
<script type="text/javascript">
$(document).ready(function(e) {
	
$("#ad_state_id").change(function(e) {
	
		if($(this).val()==0)
		{
			alert("Please Select State......!!");
			 //$('#subgroup_name').html(data); 
			return false;
		}
		else
		{
        		var ad_state_id=$(this).val();
        		//alert(ad_state_id);
		$.ajax({
			   type: "POST",
			   url: "Ajax/read_district_by_state_id.jsp?ad_state_id="+ad_state_id,
			   async:false,
			   success: function(data)
			   {	
				//   alert(data);
				   $('#ad_district_id').html(data);  
				   $('#ad_district_id').trigger("chosen:updated");
		  	} }); 
		}
		
	});

	
   
});	

function valid(){
	
	 if($("#ad_state_id").val()=="0"){
		alert("please select bank state");
		return false;
	}else if($("#ad_district_id").val()=="0"){
		alert("please select bank District");
		return false;
	}
	
	
}
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>