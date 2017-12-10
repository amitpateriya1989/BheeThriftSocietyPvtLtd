<%@page import="Model.Bean.UserBean"%>
<%@page import="Model.Bean.SetAssignSubMenuBean"%>
<%@page import="Model.Dao.SetAssignSubMenuDao"%>
<%@page import="Model.Bean.SubMenuBean"%>
<%@page import="Model.Dao.SubMenuDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>

<style>
.menu	{	
	padding:10px 5px 10px 5px;
	margin:12px 12px 12px 5px;
}

/* Menu styles */
.menu ul
	{
	margin:0px;
	padding:0px;
	}
.menu li
	{
	margin:0px 0px 0px 5px;
	padding:0px;
	list-style-type:none;
	text-align:left;
	font-family:Arial,Helvetica,sans-serif;
	font-size:13px;
	font-weight:normal;
	}

/* Submenu styles */
.menu ul ul 
	{ background-color:#F6F6F6; }
.menu li li
	{ margin:0px 0px 0px 16px; }

/* Symbol styles */
.menu .symbol-item,
.menu .symbol-open,
.menu .symbol-close
	{
	float:left;
	width:16px;
	height:1em;
	background-position:left center;
	background-repeat:no-repeat;
	}
.menu .symbol-item  { background-image:url(TreeMenu/icons/page.png); }
.menu .symbol-open  { background-image:url(TreeMenu/icons/minus.png); }
.menu .symbol-close { background-image:url(TreeMenu/icons/plus.png);}

/* Menu line styles */
.menu li.open { font-weight:bold; }
.menu li.close { font-weight:normal; }
</style>

<div class="menu">
<ul id="example1" >
<%

String m=(String)session.getAttribute("ad_menu_id");
int mid=0;
try{
mid=Integer.parseInt(m);
}catch(NumberFormatException n){
	n.printStackTrace();
}


ArrayList<MenuBean> list1=(ArrayList<MenuBean>) session.getAttribute("menubean");


if(list1!=null){
for(MenuBean menu1:list1){

	if(menu1.getAd_menu_id()==mid){
	%>
   
  <li  ><a style="font-size: 20px" href="<%=menu1.getLink()%>"><p style="curser:pointer"><%=menu1.getName() %></p></a>
  <ul>
  <%
  ArrayList<SubMenuBean> sublist=(ArrayList<SubMenuBean>) session.getAttribute("submenubean");
  if(sublist!=null){
	  for(SubMenuBean menu:sublist){
		  if(menu1.getAd_menu_id()==menu.getAd_menu_id()){
  %>
 		 <li><a   href="<%=menu.getLink()%>" style="text-decoration: none;color:black;"><%=menu.getName() %></a></li>
  <%}
		  }
	  }%>
	  
	  </ul>
	  <%}
  }
}%>
  <!-- <li><a href="ad_bank.jsp">Bank</a></li>
  <li><a href="ad_pf_no_verification.jsp">Member</a></li>
  <li><a href="ad_branch.jsp">Branch</a></li>
  <li><a href="ad_state.jsp">Add State</a></li>
  <li><a href="ad_district.jsp">Add District</a></li>
  <li><a href="ad_city.jsp">Add City</a></li>
  <li><a href="ad_gender.jsp">Add Gender</a></li>
  <li><a href="ad_designation.jsp">Add Designation</a></li>
  <li><a href="ad_designation_level.jsp">Add Designation Level</a></li>
  <li><a href="ad_designation_type.jsp">Add Designation Type</a></li>
  <li><a href="ad_relation.jsp">Add Relation</a></li>
  <li><a href="ad_member_status.jsp">Add Member Status</a></li>
  <li><a href="ad_member_type.jsp">Add Member Type</a></li>
  <li><a href="ad_member_registration_master.jsp">Registration Fees setup</a></li>
  <li><a href="ad_account_type.jsp">Account Type</a></li>
  <li><a href="ad_account_group.jsp">Account Group</a></li>
  <li><a href="ad_account_sub_group.jsp">Account Sub Group</a></li>
  <li><a href="ad_account.jsp">Ledger Opening</a></li>
  <li><a href="edit_branch.jsp">Edit Branch</a></li>
  <li><a href="ledger.jsp">Ledger</a></li>
  <li><a href="daybook.jsp">DayBook</a></li>
  <li><a href="cashbook.jsp">CashBook</a></li>
  <li><a href="trail_balance.jsp">Trial Balance</a></li>
  <li><a href="view_ad_member.jsp">View Member</a></li>
  <li><a href="ad_voucher.jsp">Voucher Entry</a></li> -->
 
  </ul>
</li>
 
</ul>
<ul id="example1" >

<!-- <li>Member</p>
  <ul>
  
  <li><a href="">link</a></li>
  <li><a href="">link</a>
    <ul>
    <li><a href="">link</a></li>
    <li><a href="">link</a></li>
    <li><a href="">link
</a></li>
    </ul>
  </li>
  </ul>
</li> -->
</ul>

<script type="text/javascript">make_tree_menu('example1');</script>

</div class="menu">