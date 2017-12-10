package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import Model.Bean.ExcessTrxBean;
import Model.Bean.LedgerAccountBean;
import Model.Bean.LoanTrxBean;
import Model.Bean.LoanTrxDetailBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.NominationBean;
import Model.Bean.PayrollAdviseBean;
import Model.Bean.PayrollBean;
import Model.Bean.PayrollTempBean;
import Model.Bean.TempTranxBean;
import Model.Bean.ThriftTrxBean;
import Model.Bean.TransactionBean;
import Model.Bean.UserBean;
import Model.Bean.VoucherBean;
import Model.Dao.BankBranchDao;
import Model.Dao.ExcessTrxDao;
import Model.Dao.LoanTrxDao;
import Model.Dao.LoanTrxDetailDao;
import Model.Dao.MemberRegistrationDao;
import Model.Dao.NominationDao;
import Model.Dao.PayrollAdviceDao;
import Model.Dao.PayrollDao;
import Model.Dao.PayrollTempDao;
import Model.Dao.TempTranxDao;
import Model.Dao.ThriftTrxDao;
import Model.Dao.TransactionDao;
import Model.Dao.VoucherDao;

/**
 * Servlet implementation class AdBulkDeposit
 */
@WebServlet("/AdBulkDeposit")
public class AdBulkDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VoucherDao dao=null;
	private UserBean user=null;
	private static final String UPLOAD_DIRECTORY = "ExcelFile";
	static HSSFRow row;
	HttpSession session=null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdBulkDeposit() {
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
		int rowsUpdated=0;
		session=request.getSession(false);
		user=(UserBean)session.getAttribute("userbean");

		String[] AppMessage = new String[2];
		AppMessage[0] = "alert-info";
		AppMessage[1] = "welcome";
		session.setAttribute("AppMessage", AppMessage);

		dao=new VoucherDao();
		String action=request.getParameter("action");

		Date trx_date=(Date)request.getSession().getAttribute("openday");
		VoucherBean  bn =null;

		if(action.equals("insert")){
			String cntr=request.getParameter("counter");	
			int no=0;

			int counter=0;
			int ad_account_id_d=0;
			int batch_no=0;
			// ad voucher 
			String discription="Payroll";
			String status="Pending";
			double vamt=0.00;
			double excess_amt_other=0.0;
			String excess_other=request.getParameter("excess_amt_other");
			String excess_narration=request.getParameter("excess_amt_narration");
			String bt_no=request.getParameter("batch_no");
			String amt_in_words=request.getParameter("amt_in_words");
			try{
				counter=Integer.parseInt(cntr);
				vamt=Double.parseDouble(request.getParameter("enter_amt").trim());
				excess_amt_other=Double.parseDouble(excess_other);
				ad_account_id_d=Integer.parseInt(request.getParameter("ad_account_id_d"));
				batch_no=Integer.parseInt(bt_no);
			}catch(NumberFormatException n){
				n.printStackTrace();
			}
			String vfrom="payroll";
			String vtype=request.getParameter("trx_mode");

			//String vno="fgfds";
			String voucher_type="Received";

			if(vamt!=0){

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
				bean.setAmt_in_words(amt_in_words);
				if(request.getParameter("chq_no")!="" && request.getParameter("ad_branch_id")!="" && request.getParameter("chk_date")!="")
				{
					bean.setInstrument_from(request.getParameter("ad_branch_id"));
					bean.setInstrument_no(request.getParameter("chq_no"));


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


				}//end of if condition



				bean.setTrx_date(trx_date);

				VoucherDao vdao=new VoucherDao();

				no=vdao.getMaxtrxNo();					
				bean.setTrx_no(no);	
				bn = dao.addVoucher(bean);




				//////trx debit

			
				
				TempTranxBean tbean = new TempTranxBean();

				tbean.setCreatedby(user.getAd_user_id());
				tbean.setUpdatedby(user.getAd_user_id());
				
				VoucherBean voucher=new VoucherBean();
				voucher.setAd_voucher_id(bn.getAd_voucher_id());
				tbean.setVoucher(voucher);
				
				LedgerAccountBean ledger=new LedgerAccountBean();
				ledger.setAd_account_id(ad_account_id_d);
				tbean.setLedger(ledger);
				
							
				
				tbean.setTrx_date(trx_date);			
						
				MemberRegistrationBean member= new MemberRegistrationBean();
				member.setAd_member_id(0);
				tbean.setMember(member);
						
				tbean.setDramt(vamt);			
				new TempTranxDao().addTempTranx(tbean);



			}	//end of voucher amt condition


			/// trx credit

			//System.out.println("counter :"+counter);
			int mid=0;
			for(int i=1;i<=counter;i++){

				if(request.getParameter("chk_"+i)!=null){					

					mid=Integer.parseInt(request.getParameter("ad_member_id_"+i));
					MemberRegistrationBean mbean1=new MemberRegistrationDao().getMemberName(mid);
					double thrift=Double.parseDouble(request.getParameter("thrift_"+i));
					double loan=Double.parseDouble(request.getParameter("loan_"+i));
					double excess=Double.parseDouble(request.getParameter("excess_"+i));
                   /* double dcf= Double.parseDouble(request.getParameter("dcf_"+i));*/
					double dcf=0;
                    double festival= Double.parseDouble(request.getParameter("festival_"+i));
                   /* double guesthouse= Double.parseDouble(request.getParameter("guesthouse_"+i));*/
                    double guesthouse=0;
                    String narration=request.getParameter("narration_"+i);
                    PayrollTempBean payrollBean=new PayrollTempBean();
                    payrollBean.setBatch_no(batch_no);
                    
                    System.out.println("main loan member :"+loan);
                   
                    if(thrift!=0){

						
                    	
                    	TempTranxBean tbean = new TempTranxBean();					

						tbean.setCreatedby(user.getAd_user_id());
						tbean.setUpdatedby(user.getAd_user_id());
						VoucherBean voucher=new VoucherBean();
						voucher.setAd_voucher_id(bn.getAd_voucher_id());
						tbean.setVoucher(voucher);
						
						LedgerAccountBean ledger=new LedgerAccountBean();
						ledger.setAd_account_id(133);
						tbean.setLedger(ledger);			
						
						tbean.setTrx_date(trx_date);			
									
						MemberRegistrationBean member= new MemberRegistrationBean();
						member.setAd_member_id(mid);
						tbean.setMember(member);
								
						tbean.setCramt(thrift);
						tbean.setNarration(narration);
						new TempTranxDao().addTempTranx(tbean);
						
						

						ThriftTrxBean thriftBean=new ThriftTrxBean();
						thriftBean.setAd_member_id(mid);
						thriftBean.setAd_voucher_id(bn.getAd_voucher_id());
						thriftBean.setCreatedby(user.getAd_user_id());
						thriftBean.setUpdatedby(user.getAd_user_id());
						thriftBean.setStatus("Pending");
						thriftBean.setIsactive(false);
						thriftBean.setThrift_amt(thrift);
						thriftBean.setTrx_date(trx_date);
						thriftBean.setThrift_int(0);
						thriftBean.setWithdrawal(0);
						thriftBean.setDiscription("");
						double bal=new ThriftTrxDao().getMaxThriftBalanceByMemberId(mid);
						bal+=thrift;
						thriftBean.setBalance(bal);
						new ThriftTrxDao().addThriftTrx(thriftBean);
						payrollBean.setThrift_amt(thrift);


					}//end of thrift deposit



					if(loan>0){

						
						
						ArrayList<LoanTrxBean> loanList=new LoanTrxDao().getAllLoanTrxBymemId(mid,"Open",1);

						if(loanList.isEmpty()!=true){

							for(LoanTrxBean loanBean:loanList){
								double balance = new LoanTrxDetailDao().getOpenLoanBal(loanBean);
									
									TempTranxBean tbean = new TempTranxBean();
									LoanTrxDetailBean ltbean=new LoanTrxDetailBean();
									ltbean.setCreatedby(user.getAd_user_id());
									ltbean.setUpdatedby(user.getAd_user_id());

									if(loan>=balance){
										ltbean.setDeposit_amt(balance);
										ltbean.setBalance_amt(0);
										ltbean.setAd_voucher_id(bn.getAd_voucher_id());
										System.out.println("inside loan balance greater than bal. amt");
										if(loanBean.getLoan_cataegory()==1 && loanBean.getLoan_type()==1){

											////// ad_trx main loan

											tbean.setCreatedby(user.getAd_user_id());
											tbean.setUpdatedby(user.getAd_user_id());
											VoucherBean voucher=new VoucherBean();
											voucher.setAd_voucher_id(bn.getAd_voucher_id());
											tbean.setVoucher(voucher);
											
											LedgerAccountBean ledger=new LedgerAccountBean();
											ledger.setAd_account_id(134);
											tbean.setLedger(ledger);
											tbean.setTrx_date(trx_date);			
											MemberRegistrationBean member= new MemberRegistrationBean();
											member.setAd_member_id(mid);
											tbean.setMember(member);	
											tbean.setCramt(loan);	
											tbean.setNarration(narration);
											new TempTranxDao().addTempTranx(tbean);
											
											loan=loan-balance;
											
											//verify interest on loan
											
											double interest=getInterst(mid, loanBean.getLoan_trx_id());
											
											if(loan>=interest){
												
												tbean.setCreatedby(user.getAd_user_id());
												tbean.setUpdatedby(user.getAd_user_id());
												voucher=new VoucherBean();
												voucher.setAd_voucher_id(bn.getAd_voucher_id());
												tbean.setVoucher(voucher);
												
												ledger=new LedgerAccountBean();
												ledger.setAd_account_id(134);//loan account
												tbean.setLedger(ledger);
												tbean.setTrx_date(trx_date);			
												member= new MemberRegistrationBean();
												member.setAd_member_id(mid);
												tbean.setMember(member);	
												tbean.setCramt(0);
												tbean.setDramt(interest);
												tbean.setNarration("Loan interest Deposit");
												new TempTranxDao().addTempTranx(tbean);
												
												
												
												tbean.setCreatedby(user.getAd_user_id());
												tbean.setUpdatedby(user.getAd_user_id());
												voucher=new VoucherBean();
												voucher.setAd_voucher_id(bn.getAd_voucher_id());
												tbean.setVoucher(voucher);
												
												ledger=new LedgerAccountBean();
												ledger.setAd_account_id(138);//interest account
												tbean.setLedger(ledger);
												tbean.setTrx_date(trx_date);			
												member= new MemberRegistrationBean();
												member.setAd_member_id(mid);
												tbean.setMember(member);	
												tbean.setDramt(0);	
												tbean.setCramt(interest);
												tbean.setNarration("Interest on Main Loan A/c");
												new TempTranxDao().addTempTranx(tbean);
													
												
												ltbean.setInterest_amt(interest);
												
												loan=loan-interest;
												
												
												
											}
											
											//excess from loan account
											if(loan>0){						
											
											ltbean.setExcess_amt(loan);
											
											//transfer excess amt from loan to excess account
											
											tbean.setCreatedby(user.getAd_user_id());
											tbean.setUpdatedby(user.getAd_user_id());
											voucher=new VoucherBean();
											voucher.setAd_voucher_id(bn.getAd_voucher_id());
											tbean.setVoucher(voucher);
											
											ledger=new LedgerAccountBean();
											ledger.setAd_account_id(134);//loan account
											tbean.setLedger(ledger);
											tbean.setTrx_date(trx_date);			
											member= new MemberRegistrationBean();
											member.setAd_member_id(mid);
											tbean.setMember(member);	
											tbean.setCramt(0);
											tbean.setDramt(loan);
											tbean.setNarration("Transfer to Excess A/c");
											new TempTranxDao().addTempTranx(tbean);
											
																					
											tbean.setCreatedby(user.getAd_user_id());
											tbean.setUpdatedby(user.getAd_user_id());
											voucher=new VoucherBean();
											voucher.setAd_voucher_id(bn.getAd_voucher_id());
											tbean.setVoucher(voucher);
											
											ledger=new LedgerAccountBean();
											ledger.setAd_account_id(139);//excess account
											tbean.setLedger(ledger);
											tbean.setTrx_date(trx_date);			
											member= new MemberRegistrationBean();
											member.setAd_member_id(mid);
											tbean.setMember(member);	
											tbean.setDramt(0);	
											tbean.setCramt(loan);
											tbean.setNarration("Transfer from Main Loan A/c");
											new TempTranxDao().addTempTranx(tbean);
											
											
																	
											
											payrollBean.setMain_loan_amt(loan+balance+interest);
											
											double exccess_bal=new ExcessTrxDao().getMaxExcessBalanceByMemberId(mid);
											ExcessTrxBean excessBean=new ExcessTrxBean();
											excessBean.setCreatedby(user.getAd_user_id());
											excessBean.setUpdatedby(user.getAd_user_id());
											excessBean.setIsactive(false);
											excessBean.setTrx_date(trx_date);
											excessBean.setAd_member_id(mid);
											excessBean.setAd_voucher_id(bn.getAd_voucher_id());
											excessBean.setExcess_amt(loan);
											excessBean.setWithdrawal(0);
											excessBean.setBalance(exccess_bal+loan);
											excessBean.setDetail("Excess From Main Loan Deposit");
											excessBean.setStatus("Pending");
											new ExcessTrxDao().addExcess(excessBean);
											
											VoucherBean vbean=new VoucherDao().getVoucherByVoucherId(bn.getAd_voucher_id());
											double amt=vbean.getVamt()+loan+interest;
											new VoucherDao().updateVoucherAmt(bn.getAd_voucher_id(),amt);
											loan=0;
											
											}//end of excess account
											
											
												if(loan==0){
												
												new LoanTrxDao().CloseLoanTrx(ltbean.getLoan_trx_id());
												}
											
										    }//end of main loan
										
										
									}else{

									if(loan>=loanBean.getEmi()){
											
										ltbean.setDeposit_amt(loan);
										//loan=loan-loanBean.getEmi();
										balance=balance-loan;
										
										ltbean.setBalance_amt(balance);
										ltbean.setAd_voucher_id(bn.getAd_voucher_id());
										if(loanBean.getLoan_cataegory()==1 && loanBean.getLoan_type()==1){

											////// ad_trx main loan

											tbean.setCreatedby(user.getAd_user_id());
											tbean.setUpdatedby(user.getAd_user_id());
											VoucherBean voucher=new VoucherBean();
											voucher.setAd_voucher_id(bn.getAd_voucher_id());
											tbean.setVoucher(voucher);
														
											LedgerAccountBean ledger=new LedgerAccountBean();
											ledger.setAd_account_id(134);
											tbean.setLedger(ledger);
											tbean.setTrx_date(trx_date);			
											MemberRegistrationBean member= new MemberRegistrationBean();
											member.setAd_member_id(mid);
											tbean.setMember(member);	
											tbean.setCramt(loan);	
											tbean.setNarration(narration);
											new TempTranxDao().addTempTranx(tbean);
											payrollBean.setMain_loan_amt(loan);
											
											if(balance==0){
												
												new LoanTrxDao().CloseLoanTrx(ltbean.getLoan_trx_id());
												}
										    }
									}else {
										ltbean.setDeposit_amt(loan);
										balance=balance-loan;
										ltbean.setBalance_amt(balance);
										ltbean.setAd_voucher_id(bn.getAd_voucher_id());

										System.out.println("inside loan < EMI ="+(loan));


										if(loanBean.getLoan_cataegory()==1 && loanBean.getLoan_type()==1){

											////// ad_trx main loan

											tbean.setCreatedby(user.getAd_user_id());
											tbean.setUpdatedby(user.getAd_user_id());
											VoucherBean voucher=new VoucherBean();
											voucher.setAd_voucher_id(bn.getAd_voucher_id());
											tbean.setVoucher(voucher);	
											
											LedgerAccountBean ledger=new LedgerAccountBean();
											ledger.setAd_account_id(134);
											tbean.setLedger(ledger);
														
											
											tbean.setTrx_date(trx_date);			
												
											MemberRegistrationBean member= new MemberRegistrationBean();
											member.setAd_member_id(mid);
											tbean.setMember(member);
												
											tbean.setCramt(loan);	
											tbean.setNarration(narration);
											new TempTranxDao().addTempTranx(tbean);
											payrollBean.setMain_loan_amt(loan);
											
											if(balance==0){
												
												new LoanTrxDao().CloseLoanTrx(ltbean.getLoan_trx_id());
												}

										}

										loan=0;
									}
								}
									ltbean.setAd_voucher_id(bn.getAd_voucher_id());
									ltbean.setTrx_date(trx_date);
									ltbean.setAd_member_id(mid);
									ltbean.setLoan_trx_id(loanBean.getLoan_trx_id());	
									new LoanTrxDetailDao().addLoanTrxDetail(ltbean);




								

							}


						}else{
							TempTranxBean tbean=new TempTranxBean(); 
							tbean.setCreatedby(user.getAd_user_id());
							tbean.setUpdatedby(user.getAd_user_id());
							VoucherBean voucher=new VoucherBean();
							voucher.setAd_voucher_id(bn.getAd_voucher_id());
							tbean.setVoucher(voucher);
							
							LedgerAccountBean ledger=new LedgerAccountBean();
							ledger.setAd_account_id(134);//loan account
							tbean.setLedger(ledger);
							tbean.setTrx_date(trx_date);			
							MemberRegistrationBean member= new MemberRegistrationBean();
							member.setAd_member_id(mid);
							tbean.setMember(member);	
							tbean.setCramt(loan);
							tbean.setDramt(0);
							tbean.setNarration("Transfer to Excess A/c");
							new TempTranxDao().addTempTranx(tbean);
							
														
							payrollBean.setMain_loan_amt(loan);
							
							double exccess_bal=new ExcessTrxDao().getMaxExcessBalanceByMemberId(mid);
							ExcessTrxBean excessBean=new ExcessTrxBean();
							excessBean.setCreatedby(user.getAd_user_id());
							excessBean.setUpdatedby(user.getAd_user_id());
							excessBean.setIsactive(false);
							excessBean.setTrx_date(trx_date);
							excessBean.setAd_member_id(mid);
							excessBean.setAd_voucher_id(bn.getAd_voucher_id());
							excessBean.setExcess_amt(loan);
							excessBean.setWithdrawal(0);
							excessBean.setBalance(exccess_bal+loan);
							excessBean.setDetail("Excess From Main Loan Deposit");
							excessBean.setStatus("Pending");
							new ExcessTrxDao().addExcess(excessBean);
							
							
							
							
						}
					}//end of loan deposit
					
					if(festival>0){
						
						ArrayList<LoanTrxBean> loanList=new LoanTrxDao().getAllLoanTrxBymemId(mid,"Open",2);

						if(loanList.isEmpty()!=true){

							for(LoanTrxBean loanBean:loanList){
								
								LoanTrxDetailBean ltbean=new LoanTrxDetailBean();
								TempTranxBean tbean = new TempTranxBean();
								
								if(loanBean.getLoan_cataegory()==1 && loanBean.getLoan_type()==2){

								double balance = new LoanTrxDetailDao().getOpenLoanBal(loanBean);

								//loan interest deposit calculation
								System.out.println("inseide festival loan ="+loan);
								
									
									
									ltbean.setCreatedby(user.getAd_user_id());
									ltbean.setUpdatedby(user.getAd_user_id());
									
									if(festival>=balance){
										ltbean.setDeposit_amt(balance);
										ltbean.setBalance_amt(0);
										ltbean.setAd_voucher_id(bn.getAd_voucher_id());
										
										if(loanBean.getLoan_cataegory()==1 && loanBean.getLoan_type()==2){

											////// ad_trx main loan

											tbean.setCreatedby(user.getAd_user_id());
											tbean.setUpdatedby(user.getAd_user_id());
											VoucherBean voucher=new VoucherBean();
											voucher.setAd_voucher_id(bn.getAd_voucher_id());
											tbean.setVoucher(voucher);
											
											LedgerAccountBean ledger=new LedgerAccountBean();
											ledger.setAd_account_id(10);
											tbean.setLedger(ledger);
											tbean.setTrx_date(trx_date);			
											MemberRegistrationBean member= new MemberRegistrationBean();
											member.setAd_member_id(mid);
											tbean.setMember(member);	
											tbean.setCramt(festival);	
											tbean.setNarration(narration);
											new TempTranxDao().addTempTranx(tbean);
											
											festival=festival-balance;
											
											//verify interest on loan
											
											double interest=getInterst(mid, loanBean.getLoan_trx_id());
											
											if(festival>=interest){
												
												tbean.setCreatedby(user.getAd_user_id());
												tbean.setUpdatedby(user.getAd_user_id());
												voucher=new VoucherBean();
												voucher.setAd_voucher_id(bn.getAd_voucher_id());
												tbean.setVoucher(voucher);
												
												ledger=new LedgerAccountBean();
												ledger.setAd_account_id(10);//loan account
												tbean.setLedger(ledger);
												tbean.setTrx_date(trx_date);			
												member= new MemberRegistrationBean();
												member.setAd_member_id(mid);
												tbean.setMember(member);	
												tbean.setCramt(0);
												tbean.setDramt(interest);
												tbean.setNarration("Loan interest Deposit");
												new TempTranxDao().addTempTranx(tbean);
												
												
												
												tbean.setCreatedby(user.getAd_user_id());
												tbean.setUpdatedby(user.getAd_user_id());
												voucher=new VoucherBean();
												voucher.setAd_voucher_id(bn.getAd_voucher_id());
												tbean.setVoucher(voucher);
												
												ledger=new LedgerAccountBean();
												ledger.setAd_account_id(200);//interest account
												tbean.setLedger(ledger);
												tbean.setTrx_date(trx_date);			
												member= new MemberRegistrationBean();
												member.setAd_member_id(mid);
												tbean.setMember(member);	
												tbean.setDramt(0);	
												tbean.setCramt(interest);
												tbean.setNarration("Interest on Main Loan A/c");
												new TempTranxDao().addTempTranx(tbean);
													
												
												ltbean.setInterest_amt(interest);
												
												festival=festival-interest;
												
												
												
											}
											
											//excess from loan account
											if(festival>0){						
											
											ltbean.setExcess_amt(festival);
											//transfer excess amt from loan to excess account
									
											
											tbean.setCreatedby(user.getAd_user_id());
											tbean.setUpdatedby(user.getAd_user_id());
											voucher=new VoucherBean();
											voucher.setAd_voucher_id(bn.getAd_voucher_id());
											tbean.setVoucher(voucher);
											
											ledger=new LedgerAccountBean();
											ledger.setAd_account_id(10);//loan account
											tbean.setLedger(ledger);
											tbean.setTrx_date(trx_date);			
											member= new MemberRegistrationBean();
											member.setAd_member_id(mid);
											tbean.setMember(member);	
											tbean.setCramt(0);
											tbean.setDramt(festival);
											tbean.setNarration("Transfer to Excess A/c");
											new TempTranxDao().addTempTranx(tbean);
											
											
											
											tbean.setCreatedby(user.getAd_user_id());
											tbean.setUpdatedby(user.getAd_user_id());
											voucher=new VoucherBean();
											voucher.setAd_voucher_id(bn.getAd_voucher_id());
											tbean.setVoucher(voucher);
											
											ledger=new LedgerAccountBean();
											ledger.setAd_account_id(139);//excess account
											tbean.setLedger(ledger);
											tbean.setTrx_date(trx_date);			
											member= new MemberRegistrationBean();
											member.setAd_member_id(mid);
											tbean.setMember(member);	
											tbean.setDramt(0);	
											tbean.setCramt(festival);
											tbean.setNarration("Transfer from Festival Loan A/c");
											new TempTranxDao().addTempTranx(tbean);
											
											
											double exccess_bal=new ExcessTrxDao().getMaxExcessBalanceByMemberId(mid);
											ExcessTrxBean excessBean=new ExcessTrxBean();
											excessBean.setCreatedby(user.getAd_user_id());
											excessBean.setUpdatedby(user.getAd_user_id());
											excessBean.setIsactive(false);
											excessBean.setTrx_date(trx_date);
											excessBean.setAd_member_id(mid);
											excessBean.setAd_voucher_id(bn.getAd_voucher_id());
											excessBean.setExcess_amt(loan);
											excessBean.setWithdrawal(0);
											excessBean.setBalance(exccess_bal+loan);
											excessBean.setDetail("Excess From Festival Loan Deposit");
											excessBean.setStatus("Pending");
											new ExcessTrxDao().addExcess(excessBean);
											
											
											payrollBean.setMain_loan_amt(loan);
											
											festival=0;
											
											}
											
											
											if(festival==0){
												
												new LoanTrxDao().CloseLoanTrx(ltbean.getLoan_trx_id());
												}
											
										    }//end of main loan
										
										
									}else{

									if(festival>=loanBean.getEmi()){
											
										ltbean.setDeposit_amt(festival);
										//festival=festival-loanBean.getEmi();
										balance=balance-festival;
										
										ltbean.setBalance_amt(balance);
										ltbean.setAd_voucher_id(bn.getAd_voucher_id());
										
											////// ad_trx main loan

											tbean.setCreatedby(user.getAd_user_id());
											tbean.setUpdatedby(user.getAd_user_id());
											VoucherBean voucher=new VoucherBean();
											voucher.setAd_voucher_id(bn.getAd_voucher_id());
											tbean.setVoucher(voucher);
											
											LedgerAccountBean ledger=new LedgerAccountBean();
											ledger.setAd_account_id(10);
											tbean.setLedger(ledger);
											tbean.setTrx_date(trx_date);			
											MemberRegistrationBean member= new MemberRegistrationBean();
											member.setAd_member_id(mid);
											tbean.setMember(member);
											
												
											tbean.setCramt(festival);	
											tbean.setNarration(narration);
											new TempTranxDao().addTempTranx(tbean);
											payrollBean.setFestival_loan_amt(festival);
											
											if(balance==0){
												
												new LoanTrxDao().CloseLoanTrx(ltbean.getLoan_trx_id());
												}
										} else {
										ltbean.setDeposit_amt(festival);
										balance=balance-festival;
										ltbean.setBalance_amt(balance);
										ltbean.setAd_voucher_id(bn.getAd_voucher_id());

									

											tbean.setCreatedby(user.getAd_user_id());
											tbean.setUpdatedby(user.getAd_user_id());
											VoucherBean voucher=new VoucherBean();
											voucher.setAd_voucher_id(bn.getAd_voucher_id());
											tbean.setVoucher(voucher);
											
											LedgerAccountBean ledger=new LedgerAccountBean();
											ledger.setAd_account_id(10);
											tbean.setLedger(ledger);
											tbean.setTrx_date(trx_date);			
											MemberRegistrationBean member= new MemberRegistrationBean();
											member.setAd_member_id(mid);
											tbean.setMember(member);
												
											tbean.setCramt(festival);	
											tbean.setNarration(narration);
											new TempTranxDao().addTempTranx(tbean);
											payrollBean.setFestival_loan_amt(festival);
      										festival=0;
      										if(balance==0){
												
												new LoanTrxDao().CloseLoanTrx(ltbean.getLoan_trx_id());
												}
									}
									}
									ltbean.setAd_voucher_id(bn.getAd_voucher_id());
									ltbean.setTrx_date(trx_date);
									ltbean.setAd_member_id(mid);
									ltbean.setLoan_trx_id(loanBean.getLoan_trx_id());	
									new LoanTrxDetailDao().addLoanTrxDetail(ltbean);




								

							}


						}
					}else{
						TempTranxBean tbean=new TempTranxBean(); 
						tbean.setCreatedby(user.getAd_user_id());
						tbean.setUpdatedby(user.getAd_user_id());
						VoucherBean voucher=new VoucherBean();
						voucher.setAd_voucher_id(bn.getAd_voucher_id());
						tbean.setVoucher(voucher);
						
						LedgerAccountBean ledger=new LedgerAccountBean();
						ledger.setAd_account_id(139);//Excess account
						tbean.setLedger(ledger);
						tbean.setTrx_date(trx_date);			
						MemberRegistrationBean member= new MemberRegistrationBean();
						member.setAd_member_id(mid);
						tbean.setMember(member);	
						tbean.setCramt(festival);
						tbean.setDramt(0);
						tbean.setNarration("Transfer to Excess A/c");
						new TempTranxDao().addTempTranx(tbean);
						
													
						payrollBean.setMain_loan_amt(festival);
						
						double exccess_bal=new ExcessTrxDao().getMaxExcessBalanceByMemberId(mid);
						ExcessTrxBean excessBean=new ExcessTrxBean();
						excessBean.setCreatedby(user.getAd_user_id());
						excessBean.setUpdatedby(user.getAd_user_id());
						excessBean.setIsactive(false);
						excessBean.setTrx_date(trx_date);
						excessBean.setAd_member_id(mid);
						excessBean.setAd_voucher_id(bn.getAd_voucher_id());
						excessBean.setExcess_amt(festival);
						excessBean.setWithdrawal(0);
						excessBean.setBalance(exccess_bal+festival);
						excessBean.setDetail("Excess From Main Loan Deposit");
						excessBean.setStatus("Pending");
						new ExcessTrxDao().addExcess(excessBean);
						
						
						
						
					}
				}
					if(dcf>0){
						
						TempTranxBean tbean = new TempTranxBean();					

						tbean.setCreatedby(user.getAd_user_id());
						tbean.setUpdatedby(user.getAd_user_id());
						VoucherBean voucher=new VoucherBean();
						voucher.setAd_voucher_id(bn.getAd_voucher_id());
						tbean.setVoucher(voucher);
						
						LedgerAccountBean ledger=new LedgerAccountBean();
						ledger.setAd_account_id(8);
						tbean.setLedger(ledger);
						tbean.setTrx_date(trx_date);			
						MemberRegistrationBean member= new MemberRegistrationBean();
						member.setAd_member_id(mid);
						tbean.setMember(member);
								
						tbean.setCramt(dcf);
						tbean.setNarration(narration);
						new TempTranxDao().addTempTranx(tbean);
						payrollBean.setDcf_amt(dcf);
					}
					
					if(guesthouse>0){
						
						
					}
					
					if(excess>0){
						
							TempTranxBean tbean = new TempTranxBean();	
											
							tbean.setCreatedby(user.getAd_user_id());
							tbean.setUpdatedby(user.getAd_user_id());
							VoucherBean voucher=new VoucherBean();
							voucher.setAd_voucher_id(bn.getAd_voucher_id());
							tbean.setVoucher(voucher);
							
							LedgerAccountBean ledger=new LedgerAccountBean();
							ledger.setAd_account_id(141);
							tbean.setLedger(ledger);
							tbean.setTrx_date(trx_date);			
							MemberRegistrationBean member= new MemberRegistrationBean();
							member.setAd_member_id(mid);
							tbean.setMember(member);
							tbean.setCramt(excess);
							tbean.setNarration(narration);
							new TempTranxDao().addTempTranx(tbean);
							payrollBean.setExcess_amt(excess);
							
							double exccess_bal=new ExcessTrxDao().getMaxExcessBalanceByMemberId(mid);
							ExcessTrxBean excessbean=new ExcessTrxBean();
							excessbean.setCreatedby(user.getAd_user_id());
							excessbean.setUpdatedby(user.getAd_user_id());
							excessbean.setIsactive(false);
							excessbean.setTrx_date(trx_date);
							excessbean.setAd_member_id(mid);
							excessbean.setAd_voucher_id(bn.getAd_voucher_id());
							excessbean.setExcess_amt(excess);
							excessbean.setWithdrawal(0);
							exccess_bal+=excess;
							excessbean.setBalance(exccess_bal);
							excessbean.setDetail("Payroll");
							excessbean.setStatus("Pending");
							new ExcessTrxDao().addExcess(excessbean);
							

					}//end of update excess amt in trx table

					
		            if(payrollBean !=null){
		            		payrollBean.setAd_member_id(mid);
		            		payrollBean.setAd_payroll_temp_id(0);
		            		payrollBean.setCreatedby(user.getAd_user_id());
		            		payrollBean.setUpdatedby(user.getAd_user_id());
		            		payrollBean.setIsactive(true);
		            		payrollBean.setPayroll_date(trx_date);
		       		   		payrollBean.setTotal_amt(thrift+loan+excess+dcf+festival+guesthouse);
		            		new PayrollDao().addPayroll(payrollBean,bn.getAd_voucher_id());
		            		
		            		
		            	}
				}//end of if condition

				
			}//end of for loop

			if(excess_amt_other>0){
				
				TempTranxBean tbean = new TempTranxBean();	
									
				tbean.setCreatedby(user.getAd_user_id());
				tbean.setUpdatedby(user.getAd_user_id());
				VoucherBean voucher=new VoucherBean();
				voucher.setAd_voucher_id(bn.getAd_voucher_id());
				tbean.setVoucher(voucher);
				
				LedgerAccountBean ledger=new LedgerAccountBean();
				ledger.setAd_account_id(141);
				tbean.setLedger(ledger);
				tbean.setTrx_date(trx_date);			
				MemberRegistrationBean member= new MemberRegistrationBean();
				member.setAd_member_id(0);
				tbean.setMember(member);
				tbean.setCramt(excess_amt_other);
				tbean.setNarration(excess_narration);
				new TempTranxDao().addTempTranx(tbean);
				
				
				ExcessTrxBean excessbean=new ExcessTrxBean();
				excessbean.setCreatedby(user.getAd_user_id());
				excessbean.setUpdatedby(user.getAd_user_id());
				excessbean.setIsactive(false);
				excessbean.setTrx_date(trx_date);
				excessbean.setAd_member_id(0);
				excessbean.setAd_voucher_id(bn.getAd_voucher_id());
				excessbean.setExcess_amt(excess_amt_other);
				excessbean.setWithdrawal(0);
				excessbean.setBalance(excess_amt_other);
				excessbean.setDetail("Payroll");
				excessbean.setStatus("Pending");
				new ExcessTrxDao().addExcess(excessbean);
				
				
			}
			
           /* ArrayList<PayrollTempBean> tempPayroll=new PayrollTempDao().getAllPayrollTemp();
            if(tempPayroll.isEmpty()!=true){
            	for(PayrollTempBean tempBean:tempPayroll){
            		
            		new PayrollDao().addPayroll(tempBean,bn.getAd_voucher_id());
            		
            		
            	}
            }*/
            
			AppMessage[0] = "alert-success";
			AppMessage[1] = "Payroll Upload successfully !!";

			response.sendRedirect("ad_temp_voucher_view.jsp?ad_voucher_id="+bn.getAd_voucher_id());




		}else if(action.equals("insertPayroll")){
			String branch_id=request.getParameter("ad_branch_id");
			String bt_no=request.getParameter("batch_no");
			String dt=request.getParameter("payroll_date");
			String id=request.getParameter("ad_member_id");
			int batch_no=0;
			int ad_member_id=0;
			int ad_branch_id=0;
			Date date=null;
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			try{
				date=sdf.parse(dt);
				batch_no=Integer.parseInt(bt_no);
				ad_branch_id=Integer.parseInt(branch_id);
				ad_member_id=Integer.parseInt(id);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(ad_branch_id>0){
				ArrayList<MemberRegistrationBean> malist=new MemberRegistrationDao().getMemberByBranch(ad_branch_id);
				if(malist.isEmpty()!=true){
					for(MemberRegistrationBean bean:malist){
						PayrollAdviseBean advice=new PayrollAdviceDao().getAllPayrollAdviceByMemberId(bean.getAd_member_id());
						PayrollTempBean temp=new PayrollTempBean();
						temp.setAd_member_id(bean.getAd_member_id());
						temp.setBatch_no(batch_no);
						temp.setCreatedby(user.getAd_user_id());
						temp.setUpdatedby(user.getAd_user_id());
						temp.setDcf_amt(advice.getDcf());
						temp.setFestival_loan_amt(advice.getFestivalloan_emi());
						temp.setMain_loan_amt(advice.getMainloan_emi());
						temp.setThrift_amt(advice.getFgds_fund());
						temp.setIsactive(true);
						temp.setTotal_amt(advice.getDcf()+advice.getFgds_fund()+advice.getFestivalloan_emi()+advice.getMainloan_emi());
						temp.setPayroll_date(trx_date);
						new PayrollTempDao().addPayrollTemp(temp);
						
					}
				}
				
				
			}else{
			PayrollAdviseBean advice=new PayrollAdviceDao().getAllPayrollAdviceByMemberId(ad_member_id);
			PayrollTempBean temp=new PayrollTempBean();
			temp.setAd_member_id(ad_member_id);
			temp.setBatch_no(batch_no);
			temp.setCreatedby(user.getAd_user_id());
			temp.setUpdatedby(user.getAd_user_id());
			temp.setDcf_amt(advice.getDcf());
			temp.setFestival_loan_amt(advice.getFestivalloan_emi());
			temp.setMain_loan_amt(advice.getMainloan_emi());
			temp.setThrift_amt(advice.getFgds_fund());
			temp.setIsactive(true);
			temp.setTotal_amt(advice.getDcf()+advice.getFgds_fund()+advice.getFestivalloan_emi()+advice.getMainloan_emi());
			temp.setPayroll_date(trx_date);
			new PayrollTempDao().addPayrollTemp(temp);
			}
			AppMessage[0] = "alert-success";
			AppMessage[1] = "Add Payroll successfully !!";

			response.sendRedirect("payroll_posting.jsp");
		}else if(action.equals("deletePayroll")){
			System.out.println("inside payroll delete");
			int id=Integer.parseInt(request.getParameter("ad_payroll_temp_id"));
			int i=new PayrollTempDao().deletePayrollTemp(id);
			
			if(i>0){
				AppMessage[0] = "alert-success";
				AppMessage[1] = "Delete Payroll successfully !!";
				response.sendRedirect("payroll_posting.jsp");
			}else{
				AppMessage[0] = "alert-danger";
				AppMessage[1] = "Please Try Again !!";
				response.sendRedirect("payroll_posting.jsp");
			}
		}else if(action.equals("excellUpload")){
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	        // process only if its multipart content
	        if (isMultipart) {
	                // Create a factory for disk-based file items
	                FileItemFactory factory = new DiskFileItemFactory();

	                // Create a new file upload handler
	                ServletFileUpload upload = new ServletFileUpload(factory);
	                try {
	                    // Parse the request
	                    List<FileItem> multiparts = upload.parseRequest(request);

	                   for (FileItem item : multiparts) {
	                   if (!item.isFormField()) {
	                   String name = new File(item.getName()).getName();
	                   item.write(new File(getServletContext().getRealPath("")+ File.separator+UPLOAD_DIRECTORY + File.separator + name));
	                  //System.out.println(new File(getServletContext().getRealPath("")+ File.separator+UPLOAD_DIRECTORY + File.separator + name));
	                  
	                FileInputStream fis = new FileInputStream(new File(getServletContext().getRealPath("")+ File.separator+UPLOAD_DIRECTORY + File.separator + name));
	           		HSSFWorkbook workbook = new HSSFWorkbook(fis);
	           		HSSFSheet spreadsheet = workbook.getSheetAt(0);
	           		 Iterator rowIterator =(Iterator) spreadsheet.iterator();
	           		
	           		while (rowIterator.hasNext())
	           		{
	           			row=(HSSFRow) rowIterator.next();
	           			 Iterator cellIterator = row.cellIterator();
	           			int i=0;
	           			PayrollTempBean temp=null;
	           			DataFormatter formatter = new DataFormatter();
	           			while(cellIterator.hasNext())
	           		{
	           			++i;
	           			HSSFCell cell=(HSSFCell) cellIterator.next();
	           			try{			
	           			switch (cell.getCellType())
	           			{
	           			case HSSFCell.CELL_TYPE_NUMERIC:
	           					
	           			double thrift=0.0,dcf=0.0,mloan=0.0,floan=0.0,excess=0.0,total=0.0;	
	           			int ad_society_no=0;
	           			if(cell.getCellNum()==0){
	           				temp=new PayrollTempBean();
	           				//System.out.println(new BankBranchDao().getBankBranchBycode(Integer.parseInt(formatter.formatCellValue(cell))).getBranch());
	           				int batch_no=new PayrollDao().getPayrollMaxBatchNo();
	           			    batch_no++;
	           				temp.setBatch_no(batch_no);
	           				temp.setCreatedby(user.getAd_user_id());
	           				temp.setUpdatedby(user.getAd_user_id());
	           				temp.setIsactive(true);
	           				temp.setPayroll_date(trx_date);
	           			}else if(cell.getCellNum()==1){
	           				MemberRegistrationBean member=new MemberRegistrationDao().getMemberNameBySocietyNo(Integer.parseInt(formatter.formatCellValue(cell)));
	           				temp.setAd_society_no(Integer.parseInt(formatter.formatCellValue(cell)));
	           				temp.setAd_member_id(member.getAd_member_id());
	           				temp.setAd_branch_id(member.getAd_branch_id());
	           			}else if(cell.getCellNum()==2){
	           				temp.setThrift_amt(Double.parseDouble(formatter.formatCellValue(cell)));
	           				thrift=Double.parseDouble(formatter.formatCellValue(cell));
	           			}else if(cell.getCellNum()==3){
	           				temp.setMain_loan_amt(Double.parseDouble(formatter.formatCellValue(cell)));
	           				mloan=Double.parseDouble(formatter.formatCellValue(cell));
	           			}else if(cell.getCellNum()==4){
	           				temp.setFestival_loan_amt(Double.parseDouble(formatter.formatCellValue(cell)));
	           				floan=Double.parseDouble(formatter.formatCellValue(cell));
	           			}else if(cell.getCellNum()==5){
	           				temp.setDcf_amt(Double.parseDouble(formatter.formatCellValue(cell)));
	           				dcf=Double.parseDouble(formatter.formatCellValue(cell));
	           			}else if(cell.getCellNum()==6){
	           				temp.setExcess_amt(Double.parseDouble(formatter.formatCellValue(cell)));
	           				excess=Double.parseDouble(formatter.formatCellValue(cell));
	           			}else if(cell.getCellNum()==7){
	           				//total=thrift+mloan+floan+dcf+excess;
	           				temp.setTotal_amt(Double.parseDouble(formatter.formatCellValue(cell)));
	           			}else if(cell.getCellNum()==8){
	           				temp.setNarration(formatter.formatCellValue(cell));
	           			}
	           				       
	           				break;                
	                      
	           			}
	           			}catch(Exception e){
	           				System.out.println("Row No:"+cell.getRowIndex()+"Cell No:"+cell.getCellNum());                
	           				e.printStackTrace();
	           			}

	           			
	           		}
	           		 
	           			if(temp!=null){
	           			if(temp.getAd_member_id()!=0){	
	           			new PayrollTempDao().addPayrollTemp(temp);
	           			}else{
	           				new PayrollTempDao().addExcessPayrollTemp(temp);
	           			}
	           			}
	           	 }
	           	 fis.close();
	                   
	                   
	                   
	                   
	                   }
	                }
	                        
	                // File uploaded successfully
	                  
							AppMessage[0] = "alert-success";
							AppMessage[1] = " Excel File Uploaded Successfully  !";
						
	                } 
	                catch (Exception e) 
	                {
	                	AppMessage[0] = "alert-info";
						AppMessage[1] = "File Upload Failed due to " + e;
	                 
	                }
	        } else 
	        {
	        request.setAttribute("message", "This Servlet only handles file upload request");
	        }
	        session.setAttribute("AppMessage", AppMessage);
			response.sendRedirect("bulkexcelupload.jsp");
	}

	}

	public double getInterst(int ad_member_id,int loan_trx_id){
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		int i=0;
		double intrest=0.0,balance=0.0,prv_balance=0.0;
		Date d =(Date)session.getAttribute("openday");
		LoanTrxDetailBean lntbn = new LoanTrxDetailDao().getLastInterestPostingDate(ad_member_id, loan_trx_id);
		if(lntbn==null){
			lntbn=new LoanTrxDetailDao().getLoanOpeningDate(ad_member_id, loan_trx_id);
		}

		LoanTrxDetailBean ltbean=new LoanTrxDetailBean();
		LoanTrxBean ltb= new LoanTrxDao().getLoanTrxByPrmryId(loan_trx_id);
		Date first=null,second=null;
		if(lntbn.getTrx_date()!=null){
			first=lntbn.getTrx_date();
			
		}else{
			
			first=ltb.getissue_date();
			
		}
		double tot_balance=0.0;
		int tot_days=0;

		double one_day_int=(ltb.getIntrest_rate());
		ArrayList<LoanTrxDetailBean> ltbn = new LoanTrxDetailDao().getLoanTrxDetailByLoantrxId(loan_trx_id,lntbn.getTrx_date());
		if(ltbn.isEmpty()!=true){
			for(LoanTrxDetailBean bean:ltbn){
				second=bean.getTrx_date();
				//prv_balance=bean.getBalance_amt();
				long diff = second.getTime() - first.getTime();
				long diffDays = diff / (24 * 60 * 60 * 1000);
				tot_days+=diffDays;
				tot_balance=prv_balance*diffDays;
				balance=bean.getBalance_amt();
				intrest +=(tot_balance*one_day_int)/36500.0;
				first=second;
				prv_balance=balance;
			}
		}



		if(d!=null ){

			second=d;
			if (second.compareTo(first) >= 0) {
				long diff =  second.getTime()-first.getTime() ;
				long diffDays = diff / (24 * 60 * 60 * 1000);
				//diffDays++;
				tot_days+=diffDays;
				tot_balance=balance*diffDays;
				intrest += (tot_balance*one_day_int)/36500.0;
		        
		    } 
			
		}
		intrest=new BigDecimal(intrest).setScale(0, RoundingMode.HALF_UP).doubleValue();
		return intrest;
	}
}

