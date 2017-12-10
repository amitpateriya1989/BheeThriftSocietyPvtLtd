package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MasterValidation.Validation;
import Model.Bean.MemberTypeBean;
import Model.Bean.UserBean;
import Model.Dao.MemberTypeDao;

/**
 * Servlet implementation class AdMemberType
 */
@WebServlet("/AdMemberType")
public class AdMemberType extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberTypeDao dao=null;
	private MemberTypeBean bean=null;
	private Validation valid = new Validation();
    private UserBean user=null;
    public AdMemberType() {
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
			dao=new MemberTypeDao();
			boolean isactive=true;
			int rowsUpdated =0;
			String member_type ="";
			
			String action=request.getParameter("action");
							
			if(action!=null){
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				if(action.equals("edit")){
					
					String ad_member_type_id=request.getParameter("ad_member_type_id");
					response.sendRedirect("edit_member_type.jsp?ad_member_type_id="+ad_member_type_id);

				}else if(action.equals("update")){
						member_type =request.getParameter("member_type");
						
						if(valid.validNotEmpty(request.getParameter("ad_member_type_id"))==false && valid.validDigits(request.getParameter("ad_member_type_id")) == false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Invalid Member Type Id!";
							session.setAttribute("AppMessage", AppMessage);
							response.sendRedirect("ad_member_type.jsp");
						}else if(valid.validNotEmpty(member_type)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Member Type Type Name is required field!";
						}else if(valid.validRegEx(member_type, "^[a-zA-Z-\\s]*$")==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Please enter only charecter and dash(-) symbol only in Member Type Name Field!";
						}else if(valid.validNotEmpty(request.getParameter("status"))==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Status is required field!";
						}else if(valid.validAlphaNum(request.getParameter("status"))==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Status Id should be numeric!";
						}else{
						
							int ad_member_type_id=Integer.parseInt(request.getParameter("ad_member_type_id"));
							
								isactive=Boolean.parseBoolean(request.getParameter("status"));	
								bean=new MemberTypeBean();
								bean.setMember_type(member_type);
								bean.setUpdatedby(user.getAd_user_id());
								bean.setIsactive(isactive);
								bean.setAd_member_type_id(ad_member_type_id);
								rowsUpdated = dao.updateMemberType(bean);
								
								if (rowsUpdated > 0) {
									AppMessage[0] = "alert-success";
									AppMessage[1] = "Data successfully Updated!";
								}else{
									AppMessage[0] = "alert-info";
									AppMessage[1] = "Sorry please try again latter!";
								}
								
						}//end validation
					response.sendRedirect("ad_member_type.jsp");
					
				}else{
						member_type =request.getParameter("member_type");
						
						if(valid.validNotEmpty(member_type)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Member Type Type Name is required field!";
						}else if(valid.validRegEx(member_type, "^[a-zA-Z-\\s]*$")==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Please enter only charecter and dash(-) symbol only in Member Type Name Field!";
						}else{
						
						MemberTypeBean bean=new MemberTypeBean();
						bean.setMember_type(member_type);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						rowsUpdated = dao.addMemberType(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						
						}
						
					response.sendRedirect("ad_member_type.jsp");
				}//end insert method
				
			}//end action
			
			}catch(Exception e){
				e.printStackTrace();
			
			}
	}//end post method
	
}//end class