package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Model.Dao.ClientDao;
import Model.Bean.ClientBean;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.List<java.lang.String> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.List<java.lang.String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html >\r\n");
      out.write("  <head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>Login </title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/style.css\">\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/plugins/jquery-1.10.2.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("    <style>\r\n");
      out.write("h3,p{\r\n");
      out.write("    font-family: \"Times New Roman\", Times, serif;\r\n");
      out.write("    color:#2b506e;\r\n");
      out.write("    font-weight:bold;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("h5 {\r\n");
      out.write("    font-family: Arial, Helvetica, sans-serif;\r\n");
      out.write("    color:#ac3939;\r\n");
      out.write("    font-weight:bold;\r\n");
      out.write("}\r\n");
      out.write("footer { \r\n");
      out.write("\tfont-variant: small-caps;\r\n");
      out.write("    display: block;\r\n");
      out.write("    vertical-align:bottom;\r\n");
      out.write("    margin-top:38%;\r\n");
      out.write("}\r\n");
      out.write(".errors {\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\tmargin-left:80px;\r\n");
      out.write("\t\r\n");
      out.write("\twidth:400px;\r\n");
      out.write("\theight:20px;\r\n");
      out.write("\tmargin-bottom:8px;\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\tfont-family: monospace;\r\n");
      out.write("\tfont-size: 20px;\r\n");
      out.write("\t\r\n");
      out.write("}\r\n");
      out.write(".errors li{ \r\n");
      out.write("\tlist-style: none; \r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("  </head>\r\n");
try{ 
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("  <header>\r\n");
      out.write("  \t<div align=\"left\">\r\n");
      out.write("  \t<table width=\"100%\">\r\n");
      out.write("  \t\t<tr>\r\n");
      out.write("  \t\t\t<td width=\"20%\" align=\"right\">\r\n");
      out.write("  \t\t\t<img src=\"Image/mono.png\" width=\"22%\" style=\"margin-top:10px; margin-left:18%\">\t\r\n");
      out.write("  \t\t\t</td>\r\n");
      out.write("  \t\t\t");

				ClientBean clientbean2=new ClientDao().getMaxClient();
			
      out.write("\r\n");
      out.write("  \t\t\t<td width=\"70%\">\r\n");
      out.write("  \t\t\t\t<!-- <h3>Central Bank <br>Employees Co.Op. Credit Society</h3> -->\r\n");
      out.write("  \t\t\t\t<h3>");
      out.print(clientbean2.getName() );
      out.write("</h3>\r\n");
      out.write("  \t\t\t\t<h5>Registration No. ");
      out.print(clientbean2.getRegistration_no() );
      out.write("</h5>\r\n");
      out.write("  \t\t\t</td>\r\n");
      out.write("  \t\t\t<td width=\"10%\">\r\n");
      out.write("  \t\t\t<img src=\"Image/contact.png\" width=\"15%\">\r\n");
      out.write("  \t\t\t\t<h5>Tele No.");
      out.print(clientbean2.getPhone_no() );
      out.write("</h5>\r\n");
      out.write("  \t\t\t\t<h5>Fax No.");
      out.print(clientbean2.getFax() );
      out.write("</h5>\r\n");
      out.write("  \t\t\t</td>\r\n");
      out.write("  \t\t</tr>\r\n");
      out.write("  \t\t<tr style=\"background-color:#2196f3\">\r\n");
      out.write("  \t\t\t<td colspan=\"3\"><hr/></td>\r\n");
      out.write("  \t\t</tr>\r\n");
      out.write("  \t</table>\r\n");
      out.write("  \t</div>\r\n");
      out.write("  </header>\r\n");
      out.write("    <div class=\"wrapper\">\r\n");
      out.write("\t<div class=\"container\" style=\"text-align: center;\">\r\n");
      out.write("\t\t<h1>Welcome </h1>\r\n");
      out.write("\t\t\r\n");
      out.write("   \r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<form  action=\"LoginVerification\" method=\"post\" id=\"form\">\r\n");
      out.write("\t\t\t<input type=\"text\" placeholder=\"Username\" name=\"user_id\" id=\"user_id\"/>\r\n");
      out.write("\t\t\t<input type=\"password\" placeholder=\"Password\" name=\"pass\" id=\"pass\" />\r\n");
      out.write("\t\t\t<input type=\"submit\" id=\"login-button\" value=\"Login\" name=\"submit\"/>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t<ul class=\"bg-bubbles\">\r\n");
      out.write("\t\t<li></li>\r\n");
      out.write("\t\t<li></li>\r\n");
      out.write("\t\t<li></li>\r\n");
      out.write("\t\t<li></li>\r\n");
      out.write("\t\t<li></li>\r\n");
      out.write("\t\t<li></li>\r\n");
      out.write("\t\t<li></li>\r\n");
      out.write("\t\t<li></li>\r\n");
      out.write("\t\t<li></li>\r\n");
      out.write("\t\t<li></li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("    <!-- <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script> -->\r\n");
      out.write("<!-- <script src=\"js/jquery-1.8.3.js\"></script> -->\r\n");
      out.write("        <!-- <script src=\"js/index.js\"></script> -->\r\n");
      out.write("\r\n");
      out.write("  </body>\r\n");
      out.write("  <div id=\"footer\">\r\n");
      out.write("\r\n");
      out.write("</div> \r\n");
      out.write("<script type=\"text/javascript\" src=\"assets/plugins/jquery.validate.min.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("jQuery(function() {       \r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\tjQuery.validator.addMethod(\"alphaSpace\", function(value, element) {\r\n");
      out.write("\t\t  return this.optional(element) || /^[a-zA-Z0-9.@*\\s]*$/.test(value);\r\n");
      out.write("\t\t}, \"Please enter character and space only.\");\r\n");
      out.write("\t\r\n");
      out.write("\tjQuery( \"#form\" ).validate({\r\n");
      out.write("\t\t  rules: {\r\n");
      out.write("\t\t\t  user_id : {\r\n");
      out.write("\t\t      required: true,\r\n");
      out.write("\t\t      alphaSpace:true,\r\n");
      out.write("\t\t      maxlength:15\r\n");
      out.write("\t\t    },\r\n");
      out.write("\t\t    pass : {\r\n");
      out.write("\t\t\t      required: true,\r\n");
      out.write("\t\t\t      alphaSpace:true,\r\n");
      out.write("\t\t\t      maxlength:15\r\n");
      out.write("\t\t\t    }\r\n");
      out.write("\t\t  },\r\n");
      out.write("\t\t  messages: {\r\n");
      out.write("\t\t\t  state: {\r\n");
      out.write("\t\t\t\t  required: \"Username & Password is required.\",\r\n");
      out.write("\t\t\t  }\r\n");
      out.write("\t\t  }\r\n");
      out.write("\t\t});\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
}catch(Exception e){
	e.printStackTrace();
} 
      out.write("\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
