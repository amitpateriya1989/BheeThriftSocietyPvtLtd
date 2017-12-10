
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.ClientBean" %>
<%@page import="Model.Dao.ClientDao"%>
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
			<li><a href="#">Client</a><i class="fa fa-angle-right"></i>Add</li>
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
	   <div class="caption">Add Client</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmClient" autocomplete="off" action="AdClient?action=insert" method="post" enctype="multipart/form-data">
			<table class="table table-bordered">
				<tr>
					<td width="15%">Name : <span class="red">*</span></td>
					<td width="35%">
					<input type="text"  name="name" id="name" class="form-control input-medium"  tabindex="2">
								 
								
						<label id="form_type_error" class="error "></label>
					</td>
					
					
					
				</tr>
				<tr>
					<td>Email_id : <span class="red">*</span></td>
					
						<td><input class="form-control input-medium" type="text" name="email_id" /><label class="error"></label></td>
						
					
					<td>Phone_No : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="phone_no" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Account_No : <span class="red">*</span></td>
					
						<td><input class="form-control input-medium" type="text" name="account_No" /><label class="error"></label></td>
						
						
						<td>Parent_organization: <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="parent_organization"  /><label class="error"></label></td>
	
					
				</tr>
				
				<tr>
				
					<td>registration_no: <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="registration_no" /><label class="error"></label></td>
					
				
				<td>Fax : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="fax" /><label class="error"></label></td>
					</tr>
					
				<tr>
				  <td>Address :</td>
				  <td colspan="3"> <textarea placeholder="Enter Address here" class="form-control" name="address" id="address" style="text-transform: uppercase;" tabindex="13"></textarea></td>
				</tr>
				
				<tr>
				<td colspan="4">
					<table class="table">
					<thead>
						<tr>
						<th>Logo</th>
						<th colspan="2">Note :- Use only JPG or PNG image with 100px * 100px height and width.</th></tr>
					</thead>
					<tbody>
						<tr>
						<td width="25%" colspan="3">
						<div class="userimg-block">
							<div class="user-pic">
							    <img class="uimg" id='photo_view' src="Image/emp.png" alt="Employee Photo" />
							</div>
							<div class="user-input">
							  <span class="text-center">Logo</span>
							<input type="file" name="logo" id="logo" accept="image/*" />
							</div>
							<label class="error"></label>
						   </div><!-- End userimg-block -->
			             </td>
						 
						</tr>
						</tbody>
					</table><!-- End member Image upload -->
				</tr>
				
					<tr>
					<td colspan="4" align="center">
					  <input class="btn btn blue" type="submit" name="Submit" value="submit"/>
					  <input class="btn btn green" type="reset" name="Clear"/>
					</td>
				</tr>
				
				
				
			</table>
		 </form>	
	    </div><!-- End portlet-body -->
	</div>
<!------------------------------------------------------------------- -->			
<!-- Containt Stop -->

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
					<th>Sno.</th>
					<th>Name</th>
					<th>Parent</th>
					<th>Email</th>
					<th>Address</th>
					<th>Phone</th>
					<th>Fax</th>
					<th>Acc. No</th>
					<th>Reg.No.</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
				</thead>
				<tbody>
				<%ClientDao dao=new ClientDao();
					ArrayList<ClientBean> slist=dao.getAllClient();
					int i=0;
					if(slist.isEmpty()!=true){
						for(ClientBean bean:slist){
							
					
				%> 
				 <tr>
				   <td><%=++i %></td>
				   <td><%=bean.getName() %></td>
				    <td><%=bean.getParent_organization() %></td>
				     <td><%=bean.getEmail_id()%></td>
				      <td><%=bean.getAddress() %></td>
				       <td><%=bean.getPhone_no() %></td>
				        <td><%=bean.getFax()%></td>
				         <td><%=bean.getAccount_No() %></td>
				          <td><%=bean.getRegistration_no() %></td>
				         
				           
				   <td><% if(bean.isIsactive()==true){ %>
						<span class="badge badge-primary">Active</span>
						<% }else{ %>
						<span class="badge badge-warning">Inactive</span>
						<%} %>
				   <td>
				   <a class="btn btn-xs green" href="AdClient?action=edit&ad_client_id=<%=bean.getAd_client_id()%>">
				  <i class="fa fa-edit"></i> edit</a>
				   </td>
				 </tr>
				<%	}
					}
					 %>
				</tbody>
			</table>
			<div class="clearfix"></div>
	    </div><!-- End portlet-body -->
	</div>
<!------------------------------------------------------------------- -->
	<!-- END BORDERED TABLE PORTLET-->	
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
<script>
jQuery(function() {       
	
	jQuery.validator.addMethod("alphasapce", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z\s\-]*$/.test(value);
		}, "Please enter character and space only.");
	
	jQuery.validator.addMethod("alphanumsapce", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z0-9\-\s]+$/.test(value);
		}, "Please enter character and space only.");
	
	jQuery.validator.addMethod("numsapce", function(value, element) {
		  return this.optional(element) || /^[0-9\s-]*$/.test(value);
		}, "Please enter character and space only.");
	
	jQuery( "#frmClient" ).validate({
		  rules: {
			  name:{
				  required: true,
				 
				 
			  },
			  address:{
				  required: true,
				 
				  
			  },
			  email_id:{
				  required: true,
				  email: true
			  },
			  phone_no: {
		       required: true,
		       numsapce:true,
		       maxlength:15
		     },
		     account_no:{
		    	required:true,
		    	digit:true,
		    	 maxlength:15
		     },
		     parent_organization:{
			    	required:true,
			    	
			     },
			     registration_no:{
				    	required:true,
				    	
				     } ,
				     fax:{
					    	required:true,
					    	numsapce:true,
					    	 maxlength:15
					     }   ,
					     logo:{
						    	required:true
						    
						     }  
			     
		     
		  }
		});
});
</script>

<script type="text/javascript">
$(document).ready(function(e) {
	
		
	$("#logo").change(function(){
	    readURL_photo(this);
	});
	
	
});
function readURL_photo(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#photo_view').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>

</body>
</html>