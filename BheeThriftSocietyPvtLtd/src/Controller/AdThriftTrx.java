package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import MasterValidation.Validation;
import Model.Bean.ThriftTrxBean;
import Model.Bean.UserBean;
import Model.Dao.ThriftTrxDao;
import Model.Dao.ThriftTrxDao;

/**
 * Servlet implementation class AdThriftTrx
 */
@WebServlet("/AdThriftTrx")
public class AdThriftTrx extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ThriftTrxDao dao=null;
    private UserBean user=null; 
	private Validation valid = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdThriftTrx() {
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
		dao=new ThriftTrxDao();
		valid =  new Validation();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		try{
			
			int rowsUpdated =0;
			int ad_thrift_trx_id=0;
			String action=request.getParameter("action");
			String[] AppMessage = new String[2];
			HttpSession session=request.getSession(false);
			if(action!=null){
				
				user=(UserBean)session.getAttribute("userbean");
				
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
			    if(action.equals("add")){
					
			    	response.sendRedirect("ad_thrift_trx.jsp");
					
				}else if(action.equals("edit")){
					
			    	String thrift_trx_id=request.getParameter("ad_thrift_trx_id");
					response.sendRedirect("edit_thrift_trx.jsp?ad_thrift_trx_id="+thrift_trx_id);
					
				}else if(action.equals("list")){
					
					try{
					List<ThriftTrxBean> lst=new ArrayList<ThriftTrxBean>();
					lst=new ThriftTrxDao().getAllThriftTrx();
					//Convert Java Object to Json
					Gson gson = new Gson();
					JsonElement element = gson.toJsonTree(lst, new TypeToken<List<ThriftTrxBean>>() {}.getType());
					JsonArray jsonArray = element.getAsJsonArray();
					String listData=jsonArray.toString();
					//Return Json in the format required by jTable plugin
					listData="{\"Result\":\"OK\",\"Records\":"+listData+"}"; 
					response.setContentType("application/json");
					response.getWriter().print(listData);
					
					}catch(Exception ex){
						String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace()+"}";
						PrintWriter pw=response.getWriter();
						pw.print(error);
					}
				}else if(action.equals("update")){
					
					String thrift_trx_id=request.getParameter("ad_thrift_trx_id");
					String member_id=request.getParameter("ad_member_id");
					String voucher_id=request.getParameter("ad_voucher_id");
					String date=request.getParameter("trx_date");
					String th_amt=request.getParameter("thrift_amt");
					String withdraw_amt=request.getParameter("withdrawal_amt");
					String bal_amt=request.getParameter("balance");
					String status =request.getParameter("status");
					String discription =request.getParameter("discription");
					String active =request.getParameter("isactive");
					
					if(valid.validNotEmpty(thrift_trx_id)==false && valid.validDigits(thrift_trx_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Thrift Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("thrift_amt_detail.jsp");
					}else if(valid.validNotEmpty(member_id)==false && valid.validDigits(member_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Member Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("thrift_amt_detail.jsp");
					}else if(valid.validNotEmpty(voucher_id)==false && valid.validDigits(voucher_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Voucher Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("thrift_amt_detail.jsp");
					}else if(valid.validNotEmpty(th_amt)==false && valid.validNumeric(th_amt)){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Thrift Amount!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("thrift_amt_detail.jsp");
					}else if(valid.validNotEmpty(withdraw_amt)==false && valid.validNumeric(withdraw_amt)){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Withdrawal Amount!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("thrift_amt_detail.jsp");
					}else if(valid.validNotEmpty(discription)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter only!";
					}else if(valid.validNotEmpty(status)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else if(valid.validNotEmpty(date)==false && valid.validDate(date, "dd/MM/yyyy")){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else{
						
						ad_thrift_trx_id=Integer.parseInt(thrift_trx_id);
						int ad_member_id=Integer.parseInt(member_id);
						int ad_voucher_id=Integer.parseInt(voucher_id);
						double thrift_amt=Double.parseDouble(th_amt);
						double withdrawal_amt=Double.parseDouble(withdraw_amt);
						double balance=Double.parseDouble(bal_amt);
						boolean isactive=Boolean.parseBoolean(active);
						Date trx_date=null;
						try{
						trx_date=sdf.parse(date);
						}catch(ParseException p){
							p.printStackTrace();
						}
						
						
						ThriftTrxBean bean=new ThriftTrxBean();
						bean.setAd_thrift_trx_id(ad_thrift_trx_id);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_member_id(ad_member_id);
						bean.setAd_voucher_id(ad_voucher_id);
						bean.setThrift_amt(thrift_amt);
						bean.setWithdrawal(withdrawal_amt);
						bean.setBalance(balance);
						bean.setStatus(status);
						bean.setDiscription(discription);
						bean.setTrx_date(trx_date);
						rowsUpdated = dao.updateThriftTrx(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						
					}//end validation				
					
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("view_thrift_detail.jsp");
					
					
				}else if(action.equals("insert")){
					
					String thrift_trx_id=request.getParameter("ad_thrift_trx_id");
					String member_id=request.getParameter("ad_member_id");
					String voucher_id=request.getParameter("ad_voucher_id");
					String date=request.getParameter("trx_date");
					String th_amt=request.getParameter("thrift_amt");
					String withdraw_amt=request.getParameter("withdrawal_amt");
					String bal_amt=request.getParameter("balance");
					String status =request.getParameter("status");
					String discription =request.getParameter("discription");
					String active =request.getParameter("isactive");
					
					if(valid.validNotEmpty(member_id)==false && valid.validDigits(member_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Member Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("thrift_amt_detail.jsp");
					}else if(valid.validNotEmpty(voucher_id)==false && valid.validDigits(voucher_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Voucher Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("thrift_amt_detail.jsp");
					}else if(valid.validNotEmpty(th_amt)==false && valid.validNumeric(th_amt)){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Thrift Amount!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("thrift_amt_detail.jsp");
					}else if(valid.validNotEmpty(withdraw_amt)==false && valid.validNumeric(withdraw_amt)){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Withdrawal Amount!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("thrift_amt_detail.jsp");
					}else if(valid.validNotEmpty(discription)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please enter only charecter only!";
					}else if(valid.validNotEmpty(status)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else if(valid.validNotEmpty(date)==false && valid.validDate(date, "dd/MM/yyyy")){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else{
						
						
						int ad_member_id=Integer.parseInt(member_id);
						int ad_voucher_id=Integer.parseInt(voucher_id);
						double thrift_amt=Double.parseDouble(th_amt);
						double withdrawal_amt=Double.parseDouble(withdraw_amt);
						double balance=Double.parseDouble(bal_amt);
						boolean isactive=Boolean.parseBoolean(active);
						Date trx_date=null;
						try{
						trx_date=sdf.parse(date);
						}catch(ParseException p){
							p.printStackTrace();
						}
						
						
						ThriftTrxBean bean=new ThriftTrxBean();
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_member_id(ad_member_id);
						bean.setAd_voucher_id(ad_voucher_id);
						bean.setThrift_amt(thrift_amt);
						bean.setWithdrawal(withdrawal_amt);
						bean.setBalance(balance);
						bean.setStatus(status);
						bean.setDiscription(discription);
						bean.setTrx_date(trx_date);
						rowsUpdated = dao.addThriftTrx(bean);
						
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Updated!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
					}//end validation
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("thrift_detail.jsp");
					
				}else if(action.equals("delete")){
					String thrift_trx_id=request.getParameter("ad_thrift_trx_id");
					if(valid.validNotEmpty(thrift_trx_id)==false && valid.validDigits(thrift_trx_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Thrift Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("thrift_amt_detail.jsp");
					}else{
						ad_thrift_trx_id=Integer.parseInt(thrift_trx_id);
						rowsUpdated = dao.deleteThriftTrxById(ad_thrift_trx_id);
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Deleted!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					}//end validation
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("view_thrift_detail.jsp");
					
				}

			}else{
				System.out.print("Thrift with null value");
				AppMessage[0] = "alert-info";
				AppMessage[1] = "Thrift with null value !";
				session.setAttribute("AppMessage", AppMessage);
				response.sendRedirect("thrift_detail.jsp");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
	}
}
