<%@page import="Model.Dao.FdTrxDao"%>
<%@page import="Model.Bean.FdTrxBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%
MemberLoginBean user1=null;
user1=(MemberLoginBean)session.getAttribute("MemberLoginBean"); %>

<link rel="stylesheet" href="../css/styles.css"></link>
<link rel="stylesheet" href="../css/mainstyle.css"></link>

<script src="../js/jquery-1.8.3.js" type="text/javascript"></script>
<script type="text/javascript">

$(document).ready(function(e){
	
	var url="getmemberdivident.jsp";
	$('#loan_window').attr('src', url);	

	
});

</script>

<head>
		<title>State Bank of India</title>
</head>
	
<body >
	

	
	
		
<!--Main Table Starts Here-->
<table id="tblContainer" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
	<tr>
		<td>
		        <%@include file= "member_hdr_menu.jsp"%> 
      
		</td>  
	</tr>
	<tr><td colspan="2" height="5"></td></tr>
	</tbody>
</table>
		

<table id="tblContainer" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody><tr>
		<td>
		
		<table>
		<tr>
		<%-- <td>Loan No</td><td>:</td><td><select id="loanno"><option value="<%=0%>">--select--</option>
		<%
		ArrayList<FdTrxBean> list = new FdTrxDao().getAllFdNoByMemId(user1.getMember().getAd_member_id()) ;
		if(list.isEmpty()!=true){
			
			for(FdTrxBean tb:list){
				%>
					<option value="<%=tb.getFd_no()%>"><%=tb.getFd_no() %></option>
				<%
			}
			
		}
		%>
		
		
		</select></td>
		<td></td><td>:</td><td><input type="button" id="go" value="Go"/></td> --%>
		<td></td><td>:</td><td><input type="button" onclick="prnts()" id="print" value="Print"/></td>
		<td></td><td>:</td><td><input onclick="tableToExcel('fd', 'DIVIDENT')" type="button" id="exportex" value="Export In Excel"/></td>
		</tr>
		</table>
		<table width="100%">
		<tr>
		<td >
		
		<iframe id="loan_window" style="width: 100%; min-height:50vh; border: none" src="">
		 
		 
		 
		 
		 </iframe>
		
		</td>
		</tr>
		
		
		
		</table>
		
		
		
		</td>
		
		
		
		
</tr>

              
<!-- footer-->
<tr>
<table id="tblContainer" border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-top: 30%">
	<tbody><tr>
		<td class="footerText" height="25" valign="middle">©&nbsp;Copyright Syphon Tech</td>
		
		<td class="footerText" height="25" valign="middle"><div align="right"><a href="#" onClick="window.open('/sbijava/Privacy_Statement.html','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')">Syphon</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" onclick="window.open('www.syphon.co.in','aboutus','width=780, height=500, status=1, scrollbars=1, location=0')"> </div></td>
		
		
	</tr> 
</tbody>
</table>
</tr>
</tbody>
</table>
</body>

<script type="text/javascript">
   
    function prnts()
    {
    
    var divElements = document.getElementById('fd_window').contentWindow.document.body.innerHTML;
    	 var printWindow = window.open("", "_blank", "");            
            printWindow.document.open();          
            printWindow.document.write('<html><body><center> <h3 style="margin-bottom:0px;margin-top:0px"> State Bank Of India Adhikari Sahakari Sakh Samiti </h3><h4 style="margin-bottom:0px;margin-top:0px">A Multi State Co-operative Society<br/></h4></center>');
          
    		printWindow.document.write(divElements);
            printWindow.document.write('</body></html>');
            printWindow.document.close();
            printWindow.focus();
    		 setTimeout(function() {
                printWindow.print();
                printWindow.close();
            }, 100);
    }
</script>   

<script type="text/javascript">
var tableToExcel = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><center> <h3 style="margin-bottom:0px;margin-top:0px"> State Bank Of India Adhikari Sahakari Sakh Samiti </h3><h4 style="margin-bottom:0px;margin-top:0px">A Multi State Co-operative Society<br/></h4></center><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById('fd_window').contentDocument.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
</script>