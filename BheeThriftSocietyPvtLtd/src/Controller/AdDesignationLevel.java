package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MasterValidation.Validation;
import Model.Bean.DesignationLevelBean;
import Model.Bean.DesignationBean;
import Model.Bean.DesignationLevelBean;
import Model.Bean.UserBean;
import Model.Dao.DesignationLevelDao;
import Model.Dao.DesignationDao;
import Model.Dao.DesignationLevelDao;

/**
 * Servlet implementation class AdDesignationLevel
 */
@WebServlet("/AdDesignationLevel")
public class AdDesignationLevel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DesignationLevelDao dao=null;
	private DesignationLevelBean bean=null;
    private UserBean user=null;
    private Validation valid = new Validation();
    public AdDesignationLevel() {
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
			
			boolean isactive=true;
			int rowsUpdated = 0;
			dao=new DesignationLevelDao();
			String action=request.getParameter("action");
			
							
			if(action!=null){
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				int ad_designation_level_id=0;
							
				if(action.equals("edit")){
					ad_designation_level_id=Integer.parseInt(request.getParameter("ad_designation_level_id"));
					response.sendRedirect("edit_designation_level.jsp?ad_designation_level_id="+ad_designation_level_id);
				
				}else if(action.equals("update")){
					String designation_level =request.getParameter("designation_level");
					
					if(valid.validNotEmpty(request.getParameter("ad_designation_level_id"))==false && valid.validDigits(request.getParameter("ad_designation_level_id")) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Designation Level Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_designation_level.jsp");
					}else if(valid.validNotEmpty(designation_level)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Designation Level Name is required field!";
					}else if(valid.validAlphaSpaceDot(designation_level)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter and dot(.) symbol only in Designation Level Field!";
					}else{
						
						ad_designation_level_id=Integer.parseInt(request.getParameter("ad_designation_level_id"));
						
						isactive=Boolean.parseBoolean("isactive");	
						bean=new DesignationLevelBean();
						bean.setDesignation_level(designation_level);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_designation_level_id(ad_designation_level_id);
						rowsUpdated = dao.updateDesignationLevel(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						
					}//end validation

					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_designation_level.jsp");
				}else{
					String designation_level =request.getParameter("designation_level");
				
					if(valid.validNotEmpty(designation_level)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Designation Level Name is required field!";
					}else if(valid.validAlphaSpaceDot(designation_level)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter and dot(.) symbol only in Designation Level Field!";
					}else{
						
						DesignationLevelBean bean=new DesignationLevelBean();
						bean.setDesignation_level(designation_level);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						rowsUpdated = dao.addDesignationLevel(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						
					}//end validation

					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_designation_level.jsp");
				}//end insert method
				
				
			}//end action		
							
			
			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}//end class