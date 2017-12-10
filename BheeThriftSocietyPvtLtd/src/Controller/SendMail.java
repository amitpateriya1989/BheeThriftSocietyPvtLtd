
package Controller;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*; 
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  

import Model.Dao.EMailUtility;



/**
 * Servlet implementation class SendMail
 */
@WebServlet("/SendMail")
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private String host;
	private String port;
	private String user;
	private String pass;
	
	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}
	
    public SendMail() {
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
		// TODO Auto-generated method stub
		
	String action=request.getParameter("action");
	
	if(action.equals("demandlist")){
		Calendar cal = Calendar.getInstance();
		String []bank=request.getParameterValues("bank");
		String subject="Demand List for the month of "+cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		String resultMessage = "";
		
		String content = "Dear Sir,\n please find the attached demand list of the month "+cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		for(String email:bank){
		
			try {
				EMailUtility.sendEmailWithAttachment( "amitpateriya.1989@gmail.com", subject,content);
				resultMessage = "The e-mail was sent successfully";
			} catch (Exception ex) {
				ex.printStackTrace();
				resultMessage = "There were an error: " + ex.getMessage();
			} finally {
				request.setAttribute("Message", resultMessage);
				}
			
				
	}
		
	}		
	}
}
