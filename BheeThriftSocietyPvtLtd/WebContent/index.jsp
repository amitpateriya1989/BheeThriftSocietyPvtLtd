<!DOCTYPE html>

<%@page import="Model.Dao.ClientDao"%>
<%@page import="Model.Bean.ClientBean"%>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Login </title>
    <link rel="stylesheet" href="css/style.css">
    <script src="${pageContext.request.contextPath}/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
    <style>
h3,p{
    font-family: "Times New Roman", Times, serif;
    color:#2b506e;
    font-weight:bold;
}

h5 {
    font-family: Arial, Helvetica, sans-serif;
    color:#ac3939;
    font-weight:bold;
}
footer { 
	font-variant: small-caps;
    display: block;
    vertical-align:bottom;
    margin-top:38%;
}
.errors {
	

	margin-left:80px;
	
	width:400px;
	height:20px;
	margin-bottom:8px;
	text-align: center;
	font-family: monospace;
	font-size: 20px;
	
}
.errors li{ 
	list-style: none; 
}
</style>
  </head>
<%try{ %>
  <body>
  <header>
  	<div align="left">
  	<table width="100%">
  		<tr>
  			<td width="20%" align="right">
  			<img src="Image/mono.png" width="22%" style="margin-top:10px; margin-left:18%">	
  			</td>
  			<%
				ClientBean clientbean2=new ClientDao().getMaxClient();
			%>
  			<td width="70%">
  				<!-- <h3>Central Bank <br>Employees Co.Op. Credit Society</h3> -->
  				<h3><%=clientbean2.getName() %></h3>
  				<h5>Registration No. <%=clientbean2.getRegistration_no() %></h5>
  			</td>
  			<td width="10%">
  			<img src="Image/contact.png" width="15%">
  				<h5>Tele No.<%=clientbean2.getPhone_no() %></h5>
  				<h5>Fax No.<%=clientbean2.getFax() %></h5>
  			</td>
  		</tr>
  		<tr style="background-color:#2196f3">
  			<td colspan="3"><hr/></td>
  		</tr>
  	</table>
  	</div>
  </header>
    <div class="wrapper">
	<div class="container" style="text-align: center;">
		<h1>Welcome </h1>
		
   
		
		<form  action="LoginVerification" method="post" id="form">
			<input type="text" placeholder="Username" name="user_id" id="user_id"/>
			<input type="password" placeholder="Password" name="pass" id="pass" />
			<input type="submit" id="login-button" value="Login" name="submit"/>
		</form>
	
		</div>
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>

    <!-- <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script> -->
<!-- <script src="js/jquery-1.8.3.js"></script> -->
        <!-- <script src="js/index.js"></script> -->

  </body>
  <div id="footer">

</div> 
<script type="text/javascript" src="assets/plugins/jquery.validate.min.js"></script>
<script>
jQuery(function() {       
	
	
	jQuery.validator.addMethod("alphaSpace", function(value, element) {
		  return this.optional(element) || /^[a-zA-Z0-9.@*\s]*$/.test(value);
		}, "Please enter character and space only.");
	
	jQuery( "#form" ).validate({
		  rules: {
			  user_id : {
		      required: true,
		      alphaSpace:true,
		      maxlength:15
		    },
		    pass : {
			      required: true,
			      alphaSpace:true,
			      maxlength:15
			    }
		  },
		  messages: {
			  state: {
				  required: "Username & Password is required.",
			  }
		  }
		});
});
</script>
<%}catch(Exception e){
	e.printStackTrace();
} %>
</html>
