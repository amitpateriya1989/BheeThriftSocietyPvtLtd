package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MasterValidation.Validation;
import Model.Bean.CalenderBean;
import Model.Bean.UserBean;
import Model.Dao.CalenderDao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class AdCalender
 */
@WebServlet("/AdCalender")
public class AdCalender extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CalenderDao dao=null;
    private UserBean user=null;
    private Validation valid = null;
    public AdCalender() {
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
				dao=new CalenderDao();
				valid = new Validation();
				
				boolean isactive=true;
				int ad_calender_id=0;
				int ad_list_item_id=0;
				int rowsUpdated = 0;
				Date holiday_date=null;
				
				String action=request.getParameter("action");
				if(action!=null ){
					
					String calender_id="";
					String list_item_id="";
					String name ="";
					String status ="";
					String hdate="";
					String year="";
					String isActive="";
					HttpSession session=request.getSession(false);
					user=(UserBean)session.getAttribute("userbean");
					
					String[] AppMessage = new String[2];
					AppMessage[0] = "alert-info";
					AppMessage[1] = "welcome";
				    session.setAttribute("AppMessage", AppMessage);
					
				if(action.equals("edit")){
					
					calender_id=request.getParameter("ad_calender_id");
					response.sendRedirect("edit_calender.jsp?ad_calender_id="+calender_id);
					
				}else if(action.equals("list")){
					try{
					List<CalenderBean> lst=new ArrayList<CalenderBean>();
					lst=new CalenderDao().getAllCalender();
					//Convert Java Object to Json
					Gson gson = new Gson();
					JsonElement element = gson.toJsonTree(lst, new TypeToken<List<CalenderBean>>() {}.getType());
					JsonArray jsonArray = element.getAsJsonArray();
					String listData=jsonArray.toString();
					//Return Json in the format required by jTable plugin
					listData="{\"Result\":\"OK\",\"Records\":"+listData+"}"; 
					response.setContentType("application/json");
					response.getWriter().print(listData);
					
					}catch(Exception ex){
						String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace()+"}";
						PrintWriter pw=response.getWriter();
						pw.print(error);
					}
				}else if(action.equals("update")){
					calender_id=request.getParameter("ad_calender_id");
					list_item_id=request.getParameter("ad_list_item_id");
					status = request.getParameter("status");
					isActive=request.getParameter("isactive");
					hdate=request.getParameter("holiday_date");
					year=request.getParameter("year");
					if(valid.validNotEmpty(list_item_id)==false && valid.validDigits(list_item_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Holiday Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_calender.jsp");
					}else if(valid.validNotEmpty(calender_id)==false && valid.validDigits(calender_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Calender Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_calender.jsp");
					}else if(valid.validNotEmpty(hdate)==false && valid.validDate(hdate, "DD/MM/YYYY") == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Holiday Date!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_calender.jsp");
					}else if(valid.validNotEmpty(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else if(valid.validAlphaSpace(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status should be character type!";
					}else{
						
						try{
							ad_list_item_id=Integer.parseInt(list_item_id);
							ad_calender_id=Integer.parseInt(calender_id);
							holiday_date=new SimpleDateFormat("dd/MM/yyyy").parse(hdate);
						}catch(NumberFormatException n){
							n.printStackTrace();
						}catch(ParseException p){
							p.printStackTrace();
						}
						isactive=Boolean.parseBoolean(isActive);	
						CalenderBean bean=new CalenderBean();
						bean.setStatus(status);
						bean.setAd_calender_id(ad_calender_id);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_list_item_id(ad_list_item_id);
						bean.setHoliday_date(holiday_date);
						bean.setYear(year);
						rowsUpdated = dao.updateCalender(bean);
						
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully Updated!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
						
						}//end validation
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_calender.jsp");
					
				}else if(action.equals("insert")){
					
					list_item_id=request.getParameter("ad_list_item_id");
					status = request.getParameter("status");
					hdate=request.getParameter("holiday_date");
					year=request.getParameter("year");
					if(valid.validNotEmpty(list_item_id)==false && valid.validDigits(list_item_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Holiday Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_calender.jsp");
					}else if(valid.validNotEmpty(hdate)==false && valid.validDate(hdate, "DD/MM/YYYY") == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Holiday Date!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_calender.jsp");
					}else if(valid.validNotEmpty(status)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "List Item Name is required field!";
					}else if(valid.validAlphaSpace(status)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter  only in Sate Name Field!";
					}else{
						try{
							
							ad_list_item_id=Integer.parseInt(list_item_id);
							holiday_date=new SimpleDateFormat("dd/MM/yyyy").parse(hdate);
						}catch(NumberFormatException n){
							n.printStackTrace();
						}catch(ParseException p){
							p.printStackTrace();
						}
					CalenderBean bean=new CalenderBean();
					bean.setStatus(status);
					bean.setAd_list_item_id(ad_list_item_id);
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					bean.setHoliday_date(holiday_date);
					bean.setYear(year);
					rowsUpdated = dao.addCalender(bean);
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Updated!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
					}//end validation
					
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_calender.jsp");
					
				}else{
					response.sendRedirect("ad_calender.jsp");
				}

			}//end action

			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}
