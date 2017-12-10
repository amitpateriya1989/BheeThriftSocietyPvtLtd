package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
import Model.Bean.TypeOfLoanBean;
import Model.Bean.UserBean;
import Model.Dao.TypeOfLoanDao;

import java.sql.Connection;

/**
 * Servlet implementation class TypeOfLoan
 */
@WebServlet("/AdTypeOfLoan")
public class AdTypeOfLoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TypeOfLoanDao dao=null;
    private UserBean user=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdTypeOfLoan() {
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
		try{
			dao=new TypeOfLoanDao(); 
			String LoanType=null;
			String name=null;
			int loan_type_id=0;
			int record=0;
			String action=request.getParameter("action");
			
			boolean isactive=true;
		if(action!=null){
			name=request.getParameter("name");
			HttpSession session=request.getSession(false);
			user=(UserBean)session.getAttribute("userbean");						
			String[] AppMessage = new String[2];
			AppMessage[0] = "alert-info";
			AppMessage[1] = "welcome";
		    session.setAttribute("AppMessage", AppMessage); 
			
			if(action.equals("list")){
				try{
				List<TypeOfLoanBean> lst=new ArrayList<TypeOfLoanBean>();
				lst=new TypeOfLoanDao().getAlltypeofloan();
				//Convert Java Object to Json
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(lst, new TypeToken<List<TypeOfLoanBean>>() {}.getType());
				JsonArray jsonArray = element.getAsJsonArray();
				String listData=jsonArray.toString();
				//Return Json in the format required by jTable plugin
				listData="{\"Result\":\"OK\",\"Records\":"+listData+"}"; 
				response.setContentType("application/json");
				response.getWriter().print(listData);
				//System.out.println(listData);
				}catch(Exception ex){
					String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace()+"}";
					PrintWriter pw=response.getWriter();
					pw.print(error);
				}
			}else if(name!=null){
					
				if(action.equals("insert")){
					TypeOfLoanBean bean=new TypeOfLoanBean();
					bean.setName(name);
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);
					dao.addTypeOfLoan(bean);
					response.sendRedirect("ad_loan_type.jsp");
					
				}//end insert

			}
			if(action.equals("edit")){
			    String id=request.getParameter("ad_type_of_loan_id");
				response.sendRedirect("edit_loan_type.jsp?ad_type_of_loan_id="+id);
			 }else if(action.equals("update")){	
				 
				 	isactive=Boolean.parseBoolean(request.getParameter("status"));
				 	loan_type_id=Integer.parseInt(request.getParameter("ad_type_of_loan_id"));
					TypeOfLoanBean bean=new TypeOfLoanBean();
					bean.setName(name);
					bean.setUpdatedby(user.getAd_user_id());
					bean.setAd_type_of_loan_id(loan_type_id);
					bean.setIsactive(isactive);
					record=dao.updatetypeofloan(bean);
							
					if (record > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Updated!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					session.setAttribute("AppMessage", AppMessage); 
					response.sendRedirect("ad_loan_type.jsp");	
			 }//end update
		
		}
			}catch(Exception e){
				e.printStackTrace();
			
			}
			
		}
	

}
