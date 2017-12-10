<%@page import="Model.Bean.VoucherTrxDetailBean"%>
<%@page import="Model.Bean.BankBean"%>
<%@page import="Model.Dao.BankDao"%>
<%@page import="Model.Bean.VoucherBean"%>
<%@page import="Model.Dao.VoucherDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Model.Bean.UserBean"%>
     <%@page import="Model.Bean.TransactionBean"%>
      <%@page import="Model.Dao.TransactionDao"%>
      
      <link rel="stylesheet" href="css/styles.css"></link>
<link rel="stylesheet" href="css/mainstyle.css"></link>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
try{
int ad_voucher_id=0;
UserBean user=null;
try{
	 ad_voucher_id = Integer.parseInt(request.getParameter("ad_voucher_id"));
	if(request.getSession(false).getAttribute("userbean")==null){
	response.sendRedirect("logout.jsp");
	}
}catch(Exception e){
	e.printStackTrace();
}
	
//ArrayList<TransactionBean> lst =new TransactionDao().getTransactionByType(ad_voucher_id);  	
ArrayList<VoucherTrxDetailBean> lst=new VoucherDao().getAllVoucherTrxDetail(ad_voucher_id);
VoucherBean vbean = new VoucherDao().getVoucherByVoucherId(ad_voucher_id);

%>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="AdVoucher?action=printchk&ad_voucher_id=<%=request.getParameter("ad_voucher_id") %>" method="post">

<table id="tblContainer"   border="0" cellpadding="0" cellspacing="5" width="100%">

<tr>
					<td colspan="6" class=""  ><hr style="color: black;" /></td>
					</tr>

<tr>
<td >To</td><td>:</td><td colspan="4"><input style="width:300px" type="text" id="name" name="name"/></td>
</tr>

<tr>
<td>Trx NO</td><td>:</td><td><%=vbean.getTrx_no() %></td>
<td>Trx Date</td><td>:</td><td><%=vbean.getTrx_date() %></td>
</tr>
<tr>
<td>Trx Type</td><td>:</td><td><select  name="vtype" id="vtype" style="width: 170px;" data-placeholder="" class="chosen-select" style="width:250px;" tabindex="2">
									<option value="0">---Select---</option>
									<option value="Adjustment">Adjustment</option>
									<option value="Cheque">Cheque</option>
									<option value="DD">Demand Draft</option>
									<option value="Online">Online</option>
									<option value="Cash">Cash</option>
								</select>
								
								<%--  <%=vbean.getVtype() %>--%></td>
<td>Voucher Form</td><td>:</td><td><%=vbean.getVfrom() %></td>



</tr>
<tr>
<td>Description</td><td>:</td><td><%=vbean.getDescription() %></td>
<td>Instrument Form</td><td>:</td><td><select  id="cheque_bank" name="cheque_bank"   style="width: 170px;" data-placeholder="Type" class="chosen-select" style="width:250px;" tabindex="2">
				<option value="0">--Select--</option>
				<%
					BankDao bankdao=new BankDao();
					ArrayList<BankBean> banklist=bankdao.getAllBank();
					if(banklist!=null){
						for(BankBean bean:banklist){%>
							<option value="<%=bean.getBank()%>"><%=bean.getBank() %></option>
					<%	}
					}
				%>
				</select><%-- <%=vbean.getInstrument_from() %> --%></td>
</tr>
<tr>
<td>Instrument No</td><td>:</td><td><input name="chk_no" id="chk_no"> 


<%-- <%=vbean.getInstrument_no() %> --%></td>
<td>Instrument Date</td><td>:</td><td><input type="date" name="chk_date" id="chk_date" value="<%=vbean.getInstrument_date() %>" /></td>

</tr><tr>
<td>Voucher Amt</td><td>:</td><td><%=vbean.getVamt() %></td>
<td>Voucher Type</td><td>:</td><td><%=vbean.getVoucher_type() %></td>

</tr>

<tr>
					<td colspan="6" class=""  ><hr style="color: black;" /></td>
					</tr>

	<tr><td colspan="2" height="5"></td></tr>
	</tbody>
</table>
<table border="1"   cellspacing="0" width="100%">
        <thead>
            <tr style="background: teal; color: white">
                <th style="width:10%">Sno</th>
              
                <th style="width:10%">Trx Date</th>
                 <th style="width:30%">A/c Name</th>
                 <th style="width:30%">Narration</th>
                <th style="width:10%">Dr</th>
                <th style="width:10%">Cr</th>
                
            </tr>
        </thead>
        
            <tbody>
        <%
        
       double totalcr=0;
        double totaldr=0;
        
		int i=1;
		if(lst.isEmpty()!=true){
			for(VoucherTrxDetailBean bean:lst){
				
				String name="";
				String narration="";
				
				if(bean.getMember_name()!=null){
					 name="[ "+bean.getMember_name()+ "]";
				}
				if(bean.getNarration()!=null){
					narration=bean.getNarration();
				}
				
				
        
        %>
        
        
    
            <tr>
                <td><%=i %></td>
                
                <td><%=bean.getTrx_date() %></td>
                <td><label style="color: olive ;"><%=bean.getAc_name() %></label><%=name %>
                </td>
                 <td><%=narration %></td>
                <td><%=bean.getDramt() %></td>
                <td><%=bean.getCramt() %></td>
               
                
            </tr>
            
            <%
            totaldr+=bean.getDramt();
            totalcr+=bean.getCramt();
            i++;
            
			}
		}
            %>
            </tbody>
            <tfoot>
            <tr>
                
               <th colspan="4"></th>
                <th><%=totaldr %></th>
                <th><%=totalcr %></th>
               
            </tr>
            
           
            
            
        </tfoot>
            </table>
            
            <table width="100%">
            <tr>
            <td>&nbsp;</td>
            </tr>
            <tr>   <td align="center">         
  <input type="submit" onclick="return prnt()" class="button" value="Print"/>     </td>  
            </tr>
            </table>
    
</form>

</body>
</html>
 <script src="js/jquery-1.10.2.js"></script>
  <script src="js/jquery-ui.js"></script>
<script>

$(document).ready(function(e) {
	
});
function prnt(){
	if($("#name").val()==""){
		alert("Please Enter Member Name");
		return false;
		
	}else if($("#vtype").val()=="0"){
		alert("Please Select Trx Type");
		return false;
	}else if($("#cheque_bank").val()=="0"){
		alert("Please Select Bank ");
		return false;
	}else if($("#chk_no").val()==""){
		alert("Please Enter Cheque No ");
		return false;
	}else if($("#chk_date").val()==""){
		alert("Please Select Cheque Date");
		return false;
	}
	
	
	

	
}


</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
