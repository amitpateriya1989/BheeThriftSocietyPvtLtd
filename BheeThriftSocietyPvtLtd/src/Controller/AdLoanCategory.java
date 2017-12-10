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

import Model.Bean.LoanCategoryBean;
import Model.Bean.UserBean;
import Model.Dao.LoanCategoryDao;

/**
 * Servlet implementation class AdLoanCategory
 */
@WebServlet("/AdLoanCategory")
public class AdLoanCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoanCategoryDao dao=null;
	private UserBean user=null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdLoanCategory() {
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

		dao=new LoanCategoryDao();
		try{
			System.out.print("inside LoanCategory controller");
			String LoanCategory=null;
			boolean isactive=true;
			int rowsUpdated=0;
			int ad_loan_category_id = 0;
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
				List<LoanCategoryBean> lst=new ArrayList<LoanCategoryBean>();
				lst=new LoanCategoryDao().getAllLoanCategory();
				//Convert Java Object to Json
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(lst, new TypeToken<List<LoanCategoryBean>>() {}.getType());
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
		
		
			LoanCategory=request.getParameter("name");
			
			 if(action.equals("edit")){
				    String id=request.getParameter("id");
				    
					response.sendRedirect("edit_loan_category.jsp?ad_loan_category_id="+id);
			 }else if(action.equals("update")){
				 ad_loan_category_id	= Integer.parseInt(request.getParameter("ad_loan_category_id"));
				 isactive=Boolean.parseBoolean(request.getParameter("status"));	
				 
				if(ad_loan_category_id==0){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Enter valid Loan Category id";
					session.setAttribute("AppMessage", AppMessage);
				}else if(request.getParameter("name")==null || request.getParameter("name").equals("")==true){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Loan Category is required field!";
					session.setAttribute("AppMessage", AppMessage);
				}else{
				System.out.println(ad_loan_category_id);
				
				LoanCategoryBean bean=new LoanCategoryBean();
				bean.setAd_loan_category_id(ad_loan_category_id);
				bean.setName(LoanCategory);
				bean.setUpdatedby(user.getAd_user_id());
				bean.setIsactive(isactive);
				rowsUpdated= dao.updateLoanCategory(bean);
				if (rowsUpdated > 0) {
					AppMessage[0] = "alert-success";
					AppMessage[1] = "Data successfully updated!";
				}else{
					AppMessage[0] = "alert-info";
					AppMessage[1] = "Sorry please try again latter!";
				}
				session.setAttribute("AppMessage", AppMessage);
				}
				
				response.sendRedirect("ad_loan_category.jsp");	
			} 
				
			if(action.equals("insert")){
				
				if(request.getParameter("name")==null || request.getParameter("name").equals("")==true){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Loan Category is required field!";
					session.setAttribute("AppMessage", AppMessage);
				}else{
					LoanCategoryBean bean=new LoanCategoryBean();
					bean.setName(LoanCategory);
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					rowsUpdated= dao.addLoanCategory(bean);
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully inserted!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					session.setAttribute("AppMessage", AppMessage);
				
				}
				response.sendRedirect("ad_loan_category.jsp");
				
			}
	
	  }else{
		System.out.print(" action with null value");
	  }//end check action null
	}catch(Exception e){
			e.printStackTrace();
	}
		
	}//end post method
}//end class
