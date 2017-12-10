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

import MasterValidation.Validation;
import Model.Bean.SalutationBean;
import Model.Bean.UserBean;
import Model.Dao.SalutationDao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class AdSalutation
 */
@WebServlet("/AdSalutation")
public class AdSalutation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalutationDao dao=null;
	private Validation valid = new Validation();
    private UserBean user=null;
    public AdSalutation() {
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
			dao=new SalutationDao(); 
			
			String name=null;
			String gender_id=null;
			String action=request.getParameter("action");
			boolean isactive=true;
			int rowsUpdated =0;
			int ad_salutation_id=0;
						
			if(action!=null){
			HttpSession session=request.getSession(false);
			user=(UserBean)session.getAttribute("userbean");
			
			String[] AppMessage = new String[2];
			AppMessage[0] = "alert-info";
			AppMessage[1] = "welcome";
		    session.setAttribute("AppMessage", AppMessage);
			
			if(action.equals("edit")){
				
				String salutation_id=request.getParameter("ad_salutation_id");
				response.sendRedirect("edit_salutation.jsp?ad_salutation_id="+salutation_id);
				
			}else if(action.equals("list")){
				try{
				List<SalutationBean> lst=new ArrayList<SalutationBean>();
				lst=new SalutationDao().getAllSalutation();
				//Convert Java Object to Json
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(lst, new TypeToken<List<SalutationBean>>() {}.getType());
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
				name=request.getParameter("name");
				gender_id=request.getParameter("gender");
				int Ad_gender_id=Integer.parseInt(gender_id);
				String salutation_id=request.getParameter("ad_salutation_id");
				
				if(valid.validNotEmpty(salutation_id)==false && valid.validDigits(salutation_id) == false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Invalid Salutation Id!";
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_salutation.jsp");
				}else if(valid.validNotEmpty(name)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Salutation Name is required field!";
				}else if(valid.validAlphaSpaceDot(name)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Please enter only charecter and dot(.) symbol only in salutation Name Field!";
				}else if(valid.validNotEmpty(gender_id)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Gender Id is required field!";
				}else if(valid.validDigits(gender_id)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Gender Id should be numeric!";
				}else if(valid.validNotEmpty(request.getParameter("status"))==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Status is required field!";
				}else if(valid.validAlphaNum(request.getParameter("status"))==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Status Id should be numeric!";
				}else{
					
				isactive=Boolean.parseBoolean(request.getParameter("status"));
				
					try{
						 ad_salutation_id=Integer.parseInt(salutation_id) ;
						
					}catch(NumberFormatException n){
						n.printStackTrace();
					}

				SalutationBean bean=new SalutationBean();
				bean.setName(name);
				bean.setUpdatedby(user.getAd_user_id());
				bean.setIsactive(isactive);
				bean.setAd_salutation_id(ad_salutation_id);
				bean.setAd_gender_id(Ad_gender_id);
				rowsUpdated = dao.updateSalutation(bean);
				
				if (rowsUpdated > 0) {
					AppMessage[0] = "alert-success";
					AppMessage[1] = "Data successfully Updated!";
				}else{
					AppMessage[0] = "alert-info";
					AppMessage[1] = "Sorry please try again latter!";
				}
				
				}//end validation
				
				
				session.setAttribute("AppMessage", AppMessage);
				response.sendRedirect("edit_salutation.jsp?ad_salutation_id="+salutation_id);
					
				}else if(action.equals("insert")){
					name=request.getParameter("name");
					gender_id=request.getParameter("getAd_gender_id");
					
					if(valid.validNotEmpty(name)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Salutation Name is required field!";
					}else if(valid.validAlphaSpaceDot(name)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter onlu charecter and dot(.) symbol only!";
					}else if(valid.validNotEmpty(gender_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Gender Id is required field!";
					}else if(valid.validDigits(gender_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Gender Id should be numeric!";
					}else{
						int Ad_gender_id=Integer.parseInt(gender_id);
						
						SalutationBean bean=new SalutationBean();
						
						bean.setAd_gender_id(Ad_gender_id);
						bean.setName(name);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						rowsUpdated = dao.addSalutation(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Inserted!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						
					}//end validation

					
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_salutation.jsp");
					
				}//end insert method
			    else{
			    	AppMessage[0] = "alert-danger";
					AppMessage[1] = "Invalid Action!";
					session.setAttribute("AppMessage", AppMessage);
			    	response.sendRedirect("ad_salutation.jsp");
			    }

		     }//end action
			}catch(Exception e){
				e.printStackTrace();
			
			}
			
		}//end post method
}//end class