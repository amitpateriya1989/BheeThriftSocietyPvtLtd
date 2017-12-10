<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<%@page import="Model.Bean.UserBean"%>
<%
try{
UserBean user=null;
try{
	if(request.getSession(false).getAttribute("userbean")==null){
	response.sendRedirect("logout.jsp");
}

	
user=(UserBean)session.getAttribute("userbean");

if(user==null){
	response.sendRedirect("logout.jsp");
}else{
	
	
	/* if(session.getAttribute("openday")==null){

	response.sendRedirect("ad_open_days.jsp");
	} */
%>

<table border="0" width="100%">
		        <tbody>
		        <tr valign="top">
		           <td rowspan="" height="0" align="right" valign="top" width="20%">&nbsp;<img src="Image/mono.png" alt="State Bank of India" border="0" width="10%" style="margin-top:0%"> </td>
		          	<td rowspan="" width="0"><h3 style="margin-top:0%">Central Bank Employees Co-Operative Credit Society Ltd. Bhopal</h3><h5 style="margin-top:-20px; margin-bottom: -201px;"> <marquee> AR/BPL/57 </marquee></h5></td>
		            <td class="wlcmname" align="right" valign="top" width="20%" style="margin-top:0%">Welcome: Mr. <%=user.getName() %><img src="Image/user-icon.png" border="0" width="10%" style="margin-top:0%"></td>
		        </tr>        
		      </tbody>
		      </table> 
      

      			<table class="headerTopNav202" border="0" cellpadding="0" cellspacing="0" width="100%" style="padding-top="0px"">
        			<tbody>
        				<tr>
          					<td align="right" > Opening Date: <%=session.getAttribute("openday") %>   <a href="logout.jsp" style="font-weight: bold;color: white;text-decoration: none">Logout&nbsp;&nbsp;</a> </td>
        				</tr>
        				<tr>
        					<td>
        
         							<div id='cssmenu' >
										<%
											ArrayList<MenuBean> list=(ArrayList<MenuBean>) session.getAttribute("menubean");
											if(list!=null){

										%>         
										<ul>
											<%for(MenuBean menu:list){%>
  												 <li class='active'><a href="AdMenu?menu=<%=menu.getAd_menu_id() %>"><%=menu.getName() %></a></li>
											<%}}}
}catch(Exception e){
	e.printStackTrace();
} %>  
										</ul>
									</div>
        					</td>
       					 </tr>
      				</tbody>
      				<%}catch(Exception e){
	e.printStackTrace();
} %></table>