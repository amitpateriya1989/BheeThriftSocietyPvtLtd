<%@page import="Model.Dao.VoucherDao"%>
<%@page import="Model.Bean.VoucherBean"%>
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
				<a href="#">Approvals</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Approval</a><i class="fa fa-angle-right"></i> view</li>
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
	   <div class="caption">
		View Approval
	   </div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	   <!-- ---------------------------------------------------------------------------- -->
	   <!-- ------------------- -------------- Start Tab ---------------------------------------------->
		<ul class="nav nav-pills">
			<li class="active"><a href="#tab1" data-toggle="tab">Manual</a></li>
	<li class=""><a href="#tab2" data-toggle="tab">New Member</a></li>
	<li class=""><a href="#tab3" data-toggle="tab">Share</a></li>
	<li class=""><a href="#tab4" data-toggle="tab">Loan</a></li>
	<li class=""><a href="#tab5" data-toggle="tab">FD</a></li>
	<li class=""><a href="#tab6" data-toggle="tab">Final Payments</a></li>
	<li class=""><a href="#tab7" data-toggle="tab">Payroll</a></li>
	<li class=""><a href="#tab8" data-toggle="tab">Pay Interest</a></li>
			
	</ul>
	  <div class="tab-content">
	  <div class="tab-pane fade active in" id="tab1">
		 <table id="dataTable1" class="table table-striped table-bordered table-hover">
			<thead>
            <tr>
                <th>Sno</th>
                <th>Trx no</th>
                <th>Trx Date</th>
                <th>VoucherType</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
           </thead>
           <tbody>
           <%
           ArrayList<VoucherBean> lst =new VoucherDao().getAllVoucherByType("manual");
       
		   int i=1;
		   if(lst!=null){
			for(VoucherBean bean:lst){
        
            %>    
            <tr>
                <td><%=i %></td>
                <td><%=bean.getTrx_no() %></td>
                <td><%=bean.getTrx_date() %></td>
                <td><%=bean.getVoucher_type() %></td>
                <td><%=bean.getVamt() %></td>
                <td><%=bean.getStatus() %></td>
                <td><a href="#" class="btn btn-xs blue" onclick="view(<%=bean.getAd_voucher_id()%>)">View</a></td>
                
            </tr>
            
            <%
            i++;
			}
		    }
            %>
            </tbody>
           </tbody>
		 </table>
	  </div>
	 <!-- ------------------------------------------------------------------- -->
	 <div class="tab-pane fade" id="tab2">
		<table id="dataTable2" class="table table-striped table-bordered table-hover">
			 <thead>
            <tr>
                <th>Sno</th>
                <th>Trx no</th>
                <th>Trx Date</th>
                <th>VoucherType</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody align="center">
        <%
        ArrayList<VoucherBean> lst2 =new VoucherDao().getAllVoucherByType("new_member");
       
		int i2=1;
		if(lst2!=null){
			for(VoucherBean bean2:lst2){
        
        %>
            <tr>
                <td><%=i2 %></td>
                <td><%=bean2.getTrx_no() %></td>
                <td><%=bean2.getTrx_date() %></td>
                <td><%=bean2.getVoucher_type() %></td>
                <td><%=bean2.getVamt() %></td>
                <td><%=bean2.getStatus() %></td>
                <td><a href="#" class="btn btn-xs blue" onclick="view(<%=bean2.getAd_voucher_id()%>)">View</a></td>
                
            </tr>
            
            <%
            i2++;
			}
			}
            %>
            </tbody>
		</table>	
	</div>
	<!-- ------------------------------------------------------------------- -->
	<div class="tab-pane fade" id="tab3">
		<table id="dataTable3" class="table table-striped table-bordered table-hover">
		   <thead>
            <tr>
                <th>Sno</th>
                <th>Trx no</th>
                <th>Trx Date</th>
                 <th>VoucherType</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
            <tbody align="center">
        <%
        ArrayList<VoucherBean> lst3 =new VoucherDao().getAllVoucherByType("new_share");
       
		int i3=1;
		if(lst3!=null){
			for(VoucherBean bean3:lst3){
        
        %>
            <tr>
                <td><%=i3 %></td>
                <td><%=bean3.getTrx_no() %></td>
                <td><%=bean3.getTrx_date() %></td>
                <td><%=bean3.getVoucher_type() %></td>
                <td><%=bean3.getVamt() %></td>
                <td><%=bean3.getStatus() %></td>
                <td><a href="#" class="btn btn-xs blue" onclick="viewshare(<%=bean3.getAd_voucher_id()%>)">View</a></td>
                
            </tr>
            <%
            i3++;
			}
		   }
            %>
            </tbody>
		</table>	
	</div>
	<!-- ------------------------------------------------------------------- -->
	<div class="tab-pane fade" id="tab4">
		<table id="dataTable4" class="table table-striped table-bordered table-hover">
		    <thead>
            <tr>
                <th>Sno</th>
                <th>Trx no</th>
                <th>Trx Date</th>
                 <th>VoucherType</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody >
        <%
        ArrayList<VoucherBean> lst4 =new VoucherDao().getAllVoucherByType("new_loan");
       
		int i4=1;
		if(lst4!=null){
			for(VoucherBean bean4:lst4){
        
        %>
            <tr>
                <td><%=i4 %></td>
                <td><%=bean4.getTrx_no() %></td>
                <td><%=bean4.getTrx_date() %></td>
                <td><%=bean4.getVoucher_type() %></td>
                <td><%=bean4.getVamt() %></td>
                <td><%=bean4.getStatus() %></td>
                <td><a href="#" class="btn btn-xs blue" onclick="view(<%=bean4.getAd_voucher_id()%>)">View</a></td>
                
            </tr>
            
            <%
            i4++;
			}
		}
            %>
             <%
        ArrayList<VoucherBean> llst4 =new VoucherDao().getAllVoucherByType("Loan_deposit");
       
		int li4=1;
		if(llst4!=null){
			for(VoucherBean lbean4:llst4){
        
        %>
            <tr>
                <td><%=li4 %></td>
                <td><%=lbean4.getTrx_no() %></td>
                <td><%=lbean4.getTrx_date() %></td>
                <td><%=lbean4.getVoucher_type() %></td>
                <td><%=lbean4.getVamt() %></td>
                <td><%=lbean4.getStatus() %></td>
                <td><a href="#" class="btn btn-xs blue" onclick="view(<%=lbean4.getAd_voucher_id()%>)">View</a></td>
                
            </tr>
            
            <%
            i4++;
			}
		}
            %>
            
              <%
        ArrayList<VoucherBean> llst5 =new VoucherDao().getAllVoucherByType("loan_interest");
       
		int li5=1;
		if(llst5!=null){
			for(VoucherBean lbean5:llst5){
        
        %>
            <tr>
                <td><%=li5 %></td>
                <td><%=lbean5.getTrx_no() %></td>
                <td><%=lbean5.getTrx_date() %></td>
                <td><%=lbean5.getVoucher_type() %></td>
                <td><%=lbean5.getVamt() %></td>
                <td><%=lbean5.getStatus() %></td>
                <td><a href="#" class="btn btn-xs blue" onclick="view(<%=lbean5.getAd_voucher_id()%>)">View</a></td>
                
            </tr>
            
            <%
            i4++;
			}
		}
            %>
            
            </tbody>
		</table>	
	</div>
	<!-- ------------------------------------------------------------------- -->
	<div class="tab-pane fade" id="tab5">
		<table id="dataTable5" class="table table-striped table-bordered table-hover">
		<thead>
            <tr>
                <th>Sno</th>
                <th>Trx no</th>
                <th>Trx Date</th>
                <th>VoucherType</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody align="center">
        <%
        ArrayList<VoucherBean> lst5 =new VoucherDao().getAllVoucherByType("new_fd");
       
		int i5=1;
		if(lst5!=null){
			for(VoucherBean bean5:lst5){
        
        %>
            <tr>
                <td><%=i5 %></td>
                <td><%=bean5.getTrx_no() %></td>
                <td><%=bean5.getTrx_date() %></td>
                <td><%=bean5.getVoucher_type() %></td>
                <td><%=bean5.getVamt() %></td>
                <td><%=bean5.getStatus() %></td>
                <td><a href="#" class="btn btn-xs blue" onclick="view(<%=bean5.getAd_voucher_id()%>)">View</a></td>
                
            </tr>
            
            <%
            i5++;
			}
		}
		
        ArrayList<VoucherBean> lstf6 =new VoucherDao().getAllVoucherByType("renew_fd");
       
		
		if(lstf6!=null){
			for(VoucherBean beanf6:lstf6){
        
        %>
            <tr>
                <td><%=i5 %></td>
                <td><%=beanf6.getTrx_no() %></td>
                <td><%=beanf6.getTrx_date() %></td>
                <td><%=beanf6.getVoucher_type() %></td>
                <td><%=beanf6.getVamt() %></td>
                <td><%=beanf6.getStatus() %></td>
                <td><a href="#" class="btn btn-xs blue" onclick="view(<%=beanf6.getAd_voucher_id()%>)">View</a></td>
                
            </tr>
            
            <%
            i5++;
			}
		}
            
             ArrayList<VoucherBean> lstrf6 =new VoucherDao().getAllVoucherByType("rediption_fd");
       
		
		if(lstrf6!=null){
			for(VoucherBean beanrf6:lstrf6){
        
        %>
            <tr>
                <td><%=i5 %></td>
                <td><%=beanrf6.getTrx_no() %></td>
                <td><%=beanrf6.getTrx_date() %></td>
                <td><%=beanrf6.getVoucher_type() %></td>
                <td><%=beanrf6.getVamt() %></td>
                <td><%=beanrf6.getStatus() %></td>
                <td><a href="#" class="btn btn-xs blue" onclick="view(<%=beanrf6.getAd_voucher_id()%>)">View</a></td>
                
            </tr>
            
            <%
            i5++;
			}
		}
            %>
            </tbody>
		</table>	
	</div>
	<!-- ------------------------------------------------------------------- -->
	<div class="tab-pane fade" id="tab6">
		<table id="dataTable6" class="table table-striped table-bordered table-hover">
		<thead>
            <tr>
                <th>Sno</th>
                <th>Trx no</th>
                <th>Trx Date</th>
                <th>VoucherType</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody align="center">
        <%
        ArrayList<VoucherBean> lst6 =new VoucherDao().getAllVoucherByType("final_pay");
       
		int i6=1;
		if(lst6!=null){
			for(VoucherBean bean6:lst6){
        
        %>
            <tr>
                <td><%=i6 %></td>
                <td><%=bean6.getTrx_no() %></td>
                <td><%=bean6.getTrx_date() %></td>
                <td><%=bean6.getVoucher_type() %></td>
                <td><%=bean6.getVamt() %></td>
                <td><%=bean6.getStatus() %></td>
                <td><a href="#" class="btn btn-xs blue" onclick="view(<%=bean6.getAd_voucher_id()%>)">View</a></td>
                
            </tr>
            
            <%
            i6++;
			}
		}
            %>
            </tbody>
		</table>	
	</div>
	<!-- ------------------------------------------------------------------- -->
	<div class="tab-pane fade" id="tab7">
		<table id="dataTable7" class="table table-striped table-bordered table-hover">
		<thead>
            <tr>
                <th>Sno</th>
                <th>Trx no</th>
                <th>Trx Date</th>
                 <th>VoucherType</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
       
            <tbody>
        <%
        ArrayList<VoucherBean> plst =new VoucherDao().getAllVoucherByType("Payroll");
       
		int j=1;
		if(plst!=null){
			for(VoucherBean bean:plst){
        
        %>
            <tr>
                <td><%=j %></td>
                <td><%=bean.getTrx_no() %></td>
                <td><%=bean.getTrx_date() %></td>
                <td><%=bean.getVoucher_type() %></td>
                <td><%=bean.getVamt() %></td>
                <td><%=bean.getStatus() %></td>
                <td><a href="#" class="btn btn-xs blue" onclick="view(<%=bean.getAd_voucher_id()%>)">View</a></td>
            </tr>
            
            <%
            j++;
			}
		}
            %>
            </tbody>
		</table>	
	 </div>
	<!-- ------------------------------------------------------------------- -->
	<div class="tab-pane fade" id="tab8">
		<table id="dataTable8" class="table table-striped table-bordered table-hover">
		<thead>
            <tr>
                <th>Sno</th>
                <th>Trx no</th>
                <th>Trx Date</th>
                 <th>VoucherType</th>
                <th>Amount</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
       
            <tbody align="center">
        <%
        ArrayList<VoucherBean> dlst =new VoucherDao().getAllVoucherByType("divident_interest");
       
		j=1;
		if(dlst!=null){
			for(VoucherBean bean:dlst){
        
        %>
        
        
    
            <tr>
                <td><%=j %></td>
                <td><%=bean.getTrx_no() %></td>
                <td><%=bean.getTrx_date() %></td>
                <td><%=bean.getVoucher_type() %></td>
                <td><%=bean.getVamt() %></td>
                <td><%=bean.getStatus() %></td>
                <td><a href="#" class="btn btn-xs blue" onclick="view(<%=bean.getAd_voucher_id()%>)">View</a></td>
                
            </tr>
            
            <%
            j++;
			}
		}
            %>
             <%
        ArrayList<VoucherBean> tlst =new VoucherDao().getAllVoucherByType("thrift_interest");
		if(tlst!=null){
			for(VoucherBean bean:tlst){
        
        %>
            <tr>
                <td><%=j %></td>
                <td><%=bean.getTrx_no() %></td>
                <td><%=bean.getTrx_date() %></td>
                <td><%=bean.getVoucher_type() %></td>
                <td><%=bean.getVamt() %></td>
                <td><%=bean.getStatus() %></td>
                <td><a href="#" class="btn btn-xs blue" onclick="view(<%=bean.getAd_voucher_id()%>)">View</a></td>
            </tr>
            
            <%
            j++;
			}
		   }
            %>
            </tbody>
		</table>	
	 </div>
	<!-- ------------------------------------------------------------------- -->
	</div><!-- End tab content -->
	<!-- --------------------------------------------------------------------------- -->
	<div class="clearfix"></div>
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
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript">
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	jQuery('#dataTable2').DataTable();
	jQuery('#dataTable3').DataTable();
	jQuery('#dataTable4').DataTable();
	jQuery('#dataTable5').DataTable();
	jQuery('#dataTable6').DataTable();
	jQuery('#dataTable7').DataTable();
	jQuery('#dataTable8').DataTable();
});
</script>
<script type="text/javascript">
function view(ad_voucher_id){
	 var popup;
popup=	window.open ("view_voucher.jsp?ad_voucher_id="+ad_voucher_id, "mywindow","location=1,status=1,scrollbars=1, width=1000,height=500, left=150,top=100");
popup.focus();
}
function viewshare(ad_voucher_id){
	 var popup;
popup=	window.open ("view_sharevoucher.jsp?ad_voucher_id="+ad_voucher_id, "mywindow","location=1,status=1,scrollbars=1, width=1000,height=500, left=150,top=100");
popup.focus();
}

</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>