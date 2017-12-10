package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Bean.CountryBean;
import Model.Bean.UserBean;
import Model.Dao.CountryDao;
import MasterValidation.Validation;

/**
 * Servlet implementation class AdCountry
 */
@WebServlet("/AdCountry")
public class AdCountry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CountryDao dao=null;
	private CountryBean bean=null;
	private Validation valid = null;
    private UserBean user=null;
    public AdCountry() {
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
			
			boolean isactive=true;
			dao=new CountryDao();
			valid = new Validation();
			String action=request.getParameter("action");
			String Status = "";
			String country = "";
			String country_id = "";
			
							
			if(action!=null){
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				int ad_country_id=0;
				int rowsUpdated = 0;
							
				if(action.equals("edit")){
					ad_country_id=Integer.parseInt(request.getParameter("ad_country_id"));
					response.sendRedirect("edit_country.jsp?ad_country_id="+ad_country_id);
				
				}else if(action.equals("update")){
					
					country = request.getParameter("country");
					Status = request.getParameter("status");
					country_id = request.getParameter("ad_country_id");
					
					if(valid.validNotEmpty(country_id)==false && valid.validDigits(country_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Country Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_country.jsp");
					}else if(valid.validNotEmpty(country)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Country field can not empty!";
					}else if(valid.validAlphaNum(country)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please Enter Alphanumeric value only in country!";
					}else if(valid.validNotEmpty(request.getParameter("ad_country_id"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Countru id can not empty!";
					}else if(valid.validDigits(request.getParameter("ad_country_id"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Country id should be numeric!";
					}else{
						isactive=Boolean.parseBoolean(Status);	
						ad_country_id=Integer.parseInt(country_id);
						
						bean=new CountryBean();
						bean.setCountry(country);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_country_id(ad_country_id);
						rowsUpdated = dao.updateCountry(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					}
					
					response.sendRedirect("edit_country.jsp?ad_country_id="+ad_country_id);
				}//end update
				
				if(action.equals("insert")){
					country =request.getParameter("country");
					
					if(valid.validNotEmpty(country)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Country field can not empty!";
					}else if(valid.validAlphaNum(country)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please Enter Alphanumeric value only in country!";
					}else{
							CountryBean bean=new CountryBean();
							bean.setCountry(country);
							bean.setCreatedby(user.getAd_user_id());
							bean.setUpdatedby(user.getAd_user_id());
							bean.setIsactive(isactive);
							rowsUpdated = dao.addCountry(bean);
							
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully Updated!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}

					}//end validation	
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_country.jsp");
			}//end insert function
				
			}else{
				response.sendRedirect("ad_country.jsp");
			}//end action null
		
			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}//end class