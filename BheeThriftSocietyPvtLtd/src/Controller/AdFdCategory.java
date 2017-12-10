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

import Model.Bean.FdCategoryBean;
import Model.Bean.UserBean;
import Model.Dao.FdCategoryDao;


/**
 * Servlet implementation class AdFdCategory
 */
@WebServlet("/AdFdCategory")
public class AdFdCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FdCategoryDao dao=null;
	private UserBean user=null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdFdCategory() {
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

		dao=new FdCategoryDao();
		try{
			System.out.print("inside FdCategory controller");
			
			boolean isactive=true;
			int rowsUpdated = 0;
			
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
				List<FdCategoryBean> lst=new ArrayList<FdCategoryBean>();
				lst=new FdCategoryDao().getAllFdCategory();
				//Convert Java Object to Json
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(lst, new TypeToken<List<FdCategoryBean>>() {}.getType());
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
			}else if(action.equals("edit")){
				
			String id=request.getParameter("ad_fd_category_id");
			response.sendRedirect("edit_fd_category.jsp?ad_fd_category_id="+id);
			
			}else if(action.equals("update")){
			String id=request.getParameter("ad_fd_category_id");
			int ad_fd_category_id=Integer.parseInt(id);
			String name=request.getParameter("name");
			FdCategoryBean bean=new FdCategoryBean();
			bean.setName(name);
			bean.setUpdatedby(user.getAd_user_id());
			bean.setAd_fd_category_id(ad_fd_category_id);
			bean.setIsactive(true);
			rowsUpdated = dao.updateFdCategory(bean);
			
			if (rowsUpdated > 0) {
				AppMessage[0] = "alert-success";
				AppMessage[1] = "Data successfully Updated!";
			}else{
				AppMessage[0] = "alert-info";
				AppMessage[1] = "Sorry please try again latter!";
			}
			session.setAttribute("AppMessage", AppMessage);
			response.sendRedirect("ad_fd_catagory.jsp");	
			}else{
				FdCategoryBean bean=new FdCategoryBean();
				String name=request.getParameter("name");
				bean.setName(name);
				bean.setCreatedby(user.getAd_user_id());
				bean.setUpdatedby(user.getAd_user_id());
				bean.setIsactive(isactive);
				rowsUpdated = dao.addFdCategory(bean);
				
				if (rowsUpdated > 0) {
					AppMessage[0] = "alert-success";
					AppMessage[1] = "Data successfully Inserted!";
				}else{
					AppMessage[0] = "alert-info";
					AppMessage[1] = "Sorry please try again latter!";
				}
				session.setAttribute("AppMessage", AppMessage);
				response.sendRedirect("ad_fd_catagory.jsp");
			}

		
	
	}else{
		System.out.print(" action with null value");
		
	}
		}catch(Exception e){
			e.printStackTrace();
		
		}
		
	}
}
