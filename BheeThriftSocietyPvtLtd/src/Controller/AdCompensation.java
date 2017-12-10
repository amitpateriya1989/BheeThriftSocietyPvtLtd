package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

import Model.Bean.CompulsationBean;
import Model.Bean.BankBean;
import Model.Bean.DesignationLevelBean;
import Model.Bean.UserBean;
import Model.Dao.CompulsationDao;
import Model.Dao.BankDao;
import Model.Dao.DesignationLevelDao;
import MasterValidation.Validation;

/**
 * Servlet implementation class AdCompulsation
 */
@WebServlet("/AdCompensation")
public class AdCompensation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompulsationDao dao=null;
    private UserBean user=null;
    private Validation valid = null;
    public AdCompensation() {
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
			dao=new CompulsationDao();
			valid = new Validation();
			boolean isactive=true;
			int rowsUpdated =0;
			
			String action=request.getParameter("action");
			
							
			if(action!=null){
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				if(action.equals("list")){
					try{
					List<CompulsationBean> lst=new ArrayList<CompulsationBean>();
					lst=new CompulsationDao().getAllCompulsation();
					//Convert Java Object to Json
					Gson gson = new Gson();
					JsonElement element = gson.toJsonTree(lst, new TypeToken<List<CompulsationBean>>() {}.getType());
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
				}if(action.equals("edit")){
					try{
					String id=request.getParameter("ad_compensation_amt_id");
					response.sendRedirect("edit_compensation_amt.jsp?ad_compensation_amt_id="+id);
					}catch(Exception ex){
						String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace()+"}";
						PrintWriter pw=response.getWriter();
						pw.print(error);
					}
				}else if(action.equals("insert")){
					String reason =request.getParameter("reason");
					String fdate=request.getParameter("fdate");
					String amount = request.getParameter("amt");

					 if(valid.validNotEmpty(reason)==false){
						 AppMessage[0] = "alert-warning";
						 AppMessage[1] = "Reason Of Compensation is required filed!";
					 }
					 else if(valid.validAlphabet(reason)==false){
						 AppMessage[0] = "alert-warning";
						 AppMessage[1] = "Effective From Date is required filed!";
					 }else if(valid.validNotEmpty(fdate)==false){
						 AppMessage[0] = "alert-warning";
						 AppMessage[1] = "Effective From Date is required filed!";
					 }else if(valid.validDate(fdate,"DD/MM/YYYY")==false){
						 AppMessage[0] = "alert-warning";
						 AppMessage[1] = "Please enter valid (DD/MM/YYYY) date formate in Effective From Date!";
					 }else if(valid.validNotEmpty(amount)==false){
						 AppMessage[0] = "alert-warning";
						 AppMessage[1] = "Compensation Amount is required filed!";
					 }else if(valid.validNumeric(amount)==false){
						 AppMessage[0] = "alert-warning";
						 AppMessage[1] = "Enter valid Compensation Amount!";
					 }else{
						CompulsationBean bean=new CompulsationBean();
						
						bean.setReson(reason);
						bean.setEffective_from_date(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
						bean.setAmt(Double.parseDouble(amount));
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(true);
						//new CompulsationDao().updateCompulsationStatus(bean);
						rowsUpdated = dao.addCompulsation(bean);
						
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully inserted!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
						session.setAttribute("AppMessage", AppMessage);
					
					}//end validation check else part
					response.sendRedirect("ad_compensation.jsp");
					
				}else if(action.equals("update")){
					String reason =request.getParameter("reason");
					String fdate=request.getParameter("fdate");
					String amount = request.getParameter("amt");
                    String id=request.getParameter("ad_compensation_amt_id");
					 if(valid.validNotEmpty(reason)==false){
						 AppMessage[0] = "alert-warning";
						 AppMessage[1] = "Reason Of Compensation is required filed!";
					 }
					 else if(valid.validAlphabet(reason)==false){
						 AppMessage[0] = "alert-warning";
						 AppMessage[1] = "Effective From Date is required filed!";
					 }else if(valid.validNotEmpty(fdate)==false){
						 AppMessage[0] = "alert-warning";
						 AppMessage[1] = "Effective From Date is required filed!";
					 }else if(valid.validDate(fdate,"DD/MM/YYYY")==false){
						 AppMessage[0] = "alert-warning";
						 AppMessage[1] = "Please enter valid (DD/MM/YYYY) date formate in Effective From Date!";
					 }else if(valid.validNotEmpty(amount)==false){
						 AppMessage[0] = "alert-warning";
						 AppMessage[1] = "Compensation Amount is required filed!";
					 }else if(valid.validNumeric(amount)==false){
						 AppMessage[0] = "alert-warning";
						 AppMessage[1] = "Enter valid Compensation Amount!";
					 }else if(valid.validNotEmpty(id)==false){
						 AppMessage[0] = "alert-warning";
						 AppMessage[1] = "Compensation id is required filed!";
					 }else if(valid.validNumeric(id)==false){
						 AppMessage[0] = "alert-warning";
						 AppMessage[1] = "Enter valid Compensation ID!";
					 }else{
						CompulsationBean bean=new CompulsationBean();
						bean.setAd_compensation_amt_id(Integer.parseInt(id));
						bean.setReson(reason);
						bean.setEffective_from_date(new SimpleDateFormat("dd/MM/yyyy").parse(fdate));
						bean.setAmt(Double.parseDouble(amount));
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(true);
						//new CompulsationDao().updateCompulsationStatus(bean);
						rowsUpdated = dao.updateCompulsation(bean);
						
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data updated successfully inserted!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
						session.setAttribute("AppMessage", AppMessage);
					
					}//end validation check else part
					response.sendRedirect("ad_compensation.jsp");
				}

			}//end check action

			}catch(Exception e){
				e.printStackTrace();
			
			}//end try catch
		
	}//end post method
	
}//end class