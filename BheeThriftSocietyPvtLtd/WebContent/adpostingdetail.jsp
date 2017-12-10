<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Bean.DepartmentBean"%>
<%@page import="Model.Dao.DepartmentDao"%>
<%@page import="Model.Bean.DesignationBean"%>
<%@page import="Model.Dao.DesignationDao"%>
<%@page import="Model.Bean.DesignationLevelBean"%>
<%@page import="Model.Dao.DesignationLevelDao"%>
<%@page import="Model.Bean.DesignationTypeBean"%>
<%@page import="Model.Dao.DesignationTypeDao"%>
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
				<a href="#">Setup</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Member</a><i class="fa fa-angle-right"></i>Posting Details add</li>
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
	<%
	String id=request.getParameter("ad_member_id");
	int ad_member_id=0;
	if(id!=null){
		
		try{
			ad_member_id=Integer.parseInt(id);
		}catch(NumberFormatException n){
			n.printStackTrace();
		}	

%>	
	 <div class="portlet box purple">
	  <div class="portlet-title">
	   <div class="caption">Posting Details</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmPostingDetail" autocomplete="off" action="AdPostingDetail?action=insert&ad_member_id=<%=ad_member_id%>" method="post">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Branch : <span class="red">*</span></td>
					<td width="35%">
					<select class="form-control" name="branch_id" id="ad_branch_id" style="width: 270px;">
						<option value="">--Select Branch--</option>
						<%BankBranchDao bankdao=new BankBranchDao();
						ArrayList <BankBranchBean> banklist=bankdao.getAllBankBranchName();
						if(banklist.isEmpty()!=true){
						for(BankBranchBean bean:banklist){
					    %>
						<option value="<%=bean.getAd_branch_id()%>"><%=bean.getBranch_code()+" "+bean.getBranch().trim() %></option>
						<%} }%>
						</select>
					<label class="error"></label>
					</td>
					<td width="15%">Region : </td>
					<td width="35%"><input type="text" name="branch_code" id="branch_code" class="form-control input-medium" readonly="readonly"></td>
				</tr>
				<tr>
					<td>Code : </td>
					<td><input type="text" name="branch_code" id="branch_code" class="form-control input-medium" readonly="readonly"><label class="error"></label></td>	
					<td>IFSC : </td>
					<td><input type="text" name="ifsc_code" id="ifsc_code" class="form-control input-medium" readonly="readonly"><label class="error"></label></td>					
				</tr>
				<tr>
					<td>State : </td>
					<td><input type="text" name="ad_bank_state_id" id="ad_bank_state_id" class="form-control input-medium" readonly="readonly"><label class="error"></label></td>	
					<td>District : </td>
					<td><input type="text" name="ad_bank_district_id" id="ad_bank_district_id" class="form-control input-medium" readonly="readonly"><label class="error"></label></td>					
				</tr>
				<tr>
					<td>City : </td>
					<td><input type="text" name="ad_bank_city_id" id="ad_bank_city_id" class="form-control input-medium" readonly="readonly"><label class="error"></label></td>	
					<td>Pincode : </td>
					<td><input type="text" name="bank_pincode" id="bank_pincode" class="form-control input-medium" readonly="readonly"><label class="error"></label></td>					
				</tr>
				<tr>
					<td>Email : </td>
					<td><input type="text" name="email_id" id="email_id" class="form-control input-medium" readonly="readonly"><label class="error"></label></td>	
					<td>Phone : </td>
					<td><input type="text" name="phone_no" id="phone_no" class="form-control input-medium" readonly="readonly"><label class="error"></label></td>					
				</tr>
				<tr>
					<td>Person : </td>
					<td><input type="text" name="contact_person" id="contact_person" class="form-control input-medium" readonly="readonly"><label class="error"></label></td>	
					<td>Contact : </td>
					<td><input type="text" name="contact_no" id="contact_no" class="form-control input-medium" readonly="readonly"><label class="error"></label></td>					
				</tr>
				<tr>
					<td>Address : </td>
					<td><input type="text" name="address" id="address" class="form-control input-medium" readonly="readonly"><label class="error"></label></td>	
					<td>Department : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium" name="ad_department_id" id="ad_department_id" >
						<option value="">--Select Department--</option>
						<%
						DepartmentDao deptdao=new DepartmentDao();
						ArrayList<DepartmentBean> deptlist=deptdao.getAllDepartmentName();
						if(deptlist!=null){
						for(DepartmentBean bean:deptlist){%>
							<option value="<%=bean.getAd_department_id()%>"><%=bean.getName() %></option>
						<%}
						}
						%>
						</select>
					<label class="error"></label>
					</td>					
				</tr>
				<tr>
				   <td>Type : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium" name="ad_designation_type_id" id="ad_designation_type_id"  >
						<option value="">--Select Designation Type--</option>
						<%
						DesignationTypeDao typedao=new DesignationTypeDao();
						ArrayList<DesignationTypeBean> typelist=typedao.getAllDesignationType();
						if(typelist!=null){
						for(DesignationTypeBean bean:typelist){%>
							<option value="<%=bean.getAd_designation_type_id()%>"><%=bean.getDesignation_type() %></option>
						<%}
						}
						%>
					</select>
					<label class="error"></label>
					</td>
					
					<td>Designation Level : <span class="red">*</span></td>
					<td>
				    <select class="form-control input-medium" name="ad_designation_level_id" id="ad_designation_level_id"  >
					 <option value="">--Select Designation Level--</option>
				   <%
				   DesignationLevelDao desiglvldao=new DesignationLevelDao();
				   ArrayList<DesignationLevelBean> desglvllist=desiglvldao.getAllDesignationLevel();
				   if(desglvllist!=null){
				   for(DesignationLevelBean bean:desglvllist){%>
						<option value="<%=bean.getAd_designation_level_id()%>"><%=bean.getDesignation_level() %></option>
					<%}
					}
					%>
					</select>
					<label class="error"></label>
					</td>		
					
					
				</tr>
				<tr>
				<td>Designation : <span class="red">*</span></td>
					<td>
				    <select class="form-control input-medium" name="ad_designation_id" id="ad_designation_id"  >
					 <option value="">--Select Designation--</option>
				   <%
				   DesignationDao desigdao=new DesignationDao();
				   ArrayList<DesignationBean> desglist=desigdao.getAllDesignationName();
				   if(desglist!=null){
				   for(DesignationBean bean:desglist){%>
						<option value="<%=bean.getAd_designation_id()%>"><%=bean.getDesignation() %></option>
					<%}
					}
					%>
					</select>
					<label class="error"></label>
					</td>			
					</tr>
					<tr>		
					<td>FormDate : <span class="red">*</span></td>
					<td><input type="text" name="fdate" id="fdate" class="form-control input-medium date-picker" /><label class="error"></label></td>	
					<td>Todate : <span class="red">*</span></td>
					<td><input type="text" name="tdate" id="tdate" class="form-control input-medium date-picker2" /><label class="error"></label></td>					
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
					  <input class="btn btn-md blue" type="submit" name="Submit" value="submit"/>
					  <a class="btn btn-md purple" href="ad_posting_detail.jsp">Back</a>
					</td>
				</tr>
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div>
	<%} %>
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
<script src="assets/plugins/select2/select2.min.js"></script>
<script>
jQuery(function() {    
	
	$('#ad_branch_id').select2();
	$('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true});
	$('.date-picker2').datepicker({format: 'dd/mm/yyyy',autoclose: true});
	
	jQuery.validator.addMethod("alphanumsapcedot", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z.\s]*$/.test(value);
		}, "Please enter character, dot(.) and space only.");
	
	jQuery.validator.addMethod("alphanumsapce", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s]*$/.test(value);
		}, "Please enter character and space only.");
	
	jQuery.validator.addMethod("validDate", function (value, element) {
        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	jQuery( "#frmPostingDetail" ).validate({
		  rules: {
			  branch_id: {
			        required: true,
			        digits: true,
			    },
			    branch_id: {
				    required: true,
				    digits: true,
				},
				ad_department_id:{
					required: true,
				    digits: true,
				},
				ad_designation_type_id:{
					required: true,
				    digits: true,
				},
				ad_designation_id:{
					required: true,
				    digits: true,
				},
				fdate:{
					required: true,
					validDate:true
				},
				tdate:{
					required: true,
					validDate:true
				}
		  }
		});
});
</script>
<script type="text/javascript">

$(document).ready(function(e){
	$("#ad_branch_id").change(function(e) {
		var id = $(this).val();
		var dataString = "ad_branch_id="+id;
		 $.ajax({
	         type: "POST",
	         url: "Ajax/getBranchById.jsp",
	         data: dataString,
	         dataType: "json",
	        
	         //if received a response from the server
	         success: function( data, textStatus, jqXHR) {
	             //our country code was correct so we have some information to display
	             
	              if(data.success){
	            	  //alert(data.BranchInfo.bank.bank);	            	            	         	  
	            	  $("#ad_bank_region_id").val(data.BranchInfo.bank_region.region);
	            	  $("#ad_bank_state_id").val(data.BranchInfo.state.state);
	            	  $("#ad_bank_district_id").val(data.BranchInfo.district.district);
	            	  $('#ad_bank_city_id').val(data.BranchInfo.city.city); 
	            	  $("#branch_code").val(data.BranchInfo.branch_code);
	            	  $("#ifsc_code").val(data.BranchInfo.ifsc_code);
	            	  $("#address").val(data.BranchInfo.address);
	            	  $("#bank_pincode").val(data.BranchInfo.pincode);
	            	  $("#contact_no").val(data.BranchInfo.contact_no);
	            	  $("#contact_person").val(data.BranchInfo.contact_person);
	            	  $("#email_id").val(data.BranchInfo.email_id);
	            	  $("#phone_no").val(data.BranchInfo.phone_no);  
	              } 
	              //display error message
	              else {
	                  //$("#ajaxResponse").html("<div><b>Branch id in Invalid!</b></div>");
	              }
	         },
	        
	         //If there was no resonse from the server
	         error: function(jqXHR, textStatus, errorThrown){
	              console.log("Something really bad happened " + textStatus);
	               //$("#ajaxResponse").html(jqXHR.responseText);
	         }

	     });   
    });  
	
	
	$('#ad_designation_type_id').change(function(){
		  var dataString = "desigType="+$(this).val();
			 $.ajax({
		         type: "POST",
		         url: "Ajax/getDesignation.jsp",
		         data: dataString,      
		         success: function(data) {
		             $("#ad_designation_id").html(data);
		         },
		         error: function(jqXHR, textStatus, errorThrown){
		               //$("#ajaxResponse").html(jqXHR.responseText);
		         }

		     });
	  });//end designation change type id
	
	
});

</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>    