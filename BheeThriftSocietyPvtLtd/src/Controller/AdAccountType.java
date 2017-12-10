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

import Model.Bean.AccountTypeBean;
import Model.Bean.BankBean;
import Model.Bean.DesignationLevelBean;
import Model.Bean.UserBean;
import Model.Dao.AccountTypeDao;
import Model.Dao.BankDao;
import Model.Dao.DesignationLevelDao;

/**
 * Servlet implementation class AdAccountType
 */
@WebServlet("/AdAccountType")
public class AdAccountType extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountTypeDao dao=null;
    private UserBean user=null;
    public AdAccountType() {
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
			dao=new AccountTypeDao();
			boolean isactive=true;
			
			String action=request.getParameter("action");
			
							
			if(action!=null){
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				if(action.equals("list")){
					try{
					List<AccountTypeBean> lst=new ArrayList<AccountTypeBean>();
					lst=new AccountTypeDao().getAllAccountType();
					//Convert Java Object to Json
					Gson gson = new Gson();
					JsonElement element = gson.toJsonTree(lst, new TypeToken<List<AccountTypeBean>>() {}.getType());
					JsonArray jsonArray = element.getAsJsonArray();
					String listData=jsonArray.toString();
					//Return Json in the format required by jTable plugin
					listData="{\"Result\":\"OK\",\"Records\":"+listData+"}"; 
					response.setContentType("application/json");
					response.getWriter().print(listData);
					System.out.println(listData);
					}catch(Exception ex){
						String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace()+"}";
						PrintWriter pw=response.getWriter();
						pw.print(error);
					}
				}
				
				
					if(action.equals("edit")){
					String ad_ac_type_id=request.getParameter("ad_ac_type_id");
					response.sendRedirect("edit_acount_type.jsp?ad_ac_type_id="+ad_ac_type_id);
				
					}else if(action.equals("update")){
						
						try{
							String name =request.getParameter("name");
							boolean isActive = Boolean.parseBoolean(request.getParameter("status"));
							int ad_ac_type_id = Integer.parseInt(request.getParameter("ad_ac_type_id"));
							int rowsUpdated = 0;
							
							if(!name.equals("") ){
								
								if(ad_ac_type_id!=0){	
									AccountTypeBean bean=new AccountTypeBean();
									bean.setName(name);
									bean.setUpdatedby(user.getAd_user_id());
									bean.setIsactive(isActive);
									bean.setAd_ac_type_id(ad_ac_type_id);
									rowsUpdated = dao.updateAccountType(bean);
									
									if (rowsUpdated > 0) {
										AppMessage[0] = "alert-success";
										AppMessage[1] = "Data successfully updated!";
									}else{
										AppMessage[0] = "alert-info";
										AppMessage[1] = "Sorry please try again latter!";
									}
									session.setAttribute("AppMessage", AppMessage);

								}else{
									System.out.print("Account Type not selected");
								}
								
								response.sendRedirect("edit_acount_type.jsp?ad_ac_type_id="+ad_ac_type_id);
							}
						}catch(Exception ex){
							ex.printStackTrace();						
						}
						
						
				}else{
					String name =request.getParameter("name");
					int rowsUpdated = 0;
					
					 if(!name.equals("") ){
						AccountTypeBean bean=new AccountTypeBean();
						bean.setName(name);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						rowsUpdated = dao.addAccountType(bean);
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully inserted!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						session.setAttribute("AppMessage", AppMessage);
					 }
					response.sendRedirect("ad_account_type.jsp");
					
				}

			}

			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}
	
}
