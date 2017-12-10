<%@page import="MasterValidation.Validation"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="Model.Bean.DayOpenBean"%>
<%@page import="Model.Dao.DayOpenDao"%>
<%@page import="Model.Bean.SalutationBean"%>
<%@page import="Model.Dao.SalutationDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Bean.MenuBean"%>
<% try{ if(request.getParameter("action").equals("listdetails")==true){ %>
<table id="dataTable1" class="table table-striped table-bordered table-hover">
	<thead>
	  <tr>
		<th>S. No.</th>
		<th>Date</th>
		<th>Opening By</th>
		<th>Opening Status</th>
		<th>Closing Status</th>
		<th>Closing By</th>
		<th>Remark</th>
	  </tr>
	</thead>
	<tbody>	
		<%
		DayOpenDao dao=new DayOpenDao();
		Validation valid = new Validation();
		
		String fdate=request.getParameter("fdate");
		String tdate=request.getParameter("tdate");
		
		if(valid.validNotEmpty(fdate)==false && valid.validDate(fdate, "DD/MM/YYYY")==false){
			response.getWriter().print("error");
		}else if(valid.validNotEmpty(tdate)==false && valid.validDate(tdate, "DD/MM/YYYY")==false){
			response.getWriter().print("error");
		}else{
		DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
		Date fdt = df1.parse(fdate);
		Date tdt = df1.parse(tdate);
				
		ArrayList<DayOpenBean> slist=dao.getAllDayOpen(fdt, tdt);
		int i=0;
		if(slist!=null){
		  for(DayOpenBean bean:slist){
		%> 
	<tr>
		<td id=""><%=++i %></td>
		<td id=""><%=df1.format(bean.getOpen_days())%></td>
		<td id=""><%=bean.getCreatedby()%></td>
		<td id=""><%if(bean.getOpening_status()!=true){
						out.print("NOT OPEN");
					}else{
						out.print("OPEN");
					}
				  %>
		</td>
		<td id=""><%if(bean.getClosing_status()!=true){
						out.print("NOT CLOSED");
					}else{
						out.print("CLOSED");
					}
				%>
		</td>
		<td id=""><%=bean.getUpdatedby() %></td>
		<td id=""><%if(bean.getRemark()!=null){
					out.print(bean.getRemark());
					}
				  %>
		</td>
	</tr> 
	<%	}//end for loop
		}//end if check list
		}//end validation
	 %>
	</tbody>
</table>
<%
}//end check action
}catch(Exception ex){
	ex.printStackTrace();
}
%>
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script>
jQuery(function() {       
	jQuery('#dataTable1').DataTable();
	});
</script>