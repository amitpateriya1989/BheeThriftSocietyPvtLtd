<%@page import="com.sun.org.apache.bcel.internal.generic.GETSTATIC"%>
<%@page import="Model.Dao.EmployeeDao"%>
<%@page import="Model.Bean.EmployeeBean"%>
<%@page import="Model.Bean.ReligionBean"%>
<%@page import="Model.Dao.ReligionDao"%>
<%@page import="Model.Bean.MemberStatusBean"%>
<%@page import="Model.Dao.MemberStatusDao"%>
<%@page import="Model.Bean.MemberTypeBean"%>
<%@page import="Model.Dao.MemberTypeDao"%>
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

<link rel="stylesheet" href="css/styles.css"></link>
<link rel="stylesheet" href="css/mainstyle.css"></link>
<link rel="stylesheet" href="00/chosen.css">
<script src="TreeMenu/TreeMenu.js" type="text/javascript"></script>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
  <link rel="stylesheet" href="css/jquery-ui.css">
  <script src="js/jquery-1.10.2.js"></script>
  <script src="js/jquery-ui.js"></script>
 
  
<script type="text/javascript">
$(document).ready(function(e) {
	
	$(function() {
	    $( "#tabs" ).tabs();
	  });
	  
	
	$("#ch1").click(function(e) {    
		if($(this).attr("checked"))
		{	$("#10_sub").css("background","none").removeAttr("readonly");
			$("#10_stream").css("background","none").removeAttr("readonly");
			$("#10_enroll").css("background","none").removeAttr("readonly");
			$("#10_per").css("background","none").removeAttr("readonly");
			$("#10_pass_year").css("background","none").removeAttr("readonly");
			$("#10_board").css("background","none").removeAttr("readonly");
		}
		else
		{
			$("#10_sub").css("background","#CCC").attr("readonly","readonly").val("");
			$("#10_stream").css("background","#CCC").attr("readonly","readonly").val("");
			$("#10_enroll").css("background","#CCC").attr("readonly","readonly").val("");
			$("#10_per").css("background","#CCC").attr("readonly","readonly").val("");
			$("#10_pass_year").css("background","#CCC").attr("readonly","readonly").val("");
			$("#10_board").css("background","#CCC").attr("readonly","readonly").val("");
		}
		
	});

	$("#ch2").click(function(e) {    
		if($(this).attr("checked"))
		{	$("#12_sub").css("background","none").removeAttr("readonly");
			$("#12_stream").css("background","none").removeAttr("readonly");
			$("#12_enroll").css("background","none").removeAttr("readonly");
			$("#12_per").css("background","none").removeAttr("readonly");
			$("#12_pass_year").css("background","none").removeAttr("readonly");
			$("#12_board").css("background","none").removeAttr("readonly");
		}
		else
		{
			$("#12_sub").css("background","#CCC").attr("readonly","readonly").val("");
			$("#12_stream").css("background","#CCC").attr("readonly","readonly").val("");
			$("#12_enroll").css("background","#CCC").attr("readonly","readonly").val("");
			$("#12_per").css("background","#CCC").attr("readonly","readonly").val("");
			$("#12_pass_year").css("background","#CCC").attr("readonly","readonly").val("");
			$("#12_board").css("background","#CCC").attr("readonly","readonly").val("");
		}
		
	});

	$("#ch3").click(function(e) {    
		if($(this).attr("checked"))
		{	$("#g_sub").css("background","none").removeAttr("readonly");
			$("#g_stream").css("background","none").removeAttr("readonly");
			$("#g_enroll").css("background","none").removeAttr("readonly");
			$("#g_per").css("background","none").removeAttr("readonly");
			$("#g_pass_year").css("background","none").removeAttr("readonly");
			$("#g_board").css("background","none").removeAttr("readonly");
		}
		else
		{
			$("#g_sub").css("background","#CCC").attr("readonly","readonly").val("");
			$("#g_stream").css("background","#CCC").attr("readonly","readonly").val("");
			$("#g_enroll").css("background","#CCC").attr("readonly","readonly").val("");
			$("#g_per").css("background","#CCC").attr("readonly","readonly").val("");
			$("#g_pass_year").css("background","#CCC").attr("readonly","readonly").val("");
			$("#g_board").css("background","#CCC").attr("readonly","readonly").val("");
		}
		
	});

	$("#ch4").click(function(e) {    
		if($(this).attr("checked"))
		{	$("#pg_sub").css("background","none").removeAttr("readonly");
			$("#pg_stream").css("background","none").removeAttr("readonly");
			$("#pg_enroll").css("background","none").removeAttr("readonly");
			$("#pg_per").css("background","none").removeAttr("readonly");
			$("#pg_pass_year").css("background","none").removeAttr("readonly");
			$("#pg_board").css("background","none").removeAttr("readonly");
		}
		else
		{
			$("#pg_sub").css("background","#CCC").attr("readonly","readonly").val("");
			$("#pg_stream").css("background","#CCC").attr("readonly","readonly").val("");
			$("#pg_enroll").css("background","#CCC").attr("readonly","readonly").val("");
			$("#pg_per").css("background","#CCC").attr("readonly","readonly").val("");
			$("#pg_pass_year").css("background","#CCC").attr("readonly","readonly").val("");
			$("#pg_board").css("background","#CCC").attr("readonly","readonly").val("");
		}
		
	});
	$("#ch5").click(function(e) {    
		if($(this).attr("checked"))
		{	$("#tr_sub").css("background","none").removeAttr("readonly");
			$("#tr_stream").css("background","none").removeAttr("readonly");
			$("#tr_enroll").css("background","none").removeAttr("readonly");
			$("#tr_per").css("background","none").removeAttr("readonly");
			$("#tr_pass_year").css("background","none").removeAttr("readonly");
			$("#tr_board").css("background","none").removeAttr("readonly");
		}
		else
		{
			$("#tr_sub").css("background","#CCC").attr("readonly","readonly").val("");
			$("#tr_stream").css("background","#CCC").attr("readonly","readonly").val("");
			$("#tr_enroll").css("background","#CCC").attr("readonly","readonly").val("");
			$("#tr_per").css("background","#CCC").attr("readonly","readonly").val("");
			$("#tr_pass_year").css("background","#CCC").attr("readonly","readonly").val("");
			$("#tr_board").css("background","#CCC").attr("readonly","readonly").val("");
		}
		
	});
	
	
	
	
	$("#photo").change(function(){
	    readURL_photo(this);
	});
	$("#id_proof").change(function(){
	    readURL_id_proof(this);
	});
	$("#sign").change(function(){
	    readURL_sign(this);
	});
	
	
	

</script>
<%try{ %>
<head>
		<title>Central Bank of India</title>
</head>
	
<body class="">
	

	
	
		
<!--Main Table Starts Here-->
<table id="tblContainer" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
	<tr>
		<td>
		      <%@include file= "admin_hdr_menu.jsp"%>
      
		</td>  
	</tr>
	<tr><td colspan="2" height="5"></td></tr>
	</tbody>
</table>
		

<table id="tblContainer"  width="100%">
	<tbody><tr>
		<td> 
			<table height="100%" border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody><tr>
					<td valign="top" width="15%">
						<table id="tblSubMenu" align="right" border="0" cellpadding="3" cellspacing="0" width="95%">
							<tbody>												
								<tr>							
									<td colspan="2"> 
                               			
										<%@include file= "tree.jsp"%>					
									
									</td>
								</tr>
							</tbody>
						</table>
					</td>
                <td colspan="2" height="100%" valign="top" width="85%">
                <% int ad_employee_id= Integer.parseInt(request.getParameter("ad_employee_id"));%>
                <% EmployeeBean bean1= new EmployeeDao().getEmployeeById(ad_employee_id);%>

<!-- Body Starts Here -->
<form id='frm' action="AdEmployee?action=edit" method="post" enctype="multipart/form-data" style="border:1px solid red">



<table height="100%"  width="100%" style="border:1px solid black">
	<tbody>
		 
		<tr> 	
			<td colspan="2">
			
				<div id="tabs">
  <ul>
    <li><a href="#tabs-1">Personal</a></li>
    <li><a href="#tabs-2">Contact</a></li>
    <li><a href="#tabs-3">Education</a></li>
    <li><a href="#tabs-4">Service</a></li>
    
    
  </ul>
  <div id="tabs-1">
  
    <table  cellpadding="5" cellspacing="10"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Edit Personal Detail</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr >
					
						
						
						<td>&nbsp;Emp No</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="employee_id" id="employee_id"  style="width: 170px" value="<%=bean1.getAd_employee_id() %>">
								 
						</td>
						<td>&nbsp;Status</td>
						<td class="" >:</td>
						<td>	<select  name="emp_status" id="emp_status"  class="chosen-select" style="width:170px;" tabindex="2" >
									
									
									<option value="Active">Active</option>
									<option value="Halted">Halted</option>
									<option value="Terminate">Terminate</option>
									<option value="Leave">Leave</option>
								 </select>
								 
								 <script type="text/javascript">
								 
								 $("#emp_status").val("<%=bean1.getEmp_status() %>");
								 
								 </script>
						</td>
						
						<td>&nbsp;Type</td>
						<td class="" >:</td>
						<td>	<select  name="emp_category" id="emp_category" class="chosen-select" style="width:170px;" tabindex="2">
									<option value="Regular">Regular</option>
									<option value="Wages">Wages</option>
									<option value="Hourly">Hourly</option>
									<option value="Temprary">Temprary</option>
								 </select>
								 <script type="text/javascript">
								 
								 $("#emp_category").val("<%=bean1.getEmp_category() %>");
								 
								 </script>
								 
						</td>
						
						
						</tr>
						
						
						<tr>
						
						
					
						<td class="" >&nbsp;Name</td>
						<td class="" >:</td>
						<td colspan="4"><select  name="ad_salutation_id" id="ad_salutation_id" style="width: 80px;" data-placeholder="Select" class="chosen-select" style="width:170px;" tabindex="2">
								 <%SalutationDao dao=new SalutationDao();
								 	ArrayList<SalutationBean> alist=dao.getAllSalutation();
								 	
								 	for(SalutationBean bean:alist){%>
								 <option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
								 
								 <% }
								 	%>
								 
								</select>	
								<script type="text/javascript">
									 
									 $("#ad_salutation_id").val("<%=bean1.getSalutation()%>");
									 
									 </script>
								<input type="text"  name="name" id="name" value="<%=bean1.getName() %>"style="width: 375px;">
						</td>
						</tr><tr>
						<td class="" >&nbsp;Father</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="fname" id="fname" value="<%=bean1.getFname() %>" style="width: 170px;">
						</td>
						
						<td class="" >&nbsp;DOB</td>
						<td class="" >:</td>
						<td>	<input type="date"  name="dob" id="dob" value="<%=bean1.getDob() %>"style="width: 170px;">
						</td>
					</tr>
					<tr >
						
			
						<td>&nbsp;Gender</td>
						<td class="" >:</td>
						<td>	<select  name="ad_gender_id" id="ad_gender_id" style="width: 170px;" data-placeholder="Choose a Gender..." class="chosen-select" style="width:170px;" tabindex="2">
								  <%GenderDao dao1=new GenderDao();
								 	ArrayList<GenderBean> alist1=dao1.getAllGender();
								 	for(GenderBean bean:alist1){%>
								 <option value="<%=bean.getAd_gender_id()%>"><%=bean.getGender() %></option>
								 <%} %>
								<script type="text/javascript">
								 
								 $("#ad_gender_id").val("<%=bean1.getGender() %>");
								 
								 </script>
						</select>
						
						</td>
						<td>&nbsp;Category</td>
						<td class="" >:</td>
						<td>	<select  name="ad_category_id" id="ad_category_id" style="width: 170px;" data-placeholder="Choose a Category..." class="chosen-select" style="width:170px;" tabindex="2">
								 <%CategoryDao dao2=new CategoryDao();
								 	ArrayList<CategoryBean> alist2=dao2.getAllCategory();
								 	for(CategoryBean bean:alist2){%>
								 <option value="<%=bean.getAd_category_id()%>"><%=bean.getCategory() %></option>
								 <%} %>
								 <script type="text/javascript">
								 
								 $("#ad_category_id").val("<%=bean1.getAd_employee_grade_id() %>");
								 
								 </script>
						</select>
						
						</td>
						
						<td>&nbsp;Marital Status</td>
						<td class="" >:</td>
						<td>	<select  name="marital_status" id="marital_status"   data-placeholder="Choose a Category..." class="chosen-select" style="width:170px;" tabindex="2">
									<option value="Married">Married</option>
									<option value="UnMarried">UnMarried</option>
									
								 </select>
						<script type="text/javascript">
								 
								 $("#marital_status").val("<%=bean1.getMarital_sts() %>");
								 
								 </script>		 
						</td>
						</tr>
						
						<tr >
						
			
						<td>&nbsp;Religion</td>
						<td class="" >:</td>
						<td>	<select  name="ad_religion_id" id="ad_religion_id" style="width: 170px;" data-placeholder="Choose a Gender..." class="chosen-select" style="width:250px;" tabindex="2">
								  <%ReligionDao reldao=new ReligionDao();
								 	ArrayList<ReligionBean> relalist1=reldao.getAllReligion();
								 	for(ReligionBean bean:relalist1){%>
								 <option value="<%=bean.getAd_religion_id()%>"><%=bean.getReligion() %></option>
								 <%} %>
								 <script type="text/javascript">
								 
								 $("#ad_religion_id").val("<%=bean1.getReligion()%>");
								 
								 </script>
								
						</select>
						
						</td>
						<td>&nbsp;Blood Group</td>
						<td class="" >:</td>
						
						<td>	<select  name="blood_group" id="blood_group"  style="width: 170px;" style="width:250px;">
								 <option value="A+">A+</option>
								 <option value="B+">B+</option>
								 <option value="O+">O+</option>
								 <option value="AB+">AB+</option>
								 <option value="OB+">OB+</option>
								 <option value="A-">A-</option>
								 <option value="B-">B-</option>
								 <option value="AB-">AB-</option>
								 <option value="OB-">OB-</option>
								
						</select>
						 <script type="text/javascript">
								 
								 $("#blood_group").val("<%=bean1.getBlood_group() %>");
								 
								 </script>
						
						
						</td>
						
						
						
						<td>&nbsp;Identity Marks</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="identity_marks" id="identity_marks" value="<%=bean1.getId_mark() %>"  style="width: 170px;">
									
								 
						</td>
						</tr>
						<tr>
						
						<td>&nbsp;Height</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="height" id="height" value="<%=bean1.getHeight() %>"  style="width: 170px;">
									
								 
						</td>
						
						<td>&nbsp;ReMarks</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="remarks" id="remarks"  value="<%=bean1.getRemark() %>" style="width: 170px;">
									
								 
						</td>
						
						
						
						</tr>
						<tr height="100px" >
  
    	
        <th  style="color:#363" align="center" width="150px" valign="top"  colspan="3">
        <table>
        	<tr>
        		<th><img height="100" width="100" id='photo_view' style="background-image:url(Image/emp.png);background-size:cover;border: 1px solid gray;" /> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>Photo</th>
        	</tr>
        	<tr>
        	<th><input type="file" name="photo" id="photo" value="<%=bean1.getPhoto() %>"style="width:100px;" required="required"/></th>
        	</tr>
        </table>
        </th>
       	
        <th  style="color:#363" align="center" width="150px" valign="top"   colspan="3">
         <table>
        	<tr>
        		<th><img height="100" width="100" id="id_proof_view" style="background-image:url(Image/id.png);background-size:cover;border: 1px solid gray;" /> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>ID Proof
        		</th>
        		</tr>
        		<tr>
        		<th><input type="file" name="id" id="id_proof" style="width:100px;" required="required"/></th>
        	</tr>
        </table>
        
        </th>
      
   
    	
        <th  style="color:#363" align="center" width="150px" valign="top" colspan="3">
        
         <table>
        	<tr>
        		<th><img height="100" width="100"  id='sign_view' style="background-image:url(Image/sign.png);background-size:cover;border: 1px solid gray;"/> &nbsp;</th>
        	</tr>
        	<tr>
        		<th>Sign
        		</th>
        		</tr>
        		<tr>
        		<th>
        		<input type="file" name="sign" id="sign" style="width:100px;" required="required" /></th>
        	</tr>
        </table>
        </th>
       
        
        
 </tr>
<tr>					
					<td colspan="9" align="center">
						<!-- <input type="Button" name="next1" id='next1' value="Next" style="width: 70px;height: 25px;"/>&nbsp;&nbsp;
						<input type="reset" name="Clear" style="width: 70px;height: 25px;"/> -->
					</td>
					</tr>						
					
					<tr>
					<td colspan="7" class=""  >&nbsp;</td>
					</tr>
	              
	            </table>	
  </div>
  <div id="tabs-2">
    <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Contact Detail</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					
					<tr>
						<td class="" >&nbsp;Mobile</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="mobile" id="mobile" value="<%=bean1.getMobile() %>" maxlength="10" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Alt Mobile</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="alt_mobile" id="alt_mobile" value="<%=bean1.getAlt_mobile() %>" maxlength="10" style="width: 170px;">
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;Phone</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="phone" id="phone" value="<%=bean1.getPhone() %>" maxlength="10" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Email</td>
						<td class="" >:</td>
						<td>	<input type="email"  name="email" id="email" value="<%=bean1.getEmail() %>" style="width: 170px;">
						</td>
					</tr>
					<tr >
						<td>&nbsp;Current Address</td>
						<td class="" >:</td>
						<td>	<textarea name="c_address" id="c_address" value="<%=bean1.getC_address() %>" style="width: 170px;"></textarea>
						<script type="text/javascript">
								 
								 $("#c_address").val("<%=bean1.getC_address() %>");
								 
								 </script>
					
						<td>&nbsp;Permanent Address</td>
						<td class="" >:</td>
						<td>	<textarea  name="p_address" id="p_address" value="<%=bean1.getP_address() %>" style="width: 170px;"></textarea>
						<script type="text/javascript">
								 
								 $("#p_address").val("<%=bean1.getP_address() %>");
								 
								 </script>		 
						
						</tr>
						<tr>
						<td class="" >&nbsp;Country</td>
						<td class="" >:</td>
						<td>	<select  name="ad_country_id" id="ad_country_id" style="width: 170px;" data-placeholder="Choose a Country..." class="chosen-select" style="width:250px;" tabindex="2">
								 <%
								 CountryDao dao3=new CountryDao();
								 ArrayList<CountryBean> alist3=dao3.getAllCountry();
								 for(CountryBean bean:alist3){%>
									 <option value="<%=bean.getAd_country_id()%>"><%=bean.getCountry() %></option>
									 
									 <%
								 }
								 %>
								 
								</select>
								<script type="text/javascript">
								 
								 $("#country").val("<%=bean1.getCountry() %>");
								 
								 </script>
								
						</td>
						<td>&nbsp;State</td>
						<td class="" >:</td>
						<td>	<select  name="ad_state_id" id="ad_state_id" style="width: 170px;" data-placeholder="Choose a State..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="0">--Select--</option>
								<%StateDao sdao=new StateDao();
								  ArrayList <StateBean> slist=sdao.getAllState();
								  if(alist2!=null){
								  for(StateBean bean:slist){%>
								  <option value="<%=bean.getAd_state_id()%>"><%=bean.getState() %></option>
									  
								 <%} }%>
								
						</select>
						<script type="text/javascript">
								 
								 $("#ad_state_id").val("<%=bean1.getState().getState() %>");
								 
								 </script>
						
						
						</td>
						</tr>
						<tr>
						<td class="" >&nbsp;District</td>
						<td class="" >:</td>
						<td>	<select  name="ad_district_id" id="ad_district_id" style="width: 170px;" data-placeholder="Choose a District..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="0">--Select--</option>
								</select>
								<script type="text/javascript">
								 
								 $("#ad_district_id").val("<%=bean1.getDistrict().getDistrict() %>");
								 
								 </script>
								
						</td>
						<td class="" >&nbsp;City</td>
						<td class="" >:</td>
						<td>	<select  name="ad_city_id" id="ad_city_id" style="width: 170px;" data-placeholder="Choose a City..." class="chosen-select" style="width:250px;" tabindex="2">
								 <option value="0">--Select--</option>
								</select>
								<script type="text/javascript">
								 
								 $("#ad_city_id").val("<%=bean1.getCity() %>");
								 
								 </script>
						</td>
						<td class="" >&nbsp;Pincode</td>
						<td class="" >:</td>
						<td>	<input type="text"  name="pincode" id="pincode" value="<%=bean1.getPin() %>" maxlength="6" style="width: 170px;">
						
						</td>
						</tr>
						
						<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					
	              
	            </table>	
  </div>
  
  <div id="tabs-3">
    

 <table  cellpadding="5"  width="95%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
<tr>
    <th colspan="7" height="10px" align="left" style="color:#960">Educational Detail<hr /></th>
</tr>
 <tr>
			<th width="50px"> Select</th>
			<th width="150px" >Education</th>
			<th width="150px" >Subjects</th><th>Stream</th>
			<th  width="150px" >Board/University</th>
            <th  width="150px"  >Passing Year</th>
          <!--  <th  width="150px" >Enroll No</th>-->
            <th  width="150px" >Percentage</th>
</tr>
<tr>
          	
            
            <th><input type="checkbox" id=ch1   style="cursor:pointer" /></th>
            <th align="left" >&nbsp; 10<sup>TH</sup></th>
            <th><input type="text" name="10_sub" id="10_sub" value="<%=bean1.getSub_10() %>" style="width:160px;margin:5px;background-color:#CCC" readonly /></th>
            <th><input type="text" name="10_stream" id="10_stream" value="<%=bean1.getStream_10() %>"style="width:160px;margin:5px;background-color:#CCC" readonly /></th>
            <th  ><input type="text" name="10_board" id="10_board" value="<%=bean1.getBoard_10() %>" style="width:160px;margin:5px;background-color:#CCC" readonly /></th>
            <th ><input type="text" name="10_pass_year" id="10_pass_year" value="<%=bean1.getPass_year_10() %>" style="width:160px;background-color:#CCC" readonly ></th>
           <!-- <th><input type="text" name="10_enroll" id="10_enroll" style="width:150px;margin:5px;background-color:#CCC" readonly /></th>-->
            <th><input type="text" name="10_per" id="10_per" value="<%=bean1.getPer_10() %>" value="0" style="width:160px;margin:5px;background-color:#CCC" readonly />  </th>
            
</tr>
<tr>
          	<th><input type="checkbox" id=ch2   style="cursor:pointer" /></th>
            <th align="left" >&nbsp;12<sup>TH</sup></th>
            <td><input type="text" name="12_sub" id="12_sub" value="<%=bean1.getSub_12() %>" style="width:160px;margin:5px;background-color:#CCC" readonly /></td>
            <th><input type="text" name="12_stream" id="12_stream" value="<%=bean1.getStream_12() %>" style="width:160px;margin:5px;background-color:#CCC" readonly /></th>
            <th><input type="text" name="12_board"  id="12_board" value="<%=bean1.getBoard_12() %>" style="width:160px;margin:5px;background-color:#CCC" readonly /></th>
            <th><input type="text" name="12_pass_year" id="12_pass_year" value="<%=bean1.getPass_year_12() %>" style="width:160px;background-color:#CCC"  readonly ></th>
           <!-- <th><input type="text" name="12_enroll" id="12_enroll"  style="width:150px;margin:5px;background-color:#CCC" readonly /></th>-->
            <th><input type="text" name="12_per"  id="12_per"  value="<%=bean1.getPer_12() %>" style="width:160px;margin:5px;background-color:#CCC" readonly /> </th>
           
</tr>
<tr>
          	<th><input type="checkbox" id=ch3   style="cursor:pointer" /></th>
            <th align="left" >&nbsp;Graduation<sup></sup></th>
            <td><input type="text" name="g_sub"  id="g_sub" value="<%=bean1.getSub_g() %>" style="width:160px;margin:5px;background-color:#CCC" readonly /></td>
            <th><input type="text" name="g_stream" id="g_stream" value="<%=bean1.getStream_g() %>" style="width:160px;margin:5px;background-color:#CCC" readonly /></th>
            <th><input type="text" name="g_board" id="g_board" value="<%=bean1.getBoard_g() %>" style="width:160px;margin:5px;background-color:#CCC" readonly /></th>
            <th><input type="text" name="g_pass_year" id="g_pass_year" value="<%=bean1.getPass_year_g() %>" style="width:160px;background-color:#CCC" readonly ></th>
           <!-- <th><input type="text" name="g_enroll" id="g_enroll"  style="width:150px;margin:5px;background-color:#CCC" readonly /></th>-->
            <th><input type="text" name="g_per" id="g_per"  value="<%=bean1.getPer_g() %>" style="width:160px;margin:5px;background-color:#CCC" readonly /> </th>
           
</tr>
<tr>
          	<th><input type="checkbox" id=ch4   style="cursor:pointer" /></th>
            <th align="left">&nbsp;PG</th>
            <td><input type="text" name="pg_sub" value="<%=bean1.getSub_pg() %>" id="pg_sub" style="width:160px;margin:5px;background-color:#CCC" /></td>
            <th><input type="text" name="pg_stream" id="pg_stream" value="<%=bean1.getStream_pg() %>" style="width:160px;margin:5px;background-color:#CCC" /></th>
            <th><input type="text" name="pg_board" id="pg_board" value="<%=bean1.getBoard_pg() %>" style="width:160px;margin:5px;background-color:#CCC" /></th>
            <th><input type="text" name="pg_pass_year" id="pg_pass_year" value="<%=bean1.getPass_year_pg() %>" style="width:160px;background-color:#CCC"></th>
           <!-- <th><input type="text" name="pg_enroll" id="pg_enroll" style="width:150px;margin:5px;background-color:#CCC" /></th>-->
            <th><input type="text" name="pg_per" id="pg_per" value="<%=bean1.getPer_pg() %>" style="width:160px;margin:5px;background-color:#CCC" /> </th>
          
</tr>
<tr>
          	<th><input type="checkbox" id=ch5   style="cursor:pointer" /></th>
            <th align="left">&nbsp;Training</th>
            <td><input type="text" name="tr_sub" id="tr_sub" value="<%=bean1.getTr_sub() %>" style="width:160px;margin:5px;background-color:#CCC" /></td>
            <th><input type="text" name="tr_stream" id="tr_stream" value="<%=bean1.getTr_stream() %>" style="width:160px;margin:5px;background-color:#CCC" /></th>
            <th><input type="text" name="tr_board" id="tr_board" value="<%=bean1.getTr_board() %>" style="width:160px;margin:5px;background-color:#CCC" /></th>
            <th><input type="text" name="tr_pass_year" id="tr_pass_year" value="<%=bean1.getTr_pass_year() %>" style="width:160px;background-color:#CCC" ></th>
         <!--   <th><input type="text" name="tr_enroll" id="tr_enroll" style="width:150px;margin:5px;background-color:#CCC" /></th>-->
            <th><input type="text" name="tr_per" id="tr_per"  value="<%=bean1.getTr_per() %>"  style="width:160px;margin:5px;background-color:#CCC" /> </th>
          
</tr>

</table>
 
         
  </div>
  <div id="tabs-4">
 <table  cellpadding="5"  width="90%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Service Detail</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					
					<tr>
							 
						
						
						
						<td class="" >&nbsp;Department</td>
						<td class="" >:</td>
						<td>	<select  name="ad_department_id" id="ad_department_id"  style="width: 170px;" data-placeholder="Choose a Department..." class="chosen-select"  tabindex="2">
									<option value="0">--Select--</option>
									<%
									DepartmentDao deptdao=new DepartmentDao();
									ArrayList<DepartmentBean> deptlist=deptdao.getAllDepartment();
									if(deptlist!=null){
										for(DepartmentBean bean:deptlist){%>
										
											<option value="<%=bean.getAd_department_id()%>"><%=bean.getName() %></option>
										<%}
									}
									%>
									
								</select>
								<script type="text/javascript">
								 
								 $("#ad_department_id").val("<%=bean1.getDepartment() %>");
								 
								 </script>
						</td>
					
						<td class="" >&nbsp;Designation</td>
						<td class="" >:</td>
						<td>		
									<select  name="ad_designation_id" id="ad_designation_id"  style="width: 170px;" data-placeholder="Choose a Designation..." class="chosen-select"  tabindex="2">
									<option value="0">--Select--</option>
									<%
									DesignationDao desigdao=new DesignationDao();
									ArrayList<DesignationBean> desglist=desigdao.getAllDesignation();
									if(desglist!=null){
										for(DesignationBean bean:desglist){%>
											<option value="<%=bean.getAd_designation_id()%>"><%=bean.getDesignation() %></option>
										<%}
									}
									%>
									
								</select>
								<script type="text/javascript">
								 
								 $("#ad_designation-_id").val("<%=bean1.getDesignation() %>");
								 
								 </script>
						</td>
						
						
						</tr>
						<tr>
						<td class="" >&nbsp;Appointment</td>
						<td class="" >:</td>
						<td>	<input type="date" name="doa" id="doa" value="<%=bean1.getDoa() %>" style="width: 170px;">
						</td>
						<td class="" >&nbsp;Joining</td>
						<td class="" >:</td>
						<td>	<input type="date" name="doj" id="doj" value="<%=bean1.getDoj() %>" style="width: 170px;">
						</td>
						
						<td class="" >&nbsp;Week Off</td>
						<td class="" >:</td>
						<td>	<select  name="week_off" id="week_off"  style="width: 170px;" data-placeholder="Choose a Department..." class="chosen-select"  tabindex="2">
								<option value="Sunday">Sunday</option>
								<option value="Monday">Monday</option>
								<option value="Tuesday">Tuesday</option>
								<option value="Wednesday">Wednesday</option>
								<option value="Thursday">Thursday</option>
								<option value="Friday">Friday</option>
								<option value="Saturday">Saturday</option>
								</select>
						</td>
					</tr>
					
					
					<tr>
						<td class="" >&nbsp;Bank</td>
						<td class="" >:</td>
						<td>	<select  name="ad_bank_id" id="ad_bank_id"  style="width: 170px;" data-placeholder="Choose a Designation..." class="chosen-select"  tabindex="2">
									<option value="0">--Select--</option>
									<%
									BankDao bankdao=new BankDao();
									ArrayList<BankBean> banklist=bankdao.getAllBank();
									if(desglist!=null){
										for(BankBean bean:banklist){%>
											<option value="<%=bean.getAd_bank_id()%>"><%=bean.getBank() %></option>
										<%}
									}
									%>
								</select>
						</td>
						
						<td class="" >&nbsp;Branch</td>
						<td class="" >:</td>
						<td>	<select  name="ad_branch_id" id="ad_branch_id"  style="width: 170px;" data-placeholder="Choose a Designation..." class="chosen-select"  tabindex="2">
									<option value="0">--Select--</option>
									
								</select>
						</td>
						
						<td class="" >&nbsp;A/C No</td>
						<td class="" >:</td>
						<td>	<input type="text" name="saving_ac_no" id="saving_ac_no" value="<%=bean1.getAcc_no() %>" style="width: 170px;">
						</td>
						</tr>
					<tr>
					<td class="" >&nbsp;Monthly Pay</td>
						<td class="" >:</td>
						<td>	<input type="text" name="monthly_pay" id="monthly_pay" value="<%=bean1.getMonth_pay() %>" style="width: 170px;">
						</td>
						
					
						<td>&nbsp;PF No</td>
						<td class="" >:</td>
						<td>	<input type="text" name="pf_no" id="pf_no" value="<%=bean1.getPf_acc_no() %>" style="width: 170px;">
							 
						</td>
					</tr>
					
					
					<tr>
					<td colspan="9" align="center">
						<input type="submit" name="Submit" value="Submit"/>&nbsp;&nbsp;
						<input type="reset" name="Clear"/>
					</td>
					</tr>						
					
					<tr>
					<td colspan="7" class=""  >&nbsp;</td>
					</tr>
	              
	            </table>		
  </div>
                
			</td>
		</tr> 
	</tbody></table>
  </form>
 
 			</td>
 		</tr>
 	</tbody>
 </table>

</td>
</tr></tbody>

<!-- footer-->
 

<!-- <table id="tblContainer" border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-top: 30%"> -->
	<tr><tbody>
		<td class="footerText" height="25" valign="middle">©&nbsp;Copyright Syphon Tech</td>
		
		<td class="footerText" height="25" valign="middle"><div align="right"><a href="#" onClick="window.open('/sbijava/Privacy_Statement.html','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')">Syphon</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" onclick="window.open('www.syphon.co.in','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')"> </div></td>
		
	</tbody>	
	</tr> 


	</table>
	
</body>
<script src="00/chosen.jquery.js" type="text/javascript"></script>
<script src="00/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
   function form_submit(){
    
    document.getElementById("frm1").submit();
    }
</script>   
<%}catch(Exception e){
	e.printStackTrace();
} %>