package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Model.Bean.EmployeeBean;
import Model.Bean.LedgerAccountBean;
import Model.Bean.LoanTrxBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.TempTranxBean;
import Model.Bean.TransactionBean;
import Model.Bean.UserBean;
import Model.Bean.VoucherBean;
import Model.Dao.LoanTrxDao;
import Model.Dao.StaffTransactionDao;
import Model.Dao.TempTranxDao;
import Model.Dao.TransactionDao;
import Model.Dao.VoucherDao;

/**
 * Servlet implementation class AdTransaction
 */
@WebServlet("/AdTransaction")
public class AdTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TransactionDao dao=null;
    private UserBean user=null;
    public AdTransaction() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**2
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HashMap<String,String> lstMap = new HashMap<String,String>();
		try{
			dao=new TransactionDao();
		
		boolean isactive=true;
		String action=request.getParameter("action");
		if(action!=null){
			int rowsUpdated=0;
		HttpSession session=request.getSession(false);
		user=(UserBean)session.getAttribute("userbean");
		String[] AppMessage = new String[2];
		AppMessage[0] = "alert-info";
		AppMessage[1] = "welcome";
		
		session.setAttribute("AppMessage", AppMessage);
		
		if (action.equals("edit")){
			int ad_voucher_id=Integer.parseInt(request.getParameter("ad_voucher_id"));
			TempTranxDao tempdao=new TempTranxDao();
			ArrayList<TempTranxBean> templist=tempdao.getAllTempTranxByVoucherId(ad_voucher_id);
			if(templist!=null){
				for(TempTranxBean bean:templist){
					TransactionBean trxbean=new TransactionBean();
					trxbean.setCramt(bean.getCramt());
					trxbean.setCreated(new java.sql.Date(new java.util.Date().getTime()));
					trxbean.setCreatedby(user.getAd_user_id());
					trxbean.setDramt(bean.getDramt());
					trxbean.setIsactive(false);
					trxbean.setLedger(bean.getLedger());
					trxbean.setAd_account_id(bean.getLedger().getAd_account_id());
					
					trxbean.setAd_member_id(bean.getMember().getAd_member_id());
					trxbean.setNarration(bean.getNarration());
					trxbean.setTrx_date(bean.getTrx_date());
					trxbean.setUpdated(new java.sql.Date(new java.util.Date().getTime()));
					trxbean.setUpdatedby(user.getAd_user_id());
					
					trxbean.setAd_voucher_id(bean.getVoucher().getAd_voucher_id());
					dao.updateTransaction(trxbean);	
				}
				}
			response.sendRedirect("ad_voucher.jsp");
			}else if(action.equals("insert")){
				int ad_voucher_id=new VoucherDao().getMaxVoucherId();
				int d=0;
				int no=0;
				if(ad_voucher_id!=0)
				{
					TempTranxDao tempdao=new TempTranxDao();
					ArrayList<TempTranxBean> templist=tempdao.getAllTempTranxByVoucherId(ad_voucher_id);
					if(templist!=null){
					for(TempTranxBean bean:templist){
						TransactionBean trxbean=new TransactionBean();
						trxbean.setCramt(bean.getCramt());
						trxbean.setCreated(new java.sql.Date(new java.util.Date().getTime()));
						trxbean.setCreatedby(user.getAd_user_id());
						trxbean.setDramt(bean.getDramt());
						trxbean.setIsactive(bean.isIsactive());
						trxbean.setAd_account_id(bean.getLedger().getAd_account_id());
					
						trxbean.setAd_member_id(bean.getMember().getAd_member_id());
						trxbean.setNarration(bean.getNarration());
						trxbean.setTrx_date(bean.getTrx_date());
						trxbean.setUpdated(new java.sql.Date(new java.util.Date().getTime()));
						trxbean.setUpdatedby(user.getAd_user_id());
						trxbean.setAd_voucher_id(bean.getVoucher().getAd_voucher_id());
										
						d=new TransactionDao().addTransaction(trxbean);	
						d+=new TempTranxDao().deleteAllTempTranx();
						no=bean.getVoucher().getTrx_no();
					}
				}
				
					
					rowsUpdated=d;
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Voucher created successfully & sent for approval..! " ;
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
					session.setAttribute("AppMessage", AppMessage);
					Gson gson = new Gson(); 
					String json = gson.toJson(lstMap); 
					response.setContentType("application/json");
					response.getWriter().print(json);
					response.sendRedirect("ad_voucher.jsp?no="+no);
				
						
			}else{
				System.out.println("voucher id not generated:"+ad_voucher_id);
				
			}	
			}else if(action.equals("insertPayroll")){
				int ad_voucher_id=new VoucherDao().getMaxVoucherId();
				int d=0;
				int no=0;
				if(ad_voucher_id!=0)
				{
					TempTranxDao tempdao=new TempTranxDao();
					ArrayList<TempTranxBean> templist=tempdao.getAllTempTranxByVoucherId(ad_voucher_id);
					if(templist!=null){
					for(TempTranxBean bean:templist){
						TransactionBean trxbean=new TransactionBean();
						trxbean.setCramt(bean.getCramt());
						trxbean.setCreated(new java.sql.Date(new java.util.Date().getTime()));
						trxbean.setCreatedby(user.getAd_user_id());
						trxbean.setDramt(bean.getDramt());
						trxbean.setIsactive(bean.isIsactive());
						trxbean.setAd_account_id(bean.getLedger().getAd_account_id());
					
						trxbean.setAd_member_id(bean.getMember().getAd_member_id());
						trxbean.setNarration(bean.getNarration());
						trxbean.setTrx_date(bean.getTrx_date());
						trxbean.setUpdated(new java.sql.Date(new java.util.Date().getTime()));
						trxbean.setUpdatedby(user.getAd_user_id());
						trxbean.setAd_voucher_id(bean.getVoucher().getAd_voucher_id());
										
						d=new TransactionDao().addTransaction(trxbean);	
						d+=new TempTranxDao().deleteAllTempTranx();
						no=bean.getVoucher().getTrx_no();
					}
				}
				
					
					rowsUpdated=d;
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Voucher created successfully & sent for approval..! " ;
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
					session.setAttribute("AppMessage", AppMessage);
					Gson gson = new Gson(); 
					String json = gson.toJson(lstMap); 
					response.setContentType("application/json");
					response.getWriter().print(json);
					response.sendRedirect("homepage.jsp?no="+no);
				
						
			}else{
				System.out.println("voucher id not generated:"+ad_voucher_id);
				AppMessage[0] = "alert-info";
				AppMessage[1] = "Voucher not generated ...! " ;
			
				session.setAttribute("AppMessage", AppMessage);
				Gson gson = new Gson(); 
				String json = gson.toJson(lstMap); 
				response.setContentType("application/json");
				response.getWriter().print(json);
				response.sendRedirect("ad_bulk_deposit.jsp" );
			
			}	
			}else if(action.equals("insertloantrx")){
				int ad_voucher_id=new VoucherDao().getMaxVoucherId();
				int d=0;
				int no=0;
				if(ad_voucher_id!=0)
				{
					TempTranxDao tempdao=new TempTranxDao();
					ArrayList<TempTranxBean> templist=tempdao.getAllTempTranxByVoucherId(ad_voucher_id);
					if(templist!=null){
					for(TempTranxBean bean:templist){
						TransactionBean trxbean=new TransactionBean();
						trxbean.setCramt(bean.getCramt());
						trxbean.setCreated(new java.sql.Date(new java.util.Date().getTime()));
						trxbean.setCreatedby(user.getAd_user_id());
						trxbean.setDramt(bean.getDramt());
						trxbean.setIsactive(bean.isIsactive());
						trxbean.setLedger(bean.getLedger());
						trxbean.setAd_account_id(bean.getLedger().getAd_account_id());
						trxbean.setMember(bean.getMember());
						trxbean.setAd_member_id(bean.getMember().getAd_member_id());
						trxbean.setNarration(bean.getNarration());
						trxbean.setTrx_date(bean.getTrx_date());
						trxbean.setUpdated(new java.sql.Date(new java.util.Date().getTime()));
						trxbean.setUpdatedby(user.getAd_user_id());
						trxbean.setVoucher(bean.getVoucher());	
						trxbean.setAd_voucher_id(bean.getVoucher().getAd_voucher_id());
						d=new TransactionDao().addTransaction(trxbean);	
						d+=new TempTranxDao().deleteAllTempTranx();
						no=bean.getVoucher().getTrx_no();
						
						int id=Integer.parseInt(request.getParameter("loan_trx_id"));
						LoanTrxBean ltbean=new LoanTrxBean();
						ltbean.setAd_voucher_id(bean.getVoucher().getAd_voucher_id());
						ltbean.setUpdated(new java.util.Date());
						ltbean.setUpdatedby(user.getAd_user_id());
						ltbean.setStatus("Disbursed");
						ltbean.setLoan_trx_id(id);
						new LoanTrxDao().updateLoanTrx(ltbean);
					}
				}
				
					rowsUpdated=d;
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Voucher created successfully & sent for approval..! " ;
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
					session.setAttribute("AppMessage", AppMessage);
					Gson gson = new Gson(); 
					String json = gson.toJson(lstMap); 
					response.setContentType("application/json");
					response.getWriter().print(json);
					response.sendRedirect("ad_loan_approval.jsp?no="+no);
				
				
				
						
			}else{
				System.out.println("voucher id not generated:"+ad_voucher_id);
				
			}	
			}else if(action.equals("inserttrx")){
				
				
				String ad_voucher_id=request.getParameter("ad_voucher_id");
				String ad_account_id=request.getParameter("ad_account_id");
				String ad_member_id=request.getParameter("ad_member_id");
				String ad_employee_id=request.getParameter("ad_employee_id");
				String trx_date=request.getParameter("trx_date");				
				String dr_amt=request.getParameter("dr_amt");
				String cr_amt=request.getParameter("cr_amt");
				String narration=request.getParameter("narration");
				TransactionBean trxbean =new TransactionBean();
				if(cr_amt.equals("")){
					cr_amt="0.00";
				}
				if(dr_amt.equals("")){
					dr_amt="0.00";
				}
				trxbean.setCramt(Double.parseDouble(cr_amt));
				trxbean.setCreated(new java.sql.Date(new java.util.Date().getTime()));
				trxbean.setCreatedby(user.getAd_user_id());
				trxbean.setDramt(Double.parseDouble(dr_amt));
				trxbean.setIsactive(true);
				
				trxbean.setAd_account_id(Integer.parseInt(ad_account_id));
				
				trxbean.setNarration(narration);
				Date d = new SimpleDateFormat("dd/MM/yyyy").parse(trx_date);
				trxbean.setTrx_date(d);
				trxbean.setUpdated(new java.sql.Date(new java.util.Date().getTime()));
				trxbean.setUpdatedby(user.getAd_user_id());
				trxbean.setAd_voucher_id(Integer.parseInt(ad_voucher_id));
				trxbean.setAd_member_id(Integer.parseInt(ad_member_id));
				rowsUpdated= new TransactionDao().addTransaction(trxbean);
				
				if (rowsUpdated > 0) {
					AppMessage[0] = "alert-success";
					AppMessage[1] = "Trx created successfully ..! " ;
				}else{
					AppMessage[0] = "alert-info";
					AppMessage[1] = "Sorry please try again latter!";
				}
				
				session.setAttribute("AppMessage", AppMessage);
				Gson gson = new Gson(); 
				String json = gson.toJson(lstMap); 
				response.setContentType("application/json");
				response.getWriter().print(json);
				response.sendRedirect("ad_voucher_edit.jsp?ad_voucher_id="+ad_voucher_id);
				
					
				
			
				
				
				
			}else if(action.equals("edittrx")){
				String ad_voucher_id=request.getParameter("ad_voucher_id");
				String ad_trx_id=request.getParameter("ad_trx_id");
			
				String ad_account_id=request.getParameter("ad_account_id");
				String ad_member_id=request.getParameter("ad_member_id");
				String ad_employee_id=request.getParameter("ad_employee_id");
				String trx_date=request.getParameter("trx_date");
				
				String dr_amt=request.getParameter("dr_amt");
				String cr_amt=request.getParameter("cr_amt");
				String narration=request.getParameter("narration");
				
				if(ad_account_id.equals("0")!=true){
				if(cr_amt.equals("")){
					cr_amt="0.00";
				}
				if(dr_amt.equals("")){
					dr_amt="0.00";
				}
				
				TransactionBean bean =new TransactionBean();
				bean.setUpdatedby(user.getAd_user_id());
				if(ad_member_id.equals("0")!=true){
					 bean.setAd_member_id(Integer.parseInt(ad_member_id));
					 bean.setAd_employee_id(0);;
					
					
					
				}else {
					
					
					 bean.setAd_member_id(0);
					 bean.setAd_employee_id(Integer.parseInt(ad_employee_id));
				}
				
				bean.setAd_voucher_id(Integer.parseInt(ad_voucher_id));
				 bean.setAd_account_id(Integer.parseInt(ad_account_id));
				
				bean.setDramt(Double.parseDouble(dr_amt));
				bean.setCramt(Double.parseDouble(cr_amt));
				 bean.setNarration(narration);
				
				 bean.setAd_trx_id(Integer.parseInt(ad_trx_id));
				 new TransactionDao().updateTransaction(bean);
				
				
			}
				response.sendRedirect("edit_voucher.jsp?ad_voucher_id="+ad_voucher_id);
			}else if(action.equals("shareVarification")){
				String id=request.getParameter("ad_account_id");
				boolean result=false;
				if(id!=null && id.equals("")!=true){
				int ad_account_id=0;	
					try{
						ad_account_id=Integer.parseInt(id);
						//TempTranxBean bean = new TempTranxDao().getAllTempTranxByAccountId(ad_account_id);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}else if(action.equals("deletetrx")){
				System.out.println("inside delete trx");
				int ad_voucher_id=0;
				int ad_trx_temp_id=0;
				try{
					ad_voucher_id=Integer.parseInt(request.getParameter("ad_voucher_id"));
					ad_trx_temp_id=Integer.parseInt(request.getParameter("ad_trx_temp_id"));
				}catch(NumberFormatException n){
					n.printStackTrace();

				}

				TempTranxBean dvt= new TempTranxBean();
				dvt.setAd_voucher_id(ad_voucher_id);
				dvt.setAd_trx_temp_id(ad_trx_temp_id);
				rowsUpdated=new TempTranxDao().deleteTempTranx(dvt);


					
				


			}
		}
		
		
		}catch(Exception e){
			
		}
 }
}
