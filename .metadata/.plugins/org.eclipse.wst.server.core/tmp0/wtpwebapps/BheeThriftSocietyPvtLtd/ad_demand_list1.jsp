<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<link rel="stylesheet" href="css/styles.css"></link>
<link rel="stylesheet" href="css/mainstyle.css"></link>
<script src="TreeMenu/TreeMenu.js" type="text/javascript"></script>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
<link rel="stylesheet" href="00/chosen.css">
<script type="text/javascript">

</script>

<head>
		<title>State Bank of India</title>
</head>
	
<body class="">
	
<form  action="createdemandlist.jsp" method="post" id="form">
	
	
		
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
		
			<td colspan="2">
			<table width="100%" style="margin-top:20px" cellpadding="3" cellspacing="3">
			<tr align="center">
			<td colspan="3">Demand List </td>
			
			</tr>
			<tr>
			<td colspan="3"><hr/></td>
			
			</tr>
			<tr >
			<td>Month</td><td>:</td><td><input type="month" name="month" id="month" value="<%=new SimpleDateFormat("yyyy-MM").format(session.getAttribute("openday")) %>" /></td>
		</tr>
		<tr>
			<td>Branch</td><td>:</td><td><select name="ad_branch_id" id="ad_branch_id" multiple="multiple" data-placeholder="Choose a Branch..." class="chosen-select" style="width:100%;" tabindex="2"><option value="0">--Select All Branch--</option>
		 			<%BankBranchDao dao =new BankBranchDao();
								 	ArrayList<BankBranchBean> alist=dao.getAllBankBranchName();
								 	if(alist!=null){
								 	for(BankBranchBean bean:alist){%>
								 <option value="<%=bean.getAd_branch_id()%>"> <%=bean.getBranch_code() %> <%=bean.getBranch() %></option>
								 <%} 
								 }%>
											 			
		 			</select>		</td>
			
			</tr>
			<tr>
			<td colspan="3"><hr/></td>
			
			</tr>
			<tr align="center">
			<td colspan="3">
			<input type="submit" value="Genrate Demand List"  name="genrate_demand_list" id="genrate_demand_list" />
			<input type="submit" value="Find Demand List"  name="find_demand_list" id="find_demand_list" />
			</td>
			
			</tr>
			<tr>
			<td colspan="3"><hr/></td>
			
			</tr>
			</table>
				
			</td>
		</tr> 
	<tr> 
		 <td colspan="2" scope="row" class="" align="center" valign="top"> 
			
  <div id="" style="width: 70%" align="center"></div>
 
			
			
			
			
			
			
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
</td>
</tr>
</tbody>
</table>
</form>

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