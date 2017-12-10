<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>

<link rel="stylesheet" href="css/styles.css"></link>
<link rel="stylesheet" href="css/mainstyle.css"></link>

<script src="TreeMenu/TreeMenu.js" type="text/javascript"></script>

<head>
		<title>State Bank of India</title>
</head>
	
<body class="   ext-gecko ext-gecko3">
	
<form name="executefavoritesForm" method="post">
	
	
		
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
		

<table id="tblContainer" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody><tr>
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
		<tr> 	
			<td colspan="2"> 
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>
					<tr>
					<td colspan="7" class="maincurvecls" scope="col"  >&nbsp;</td>
					</tr>
					
					<tr >
					
					</tr>						
					<tr>
						<td colspan="2" style="" align="center">&nbsp;</td>
					</tr>
					<tr>
					<td colspan="7" class="maincurvecls" scope="col"  >&nbsp;</td>
					</tr>
	              </tbody>
	            </table>		
			</td>
		</tr> 
	<tr> 
		<td colspan="2" scope="row" class="pageContentBG" align="left" valign="top"> 
			<table id="tblDeposit" align="center" border="0" cellpadding="0" cellspacing="0" width="98%">
				<tbody>
				<tr>
					
				</tr> 
			</tbody>
			</table>
		</td>
 </tr></tbody></table>
 
 
 			</td>
 		</tr>
 	</tbody>
 </table>



<!-- footer-->
 

<table id="tblContainer" border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-top: 30%">
	<tbody><tr>
		<td class="footerText" height="25" valign="middle">©&nbsp;Copyright Syphon Tech</td>
		
		<td class="footerText" height="25" valign="middle"><div align="right"><a href="#" onClick="window.open('/sbijava/Privacy_Statement.html','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')">Syphon</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" onclick="window.open('www.syphon.co.in','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')"> </div></td>
		
		
	</tr> 
</tbody></table>
</body>