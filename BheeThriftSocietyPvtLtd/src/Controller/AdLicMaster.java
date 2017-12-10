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
import Model.Bean.LicMasterBean;
import Model.Bean.ListItemBean;
import Model.Bean.LoanRoiBean;
import Model.Bean.UserBean;
import Model.Dao.LicMasterDao;
import Model.Dao.ListItemDao;
import Model.Dao.LoanRoiDao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class AdLicMaster
 */
@WebServlet("/AdLicMaster")
public class AdLicMaster extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    private UserBean user=null;
    private Validation valid = null;
    public AdLicMaster() {
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
				
				valid = new Validation();
				
				boolean isactive=true;
				int ad_lic_master_id=0;
				int ad_type_of_loan_id=0;
				int ad_loan_category_id=0;
				int ad_loan_roi_id=0;
				int rowsUpdated = 0;
				Date applied_date=null;
				double lic_rate=0.0;
				
				String action=request.getParameter("action");
				if(action!=null ){
					
					String lic_master_id="";
					String type_of_loan_id="";
					String loan_category_id="";
					String rate ="";
					String date="";
					String status ="";
				
					HttpSession session=request.getSession(false);
					user=(UserBean)session.getAttribute("userbean");
					
					String[] AppMessage = new String[2];
					AppMessage[0] = "alert-info";
					AppMessage[1] = "welcome";
				    session.setAttribute("AppMessage", AppMessage);
					
				if(action.equals("edit")){
					
					lic_master_id=request.getParameter("ad_lic_master_id");
					response.sendRedirect("edit_lic_master.jsp?ad_lic_master_id="+lic_master_id);
					
				}else if(action.equals("update")){
					lic_master_id=request.getParameter("ad_lic_master_id");
					type_of_loan_id=request.getParameter("ad_type_of_loan_id");
					loan_category_id=request.getParameter("ad_loan_category_id");
					rate =request.getParameter("lic_rate");
					date=request.getParameter("applied_date");
					status = request.getParameter("status");
					
					if(valid.validNotEmpty(lic_master_id)==false && valid.validDigits(lic_master_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid LIC Master Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_lic_master.jsp");
					}else if(valid.validNotEmpty(type_of_loan_id)==false && valid.validDigits(type_of_loan_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Loan Type Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_lic_master.jsp");
					}else if(valid.validNotEmpty(loan_category_id)==false && valid.validDigits(loan_category_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Loan Category Id !";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_lic_master.jsp");
					}else if(valid.validNotEmpty(rate)==false && valid.validDigits(rate)){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "List Item Name is required field!";
					}else if(valid.validNotEmpty(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else if(valid.validAlphaNum(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status Id should be numeric!";
					}else{
						
						try{
							ad_lic_master_id=Integer.parseInt(lic_master_id);
							ad_type_of_loan_id=Integer.parseInt(type_of_loan_id);
							ad_loan_category_id=Integer.parseInt(loan_category_id);
							lic_rate=Double.parseDouble(rate);
							applied_date=new SimpleDateFormat("dd/MM/yyyy").parse(date);
						}catch(NumberFormatException n){
							n.printStackTrace();
						}catch(ParseException n){
							n.printStackTrace();
						}
						isactive=Boolean.parseBoolean(status);	
						LicMasterBean bean=new LicMasterBean();
						bean.setLic_rate(lic_rate);
						bean.setApplied_date(applied_date);
						bean.setAd_lic_master_id(ad_lic_master_id);
						bean.setAd_loan_category_id(ad_loan_category_id);
						bean.setAd_type_of_loan_id(ad_type_of_loan_id);
						ad_loan_roi_id=new LoanRoiDao().getLoanRoiMaxId(ad_loan_category_id, ad_type_of_loan_id);
						bean.setAd_loan_roi_id(ad_loan_roi_id);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						
						rowsUpdated = new LicMasterDao().updateLicMaster(bean);
						
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully Updated!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
						
						}//end validation
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_lic_master.jsp");
					
				}else if(action.equals("insert")){
					
					
					type_of_loan_id=request.getParameter("ad_type_of_loan_id");
					loan_category_id=request.getParameter("ad_loan_category_id");
					rate =request.getParameter("lic_rate");
					date=request.getParameter("applied_date");
					status = "true";
					
					 if(valid.validNotEmpty(type_of_loan_id)==false && valid.validDigits(type_of_loan_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Loan Type Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_lic_master.jsp");
					}else if(valid.validNotEmpty(loan_category_id)==false && valid.validDigits(loan_category_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Loan Category Id !";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_lic_master.jsp");
					}else if(valid.validNotEmpty(rate)==false && valid.validDigits(rate)){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "List Item Name is required field!";
					}else{
						
						try{
							
							ad_type_of_loan_id=Integer.parseInt(type_of_loan_id);
							ad_loan_category_id=Integer.parseInt(loan_category_id);
							lic_rate=Double.parseDouble(rate);
							applied_date=new SimpleDateFormat("dd/MM/yyyy").parse(date);
						}catch(NumberFormatException n){
							n.printStackTrace();
						}catch(ParseException n){
							n.printStackTrace();
						}
						isactive=Boolean.parseBoolean(status);	
						LicMasterBean bean=new LicMasterBean();
						bean.setLic_rate(lic_rate);
						bean.setApplied_date(applied_date);
						bean.setAd_loan_category_id(ad_loan_category_id);
						bean.setAd_type_of_loan_id(ad_type_of_loan_id);
						ad_loan_roi_id=new LoanRoiDao().getLoanRoiMaxId(ad_loan_category_id, ad_type_of_loan_id);
						bean.setAd_loan_roi_id(ad_loan_roi_id);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						
						rowsUpdated = new LicMasterDao().addLicMaster(bean);
						
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully Updated!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
						
						}//end validation
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_lic_master.jsp");
					
					
				}else{
					response.sendRedirect("ad_list_.jsp");
				}

			}//end action

			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}
