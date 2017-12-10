<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Dao.LoanRoiDao"%>
<%@page import="Model.Bean.LoanRoiBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%@page import="Model.Bean.TypeOfLoanBean"%>
<%@page import="Model.Dao.TypeOfLoanDao"%>
<%@page import="Model.Bean.LoanCategoryBean"%>
<%@page import="Model.Dao.LoanCategoryDao"%>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2-bootstrap.css"/>
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
			<li><a href="#">Loan ROI</a><i class="fa fa-angle-right"></i>Add and view</li>
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
	   <div class="caption">Add Loan ROI</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	
       <form id="frmLoanRoi"  action="AdLoanRoi?action=insert" method="post" >
	    <table class="table table-bordered">										
		<tr >
					<td width="18%"> Loan Type : <span class="red">*</span></td>
					<td width="32%">
					<select class="form-control input-medium" name='ad_type_of_loan_id' id="ad_type_of_loan_id">
					<option value="">--Select Loan Type--</option>
					<%
					 TypeOfLoanDao dao=new TypeOfLoanDao();
					 ArrayList<TypeOfLoanBean> alist=dao.getAlltypeofloan();
					 if(alist!=null){
					 for(TypeOfLoanBean bean:alist){%>
					 <option value="<%=bean.getAd_type_of_loan_id()%>"><%=bean.getName() %></option>
					 <%} 
					 }%>	
					</select><label class="error"></label>
					</td>
					<td width="18%"> Loan Category : <span class="red">*</span></td>
					<td width="32%">	
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
					<td> Max Limit : <span class="red">*</span></td>
					<td><input class="form-control input-medium"  type="Text" id="max_limit" name="max_limit"/> <label class="error"></label></td>
					<td>ROI(%) : <span class="red">*</span> </td>
					<td><input class="form-control input-medium" type="Text" id="roi" name="roi" /> <label class="error"></label></td>
			</tr>
				<tr>
					<td>Share Needed (%) : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="share_needed" name="share_needed" /> <label class="error"></label></td>
					<td> Effective Form Date : <span class="red">*</span></td>
					<td><input class="form-control input-medium date-picker" type="text" id="fdate" name="fdate" placeholder="dd/mm/yyyy" /> <label class="error"></label></td>
				</tr>
				<tr>
					<td> Min Period : (year) </td>
					<td><input class="form-control input-medium"  type="Text" id="min_period" name="min_period"/> <label class="error"></label></td>
					<td>Max Period : (year) <span class="red">*</span>  </td>
					<td><input class="form-control input-medium" type="Text" id="max_period" name="max_period" /> <label class="error"></label></td>
			    </tr>
			    <tr>
					<td> Min Salary (%): <span class="red">*</span>  </td>
					<td><input class="form-control input-medium"  type="Text" id="min_salary" name="min_salary"/> <label class="error"></label></td>
			   </tr>
				<tr>
					<td></td>
					<td colspan="3">
	                 <input class="btn btn-md blue"  type="submit" name="Submit" value="submit"/>
					  <input class="btn btn-md green" type="reset" name="Clear"/>
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
		View Bank
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
<table id="dataTable1" class="table table-striped table-bordered table-hover">
<thead>
	<tr>
	<th>Loan<br/>Category</th>
	<th>Loan Type</th>
	<th>Max Limit</th>
	<th>Share<br/>Needed</th>
	<th>Roi</th>
	<th>Effective From</th>
	<th>Effective To</th>
	<th>Min<br/>Period</th>
	<th>Max<br/>Period</th>
	<th>Min<br/>Salary(%)</th>
	<th>Status</th>
	<th>Action</th>
	</tr>
</thead>
<tbody>
<%
ArrayList<LoanRoiBean> lrlist= new LoanRoiDao().getAllLoanRoi();
if(lrlist.isEmpty()!=true){
	for(LoanRoiBean lrb:lrlist){
		%>
		<tr>
		<td><%=lrb.getCetegory()%></td>
		<td><%=lrb.getType()%></td>
		<td><%=lrb.getMax_limit()%></td>
		<td><%=lrb.getShare_needed()%></td>
		<td><%=lrb.getroi()%></td>
		<td><%=sdf.format(lrb.getEffective_form())%></td>
		<td><%=lrb.getEffective_to()%></td>
		<td><%=lrb.getMin_period()%></td>
		<td><%=lrb.getMax_period()%></td>
		<td><%=lrb.getMin_salary()%></td>
		<td><% if(lrb.isIsactive()==true){ %>
				<span class="badge badge-primary">Active</span>
			<% }else{ %>
				<span class="badge badge-warning">Inactive</span>
			<%} %>
		</td>	
		<td>
		<% if(lrb.isIsactive()==true){ %>
		<a class="btn btn-xs green" href="AdLoanRoi?action=edit&ad_loan_roi_id=<%=lrb.getAd_loan_roi_id()%>">
				  <i class="fa fa-edit"></i> edit</a></td>
		<%} %>
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
<script type="text/javascript">
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate :new Date('<%=(Date)session.getAttribute("openday")%>'),autoclose: true});

	
	jQuery.validator.addMethod("alphanumdotspace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z0-1.\s]*$/.test(value);
		}, "Please enter character, dot(.) and space only.");	
	
	jQuery.validator.addMethod("validDate", function (value, element) {
		return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	jQuery( "#frmLoanRoi" ).validate({
		  rules: {
			  ad_type_of_loan_id: {
		       required: true,
		       digits:true
		      },
		      ad_loan_category_id:{
		    	  required: true,
			       digits:true 
		      },
		      max_limit:{
		    	  required: true,
		    	  number:true
		      },
		      roi:{
		    	  required: true,
		    	  number:true
		      },
		      share_needed:{
		    	  required: true,
		    	  number:true
		      },
		      fdate:{
		    	  required: true,
		    	  validDate:true
		      },
		      min_period:{
		    	  min:1,
		      	  digits:true
		      },
		      max_period:{
		    	  required: true,
		    	  max:12,
	      	      digits:true
		      },
		      min_salary:{
		    	  required: true,
		    	  number:true
		      }
		  },
		  message:{
			  min_period:{
				  maxlength:"maximum length of min period should be 2 digits"
			  },
			  max_period:{
				  maxlength:"maximum length of min period should be 2 digits"
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