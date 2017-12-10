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
import Model.Bean.UserBean;
import Model.Dao.PFRuleDao;
import MasterValidation.Validation;

/**
 * Servlet implementation class AdPFRule
 */
@WebServlet("/AdPFRule")
public class AdPFRule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdPFRule() {
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
		 //TODO Auto-generated method stub
		String action=request.getParameter("action");
		int rowsUpdated = 0;
		
		HttpSession session=request.getSession();
		UserBean user=(UserBean)session.getAttribute("userbean");
		
		String[] AppMessage = new String[2];
		AppMessage[0] = "alert-info";
		AppMessage[1] = "welcome";
	    session.setAttribute("AppMessage", AppMessage);
		
		Validation valid = new Validation();
		
		if(action.equals("insert")){

			PFRuleDao dao = new PFRuleDao();
			PFRuleBean bean =new PFRuleBean();
			
			String effective_from     = request.getParameter("effective_from");
			String epf_emp_share      = request.getParameter("epf_emp_share");
			String epf_employer_share = request.getParameter("epf_employer_share");
			String eps_employer_share = request.getParameter("eps_employer_share");
			String edli_charges       = request.getParameter("edli_charges");
			String epf_admin_charges  = request.getParameter("epf_admin_charges");
			String edli_admin_charges = request.getParameter("edli_admin_charges");

			try{	
			
			if(valid.validNotEmpty(effective_from)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Effective from is required field!";
			}else if(valid.validDate(effective_from,"DD/MM/YYYY")==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter valid (DD/MM/YYYY) date format in Effective from field!";
			}else if(valid.validNotEmpty(epf_emp_share)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "EPF Employee Share is required field!";
			}else if(valid.validNumeric(epf_emp_share)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter Number only in EPF Employee Share field!";
			}else if(valid.validNotEmpty(epf_employer_share)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "EPF Employer Share is required field!";
			}else if(valid.validNumeric(epf_employer_share)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter Number only in EPF Employer Share field!";
			}else if(valid.validNotEmpty(eps_employer_share)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "EPS Employer Share is required field!";
			}else if(valid.validNumeric(eps_employer_share)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter Number only in EPS Employer Share field!";
			}else if(valid.validNotEmpty(edli_charges)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "EDLI Charges is required field!";
			}else if(valid.validNumeric(edli_charges)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter Number only in EDLI Charges field!";
			}else if(valid.validNotEmpty(epf_admin_charges)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "EPF Admin Charges is required field!";
			}else if(valid.validNumeric(epf_admin_charges)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter Number only in EPF Admin Charges field!";
			}else if(valid.validNotEmpty(edli_admin_charges)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "EDLI Admin Charges is required field!";
			}else if(valid.validNumeric(edli_admin_charges)==false){
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Enter Number only in EDLI Admin Charges  field!";
			}else{

			bean.setUpdatedby(user.getUpdatedby());
			
				try{
				SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
				Date ef =sf.parse(effective_from);
				bean.setEffective_from(ef);
				}catch(Exception e){
					e.printStackTrace();
				}
				
			dao.updatePFRule(bean);

			double epf_emp_share1= Double.parseDouble(epf_emp_share);
			double epf_employer_share1= Double.parseDouble(epf_employer_share);
			double eps_employer_share1= Double.parseDouble(eps_employer_share);
			double edli_charges1= Double.parseDouble(edli_charges);
			double epf_admin_charges1= Double.parseDouble(epf_admin_charges);
			double edli_admin_charges1= Double.parseDouble(edli_admin_charges);
			
			bean.setEpf_emp_share(epf_emp_share1);
			bean.setEpf_employer_share(epf_employer_share1);
			bean.setEps_employer_share(eps_employer_share1);
			bean.setEdli_charges(edli_charges1);
			bean.setEpf_admin_charges(epf_admin_charges1);
			bean.setEdli_admin_charges(edli_admin_charges1);
			bean.setCreatedby(user.getAd_user_id());
			bean.setIsactive(true);
			//String effective_to= request.getParameter("effective_to");			
			//bean.setEffective_to(effective_to);

			//dao.updatePFRule(bean);			
			rowsUpdated = new PFRuleDao().addPFRule(bean);
			
			if (rowsUpdated > 0) {
				AppMessage[0] = "alert-success";
				AppMessage[1] = "Data successfully Updated!";
			}else{
				AppMessage[0] = "alert-info";
				AppMessage[1] = "Sorry please try again latter!";
			}
			
			
			}//end validate rule
			session.setAttribute("AppMessage", AppMessage);
			}catch(NumberFormatException m){
				m.printStackTrace();
			}//end try catch

			response.sendRedirect("ad_pf_rule.jsp");

	       }//end insert action

		}//end post method
	}//end class