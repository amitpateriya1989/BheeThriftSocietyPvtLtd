package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Model.Dao.MemberRegistrationDao;
import Model.Bean.MemberRegistrationBean;
import java.util.Calendar;
import java.util.Map;
import java.util.List;
import Model.Dao.ClientDao;
import Model.Bean.ClientBean;
import Model.Bean.ClientBean;
import Model.Dao.ClientDao;
import java.text.SimpleDateFormat;
import java.util.Date;
import Model.Bean.UserBean;
import java.util.Date;
import java.util.ArrayList;
import Model.Bean.MenuBean;
import Model.Bean.UserBean;
import java.util.Date;
import java.util.ArrayList;
import Model.Bean.MenuBean;
import Model.Bean.UserBean;
import Model.Bean.UserBean;
import Model.Bean.SetAssignSubMenuBean;
import Model.Dao.SetAssignSubMenuDao;
import Model.Bean.SubMenuBean;
import Model.Dao.SubMenuDao;
import java.util.ArrayList;
import Model.Bean.MenuBean;

public final class ad_005fmember_005fdetails_005fsearch_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.List<java.lang.String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<java.lang.String>(7);
    _jspx_dependants.add("/Layout/header.jsp");
    _jspx_dependants.add("/Layout/headtitle.jsp");
    _jspx_dependants.add("/Layout/navbar.jsp");
    _jspx_dependants.add("/Layout/emptynavbar.jsp");
    _jspx_dependants.add("/Layout/sidebar.jsp");
    _jspx_dependants.add("/Layout/footer.jsp");
    _jspx_dependants.add("/Layout/loadElement.jsp");
  }

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

try{
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 		String from = null;
 		String to = null;
 		try {
 			Date date = (Date)session.getAttribute("openday");
 			
 			
 			
 			from=sdf.format(date).toString();
 			to= sdf.format(date).toString();
 			
 			
 			
 		} catch (Exception i) {
 			i.printStackTrace();
 		}
 	
      out.write("\r\n");
      out.write(" \t\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<!--[if IE 8]> <html lang=\"en\" class=\"ie8 no-js\"> <![endif]-->\r\n");
      out.write("<!--[if IE 9]> <html lang=\"en\" class=\"ie9 no-js\"> <![endif]-->\r\n");
      out.write("<!--[if !IE]><!-->\r\n");
      out.write("<html lang=\"en\" class=\"no-js\">\r\n");
      out.write("<!--<![endif]-->\r\n");
      out.write("<!-- BEGIN HEAD -->\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<meta charset=\"utf-8\"/>\r\n");
      out.write("<title>\r\n");

ClientBean clientbean1=new ClientDao().getMaxClient();

      out.write("\r\n");
      out.write("Welcome | ");
      out.print(clientbean1.getName() );
      out.write("\r\n");
      out.write("</title>\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\"/>\r\n");
      out.write("<meta content=\"\" name=\"description\"/>\r\n");
      out.write("<meta content=\"\" name=\"author\"/>\r\n");
      out.write("\r\n");
      out.write("<!-- BEGIN GLOBAL MANDATORY STYLES -->\r\n");
      out.write("<link href=\"http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/plugins/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/plugins/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/plugins/uniform/css/uniform.default.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("<!-- END GLOBAL MANDATORY STYLES -->\r\n");
      out.write("<!-- BEGIN THEME STYLES -->\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/style-color.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/style.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/style-responsive.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/plugins.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/themes/default.css\" rel=\"stylesheet\" type=\"text/css\" id=\"style_color\"/>\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/custom.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("<!-- END THEME STYLES -->\r\n");
      out.write("<link rel=\"shortcut icon\" href=\"favicon.ico\"/>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/plugins/jquery-1.10.2.min.js\" type=\"text/javascript\"></script>");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"assets/plugins/data-tables/DT_bootstrap.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"assets/plugins/bootstrap-datepicker/css/datepicker.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"assets/plugins/select2/select2.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"assets/plugins/select2/select2-bootstrap.css\"/>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<!-- END HEAD -->\r\n");
      out.write("<!-- BEGIN BODY -->\r\n");
      out.write("<body class=\"page-full-width\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
try{
	if(session.getAttribute("userbean")==null){
		 response.sendRedirect("logout.jsp");
	}else{
		
	
UserBean userSession=(UserBean)session.getAttribute("userbean");
Date date=(Date)session.getAttribute("openday");
ClientBean clientbean=new ClientDao().getMaxClient();

      out.write("\r\n");
      out.write("<div class=\"container-fluid\">\r\n");
      out.write("<div class=\"row light bg-grey\">\r\n");
      out.write("\t<div class=\"col-md-7 \">\r\n");
      out.write("\t\t<!--  <h3 class=\"top-head-title\">Central Bank Employees Co.Op. Credit Society</h3> --> \r\n");
      out.write("\t\t<h3 class=\"top-head-title\">");
      out.print(clientbean.getName() );
      out.write("</h3>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"col-md-5\">\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"top-user-lbl\">Welcome: Mr. ");
      out.print(userSession.getName());
      out.write(" <i class=\"fa fa-user\"></i><br>\r\n");
      out.write("\t");
      out.print(new SimpleDateFormat("dd/MM/yyyy").format(date));
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");

	}
	}catch(Exception e){
		e.printStackTrace();
	}
		

      out.write("\r\n");
      out.write("<!-- BEGIN HEADER -->\r\n");
      out.write("<div class=\"header navbar mega-menu\">\r\n");
      out.write("<!-- BEGIN TOP NAVIGATION BAR -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");


	
	

      out.write("\r\n");
      out.write("\t<!-- BEGIN TOP NAVIGATION BAR -->\r\n");
      out.write("\t<div class=\"header-inner\">\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- BEGIN HORIZANTAL MENU -->\r\n");
      out.write("\t\t<div class=\"hor-menu hidden-sm hidden-xs\">\r\n");
      out.write("\t\t");

		try{
			if(session.getAttribute("userbean")==null){
				 response.sendRedirect("logout.jsp");
			}else{
		ArrayList<MenuBean> list=(ArrayList<MenuBean>) session.getAttribute("menubean");
		if(list.isEmpty()!=true){
		
      out.write("   \r\n");
      out.write("\t\t\t<ul class=\"nav navbar-nav\">\r\n");
      out.write("\t\t\t");

			int i = 0;
			for(MenuBean menu:list){ i++;
			//classic-menu-dropdown active
			
      out.write("\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t<a href=\"AdMenu?menu=");
      out.print(menu.getAd_menu_id() );
      out.write('"');
      out.write('>');
      out.print(menu.getName() );
      out.write(" \t<span class=\"selected\">\t</span>\t</a>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t");
}} 
      out.write("\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- END HORIZANTAL MENU -->\r\n");
      out.write("\t\t<!-- BEGIN RESPONSIVE MENU TOGGLER -->\r\n");
      out.write("\t\t<a href=\"javascript:;\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\r\n");
      out.write("\t\t\t<img src=\"assets/img/menu-toggler.png\" alt=\"\"/>\r\n");
      out.write("\t\t</a>\r\n");
      out.write("\t\t<!-- END RESPONSIVE MENU TOGGLER -->\r\n");
      out.write("\t\t<!-- BEGIN TOP NAVIGATION MENU -->\r\n");
      out.write("\t\t<ul class=\"nav navbar-nav pull-right\">\r\n");
      out.write("\t\t\t<!-- BEGIN USER LOGIN DROPDOWN -->\r\n");
      out.write("\t\t\t<li class=\"dropdown user\">\r\n");
      out.write("\t\t\t\t<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" data-hover=\"dropdown\" data-close-others=\"true\">\r\n");
      out.write("\t\t\t\t\t<i class=\"fa fa-user\"></i>\r\n");
      out.write("\t\t\t\t\t<span class=\"username hidden-1024\">\r\n");
      out.write("\t\t\t\t\t\t Setting\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t<i class=\"fa fa-angle-down\"></i>\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t<ul class=\"dropdown-menu\">\r\n");
      out.write("\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"userProfile.jsp\">\r\n");
      out.write("\t\t\t\t\t\t\t<i class=\"fa fa-user\"></i> My Profile\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"#\">\r\n");
      out.write("\t\t\t\t\t\t\t<i class=\"fa fa-lock\"></i> Change Password\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<li class=\"divider\">\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"javascript:;\" id=\"trigger_fullscreen\">\r\n");
      out.write("\t\t\t\t\t\t\t<i class=\"fa fa-arrows\"></i> Full Screen\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"logout.jsp\">\r\n");
      out.write("\t\t\t\t\t\t\t<i class=\"fa fa-key\"></i> Logout\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t<!-- END USER LOGIN DROPDOWN -->\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<!-- END TOP NAVIGATION MENU -->\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- END TOP NAVIGATION BAR -->\r\n");
      out.write("\r\n");
      out.write("<div class=\"clearfix\">\r\n");
      out.write("</div>\r\n");

	}
	}catch(Exception e){
		e.printStackTrace();
	}
		

      out.write("\r\n");
      out.write("<!-- END TOP NAVIGATION BAR -->\r\n");
      out.write("</div>\r\n");
      out.write("<!-- END HEADER -->\r\n");
      out.write("<div class=\"clearfix\">\r\n");
      out.write("</div>\r\n");
      out.write("<!-- BEGIN CONTAINER -->\r\n");
      out.write("<!-- BEGIN CONTAINER -->\r\n");
      out.write("<div class=\"page-container\">\r\n");
      out.write("\t<!-- BEGIN EMPTY PAGE SIDEBAR -->\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");



      out.write("\r\n");
      out.write("<div class=\"page-sidebar navbar-collapse collapse\">\r\n");
      out.write("   ");
try{
		if(session.getAttribute("menubean")==null){
			 response.sendRedirect("logout.jsp");
		}else{
		ArrayList<MenuBean> menulist=(ArrayList<MenuBean>) session.getAttribute("menubean");
		if(menulist.isEmpty()!=true){
		
      out.write("   \r\n");
      out.write("\t\t<ul class=\"page-sidebar-menu\" data-auto-scroll=\"true\" data-slide-speed=\"200\">\r\n");
      out.write("\t\t\t");

			int i = 0;
			for(MenuBean menu:menulist){ i++;
			//classic-menu-dropdown active
			
      out.write("\r\n");
      out.write("\t <li>\r\n");
      out.write("\t\t <a href=\"AdMenu?menu=");
      out.print(menu.getAd_menu_id() );
      out.write('"');
      out.write('>');
      out.write(' ');
      out.print(menu.getName() );
      out.write("</a>\r\n");
      out.write("\t </li>\r\n");
      out.write("\t ");
}
		}
      out.write("\r\n");
      out.write("  </ul>\r\n");
      out.write("</div>\r\n");

	}
	}catch(Exception e){
		e.printStackTrace();
	}
		

      out.write("\r\n");
      out.write("\t<!-- END EMPTY PAGE SIDEBAR -->\r\n");
      out.write("\t<!-- BEGIN CONTENT -->\r\n");
      out.write("<div class=\"page-content-wrapper\">\r\n");
      out.write("<div class=\"page-content\">\r\n");
      out.write("<!-- BEGIN PAGE CONTENT-->\r\n");
      out.write("<div class=\"row\">\r\n");
      out.write("<div class=\"col-md-2 sidebar-content \">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");


		

	
String m=(String)session.getAttribute("ad_menu_id");
int mid=0;
try{
mid=Integer.parseInt(m);
}catch(NumberFormatException n){
	n.printStackTrace();
}

try{
	if(session.getAttribute("menubean")==null){
		 response.sendRedirect("logout.jsp");
	}else{
ArrayList<MenuBean> list1=(ArrayList<MenuBean>) session.getAttribute("menubean");


if(list1.isEmpty()!=true){
for(MenuBean menu1:list1){

	if(menu1.getAd_menu_id()==mid){
	
      out.write("\r\n");
      out.write("   \r\n");
      out.write("  <a style=\"font-size: 20px\" href=\"");
      out.print(menu1.getLink());
      out.write('"');
      out.write('>');
      out.print(menu1.getName() );
      out.write("</a>\r\n");
      out.write("  <ul class=\"ver-inline-menu tabbable margin-bottom-25\">\r\n");
      out.write("  ");

  ArrayList<SubMenuBean> sublist=(ArrayList<SubMenuBean>) session.getAttribute("submenubean");
  if(sublist.isEmpty()!=true){
	  for(SubMenuBean menu:sublist){
		  if(menu1.getAd_menu_id()==menu.getAd_menu_id()){
  
      out.write("\r\n");
      out.write("  \t<li>\r\n");
      out.write("\t<a href=\"");
      out.print(menu.getLink());
      out.write("\" >\r\n");
      out.write("\t<i class=\"fa fa-hand-o-right\"></i>\r\n");
      out.write("\t  ");
      out.print(menu.getName() );
      out.write("</a>\r\n");
      out.write("\t</li>\r\n");
      out.write("  ");
}
		  }
		  }
      out.write("\r\n");
      out.write("\t  \r\n");
      out.write("\t  </ul>\r\n");
      out.write("\t  ");
}
	} 
	}
	}
	}catch(Exception e){
		e.printStackTrace();
	}
		

      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"col-md-10\">\r\n");
      out.write("<!-- BEGIN PAGE HEADER-->\r\n");
      out.write("<div class=\"row\">\r\n");
      out.write("\t<div class=\"col-md-12\">\r\n");
      out.write("\t\t<ul class=\"page-breadcrumb breadcrumb\">\r\n");
      out.write("\t\t\t<li><i class=\"fa fa-home\"></i>\r\n");
      out.write("\t\t\t\t<a href=\"#\">Setup</a><i class=\"fa fa-angle-right\"></i>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t<li><a href=\"#\">Member</a><i class=\"fa fa-angle-right\"></i>Detail Search</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<!-- END PAGE TITLE & BREADCRUMB-->\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- END PAGE HEADER-->\r\n");
      out.write("<!-- BEGIN PAGE CONTENT-->\r\n");
      out.write("<div class=\"row\">\r\n");
      out.write("<div class=\"col-md-12\">\r\n");
      out.write("<!-- Containt Start -->\r\n");

Object AppObj = request.getSession().getAttribute("AppMessage");
String[] AppMessage = (String[])AppObj;
if(AppMessage[1].isEmpty()!=true && AppMessage[1].equals("welcome")!=true){ 
      out.write("\r\n");
      out.write("<div class=\"alert ");
      out.print(AppMessage[0] );
      out.write(" alert-dismissable\">\r\n");
      out.write("\t<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\"></button>\r\n");
      out.write("\t");
      out.print(AppMessage[1] );
      out.write("\r\n");
      out.write("</div>\r\n");

}

      out.write("\r\n");
      out.write("<!------------------------------------------------------------------- -->\r\n");
      out.write("\t<!-- BEGIN BORDERED TABLE PORTLET-->\r\n");
      out.write("\t <div class=\"portlet box purple\">\r\n");
      out.write("\t  <div class=\"portlet-title\">\r\n");
      out.write("\t   <div class=\"caption\">Search Member Detail</div>\r\n");
      out.write("\t   <div class=\"tools\"> <a href=\"javascript:;\" class=\"collapse\"> </a>\r\n");
      out.write("\t   </div>\r\n");
      out.write("\t   </div>\r\n");
      out.write("\t   <div class=\"portlet-body\">\r\n");
      out.write("\t   <form id=\"frmSearchDetail\" name=\"frmPostingDetail\" action=\"#\">\r\n");
      out.write("\t\t\t<table class=\"table table-bordered\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td width=\"15%\">Start Date : <span class=\"red\">*</span></td>\r\n");
      out.write("\t\t\t\t    <td width=\"35%\">\r\n");
      out.write("\t\t\t\t    <input class=\"form-control input-medium date-picker\" type=\"text\" name=\"fdate\" id=\"fdate\" value=\"");
      out.print(from );
      out.write("\" />\r\n");
      out.write("\t\t\t\t    <label class=\"error\"></label>\r\n");
      out.write("\t\t\t\t    </td>\t\r\n");
      out.write("\t\t\t\t    <td width=\"15%\">End Date : <span class=\"red\">*</span></td>\r\n");
      out.write("\t\t\t\t    <td width=\"35%\">\r\n");
      out.write("\t\t\t\t    <input class=\"form-control input-medium date-picker\" type=\"text\" name=\"tdate\" id=\"tdate\" value=\"");
      out.print(to );
      out.write("\" />\r\n");
      out.write("\t\t\t\t    <label class=\"error\"></label>\r\n");
      out.write("\t\t\t\t    </td>\t\t\t\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t <tr>\r\n");
      out.write("\t\t\t\t\t<td width=\"15%\">Member Society No. : </td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"3\"> <select class=\"form-control input-medium\"  name=\"memberno\" id=\"memberno\" >\r\n");
      out.write("\t\t\t\t\t<option value=\"\">--Select--</option>\r\n");
      out.write("\t\t\t\t\t");

					   ArrayList<MemberRegistrationBean> list=new MemberRegistrationDao().getAllMemberlistName();
					if(list.isEmpty()!=true){
						for(MemberRegistrationBean bean:list){
							
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"M");
      out.print(bean.getAd_society_no());
      out.write('"');
      out.write('>');
      out.write('M');
      out.print(bean.getAd_society_no() );
      out.write('|');
      out.print(bean.getName() );
      out.write("</option>\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t");

						}
					}
					
					
      out.write("\r\n");
      out.write("\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t<label class=\"error\"></label>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr> \r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td></td>\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<td colspan=\"3\">\r\n");
      out.write("\t\t\t\t\t  <input class=\"btn btn blue btn-search\" type=\"button\" name=\"search\" value=\"Search\"/>\r\n");
      out.write("\t\t\t\t\t  <input id=\"clear\" class=\"btn btn green\" type=\"reset\" name=\"Clear\"/>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t    </div><!-- End portlet-body -->\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- BEGIN BORDERED TABLE PORTLET-->\r\n");
      out.write("\t <div class=\"portlet box purple\">\r\n");
      out.write("\t  <div class=\"portlet-title\">\r\n");
      out.write("\t   <div class=\"caption\">\r\n");
      out.write("\t\tView Member Detail\r\n");
      out.write("\t   </div>\r\n");
      out.write("\t   <div class=\"tools\"> <a href=\"javascript:;\" class=\"collapse\"> </a>\r\n");
      out.write("\t   </div>\r\n");
      out.write("\t   </div>\r\n");
      out.write("\t    <div class=\"portlet-body\">\r\n");
      out.write("\t   <!--  class=\"scroller\" style=\"height:600px\" data-always-visible=\"1\" data-rail-visible=\"0\" -->\r\n");
      out.write("\t\t <div id=\"dispaly\">\r\n");
      out.write("\t\t \r\n");
      out.write("\t\t\r\n");
      out.write("\t\t \r\n");
      out.write("\t\t </div>\r\n");
      out.write("\t\t<div class=\"clearfix\"></div>\r\n");
      out.write("\t    </div><!-- End portlet-body -->\r\n");
      out.write("\t</div>\r\n");
      out.write("<!------------------------------------------------------------------- -->\r\n");
      out.write("\t<!-- END BORDERED TABLE PORTLET-->\t\t\t\t\t\r\n");
      out.write("<!-- Containt Stop -->\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- END PAGE CONTENT-->\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- END CONTENT -->\r\n");
      out.write("</div>\r\n");
      out.write("<!-- END CONTAINER -->\r\n");
      out.write("<!-- BEGIN FOOTER -->\r\n");
      out.write("<!-- BEGIN FOOTER -->\r\n");
      out.write("<div class=\"footer\">\r\n");
      out.write("\t<div class=\"footer-inner\">\r\n");
      out.write("\t\t 2014 &copy; Syphon Tech.\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"footer-tools\">\r\n");
      out.write("\t\t<span class=\"go-top\">\r\n");
      out.write("\t\t\t<i class=\"fa fa-angle-up\"></i>\r\n");
      out.write("\t\t</span>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- END FOOTER -->\r\n");
      out.write("<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->\r\n");
      out.write("<div class=\"modal fade custom-messageBox\" id=\"portlet-config\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\r\n");
      out.write("\t<div class=\"modal-dialog\">\r\n");
      out.write("\t\t<div class=\"modal-content\">\r\n");
      out.write("\t\t\t<div class=\"modal-header bg-blue\">\r\n");
      out.write("\t\t\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\"></button>\r\n");
      out.write("\t\t\t\t\t\t\t<h4 class=\"modal-title\"><i class=\"fa fa-cogs\"></i> Information</h4>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\t\t\t\t\t\t\t <div id=\"custom-page-message\"></div><!-- End custom-page-message -->\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t\t\t\t<button type=\"button\" class=\"btn blue\" data-dismiss=\"modal\">Close</button>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- /.modal-content -->\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- /.modal-dialog -->\r\n");
      out.write("\t</div>\r\n");
      out.write("<!-- /.modal -->\r\n");
      out.write("<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->\r\n");
      out.write("<!-- BEGIN CORE PLUGINS -->\r\n");
      out.write("<!--[if lt IE 9]>\r\n");
      out.write("<script src=\"assets/plugins/excanvas.min.js\"></script>\r\n");
      out.write("<script src=\"assets/plugins/respond.min.js\"></script>  \r\n");
      out.write("<![endif]-->\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/plugins/jquery-migrate-1.2.1.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/plugins/bootstrap/js/bootstrap.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/plugins/jquery.blockui.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/plugins/jquery.cokie.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/plugins/uniform/jquery.uniform.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<!-- END CORE PLUGINS -->\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/scripts/core/app.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("jQuery(document).ready(function() {    \r\n");
      out.write("   App.init();\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("<!-- END JAVASCRIPTS -->\r\n");
      out.write("<!-- ----------------Start Loading box----------------- -->\r\n");
      out.write("<div id=\"modelLoad\" class=\"hide\">\r\n");
      out.write("\t<div class=\"loading1\">\r\n");
      out.write("\t\t<div class=\"text-center\">\r\n");
      out.write("\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/img/Preloader_8.gif\" name=\"load-image\" alt=\"load-image\" />\r\n");
      out.write("\t\tLoading........\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div><!--End loading1-->\r\n");
      out.write("</div> <!--End ModelLoad-->\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    $(function () {\r\n");
      out.write("        var loading = $('.loading1');\r\n");
      out.write("        if (loading[0] != null) {\r\n");
      out.write("              var top = Math.max($(window).height() / 2 - loading[0].offsetHeight / 2, 0);\r\n");
      out.write("              var left = Math.max($(window).width() / 2 - loading[0].offsetWidth / 2, 0);\r\n");
      out.write("              loading.css({ top: top, left: left });\r\n");
      out.write("        }\r\n");
      out.write("        \r\n");
      out.write("     $('#modelLoad .loading1').click(function(){\r\n");
      out.write("    \t $('#modelLoad').addClass('hide').removeClass('show');\r\n");
      out.write("     });   \r\n");
      out.write("        \r\n");
      out.write("    });\r\n");
      out.write("</script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(document).ready(\r\n");
      out.write("\t\t\r\n");
      out.write("  function() {\r\n");
      out.write("\t//$(\"body\").ajaxError(\r\n");
      out.write("\t // function(e,request) {\r\n");
      out.write("\t\t//if (request.status == 403) {\r\n");
      out.write("\t\t\t//window.location.reload();\r\n");
      out.write("\t\t//window.location.href = \"/myapp/login\";\r\n");
      out.write("\t\t//}\r\n");
      out.write("  \t//});\r\n");
      out.write("\t\r\n");
      out.write("\tfunction loading_show(){\r\n");
      out.write("        $('#modelLoad').removeClass('hide').addClass('show');\r\n");
      out.write("     }\r\n");
      out.write("     function loading_hide(){\r\n");
      out.write("       $('#modelLoad').addClass('hide').removeClass('show');\r\n");
      out.write("     } \r\n");
      out.write("     \r\n");
      out.write("     $( document ).ajaxStart(function() {\r\n");
      out.write("    \t loading_show();\r\n");
      out.write("     });\r\n");
      out.write("\t\r\n");
      out.write("\t$( document ).ajaxComplete(function() {\r\n");
      out.write("\t\tloading_hide();\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("});// end dom\r\n");
      out.write("</script>\r\n");
      out.write("<!-- -----------------------End loadning box--------------------- -->");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"assets/plugins/jquery.validate.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js\"></script>\r\n");
      out.write("<script src=\"assets/scripts/custom/components-pickers.js\"></script>\r\n");
      out.write("<script src=\"assets/plugins/select2/select2.min.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("jQuery(function() {       \r\n");
      out.write("\t$('#memberno').select2();\r\n");
      out.write("\tjQuery('.date-picker').datepicker({format: 'dd/mm/yyyy',endDate : '");
      out.print(to);
      out.write("',\r\n");
      out.write("\t\tautoclose : true});\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\tjQuery.validator.addMethod(\"validDate\", function (value, element) {\r\n");
      out.write("        return this.optional(element) || /^[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}$/i.test(value);\r\n");
      out.write("    }, \"Please enter valid Date.\");\r\n");
      out.write("\t\r\n");
      out.write("\tjQuery(\"#frmSearchDetail\").validate({\r\n");
      out.write("\t\t  rules: {\r\n");
      out.write("\t\t\tfdate: {\r\n");
      out.write("\t\t      required: true,\r\n");
      out.write("\t\t      validDate:true\r\n");
      out.write("\t\t    },\r\n");
      out.write("\t\t    tdate: {\r\n");
      out.write("\t\t\t      required: true,\r\n");
      out.write("\t\t\t      validDate:true\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tmemberno:{\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t  }\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t$(\".btn-search\").click(function(e){\t\r\n");
      out.write("\t\t\r\n");
      out.write("\tvar id = $(this).val();\r\n");
      out.write("\tif($(\"#frmSearchDetail\").valid()==false){\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tvar member_no = $('#memberno').val().trim();\r\n");
      out.write("\t\tvar fdate = $('#fdate').val().trim();\r\n");
      out.write("\t\tvar tdate = $('#tdate').val().trim();\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\t  type:\"post\",\r\n");
      out.write("\t\t\t  url:\"Ajax/get_member_detail.jsp\",\r\n");
      out.write("\t\t\t  data:{\"member_no\":member_no,\"fdate\":fdate,\"tdate\":tdate},\r\n");
      out.write("\t\t\t  success: function(data){\r\n");
      out.write("\t\t\t\t  $('#dispaly').html(data);\r\n");
      out.write("\t\t\t  },\r\n");
      out.write("\t\t\t  error: function(dataXhr, status, errorElement){\r\n");
      out.write("\t\t\t\t  console.log(status);\r\n");
      out.write("\t\t\t  }\r\n");
      out.write("\t\t\t});//end ajax\r\n");
      out.write("\t}//end check validation\r\n");
      out.write("\t\t\r\n");
      out.write("\t});//end member serach\r\n");
      out.write("\t\r\n");
      out.write("});//end dom\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
}catch(Exception e){
	e.printStackTrace();
} 
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
