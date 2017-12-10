package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import Model.Bean.DayOpenBean;
import Model.Bean.DayOpenBean;
import Model.Bean.LedgerAccountBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.TransactionBean;
import Model.Bean.UserBean;
import Model.Bean.VoucherBean;
import Model.Dao.DayOpenDao;
import Model.Dao.DayOpenDao;
import Model.Dao.TransactionDao;
import Model.Dao.VoucherDao;

/**
 * Servlet implementation class AdDayOpen
 */
@WebServlet("/AdDayOpen")
public class AdDayOpen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DayOpenDao dao=null;
	private DayOpenBean bean=null;
    private UserBean user=null;
    Date od1=null;
    int rowsUpdated =0;
    String[] AppMessage = new String[2];
    HttpSession session=null;
    public AdDayOpen() {
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
try{
			
			
	boolean isactive=true;
	dao=new DayOpenDao();
	String action=request.getParameter("action");
	int ad_open_days_id=0;
	
					
	if(action!=null){
		 session=request.getSession(false);
		user=(UserBean)session.getAttribute("userbean");
		
		
		AppMessage[0] = "alert-info";
		AppMessage[1] = "welcome";
	    session.setAttribute("AppMessage", AppMessage);
					
		if(action.equals("edit")){
			String id=request.getParameter("ad_open_days_id");
			if(id!=null){
				response.sendRedirect("editDayOpen.jsp?ad_open_days_id="+id);
			}else{
				
				AppMessage[0] = "alert-info";
				AppMessage[1] = "Sorry please try again latter!";
				session.setAttribute("AppMessage", AppMessage);
				response.sendRedirect("editDayOpen.jsp?ad_open_days_id="+id);
			}
			
		}else if(action.equals("update")){
			String id=request.getParameter("ad_open_days_id");
			String date=request.getParameter("open_days");
			if(id!=null && date!=null){
				ad_open_days_id=Integer.parseInt(id);
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				Date open_date=null;
				try{
					open_date=sdf.parse(date);
				}catch(ParseException p){
					p.printStackTrace();
				}
				DayOpenBean bean=new DayOpenBean();
				bean.setAd_open_days_id(ad_open_days_id);
				bean.setOpen_days(open_date);
				bean.setUpdatedby(user.getAd_user_id());
				bean.setUpdated(new java.util.Date());
				rowsUpdated=new DayOpenDao().ChangeDayOpen(bean);
				if (rowsUpdated > 0) {
					AppMessage[0] = "alert-success";
					AppMessage[1] = "Day Change Succesfully !";
				}else{
					AppMessage[0] = "alert-info";
					AppMessage[1] = "Sorry please try again latter!";
				}
				session.setAttribute("openday",open_date);
				session.setAttribute("AppMessage", AppMessage);
				response.sendRedirect("ad_open_day.jsp");
			}else{
				
				AppMessage[0] = "alert-info";
				AppMessage[1] = "Sorry Enter Valid Data !";
				session.setAttribute("AppMessage", AppMessage);
				response.sendRedirect("ad_open_day.jsp");
			}
			
			
			
		} else if(action.equals("insertbetween")){
			
		//between day
			int counter =0;
			try{
				 counter = Integer.parseInt(request.getParameter("counter"));
			}catch( NumberFormatException n){
				n.printStackTrace();
			}
			
			if(counter>0 ){
				
				for(int i=1; i<=counter;i++){
				
				String open_days =request.getParameter("between_"+i);
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date od = df.parse(open_days);
				DayOpenBean bean=new DayOpenBean();
				bean.setOpen_days(od);
				bean.setOpening_status(false);
				bean.setClosing_status(false);
				bean.setCreatedby(user.getAd_user_id());
				bean.setUpdatedby(user.getAd_user_id());
				bean.setIsactive(false);
				bean.setRemark(request.getParameter("remark_"+i));
				new DayOpenDao().addDayOpen(bean);
				}
				
			}
			

			String open_days =request.getParameter("open_days");
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date od = df.parse(open_days);
			DayOpenBean bean=new DayOpenBean();
			bean.setOpen_days(od);
			bean.setOpening_status(true);
			bean.setClosing_status(false);
			bean.setCreatedby(user.getAd_user_id());
			bean.setUpdatedby(user.getAd_user_id());
			bean.setIsactive(isactive);
			dao.addDayOpen(bean);
			response.sendRedirect("logout.jsp");
			
		}else if(action.equals("insert")){
			
			String open_days =request.getParameter("open_days");
			String status=request.getParameter("status");
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date od = df.parse(open_days);
			System.out.println("date:"+od+"--status:"+status);
			if(status.equals("OPEN")){
				
				DayOpenBean bean1 = new DayOpenDao().chkDayOpen();
				
				if(bean1.getAd_open_days_id()!=0){
					//+
					response.getWriter().print("<script>alert('Day alredy open.... Date: "+bean1.getOpen_days()+"'); window.location.href='ad_open_days.jsp';</script> ");
					
				}else{
				
				
					DayOpenBean bean2 = new DayOpenDao().chkalreadyDayopenclose(od);
					
					if(bean2.getAd_open_days_id()!=0){
						
						response.getWriter().print("<script>alert('Day alredy Completed....'); window.location.href='ad_open_days.jsp';</script> ");
				
					}else{
						
						
						DayOpenBean bean3 = new DayOpenDao().getlastclosedday();						
						
						long millisDiff = od.getTime() - bean3.getOpen_days().getTime();
						double daysDiff = (double)millisDiff/(1000*60*60*24);

						System.out.print(daysDiff);
						
						
					if(daysDiff<1.0){
						response.getWriter().print("<script>alert('You Enter Before Date of Last Closing Day ,Please Enter Next Date of last Closing Day....'); window.location.href='ad_open_days.jsp';</script> ");
					}else if((int)daysDiff==1){
						
						
						DayOpenBean bean=new DayOpenBean();
						bean.setOpen_days(od);
						bean.setOpening_status(true);
						bean.setClosing_status(false);
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(isactive);
						dao.addDayOpen(bean);
						response.sendRedirect("dayopen.jsp");
						
					}else{
						response.sendRedirect("between_ad_open_days.jsp?closedate="+bean3.getOpen_days()+"&copndate="+open_days);
					}
					}
				}
			
			}
			if(status.equals("CLOSE")){
				
				
				DayOpenBean beanc1 = new DayOpenDao().chkDayClosed(od);
				
				if(beanc1.getAd_open_days_id()!=0){
					//+
					response.getWriter().print("<script>alert('Day alredy closed.... Date: "+beanc1.getOpen_days()+"'); window.location.href='ad_open_days.jsp';</script> ");
					
				}else{
					
					ArrayList<VoucherBean> list=new VoucherDao().getAllPendingVoucherForDay(od);
					if(list.isEmpty()!=false){
					
					DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
					 od1 = df1.parse(open_days);
					DayOpenBean bean=new DayOpenBean();
					bean.setOpen_days(od1);
					bean.setOpening_status(true);
					bean.setClosing_status(false);
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(isactive);	
					System.out.println("inside voucher pending");			
					dao.updateDayOpen(bean);
					intUpdate();
					response.sendRedirect("dayclose.jsp");
				        	
				   
					}else{
						AppMessage[0] = "alert-danger";
						AppMessage[1] = "Vouchers are Pending !!";
						response.sendRedirect("ad_open_day.jsp?no="+list.size()+" Vouchers are Pending..!!");
						
					}	
					
				}
				
		
		}
		}else if(action.equals("searchbydate")){
			
			
			
			
			String fdate=request.getParameter("fdate");
			String tdate=request.getParameter("tdate");
			
			DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
			Date fdt = df1.parse(fdate);
			Date tdt = df1.parse(tdate);
			
			
			
			try{
				List<DayOpenBean> lst=new ArrayList<DayOpenBean>();
				lst=new DayOpenDao().getAllDayOpen(fdt, tdt);
				//Convert Java Object to Json
				Gson gson = new Gson();
				JsonElement element = gson.toJsonTree(lst, new TypeToken<List<DayOpenBean>>() {}.getType());
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
			
			
		}
		
			
			
		//	
		
		

		
	}
	
					
				
			
			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}
	


public void intUpdate(){
	//Date date; // your date
    Calendar cal = Calendar.getInstance();
    cal.setTime(od1);
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);
	
    if(day==31 && month==02){
    	
    	cal.set((year-1), 3, 1); 
    	Date fdate=cal.getTime();
    	cal.set(year, 2, 31); 
    	Date tdate= cal.getTime();		    	
    	
    	double np= calculateNetProfit(fdate, tdate);				    	
    	double netprofit=0.0;
    	double netloss=0.0;	
    	
    	String bal_type=null;
    	if(np>0){
    		netprofit=np;
    		bal_type="Cr Bal";
    	}else if(np<0){
    		netloss=Math.abs(np);
    		bal_type="Dr Bal";
    	}
    	
    	
    
		int ad_account_id=10;
		
		String narration ="Net Profit Of The ";
		
		if(np!=0 &&  ad_account_id==10 && bal_type!=null){
			int adacid=ad_account_id;
		//	double balamt = netprofit;							
			
			VoucherBean vbean=new VoucherBean();							
			vbean.setCreatedby(user.getAd_user_id());
			vbean.setUpdatedby(user.getAd_user_id());
			vbean.setDescription("manual");
			vbean.setStatus("Approved");
			vbean.setVamt(Math.abs(np));
			vbean.setVfrom("");
			vbean.setVno("");
			vbean.setVtype("Adjestment");
			vbean.setIsactive(true);
			vbean.setCreated(new java.util.Date());
			vbean.setUpdated(new java.util.Date());
			vbean.setVoucher_type("Net Profit Adjestment");	
		//	Date d=(Date)request.getSession().getAttribute("openday");
			VoucherDao vdao=new VoucherDao();
			
			int no=vdao.getMaxtrxNo();
			vbean.setTrx_no(no);
			vbean.setTrx_date(od1);
			
			vbean= new VoucherDao().addVoucher(vbean);
			//vbean.getAd_voucher_id();
			TransactionBean tbean =new TransactionBean();
			
			tbean.setVoucher(vbean);
			tbean.setCreatedby(user.getAd_user_id());
			tbean.setUpdatedby(user.getAd_user_id());
			tbean.setIsactive(true);
			LedgerAccountBean lb = new LedgerAccountBean();
			lb.setAd_account_id(adacid);
			tbean.setLedger(lb);
			
			Calendar c = Calendar.getInstance(); 
			c.setTime(od1); 
			c.add(Calendar.DATE, 1);							
			tbean.setTrx_date(c.getTime());
			MemberRegistrationBean mrb = new MemberRegistrationBean();
			mrb.setAd_member_id(0);
			tbean.setMember(mrb);
			if(bal_type.equals("Cr Bal")){
				tbean.setCramt(netprofit);
				tbean.setDramt(0.00);
				
			}else if(bal_type.equals("Dr Bal")){
				tbean.setDramt(netloss);
				tbean.setCramt(0.00);
			}
			tbean.setNarration(narration +""+fdate+"-"+tdate);
			
			new TransactionDao().addTransaction(tbean);
		
			if (rowsUpdated > 0) {
				AppMessage[0] = "alert-success";
				AppMessage[1] = "Process succesfully completed!";
			}else{
				AppMessage[0] = "alert-info";
				AppMessage[1] = "Sorry please try again latter!";
			}
			session.setAttribute("AppMessage", AppMessage);
}	
}
}

public double calculateNetProfit(Date fdate,Date tdate){
	 
	// expenses ac
			  double t1=0.0;
			  double t2=0.0;
			
				ArrayList<TransactionBean> tb1 = new TransactionDao().getLedgerBalByTypeBetweendates(4, fdate, tdate);
				
				if(tb1!=null){	
					double bal=0;
					for(TransactionBean tbean1:tb1){	
						
							if(tbean1.getDramt()!=0.00)
							{
								
									bal=bal-tbean1.getDramt();
							}
							if(tbean1.getCramt()!=0.00)
							{
								
									bal=bal-tbean1.getCramt();
								
							}	
			 			t1=t1+bal; 
			 		}

			}
		

	//income
			  
				ArrayList<TransactionBean> tb2 = new TransactionDao().getLedgerBalByTypeBetweendates(3, fdate, tdate);
				if(tb2!=null){
		
					double bal=0;
					for(TransactionBean tbean1:tb2){	
						
						if(tbean1.getDramt()!=0.00)		{
					
							bal=bal-tbean1.getDramt();
						}
						if(tbean1.getCramt()!=0.00)		{
					
							bal=bal+tbean1.getCramt();
						}
				
				
						 t2=t2+bal; 
					}

				}


	//------------calculate net profit -----------

	double totalnetprofit=Math.abs(t2)-Math.abs(t1);
	return totalnetprofit;
}
}
