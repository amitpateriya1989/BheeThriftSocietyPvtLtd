<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<% 
String appointment = request.getParameter("doa");
String retirement = request.getParameter("dor");

SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
try{
Date appointmentDate = df.parse(appointment);
Date retirementDate = df.parse(retirement);

long msYear = 1000L * 60 * 60 * 24 * 365;
long msDiff = retirementDate.getTime() - appointmentDate.getTime();
out.println(String.valueOf(msDiff / msYear));

}catch (ParseException e) {
	e.printStackTrace();
}
%>  