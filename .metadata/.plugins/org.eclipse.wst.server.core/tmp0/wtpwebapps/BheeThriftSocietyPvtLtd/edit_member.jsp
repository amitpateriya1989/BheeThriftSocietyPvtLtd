<%@page import="Model.Bean.WitnessBean"%>
<%@page import="Model.Dao.WitnessDao"%>
<%@page import="Model.Bean.NominationBean"%>
<%@page import="Model.Bean.CityBean"%>
<%@page import="Model.Bean.DistrictBean"%>
<%@page import="Model.Dao.DistrictDao"%>
<%@page import="Model.Bean.MemberAddressBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Bean.ShareBean"%>
<%@page import="Model.Dao.ShareDao"%>
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
<%@page import="Model.Bean.SalutationBean"%>
<%@page import="Model.Dao.SalutationDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%@page import="MasterValidation.Validation"%>
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
			<li><a href="#">Member</a><i class="fa fa-angle-right"></i>Edit</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
<div class="col-md-12">

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
<!-- Containt Start -->
<% 
MemberRegistrationBean member = null;
int ad_member_id = 0;
try{
Validation valid = new Validation();
String id = request.getParameter("ad_member_id");
System.out.println(id);
	if(valid.validNotEmpty(id)==true && valid.validDigits(id)==true){
	
	ad_member_id = Integer.parseInt(id);
	member=new MemberRegistrationDao().getMemberRegistrationById(ad_member_id); %>
	<%}//end id check
}catch(Exception Ex){
		Ex.printStackTrace();
} %>
<!------------------------------------------------------------------- -->
<!-- BEGIN BORDERED TABLE PORTLET-->
<div class="portlet box purple" id="form_wizard_1">
<div class="portlet-title">
<div class="caption"><i class="fa fa-reorder"></i> Update Member Information -
<span class="step-title">Step 1 of 5</span>
</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body form">
<form id="frmMemberReg" action="AdMemberRegistration?action=update" enctype="multipart/form-data" method="post" autocomplete="off">
<div class="form-wizard">
<div class="form-body">
<ul class="nav nav-pills steps">
	<li class="active"><a href="#tab1" data-toggle="tab" class="step">Personal</a></li>
	<li class=""><a href="#tab2" data-toggle="tab" class="step">Contact</a></li>
	<li class=""><a href="#tab3" data-toggle="tab" class="step">Service</a></li>
	<li class=""><a href="#tab4" data-toggle="tab" class="step">Nominee</a></li>
	<li class=""><a href="#tab5" data-toggle="tab" class="step">Witness</a></li>
</ul>
<div id="bar" class="progress progress-striped" role="progressbar">
	<div class="progress-bar progress-bar-success" style="width: 16.6%;"></div>
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
				<td width="12%">PF Number : </td>
				<td width="38%"><input class="form-control input-medium" type="text" id="ad_pf_id"  name="ad_pf_id" value="<%=member.getAd_pf_no()%>" /><label class="error"></label></td>
				<td width="12%">Member Number</td>
				<td width="38%"><input class="form-control input-medium" type="text" id="ad_society_id" name="ad_society_id" value="<%=member.getAd_society_no()%>" readonly="readonly" /><label class="error"></label>
				<input type="hidden" name="ad_member_id" value="<%=member.getAd_member_id()%>"/>
				</td>
			</tr>
			<tr>
				<td>Type : <span class="red">*</span>
				</td>
				<td><select class="form-control input-medium" name="ad_member_type_id" id="ad_member_type_id">
				<option value="">--Select Type--</option>
					<%MemberTypeDao type=new MemberTypeDao();
					 ArrayList<MemberTypeBean> membertypelist=type.getAllMemberType();
					  if(membertypelist!=null){ 
						for(MemberTypeBean bean:membertypelist){%>
						<option value="<%=bean.getAd_member_type_id()%>" <%if(member.getMember_type().getAd_member_type_id()==bean.getAd_member_type_id()){%>selected="selected"<%}%> ><%=bean.getMember_type() %></option>
					<%		  
					}
					}
					%>
					</select>
					<label class="error"></label>					
				</td>
				<td>Status : <span class="red">*</span></td>
				<td>
					<select class="form-control input-medium" name="ad_member_status_id" id="ad_member_satus_id" >
					<option value="">--Select Status--</option>
						<%MemberStatusDao status=new MemberStatusDao();
						ArrayList<MemberStatusBean> statuslist=status.getAllMemberStatus();
						if(statuslist!=null){
						for(MemberStatusBean bean:statuslist){%>
						<option value="<%=bean.getAd_member_status_id()%>" <%if(member.getMember_status().getAd_member_status_id()==bean.getAd_member_status_id()){%>selected="selected"<%}%>   ><%=bean.getMember_status() %></option>
						 <%	
						}
						}
						 %>
					</select>
					<label class="error"></label>
				</td>
			</tr>
			<tr>
				<td>Gender : <span class="red">*</span></td>
				<td>
				<select class="form-control input-medium"  name="ad_gender_id" id="ad_gender_id" onChange="getSalutationData(this.value,'ad_salutation_id');" >
				<option value="">--Select Gender--</option>
					<%GenderDao dao1=new GenderDao();
						ArrayList<GenderBean> alist1=dao1.getAllGender();
						for(GenderBean bean:alist1){%>
						<option value="<%=bean.getAd_gender_id()%>"  <%if(member.getGender().getAd_gender_id()==bean.getAd_gender_id()){%>selected="selected"<%}%> ><%=bean.getGender() %></option>
					 <%} %>	
				</select>
				<label class="error"></label>
				</td>
				<td>Category : <span class="red">*</span></td>
				<td>
				<select class="form-control input-medium" name="ad_category_id" id="ad_category_id">
				<option value="">--Select Category--</option>
					<%CategoryDao dao2=new CategoryDao();
					ArrayList<CategoryBean> alist2=dao2.getAllCategory();
					for(CategoryBean bean:alist2){%>
					<option value="<%=bean.getAd_category_id()%>" <%if(member.getCategory().getAd_category_id()==bean.getAd_category_id()){%>selected="selected"<%}%> ><%=bean.getCategory() %></option>
					 <%} %>
				</select>
				<label class="error"></label>
				</td>
			</tr>
			<tr>
				<td>Salutation : <span class="red">*</span></td>
				<td>
				<select class="form-control input-medium" name="ad_salutation_id" id="ad_salutation_id">
				<option value="">--Select Salutation--</option>
					<%SalutationDao dao=new SalutationDao();
					ArrayList<SalutationBean> alist=dao.getAllSalutation();
					for(SalutationBean bean:alist){%>
					<option value="<%=bean.getAd_salutation_id()%>" <%if(member.getSalutation().getAd_salutation_id()==bean.getAd_salutation_id()){%>selected="selected"<%}%> ><%=bean.getName() %></option>
					<%} %>
				</select>
				<label class="error"></label>
				</td>
				<td>Name : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text"  name="name" id="name" value="<%=member.getName()%>" /><label class="error"></label></td>
			</tr>
			<tr>
				<td>Father : <span class="red">*</span></td>
				<td><input class="form-control input-medium" type="text" value="<%=member.getFather()%>" name="father" id="father" /><label class="error"></label></td>
				<td>Husband : <span  class="red husband">*</span></td>
				<td><input class="form-control input-medium" type="text" value="<%=member.getHusband()%>" name="husband" id="husband" /><label class="error"></label></td>
			</tr>
			<tr>
			    <td>Marital Status : <span class="red">*</span></td>
				<td>
				<select class="form-control input-medium" name="marital_status" id="marital_status">
				<option value="Y">Married</option>
				<option value="N%>">Unmarried</option>
				</select>
				<label class="error"></label>
				</td>
				<td>DOB : <span class="red">*</span></td>
				<td colspan="3"><input class="form-control input-medium date-picker2" type="text" name="dob" id="dob"  placeholder="dd/mm/yyyy" value="<%=new SimpleDateFormat("dd/MM/yyyy").format(member.getDob().getTime())%>" />
				<label class="error"></label></td>
			</tr>
		</table>
		<table class="table">
		<thead>
			<tr><td colspan="3">Note :- Use only JPG or PNG image with 100px * 100px height and width.</td></tr>
		</thead>
		<tbody>
			<tr>
			<td width="25%">
			<div class="userimg-block">
				<div class="user-pic">
				    <img class="uimg" id='photo_view' src="<%=request.getContextPath()%>/member_images/<%=member.getPhoto() %>" alt="Employee Photo" />
				</div>
				<div class="user-input">
				  <span class="text-center">Photo</span>
				<input type="file" name="photo" id="photo" value="<%=request.getContextPath()%>/member_images/<%=member.getPhoto() %>" accept="image/*" />
				</div>
				<label class="error"></label>
			   </div><!-- End userimg-block -->
             </td>
			  <td width="25%">
			  <div class="userimg-block">
			  <div class="user-pic">
				    <img class="uimg" id='id_proof_view' src="<%=request.getContextPath()%>/member_images/<%=member.getId_proof() %>" alt="Employee Id Card"  />
				</div>
				<div class="user-input">
				  <span class="text-center">ID Proof</span>
				<input type="file" name="id" id="id_proof" value="<%=request.getContextPath()%>/member_images/<%=member.getId_proof() %>" accept="image/*" />
				</div>
				<label class="error"></label>
			   </div><!-- End userimg-block -->
            </td>
				<td colspan="2" width="25%">
				<div class="userimg-block">
			     <div class="user-pic">
				    <img class="uimg" id='sign_view' src="<%=request.getContextPath()%>/member_images/<%=member.getSignature() %>" alt="Employee Sign"  />
				 </div>
				 <div class="user-input">
				  <span class="text-center">Sign</span>
				  <input type="file" name="sign" id="sign" value="<%=request.getContextPath()%>/member_images/<%=member.getSignature() %>" accept="image/*" />
				</div>
				<label class="error"></label>
			   </div><!-- End userimg-block -->
				</td>
			</tr>
			</tbody>
		</table>	
	</div><!-- End Tab Personal -->
	<!-- ------------------------------------------------------End Personal---------------------------------------------------- -->	
	<div class="tab-pane fade" id="tab2">
		<% 
						
						 String line1="" ;
						 String line2 ="";
						String email ="";
						 String state ="";
						 String country="";
						 String pincode ="";
						int districtid =0;
						String districtname="";
						int cityid=0;
						String cityname ="";
						int contryid =0;
						int stateid =0;
						String mobile ="";
						 String phone ="";
						 int ad_member_address_id1=0;
						if(member.getAddress().size()!=0){
							MemberAddressBean contact1=(MemberAddressBean)member.getAddress().get(0); 
							mobile=contact1.getMobile() ;
							email=contact1.getEmail();
							  line1 =contact1.getLine1();
							  line2 =contact1.getLine2();				
							  state=contact1.getState().getState();
							  contryid=contact1.getCountry().getAd_country_id();
							  country =contact1.getCountry().getCountry();
							  districtid=contact1.getDistrict().getAd_district_id(); 
							  districtname =contact1.getDistrict().getDistrict();
							  cityid=contact1.getCity().getAd_city_id();
							  cityname=contact1.getCity().getCity();
							  pincode =contact1.getPincode();					  
							 stateid=contact1.getState().getAd_state_id();
							  phone =contact1.getPhone();
							  ad_member_address_id1=contact1.getAd_member_address_id();
							
						}
						%>
	<table class="table table-bordered">
			<thead>
			<tr><th colspan="4">Present Address</th></tr>
			</thead>
			<tbody>
				<tr>
					<td>Mobile : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" value="<%=mobile.trim()%>" name="mobile" id="mobile" /><label class="error"></label>
					     <input type="hidden" name="ad_member_address_id1" value="<%=ad_member_address_id1%>">
					</td>
					<td>Phone :</td>
					<td><input class="form-control input-medium" type="text" value="<%=phone.trim()%>" name="phone" id="phone" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Email : </td>
					<td><input class="form-control input-medium" type="email" value="<%=email%>" name="email" id="email" /><label class="error"></label></td>
					<td>Street1 : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" value="<%=line1%>" name="line1" id="line1" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Street2 : </td>
					<td><input class="form-control input-medium" type="text" value="<%=line2%>" name="line2" id="line2" /><label class="error"></label></td>
					<td>Country : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium"  name="ad_country_id" id="ad_country_id" >
								 <%
								 CountryDao dao3=new CountryDao();
								 ArrayList<CountryBean> alist3=dao3.getAllCountryName();
								 if(alist3!=null){
								 for(CountryBean bean5:alist3){%>
									 <option value="<%=bean5.getAd_country_id()%>" <%if(contryid==bean5.getAd_country_id()){ %>selected="selected"<%} %> ><%=bean5.getCountry()%></option>
									 
							    <%
								 }
								 }
								 %>
					</select><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td>State : <span class="red">*</span></td>
					<td>
					<select class="input-medium"  name="ad_state_id" id="ad_state_id" >
								 <option value="">--Select--</option>
								<%StateDao sdao=new StateDao();
								  ArrayList <StateBean> slist=sdao.getAllStateName();
								  if(alist2!=null){
								  for(StateBean bean:slist){%>
								  <option value="<%=bean.getAd_state_id()%>" <%if(stateid==bean.getAd_state_id()){ %>selected="selected"<%} %>><%=bean.getState() %></option>
									  
								 <%} }%>
						</select><label class="error"></label>
					</td>
					<td>District : <span class="red">*</span></td>
					<td>
					<select  class="input-medium" name="ad_district_id" id="ad_district_id">
							<option value="">--Select--</option>
								<%DistrictDao distDao=new DistrictDao();
								  ArrayList <DistrictBean> dlist= distDao.getAllDistrictNameByStateId(stateid);
								  if(alist2!=null){
								  for(DistrictBean bean:dlist){%>
								  <option value="<%=bean.getAd_district_id()%>" <%if(districtid==bean.getAd_district_id()){ %>selected="selected"<%} %>><%=bean.getDistrict()%></option>
									  
								 <%} }%>
					</select><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td>City : <span class="red">*</span></td>
					<td>
					<select class="input-medium"  name="ad_city_id" id="ad_city_id" >
						<option value="">--Select--</option>
						<%CityDao cityDao=new CityDao();
							ArrayList <CityBean> clist1= cityDao.getAllCityNameByDistrictId(districtid);
					    	if(alist2!=null){
							for(CityBean cityBean:clist1){%>
							<option value="<%=cityBean.getAd_city_id()%>" <%if(cityid==cityBean.getAd_city_id()){ %>selected="selected"<%} %>><%=cityBean.getCity()%></option>
						<%} }%>
					</select><label class="error"></label>
					</td>
					<td>Pin Code :</td>
					<td>
					<input class="form-control input-medium" type="text" name="pincode" id="pincode" value="<%=pincode%>" /><label class="error"></label>
					</td>
				</tr>
			</tbody>
		</table>
		<table class="table table-bordered">
			<tr><th width="70%"></th><th width="30%">Same as above : <input type="checkbox" name="same" id="same" style="width: 15px; height: 15px"></th></tr>
		</table>
		<%
			line1="" ;
			line2 ="";
			email ="";
			state ="";
			country="";
		    pincode ="";
			districtid =0;
			districtname="";
			cityid=0;
			cityname ="";
			contryid =0;
			stateid =0;
			mobile ="";
			phone ="";
			int ad_member_address_id2=0;		
			if(member.getAddress().size()>1) {
			MemberAddressBean contact2=(MemberAddressBean)member.getAddress().get(1); 
			mobile=contact2.getMobile() ;
			email=contact2.getEmail();
			line1 =contact2.getLine1();
			line2 =contact2.getLine2();				
			state=contact2.getState().getState();
			contryid=contact2.getCountry().getAd_country_id();
			country =contact2.getCountry().getCountry();
			districtid=contact2.getDistrict().getAd_district_id(); 
			districtname =contact2.getDistrict().getDistrict();
			cityid=contact2.getCity().getAd_city_id();
			cityname=contact2.getCity().getCity();
			pincode =contact2.getPincode();					  
			stateid=contact2.getState().getAd_state_id();
			phone =contact2.getPhone();
			ad_member_address_id2=contact2.getAd_member_address_id();
			}
					
					%>
		<table class="table table-bordered">
			<thead>
			<tr>
			<th colspan="4">Permanent Address</th>
			</tr>
			</thead>
			<tbody>
				<tr>
					<td>Mobile : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="mobile1" id="mobile1" value="<%=mobile.trim()%>" /><label class="error"></label>
						<input type="hidden" name="ad_member_address_id2" value="<%=ad_member_address_id2%>">
					</td>
					<td>Phone :</td>
					<td><input class="form-control input-medium" type="text" name="phone1" id="phone1" value="<%=phone.trim()%>" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Email :</td>
					<td><input class="form-control input-medium" type="email" name="email1" id="email1" value="<%=email%>" /><label class="error"></label></td>
					<td>Street1 : <span class="red">*</span></td>
					<td><input class="form-control input-medium" type="text" name="line1_1" id="line1_1" value="<%=line1%>" /><label class="error"></label></td>
				</tr>
				<tr>
					<td>Street2 : </td>
					<td><input class="form-control input-medium" type="text" name="line2_1" id="line2_1" value="<%=line2%>" /><label class="error"></label></td>
					<td>Country : <span class="red">*</span></td>
					<td>
					<select class="form-control input-medium"  name="ad_country_id_1" id="ad_country_id_1">
								 <%
								 CountryDao countrydao=new CountryDao();
								 ArrayList<CountryBean> countrylist=countrydao.getAllCountryName();
								 for(CountryBean bean:countrylist){%>
									 <option value="<%=bean.getAd_country_id()%>" <%if(contryid==bean.getAd_country_id()){ %>selected="selected"<%} %> ><%=bean.getCountry() %></option>
									 <%
								 }
								 %>
					</select><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td>State : <span class="red">*</span></td>
					<td>
					<select class="input-medium"  name="ad_state_id_1" id="ad_state_id_1">
								 <option value="">--Select--</option>
								<%StateDao statedao=new StateDao();
								  ArrayList <StateBean> statelist=statedao.getAllStateName();
								  if(statelist!=null){
								  for(StateBean bean:statelist){%>
								 <option value="<%=bean.getAd_state_id()%>" <%if(stateid==bean.getAd_state_id()){ %>selected="selected"<%} %>><%=bean.getState() %></option>
								 <%} }%>
					</select><label class="error"></label>
					</td>
					<td>District : <span class="red">*</span></td>
					<td>
					<select  class="input-medium" name="ad_district_id_1" id="ad_district_id_1">
						<option value="">--Select--</option>
							<%DistrictDao distDao2=new DistrictDao();
							ArrayList <DistrictBean> dlist2= distDao2.getAllDistrictNameByStateId(stateid);
							if(alist2!=null){
							for(DistrictBean bean:dlist2){%>
							<option value="<%=bean.getAd_district_id()%>" <%if(districtid==bean.getAd_district_id()){ %>selected="selected"<%} %>><%=bean.getDistrict()%></option>	  
						 <%} }%>
					</select><label class="error"></label>
					</td>
				</tr>
				<tr>
					<td>City : <span class="red">*</span></td>
					<td>
					<select class="input-medium"  name="ad_city_id_1" id="ad_city_id_1">
							<option value="">--Select--</option>
						    <%CityDao cityDao2=new CityDao();
							ArrayList <CityBean> clist2= cityDao2.getAllCityNameByDistrictId(districtid);
					    	if(alist2!=null){
							for(CityBean bean:clist2){%>
							<option value="<%=bean.getAd_city_id()%>" <%if(cityid==bean.getAd_city_id()){ %>selected="selected"<%} %>><%=bean.getCity()%></option>
						<%} }%>
					</select><label class="error"></label>
					</td>
					<td>Pin Code :</td>
					<td>
					<input class="form-control input-medium" type="text" name="pincode1" id="pincode1" value="<%=pincode%>" /><label class="error"></label>
					</td>
				</tr>
			</tbody>
		</table>					
	</div><!-- End Tab Contact -->
	<!-- ------------------------------------------------------End Contact--------------------------------------------------- -->
	<div class="tab-pane fade" id="tab3">

<table  class="table table-bordered">
					
					<thead>
			<tr>
			<th colspan="6">Service Detail</th>
			</tr>
			</thead>
			<tbody>
					<tr >
						<td>Branch : <span class="red">*</span></td>
						
						<td colspan="5">	
						<select  name="ad_branch_id" id="ad_branch_id" class="form-control input-large">
								 <option value="<%=member.getBranch().getAd_branch_id()%>"><%=member.getBranch().getBranch() %></option>
								<%BankBranchDao bankdao=new BankBranchDao();
								  ArrayList <BankBranchBean> banklist=bankdao.getAllBankBranchName();
								  if(banklist.isEmpty()!=true){
								  for(BankBranchBean bean:banklist){
								//  String city=new CityDao().getCityById(bean.getCity().getAd_city_id()).getCity();
								 // String state1=new StateDao().getStateById(bean.getState().getAd_state_id()).getState();
								  %>
								  
								  <option value="<%=bean.getAd_branch_id()%>"><%=bean.getBranch_code()+" | "+bean.getBranch() %></option>
									  
								 <%} }%>
						</select>
						</td>
						
					
						
					
						
						
					</tr>
					<tr >
											
						<td>Region :</td>
						
						<td>	
						      <input type="text"  name="ad_bank_region_id" id="ad_bank_region_id" value="<%=member.getBranch().getBank_region().getRegion() %>" class="form-control input-medium" readonly="readonly" >
						</td>
					
						<td>Code :</td>
						
						<td>	<input type="text" name="branch_code" id="branch_code" value="<%=member.getBranch().getBranch_code() %>" class="form-control input-medium" readonly="readonly">
								 
						</td>
						<td>IFSC :</td>
						
						<td>	<input type="text" name="ifsc_code" id="ifsc_code" value="<%=member.getBranch().getIfsc_code() %>" class="form-control input-medium" readonly="readonly">
							
					</tr>
					<tr >
						<td>State :</td>
						
						<td>	<input type="text"  name="ad_bank_state_id" id="ad_bank_state_id" value="<%=member.getBranch().getState().getState() %>" class="form-control input-medium" readonly="readonly">
					
						</td>
						
					
						
					
						<td>District :</td>
						
						<td>	<input type="text" name="ad_bank_district_id" id="ad_bank_district_id" value="<%=member.getBranch().getDistrict().getDistrict() %>" class="form-control input-medium" readonly="readonly" >
								
						</td>
						
						<td>City :</td>
						
						<td>	<input type="text"  name="ad_bank_city_id" id="ad_bank_city_id" value="<%=member.getBranch().getCity().getCity() %>" readonly="readonly" class="form-control input-medium" >
								
						</td>
						
					</tr>
					
					<tr>
							 
						</td>
						<td>Pincode :</td>
						
						<td>	<input type="text" name="bank_pincode" value="<%=member.getBranch().getPincode() %>" id="bank_pincode" readonly="readonly" class="form-control input-medium">
						</td>
						
					
						<td>Email :</td>
						
						<td>	<input type="text" name="email_id" id="email_id" value="<%=member.getBranch().getEmail_id() %>" readonly="readonly" class="form-control input-medium">
							 
						</td>
					</tr>
					<tr>
						<td>Phone :</td>
						
						<td>	<input type="text" name="phone_no" id="phone_no" value="<%=member.getBranch().getPhone_no() %>" readonly="readonly" class="form-control input-medium">
						</td>
						
					
						<td>Person :</td>
						
						<td>	<input type="text" name="contact_person" id="contact_person" value="<%=member.getBranch().getContact_person() %>" readonly="readonly" class="form-control input-medium">
								 
						</td>
						<td>Contact :</td>
						
						<td>	<input type="text" name="contact_no" id="contact_no" value="<%=member.getBranch().getContact_person() %>" readonly="readonly" class="form-control input-medium">
						</td>
					</tr>
					<tr>
						<td>Address :</td>
						
						<td colspan="5">	<textarea name="address" id="address" cols="120" readonly="readonly"><%=member.getBranch().getAddress() %></textarea>
						</td>
					</tr>
					
					<tr>
							 
						</td>
						<td>Pan No :</td>
						
						<td>	<input type="text" name="pan_no" id="pan_no" value="<%=member.getPan_no().trim() %>" class="form-control input-medium">
						</td>
						
					
						<td>Aadhar :</td>
						
						<td>	<input type="text" name="aadhar_no" id="aadhar_no" value="<%=member.getAadhar_no().trim() %>" class="form-control input-medium">
							 
						</td>
						
						<td>Department : <span class="red">*</span></td>
						
						<td>	<select  name="ad_department_id" id="ad_department_id"  class="form-control input-medium" >
									<option value="<%=member.getDepartment().getAd_department_id()%>"><%=member.getDepartment().getName() %></option>
									<%
									DepartmentDao deptdao=new DepartmentDao();
									ArrayList<DepartmentBean> deptlist=deptdao.getAllDepartmentName();
									if(deptlist.isEmpty()!=true){
										for(DepartmentBean bean:deptlist){%>
											<option value="<%=bean.getAd_department_id()%>"><%=bean.getName() %></option>
										<%}
									}
									%>
								</select>
						</td>
					</tr>
					<tr>
						<td>Designation : <span class="red">*</span></td>
						
						<td>	<select  name="ad_designation_id" id="ad_designation_id"  class="form-control input-medium" >
									<option value="<%=member.getDesignation().getAd_designation_id()%>"><%=member.getDesignation().getDesignation() %></option>
									<%
									DesignationDao desigdao=new DesignationDao();
									ArrayList<DesignationBean> desglist=desigdao.getAllDesignationName();
									if(desglist.isEmpty()!=true){
										for(DesignationBean bean:desglist){%>
											<option value="<%=bean.getAd_designation_id()%>"><%=bean.getDesignation() %></option>
										<%}
									}
									%>
								</select>
						</td>
						
						<td>Level : <span class="red">*</span></td>
						
						<td>	<select  name="ad_designation_level_id" id="ad_designation_level_id"  class="form-control input-medium" >
									<option value="<%=member.getDesignation_level().getAd_designation_level_id()%>"><%=member.getDesignation_level().getDesignation_level() %></option>
									<%
									DesignationLevelDao leveldao=new DesignationLevelDao();
									ArrayList<DesignationLevelBean> levellist=leveldao.getAllDesignationLevel();
									if(desglist.isEmpty()!=true){
										for(DesignationLevelBean bean:levellist){%>
											<option value="<%=bean.getAd_designation_level_id()%>"><%=bean.getDesignation_level() %></option>
										<%}
									}
									%>
								</select>
						</td>
						<td>Type : <span class="red">*</span></td>
						
						<td>	<select  name="ad_designation_type_id" id="ad_designation_type_id"  class="form-control input-medium" >
									<option value="<%=member.getDesignation_type().getAd_designation_type_id()%>"><%=member.getDesignation_type().getDesignation_type() %></option>
									<%
									DesignationTypeDao typedao=new DesignationTypeDao();
									ArrayList<DesignationTypeBean> typelist=typedao.getAllDesignationType();
									if(desglist.isEmpty()!=true){
										for(DesignationTypeBean bean:typelist){%>
											<option value="<%=bean.getAd_designation_type_id()%>"><%=bean.getDesignation_type() %></option>
										<%}
									}
									%>
								</select>
						</td>
						
						</tr>
						<tr>
						<td>Appointment : <span class="red">*</span></td>
						
						<% SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
           					String doa=sdf1.format(member.getDoa().getTime());
           					String doj=sdf1.format(member.getDoj().getTime());
           					String dor=sdf1.format(member.getDor().getTime());
           					
       					 %>
						<td>	<input type="text" name="doa" id="doa" value="<%=doa.trim() %>"  class="form-control input-medium date-picker2">
						</td>
						<%-- <td>Joining</td>
						
						<td>	<input type="date" name="doj" id="doj" value="<%=doj %>"  class="form-control input-medium">
						</td> --%>
						
						
						<td>Retirement :</td>
						
						<td>	<input type="text" name="dor" id="dor" value="<%=dor.trim() %>"  class="form-control input-medium date-picker2" readonly="readonly">
						</td>
						</tr>
						<tr>
						<td>Service Duration :</td>
						
						<td>	<input type="text" name="service_duration" value="<%=member.getService_duration() %>" id="service_duration"  class="form-control input-medium" readonly="readonly">
						</td>
					
					
					
						
						<td>A/C No : <span class="red">*</span></td>
						
						<td>	<input type="text" name="saving_ac_no" value="<%=member.getSaving_ac_no() %>" id="saving_ac_no"  class="form-control input-medium">
						</td>
					
					
					</tr>
					
					</tbody>
	              
	            </table>		






	</div><!-- End Tab Service -->
	<!-- ------------------------------------------------------End Service--------------------------------------------------- -->
	<div class="tab-pane fade" id="tab4">
<table  class="table table-bordered">
					<thead>
					<tr>
					<th colspan="6" >Nominee Detail</th>
					</tr>
					<tr>
					<th colspan="6"  >First Nominee</th>
					</tr>
					</thead>
					<tbody>
						<tr>
						<%
						String name="";
						Date dob =null;
						String age ="";
						int nrid=0;
						String nrelation="";
						int ngid=0;
						String ngname="";
						int sailutationid=0;
						String sailutationname="";
						String nominee_photo1="";
						String nominee_id1="";
						String nominee_sign1="";
						int ad_nomination_id1=0;
						
						if(member.getNominee().size()>0){
							NominationBean nominee1=member.getNominee().get(0); 
							name=nominee1.getName();
							nrid=nominee1.getRelation().getAd_relation_id();
							nrelation=nominee1.getRelation().getRelation();
							ngid=nominee1.getGender().getAd_gender_id();
							ngname=nominee1.getGender().getGender() ;
							nominee_photo1=nominee1.getPhoto();
							nominee_id1=nominee1.getId_proof();
							nominee_sign1=nominee1.getSign();
							ad_nomination_id1=nominee1.getAd_nomination_id();
							if(nominee1.getDob()!=null){
								dob=nominee1.getDob();
							}
							age=nominee1.getAge();
							sailutationid=nominee1.getSalutation().getAd_salutation_id();
							sailutationname=nominee1.getSalutation().getName();
						}
						%>
						<td>Salutation : <span class="red">*</span></td>
						<td><select  name="nominee_ad_salutation_id_1" id="nominee_ad_salutation_id_1" class="form-control input-medium">
								 <option value="<%=sailutationid %>"><%=sailutationname %></option>
								 <%
								 SalutationDao salutationdao=new SalutationDao();
								 ArrayList<SalutationBean> salutationlist=salutationdao.getAllSalutation();
								 if(salutationlist.isEmpty()!=true){
									 for(SalutationBean bean:salutationlist){%>
									 <option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
																		 
									 <%	 }
								 }%>
								</select>
								
								<input type="hidden" name="ad_nomination_id1" value="<%=ad_nomination_id1 %>"/>
								</td>
					
						<td>Name : <span class="red">*</span></td>
						
						<td>
							<input type="text"  name="nominee_name_1" value="<%=name %>" id="nominee_name_1" class="form-control input-medium">
						</td>
						<td>Relation : <span class="red">*</span></td>
						
						<td>	<select  name="nominee_ad_relation_id_1" id="nominee_ad_relation_id_1" class="form-control input-medium" >
								 <option value="<%=nrid%>"><%=nrelation %></option>
								  <%RelationDao rdao=new RelationDao();
								 	ArrayList<RelationBean> rlist=rdao.getAllRelation();
								 	if(rlist.isEmpty()!=true){
								 	for(RelationBean bean:rlist){%>
								 <option value="<%=bean.getAd_relation_id()%>"><%=bean.getRelation() %></option>
								 <%}
								 	}%>
								
						</select>
						</td>
						
						</tr>
						<tr>
							
						
						<td>Gender : <span class="red">*</span></td>
						
						<td>	<select  name="nominee_ad_gender_id_1" id="nominee_ad_gender_id_1" class="form-control input-medium" >
								 <option value="<%= ngid%>"><%=ngname %></option>
								  <%GenderDao gdao=new GenderDao();
								 	ArrayList<GenderBean> glist=gdao.getAllGender();
								 	if(glist.isEmpty()!=true){
								 	for(GenderBean bean:glist){%>
								 <option value="<%=bean.getAd_gender_id()%>"><%=bean.getGender() %></option>
								 <%}
								 	}%>
								
						</select>
						</td>
						
						<td>DOB : <span class="red">*</span></td>
						
						<% 
						String nominee_dob="";
						
						if(dob!=null){
          				    nominee_dob=new SimpleDateFormat("dd/MM/yyyy").format(dob.getTime());
          				  
          				    
						}
       					 %>
						<td>	<input type="text"  name="nominee_dob_1" value="<%=nominee_dob %>" id="nominee_dob_1" class="form-control input-medium date-picker">
						</td>
						<td>Age</td>
						
						<td>	<input type="text"  name="nominee_age_1" value="<%=new String(age).trim() %>" id="nominee_age_1" readonly="readonly" class="form-control input-medium">
						</td>
						
						
					
						</tr>
						
  
    	
       </tbody></table>
    	
    	<table class="table">
		<thead>
			<tr><td colspan="3">Note :- Use only JPG or PNG image with 100px * 100px height and width.</td></tr>
		</thead>
		<tbody>
			<tr>
			<td width="25%">
			<div class="userimg-block">
				<div class="user-pic">
				<img class="uimg" id='nominee_photo_view_1' src="<%=request.getContextPath()%>/member_images/<%=nominee_photo1 %>" alt="Employee Photo" />
				
				</div>
				<div class="user-input">
				  <span class="text-center">Photo</span>
				<input type="file" name="nominee_photo_1" id="nominee_photo_1" accept="image/*" />
				
				</div>
				<label class="error"></label>
			   </div><!-- End userimg-block -->
             </td>
			  <td width="25%">
			  <div class="userimg-block">
			  <div class="user-pic">
				    <img class="uimg" id='nominee_id_proof_view_1' src="<%=request.getContextPath()%>/member_images/<%=nominee_id1 %>" alt="Employee Id Card"  />
				</div>
				<div class="user-input">
				  <span class="text-center">ID Proof</span>
				<input type="file" name="nominee_id_proof_1" id="nominee_id_proof_1" accept="image/*" />
				
				</div>
				<label class="error"></label>
			   </div><!-- End userimg-block -->
            </td>
				<td colspan="2" width="25%">
				<div class="userimg-block">
			     <div class="user-pic">
				    <img class="uimg" id='nominee_sign_view_1' src="<%=request.getContextPath()%>/member_images/<%=nominee_sign1 %>" alt="Employee Sign"  />
				 </div>
				 <div class="user-input">
				  <span class="text-center">Sign</span>
				  <input type="file" name="nominee_sign_1" id="nominee_sign_1" accept="image/*" />
				  
				</div>
				<label class="error"></label>
			   </div><!-- End userimg-block -->
				</td>
			</tr>
			</tbody>
		</table>	
		
       
  		<%
  		
  		  name="";
		  dob =null;
		  age ="";
		 nrid=0;
		 nrelation="";
		 ngid=0;
		 ngname="";
		 sailutationid=0;
		 sailutationname="";
		 int ad_nomination_id2=0;
		if(member.getNominee().size()>1){
			NominationBean nominee2=member.getNominee().get(1); 
			name=nominee2.getName();
			nrid=nominee2.getRelation().getAd_relation_id();
			nrelation=nominee2.getRelation().getRelation();
			ngid=nominee2.getGender().getAd_gender_id();
			ngname=nominee2.getGender().getGender() ;
			ad_nomination_id2=nominee2.getAd_nomination_id();
			if(nominee2.getDob()!=null){
				dob=nominee2.getDob();
			}
			age=nominee2.getAge();
			sailutationid=nominee2.getSalutation().getAd_salutation_id();
			sailutationname=nominee2.getSalutation().getName();
		}
  		
  		
  		
  		
  		%>
					<table class="table table-bordered">
					<tr>
					<th colspan="6">Second Nominee</th>
					</tr>
					
						<tr>
						<td>Salutation : <span class="red">*</span></td>
						<td><select  name="nominee_ad_salutation_id_2" id="nominee_ad_salutation_id_2" class="form-control input-medium">
								 <option value="<%= sailutationid%>"><%=sailutationname%></option>
								  <%
								 SalutationDao sdao2=new SalutationDao();
								 ArrayList<SalutationBean> slist2=sdao2.getAllSalutation();
								 if(slist2.isEmpty()!=true){
									 for(SalutationBean bean:slist2){%>
									 <option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
																		 
									 <%	 }
								 }%>
								</select>
								<input type="hidden" name="ad_nomination_id2" value="<%=ad_nomination_id2%>">
								</td>
					
						<td>Name : <span class="red">*</span></td>
						
						<td >
						
								<input type="text"  name="nominee_name_2" value="<%=name%>" id="nominee_name_2" class="form-control input-medium" >
						</td>
						<td>Relation : <span class="red">*</span></td>
						
						<td>	<select  name="nominee_ad_relation_id_2" id="nominee_ad_relation_id_2" class="form-control input-medium" >
						 		<option value="<%=nrid%>"><%=nrelation %></option>
								  <%RelationDao rdao2=new RelationDao();
								 	ArrayList<RelationBean> rlist2=rdao2.getAllRelation();
									 if(rlist2.isEmpty()!=true){
								 	for(RelationBean bean:rlist2){%>
								 <option value="<%=bean.getAd_relation_id()%>"><%=bean.getRelation() %></option>
								 <%}
								 	}%>
								
						</select>
						</td>
						</tr>
									<tr>
						
						
						<td>Gender : <span class="red">*</span></td>
						
						<td>	<select  name="nominee_ad_gender_id_2" id="nominee_ad_gender_id_2" class="form-control input-medium" >
						 <option value="<%=ngid%>"><%=ngname%></option>
								 <%GenderDao gdao1=new GenderDao();
								 	ArrayList<GenderBean> glist1=gdao1.getAllGender();
									 if(glist1.isEmpty()!=true){
								 	for(GenderBean bean:glist1){%>
								 <option value="<%=bean.getAd_gender_id()%>"><%=bean.getGender() %></option>
								 <%}
								 	}%>
								
						</select>
						</td>
						
					
						
						<td>DOB: <span class="red">*</span></td>
						
						<%
					
						
						if(dob!=null){
          				    nominee_dob=new SimpleDateFormat("dd/MM/yyyy").format(dob.getTime());
          				  
          				    
						}
						
						%>
						<td>	<input type="text"  name="nominee_dob_2" value="<%=nominee_dob %>" id="nominee_dob_2" class="form-control input-medium date-picker">
						</td>
						<td>Age</td>
						
						<td>	<input type="text"  name="nominee_age_2" value="<%=new String(age).trim() %>" id="nominee_age_2" readonly="readonly" class="form-control input-medium">
						</td>
						</tr>
			</tbody>
			</table>
			
			
			<table class="table">
		<thead>
			<tr><td colspan="3">Note :- Use only JPG or PNG image with 100px * 100px height and width.</td></tr>
		</thead>
		<tbody>
			<tr>
			<td width="25%">
			<div class="userimg-block">
				<div class="user-pic">
				<img class="uimg" id='nominee_photo_view_2' src="Image/emp.png" alt="Employee Photo" />
				
				</div>
				<div class="user-input">
				  <span class="text-center">Photo</span>
				<input type="file" name="nominee_photo_2" id="nominee_photo_2" accept="image/*" />
				
				</div>
				<label class="error"></label>
			   </div><!-- End userimg-block -->
             </td>
			  <td width="25%">
			  <div class="userimg-block">
			  <div class="user-pic">
				    <img class="uimg" id='nominee_id_proof_view_2' src="Image/id.png" alt="Employee Id Card"  />
				</div>
				<div class="user-input">
				  <span class="text-center">ID Proof</span>
				<input type="file" name="nominee_id_proof_2" id="nominee_id_proof_2" accept="image/*" />
				
				</div>
				<label class="error"></label>
			   </div><!-- End userimg-block -->
            </td>
				<td colspan="2" width="25%">
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
			</tbody>
		</table>	
			
			
			
			</div><!-- End Tab Nominee -->
	<!-- ------------------------------------------------------End Nominee--------------------------------------------------- -->
	<div class="tab-pane fade" id="tab5">
	
	
	<table class="table table-bordered">
	   <tr>
	   		<td>Member : </td>
	   		<td>
	   			<select class="input-large" name="witness_ad_member_id" id="witness_ad_member_id" >
							<%
							
							int witness_society_no=0;
							int witness_pf_no=0;
							String witness_name="NA";
							String witness_mobile="NA";
							String witness_address="NA";
							
							
							
							
							WitnessDao witnessdao=new WitnessDao();
							WitnessBean witness=witnessdao.getWitnessById(member.getAd_member_id());%>
							<option value="<%=witness.getAd_member_id()%>"><%=witness.getAd_witness_mem_no()+" | "+ witness.getAd_witness_name() %></option>
							<%
						
							
							ArrayList<MemberRegistrationBean> memberlist=new MemberRegistrationDao().getAllMemberlist();
							if(memberlist!=null){
								for(MemberRegistrationBean bean:memberlist){%>
									<option value="<%=bean.getAd_member_id()%>"><%=bean.getAd_society_no()+" | "+bean.getName() %></option>
								<%}
							}%>
				</select><label class="error"></label>
	   		</td>
	   		<!-- </tr>
	   		
	   		<tr>
	   		<td>PF.No : <span class="red">*</span></td>
	   		 -->
	   		<td>Society.No : </td>
	   		<td><input type="text"  name="witness_ad_society_id" value="<%=witness.getAd_witness_mem_no() %>" id="witness_ad_society_id" class="form-control input-medium">
	   		</td>
	   </tr>
	   <tr>
	   		<td>Salutation : <span class="red">*</span></td>
	   		<td>
	   			<select class="form-control input-medium" name="witness_ad_salutation_id" id="witness_ad_salutation_id" >
					 <%
						 SalutationDao sdao3=new SalutationDao();
						 ArrayList<SalutationBean> slist3=sdao3.getAllSalutation();
						 if(slist3.isEmpty()!=true){
						 for(SalutationBean bean:slist3){%>
						 <option value="<%=bean.getAd_salutation_id()%>"><%=bean.getName() %></option>
					<%}
					}%>
			    </select><label class="error"></label>
			    <input type="hidden" name="ad_witness_id" value="<%=witness.getAd_witness_id()%>"/>
	   		</td>
	   		<td>Name : <span class="red">*</span></td>
	   		<td><input class="form-control input-medium" type="text"  name="witness_name" id="witness_name" value="<%=witness.getAd_witness_name() %>" style="text-transform: uppercase;"/><label class="error"></label></td>
	   </tr>
	   <tr>
	   		<td>Mobile : <span class="red">*</span> </td>
	   		<td><input type="text"  name="witness_mobile" value="<%=witness.getAd_witness_mobile() %>" id="witness_mobile" class="form-control input-medium">
	   		<label class="error"></label>
	  
	   		<td>Address : <span class="red">*</span></td>
	   		<td>	<input type="text"  name="witness_address" value="<%= witness.getAd_witness_address() %>" id="witness_address" class="form-control input-large">
					<label class="error"></label>	</td>
	   </tr>
	   </tbody>
	</table>
          
	
	</div><!-- End Tab Witness -->
	<!-- ------------------------------------------------------End Withness-------------------------------------------------- -->
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
			<a href="javascript:;" class="btn blue button-next">
				Continue <i class="m-icon-swapright m-icon-white"></i>
			</a>
			<button  class="btn green button-submit" style="display: none;">Submit <i class="m-icon-swapright m-icon-white"></i></button>
		   </div>
		 </div>
	 </div>
</div><!--End form-actions fluid -->
</form>	
</div><!-- End portlet-body -->
</div><!-- End portlet -->
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
<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/scripts/custom/components-pickers.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script type="text/javascript" src="assets/scripts/custom/form-wizard-member-edit-app.js"></script>
<script src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript">
jQuery(document).ready(function() {       
	FormWizardMember.init();
	
	//$('#tblSecondNomineeInfo').hide();
	//$('#tblSecondNomineeImage').hide();
	//$('#tblSecondNomineeImageModal').hide();

	$('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true});
	$('.date-picker2').datepicker({format: 'dd/mm/yyyy',autoclose: true,endDate:'<%=new SimpleDateFormat("dd/MM/yyyy").format(session.getAttribute("openday"))%>'});
	$('#ad_state_id').select2();
	$('#ad_district_id').select2();
	$('#ad_city_id').select2();
	$('#ad_state_id_1').select2();
	$('#ad_district_id_1').select2();
	$('#ad_city_id_1').select2();
	$('#ad_branch_id').select2();
	$('#ad_department_id').select2();
	$('#ad_designation_id').select2();
	$('#nominee_ad_relation_id_1').select2();
	$('#nominee_ad_relation_id_2').select2();
	$('#witness_ad_member_id').select2();
});
</script>
<script type="text/javascript">
$(document).ready(function(e) {
jQuery('#witness_ad_member_id').change(function(){
	//loading_show();
	var w_memeber_id = parseInt($(this).val());
	
	if(w_memeber_id!=""){
		var dataString = {"action":"view","ad_member_id":w_memeber_id};
		$.ajax({
			method:"post",
			url:"AdMemberRegistration",
			data:dataString,
			success: function(data){
				
				$('#witness_ad_society_id').val(data.MemberInfo.ad_society_no);
				$('#witness_ad_salutation_id').val(data.MemberInfo.salutation.ad_salutation_id);
				$('#witness_name').val(data.MemberInfo.name);
				$('#witness_mobile').val(data.MemberInfo.address[0].mobile);
				$('#witness_address').html(data.MemberInfo.address[0].line1+" "+data.MemberInfo.address[0].line2+" "+data.MemberInfo.address[0].city.city+" "+data.MemberInfo.address[0].district.district+" "+data.MemberInfo.address[0].city.city);
			},
			error: function(xhrerror, status, error){
				console.log(status);
			}
		});
		
	}//end check member id
	
});



	
	$('#nominee_dob_1').datepicker().on('changeDate', function (ev) {
		var date = $(this).val();
		var age = calculateAgeByDob(date);
		if(age<0){age=0;}
		$('#nominee_age_1').val(age);
		
		if(age<18){
			$('#guardian1').removeAttr('disabled');
			$('#custom-page-message').html("<div class='note note-info'>Nominee is "+ age +" year old. Please fill Guardian Name Below.</div>");
			$('.custom-messageBox').modal('show');
		}else{
			$('#guardian1').val("");
			$('#guardian1').attr("disabled","disabled");
		}
		
	});//end chanage nominee dob for calculate age
	
	$('#nominee_dob_2').datepicker().on('changeDate', function (ev) {
		var date = $(this).val();
		var age = parseInt(calculateAgeByDob(date));

		if(age<0){age=0;}
		$('#nominee_age_2').val(age);
		
		if(age<18){
			$('#guardian2').removeAttr('disabled');
			$('#custom-page-message').html("<div class='note note-info'>Nominee is "+ age +" year old. Please fill Guardian Name Below.</div>");
			$('.custom-messageBox').modal('show');
		}else{
			$('#guardian2').attr("disabled","disabled");
		}
	});//end chanage nominee dob for calculate age
	
	function calculateAgeByDob(date){
		var newDate = date.split("/").reverse().join("/");
		var dob = new Date(newDate);
		var today = new Date();
		var diff = today-dob;
		var age = Math.floor(diff / (31536000000));// Divide by 1000*60*60*24*365
		
		return  age;
	}//end calculateAgeByDob
	
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
	
	
$("#ad_state_id").change(function(e) {
	
		if($(this).val()==0)
		{
			alert("Please Select State......!!");
			return false;
		}
		else
		{
        		var ad_state_id=$(this).val();
		$.ajax({
			   type: "POST",
			   url: "Ajax/read_district_by_state_id.jsp?ad_state_id="+ad_state_id,
			   async:false,
			   success: function(data)
			   {	
				   $('#ad_district_id').html(data);
				   $('#ad_district_id_1').html(data);
		  	} }); 
		}
		
	});

	
$("#ad_district_id").change(function(e) {
	
	if($(this).val()==0)
	{
		alert("Please Select State......!!");
		return false;
	}
	else
	{
    		var ad_district_id=$(this).val();
    		//alert(group_id);
	$.ajax({
		   type: "POST",
		   url: "Ajax/read_city_by_district_id.jsp?ad_district_id="+ad_district_id,
		   async:false,
		   success: function(data)
		   {	
			   $('#ad_city_id').html(data);
			   $('#ad_city_id_1').html(data);
	  	} }); 
	}
	
});  

$("#ad_state_id_1").change(function(e) {
	
	if($(this).val()==0)
	{
		alert("Please Select State......!!");
		return false;
	}
	else
	{
    		var ad_state_id=$(this).val();
    		//alert(group_id);
	$.ajax({
		   type: "POST",
		   url: "Ajax/read_district_by_state_id.jsp?ad_state_id="+ad_state_id,
		   async:false,
		   success: function(data)
		   {	
			   $('#ad_district_id_1').html(data);
	  	} }); 
	}
	
});


$("#ad_district_id_1").change(function(e) {

if($(this).val()==0)
{
	alert("Please Select State......!!");
	return false;
}
else
{
		var ad_district_id=$(this).val();
$.ajax({
	   type: "POST",
	   url: "Ajax/read_city_by_district_id.jsp?ad_district_id="+ad_district_id,
	   async:false,
	   success: function(data)
	   {	
		   $('#ad_city_id_1').html(data);
		   $('#ad_city_id_1').trigger("chosen:updated");
  	} }); 
}

});  


$("#same").on("click", function() {
    if($(this).is(':checked')){
    	
    	$('#mobile1').val($('#mobile').val());
    	$('#phone1').val($('#phone').val());
    	$('#email1').val($('#email').val());
    	$('#line1_1').val($('#line1').val());
    	$('#line2_1').val($('#line2').val());
    	var country  = $('#ad_country_id option:selected').val();
    	var state    = $('#ad_state_id option:selected').val();
    	var district = $('#ad_district_id option:selected').val();
    	var city     = $('#ad_city_id option:selected').val();
    	
    	$('#ad_country_id_1 option[value='+country+']').attr('selected','selected');
    	$("#ad_state_id_1").select2("val", state);
    	$("#ad_district_id_1").select2("val", district);
    	$("#ad_city_id_1").select2("val", city);
    	$('#pincode1').val($('#pincode').val());
    	
    }else{
    	
    	$('#mobile1').val('');
    	$('#phone1').val('');
    	$('#email1').val('');
    	$('#line1_1').val('');
    	$('#line2_1').val('');
    	var country=$('#country').val();
    	$("#ad_state_id_1").select2("val", "");
    	$("#ad_district_id_1").select2("val", "");
    	$("#ad_city_id_1").select2("val", "");
    	$('#pincode1').val('');
    }
  });
  
  //check same nominee
  $("#second_nominee").click(function(){
	  if($(this).is(':checked')){
		  $('#tblSecondNomineeInfo').show();
		  $('#tblSecondNomineeImage').show();
		  $('#tblSecondNomineeImageModal').show();
	  }else{
		  $('#tblSecondNomineeInfo').hide();
		  $('#tblSecondNomineeImage').hide();
		  $('#tblSecondNomineeImageModal').hide();
	  }
  });
  
  //branch display
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
                  $("#ajaxResponse").html("<div><b>Branch id in Invalid!</b></div>");
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
<script type="text/javascript">
$(function(){
	
	 $('#doa').datepicker().on('changeDate', function (ev) {
		var doa = $(this).val();
		var dor = $("#dor").val();
		var dataString = {"doa":doa,"dor":dor};
		 $.ajax({
			  type: "POST",
			  url: "Ajax/getServiceDuration.jsp",
			  data: dataString,
			  success: function(data){
				  $("#service_duration").removeAttr('readonly');
				  $('#service_duration').val(data);
				  $('#h_service_duration').val(data);
				  $("#service_duration").attr('readonly', 'readonly');
			  },
			  error:function(jqXHR, textStatus, errorThrown){
				  console.log("error");
			  }
		  });
	    });	//end datepick change doa
	 
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
		               $("#ajaxResponse").html(jqXHR.responseText);
		         }

		     });
	  });//end designation change type id
	  
	  $("#dob").change(function(e){
		  var dataString = "date="+$("#dob").val();
			 $.ajax({
		         type: "POST",
		         url: "Ajax/calculate_retirement_date.jsp",
		         data: dataString,      
		         success: function( data, textStatus, jqXHR) {
		             $("#dor").val(data);
		             $("#h_dor").val(data);
		         },
		         error: function(jqXHR, textStatus, errorThrown){
		               $("#ajaxResponse").html(jqXHR.responseText);
		         }

		     }); 
			 
	  });//end dob change 
	  
});//end dom


function getSalutationData(val,senderID){
	   $.ajax({
		  type: "POST",
		  url: "Ajax/getSalutation.jsp",
		  data: "gender="+parseInt(val),
		  success: function(data){
			  $('#'+senderID).html(data);
		  },
		  error:function(jqXHR, textStatus, errorThrown){
			  console.log("error");
		  }
	  });
}//getSalutation function

</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>