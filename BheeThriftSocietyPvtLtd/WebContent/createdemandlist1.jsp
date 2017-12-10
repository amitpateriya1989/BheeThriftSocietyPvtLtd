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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<link rel="stylesheet" href="css/styles.css"></link>
<link rel="stylesheet" href="css/mainstyle.css"></link>
<script src="TreeMenu/TreeMenu.js" type="text/javascript"></script>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>

<script type="text/javascript">

</script>

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
		

<table id="tblContainer" border="0" cellpadding="0" cellspacing="0" width="100%" >
	<tbody><tr valign="top">
		<td> 
			<table height="100%" border="0" cellpadding="0" cellspacing="0" width="100%" style="min-height: 75vh;">
				<tbody><tr>
					<td valign="top" width="18%">
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
                <td colspan="2" height="100%" valign="top" width="82%">

<!-- Body Starts Here -->
<form  action="SendMail?action=demandlist" method="post" id="form">
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



for(String ad_branch_id:branch){
	
	try {
				
		if(ad_branch_id.equals("0")){
			
			ArrayList<BankBranchBean> blist = new BankBranchDao().getAllBankBranchName();
			
			if(blist.isEmpty()!=true){
				for(BankBranchBean bbean:blist){
					OutputStream file = new FileOutputStream(new File("D:\\"+month+"Branchid"+bbean.getAd_branch_id()+".pdf"));	
					
					Document document = new Document();
					PdfWriter.getInstance(document, file);
					document.open();
					
					 Font ffont = new Font(Font.FontFamily.UNDEFINED, 14, Font.ITALIC);			
					 Phrase content = new Phrase("State Bank Of India Adhikari Sahakari Sakh Samiti", ffont);		
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
					out.print("</td><td> <a target='_blank' href='D://Test.pdf' > view </a>");
					out.print("</td></tr>");
					
					document.close();
					file.close();
					
					
					
				}
				
			}
			

			
			
		}else{
		
		OutputStream file = new FileOutputStream(new File("D:\\"+month+"Branchid"+ad_branch_id+".pdf"));	
		
		Document document = new Document();
		PdfWriter.getInstance(document, file);
		document.open();
		
		 Font ffont = new Font(Font.FontFamily.UNDEFINED, 14, Font.ITALIC);			
		 Phrase content = new Phrase("State Bank Of India Adhikari Sahakari Sakh Samiti", ffont);		
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
		out.print("</td><td> <a target='_blank' href='D://Test.pdf' > view </a>");
		out.print("</td></tr>");
		
		document.close();
		file.close();
	}
	} catch (Exception e) {

		e.printStackTrace();
	}

	
}
}else if(find_demand_list!=null){
	String []branch =request.getParameterValues("ad_branch_id");
	String month = request.getParameter("month");
	
	
	for(String ad_branch_id:branch){
		
		BankBranchBean bean = new BankBranchDao().getBankBranchById(Integer.parseInt(ad_branch_id));
		int i=0;
		i++;
		if(ad_branch_id.equals("0")){
			
			ArrayList<BankBranchBean> blist = new BankBranchDao().getAllBankBranchName();
			
			if(blist.isEmpty()!=true){
				
				for(BankBranchBean bbean:blist){
					File f = new File("D:\\"+month+"Branchid"+ad_branch_id+".pdf");
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
		
		File f = new File("D:\\"+month+"Branchid"+ad_branch_id+".pdf");
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
%>
				
				
	</table>		
	<br>
	<hr>	
<center > <input type="submit" value="Send Mail" > </center>
</td>
</tr>
</tbody>
</form>
</table>
</td>
</tr>
</tbody>

 </table>



<!-- footer-->
 

<!-- <table id="tblContainer" border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-top: 30%"> -->
	<tbody><tr>
		<td class="footerText" height="25" valign="middle">©&nbsp;Copyright Syphon Tech</td>
		
		<td class="footerText" height="25" valign="middle"><div align="right"><a href="#" onClick="window.open('/sbijava/Privacy_Statement.html','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')">Syphon</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" onclick="window.open('www.syphon.co.in','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')"> </div></td>
		
		
	</tr> 
</tbody></table>
</body>

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