

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
			<li><a href="#">Holiday Calender</a><i class="fa fa-angle-right"></i>Edit</li>
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

<%

String id=request.getParameter("ad_calender_id");
int ad_calender_id=0;
CalenderBean bean=null;
if(id!=null){
	try{
		ad_calender_id=Integer.parseInt(id);
		bean=new CalenderDao().getCalenderById(ad_calender_id);
	}catch(NumberFormatException n){
		n.printStackTrace();
	}
}




%>


<!------------------------------------------------------------------- -->
<!-- BEGIN BORDERED TABLE PORTLET-->
<div class="portlet box purple">
<div class="portlet-title">
<div class="caption">Edit Holiday</div>
<div class="tools"> <a href="javascript:;" class="collapse"> </a>
</div>
</div>
<div class="portlet-body">
<form id="frmCalender" action="AdCalender?action=update&ad_calender_id=<%=bean.getAd_calender_id() %>" method="post" autocomplete="off" >
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
       <option value="<%=bean.getYear()%>"><%=bean.getYear() %></option>
		<%for(ListItemBean bean1:itembean){ %>
		 <option value="<%=bean1.getName()%>"><%=bean1.getName() %></option>
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
  <option value="<%=bean.getAd_list_item_id()%>"><%=new ListItemDao().getListItemById(bean.getAd_list_item_id()).getName() %></option>
		<%for(ListItemBean bean1:itembean){ %>
		 <option value="<%=bean1.getAd_list_item_id()%>"><%=bean1.getName() %></option>
		 <%} %>
	   </select></td>
 </tr>
 <tr>
 <td>Date: <span class="red">*</span></td>
					<td> <input class="form-control input-medium date-picker" type="text" placeholder="dd/mm/yyyy" name="holiday_date" id="holiday_date"
					value="<%=new SimpleDateFormat("dd/MM/yyyy").format(bean.getHoliday_date()) %>"  />
					<label id="form_type_error" class="error "></label>
					</td>
					
					<td>Status : <span class="red">*</span></td>
 <td><input type="text" name="status" value="Holiday" class="form-control input-medium" value="<%=bean.getStatus()%>"/></td>
 </tr>
 
 
 
 
 <tr>
	<td>IsActive : <span class="red">*</span></td>
 <td><select class="form-control input-medium" name="isactive">
					    <option>-- Select Status --</option>
						<option value="true" <% if(bean.isIsactive()==true){%> selected="selected" <%}%>>Active</option>
						<option value="false" <% if(bean.isIsactive()==false){%> selected="selected" <%}%>>Inactive</option>
					</select><label class="error"></label></td>
	<td colspan="2">
	  <input class="btn blue" type="submit" name="Submit" value="submit"/>
	  
	   <input class="btn  green" type="reset" name="Clear"/>
	</td>
 </tr>						
 </table>
 </form>	
 </div><!-- End portlet-body -->
</div>
<!-- BEGIN BORDERED TABLE PORTLET-->

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
