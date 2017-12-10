<%@page import="Model.Dao.FdTrxDao"%>
<%@page import="Model.Bean.FdTrxBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%HttpSession session1=request.getSession(false);
MemberLoginBean user1=null;
user1=(MemberLoginBean)session1.getAttribute("MemberLoginBean"); %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%@ include file= "../Layout/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/data-tables/DT_bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/plugins/bootstrap-datepicker/css/datepicker.css"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-full-width">
<%@ include file= "memberheadtitle.jsp" %>
<!-- BEGIN HEADER -->
<div class="header navbar mega-menu">
<!-- BEGIN TOP NAVIGATION BAR -->
<%@include file= "member_hdr_menu.jsp"%>
<!-- END TOP NAVIGATION BAR -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN EMPTY PAGE SIDEBAR -->
	<%@ include file= "memberemptynavbar.jsp" %>
	<!-- END EMPTY PAGE SIDEBAR -->
<!-- BEGIN CONTENT -->
<div class="page-content-wrapper">
<div class="page-content">
<!-- BEGIN PAGE CONTENT-->
<div class="row">
<div class="col-md-12">
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i>
				<a href="#">Home</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">FGDS</a><i class="fa fa-angle-right"></i>View</li>
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
	 <div class="portlet box blue">
	  <div class="portlet-title">
	   <div class="caption">
		View FDGS
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	   <form id="frmfd" action="#" method="post" autocomplete="off">
			<table class="table table-striped table-bordered">
				<tr>
				<td>Select Account : <span class="red">*</span></td>
				<td>
					<select id="fdno" class="form-control input-medium" name="fdno">
					<option value="">--select--</option>
					<%
					ArrayList<FdTrxBean> list = new FdTrxDao().getAllFdNoByMemId(user1.getMember().getAd_member_id()) ;
					if(list.isEmpty()!=true){
						
						for(FdTrxBean tb:list){
							%>
								<option value="<%=tb.getFd_no()%>"><%=tb.getFd_no() %></option>
							<%
						}
						
					}
					%>
		         </select>
				</td>
				<td><input class="btn btn-md blue" type="button" id="go" value="Go"/></td>
				</tr>
				<tr>
				<td></td>
				<td>
				<input class="btn btn-md blue" type="button" onclick="prnts()" id="print" value="Print"/>
				<input class="btn btn-md blue" onclick="tableToExcel('fd', 'FD')" type="button" id="exportex" value="Export In Excel"/>
				</td>
				</tr>
			</table>
			</form>
			<div class="clearfix"></div>
			<div>
				<iframe id="fd_window" style="width: 100%; min-height:50vh; border: none" src=""></iframe>
			</div>
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
<%@ include file= "../Layout/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript">
jQuery(function() {       
	//jQuery('#dataTable1').DataTable();
	jQuery("#frmfd").validate({
		  rules: {
			fdno: {
		      required: true,
		      digits:true
		    }
		  }
		});

	$("#print").prop("disabled",true);
	$("#exportex").prop("disabled",true);
	
	$("#go").click(function(e){
		
		var fdno=$("#fdno").val();		
		if(fdno!="0" && fdno!=""){			
					var url="getmemberfd.jsp?fdno="+fdno;
					$('#fd_window').attr('src', url);			
					$("#print").prop("disabled",false);
					$("#exportex").prop("disabled",false);			
		}		
		
	});
	
});

</script>
<script type="text/javascript">
   
    function prnts()
    {
    
    var divElements = document.getElementById('fd_window').contentWindow.document.body.innerHTML;
    	 var printWindow = window.open("", "_blank", "");            
            printWindow.document.open();          
            printWindow.document.write('<html><body><center> <h3 style="margin-bottom:0px;margin-top:0px"> State Bank Of India Adhikari Sahakari Sakh Samiti </h3><h4 style="margin-bottom:0px;margin-top:0px">A Multi State Co-operative Society<br/></h4></center>');
          
    		printWindow.document.write(divElements);
            printWindow.document.write('</body></html>');
            printWindow.document.close();
            printWindow.focus();
    		 setTimeout(function() {
                printWindow.print();
                printWindow.close();
            }, 100);
    }
</script>   

<script type="text/javascript">
var tableToExcel = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><center> <h3 style="margin-bottom:0px;margin-top:0px"> State Bank Of India Adhikari Sahakari Sakh Samiti </h3><h4 style="margin-bottom:0px;margin-top:0px">A Multi State Co-operative Society<br/></h4></center><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById('fd_window').contentDocument.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
</script>
</body>
</html>