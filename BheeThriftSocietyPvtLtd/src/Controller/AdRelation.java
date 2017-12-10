package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MasterValidation.Validation;
import Model.Bean.RelationBean;
import Model.Bean.RelationBean;
import Model.Bean.UserBean;
import Model.Dao.RelationDao;
import Model.Dao.RelationDao;

/**
 * Servlet implementation class AdRelation
 */
@WebServlet("/AdRelation")
public class AdRelation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RelationDao dao=null;
	private RelationBean bean=null;
	private Validation valid = new Validation();
    private UserBean user=null;
    public AdRelation() {
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
			
	 		dao=new RelationDao();
			boolean isactive=true;
			int rowsUpdated =0;
			String relation = "";
			
			String action=request.getParameter("action");
			
			if(action!=null){
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				if(action.equals("edit")){
				String id=	request.getParameter("ad_relation_id");
				response.sendRedirect("edit_relation.jsp?ad_relation_id="+id);	
				}else if(action.equals("update")){
					relation =request.getParameter("relation");
					
					if(valid.validNotEmpty(request.getParameter("ad_relation_id"))==false && valid.validDigits(request.getParameter("ad_relation_id")) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Relation Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_relation.jsp");
					}else if(valid.validNotEmpty(relation)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Relation Name is required field!";
					}else if(valid.validAlphaSpaceDot(relation)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter and dot(.) symbol only in Relation Name Field!";
					}else if(valid.validNotEmpty(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else if(valid.validAlphaNum(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status Id should be numeric!";
					}else{
					
					int ad_relation_id=Integer.parseInt(request.getParameter("ad_relation_id"));
					
						isactive=Boolean.parseBoolean(request.getParameter("status"));	
						bean=new RelationBean();
						bean.setRelation(relation);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_relation_id(ad_relation_id);
						rowsUpdated = dao.updateRelation(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					}//end validation
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_relation.jsp");
				}else{
					relation =request.getParameter("relation");
					
					if(valid.validNotEmpty(relation)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Relation Name is required field!";
					}else if(valid.validAlphaSpaceDot(relation)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter and dot(.) symbol only in Relation Name Field!";
					}else{
						bean=new RelationBean();
						bean.setRelation(relation);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						rowsUpdated = dao.addRelation(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						
					}//end validation
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_relation.jsp");
				}//end insert method

			  }//end action

			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method

}//end class