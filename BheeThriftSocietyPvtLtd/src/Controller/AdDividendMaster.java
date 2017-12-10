package Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.DataFormatException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import MasterValidation.Validation;
import Model.Bean.DividendMasterBean;
import Model.Bean.DividentBean;
import Model.Bean.UserBean;
import Model.Dao.DividendMasterDao;
import Model.Dao.DividentDao;

/**
 * Servlet implementation class AdDividendMaster
 */
@WebServlet("/AdDividendMaster")
public class AdDividendMaster extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DividendMasterDao dao = null;
	private DividendMasterBean bean = null;
	private UserBean user=null;
	private Validation valid = new Validation();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdDividendMaster() {
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
		String action=request.getParameter("action");
		String divident_master_id = "";
		String year_from = "";
		String year_to = "";
		String convence_amt  = "";
		String dividend_per  = "";
		
		int ad_divident_master_id =0;
		Date ad_year_from = null;
		Date ad_year_to = null;
		double ad_convence_amt =0.0;
		double ad_dividend_per =0.0;
		boolean isactive=true;
		int rowsUpdated =0;
		String year=null;
		HttpSession session=request.getSession(false);
		user=(UserBean)session.getAttribute("userbean");
		
		String[] AppMessage = new String[2];
		AppMessage[0] = "alert-info";
		AppMessage[1] = "welcome";
	    session.setAttribute("AppMessage", AppMessage);
		
		if(action!=null){
			
			dao = new DividendMasterDao();
          
			if(action.equals("div_year_detail")){
				year=request.getParameter("div_year");
				Gson gson = new Gson();
				JsonObject myObject=new JsonObject();
				System.out.println(year);
				JsonElement element=null;
				if(year.equals("")!=true){
					
				DividendMasterBean bean=new DividendMasterDao().getDividendMasterBeanByYear(year);
								
				//Convert Java Object to json
				
				 element = gson.toJsonTree(bean);
				if(bean.getYear()== null){
					myObject.addProperty("success", false);
					
				}
				else {
					myObject.addProperty("success", true);
					myObject.add("OBJ", element);
				} 
				
				}else{
				myObject.addProperty("success", false);
				}
				
				response.setContentType("application/json");
				response.getWriter().print(myObject);
				//System.out.println(element);
			}else if(action.equals("delete_dividend")){
				String ad_dividend_id = request.getParameter("ad_dividend_id");
						
				
				if(valid.validNotEmpty(ad_dividend_id)==false && valid.validDigits(ad_dividend_id) == false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Invalid Dividend Id!";
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_dividend.jsp");
				}else{
					int id=Integer.parseInt(ad_dividend_id);
					DividentBean bean=new DividentBean();
					bean.setAd_divident_id(id);
					int result=new DividentDao().deleteDividend(bean);
					if(result>0){
						AppMessage[0] = "alert-suucess";
						AppMessage[1] = "Record deleted successfully!";
						
					}else{
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Please try again !";
					}
				}
				session.setAttribute("AppMessage", AppMessage);
		    	response.sendRedirect("generate_dividend.jsp");
			}else if(action.equals("generate_dividend")){
				
				Date open_day=(Date)session.getAttribute("openday");
				year=request.getParameter("div_year");
				String date=request.getParameter("date");
				String roi=request.getParameter("dividend_per");
				String conv=request.getParameter("convence_amt");
				String ad_member_id=request.getParameter("ad_member_id");
				DividentBean bean=new DividentBean();
				bean.setConv_amt(Double.parseDouble(conv));
				bean.setTotal_intamt(Double.parseDouble(roi));
				bean.setDiv_year(year);
				bean.setCreatedby(user.getAd_user_id());
				bean.setUpdatedby(user.getAd_user_id());
				try{
				bean.setFromdate(new SimpleDateFormat("dd/MM/yyyy").parse(date));
				}catch (ParseException e) {
					e.printStackTrace();
					
				}
				boolean result=false;
				
				
				if(Integer.parseInt(ad_member_id)>0){
					result=new DividentDao().isDividentYearOrMemberAvailable(year,Integer.parseInt(ad_member_id));
					if(result!=true){
					bean.setAd_member_id(Integer.parseInt(ad_member_id));
					new DividentDao().addMemberDivident(bean,open_day);
					AppMessage[0] = "alert-success";
					AppMessage[1] = "Dividend generated successfully..!!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Member's dividend already generated..!!";
					}
				}else{
					result=new DividentDao().isDividentYearAvailable(year);
					if(result!=true){	
						bean.setAd_member_id(Integer.parseInt(ad_member_id));
						new DividentDao().addDivident(bean,open_day);
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Dividend generated successfully..!!";
				}else{
					AppMessage[0] = "alert-info";
					AppMessage[1] = "Dividend already generated for the given year..!!";
				}
				}
				session.setAttribute("AppMessage", AppMessage);
				response.sendRedirect("generate_dividend.jsp");
				
				
				
				
				
				
			}else if(action.equals("insert")){
		    	
		    	year_from = request.getParameter("year_from");
		    	year_to = request.getParameter("year_to");
				convence_amt  = request.getParameter("convence_amt");
				dividend_per  = request.getParameter("dividend_per");
				year=request.getParameter("div_year");
				
				if(valid.validNotEmpty(year_from)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend from date is required field!";
				}else if(valid.validNotEmpty(year_to)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend to date is required field!";
				}/*else if(valid.validNotEmpty(year)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend year to is required field!";
				}*/else if(valid.validNotEmpty(convence_amt)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Convence amount is required field!";
				}else if(valid.validNumeric(convence_amt)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Convence amount should be numeric!";
				}else if(valid.validNotEmpty(dividend_per)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend percentage is required field!";
				}else if(valid.validNumeric(dividend_per)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend percentage should be numeric!";
				}else{
					
					ad_convence_amt = Double.parseDouble(convence_amt) ;
					ad_dividend_per = Double.parseDouble(dividend_per) ;
					
					try{
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						ad_year_from = sdf.parse(year_from);
						ad_year_to = sdf.parse(year_to);
					}catch(ParseException pe){
						
					}

					bean = new DividendMasterBean();
					bean.setYear(year);
					bean.setCreatedby(user.getAd_user_id());
					bean.setYear_from(ad_year_from);
					bean.setYear_to(ad_year_to);
					bean.setAd_dividend_per(ad_dividend_per);
					bean.setAd_convence_amt(ad_convence_amt);
					bean.setIsactive(isactive);
					bean.setUpdatedby(user.getAd_user_id());
					
					rowsUpdated = dao.addDividendMaster(bean);
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Updated!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
				}//end validation
				
				session.setAttribute("AppMessage", AppMessage);
		    	response.sendRedirect("ad_dividend_master.jsp");
				
			}else if(action.equals("edit")){
				
				divident_master_id=request.getParameter("id");
				response.sendRedirect("edit_dividend_master.jsp?id="+divident_master_id);
				
			}else if(action.equals("update")){
				
				divident_master_id = request.getParameter("id");
				year_from = request.getParameter("year_from");
		    	year_to = request.getParameter("year_to");
				convence_amt  = request.getParameter("convence_amt");
				dividend_per  = request.getParameter("dividend_per");
				year=request.getParameter("div_year");
				if(valid.validNotEmpty(divident_master_id)==false && valid.validDigits(divident_master_id) == false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Invalid Dividend master Id!";
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_dividend_master.jsp");
				}else if(valid.validNotEmpty(year_from)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend from date is required field!";
				}else if(valid.validNotEmpty(year_to)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend to date is required field!";
				}else if(valid.validNotNull(year)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend year should not be null !";
				}else if(valid.validNotEmpty(year)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend year to is required field!";
				}else if(valid.validNotEmpty(convence_amt)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Convence amount is required field!";
				}else if(valid.validNumeric(convence_amt)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Convence amount should be numeric!";
				}else if(valid.validNotEmpty(dividend_per)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend percentage is required field!";
				}else if(valid.validNumeric(dividend_per)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend percentage should be numeric!";
				}else{
					
					ad_divident_master_id=Integer.parseInt(divident_master_id) ;
					ad_convence_amt = Double.parseDouble(convence_amt) ;
					ad_dividend_per = Double.parseDouble(dividend_per) ;
					
					try{
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						ad_year_from = sdf.parse(year_from);
						ad_year_to = sdf.parse(year_to);
					}catch(ParseException pe){
						
					}
					
					bean = new DividendMasterBean();
					
					bean.setAd_divident_master_id(ad_divident_master_id);
					bean.setYear_from(ad_year_from);
					bean.setYear_to(ad_year_to);
					bean.setAd_dividend_per(ad_dividend_per);
					bean.setAd_convence_amt(ad_convence_amt);
					bean.setIsactive(isactive);
					bean.setUpdatedby(user.getAd_user_id());
					bean.setYear(year);
					rowsUpdated = dao.updateDividendMaster(bean);
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Updated!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
				}//end validation
				
				session.setAttribute("AppMessage", AppMessage);
		    	response.sendRedirect("ad_dividend_master.jsp");
				
			}else if(action.equals("updateDividend")){
				
				String ad_dividend_id = request.getParameter("ad_dividend_id");
				year_from = request.getParameter("fromdate");
		    	year_to = request.getParameter("todate");
				convence_amt  = request.getParameter("convence_amt");
				dividend_per  = request.getParameter("dividend_per");
				year=request.getParameter("div_year");
				String share_qty=request.getParameter("share_qty");
				String share_amt=request.getParameter("share_amt");
				String div_amt=request.getParameter("dividend_amt");
				String tot_amt=request.getParameter("total_amt");
				String ins_type=request.getParameter("instrument_type");
				String ins_no=request.getParameter("instrument_no");
				String ins_from=request.getParameter("instrument_from");
				String desc=request.getParameter("desc");
				String pay_status=request.getParameter("pay_status");
				String date=request.getParameter("date");
				
				
				if(valid.validNotEmpty(ad_dividend_id)==false && valid.validDigits(ad_dividend_id) == false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Invalid Dividend Id!";
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_dividend.jsp");
				}else if(valid.validNotEmpty(date)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend date is required field!";
				}else if(valid.validNotEmpty(year_from)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend from date is required field!";
				}else if(valid.validNotEmpty(year_to)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend to date is required field!";
				}else if(valid.validNotNull(year)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend year should not be null !";
				}else if(valid.validNotEmpty(year)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend year to is required field!";
				}else if(valid.validNotEmpty(convence_amt)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Convence amount is required field!";
				}else if(valid.validNumeric(convence_amt)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Convence amount should be numeric!";
				}else if(valid.validNotEmpty(dividend_per)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend percentage is required field!";
				}else if(valid.validNumeric(dividend_per)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend percentage should be numeric!";
				}else if(valid.validNotEmpty(div_amt)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend amount is required field!";
				}else if(valid.validNumeric(div_amt)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend amount should be numeric!";
				}else if(valid.validNotEmpty(tot_amt)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend total amount is required field!";
				}else if(valid.validNumeric(tot_amt)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend total amount should be numeric!";
				}else if(valid.validNotEmpty(share_qty)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend share qty is required field!";
				}else if(valid.validNumeric(share_qty)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend share qty should be numeric!";
				}else if(valid.validNotEmpty(share_amt)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend share amt is required field!";
				}else if(valid.validNumeric(share_amt)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend share amt should be numeric!";
				}else if(valid.validAlphaNum(desc)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Dividend description should be Alphabets or Numbers only!";
				}else{
					
					
					ad_convence_amt = Double.parseDouble(convence_amt) ;
					ad_dividend_per = Double.parseDouble(dividend_per) ;
					double s_qty=Double.parseDouble(share_qty) ;
					double s_amt=Double.parseDouble(share_amt) ;
					double total_amt=Double.parseDouble(tot_amt) ;
					double divi_amt=Double.parseDouble(div_amt) ;
					int inst_no=Integer.parseInt(ins_no);
					Date divdate=null;
					try{
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						ad_year_from = sdf.parse(year_from);
						ad_year_to = sdf.parse(year_to);
						divdate=sdf.parse(date);
					}catch(ParseException pe){
						
					}
					
					DividentBean bean = new DividentBean();
					bean.setShare_qty(s_qty);
					bean.setTotalshare_amt(s_amt);
					bean.setTotal_amt(total_amt);
					bean.setTotal_intamt(divi_amt);
					bean.setPay_status(pay_status);
					bean.setAd_divident_id(Integer.parseInt(ad_dividend_id));
					bean.setFromdate(ad_year_from);
					bean.setTodate(ad_year_to);
					bean.setRoi(ad_dividend_per);
					bean.setConv_amt(ad_convence_amt);
					bean.setIsactive(isactive);
					bean.setUpdatedby(user.getAd_user_id());
					bean.setDiv_year(year);
					bean.setDiscription(desc);
					bean.setInstrument_type(ins_type);
					bean.setInstrument_no(inst_no);
					bean.setInstrument_date(divdate);
					
					rowsUpdated=new DividentDao().updateDivident(bean);
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Updated!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					
				}//end validation
				
				session.setAttribute("AppMessage", AppMessage);
		    	response.sendRedirect("ad_dividend.jsp");
				
			}else{
				AppMessage[0] = "alert-danger";
				//AppMessage[1] = "Invalid Action!";
				session.setAttribute("AppMessage", AppMessage);
		    	response.sendRedirect("ad_dividend.jsp");
			}//end check actionelse{
				AppMessage[0] = "alert-danger";
				//AppMessage[1] = "Invalid Action!";
				session.setAttribute("AppMessage", AppMessage);
		    	//response.sendRedirect("ad_dividend_master.jsp");
			}//end check action
	
		    
				
		
	}//end post method

}//end class