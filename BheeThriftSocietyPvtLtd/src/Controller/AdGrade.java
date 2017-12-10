package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Bean.GradeBean;
import Model.Bean.UserBean;
import Model.Dao.GradeDao;
import MasterValidation.Validation;

/**
 * Servlet implementation class AdGrade
 */
@WebServlet("/AdGrade")
public class AdGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdGrade() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Validation valid = new Validation();
		HttpSession session=request.getSession();
		UserBean user=(UserBean)session.getAttribute("userbean");
		
		String[] AppMessage = new String[2];
		AppMessage[0] = "alert-info";
		AppMessage[1] = "welcome";
	    session.setAttribute("AppMessage", AppMessage);
		
		String action=request.getParameter("action");
		int rowsUpdated = 0;
		System.out.print(action);
		
		if(action.equals("insert")){
			
			GradeDao dao = new GradeDao();
			GradeBean bean =new GradeBean();
			try{
			
			String grade_name= request.getParameter("grade_name1");
			String basics=request.getParameter("basics");
			String da=request.getParameter("da");
			String convey=request.getParameter("convey");
			String hra=request.getParameter("hra");
			String sw=request.getParameter("sw");
			String mdcl=request.getParameter("mdcl");
			String alwnc=request.getParameter("alwnc");
			
			if(valid.validNotEmpty(grade_name)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Grade Name is required field!";
			}else if(valid.validAlphaNum(grade_name)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter number only in Grade Name field!";
			}else if(valid.validNotEmpty(basics)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Basics is required field!";
			}else if(valid.validNumeric(basics)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter number only in basics field!";
			}else if(valid.validNotEmpty(da)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "DA is required field!";
			}else if(valid.validNumeric(da)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter number only in DA field!";
			}else if(valid.validNotEmpty(convey)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Conveyanc is required field!";
			}else if(valid.validNumeric(convey)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter number only in Conveyanc field!";
			}else if(valid.validNotEmpty(hra)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "HRA is required field!";
			}else if(valid.validNumeric(hra)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter number only in HRA field!";
			}else if(valid.validNotEmpty(sw)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Staff Welfare is required field!";
			}else if(valid.validNumeric(sw)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter number only in Staff Welfare field!";
			}else if(valid.validNotEmpty(mdcl)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Medical is required field!";
			}else if(valid.validNumeric(mdcl)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter number only in Medical field!";
			}else if(valid.validNotEmpty(alwnc)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Allowance  is required field!";
			}else if(valid.validNumeric(alwnc)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter number only in Allowance field!";
			}else{
			

			int basics1= Integer.parseInt(basics);
			double da1= Double.parseDouble(da);
			double convey1= Double.parseDouble(convey);
			double hra1= Double.parseDouble(hra);
			double sw1= Double.parseDouble(sw);
			double mdcl1= Double.parseDouble(mdcl);
			double alwnc1= Double.parseDouble(alwnc);
			
			bean.setGrade_name(grade_name);
			bean.setBasics(basics1);
			bean.setDa(da1);
			bean.setConvey(convey1);
			bean.setHra(hra1);
			bean.setSw(sw1);
			bean.setMdcl(mdcl1);
			bean.setAlwnc(alwnc1);
			bean.setCreatedby(user.getAd_user_id());
			bean.setIsactive(true);
			
			rowsUpdated = dao.addGrade(bean);
			
			if (rowsUpdated > 0) {
				AppMessage[0] = "alert-success";
				AppMessage[1] = "Data successfully Updated!";
			}else{
				AppMessage[0] = "alert-info";
				AppMessage[1] = "Sorry please try again latter!";
			}
			
			
			}//end validation
			
			session.setAttribute("AppMessage", AppMessage);
			}catch(NumberFormatException m){
				m.printStackTrace();
			}
			
			response.sendRedirect("ad_grade.jsp");
			
		}else if(action.equals("update")){
				GradeDao dao = new GradeDao();
				GradeBean bean =new GradeBean();
				try{
					
				String ad_grade_id=request.getParameter("ad_grade_id");
				String grade_name= request.getParameter("grade_name");
				String basics=request.getParameter("basics");
				String da=request.getParameter("da");
				String convey=request.getParameter("convey");
				String hra=request.getParameter("hra");
				String sw=request.getParameter("sw");
				String mdcl=request.getParameter("mdcl");
				String alwnc=request.getParameter("alwnc");
				String status = request.getParameter("status");
				
				if(valid.validNotEmpty(ad_grade_id)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Grade Id is required field!";
				}else if(valid.validDigits(ad_grade_id)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Enter number only in Gragde field!";
				}else if(valid.validNotEmpty(grade_name)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Grade Name is required field!";
				}else if(valid.validAlphaNum(grade_name)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Enter number only in Grade Name field!";
				}else if(valid.validNotEmpty(basics)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Basics is required field!";
				}else if(valid.validNumeric(basics)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Enter number only in basics field!";
				}else if(valid.validNotEmpty(da)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "DA is required field!";
				}else if(valid.validNumeric(da)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Enter number only in DA field!";
				}else if(valid.validNotEmpty(convey)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Conveyanc is required field!";
				}else if(valid.validNumeric(convey)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Enter number only in Conveyanc field!";
				}else if(valid.validNotEmpty(hra)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "HRA is required field!";
				}else if(valid.validNumeric(hra)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Enter number only in HRA field!";
				}else if(valid.validNotEmpty(sw)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Staff Welfare is required field!";
				}else if(valid.validNumeric(sw)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Enter number only in Staff Welfare field!";
				}else if(valid.validNotEmpty(mdcl)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Medical is required field!";
				}else if(valid.validNumeric(mdcl)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Enter number only in Medical field!";
				}else if(valid.validNotEmpty(alwnc)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Allowance  is required field!";
				}else if(valid.validNumeric(alwnc)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Enter number only in Allowance field!";
				}if(valid.validNotEmpty(status)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Status is required field!";
				}else if(valid.validAlphaNum(status)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Enter number only in status field!";
				}else{
				
				//System.out.print(ad_grade_id);
				int ad_grade_id1= Integer.parseInt(ad_grade_id);
				double basics1= Double.parseDouble(basics);
				double da1= Double.parseDouble(da);
				double convey1= Double.parseDouble(convey);
				double hra1= Double.parseDouble(hra);
				double sw1= Double.parseDouble(sw);
				double mdcl1= Double.parseDouble(mdcl);
				double alwnc1= Double.parseDouble(alwnc);
				boolean isactive=Boolean.parseBoolean(status);
				
				bean.setAd_grade_id(ad_grade_id1);
				bean.setGrade_name(grade_name);
				bean.setBasics(basics1);
				bean.setDa(da1);
				bean.setConvey(convey1);
				bean.setHra(hra1);
				bean.setSw(sw1);
				bean.setMdcl(mdcl1);
				bean.setAlwnc(alwnc1);
				bean.setCreatedby(user.getAd_user_id());
				bean.setIsactive(isactive);
				
				}
				}catch(NumberFormatException m){
					m.printStackTrace();
				}
				rowsUpdated = dao.updateGrade(bean);
				
				if (rowsUpdated > 0) {
					AppMessage[0] = "alert-success";
					AppMessage[1] = "Data successfully Updated!";
				}else{
					AppMessage[0] = "alert-info";
					AppMessage[1] = "Sorry please try again latter!";
				}
				session.setAttribute("AppMessage", AppMessage);
				
				response.sendRedirect("ad_grade.jsp");
				
		}else if(action.equals("edit")){
			if(valid.validDigits(request.getParameter("ad_grade_id"))==true && valid.validNotEmpty(request.getParameter("ad_grade_id"))==true){
				int ad_grade_id=Integer.parseInt(request.getParameter("ad_grade_id"));
				//boolean isactive=Boolean.parseBoolean("isactive");	
				//GradeBean bean=new GradeBean();
				response.sendRedirect("edit_grade.jsp?ad_grade_id="+ad_grade_id);
			}else{
				AppMessage[0] = "alert-danger";
				AppMessage[1] = "Invalid ID!";
				response.sendRedirect("ad_grade.jsp");
			}
						
		}else if(action.equals("delete")){
				
				GradeBean bean=new GradeBean();
				
				String id=request.getParameter("ad_grade_id");  
				int sid=Integer.parseInt(id);
				bean.setAd_grade_id(sid);
				
				new GradeDao().deleteGrade(bean);
				response.sendRedirect("ad_grade.jsp");
		}
		
	}//end post method

}//end class