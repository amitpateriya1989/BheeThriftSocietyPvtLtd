package Controller;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;
import Model.Bean.LedgerAccountBean;
import Model.Bean.LoanGuaranterBean;
import Model.Bean.LoanTrxBean;
import Model.Bean.LoanTrxDetailBean;
import Model.Bean.EmployeeBean;
import Model.Bean.TransactionBean;
import Model.Bean.UserBean;
import Model.Bean.VoucherBean;
import Model.Dao.LoanGuaranterDao;
import Model.Dao.LoanTrxDao;
import Model.Dao.StaffLoanTrxDetailDao;
import Model.Dao.StaffLoanTrxDao;
import Model.Dao.StaffTransactionDao;
import Model.Dao.VoucherDao;

/**
 * Servlet implementation class AdStaffLoanTrx
 */
@WebServlet("/AdStaffLoanTrx")
public class AdStaffLoanTrx extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StaffLoanTrxDao dao=null;
    private UserBean user=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdStaffLoanTrx() {
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
			
			dao=new StaffLoanTrxDao();
			
			String action=request.getParameter("action");
			String ad_employee_id=request.getParameter("il_ad_employee_id");
			String ad_type_of_loan_id=request.getParameter("ad_type_of_loan_id");
			String ad_loan_category_id=request.getParameter("ad_loan_category_id");
			String req_loan_amt=request.getParameter("req_loan_amt");
			String gross_sal=request.getParameter("gross_sel");
			String int_rate=request.getParameter("int_rate");
			String period_month=request.getParameter("period_month");
			String loan_date=request.getParameter("loan_date");
			String end_date=request.getParameter("end_date");
			String emi=request.getParameter("emi");
			//String witness_ad_employee_id=request.getParameter("witness_ad_employee_id");
			String guar_ad_employee_id=request.getParameter("guar_ad_employee_id");
			String chk_qnt=request.getParameter("chk_qnt");
			String chk_no_form=request.getParameter("chk_no_form");
			String chk_no_to=request.getParameter("chk_no_to");
			String chk_date=request.getParameter("chk_date");
			String bank_name=request.getParameter("bank_name");
			String bank_code=request.getParameter("bank_code");
			String loan_purpose=request.getParameter("loan_purpose");
			
			
			if(action.equals("loanvalidation")){
	
				int mid=Integer.parseInt(request.getParameter("ad_employee_id"));
				int loan_trx_id= new StaffLoanTrxDao().getOpenLoan(mid);
				
				
					response.getWriter().print(loan_trx_id);
					
				
				
				
				
			}
			
			
			
			if(action.equals("ad_date")){
				
				String loan_date1 = request.getParameter("loan_date");
				String period_month1=request.getParameter("period_month");
				int per_month=0;
				
				try{
					per_month=Integer.parseInt(period_month1);
					
				}catch(NumberFormatException n){
				n.printStackTrace();
				}
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date ldate=df.parse(loan_date1);
				
				GregorianCalendar gcal = new GregorianCalendar();
				gcal.setTime(ldate);
				gcal.add(Calendar.MONDAY, per_month);
				String d=df.format(gcal.getTime());
				
				System.out.println(d);
				
				//HttpSession session = request.getSession(false);
				//session.setAttribute("ldate", d);
				
				response.getWriter().print("success");
				
				
			}
			
			////////////////////
if(action.equals("deposit")){
				
			
				
				
				String discription="Loan_deposit";
				String status="Pending";
				double vamt=0.00;
				try{					
					 vamt=Double.parseDouble(request.getParameter("total_pay_amt").trim());	
				}catch(NumberFormatException n){
					n.printStackTrace();
				}
				
				if(vamt>0){
				String vfrom="loan";
				String vtype=request.getParameter("trx_by");
				
				//String vno="fgfds";
				String voucher_type="Received";
				
				if(vamt!=0){
					HttpSession session=request.getSession(false);
					user=(UserBean)session.getAttribute("userbean");
				VoucherBean bean=new VoucherBean();
				
				bean.setCreatedby(user.getAd_user_id());
				bean.setUpdatedby(user.getAd_user_id());
				bean.setDescription(discription);
				bean.setStatus(status);
				bean.setVamt(vamt);
				bean.setVfrom(vfrom);
				//bean.setVno(vno);
				bean.setVtype(vtype);
				bean.setIsactive(true);
				bean.setCreated(new java.util.Date());
				bean.setUpdated(new java.util.Date());
				bean.setVoucher_type(voucher_type);				
				bean.setInstrument_from(request.getParameter("ad_branch_id"));
				bean.setInstrument_no(request.getParameter("chk_no"));					
				
				String d1=request.getParameter("chk_date");
				Date d2=null;
				if(d1!=null){
					try{
					SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
					d2=sdf.parse(d1);
					}catch(ParseException p){
						p.printStackTrace();
					}
				}
						
				bean.setInstrument_date(d2);
				Date trx_date=(Date)request.getSession().getAttribute("openday");	
				bean.setTrx_date(trx_date);					
				VoucherDao vdao=new VoucherDao();				
				int no=vdao.getMaxtrxNo();					
				bean.setTrx_no(no);	
				VoucherBean  bn = new VoucherDao().addVoucher(bean);
				
				/// dr in bank ac
				
				TransactionBean tbean = new TransactionBean();
				System.out.println("voucher id= "+bn.getAd_voucher_id());
				
				tbean.setCreatedby(user.getAd_user_id());
				tbean.setUpdatedby(user.getAd_user_id());
				tbean.setAd_voucher_id(bn.getAd_voucher_id());	
				
				tbean.setAd_account_id(1);
				
				
				tbean.setTrx_date(trx_date);
				
				
				
				tbean.setAd_employee_id(0);
				
				
				tbean.setDramt(vamt);
				
				new StaffTransactionDao().addTransaction(tbean);
				
				ad_employee_id=request.getParameter("admid");
				//cr in mem loan ac
				String lbal=request.getParameter("loan_bal");
				if(lbal!=""){
				TransactionBean tbean2 = new TransactionBean();
				
				tbean2.setCreatedby(user.getAd_user_id());
				tbean2.setUpdatedby(user.getAd_user_id());
				tbean2.setAd_voucher_id(bn.getAd_voucher_id());		
				tbean2.setAd_account_id(5);
				
				tbean2.setTrx_date(trx_date);
				tbean2.setAd_employee_id(Integer.parseInt(ad_employee_id.trim()));
				
				tbean2.setCramt(Double.parseDouble(lbal));
				new StaffTransactionDao().addTransaction(tbean2);
				}
				
				String intrest=request.getParameter("intrest");
				if(intrest!=""){
				TransactionBean tbean3 = new TransactionBean();				
				tbean3.setCreatedby(user.getAd_user_id());
				tbean3.setUpdatedby(user.getAd_user_id());
				tbean3.setAd_voucher_id(bn.getAd_voucher_id());		
				tbean3.setAd_account_id(12);
				
				tbean3.setTrx_date(trx_date);
				tbean3.setAd_employee_id(Integer.parseInt(ad_employee_id.trim()));
				
				tbean3.setCramt(Double.parseDouble(intrest));
				new StaffTransactionDao().addTransaction(tbean3);
				}
				
				
				LoanTrxDetailBean ltbn = new StaffLoanTrxDetailDao().getOpenLoanBal(Integer.parseInt(ad_employee_id.trim()));	
			
				
				////
				
				LoanTrxDetailBean ltbean1=new LoanTrxDetailBean();			
				ltbean1.setCreatedby(user.getAd_user_id());
				ltbean1.setUpdatedby(user.getAd_user_id());
				ltbean1.setDeposit_amt(0.00);
				ltbean1.setBalance_amt(ltbn.getBalance_amt()+Double.parseDouble(intrest));
				ltbean1.setInterest_amt(Double.parseDouble(intrest));
				ltbean1.setTrx_date(trx_date);
				ltbean1.setAd_employee_id(Integer.parseInt(ad_employee_id.trim()));
				ltbean1.setLoan_trx_id(ltbn.getLoan_trx_id());
				new StaffLoanTrxDetailDao().addLoanTrxDetail(ltbean1);
				
				
				/////
				
				ltbn = new StaffLoanTrxDetailDao().getOpenLoanBal(Integer.parseInt(ad_employee_id.trim()));	
				LoanTrxDetailBean ltbean=new LoanTrxDetailBean();			
				ltbean.setCreatedby(user.getAd_user_id());
				ltbean.setUpdatedby(user.getAd_user_id());
				ltbean.setDeposit_amt(Double.parseDouble(lbal)+Double.parseDouble(intrest));
				ltbean.setBalance_amt(ltbn.getBalance_amt()-(Double.parseDouble(lbal)+Double.parseDouble(intrest)));
				ltbean.setInterest_amt(0.00);
				ltbean.setTrx_date(trx_date);
				ltbean.setAd_employee_id(Integer.parseInt(ad_employee_id.trim()));
				ltbean.setLoan_trx_id(ltbn.getLoan_trx_id());
				new StaffLoanTrxDetailDao().addLoanTrxDetail(ltbean);
				
				
				
				
				
				}
			}
				response.sendRedirect("staff_loan_trx.jsp");
}
			
			if(action.equals("insert")){
				
				if(ad_employee_id !="" && ad_type_of_loan_id !="" && int_rate !=""  ){
					
							
				
					int gid=0;
					HttpSession session=request.getSession(false);
					user=(UserBean)session.getAttribute("userbean");					
					//ad_voucher
					VoucherBean vbean = new VoucherBean();					
					vbean.setCreatedby(user.getAd_user_id());
					vbean.setUpdatedby(user.getAd_user_id());
					vbean.setDescription("new_loan");
					vbean.setStatus("Pending");
					vbean.setVamt(Double.parseDouble(req_loan_amt));
					//vbean.setVfrom("");
					//bean.setVno(vno);
					//vbean.setVtype("");
					//vbean.setVoucher_type("");
					vbean.setIsactive(true);
					//vbean.setInstrument_from("");
					//vbean.setInstrument_no("");					
					//vbean.setInstrument_date("");					
					Date d=(Date)request.getSession().getAttribute("openday");
					vbean.setTrx_date(d);
					VoucherDao vdao=new VoucherDao();
					
					int no=vdao.getMaxtrxNo();					
					vbean.setTrx_no(no);
					
					VoucherBean  bn = new VoucherDao().addVoucher(vbean);		
					
					
								
					//loan_trx
					LoanTrxBean bean=new LoanTrxBean();
					bean.setAd_employee_id(Integer.parseInt(ad_employee_id.trim()));
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					try{
						
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				    Date dt1 =  df.parse(loan_date);  
					bean.setissue_date(dt1);
					DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
				    Date dt2 =  df1.parse(end_date);  
					bean.setEnd_date(dt2);				
					bean.setLoan_purpose(loan_purpose);
					
					}catch(ParseException p){
						p.printStackTrace();
					}
					
			
					try{
						// gid =Integer.parseInt(guar_ad_employee_id);
						bean.setLoan_type(Integer.parseInt(ad_type_of_loan_id));
						bean.setLoan_cataegory(Integer.parseInt(ad_loan_category_id));
						bean.setLoan_amt(Double.parseDouble(req_loan_amt));
						bean.setIntrest_rate(Double.parseDouble(int_rate));
						bean.setPeriod_month(Integer.parseInt(period_month));
						bean.setEmi(Double.parseDouble(emi));
						bean.setWitnes(0);//Integer.parseInt(witness_ad_employee_id));
						bean.setGuaranter(gid);
						bean.setAd_voucher_id(bn.getAd_voucher_id());
						
					}catch(NumberFormatException n){
						n.printStackTrace();
					}
					
					int id =	dao.addLoanTrx(bean);			
					
					
					
					
					
					
					//guranter
					/*if(gid!=0){
						
						LoanGuaranterBean lg = new LoanGuaranterBean();
						lg.setCreatedby(user.getAd_user_id());
						lg.setUpdatedby(user.getAd_user_id());
						lg.setAd_employee_id(gid);
						lg.setChk_bank_code(bank_code);
						lg.setChk_bank_name(bank_name);
						lg.setChk_qnt(Integer.parseInt(chk_qnt));
						lg.setChk_no_form(Integer.parseInt(chk_no_form));
						lg.setChk_no_to(Integer.parseInt(chk_no_to));
						DateFormat df11 = new SimpleDateFormat("yyyy-MM-dd");
					    Date dt12 =  df11.parse(chk_date);  
						lg.setChk_date(dt12);
						lg.setLoan_trx_id(id);
						new LoanGuaranterDao().addLoanGuaranter(lg);
						
					}*/
					
					Date trx_date =(Date)session.getAttribute("openday");
					
					/// ad_loan_trx
					LoanTrxDetailBean ltdbean = new LoanTrxDetailBean();
					ltdbean.setCreatedby(user.getAd_user_id());
					ltdbean.setUpdatedby(user.getAd_user_id());
					ltdbean.setAd_employee_id(Integer.parseInt(ad_employee_id.trim()));
					ltdbean.setLoan_trx_id(id);
					ltdbean.setBalance_amt(Double.parseDouble(req_loan_amt));
					ltdbean.setTrx_date(trx_date);
					ltdbean.setAd_voucher_id(bn.getAd_voucher_id());
					new StaffLoanTrxDetailDao().addLoanTrxDetail(ltdbean);
					
					
					/// ad_trx
					
					/// dr in bank ac
					
					TransactionBean tbean = new TransactionBean();
					
					
					tbean.setCreatedby(user.getAd_user_id());
					tbean.setUpdatedby(user.getAd_user_id());
					tbean.setVoucher(bn);	
					LedgerAccountBean beanl = new LedgerAccountBean();
					beanl.setAd_account_id(1);
					
					tbean.setLedger(beanl);
					tbean.setTrx_date(d);
					
					EmployeeBean mbean=new EmployeeBean();
					
					mbean.setAd_employee_id(0);
					tbean.setEmp(mbean);
					
					tbean.setDramt(Double.parseDouble(req_loan_amt));
					
					new StaffTransactionDao().addTransaction(tbean);
					
					
					//dr in mem loan ac
					TransactionBean tbean2 = new TransactionBean();					
					tbean2.setCreatedby(user.getAd_user_id());
					tbean2.setUpdatedby(user.getAd_user_id());
					tbean2.setAd_voucher_id(bn.getAd_voucher_id());		
					tbean2.setAd_account_id(5);
					
					tbean2.setTrx_date(d);
					tbean2.setAd_employee_id(Integer.parseInt(ad_employee_id.trim()));
					
					tbean2.setCramt(Double.parseDouble(req_loan_amt));
					new StaffTransactionDao().addTransaction(tbean2);
					
					
					
					
					response.sendRedirect("staff_loan_trx.jsp");
				}else{
					
				}
				
				
				
		}
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
	
	

}
