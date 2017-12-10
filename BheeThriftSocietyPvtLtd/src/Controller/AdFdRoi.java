package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
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

import Model.Bean.FdRoiBean;
import Model.Bean.UserBean;
import Model.Dao.FdRoiDao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class AdFdRoi
 */
@WebServlet("/AdFdRoi")
public class AdFdRoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FdRoiDao dao=null;
    private UserBean user=null;
    public AdFdRoi() {
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
			dao=new FdRoiDao();
			boolean isactive=true;
			int ad_fd_roi_id=0;
			int ad_type_of_fd_id=0;
			int ad_fd_category_id=0;
			int time_period=0;
			double roi=0;
			double frequency=0.0;
			String fdate=null;
			int rowsUpdated =0;

			String action=request.getParameter("action");

			if(action.equals("list")){
				try{
				List<FdRoiBean> lst=new ArrayList<FdRoiBean>();
				lst=new FdRoiDao().getAllFdRoi();
				//Convert Java Object to Json
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(lst, new TypeToken<List<FdRoiBean>>() {}.getType());
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
			}

				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);

				if(action.equals("getroiratebytime")){
					
					ad_fd_roi_id=0;
					try{
						ad_fd_roi_id=Integer.parseInt(request.getParameter("ad_fd_roi_id"));

					}catch(NumberFormatException n){
						n.printStackTrace();
					}

					FdRoiBean bean1  = new FdRoiDao().getFdRoiById(ad_fd_roi_id);

					Gson gson = new Gson();
					JsonObject myObject=new JsonObject();
					JsonElement element = gson.toJsonTree(bean1);
					if(bean1==null){
						myObject.addProperty("success", false);
					}
					else {
						myObject.addProperty("success", true);
					} 
					myObject.add("FdInfo", element);
					response.setContentType("application/json");
					response.getWriter().print(myObject);
					
					System.out.println(myObject);
				}
	
				if(action.equals("edit")){
					String id=request.getParameter("ad_fd_roi_id");
					response.sendRedirect("edit_fd_roi.jsp?ad_fd_roi_id="+id);
					
				}else if(action.equals("update")){
					
					try{
						ad_fd_roi_id=Integer.parseInt(request.getParameter("ad_fd_roi_id"));
						 ad_type_of_fd_id=Integer.parseInt(request.getParameter("ad_type_of_fd_id"));
						 ad_fd_category_id=Integer.parseInt(request.getParameter("ad_fd_category_id"));
						 time_period=Integer.parseInt(request.getParameter("time_period"));
						 roi=Double.parseDouble(request.getParameter("roi"));
						 frequency=Double.parseDouble(request.getParameter("compound_frequency"));

						}catch(NumberFormatException nm){
							nm.printStackTrace();
							
						}
					if(ad_type_of_fd_id !=0 && ad_fd_category_id !=0  && time_period!=0 && roi != 0){

						
						FdRoiBean bean=new FdRoiBean();
						bean.setRoi(roi);
						bean.setAd_type_of_fd_id(ad_type_of_fd_id);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setAd_fd_category_id(ad_fd_category_id);
						bean.setTime_period(time_period);
						bean.setAd_fd_roi_id(ad_fd_roi_id);
						bean.setCompound_frequency(frequency);
						try{
						fdate=request.getParameter("fdate");

						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						Date d= df.parse(fdate);
						bean.setEffective_form(d);
						}catch(ParseException p){
							System.out.println("date in invalid formate");
						}
						bean.setEffective_to("Till Now");
						bean.setIsactive(true);
						
										
						rowsUpdated = new FdRoiDao().updateFdRoi(bean);
						System.out.println("inside update method");
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("fd_roi.jsp");
					}else{
						System.out.print("FdRoi / Date not selected");
						response.sendRedirect("fd_roi.jsp");
					}
				

					
				}else if(action.equals("insert")){
					try{
						 ad_type_of_fd_id=Integer.parseInt(request.getParameter("ad_type_of_fd_id"));
						 ad_fd_category_id=Integer.parseInt(request.getParameter("ad_fd_category_id"));
						 time_period=Integer.parseInt(request.getParameter("time_period"));
						 roi=Double.parseDouble(request.getParameter("roi"));
						 frequency=Double.parseDouble(request.getParameter("compound_frequency"));

						}catch(NumberFormatException nm){
							nm.printStackTrace();
							
						}
					if(ad_type_of_fd_id !=0 && ad_fd_category_id !=0  && time_period!=0 && roi != 0){

						isactive=Boolean.parseBoolean("isactive");	
						FdRoiBean bean=new FdRoiBean();
						bean.setRoi(roi);
						bean.setAd_type_of_fd_id(ad_type_of_fd_id);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setAd_fd_category_id(ad_fd_category_id);
						bean.setTime_period(time_period);
						bean.setCompound_frequency(frequency);
						try{
						fdate=request.getParameter("fdate");

						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						Date d= df.parse(fdate);
						bean.setEffective_form(d);
						}catch(ParseException p){
							System.out.println("date in invalid formate");
						}
						bean.setEffective_to("Till Now");
						bean.setIsactive(isactive);
						
						
						
					//	new FdRoiDao().updateFdRoiStatus(s);
						
						rowsUpdated = new FdRoiDao().addFdRoi(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("fd_roi.jsp");
					}else{
						System.out.print("FdRoi / Date not selected");
					}
				}
				
				
				
				if(action.equals("getroirate")){
					int ad_fd_category_id1=0;
					int ad_type_of_fd_id1=0;
					try{
						
					 ad_fd_category_id1=Integer.parseInt(request.getParameter("ad_fd_category_id"));
					 ad_type_of_fd_id1=Integer.parseInt(request.getParameter("ad_type_of_fd_id"));

					}catch(NumberFormatException n){
						n.printStackTrace();
					}

					FdRoiBean bean1  =new FdRoiBean();
					bean1.setAd_type_of_fd_id(ad_type_of_fd_id1);
					bean1.setAd_fd_category_id(ad_fd_category_id1);
					
					ArrayList<FdRoiBean> bean3 = new FdRoiDao().getTypeCatFdRoi(bean1);
					
					Gson gson = new Gson();
					JsonObject myObject=new JsonObject();
					JsonElement element = gson.toJsonTree(bean3);
					if(bean3==null){
						myObject.addProperty("success", false);
					}
					else {
						myObject.addProperty("success", true);
					} 
					myObject.add("FdInfo", element);
					response.setContentType("application/json");
					response.getWriter().print(myObject);
					
					System.out.println(myObject);
				}

			}catch(Exception e){
				e.printStackTrace();
			
			}

	}
}