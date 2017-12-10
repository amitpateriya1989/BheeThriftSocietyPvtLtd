<%@page import="Model.Bean.CityBean"%>
<%@page import="Model.Dao.CityDao"%>
<%@page import="Model.Bean.DistrictBean"%>
<%@page import="Model.Dao.DistrictDao"%>
<%@page import="Model.Bean.StateBean"%>
<%@page import="Model.Dao.StateDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>

<link rel="stylesheet" href="css/styles.css"></link>
<link rel="stylesheet" href="css/mainstyle.css"></link>
<script src="TreeMenu/TreeMenu.js" type="text/javascript"></script>
<script src="js/jquery-1.8.3.js" type="text/javascript"></script>
<head>
		<title>Central Bank of India</title>
</head>
<script type="text/javascript">
$(document).ready(function(e) {
$("#ad_state_id").change(function(e) {
	
		if($(this).val()==0)
		{
			alert("Please Select State......!!");
			 //$('#subgroup_name').html(data); 
			return false;
			
			
		}
		else
		{
        		var ad_state_id=$(this).val();
        		//alert(group_id);
		$.ajax({
			   type: "POST",
			   url: "Ajax/read_district_by_state_id.jsp?ad_state_id="+ad_state_id,
			   async:false,
			   success: function(data)
			   {	
				   $('#ad_district_id').html(data);  			  				
		  	} }); 
		}
		
	});

	
   
});	
</script>



<%
try{
							String city_id=request.getParameter("ad_city_id");
							if(city_id!=null){
							int ad_city_id=0;	
								try{
								
									ad_city_id=Integer.parseInt(city_id);
									
									
								}catch(NumberFormatException n){
									n.printStackTrace();
								}
								
								CityDao citydao=new CityDao();
								CityBean bean=citydao.getCityById(ad_city_id);
								if(bean!=null){
								
							
%>
	
<body class="   ext-gecko ext-gecko3">
	
<form  action="AdCity?action=edit&ad_city_id=<%=bean.getAd_city_id()%>" method="post" >
	
	
		
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
			
				<table  cellpadding="5"  width="40%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="3" class="" align="center" style="font-weight: bold;" >City</td>
					</tr>
					<tr>
					<td colspan="3" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr >
							
					
					
						<td class="" >&nbsp;State</td>
						<td class="" >:</td>
						<td>	<select  name="ad_state_id" id="ad_state_id" style="width: 170px;">
								<option value="0"></option>
								<%StateDao dao=new StateDao();
								  ArrayList <StateBean> alist=dao.getAllState();
								  if(alist!=null){
								  for(StateBean sbean:alist){%>
								  <option value="<%=sbean.getAd_state_id()%>"><%=sbean.getState() %></option>
									  
								 <%} }%>
						</select>
						<script type="text/javascript">
						$(document).ready(function(e){
							$("#ad_state_id").val(<%=bean.getAd_state_id()%>);
							$("#ad_district_id").val(<%=bean.getAd_district_id()%>);
						});
						
						
						</script>
						</td>
						
					</tr>
					<tr >
						<td>&nbsp;District</td>
						<td class="" >:</td>
						<td>	<select  name="ad_district_id" id="ad_district_id" style="width: 170px;">
								 <option value="0">--Select--</option>
								 <%DistrictDao districtdao=new DistrictDao();
								  ArrayList <DistrictBean> dlist=districtdao.getAllDistrictByStateId(bean.getAd_state_id());
								  if(dlist!=null){
								  for(DistrictBean sbean:dlist){%>
								  <option value="<%=sbean.getAd_district_id()%>"><%=sbean.getDistrict() %></option>
									  
								 <%} }%>
								</select>
						</td>
						
					</tr>
					<tr >
						<td class="" >&nbsp;City</td>
						<td class="" >:</td>
						<td>	<input type="text" name="city" value="<%=bean.getCity()%>"/>
								
						</td>
						<%	
								}
								
							} %>
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
	</tbody></table>
 
 
 			</td>
 		</tr>
 	</tbody>
 </table>



<!-- footer-->
 

<table id="tblContainer" border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-top: 5%">
	<tbody><tr>
		<td class="footerText" height="25" valign="middle">©&nbsp;Copyright Syphon Tech</td>
		
		<td class="footerText" height="25" valign="middle"><div align="right"><a href="#" onClick="window.open('/sbijava/Privacy_Statement.html','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')">Syphon</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" onclick="window.open('www.syphon.co.in','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')"> </div></td>
		
		
	</tr> 
</tbody>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</table>
</body>


