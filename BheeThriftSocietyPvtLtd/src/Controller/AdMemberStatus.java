package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MasterValidation.Validation;
import Model.Bean.MemberStatusBean;
import Model.Bean.UserBean;
import Model.Dao.MemberStatusDao;


/**
 * Servlet implementation class AdMemberStatus
 */
@WebServlet("/AdMemberStatus")
public class AdMemberStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberStatusDao dao=null;
	private MemberStatusBean bean=null;
	private Validation valid = new Validation();
    private UserBean user=null;
    public AdMemberStatus() {
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
			
			boolean isactive=true;
			int rowsUpdated =0;
			dao=new MemberStatusDao();
			String action=request.getParameter("action");
			
							
			if(action!=null){
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				int ad_member_status_id=0;
							
				if(action.equals("edit")){
					ad_member_status_id=Integer.parseInt(request.getParameter("ad_member_status_id"));
					response.sendRedirect("edit_member_status.jsp?ad_member_status_id="+ad_member_status_id);
				
				}else if(action.equals("update")){
					String member_status =request.getParameter("member_status");
					
					if(valid.validNotEmpty(request.getParameter("member_status"))==false && valid.validDigits(request.getParameter("member_status")) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Bank Member Status Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_bank_region.jsp");
					}else if(valid.validNotEmpty(member_status)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Member Status is required field!";
					}else if(valid.validAlphabet(member_status)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter only in Member Status Field!";
					}else if(valid.validNotEmpty(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else if(valid.validAlphaNum(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status Id should be numeric!";
					}else{
						
						ad_member_status_id=Integer.parseInt(request.getParameter("ad_member_status_id"));
						isactive=Boolean.parseBoolean(request.getParameter("status"));	
						bean=new MemberStatusBean();
						bean.setMember_status(member_status);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_member_status_id(ad_member_status_id);
						rowsUpdated = dao.updateMemberStatus(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					}//end validation
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_member_status.jsp");
				}else{
					String member_status =request.getParameter("member_status");
					if(valid.validNotEmpty(member_status)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Member Status is required field!";
					}else if(valid.validAlphabet(member_status)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter only in Member Status Field!";
					}else{
						MemberStatusBean bean=new MemberStatusBean();
						bean.setMember_status(member_status);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						rowsUpdated = dao.addMemberStatus(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully inserted!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					}//end validation
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_member_status.jsp");
				}//end insert method
				
				
			}//end action

			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}//end class