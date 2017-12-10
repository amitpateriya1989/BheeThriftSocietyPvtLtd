

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
$('#PendingVoucherTableContainer').jtable({
title: 'Salutation',
paging: true, //Enable paging
pageSize: 10, //Set page size (default: 10)
sorting: true, //Enable sorting
defaultSorting: 'Name ASC', //Set default sorting
actions: {
listAction: 'AdVoucher?action=pendingVoucherlist',
updateAction: 'AdVoucher?action=edit',
type: 'POST'
},
fields: {
ad_voucher_id: {
title: 'ID',
key: true,
list: true
},
trx_date: {
title: 'Date',
width: '20%'
},
vtype: {
title: 'Mode',
width: '20%'
},
vtype: {
title: 'Type',
width: '20%',
create: false,
edit: false
},
vamt: {
title: 'Amount',
width: '20%',

}
,
description: {
title: 'For',
width: '20%',

},
status:{
	title: 'Status',
	width: '20%',
	options: { 'Pending': 'Pending', 'Approved': 'Approved' }
	}
}
});
$('#PendingVoucherTableContainer').jtable('load');
});
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
		

<table id="tblContainer"  width="100%">
	<tbody><tr>
		<td> 
			<table height="100%" border="0" cellpadding="0" cellspacing="0" width="100%" style="min-height: 75vh;">
				<tbody><tr>
					<td valign="top" width="15%">
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
                <td colspan="2" height="100%" valign="top" width="85%">


<!-- Body Starts Here -->
<table height="100%"  width="100%" style="border:1px solid black">
	<tbody>
		 
		<tr> 	
			<td colspan="2">
			
				<form action="" method="post">
			<table  cellpadding="5"  width="90%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >Voucher Aprrovals</td>
					</tr>
					<tr>
					<td colspan="9" class=""  >
					 <div id="PendingVoucherTableContainer" style="width: 100%" align="center">
					 </div></td>
					</tr>
					
	              
	            </table>	
			</form>

			</td>
		</tr> 
	</tbody>
 </table>



<!-- footer-->
 


	<tbody><tr>
		<td class="footerText" height="25" valign="middle">©&nbsp;Copyright Syphon Tech</td>
		
		<td class="footerText" height="25" valign="middle"><div align="right"><a href="#" onClick="window.open('/sbijava/Privacy_Statement.html','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')">Syphon</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="#" onclick="window.open('www.syphon.co.in','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')"></a> </div></td>
		
		
	</tr> 
</tbody></table>
	
</body>
<script src="00/chosen.jquery.js" type="text/javascript"></script>
<script src="00/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
</script>  
