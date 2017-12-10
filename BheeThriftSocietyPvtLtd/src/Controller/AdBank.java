package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import MasterValidation.Validation;
import Model.Bean.BankBean;
import Model.Bean.GenderBean;
import Model.Bean.SalutationBean;
import Model.Bean.UserBean;
import Model.Dao.BankDao;
import Model.Dao.GenderDao;
import Model.Dao.SalutationDao;

/**
 * Servlet implementation class AdBank
 */
@WebServlet("/AdBank")
public class AdBank extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankDao dao=null;
	private UserBean user=null; 
	private Validation valid = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdBank() {
        super();
       
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

		dao=new BankDao();
		valid =  new Validation();
		
		
		try{
			
			String bank="";
			String bank_id= "";
			String status="";
			
			boolean isactive=true;
			int ad_bank_id=0;
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
				
				bank_id=request.getParameter("ad_bank_id");
				response.sendRedirect("edit_bank.jsp?ad_bank_id="+bank_id);
				
			}else if(action.equals("list")){
				
				try{
				List<BankBean> lst=new ArrayList<BankBean>();
				lst=new BankDao().getAllBank();
				//Convert Java Object to Json
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(lst, new TypeToken<List<BankBean>>() {}.getType());
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
				
				bank_id=request.getParameter("ad_bank_id");
				bank=request.getParameter("bank");
				status =request.getParameter("status");
				
				if(valid.validNotEmpty(bank_id)==false && valid.validDigits(bank_id) == false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Invalid Bank Id!";
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_bank.jsp");
				}else if(valid.validNotEmpty(bank)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Bank Name is required field!";
				}else if(valid.validAlphaSpaceDot(bank)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter only charecter and dot(.) symbol only!";
				}else if(valid.validNotEmpty(request.getParameter("status"))==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Status is required field!";
				}else if(valid.validAlphaNum(request.getParameter("status"))==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Status Id should be numeric!";
				}else{
					
					ad_bank_id=Integer.parseInt(bank_id);
					isactive=Boolean.parseBoolean(status);
					
					BankBean bean=new BankBean();
					bean.setBank(bank);
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					bean.setAd_bank_id(ad_bank_id);
					rowsUpdated = dao.updateBank(bean);
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Updated!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
				}//end validation				
				
				session.setAttribute("AppMessage", AppMessage);
				response.sendRedirect("ad_bank.jsp");
				
				
			}else if(action.equals("insert")){
				
				bank=request.getParameter("bank");
				
				if(valid.validNotEmpty(bank)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Bank Name is required field!";
				}else if(valid.validAlphaSpaceDot(bank)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter only charecter and dot(.) symbol only!";
				}else{
				
				BankBean bean=new BankBean();
				bean.setBank(bank);
				bean.setCreatedby(user.getAd_user_id());
				bean.setUpdatedby(user.getAd_user_id());
				bean.setIsactive(isactive);
				rowsUpdated = dao.addBank(bean);
				
				if (rowsUpdated > 0) {
					AppMessage[0] = "alert-success";
					AppMessage[1] = "Data successfully Updated!";
				}else{
					AppMessage[0] = "alert-info";
					AppMessage[1] = "Sorry please try again latter!";
				}
				
				}//end validation
				session.setAttribute("AppMessage", AppMessage);
				response.sendRedirect("ad_bank.jsp");
				
			}//end insert function

		}else{
			System.out.print("bank with null value");
			response.sendRedirect("ad_bank.jsp");
		}//end action null or not
	
	
		}catch(Exception e){
			e.printStackTrace();
		
		}
		
	}//end post method
	
}//end class