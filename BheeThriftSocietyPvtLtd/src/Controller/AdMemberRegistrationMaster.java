package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import MasterValidation.Validation;
import Model.Bean.MemberRegistrationMasterBean;
import Model.Bean.MemberTypeBean;
import Model.Bean.UserBean;
import Model.Dao.MemberRegistrationMasterDao;

/**
 * Servlet implementation class AdMemberRegistrationMaster
 */
@WebServlet("/AdMemberRegistrationMaster")
public class AdMemberRegistrationMaster extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberRegistrationMasterDao dao=null;
	private MemberRegistrationMasterBean bean=null;
	private Validation valid = new Validation();
	private UserBean user=null;
    public AdMemberRegistrationMaster() {
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
			dao=new MemberRegistrationMasterDao();
			boolean isactive=true;
			double membership_fees=0.0;
			double fgds_fund =0.0;
			double dcf =0.0;
			int share=0;
			int rowsUpdated =0;
			
			String member_registration_master_id ="";
			String membership_fee = "";
			String fgds_fund_fee ="";
			String dcf_fee = "";
			String share_rec = "";
			String status = "";
			
			String action=request.getParameter("action");
			if(action!=null){
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				if(action.equals("edit")){
					String ad_member_registration_master_id =request.getParameter("ad_member_registration_master_id");
					response.sendRedirect("edit_member_registration_master.jsp?ad_member_registration_master_id="+ad_member_registration_master_id);
					
				}else if(action.equals("update")){
					
					member_registration_master_id = request.getParameter("ad_member_registration_master_id");
					membership_fee = request.getParameter("membership_fees");
					fgds_fund_fee = request.getParameter("fgds_fund");
					dcf_fee   = request.getParameter("dcf");
					share_rec = request.getParameter("share");
					status    = request.getParameter("status");

					if(valid.validNotEmpty(member_registration_master_id)==false && valid.validDigits(member_registration_master_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid member registration Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_member_registration_master.jsp");
					}else if(valid.validNotEmpty(membership_fee)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Member ship fee is required field!";
					}else if(valid.validNumeric(membership_fee)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Member ship fee should be valid number!";
					}else if(valid.validNotEmpty(fgds_fund_fee)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Fgds fund fee is required field!";
					}else if(valid.validNumeric(fgds_fund_fee)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "FGDS fund fee field should be valid number!";
					}else if(valid.validNotEmpty(dcf_fee)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Dcf fee is required field!";
					}else if(valid.validNumeric(dcf_fee)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "DCF fee field should be valid number!";
					}else if(valid.validNotEmpty(share_rec)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Share  is required field!";
					}else if(valid.validNumeric(share_rec)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Share field should be valid number!";
					}else if(valid.validNotEmpty(status)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else if(valid.validAlphaNum(status)==false){
						
					}else{
						try{
						int ad_member_registration_master_id = Integer.parseInt(member_registration_master_id);
						membership_fees=Double.parseDouble(membership_fee);
						fgds_fund=Double.parseDouble(fgds_fund_fee);
						dcf=Double.parseDouble(dcf_fee);
						share=Integer.parseInt(share_rec);
						isactive=Boolean.parseBoolean(status);	
						
						bean=new MemberRegistrationMasterBean();
						bean.setDcf(dcf);
						bean.setFgds_fund(fgds_fund);
						bean.setMembership_fees(membership_fees);
						bean.setShare(share);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_member_registration_master_id(ad_member_registration_master_id);
						}catch(NumberFormatException ne){
							System.out.print("invalid no type");
							ne.printStackTrace();
						}
						rowsUpdated = dao.updateMemberRegistrationMaster(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}

					}//end validation
					
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_member_registration_master.jsp");
					
				}else if(action.equals("insert")){
					
					membership_fee = request.getParameter("membership_fees");
					fgds_fund_fee = request.getParameter("fgds_fund");
					dcf_fee   = request.getParameter("dcf");
					share_rec = request.getParameter("share");
					
					if(valid.validNotEmpty(membership_fee)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Member ship fee is required field!";
					}else if(valid.validNumeric(membership_fee)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Member ship fee should be valid number!";
					}else if(valid.validNotEmpty(fgds_fund_fee)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Fgds fund fee is required field!";
					}else if(valid.validNumeric(fgds_fund_fee)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "FGDS fund fee field should be valid number!";
					}else if(valid.validNotEmpty(dcf_fee)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Dcf fee is required field!";
					}else if(valid.validNumeric(dcf_fee)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "DCF fee field should be valid number!";
					}else if(valid.validNotEmpty(share_rec)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Share  is required field!";
					}else if(valid.validNumeric(share_rec)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Share field should be valid number!";
					}else{
						try{
							membership_fees=Double.parseDouble(request.getParameter("membership_fees"));
							fgds_fund=Double.parseDouble(request.getParameter("fgds_fund"));
							dcf=Double.parseDouble(request.getParameter("dcf"));
							share=Integer.parseInt(request.getParameter("share"));
						}catch(NumberFormatException ne){
							System.out.print("invalid no type");
							ne.printStackTrace();
						}
						
						bean=new MemberRegistrationMasterBean();
						bean.setDcf(dcf);
						bean.setFgds_fund(fgds_fund);
						bean.setMembership_fees(membership_fees);
						bean.setShare(share);
						bean.setIsactive(isactive);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						rowsUpdated = dao.addMemberRegistrationMaster(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Inserted!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					}//end validation

					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_member_registration_master.jsp");
				}//end insert action

			}//end check action null			
			
			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}//end class