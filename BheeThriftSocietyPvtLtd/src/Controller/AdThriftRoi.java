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



import Model.Bean.ThriftRoiBean;
import Model.Bean.UserBean;


import Model.Dao.ThriftRoiDao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class AdThriftRoi
 */
@WebServlet("/AdThriftRoi")
public class AdThriftRoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ThriftRoiDao dao=null;
    private UserBean user=null;
    public AdThriftRoi() {
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
			 dao=new ThriftRoiDao();
			String action=request.getParameter("action");	
			
			if(action.equals("list")){
				try{
				List<ThriftRoiBean> lst=new ArrayList<ThriftRoiBean>();
				lst=new ThriftRoiDao().getAllThriftRoi();
				//Convert Java Object to Json
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(lst, new TypeToken<List<ThriftRoiBean>>() {}.getType());
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
				
				if(action.equals("edit")){
					String ad_thrift_roi_id=request.getParameter("ad_thrift_roi_id");
					response.sendRedirect("edit_thrift_roi.jsp?ad_thrift_roi_id="+ad_thrift_roi_id);
					
				}
				if(action.equals("update")){
					String ad_thrift_roi_id=request.getParameter("ad_thrift_roi_id");
					String effectivefdate =  request.getParameter("effectivefdate");
					String effectiveto =  request.getParameter("effectiveto");
					String roi =  request.getParameter("roi");
					String ratio=request.getParameter("ratio");
					ThriftRoiBean bean = new ThriftRoiBean();
					
					bean.setAd_thrift_roi_id(Integer.parseInt(ad_thrift_roi_id));
					bean.setUpdatedby(user.getAd_user_id());
					bean.setEffectivefromdate(new SimpleDateFormat("dd/MM/yyyy").parse(effectivefdate));
					bean.setEffectivetodate(new SimpleDateFormat("dd/MM/yyyy").parse(effectiveto));
					bean.setRoi(Double.parseDouble(roi));
					bean.setRatio(Double.parseDouble(ratio));
					
					int rowsUpdated=dao.updateThriftRoi(bean);
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Updated!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					response.sendRedirect("ad_thrift_roi.jsp");
				}

				if(action.equals("insert")){
					
					String effectivefdate =  request.getParameter("effectivefdate");
					String effectiveto =  request.getParameter("effectiveto");
					String roi =  request.getParameter("roi");
					String ratio=request.getParameter("ratio");
					ThriftRoiBean bean = new ThriftRoiBean();
					System.out.println("from-"+effectivefdate+" to -"+effectiveto);
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setEffectivefromdate(new SimpleDateFormat("dd/MM/yyyy").parse(effectivefdate));
					bean.setEffectivetodate(new SimpleDateFormat("dd/MM/yyyy").parse(effectiveto));
					bean.setRoi(Double.parseDouble(roi));
					bean.setRatio(Double.parseDouble(ratio));
					//new ThriftRoiDao().addThriftRoi(bean);
					int rowsUpdated=dao.addThriftRoi(bean);
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Inserted!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					response.sendRedirect("ad_thrift_roi.jsp");
				}
				
			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	
	
	}
}
