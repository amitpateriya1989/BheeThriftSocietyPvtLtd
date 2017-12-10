package Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MasterValidation.Validation;
import Model.Bean.AccountSubGroupBean;
import Model.Bean.LedgerAccountBean;
import Model.Bean.TransactionBean;
import Model.Bean.UserBean;
import Model.Bean.VoucherBean;
import Model.Dao.AccountSubGroupDao;
import Model.Dao.LedgerAccountDao;
import Model.Dao.TransactionDao;
import Model.Dao.VoucherDao;

/**
 * Servlet implementation class AdLedgerAccount
 */
@WebServlet("/AdLedgerAccount")
public class AdLedgerAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LedgerAccountDao dao=null;
    private UserBean user=null;
    private LedgerAccountBean bean=null;
    private Validation valid = null;
    public AdLedgerAccount() {
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
			dao=new LedgerAccountDao();
			boolean isactive=true;
			valid = new Validation();
			String action=request.getParameter("action");
			
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				int ad_ac_group_id=0;
				int ad_ac_subgroup_id=0;
				int ad_account_id=0;
				int ad_ac_type_id=0;
				int ad_bank_id=0;
				int ad_branch_id=0;
				int ad_account_no=0;
				double opening=0.0;
				
				
				
				
				
				
				
				
				int rowsUpdated = 0;
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
			    
			    if(action!=null){
			    	String ac_name ="";
			    	String ac_for="";
			    	String ac_no="";
			    	String type="";
			    	String address="" ;
			    	String pincode ="";
			    	String phone ="";
			    	String mobile ="";
			    	String bank_ac_no="" ;
			    	String ifsc_code ="";
			    	String tele_phone_no="" ;
			    	String pan_no ="";
			    	String sale_tax_no="" ;
			    	String cst_no="";
			    	String ac_group_id="";
			    	String ac_subgroup_id="";
			    	String ac_type_id="";
			    	String account_id="";		
			    	String opening_type="";
			    	String balance="";
			    	
				if(action.equals("edit")){
					String id=request.getParameter("ad_account_id");
					
						if(valid.validNotEmpty(id)==false && valid.validDigits(id) == false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Invalid Account Id!";
							session.setAttribute("AppMessage", AppMessage);
							response.sendRedirect("edit_account.jsp");
						}else{
							try{
								ad_account_id=Integer.parseInt(id);
								response.sendRedirect("edit_account_detail.jsp?ad_account_id="+ad_account_id);
							}catch(NumberFormatException n){
								n.printStackTrace();
							}
							response.sendRedirect("edit_account.jsp");
						
						}
										
				}else if(action.equals("update")){
					
					ac_name =request.getParameter("ac_name");
					ac_no=request.getParameter("ac_no");
			    	ac_for=request.getParameter("ac_for");
			    	type=request.getParameter("type");
			    	address=request.getParameter("address");
			    	pincode =request.getParameter("pincode");
			    	phone =request.getParameter("phone");
			    	mobile =request.getParameter("mobile");
			    	bank_ac_no=request.getParameter("bank_ac_no");
			    	ifsc_code =request.getParameter("ifsc_code");
			    	tele_phone_no=request.getParameter("tele_phone_no");
			    	pan_no =request.getParameter("pan_no");
			    	sale_tax_no=request.getParameter("sale_tax_no");
			    	cst_no=request.getParameter("cst_no");
					ac_group_id=request.getParameter("ad_ac_group_id");					
					ac_subgroup_id=request.getParameter("ad_ac_subgroup_id");
					ac_type_id=request.getParameter("ad_ac_type_id");
					account_id=request.getParameter("ad_account_id");
					
					if(valid.validNotEmpty(ac_type_id)==false && valid.validDigits(ac_type_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Account Type Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("edit_account.jsp");
					}else if(valid.validNotEmpty(ac_group_id)==false && valid.validDigits(ac_group_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Account Group Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("edit_account.jsp");
					}else if(valid.validNotEmpty(ac_subgroup_id)==false && valid.validDigits(ac_subgroup_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Account Sub Group Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("edit_account.jsp");
					}else if(valid.validNotEmpty(account_id)==false && valid.validDigits(account_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Account Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("edit_account.jsp");
					}else if(valid.validNotEmpty(ac_no)==false && valid.validDigits(ac_no) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Account No.  ..!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("edit_account.jsp");
					}else if(valid.validNotEmpty(ac_name)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Account Name is required field!";
					}else if(valid.validNotEmpty(ac_for)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Account For is required field!";
					}else if(valid.validNotEmpty(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status is required field!";
					}else if(valid.validAlphaNum(request.getParameter("status"))==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Status Id should be numeric!";
					}else{
			    	try{
					
						ad_ac_type_id=Integer.parseInt(request.getParameter("ad_ac_type_id"));
						ad_ac_group_id=Integer.parseInt(request.getParameter("ad_ac_group_id"));
						ad_ac_subgroup_id=Integer.parseInt(request.getParameter("ad_ac_subgroup_id"));
						ad_account_id=Integer.parseInt(request.getParameter("ad_account_id"));
						ad_bank_id=Integer.parseInt(request.getParameter("ad_bank_id"));
						ad_branch_id=Integer.parseInt(request.getParameter("ad_branch_id"));
						ad_account_no=Integer.parseInt(ac_no);
						
					}catch(NumberFormatException ne){
						System.out.println("Error in group,sub group,account Id Format");
						ne.printStackTrace();
					}
					
					if(ad_ac_group_id!=0 && ad_ac_subgroup_id!=0 && ad_account_id!=0 && ad_account_no!=0)
					{
						
						isactive=Boolean.parseBoolean("isactive");	
						bean=new LedgerAccountBean();
						bean.setAc_name(ac_name);
						bean.setAc_no(Integer.parseInt(ac_no.trim()));
						bean.setAc_for(ac_for);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						bean.setAd_ac_type_id(ad_ac_type_id);
						bean.setAd_ac_group_id(ad_ac_group_id);
						bean.setAd_ac_subgroup_id(ad_ac_subgroup_id);
						bean.setAd_account_id(ad_account_id);
						bean.setAc_no(ad_account_no);
						bean.setAd_bank_id(ad_bank_id);
						bean.setAd_branch_id(ad_branch_id);
						bean.setAddress(address);
						bean.setBank_ac_no(bank_ac_no);
						bean.setCst_no(cst_no);
						bean.setIfsc_code(ifsc_code);
						bean.setMobile(mobile);
						bean.setPan_no(pan_no);
						bean.setTele_phone_no(tele_phone_no);
						bean.setPincode(pincode);
						bean.setPhone(phone);
						bean.setSale_tax_no(sale_tax_no);
						bean.setType(type);
						
			
						rowsUpdated= dao.updateLedgerAccount(bean);
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					
					}//end validation
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("edit_account.jsp");
					}
				}else if(action.equals("insert")){
				
					ac_name =request.getParameter("ac_name");
					ac_no=request.getParameter("ac_no");
			    	ac_for=request.getParameter("ac_for");
			    	type=request.getParameter("type");
			    	address=request.getParameter("address");
			    	pincode =request.getParameter("pincode");
			    	phone =request.getParameter("phone");
			    	mobile =request.getParameter("mobile");
			    	bank_ac_no=request.getParameter("bank_ac_no");
			    	ifsc_code =request.getParameter("ifsc_code");
			    	tele_phone_no=request.getParameter("tele_phone_no");
			    	pan_no =request.getParameter("pan_no");
			    	sale_tax_no=request.getParameter("sale_tax_no");
			    	cst_no=request.getParameter("cst_no");
					ac_group_id=request.getParameter("ad_ac_group_id");					
					ac_subgroup_id=request.getParameter("ad_ac_subgroup_id");
					ac_type_id=request.getParameter("ad_ac_type_id");
					opening_type=request.getParameter("opening_type");
					balance=request.getParameter("opening_amt");
					
					if(valid.validNotEmpty(ac_type_id)==false && valid.validDigits(ac_type_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Account Type Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("edit_account.jsp");
					}else if(valid.validNotEmpty(ac_group_id)==false && valid.validDigits(ac_group_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Account Group Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("edit_account.jsp");
					}else if(valid.validNotEmpty(ac_subgroup_id)==false && valid.validDigits(ac_subgroup_id) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Account Sub Group Id!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("edit_account.jsp");
					}else if(valid.validNotEmpty(ac_no)==false && valid.validDigits(ac_no) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Account No.  ..!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("edit_account.jsp");
					}else if(valid.validNotEmpty(ac_name)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Account Name is required field!";
					}else if(valid.validNotEmpty(ac_for)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Account For is required field!";
					}else if(valid.validNotEmpty(opening_type)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Opening Type!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("edit_account.jsp");
					}else if(valid.validNotEmpty(balance)==false && valid.validDigits(balance) == false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Invalid Opening Balance!";
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("edit_account.jsp");
					}else{
			    	try{
					
						ad_ac_type_id=Integer.parseInt(request.getParameter("ad_ac_type_id"));
						ad_ac_group_id=Integer.parseInt(request.getParameter("ad_ac_group_id"));
						ad_ac_subgroup_id=Integer.parseInt(request.getParameter("ad_ac_subgroup_id"));
						ad_bank_id=Integer.parseInt(request.getParameter("ad_bank_id"));
						ad_branch_id=Integer.parseInt(request.getParameter("ad_branch_id"));
						ad_account_no=Integer.parseInt(ac_no);
						opening=Double.parseDouble(balance);
					}catch(NumberFormatException ne){
						System.out.println("Error in group,sub group,account Id Format");
						ne.printStackTrace();
					}
					
					if(ad_ac_group_id!=0 && ad_ac_subgroup_id!=0 && ad_account_no!=0)
					{
						int no=0;
						
						bean=new LedgerAccountBean();
						bean.setAc_name(ac_name);
						bean.setAc_no(Integer.parseInt(ac_no.trim()));
						bean.setAc_for(ac_for);
						bean.setUpdatedby(user.getAd_user_id());
						bean.setAd_ac_type_id(ad_ac_type_id);
						bean.setAd_ac_group_id(ad_ac_group_id);
						bean.setAd_ac_subgroup_id(ad_ac_subgroup_id);
						bean.setAc_no(ad_account_no);
						bean.setAd_bank_id(ad_bank_id);
						bean.setAd_branch_id(ad_branch_id);
						bean.setAddress(address);
						bean.setBank_ac_no(bank_ac_no);
						bean.setCst_no(cst_no);
						bean.setIfsc_code(ifsc_code);
						bean.setMobile(mobile);
						bean.setPan_no(pan_no);
						bean.setTele_phone_no(tele_phone_no);
						bean.setPincode(pincode);
						bean.setPhone(phone);
						bean.setSale_tax_no(sale_tax_no);
						bean.setType(type);
						
						rowsUpdated= dao.addLedgerAccount(bean);
						
						VoucherBean vbean = new VoucherBean();					
						vbean.setCreatedby(user.getAd_user_id());
						vbean.setUpdatedby(user.getAd_user_id());
						vbean.setDescription("Opening Balance");
						vbean.setStatus("Approved");
						vbean.setVamt(0);
						vbean.setVfrom("Opening");
						//bean.setVno(vno);
						vbean.setVtype("SYSTEM");
						vbean.setVoucher_type("Opening Balance");
						vbean.setIsactive(true);
						vbean.setInstrument_from("SYSTEM");
						vbean.setInstrument_no("000000");	
						SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
						Date d=(Date)request.getSession().getAttribute("openday");
						vbean.setInstrument_date(d);
						vbean.setTrx_date(d);
						VoucherDao vdao=new VoucherDao();					
						no=vdao.getMaxtrxNo();					
						vbean.setTrx_no(no);					
						VoucherBean  bn = new VoucherDao().addVoucher(vbean);
						
					
							
							TransactionBean tbean2 = new TransactionBean();					
							tbean2.setCreatedby(user.getAd_user_id());
							tbean2.setUpdatedby(user.getAd_user_id());
							tbean2.setAd_voucher_id(bn.getAd_voucher_id());		
							tbean2.setAd_account_id(rowsUpdated);
							
							tbean2.setTrx_date(d);
							tbean2.setAd_member_id(0);
							if(opening_type.equals("Debit")){
							tbean2.setDramt(opening);
							}else{
							tbean2.setCramt(opening);	
							}
							rowsUpdated+=new TransactionDao().addTransaction(tbean2);
							
						}
						
						
						
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Inserted!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					
					}//end validation
					
					}
								
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_account.jsp");
					
				}//end of insert
			 
			}catch(Exception e){
				e.printStackTrace();
			}
			
		
	}//end of doPost() method
	
	
}//end of servlet