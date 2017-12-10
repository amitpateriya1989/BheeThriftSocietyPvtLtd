package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Model.Bean.BankBean;
import Model.Bean.BankBranchBean;
import Model.Bean.CategoryBean;
import Model.Bean.CityBean;
import Model.Bean.CountryBean;
import Model.Bean.DepartmentBean;
import Model.Bean.DesignationBean;
import Model.Bean.DesignationLevelBean;
import Model.Bean.DesignationTypeBean;
import Model.Bean.DistrictBean;
import Model.Bean.GenderBean;
import Model.Bean.EmployeeBean;
import Model.Bean.MemberStatusBean;
import Model.Bean.MemberTypeBean;
import Model.Bean.PFRuleBean;
import Model.Bean.ReligionBean;
import Model.Bean.SalutationBean;
import Model.Bean.SocietyDepartmentBean;
import Model.Bean.StateBean;
import Model.Bean.UserBean;
import Model.Dao.BankBranchDao;
import Model.Dao.BankDao;
import Model.Dao.EmployeeDao;
import Model.Dao.PFRuleDao;
import Model.Dao.SalutationDao;

/**
 * Servlet implementation class AdEmployee
 */
@WebServlet("/AdEmployee")
public class AdEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "employee_images";
	private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    HashMap<String,String>map=new HashMap<String,String>();
    public AdEmployee() {
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
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		HttpSession session=request.getSession(false);
		UserBean user=(UserBean)session.getAttribute("userbean");
		if(action!=null){
			
			if (action.equals("view")) {
				int ad_employee_id=Integer.parseInt(request.getParameter("ad_employee_id"));
				EmployeeDao dao=new EmployeeDao();
				EmployeeBean bean=dao.getEmployeeById(ad_employee_id);
				//Convert Java Object to Json
				Gson gson = new Gson();
				JsonObject myObject=new JsonObject();
				JsonElement element = gson.toJsonTree(bean);
				if(bean.getName()== null){
					myObject.addProperty("success", false);
				}
				else {
					myObject.addProperty("success", true);
				} 
				myObject.add("EmployeeInfo", element);
				response.setContentType("application/json");
				response.getWriter().print(myObject);
				System.out.println(myObject.toString());

			}else if(action.equals("insert")){
				
				try{
				
				if (!ServletFileUpload.isMultipartContent(request)) {
		            PrintWriter writer = response.getWriter();
		            writer.println("Request does not contain upload data");
		            writer.flush();
		            return;
		        }
		         
		        // configures upload settings
		        DiskFileItemFactory factory = new DiskFileItemFactory();
		        factory.setSizeThreshold(THRESHOLD_SIZE);
		        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		         
		        ServletFileUpload upload = new ServletFileUpload(factory);
		        upload.setFileSizeMax(MAX_FILE_SIZE);
		        upload.setSizeMax(MAX_REQUEST_SIZE);
		         
		        // constructs the directory path to store upload file
		        String uploadPath = getServletContext().getRealPath("")
		            + File.separator + UPLOAD_DIRECTORY;
		        // creates the directory if it does not exist
		        File uploadDir = new File(uploadPath);
		        if (!uploadDir.exists()) {
		            uploadDir.mkdir();
		        }
				
		        try {
		            // parses the request's content to extract file data
		            List formItems = upload.parseRequest(request);
		            Iterator iter = formItems.iterator();
		             
		            // iterates over form's fields
		            while (iter.hasNext()) {
		                FileItem item = (FileItem) iter.next();
		                // processes only fields that are not form fields
		                if (!item.isFormField()) {
		                    String fileName = new File(item.getName()).getName();
		                    String filePath = uploadPath + File.separator + fileName;
		                    File storeFile = new File(filePath);
		                    System.out.println(item.getName()+"======="+fileName);
		                    map.put(item.getFieldName(), fileName);
		                    // saves the file on disk
		                    item.write(storeFile);
		                }
		                else{
		                	// Get the  file input parameters
		                	//System.out.println(item.getFieldName()+"======="+item.getString());
		                    String fieldName = item.getFieldName();
		                    String value = item.getString();
		                    map.put(fieldName, value);
		                    
		                    
		                }
		            }
		           
		        }catch(Exception e){
		        	e.printStackTrace();
		        }
		        
		        
		        
		        
		        EmployeeBean bean = new EmployeeBean();
				
				bean.setCreated(new java.util.Date());
				bean.setCreatedby(user.getAd_user_id());
				bean.setUpdated(new java.util.Date());
				bean.setUpdatedby(user.getAd_user_id());
				bean.setIsactive(true);
				
				
			
				bean.setEmployee_id(map.get("ad_employee_id"));
				
				bean.setName(map.get("name"));
				bean.setFname(map.get("fname"));


				try{
					if(map.get("dob")!=null){
						bean.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(map.get("dob")));
					}
					if(map.get("doa")!=null){
						bean.setDoa(new SimpleDateFormat("dd/MM/yyyy").parse(map.get("doa")));
					}
					if(map.get("doj")!=null){
						bean.setDoj(new SimpleDateFormat("dd/MM/yyyy").parse(map.get("doj")));
					}
					
					if(map.get("photo")!=null){
					bean.setPhoto(map.get("photo"));
					}
					if(map.get("id")!=null){
					bean.setId(map.get("id"));
					}
					if(map.get("sign")!=null){
					bean.setSign(map.get("sign"));
					}
									
				GenderBean gender=new GenderBean();
				gender.setAd_gender_id(Integer.parseInt(map.get("ad_gender_id")));
				bean.setGender(gender);
				
				
				
				SocietyDepartmentBean department=new SocietyDepartmentBean();
				department.setAd_society_department_id(Integer.parseInt(map.get("ad_department_id")));
				bean.setDepartment(department);
				
				DesignationBean designation=new DesignationBean();
				designation.setAd_designation_id(Integer.parseInt(map.get("ad_designation_id")));
				bean.setDesignation(designation);
				
				
				}catch(NumberFormatException n){
					n.printStackTrace();
					}
				catch(ParseException p){
					p.printStackTrace();
				}
				
				try{
					
					if(map.get("ad_city_id")!=null){
						CityBean city=new CityBean();
						try{
							 city.setAd_city_id(Integer.parseInt(map.get("ad_city_id")));
							 bean.setCity(city);
					}catch(NumberFormatException e){
						System.out.println("Number format Exception in ad_city_id");
						}
					}
					if(map.get("ad_district_id")!=null){
						DistrictBean district=new DistrictBean();
						try{
							 district.setAd_district_id(Integer.parseInt(map.get("ad_district_id")));
							 bean.setDistrict(district);
					}catch(NumberFormatException e){
						System.out.println("Number format Exception in ad_district_id");
						}
					}
					if(map.get("ad_state_id")!=null){
						StateBean state=new StateBean();
						try{
							state.setAd_state_id(Integer.parseInt(map.get("ad_state_id")));
							 bean.setState(state);
					}catch(NumberFormatException e){
						System.out.println("Number format Exception in ad_state_id");
						}
					}
					if(map.get("ad_country_id")!=null){
						CountryBean country=new CountryBean();
						try{
							country.setAd_country_id(Integer.parseInt(map.get("ad_country_id")));
							 bean.setCountry(country);
					}catch(NumberFormatException e){
						System.out.println("Number format Exception in ad_country_id");
						}
					}
					
					if(map.get("ad_religion_id")!=null){
						ReligionBean religion=new ReligionBean();
						try{
							religion.setAd_religion_id(Integer.parseInt(map.get("ad_religion_id")));
							 bean.setReligion(religion);
					}catch(NumberFormatException e){
						System.out.println("Number format Exception in ad_country_id");
						}
					}
					
					if(map.get("ad_grade_id")!=null){
						
						try{
						
							 bean.setAd_employee_grade_id(Integer.parseInt(map.get("ad_grade_id")));
					}catch(NumberFormatException e){
						System.out.println("Number format Exception in ad_grade_id");
						}
					}
					
					}catch(Exception e){
						e.printStackTrace();
					}
				
				
				
					bean.setEmp_status(map.get("emp_status"));
					bean.setEmployee_id(map.get("employee_id"));
					bean.setEmp_category(map.get("emp_category"));
					bean.setMarital_sts(map.get("marital_status"));
					bean.setBlood_group(map.get("blood_group"));
					bean.setId_mark(map.get("identity_marks"));
					bean.setHeight(map.get("height"));
					bean.setRemark(map.get("remarks"));
					bean.setPin(map.get("pincode"));
					bean.setMobile(map.get("mobile"));
					bean.setAlt_mobile(map.get("alt_mobile"));
					bean.setPhone(map.get("phone"));
					bean.setC_address(map.get("c_address"));
					bean.setP_address(map.get("p_address"));
					bean.setEmail(map.get("email"));
					bean.setStream_10(map.get("12_stream"));
					bean.setSub_10(map.get("10_sub"));
					bean.setBoard_10(map.get("10_board"));
					bean.setPass_year_10(map.get("10_pass_year"));
					bean.setPer_10(Double.parseDouble(map.get("10_per")));
					bean.setStream_12(map.get("12_stream"));
					bean.setSub_12(map.get("12_sub"));
					bean.setBoard_12(map.get("12_board"));
					bean.setPass_year_12(map.get("12_pass_year"));
					bean.setPer_12(Double.parseDouble(map.get("12_per")));
					bean.setStream_g(map.get("g_stream"));
					bean.setSub_g(map.get("g_sub"));
					bean.setBoard_g(map.get("g_board"));
					bean.setPass_year_g(map.get("g_pass_year"));
					bean.setPer_g(Double.parseDouble(map.get("g_per")));
					bean.setStream_pg(map.get("pg_stream"));
					bean.setSub_pg(map.get("pg_sub"));
					bean.setBoard_pg(map.get("pg_board"));
					bean.setPass_year_pg(map.get("pg_pass_year"));
					bean.setPer_pg(Double.parseDouble(map.get("pg_per")));
					bean.setTr_sub(map.get("tr_sub"));
					bean.setTr_stream(map.get("tr_stream"));
					bean.setTr_board(map.get("tr_board"));
					bean.setTr_pass_year(map.get("tr_pass_year"));
					bean.setTr_per(Double.parseDouble(map.get("tr_per")));
					bean.setAcc_no(map.get("saving_ac_no"));
					bean.setPf_acc_no(map.get("pf_no"));
					//bean.setEmployee_id(map.get("employee_id"));
					bean.setMonth_pay(Integer.parseInt(map.get("monthly_pay")));
					bean.setWeakly_off(map.get("week_off"));
					SalutationBean salutation=new SalutationBean();
					salutation=new SalutationDao().getSalutationById(Integer.parseInt(map.get("ad_salutation_id")));
					bean.setSalutation(salutation);
					BankBean bank=new BankBean();
					bank=new BankDao().getBankById(Integer.parseInt(map.get("ad_bank_id")));
					bean.setBank(bank);
					BankBranchBean branch =new BankBranchBean();
					branch= new BankBranchDao().getBankBranchById(Integer.parseInt(map.get("ad_branch_id")));
					bean.setBranch(branch);
					new EmployeeDao().addEmployee(bean);
				}catch(Exception e){
					e.printStackTrace();
				}
					response.sendRedirect("ad_employee.jsp");
					
				}else if(action.equals("update")){
						if (!ServletFileUpload.isMultipartContent(request)) {
			            PrintWriter writer = response.getWriter();
			            writer.println("Request does not contain upload data");
			            writer.flush();
			            return;
			        }
			         
			        // configures upload settings
			        DiskFileItemFactory factory = new DiskFileItemFactory();
			        factory.setSizeThreshold(THRESHOLD_SIZE);
			        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			         
			        ServletFileUpload upload = new ServletFileUpload(factory);
			        upload.setFileSizeMax(MAX_FILE_SIZE);
			        upload.setSizeMax(MAX_REQUEST_SIZE);
			         
			        // constructs the directory path to store upload file
			        String uploadPath = getServletContext().getRealPath("")
			            + File.separator + UPLOAD_DIRECTORY;
			        // creates the directory if it does not exist
			        File uploadDir = new File(uploadPath);
			        if (!uploadDir.exists()) {
			            uploadDir.mkdir();
			        }
					
			        try {
			            // parses the request's content to extract file data
			            List formItems = upload.parseRequest(request);
			            Iterator iter = formItems.iterator();
			             
			            // iterates over form's fields
			            while (iter.hasNext()) {
			                FileItem item = (FileItem) iter.next();
			                // processes only fields that are not form fields
			                if (!item.isFormField()) {
			                    String fileName = new File(item.getName()).getName();
			                    String filePath = uploadPath + File.separator + fileName;
			                    File storeFile = new File(filePath);
			                    System.out.println(item.getName()+"======="+fileName);
			                    map.put(item.getFieldName(), fileName);
			                    // saves the file on disk
			                    item.write(storeFile);
			                }
			                else{
			                	// Get the  file input parameters
			                	//System.out.println(item.getFieldName()+"======="+item.getString());
			                    String fieldName = item.getFieldName();
			                    String value = item.getString();
			                    map.put(fieldName, value);
			                    
			                    
			                }
			            }
			           
			        }catch(Exception e){
			        	e.printStackTrace();
			        }
			        
			        
			        
			        
			        EmployeeBean bean = new EmployeeBean();
					
					bean.setUpdated(new java.util.Date());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(true);
					
					EmployeeBean employee=new EmployeeBean();
					employee.setAd_employee_id(Integer.parseInt(map.get("ad_employee_id")));
					bean.setEmployee_id(map.get("ad_employee_id"));
					
					bean.setName(map.get("name"));
					bean.setFname(map.get("fname"));


					try{
						if(map.get("dob")!=null){
							bean.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(map.get("dob")));
						}
						if(map.get("doa")!=null){
							bean.setDoa(new SimpleDateFormat("yyyy-MM-dd").parse(map.get("doa")));
						}
						if(map.get("doj")!=null){
							bean.setDoj(new SimpleDateFormat("yyyy-MM-dd").parse(map.get("doj")));
						}
						
						if(map.get("photo")!=null){
						bean.setPhoto(map.get("photo"));
						}
						if(map.get("id")!=null){
						bean.setId(map.get("id"));
						}
						if(map.get("sign")!=null){
						bean.setSign(map.get("sign"));
						}
										
					GenderBean gender=new GenderBean();
					gender.setAd_gender_id(Integer.parseInt(map.get("ad_gender_id")));
					bean.setGender(gender);
					
					
					
					SocietyDepartmentBean department=new SocietyDepartmentBean();
					department.setAd_society_department_id(Integer.parseInt(map.get("ad_department_id")));
					bean.setDepartment(department);
					
					DesignationBean designation=new DesignationBean();
					designation.setAd_designation_id(Integer.parseInt(map.get("ad_designation_id")));
					bean.setDesignation(designation);
					
					
					}catch(NumberFormatException n){
						n.printStackTrace();
						}
					catch(ParseException p){
						p.printStackTrace();
					}
					
	try{
						
						if(map.get("ad_city_id")!=null){
							CityBean city=new CityBean();
							try{
								 city.setAd_city_id(Integer.parseInt(map.get("ad_city_id")));
								 bean.setCity(city);
						}catch(NumberFormatException e){
							System.out.println("Number format Exception in ad_city_id");
							}
						}
						if(map.get("ad_district_id")!=null){
							DistrictBean district=new DistrictBean();
							try{
								 district.setAd_district_id(Integer.parseInt(map.get("ad_district_id")));
								 bean.setDistrict(district);
						}catch(NumberFormatException e){
							System.out.println("Number format Exception in ad_district_id");
							}
						}
						if(map.get("ad_state_id")!=null){
							StateBean state=new StateBean();
							try{
								state.setAd_state_id(Integer.parseInt(map.get("ad_state_id")));
								 bean.setState(state);
						}catch(NumberFormatException e){
							System.out.println("Number format Exception in ad_state_id");
							}
						}
						if(map.get("ad_country_id")!=null){
							CountryBean country=new CountryBean();
							try{
								country.setAd_country_id(Integer.parseInt(map.get("ad_country_id")));
								 bean.setCountry(country);
						}catch(NumberFormatException e){
							System.out.println("Number format Exception in ad_country_id");
							}
						}
						
						}catch(Exception e){
							
						}
					
						bean.setEmp_status(map.get("emp_status"));
						bean.setEmployee_id(map.get("employee_id"));
						bean.setEmp_category(map.get("emp_category"));
						bean.setMarital_sts(map.get("marital_sts"));
						bean.setBlood_group(map.get("blood_group"));
						bean.setId_mark(map.get("id_mark"));
						bean.setHeight(map.get("height"));
						bean.setRemark(map.get("remark"));
						bean.setPin(map.get("pin"));
						bean.setMobile(map.get("mobile"));
						bean.setAlt_mobile(map.get("alt_mobile"));
						bean.setPhone(map.get("phone"));
						bean.setC_address(map.get("c_address"));
						bean.setP_address(map.get("p_address"));
						bean.setEmail(map.get("email"));
						bean.setStream_10(map.get("stream_10"));
						bean.setSub_10(map.get("sub_10"));
						bean.setBoard_10(map.get("board_10"));
						bean.setPass_year_10(map.get("pass_year_10"));
						bean.setPer_10(Double.parseDouble(map.get("per_10")));
						bean.setStream_12(map.get("stream_12"));
						bean.setSub_12(map.get("sub_12"));
						bean.setBoard_12(map.get("board_12"));
						bean.setPass_year_12(map.get("pass_year_12"));
						bean.setPer_12(Double.parseDouble(map.get("per_12")));
						bean.setStream_g(map.get("stream_g"));
						bean.setSub_g(map.get("sub_g"));
						bean.setBoard_g(map.get("board_g"));
						bean.setPass_year_g(map.get("pass_year_g"));
						bean.setPer_g(Double.parseDouble(map.get("per_g")));
						bean.setStream_pg(map.get("stream_pg"));
						bean.setSub_pg(map.get("sub_pg"));
						bean.setBoard_pg(map.get("board_pg"));
						bean.setPass_year_pg(map.get("pass_year_pg"));
						bean.setPer_pg(Double.parseDouble(map.get("per_pg")));
						bean.setTr_sub(map.get("tr_sub"));
						bean.setTr_stream(map.get("tr_stream"));
						bean.setTr_board(map.get("tr_board"));
						bean.setTr_pass_year(map.get("tr_pass_year"));
						bean.setTr_per(Double.parseDouble(map.get("tr_per")));
						bean.setAcc_no(map.get("acc_no"));
						bean.setPf_acc_no(map.get("pf_acc_no"));
						bean.setAd_employee_id(Integer.parseInt(map.get("ad_employee")));
						bean.setMonth_pay(Integer.parseInt(map.get("month_pay")));
						bean.setWeakly_off(map.get("weakly_off"));
						SalutationBean salutation=new SalutationBean();
						salutation=new SalutationDao().getSalutationById(Integer.parseInt(map.get("salutation")));
						bean.setSalutation(salutation);
						
						
						
						new EmployeeDao().updateEmployee(bean);
					
					
					
					
					
				}else if(action.equals("edit")){
					System.out.println("Inside edit");
					
					String ad_employee_id=request.getParameter("ad_employee_id");
					
					response.sendRedirect("edit_employee.jsp?ad_employee_id="+ad_employee_id);
					
					
				}else if(action.equals("delete")){
					
					EmployeeBean bean=new EmployeeBean();
					
					String id=request.getParameter("ad_employee_id");
					int sid=Integer.parseInt(id);
					bean.setAd_employee_id(sid);
					
					new EmployeeDao().deleteEmployee(bean);
					response.sendRedirect("ad_employee.jsp");
				}
					
				
				
		        
		 
	}//action not=null


}
}