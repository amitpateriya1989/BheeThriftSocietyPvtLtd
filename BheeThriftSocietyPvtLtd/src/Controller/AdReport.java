package Controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

import Model.Dao.DBConnection;

/**
 * Servlet implementation class AdReport
 */
@WebServlet("/AdReport")
public class AdReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;   
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action=request.getParameter("action");
		if(action!=null && action.equals("")!=true){

			connection = DBConnection.getConnection();

			if(action.equals("share_day_book")){
				String date=request.getParameter("fdate");
				if(date!=null && date.equals("")!=true){
					try {  
						Date d=new SimpleDateFormat("dd/MM/yyyy").parse(date);
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/share_day_book.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						parameters.put("date", new java.sql.Timestamp(d.getTime()));
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 

				}

			}
					
			if(action.equals("loan_day_book")){
				String date=request.getParameter("fdate");
				if(date!=null && date.equals("")!=true){
					try {  
						Date d=new SimpleDateFormat("dd/MM/yyyy").parse(date);
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/loan_daybook.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						parameters.put("date", new java.sql.Timestamp(d.getTime()));
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 
					

				}

			}
			
			if(action.equals("share_certificate")){
				String share_id=request.getParameter("ad_member_share_id");
				int ad_member_share_id=0;
				if(share_id!=null){
					ad_member_share_id=Integer.parseInt(share_id);
				}
				
				if(ad_member_share_id>0){
					
					try {  
						
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/share_certificate.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						
						parameters.put("share_id", ad_member_share_id);
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 
					
					
				}
				
				
				
			}
			if(action.equals("loan_detail")){
				String fdate=request.getParameter("fdate");
				String tdate=request.getParameter("tdate");
				if(fdate!=null && fdate.equals("")!=true && tdate!=null && tdate.equals("")!=true){
					try {  
						Date frm=new SimpleDateFormat("dd/MM/yyyy").parse(fdate);
						Date to=new SimpleDateFormat("dd/MM/yyyy").parse(tdate);
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/loan_detail.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						parameters.put("fdate", new java.sql.Timestamp(frm.getTime()));
						parameters.put("tdate", new java.sql.Timestamp(to.getTime()));
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 
					
				}

			}
			
			if(action.equals("retirement_list")){
				String fdate=request.getParameter("fdate");
				
				if(fdate!=null && fdate.equals("")!=true ){
					try {  
						Date frm=new SimpleDateFormat("dd/MM/yyyy").parse(fdate);
						System.out.println(fdate);
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/retirement_report.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						System.out.println(new java.sql.Timestamp(frm.getTime()));
						parameters.put("rdate", new java.sql.Timestamp(frm.getTime()));
						
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 
					
				}else{
					 response.getWriter().print("<html> <script type='text/javascript'> alert('Please Enter Valid Date...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
				}

			}

			if(action.equals("dividend_list")){
				String fdate=request.getParameter("fdate");
				
				if(fdate!=null && fdate.equals("")!=true ){
					try {  
						Date frm=new SimpleDateFormat("dd/MM/yyyy").parse(fdate);
						System.out.println(fdate);
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/divident_report.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						System.out.println(new java.sql.Timestamp(frm.getTime()));
						parameters.put("fdate", new java.sql.Timestamp(frm.getTime()));
						
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 
					
				}else{
					 response.getWriter().print("<html> <script type='text/javascript'> alert('Please Enter Valid Date...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
				}

			}
//----------------------------------------------------------------------------------------------------------------------------			
			if(action.equals("dividend_bank_report")){
				String fdate=request.getParameter("fdate");
				
				if(fdate!=null && fdate.equals("")!=true ){
					try {  
						Date frm=new SimpleDateFormat("dd/MM/yyyy").parse(fdate);
						System.out.println(fdate);
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/divident_bank_report.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						System.out.println(new java.sql.Timestamp(frm.getTime()));
						parameters.put("fdate", new java.sql.Timestamp(frm.getTime()));
						
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 
					
				}else{
					 response.getWriter().print("<html> <script type='text/javascript'> alert('Please Enter Valid Date...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
				}

			}
			
//----------------------------------------------------------------------------------------------------
			if(action.equals("fd_report_by_type")){
				String fdate=request.getParameter("fdate");
				String tdate=request.getParameter("tdate");
				String ad_type_of_fd_id=request.getParameter("ad_type_of_fd_id");
				
				if((fdate!=null && fdate.equals("")!=true)  && (tdate!=null && tdate.equals("")!=true) && (ad_type_of_fd_id!=null && ad_type_of_fd_id.equals("")!=true)){
					try {  
						Date frm1=new SimpleDateFormat("dd/MM/yyyy").parse(fdate);
						Date frm2=new SimpleDateFormat("dd/MM/yyyy").parse(tdate);
						int id=Integer.parseInt(ad_type_of_fd_id);
						System.out.println(fdate);
						System.out.println(tdate);
						System.out.println(ad_type_of_fd_id);
						
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/fd_report_by_type.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						
						parameters.put("from", new java.sql.Timestamp(frm1.getTime()));
						parameters.put("to", new java.sql.Timestamp(frm2.getTime()));
						parameters.put("fd_type", id);
						
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 
					
				}else{
					 response.getWriter().print("<html> <script type='text/javascript'> alert('Please Enter Valid Date...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
				}

			}	
			
//-----------------------------------------------------------------------------------------------------
			
			if(action.equals("fd_opening_report")){
				String fdate=request.getParameter("fdate");
				String tdate=request.getParameter("tdate");
				
				if((fdate!=null && fdate.equals("")!=true)  && (tdate!=null && tdate.equals("")!=true) ){
					try {  
						Date frm1=new SimpleDateFormat("dd/MM/yyyy").parse(fdate);
						Date frm2=new SimpleDateFormat("dd/MM/yyyy").parse(tdate);
						
						System.out.println(fdate);
						System.out.println(tdate);
						
						
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/fd_opening_report.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						
						parameters.put("from", new java.sql.Timestamp(frm1.getTime()));
						parameters.put("to", new java.sql.Timestamp(frm2.getTime()));
						
						
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 
					
				}else{
					 response.getWriter().print("<html> <script type='text/javascript'> alert('Please Enter Valid Date...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
				}

			}	
			
//-----------------------------------------------------------------------------------------------------
			if(action.equals("fd_maturity_report")){
				String fdate=request.getParameter("fdate");
				String tdate=request.getParameter("tdate");
				
				if((fdate!=null && fdate.equals("")!=true)  && (tdate!=null && tdate.equals("")!=true) ){
					try {  
						Date frm1=new SimpleDateFormat("dd/MM/yyyy").parse(fdate);
						Date frm2=new SimpleDateFormat("dd/MM/yyyy").parse(tdate);
						
						System.out.println(fdate);
						System.out.println(tdate);
						
						
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/fd_maturity_report.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						
						parameters.put("from", new java.sql.Timestamp(frm1.getTime()));
						parameters.put("to", new java.sql.Timestamp(frm2.getTime()));
						
						
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 
					
				}else{
					 response.getWriter().print("<html> <script type='text/javascript'> alert('Please Enter Valid Date...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
				}

			}	
			
//-----------------------------------------------------------------------------------------------------
			//-----------------------------------------------------------------------------------------------------
			if(action.equals("fd_payment_report")){
				String fdate=request.getParameter("fdate");
				String tdate=request.getParameter("tdate");
				
				if((fdate!=null && fdate.equals("")!=true)  && (tdate!=null && tdate.equals("")!=true) ){
					try {  
						Date frm1=new SimpleDateFormat("dd/MM/yyyy").parse(fdate);
						Date frm2=new SimpleDateFormat("dd/MM/yyyy").parse(tdate);
						
						System.out.println(fdate);
						System.out.println(tdate);
						
						
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/fd_payment_report.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						
						parameters.put("from", new java.sql.Timestamp(frm1.getTime()));
						parameters.put("to", new java.sql.Timestamp(frm2.getTime()));
						
						
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 
					
				}else{
					 response.getWriter().print("<html> <script type='text/javascript'> alert('Please Enter Valid Date...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
				}

			}	
			
//-----------------------------------------------------------------------------------------------------
			//-----------------------------------------------------------------------------------------------------
			if(action.equals("share_report_by_date")){
				String fdate=request.getParameter("fdate");
				String tdate=request.getParameter("tdate");
				
				if((fdate!=null && fdate.equals("")!=true)  && (tdate!=null && tdate.equals("")!=true) ){
					try {  
						Date frm1=new SimpleDateFormat("dd/MM/yyyy").parse(fdate);
						Date frm2=new SimpleDateFormat("dd/MM/yyyy").parse(tdate);
						
						System.out.println(fdate);
						System.out.println(tdate);
						
						
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/share_detail_by_date.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						
						parameters.put("fdate", new java.sql.Timestamp(frm1.getTime()));
						parameters.put("tdate", new java.sql.Timestamp(frm2.getTime()));
						
						
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 
					
				}else{
					 response.getWriter().print("<html> <script type='text/javascript'> alert('Please Enter Valid Date...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
				}

			}	
			
//-----------------------------------------------------------------------------------------------------
			if(action.equals("share_report_by_status")){
				String fdate=request.getParameter("fdate");
				String tdate=request.getParameter("tdate");
				String ad_member_status_id=request.getParameter("ad_member_status_id");
				if((fdate!=null && fdate.equals("")!=true)  && (tdate!=null && tdate.equals("")!=true) && (ad_member_status_id!=null && ad_member_status_id.equals("")!=true) ){
					try {  
						Date frm1=new SimpleDateFormat("dd/MM/yyyy").parse(fdate);
						Date frm2=new SimpleDateFormat("dd/MM/yyyy").parse(tdate);
						int id=Integer.parseInt(ad_member_status_id);
						System.out.println(fdate);
						System.out.println(tdate);
						
						
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/share_detail_by_member_status.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						
						parameters.put("fdate", new java.sql.Timestamp(frm1.getTime()));
						parameters.put("tdate", new java.sql.Timestamp(frm2.getTime()));
						parameters.put("ad_member_status_id", id);
						
						
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 
					
				}else{
					 response.getWriter().print("<html> <script type='text/javascript'> alert('Please Enter Valid Date...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
				}

			}	
			
//-----------------------------------------------------------------------------------------------------
			if(action.equals("share_report_by_member")){
				
				String ad_member_id=request.getParameter("ad_member_id");
				if( (ad_member_id!=null && ad_member_id.equals("")!=true) ){
					try {  
						
						int id=Integer.parseInt(ad_member_id);
						
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/share_detail_by_member.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						
						System.out.println(ad_member_id);
						parameters.put("ad_member_id", id);
						
						
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 
					
				}else{
					 response.getWriter().print("<html> <script type='text/javascript'> alert('Please Enter Valid Date...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
				}

			}	
			
//-----------------------------------------------------------------------------------------------------
if(action.equals("share_summary_report")){
				
				
					try {  
						
						
						
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/share_summry_report.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						
						
						
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 
					
				

			}	
			
//-----------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------
if(action.equals("demandList")){
				
				String bid=request.getParameter("ad_branch_id");
				int ad_branch_id=Integer.parseInt(bid);
					try {  
						
						
						
						FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/demand_list.jasper"));
						BufferedInputStream reportFile = new BufferedInputStream(fis);
						Map parameters = new HashMap();
						parameters.put("branch_id", ad_branch_id);
						
						
						JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
						List<JRPrintPage> pages = print.getPages();
					    if (pages.size()==0){
					            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
					            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
					    }else{
					    	
					    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
					    	response.setContentType("application/pdf");
					    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
					    	response.setContentLength(baos.size());
					    	ServletOutputStream outStream = response.getOutputStream();
					    	baos.writeTo(outStream);
					    	outStream.flush();
					    	outStream.close();
					   
					    }
					} catch (Exception ex) {
						ex.printStackTrace();
					} 
					
				

			}	
		if(action.equals("membership_card_report")){
			String mid=request.getParameter("ad_member_id");
			
				try {  
					
					int ad_member_id=Integer.parseInt(mid);
					
					FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/membership_card.jasper"));
					BufferedInputStream reportFile = new BufferedInputStream(fis);
					Map parameters = new HashMap();
					parameters.put("member_id", ad_member_id);
					
					
					JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
					List<JRPrintPage> pages = print.getPages();
				    if (pages.size()==0){
				            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
				            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
				    }else{
				    	
				    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
				    	response.setContentType("application/pdf");
				    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
				    	response.setContentLength(baos.size());
				    	ServletOutputStream outStream = response.getOutputStream();
				    	baos.writeTo(outStream);
				    	outStream.flush();
				    	outStream.close();
				   
				    }
				} catch (Exception ex) {
					ex.printStackTrace();
				} 
				
			
			
			
			
			
		}	
		
		if(action.equals("interestcertificate")){
			
			String mid=request.getParameter("ad_member_id");
			String loan_type_id=request.getParameter("type_of_loan_id");
			String fdate=request.getParameter("fdate");
			String tdate=request.getParameter("tdate");
			
			if((fdate!=null && fdate.equals("")!=true)  && (tdate!=null && tdate.equals("")!=true) ){
				try {  
					Date frm1=new SimpleDateFormat("dd/MM/yyyy").parse(fdate);
					Date frm2=new SimpleDateFormat("dd/MM/yyyy").parse(tdate);
			 
				
				int ad_member_id=Integer.parseInt(mid);
				int type_of_loan_id=Integer.parseInt(loan_type_id);
				FileInputStream fis=	new FileInputStream(request.getServletContext().getRealPath("reports/interest_certificate.jasper"));
				BufferedInputStream reportFile = new BufferedInputStream(fis);
				Map parameters = new HashMap();
				parameters.put("member_id", ad_member_id);
				parameters.put("loan_type", type_of_loan_id);
				parameters.put("fdate", new java.sql.Timestamp(frm1.getTime()));
				parameters.put("tdate", new java.sql.Timestamp(frm2.getTime()));
				
				JasperPrint print = JasperFillManager.fillReport(reportFile, parameters, connection);
				List<JRPrintPage> pages = print.getPages();
			    if (pages.size()==0){
			            response.getWriter().print("<html> <script type='text/javascript'> alert('Data Not Found...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
			            //request.getRequestDispatcher("/reports.jsp").forward(request, response);
			    }else{
			    	
			    	ByteArrayOutputStream baos =new ByteArrayOutputStream();
			    	response.setContentType("application/pdf");
			    	net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(print,baos);
			    	response.setContentLength(baos.size());
			    	ServletOutputStream outStream = response.getOutputStream();
			    	baos.writeTo(outStream);
			    	outStream.flush();
			    	outStream.close();
			   
			    }
			} catch (Exception ex) {
				ex.printStackTrace();
			} 
			
			}else{
				 response.getWriter().print("<html> <script type='text/javascript'> alert('Please Enter Valid Date...!!');window.location.href = '/CBISociety/reports.jsp'; window.close()</script></html>");
			}
			
		}
//-----------------------------------------------------------------------------------------------------
													
		}	


	}

}
