package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Bean.AccountGroupBean;
import Model.Bean.AccountSubGroupBean;
import Model.Bean.UserBean;
import Model.Dao.AccountGroupDao;
import Model.Dao.AccountSubGroupDao;

/**
 * Servlet implementation class AdAccountSubGroup
 */
@WebServlet("/AdAccountSubGroup")
public class AdAccountSubGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountSubGroupDao dao=null;
    private UserBean user=null;
    public AdAccountSubGroup() {
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
			dao=new AccountSubGroupDao();
			boolean isactive=true;
			String name =request.getParameter("name");
			String action=request.getParameter("action");
			if(action!=null && name!=null ){
							
			
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				int ad_ac_group_id=0;
				int ad_ac_subgroup_id=0;
				int ad_ac_type_id=0;
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				if(action.equals("isactive")){
				
					try{
						ad_ac_subgroup_id=Integer.parseInt(request.getParameter("ad_ac_subgroup_id"));
					}catch(NumberFormatException ne){
						System.out.println("Error in Id Format");
						ne.printStackTrace();
					}
					isactive=Boolean.parseBoolean("isactive");	
					AccountSubGroupBean bean=new AccountSubGroupBean();
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					bean.setAd_ac_subgroup_id(ad_ac_subgroup_id);
					dao.updateAccountSubGroupStatus(bean);
				
				}
							
			if(action.equals("edit")){
					try{
						ad_ac_group_id=Integer.parseInt(request.getParameter("ad_ac_group_id"));
						ad_ac_subgroup_id=Integer.parseInt(request.getParameter("ad_ac_subgroup_id"));
						ad_ac_type_id=Integer.parseInt(request.getParameter("ad_ac_type_id"));
						isactive=Boolean.parseBoolean(request.getParameter("status"));
						int rowsUpdated =0;
						
						if(ad_ac_group_id!=0 && ad_ac_subgroup_id!=0 && ad_ac_type_id!=0){
	
							AccountSubGroupBean bean=new AccountSubGroupBean();
							bean.setName(name);
							bean.setUpdatedby(user.getAd_user_id());
							bean.setIsactive(isactive);
							bean.setAd_ac_group_id(ad_ac_group_id);
							bean.setAd_ac_type_id(ad_ac_type_id);
							bean.setAd_ac_subgroup_id(ad_ac_subgroup_id);
							rowsUpdated =  dao.updateAccountSubGroup(bean);
							
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully updated!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
							
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Account Group,subGroup,Type not selected!";
						}
						
					}catch(NumberFormatException ne){
						ne.printStackTrace();
					}
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("edit_account_sub_group.jsp?ad_ac_subgroup_id="+ad_ac_subgroup_id);
					
				}else if(action.equals("insert")){
					AccountSubGroupBean bean=new AccountSubGroupBean();
					int sub_group_ac_no=0;
					int rowsUpdated = 0;
					
					try{
						ad_ac_type_id=Integer.parseInt(request.getParameter("ad_ac_type_id"));
						ad_ac_group_id=Integer.parseInt(request.getParameter("ad_ac_group_id"));
						
					}catch(NumberFormatException ne){
						System.out.println("Error in Id Format");
						ne.printStackTrace();
					}
					bean.setName(name);
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setAd_ac_group_id(ad_ac_group_id);
					bean.setAd_ac_type_id(ad_ac_type_id);
					bean.setIsactive(isactive);
					
					int sgno= new AccountSubGroupDao().getmaxSubGroupNoByGroupId(ad_ac_group_id);
					if(sgno==0){
						int gno = new AccountGroupDao().getAccountGroupnoById(ad_ac_group_id);
						sub_group_ac_no=gno+1000;
					}else{
						sub_group_ac_no=sgno+1000;
					}
					
					bean.setSub_group_ac_no(sub_group_ac_no);
					rowsUpdated = dao.addAccountSubGroup(bean);
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully updated!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_account_sub_group.jsp");
				}
				
				
				
			}else{
				System.out.println("Null value inside action and name Detail");
			}
			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}
	
	

}
