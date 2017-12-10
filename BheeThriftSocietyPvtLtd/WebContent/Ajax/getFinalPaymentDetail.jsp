<%@page import="Model.Bean.FinalPayDetailBean"%>
<%@page import="Model.Dao.FinalPayDao"%>
<%@page import="Model.Bean.JournalLedgerBean"%>
<%@page import="Model.Bean.VoucherTrxDetailBean"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="Model.Bean.LedgerAccountBean"%>
<%@page import="Model.Dao.LedgerAccountDao"%>
<%@page import="Model.Bean.VoucherBean"%>
<%@page import="Model.Dao.VoucherDao"%>
<%@page import="Model.Bean.TransactionBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Dao.TransactionDao"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%
String str_from=request.getParameter("from");
String str_to=request.getParameter("to");
DateFormat formatter ; 
Date from =null; 
Date to =null; 
formatter = new SimpleDateFormat("dd/MM/yyyy");
try {
from = formatter.parse(str_from);
to = formatter.parse(str_to);
} catch (ParseException e) {
	e.printStackTrace();
}
System.out.println(from+"__"+to);
%>

<table id="final_pay_detail" name="final_pay_detail" border="1"  width="100%" style="border:0px solid black;background-image:url(images/tab_background.png); margin-top:0px;margin-bottom:0px; font-family:'Times New Roman', Times, serif;" align="center">
			
			<tr style="font-size: 14px;">
				<th>S.No</th>
				<th >Date</th>
				<th>Mem No.</th>
				<th>Member</th>
				<th>Reason</th>
				<th>RDate</th>
				<th>FDate</th>
				<th>Thift</th>
				<th>Thift Int</th>
				<th>Share</th>
				<th>Compensation</th>
				
			</tr>
					
<%int i=0;
Double total_deb=0.0;
Double total_cre=0.0;
FinalPayDao vtd=new FinalPayDao();
ArrayList<FinalPayDetailBean> vtb=vtd.getAllFinalPayDetail("Approved",from,to);
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
for(FinalPayDetailBean b:vtb){
i++;

%>
			<tr style="font-size: 12px;">
			
		
			<td ><%=i %></td>
			<td ><%=new SimpleDateFormat("dd/MM/yyyy").format(b.getTrx_date()) %></td>
			
			<td ><%=b.getAd_society_no() %></td>
			<td ><%=b.getName() %></td>
			<td ><%=b.getPay_reson() %></td>
			<td ><%=sdf.format(b.getReson_date()) %></td>
			<td ><%=sdf.format(b.getFinal_pay_date()) %></td>
			<td><%=b.getFdgs_amt() %></td>
			<td ><%=b.getInt_fdgs() %></td>
			<td ><%=b.getShare_amt() %></td>
			<td ><%=b.getCompensation_amt() %></td>
			
			
			</tr>
<%//} 
}%>	
	
			</table>
			