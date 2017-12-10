package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import MasterValidation.Validation;
import Model.Bean.SocietyDepartmentBean;
import Model.Bean.UserBean;
import Model.Dao.SocietyDepartmentDao;

/**
 * Servlet implementation class AdSocietyDepartment
 */
@WebServlet("/AdSocietyDepartment")
public class AdSocietyDepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SocietyDepartmentDao dao=null;
	private SocietyDepartmentBean bean=null;
    private UserBean user=null;
    private Validation valid = null;
    public AdSocietyDepartment() {
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
			
			dao=new SocietyDepartmentDao();
			String action=request.getParameter("action");
			valid = new Validation();
			
			String department = "";
			String society_department_id = "";
			String status = "";
			
			int rowsUpdated = 0;
			boolean isactive=true;
						
			if(action!=null){
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				int ad_society_department_id=0;
							
				if(action.equals("edit")){
					ad_society_department_id=Integer.parseInt(request.getParameter("ad_society_department_id"));
					response.sendRedirect("edit_department.jsp?ad_society_department_id="+ad_society_department_id);
				
				}else if(action.equals("update")){
					
						department = request.getParameter("department");
						society_department_id =request.getParameter("ad_society_department_id");
						status = request.getParameter("status");
						
						if(valid.validNotEmpty(society_department_id)==false && valid.validDigits(society_department_id) == false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Invalid Salutation Id!";
							session.setAttribute("AppMessage", AppMessage);
							response.sendRedirect("ad_department.jsp");
						}else if(valid.validNotEmpty(department)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Salutation Name is required field!";
						}else if(valid.validAlphaSpaceDot(department)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Please enter only charecter and dot(.) symbol only in salutation Name Field!";
						}else if(valid.validNotEmpty(request.getParameter("status"))==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Status is required field!";
						}else if(valid.validAlphaNum(request.getParameter("status"))==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Status Id should be numeric!";
						}else{
							
							ad_society_department_id=Integer.parseInt(society_department_id);
							isactive=Boolean.parseBoolean(status);	
							
							bean=new SocietyDepartmentBean();
							bean.setDepartment(department);
							bean.setUpdatedby(user.getAd_user_id());
							bean.setIsactive(isactive);
							bean.setAd_society_department_id(ad_society_department_id);
							rowsUpdated = dao.updateSocietyDepartment(bean);
							
							if (rowsUpdated > 0) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully Updated!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}
							
						}//end validation
						
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_department.jsp");
				}else if(action.equals("insert")){
					
				    department =request.getParameter("department");

					SocietyDepartmentBean bean=new SocietyDepartmentBean();
					bean.setDepartment(department);
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					rowsUpdated = dao.addSocietyDepartment(bean);
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Inserted!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_department.jsp");
				}//end insert action
				else{
					AppMessage[0] = "alert-danger";
					AppMessage[1] = "Invalid Action!";
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_department.jsp");
				}
				
				
			}//end action

			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}//end class