
<%@page import="Model.Bean.EmployeeBean"%>
<%@page import="Model.Dao.EmployeeDao"%>
<link rel="stylesheet" href="css/styles.css"></link>
<link rel="stylesheet" href="css/mainstyle.css"></link>
<link rel="stylesheet" href="00/chosen.css">

<script src="TreeMenu/TreeMenu.js" type="text/javascript"></script>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
<script type="text/javascript">

$(document).ready(function(e){
	$("#go").click(function(e){
		
	var rt=	$("#reporttype").val();
	var empid=	$("#ad_employee_id").val();
	var month=	$("#month").val();
	
	if(rt=="PT"){
		var url="getempptdetail.jsp?month="+month+"&ad_employee_id="+empid;
		$('#report_window').attr('src', url);
		
	}else if(rt=="PF"){
		var url="getemppfdetail.jsp?month="+month+"&ad_employee_id="+empid;
		$('#report_window').attr('src', url);
		
	}else if(rt=="Salary"){
		
		var url="getempsalarydetail.jsp?month="+month+"&ad_employee_id="+empid;
		$('#report_window').attr('src', url);
	}
		
	});
	
	
});

</script>

<head>
		<title>Central Bank</title>
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
		 
		<tr> 	
			<td colspan="9" align="center">
			
			Employees Report	
			</td>
			<td colspan="" align="right">
			<a href="#"><img src="Image/excel-icon.png" height="48" width="48" /></a>
			<a href="#"><img src="Image/word-icon.png" height="48" width="48" /></a>
			<a onclick="prnts()" href="#"><img src="Image/print-icon.png" height="48" width="48" /></a>
			</td>
		</tr> <tr>
		<td colspan="10" align="center">
			
			<hr>
			</td>
			<tr> 	
			<td > Report Type </td><td>:</td><td><select name="reporttype" id="reporttype"><option>PT</option><option>PF</option><option>Salary</option></select></td>
			<td > Month </td><td>:</td><td><input id="month" name="month" type="month"/></td>
			<td > Employee </td><td>:</td><td><select  name="ad_employee_id" id="ad_employee_id" style="width: 350px;" data-placeholder="Select" class="chosen-select" style="width:170px;" tabindex="2">
								 <option value="All">--All Employess--</option>
								 
								 <%EmployeeDao dao=new EmployeeDao();
								 	ArrayList<EmployeeBean> alist=dao.getAllEmployee();
								 	System.out.println(dao.getAllEmployee());
								 	for(EmployeeBean bean:alist){%>
								 <option value="<%=bean.getAd_employee_id()%>"><%=bean.getEmployee_id()%> | <%=bean.getName() %></option>
								 <%} %>
								</select>	  </td>
								
								<td> <input type="button" onclick="report_window_url()" name="go" id="go" Value="GO"  /></td>
								
		</tr> 
		<tr> 	
			<td colspan="10" align="center">
			
			<hr>
			</td>
		</tr> 
		
	<tr> 
		 <td colspan="10" scope="row" class="" align="center" valign="top"> 
			
  <iframe id="report_window" style="width: 100%;min-height: 50vh; border: none" src="">
		 
		 
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
   function form_submit(){
    
    document.getElementById("frm1").submit();
    }

   
   function prnts()
   {
   	
   	var divElements = document.getElementById('report_window').contentWindow.document.body.innerHTML;
   	 var printWindow = window.open("", "_blank", "");            
           printWindow.document.open();          
           printWindow.document.write('<html><body><center> <h3> Central Bank Employees Co-Operative Credit Society Ltd. Bhopal </h3><h4>AR/BPL/57<br/></h4></center>');
         
   		printWindow.document.write(divElements);
           printWindow.document.write('</body></html>');
           printWindow.document.close();
          printWindow.focus();       
          printWindow.print();
           printWindow.close();
          
   }
</script>   