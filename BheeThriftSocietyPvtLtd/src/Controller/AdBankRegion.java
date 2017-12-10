package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import MasterValidation.Validation;
import Model.Bean.BankRegionBean;
import Model.Bean.UserBean;


import Model.Dao.BankRegionDao;


@WebServlet("/AdBankRegion")
public class AdBankRegion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankRegionDao dao=null;
	private BankRegionBean bean=null;
	private Validation valid = new Validation();
    private UserBean user=null;
    public AdBankRegion() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			boolean isactive=true;
			int rowsUpdated =0;
			dao=new BankRegionDao();
			String action=request.getParameter("action");
			String region = "";
			String region_code = "";
							
			if(action!=null){
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				int ad_bank_region_id=0;
							
				if(action.equals("edit")){
					ad_bank_region_id=Integer.parseInt(request.getParameter("ad_bank_region_id"));
					response.sendRedirect("edit_bank_region.jsp?ad_bank_region_id="+ad_bank_region_id);
				
				}else if(action.equals("update")){
					region =request.getParameter("region");
					region_code=request.getParameter("region_code");
					if(valid.validNotEmpty(request.getParameter("ad_bank_region_id"))==false && valid.validDigits(request.getParameter("ad_bank_region_id")) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Bank Region Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_bank_region.jsp");
					}else if(valid.validNotEmpty(region)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Bank Region Name is required field!";
					}else if(valid.validAlphaSpaceDot(region)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter and dot(.) symbol only in Bank Region Name Field!";
					}else if(valid.validNotEmpty(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else if(valid.validAlphaNum(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status Id should be numeric!";
					}else{
						ad_bank_region_id=Integer.parseInt(request.getParameter("ad_bank_region_id"));
						
						isactive=Boolean.parseBoolean(request.getParameter("status"));	
						bean=new BankRegionBean();
						bean.setRegion(region);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setRegion_code(region_code);
						bean.setAd_bank_region_id(ad_bank_region_id);
						rowsUpdated = dao.updateBankRegion(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					}//end validation					
				
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_bank_region.jsp");
				}else{
					region =request.getParameter("region");
					region_code=request.getParameter("region_code");
					 if(valid.validNotEmpty(region)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Bank Region Name is required field!";
						}else if(valid.validAlphaSpaceDot(region)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Please enter only charecter and dot(.) symbol only in Bank Region Name Field!";
						}else{
							BankRegionBean bean=new BankRegionBean();
							bean.setRegion(region);
							bean.setRegion_code(region_code);
							bean.setCreatedby(user.getAd_user_id());
							bean.setUpdatedby(user.getAd_user_id());
							bean.setIsactive(isactive);
							rowsUpdated = dao.addBankRegion(bean);
							
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully Updated!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
						}//end validation

					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_bank_region.jsp");
				}//end insert function
				
				
			}//end action

			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}//end class