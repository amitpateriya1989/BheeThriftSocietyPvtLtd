package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import Model.Bean.LoanPurposeBean;
import Model.Bean.UserBean;
import Model.Dao.LoanPurposeDao;


/**
 * Servlet implementation class AdLoanPurpose
 */
@WebServlet("/AdLoanPurpose")
public class AdLoanPurpose extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoanPurposeDao dao=null;
	private UserBean user=null;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdLoanPurpose() {
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

		dao=new LoanPurposeDao();
		try{
			System.out.print("inside LoanPurpose controller");
			String LoanPurpose=null;
			boolean isactive=true;
			int ad_loan_purpose_id = 0;
			int record=0;
			String action=request.getParameter("action");
			if(action!=null){
			
			HttpSession session=request.getSession(false);
			user=(UserBean)session.getAttribute("userbean");
			
			String[] AppMessage = new String[2];
			AppMessage[0] = "alert-info";
			AppMessage[1] = "welcome";
		    session.setAttribute("AppMessage", AppMessage); 
		    
			if(action.equals("list")){
				
				try{
				List<LoanPurposeBean> lst=new ArrayList<LoanPurposeBean>();
				lst=new LoanPurposeDao().getAllLoanPurpose();
				//Convert Java Object to Json
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(lst, new TypeToken<List<LoanPurposeBean>>() {}.getType());
				JsonArray jsonArray = element.getAsJsonArray();
				String listData=jsonArray.toString();
				//Return Json in the format required by jTable plugin
				listData="{\"Result\":\"OK\",\"Records\":"+listData+"}"; 
				response.setContentType("application/json");
				response.getWriter().print(listData);
				System.out.println(listData);
				}catch(Exception ex){
					String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace()+"}";
					PrintWriter pw=response.getWriter();
					pw.print(error);
				}
			}else if(request.getParameter("name")!=null){
				
			LoanPurpose=request.getParameter("name");
			
				if(action.equals("insert")){
					LoanPurposeBean bean=new LoanPurposeBean();
					bean.setPurpose(LoanPurpose);
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					dao.addLoanPurpose(bean);
					response.sendRedirect("ad_loan_purpose.jsp");
				}//end insert
	
			}else{
				System.out.print(" LoanPurpose with null value");
				
			}
			
			if(action.equals("edit")){
			    String id=request.getParameter("ad_loan_purpose_id");
				response.sendRedirect("edit_loan_purpose.jsp?ad_loan_purpose_id="+id);
			 }else if(action.equals("update")){	
				    
					isactive=Boolean.parseBoolean(request.getParameter("status"));	
					LoanPurpose = request.getParameter("name");
					ad_loan_purpose_id=Integer.parseInt(request.getParameter("loan_purpose_id"));
					
					LoanPurposeBean bean=new LoanPurposeBean();
					bean.setAd_loan_purpose_id(ad_loan_purpose_id);
					bean.setPurpose(LoanPurpose);
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					record=dao.updateLoanPurpose(bean);				
					if (record > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Updated!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					session.setAttribute("AppMessage", AppMessage); 
					response.sendRedirect("ad_loan_purpose.jsp");	
			 }//end update
	
	}else{
		System.out.print(" action with null value");
		
	}
		}catch(Exception e){
			e.printStackTrace();
		
		}
		
	}
}
