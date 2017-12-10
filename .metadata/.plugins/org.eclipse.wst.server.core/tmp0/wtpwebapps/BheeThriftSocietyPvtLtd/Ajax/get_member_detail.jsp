<%@page import="MasterValidation.Validation"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParseException"%>
<%@page import="Model.Dao.CustomMemberInfoDao"%>
<%
try{
Validation valid = new Validation();

String sdate = request.getParameter("fdate");
String edate = request.getParameter("tdate");
String member_no = request.getParameter("member_no");

if(valid.validNotEmpty(sdate)==true && valid.validDate(sdate, "DD/MM/YYYY")==true 
 && valid.validNotEmpty(edate)==true && valid.validDate(edate, "DD/MM/YYYY")==true 
 ){

SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

Date fdate= sdf.parse(sdate);
Date tdate= sdf.parse(edate);
String society_no = member_no.trim();

CustomMemberInfoDao dao =new CustomMemberInfoDao();
List<Map<String, Object>> Memberlist = null;
Memberlist = dao.getMemberDetails(society_no, fdate, tdate);

%>
<table id="sample_1" class="table table-striped table-bordered table-hover table-full-width">
	<thead>
	<tr>
	 <th>Member Name</th>
	 <th>Member Id</th>
	 <th>PF No.</th>
	 <th>Society No.</th>
	 <th class="hidden-xs">Member Type</th>
	 <th class="hidden-xs">Created</th>
	 <th class="hidden-xs">Member Status</th>
	 <th class="hidden-xs">Father Name :</th>
	 <th class="hidden-xs">Husband Name :</th>
	 <th class="hidden-xs">Date Of Birth :</th>
	 <th class="hidden-xs">Gender :</th>
	 <th class="hidden-xs">Marital Status :</th>
	 <th class="hidden-xs">Category :</th>
	 <th class="hidden-xs">Pan No. :</th>
	 <th class="hidden-xs">Aadhar No. :</th>
	 <th class="hidden-xs">Date of Appointment :</th>
	 <th class="hidden-xs">Date of Joining :</th>
	 <th class="hidden-xs">Service Duration :</th>
	 <th class="hidden-xs">Date of Resignation :</th>
	 <th class="hidden-xs">Account No. :</th>
	 <th class="hidden-xs">Branch :</th>
	 <th class="hidden-xs">Designation Type :</th>
	 <th class="hidden-xs">Designation Level :</th>
	 <th class="hidden-xs">Designation :</th>
	 <th class="hidden-xs">Department Name :</th>
	 <th class="hidden-xs">Society :</th>
	 </tr>
	</thead>
	<tbody>
	<%
	if(Memberlist!=null){
	for (Map<String, Object> map : Memberlist) {
	 %>
		<tr>
		  <td><%out.println(map.get("MemberSalutation")+" "+map.get("MemberName"));%></td>
		  <td><%out.println(map.get("MemberId"));%></td>
		  <td><%out.println(map.get("MemberPfNo"));%></td>
		  <td><%out.println(map.get("MemberSocietyNo").toString().trim());%></td>
		  <td><%out.println(map.get("MemberType"));%></td>
		  <td>
		  <%
		  if(map.get("MemberCreated")!=null && map.get("MemberCreated").equals("")!=true){
			  out.println(sdf.format(map.get("MemberCreated")));
		  }	  
		  %>
		  </td>
		  <td><%out.println(map.get("MemberStatus"));%></td>
		  <td><%out.println(map.get("MemberFather"));%></td>
		  <td><%out.println(map.get("MemberHusband"));%></td>
		  <td><%
		  if(map.get("MemberDob")!=null && map.get("MemberDob").equals("")!=true){
			  out.println(sdf.format(map.get("MemberDob")));
		  }	  
		  %></td>
		  <td><%out.println(map.get("MemberGender"));%></td>
		  <td><%out.println(map.get("MemberMaritalStatus"));%></td>
		  <td><%out.println(map.get("MemberCategory"));%></td>
		  <td><%out.println(map.get("MemberPanNo"));%></td>
		  <td><%out.println(map.get("MemberAadharNo"));%></td>
		  <td>
		  <%
		  if(map.get("MemberDoa")!=null && map.get("MemberDoa").equals("")!=true){
			  out.println(sdf.format(map.get("MemberDoa")));
		  }	  
		  %>
		 </td>
		 <td>
		  <%
		  if(map.get("MemberDoj")!=null && map.get("MemberDoj").equals("")!=true){
			  out.println(sdf.format(map.get("MemberDoj")));
		  }	  
		  %>
		  </td>
		  <td><%out.println(map.get("MemberServiceDuration"));%></td>
		  <td>
		  <%
		  if(map.get("MemberDor")!=null && map.get("MemberDor").equals("")!=true){
			  out.println(sdf.format(map.get("MemberDor")));
		  }	  
		  %>
		  </td>
		  <td><%out.println(map.get("MemberAc"));%></td>
		  <td><%out.println(map.get("MemberBranch"));%></td>
		  <td><%out.println(map.get("MemberDesigType"));%></td>
		  <td><%out.println(map.get("MemberDesigLevel"));%></td>
		  <td><%out.println(map.get("MemberDesig"));%></td>
		  <td><%out.println(map.get("MemberDeptName"));%></td>
		  <td><%out.println(map.get("MemberTermination"));%></td>
	   </tr>
	<%}} //end for loop %>
	</tbody>
</table>
<%
}//end validation
}catch (Exception e) {
	e.printStackTrace();
} %>

<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/plugins/data-tables/DT_bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="assets/scripts/custom/table-advanced-member.js"></script>
<script>
jQuery(document).ready(function() {       
   TableAdvanced.init();
});
</script>