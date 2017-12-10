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
import Model.Bean.ListItemBean;
import Model.Bean.UserBean;
import Model.Dao.ListItemDao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class AdListItem
 */
@WebServlet("/AdListItem")
public class AdListItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ListItemDao dao=null;
    private UserBean user=null;
    private Validation valid = null;
    public AdListItem() {
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
				dao=new ListItemDao();
				valid = new Validation();
				
				boolean isactive=true;
				int ad_list_id=0;
				int ad_list_item_id=0;
				int rowsUpdated = 0;
				
				String action=request.getParameter("action");
				if(action!=null ){
					
					String list_id="";
					String list_item_id="";
					String name ="";
					String status ="";
				
					HttpSession session=request.getSession(false);
					user=(UserBean)session.getAttribute("userbean");
					
					String[] AppMessage = new String[2];
					AppMessage[0] = "alert-info";
					AppMessage[1] = "welcome";
				    session.setAttribute("AppMessage", AppMessage);
					
				if(action.equals("edit")){
					
					list_item_id=request.getParameter("ad_list_item_id");
					response.sendRedirect("edit_list_item.jsp?ad_list_item_id="+list_item_id);
					
				}else if(action.equals("list")){
					try{
					List<ListItemBean> lst=new ArrayList<ListItemBean>();
					lst=new ListItemDao().getAllListItem();
					//Convert Java Object to Json
					Gson gson = new Gson();
					JsonElement element = gson.toJsonTree(lst, new TypeToken<List<ListItemBean>>() {}.getType());
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
					list_id=request.getParameter("ad_list_id");
					list_item_id=request.getParameter("ad_list_item_id");
					name =request.getParameter("name");
					status = request.getParameter("status");
					
					if(valid.validNotEmpty(list_item_id)==false && valid.validDigits(list_item_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Item Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_list_item.jsp");
					}else if(valid.validNotEmpty(list_id)==false && valid.validDigits(list_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid List Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_list.jsp");
					}else if(valid.validNotEmpty(name)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "List Item Name is required field!";
					}else if(valid.validNotEmpty(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else if(valid.validAlphaNum(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status Id should be numeric!";
					}else{
						
						try{
							ad_list_item_id=Integer.parseInt(list_item_id);
							ad_list_id=Integer.parseInt(list_id);
						}catch(NumberFormatException n){
							n.printStackTrace();
						}
						isactive=Boolean.parseBoolean(status);	
						ListItemBean bean=new ListItemBean();
						bean.setName(name);
						bean.setAd_list_id(ad_list_id);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_list_item_id(ad_list_item_id);
						rowsUpdated = dao.updateListItem(bean);
						
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully Updated!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
						
						}//end validation
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_list_item.jsp?ad_list_id="+ad_list_id);
					
				}else if(action.equals("insert")){
					
					name = request.getParameter("name");
					list_id=request.getParameter("ad_list_id");
					
					if(valid.validNotEmpty(list_id)==false && valid.validDigits(list_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid List Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_list.jsp");
					}else if(valid.validNotEmpty(name)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "List Item Name is required field!";
					}else{
						try{
							
							ad_list_id=Integer.parseInt(list_id);
						}catch(NumberFormatException n){
							n.printStackTrace();
						}
					ListItemBean bean=new ListItemBean();
					bean.setName(name);
					bean.setAd_list_id(ad_list_id);
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					rowsUpdated = dao.addListItem(bean);
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Updated!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
					}//end validation
					
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_list_item.jsp?ad_list_id="+ad_list_id);
					
				}else{
					response.sendRedirect("ad_list_.jsp");
				}

			}//end action

			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}
