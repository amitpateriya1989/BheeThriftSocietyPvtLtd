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

import Model.Bean.TypeOfFdBean;
import Model.Bean.UserBean;
import Model.Dao.TypeOfFdDao;

/**
 * Servlet implementation class TypeOfFd
 */
@WebServlet("/AdTypeOfFd")
public class AdTypeOfFd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdTypeOfFd() {
        super();
        // TODO Auto-generated constructor stub
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		try{
			 
			int rowsUpdated =0;
			String action=request.getParameter("action");
			
			boolean isactive=true;
		if(action!=null){
			String name=request.getParameter("name");
			HttpSession session=request.getSession(false);
			UserBean user=(UserBean)session.getAttribute("userbean");
			
			String[] AppMessage = new String[2];
			AppMessage[0] = "alert-info";
			AppMessage[1] = "welcome";
		    session.setAttribute("AppMessage", AppMessage);
			
			if(action.equals("list")){
				try{
				List<TypeOfFdBean> lst=new ArrayList<TypeOfFdBean>();
				lst=new TypeOfFdDao().getAlltypeoffd();
				//Convert Java Object to Json
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(lst, new TypeToken<List<TypeOfFdBean>>() {}.getType());
				JsonArray jsonArray = element.getAsJsonArray();
				String listData=jsonArray.toString();
				//Return Json in the format required by jTable plugin
				listData="{\"Result\":\"OK\",\"Records\":"+listData+"}"; 
				response.setContentType("application/json");
				response.getWriter().print(listData);
				//System.out.println(listData);
				}catch(Exception ex){
					String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace()+"}";
					PrintWriter pw=response.getWriter();
					pw.print(error);
				}
			}else if(action.equals("edit")){
				String id=request.getParameter("ad_type_of_fd_id");
				response.sendRedirect("edit_type_of_fd.jsp?ad_type_of_fd_id="+id);
				
			}else if(action.equals("update")){
				String id=request.getParameter("ad_type_of_fd_id");
				int ad_type_of_fd_id=Integer.parseInt(id);
				TypeOfFdBean bean=new TypeOfFdBean();
				bean.setName(name);
				bean.setAd_type_of_fd_id(ad_type_of_fd_id);
				bean.setUpdatedby(user.getAd_user_id());
				bean.setIsactive(true);
				
				rowsUpdated = new TypeOfFdDao().updatetypeoffd(bean);
				
				if (rowsUpdated > 0) {
					AppMessage[0] = "alert-success";
					AppMessage[1] = "Data successfully Updated!";
				}else{
					AppMessage[0] = "alert-info";
					AppMessage[1] = "Sorry please try again latter!";
				}
				session.setAttribute("AppMessage", AppMessage);
				response.sendRedirect("ad_fd_type.jsp");
					
				}else{
					
					TypeOfFdBean bean=new TypeOfFdBean();
					bean.setName(name);
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					rowsUpdated = new TypeOfFdDao().addTypeOfFd(bean);
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Inserted!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_fd_type.jsp");
					
				}
				
		
		
		}
			}catch(Exception e){
				e.printStackTrace();
			
			}
			
		}
	

}
