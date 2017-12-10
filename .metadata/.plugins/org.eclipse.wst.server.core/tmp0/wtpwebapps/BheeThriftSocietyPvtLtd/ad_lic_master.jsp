<%@page import="Model.Bean.LicMasterBean"%>
<%@page import="Model.Dao.LicMasterDao"%>
<%@page import="Model.Bean.LoanCategoryBean"%>
<%@page import="Model.Dao.LoanCategoryDao"%>
<%@page import="Model.Bean.TypeOfLoanBean"%>
<%@page import="Model.Dao.TypeOfLoanDao"%>

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
<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-datepicker/css/datepicker.css"/>
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
				<a href="#">Setup</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">LIC </a><i class="fa fa-angle-right"></i>Add and view</li>
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
<div class="caption">Add LIC</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<form id="frmLicMaster" action="AdLicMaster?action=insert" method="post" autocomplete="off" >
<table class="table table-bordered">	
<tr>
   <td>Loan Type  : <span class="red">*</span></td>
   <td>	
       <select class="form-control input-medium" name="ad_type_of_loan_id" id="ad_type_of_loan_id" >
		 <option value="">--Select Account Type --</option>
		 <%TypeOfLoanDao dao=new TypeOfLoanDao();
		 ArrayList <TypeOfLoanBean> alist=dao.getAlltypeofloan();
		 if(alist.isEmpty()!=true){
		 for(TypeOfLoanBean bean:alist){%>
		 <option value="<%=bean.getAd_type_of_loan_id()%>"><%=bean.getName() %></option>
		 <%} }%>
	   </select>
	</td>
	<td > Loan Category : <span class="red">*</span></td>
					<td >	
					<select class="form-control input-medium" name='ad_loan_category_id' id="ad_loan_category_id">
					<option value="">--Select Loan Category--</option>
					<%
					 LoanCategoryDao dao1=new LoanCategoryDao();
					 	ArrayList<LoanCategoryBean> alist1=dao1.getAllLoanCategory();
					 	if(alist1!=null){
					 	for(LoanCategoryBean bean:alist1){%>
					 <option value="<%=bean.getAd_loan_category_id()%>"><%=bean.getName() %></option>
					 <%} 
					 }%>
					</select><label class="error"></label>
					</td>
 </tr>
 <tr>
 <td>LIC Rate : <span class="red">*</span></td>
 <td><input type="text" name="lic_rate" id="lic_rate" class="form-control input-medium"/></td>
 <td>Applied Date : <span class="red">*</span></td>
 <td><input type="text" name="applied_date" id="applied_date" class="form-control input-medium date-picker"/></td>
 </tr>
 <tr>
	<td></td>
	<td>
	  <input class="btn blue" type="submit" name="Submit" value="submit"/>
	   <input class="btn  green" type="reset" name="Clear"/>
	</td>
 </tr>						
 </table>
 </form>	
 </div><!-- End portlet-body -->
</div>
<!-- BEGIN BORDERED TABLE PORTLET-->
<div class="portlet box purple">
<div class="portlet-title">
<div class="caption">View Lic Detail</div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a>
	</div>
	</div>
	<div class="portlet-body">
	<table id="dataTable1" class="table table-striped table-bordered table-hover">
		<thead>
		   <tr>
			<th>Sno</th>
			<th>Loan Type</th>
			<th>Category</th>
			<th>LIC Rate</th>
			<th>Applied Date</th>
			<th>Status</th>
			<th>Action</th>
		  </tr>
		</thead>
		<tbody>
		<%
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				LicMasterDao sdao=new LicMasterDao();
			  	ArrayList<LicMasterBean> slist=sdao.getAllLicMaster();
			   	int i=0;
			   	if(slist.isEmpty()!=true){
			     for(LicMasterBean bean:slist){
			 %> 
		<tr>
			<td><%=++i %></td>
			<td><%=new TypeOfLoanDao().gettypeofloanById(bean.getAd_type_of_loan_id()).getName() %></td>
			<td><%=new LoanCategoryDao().getLoanCategoryById(bean.getAd_loan_category_id()).getName() %></td>
			<td><%=bean.getLic_rate() %></td>
			<td><%=sdf.format(bean.getApplied_date()) %></td>
			<td><% if(bean.isIsactive()==true){ %>
				<span class="badge badge-primary">Active</span>
				<% }else{ %>
				<span class="badge badge-warning">Inactive</span>
				<%} %>
			</td>
			<td>
			  <a class="btn btn-xs green" href="AdLicMaster?action=edit&ad_lic_master_id=<%=bean.getAd_lic_master_id()%>"><i class="fa fa-edit"></i> Edit</a>
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
<!--------------------------------------------------------------------->
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
<script src="assets/scripts/custom/components-pickers.js"></script>
<script>
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate : 'new Date(<%=session.getAttribute("openday")%>)',autoclose: true});
	jQuery.validator.addMethod("AlphaSpace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z&\s]*$/.test(value);
		}, "Please enter latter, special symbol(&) and space only.");
	
	jQuery.validator.addMethod("validDate", function (value, element) {
        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	
	jQuery("#frmLicMaster").validate({
		  rules: {
			  ad_type_of_loan_id:{
				  required:true,
				  digits:true
			  },
			  ad_loan_category_id:{
				  required:true,
				  digits:true
			  },
			  lic_rate: {
		          required: true,
		          number:true
		    },
		    applied_date: {
		    	required: true,
				  validDate:true
		    }
		  }
		});
});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>
