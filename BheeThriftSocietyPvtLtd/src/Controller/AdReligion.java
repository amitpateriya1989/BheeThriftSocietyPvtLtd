package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MasterValidation.Validation;
import Model.Bean.ReligionBean;
import Model.Bean.UserBean;
import Model.Dao.DBConnection;
import Model.Dao.ReligionDao;

/**
 * Servlet implementation class AdReligion
 */
@WebServlet("/AdReligion")
public class AdReligion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReligionDao dao=null;
	private ReligionBean bean=null;
    private UserBean user=null;
    private Validation valid = new Validation();
    public AdReligion() {
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
try{
			
			boolean isactive=true;
			dao=new ReligionDao();
			String action=request.getParameter("action");
			int rowsUpdated = 0;
			
			String religion = "";
			
							
			if(action!=null){
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				int ad_religion_id=0;
							
				if(action.equals("edit")){
					ad_religion_id=Integer.parseInt(request.getParameter("ad_religion_id"));
					response.sendRedirect("edit_religion.jsp?ad_religion_id="+ad_religion_id);
				
				}else if(action.equals("update")){
					religion =request.getParameter("religion");
					
					if(valid.validNotEmpty(request.getParameter("ad_religion_id"))==false && valid.validDigits(request.getParameter("ad_religion_id")) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Religion Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_religion.jsp");
					}else if(valid.validNotEmpty(religion)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Religion Name is required field!";
					}else{
						
						ad_religion_id=Integer.parseInt(request.getParameter("ad_religion_id"));
						isactive=Boolean.parseBoolean(request.getParameter("status"));	
						bean=new ReligionBean();
						bean.setReligion(religion);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_religion_id(ad_religion_id);
						rowsUpdated = dao.updateReligion(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						
					}//end validation
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_religion.jsp");
				}else{
					
					religion =request.getParameter("religion");
					if(valid.validNotEmpty(religion)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Religion Name is required field!";
					}else{
					ReligionBean bean=new ReligionBean();
					bean.setReligion(religion);
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					rowsUpdated = dao.addReligion(bean);
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Inserted!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
					}
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_religion.jsp");
				}//end insert method
				
				
			}//end check action
	
			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}//end class