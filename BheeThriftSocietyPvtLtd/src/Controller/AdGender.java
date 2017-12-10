package Controller;

import java.io.IOException;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





import MasterValidation.Validation;
import Model.Bean.GenderBean;
import Model.Bean.UserBean;
import Model.Dao.GenderDao;

/**
 * Servlet implementation class AdGender
 */
@WebServlet("/AdGender")
public class AdGender extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GenderDao dao=null;
    private UserBean user=null;
    private Validation valid = new Validation();
    public AdGender() {
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
	
			dao=new GenderDao();
			
			int rowsUpdated =0;
			boolean isactive=true;
			String action=request.getParameter("action");

			if(action!=null){
			
			HttpSession session=request.getSession(false);
			user=(UserBean)session.getAttribute("userbean");
			
			String[] AppMessage = new String[2];
			AppMessage[0] = "alert-info";
			AppMessage[1] = "welcome";
		    session.setAttribute("AppMessage", AppMessage);
			
			if(action.equals("edit")){
				
				String gender_id=request.getParameter("ad_gender_id");
				response.sendRedirect("edit_gender.jsp?ad_gender_id="+gender_id);
				
			}else if(action.equals("update")){
				
					String gender=request.getParameter("gender");
					String gender_id=request.getParameter("ad_gender_id");
					int ad_gender_id=0;
					
					if(valid.validNotEmpty(gender_id)==false && valid.validDigits(gender_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Gender Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_gender.jsp");
					}else if(valid.validNotEmpty(gender)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Bank Gender Name is required field!";
					}else if(valid.validAlphaSpaceDot(gender)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter and dot(.) symbol only in Bank Gender Name Field!";
					}else if(valid.validNotEmpty(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else if(valid.validAlphaNum(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status Id should be numeric!";
					}else{
						
						try{
							ad_gender_id=Integer.parseInt(gender_id);
						}catch(Exception e){
							e.printStackTrace();
						}
					
					isactive=Boolean.parseBoolean(request.getParameter("status"));	
					GenderBean bean=new GenderBean();
					bean.setAd_gender_id(ad_gender_id);
					bean.setGender(gender);
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					rowsUpdated  = dao.updateGender(bean);
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Updated!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					}//end validation
					
					session.setAttribute("AppMessage", AppMessage);	
					response.sendRedirect("ad_gender.jsp");
						
			}else{
					
					String gender=request.getParameter("gender");
					
					if(valid.validNotEmpty(gender)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Bank Gender Name is required field!";
					}else if(valid.validAlphaSpaceDot(gender)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter and dot(.) symbol only in Bank Gender Name Field!";
					} else{
						GenderBean bean=new GenderBean();
						bean.setGender(gender);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						rowsUpdated = dao.addGender(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					}//end validation					
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_gender.jsp");
			}//end insert method

	    }//end action
		}catch(Exception e){
			e.printStackTrace();
		
		}
		
	}//end post method

}//end class