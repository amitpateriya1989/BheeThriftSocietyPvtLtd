package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MasterValidation.Validation;
import Model.Bean.CategoryBean;
import Model.Bean.UserBean;
import Model.Dao.CategoryDao;

/**
 * Servlet implementation class AdCategory
 */
@WebServlet("/AdCategory")
public class AdCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoryDao dao=null;
	private CategoryBean bean=null;
    private UserBean user=null;
    private Validation valid = null;
    public AdCategory() {
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
try{
			
			dao=new CategoryDao();
			valid = new Validation();
			
			String category = "";
			String category_id ="";
			String stauts = "";
			
			String action=request.getParameter("action");
							
			if(action!=null){
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				int ad_category_id=0;
				boolean isactive=true;
				int rowsUpdated = 0;
							
				if(action.equals("edit")){
					ad_category_id=Integer.parseInt(request.getParameter("ad_category_id"));
					response.sendRedirect("edit_category.jsp?ad_category_id="+ad_category_id);
				
				}else if(action.equals("update")){
					
					category =request.getParameter("category");
					category_id = request.getParameter("ad_category_id");
					stauts = request.getParameter("status");
					
					if(valid.validNotEmpty(category_id)==false && valid.validDigits(category_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Category Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_category.jsp");
					}else if(valid.validNotEmpty(category)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Category Name is required field!";
					}else if(valid.validAlphabet(category)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter in Category Name Field!";
					}else if(valid.validNotEmpty(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else if(valid.validAlphaNum(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status Id should be numeric!";
					}else{
						
						ad_category_id=Integer.parseInt(category_id);
						isactive=Boolean.parseBoolean(stauts);	
						
							bean=new CategoryBean();
							bean.setCategory(category);
							bean.setUpdatedby(user.getAd_user_id());
							bean.setIsactive(isactive);
							bean.setAd_category_id(ad_category_id);
							rowsUpdated = dao.updateCategory(bean);
							
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully Inserted!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
						
					}//end validation					
					
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_category.jsp");
					
				}else if(action.equals("insert")){
					category =request.getParameter("category");
					
					if(valid.validNotEmpty(category)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Category Name is required field!";
					}else if(valid.validAlphabet(category)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter in Category Name Field!";
					}else{
						CategoryBean bean=new CategoryBean();
						bean.setCategory(category);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						rowsUpdated = dao.addCategory(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Inserted!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					}					
					
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_category.jsp");
				}//end insert action
				else{
					AppMessage[0] = "alert-danger";
					AppMessage[1] = "Invalid Action!";
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_category.jsp");
				}
				
			}//end action

			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}//end class