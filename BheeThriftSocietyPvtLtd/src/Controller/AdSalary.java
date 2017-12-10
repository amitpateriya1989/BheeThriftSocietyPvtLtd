package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.lookup.MethodScope;

import Model.Bean.EmployeeBean;
import Model.Bean.GradeBean;
import Model.Bean.SalaryBean;
import Model.Dao.EmployeeDao;
import Model.Dao.GradeDao;
import Model.Dao.SalaryDao;

/**
 * Servlet implementation class AdSalary
 */
@WebServlet("/AdSalary")

    /**
     * @see HttpServlet#HttpServlet()
     */
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public class AdSalary extends HttpServlet {
    	private static final long serialVersionUID = 1L;
    	private String month_name;
    	private int no_of_sunday;
    	private int days;
    	private long pf=0,pt=0,esi=0,tds=0,other_ded=0,other_amt=0;
    	private double gross;
        public AdSalary() {
            super();
           
        }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String action=request.getParameter("action");
		
		  
		//System.out.println("from AdAreaType: "+request.getParameter("ad_bank_id"));
		if (action.equals("view")) {
			//request.getRequestDispatcher("ad_link_account.jsp?action=view").forward(request, response);

		} else if (action.equals("edit")) {
			//request.getRequestDispatcher("ad_link_account.jsp?action=edit").forward(request, response);

		} else if (action.equals("delete")) {
			SalaryDao dao = new SalaryDao();
			SalaryBean bean = new SalaryBean();
			EmployeeBean empbean=new EmployeeDao().getEmployeeByCode(request.getParameter("emp_id").trim());
			System.out.println(empbean.getAd_employee_id());
			bean.setAd_employee_id(empbean.getAd_employee_id());
			bean.setMonth_name(request.getParameter("month"));
			//AccountDao dao=new AccountDao();
			dao.deleteSalary(bean);
			response.sendRedirect("ad_salary.jsp");

		} else if (action.equals("update")) {
			SalaryDao dao = new SalaryDao();
			SalaryBean bean = new SalaryBean();
			int n=Integer.parseInt(request.getParameter("tot_records"));
			for(int i=0;i<n;i++){
				
				EmployeeDao edao=new EmployeeDao();
				EmployeeBean ebean=edao.getEmployeeByCode(request.getParameter("emp_id_"+i));
				bean.setAd_employee_id(Integer.parseInt(request.getParameter("ad_employee_id_"+i)));
				bean.setMonth_name(request.getParameter("month_"+i));
				bean.setHolidays(Integer.parseInt(request.getParameter("holidays_"+i)));
				bean.setTot_absent(Integer.parseInt(request.getParameter("absent_"+i)));
				bean.setPayble_days(Integer.parseInt(request.getParameter("payble_"+i)));
				bean.setWorking_days(Integer.parseInt(request.getParameter("wkgdays_"+i)));
				bean.setWorked_days(Integer.parseInt(request.getParameter("wkdays_"+i)));
				bean.setCreated(new java.util.Date());
				bean.setMonthly_pay(Double.parseDouble(request.getParameter("monthly_sal_"+i)));
				bean.setBasic(Double.parseDouble(request.getParameter("basic_"+i)));
				bean.setDa(Double.parseDouble(request.getParameter("da_"+i)));
				bean.setConvey(Double.parseDouble(request.getParameter("conv_"+i)));
				bean.setHra(Double.parseDouble(request.getParameter("hra_"+i)));
				bean.setMdcl(Double.parseDouble(request.getParameter("med_"+i)));
				bean.setAlwnc(Double.parseDouble(request.getParameter("allow_"+i)));
				bean.setOther_amt(Double.parseDouble(request.getParameter("other_"+i)));
				bean.setGross_amt(Double.parseDouble(request.getParameter("gross_"+i)));
				bean.setPf_amt(Double.parseDouble(request.getParameter("pf_"+i)));
				bean.setPt_amt(Double.parseDouble(request.getParameter("pt_"+i)));
				bean.setEsi_amt(Double.parseDouble(request.getParameter("esi_"+i)));
				bean.setTds_amt(Double.parseDouble(request.getParameter("tds_"+i)));
				bean.setOther_deduction(Double.parseDouble(request.getParameter("otherdeduction_"+i)));
				bean.setGross_deduction(Double.parseDouble(request.getParameter("grossdeduction_"+i)));
				bean.setNet_salary(Double.parseDouble(request.getParameter("netsal_"+i)));
				
				dao.updateSalary(bean);
			}
			
			response.sendRedirect("ad_salary.jsp");

		} else if(action.equalsIgnoreCase("generate_list")) {
			String month=request.getParameter("month");
			DateFormat format;
			 format=new SimpleDateFormat("yyyy-MM");
			  Date parse;
			  
			  try{
				  parse=format.parse(month);
					
				  Calendar c=Calendar.getInstance();
				  c.setTime(parse);
				 int month_no= c.get(Calendar.MONTH);
				 int year=c.get(Calendar.YEAR);
				 c.set(year, month_no, 1);
				 days=c.getActualMaximum(Calendar.DAY_OF_MONTH);
				 // find sundays in month 
				 for(int i=1;i<=days;i++){
					 c.set(year, month_no, i);
					 int day=c.get(Calendar.DAY_OF_WEEK);
					 if(day==1){
						 no_of_sunday++;
					 }
				 }
				 // System.out.println("month no"+month_no);
				  //System.out.println("Month days"+days);
				  //Methods m=new Methods();
				  month_name=getMonthForInt(month_no);
				  //System.out.println(month_name);
			  }catch(Exception e){e.printStackTrace();}
			SalaryDao dao = new SalaryDao();
			SalaryBean bean = new SalaryBean();
			
			int r=dao.chkSalaryByMonthYear(month);
			//System.out.print(bean2.getMonth_name());
			if(r!=0){
				out.print("<script>alert(\"Salary Already Generated...!!\");</script>");
			}
			else{
			bean.setWorked_days(days);
			bean.setHolidays(no_of_sunday);
			bean.setTot_absent(0);
			bean.setPayble_days(days);
			bean.setMonth_name(month);
			bean.setWorking_days(days-no_of_sunday);
			
			
			EmployeeDao empdao=new EmployeeDao();
			GradeDao grddao=new GradeDao();
			GradeBean grdbean=null;
			ArrayList<EmployeeBean> emplist=empdao.getAllActiveEmployee("Active");
			System.out.println(emplist);
			
			for(EmployeeBean empbean:emplist){
				
				bean.setAd_employee_id(empbean.getAd_employee_id());
				bean.setAd_grade_id(empbean.getAd_employee_grade_id());
				bean.setCreated(new java.util.Date());
				bean.setMonthly_pay(empbean.getMonth_pay());
				
				
				grdbean=grddao.getGradeById(empbean.getAd_employee_grade_id());
				double basic=grdbean.getBasics();
				double da=grdbean.getDa();
				double conv=grdbean.getConvey();
				double hra=grdbean.getHra();
				double med=grdbean.getMdcl();
				double allow=grdbean.getAlwnc();
				double month_pay=empbean.getMonth_pay();
				
				//String type=grdbean.getAmount_type();
				bean.setBasic(basic);
				
				//if(type.equalsIgnoreCase("Rs")){
					
					
					bean.setDa(da);
					bean.setConvey(conv);
					bean.setHra(hra);
					bean.setMdcl(med);
					bean.setAlwnc(allow);
					bean.setGross_amt(basic+da+conv+hra+med+allow);
					pf=Math.round((basic*12)/100);
					bean.setPf_amt(pf);
					if(month_pay>=15000){
						pt=208;
						bean.setPt_amt(pt);
					//}
					
				//}else if(type.equalsIgnoreCase("Percent")){	
					
					double salcal=month_pay-basic-da;
					
					double da1,conv1,hra1,med1,allow1;
					da1=Math.round(da);
					conv1=Math.round(((salcal*conv)/100));
					hra1=Math.round(((salcal*hra)/100));
					med1=Math.round(((salcal*med)/100));
					allow1=Math.round(((salcal*allow)/100));
					gross=basic+da1+conv1+hra1+med1+allow1;
					
					
					bean.setDa(da1);
					bean.setConvey(conv1);
					bean.setHra(hra1);
					bean.setMdcl(med1);
					bean.setAlwnc(allow1);
					bean.setGross_amt(gross);
					bean.setStatus(true);
					pf=Math.round((basic*12)/100);
					bean.setPf_amt(pf);
					if(month_pay>=15000){
						pt=208;
						bean.setPt_amt(pt);
					}
					
				}
				
				bean.setEsi_amt(esi);
				bean.setTds_amt(tds);
				bean.setOther_amt(other_amt);
				bean.setOther_deduction(other_ded);
				long tot_ded=pf+pt+esi+tds+other_ded;
				bean.setGross_deduction(tot_ded);
				bean.setNet_salary(gross-tot_ded);
				
				dao.addSalary(bean);
				}
		
			
			}	
			no_of_sunday=0;
			request.getRequestDispatcher("Ajax/generate_salary.jsp?action=view&month="+month+"&status=true").forward(request, response);
		}
		
	}
	

public String getMonthForInt(int x){
	String month=null;
	switch(x){
	case 1 :  month=("January");
			break;
	case 2 :  month=("February");break;
	case 3 :  month=("March");break;
	case 4 :  month=("April");break;
	case 5 : month=("May");break;
	case 6 :  month=("June");break;
	case 7 :  month=("July");break;
	case 8 :  month=("August");break;
	case 9 :  month=("September");break;
	case 10 :  month=("October");break;
	case 11 :  month=("November");break;
	case 12 :  month=("December");break;
	default: break;
	}
	return(month);
	
	
}
}
	

