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



import Model.Bean.LoanRoiBean;
import Model.Bean.UserBean;


import Model.Dao.LoanRoiDao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class AdLoanRoi
 */
@WebServlet("/AdLoanRoi")
public class AdLoanRoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoanRoiDao dao=null;
    private UserBean user=null;
    public AdLoanRoi() {
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
			 dao=new LoanRoiDao();
			int ad_loan_roi_id=0;
			boolean isactive=true;
			int ad_type_of_loan_id=0;
			int ad_loan_category_id=0;
			double max_limit=0;
			double roi=0;
			double share_needed=0;
			String fdate=null;
			int min_period=0;
			int max_period=0;
			double min_salary=0;
			String action=request.getParameter("action");
			
			
			if(action.equals("list")){
				try{
				List<LoanRoiBean> lst=new ArrayList<LoanRoiBean>();
				lst=new LoanRoiDao().getAllLoanRoi();
				//Convert Java Object to Json
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(lst, new TypeToken<List<LoanRoiBean>>() {}.getType());
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
				
				if(action.equals("insert")){
					try{
						 ad_type_of_loan_id=Integer.parseInt(request.getParameter("ad_type_of_loan_id"));
						 ad_loan_category_id=Integer.parseInt(request.getParameter("ad_loan_category_id"));
						 max_limit=Double.parseDouble(request.getParameter("max_limit"));
						 roi=Double.parseDouble(request.getParameter("roi"));
						 share_needed=Double.parseDouble(request.getParameter("share_needed"));
						 min_period=Integer.parseInt(request.getParameter("min_period"));
						 max_period=Integer.parseInt(request.getParameter("max_period"));
						 min_salary=Double.parseDouble(request.getParameter("min_salary"));
							 if(max_period==0){
								 max_period=10;
							 }
						}catch(NumberFormatException nm){
							nm.printStackTrace();
							
						}

					if(ad_type_of_loan_id !=0 && ad_loan_category_id !=0  && max_limit!=0  ){

						isactive=Boolean.parseBoolean("isactive");	
						LoanRoiBean bean=new LoanRoiBean();
						bean.setRoi(roi);
						bean.setAd_type_of_loan_id(ad_type_of_loan_id);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setAd_loan_category_id(ad_loan_category_id);
						bean.setMax_limit(max_limit);
						bean.setMin_period(min_period);
						bean.setMax_period(max_period);
						bean.setMin_salary(min_salary);
							try{
							fdate=request.getParameter("fdate");
							DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
							Date d= df.parse(fdate);
							bean.setEffective_form(d);
							}catch(ParseException p){
								System.out.println("date in invalid formate");
							}
						bean.setEffective_to("Till Now");
						bean.setShare_needed(share_needed);
						bean.setIsactive(isactive);
						int record = dao.addLoanRoi(bean);
							if (record > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully inserted!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
							session.setAttribute("AppMessage", AppMessage); 
						response.sendRedirect("loan_roi.jsp");
					}else{
						System.out.print("LoanRoi / select required field");
					}
				}//end insert function
				
				if(action.equals("edit")){
				    String id=request.getParameter("ad_loan_roi_id");
					response.sendRedirect("edit_loan_roi.jsp?ad_loan_roi_id="+id);
				 }else if(action.equals("update")){
					 try{
						 ad_loan_roi_id=Integer.parseInt(request.getParameter("ad_loan_roi_id"));
						 ad_type_of_loan_id=Integer.parseInt(request.getParameter("ad_type_of_loan_id"));
						 ad_loan_category_id=Integer.parseInt(request.getParameter("ad_loan_category_id"));
						 max_limit=Double.parseDouble(request.getParameter("max_limit"));
						 roi=Double.parseDouble(request.getParameter("roi"));
						 share_needed=Double.parseDouble(request.getParameter("share_needed"));
						 min_period=Integer.parseInt(request.getParameter("min_period"));
						 max_period=Integer.parseInt(request.getParameter("max_period"));
						 min_salary=Double.parseDouble(request.getParameter("min_salary"));
							 if(max_period==0){
								 max_period=10;
							 }
						}catch(NumberFormatException nm){
							nm.printStackTrace();
							
						}

					if(ad_type_of_loan_id !=0 && ad_loan_category_id !=0  && max_limit!=0  ){

						//isactive=Boolean.parseBoolean("isactive");	
						LoanRoiBean bean=new LoanRoiBean();
						bean.setAd_loan_roi_id(ad_loan_roi_id);
						bean.setRoi(roi);
						bean.setAd_type_of_loan_id(ad_type_of_loan_id);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setAd_loan_category_id(ad_loan_category_id);
						bean.setMax_limit(max_limit);
						bean.setMin_period(min_period);
						bean.setMax_period(max_period);
						bean.setMin_salary(min_salary);
							try{
							fdate=request.getParameter("fdate");
							DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
							Date d= df.parse(fdate);
							bean.setEffective_form(d);
							}catch(ParseException p){
								System.out.println("date in invalid formate");
							}
						bean.setEffective_to("Till Now");
						bean.setShare_needed(share_needed);
						bean.setIsactive(isactive);
						int record = dao.updateLoanRoi(bean);
							if (record > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully Updated!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
							session.setAttribute("AppMessage", AppMessage); 
						response.sendRedirect("loan_roi.jsp");
					}else{
						System.out.print("LoanRoi / select required field");
					}
				 }//end update
				
				if(action.equals("getroirate")){
					int ad_loan_category_id1=0;
					int ad_type_of_loan_id1=0;
					try{
					 ad_loan_category_id1=Integer.parseInt(request.getParameter("ad_loan_category_id"));
					 ad_type_of_loan_id1=Integer.parseInt(request.getParameter("ad_type_of_loan_id"));
				
					}catch(NumberFormatException n){
						n.printStackTrace();
					}
					
					int maxid=new LoanRoiDao().getLoanRoiMaxId(ad_loan_category_id1,ad_type_of_loan_id1);					
					
					
					LoanRoiBean bean3  =new LoanRoiDao().getLoanRoiById(maxid);
					
					Gson gson = new Gson();
					JsonObject myObject=new JsonObject();
					JsonElement element = gson.toJsonTree(bean3);
					if(bean3.getAd_loan_roi_id()== 0){
						myObject.addProperty("success", false);
					}
					else {
						myObject.addProperty("success", true);
					} 
					myObject.add("ShareInfo", element);
					response.setContentType("application/json");
					response.getWriter().print(myObject);
				}
			
			}catch(Exception e){
				e.printStackTrace();
			
			}

	}
}//end class
