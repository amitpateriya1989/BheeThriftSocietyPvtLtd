package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

import MasterValidation.Validation;
import Model.Bean.BankBean;
import Model.Bean.DistrictBean;
import Model.Bean.StateBean;
import Model.Bean.UserBean;
import Model.Dao.BankDao;
import Model.Dao.DistrictDao;
import Model.Dao.StateDao;

/**
 * Servlet implementation class AdDistrict
 */
@WebServlet("/AdDistrict")
public class AdDistrict extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DistrictDao dao=null;
    private UserBean user=null;  
    private Validation valid = new Validation();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdDistrict() {
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
	dao=new DistrictDao();
			
			boolean isactive=true;
			int rowsUpdated =0;
			HttpSession session=request.getSession(false);
			user=(UserBean)session.getAttribute("userbean");
			
			String[] AppMessage = new String[2];
			AppMessage[0] = "alert-info";
			AppMessage[1] = "welcome";
		    session.setAttribute("AppMessage", AppMessage);
			
			System.out.print(user);
			int ad_state_id=0;
			int ad_district_id=0;
			
			String action=request.getParameter("action");
			if(action!=null ){
				if(action.equals("edit")){
					
					String district_id=request.getParameter("ad_district_id");
					response.sendRedirect("edit_district.jsp?ad_district_id="+district_id);
					
				}else if(action.equals("list")){
					try{
					List<DistrictBean> lst=new ArrayList<DistrictBean>();
					lst=new DistrictDao().getAllDistrict();
					//Convert Java Object to Json
					Gson gson = new Gson();
					JsonElement element = gson.toJsonTree(lst, new TypeToken<List<DistrictBean>>() {}.getType());
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
				}else if(action.equals("update")){
					String district =request.getParameter("district");
					
					if(valid.validNotEmpty(request.getParameter("ad_district_id"))==false && valid.validDigits(request.getParameter("ad_district_id")) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid District Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_district.jsp");
					}else if(valid.validNotEmpty(district)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "District Name is required field!";
					}else if(valid.validAlphaNumSpace(district)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter and dot(.) symbol only in District Name Field!";
					}else if(valid.validNotEmpty(request.getParameter("ad_state_id"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "State Id is required field!";
					}else if(valid.validDigits(request.getParameter("ad_district_id")) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "State Id field should be numeric!";
					}else{
						
						ad_state_id=Integer.parseInt(request.getParameter("ad_state_id"));
						ad_district_id=Integer.parseInt(request.getParameter("ad_district_id"));
					
							isactive=Boolean.parseBoolean("isactive");	
							DistrictBean bean=new DistrictBean();
							bean.setDistrict(district);
							bean.setUpdatedby(user.getAd_user_id());
							bean.setIsactive(isactive);
							StateBean sb = new StateBean();
							sb.setAd_state_id(ad_state_id);
							bean.setState(sb);
							bean.setAd_district_id(ad_district_id);
							rowsUpdated = dao.updateDistrict(bean);
							
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully Updated!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
						
					}//end validation

						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_district.jsp");
					
				}else{
					String district =request.getParameter("district");
					DistrictBean bean=new DistrictBean();
					
					if(valid.validNotEmpty(district)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "District Name is required field!";
					}else if(valid.validAlphaNumSpace(district)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter and dot(.) symbol only in District Name Field!";
					}else if(valid.validNotEmpty(request.getParameter("ad_state_id"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "State Id is required field!";
					}else if(valid.validDigits(request.getParameter("ad_district_id")) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "State Id field should be numeric!";
					}else{
						
						ad_state_id=Integer.parseInt(request.getParameter("ad_state_id"));
						StateBean sb = new StateBean();
						sb.setAd_state_id(ad_state_id);
						bean.setState(sb);
						bean.setDistrict(district);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						rowsUpdated = dao.addDistrict(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						
					}//end validation

					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_district.jsp");
					
				}

			}//end action	
			
			}catch(Exception e){
				e.printStackTrace();
			
			}
	}//end post method
	
}//end class