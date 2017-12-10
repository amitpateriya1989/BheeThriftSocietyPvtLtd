package Controller;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import Model.Bean.LedgerAccountBean;
import Model.Bean.LoanDetailViewBean;
import Model.Bean.LoanGuaranterBean;
import Model.Bean.LoanRoiBean;
import Model.Bean.LoanTrxBean;
import Model.Bean.LoanTrxDetailBean;
import Model.Bean.LoanwitnessBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.MemberShareBean;
import Model.Bean.MemberWitnessChkBean;
import Model.Bean.TempTranxBean;
import Model.Bean.ThriftRoiBean;
import Model.Bean.ThriftTrxBean;
import Model.Bean.TransactionBean;

import Model.Bean.UserBean;
import Model.Bean.VoucherBean;
import Model.Dao.ExcessTrxDao;
import Model.Dao.LoanGuaranterDao;
import Model.Dao.LoanRoiDao;
import Model.Dao.LoanTrxDao;
import Model.Dao.LoanTrxDetailDao;
import Model.Dao.LoanWitnessDao;
import Model.Dao.MemberRegistrationDao;
import Model.Dao.MemberShareDao;
import Model.Dao.MemberWitnessChkDao;
import Model.Dao.TempTranxDao;
import Model.Dao.ThriftTrxDao;

import Model.Dao.TransactionDao;
import Model.Dao.VoucherDao;
import MasterValidation.Validation;

import java.io.PrintWriter;


/**
 * Servlet implementation class AdLoanTrx
 */
@WebServlet("/AdLoanTrx")
public class AdLoanTrx extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoanTrxDao dao=null;
	private UserBean user=null;
	private Validation valid = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdLoanTrx() {
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
		HashMap<String,String> lstMap = new HashMap<String,String>();
		String[] AppMessage = new String[2];
		AppMessage[0] = "alert-info";
		AppMessage[1] = "welcome";
		HttpSession session=request.getSession(false);
		session.setAttribute("AppMessage", AppMessage);
		
		int ad_account_id=0;
		
		
		
		try{

			dao=new LoanTrxDao();
			valid = new Validation();
			user=(UserBean)session.getAttribute("userbean");
			
			String action=request.getParameter("action");
			String ad_member_id=request.getParameter("il_ad_member_id");
			String ad_type_of_loan_id=request.getParameter("ad_type_of_loan_id");
			String ad_loan_category_id=request.getParameter("ad_loan_category_id");
			String req_loan_amt=request.getParameter("req_loan_amt");
			String sanction_loan_amt=request.getParameter("loan_amt");
			String gross_sal=request.getParameter("gross_sel");
			String int_rate=request.getParameter("int_rate");
			String period_month=request.getParameter("period_month");
			String loan_date=request.getParameter("loan_date");
			String end_date=request.getParameter("end_date");
			String emi=request.getParameter("emi");
			//String witness_ad_member_id=request.getParameter("witness_ad_member_id");
			String guar_ad_member_id=request.getParameter("guar_ad_member_id");
			String chk_qnt=request.getParameter("chk_qnt");
			String chk_no_form=request.getParameter("chk_no_form");
			String chk_no_to=request.getParameter("chk_no_to");
			String chk_date=request.getParameter("chk_date");
			String bank_name=request.getParameter("bank_name");
			String bank_code=request.getParameter("bank_code");
			String loan_purpose=request.getParameter("loan_purpose");
			String status=request.getParameter("status");
			String loan_amt_in_words=request.getParameter("loan_amt_in_words");
			String share_amt_in_words=request.getParameter("share_amt_in_words");
			
			
			if(action.equals("loanvalidation")){
				int mid = 0;
				mid=Integer.parseInt(request.getParameter("ad_member_id"));
				double loan_amt=Double.parseDouble(request.getParameter("loan_amt"));
				int	ad_type_of_loan_id1=Integer.parseInt(request.getParameter("ad_type_of_loan_id"));
				int ad_loan_category_id1=Integer.parseInt(request.getParameter("ad_loan_category_id"));
				LoanRoiBean roi=new LoanRoiDao().getLoanRoi(ad_loan_category_id1, ad_type_of_loan_id1);
				//System.out.println(mid +""+ ad_loan_category_id1 +""+ ad_type_of_loan_id1);
				int loan_trx_id= new LoanTrxDao().getOpenLoan(mid ,ad_loan_category_id1,  ad_type_of_loan_id1);
				LoanTrxDetailBean lbean=new LoanTrxDetailDao().getOpenLoanBal(mid, loan_trx_id);
				
				loan_amt+=lbean.getBalance_amt();
				if(lbean.getBalance_amt()==0){
				if(loan_amt<=roi.getMax_limit()){
					HashMap<String,String> smap=new HashMap<String,String>(); 
					String msg="Current Loan Balance is "+lbean.getBalance_amt();
					smap.put("prv_balance",Double.toString(lbean.getBalance_amt()));
					smap.put("loan_limit",Double.toString(roi.getMax_limit()));
					smap.put("status", "Pass");
					smap.put("msg", msg);
					Gson gson = new Gson(); 
					String json = gson.toJson(smap); 
					response.setContentType("application/json");
					response.getWriter().print(json);
				
				}else{
					HashMap<String,String> smap=new HashMap<String,String>(); 
					String msg="Current Loan Balance is "+lbean.getBalance_amt();
					smap.put("prv_balance",Double.toString(lbean.getBalance_amt()));
					smap.put("loan_limit",Double.toString(roi.getMax_limit()));
					smap.put("status", "Fail");
					smap.put("msg", msg);
					Gson gson = new Gson(); 
					String json = gson.toJson(smap); 
					response.setContentType("application/json");
					response.getWriter().print(json);
				}
				}else{
					HashMap<String,String> smap=new HashMap<String,String>(); 
					String msg="Loan Already Open";
					smap.put("prv_balance",Double.toString(lbean.getBalance_amt()));
					smap.put("loan_limit",Double.toString(roi.getMax_limit()));
					smap.put("status", "Fail");
					smap.put("msg", msg);
					Gson gson = new Gson(); 
					String json = gson.toJson(smap); 
					response.setContentType("application/json");
					response.getWriter().print(json);
				}
			}//end loanvalidation function

			if(action.equals("sharevalidation")){
				
				int mid=Integer.parseInt(request.getParameter("ad_member_id"));
				double loan_amt=Double.parseDouble(request.getParameter("loan_amt")); 				
				int ad_type_of_loan_id1= Integer.parseInt(request.getParameter("ad_type_of_loan_id"));
				int ad_loan_category_id1= Integer.parseInt(request.getParameter("ad_loan_category_id"));				
				int maxid=new LoanRoiDao().getLoanRoiMaxId(ad_loan_category_id1,ad_type_of_loan_id1);				
				LoanRoiBean bean3  =new LoanRoiDao().getLoanRoiById(maxid);				
				ArrayList<LoanDetailViewBean> loanlist=new LoanTrxDao().getAllLoanDetailByMemberId(mid, "Open");
				double loan_balance=0.0;
				if(loanlist.isEmpty()!=true){
					for(LoanDetailViewBean bean:loanlist){
						 LoanTrxDetailBean lbean=new LoanTrxDetailDao().getOpenLoanBal(mid, bean.getLoan_trx_id());
						  loan_balance+=lbean.getBalance_amt();
					}
				}		
               
				MemberShareBean msb = new MemberShareDao().getAllShareAmtByMemberId(mid);
				
				
				loan_amt+=loan_balance;
				
					System.out.println((loan_amt*bean3.getShare_needed())/100.0);
					double share_needed=((loan_amt*bean3.getShare_needed())/100.0);
					if(msb.getShare_amt()<=share_needed){
					HashMap<String,String> smap=new HashMap<String,String>(); 
					String msg="This Member Dont Have Enough Share ....!! Total available Share  "+msb.getShare_amt() +", Needed Share "+(share_needed-msb.getShare_amt());
					smap.put("msg",msg);
					smap.put("shareNeed",Double.toString(share_needed-msb.getShare_amt()));
					smap.put("shareAmt", Double.toString(msb.getShare_amt()));
					Gson gson = new Gson(); 
					String json = gson.toJson(smap); 
					response.setContentType("application/json");
					response.getWriter().print(json);
				}else{
					HashMap<String,String> smap=new HashMap<String,String>(); 
					String msg="This Member Have Enough Share ....!! Total available Share  "+msb.getShare_amt() +", Needed Share "+0;
					smap.put("msg",msg);
					smap.put("shareNeed",Double.toString(0));
					smap.put("shareAmt", Double.toString(msb.getShare_amt()));
					Gson gson = new Gson(); 
					String json = gson.toJson(smap); 
					response.setContentType("application/json");
					response.getWriter().print(json);
				}	

			
			}
			if(action.equals("verifyvalidsalary")){

				//PrintWriter out = response.getWriter();


				if(valid.validNotEmpty(request.getParameter("td"))==false && valid.validNumeric(request.getParameter("td"))==false){
					lstMap.put("message","error");
				}else if(valid.validNotEmpty(request.getParameter("emi"))==false && valid.validNumeric(request.getParameter("emi"))==false){
					lstMap.put("message","error");
				}else if(valid.validNotEmpty(request.getParameter("gs"))==false && valid.validNumeric(request.getParameter("gs"))==false){
					lstMap.put("message","error");
				}else if(valid.validNotEmpty(request.getParameter("loanCategory"))==false && valid.validDigits(request.getParameter("loanCategory"))==false){
					lstMap.put("message","error");
				}else if(valid.validNotEmpty(request.getParameter("loanType"))==false && valid.validDigits(request.getParameter("loanType"))==false){
					lstMap.put("message","error");
				}else{
					try{
						double tdval = Double.parseDouble(request.getParameter("td").trim());
						double emival = Double.parseDouble(request.getParameter("emi").trim());
						double gsval = Double.parseDouble(request.getParameter("gs").trim());
						int loanCategory = Integer.parseInt(request.getParameter("loanCategory").trim());
						int loanType = Integer.parseInt(request.getParameter("loanType").trim());
						int minSalaPer=0;

						minSalaPer = dao.getMinSalaryPer(loanType, loanCategory);

						double totalPerSal = 0;
						totalPerSal = ((tdval+emival)/gsval)*100;
						if(totalPerSal <= minSalaPer){
							lstMap.put("message","true");
							lstMap.put("total",Double.toString(totalPerSal));
							lstMap.put("salaryPer",Double.toString(minSalaPer));
						}else{
							lstMap.put("message","false");
							lstMap.put("total",Double.toString(totalPerSal));
							lstMap.put("salaryPer",Double.toString(minSalaPer));
						}
						//out.println(totalPerSal);
					}catch(Exception ex){
						lstMap.put("message","false");
						StringWriter errors = new StringWriter();
						ex.printStackTrace(new PrintWriter(errors));
						lstMap.put("errormessage",errors.toString());
					}

					Gson gson = new Gson(); 
					String json = gson.toJson(lstMap); 
					response.setContentType("application/json");
					response.getWriter().print(json);

				}//end validation and process
				//out.println("Hello");
			}//end verifyValidSalary function

			if(action.equals("ad_date")){

				String loan_date1 = request.getParameter("loan_date");
				String period_month1=request.getParameter("period_month");
				int per_month=0;

				try{
					per_month=Integer.parseInt(period_month1);

				}catch(NumberFormatException n){
					n.printStackTrace();
				}

				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date ldate=df.parse(loan_date1);
				Calendar c = Calendar.getInstance();
				c.setTime(ldate);
				c.add(Calendar.MONTH, per_month);
				//	System.out.print(df.format(c.getTime()));
				response.getWriter().print(df.format(c.getTime()).trim());			

				//response.getWriter().print("success");


			}//end ad_date function

			////////////////////
			if(action.equals("deposit")){
                int no=0;
                
				String discription="Loan_deposit";
				status="Pending";
				
				String thrift_acc_id=request.getParameter("ad_account_id");
				String share_acc_id=request.getParameter("ad_account_id2");
				String excess_acc_id=request.getParameter("ad_account_id3");
				String narration=request.getParameter("narration");
				String intrest=request.getParameter("intrest");
				String amt_in_words=request.getParameter("amt_in_words");
				String suspance_id=request.getParameter("ad_account_id4");
				double vamt=0.00;
				int thrift_account_id=0;
				int share_account_id=0;
				int excess_account_id=0;
				int system_suspence_id=0;
				double thrift_bal=0.0,share_bal=0.0,excess_bal=0.0, loan_int=0.0,sundary_bal=0.0;
				try{					
					if(request.getParameter("total_pay_amt")!=null){
					vamt=Double.parseDouble(request.getParameter("total_pay_amt").trim());
					}
					if(thrift_acc_id!=null){
					thrift_account_id=Integer.parseInt(thrift_acc_id);
					}
					if(share_acc_id!=null){
					share_account_id=Integer.parseInt(share_acc_id);
					}
					if(excess_acc_id!=null){
					excess_account_id=Integer.parseInt(excess_acc_id);
					}
					if(suspance_id!=null){
						system_suspence_id=Integer.parseInt(suspance_id);
						}
					
					if(intrest!=null){
						loan_int=Double.parseDouble(intrest);
					}
					
				}catch(NumberFormatException n){
					n.printStackTrace();
				}

				if(vamt>0){
					String vfrom="";
					String vtype=request.getParameter("trx_by");
					String voucher_type="Received";

					if(thrift_account_id!=0 || share_account_id!=0 || excess_account_id!=0 || system_suspence_id!=0){
						vfrom="Loan Adjustment";
						
						VoucherBean bean=new VoucherBean();
						
						bean.setCreated(new java.util.Date());
						bean.setUpdated(new java.util.Date());
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(true);
						bean.setDescription(discription);
						bean.setStatus(status);
						bean.setVamt(vamt+loan_int);
						bean.setVfrom(vfrom);
						bean.setVtype(vtype);
						bean.setVoucher_type(voucher_type);				
						bean.setInstrument_from("");
						bean.setInstrument_no("");
						bean.setAmt_in_words(amt_in_words);
						Date d2=(Date)session.getAttribute("openday");;
						bean.setInstrument_date(d2);					
						Date trx_date=(Date)request.getSession().getAttribute("openday");	
						bean.setTrx_date(trx_date);					
						VoucherDao vdao=new VoucherDao();				
						no=vdao.getMaxtrxNo();					
						bean.setTrx_no(no);	
						VoucherBean  bn = new VoucherDao().addVoucher(bean);

						/// dr in bank ac

						
						ad_member_id=request.getParameter("admid");
						String loan_trx_id = request.getParameter("loantrxid");

						//cr in mem loan ac
						String lbal=request.getParameter("loan_bal");
						double loan_balance=Double.parseDouble(lbal);
						if(lbal!="" && loan_balance>0){
							
							if(thrift_account_id>0){
							TempTranxBean tbean = new TempTranxBean();
							tbean.setCreatedby(user.getAd_user_id());
							tbean.setUpdatedby(user.getAd_user_id());
							VoucherBean vbean=new VoucherBean();
							vbean.setAd_voucher_id(bn.getAd_voucher_id());
							tbean.setVoucher(vbean);	
							LedgerAccountBean ledger=new LedgerAccountBean();
							ledger.setAd_account_id(thrift_account_id);
							tbean.setLedger(ledger);
							tbean.setTrx_date(trx_date);
							MemberRegistrationBean member=new MemberRegistrationBean();
							member.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
							tbean.setMember(member);
								try{
									thrift_bal=Double.parseDouble(request.getParameter("thrift_bal"));
									tbean.setDramt(thrift_bal);
								}catch(NumberFormatException n){
									n.printStackTrace();
								}
							tbean.setNarration(narration);
							new TempTranxDao().addTempTranx(tbean);
							
							
							ThriftTrxBean thriftBean=new ThriftTrxBean();
							thriftBean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
							thriftBean.setAd_voucher_id(bn.getAd_voucher_id());
							thriftBean.setCreatedby(user.getAd_user_id());
							thriftBean.setUpdatedby(user.getAd_user_id());
							thriftBean.setStatus("Pending");
							thriftBean.setIsactive(false);
							thriftBean.setThrift_amt(0);
							thriftBean.setTrx_date(trx_date);
							thriftBean.setThrift_int(0);
							thriftBean.setWithdrawal(thrift_bal);
							double bal=new ThriftTrxDao().getMaxThriftBalanceByMemberId(Integer.parseInt(ad_member_id.trim()));
							bal-=thrift_bal;
							thriftBean.setBalance(bal);
							new ThriftTrxDao().addThriftTrx(thriftBean);
							
							
							}//end of thrift account
							
							if(share_account_id>0){
								TempTranxBean tbean = new TempTranxBean();
								tbean.setCreatedby(user.getAd_user_id());
								tbean.setUpdatedby(user.getAd_user_id());
								VoucherBean vbean=new VoucherBean();
								vbean.setAd_voucher_id(bn.getAd_voucher_id());
								tbean.setVoucher(vbean);	
								LedgerAccountBean ledger=new LedgerAccountBean();
								ledger.setAd_account_id(share_account_id);
								tbean.setLedger(ledger);
								tbean.setTrx_date(trx_date);
								MemberRegistrationBean member=new MemberRegistrationBean();
								member.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
								tbean.setMember(member);
									try{
										share_bal=Double.parseDouble(request.getParameter("share_bal"));
										tbean.setDramt(share_bal);
									}catch(NumberFormatException n){
										n.printStackTrace();
									}
								tbean.setNarration(narration);
								new TempTranxDao().addTempTranx(tbean);
								
								//close share
								/*MemberShareBean sharebean=new MemberShareBean();
								sharebean.setUpdated(trx_date);
								sharebean.setUpdatedby(user.getAd_user_id());
								sharebean.setIsactive(false);
								sharebean.setPay_type("Loan Adjustment");
								sharebean.setPayment_date(trx_date);
								sharebean.setStatus("Paid");
								sharebean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
								new MemberShareDao().updateMemberPaidShareStatus(sharebean);
								
								*/
								
								
								
							}
							
							if(excess_account_id>0){
								TempTranxBean tbean = new TempTranxBean();
								tbean.setCreatedby(user.getAd_user_id());
								tbean.setUpdatedby(user.getAd_user_id());
								VoucherBean vbean=new VoucherBean();
								vbean.setAd_voucher_id(bn.getAd_voucher_id());
								tbean.setVoucher(vbean);
								LedgerAccountBean ledger=new LedgerAccountBean();
								ledger.setAd_account_id(excess_account_id);
								tbean.setLedger(ledger);
								tbean.setTrx_date(trx_date);
								MemberRegistrationBean member=new MemberRegistrationBean();
								member.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
								tbean.setMember(member);
								try{
									excess_bal=Double.parseDouble(request.getParameter("excess_bal"));
									tbean.setDramt(excess_bal);
								}catch(NumberFormatException n){
									n.printStackTrace();
								}
								tbean.setNarration(narration);
								new TempTranxDao().addTempTranx(tbean);
								
								double exccess_balance=new ExcessTrxDao().getMaxExcessBalanceByMemberId(Integer.parseInt(ad_member_id.trim()));
								ExcessTrxBean excessbean=new ExcessTrxBean();
								excessbean.setCreatedby(user.getAd_user_id());
								excessbean.setUpdatedby(user.getAd_user_id());
								excessbean.setIsactive(false);
								excessbean.setTrx_date(trx_date);
								excessbean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
								excessbean.setAd_voucher_id(bn.getAd_voucher_id());
								excessbean.setExcess_amt(0);
								excessbean.setWithdrawal(excess_bal);
								exccess_balance-=excess_bal;
								excessbean.setBalance(exccess_balance);
								excessbean.setDetail("Loan Closure");
								excessbean.setStatus("Pending");
								new ExcessTrxDao().addExcess(excessbean);
								
							}
							
							if(system_suspence_id>0){
								TempTranxBean tbean = new TempTranxBean();
								tbean.setCreatedby(user.getAd_user_id());
								tbean.setUpdatedby(user.getAd_user_id());
								VoucherBean vbean=new VoucherBean();
								vbean.setAd_voucher_id(bn.getAd_voucher_id());
								tbean.setVoucher(vbean);
								LedgerAccountBean ledger=new LedgerAccountBean();
								ledger.setAd_account_id(system_suspence_id);
								tbean.setLedger(ledger);
								tbean.setTrx_date(trx_date);
								MemberRegistrationBean member=new MemberRegistrationBean();
								member.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
								tbean.setMember(member);
								try{
									sundary_bal=Double.parseDouble(request.getParameter("sundary_bal"));
									tbean.setDramt(sundary_bal);
								}catch(NumberFormatException n){
									n.printStackTrace();
								}
								tbean.setNarration(narration);
								new TempTranxDao().addTempTranx(tbean);
								
								
							}
							
							//loan to member a/c
							TempTranxBean tbean2 = new TempTranxBean();
							tbean2.setCreatedby(user.getAd_user_id());
							tbean2.setUpdatedby(user.getAd_user_id());
							VoucherBean vbean=new VoucherBean();
							vbean.setAd_voucher_id(bn.getAd_voucher_id());
							tbean2.setVoucher(vbean);
							LedgerAccountBean ledger=new LedgerAccountBean();
							ledger.setAd_account_id(134);
							tbean2.setLedger(ledger);
							tbean2.setTrx_date(trx_date);
							MemberRegistrationBean member=new MemberRegistrationBean();
							member.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
							tbean2.setMember(member);
							tbean2.setCramt(vamt);
							tbean2.setNarration(narration);
							new TempTranxDao().addTempTranx(tbean2);
						}

						//loan interest account
						if(intrest!="" && loan_int>0){
							//interest dr entry
							TempTranxBean tbean4 = new TempTranxBean();				
							tbean4.setCreatedby(user.getAd_user_id());
							tbean4.setUpdatedby(user.getAd_user_id());
							VoucherBean vbean=new VoucherBean();
							vbean.setAd_voucher_id(bn.getAd_voucher_id());
							tbean4.setVoucher(vbean);
							LedgerAccountBean ledger=new LedgerAccountBean();
							ledger.setAd_account_id(134);
							tbean4.setLedger(ledger);
							tbean4.setTrx_date(trx_date);
							MemberRegistrationBean member=new MemberRegistrationBean();
							member.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
							tbean4.setMember(member);
							tbean4.setDramt(Double.parseDouble(intrest));
							tbean4.setNarration(narration);
							new TempTranxDao().addTempTranx(tbean4);
							
							
							//interest cr entry
							TempTranxBean tbean3 = new TempTranxBean();				
							tbean3.setCreatedby(user.getAd_user_id());
							tbean3.setUpdatedby(user.getAd_user_id());
							VoucherBean vbean1=new VoucherBean();
							vbean1.setAd_voucher_id(bn.getAd_voucher_id());
							tbean3.setVoucher(vbean1);
							LedgerAccountBean ledger1=new LedgerAccountBean();
							ledger1.setAd_account_id(138);
							tbean3.setLedger(ledger1);
							tbean3.setTrx_date(trx_date);
							MemberRegistrationBean member1=new MemberRegistrationBean();
							member1.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
							tbean3.setMember(member1);
							tbean3.setCramt(Double.parseDouble(intrest));
							tbean3.setNarration(narration);
							new TempTranxDao().addTempTranx(tbean3);
						}


						LoanTrxDetailBean ltbn = new LoanTrxDetailDao().getOpenLoanBal(Integer.parseInt(ad_member_id.trim()) , Integer.parseInt(loan_trx_id.trim()));	
						LoanTrxDetailBean ltbean1=new LoanTrxDetailBean();			
						ltbean1.setCreatedby(user.getAd_user_id());
						ltbean1.setUpdatedby(user.getAd_user_id());
						ltbean1.setInterest_amt(Double.parseDouble(intrest));
						ltbean1.setDeposit_amt(Double.parseDouble(lbal));
						
						ltbean1.setBalance_amt((ltbn.getBalance_amt()+Double.parseDouble(intrest))-(Double.parseDouble(lbal)+Double.parseDouble(intrest)));
						ltbean1.setTrx_date(trx_date);
						ltbean1.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
						ltbean1.setLoan_trx_id(ltbn.getLoan_trx_id());
						System.out.println("voucher id= "+bn.getAd_voucher_id());
						ltbean1.setAd_voucher_id(bn.getAd_voucher_id());
						new LoanTrxDetailDao().addLoanTrxDetail(ltbean1);

						
						if((ltbn.getBalance_amt()+Double.parseDouble(intrest))-(Double.parseDouble(lbal)+Double.parseDouble(intrest))==0){	
						new LoanTrxDao().CloseLoanTrx(Integer.parseInt(loan_trx_id.trim()));
						}
						
						
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Loan Deposit successfully & send for approval !!";
						response.sendRedirect("ad_temp_voucher_view.jsp?ad_voucher_id="+bn.getAd_voucher_id());		
						
						
					}else{

						vfrom="loan deposit";
						VoucherBean bean=new VoucherBean();
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setDescription(discription);
						bean.setStatus(status);
						bean.setVamt(vamt+loan_int);
						bean.setVfrom(vfrom);
						//bean.setVno(vno);
						bean.setVtype(vtype);
						bean.setIsactive(true);
						bean.setCreated(new java.util.Date());
						bean.setUpdated(new java.util.Date());
						bean.setVoucher_type(voucher_type);				
						bean.setInstrument_from(request.getParameter("ad_branch_id"));
						bean.setAmt_in_words(amt_in_words);
						bean.setInstrument_no(request.getParameter("chk_no"));
						String d1=request.getParameter("chk_date");
						SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
						Date d2=null;
						try{

							d2=sdf.parse(d1);
						}catch(ParseException p){
							p.printStackTrace();
						}
						bean.setInstrument_date(d2);					
						Date trx_date=(Date)request.getSession().getAttribute("openday");	
						bean.setTrx_date(trx_date);					
						VoucherDao vdao=new VoucherDao();				
						no=vdao.getMaxtrxNo();					
						bean.setTrx_no(no);	
						VoucherBean  bn = new VoucherDao().addVoucher(bean);
						
						ad_member_id=request.getParameter("admid");
						String loan_trx_id = request.getParameter("loantrxid");

						//cr in mem loan ac
						String lbal=request.getParameter("loan_bal");
						double loan_balance=Double.parseDouble(lbal);
						intrest=request.getParameter("intrest");
						
						if(lbal!="" && loan_balance>0 ){
							
							// dr in bank ac

							TempTranxBean tbean = new TempTranxBean();
							

							tbean.setCreatedby(user.getAd_user_id());
							tbean.setUpdatedby(user.getAd_user_id());
							
							VoucherBean vbean2=new VoucherBean();
							vbean2.setAd_voucher_id(bn.getAd_voucher_id());
							tbean.setVoucher(vbean2);
							LedgerAccountBean ledger2=new LedgerAccountBean();
							ledger2.setAd_account_id(131);
							tbean.setLedger(ledger2);
							tbean.setTrx_date(trx_date);
							MemberRegistrationBean member2=new MemberRegistrationBean();
							member2.setAd_member_id(0);
							tbean.setMember(member2);
							tbean.setNarration(narration);
							tbean.setDramt(vamt);

							new TempTranxDao().addTempTranx(tbean);
							
							
							TempTranxBean tbean2 = new TempTranxBean();

							tbean2.setCreatedby(user.getAd_user_id());
							tbean2.setUpdatedby(user.getAd_user_id());
							VoucherBean vbean1=new VoucherBean();
							vbean1.setAd_voucher_id(bn.getAd_voucher_id());
							tbean2.setVoucher(vbean1);
							LedgerAccountBean ledger1=new LedgerAccountBean();
							ledger1.setAd_account_id(134);
							tbean2.setLedger(ledger1);
							tbean2.setTrx_date(trx_date);
							MemberRegistrationBean member1=new MemberRegistrationBean();
							member1.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
							tbean2.setMember(member1);
							
							tbean2.setNarration(narration);
							tbean2.setCramt(vamt);
							new TempTranxDao().addTempTranx(tbean2);
						

						

						}
							
							//interest dr entry
						loan_int=Double.parseDouble(intrest);
						if(intrest!="" && loan_int>0)	{
						TempTranxBean tbean4 = new TempTranxBean();				
							tbean4.setCreatedby(user.getAd_user_id());
							tbean4.setUpdatedby(user.getAd_user_id());
							VoucherBean vbean2=new VoucherBean();
							vbean2.setAd_voucher_id(bn.getAd_voucher_id());
							tbean4.setVoucher(vbean2);
							LedgerAccountBean ledger2=new LedgerAccountBean();
							ledger2.setAd_account_id(134);
							tbean4.setLedger(ledger2);
							tbean4.setTrx_date(trx_date);
							MemberRegistrationBean member2=new MemberRegistrationBean();
							member2.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
							tbean4.setMember(member2);
							tbean4.setDramt(Double.parseDouble(intrest));
							tbean4.setNarration(narration);
							new TempTranxDao().addTempTranx(tbean4);
							
							
							//interest cr entry
							TempTranxBean tbean3 = new TempTranxBean();				
							tbean3.setCreatedby(user.getAd_user_id());
							tbean3.setUpdatedby(user.getAd_user_id());
							vbean2=new VoucherBean();
							vbean2.setAd_voucher_id(bn.getAd_voucher_id());
							tbean3.setVoucher(vbean2);
							ledger2=new LedgerAccountBean();
							ledger2.setAd_account_id(138);
							tbean3.setLedger(ledger2);
							tbean3.setTrx_date(trx_date);
							member2=new MemberRegistrationBean();
							member2.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
							tbean3.setMember(member2);
							tbean3.setCramt(Double.parseDouble(intrest));
							tbean3.setNarration(narration);
							new TempTranxDao().addTempTranx(tbean3);
						}

						
						
						LoanTrxDetailBean ltbn = new LoanTrxDetailDao().getOpenLoanBal(Integer.parseInt(ad_member_id.trim()) , Integer.parseInt(loan_trx_id.trim()));	
						LoanTrxDetailBean ltbean1=new LoanTrxDetailBean();			
						ltbean1.setCreatedby(user.getAd_user_id());
						ltbean1.setUpdatedby(user.getAd_user_id());
						ltbean1.setInterest_amt(Double.parseDouble(intrest));
						ltbean1.setDeposit_amt(Double.parseDouble(lbal));
						
						ltbean1.setBalance_amt((ltbn.getBalance_amt()+Double.parseDouble(intrest))-(Double.parseDouble(lbal)+Double.parseDouble(intrest)));
						ltbean1.setTrx_date(trx_date);
						ltbean1.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
						ltbean1.setLoan_trx_id(ltbn.getLoan_trx_id());
						System.out.println("voucher id= "+bn.getAd_voucher_id());
						ltbean1.setAd_voucher_id(bn.getAd_voucher_id());
						new LoanTrxDetailDao().addLoanTrxDetail(ltbean1);

						/*ltbn = new LoanTrxDetailDao().getOpenLoanBal(Integer.parseInt(ad_member_id.trim()), Integer.parseInt(loan_trx_id.trim()));	
						LoanTrxDetailBean ltbean=new LoanTrxDetailBean();			
						ltbean.setCreatedby(user.getAd_user_id());
						ltbean.setUpdatedby(user.getAd_user_id());
						ltbean.setDeposit_amt(Double.parseDouble(lbal)+Double.parseDouble(intrest));
						ltbean.setBalance_amt(ltbn.getBalance_amt()-(Double.parseDouble(lbal)+Double.parseDouble(intrest)));
						ltbean.setInterest_amt(0.00);
						ltbean.setTrx_date(trx_date);
						ltbean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
						ltbean.setLoan_trx_id(ltbn.getLoan_trx_id());
						
						new LoanTrxDetailDao().addLoanTrxDetail(ltbean);*/

						if((ltbn.getBalance_amt()+Double.parseDouble(intrest))-(Double.parseDouble(lbal)+Double.parseDouble(intrest))==0){	
						new LoanTrxDao().CloseLoanTrx(Integer.parseInt(loan_trx_id.trim()));
						}

						AppMessage[0] = "alert-success";
						AppMessage[1] = "Loan Deposit successfully & send for approval !!";
						response.sendRedirect("ad_temp_voucher_view.jsp?ad_voucher_id="+bn.getAd_voucher_id());		
						
					
				}
				}
				
			}

			if(action.equals("insert")){

				if(ad_member_id !="" && ad_type_of_loan_id !="" && int_rate !=""   ){

					int gid=0;

					user=(UserBean)session.getAttribute("userbean");					
					
					//loan witness

					String guar_name = request.getParameter("guar_name");
					String guar_ad_society_id = request.getParameter("guar_ad_society_id");
					String pf_no = request.getParameter("pf_no");
					String guar_mobile = request.getParameter("guar_mobile");
					String guar_address = request.getParameter("guar_address");
					String no_of_share=request.getParameter("no_of_share");
					String share_amt=request.getParameter("share_amt");
					String recived_by=request.getParameter("vtype");
					int wid=0;

				
					if(guar_name!="" || guar_ad_society_id!="" || pf_no!="" || guar_mobile!="" || guar_address!="" ){
						LoanwitnessBean bean = new LoanwitnessBean();
						bean.setName(guar_name);
						bean.setMemno(guar_ad_society_id);
						bean.setPfno(pf_no);
						bean.setMobile(guar_mobile);
						bean.setAddress(guar_address);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						wid=new LoanWitnessDao().addLoanWitness(bean);
					}




					//loan_trx
					LoanTrxBean bean=new LoanTrxBean();
					bean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					try{

						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						Date dt1 =  df.parse(loan_date);  
						bean.setissue_date(dt1);
						DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
						Date dt2 =  df1.parse(end_date);  
						bean.setEnd_date(dt2);				
						bean.setLoan_purpose(loan_purpose);

					}catch(ParseException p){
						p.printStackTrace();
					}


					try{
						//gid =Integer.parseInt(guar_ad_member_id);
						bean.setLoan_type(Integer.parseInt(ad_type_of_loan_id));
						bean.setLoan_cataegory(Integer.parseInt(ad_loan_category_id));
						bean.setLoan_amt(Double.parseDouble(sanction_loan_amt));
						bean.setRequest_loan_amt(Double.parseDouble(req_loan_amt));
						bean.setIntrest_rate(Double.parseDouble(int_rate));
						bean.setPeriod_month(Integer.parseInt(period_month));
						bean.setEmi(Double.parseDouble(emi));
						bean.setWitnes(wid);
						bean.setAmt_in_words(loan_amt_in_words);
						//Integer.parseInt(witness_ad_member_id));
						//	bean.setGuaranter(gid);
						//bean.setAd_voucher_id(bn.getAd_voucher_id());

					}catch(NumberFormatException n){
						n.printStackTrace();
					}

					int id =	dao.addLoanTrx(bean);			

					// insert chk detail

					int totalChkInfo =Integer.parseInt(request.getParameter("totalChkInfo"));

					for(int i=1;i<=totalChkInfo;i++){

						MemberWitnessChkBean mwchkbean = new MemberWitnessChkBean();
						mwchkbean.setCreatedby(user.getAd_user_id());
						mwchkbean.setUpdatedby(user.getAd_user_id());
						mwchkbean.setChkno(Integer.parseInt(request.getParameter("guar_cheque_no"+i)));
						mwchkbean.setBankname(request.getParameter("guar_bank_name"+i));
						mwchkbean.setBranchname(request.getParameter("guar_branch_name"+i));
						mwchkbean.setLoan_trx_id(id);
						new MemberWitnessChkDao().addMemberWitnessChkChk(mwchkbean);
					}





					//guranter
					if(gid!=0){

						LoanGuaranterBean lg = new LoanGuaranterBean();
						lg.setCreatedby(user.getAd_user_id());
						lg.setUpdatedby(user.getAd_user_id());
						lg.setAd_member_id(gid);
						lg.setChk_bank_code(bank_code);
						lg.setChk_bank_name(bank_name);
						lg.setChk_qnt(Integer.parseInt(chk_qnt));
						lg.setChk_no_form(Integer.parseInt(chk_no_form));
						lg.setChk_no_to(Integer.parseInt(chk_no_to));
						DateFormat df11 = new SimpleDateFormat("dd/MM/yyyy");
						Date dt12 =  df11.parse(chk_date);  
						lg.setChk_date(dt12);
						lg.setLoan_trx_id(id);
						new LoanGuaranterDao().addLoanGuaranter(lg);

					}
					
					if(ad_member_id !="" && no_of_share !="" && share_amt !=""  ){
						
						
						int cnt=new VoucherDao().getCountsharepending("new_share");
						
						if(cnt!=0){
							//response.getWriter().print("<script>alert('One Share Voucher is  Pending First Approved then try.... ');</script>");
							lstMap.put("message","error");
							lstMap.put("errorMessage","One Share Voucher is  Pending First Approved then try.... .");
							AppMessage[0] = "alert-info";
							AppMessage[1] = "One Share Voucher is  Pending First Approved then try.... .!!";
							
						}else{
							MemberShareBean shareBean=new MemberShareBean();
							shareBean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
							shareBean.setCreatedby(user.getAd_user_id());
							shareBean.setUpdatedby(user.getAd_user_id());
							try{
								
								DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
							    Date dt1 =  df.parse(loan_date);  
							    shareBean.setDate_of_allocation(dt1);
								}catch(ParseException p){
									p.printStackTrace();
								}
								shareBean.setTrx_by(recived_by);
								shareBean.setStatus("Pending");	
								shareBean.setShare_amt(Double.parseDouble(share_amt));
								shareBean.setQnt_of_share(Integer.parseInt(no_of_share));					
								MemberShareBean bean2=new MemberShareDao().getMemberShareMaxId();
								MemberShareBean bean1=new MemberShareDao().getMemberShareBatchshareNo(bean2.getAd_member_share_id());
								
								int share_no_to=0;
								
								if(bean2!=null){					
									shareBean.setShare_no_form(bean1.getShare_no_to()+1);					
								 share_no_to =bean1.getShare_no_to()+Integer.parseInt(no_of_share);					
								}
								
								shareBean.setShare_no_to(share_no_to);					
								shareBean.setBatch_no(bean1.getBatch_no()+1);
								shareBean.setIsactive(true);
								shareBean.setAd_voucher_id(0);
								shareBean.setLoan_trx_id(id);
								shareBean.setAmt_in_words(share_amt_in_words);
								new MemberShareDao().addMemberShareByLoan(shareBean);		
							}
						}

					Date trx_date =(Date)session.getAttribute("openday");

					/// ad_loan_trx
					LoanTrxDetailBean ltdbean = new LoanTrxDetailBean();
					ltdbean.setCreatedby(user.getAd_user_id());
					ltdbean.setUpdatedby(user.getAd_user_id());
					ltdbean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
					ltdbean.setLoan_trx_id(id);
					ltdbean.setBalance_amt(Double.parseDouble(sanction_loan_amt));
					ltdbean.setTrx_date(trx_date);
					//	ltdbean.setAd_vouche_id(bn.getAd_voucher_id());

					new LoanTrxDetailDao().addLoanTrxDetail(ltdbean);

					AppMessage[0] = "alert-info";
					AppMessage[1] = "Loan successfully sent for loan disbursment..!! ";
					session.setAttribute("AppMessage", AppMessage);
					Gson gson = new Gson(); 
					String json = gson.toJson(lstMap); 
					response.setContentType("application/json");
					response.getWriter().print(json);
					response.sendRedirect("issue_new_loan.jsp");
				}else{
					AppMessage[0] = "alert-info";
					AppMessage[1] = "Please Try Again..!! ";
					session.setAttribute("AppMessage", AppMessage);
					Gson gson = new Gson(); 
					String json = gson.toJson(lstMap); 
					response.setContentType("application/json");
					response.getWriter().print(json);
					response.sendRedirect("issue_new_loan.jsp");
				}



			}if(action.equals("loanapproval")){


				user=(UserBean)session.getAttribute("userbean");					
				//ad_voucher
				VoucherBean vbean = new VoucherBean();					
				vbean.setCreatedby(user.getAd_user_id());
				vbean.setUpdatedby(user.getAd_user_id());
				vbean.setDescription("new_loan");
				vbean.setStatus("Pending");
				vbean.setVamt(Double.parseDouble(request.getParameter("drtotal")));
				vbean.setVfrom("New Loan");
				//bean.setVno(vno);
				vbean.setVtype("Cheque");
				vbean.setVoucher_type("New Loan");
				vbean.setIsactive(true);
				vbean.setInstrument_from(request.getParameter("chkbank"));
				vbean.setInstrument_no(request.getParameter("chkno"));	

				String d1=request.getParameter("chkdate");
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				Date d2=null;
				try{
					d2=sdf.parse(d1);

				}catch(ParseException p){
					p.printStackTrace();
				}
				vbean.setInstrument_date(d2);					
				Date d=(Date)request.getSession().getAttribute("openday");
				vbean.setTrx_date(d);
				VoucherDao vdao=new VoucherDao();				
				int no=vdao.getMaxtrxNo();					
				vbean.setTrx_no(no);				
				VoucherBean  bn = new VoucherDao().addVoucher(vbean);		

				/// ad_trx


				MemberRegistrationBean mbean=new MemberRegistrationBean();
				LedgerAccountBean beanl = new LedgerAccountBean();
				//dr in mem loan ac
				TransactionBean tbean2 = new TransactionBean();					
				tbean2.setCreatedby(user.getAd_user_id());
				tbean2.setUpdatedby(user.getAd_user_id());
				
				tbean2.setAd_voucher_id(bn.getAd_voucher_id());
				tbean2.setAd_account_id(134);
				
				tbean2.setTrx_date(d);
				tbean2.setNarration("New Loan : "+new MemberRegistrationDao().getMemberName(Integer.parseInt(request.getParameter("ad_member_id"))).getName());
				tbean2.setAd_member_id(Integer.parseInt(request.getParameter("ad_member_id")));

				
				tbean2.setDramt(Double.parseDouble(request.getParameter("crmember")));

				new TransactionDao().addTransaction(tbean2);


				/// dr in bank ac

				TransactionBean tbean = new TransactionBean();


				tbean.setCreatedby(user.getAd_user_id());
				tbean.setUpdatedby(user.getAd_user_id());
				tbean.setAd_voucher_id(bn.getAd_voucher_id());	

				tbean.setAd_account_id(131);

				
				tbean.setTrx_date(d);
				tbean.setNarration("New Loan : "+new MemberRegistrationDao().getMemberName(Integer.parseInt(request.getParameter("ad_member_id"))).getName());


				tbean.setAd_member_id(0);
				
				tbean.setCramt(Double.parseDouble(request.getParameter("drtotal")));


				new TransactionDao().addTransaction(tbean);


				//other

				if(request.getParameter("reqcrhead").equals("00.0")!=true){

					TransactionBean tbean3 = new TransactionBean();					
					tbean3.setCreatedby(user.getAd_user_id());
					tbean3.setUpdatedby(user.getAd_user_id());
					tbean3.setAd_voucher_id(bn.getAd_voucher_id());		
					tbean3.setAd_account_id(Integer.parseInt(request.getParameter("ad_account_id")));
					
					tbean3.setTrx_date(d);
					tbean3.setAd_member_id(Integer.parseInt(request.getParameter("ad_member_id_h")));
					
					tbean3.setNarration("New Loan : "+new MemberRegistrationDao().getMemberName(Integer.parseInt(request.getParameter("ad_member_id"))).getName());
					
					tbean3.setCramt(Double.parseDouble(request.getParameter("reqcrhead")));
					new TransactionDao().addTransaction(tbean3);
				}
				if(request.getParameter("reqdrhead").equals("00.0")!=true){
					TransactionBean tbean4 = new TransactionBean();					
					tbean4.setCreatedby(user.getAd_user_id());
					tbean4.setUpdatedby(user.getAd_user_id());
					tbean4.setAd_voucher_id(bn.getAd_voucher_id());			
					tbean4.setAd_account_id(Integer.parseInt(request.getParameter("ad_account_id")));
					
					tbean4.setTrx_date(d);
					tbean4.setAd_member_id(Integer.parseInt(request.getParameter("ad_member_id_h")));
					tbean4.setNarration("New Loan : "+new MemberRegistrationDao().getMemberName(Integer.parseInt(request.getParameter("ad_member_id"))).getName());
					
					
					tbean4.setDramt(Double.parseDouble(request.getParameter("reqdrhead")));
					new TransactionDao().addTransaction(tbean4);

				}

				new LoanTrxDao().updateLoanTrx(bn.getAd_voucher_id(),Integer.parseInt(request.getParameter("loan_trx_id")) );

				new LoanTrxDetailDao().updateLoanTrxDetail(bn.getAd_voucher_id(),Integer.parseInt(request.getParameter("loan_trx_id")) );

				AppMessage[0] = "alert-info";
				AppMessage[1] = "Loan successfully sent for approval..!! ";
				session.setAttribute("AppMessage", AppMessage);
				Gson gson = new Gson(); 
				String json = gson.toJson(lstMap); 
				response.setContentType("application/json");
				response.getWriter().print(json);
				response.sendRedirect("ad_loan_approval.jsp?no="+no);


			}if(action.equals("calculateinterest")){
				
				String date= request.getParameter("date");
				user=(UserBean)session.getAttribute("userbean");
				double intrest=0.0,balance=0.0,prv_balance=0.0;
				Date d =(Date)session.getAttribute("openday");
				Date maxDate=new LoanTrxDao().getLoanTrxMaxDate();
				int no=0;
				
				if(d.getTime()>=maxDate.getTime()){
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				
			    ArrayList<LoanTrxBean> loanlist=new LoanTrxDao().getAllActiveLoanForInterestCalculation(true,"Open");
			    
			    VoucherBean vbean = new VoucherBean();					
				vbean.setCreatedby(user.getAd_user_id());
				vbean.setUpdatedby(user.getAd_user_id());
				vbean.setDescription("loan_interest");
				vbean.setStatus("Pending");
				vbean.setVamt(0.0);
				vbean.setVfrom("Loan Interest");
				//bean.setVno(vno);
				vbean.setVtype("System");
				vbean.setVoucher_type("Loan Interest");
				vbean.setIsactive(true);
				vbean.setInstrument_from("NA");
				vbean.setInstrument_no("NA");	

				String d1=request.getParameter("date");
				sdf=new SimpleDateFormat("dd/MM/yyyy");
				Date d2=null;
				try{
					d2=sdf.parse(d1);

				}catch(ParseException p){
					p.printStackTrace();
				}
				vbean.setInstrument_date(d2);					
				
				vbean.setTrx_date(d);
				VoucherDao vdao=new VoucherDao();				
				no=vdao.getMaxtrxNo();					
				vbean.setTrx_no(no);				
				VoucherBean  bn = new VoucherDao().addVoucher(vbean);
				
			    if(loanlist.isEmpty()!=true){
			    	for(LoanTrxBean loanBean:loanlist){
			    		if(loanBean.getAd_member_id()!=0){
			    		LoanTrxDetailBean lntbn = new LoanTrxDetailDao().getLastInterestPostingDate(loanBean.getAd_member_id(), loanBean.getLoan_trx_id());
						if(lntbn==null){
							lntbn=new LoanTrxDetailDao().getLoanOpeningDate(loanBean.getAd_member_id(), loanBean.getLoan_trx_id());
						}
			    		
						
						Date first=null,second=null;
						if(lntbn.getTrx_date()!=null){
							first=lntbn.getTrx_date();
							prv_balance=lntbn.getBalance_amt();
						}
						
						double tot_balance=0.0;
						
						int tot_days=0;

						double one_day_int=(loanBean.getIntrest_rate());
						
						ArrayList<LoanTrxDetailBean> ltbn = new LoanTrxDetailDao().getLoanTrxDetailByLoantrxId(loanBean.getLoan_trx_id(),first);
						
						if(ltbn.isEmpty()!=true){
							for(LoanTrxDetailBean bean:ltbn){
								second=bean.getTrx_date();
								//prv_balance=bean.getBalance_amt();
								long diff = second.getTime() - first.getTime();
								long diffDays = diff / (24 * 60 * 60 * 1000);
								tot_days+=diffDays;
								/*tot_days++;*/
								tot_balance=prv_balance*diffDays;
								balance=bean.getBalance_amt();
								intrest +=(tot_balance*one_day_int)/36500.0;
								first=second;
								prv_balance=balance;
							}
						}
						
						if(d!=null ){

							second=d;
							if(second.compareTo(first)>=0){
							long diff = second.getTime() - first.getTime();
							long diffDays = diff / (24 * 60 * 60 * 1000);
							diffDays++;
							tot_days+=diffDays;
							
							tot_balance=prv_balance*diffDays;
							//balance=bean.getBalance_amt();
							intrest +=(tot_balance*one_day_int)/36500.0;
							first=second;
							prv_balance=balance;
							}
						}
						intrest=new BigDecimal(intrest).setScale(0, RoundingMode.HALF_UP).doubleValue();
						System.out.println("interest"+intrest);
						if(intrest!=0 && intrest>0){

						System.out.println("interest"+intrest);	
							//main loan to member category 1 type 1
							if(loanBean.getLoan_type()==1){
							TransactionBean trxb1  = new TransactionBean();
							trxb1.setCreatedby(user.getAd_user_id());
							trxb1.setUpdatedby(user.getAd_user_id());
							trxb1.setAd_voucher_id(bn.getAd_voucher_id());	
							trxb1.setTrx_date(d);
							trxb1.setAd_member_id(loanBean.getAd_member_id());
							trxb1.setAd_account_id(134);
							trxb1.setNarration("Interest On Loan Date-"+date);
							trxb1.setDramt(intrest);
							trxb1.setCramt(0.0);
							new TransactionDao().addTransaction(trxb1);
							
							TransactionBean trxb  = new TransactionBean();
							trxb.setCreatedby(user.getAd_user_id());
							trxb.setUpdatedby(user.getAd_user_id());
							trxb.setAd_voucher_id(bn.getAd_voucher_id());
							trxb.setTrx_date(d);
							trxb.setAd_member_id(loanBean.getAd_member_id());
							trxb.setAd_account_id(138);	
							trxb.setNarration("Interest On Loan-"+date);
							trxb.setDramt(0.0);
							trxb.setCramt(intrest);
							
							new TransactionDao().addTransaction(trxb);

							}else if(loanBean.getLoan_type()==2){
								TransactionBean trxb1  = new TransactionBean();
								trxb1.setCreatedby(user.getAd_user_id());
								trxb1.setUpdatedby(user.getAd_user_id());
								trxb1.setAd_voucher_id(bn.getAd_voucher_id());	
								trxb1.setTrx_date(d);
								trxb1.setAd_member_id(loanBean.getAd_member_id());
								trxb1.setAd_account_id(10);
								trxb1.setNarration("Interest On Loan Date-"+date);
								trxb1.setDramt(intrest);
								trxb1.setCramt(0.0);
								new TransactionDao().addTransaction(trxb1);
								
								TransactionBean trxb  = new TransactionBean();
								trxb.setCreatedby(user.getAd_user_id());
								trxb.setUpdatedby(user.getAd_user_id());
								trxb.setAd_voucher_id(bn.getAd_voucher_id());
								trxb.setTrx_date(d);
								trxb.setAd_member_id(loanBean.getAd_member_id());
								trxb.setAd_account_id(139);	
								trxb.setNarration("Interest On Loan-"+date);
								trxb.setDramt(0.0);
								trxb.setCramt(intrest);
								
								new TransactionDao().addTransaction(trxb);
							}
							LoanTrxDetailBean ltbn1 = new LoanTrxDetailDao().getOpenLoanBal(loanBean.getAd_member_id(), loanBean.getLoan_trx_id());	
							LoanTrxDetailBean ltbean1=new LoanTrxDetailBean();			
							ltbean1.setCreatedby(user.getAd_user_id());
							ltbean1.setUpdatedby(user.getAd_user_id());
							ltbean1.setInterest_amt(intrest);
							ltbean1.setDeposit_amt(0);
							
							ltbean1.setBalance_amt((ltbn1.getBalance_amt()+intrest));
							ltbean1.setTrx_date(d);
							ltbean1.setAd_member_id(loanBean.getAd_member_id());
							ltbean1.setLoan_trx_id(loanBean.getLoan_trx_id());
							ltbean1.setAd_voucher_id(bn.getAd_voucher_id());
							new LoanTrxDetailDao().addLoanTrxDetail(ltbean1);

							intrest=0;
						}
			    		
			    	
			    	
			    	
			    	
			    	}else if(loanBean.getAd_employee_id()!=0){
			    		
			    		LoanTrxDetailBean lntbn = new LoanTrxDetailDao().getLastInterestPostingDateForEmployee(loanBean.getAd_employee_id(), loanBean.getLoan_trx_id());
						if(lntbn==null){
							lntbn=new LoanTrxDetailDao().getLoanOpeningDateForEmployee(loanBean.getAd_employee_id(), loanBean.getLoan_trx_id());
						}
			    		
						
						Date first=null,second=null;
						if(lntbn.getTrx_date()!=null){
							first=lntbn.getTrx_date();
							prv_balance=lntbn.getBalance_amt();
						}
						
						double tot_balance=0.0;
						
						int tot_days=0;

						double one_day_int=(loanBean.getIntrest_rate());
						
						ArrayList<LoanTrxDetailBean> ltbn = new LoanTrxDetailDao().getLoanTrxDetailByLoantrxId(loanBean.getLoan_trx_id(),first);
						
						if(ltbn.isEmpty()!=true){
							for(LoanTrxDetailBean bean:ltbn){
								second=bean.getTrx_date();
								//prv_balance=bean.getBalance_amt();
								long diff = second.getTime() - first.getTime();
								long diffDays = diff / (24 * 60 * 60 * 1000);
								tot_days+=diffDays;
								/*tot_days++;*/
								tot_balance=prv_balance*diffDays;
								balance=bean.getBalance_amt();
								intrest +=(tot_balance*one_day_int)/36500.0;
								first=second;
								prv_balance=balance;
							}
						}
						
						if(d!=null ){

							second=d;
							if(second.compareTo(first)>=0){
							long diff = second.getTime() - first.getTime();
							long diffDays = diff / (24 * 60 * 60 * 1000);
							diffDays++;
							tot_days+=diffDays;
							
							tot_balance=prv_balance*diffDays;
							//balance=bean.getBalance_amt();
							intrest +=(tot_balance*one_day_int)/36500.0;
							first=second;
							prv_balance=balance;
							}
						}
						intrest=new BigDecimal(intrest).setScale(0, RoundingMode.HALF_UP).doubleValue();
						System.out.println("interest"+intrest);
						if(intrest!=0 && intrest>0){

						System.out.println("interest"+intrest);	
							//main loan to member category 1 type 1
							if(loanBean.getLoan_type()==1){
							TransactionBean trxb1  = new TransactionBean();
							trxb1.setCreatedby(user.getAd_user_id());
							trxb1.setUpdatedby(user.getAd_user_id());
							trxb1.setAd_voucher_id(bn.getAd_voucher_id());	
							trxb1.setTrx_date(d);
							trxb1.setAd_member_id(0);
							trxb1.setAd_employee_id(loanBean.getAd_employee_id());
							trxb1.setAd_account_id(12);
							trxb1.setNarration("Interest On Loan Date-"+date);
							trxb1.setDramt(intrest);
							trxb1.setCramt(0.0);
							new TransactionDao().addTransaction(trxb1);
							
							TransactionBean trxb  = new TransactionBean();
							trxb.setCreatedby(user.getAd_user_id());
							trxb.setUpdatedby(user.getAd_user_id());
							trxb.setAd_voucher_id(bn.getAd_voucher_id());
							trxb.setTrx_date(d);
							trxb.setAd_member_id(0);
							trxb.setAd_employee_id(loanBean.getAd_employee_id());
							trxb.setAd_account_id(65);	
							trxb.setNarration("Interest On Loan-"+date);
							trxb.setDramt(0.0);
							trxb.setCramt(intrest);
							
							new TransactionDao().addTransaction(trxb);

							}/*else if(loanBean.getLoan_type()==2){
								TransactionBean trxb1  = new TransactionBean();
								trxb1.setCreatedby(user.getAd_user_id());
								trxb1.setUpdatedby(user.getAd_user_id());
								trxb1.setAd_voucher_id(bn.getAd_voucher_id());	
								trxb1.setTrx_date(d);
								trxb1.setAd_member_id(loanBean.getAd_member_id());
								trxb1.setAd_account_id(10);
								trxb1.setNarration("Interest On Loan Date-"+date);
								trxb1.setDramt(intrest);
								trxb1.setCramt(0.0);
								new TransactionDao().addTransaction(trxb1);
								
								TransactionBean trxb  = new TransactionBean();
								trxb.setCreatedby(user.getAd_user_id());
								trxb.setUpdatedby(user.getAd_user_id());
								trxb.setAd_voucher_id(bn.getAd_voucher_id());
								trxb.setTrx_date(d);
								trxb.setAd_member_id(loanBean.getAd_member_id());
								trxb.setAd_account_id(139);	
								trxb.setNarration("Interest On Loan-"+date);
								trxb.setDramt(0.0);
								trxb.setCramt(intrest);
								
								new TransactionDao().addTransaction(trxb);
							}*/
							LoanTrxDetailBean ltbn1 = new LoanTrxDetailDao().getOpenLoanBal(loanBean.getAd_member_id(), loanBean.getLoan_trx_id());	
							LoanTrxDetailBean ltbean1=new LoanTrxDetailBean();			
							ltbean1.setCreatedby(user.getAd_user_id());
							ltbean1.setUpdatedby(user.getAd_user_id());
							ltbean1.setInterest_amt(intrest);
							ltbean1.setDeposit_amt(0);
							
							ltbean1.setBalance_amt((ltbn1.getBalance_amt()+intrest));
							ltbean1.setTrx_date(d);
							ltbean1.setAd_member_id(loanBean.getAd_member_id());
							ltbean1.setLoan_trx_id(loanBean.getLoan_trx_id());
							ltbean1.setAd_voucher_id(bn.getAd_voucher_id());
							new LoanTrxDetailDao().addLoanTrxDetail(ltbean1);

							intrest=0;
						}
			    		
			    		}
			    	}
			    }
				
			    AppMessage[0] = "alert-success";
				AppMessage[1] = "Loan interest Posted Successfully...!!";
				
				session.setAttribute("AppMessage", AppMessage);
				Gson gson = new Gson(); 
				String json = gson.toJson(lstMap); 
				response.setContentType("application/json");
				response.getWriter().print(json);
				response.sendRedirect("calculate_loan_int.jsp?no="+no);
				
				
				

			//	response.getWriter().print("<script>alert('Success fully Genrated Interest Of all Loan'); window.location.href='calculate_loan_int.jsp';</script> ");
				//	response.sendRedirect("calculate_loan_int.jsp");


			}else{
				AppMessage[0] = "alert-info";
				AppMessage[1] = "Loan interest cannot be post in current system date...!!";
				lstMap.put("message","error");
				lstMap.put("errorMessage","Loan interest cannot be post in current system date...!");
				session.setAttribute("AppMessage", AppMessage);
			}
			
			Gson gson = new Gson(); 
			String json = gson.toJson(lstMap); 
			response.setContentType("application/json");
			response.getWriter().print(json);
			response.sendRedirect("calculate_loan_int.jsp?no="+no);
			}

	else if(action.equals("edit")){
		
		
		//loan_trx
		String loan_trx_id=request.getParameter("loan_trx_id");
		String isactive=request.getParameter("isactive");
		LoanTrxBean bean=new LoanTrxBean();
		bean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
		bean.setUpdatedby(user.getAd_user_id());
		bean.setLoan_trx_id(Integer.parseInt(loan_trx_id.trim()));
		try{

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date dt1 =  df.parse(loan_date);  
			bean.setissue_date(dt1);
			DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
			Date dt2 =  df1.parse(end_date);  
			bean.setEnd_date(dt2);				
			bean.setLoan_purpose(loan_purpose);
			bean.setIsactive(Boolean.parseBoolean(isactive));

		}catch(ParseException p){
			p.printStackTrace();
		}


		try{
			
			bean.setStatus(status);
			bean.setLoan_amt(Double.parseDouble(req_loan_amt));
			bean.setIntrest_rate(Double.parseDouble(int_rate));
			bean.setPeriod_month(Integer.parseInt(period_month));
			bean.setEmi(Double.parseDouble(emi));
			
		}catch(NumberFormatException n){
			n.printStackTrace();
		}

		int id =dao.updateLoan(bean);
		
		if(id>0){
		AppMessage[0] = "alert-success";
		AppMessage[1] = "Loan Updated successfully ..!!";
		session.setAttribute("AppMessage", AppMessage);
		Gson gson = new Gson(); 
		String json = gson.toJson(lstMap); 
		response.setContentType("application/json");
		response.getWriter().print(json);
		response.sendRedirect("loan_detail_view_2.jsp?loan_trx_id="+loan_trx_id);
		}else{
			AppMessage[0] = "alert-info";
			AppMessage[1] = "Sorry try again later..!!";
			session.setAttribute("AppMessage", AppMessage);
			Gson gson = new Gson(); 
			String json = gson.toJson(lstMap); 
			response.setContentType("application/json");
			response.getWriter().print(json);
			response.sendRedirect("loan_detail_view_2.jsp?loan_trx_id="+loan_trx_id);
			
			
			
		}
		
	}else if(action.equals("edit_loan_trx_detail")){
		String loan_trx_id=request.getParameter("loan_trx_id");
		String ltd=request.getParameter("ad_loan_trx_id");
		String trxdate=request.getParameter("trx_date");
		String bal=request.getParameter("balance_amt");
		String deposit=request.getParameter("deposit_amt");
		String interest=request.getParameter("interest_amt");
		status=request.getParameter("isactive");
		double deposit_amt=0.0,balance_amt=0.0,interest_amt=0.0;
		int ad_loan_trx_id=0,loan_id=0;
		Date trx_date=null;
		boolean isactive=false;
		try{
			
			loan_id=Integer.parseInt(loan_trx_id);
			ad_loan_trx_id=Integer.parseInt(ltd);
			deposit_amt=Double.parseDouble(deposit);
			interest_amt=Double.parseDouble(interest);
			balance_amt=Double.parseDouble(bal);
			trx_date=new SimpleDateFormat("dd/MM/yyyy").parse(trxdate);
			isactive=Boolean.parseBoolean(status);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		LoanTrxDetailBean bean=new LoanTrxDetailBean();
		bean.setAd_loan_trx_id(ad_loan_trx_id);
		bean.setDeposit_amt(deposit_amt);
		bean.setInterest_amt(interest_amt);
		bean.setBalance_amt(balance_amt);
		bean.setAd_loan_trx_id(ad_loan_trx_id);
		bean.setTrx_date(trx_date);
		bean.setIsactive(isactive);
		bean.setUpdatedby(user.getAd_user_id());
		int id=new LoanTrxDetailDao().updateLoanTrx(bean);
		
		if(id>0){
			AppMessage[0] = "alert-success";
			AppMessage[1] = "Loan Detail Updated successfully ..!!";
			session.setAttribute("AppMessage", AppMessage);
			Gson gson = new Gson(); 
			String json = gson.toJson(lstMap); 
			response.setContentType("application/json");
			response.getWriter().print(json);
			response.sendRedirect("loan_detail_view_2.jsp?loan_trx_id="+loan_trx_id);
			}else{
				AppMessage[0] = "alert-info";
				AppMessage[1] = "Sorry try again later..!!";
				session.setAttribute("AppMessage", AppMessage);
				Gson gson = new Gson(); 
				String json = gson.toJson(lstMap); 
				response.setContentType("application/json");
				response.getWriter().print(json);
				response.sendRedirect("loan_detail_view_2.jsp?loan_trx_id="+loan_trx_id);
				
				
				
			}
	}else if(action.equals("deleteLoanTrx")){
		String loan_trx_id=request.getParameter("loan_trx_id");
		String ltd=request.getParameter("ad_loan_trx_id");
		int ad_loan_trx_id=0,loan_id=0;
		
		try{
			
			loan_id=Integer.parseInt(loan_trx_id);
			ad_loan_trx_id=Integer.parseInt(ltd);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		int id=new LoanTrxDetailDao().deleteLoanTrxDetailById(ad_loan_trx_id);

		if(id>0){
			AppMessage[0] = "alert-success";
			AppMessage[1] = "Loan Detail Deleted successfully ..!!";
			session.setAttribute("AppMessage", AppMessage);
			Gson gson = new Gson(); 
			String json = gson.toJson(lstMap); 
			response.setContentType("application/json");
			response.getWriter().print(json);
			response.sendRedirect("loan_detail_view_2.jsp?loan_trx_id="+loan_id);
			}else{
				AppMessage[0] = "alert-info";
				AppMessage[1] = "Sorry try again later..!!";
				session.setAttribute("AppMessage", AppMessage);
				Gson gson = new Gson(); 
				String json = gson.toJson(lstMap); 
				response.setContentType("application/json");
				response.getWriter().print(json);
				response.sendRedirect("loan_detail_view_2.jsp?loan_trx_id="+loan_id);
				
				
				
			}
	}else if(action.equals("deleteLoan")){
		String loan_trx_id=request.getParameter("loan_trx_id");
		int loan_id=0;
		String error="";
		int id=0;
		try{
			
			loan_id=Integer.parseInt(loan_trx_id);
			id=new LoanTrxDao().deleteLoan(loan_id);
			
		}catch (Exception e) {
			e.printStackTrace();
			error=e.getMessage();
		}
		
		

		if(id>0){
			AppMessage[0] = "alert-success";
			AppMessage[1] = "Loan Deleted successfully ..!!";
			session.setAttribute("AppMessage", AppMessage);
			Gson gson = new Gson(); 
			String json = gson.toJson(lstMap); 
			response.setContentType("application/json");
			response.getWriter().print(json);
			response.sendRedirect("loan_detail_view_2.jsp?loan_trx_id="+loan_id);
			}else{
				AppMessage[0] = "alert-info";
				AppMessage[1] = "Sorry try again later..!! "+error;
				session.setAttribute("AppMessage", AppMessage);
				Gson gson = new Gson(); 
				String json = gson.toJson(lstMap); 
				response.setContentType("application/json");
				response.getWriter().print(json);
				response.sendRedirect("loan_detail_view_2.jsp?loan_trx_id="+loan_id);
				
				
				
			}
	}else if(action.equals("deletePendingLoan")){
		String loan_trx_id=request.getParameter("loan_trx_id");
		int loan_id=0;
		String error="";
		int id=0;
		try{
			
			loan_id=Integer.parseInt(loan_trx_id);
			id+=new MemberShareDao().deleteMemberShareByLoanId(loan_id);
			id+=new MemberWitnessChkDao().deleteMemberWitnessChk(loan_id);
			id+=new LoanTrxDetailDao().deleteLoanTrxByLoanId(loan_id);
			id+=new LoanTrxDao().deleteLoan(loan_id);
			
		}catch (Exception e) {
			e.printStackTrace();
			error=e.getMessage();
		}
		
		
		System.out.println("Loan deleted id "+id);
		if(id>=4){
			AppMessage[0] = "alert-success";
			AppMessage[1] = "Loan Deleted successfully ..!!";
			session.setAttribute("AppMessage", AppMessage);
			Gson gson = new Gson(); 
			String json = gson.toJson(lstMap); 
			response.setContentType("application/json");
			response.getWriter().print(json);
			response.sendRedirect("ad_loan_approval.jsp");
			}else{
				AppMessage[0] = "alert-info";
				AppMessage[1] = "Sorry try again later..!! "+error;
				session.setAttribute("AppMessage", AppMessage);
				Gson gson = new Gson(); 
				String json = gson.toJson(lstMap); 
				response.setContentType("application/json");
				response.getWriter().print(json);
				response.sendRedirect("ad_loan_approval.jsp");
				
				
				
			}
	}


}catch(Exception e){
	e.printStackTrace();

}

}

}
