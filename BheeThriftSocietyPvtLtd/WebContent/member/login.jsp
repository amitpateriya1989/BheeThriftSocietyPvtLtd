<!DOCTYPE html>

<html >
  <head>
    <meta charset="UTF-8">
    <title>Login </title>
    <link rel="stylesheet" href="../css/style.css">
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

  <body>
  <header>
  	<div align="left">
  	<table width="100%">
  		<tr>
  			<td width="20%" align="right">
  			<img src="../Image/mono.png" width="22%" style="margin-top:10px; margin-left:18%">	
  			</td>
  			<td width="60%">
  				<h3>Central Bank <br>Employees Co-Operative Credit Society Ltd. Bhopal </h3>
  				<h5>AR/BPL/57</h5>
  			</td>
  			<td width="20%">
  			<img src="../Image/contact.png" width="15%">
  				<h5>Tele No.0000-0000000, 0000000</h5>
  			</td>
  		</tr>
  		<tr style="background-color:#33cccc">
  			<td colspan="3"><hr/></td>
  		</tr>
  	</table>
  	</div>
  </header>
    <div class="wrapper">
	<div class="container" style="text-align: center;">
		<h1>Welcome</h1>
		
   
		
		<form  action="../MemberLoginVerification" method="post" id="form">
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
<script src="../js/jquery-1.8.3.js"></script>
        <script src="../js/index.js"></script>

  </body>
  <div id="footer">
<footer style="background-color:#33cccc">
	<p align="right">Posted by: Syphon Tech </p>
	 <address>
		Contact information: <a href="mailto:info@syphon.co.in">
  		info@syphon.co.in</a>
  	</address> 
 
 </footer>
</div> 
</html>
