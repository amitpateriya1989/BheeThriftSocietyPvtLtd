
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<link rel="stylesheet" href="css/styles.css"></link>
<link rel="stylesheet" href="css/mainstyle.css"></link>


<script src="TreeMenu/TreeMenu.js" type="text/javascript"></script>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
<!-- Include one of jTable styles. -->
<link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" type="text/css" />
<!-- Include jTable script file. -->
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>
<script type="text/javascript">
function showBalance(){
	var url="getbalancesheet.jsp?fdate="+$("#datefrom").val()+"&tdate="+$("#dateto").val();
	$('#balance_window').attr('src', url);
}
function prnts()
{
	
	var divElements = document.getElementById('balance_window').contentWindow.document.body.innerHTML;
	 var printWindow = window.open("", "_blank", "");            
        printWindow.document.open();          
        printWindow.document.write('<html><body><center> <h3> State Bank Of India Adhikari Sahakari Sakh Samiti </h3><h4>A Multi State Co-operative Society<br/></h4></center>');
      
		printWindow.document.write(divElements);
        printWindow.document.write('</body></html>');
        printWindow.document.close();
       printWindow.focus();       
       printWindow.print();
        printWindow.close();
       
}

</script>

<%
try{
 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 		String from = null;
 		String to = null;
 		try {
 			Date date = (Date)session.getAttribute("openday");
 			
 			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
 			
 			Calendar cal = Calendar.getInstance();
 		    cal.setTime(date);
 		    int year = cal.get(Calendar.YEAR);
 		    
 			Date dt=sdf.parse(year+"-03-31");
 			
 			
 			if(date.after(dt)){
 			
 				Date fdate = sdf.parse(year+"-04-01");
 				from=sdf.format(fdate).toString();
 				
 				
 			}else{
 				Date tdate = sdf.parse((year-1)+"-04-01");
 				from=sdf.format(tdate).toString();
 				
 			}
 			
 			to= sdf.format(date).toString();
 			
 			
 			
 		} catch (IllegalArgumentException i) {
 			i.printStackTrace();
 		}
 	%>


<head>
		<title>State Bank of India</title>
</head>
	
<body class="">
	
<form  action="AdSalutation?action=insert" method="post" id="form">
	
	
		
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
<table height="100%" border="0" cellpadding="0" cellspacing="0" width="100%" style="border:1px solid black">
	<tbody>
		 
		<tr valign="top"> 	
			<td colspan="4">
			
			<table border="1" cellspacing="0" cellpadding="10" 
			style="border-radius:3px; margin-top: 50px; border: 1px solid black; width: 95%; text-align: left;background-image:url(images/tab_background.png);"
			align="center">
			<tr>
			<td>
				<table width="100%">
			<tr><td width="80%">
			<h2 align="center" style="color: maroon;" > BALANCE SHEET</h2></td>
			
			<td>
			<a href="#"><img src="Image/excel-icon.png" height="30" width="48" /></a>
			<a href="#"><img src="Image/word-icon.png" height="30" width="48" /></a>
			<a onclick="prnts()" href="#"><img  src="Image/print-icon.png" height="30" width="48" /></a>
			</td>
			</tr>
			</table>
			
			
			
			</td></tr>
 <tr>
 <td >Date From: &nbsp;&nbsp;&nbsp;&nbsp;<input type="date" name="datefrom" id="datefrom" value="<%=from %>">&nbsp;&nbsp;&nbsp;&nbsp;
  Date To: &nbsp;&nbsp;&nbsp;&nbsp;<input type="date" name="dateto" id="dateto" value="<%=to %>" >&nbsp;&nbsp;&nbsp;&nbsp;
 <input type="button" value="Submit" onclick="showBalance()"></td>
 
 </tr>
		
		
	<tr valign="top"> 
		 <td colspan="2" scope="row" class="" align="center" > 
		 
		<iframe id="balance_window" style="width: 100%;min-height: 50vh; border: none" src="">
		 
		 
		 </iframe>
		 
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
</tbody>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</table>
</body>
