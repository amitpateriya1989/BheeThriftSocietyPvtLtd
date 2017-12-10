<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.google.gson.JsonElement"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="Model.Bean.BankBranchBean"%>
<%@page import="Model.Bean.BankRegionBean"%>
<%@page import="Model.Dao.BankBranchDao"%>
<%
/* response.setContentType("text/html");
response.setHeader("Cache-control", "no-cache, no-store");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "-1");

response.setHeader("Access-Control-Allow-Origin", "*");
response.setHeader("Access-Control-Allow-Methods", "POST");
response.setHeader("Access-Control-Allow-Headers", "Content-Type");
response.setHeader("Access-Control-Max-Age", "86400"); */

int ad_branch_id=Integer.parseInt(request.getParameter("ad_branch_id"));
BankBranchDao dao=new BankBranchDao();
BankBranchBean bean=dao.getBankBranchById(ad_branch_id);
//Convert Java Object to Json
Gson gson = new Gson();
JsonObject myObject=new JsonObject();
JsonElement element = gson.toJsonTree(bean);
if(bean.getBranch()== null){
	myObject.addProperty("success", false);
}
else {
	myObject.addProperty("success", true);
} 
myObject.add("BranchInfo", element);
response.setContentType("application/json");
response.getWriter().print(myObject);
System.out.println(myObject.toString());
%>