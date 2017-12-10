<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Bean.LoanCategoryBean"%>
<%@page import="Model.Dao.LoanCategoryDao"%>
<%@page import="Model.Bean.TypeOfLoanBean"%>
<%@page import="Model.Dao.TypeOfLoanDao"%>
<%@page import="Model.Bean.LoanRoiBean"%>
<%@page import="Model.Dao.LoanRoiDao"%>
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
			<li><a href="#">Account</a><i class="fa fa-angle-right"></i>Loan Roi Edit</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
<div class="col-md-12">
<!-- Containt Start -->



<!------------------------------------------------------------------- -->
	<!-- BEGIN BORDERED TABLE PORTLET-->
	<%
	try{
	String roi_id=request.getParameter("ad_loan_roi_id");
	int ad_loan_roi_id=0;

	if(roi_id!=null){
		
		try{
			ad_loan_roi_id=Integer.parseInt(roi_id);
			LoanRoiDao gdao=new LoanRoiDao();
			LoanRoiBean gbean=gdao.getLoanRoiById(ad_loan_roi_id);
			if(gbean!=null){
				//SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				//String date = gbean.getEffective_form().toString();
				//Date EfDate= df.parse(date);
%>	
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Edit Loan Category</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmLoanRoi"  action="AdLoanRoi?action=update&ad_loan_roi_id=<%=gbean.getAd_loan_roi_id()%>" method="post" >
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
					 <option value="<%=bean.getAd_type_of_loan_id()%>" <% if(bean.getAd_type_of_loan_id()==gbean.getAd_type_of_loan_id()){%> selected="selected" <%}%> ><%=bean.getName() %></option>
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
					 <option value="<%=bean.getAd_loan_category_id()%>" <% if(bean.getAd_loan_category_id()==gbean.getAd_loan_category_id()){%> selected="selected" <%}%> ><%=bean.getName() %></option>
					 <%} 
					 }%>
					</select><label class="error"></label>
					</td>
			</tr>
			<tr>
					<td> Max Limit : <span class="red">*</span></td>
					<td><input class="form-control input-medium"  type="Text" id="max_limit" name="max_limit" value="<%=gbean.getMax_limit()%>"/> <label class="error"></label></td>
					<td>ROI(%) : <span class="red">*</span> </td>
					<td><input class="form-control input-medium" type="Text" id="roi" name="roi" value="<%=gbean.getroi()%>"/> <label class="error"></label></td>
			</tr>
				<tr>
					<td>Share Needed (%) : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" id="share_needed" name="share_needed" value="<%=gbean.getShare_needed()%>"/> <label class="error"></label></td>
					<td> Effective Form Date : <span class="red">*</span></td>
					<td><input class="form-control input-medium date-picker" type="text" id="fdate" name="fdate" value="<%=new SimpleDateFormat("dd/MM/yyyy").format( gbean.getEffective_form())%>" placeholder="DD/MM/YYYY" /> <label class="error"></label></td>
				</tr>
				<tr>
					<td> Min Period : (year) </td>
					<td><input class="form-control input-medium"  type="Text" id="min_period" name="min_period" value="<%=gbean.getMin_period()%>"/> <label class="error"></label></td>
					<td>Max Period : (year) <span class="red">*</span>  </td>
					<td><input class="form-control input-medium" type="Text" id="max_period" name="max_period" value="<%=gbean.getMax_period()%>"/> <label class="error"></label></td>
			    </tr>
			    <tr>
					<td> Min Salary : <span class="red">*</span>  </td>
					<td colspan="3"><input class="form-control input-medium"  type="Text" id="min_salary" name="min_salary" value="<%=gbean.getMin_salary()%>" /> <label class="error"></label></td>
			   </tr>
				<tr>
					<td></td>
					<td colspan="3">
	                 <input class="btn  blue"  type="submit" name="submit" value="update"/>
					  <a class="btn  purple" href="loan_roi.jsp">back</a>
					</td>
					</tr>						
	            </table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div>
	<%	}
		}catch(Exception n){
			n.printStackTrace();
		}
	}
	 %>
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
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script>
jQuery(function() {       
	
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate:new Date('<%=session.getAttribute("openday")%>'),autoclose: true});
	
	jQuery.validator.addMethod("alphanumdotspace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z0-1.\s]*$/.test(value);
	}, "Please enter character, dot(.) and space only.");
	
	jQuery.validator.addMethod("alpha", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z]*$/.test(value);
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
	    	  maxlength:2,
	      	  digits:true
	      },
	      max_period:{
	    	  required: true,
	    	  maxlength:2,
      	      digits:true
	      },
	      min_salary:{
	    	  required: true,
	    	  number:true
	      },
	      status:{
	    	  required: true,
	    	  alpha:true
	    	  
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