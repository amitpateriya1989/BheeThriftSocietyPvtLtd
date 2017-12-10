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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import MasterValidation.Validation;
import Model.Bean.BankBean;
import Model.Bean.StateBean;
import Model.Bean.UserBean;
import Model.Dao.BankDao;
import Model.Dao.StateDao;


/**
 * Servlet implementation class AdState
 */
@WebServlet("/AdState")
public class AdState extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StateDao dao=null;
    private UserBean user=null;
    private Validation valid = null;
    public AdState() {
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
			try{
				dao=new StateDao();
				valid = new Validation();
				
				boolean isactive=true;
				int ad_state_id=0;
				int rowsUpdated = 0;
				
				String action=request.getParameter("action");
				if(action!=null ){
					
					String state_id="";
					String state ="";
					String status ="";
				
					HttpSession session=request.getSession(false);
					user=(UserBean)session.getAttribute("userbean");
					
					String[] AppMessage = new String[2];
					AppMessage[0] = "alert-info";
					AppMessage[1] = "welcome";
				    session.setAttribute("AppMessage", AppMessage);
					
				if(action.equals("edit")){
					
					state_id=request.getParameter("ad_state_id");
					response.sendRedirect("edit_state.jsp?ad_state_id="+state_id);
					
				}else if(action.equals("list")){
					try{
					List<StateBean> lst=new ArrayList<StateBean>();
					lst=new StateDao().getAllState();
					//Convert Java Object to Json
					Gson gson = new Gson();
					JsonElement element = gson.toJsonTree(lst, new TypeToken<List<StateBean>>() {}.getType());
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

					state_id=request.getParameter("ad_state_id");
					state =request.getParameter("state");
					status = request.getParameter("status");
					
					if(valid.validNotEmpty(state_id)==false && valid.validDigits(state_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Salutation Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_state.jsp");
					}else if(valid.validNotEmpty(state)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Sate Name is required field!";
					}else if(valid.validAlphaSpaceDot(state)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter and dot(.) symbol only in Sate Name Field!";
					}else if(valid.validNotEmpty(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else if(valid.validAlphaNum(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status Id should be numeric!";
					}else{
						
						try{
							ad_state_id=Integer.parseInt(state_id);
						}catch(NumberFormatException n){
							n.printStackTrace();
						}
						isactive=Boolean.parseBoolean(status);	
						StateBean bean=new StateBean();
						bean.setState(state);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_state_id(ad_state_id);
						rowsUpdated = dao.updateState(bean);
						
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully Updated!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
						
						}//end validation
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_state.jsp");
					
				}else if(action.equals("insert")){
					
					state = request.getParameter("state");
					
					if(valid.validNotEmpty(state)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Sate Name is required field!";
					}else if(valid.validAlphaSpaceDot(state)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter and dot(.) symbol only in Sate Name Field!";
					}else{
					
					StateBean bean=new StateBean();
					bean.setState(state);
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					rowsUpdated = dao.addState(bean);
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Updated!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
					}//end validation
					
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_state.jsp");
					
				}else{
					response.sendRedirect("ad_state.jsp");
				}

			}//end action

			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}//end class