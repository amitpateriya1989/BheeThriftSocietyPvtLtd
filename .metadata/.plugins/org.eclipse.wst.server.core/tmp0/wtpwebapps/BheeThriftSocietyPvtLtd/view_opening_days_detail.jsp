<%@page import="java.util.Date"%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="Model.Bean.DayOpenBean"%>
<%@page import="Model.Dao.DayOpenDao"%>
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


</script>

<head>
		<title>State Bank of India</title>
</head>
	
<body class="">
	
<form  action="view_opening_days_detail.jsp" method="post" id="form">
	
	
		
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
			
				<table border="0" cellpadding="5" cellspacing="0" width="100%" style="border: 1px solid black;margin-top: 3%;margin-bottom: 3%" align="center">
					
					<tr>
					<td colspan="6" class="" align="center" style="font-weight: bold;" >Opening Days Detail From <%=request.getParameter("fdate")%> TO <%=request.getParameter("tdate")%> </td>
					</tr>
					<tr>
					<td colspan="6" class=""  ><hr style="color: black;" /></td>
					</tr>
				<tr>
					<td colspan="6" class=""  >
					
					<div id="divcon">
					
					<table id="" align="center" border="1" cellpadding="0" cellspacing="0" width="60%" style="margin-bottom: 3%;">
				<tbody>
				<tr>
					
					<th id="">
						Sno.
					</th>
					<th id="">
						Date
					</th>
					<th id="">
						Opening By
					</th>
					<th id="">
						Opening Status
					</th>
					<th id="">
						Closing Status
					</th>
					<th id="">
						Closing By
					</th>
					<th id="">
						Remark
					</th>
					
				</tr>
				<%
				
				try{
				DayOpenDao dao=new DayOpenDao();
				
				String fdate=request.getParameter("fdate");
				String tdate=request.getParameter("tdate");
				
				DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
				Date fdt = df1.parse(fdate);
				Date tdt = df1.parse(tdate);
				
				
					ArrayList<DayOpenBean> slist=dao.getAllDayOpen(fdt, tdt);
					int i=0;
					if(slist!=null){
						for(DayOpenBean bean:slist){
							
					
				%> 
				<tr>
					
					<td id="">
						<%=++i %>
					</td>
					<td id="">
						<%=bean.getOpen_days()%>
					</td>
					<td id="">
						<%=bean.getCreatedby()%>
					</td>
					<td id="">
						<%
						if(bean.getOpening_status()!=true){
							out.print("NOT OPEN");
						}else{
							out.print("OPEN");
						}
						
						
						
						%>
						<td id="">
						<%
						if(bean.getClosing_status()!=true){
							out.print("NOT CLOSED");
						}else{
							out.print("CLOSED");
						}
						
						 %>
						
					</td>
					<td id="">
						<%=bean.getUpdatedby() %>
					</td>
					<td id="">
						<%if(bean.getRemark()!=null){
						out.print(bean.getRemark());
						}
						
						%>
					</td>
					</td>
					
				</tr> 
				<%	}
					}
					 %>
			</tbody>
			</table>
					
					
					</</div>
					
					</td>
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
<%}catch(Exception e){
	e.printStackTrace();
} %>
</body>
