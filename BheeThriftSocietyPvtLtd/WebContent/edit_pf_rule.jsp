

<%@page import="Model.Bean.BankRegionBean"%>
<%@page import="Model.Dao.BankRegionDao"%>
<%@page import="Model.Bean.StateBean"%>
<%@page import="Model.Dao.StateDao"%>
<%@page import="Model.Bean.BankBean"%>
<%@page import="Model.Dao.BankDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>

<link rel="stylesheet" href="css/styles.css"></link>
<link rel="stylesheet" href="css/mainstyle.css"></link>
<link rel="stylesheet" href="00/chosen.css">
<script src="TreeMenu/TreeMenu.js" type="text/javascript"></script>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
<head>
		<title>Central Bank of India</title>
</head>
<script type="text/javascript">
$(document).ready(function(e) {

});	
</script>




	
<body class="   ext-gecko ext-gecko3">
	
<form  action="AdPFRule?action=insert" method="post" >
	
	
		
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
			
				<table  cellpadding="5"  width="90%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="9" class="" align="center" style="font-weight: bold;" >PF Rules</td>
					</tr>
					<tr>
					<td colspan="9" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr >
						<td>&nbsp;Year</td>
						<td class="" >:</td>
						<td>	<select  name="year" id="year" style="width: 170px;" data-placeholder="Choose a Bank..." class="chosen-select" style="width:250px;" tabindex="2">
																		<option value="0">--Select--</option>
																		<option>2016-17</option>
																		<option>2017-18</option>
																		<option>2018-19</option>
																		<option>2019-20</option>
																		<option>2020-21</option>
																		<option>2021-22</option>
																		<option>2022-23</option>
																		<option>2023-24</option>
																		<option>2024-25</option>
																		<option>2025-26</option>
																		<option>2026-27</option>
																		<option>2027-28</option>
																		<option>2028-29</option>
																		<option>2029-30</option>
																		<option>2030-31</option>
																		<option>2031-32</option>
																		<option>2032-33</option>
																		<option>2033-34</option>
																		<option>2034-35</option>
																		<option>2035-36</option>
																		<option>2036-37</option>
																		<option>2037-38</option>
																		<option>2038-39</option>
																		<option>2039-40</option>


																</select>
						</td>
						
					
						
					
						
						
					</tr>
					
					<tr >
						<td>&nbsp;EPF Employee Share</td>
						<td class="" >:</td>
						<td>	<input type="text" name="epf_emp_share" id="epf_emp_share" style="width: 170px;">
								 
						</td>
						
					
						
					
						<td>&nbsp;EPF Employer Share</td>
						<td class="" >:</td>
						<td>	<input type="text" name="epf_employer_share" id="epf_employer_share" style="width: 170px;">
								 
						</td>
						
					</tr>
					<tr>
							 
						<td>&nbsp;EPS Employer Share</td>
						<td class="" >:</td>
						<td>	<input type="text" name="eps_employer_share" id="eps_employer_share" style="width: 170px;">
						</td>
							
						<td class="" >&nbsp;EDLI Charges</td>
						<td class="" >:</td>
						<td>	<input type="text" name="edli_charges" id="edli_charges" style="width: 170px;">
						</td>
					</tr>
					<tr>	
					
						<td>&nbsp;EPF Admin Charges</td>
						<td class="" >:</td>
						<td>	<input type="text" name="epf_admin_charges" id="epf_admin_charges" style="width: 170px;">
							 
						</td>
						<td>&nbsp;EDLI Admin Charges</td>
						<td class="" >:</td>
						<td>	<input type="text" name="edli_admin_charges" id="edli_admin_charges" style="width: 170px;">
							 
						</td>
					</tr>
					
					<tr>
					<td colspan="9" align="center">
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
	
				
			</tbody>
			</table>
		</td>
 </tr></tbody></table>
 
 
 			</td>
 		</tr>
 	</tbody>
 </table>



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


