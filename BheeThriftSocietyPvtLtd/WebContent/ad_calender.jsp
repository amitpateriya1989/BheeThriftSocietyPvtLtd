

<%@page import="Model.Bean.CalenderBean"%>
<%@page import="Model.Dao.CalenderDao"%>
<%@page import="Model.Dao.UserDao"%>
<%@page import="Model.Bean.ListItemBean"%>
<%@page import="Model.Dao.ListItemDao"%>
<%@page import="Model.Bean.ListBean"%>
<%@page import="Model.Dao.ListDao"%>
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
			<li><a href="#">Holiday Calender</a><i class="fa fa-angle-right"></i>Add and view</li>
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
<div class="caption">Add Holiday</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<form id="frmCalender" action="AdCalender?action=insert" method="post" autocomplete="off" >
<table class="table table-bordered">

<%
							
							
							ArrayList<ListItemBean> itembean=null;
							ListItemDao itemdao=new ListItemDao();
							itembean=itemdao.getListItemByListId(2);
								
								%>	
<tr>
   <td>Year  : <span class="red">*</span></td>
   <td>	
       <select class="form-control input-medium" name="year" id="year" >
		<%for(ListItemBean bean:itembean){ %>
		 <option value="<%=bean.getName()%>"><%=bean.getName() %></option>
		 <%} %>
		 
	   </select>
	</td>
 
                         <%
							itembean=null;
							itemdao=new ListItemDao();
							itembean=itemdao.getListItemByListId(1);
								
						%>	
 
 <td>Holiday : <span class="red">*</span></td>
 <td><select class="form-control input-medium" name="ad_list_item_id" id="ad_list_item_id" >
		<%for(ListItemBean bean:itembean){ %>
		 <option value="<%=bean.getAd_list_item_id()%>"><%=bean.getName() %></option>
		 <%} %>
	   </select></td>
 </tr>
 <tr>
 <td>From Date: <span class="red">*</span></td>
					<td> <input class="form-control input-medium date-picker" type="text" placeholder="dd/mm/yyyy" name="holiday_date" id="holiday_date"  />
					<label id="form_type_error" class="error "></label>
					</td>
					
					<td>Status : <span class="red">*</span></td>
 <td><input type="text" name="status" value="Holiday" class="form-control input-medium"/></td>
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
<div class="caption">View Holiday Calender</div>
	<div class="tools"> <a href="javascript:;" class="collapse"> </a>
	</div>
	</div>
	<div class="portlet-body" style="height: 450px;overflow-y:auto;">
	<table id="dataTable1" class="table table-striped table-bordered table-hover">
		<thead>
		   <tr>
			<th>Sno</th>
			<th>Year</th>
			<th>Date</th>
			<th>Holiday</th>
			<th>Status</th>
			<th>IsActive</th>
			<th>Action</th>
		  </tr>
		</thead>
		<tbody>
		    <% CalenderDao sdao=new CalenderDao();
			   ArrayList<CalenderBean> slist=sdao.getAllCalender();
			   int i=0;
			   if(slist.isEmpty()!=true){
			     for(CalenderBean bean:slist){
			 %> 
		<tr>
			<td><%=++i %></td>
			
			<td><%=bean.getYear() %></td>
			<td><%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getHoliday_date().getTime()) %></td>
			<td><%=new ListItemDao().getListItemById(bean.getAd_list_item_id()).getName() %></td>
			<td><%=bean.getStatus() %></td>
			<td><% if(bean.isIsactive()==true){ %>
				<span class="badge badge-primary">Active</span>
				<% }else{ %>
				<span class="badge badge-warning">Inactive</span>
				<%} %>
			</td>
			<td>
			  <a class="btn btn-xs green" href="AdCalender?action=edit&ad_calender_id=<%=bean.getAd_calender_id()%>"><i class="fa fa-edit"></i> Edit</a>
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
<script>
jQuery(function() {       
	jQuery('#dataTable1').DataTable({"scrollY":"200px","bPaginate": false});
	jQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',autoclose: true});
	
	jQuery.validator.addMethod("AlphaSpace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z&\s]*$/.test(value);
		}, "Please enter latter, special symbol(&) and space only.");
	
	jQuery.validator.addMethod("validDate", function (value, element) {
        return this.optional(element) || /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/i.test(value);
    }, "Please enter valid Date.");
	
	jQuery("#frmCalender").validate({
		  rules: {
			  ad_list_id:{
				  required:true,
				  digits:true
			  },
			  name: {
		          required: true,
		          AlphaSpace:true,
		          maxlength:50
		    },
		    holiday_date: {
		    	
		    	required: true,
				  validDate:true
		    	
		    },year: {
		    	required: true
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
