package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import Model.Bean.GenrateShareNoBean;
import Model.Bean.LedgerAccountBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.MemberShareBean;
import Model.Bean.ShareBean;
import Model.Bean.TransactionBean;
import Model.Bean.UserBean;
import Model.Bean.VoucherBean;
import Model.Dao.GenrateShareNoDao;
import Model.Dao.MemberRegistrationDao;
import Model.Dao.MemberShareDao;
import Model.Dao.ShareDao;
import Model.Dao.TransactionDao;
import Model.Dao.VoucherDao;

/**
 * Servlet implementation class AdMemberShare
 */
@WebServlet("/AdMemberShare")
public class AdMemberShare extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberShareDao dao=null;
    private UserBean user=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdMemberShare() {
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
			HashMap<String,String> lstMap = new HashMap<String,String>();
			String[] AppMessage = new String[2];
			AppMessage[0] = "alert-info";
			AppMessage[1] = "welcome";
			int no =0;
			HttpSession session=request.getSession(false);
			user=(UserBean)session.getAttribute("userbean");
		    session.setAttribute("AppMessage", AppMessage);
		    
			dao=new MemberShareDao();
			boolean isactive=false;
			String action=request.getParameter("action");
			String ad_member_id=request.getParameter("ad_member_id_1");
			String no_of_share=request.getParameter("no_of_share");
			String share_amt=request.getParameter("share_amt");
			String recived_by=request.getParameter("vtype");
			String chk_dd_date=request.getParameter("chk_dd_date");
			String chk_dd_no=request.getParameter("chk_dd_no");
			String date_of_allocation=request.getParameter("date_of_allocation");
			String bank=request.getParameter("bank");
			String branch=request.getParameter("branch");
			String status=request.getParameter("status");
			String voucher_id=request.getParameter("ad_voucher_id");
			String loan_id=request.getParameter("loan_trx_id");
			String share_id=request.getParameter("ad_member_share_id");
			String no_from=request.getParameter("share_no_form");
			String no_to=request.getParameter("share_no_to");
			String batch_no=request.getParameter("batch_no");
			String amt_in_words=request.getParameter("amt_in_words");
			if(action.equals("insert")){
				
				if(ad_member_id !="" && no_of_share !="" && share_amt !="" && recived_by !="" && chk_dd_date !="" && chk_dd_no !="" && bank !="" && branch !="" ){
				
					
					int cnt=new VoucherDao().getCountsharepending("new_share");
					
					if(cnt!=0){
						//response.getWriter().print("<script>alert('One Share Voucher is  Pending First Approved then try.... ');</script>");
						lstMap.put("message","error");
						lstMap.put("errorMessage","One Share Voucher is  Pending First Approved then try.... .");
						AppMessage[0] = "alert-info";
						AppMessage[1] = "One Share Voucher is  Pending First Approved then try.... .!!";
						
					}else{
					 
					 
						
					MemberShareBean bean=new MemberShareBean();
					bean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					VoucherBean vbean = new VoucherBean();					
					vbean.setCreatedby(user.getAd_user_id());
					vbean.setUpdatedby(user.getAd_user_id());
					vbean.setDescription("new_share");
					vbean.setStatus("Pending");
					vbean.setVamt(Double.parseDouble(share_amt));
					vbean.setVfrom(branch+" "+bank);
					//bean.setVno(vno);
					vbean.setVtype(recived_by);
					vbean.setVoucher_type("Share Allotment");
					vbean.setIsactive(true);
					vbean.setInstrument_from("Cheque");
					vbean.setInstrument_no(chk_dd_no);					
					vbean.setAmt_in_words(amt_in_words);
					String d1=request.getParameter("chk_dd_date");
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
					
									
					
					try{
						
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				    Date dt1 =  df.parse(date_of_allocation);  
					bean.setDate_of_allocation(dt1);
					}catch(ParseException p){
						p.printStackTrace();
					}
					bean.setTrx_by(recived_by);
					try{
					DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
				    Date dt =  df1.parse(chk_dd_date);  
					bean.setChk_dd_date(dt);
					}catch(ParseException p){
						p.printStackTrace();
					}
					try{
						bean.setChk_dd_no(Integer.parseInt(chk_dd_no));
					}catch(NumberFormatException ne){
						ne.printStackTrace();
					}
					
					bean.setShare_amt(Double.parseDouble(share_amt));
					bean.setAmt_in_words(amt_in_words);
					bean.setQnt_of_share(Integer.parseInt(no_of_share));					
					MemberShareBean bean2=new MemberShareDao().getMemberShareMaxId();
					MemberShareBean bean1=new MemberShareDao().getMemberShareBatchshareNo(bean2.getAd_member_share_id());
					
					int share_no_to=0;
					
					if(bean2!=null){					
					bean.setShare_no_form(bean1.getShare_no_to()+1);					
					 share_no_to =bean1.getShare_no_to()+Integer.parseInt(no_of_share);					
					}
					bean.setStatus(status);
					bean.setShare_no_to(share_no_to);					
					bean.setBatch_no(bean1.getBatch_no()+1);
					bean.setIsactive(isactive);
					bean.setAd_voucher_id(bn.getAd_voucher_id());
					bean.setTrx_by(branch+" "+bank);
					
					dao.addMemberShare(bean);		

					/// dr in bank ac
					
					TransactionBean tbean = new TransactionBean();
					//System.out.println("voucher id= "+bn.getAd_voucher_id());
					
					tbean.setCreatedby(user.getAd_user_id());
					tbean.setUpdatedby(user.getAd_user_id());
					tbean.setAd_voucher_id(bn.getAd_voucher_id());
					
					tbean.setAd_account_id(131);					
					
					tbean.setTrx_date(d);					
									
					tbean.setAd_member_id(0);
								
					tbean.setDramt(Double.parseDouble(share_amt));					
					new TransactionDao().addTransaction(tbean);
					
					
					//cr in mem loan ac
					TransactionBean tbean2 = new TransactionBean();
					
					tbean2.setCreatedby(user.getAd_user_id());
					tbean2.setUpdatedby(user.getAd_user_id());
					tbean2.setAd_voucher_id(bn.getAd_voucher_id());		
					tbean2.setAd_account_id(132);
					
					tbean2.setTrx_date(d);
					tbean2.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
					
					tbean2.setCramt(Double.parseDouble(share_amt));
					int count=new TransactionDao().addTransaction(tbean2);
					if(count>0){
					lstMap.put("message","true");
					lstMap.put("successMessage","Member Successfully Registered and send for approval.");
					AppMessage[0] = "alert-success";
					AppMessage[1] = "Share send for approval successfully !";
					//response.sendRedirect("new_share.jsp");
					}else{
						lstMap.put("message","error");
						lstMap.put("errorMessage","Sorry contact to you admin.");
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry contact to you admin.";
						
					}
					
					session.setAttribute("AppMessage", AppMessage);
				}
				}				
				Gson gson = new Gson(); 
				String json = gson.toJson(lstMap); 
				response.setContentType("application/json");
				response.getWriter().print(json);
				session.setAttribute("AppMessage", AppMessage);
				response.sendRedirect("new_share.jsp?no="+no);
		}else if(action.equals("getBatchShareno")){
				
				//int no_of_share1=Integer.parseInt(request.getParameter("no_of_share"));
				
				
				MemberShareBean bean=new MemberShareDao().getMemberShareMaxId();
				//System.out.println(bean.getAd_member_share_id());
				
				MemberShareBean bean1=new MemberShareDao().getMemberShareBatchshareNo(bean.getAd_member_share_id());
				
				
				//Convert Java Object to Json
				Gson gson = new Gson();
				JsonObject myObject=new JsonObject();
				JsonElement element = gson.toJsonTree(bean1);
				if(bean==null){
					myObject.addProperty("success", false);
				}
				else {
					myObject.addProperty("success", true);
				} 
				myObject.add("ShareInfo", element);
				response.setContentType("application/json");
				response.getWriter().print(myObject);
				//System.out.println(myObject.toString());
				
			}else if(action.equals("list")){
			
				int ad_mem_id =Integer.parseInt(request.getParameter("ad_member_id"));
				
				
				try{
					ArrayList<MemberShareBean> lst=new ArrayList<MemberShareBean>();
					lst=new MemberShareDao().getMemberShareByMemberId(ad_mem_id);
					//Convert Java Object to Json
					Gson gson = new Gson();
					JsonElement element = gson.toJsonTree(lst, new TypeToken<List<MemberShareBean>>() {}.getType());
					JsonArray jsonArray = element.getAsJsonArray();
					String listData=jsonArray.toString();
					//Return Json in the format required by jTable plugin
					//listData="{\"Result\":\"OK\",\"Records\":"+listData+"}"; 

					response.setContentType("application/json");
					response.getWriter().print(listData);
					//System.out.println(listData);
					}catch(Exception ex){
						String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace()+"}";
						PrintWriter pw=response.getWriter();
						pw.print(error);
					}
			}else if(action.equals("edit")){
				String id=request.getParameter("ad_member_share_id");
				int ad_member_share_id=0;
				if(id!=null){
					ad_member_share_id=Integer.parseInt(id);
					if(new MemberShareDao().getMemberShareBatchshareNo(ad_member_share_id)!=null){
					response.sendRedirect("ad_share_detail_edit.jsp?ad_member_share_id="+ad_member_share_id);
					}else{
						lstMap.put("message","error");
						lstMap.put("errorMessage","Invalid Share Id");
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Invalid Share Id";
						
					}
				}else{
					lstMap.put("message","error");
					lstMap.put("errorMessage","Share id is null");
					AppMessage[0] = "alert-info";
					AppMessage[1] = "Share id is null";
					
				}
				Gson gson = new Gson(); 
				String json = gson.toJson(lstMap); 
				response.setContentType("application/json");
				response.getWriter().print(json);
				session.setAttribute("AppMessage", AppMessage);
				response.sendRedirect("ad_view_share_detail.jsp");
				
			}else if(action.equals("update")){
				MemberShareBean bean=new MemberShareBean();
				int result=0;
				try{
					
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					    Date dt1 =  df.parse(date_of_allocation);
					    isactive=Boolean.parseBoolean(request.getParameter("isactive"));
						bean.setDate_of_allocation(dt1);
						bean.setAd_member_id(Integer.parseInt(ad_member_id.trim()));
						bean.setUpdatedby(user.getAd_user_id());
						bean.setLoan_trx_id(Integer.parseInt(loan_id));
						bean.setAd_voucher_id(Integer.parseInt(voucher_id));
						bean.setAd_member_share_id(Integer.parseInt(share_id));
						bean.setShare_no_form(Integer.parseInt(no_from));
						bean.setShare_no_to(Integer.parseInt(no_to));
						bean.setShare_amt(Double.parseDouble(share_amt));
						bean.setQnt_of_share(Integer.parseInt(no_of_share));					
						bean.setBatch_no(Integer.parseInt(batch_no));
					}catch(NumberFormatException ne){
						ne.printStackTrace();
					}catch(ParseException p){
						p.printStackTrace();
					}catch(Exception p){
						p.printStackTrace();
					}
					
					bean.setStatus(status);
					bean.setIsactive(isactive);
					bean.setTrx_by(recived_by.trim());
					
					result=new MemberShareDao().updateMemberShare(bean);
					if(result>0){
						lstMap.put("message","true");
						lstMap.put("successMessage","Member Share Updated Successfully ...!!");
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Member Share Updated Successfully ...!!";
						
						}else{
							lstMap.put("message","error");
							lstMap.put("errorMessage","Sorry contact to you admin.");
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry contact to you admin.";
							
						}
					session.setAttribute("AppMessage", AppMessage);	
					Gson gson = new Gson(); 
					String json = gson.toJson(lstMap); 
					response.setContentType("application/json");
					response.getWriter().print(json);
					
					response.sendRedirect("ad_share_detail_view.jsp");
			}else if(action.equals("delete")){
				int result=0;
				String id=request.getParameter("ad_member_share_id");
				int ad_member_share_id=0;
				if(id!=null){
					ad_member_share_id=Integer.parseInt(id);
					MemberShareBean bean=new MemberShareBean();
					bean.setAd_member_share_id(ad_member_share_id);
					result=new MemberShareDao().deleteMemberShare(bean);
					if(result>0){
						lstMap.put("message","true");
						lstMap.put("successMessage","Member Share Deleted Successfully ...!!");
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Member Share Deleted Successfully ...!!";
						
						}else{
							lstMap.put("message","error");
							lstMap.put("errorMessage","Sorry contact to you admin.");
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry contact to you admin.";
							
						}
						
					Gson gson = new Gson(); 
					String json = gson.toJson(lstMap); 
					response.setContentType("application/json");
					response.getWriter().print(json);
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_share_detail_view.jsp");
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
	
	

}
