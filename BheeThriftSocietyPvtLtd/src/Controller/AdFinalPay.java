package Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Model.Bean.ExcessTrxBean;
import Model.Bean.FinalPayBean;
import Model.Bean.LedgerAccountBean;
import Model.Bean.LoanTrxDetailBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.MemberShareBean;
import Model.Bean.TempTranxBean;
import Model.Bean.TransactionBean;
import Model.Bean.UserBean;
import Model.Bean.VoucherBean;
import Model.Dao.ExcessTrxDao;
import Model.Dao.FinalPayDao;
import Model.Dao.LoanTrxDetailDao;
import Model.Dao.MemberRegistrationDao;
import Model.Dao.MemberShareDao;
import Model.Dao.TempTranxDao;
import Model.Dao.TransactionDao;
import Model.Dao.VoucherDao;

/**
 * Servlet implementation class AdFinalPay
 */
@WebServlet("/AdFinalPay")
public class AdFinalPay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBean user=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdFinalPay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HashMap<String,String> lstMap = new HashMap<String,String>();
		String[] AppMessage = new String[2];
		AppMessage[0] = "alert-info";
		AppMessage[1] = "welcome";
		int no =0;
		int rowsUpdated=0;
		HttpSession session=request.getSession(false);
		user=(UserBean)session.getAttribute("userbean");
	    session.setAttribute("AppMessage", AppMessage);
	    
		String action = request.getParameter("action");
		if(action.equals("insert")){
			
			int ad_member_id = Integer.parseInt(request.getParameter("ad_member_id"));					
			String thrift_amt=request.getParameter("thrift_amt");
			String thrift_int=request.getParameter("thrift_int");
			String share_amt=request.getParameter("share_amt");
			String payale_amt=request.getParameter("payale_amt");
			String excBalance=request.getParameter("excBalance");
			String net_payable=request.getParameter("net_payable");
			String compensation_amt=request.getParameter("compensation_amt");
			String reson=request.getParameter("reason");
			String reson_date=request.getParameter("reson_date");
			String final_pay_date=request.getParameter("final_pay_date");
			double tloan_amt=0.0;//request.getParameter("loan_amt");
			double tloan_int=0.0;//request.getParameter("loan_int");
			/*--------------------trx_detail-----------------*/
			String narration=request.getParameter("narration");
			String trx_by=request.getParameter("trx_by");
			String ad_bank_id=request.getParameter("ad_bank_id");
			String cheque_dd_no=request.getParameter("cheque_dd_no");
			String cheque_dd_date=request.getParameter("cheque_dd_date");
			String voucher_type=request.getParameter("voucher_type");
			String amt_in_words=request.getParameter("amt_in_words");
			/*--------------------trx_detail end-----------------*/
			System.out.println(net_payable);
			if(net_payable!=""){
				double payamt=0;
				try{
					 payamt=Double.parseDouble(net_payable);
				}catch(NumberFormatException n){
					n.printStackTrace();
				}
				
				
				if(payamt>0.00){
					
					
					user=(UserBean)session.getAttribute("userbean");
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					
					try{
					cheque_dd_date=sdf.format(sdf.parse(cheque_dd_date));
					}catch(Exception e){
						e.printStackTrace();
					}
					//ad_voucher
					VoucherBean vbean = new VoucherBean();					
					vbean.setCreatedby(user.getAd_user_id());
					vbean.setUpdatedby(user.getAd_user_id());
					vbean.setDescription("final_pay");
					vbean.setStatus("Pending");
					vbean.setVamt(payamt);
					vbean.setVfrom("Final Payment");
					//bean.setVno(vno);
					vbean.setVtype(trx_by);
					vbean.setVoucher_type(voucher_type);
					vbean.setIsactive(true);
					vbean.setInstrument_from(trx_by);
					vbean.setInstrument_no(cheque_dd_no);	
					vbean.setAmt_in_words(amt_in_words);
					
					String d1=request.getParameter("cheque_dd_date");
					Date d2=null;
					if(d1!=null){
						try{
						
						d2=sdf.parse(d1);
						}catch(ParseException p){
							p.printStackTrace();
						}
					}
							
					vbean.setInstrument_date(d2);
					Date d=(Date)request.getSession().getAttribute("openday");
					
					vbean.setTrx_date(d);
					VoucherDao vdao=new VoucherDao();					
					no=vdao.getMaxtrxNo();					
					vbean.setTrx_no(no);					
					VoucherBean  bn = new VoucherDao().addVoucher(vbean);		
					
					/// ad_trx
					
					
					if(thrift_amt!=""){
						double thrift=Double.parseDouble(thrift_amt);
						if(thrift>0.0){
						
						
						TempTranxBean tbean= new TempTranxBean();
						tbean.setCreatedby(user.getAd_user_id());
						tbean.setUpdatedby(user.getAd_user_id());
						VoucherBean voucher=new VoucherBean();
						voucher.setAd_voucher_id(bn.getAd_voucher_id());
						tbean.setVoucher(voucher);
						
						LedgerAccountBean ledger=new LedgerAccountBean();
						ledger.setAd_account_id(133);
						tbean.setLedger(ledger);
						tbean.setTrx_date(d);			
						MemberRegistrationBean member= new MemberRegistrationBean();
						member.setAd_member_id(ad_member_id);
						tbean.setMember(member);
						tbean.setDramt(thrift);
						tbean.setNarration(narration);
						new TempTranxDao().addTempTranx(tbean);
						
						
						}
					}
					
					if(thrift_int!=""){
						double thriftint=Double.parseDouble(thrift_int);
						if(thriftint>0.0){
						
						TempTranxBean tbean= new TempTranxBean();
						tbean.setCreatedby(user.getAd_user_id());
						tbean.setUpdatedby(user.getAd_user_id());
						VoucherBean voucher=new VoucherBean();
						voucher.setAd_voucher_id(bn.getAd_voucher_id());
						tbean.setVoucher(voucher);
						
						LedgerAccountBean ledger=new LedgerAccountBean();
						ledger.setAd_account_id(136);
						tbean.setLedger(ledger);
						tbean.setTrx_date(d);			
						MemberRegistrationBean member= new MemberRegistrationBean();
						member.setAd_member_id(ad_member_id);
						tbean.setMember(member);
						tbean.setDramt(thriftint);
						tbean.setNarration(narration);
						new TempTranxDao().addTempTranx(tbean);
						
						}
					}
					if(share_amt!=""){
						double share=Double.parseDouble(share_amt);	
						if(share>0.0){
						
						TempTranxBean tbean= new TempTranxBean();
						tbean.setCreatedby(user.getAd_user_id());
						tbean.setUpdatedby(user.getAd_user_id());
						VoucherBean voucher=new VoucherBean();
						voucher.setAd_voucher_id(bn.getAd_voucher_id());
						tbean.setVoucher(voucher);
						
						LedgerAccountBean ledger=new LedgerAccountBean();
						ledger.setAd_account_id(132);
						tbean.setLedger(ledger);
						tbean.setTrx_date(d);			
						MemberRegistrationBean member= new MemberRegistrationBean();
						member.setAd_member_id(ad_member_id);
						tbean.setMember(member);
						tbean.setDramt(share);
						tbean.setNarration(narration);
						new TempTranxDao().addTempTranx(tbean);
						
						
						
						}
					}
					if(excBalance!=""){
						double excess_amt=Double.parseDouble(excBalance);
						
						if(excess_amt>0.0){
						
						TempTranxBean tbean= new TempTranxBean();
						tbean.setCreatedby(user.getAd_user_id());
						tbean.setUpdatedby(user.getAd_user_id());
						VoucherBean voucher=new VoucherBean();
						voucher.setAd_voucher_id(bn.getAd_voucher_id());
						tbean.setVoucher(voucher);
						
						LedgerAccountBean ledger=new LedgerAccountBean();
						ledger.setAd_account_id(139);
						tbean.setLedger(ledger);
						tbean.setTrx_date(d);			
						MemberRegistrationBean member= new MemberRegistrationBean();
						member.setAd_member_id(ad_member_id);
						tbean.setMember(member);
						tbean.setDramt(excess_amt);
						tbean.setNarration(narration);
						new TempTranxDao().addTempTranx(tbean);
						
						double exccess_bal=new ExcessTrxDao().getMaxExcessBalanceByMemberId(ad_member_id);
						ExcessTrxBean excess=new ExcessTrxBean();
						excess.setCreatedby(user.getAd_user_id());
						excess.setUpdatedby(user.getAd_user_id());
						excess.setIsactive(false);
						excess.setTrx_date(d);
						excess.setAd_member_id(ad_member_id);
						excess.setAd_voucher_id(bn.getAd_voucher_id());
						excess.setExcess_amt(0);
						excess.setWithdrawal(excess_amt);
						excess.setBalance(exccess_bal-excess_amt);
						excess.setDetail("Final Payment");
						excess.setStatus("Pending");
						new ExcessTrxDao().addExcess(excess);
						}
					}
					if(compensation_amt!=""){
						double compensation=Double.parseDouble(compensation_amt);	
						if(compensation>0.0){
						
						TempTranxBean tbean= new TempTranxBean();
						tbean.setCreatedby(user.getAd_user_id());
						tbean.setUpdatedby(user.getAd_user_id());
						VoucherBean voucher=new VoucherBean();
						voucher.setAd_voucher_id(bn.getAd_voucher_id());
						tbean.setVoucher(voucher);
						
						LedgerAccountBean ledger=new LedgerAccountBean();
						ledger.setAd_account_id(198);
						tbean.setLedger(ledger);
						tbean.setTrx_date(d);			
						MemberRegistrationBean member= new MemberRegistrationBean();
						member.setAd_member_id(ad_member_id);
						tbean.setMember(member);
						tbean.setDramt(compensation);
						tbean.setNarration(narration);
						new TempTranxDao().addTempTranx(tbean);
						}
					}
					
					/// cr in bank ac
					
					
					TempTranxBean tbean= new TempTranxBean();
					tbean.setCreatedby(user.getAd_user_id());
					tbean.setUpdatedby(user.getAd_user_id());
					VoucherBean voucher=new VoucherBean();
					voucher.setAd_voucher_id(bn.getAd_voucher_id());
					tbean.setVoucher(voucher);
					
					LedgerAccountBean ledger=new LedgerAccountBean();
					ledger.setAd_account_id(131);
					tbean.setLedger(ledger);
					tbean.setTrx_date(d);			
					MemberRegistrationBean member= new MemberRegistrationBean();
					member.setAd_member_id(0);
					tbean.setMember(member);
					tbean.setCramt(payamt);
					tbean.setNarration(narration);
					new TempTranxDao().addTempTranx(tbean);
					
					
						FinalPayBean fbean = new FinalPayBean();
						fbean.setAd_voucher_id(bn.getAd_voucher_id());
						fbean.setAd_member_id(ad_member_id);
						fbean.setCreatedby(user.getAd_user_id());
						fbean.setUpdatedby(user.getAd_user_id());
						fbean.setFdgs_amt(Double.parseDouble(thrift_amt));
						fbean.setInt_fdgs(Double.parseDouble(thrift_int));
						fbean.setShare_amt(Double.parseDouble(share_amt));
						fbean.setLoan_amt(tloan_amt);
						fbean.setInt_loan(tloan_int);
						fbean.setCompensation_amt(Double.parseDouble(compensation_amt));
						fbean.setReson(reson);
						fbean.setAmt_in_words(amt_in_words);
						try{
						fbean.setReson_date(sdf.parse(reson_date));
						
						fbean.setFinal_pay_date(sdf.parse(final_pay_date));
						}catch(ParseException p){
							p.printStackTrace();
						}
						rowsUpdated+=new FinalPayDao().addFinalPay(fbean);
					
						// Member Account Closing
						
						
						if (rowsUpdated > 0) {
							rowsUpdated+=new MemberRegistrationDao().updateMemberStatusOfFinalPayment(user.getAd_user_id(),false, 2, ad_member_id);
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Member Account Closed Successfully..!!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						
						session.setAttribute("AppMessage", AppMessage);
						Gson gson = new Gson(); 
						String json = gson.toJson(lstMap); 
						response.setContentType("application/json");
						response.getWriter().print(json);
						response.sendRedirect("ad_temp_voucher_view.jsp?ad_voucher_id="+bn.getAd_voucher_id());
				}
				
					
				
			}
			
			
			
			
			
		}
	}

}
