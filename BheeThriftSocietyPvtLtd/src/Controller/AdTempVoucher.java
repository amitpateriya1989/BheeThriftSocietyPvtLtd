package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.DataFormatException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import Model.Bean.LedgerAccountBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.TempTranxBean;
import Model.Bean.UserBean;
import Model.Bean.VoucherBean;
import Model.Dao.LedgerAccountDao;
import Model.Dao.TempTranxDao;
import Model.Dao.VoucherDao;

/**
 * Servlet implementation class AdTempVoucher
 */
@WebServlet("/AdTempVoucher")
public class AdTempVoucher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TempTranxDao dao=null;
    private UserBean user=null;
    public AdTempVoucher() {
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
		dao=new TempTranxDao();
		
		boolean isactive=true;
		String action=request.getParameter("action");
		if(action!=null){
		
		HttpSession session=request.getSession(false);
		user=(UserBean)session.getAttribute("userbean");
			
		if (action.equals("edit")){
			TempTranxBean bean=new TempTranxBean();
			LedgerAccountBean ledger=new LedgerAccountBean();
			ledger.setAd_account_id(Integer.parseInt(request.getParameter("ad_account_id")));
			
			bean.setLedger(ledger);
			MemberRegistrationBean member=new MemberRegistrationBean();
			member.setAd_member_id(0);
			bean.setMember(member);
			VoucherBean voucher=new VoucherBean();
			voucher.setAd_voucher_id(Integer.parseInt(request.getParameter("ad_voucher_id")));
			bean.setVoucher(voucher);
			bean.setAd_trx_temp_id(Integer.parseInt(request.getParameter("ad_trx_temp_id")));
			bean.setCreated(new java.util.Date());
			bean.setCreatedby(user.getAd_user_id());
			bean.setUpdated(new java.util.Date());
			bean.setUpdatedby(user.getAd_user_id());
			bean.setIsactive(true);
			bean.setDramt(Double.parseDouble(request.getParameter("dramt")));
			bean.setCramt(Double.parseDouble(request.getParameter("cramt")));
			bean.setNarration(request.getParameter("narration"));
			try{
				Date d=(Date) session.getAttribute("openday");
				bean.setTrx_date(d);
			}catch(Exception p){
				
			}
			dao.updateTempTranx(bean);	
					
			}else if(action.equals("insert")){
				
				TempTranxBean bean=new TempTranxBean();
				LedgerAccountBean ledger=new LedgerAccountBean();
				MemberRegistrationBean member=new MemberRegistrationBean();
				try{
				ledger.setAd_account_id(Integer.parseInt(request.getParameter("ad_account_id")));
				if(ledger.getAd_account_id()==131){
					member.setAd_member_id(0);
				}else{
				member.setAd_member_id(Integer.parseInt(request.getParameter("ad_member_id")));
				}
				}catch(NumberFormatException n){
					System.out.println(request.getParameter("ad_account_id"));
					n.printStackTrace();
				}
				bean.setLedger(ledger);
				bean.setMember(member);
				
				bean.setCreated(new java.util.Date());
				bean.setCreatedby(user.getAd_user_id());
				bean.setUpdated(new java.util.Date());
				bean.setUpdatedby(user.getAd_user_id());
				bean.setIsactive(true);
				bean.setDramt(Double.parseDouble(request.getParameter("dramt")));
				bean.setCramt(Double.parseDouble(request.getParameter("cramt")));
				bean.setNarration(request.getParameter("narration"));
				try{
					Date d=(Date) session.getAttribute("openday");
					bean.setTrx_date(d);
				}catch(Exception p){
					
				}
				int ad_voucher_id=new VoucherDao().getMaxVoucherId();
				VoucherBean voucher=new VoucherBean();
				voucher.setAd_voucher_id(ad_voucher_id);
				bean.setVoucher(voucher);
				
				dao.addTempTranx(bean);	
				response.setContentType("text/plain");
				PrintWriter print=response.getWriter();
				print.print(ad_voucher_id);
				
			}
		}
		
		
		}catch(Exception e){
			
		}
}
}
