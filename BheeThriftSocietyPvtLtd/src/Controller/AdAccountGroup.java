package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Model.Bean.AccountGroupBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.UserBean;
import Model.Dao.AccountGroupDao;
import Model.Dao.AccountGroupDao;
import Model.Dao.MemberRegistrationDao;

/**
 * Servlet implementation class AdAccountGroup
 */
@WebServlet("/AdAccountGroup")
public class AdAccountGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountGroupDao dao=null;
    private UserBean user=null;
    public AdAccountGroup() {
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
			 dao=new AccountGroupDao();
			boolean isactive=true;
			String name =request.getParameter("name");
			String action=request.getParameter("action");
			
							
			if(action!=null){
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				int ad_ac_group_id=0;
				int ad_ac_type_id=0;
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				if(action.trim().equals("ac_group_by_type")){
					
					ad_ac_type_id=Integer.parseInt(request.getParameter("ad_ac_type_id"));
					
					List<AccountGroupBean> bean=dao.getAllAccountGroupByType(ad_ac_type_id);
					//Convert Java Object to Json
					Gson gson = new Gson();
					JsonObject myObject=new JsonObject();
					JsonElement element = gson.toJsonTree(bean);
					if(bean== null){
						myObject.addProperty("success", false);
					}
					else {
						myObject.addProperty("success", true);
					} 
					myObject.add("AccountGroup", element);
					response.setContentType("application/json");
					response.getWriter().print(myObject);
				
				}else if(action.trim().equals("edit")){
					String id=request.getParameter("ad_account_group_id");
					response.sendRedirect("edit_account_group.jsp?id="+id);
					
					
				}else if(action.trim().equals("update")){
					try{
						ad_ac_group_id=Integer.parseInt(request.getParameter("ad_ac_group_id"));
						ad_ac_type_id=Integer.parseInt(request.getParameter("ad_ac_type_id"));
						boolean isActive = Boolean.parseBoolean(request.getParameter("status"));
						int rowsUpdated =0;
						
						if(ad_ac_group_id!=0 ){	
							AccountGroupBean bean=new AccountGroupBean();
							bean.setName(name);
							bean.setUpdatedby(user.getAd_user_id());
							bean.setIsactive(isActive);
							bean.setAd_ac_type_id(ad_ac_type_id);
							bean.setAd_ac_group_id(ad_ac_group_id);
							rowsUpdated = dao.updateAccountGroup(bean);
							
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully updated!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
							session.setAttribute("AppMessage", AppMessage);
							
							response.sendRedirect("edit_account_group.jsp?id="+ad_ac_group_id);
						}else{
							System.out.print("Account Group not selected");
						}
						
					}catch(NumberFormatException ne){
						System.out.println("Error in Id Format");
						ne.printStackTrace();
					}
					
					
				}else{
					AccountGroupBean bean=new AccountGroupBean();
					try{
						ad_ac_type_id=Integer.parseInt(request.getParameter("ad_ac_type_id"));
						int rowsUpdated = 0;
						
						bean.setName(name);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setAd_ac_type_id(ad_ac_type_id);
						bean.setIsactive(isactive);	
						int r =new AccountGroupDao().getAccountGroupno();
						bean.setGroup_ac_no((r+10000));
						rowsUpdated = dao.addAccountGroup(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully inserted!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						session.setAttribute("AppMessage", AppMessage);
						
					}catch(NumberFormatException ne){
						System.out.println("Error in Id Format");
						ne.printStackTrace();
					}

					response.sendRedirect("ad_account_group.jsp");
					
				}
				
				
				
			}
		
		
			
							
			
			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}
	
}
