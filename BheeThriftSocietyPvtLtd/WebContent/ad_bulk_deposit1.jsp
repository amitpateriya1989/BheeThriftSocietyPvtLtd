

<%@page import="Model.Bean.MemberRegistrationBean"%>
<%@page import="Model.Dao.MemberRegistrationDao"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<link rel="stylesheet" href="css/styles.css"></link>
<link rel="stylesheet" href="css/mainstyle.css"></link>

<link rel="stylesheet" href="00/chosen.css">
<script src="TreeMenu/TreeMenu.js" type="text/javascript"></script>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function(e){
	$("#ad_branch_id").change(function(e){
		
		var url="getbulkmember.jsp?ad_branch_id="+$("#ad_branch_id").val();
		$('#bulk_frame').attr('src', url);
	});
	
	$("#ad_member_id").change(function(e){
		var url="getbulkmemberbyid.jsp?ad_member_id="+$("#ad_member_id").val();
		$('#bulk_frame').attr('src', url);
		
	});
	
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
		 <tr valign="top">
		 <td>
		 	<table height="100%" border="0" cellpadding="0" cellspacing="0" width="100%" style="border:1px solid black">
		 	<tbody>
		 	<tr valign="top" style="height:10px;">
		 	<td colspan="6" style="background: #581845;">
		 	&nbsp;
		 	</td>
		 	
		 	</tr>
		 	<tr valign="top" style="height:10px;">
		 	<td colspan="6" style="background:;">
		 	
		 	&nbsp;
		 	</td>
		 	
		 	</tr>
		 	<tr valign="top" style="height:10px;">
				<td colspan="6" class=""  ><hr style="color: black;" /></td>
			</tr>
		 	
		 		<tr valign="top" style="height:20px;">
		 			<td>Branch</td><td>:</td><td>
		 			<select name="ad_branch_id" id="ad_branch_id" data-placeholder="Choose a Bank..." class="chosen-select" style="width:250px;" tabindex="2"><option>--select--</option>
		 			<%BankBranchDao dao =new BankBranchDao();
								 	ArrayList<BankBranchBean> alist=dao.getAllBankBranchName();
								 	if(alist!=null){
								 	for(BankBranchBean bean:alist){%>
								 <option value="<%=bean.getAd_branch_id()%>"> <%=bean.getBranch_code() %> <%=bean.getBranch() %></option>
								 <%} 
								 }%>
											 			
		 			</select>		
		 			</td>
		 		
		 			<td>Member</td><td>:</td><td>
		 			<select name="ad_member_id" id="ad_member_id" data-placeholder="Choose a Bank..." class="chosen-select" style="width:250px;" tabindex="2"><option>--select--</option>
		 			<%MemberRegistrationDao mdao =new MemberRegistrationDao();
								 	ArrayList<MemberRegistrationBean> malist=mdao.getAllMemberlist();
								 	if(malist!=null){
								 	for(MemberRegistrationBean bean:malist){%>
								 <option value="<%=bean.getAd_member_id()%>"><%=bean.getName() %></option>
								 <%} 
								 }%>
											 			
		 			</select>		
		 			</td>
		 		</tr>
		 	<tr valign="top">
		 	<td colspan="9">
		 	<iframe name="bulk_frame" id="bulk_frame" src="" frameborder="0" scrolling="yes" style="width: 100%; height:100%; border: none">
		 	
		 	
		 	
		 	</iframe>
		 	
		 	</td>
		 	
		 	</tr>
		 	
		 	
		 	</tbody>
		 	
		 	</table>
		 
		 </td>
		 </tr>
		 
			</tbody>
			</table>
 			</td>
 		</tr>
 	</tbody>
 </table>



<!-- footer-->
 


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
   
</script>  