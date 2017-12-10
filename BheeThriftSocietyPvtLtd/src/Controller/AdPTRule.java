package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Bean.PFRuleBean;
import Model.Bean.PTRuleBean;
import Model.Bean.UserBean;
import Model.Dao.PFRuleDao;
import Model.Dao.PTRuleDao;
import MasterValidation.Validation;

/**
 * Servlet implementation class AdPTRule
 */
@WebServlet("/AdPTRule")
public class AdPTRule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdPTRule() {
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
		String action=request.getParameter("action");
		int rowsUpdated =0;
		
		Validation valid = new Validation();
		HttpSession session=request.getSession();
		UserBean user=(UserBean)session.getAttribute("userbean");
		
		String[] AppMessage = new String[2];
		AppMessage[0] = "alert-info";
		AppMessage[1] = "welcome";
	    session.setAttribute("AppMessage", AppMessage);
		
		
		System.out.print(action);
		
	if(action.equals("insert")){
			
			PTRuleDao dao = new PTRuleDao();
			PTRuleBean bean =new PTRuleBean();
			try{
				
				bean.setUpdatedby(user.getUpdatedby());
				
				String effective_from = request.getParameter("effective_from");
				String min_amt=request.getParameter("min_amt");
				String max_amt=request.getParameter("max_amt");	
				String regualar_charges=request.getParameter("r_c");
				String march_specific_charges=request.getParameter("march_c");
				//String createdby=request.getParameter("createdby");
				//String updatedby=request.getParameter("updatedby");
		
			if(valid.validNotEmpty(effective_from)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Effective From date is required field!";
			}else if(valid.validDate(effective_from,"DD/MM/YYYY")==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Please enter valid date(DD/MM/YYYY) in Effective date formate!";
			}else if(valid.validNotEmpty(min_amt)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Min Amount is required field!";
			}else if(valid.validNumeric(min_amt)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter number only in min amount field!";
			}else if(valid.validNotEmpty(max_amt)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Max amount is required field!";
			}else if(valid.validNumeric(max_amt)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter number only in max amount field!";
			}else if(valid.validNotEmpty(regualar_charges)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Regular Charges is required field!";
			}else if(valid.validNumeric(regualar_charges)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter number only in regular charges field!";
			}else if(valid.validNotEmpty(march_specific_charges)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "March specific charges is required field!";
			}else if(valid.validNumeric(march_specific_charges)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter number only in march specific charges field!";
			}else{
			
				
				try{
				
				SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
				Date ef =sf.parse(effective_from);
				bean.setEffective_from(ef);
				}catch(Exception e){
					e.printStackTrace();
				}
				new PTRuleDao().updatePTRule(bean);

			int min_amt1= Integer.parseInt(min_amt);
			int max_amt1= Integer.parseInt(max_amt);
			int regualar_charges1= Integer.parseInt(regualar_charges);
			int march_specific_charges1= Integer.parseInt(march_specific_charges);
			//int createdby1=Integer.parseInt(createdby);
			//int updatedby1=Integer.parseInt(updatedby);
			
			bean.setMin_amt(min_amt1);
			bean.setMax_amt(max_amt1);
			bean.setRegular_charges(regualar_charges1);
			bean.setMarch_specific_charges(march_specific_charges1);
			bean.setCreatedby(user.getAd_user_id());
			bean.setUpdatedby(user.getAd_user_id());
			//bean.setCreatedby(createdby1);
			//bean.setUpdatedby(updatedby1);
			bean.setIsactive(true);
			
			new PTRuleDao().updatePTRule(bean);			
			rowsUpdated = dao.addPTRule(bean);
			
			if (rowsUpdated > 0) {
				AppMessage[0] = "alert-success";
				AppMessage[1] = "Data successfully inserted!";
			}else{
				AppMessage[0] = "alert-info";
				AppMessage[1] = "Sorry please try again latter!";
			}
			
			}//end validate check
			session.setAttribute("AppMessage", AppMessage);
			}catch(NumberFormatException m){
				m.printStackTrace();
			}

			response.sendRedirect("ad_pt_rule.jsp");
			
			
	}//end insert action

	}//end post method
}//end class
