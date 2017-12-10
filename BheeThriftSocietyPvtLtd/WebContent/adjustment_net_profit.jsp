
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<link rel="stylesheet" href="css/styles.css"></link>
<link rel="stylesheet" href="css/mainstyle.css"></link>


<script src="TreeMenu/TreeMenu.js" type="text/javascript"></script>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
<%try{ %>
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

<table height="100%" border="0" cellpadding="0" cellspacing="0" width="100%" style="border:1px solid black">
	<tbody>
		 <form  action="AdVoucher?action=insertnetprofit" method="post" id="form">
		<tr> 	
			<td colspan="2">
			<table  cellpadding="5"  width="40%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="3" class="" align="center" style="font-weight: bold;" >Adjustments to Net Profit</td>
					</tr>
					<tr>
					<td colspan="3" class=""  ><hr style="color: black;" /></td>
					</tr>
					
					<tr >
						<td class="" >&nbsp;Balance Amt</td>
						<td class="" >:</td>
						<td>	<input type="text" name="bal_amt" id="bal_amt"/>
						</td>
						
					</tr>
					<tr >
						<td class="" >&nbsp;A/c Head</td>
						<td class="" >:</td>
						<td>	<select name="ad_account_id" id="ad_account_id" ><option value="0">--select--</option>
<%LedgerAccountDao ldao=new LedgerAccountDao();
    				ArrayList<LedgerAccountBean> ledgerList=ldao.getAllLedgerAccount();
    				if(ledgerList!=null){
    				for(LedgerAccountBean bean1:ledgerList){
    				%>
    				 <option value="<%=bean1.getAd_account_id()%>"><%=bean1.getAc_name() %></option>	
    				    
    				<%} }%>


</select> 
						</td>
						
					</tr>
					<tr >
						<td class="" >&nbsp;Balance Type </td>
						<td class="" >:</td>
						<td>	<select name="bal_type" id="bal_type"><option value="0">--Select--</option><option value="Cr Bal">Cr Bal</option><option value="Dr Bal">Dr Bal</option></select>
						</td>
						
					</tr>
					<tr >
						<td class="" >&nbsp;Narration </td>
						<td class="" >:</td>
						<td>	<input type="text" name="narration" id="narration"/>
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
	            
				
			</td>
		</tr> 
		</form>
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
</tbody>
</table>
</body>
<%}catch(Exception e){
	e.printStackTrace();
} %>
