<%@page import="Model.Bean.BankRegionBean"%>
<%@page import="Model.Dao.BankRegionDao"%>
<%@page import="Model.Bean.StateBean"%>
<%@page import="Model.Dao.StateDao"%>
<%@page import="Model.Bean.BankBean"%>
<%@page import="Model.Dao.BankDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%@include file= "validation.html"%>
<link rel="stylesheet" href="css/styles.css"></link>
<link rel="stylesheet" href="css/mainstyle.css"></link>
<link rel="stylesheet" href="00/chosen.css">
<script src="TreeMenu/TreeMenu.js" type="text/javascript"></script>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
<head>
		<title>Central Bank</title>
</head>
<script>
function genSalary(){
	var month=document.getElementById("month").value;
	if($("#month").val()==''){
	alert("Please Select Month");
	}else{
		$.ajax({
			   type: "POST",
			   url: "AdSalary?action=generate_list&month="+month,
			   async:false,
			   success: function(data)
			   { 
				   document.getElementById('sal').innerHTML=data;
			       
		  	} });
	}
	
}

function delSalary(empid){
	var month=document.getElementById("month").value;
	if($("#month").val()==''){
	alert("Please Select Month");
	}else{
		$.ajax({
			   type: "POST",
			   url: "AdSalary?action=delete&emp_id="+empid+"&month="+month,
			   async:false,
			   success: function(data)
			   { 
				   alert("Record Deleted Successfully..!!");
				   genSalary();
			       
		  	} });
	}
	
}
function cal(i){
	
	var wkdays=parseInt($("#wkdays_"+i).val());	
	var holiday=parseInt($("#holidays_"+i).val());
	$("#wkgdays_"+i).val(wkdays-holiday);
	
	var wkgdays=parseInt($("#wkgdays_"+i).val());
	var absent=parseInt($("#absent_"+i).val());
	$("#payble_"+i).val((wkgdays+holiday)-absent);	
	var absent=$("#absent_"+i).val();
	
	var monthly_sal=parseInt($("#monthly_sal_"+i).val());
	var oneday=(monthly_sal/wkdays);
	var pdays=parseInt($("#payble_"+i).val());
	var t_sal=oneday*pdays;
	var t1_sal=t_sal-(parseInt($("#basic_"+i).val())+parseInt($("#da_"+i).val()));
	
	var phra=parseInt($("#phra_"+i).val());
	var hra= Math.round(((t1_sal*phra)/100));
	$("#hra_"+i).val(hra);
	
	var pconv=parseInt($("#pconv_"+i).val());
	var conv= Math.round(((t1_sal*pconv)/100));
	$("#conv_"+i).val(conv);
	
	
	var pmed=parseInt($("#pmed_"+i).val());
	var med= Math.round(((t1_sal*pmed)/100));
	$("#med_"+i).val(med);
	
	var pallow=parseInt($("#pallow_"+i).val());	
	var allow= Math.round(((t1_sal*pallow)/100));
	$("#allow_"+i).val(allow);
	
	var other=parseInt($("#other_"+i).val());
	var basic=parseInt($("#basic_"+i).val());
	var da=parseInt($("#da_"+i).val());
	
	var gross=(basic+da+hra+conv+med+allow+other);
	
	$("#gross_"+i).val(gross);
	
	var pf=parseInt($("#pf_"+i).val());
	var pt=parseInt($("#pt_"+i).val());
	var esi=parseInt($("#esi_"+i).val());
	var tds=parseInt($("#tds_"+i).val());
	var other_ded=parseInt($("#otherdeduction_"+i).val());
	
	var gross_ded=pf+pt+esi+tds+other_ded;
	$("#grossdeduction_"+i).val(gross_ded);
	
	var new_sal=(gross-gross_ded);
	$("#netsal_"+i).val(new_sal);
	
	}
</script>




	
<body class="   ext-gecko ext-gecko3">
	
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
			<table height="100%" border="0" cellpadding="0" cellspacing="0" width="100%">
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
			
				
					
					
					<input type="hidden" id="cmonth" value=/>
<table border="1" cellspacing="0" cellpadding="10" 
			style="border-radius:8px;box-shadow:3px 3px 3px black; margin-top: 50px; border: 1px solid black; width: 90%; text-align: left;background-color: white;"
			align="center">
			<tr><td colspan="3"><h2 align="center" style="color: maroon;" >Generate Salary</h2></td></tr>
 <tr>
 <td colspan="3">Select Month: &nbsp;&nbsp;&nbsp;&nbsp;<input type="month" name="month" id="month"  >&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Submit" onclick="genSalary()" style="width: 70px; height: 30px;"></td>
 </tr>
 </table> 
 </td>
 </tr>
 <tr>
 <td colspan="7" >
 <form action="AdSalary?action=update" method="post">
 <div id="sal" style="overflow-x: scroll;"></div>
 </form>
 </td>
 </tr>				
	<tr>
	<td colspan="7" class=""  >&nbsp;</td>
	</tr>
	              
	            </table>	
	            	
			</td>
		</tr> 
	
				
			</tbody>
			</table>
		</td>
 </tr></tbody></table>
 
 
 			



<!-- footer-->
 

<table id="tblContainer" border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-top: 5%">
	<tbody><tr>
		<td class="footerText" height="25" valign="middle">©&nbsp;Copyright Syphon Tech</td>
		
		<td class="footerText" height="25" valign="middle"><div align="right"><a href="#" onClick="window.open('/sbijava/Privacy_AccountGroupment.html','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')">Syphon</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" onclick="window.open('www.syphon.co.in','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')"> </div></td>
		
		
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


