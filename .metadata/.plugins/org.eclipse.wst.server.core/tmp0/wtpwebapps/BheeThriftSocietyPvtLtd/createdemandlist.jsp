<%@page import="Model.Bean.SalutationBean"%>
<%@page import="Model.Dao.SalutationDao"%>
<%@page import="java.util.Arrays"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="Model.Bean.LoanTrxBean"%>
<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Dao.MemberRegistrationMasterDao"%>
<%@page import="Model.Bean.MemberRegistrationMasterBean"%>
<%@page import="com.itextpdf.text.pdf.PdfPCell"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="com.itextpdf.text.Phrase"%>
<%@page import="com.itextpdf.text.Font"%>
<%@page import="java.util.Date"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.Document"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>

<%@page import="java.io.File"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStream"%>
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
				<a href="#">Payroll</a><i class="fa fa-angle-right"></i>
			</li>
			<li><a href="#">Demand List</a><i class="fa fa-angle-right"></i>Add and View</li>
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
	   <div class="caption">View DemandList</div>
	   <div class="tools"> <a href="javascript:;" class="collapse"> </a>
	   </div>
	   </div>
	   <div class="portlet-body">
	    <form id="frmSalutation" action="SendMail?action=demandlist" method="post" autocomplete="off">
			<table height="100%" border="0" cellpadding="0" cellspacing="0" width="100%" style="border:1px solid black">
	<tbody>
		 
		<tr valign="top"> 	
			<th colspan="6" align="center" height="50px">
			
			 Demand List
			
			</th>
			</tr>
			<tr valign="top">
			<td>
			
			<table width="100%" border="1" cellpadding="0" cellspacing="0">
			
<tr>
<td>Sno</td><td><input type="checkbox" name="selectall" id="selectall"/>Select All</td><td>Branch Code</td> <td>Branch Name </td><td>Email Address</td> <td>View</td>
</tr>
<%
String genrate_demand_list =request.getParameter("genrate_demand_list");
String find_demand_list =request.getParameter("find_demand_list");

if(genrate_demand_list!=null){	

String []branch =request.getParameterValues("ad_branch_id");
String month = request.getParameter("month");


if(branch!=null){
for(String ad_branch_id:branch){
	
	try {
				
		if(ad_branch_id.equals("0")){
			
			ArrayList<BankBranchBean> blist = new BankBranchDao().getAllBankBranchName();
			
			if(blist.isEmpty()!=true){
				for(BankBranchBean bbean:blist){
					File filepath=new File("D://"+month+"_Branchid_"+bbean.getAd_branch_id()+".pdf");
					
					//filepath.getParentFile().mkdir();
					
					OutputStream file = new FileOutputStream(filepath);	
					
					Document document = new Document();
					PdfWriter.getInstance(document, file);
					document.open();
					
					 Font ffont = new Font(Font.FontFamily.UNDEFINED, 14, Font.ITALIC);			
					 Phrase content = new Phrase("Central Bank Employees Co-Operative Credit Society Ltd. Bhopal", ffont);		
					 document.add(new Paragraph(content));
					
					 ffont = new Font(Font.FontFamily.UNDEFINED, 10, Font.ITALIC);		
					 content = new Phrase("A Multi State Co­operative Society", ffont);		 
					 document.add(new Paragraph(content));		 
				
					 document.add(new Paragraph("-------------------------------------------------------------------------------------------------------------------------------"));
					
					ffont = new Font(Font.FontFamily.UNDEFINED, 8, Font.ITALIC);		
					content = new Phrase("MONTHLY DEMOND LIST FOR THE MONTH OF "+month, ffont);
					document.add(new Paragraph(content));
					 
						
					content = new Phrase("SEND TO,", ffont);
					document.add(new Paragraph(content));
					 
					content = new Phrase("THE MANAGER", ffont);
					document.add(new Paragraph(content));
					
					
					BankBranchBean bean = new BankBranchDao().getBankBranchById(bbean.getAd_branch_id());
					
					
					content = new Phrase("BRANCH:"+bean.getBranch(), ffont);		
					document.add(new Paragraph(content));
					content = new Phrase("DIST:"+bean.getDistrict().getDistrict(), ffont);
					document.add(new Paragraph(content));
					
					content = new Phrase("BRANCH CODE:"+bean.getBranch_code(), ffont);
					document.add(new Paragraph(content));
					 
						PdfPTable table = new PdfPTable(7);	      
				        PdfPCell cell;	       
				        table.addCell("Sno");
				        table.addCell("PF No");	  
				        cell = new PdfPCell(new Phrase("Member Name"));
				        cell.setColspan(2);
				        table.addCell(cell);
				        table.addCell("Thrift");
				        table.addCell("Loan");
				        table.addCell("Total Amt");
				        
				        MemberRegistrationMasterBean  mrmbean = new  MemberRegistrationMasterDao().getMemberRegistrationMasterByMaxId();
				        
				        double thrift=0.00;
				        if(mrmbean.getFgds_fund()!=0.00){
				        	thrift=mrmbean.getFgds_fund();
				        }
				        
				        int i=0;

				        MemberRegistrationDao mrdao =new MemberRegistrationDao();
				        ArrayList<MemberRegistrationBean> mralist=mrdao.getMemberByBranch(bbean.getAd_branch_id());
				      	if(mralist!=null){
				        	i=0;
				        	for(MemberRegistrationBean mbean:mralist){
				        	i++;
				        	
				        	table.addCell(""+i);
					        table.addCell(""+mbean.getAd_pf_no());	  
					        cell = new PdfPCell(new Phrase(""+mbean.getName()));
					        cell.setColspan(2);
					        table.addCell(cell);
					        table.addCell(""+thrift);
					        LoanTrxBean ltbean = new LoanTrxDao().getLoanTrxById1(mbean.getAd_member_id());			 	
						 	double loan=0.00;
						 	if(ltbean.getEmi()!=null){loan=ltbean.getEmi(); }
					        table.addCell(""+loan);
					        table.addCell(""+(loan+thrift));
				        	
				       		}
				        }
				        
				        document.add(table);
				        ffont = new Font(Font.FontFamily.UNDEFINED, 14, Font.ITALIC);	
					content = new Phrase("1. Please retransmit the duplicate copy certifying there on that the amounts noted above have been duly recoverd. The perticulars of non-recovery, if any and the reason there of should be communicated.", ffont);
					document.add(new Paragraph(content));
					content = new Phrase("2. If one has been transferred, please advice the branch to which the officer is transferred to recover", ffont);
					document.add(new Paragraph(content));
					content = new Phrase("3. Any change in demand list must be forword to regional office for updation in HRMS.", ffont);
					document.add(new Paragraph(content));
					content = new Phrase("Note:- Please do not change any amount in the list without consulting the society.", ffont);
					document.add(new Paragraph(content));		
					content = new Phrase("Account number: 123456 ,", ffont);
					document.add(new Paragraph(content));	
					
					out.print("<tr><td>"+i);
					out.print("</td><td><input type=checkbox class=checkbox name=bank id=bank value="+bean.getEmail_id()+">");
					out.print("</td><td>"+bean.getBranch_code());
					out.print("</td><td>"+bean.getBranch());
					out.print("</td><td>"+bean.getEmail_id());
					out.print("</td><td> <a target='_blank' href='D:/DemandList/' > view </a>");
					out.print("</td></tr>");
					
					document.close();
					file.close();
					
					
					
				}
				
			}
			

			
			
		}else{
		
		OutputStream file = new FileOutputStream(new File("D:/"+month+"_Branchid_"+ad_branch_id+".pdf"));	
		
		Document document = new Document();
		PdfWriter.getInstance(document, file);
		document.open();
		
		 Font ffont = new Font(Font.FontFamily.UNDEFINED, 14, Font.ITALIC);			
		 Phrase content = new Phrase("Central Bank Employees Co-Operative Credit Society Ltd. Bhopal", ffont);		
		 document.add(new Paragraph(content));
		
		 ffont = new Font(Font.FontFamily.UNDEFINED, 10, Font.ITALIC);		
		 content = new Phrase("A Multi State Co­operative Society", ffont);		 
		 document.add(new Paragraph(content));		 
	
		 document.add(new Paragraph("-------------------------------------------------------------------------------------------------------------------------------"));
		
		ffont = new Font(Font.FontFamily.UNDEFINED, 8, Font.ITALIC);		
		content = new Phrase("MONTHLY DEMOND LIST FOR THE MONTH OF "+month, ffont);
		document.add(new Paragraph(content));
		 
			
		content = new Phrase("SEND TO,", ffont);
		document.add(new Paragraph(content));
		 
		content = new Phrase("THE MANAGER", ffont);
		document.add(new Paragraph(content));
		
		
		BankBranchBean bean = new BankBranchDao().getBankBranchById(Integer.parseInt(ad_branch_id));
		
		
		content = new Phrase("BRANCH:"+bean.getBranch(), ffont);		
		document.add(new Paragraph(content));
		content = new Phrase("DIST:"+bean.getDistrict().getDistrict(), ffont);
		document.add(new Paragraph(content));
		
		content = new Phrase("BRANCH CODE:"+bean.getBranch_code(), ffont);
		document.add(new Paragraph(content));
		 
			PdfPTable table = new PdfPTable(7);	      
	        PdfPCell cell;	       
	        table.addCell("Sno");
	        table.addCell("PF No");	  
	        cell = new PdfPCell(new Phrase("Member Name"));
	        cell.setColspan(2);
	        table.addCell(cell);
	        table.addCell("Thrift");
	        table.addCell("Loan");
	        table.addCell("Total Amt");
	        
	        MemberRegistrationMasterBean  mrmbean = new  MemberRegistrationMasterDao().getMemberRegistrationMasterByMaxId();
	        
	        double thrift=0.00;
	        if(mrmbean.getFgds_fund()!=0.00){
	        	thrift=mrmbean.getFgds_fund();
	        }
	        
	        int i=0;

	        MemberRegistrationDao mrdao =new MemberRegistrationDao();
	        ArrayList<MemberRegistrationBean> mralist=mrdao.getMemberByBranch(Integer.parseInt(ad_branch_id));
	      	if(mralist!=null){
	        	i=0;
	        	for(MemberRegistrationBean mbean:mralist){
	        	i++;
	        	
	        	table.addCell(""+i);
		        table.addCell(""+mbean.getAd_pf_no());	  
		        cell = new PdfPCell(new Phrase(""+mbean.getName()));
		        cell.setColspan(2);
		        table.addCell(cell);
		        table.addCell(""+thrift);
		        LoanTrxBean ltbean = new LoanTrxDao().getLoanTrxById1(mbean.getAd_member_id());			 	
			 	double loan=0.00;
			 	if(ltbean.getEmi()!=null){loan=ltbean.getEmi(); }
		        table.addCell(""+loan);
		        table.addCell(""+(loan+thrift));
	        	
	       		}
	        }
	        
	        document.add(table);
	        ffont = new Font(Font.FontFamily.UNDEFINED, 14, Font.ITALIC);	
		content = new Phrase("1. Please retransmit the duplicate copy certifying there on that the amounts noted above have been duly recoverd. The perticulars of non-recovery, if any and the reason there of should be communicated.", ffont);
		document.add(new Paragraph(content));
		content = new Phrase("2. If one has been transferred, please advice the branch to which the officer is transferred to recover", ffont);
		document.add(new Paragraph(content));
		content = new Phrase("3. Any change in demand list must be forword to regional office for updation in HRMS.", ffont);
		document.add(new Paragraph(content));
		content = new Phrase("Note:- Please do not change any amount in the list without consulting the society.", ffont);
		document.add(new Paragraph(content));		
		content = new Phrase("Account number: 123456 ,", ffont);
		document.add(new Paragraph(content));	
		out.print("<tr><td>"+i);
		out.print("</td><td><input type=checkbox class=checkbox name=bank id=bank value="+bean.getEmail_id()+">");
		out.print("</td><td>"+bean.getBranch_code());
		out.print("</td><td>"+bean.getBranch());
		out.print("</td><td>"+bean.getEmail_id());
		out.print("</td><td> <a target='_blank' href='D://' > view </a>");
		out.print("</td></tr>");
		
		document.close();
		file.close();
	}
	} catch (Exception e) {

		e.printStackTrace();
	}

	
}
}
}else if(find_demand_list!=null){
	String []branch =request.getParameterValues("ad_branch_id");
	String month = request.getParameter("month");
	
	if(branch!=null){
	for(String ad_branch_id:branch){
		
		BankBranchBean bean = new BankBranchDao().getBankBranchById(Integer.parseInt(ad_branch_id));
		int i=0;
		i++;
		if(ad_branch_id.equals("0")){
			
			ArrayList<BankBranchBean> blist = new BankBranchDao().getAllBankBranchName();
			
			if(blist.isEmpty()!=true){
				
				for(BankBranchBean bbean:blist){
					File f = new File("D:/DemandList/",month+"_Branchid_"+bbean.getAd_branch_id()+".pdf");
					if(f.exists() && !f.isDirectory()) { 
						out.print("<tr><td>"+i);
						out.print("</td><td><input type=checkbox class=checkbox name=bank id=bank value="+bean.getEmail_id()+">");
						out.print("</td><td>"+bean.getBranch_code());
						out.print("</td><td>"+bean.getBranch());
						out.print("</td><td>"+bean.getEmail_id());
						out.print("</td><td> <a target='_blank' href='D://Test.pdf' > view </a>");
						out.print("</td></tr>");
					
				}
				
				
			}
			
			
			}
			
			
			
		}else{
		
		File f = new File("D:/DemandList/",month+"_Branchid_"+bean.getAd_branch_id()+".pdf");
		if(f.exists() && !f.isDirectory()) { 
			out.print("<tr><td>"+i);
			out.print("</td><td><input type=checkbox class=checkbox name=bank id=bank value="+bean.getEmail_id()+">");
			out.print("</td><td>"+bean.getBranch_code());
			out.print("</td><td>"+bean.getBranch());
			out.print("</td><td>"+bean.getEmail_id());
			out.print("</td><td> <a target='_blank' href='D://Test.pdf' > view </a>");
			out.print("</td></tr>");
		}
		}	
	}
	
	}
}
%>
				
				
	</table>		
	<br>
	<hr>	
<center > <input type="submit" value="Send Mail" > </center>
</td>
</tr>
</tbody>
		 </form>	
	    </div><!-- End portlet-body -->
	</div>
	
	
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
<%-- <%@ include file= "Layout/footer.jsp" %> --%>
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript">
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	
	jQuery.validator.addMethod("Alphanumspacedot", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z.\s]*$/.test(value);
		}, "Please enter latter and space only.");
	
	jQuery( "#frmSalutation" ).validate({
		  rules: {
			  name: {
		      required: true,
		      Alphanumspacedot:true,
		      maxlength:8
		    },
		      getAd_gender_id : {
		      required: true,
		    }
		  }
		});
});
</script>
<script type="text/javascript">
$(document).ready(function(e) {  
	
	
	$('#selectall').click(function(event) {  //on click 
 alert();
        if(this.checked) { // check select status
            $('.checkbox').each(function() { //loop through each checkbox
                this.checked = true;  //select all checkboxes with class "checkbox1"   
				       
            });
        }else{
            $('.checkbox').each(function() { //loop through each checkbox
                this.checked = false; //deselect all checkboxes with class "checkbox1"                       
            });         
        }
		
		
    });
});
	
 
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
</html>