package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MasterValidation.Validation;
import Model.Bean.ListBean;
import Model.Bean.UserBean;
import Model.Dao.ListDao;

/**
 * Servlet implementation class Ad
 */
@WebServlet("/AdList")
public class AdList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ListDao dao=null;
	private ListBean bean=null;
	private Validation valid = null;
    private UserBean user=null;
    public AdList() {
        super();
        // TODO Auto-generated constructor stub
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
			dao=new ListDao();
			valid = new Validation();
			String action=request.getParameter("action");
			String Status = "";
			String name = "";
			String list_id = "";
			
							
			if(action!=null){
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				int ad_list_id=0;
				int rowsUpdated = 0;
					
				if(action.equals("edit")){
					ad_list_id=Integer.parseInt(request.getParameter("ad_list_id"));
					response.sendRedirect("edit_list.jsp?ad_list_id="+ad_list_id);
				
				}else if(action.equals("ad_list_item")){
					ad_list_id=Integer.parseInt(request.getParameter("ad_list_id"));
					response.sendRedirect("ad_list_item.jsp?ad_list_id="+ad_list_id);
				
				}else if(action.equals("update")){
					
					name = request.getParameter("name");
					Status = request.getParameter("status");
					list_id = request.getParameter("ad_list_id");
					
					if(valid.validNotEmpty(list_id)==false && valid.validDigits(list_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid List Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_name.jsp");
					}else if(valid.validNotEmpty(name)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "List field can not empty!";
					}else if(valid.validAlphaNum(name)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please Enter Alphanumeric value only in name!";
					}else if(valid.validNotEmpty(request.getParameter("ad_list_id"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Countru id can not empty!";
					}else if(valid.validDigits(request.getParameter("ad_list_id"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "List id should be numeric!";
					}else{
						isactive=Boolean.parseBoolean(Status);	
						ad_list_id=Integer.parseInt(list_id);
						
						bean=new ListBean();
						bean.setName(name);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_list_id(ad_list_id);
						rowsUpdated = dao.updateList(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					}
					
					response.sendRedirect("ad_list.jsp");
				}//end update
				
				if(action.equals("insert")){
					name =request.getParameter("name");
					System.out.println(name);
					if(valid.validNotEmpty(name)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "List field can not empty!";
					}else if(valid.validAlphaNum(name.trim())==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please Enter Alphanumeric value only in name!";
					}else{
							ListBean bean=new ListBean();
							bean.setName(name);
							bean.setCreatedby(user.getAd_user_id());
							bean.setUpdatedby(user.getAd_user_id());
							bean.setIsactive(isactive);
							rowsUpdated = dao.addList(bean);
							
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully Updated!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}

					}//end validation	
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_list.jsp");
			}//end insert function
				
			}else{
				response.sendRedirect("ad_list.jsp");
			}//end action null
		
			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}
