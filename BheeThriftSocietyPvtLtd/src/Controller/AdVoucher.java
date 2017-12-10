package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import Model.Bean.ExcessTrxBean;
import Model.Bean.FdTrxBean;
import Model.Bean.GenderBean;
import Model.Bean.LedgerAccountBean;
import Model.Bean.LoanTrxBean;
import Model.Bean.LoanTrxDetailBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.MemberRegistrationMasterBean;
import Model.Bean.MemberShareBean;
import Model.Bean.SalutationBean;
import Model.Bean.TempTranxBean;
import Model.Bean.ThriftRoiBean;
import Model.Bean.ThriftTrxBean;
import Model.Bean.ThriftViewBean;
import Model.Bean.TransactionBean;
import Model.Bean.UserBean;
import Model.Bean.VoucherBean;
import Model.Dao.AccountTypeDao;
import Model.Dao.ExcessTrxDao;
import Model.Dao.FdTrxDao;
import Model.Dao.FinalPayDao;
import Model.Dao.GenderDao;
import Model.Dao.LedgerAccountDao;
import Model.Dao.LoanTrxDao;
import Model.Dao.LoanTrxDetailDao;
import Model.Dao.MemberRegistrationDao;
import Model.Dao.MemberShareDao;
import Model.Dao.SalutationDao;
import Model.Dao.TempTranxDao;
import Model.Dao.ThriftIntDao;
import Model.Dao.ThriftRoiDao;
import Model.Dao.ThriftTrxDao;
import Model.Dao.TransactionDao;
import Model.Dao.VoucherDao;

/**
 * Servlet implementation class AdVoucher
 */
@WebServlet("/AdVoucher")
public class AdVoucher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VoucherDao dao=null;
	private UserBean user=null;
	public AdVoucher() {
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
		try{
			dao=new VoucherDao();
			String action=request.getParameter("action");
			String loan_id=request.getParameter("loan_trx_id");
			int rowsUpdated=0;

			if(action!=null){ 

				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				HashMap<String,String> lstMap = new HashMap<String,String>();
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
				session.setAttribute("AppMessage", AppMessage);

				if(action.equals("approve")){

					int ad_voucher_id=0;
					try{

						ad_voucher_id=Integer.parseInt(request.getParameter("ad_voucher_id"));
					}catch(NumberFormatException n){
						n.printStackTrace();

					}

					System.out.println(ad_voucher_id);
					VoucherBean bean = new VoucherBean();
					bean.setUpdatedby(user.getAd_user_id());
					bean.setUpdated(new java.util.Date());
					bean.setAd_voucher_id(ad_voucher_id);	
					bean.setStatus("Approved");
					Date d=(Date)request.getSession().getAttribute("openday");
					String date=new SimpleDateFormat("ddMMyy").format(d);
					int vno=new VoucherDao().getMaxVoucherNo(d);
					String v_no=date+"28TR/";
					v_no=v_no+(vno);
					bean.setVno(v_no);


					
					new ThriftTrxDao().updateThriftTrx(ad_voucher_id);
					
					ExcessTrxBean excessBean=new ExcessTrxBean();
					excessBean.setIsactive(true);
					excessBean.setIsactive(true);
					excessBean.setAd_voucher_id(ad_voucher_id);
					excessBean.setUpdatedby(user.getAd_user_id());
					excessBean.setStatus("Approved");
					excessBean.setUpdatedby(user.getAd_user_id());
					rowsUpdated+= new ExcessTrxDao().updateExcess(excessBean);

					new VoucherDao().VoucherApprovel(bean);
					
					VoucherBean vbean = new VoucherDao().getVoucherByVoucherId(ad_voucher_id);


					if(vbean.getDescription().equals("new_member")){

						new MemberRegistrationDao().updateMemberStatus(ad_voucher_id,user.getAd_user_id());

						MemberShareBean msbean = new MemberShareBean();
						msbean.setUpdatedby(user.getAd_user_id());
						msbean.setIsactive(true);
						msbean.setAd_voucher_id(ad_voucher_id);
						new MemberShareDao().updateMemberShareStatusbyvid(msbean);
                        new ThriftTrxDao().updateThriftTrx(ad_voucher_id);
                        
					}else if(vbean.getDescription().equals("new_share")){

						MemberShareBean msbean = new MemberShareBean();
						msbean.setUpdatedby(user.getAd_user_id());
						msbean.setIsactive(true);
						msbean.setAd_voucher_id(ad_voucher_id);
						new MemberShareDao().updateMemberShareStatusbyvid(msbean);
					}else if(vbean.getDescription().equals("")){
						MemberRegistrationBean mrbean = new MemberRegistrationBean();
						mrbean.setUpdatedby(user.getAd_user_id());

						mrbean.setAd_member_id(new FinalPayDao().getFinalPaymidVById(ad_voucher_id));
						new MemberRegistrationDao().updateMemberStatusbymid(mrbean);

					}else if(vbean.getDescription().equals("new_fd")){
						
						FdTrxBean fdbean=new FdTrxBean();
						fdbean.setAd_voucher_id(ad_voucher_id);
						fdbean.setUpdatedby(user.getAd_user_id());
						fdbean.setIsactive(true);
						fdbean.setRemark("OPEN");
						new FdTrxDao().updateFdVoucher(fdbean);
						
						
					}else if(vbean.getDescription().equals("renew_fd")){
						
						FdTrxBean fdbean=new FdTrxBean();
						fdbean.setAd_voucher_id(ad_voucher_id);
						fdbean.setUpdatedby(user.getAd_user_id());
						fdbean.setIsactive(true);
						fdbean.setRemark("OPEN");
						new FdTrxDao().updateFdVoucher(fdbean);
						
						
					}else if(vbean.getDescription().equals("new_loan")){
						int loan_trx_id=0;
						LoanTrxBean lnbean=new LoanTrxBean();
						lnbean.setAd_voucher_id(ad_voucher_id);
						lnbean.setUpdatedby(user.getAd_user_id());
						if(loan_id!=null){
							try{
								loan_trx_id=Integer.parseInt(loan_id);
							}catch(NumberFormatException n){
								n.printStackTrace();
							}
							
						}
						lnbean.setLoan_trx_id(loan_trx_id);
						lnbean.setIsactive(true);
						lnbean.setStatus("Open");
						rowsUpdated+= new LoanTrxDao().updateLoanTrx(lnbean);
						
						MemberShareBean shbean=new MemberShareDao().getMemberShareByLoanId(loan_trx_id);
						shbean.setIsactive(true);
						shbean.setAd_voucher_id(ad_voucher_id);
						shbean.setUpdatedby(user.getAd_user_id());
						shbean.setStatus("Approved");
						rowsUpdated+= new MemberShareDao().updateMemberShareStatus(shbean);
						
						
						
						LoanTrxDetailBean ltrxbean=new LoanTrxDetailBean();
						ltrxbean.setUpdatedby(user.getAd_user_id());
						ltrxbean.setAd_voucher_id(ad_voucher_id);
						ltrxbean.setLoan_trx_id(loan_trx_id);
						rowsUpdated+= new LoanTrxDetailDao().updateLoanTrxDetail(ltrxbean);
						
					}



					//VoucherBean bean1=new VoucherDao().VoucherApprovel(bean);
					// VoucherBean bean1=dao.addVoucher(bean);
					//if (bean1.getAd_voucher_id() > 0) {
					rowsUpdated=dao.getMaxVoucherNo(d);
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Voucher Approved Successfully! " ;
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					//System.out.println(v_no);
					response.getWriter().print(v_no);

				}else if(action.equals("pendingVoucherlist")){
					try{
						List<VoucherBean> lst=new ArrayList<VoucherBean>();
						lst=new VoucherDao().getAllPendingVoucher();
						//Convert Java Object to Json
						Gson gson = new Gson();
						JsonElement element = gson.toJsonTree(lst, new TypeToken<List<VoucherBean>>() {}.getType());
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

				}else if(action.equals("trx_forword")){

					String discription="Manual Voucher";
					String status=request.getParameter("voucher_status");
					System.out.println(request.getParameter("vamt"));
					double vamt=Double.parseDouble(request.getParameter("vamt").trim());
					String vfrom=request.getParameter("vfrom");
					String vtype=request.getParameter("vtype");
					String vno=request.getParameter("voucher_no");
					String voucher_type=request.getParameter("voucher_type");
					String amt_in_words=request.getParameter("amt_in_words");
					
					VoucherBean bean=new VoucherBean();

					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setDescription(discription);
					bean.setStatus(status);
					bean.setVamt(vamt);
					bean.setAmt_in_words(amt_in_words);
					bean.setVfrom(voucher_type);
					//bean.setVno(vno);
					bean.setVtype(vtype);
					bean.setVoucher_type(voucher_type);
					bean.setIsactive(true);
                     
					if(request.getParameter("cheque_no")!="" && request.getParameter("cheque_bank")!="" && request.getParameter("cheque_date")!="")
					{
						bean.setInstrument_from(request.getParameter("cheque_bank"));
						bean.setInstrument_no(request.getParameter("cheque_no"));
						bean.setInstrument_amt(Double.parseDouble(request.getParameter("cheque_amt")));
                        String date=  request.getParameter("cheque_date");
                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                        
                        Date d1=null;
                        try{
                        	d1=sdf.parse(date);
                        }catch(ParseException p){
                        	p.printStackTrace();
                        }
						bean.setInstrument_date(d1);


					}
					else if(request.getParameter("dd_no")!="" && request.getParameter("dd_bank")!="" && request.getParameter("dd_date")!="")
					{
						bean.setInstrument_from(request.getParameter("dd_bank"));
						bean.setInstrument_no(request.getParameter("dd_no"));
						bean.setInstrument_amt(Double.parseDouble(request.getParameter("dd_amt")));
						
						 String date=  request.getParameter("dd_date");
	                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	                        Date d1=null;
	                        try{
	                        	d1=sdf.parse(date);
	                        }catch(ParseException p){
	                        	p.printStackTrace();
	                        }
							bean.setInstrument_date(d1);

					}if(request.getParameter("online_ref_no")!="" && request.getParameter("online_bank")!="" && request.getParameter("online_date")!="")
					{
						bean.setInstrument_from(request.getParameter("online_bank"));
						bean.setInstrument_no(request.getParameter("online_ref_no"));
						bean.setInstrument_amt(Double.parseDouble(request.getParameter("online_amt")));
						 String date=  request.getParameter("online_trx_date");
	                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	                        Date d1=null;
	                        try{
	                        	d1=sdf.parse(date);
	                        }catch(ParseException p){
	                        	p.printStackTrace();
	                        }
							bean.setInstrument_date(d1);
						

					}
					try{


						if(request.getParameter("voucher_date")!=null){
							bean.setTrx_date(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("voucher_date")));
                         
						}
						if(vtype.equals("Adjustment")==true){
							bean.setInstrument_date(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("voucher_date")));
					
						}


					}catch(Exception e){
						e.printStackTrace();
					}

					//---------generate voucher no-------------------------------------------------------------
					Date d=(Date)request.getSession().getAttribute("openday");
					VoucherDao vdao=new VoucherDao();

					int no=vdao.getMaxtrxNo();

					//System.out.println(no);
					PrintWriter out=response.getWriter();

					//	String date=Integer.toString(1900+d.getYear())+Integer.toString(1+d.getMonth())+Integer.toString(d.getDate());
					//	date=date+(no);
					//-------------------------------------------------------------------------------------------
					bean.setTrx_no(no);
					//bean.setVno('');

					VoucherBean vidbean = new VoucherBean();
					vidbean=dao.addVoucher(bean);
					new VoucherDao().updateTrxNo();
					//	System.out.println("vid"+vidbean.getAd_voucher_id());

					session.setAttribute("voucher", bean);

				/*	out.print("<script>alert(\"Trx No.: "+no+"\");</script>");*/

					out.print("<script>window.location.href='ad_transaction.jsp?ad_voucher_id="+vidbean.getAd_voucher_id()+"';</script>");


				}else if(action.equals("loan_trx_forword")){
					String discription="new_loan";
					String status=request.getParameter("voucher_status");
									
					double vamt=Double.parseDouble(request.getParameter("vamt").trim());
					String vfrom=request.getParameter("vfrom");
					String vtype=request.getParameter("vtype");
					String vno=request.getParameter("voucher_no");
					String voucher_type=request.getParameter("voucher_type");
					String amt_in_words=request.getParameter("amt_in_words");
					VoucherBean bean=new VoucherBean();

					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setDescription(discription);
					bean.setStatus(status);
					bean.setVamt(vamt);
					bean.setAmt_in_words(amt_in_words);
					bean.setVfrom(voucher_type);
					//bean.setVno(vno);
					bean.setVtype(vtype);
					bean.setVoucher_type(voucher_type);
					bean.setIsactive(true);

					
					try{


						if(request.getParameter("voucher_date")!=null){
							bean.setTrx_date(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("voucher_date")));

						}


					}catch(Exception e){
						e.printStackTrace();
					}

					//---------generate voucher no-------------------------------------------------------------
					Date d=(Date)request.getSession().getAttribute("openday");
					VoucherDao vdao=new VoucherDao();

					int no=vdao.getMaxtrxNo();
                    
					//System.out.println(no);
					PrintWriter out=response.getWriter();

					//	String date=Integer.toString(1900+d.getYear())+Integer.toString(1+d.getMonth())+Integer.toString(d.getDate());
					//	date=date+(no);
					//-------------------------------------------------------------------------------------------
					bean.setTrx_no(no);
					//bean.setVno('');

					VoucherBean vidbean = new VoucherBean();
					vidbean=dao.addLoanVoucher(bean);
					new VoucherDao().updateTrxNo();
					//	System.out.println("vid"+vidbean.getAd_voucher_id());

					session.setAttribute("voucher", bean);

				/*	out.print("<script>alert(\"Trx No.: "+no+"\");</script>");*/

					out.print("<script>window.location.href='new_loan_disbursement.jsp?ad_voucher_id="+vidbean.getAd_voucher_id()+"';</script>");

				}else if (action.equals("edit")){

					VoucherBean bean=new VoucherBean();

					boolean isactive=true;
					String discription=request.getParameter("discription");
					String status=request.getParameter("voucher_status");
					//System.out.println(request.getParameter("vamt"));
					double vamt=Double.parseDouble(request.getParameter("vamt").trim());
					String vfrom=request.getParameter("vfrom");
					String vtype=request.getParameter("vtype");
					String vno=request.getParameter("voucher_no");
					String voucher_type=request.getParameter("voucher_type");				
					bean.setUpdatedby(user.getAd_user_id());
					bean.setUpdated(new java.util.Date());
					bean.setDescription(discription);
					bean.setStatus(status);
					bean.setVamt(vamt);
					bean.setVfrom(vfrom);
					bean.setVno(vno);
					bean.setVtype(vtype);
					bean.setVoucher_type(voucher_type);
					bean.setAd_voucher_id(Integer.parseInt(request.getParameter("ad_voucher_id")));

					bean.setIsactive(isactive);
					if(request.getParameter("cheque_no")!="" && request.getParameter("cheque_bank")!="" && request.getParameter("cheque_date")!="")
					{
						bean.setInstrument_from(request.getParameter("cheque_bank"));
						bean.setInstrument_no(request.getParameter("cheque_no"));
						bean.setInstrument_amt(Double.parseDouble(request.getParameter("instrument_amt")));
						

						 String date=  request.getParameter("cheque_date");
	                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	                        Date d1=null;
	                        try{
	                        	d1=sdf.parse(date);
	                        }catch(ParseException p){
	                        	p.printStackTrace();
	                        }
							bean.setInstrument_date(d1);
						

					}
					else if(request.getParameter("dd_no")!="" && request.getParameter("dd_bank")!="" && request.getParameter("dd_date")!=""){
						bean.setInstrument_from(request.getParameter("dd_bank"));
						bean.setInstrument_no(request.getParameter("dd_no"));
						bean.setInstrument_amt(Double.parseDouble(request.getParameter("instrument_amt")));
						String date=  request.getParameter("dd_date");
	                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	                        Date d1=null;
	                        try{
	                        	d1=sdf.parse(date);
	                        }catch(ParseException p){
	                        	p.printStackTrace();
	                        }
							bean.setInstrument_date(d1);
						
						


					}if(request.getParameter("online_ref_no")!="" && request.getParameter("online_bank")!="" && request.getParameter("online_date")!="")
					{
						bean.setInstrument_from(request.getParameter("online_bank"));
						bean.setInstrument_no(request.getParameter("online_ref_no"));
						bean.setInstrument_amt(Double.parseDouble(request.getParameter("instrument_amt")));
						 String date=  request.getParameter("online_trx_date");
	                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	                        Date d1=null;
	                        try{
	                        	d1=sdf.parse(date);
	                        }catch(ParseException p){
	                        	p.printStackTrace();
	                        }
							bean.setInstrument_date(d1);
						
						
					}
					try{


						if(request.getParameter("trx-date")!=null){
							bean.setTrx_date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("voucher_date")));
						}


					}catch(Exception e){e.printStackTrace();
					}
					dao.updatedVoucher(bean);
					response.sendRedirect("ad_voucher.jsp");

				}else if(action.equals("insert")){
					String discription=request.getParameter("discription");
					String status=request.getParameter("voucher_status");
					System.out.println(request.getParameter("vamt"));
					double vamt=Double.parseDouble(request.getParameter("vamt").trim());
					String vfrom=request.getParameter("vfrom");
					String vtype=request.getParameter("vtype");
					String vno=request.getParameter("voucher_no");
					String voucher_type=request.getParameter("voucher_type");				
					VoucherBean bean=new VoucherBean();				
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setDescription(discription);
					bean.setStatus(status);
					bean.setVamt(vamt);
					bean.setVfrom(vfrom);
					bean.setVno(vno);
					bean.setVtype(vtype);
					bean.setIsactive(true);
					bean.setCreated(new java.util.Date());
					bean.setUpdated(new java.util.Date());
					bean.setVoucher_type(voucher_type);
					if(request.getParameter("cheque_no")!="" && request.getParameter("cheque_bank")!="" && request.getParameter("cheque_date")!="")
					{
						bean.setInstrument_from(request.getParameter("cheque_bank"));
						bean.setInstrument_no(request.getParameter("cheque_no"));
						bean.setInstrument_amt(Double.parseDouble(request.getParameter("instrument_amt")));
						String date=  request.getParameter("cheque_date");
                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                        Date d1=null;
                        try{
                        	d1=sdf.parse(date);
                        }catch(ParseException p){
                        	p.printStackTrace();
                        }
						bean.setInstrument_date(d1);
					
						

					}
					else if(request.getParameter("dd_no")!="" && request.getParameter("dd_bank")!="" && request.getParameter("dd_date")!=""){
						bean.setInstrument_from(request.getParameter("dd_bank"));
						bean.setInstrument_no(request.getParameter("dd_no"));
						bean.setInstrument_amt(Double.parseDouble(request.getParameter("instrument_amt")));
						String date=  request.getParameter("dd_date");
                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                        Date d1=null;
                        try{
                        	d1=sdf.parse(date);
                        }catch(ParseException p){
                        	p.printStackTrace();
                        }
						bean.setInstrument_date(d1);
					
						

					}if(request.getParameter("online_ref_no")!="" && request.getParameter("online_bank")!="" && request.getParameter("online_date")!="")
					{
						bean.setInstrument_from(request.getParameter("online_bank"));
						bean.setInstrument_no(request.getParameter("online_ref_no"));
						bean.setInstrument_amt(Double.parseDouble(request.getParameter("instrument_amt")));
						String date=  request.getParameter("online_trx_date");
                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                        Date d1=null;
                        try{
                        	d1=sdf.parse(date);
                        }catch(ParseException p){
                        	p.printStackTrace();
                        }
						bean.setInstrument_date(d1);
					
						

					}
					try{


						if(request.getParameter("trx-date")!=null){
							bean.setTrx_date(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("voucher_date")));
						}


					}catch(Exception e){e.printStackTrace();
					}


					//dao.addVoucher(bean);
					VoucherBean bean1=dao.addVoucher(bean);
					if (bean1.getAd_voucher_id() > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Inserted!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}

					response.sendRedirect("ad_voucher.jsp");
				}
				else if(action.equals("insertnetprofit")){

					String bal_amt = request.getParameter("bal_amt");
					String ad_account_id=request.getParameter("ad_account_id");
					String bal_type=request.getParameter("bal_type");
					String narration =request.getParameter("narration");

					if(bal_amt!="" &&  ad_account_id!="0" && bal_type!="0"){
						int adacid=Integer.parseInt(ad_account_id);
						double balamt = Double.parseDouble(bal_amt);


						VoucherBean bean=new VoucherBean();

						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setDescription("manual");
						bean.setStatus("Pending");
						bean.setVamt(balamt);
						bean.setVfrom("");
						bean.setVno("");
						bean.setVtype("Adjestment");
						bean.setIsactive(true);
						bean.setCreated(new java.util.Date());
						bean.setUpdated(new java.util.Date());
						bean.setVoucher_type("Net Profit Adjestment");	
						Date d=(Date)request.getSession().getAttribute("openday");
						VoucherDao vdao=new VoucherDao();

						int no=vdao.getMaxtrxNo();
						bean.setTrx_no(no);
						bean.setTrx_date(d);

						bean= new VoucherDao().addVoucher(bean);
						bean.getAd_voucher_id();
						TransactionBean tbean =new TransactionBean();

						tbean.setVoucher(bean);
						tbean.setCreatedby(user.getAd_user_id());
						tbean.setUpdatedby(user.getAd_user_id());
						tbean.setIsactive(true);
						LedgerAccountBean lb = new LedgerAccountBean();
						lb.setAd_account_id(adacid);
						tbean.setLedger(lb);
						tbean.setTrx_date(d);
						MemberRegistrationBean mrb = new MemberRegistrationBean();
						mrb.setAd_member_id(0);
						tbean.setMember(mrb);
						if(bal_type.equals("Cr Bal")){
							tbean.setCramt(balamt);
							tbean.setDramt(0.00);

						}else if(bal_type.equals("Dr Bal")){
							tbean.setDramt(balamt);
							tbean.setCramt(0.00);
						}
						tbean.setNarration(narration);

						new TransactionDao().addTransaction(tbean);

					}
					response.sendRedirect("adjustment_net_profit.jsp");
				}else if(action.equals("delete_voucher_main")){
					
					int ad_voucher_id=0;
					try{
						ad_voucher_id=Integer.parseInt(request.getParameter("ad_voucher_id"));
					}catch(NumberFormatException n){
						n.printStackTrace();

					}
					VoucherBean dv= new VoucherBean();
					dv.setAd_voucher_id(ad_voucher_id);
					int i=new VoucherDao().deleteVoucher(dv);
					if(i>0){
					AppMessage[0] = "alert-success";
					AppMessage[1] = "Voucher Deleted Successfully...!!";
					
					session.setAttribute("AppMessage", AppMessage);
					Gson gson = new Gson(); 
					String json = gson.toJson(lstMap); 
					response.setContentType("application/json");
					response.getWriter().print(json);
					response.sendRedirect("view_voucher_detail.jsp");
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Please Try Again...!!";
						
						session.setAttribute("AppMessage", AppMessage);
						Gson gson = new Gson(); 
						String json = gson.toJson(lstMap); 
						response.setContentType("application/json");
						response.getWriter().print(json);
						response.sendRedirect("view_voucher_detail.jsp");
						
					}
					
				}else if(action.equals("deletevoucher")){

					int ad_voucher_id=0;
					try{
						ad_voucher_id=Integer.parseInt(request.getParameter("ad_voucher_id"));
					}catch(NumberFormatException n){
						n.printStackTrace();

					}

					new FdTrxDao().deleteFdTrxByVoucherID(ad_voucher_id);
					new MemberShareDao().deleteMemberShareByVoucherId(ad_voucher_id);	
					new LoanTrxDetailDao().deleteLoanTrxDetail(ad_voucher_id);			
					new LoanTrxDao().deleteLoanTrx(ad_voucher_id);			
					new FinalPayDao().deleteFinalPay(ad_voucher_id);
					new ThriftTrxDao().deleteThriftTrx(ad_voucher_id);
					new ExcessTrxDao().deleteExcessByVoucher(ad_voucher_id);
					new TransactionDao().deleteTransactionbyvoucherid(ad_voucher_id);
					new TempTranxDao().deleteTempTranxByvNo(ad_voucher_id);	
					
					VoucherBean dv= new VoucherBean();
					dv.setAd_voucher_id(ad_voucher_id);
					new VoucherDao().deleteVoucher(dv);

					response.sendRedirect("ad_voucher.jsp");


				}else if(action.equals("deletepayrolltempvoucher")){
					String from=request.getParameter("from");
					int ad_voucher_id=0;
					try{
						ad_voucher_id=Integer.parseInt(request.getParameter("ad_voucher_id"));
					}catch(NumberFormatException n){
						n.printStackTrace();

					}

					new FdTrxDao().deleteFdTrxByVoucherID(ad_voucher_id);
					new MemberShareDao().deleteMemberShareByVoucherId(ad_voucher_id);	
					new LoanTrxDetailDao().deleteLoanTrxDetail(ad_voucher_id);			
					new LoanTrxDao().deleteLoanTrx(ad_voucher_id);	
					
					new FinalPayDao().deleteFinalPay(ad_voucher_id);
					new ThriftTrxDao().deleteThriftTrx(ad_voucher_id);
					new ExcessTrxDao().deleteExcessByVoucher(ad_voucher_id);
					new TransactionDao().deleteTransactionbyvoucherid(ad_voucher_id);
					new TempTranxDao().deleteTempTranxByvNo(ad_voucher_id);	
					
					//for active member account
					
					VoucherBean dv= new VoucherBean();
					dv.setAd_voucher_id(ad_voucher_id);
					new VoucherDao().deleteVoucher(dv);
					if(from.equals("final_pay")){
						response.sendRedirect("homepage.jsp");
					}
					response.sendRedirect("homepage.jsp");


				}else if(action.equals("deleteloanvoucher")){

					int ad_voucher_id=0;
					try{
						ad_voucher_id=Integer.parseInt(request.getParameter("ad_voucher_id"));
					}catch(NumberFormatException n){
						n.printStackTrace();

					}

					TempTranxBean dvt= new TempTranxBean();
					dvt.setAd_voucher_id(ad_voucher_id);
					new TempTranxDao().deleteTempTranxByvNo(dvt);

					VoucherBean dv= new VoucherBean();
					dv.setAd_voucher_id(ad_voucher_id);
					new VoucherDao().deleteVoucher(dv);

					response.sendRedirect("new_loan_disbursement.jsp");


				} if(action.equals("deletevoucherbeforeapprovel")){
					int ad_voucher_id=0;	

					try{
						ad_voucher_id=Integer.parseInt(request.getParameter("ad_voucher_id"));
					}catch(NumberFormatException n){
						n.printStackTrace();

					}


					int r=new VoucherDao().countVoucherByVoucherIdStatus("Pending", ad_voucher_id);
					if(r==0){
						response.getWriter().print("Voucher Not Deleted May Be Approved...!");	
					}else if(r==1){	

						new FdTrxDao().deleteFdTrxByVoucherID(ad_voucher_id);
						new MemberShareDao().deleteMemberShareByVoucherId(ad_voucher_id);	
						new LoanTrxDetailDao().deleteLoanTrxDetail(ad_voucher_id);			
						new LoanTrxDao().deleteLoanTrx(ad_voucher_id);			
						new FinalPayDao().deleteFinalPay(ad_voucher_id);
						new ThriftTrxDao().deleteThriftTrx(ad_voucher_id);
						new ExcessTrxDao().deleteExcessByVoucher(ad_voucher_id);
						new TransactionDao().deleteTransactionbyvoucherid(ad_voucher_id);
						new TempTranxDao().deleteTempTranxByvNo(ad_voucher_id);		
						VoucherBean dv= new VoucherBean();		
						dv.setAd_voucher_id(ad_voucher_id);
						new VoucherDao().deleteVoucher(dv);
						response.getWriter().print("Voucher deleted successfully ...!");


					}else{
						response.getWriter().print("Voucher Not Deleted May Be Approved...!");
					}





				}else if(action.equals("printchk")){

					int ad_voucher_id=0;
					String vtype=request.getParameter("vtype");
					String cheque_bank=request.getParameter("cheque_bank");
					String date=  request.getParameter("chk_date");
					double instrument_amt=Double.parseDouble(request.getParameter("instrument_amt"));
                    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                    Date d1=null;
                    try{
                    	d1=sdf.parse(date);
                    }catch(ParseException p){
                    	p.printStackTrace();
                    }
					
					String chk_no=request.getParameter("chk_no");
					String name=request.getParameter("name");

					try{
						ad_voucher_id = Integer.parseInt(request.getParameter("ad_voucher_id"));


					}catch(Exception e){
						e.printStackTrace();
					}

					VoucherBean vbn = new VoucherBean();

					vbn.setAd_voucher_id(ad_voucher_id);
					vbn.setInstrument_no(chk_no);
					vbn.setInstrument_date(d1);
					vbn.setInstrument_from(cheque_bank);
					vbn.setInstrument_amt(instrument_amt);
					vbn.setVfrom(name);
					vbn.setVtype(vtype);
					new VoucherDao().updatedVoucherForCheque(vbn);


					response.sendRedirect("view_voucher_cheque.jsp");

				}else if (action.equals("voucheredit")){

					int ad_voucher_id=0;
					
					
					String status = request.getParameter("status");
					String v_amt = request.getParameter("v_amt");
					String voucher_type=request.getParameter("voucher_type");	
					String trx_no = request.getParameter("trx_no");
					String trx_date = request.getParameter("trx_date");
					String vfrom = request.getParameter("vfrom");
					String vtype = request.getParameter("vtype");
					String ins_form=request.getParameter("ins_form");
					String ins_no=request.getParameter("ins_no");
					String date=  request.getParameter("ins_date");
					String description=request.getParameter("description");
					double instrument_amt=Double.parseDouble(request.getParameter("instrument_amt"));
                    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                    Date d1=null;
                    VoucherBean vbn = new VoucherBean();
                    try{
                    	d1=sdf.parse(date);
                    	ad_voucher_id = Integer.parseInt(request.getParameter("ad_voucher_id"));
                    	vbn.setTrx_no(Integer.parseInt(trx_no));
                    	vbn.setTrx_date(sdf.parse(trx_date));
                    }catch(ParseException p){
                    	p.printStackTrace();
                    }catch (Exception e) {
						e.printStackTrace();
					}
					
					vbn.setStatus(status);	
					vbn.setVfrom(vfrom);
					vbn.setAd_voucher_id(ad_voucher_id);
					vbn.setVtype(vtype);
					vbn.setInstrument_from(ins_form);
					vbn.setInstrument_no(ins_no);
					vbn.setInstrument_date(d1);
					vbn.setInstrument_amt(instrument_amt);
					vbn.setVamt(Double.parseDouble(v_amt));
					vbn.setVoucher_type(voucher_type);
					vbn.setUpdatedby(user.getAd_user_id());
					vbn.setUpdated(new java.util.Date());
					vbn.setDescription(description);
					int i=new VoucherDao().updatedVoucher1(vbn);
					if(i>0){
					AppMessage[0] = "alert-success";
					AppMessage[1] = "Voucher Updated Successfully...!!";
					
					session.setAttribute("AppMessage", AppMessage);
					Gson gson = new Gson(); 
					String json = gson.toJson(lstMap); 
					response.setContentType("application/json");
					response.getWriter().print(json);
					response.sendRedirect("ad_voucher_edit.jsp?ad_voucher_id="+ad_voucher_id);
				
				
				}else{
					AppMessage[0] = "alert-info";
					AppMessage[1] = "Please Try Again...!!";
					session.setAttribute("AppMessage", AppMessage);
					
					response.sendRedirect("ad_voucher_edit.jsp?ad_voucher_id="+ad_voucher_id);
				}
					

				}else if(action.equals("calculatethriftinterest")){
					SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
					Date d=(Date)request.getSession().getAttribute("openday");
					Date maxDate=new ThriftTrxDao().getThriftTrxMaxDate();
					int no=0;
					if(d.getTime()>maxDate.getTime()){
					
					
					VoucherBean vbean = new VoucherBean();					
					vbean.setCreatedby(user.getAd_user_id());
					vbean.setUpdatedby(user.getAd_user_id());
					vbean.setDescription("Manual Voucher");
					vbean.setStatus("Pending");
					vbean.setVamt(0.0);
					vbean.setVfrom("Thrift Interest");
					//bean.setVno(vno);
					vbean.setVtype("System");
					vbean.setVoucher_type("Thrift Interest");
					vbean.setIsactive(true);
					vbean.setInstrument_from("NA");
					vbean.setInstrument_no("NA");
					
					vbean.setInstrument_date(d);					
					vbean.setTrx_date(d);
					VoucherDao vdao=new VoucherDao();
					no=vdao.getMaxtrxNo();					
					vbean.setTrx_no(no);				
					VoucherBean  bn = new VoucherDao().addVoucher(vbean);
					
					
					ThriftRoiBean thrift=new ThriftRoiDao().getThriftRoiMaxId();
					ArrayList<MemberRegistrationBean> member=new MemberRegistrationDao().getAllActiveRegularMember();
					
					if(member.isEmpty()!=true){
						for(MemberRegistrationBean mbean:member){
							int ad_member_id=mbean.getAd_member_id();
							ThriftViewBean tvb=new ThriftTrxDao().getMaxThriftInterestDate(ad_member_id);
							if(tvb==null){
								tvb=new ThriftTrxDao().getMaxThriftOpeningDate(ad_member_id);
							}
			   		
					double interest=0.0,balance=0.0,prv_balance=0.0; 
					int i=0;
					double int_total=0.0;
					int tot_days=0;
					Date first=null,second=null;
					
					
					if(tvb.getTrx_date()!=null){
						first=tvb.getTrx_date();
						prv_balance=tvb.getBalance();
					}
					
					double tot_balance=0.0;
					ArrayList<ThriftViewBean> thriftlist=new ThriftTrxDao().getAllThriftDetailByMemberIdAndDate(ad_member_id,first);
					if(thriftlist.isEmpty()!=true){
						for(ThriftViewBean bean:thriftlist){
							second=bean.getTrx_date();
							//prv_balance=bean.getBalance_amt();
							long diff = second.getTime() - first.getTime();
							long diffDays = diff / (24 * 60 * 60 * 1000);
							tot_days+=diffDays;
							tot_balance=prv_balance*diffDays;
							balance=bean.getBalance();
							interest =(tot_balance*thrift.getRoi())/36500.0;
							first=second;
							prv_balance=balance;
							int_total+=interest;
							
						interest=0;
						}
						
						}
					
					if(d!=null ){

						second=d;
						
						long diff = second.getTime() - first.getTime();
						long diffDays = diff / (24 * 60 * 60 * 1000);
						tot_days+=diffDays;
						tot_balance=prv_balance*diffDays;
						//balance=bean.getBalance_amt();
						interest =(tot_balance*thrift.getRoi())/36500.0;
						int_total+=interest;
						first=second;
						prv_balance=balance;
						
					}
					int_total=new BigDecimal(int_total).setScale(0, RoundingMode.CEILING).doubleValue();
					//System.out.println("Member : "+mbean.getName()+" total interest"+int_total);
					
					if(int_total!=0 && int_total>0){

							TransactionBean trxb  = new TransactionBean();
							trxb.setCreatedby(user.getAd_user_id());
							trxb.setUpdatedby(user.getAd_user_id());
							trxb.setAd_voucher_id(bn.getAd_voucher_id());
							trxb.setTrx_date(d);
							trxb.setAd_member_id(mbean.getAd_member_id());
							trxb.setAd_account_id(142);	
							trxb.setNarration("Interest On Thrift-"+sdf.format(d));
							trxb.setDramt(int_total);
							trxb.setCramt(0);
							new TransactionDao().addTransaction(trxb);
							
				
							TransactionBean trxb1  = new TransactionBean();
							trxb1.setCreatedby(user.getAd_user_id());
							trxb1.setUpdatedby(user.getAd_user_id());
							trxb1.setAd_voucher_id(bn.getAd_voucher_id());	
							trxb1.setTrx_date(d);
							trxb1.setAd_member_id(mbean.getAd_member_id());
							trxb1.setAd_account_id(133);
							trxb1.setNarration("Interest On Thrift Date-"+sdf.format(d));
							trxb1.setDramt(0);
							trxb1.setCramt(int_total);
							new TransactionDao().addTransaction(trxb1);
							
							
							ThriftTrxBean thriftBean=new ThriftTrxBean();
							thriftBean.setAd_member_id(mbean.getAd_member_id());
							thriftBean.setAd_voucher_id(bn.getAd_voucher_id());
							thriftBean.setCreatedby(user.getAd_user_id());
							thriftBean.setUpdatedby(user.getAd_user_id());
							thriftBean.setStatus("Pending");
							thriftBean.setIsactive(false);
							thriftBean.setThrift_amt(0);
							thriftBean.setTrx_date(d);
							thriftBean.setThrift_int(int_total);
							thriftBean.setBalance(balance+int_total);

							new ThriftTrxDao().addThriftTrx(thriftBean);
					
					}
					interest=0;
					int_total=0;
						}
					
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Thrift interest Posted Successfully...!!";
						
						session.setAttribute("AppMessage", AppMessage);
						Gson gson = new Gson(); 
						String json = gson.toJson(lstMap); 
						response.setContentType("application/json");
						response.getWriter().print(json);
						response.sendRedirect("interestonthrift.jsp?no="+no);
					}
					
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Thrift interest cannot be post in current system date...!!";
						lstMap.put("message","error");
						lstMap.put("errorMessage","Thrift interest cannot be post in current system date...!");
						session.setAttribute("AppMessage", AppMessage);
					}
					
					Gson gson = new Gson(); 
					String json = gson.toJson(lstMap); 
					response.setContentType("application/json");
					response.getWriter().print(json);
					response.sendRedirect("interestonthrift.jsp?no="+no);
			

				}else if(action.equals("updateVoucher")){
					try{
					VoucherBean bean=new VoucherBean();
					String bank=request.getParameter("chk_bank");
					String ins_no=request.getParameter("chk_no");
					String date=  request.getParameter("chk_date");
					double instrument_amt=Double.parseDouble(request.getParameter("chk_amt"));
                    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                    Date d1=null;
                    try{
                    	d1=sdf.parse(date);
                    }catch(ParseException p){
                    	p.printStackTrace();
                    }
                    int ad_voucher_id=0;
					try{
						ad_voucher_id = Integer.parseInt(request.getParameter("ad_voucher_id"));


					}catch(Exception e){
						e.printStackTrace();
					}
                    bean.setInstrument_amt(instrument_amt);
                    bean.setInstrument_date(d1);
                    bean.setInstrument_no(ins_no);
                    bean.setInstrument_from("Loan");
                   	bean.setUpdatedby(user.getAd_user_id());
                   	bean.setAd_voucher_id(ad_voucher_id);
                   	bean.setVtype("Cheque");
                   	bean.setVfrom(bank);
                   	int i=new VoucherDao().updatedVoucherForCheque(bean);
					
                   	if (i > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Inserted!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
                   response.setContentType("text/plain");
                   response.getWriter().print("Cheque Detail Updated Successfully...!!");
				}catch(Exception e){
					AppMessage[0] = "alert-info";
					AppMessage[1] = "Sorry please try again latter!";
					response.setContentType("text/plain");
	                response.getWriter().print("Something went wrong please try again ...!!");
				}
				}else if(action.equals("fundtransfertopnlaccount")){
					try{
					SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
					Date d=(Date)request.getSession().getAttribute("openday");
					String opng_date="01/04/2016";
					Date opening=null;
					try{
					opening=sdf.parse(opng_date);
					}catch(ParseException p){
						p.printStackTrace();
					}
					int no=0;
					VoucherBean vbean = new VoucherBean();					
					vbean.setCreatedby(user.getAd_user_id());
					vbean.setUpdatedby(user.getAd_user_id());
					vbean.setDescription("Manual Voucher");
					vbean.setStatus("Pending");
					vbean.setVamt(0.0);
					vbean.setVfrom("Fund Transfer");
					//bean.setVno(vno);
					vbean.setVtype("System");
					vbean.setVoucher_type("Fund Transfer");
					vbean.setIsactive(true);
					vbean.setInstrument_from("NA");
					vbean.setInstrument_no("NA");
					
					vbean.setInstrument_date(d);					
					vbean.setTrx_date(d);
					VoucherDao vdao=new VoucherDao();
					no=vdao.getMaxtrxNo();					
					vbean.setTrx_no(no);				
					VoucherBean  bn = new VoucherDao().addVoucher(vbean);
					
					double deb_amount=0.0,cre_amount=0.0,balance=0.0;;
					//transfer all profit head to p&l account
					ArrayList<LedgerAccountBean> llist=new LedgerAccountDao().getLedgerAccountType(3);
					if(llist.isEmpty()!=true){
						for(LedgerAccountBean bean:llist){
							
					TransactionBean incomeAccountList=new TransactionDao().getLedgerBalBetweendates_2(bean.getAd_account_id(), opening ,d);
					
					if(incomeAccountList!=null){
						
						
							deb_amount+=incomeAccountList.getDramt();
							cre_amount+=incomeAccountList.getCramt();
							balance=cre_amount-deb_amount;
							if(balance>0){
							TransactionBean trxb1  = new TransactionBean();
							trxb1.setCreatedby(user.getAd_user_id());
							trxb1.setUpdatedby(user.getAd_user_id());
							trxb1.setAd_voucher_id(bn.getAd_voucher_id());	
							trxb1.setTrx_date(d);
							trxb1.setAd_member_id(0);
							trxb1.setAd_account_id(9);
							trxb1.setNarration("Fund Transfer Date-"+sdf.format(d));
							trxb1.setDramt(0);
							trxb1.setCramt(balance);
							new TransactionDao().addTransaction(trxb1);
							
							trxb1  = new TransactionBean();
							trxb1.setCreatedby(user.getAd_user_id());
							trxb1.setUpdatedby(user.getAd_user_id());
							trxb1.setAd_voucher_id(bn.getAd_voucher_id());	
							trxb1.setTrx_date(d);
							trxb1.setAd_member_id(0);
							trxb1.setAd_account_id(bean.getAd_account_id());
							trxb1.setNarration("Fund Transfer-"+sdf.format(d));
							trxb1.setDramt(balance);
							trxb1.setCramt(0);
							new TransactionDao().addTransaction(trxb1);
							}
							
						}
					deb_amount=0.0;
					cre_amount=0.0;
					balance=0.0;
					}
					}
					//transfer all excess head to p&l account
					
					 llist=new LedgerAccountDao().getLedgerAccountType(4);
					if(llist.isEmpty()!=true){
						for(LedgerAccountBean bean:llist){
							
					TransactionBean expenseAccountList=new TransactionDao().getLedgerBalBetweendates_2(bean.getAd_account_id(), opening ,d);
					
						if(expenseAccountList!=null)
						{
							deb_amount+=expenseAccountList.getDramt();
							cre_amount+=expenseAccountList.getCramt();
							balance=cre_amount-deb_amount;
							if(balance>0){
							TransactionBean trxb1  = new TransactionBean();
							trxb1.setCreatedby(user.getAd_user_id());
							trxb1.setUpdatedby(user.getAd_user_id());
							trxb1.setAd_voucher_id(bn.getAd_voucher_id());	
							trxb1.setTrx_date(d);
							trxb1.setAd_member_id(0);
							trxb1.setAd_account_id(9);
							trxb1.setNarration("Fund Transfer Date-"+sdf.format(d));
							trxb1.setDramt(balance);
							trxb1.setCramt(0);
							new TransactionDao().addTransaction(trxb1);
							
							trxb1  = new TransactionBean();
							trxb1.setCreatedby(user.getAd_user_id());
							trxb1.setUpdatedby(user.getAd_user_id());
							trxb1.setAd_voucher_id(bn.getAd_voucher_id());	
							trxb1.setTrx_date(d);
							trxb1.setAd_member_id(0);
							trxb1.setAd_account_id(bean.getAd_account_id());
							trxb1.setNarration("Fund Transfer-"+sdf.format(d));
							trxb1.setDramt(0);
							trxb1.setCramt(balance);
							new TransactionDao().addTransaction(trxb1);
							}
							
						}
						deb_amount=0.0;
						cre_amount=0.0;
						balance=0.0;
					}
					}
					}catch(Exception e){
						e.printStackTrace();
						AppMessage[0] = "alert-error";
						AppMessage[1] = "Please try again...!!";
						response.sendRedirect("homepage.jsp");
						
					}
					AppMessage[0] = "alert-info";
					AppMessage[1] = "Fund Transfer Successfully...!!";
					response.sendRedirect("homepage.jsp");
					
				}
					
					
					
				
			}
		}catch(Exception e){
			e.printStackTrace();

		}

	}

}
