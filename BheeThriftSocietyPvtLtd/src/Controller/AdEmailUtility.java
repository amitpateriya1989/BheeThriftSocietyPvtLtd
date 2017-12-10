package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MasterValidation.Validation;
import Model.Bean.EmailUtilityBean;
import Model.Bean.UserBean;
import Model.Dao.EmailUtilityDao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class AdEmailUtility
 */
@WebServlet("/AdEmailUtility")
public class AdEmailUtility extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserBean user=null; 
	private Validation valid = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdEmailUtility() {
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
		
		
		valid =  new Validation();
		
		
		try{
			
			String email="";
			String pwd= "";
			String host_name= "";
			String port= "";
			String status="";
			
			boolean isactive=true;
			int ad_email_id=0;
			int rowsUpdated =0;
			
			String action=request.getParameter("action");

			if(action!=null){
			
			HttpSession session=request.getSession(false);
			user=(UserBean)session.getAttribute("userbean");
			String[] AppMessage = new String[2];
			AppMessage[0] = "alert-info";
			AppMessage[1] = "welcome";
		    session.setAttribute("AppMessage", AppMessage);			
			
			if(action.equals("edit")){
				
				String email_id=request.getParameter("ad_email_id");
				response.sendRedirect("edit_emailSetup.jsp?ad_email_id="+email_id);
				
			}else if(action.equals("list")){
				
				try{
				List<EmailUtilityBean> lst=new ArrayList<EmailUtilityBean>();
				lst=new EmailUtilityDao().getAllEmailUtility();
				//Convert Java Object to Json
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(lst, new TypeToken<List<EmailUtilityBean>>() {}.getType());
				JsonArray jsonArray = element.getAsJsonArray();
				String listData=jsonArray.toString();
				//Return Json in the format required by jTable plugin
				listData="{\"Result\":\"OK\",\"Records\":"+listData+"}"; 
				response.setContentType("application/json");
				response.getWriter().print(listData);
				
				}catch(Exception ex){
					String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace()+"}";
					PrintWriter pw=response.getWriter();
					pw.print(error);
				}
			}else if(action.equals("update")){
				
				String email_id=request.getParameter("ad_email_id");
				email=request.getParameter("email");
				pwd=request.getParameter("pwd");
				host_name=request.getParameter("host_name");
				port=request.getParameter("port_no");
				status =request.getParameter("status");
				
				if(valid.validNotEmpty(email_id)==false && valid.validDigits(email_id) == false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Invalid EmailUtility Id!";
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_email.jsp");
				}else if(valid.validNotEmpty(email)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "EmailUtility Name is required field!";
				}else if(valid.validEmail(email)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter valid email id!";
				}else if(valid.validEmail(email)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter valid email id!";
				}else if(valid.validNotEmpty(pwd)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter valid password!";
				}else if(valid.validNotEmpty(host_name)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter valid host_name!";
				}else if(valid.validAlphabet(host_name)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter valid hostname !";
				}else if(valid.validNotEmpty(port)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter valid Port No!";
				}else if(valid.validNumeric(port)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter valid Port No !";
				}else if(valid.validNotEmpty(request.getParameter("status"))==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Status is required field!";
				}else if(valid.validAlphaNum(request.getParameter("status"))==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Status Id should be numeric!";
				}else{
					
					ad_email_id=Integer.parseInt(email_id);
					isactive=Boolean.parseBoolean(status);
					
					EmailUtilityBean bean=new EmailUtilityBean();
					bean.setEmail_id(email);
					bean.setPwd(pwd);
					bean.setHost_name(host_name);
					bean.setPort_no(Integer.parseInt(port));
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					bean.setAd_email_id(ad_email_id);
					rowsUpdated = new EmailUtilityDao().updateEmailUtility(bean);
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Updated!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
				}//end validation				
				
				session.setAttribute("AppMessage", AppMessage);
				response.sendRedirect("emailSetup.jsp");
				
				
			}else if(action.equals("insert")){
				
				
				email=request.getParameter("email");
				pwd=request.getParameter("pwd");
				host_name=request.getParameter("host_name");
				port=request.getParameter("port_no");
				status ="true";
				
				 if(valid.validNotEmpty(email)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "EmailUtility Name is required field!";
				}else if(valid.validEmail(email)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter valid email id!";
				}else if(valid.validEmail(email)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter valid email id!";
				}else if(valid.validNotEmpty(pwd)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter valid password!";
				}else if(valid.validNotEmpty(host_name)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter valid host_name!";
				}else if(valid.validAlphabet(host_name)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter valid hostname !";
				}else if(valid.validNotEmpty(port)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter valid Port No!";
				}else if(valid.validNumeric(port)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter valid Port No !";
				}else if(valid.validNotEmpty(status)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Status is required field!";
				}else if(valid.validAlphaNum(status)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Status Id should be numeric!";
				}else{
					
					
					isactive=Boolean.parseBoolean(status);
					
					EmailUtilityBean bean=new EmailUtilityBean();
					bean.setEmail_id(email);
					bean.setPwd(pwd);
					bean.setHost_name(host_name);
					bean.setPort_no(Integer.parseInt(port));
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					bean.setAd_email_id(ad_email_id);
					rowsUpdated = new EmailUtilityDao().addEmailUtility(bean);
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Record Successfully Inserted!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
				}//end validation				
				
				session.setAttribute("AppMessage", AppMessage);
				response.sendRedirect("emailSetup.jsp");
			}

		}else{
			
			response.sendRedirect("emailSetup.jsp");
		}//end action null or not
	
	
		}catch(Exception e){
			e.printStackTrace();
		
		}
		
	}//end post method
		
		
		
		
		
	}


