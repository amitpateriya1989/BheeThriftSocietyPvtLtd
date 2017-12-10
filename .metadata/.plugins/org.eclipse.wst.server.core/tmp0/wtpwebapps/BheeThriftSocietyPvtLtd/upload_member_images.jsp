<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%@ include file= "Layout/header.jsp" %>

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
			<li><a href="#">Member</a><i class="fa fa-angle-right"></i>Add</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<!-- END PAGE HEADER-->
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

<form id="frmMemberFileUpload" action="AdMemberRegistration?action=uploadimage" method="post" enctype="multipart/form-data">
  	<table class="table">
					<thead>
						<tr>
						<th>Member Image Upload</th>
						<th colspan="2">Note :- Use only JPG or PNG image with 100px * 100px height and width.</th>
						</tr>
					 <tr>
					<td width="15%">Member : </td>
					<td colspan="3"> <select class="form-control input-large"  name="h_member_id_up" id="h_member_id_up" >
					<option value="">--Select--</option>
					<%
					   ArrayList<MemberRegistrationBean> list=new MemberRegistrationDao().getAllMemberlistName();
					if(list.isEmpty()!=true){
						for(MemberRegistrationBean bean:list){
							%>
							<option value="<%=bean.getAd_member_id()%>"><%=bean.getAd_society_no() %>|<%=bean.getName() %></option>
							
							<%
						}
					}
					
					%>
					</select>
					<label class="error"></label>
					</td>
				</tr> 
					</thead>
					<tbody>
						<tr>
						<td width="25%">
						<div class="userimg-block">
							<div class="user-pic">
							    <img class="uimg" id='photo_view' src="Image/emp.png" alt="Employee Photo" />
							</div>
							<div class="user-input">
							  <span class="text-center">Photo</span>
							<input type="file" name="photo" id="photo" accept="image/*" />
							</div>
							<label class="error"></label>
						   </div><!-- End userimg-block -->
			             </td>
						  <td width="25%">
						  <div class="userimg-block">
						  <div class="user-pic">
							    <img class="uimg" id='id_proof_view' src="Image/id.png" alt="Employee Id Card"  />
							</div>
							<div class="user-input">
							  <span class="text-center">ID Proof</span>
							<input type="file" name="id" id="id_proof" accept="image/*" />
							</div>
							<label class="error"></label>
						   </div><!-- End userimg-block -->
			            </td>
							<td colspan="2" width="25%">
							<div class="userimg-block">
						     <div class="user-pic">
							    <img class="uimg" id='sign_view' src="Image/sign.png" alt="Employee Sign"  />
							 </div>
							 <div class="user-input">
							  <span class="text-center">Sign</span>
							  <input type="file" name="sign" id="sign" accept="image/*" />
							</div>
							<label class="error"></label>
						   </div><!-- End userimg-block -->
							</td>
						</tr>
						</tbody>
					</table><!-- End member Image upload -->
					
			<table class="table">
			<thead><tr><th>Nominee 1 Image Upload</th></tr></thead>
			<tbody>
			<tr>
				<td>
					<div class="userimg-block">
				     <div class="user-pic">
					    <img class="uimg" id='nominee_photo_view_1' src="Image/emp.png" alt="Employee Sign"  />
					 </div>
					 <div class="user-input">
					  <span class="text-center">Sign</span>
					  <input type="file" name="nominee_photo_1" id="nominee_photo_1" accept="image/*" />
					</div>
					<label class="error"></label>
				   </div><!-- End userimg-block -->
				</td>
				<td>
					<div class="userimg-block">
				     <div class="user-pic">
					    <img class="uimg" id='nominee_id_proof_view_1' src="Image/id.png" alt="Employee Sign" />
					 </div>
					 <div class="user-input">
					  <span class="text-center">Sign</span>
					  <input type="file" name="nominee_id_proof_1" id="nominee_id_proof_1" accept="image/*"  />
					</div>
					<label class="error"></label>
				   </div><!-- End userimg-block -->
				</td>
				<td>
					<div class="userimg-block">
				     <div class="user-pic">
					    <img class="uimg" id='nominee_sign_view_1' src="Image/sign.png" alt="Employee Sign" />
					 </div>
					 <div class="user-input">
					  <span class="text-center">Sign</span>
					  <input type="file" name="nominee_sign_1" id="nominee_sign_1" accept="image/*"  />
					</div>
					<label class="error"></label>
				   </div><!-- End userimg-block -->
				</td>
			</tr>
			</tbody>
		</table><!-- End table nominee first image upload -->
		
		<table id="tblSecondNomineeImageModal" class="table">
		<thead><tr><th>Second Nominee Image Upload</th></tr></thead>
			<tr>
				<td>
					<input type="hidden" name="second_nominee" />
					<div class="userimg-block">
				     <div class="user-pic">
					    <img class="uimg" id='nominee_photo_view_2' src="Image/emp.png" alt="Employee Sign" />
					 </div>
					 <div class="user-input">
					  <span class="text-center">Sign</span>
					  <input type="file" name="nominee_photo_2" id="nominee_photo_2"  accept="image/*" />
					</div>
					<label class="error"></label>
				   </div><!-- End userimg-block -->
				</td>
				<td>
					<div class="userimg-block">
				     <div class="user-pic">
					    <img class="uimg" id='nominee_id_proof_view_2' src="Image/id.png" alt="Employee Sign"  />
					 </div>
					 <div class="user-input">
					  <span class="text-center">Sign</span>
					  <input type="file" name="nominee_id_proof_2" id="nominee_id_proof_2"  accept="image/*"/>
					</div>
					<label class="error"></label>
				   </div><!-- End userimg-block -->
				</td>
				<td>
					<div class="userimg-block">
				     <div class="user-pic">
					    <img class="uimg" id='nominee_sign_view_2' src="Image/sign.png" alt="Employee Sign"  />
					 </div>
					 <div class="user-input">
					  <span class="text-center">Sign</span>
					  <input type="file" name="nominee_sign_2" id="nominee_sign_2" accept="image/*" />
					</div>
					<label class="error"></label>
				   </div><!-- End userimg-block -->
				</td>
			</tr>
		<tr>
				 
				 <td colspan="6" align="center">
					<button type="submit" class="btn btn-md blue">Upload</button>
					<button type="reset" name="reset" id="reset" class="btn btn-md red" >Clear All</button>
					<button type="button" class="btn btn-md blue" data-dismiss="modal">Close</button>
					
		</td><!-- modeal-footer -->
		</tr>
		</table><!-- End nominee 2 image upload -->
</form>
</div>
</div>
</div>
</div>
</div>
</div>
</div>




<script src="assets/plugins/select2/select2.min.js"></script>
<script>
jQuery(function() {       
	$('#h_member_id_up').select2();
});
</script>
<script type="text/javascript">
$(document).ready(function(e) {
		
	$("#photo").change(function(){
	    readURL_photo(this);
	});
	$("#id_proof").change(function(){
	    readURL_id_proof(this);
	});
	$("#sign").change(function(){
	    readURL_sign(this);
	});
	
	$("#nominee_photo_1").change(function(){
	    readURL_nominee_photo_1(this);
	});
	$("#nominee_id_proof_1").change(function(){
	    readURL_nominee_id_proof_1(this);
	});
	$("#nominee_sign_1").change(function(){
	    readURL_nominee_sign_1(this);
	});
	
	$("#nominee_photo_2").change(function(){
	    readURL_nominee_photo_2(this);
	});
	$("#nominee_id_proof_2").change(function(){
	    readURL_nominee_id_proof_2(this);
	});
	$("#nominee_sign_2").change(function(){
	    readURL_nominee_sign_2(this);
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
function readURL_id_proof(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#id_proof_view').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
function readURL_sign(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#sign_view').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}

function readURL_nominee_photo_1(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#nominee_photo_view_1').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
function readURL_nominee_id_proof_1(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#nominee_id_proof_view_1').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
function readURL_nominee_sign_1(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#nominee_sign_view_1').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}

function readURL_nominee_photo_2(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#nominee_photo_view_2').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
function readURL_nominee_id_proof_2(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#nominee_id_proof_view_2').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
function readURL_nominee_sign_2(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#nominee_sign_view_2').attr('src', e.target.result);
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