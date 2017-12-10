<%@page import="Model.Bean.SalutationBean"%>
<%@page import="Model.Dao.SalutationDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>

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
$(document).ready(function () {
$('#SalutationTableContainer').jtable({
title: 'Salutation',
paging: true, //Enable paging
pageSize: 10, //Set page size (default: 10)
sorting: true, //Enable sorting
defaultSorting: 'Name ASC', //Set default sorting
actions: {
listAction: 'AdSalutation?action=list',
type: 'POST'
},
fields: {
ad_salutation_id: {
title: 'ID',
key: true,
list: true
},
name: {
title: 'Admin Loan',
width: '20%'
},
updatedby: {
title: 'updatedby',
width: '20%'
},
created: {
title: 'created',
width: '20%',
create: false,
edit: false
},
createdby: {
title: 'createdby',
width: '20%',

}

}
});
$('#SalutationTableContainer').jtable('load');
});
</script>
<script type="text/javascript">
$(function(){
	var V_Trx_No = $("#V_Trx_No").val();
	
	if(V_Trx_No.trim()!=""){
		$('.custom-messageBox').modal('show');
		$('#custom-page-message').html("Transaction number is "+V_Trx_No);
	}
	
});
</script>
<head>
		<title>Central Bank Employees Co.Op. Credit Society</title>
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
			
				<table border="0" cellpadding="5" cellspacing="0" width="40%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="3" class="" align="center" style="font-weight: bold;" >Type OF Loan</td>
					</tr>
					<tr>
					<td colspan="3" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr >
						<td class="" >&nbsp;Salutation</td>
						<td class="" >:</td>
						<td>	<input type="text" name="name"/>
						</td>
						
					</tr>
					
					<tr>
					<td colspan="3" align="center">
						<input type="submit" name="Submit" value="submit"/>&nbsp;&nbsp;
						<input type="reset" name="Clear"/>
					</td>
					</tr>						
					
					<tr>
					<td colspan="7" class=""  >&nbsp;</td>
					</tr>
	              
	            </table>	
	            </form>	
			</td>
		</tr> 
	<tr> 
		 <td colspan="2" scope="row" class="" align="center" valign="top"> 
			
  <div id="SalutationTableContainer" style="width: 70%" align="center"></div>
 
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
