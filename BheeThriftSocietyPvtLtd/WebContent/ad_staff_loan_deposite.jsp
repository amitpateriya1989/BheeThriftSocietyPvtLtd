<%@page import="Model.Bean.EmployeeBean"%>
<%@page import="Model.Dao.EmployeeDao"%>
<%@page import="Model.Bean.LoanTrxDetailBean"%>
<%@page import="Model.Dao.LoanTrxDetailDao"%>
<%@page import="Model.Bean.LoanPurposeBean"%>
<%@page import="Model.Dao.LoanPurposeDao"%>
<%@page import="Model.Bean.MemberRegistrationMasterBean"%>
<%@page import="Model.Dao.MemberRegistrationMasterDao"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Bean.RelationBean"%>
<%@page import="Model.Dao.RelationDao"%>
<%@page import="Model.Dao.CityDao"%>
<%@page import="Model.Bean.DesignationTypeBean"%>
<%@page import="Model.Dao.DesignationTypeDao"%>
<%@page import="Model.Bean.DesignationLevelBean"%>
<%@page import="Model.Dao.DesignationLevelDao"%>
<%@page import="Model.Bean.DesignationBean"%>
<%@page import="Model.Dao.DesignationDao"%>
<%@page import="Model.Bean.DepartmentBean"%>
<%@page import="Model.Dao.DepartmentDao"%>
<%@page import="Model.Bean.BankRegionBean"%>
<%@page import="Model.Dao.BankRegionDao"%>
<%@page import="Model.Bean.CategoryBean"%>
<%@page import="Model.Dao.CategoryDao"%>
<%@page import="Model.Bean.CountryBean"%>
<%@page import="Model.Dao.CountryDao"%>
<%@page import="Model.Bean.CastBean"%>
<%@page import="Model.Dao.CastDao"%>
<%@page import="Model.Bean.GenderBean"%>
<%@page import="Model.Dao.GenderDao"%>
<%@page import="Model.Bean.SalutationBean"%>
<%@page import="Model.Dao.SalutationDao"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Dao.BankDao"%>
<%@page import="Model.Bean.BankBean"%>
<%@page import="Model.Bean.StateBean"%>
<%@page import="Model.Dao.StateDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%@page import="Model.Bean.TypeOfLoanBean"%>
<%@page import="Model.Dao.TypeOfLoanDao"%>
<%@page import="Model.Bean.LoanCategoryBean"%>
<%@page import="Model.Dao.LoanCategoryDao"%>
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
			<li><i class="fa fa-home"></i><a href="#">Setup</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Loan</a><i class="fa fa-angle-right"></i></li>
			<li><a href="#">Staff Loan Deposit / Close</a><i class="fa fa-angle-right"></i>Add</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB -->
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
<div class="portlet box purple" id="form_wizard_1">
<div class="portlet-title">
<div class="caption"><i class="fa fa-reorder"></i> Staff Loan Deposit / Close -
<span class="step-title">Step 1 of 4</span>
</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<form id="frmLoanStaffDeposit" autocomplete="off" action="AdStaffLoanTrx?action=deposit" method="post">
<input type="hidden" name="il_ad_employee_id" id="il_ad_employee_id" value='0' />
<!-- -----------------------------------------Start Form----------------------------------------------- -->	
<div class="form-wizard">
<div class="form-body">
<ul class="nav nav-pills steps">
	<li class="active"><a href="#tab1" data-toggle="tab" class="step">Personal</a></li>
	<li class=""><a href="#tab2" data-toggle="tab" class="step">Contact</a></li>
	<li class=""><a href="#tab3" data-toggle="tab" class="step">Loan Details</a></li>
	<li class=""><a href="#tab4" data-toggle="tab" class="step">Deposit/Close Loan</a></li>
</ul>
<div id="bar" class="progress progress-striped" role="progressbar">
	<div class="progress-bar progress-bar-success" style="width: 0%;"></div>
</div>
<div class="tab-content">
	   <div class="alert alert-danger display-none">
		   <button class="close" data-dismiss="alert"></button>
			 You have some form errors. Please check below.
	  </div>
	  <div class="alert alert-success display-none">
		    <button class="close" data-dismiss="alert"></button>
			 Your form validation is successful!
	  </div>
<!-- -----------------------------Start Tab Content------------------------------------------------- -->
	<div class="tab-pane fade active in" id="tab1">
		<table class="table table-bordered">
			<tr>
				<td width="15%">Name : <span class="red">*</span></td>
				<td width="35%">
				<select  name="ad_employee_id" id="ad_employee_id" style="width: 280px;">
				<option value="">--Select Employee--</option>
				 <%EmployeeDao dao =new EmployeeDao();
				ArrayList<EmployeeBean> alist=dao.getEmployeeName();
				if(alist!=null){
				for(EmployeeBean bean:alist){%>
				<option value="<%=bean.getAd_employee_id()%>"> <%=bean.getEmployee_id() %> | <%=bean.getName() %></option>
				 <%} 
				 }%>
				</select><label class="error"></label>
				</td>
				<td width="15%">PF No. :</td>
				<td width="35%"><label for="ad_pf_no" id="ad_pf_no"></label></td>
			</tr>
			<tr>
				<td>Emp.No :</td>
				<td><label for="employee_id" id="employee_id" ></label></td>
				<td>Gender :</td>
				<td><label for="ad_gender_id" id="ad_gender_id" ></label></td>
			</tr>
			<tr>
				<td>Emp Category :</td>
				<td><label for="emp_category" id="emp_category" ></label></td>
				<td>Status :</td>
				<td><label for="emp_status" id="emp_status" ></label></td>
			</tr>
			<tr>
				<td>Father :</td>
				<td><label for="father" id="father" ></label></td>
				<td>DOB</td>
				<td><label for="dob" id="dob" ></label></td>
			</tr>
			</table>
			<table class="table table-bordered">
			<tr>
			<td width="25%">
			<div class="userimg-block">
				<div class="user-pic">
				    <img class="uimg" id="photo_view" name="photo_view" src="Image/emp.png" alt="Employee Photo" />
				</div>
				<div class="user-input">
				  <span class="text-center">Photo :</span>
				</div>
			   </div><!-- End userimg-block -->
             </td>
			  <td width="25%">
			  <div class="userimg-block">
			  <div class="user-pic">
				    <img class="uimg" id="id_proof_view" name="id_proof_view" src="Image/id.png" alt="Employee Id Card"  />
				</div>
				<div class="user-input">
				  <span class="text-center">ID Proof :</span>
				</div>
			   </div><!-- End userimg-block -->
            </td>
				<td colspan="2" width="25%">
				<div class="userimg-block">
			     <div class="user-pic">
				    <img class="uimg" id="sign_view" name="sign_view" src="Image/sign.png" alt="Employee Sign"  />
				 </div>
				 <div class="user-input">
				  <span class="text-center">Sign :</span>
				</div>
			   </div><!-- End userimg-block -->
				</td>
			</tr>
		</table>	
	</div><!-- End Tab Personal -->
<!-- ------------------------------------------------------End Personal----------------------------- -->
	<div class="tab-pane fade" id="tab2">
		<table class="table table-bordered">
		<thead>
			<tr><th colspan="4"><div class="text-center">Present Address</div></th></tr>
		</thead>
		<tbody>
			<tr>
				<td width="15%">Mobile :</td>
				<td width="35%"><label for="mobile" id="mobile" ></label></td>
				<td width="15%">Phone :</td>
				<td width="35%"><label for="phone" id="phone" ></label></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><label for="email" id="email" ></label></td>
				<td>Country :</td>
				<td><label for="ad_country_id" id="ad_country_id" ></label></td>
			</tr>
			<tr>
				<td>State :</td>
				<td><label for="ad_state_id" id="ad_state_id" ></label></td>
				<td>District :</td>
				<td><label for="ad_district_id" id="ad_district_id" ></label></td>
			</tr>
			<tr>
				<td>City :</td>
				<td><label for="ad_city_id" id="ad_city_id" ></label></td>
				<td>Pincode :</td>
				<td><label for="pincode" id="pincode" ></label>
				    <input type="hidden"  name="present" id="present" value="present"/>
				</td>
			</tr>
		  </tbody>
		</table>
    </div><!-- End Tab Contact -->
<!-- ------------------------------------------------------End Contact----------------------------- -->
<div class="tab-pane fade" id="tab3">
	<div class="row">
		<div class="col-md-12">
			<div id="show_loan_detail"></div>
		</div><!-- End column -->
	</div><!-- End row -->
</div><!-- End Tab Loan Details -->
<!-- ----------------------------------------------End Loan Details----------------------------- -->
<div class="tab-pane fade" id="tab4">
		<div id="loan_deposit_div"></div>
    </div><!-- End Tab Loan Deposite / Close  
<!-- ------------------------------------------------------End Loan Deposite / Close----------------------------- -->
<!-- -----------------------------End Tab Content -------------------------------------------------- -->
</div><!--End tab-content-->	
</div><!-- End form-body -->
</div><!-- End form-wizard -->
<div class="form-actions fluid">
	 <div class="row">
		 <div class="col-md-12">
			<div class="col-md-offset-3 col-md-9">
			<a href="javascript:;" class="btn default button-previous disabled" style="display: none;">
				<i class="m-icon-swapleft"></i> Back
			</a>
			<a id="btnContinue" href="javascript:;" class="btn blue button-next">
				Continue <i class="m-icon-swapright m-icon-white"></i>
			</a>
			<button  class="btn green button-submit" style="display: none;">Submit <i class="m-icon-swapright m-icon-white"></i></button>
		   </div>
		 </div>
	 </div>
</div><!--End form-actions fluid -->
<!-- -----------------------------------------End  Form ----------------------------------------------- -->
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
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>

<script type="text/javascript">
jQuery(function(){
	jQuery('#ad_employee_id').select2();
});
</script>
<script type="text/javascript">
$(document).ready(function(e) {
	
	$("#ad_employee_id").change(function(e) {
		
		var id = $(this).val();
		$("#il_ad_employee_id").val(id);
		var dataString = "action=view&ad_employee_id="+id;
		 $.ajax({
             type: "POST",
             url: "AdEmployee",
             data: dataString,
             dataType: "json",
            
             //if received a response from the server
             success: function( data, textStatus, jqXHR) {
                 //our country code was correct so we have some information to display
                 
                  if(data.success){
                	 //alert(data.EmployeeInfo.branch.bank_region.region);
                	  $("#employee_id").html(data.EmployeeInfo.employee_id);
                	  $("#father").html(data.EmployeeInfo.fname);
                	  $("#emp_category").html(data.EmployeeInfo.emp_category);
                	  $("#emp_status").html(data.EmployeeInfo.emp_status);
                	  $("#dob").html(data.EmployeeInfo.dob);
              		  $("#ad_gender_id").html(data.EmployeeInfo.gender.gender);           		   
                	  $("#mobile").html(data.EmployeeInfo.mobile);
                	  $("#phone").html(data.EmployeeInfo.phone);
                	  $("#email").html(data.EmployeeInfo.email);
                	  $("#ad_city_id").html(data.EmployeeInfo.city.city);
                	  $("#ad_country_id").html(data.EmployeeInfo.country.country);
                	  $("#ad_state_id").html(data.EmployeeInfo.state.state);
                	  $("#ad_district_id").html(data.EmployeeInfo.district.district); 
                	  $("#pincode").html(data.EmployeeInfo.pin);

                  } 
                  //display error message
                  else {
                      $("#ajaxResponse").html("<div><b>Employee Id in Invalid!</b></div>");
                  }
             },
            
             //If there was no resonse from the server
             error: function(jqXHR, textStatus, errorThrown){
                  console.log("Something really bad happened " + textStatus);
                   $("#ajaxResponse").html(jqXHR.responseText);
             }
  
         });  
		 
		 getLoanDetails(id);
		 StaffLoanDeposite(id);
 
       });//end ad_employeee change
       
 	
 	function getLoanDetails(empId){
 		if($("#ad_employee_id").val()==""){
			alert("Please Select Employee Then Show Loan Detail.....");			
		}else{
			var dataString = "ad_employee_id="+empId;
			 $.ajax({
	             type: "POST",
	             url: "Ajax/staffopenloandetail.jsp",
	             data: dataString,         
	             //if received a response from the server
	             success: function( data, textStatus, jqXHR) {
	 	             	$("#show_loan_detail").html(data);
	             },
	            
	             //If there was no resonse from the server
	             error: function(jqXHR, textStatus, errorThrown){
	                  console.log("Something really bad happened " + textStatus);
	                   $("#ajaxResponse").html(jqXHR.responseText);
	             }
	  
	         });        
		}
 	}//end getLoan Details function
	
	function StaffLoanDeposite(empId){
		if(empId=="0" || empId ==""){
			alert("Please Select Member Then Show Loan Detail.....");			
		}else{
			ad_employee_id=$("#ad_employee_id").val();
			var dataString = "ad_employee_id="+ad_employee_id;
			 $.ajax({
	             type: "POST",
	             url: "Ajax/staffopenloandetailfordeposit.jsp",
	             data: dataString,         
	            
	             //if received a response from the server
	             success: function( data, textStatus, jqXHR) {
	 	             	$("#loan_deposit_div").html(data);
	             },
	            
	             //If there was no resonse from the server
	             error: function(jqXHR, textStatus, errorThrown){
	                  console.log("Something really bad happened " + textStatus);
	                   $("#ajaxResponse").html(jqXHR.responseText);
	             }
	  
	         });        
			
		}
	}//end StaffLoanDeposite
	

});//end dom

</script>
<script type="text/javascript">
$(document).ready(function(e){
					
	var ad_type_of_loan_id= $("#ad_type_of_loan_id").val();
	var ad_loan_category_id= $("#ad_loan_category_id").val();
			
	if(parseInt(ad_type_of_loan_id)!=0 && parseInt(ad_loan_category_id)!=0){
								
	var dataString = "action=getroirate&ad_type_of_loan_id="+ad_type_of_loan_id+"&ad_loan_category_id="+ad_loan_category_id;
	$.ajax({
	type: "POST",
	url: "AdLoanRoi",
	data: dataString,
	dataType: "json",
		//if received a response from the server
		success: function( data, textStatus, jqXHR) {
		//our country code was correct so we have some information to display    
		if(data.success){               	 
			//alert(data.ShareInfo.roi);
			$("#int_rate").val(data.ShareInfo.roi);
			$("#show_max").val(data.ShareInfo.max_limit);		                      
		} 
		//display error message
		else {
		 $("#int_rate").val('');			                	
		 $("#show_max").val("");
		}
	},
					            
	//If there was no resonse from the server
	error: function(jqXHR, textStatus, errorThrown){
		console.log("Something really bad happened " + textStatus);
		$("#ajaxResponse").html(jqXHR.responseText);
	 }
					  
	});					 
	}	
							
	$("#ad_loan_category_id").change(function(e){
	var ad_type_of_loan_id= $("#ad_type_of_loan_id").val();
	var ad_loan_category_id= $("#ad_loan_category_id").val();

	if(parseInt(ad_type_of_loan_id)!=0 && parseInt(ad_loan_category_id)!=0){
									
	var dataString = "action=getroirate&ad_type_of_loan_id="+ad_type_of_loan_id+"&ad_loan_category_id="+ad_loan_category_id;
	
	$.ajax({
		type: "POST",
		url: "AdLoanRoi",
		data: dataString,
		dataType: "json",
		 //if received a response from the server
		success: function( data, textStatus, jqXHR) {
		//our country code was correct so we have some information to display  
		if(data.success){	 
		//alert(data.ShareInfo.roi);
		$("#int_rate").val(data.ShareInfo.roi);
		$("#show_max").val(data.ShareInfo.max_limit);			                      
		} 
			//display error message
			else {
				$("#int_rate").val(''); 	
				$("#show_max").val('');
			}
		},
	    //If there was no resonse from the server
				error: function(jqXHR, textStatus, errorThrown){
					 console.log("Something really bad happened " + textStatus);
					 $("#ajaxResponse").html(jqXHR.responseText);
				}		  
			});					 
		}					
		});
							
		$("#ad_type_of_loan_id").change(function(e){
		var ad_type_of_loan_id= $("#ad_type_of_loan_id").val();
		var ad_loan_category_id= $("#ad_loan_category_id").val();
		
		if(parseInt(ad_type_of_loan_id)!=0 && parseInt(ad_loan_category_id)!=0){
									
		var dataString = "action=getroirate&ad_type_of_loan_id="+ad_type_of_loan_id+"&ad_loan_category_id="+ad_loan_category_id;
		$.ajax({
		type: "POST",
		url: "AdLoanRoi",
		data: dataString,
		dataType: "json",
		//if received a response from the server
		success: function( data, textStatus, jqXHR) {
		//our country code was correct so we have some information to display	                 
		if(data.success){ 
			//alert(data.ShareInfo.roi);
			$("#int_rate").val(data.ShareInfo.roi);
			$("#show_max").val(data.ShareInfo.max_limit);  
		} 
		//display error message
		 else {
			 $("#int_rate").val('');
			  $("#show_max").val('');
			}
		},
		 //If there was no resonse from the server
		 error: function(jqXHR, textStatus, errorThrown){
			console.log("Something really bad happened " + textStatus);
			$("#ajaxResponse").html(jqXHR.responseText);
		  }
						  
		});					 
		}
								
		});
});//end dom
					
</script>
<script type="text/javascript">
					
$(document).ready(function(e){
	   $("#loan_issue_form").hide();
					
		$("#verify").click(function(e){
						
		if($("#il_ad_employee_id").val()==0){
			alert("Please Select Employee....!");
			event.stop();
		}
				
		if($("#req_loan_amt").val()==''){
			alert("Please Enter Loan Amt......!");
			$("#loan_issue_form").hide();
			//event.stop();
		}else{
							
		if(parseFloat($("#req_loan_amt").val())>parseFloat($("#show_max").val())){
			alert("Invalid loan amount ");								
			$("#loan_issue_form").hide();
			vent.stop();
		}else{			
		var dataString = "action=loanvalidation&ad_employee_id="+$("#il_ad_employee_id").val();
		$.ajax({
		type: "POST",
		url: "AdStaffLoanTrx",
		data: dataString,
		dataType: "json",						            
		success: function( data, textStatus, jqXHR) {
						                
		if(data!=0){ 
		  alert("loan already open of This Member....! Close First Then Try ....!!");
		  $("#loan_issue_form").hide();
				event.stop();
				}else{     	 
				   $("#loan_issue_form").show();
				}  
			}, 
			//If there was no resonse from the server
			error: function(jqXHR, textStatus, errorThrown){
				console.log("Something really bad happened " + textStatus);
				$("#ajaxResponse").html(jqXHR.responseText);
			}
			});        	
			//check share
			}
		}			
	}); 
});//end dom
</script>

<script type="text/javascript">
$(document).ready(function(e){
		$("#period_month").change(function(e){					
			var l=parseInt($("#req_loan_amt").val());
			var p=parseInt($("#period_month").val());
			var i=parseFloat($("#int_rate").val());
			if(p!=0){
				var t=emi(l,i,p);
				$("#emi").val(t);
			}else{
				$("#emi").val(0.0);
			}		
		});						
});
</script>
<script type="text/javascript">
	$(document).ready(function(e){
	
	$("#loan_date").change(function(e){
	var loan_date=	$("#loan_date").val();
	var period_month =$("#period_month").val();
								
	if($("#period_month").val()==0){
		alert("please select month ");
		$("#loan_date").val("");
		event.stop();
	}
	if($("#loan_date").val()==''){
		alert("please select loan Date ");
		event.stop();
	}
								
								
	var dataString = "action=ad_date&loan_date="+loan_date+"&period_month="+period_month;
	$.ajax({
		type: "POST",
		url: "AdLoanTrx",
		data: dataString,   
		success: function( data, textStatus, jqXHR) {     
		if(data){           	
			$("#end_date").val(data);
        	 
			}			                 
		},			            
		 //If there was no resonse from the server
		error: function(jqXHR, textStatus, errorThrown){
			console.log("Something really bad happened " + textStatus);
			$("#ajaxResponse").html(jqXHR.responseText);
		}	  
	 });        
		
	});	
});//end dom

function emi(l,i,p)
{
	   	var r=(i/100)/12;
	 	var tempip=Math.pow((1+r), p);
	   	var emi=((l*r)*tempip)/(tempip-1);
	   	return Math.ceil(emi);
}
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>
