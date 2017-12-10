package Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Model.Bean.FDInterestChequeBean;
import Model.Bean.FdRoiBean;
import Model.Bean.FdTrxBean;
import Model.Bean.FdViewBean;
import Model.Bean.FdrediptionBean;
import Model.Bean.LedgerAccountBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.MemberWitnessChkBean;
import Model.Bean.TransactionBean;
import Model.Bean.UserBean;
import Model.Bean.VoucherBean;
import Model.Dao.FDInterestChequeDao;
import Model.Dao.FdRoiDao;
import Model.Dao.FdTrxDao;
import Model.Dao.FdrediptionDao;
import Model.Dao.MemberWitnessChkDao;
import Model.Dao.TransactionDao;
import Model.Dao.VoucherDao;


/**
 * Servlet implementation class AdFdTrx
 */
@WebServlet("/AdFdTrx")
public class AdFdTrx extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FdTrxDao dao=null;
    private UserBean user=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdFdTrx() {
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
		
		try{
			
			dao=new FdTrxDao();
			
			String action=request.getParameter("action");
			int rowsUpdated = 0;
			
			HttpSession session=request.getSession(false);
			user=(UserBean)session.getAttribute("userbean");
			
			String[] AppMessage = new String[2];
			AppMessage[0] = "alert-info";
			AppMessage[1] = "welcome";
		    session.setAttribute("AppMessage", AppMessage);
			
			
			if(action.equals("fdrediption")){
				String rede_date=request.getParameter("rede_date");
				
				int ad_fd_trx_id=0;
				
				try{
					ad_fd_trx_id=Integer.parseInt(request.getParameter("fd_no_rede"));
				}catch(NumberFormatException n){
					n.printStackTrace();
				}
				
				FdViewBean beanfd=new FdTrxDao().getFdDetailByFDId(ad_fd_trx_id);
				
				DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
			    Date rede_dt =  df1.parse(rede_date);  
			    Date maturity_dt=beanfd.getMaturity_date();
			    Date opening_date =beanfd.getOpening_date();
			    
			    long diff = rede_dt.getTime() - maturity_dt.getTime();
			    long diffDays = diff / (24 * 60 * 60 * 1000);
			 
			   // System.out.println(diffDays);
			    if(diffDays>0){
			    	
			    	double maturity_amt=beanfd.getFd_amt()*Math.pow((1+(beanfd.getRoi()/100)/beanfd.getFrequency()),(beanfd.getTime_period()/12)*beanfd.getFrequency());
			    	double intr=maturity_amt-beanfd.getFd_amt();
			    	double one_month_intr=intr/beanfd.getTime_period();
			    	double one_day_intr=one_month_intr/30;
			    	double total_intr=Math.round(intr+(one_day_intr*diffDays));
			    	
			    	String Maturity="Post Maturity";
			    	String json1 = new Gson().toJson(Maturity); 
					String json2 = new Gson().toJson(beanfd.getFd_amt()); 
					String json3 = new Gson().toJson(beanfd.getRoi()); 
					String json4 = new Gson().toJson(total_intr); 
					String json5 = new Gson().toJson(total_intr+beanfd.getFd_amt()); 
					response.setContentType("application/json"); 
					response.setCharacterEncoding("utf-8"); 
					String bothJson = "["+json1+","+json2+","+json3+","+json4+","+json5+"]"; //Put both objects in an array of 2 elements
					response.getWriter().write(bothJson);
					System.out.print(bothJson);
			    	
			    	
			    	
			    }else if(diffDays<0){
			    	
			    	Calendar startCalendar = new GregorianCalendar();
			    	startCalendar.setTime(rede_dt);
			    	Calendar endCalendar = new GregorianCalendar();
			    	endCalendar.setTime(opening_date);

			    	int diffYear = startCalendar.get(Calendar.YEAR)-endCalendar.get(Calendar.YEAR) ;
			    	int diffMonth = diffYear * 12 +  startCalendar.get(Calendar.MONTH) - endCalendar.get(Calendar.MONTH) ;
			    	int min_time_period =new FdRoiDao().getFdRoiminId(beanfd.getAd_fd_category_id() , beanfd.getAd_type_of_fd_id());
			    	
			    	if(diffMonth<min_time_period){
			    		
				    	String Maturity="Pre Maturity";
				    	String json1 = new Gson().toJson(Maturity); 
						String json2 = new Gson().toJson(beanfd.getFd_amt()); 
						String json3 = new Gson().toJson(0.00); 
						String json4 = new Gson().toJson(0.00); 
						String json5 = new Gson().toJson(beanfd.getFd_amt()); 
						response.setContentType("application/json"); 
						response.setCharacterEncoding("utf-8"); 
						String bothJson = "["+json1+","+json2+","+json3+","+json4+","+json5+"]"; //Put both objects in an array of 2 elements
						response.getWriter().write(bothJson);
						System.out.print(bothJson);
			    		
			    		
			    	}else{
			    		
			    		double intret= new FdRoiDao().getFdRoiIntrestId(beanfd.getAd_fd_category_id() , beanfd.getAd_type_of_fd_id(), diffMonth );
			    		double maturity_amt=beanfd.getFd_amt()*Math.pow((1+(beanfd.getRoi()/100)/beanfd.getFrequency()),(diffMonth/12)*beanfd.getFrequency());
			    		double intr=(maturity_amt-beanfd.getFd_amt());
				    	String Maturity="Pre Maturity";
				    	String json1 = new Gson().toJson(Maturity); 
						String json2 = new Gson().toJson(beanfd.getFd_amt()); 
						String json3 = new Gson().toJson(intret); 
						String json4 = new Gson().toJson(Math.round(intr)); 
						String json5 = new Gson().toJson(Math.round(maturity_amt)); 
						response.setContentType("application/json"); 
						response.setCharacterEncoding("utf-8"); 
						String bothJson = "["+json1+","+json2+","+json3+","+json4+","+json5+"]"; //Put both objects in an array of 2 elements
						response.getWriter().write(bothJson);
						System.out.print(bothJson);
			    			    		
			    	}
			    	
			    				    	
			    }else if(diffDays==0){
			    	double maturity_amt=beanfd.getFd_amt()*Math.pow((1+(beanfd.getRoi()/100)/beanfd.getFrequency()),(beanfd.getTime_period()/12)*beanfd.getFrequency());
			    	
			    	double intr=maturity_amt-beanfd.getFd_amt();
			    	String Maturity="Maturity";
			    	String json1 = new Gson().toJson(Maturity); 
					String json2 = new Gson().toJson(beanfd.getFd_amt()); 
					String json3 = new Gson().toJson(beanfd.getRoi()); 
					String json4 = new Gson().toJson(intr); 
					String json5 = new Gson().toJson(maturity_amt); 
					response.setContentType("application/json"); 
					response.setCharacterEncoding("utf-8"); 
					String bothJson = "["+json1+","+json2+","+json3+","+json4+","+json5+"]"; //Put both objects in an array of 2 elements
					response.getWriter().write(bothJson);
					System.out.print(bothJson);
			    }
			   
			//	
			}
			//reinsert
			if(action.equals("reinsert")){
				rowsUpdated=0;
				String ad_member_id=request.getParameter("ad_member_id");
				String ad_type_of_fd_id=request.getParameter("re_ad_type_of_fd_id");
				String ad_fd_category_id=request.getParameter("re_ad_fd_category_id");
				
				String fd_amt=request.getParameter("fdamt");
				String t_fdint = request.getParameter("tfdint");
				String re_fd_amt = request.getParameter("re_fd_amt");
				String opening_date=request.getParameter("re_opening_date");
				String time_period=request.getParameter("re_time_period");
				String maturity_date=request.getParameter("re_maturity_date");
				String interest_rate=request.getParameter("re_interest_rate");
				String intrest_amt=request.getParameter("re_intrest_amt");
				String maturity_amt=request.getParameter("re_maturity_amt");
				String partpayment = request.getParameter("partpayment");
				
				if(ad_member_id !="" && ad_type_of_fd_id !="" && ad_fd_category_id !=""  &&  time_period !=null ){
					
					double fdamt = Double.parseDouble(fd_amt);
					double tfdint = Double.parseDouble(t_fdint);
					
					int gid=0;
					//ad_voucher
					VoucherBean vbean = new VoucherBean();					
					vbean.setCreatedby(user.getAd_user_id());
					vbean.setUpdatedby(user.getAd_user_id());
					vbean.setDescription("renew_fd");
					vbean.setStatus("Pending");
					vbean.setVamt(fdamt+tfdint);
					vbean.setVfrom("FD Renewal");
				//	bean.setVno(vno);
					vbean.setVtype("FD Renewal");
					vbean.setVoucher_type("fd renewal");
					vbean.setIsactive(true);
					vbean.setInstrument_from("FD Renewal");
					//vbean.setInstrument_no("");					
					String d1=request.getParameter("re_opening_date");
					Date d2=null;
					if(d1!=null){
						try{
						SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
						d2=sdf.parse(d1);
						}catch(ParseException p){
							p.printStackTrace();
						}
					}
							
					vbean.setInstrument_date(d2);;					
					Date d=(Date)request.getSession().getAttribute("openday");
					vbean.setTrx_date(d);
					
					VoucherDao vdao=new VoucherDao();
					
					
					int no=vdao.getMaxtrxNo();					
					vbean.setTrx_no(no);
					
					VoucherBean  bn = new VoucherDao().addVoucher(vbean);		
					if(bn!=null){
						rowsUpdated++;
					}
					
					if(partpayment.equals("no")){
						
						/// dr in fd ac
						
						TransactionBean tbean = new TransactionBean();
						System.out.println("voucher id= "+bn.getAd_voucher_id());
						
						tbean.setCreatedby(user.getAd_user_id());
						tbean.setUpdatedby(user.getAd_user_id());
							
						tbean.setAd_voucher_id(bn.getAd_voucher_id());
						
						tbean.setAd_account_id(143);
						tbean.setTrx_date(d);
						tbean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
						tbean.setDramt(Double.parseDouble(fd_amt));
						
						if(new TransactionDao().addTransaction(tbean)>0){
							rowsUpdated++;
						};
						
						
						//dr in mem fd int ac
						TransactionBean tbean2 = new TransactionBean();
						
						tbean2.setCreatedby(user.getAd_user_id());
						tbean2.setUpdatedby(user.getAd_user_id());
						tbean2.setAd_voucher_id(bn.getAd_voucher_id());
								
						tbean2.setAd_account_id(185);
						
						tbean2.setTrx_date(d);
						tbean2.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
						
						tbean2.setDramt(tfdint);
						
						if(new TransactionDao().addTransaction(tbean2)>0){
							rowsUpdated++;
						};
						
						//cr in mem fd int ac
						 tbean2 = new TransactionBean();
						
						tbean2.setCreatedby(user.getAd_user_id());
						tbean2.setUpdatedby(user.getAd_user_id());
						tbean2.setAd_voucher_id(bn.getAd_voucher_id());		
						tbean2.setAd_account_id(143);
						tbean2.setTrx_date(d);
						tbean2.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
						
						tbean2.setCramt(tfdint+fdamt);
						
						if(new TransactionDao().addTransaction(tbean2)>0){
							rowsUpdated++;
						}
						
						}else if(partpayment.equals("yes")){
						
						
						/// dr in fd ac
						
						TransactionBean tbean = new TransactionBean();
						System.out.println("voucher id= "+bn.getAd_voucher_id());
						
						tbean.setCreatedby(user.getAd_user_id());
						tbean.setUpdatedby(user.getAd_user_id());
						tbean.setAd_voucher_id(bn.getAd_voucher_id());	
						tbean.setAd_account_id(143);
						tbean.setTrx_date(d);
						tbean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
						tbean.setDramt(Double.parseDouble(fd_amt));
						
						if(new TransactionDao().addTransaction(tbean)>0){
							rowsUpdated++;
						};
						
						
						//dr in mem fd int ac
						TransactionBean tbean2 = new TransactionBean();
						
						tbean2.setCreatedby(user.getAd_user_id());
						tbean2.setUpdatedby(user.getAd_user_id());
						tbean2.setAd_voucher_id(bn.getAd_voucher_id());		
						tbean2.setAd_account_id(185);
						
						tbean2.setTrx_date(d);
						tbean2.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
						
						tbean2.setDramt(tfdint);
						if(new TransactionDao().addTransaction(tbean2)>0){
							rowsUpdated++;
						}
						
						//cr bank
						 tbean2 = new TransactionBean();
							
							tbean2.setCreatedby(user.getAd_user_id());
							tbean2.setUpdatedby(user.getAd_user_id());
							tbean2.setAd_voucher_id(bn.getAd_voucher_id());		
							tbean2.setAd_account_id(131);
							
							tbean2.setTrx_date(d);
							tbean2.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
							
							tbean2.setCramt(tfdint);
							if(new TransactionDao().addTransaction(tbean2)>0){
								rowsUpdated++;
							};
							
						
						//cr in mem fd int ac
						 tbean2 = new TransactionBean();
						
						tbean2.setCreatedby(user.getAd_user_id());
						tbean2.setUpdatedby(user.getAd_user_id());
						tbean2.setAd_voucher_id(bn.getAd_voucher_id());		
						tbean2.setAd_account_id(143);
						
						tbean2.setTrx_date(d);
						tbean2.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
						
						tbean2.setCramt(fdamt);
						if(new TransactionDao().addTransaction(tbean2)>0){
							rowsUpdated++;
						};
						
					
					}
					
									
					FdTrxBean bean=new FdTrxBean();		
									
					bean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					try{
						
						
					int ad_fd_trx_id=Integer.parseInt(request.getParameter("fd_no"));
						
					FdTrxBean fd_no=new FdTrxDao().getFdTrxById(ad_fd_trx_id)	;
					new FdTrxDao().updateFdTrx(fd_no);
						
					bean.setFd_no(fd_no.getFd_no());
					bean.setFd_amt(Double.parseDouble(re_fd_amt));
					bean.setAd_fd_roi_id(Integer.parseInt(time_period));
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				    Date dt1 =  df.parse(opening_date.trim());  
					bean.setOpening_date(dt1);
					
					DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
				    Date dt2 =  df1.parse(maturity_date);  
					bean.setMaturity_date(dt2);				
					
					
					}catch(ParseException p){
						p.printStackTrace();
					}
					bean.setAd_voucher_id(bn.getAd_voucher_id());
					
					dao.addFdTrx(bean);		
					
					if (rowsUpdated >=4) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "FD Renewal successfully send for approval..!! ";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					session.setAttribute("AppMessage", AppMessage);
					
					response.sendRedirect("fd_trx.jsp?no="+no);
				}else{
					
				}
				
				
				
		}
			
			
			
			if(action.equals("insert")){
				
				String ad_member_id=request.getParameter("ad_member_id_nfd");
				String ad_type_of_fd_id=request.getParameter("ad_type_of_fd_id");
				String ad_fd_category_id=request.getParameter("ad_fd_category_id");
				String fd_amt=request.getParameter("fd_amt");
				String opening_date=request.getParameter("opening_date");
				String time_period=request.getParameter("time_period");
				String maturity_date=request.getParameter("maturity_date");
				String interest_rate=request.getParameter("interest_rate");
				String intrest_amt=request.getParameter("intrest_amt");
				String maturity_amt=request.getParameter("maturity_amt");
				int no=0;
				
				if(ad_member_id !="" && ad_type_of_fd_id !="" && ad_fd_category_id !=""  &&  time_period !=null ){
					
					//HttpSession session=request.getSession(false);
					//user=(UserBean)session.getAttribute("userbean");
					VoucherBean vbean = new VoucherBean();					
					vbean.setCreatedby(user.getAd_user_id());
					vbean.setUpdatedby(user.getAd_user_id());
					vbean.setDescription("new_fd");
					vbean.setStatus("Pending");
					vbean.setVamt(Double.parseDouble(fd_amt));
					vbean.setVfrom("New FD");
					//bean.setVno(vno);
					vbean.setVtype("Cheque");
					vbean.setVoucher_type("New FD");
					vbean.setIsactive(true);
					vbean.setInstrument_from(request.getParameter("branch_name"));
					vbean.setInstrument_no(request.getParameter("cheque_no"));					
						
					String d1=request.getParameter("cheque_date");
					Date d2=null;
					if(d1!=null){
						try{
						SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
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
					if(bn.getAd_voucher_id()>0){
						rowsUpdated = 1;
					}
				
					int gid=0;
					
					FdTrxBean bean=new FdTrxBean();
					bean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					
					
					try{
						
					bean.setFd_no(new FdTrxDao().getMaxFdNo());
					bean.setFd_amt(Double.parseDouble(fd_amt));
					bean.setAd_fd_roi_id(Integer.parseInt(time_period));
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				    Date dt1 =  df.parse(opening_date.trim());  
					bean.setOpening_date(dt1);
					
					DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
				    Date dt2 =  df1.parse(maturity_date);  
					bean.setMaturity_date(dt2);				
					
					
					}catch(ParseException p){
						p.printStackTrace();
					}
					bean.setAd_voucher_id(bn.getAd_voucher_id());
					
					int chkTransactionValid =0;
					chkTransactionValid =  dao.addFdTrx(bean);
					
					if(chkTransactionValid >0){
						rowsUpdated = rowsUpdated + 1;
						
						int totalChkInfo =Integer.parseInt(request.getParameter("totalChkInfo"));
						for(int i=1;i<totalChkInfo;i++){

							FDInterestChequeBean mwchkbean = new FDInterestChequeBean();
							mwchkbean.setCreatedby(user.getAd_user_id());
							mwchkbean.setUpdatedby(user.getAd_user_id());
							mwchkbean.setCheque_no(request.getParameter("guar_cheque_no"+i));
							mwchkbean.setBranch(request.getParameter("guar_branch_name"+i));
							Date date1=null;
							try{
								SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
								date1=format.parse(request.getParameter("guar_cheque_date"+i));
							}catch(ParseException p){
								p.printStackTrace();
							}
							mwchkbean.setCheque_date(date1);
							mwchkbean.setCheque_amt(Double.parseDouble(request.getParameter("guar_cheque_amt"+i)));
							mwchkbean.setAd_fd_trx_id(chkTransactionValid);
							mwchkbean.setIsactive(true);
							new FDInterestChequeDao().addFDInterestCheque(mwchkbean);
						}
					}
					
					/// dr in bank ac
					
					TransactionBean tbean = new TransactionBean();
					System.out.println("voucher id= "+bn.getAd_voucher_id());
					
					tbean.setCreatedby(user.getAd_user_id());
					tbean.setUpdatedby(user.getAd_user_id());
					
					tbean.setAd_voucher_id(bn.getAd_voucher_id());
					tbean.setAd_account_id(131);					
					
					tbean.setTrx_date(d);					
					tbean.setAd_member_id(0);
					
					tbean.setDramt(Double.parseDouble(fd_amt));
					new TransactionDao().addTransaction(tbean);
					
					//cr in mem loan ac
					TransactionBean tbean2 = new TransactionBean();					
					tbean2.setCreatedby(user.getAd_user_id());
					tbean2.setUpdatedby(user.getAd_user_id());
					
					tbean2.setAd_voucher_id(bn.getAd_voucher_id());
					tbean2.setAd_account_id(143);
					
					tbean2.setTrx_date(d);
					tbean2.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
					
					tbean2.setCramt(Double.parseDouble(fd_amt));
					
					
					if(new TransactionDao().addTransaction(tbean2)>0){
						rowsUpdated = rowsUpdated + 1;
					}
					
					

					
					
				}//end validation
				
				if (rowsUpdated == 3) {
					AppMessage[0] = "alert-success";
					AppMessage[1] = "FD created successfully send for approval..!!";
				}else{
					AppMessage[0] = "alert-info";
					AppMessage[1] = "Sorry please try again latter!";
				}
				session.setAttribute("AppMessage", AppMessage);
				response.sendRedirect("fd_trx.jsp?no="+no);
		}//end insert method
			
		if(action.equals("fdrediptioninsert")){
			
			rowsUpdated=0;
			int no=0;
			double rede_fd_amt = Double.parseDouble(request.getParameter("rede_fd_amt"));
			double	total_int = Double.parseDouble(request.getParameter("total_int"));
			String ad_member_id= request.getParameter("ad_member_id");
			
			VoucherBean vbean = new VoucherBean();					
			vbean.setCreatedby(user.getAd_user_id());
			vbean.setUpdatedby(user.getAd_user_id());
			vbean.setDescription("rediption_fd");
			vbean.setStatus("Pending");
			vbean.setVamt(Double.parseDouble(request.getParameter("v_amt")));
			vbean.setVfrom(request.getParameter("ins_from"));
			//bean.setVno(vno);
			vbean.setVtype(request.getParameter("v_type"));
			vbean.setVoucher_type(request.getParameter("voucher_type"));
			vbean.setIsactive(true);
		    vbean.setInstrument_from(request.getParameter("ins_from"));
		    vbean.setInstrument_no(request.getParameter("ins_no"));	
		    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		    Date ins_date=null;
		    try{
		    	ins_date=sdf.parse(request.getParameter("ins_date"));
		    }catch(ParseException p){
		    	p.printStackTrace();
		    }
			vbean.setInstrument_date(ins_date);
			String d1=request.getParameter("rede_date");
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
			
			
			if(bn!=null){
			/// dr in bank ac
		     	
			TransactionBean tbean = new TransactionBean();
		//	System.out.println("voucher id= "+bn.getAd_voucher_id());
			
			tbean.setCreatedby(user.getAd_user_id());
			tbean.setUpdatedby(user.getAd_user_id());
		
			tbean.setAd_voucher_id(bn.getAd_voucher_id());
			tbean.setAd_account_id(143);					
			
			tbean.setTrx_date(d);					
					
			tbean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
						
			tbean.setDramt(rede_fd_amt);
			
			
			if(new TransactionDao().addTransaction(tbean)>0){
				rowsUpdated++;
			}
			
			TransactionBean tbean2 = new TransactionBean();	
			tbean2.setCreatedby(user.getAd_user_id());
			tbean2.setUpdatedby(user.getAd_user_id());
			tbean2.setAd_voucher_id(bn.getAd_voucher_id());
			
			tbean2.setAd_account_id(185);					
			
			tbean2.setTrx_date(d);					
		
			tbean2.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
					
			tbean2.setDramt(total_int);
			
			if(new TransactionDao().addTransaction(tbean2)>0){
				rowsUpdated++;
			}
			
			
			
			TransactionBean tbean3 = new TransactionBean();					
			tbean3.setCreatedby(user.getAd_user_id());
			tbean3.setUpdatedby(user.getAd_user_id());
			
			tbean3.setAd_voucher_id(bn.getAd_voucher_id());
			tbean3.setAd_account_id(131);
			
			tbean3.setTrx_date(d);
			tbean3.setAd_member_id(0);
			
			tbean3.setCramt(rede_fd_amt+total_int);		
			
			if(new TransactionDao().addTransaction(tbean3)>0){
				rowsUpdated++;
			}
			
			
			
			
			Date  mdate = sdf.parse(request.getParameter("rede_date"));
			int ad_fd_trx_id= Integer.parseInt(request.getParameter("fd_no_rede"));
			double rede_int_rate = Integer.parseInt(request.getParameter("rede_int_rate"));
			
			FdrediptionBean fdrbean = new FdrediptionBean();
			fdrbean.setCreatedby(user.getAd_user_id());
			fdrbean.setUpdatedby(user.getAd_user_id());
			fdrbean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
			fdrbean.setAd_fd_trx_id(ad_fd_trx_id);
			fdrbean.setFdamt(rede_fd_amt);
			fdrbean.setPayroi(total_int);
			fdrbean.setMaturitydate(mdate);
			fdrbean.setPayint(rede_int_rate);
			fdrbean.setTotalpayamt(rede_fd_amt+total_int);
			fdrbean.setAd_voucher_id(bn.getAd_voucher_id());
			fdrbean.setRediptiontype(request.getParameter("redeption_type"));
			
			if(new FdrediptionDao().addFdrediption(fdrbean)>0){
				rowsUpdated++;
			}
			
			FdTrxBean fdtb = new FdTrxBean();
			fdtb.setAd_fd_trx_id(ad_fd_trx_id);
			fdtb.setCreatedby(user.getAd_user_id());
			fdtb.setUpdatedby(user.getAd_user_id());
			
			if(new FdTrxDao().updateFdTrx(fdtb)>0){
				rowsUpdated++;
			}
			
			}
			if (rowsUpdated == 5) {
				AppMessage[0] = "alert-success";
				AppMessage[1] = "FD Redemption successfully send for approval..!!";
			}else{
				AppMessage[0] = "alert-info";
				AppMessage[1] = "Sorry please try again latter!";
			}
			session.setAttribute("AppMessage", AppMessage);
		
			response.sendRedirect("redeption_fd.jsp?no="+no);
		}
		if(action.equals("getfdbymem")){
			int ad_member_id=0;
			try{
				ad_member_id = Integer.parseInt(request.getParameter("ad_member_id"));
			}catch(NumberFormatException n){
				n.printStackTrace();
				
			}
			
			
		ArrayList<FdTrxBean> bean=	new FdTrxDao().getFdTrxByMemId(ad_member_id);
			Gson gson = new Gson();
			JsonObject myObject=new JsonObject();
			JsonElement element = gson.toJsonTree(bean);
			if(bean.isEmpty()){
				myObject.addProperty("success", false);
			}
			else {
				myObject.addProperty("success", true);
			} 
			myObject.add("FdInfo", element);
			response.setContentType("application/json");
			response.getWriter().print(myObject);
			System.out.print(myObject);
		}
		
		if(action.equals("getfdRedemptionbymem")){
			int ad_member_id=0;
			try{
				ad_member_id = Integer.parseInt(request.getParameter("ad_member_id"));
			}catch(NumberFormatException n){
				n.printStackTrace();
				
			}
			
			
		ArrayList<FdTrxBean> bean=	new FdTrxDao().getRenewalFdTrxByMemId(ad_member_id);
			Gson gson = new Gson();
			JsonObject myObject=new JsonObject();
			JsonElement element = gson.toJsonTree(bean);
			if(bean.isEmpty()){
				myObject.addProperty("success", false);
			}
			else {
				myObject.addProperty("success", true);
			} 
			myObject.add("FdInfo", element);
			response.setContentType("application/json");
			response.getWriter().print(myObject);
			System.out.print(myObject);
		}
		
		if(action.equals("getfdbyid")){
			int ad_fd_trx_id=0;
			String maturity_date=request.getParameter("maturity_date");
			try{
				ad_fd_trx_id = Integer.parseInt(request.getParameter("ad_fd_trx_id"));
			}catch(NumberFormatException n){
				n.printStackTrace();
				
			}
			
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date mdate = formatter.parse(maturity_date);
			FdTrxBean bean1=	new FdTrxDao().getFdTrxById(ad_fd_trx_id,mdate);
			FdRoiBean beanroi1 = new FdRoiBean();
			long day=0;
			int msg = 0;
			if(bean1.getAd_fd_trx_id()!=0){
			
			FdRoiBean beanroi= new FdRoiBean();
			beanroi.setAd_fd_roi_id(bean1.getAd_fd_roi_id());
			
			Date d1 = null;
			Date d2 = null;
			
			try {
			d1 = formatter.parse(maturity_date);
			d2 = bean1.getMaturity_date();//format.parse(dStop);

			

			long difference = d1.getTime() - d2.getTime();
			if(difference!=0){
			long seconds = difference / 1000;
			day=seconds/86400;
			
			}else{
				day=0;
			}

			} catch (Exception e) {
			e.printStackTrace();
			}
			
			
			
			 beanroi1 = new FdRoiDao().getFdRoiById(beanroi);
			
			}else{
			
				msg=1;
				
			}
			
			String json1 = new Gson().toJson(bean1); 
			String json2 = new Gson().toJson(beanroi1); 
			String json3 = new Gson().toJson(day); 
			String json4=new Gson().toJson(msg);
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json1+","+json2+","+json3+","+json4+"]"; //Put both objects in an array of 2 elements
			response.getWriter().write(bothJson);
			
			//System.out.print(bothJson);
			
			
		}
			
		if(action.equals("getfdbyid1")){
			int ad_fd_trx_id=0;
			try{
				ad_fd_trx_id = Integer.parseInt(request.getParameter("ad_fd_trx_id"));
			}catch(NumberFormatException n){
				n.printStackTrace();
				
			}
			
			FdViewBean bean1=	new FdTrxDao().getFdDetailByFDId(ad_fd_trx_id);
			
			if(bean1.getAd_fd_trx_id()!=0){
			
			
			
			String json1 = new Gson().toJson(bean1); 
			
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json1+"]"; //Put both objects in an array of 2 elements
			response.getWriter().write(bothJson);
			
			
			
			System.out.print(bothJson);
			}else{
			
				
			}
			
		}
		
		
		if(action.equals("getfdbymember")){
			
			try{
			int	ad_member_id=Integer.parseInt(request.getParameter("ad_member_id"));
			FdTrxBean fdtbn = new FdTrxBean();
			
			fdtbn.setAd_member_id(ad_member_id);
			
			ArrayList<FdTrxBean> fdbn = new FdTrxDao().getFdTrxByMemId(fdtbn);
			
			String json1 = new Gson().toJson(fdbn); 
			response.getWriter().write(json1);
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		if(action.equals("ad_date")){
			
			String opening_date = request.getParameter("opening_date");
			String time_period=request.getParameter("time_period");
			
			
			
			int fdroiid=0;
			
			try{
				fdroiid=Integer.parseInt(time_period);
				
			}catch(NumberFormatException n){
			n.printStackTrace();
			}
			
			FdRoiBean fdrbean = new FdRoiDao().getFdRoiById(fdroiid);
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date ldate=df.parse(opening_date);
			Calendar c = Calendar.getInstance();
			c.setTime(ldate);
			c.add(Calendar.MONTH, fdrbean.getTime_period());
		//	System.out.print(df.format(c.getTime()));
			response.getWriter().print(df.format(c.getTime()).trim());			
			
			//response.getWriter().print("success");
			
			
		}
		
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
	
	

}
