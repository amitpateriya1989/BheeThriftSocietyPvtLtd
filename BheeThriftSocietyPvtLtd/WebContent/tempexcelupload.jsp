
<%@page import="Model.Dao.BulkExcelFileDao"%>
<%@page import="Model.Bean.BulkExcelFileBean"%>
<%@page import="Model.Dao.LoanTrxDao"%>
<%@page import="Model.Bean.LoanTrxBean"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<link rel="stylesheet" href="css/styles.css"></link>
<link rel="stylesheet" href="css/mainstyle.css"></link>


<script src="TreeMenu/TreeMenu.js" type="text/javascript"></script>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>




<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
<script type="text/javascript">


</script>

<head>
		<title>State Bank of India</title>
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
<form action="TempExcelUpload?action=upload" method="post" enctype="multipart/form-data" id="form">
<table height="100%" border="0" cellpadding="0" cellspacing="0" width="100%" style="border:1px solid black">
	<tbody>
	<tr>
	<td colspan="6"><HR/></td>
	</tr>
	
	<tr valign="top"> 
		 <td  > 
			Select Excel File</td><td>:</td><td><input type="file" name="file" id="file"></td>
			<td colspan="2" ><input type="submit"  value="submit"></td>	
			</td>	
 		</tr>
 		<tr valign="top" align="center"> 
		 <td  colspan="6"> 
			
 		</tr>
 		<tr><td colspan="6"><HR/></td>
 		</tr>
 		<tr>
 		

</td>
</tr >
</table>
 			
 	</form>	
 		
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
