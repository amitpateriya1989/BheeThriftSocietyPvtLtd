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
import Model.Bean.DesignationTypeBean;
import Model.Bean.UserBean;
import Model.Dao.DesignationLevelDao;
import Model.Dao.DesignationTypeDao;

/**
 * Servlet implementation class AdDesignationType
 */
@WebServlet("/AdDesignationType")
public class AdDesignationType extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DesignationTypeDao dao=null;
	private DesignationTypeBean bean=null;
	private Validation valid = new Validation();
    private UserBean user=null;
    
    public AdDesignationType() {
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
			dao=new DesignationTypeDao();
			boolean isactive=true;
			int rowsUpdated =0;
			
			String action=request.getParameter("action");
			
							
			if(action!=null){
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				int ad_designation_type_id=0;
					
				if(action.equals("edit")){
					
					String designation_type_id = request.getParameter("ad_designation_type_id");
					response.sendRedirect("edit_designation_type.jsp?ad_designation_type_id="+designation_type_id);
						
				}else if(action.equals("update")){
					String designation_type =request.getParameter("designation_type");
					
					if(valid.validNotEmpty(request.getParameter("ad_designation_type_id"))==false && valid.validDigits(request.getParameter("ad_designation_type_id")) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Designation Type Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_designation_type.jsp");
					}else if(valid.validNotEmpty(designation_type)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Bank Designation Type Name is required field!";
					}else if(valid.validAlphaNumSpace(designation_type)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter , number and space only in Designation Type Name Field!";
					}else if(valid.validNotEmpty(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else if(valid.validAlphaNum(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status Id should be numeric!";
					}else{
					
					ad_designation_type_id=Integer.parseInt(request.getParameter("ad_designation_type_id"));
					
						isactive=Boolean.parseBoolean(request.getParameter("status"));	
						bean=new DesignationTypeBean();
						bean.setDesignation_type(designation_type);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_designation_type_id(ad_designation_type_id);
						rowsUpdated = dao.updateDesignationType(bean);
					}//end validation
					
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_designation_type.jsp");
					
				}else{
					String designation_type =request.getParameter("designation_type");
					
					if(valid.validNotEmpty(designation_type)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Designation Type Name is required field!";
					}else if(valid.validAlphaNumSpace(designation_type)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter , number and space only in Designation Type Name Field!";
					}else{
						bean=new DesignationTypeBean();
						bean.setDesignation_type(designation_type);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						rowsUpdated = dao.addDesignationType(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					}//end validation
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_designation_type.jsp");
				}//end insert method
				
			}//end action

			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
     
}//end class