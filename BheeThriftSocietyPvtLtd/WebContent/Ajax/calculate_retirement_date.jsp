<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
String date = request.getParameter("date");

SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
try{
Date dob= df.parse(date);

Calendar c = Calendar.getInstance();
c.setTime(dob);
c.add(Calendar.YEAR, 60);
//System.out.print(df.format(c.getTime()));
response.getWriter().print(df.format(c.getTime()).trim());
}catch (ParseException e) {
	e.printStackTrace();
}
%>     