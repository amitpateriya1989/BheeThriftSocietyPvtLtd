package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Bean.BankBranchBean;
import Model.Bean.CityBean;
import Model.Bean.UserBean;
import Model.Dao.BankBranchDao;
import Model.Dao.CityDao;

/**
 * Servlet implementation class AdCity
 */
@WebServlet("/AdCity")
public class AdCity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CityDao dao=null;
    private UserBean user=null;
    public AdCity() {
        super();
        dao=new CityDao();
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
			int rowsUpdated = 0 ;
			int ad_state_id =Integer.parseInt(request.getParameter("ad_state_id"));
			int ad_district_id=Integer.parseInt(request.getParameter("ad_district_id"));
			String city =request.getParameter("city");
			String action=request.getParameter("action");
			if(ad_state_id!=0){
				if(ad_district_id!=0){
						if(city!=null ){
							
				
			if(action!=null){
				
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				int ad_city_id=0;
							
				if(action.equals("edit")){
					ad_city_id=Integer.parseInt(request.getParameter("ad_city_id"));
					if(ad_city_id!=0){
						isactive=Boolean.parseBoolean("isactive");	
						CityBean bean=new CityBean();
						bean.setCity(city);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_city_id(ad_city_id);
						bean.setAd_district_id(ad_district_id);
						bean.setAd_state_id(ad_state_id);
						rowsUpdated = new CityDao().updateCity(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						
						response.sendRedirect("ad_city.jsp");
					}else{
						System.out.print("City not selected");
					}
				}else{
					CityBean bean=new CityBean();
					bean.setCity(city);
					bean.setAd_district_id(ad_district_id);
					bean.setAd_state_id(ad_state_id);
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					rowsUpdated = dao.addCity(bean);
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Updated!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
					response.sendRedirect("ad_city.jsp");
				}
				
				
				
			}//end action
		
		
			}else{
				System.out.println("State not selected");
			}
			}else{
				System.out.println("District not selected");
			}
					
			}else{
				System.out.println("Null value inside Branch Detail");
			}
			}catch(Exception e){
				e.printStackTrace();
			
		}
		
	}
	
}//end class