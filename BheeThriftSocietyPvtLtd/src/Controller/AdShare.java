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

import Model.Bean.ShareBean;
import Model.Bean.UserBean;
import Model.Dao.ShareDao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class AdShare
 */
@WebServlet("/AdShare")
public class AdShare extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShareDao dao=null;
    private UserBean user=null;
    public AdShare() {
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
			dao=new ShareDao();
			boolean isactive=true;
			double per_share_rate=0;
			int rowsUpdated = 0;
			String f_date=request.getParameter("f_date");
			String action=request.getParameter("action");
			String rate=request.getParameter("per_share_rate");
			
			HttpSession session=request.getSession(false);
			user=(UserBean)session.getAttribute("userbean");
			
			String[] AppMessage = new String[2];
			AppMessage[0] = "alert-info";
			AppMessage[1] = "welcome";
		    session.setAttribute("AppMessage", AppMessage);
			
			if(action.equals("list")){
				try{
				List<ShareBean> lst=new ArrayList<ShareBean>();
				lst=new ShareDao().getAllShare();
				//Convert Java Object to Json
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(lst, new TypeToken<List<ShareBean>>() {}.getType());
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
			
			
			
			if(rate!=null){
				try{
					per_share_rate =Double.parseDouble(rate);
				}catch(NumberFormatException n){
					n.printStackTrace();
					
				}
			}
			
			
			if(per_share_rate!=0 ){
							
			if(f_date!=null){
									
				if(action.equals("insert")){
					
					if(per_share_rate !=0 && f_date!=""){
						isactive=Boolean.parseBoolean("isactive");	
						ShareBean bean=new ShareBean();
						bean.setPer_share_rate(per_share_rate);
						bean.setF_date(f_date);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setT_date("Till Now");
						
						ShareBean s=new ShareDao().getShareMaxId();
						s.setIsactive(false);
						s.setUpdatedby(user.getAd_user_id());
						s.setT_date(new java.util.Date().toString());
						new ShareDao().updateShareStatus(s);
						
						rowsUpdated = dao.addShare(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully inserted!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("share.jsp");
					}else{
						System.out.print("Share / Date not selected");
					}
				}else{
					ShareBean bean=new ShareBean();
					
					//bean.setName(name);
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
				//	bean.setAd_ac_type_id(ad_ac_type_id);
					bean.setIsactive(isactive);
					
					rowsUpdated = dao.addShare(bean);
					
					if (rowsUpdated > 0) {
						
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Share send for approval successfully !";
						 
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_account_group.jsp");
					
				}
				
				
				
			}
		
		
			
							
			}else{
				System.out.println("Null value inside designation level Detail");
			}
			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}
	
}
