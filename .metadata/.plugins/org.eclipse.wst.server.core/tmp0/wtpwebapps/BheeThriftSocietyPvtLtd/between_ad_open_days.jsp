<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="Model.Bean.SalutationBean"%>
<%@page import="Model.Dao.SalutationDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%@page  import ="java.util.Date" %>
<%@page  import ="java.util.GregorianCalendar" %>
<%@page  import ="java.text.SimpleDateFormat" %>
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

<head>
		<title>Central Bank of India</title>
</head>
	
<body class="">
	
<form  action="AdDayOpen?action=insertbetween" method="post" id="form">
	
	
		
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
					<td colspan="6" class="" align="center" style="font-weight: bold;" >Opening Days </td>
					</tr>
					<tr>
					<td colspan="6" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<th>Date</th>
					<th>Remark</th>
					</tr>
					<%
					String fdate=request.getParameter("closedate");
					String tdate=request.getParameter("copndate");
					
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date start = sdf.parse(fdate);
					Date end = sdf.parse(tdate);

					GregorianCalendar gcal = new GregorianCalendar();
					gcal.setTime(start);
int i=0;
					while (!gcal.getTime().after(end)) {
					    Date d = gcal.getTime();
					    String od =null;
					    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						   od = df.format(d);
					    if(d.equals(start)){
					    	
					    %>
					    
					    <tr><td><input type='date' name="closed" value='<%=od%>' style="width: 200px" readonly="readonly"/></td>
					    <td>Last Closed Day</td></tr>
					    <% 
					    }else if(d.equals(end)){
						    %>
						    
						    <tr><td><input name='open_days' type='date' value='<%=od%>' style="width: 200px" readonly="readonly"/></td>
						    <td><select><option>OPEN</option></select></td></tr>
						    <% 
						  }else{
							  i++;
							  
							   
							   
							  %>
							    
							    <tr><td><input type="date" name='between_<%=i %>'  value='<%=od%>' style="width: 200px" readonly="readonly"/></td>
							    <td><input type="text" name='remark_<%=i %>' required="required" ></td></tr>
							    <% 
							  
							  
							  
						  }
					    
					  //  System.out.println(d);
					    gcal.add(Calendar.DATE, 1);
					}
					
					%>
					<input type="hidden" value="<%=i %>" name="counter" />
					<tr>
					<td colspan="6" class=""  ><hr style="color: black;" /></td>
					</tr>
					<tr>
					<td colspan="6" align="center">
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
			
  <div id="" style="width: 70%" align="center"></div>
 <table id="" align="center" border="1" cellpadding="0" cellspacing="0" width="60%" style="margin-bottom: 3%;">
				<tbody>
				
			
			</tbody>
			</table>
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
