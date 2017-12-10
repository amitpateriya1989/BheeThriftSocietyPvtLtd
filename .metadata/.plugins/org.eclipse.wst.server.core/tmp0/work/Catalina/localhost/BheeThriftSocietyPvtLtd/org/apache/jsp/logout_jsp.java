package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class logout_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title></title>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/style.css\">\r\n");
      out.write("    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>\r\n");
      out.write("\t<script src=\"js/index.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("<div align=\"right\">   </div>\r\n");
      out.write("<div class=\"wrapper\">\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"container\">\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\t\t<h1>Logout Successfully..!!</h1>\r\n");
      out.write("\t\t<!-- <form action=\"Login\" method=\"post\">\r\n");
      out.write("\t\t\t<input type=\"text\" placeholder=\"Username\" name=\"uname\" id=\"uname\">\r\n");
      out.write("\t\t\t<input type=\"password\" placeholder=\"Password\" name=\"pass\" id=\"pass\">\r\n");
      out.write("\t\t\t<input type=\"submit\" id=\"login-button\" value=\"Login\">\r\n");
      out.write("\t\t</form> -->\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
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
      out.write("\t\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("  \r\n");
      out.write("    \r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");

try{
session.invalidate();
session=null;

      out.write("\r\n");
      out.write("  \r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("setTimeout(\"window.location='index.jsp'\",10000);\r\n");
      out.write("</script>\r\n");
      out.write("<script src=\"js/jquery-1.8.3.js\"></script>\r\n");
      out.write("        <script src=\"js/index.js\"></script>\r\n");
      out.write("        ");
}catch(Exception e){
	e.printStackTrace();
} 
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
