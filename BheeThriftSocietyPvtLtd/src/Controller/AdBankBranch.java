package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MasterValidation.Validation;
import Model.Bean.BankBean;
import Model.Bean.BankBranchBean;
import Model.Bean.BankRegionBean;
import Model.Bean.CityBean;
import Model.Bean.DistrictBean;

import Model.Bean.StateBean;
import Model.Bean.UserBean;
import Model.Dao.BankBranchDao;


/**
 * Servlet implementation class AdBankBranch
 */
@WebServlet("/AdBankBranch")
public class AdBankBranch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankBranchDao dao=null;
    private UserBean user=null;
    private Validation valid = new Validation();
    public AdBankBranch() {
        super();
        dao=new BankBranchDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			HttpSession session=request.getSession(false);
			user=(UserBean)session.getAttribute("userbean");
			
			String[] AppMessage = new String[2];
			AppMessage[0] = "alert-info";
			AppMessage[1] = "welcome";
		    session.setAttribute("AppMessage", AppMessage);
			
			String bank_id = "";
			String state_id = "";
			String district_id="";
			String city_id = "";
			String bank_region_id="";
			String branch = "";
			String branch_code = "";
			String ifsc_code = "";
			String address = "";
			String pincode = "";
			String contact_no = "";
			String contact_person = "";
			String email_id = "";
			String phone_no = "";
			String branch_id="";
			int ad_branch_id=0;
			int ad_bank_id =0;
			int ad_state_id =0;
			int ad_district_id=0;
			int ad_city_id =0;
			int ad_bank_region_id=0;
			
			int rowsUpdated =0;
			
			if(request.getParameter("action")!=null){
				
				String action=request.getParameter("action");
				
				if(action.equals("edit")){
					
					String id = request.getParameter("ad_branch_id");
					if(valid.validNotEmpty(id)==false && valid.validDigits(id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Bank Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_branch.jsp");
					}else{
						try{
							System.out.println("Check"+id);
							ad_branch_id=Integer.parseInt(id);
							response.sendRedirect("edit_branch.jsp?ad_branch_id="+ad_branch_id);
						}catch(NumberFormatException e){
							e.printStackTrace();
						}
					
						
					}

					
				}else if(action.equals("update")){
					
					boolean isactive=true;
						
						bank_id =request.getParameter("ad_bank_id");
						state_id =request.getParameter("ad_state_id");
						district_id=request.getParameter("ad_district_id");
						city_id =request.getParameter("ad_city_id");
						bank_region_id=request.getParameter("ad_bank_region_id");
					    branch_id=request.getParameter("ad_branch_id");
						branch =request.getParameter("branch");
						branch_code =request.getParameter("branch_code");
						ifsc_code =request.getParameter("ifsc_code");
						address =request.getParameter("address");
						pincode =request.getParameter("pincode");
						contact_no =request.getParameter("contact_no");
						contact_person =request.getParameter("contact_person");
						email_id =request.getParameter("email_id");
						phone_no =request.getParameter("phone_no");
						
						if(valid.validNotEmpty(branch_id)==false && valid.validDigits(branch_id) == false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Invalid Branch Id!";
							session.setAttribute("AppMessage", AppMessage);
							response.sendRedirect("ad_branch.jsp");
						}else if(valid.validNotEmpty(bank_id)==false && valid.validDigits(bank_id) == false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Invalid bank Id!";
							session.setAttribute("AppMessage", AppMessage);
							response.sendRedirect("ad_branch.jsp");
						}else if(valid.validNotEmpty(state_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "State name is required field!";
						}else if(valid.validDigits(state_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "State name should be digits!";
						}else if(valid.validNotEmpty(district_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "District name is required field!";
						}else if(valid.validDigits(district_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "District name should be digits!";
						}else if(valid.validNotEmpty(city_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "City name is required field!";
						}else if(valid.validDigits(city_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "City name should be digits!";
						}else if(valid.validNotEmpty(bank_region_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Bank region name is required field!";
						}else if(valid.validDigits(bank_region_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Bank region name should be digits!";
						}else if(valid.validNotEmpty(branch)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Branch name is required field!";
						}else if(valid.validDigits(branch_code)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "City name should be digits!";
						}else if(valid.validIfscCode(ifsc_code)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Please enter valid ifsc code!";
						}/*else if(valid.validPincode(pincode)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Please enter valid pin code!";
						}else if(valid.validEmail(email_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Please enter valid email Id!";
						}*/else{
							
							ad_bank_id =Integer.parseInt(bank_id);
							ad_state_id =Integer.parseInt(state_id);
							ad_district_id=Integer.parseInt(district_id);
							ad_city_id =Integer.parseInt(city_id);
							ad_bank_region_id=Integer.parseInt(bank_region_id);
						    ad_branch_id=Integer.parseInt(branch_id);
							isactive=Boolean.parseBoolean("isactive");	
							BankBranchBean bean=new BankBranchBean();
							bean.setBranch(branch);
							bean.setAd_branch_id(ad_branch_id);
							bean.setUpdatedby(user.getAd_user_id());
							bean.setIsactive(isactive);
							BankBean bank=new BankBean();
							bank.setAd_bank_id(ad_bank_id);
							bean.setBank(bank);
							BankRegionBean region=new BankRegionBean();
							region.setAd_bank_region_id(ad_bank_region_id);
							bean.setBank_region(region);
							StateBean state=new StateBean();
							state.setAd_state_id(ad_state_id);
							bean.setState(state);
							DistrictBean district=new DistrictBean();
							district.setAd_district_id(ad_district_id);
							bean.setDistrict(district);
							CityBean city=new CityBean();
							city.setAd_city_id(ad_city_id);
							bean.setCity(city);
							bean.setBranch_code(Integer.parseInt(branch_code));
							bean.setContact_no(contact_no);
							bean.setContact_person(contact_person);
							bean.setEmail_id(email_id);
							bean.setIfsc_code(ifsc_code);
							bean.setPhone_no(phone_no);
							bean.setAddress(address);
							bean.setPincode(pincode);
							rowsUpdated = new BankBranchDao().updateBankBranch(bean);
							
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully Updated!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
							
						}//end validation
						
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_branch.jsp");

				}else if(action.equals("insert")){
					
					boolean isactive=true;
					
					bank_id =request.getParameter("ad_bank_id");
					state_id =request.getParameter("ad_state_id");
					district_id=request.getParameter("ad_district_id");
					city_id =request.getParameter("ad_city_id");
					bank_region_id=request.getParameter("ad_bank_region_id");	
					
					branch =request.getParameter("branch");
					branch_code =request.getParameter("branch_code");
					ifsc_code =request.getParameter("ifsc_code");
					address =request.getParameter("address");
					pincode =request.getParameter("pincode");
					contact_no =request.getParameter("contact_no");
					contact_person =request.getParameter("contact_person");
					email_id =request.getParameter("email_id");
					phone_no =request.getParameter("phone_no");
					
					if(valid.validNotEmpty(state_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "State name is required field!";
					}else if(valid.validDigits(state_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "State name should be digits!";
					}else if(valid.validNotEmpty(district_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "District name is required field!";
					}else if(valid.validDigits(district_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "District name should be digits!";
					}else if(valid.validNotEmpty(city_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "City name is required field!";
					}else if(valid.validDigits(city_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "City name should be digits!";
					}else if(valid.validNotEmpty(bank_region_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Bank region name is required field!";
					}else if(valid.validDigits(bank_region_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Bank region name should be digits!";
					}else if(valid.validNotEmpty(branch)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Branch name is required field!";
					}else if(valid.validDigits(branch_code)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "City name should be digits!";
					}else if(valid.validIfscCode(ifsc_code)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter valid ifsc code!";
					}/*else if(valid.validPincode(pincode)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter valid pin code!";
					}else if(valid.validEmail(email_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter valid email Id!";
					}*/else{
					
					ad_bank_id =Integer.parseInt(bank_id);
					ad_state_id =Integer.parseInt(state_id);
					ad_district_id=Integer.parseInt(district_id);
					ad_city_id =Integer.parseInt(city_id);
					ad_bank_region_id=Integer.parseInt(bank_region_id);
					
					BankBranchBean bean=new BankBranchBean();
					bean.setBranch(branch);
					bean.setAd_branch_id(ad_branch_id);
					bean.setIsactive(true);
					BankBean bank=new BankBean();
					bank.setAd_bank_id(ad_bank_id);
					bean.setBank(bank);
					BankRegionBean region=new BankRegionBean();
					region.setAd_bank_region_id(ad_bank_region_id);
					bean.setBank_region(region);
					StateBean state=new StateBean();
					state.setAd_state_id(ad_state_id);
					bean.setState(state);
					DistrictBean district=new DistrictBean();
					district.setAd_district_id(ad_district_id);
					bean.setDistrict(district);
					CityBean city=new CityBean();
					city.setAd_city_id(ad_city_id);
					bean.setCity(city);
					bean.setBranch_code(Integer.parseInt(branch_code));
					bean.setContact_no(contact_no);
					bean.setContact_person(contact_person);
					bean.setEmail_id(email_id);
					bean.setIfsc_code(ifsc_code);
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setPhone_no(phone_no);
					bean.setPincode(pincode);
					bean.setAddress(address);
					rowsUpdated = dao.addBankBranch(bean);
					
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					
					}//end validation
					
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_branch.jsp");
					
			 }//end insert method	
			}//end action
			else{
				 AppMessage[0] = "alert-danger";
				 AppMessage[1] = "Invalid Action!";
				 session.setAttribute("AppMessage", AppMessage);
				 response.sendRedirect("ad_branch.jsp");
			 }		

			
			
			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}//end class