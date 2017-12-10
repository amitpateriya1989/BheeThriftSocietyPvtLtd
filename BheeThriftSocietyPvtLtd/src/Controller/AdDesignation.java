package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MasterValidation.Validation;
import Model.Bean.CityBean;
import Model.Bean.DesignationBean;
import Model.Bean.UserBean;
import Model.Dao.CityDao;
import Model.Dao.DesignationDao;
import Model.Dao.DesignationTypeDao;

/**
 * Servlet implementation class AdDesignation
 */
@WebServlet("/AdDesignation")
public class AdDesignation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DesignationDao dao=null;
    private UserBean user=null;
    private Validation valid = new Validation();
    public AdDesignation() {
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
			 dao=new DesignationDao();
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
				
				int ad_designation_id=0;
				int ad_designation_type_id=0;
							
				if(action.equals("edit")){
					
					String designation_id=request.getParameter("ad_designation_id");
					response.sendRedirect("edit_designation.jsp?ad_designation_id="+designation_id);
					
				}else if(action.equals("update")){
					
					String designation_id=request.getParameter("ad_designation_id");
					String designation =request.getParameter("designation");
					String designation_type=request.getParameter("ad_designation_type_id");
					
					if(valid.validNotEmpty(designation_id)==false && valid.validDigits(designation_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Bank Region Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_bank_region.jsp");
					}else if(valid.validNotEmpty(designation)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Designation  Name is required field!";
					}else if(valid.validNotEmpty(designation_type)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Designation type Name is required field!";
					}else if(valid.validDigits(designation_type)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Designation type should be numeric only!";
					}else if(valid.validNotEmpty(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else if(valid.validAlphaNum(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status Id should be numeric!";
					}else{
					
						try{
							ad_designation_id=Integer.parseInt(designation_id);
							ad_designation_type_id=Integer.parseInt(designation_type);
						}catch(NumberFormatException n){
							n.printStackTrace();
						}
					
						isactive=Boolean.parseBoolean(request.getParameter("status"));	
						DesignationBean bean=new DesignationBean();
						
						bean.setDesignation(designation);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_designation_id(ad_designation_id);
						bean.setAd_designation_type_id(ad_designation_type_id);
						rowsUpdated = dao.updatedDesignation(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						
					}//end validation
					
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_designation.jsp");
				}else{
					DesignationBean bean=new DesignationBean();
					String designation =request.getParameter("designation");
					String designation_type=request.getParameter("ad_designation_type_id");
					
					if(valid.validNotEmpty(designation)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Designation  Name is required field!";
					}else if(valid.validNotEmpty(designation_type)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Designation type Name is required field!";
					}else if(valid.validDigits(designation_type)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Designation type should be numeric only!";
					}else{
						bean.setDesignation(designation);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						try{
							ad_designation_type_id=Integer.parseInt(designation_type);
						}catch(Exception e){
							e.printStackTrace();
						}
						bean.setAd_designation_type_id(ad_designation_type_id);
						bean.setIsactive(isactive);
						rowsUpdated = dao.addDesignation(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Inserted!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					}//end validation
					
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_designation.jsp");
				}//end insert method

			}//end action

			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}//end class