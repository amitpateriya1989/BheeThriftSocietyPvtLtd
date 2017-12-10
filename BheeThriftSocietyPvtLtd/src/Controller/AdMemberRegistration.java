package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.StringWriter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

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
import Model.Bean.LedgerAccountBean;
import Model.Bean.MemberAddressBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.MemberShareBean;
import Model.Bean.MemberStatusBean;
import Model.Bean.MemberTypeBean;
import Model.Bean.NominationBean;
import Model.Bean.RelationBean;
import Model.Bean.SalutationBean;
import Model.Bean.StateBean;
import Model.Bean.ThriftTrxBean;
import Model.Bean.TransactionBean;
import Model.Bean.UserBean;
import Model.Bean.VoucherBean;
import Model.Bean.WitnessBean;
import Model.Dao.AccountGroupDao;
import Model.Dao.MemberAddressDao;
import Model.Dao.MemberRegistrationDao;
import Model.Dao.MemberShareDao;
import Model.Dao.NominationDao;
import Model.Dao.ThriftTrxDao;
import Model.Dao.TransactionDao;
import Model.Dao.VoucherDao;
import Model.Dao.WitnessDao;
import MasterValidation.Validation;

/**
 * Servlet implementation class AdMemberRegistration
 */
@WebServlet("/AdMemberRegistration")
public class AdMemberRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "member_images";
	private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	HashMap<String,String>map=new HashMap<String,String>();
	private MemberRegistrationDao memberdao=null;
	String[] AppMessage = new String[2];
	private UserBean user=null;
	public AdMemberRegistration() {
		super();

	}




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		memberdao=new MemberRegistrationDao();
		Validation valid = new Validation();
		HttpSession session=request.getSession(false);
		user=(UserBean)session.getAttribute("userbean");

		HashMap<String,String> lstMap = new HashMap<String,String>();

		AppMessage[0] = "alert-info";
		AppMessage[1] = "welcome";
		session.setAttribute("AppMessage", AppMessage);

		double mfee=0;
		double fgdsfee=0;
		double dcfee=0;
		double shareamt=0;
		double adfees=0;
		int ad_ins_no = 0;
		String default_date = "00/00/0000";
		int rowcount = 0;
		int rowsUpdated = 0;
        int no=0;

		String ad_pf_id ="";
		String member_id="";
		String ad_society_id ="";
		String ad_member_type_id ="";
		String ad_member_status_id ="";
		String ad_gender_id ="";
		String ad_category_id ="";
		String ad_salutation_id ="";
		String name ="";
		String father ="";
		String husband ="";
		String marital_status="";
		String dob ="";
		String photo ="";
		String id_proof ="";
		String sign ="";
		String ad_member_address_id1="0";
		String mobile ="";
		String phone ="";
		String email ="";
		String line1 ="";
		String line2 ="";
		String ad_country_id ="";
		String ad_state_id ="";
		String ad_district_id ="";
		String ad_city_id ="";
		String pincode ="";
		String ad_member_address_id2="0";
		String mobile1 ="";
		String phone1 ="";
		String email1 ="";
		String line1_1 ="";
		String line2_1 ="";
		String ad_country_id_1 ="";
		String ad_state_id_1 ="";
		String ad_district_id_1 ="";
		String ad_city_id_1 ="";
		String pincode1 ="";
		String ad_branch_id ="";
		String pan_no ="";
		String aadhar_no ="";
		String ad_department_id ="";
		String ad_designation_type_id ="";
		String ad_designation_id ="";
		String doa ="";
		String dor ="";
		String service_duration ="";
		String saving_ac_no ="";
		String nominee_ad_relation_id_1 ="";
		String nominee_ad_gender_id_1 ="";
		String nominee_ad_salutation_id_1 ="";
		String ad_nomination_id1="0";
		String nominee_name_1 ="";
		String nominee_dob_1 ="";
		String nominee_age_1 ="";
		String nominee_no_1="";
		String guardian1 ="";
		String nominee_photo_1 ="";
		String nominee_id_proof_1 ="";
		String nominee_sign_1 ="";
		String ad_nomination_id2="0";
		String second_nominee ="";
		String nominee_no_2="";
		String nominee_ad_relation_id_2 ="";
		String nominee_ad_gender_id_2 ="";
		String nominee_ad_salutation_id_2 ="";
		String nominee_name_2 ="";
		String nominee_dob_2 ="";
		String nominee_age_2 ="";
		String guardian2 ="";
		String nominee_photo_2 ="";
		String nominee_id_proof_2 ="";
		String nominee_sign_2 ="";
		String witness_ad_member_id ="";
		String witness_ad_society_id ="";
		String witness_ad_salutation_id ="";
		String witness_name ="";
		String witness_mobile ="";
		String witness_address ="";
		String ad_witness_id="";
		String membership_fees ="";
		String fgds_fund ="";
		String dcf ="";
		String share_qty ="";
		String share_amt ="";
		String admission_fees ="";
		String vtype ="";
		String ins_form ="";
		String ins_no ="";
		String ins_date ="";
		String h_v_amt =""; 
		String voucher_type ="";

		if (action.equals("branchupdate")) {
			int ad_member_id=Integer.parseInt(request.getParameter("ad_member_id"));
            int bid=Integer.parseInt(request.getParameter("ad_branch_id"));
            
			MemberRegistrationDao dao=new MemberRegistrationDao();
			rowcount=dao.updateMemberBranch(ad_member_id, bid);
			//------------------------------if success
			if(rowcount>0){
				AppMessage[0] = "alert-success";
				AppMessage[1] = "Record Updated Successfully ..!!!";
				
			}else{
				AppMessage[0] = "alert-warning";
				AppMessage[1] = "Sorry Try Again Later ..!!!";
				
			}
			session.setAttribute("AppMessage", AppMessage);
			response.sendRedirect("edit_member_branch.jsp");
			

			
		} else if (action.equals("view")) {
			int ad_member_id=Integer.parseInt(request.getParameter("ad_member_id"));

			MemberRegistrationDao dao=new MemberRegistrationDao();
			MemberRegistrationBean bean=dao.getMemberRegistrationById(ad_member_id);
			//Convert Java Object to json
			Gson gson = new Gson();
			JsonObject myObject=new JsonObject();
			JsonElement element = gson.toJsonTree(bean);
			if(bean.getName()== null){
				myObject.addProperty("success", false);
			}
			else {
				myObject.addProperty("success", true);
			} 
			myObject.add("MemberInfo", element);
			response.setContentType("application/json");
			response.getWriter().print(myObject);
			//System.out.println(myObject.toString());

		} else if (action.equals("verifypfno")) {
			if(request.getParameter("ad_pf_no")!=null){
				int ad_pf_no=0;
				try{
					ad_pf_no=Integer.parseInt(request.getParameter("ad_pf_no"));
					int pf_no=new MemberRegistrationDao().getVerifyMemberPFNo(ad_pf_no);
					if(pf_no>0){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Staff No already present in system...!");
						Gson gson = new Gson(); 
						String json = gson.toJson(lstMap); 
						response.setContentType("application/json");
						response.getWriter().print(json);

					}
				}catch(NumberFormatException e){
					System.out.print("PF No is Not Formated in Correct Numeric Form");
				}
			}
		}else if (action.equals("verifymobileno")) {
			if(request.getParameter("mobile_no")!=null){
				String mobile_no="";
				try{
					
					mobile_no=request.getParameter("mobile_no");
					int mob_no=new MemberRegistrationDao().getVerifyMemberMobileNo(mobile_no.trim());
					if(mob_no>0){
						System.out.print("Mobile No already present in system...!");
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Mobile No already present in system...!");
						Gson gson = new Gson(); 
						String json = gson.toJson(lstMap); 
						response.setContentType("application/json");
						response.getWriter().print(json);

					}
				}catch(Exception e){
					System.out.print("Mobile No is Not Formated in Correct Numeric Form");
				}
			}
		}else if (action.equals("verifyphoneno")) {
			if(request.getParameter("phone_no")!=null){
				String phone_no="";
				try{
					phone_no=request.getParameter("phone_no").trim();
					int phn_no=new MemberRegistrationDao().getVerifyMemberPhoneNo(phone_no.trim());
					if(phn_no>0){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Phone No already present in system...!");
						Gson gson = new Gson(); 
						String json = gson.toJson(lstMap); 
						response.setContentType("application/json");
						response.getWriter().print(json);

					}
				}catch(Exception e){
					System.out.print("Phone No is Not Formated in Correct Numeric Form");
				}
			}
		}else if (action.equals("verifyemail")) {
			if(request.getParameter("email")!=null){
				email="";
				try{
					email=request.getParameter("email");
					int email_no=new MemberRegistrationDao().getVerifyMemberEmail(email.trim());
					if(email_no>0){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Email already present in system...!");
						Gson gson = new Gson(); 
						String json = gson.toJson(lstMap); 
						response.setContentType("application/json");
						response.getWriter().print(json);

					}
				}catch(Exception e){
					System.out.print("Email Id is Not Formated in Correct Numeric Form");
				}
			}
		}else if (action.equals("newMember")) {
			String ad_society_no=null;
			String pf_no=null;
			if(request.getParameter("ad_pf_no")!=null){
				int ad_pf_no=Integer.parseInt(request.getParameter("ad_pf_no"));
				System.out.print(ad_pf_no);
				if(ad_pf_no!=0){
					ad_society_no=Integer.toString(new MemberRegistrationDao().getMemberSocietyNo(ad_pf_no));
					response.sendRedirect("ad_member.jsp?ad_pf_no="+ad_pf_no+"&ad_society_no="+ad_society_no);
				}else{
					pf_no="Not Available";
					int society_no=new MemberRegistrationDao().getMemberSocietyNo();
					society_no++;
					ad_society_no=Integer.toString(society_no);

					response.sendRedirect("ad_member.jsp?ad_pf_no="+pf_no+"&ad_society_no="+ad_society_no);
				}
			}
		}else if (action.equals("edit")) {

			String ad_member_id=request.getParameter("ad_member_id");
			//System.out.print("inside member controller edit section"+ad_member_id);

			if(valid.validNotEmpty(ad_member_id) !=false && valid.validDigits(ad_member_id)!=false){
				try{
					int id=Integer.parseInt(ad_member_id);

					response.sendRedirect("edit_member.jsp?ad_member_id="+id);
				}catch(NumberFormatException e){

				}
			}//end validation

		}else if (action.equals("uploadimage")) {

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

				try{

					List formItems = upload.parseRequest(request);
					Iterator iter = formItems.iterator();

					// iterates over form's fields
					while (iter.hasNext()) {
						FileItem item = (FileItem) iter.next();
						// processes only fields that are not form fields
						if (!item.isFormField()) {

							String fileName = new File(item.getName()).getName();
							if(valid.validNotEmpty(fileName)==true && item !=null){
								String filePath = uploadPath + File.separator + fileName;	                    
								File storeFile = new File(filePath);
								//System.out.println(storeFile);
								//System.out.println(item.getName()+"======="+fileName);
								map.put(item.getFieldName(), fileName);
								//saves the file on disk
								storeFile.setReadable(true);
								try{
									item.write(storeFile);
								}catch(Exception e){
									e.printStackTrace();
								}
							}


						}else{
							// Get the  file input parameters
							//System.out.println(item.getFieldName()+"======="+item.getString());
							String fieldName = item.getFieldName();
							String value = item.getString();
							map.put(fieldName, value);
						}
					}//end while

				} catch (FileUploadException e) {
					e.printStackTrace();
				}


				MemberRegistrationBean bean = new MemberRegistrationBean();
				NominationBean first=new NominationBean();
				NominationBean second=new NominationBean();

				member_id = map.get("h_member_id_up");
				//h_member_name_up

				if(valid.validNotEmpty(member_id)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Member Id Should not empty!";
				}else if(valid.validDigits(member_id)==false){
					AppMessage[0] = "alert-warning";
					AppMessage[1] = "Member Id Should be integer!";
				}else{

					bean.setAd_member_id(Integer.parseInt(member_id));
					first.setAd_member_id(Integer.parseInt(member_id));
					second.setAd_member_id(Integer.parseInt(member_id));

					if(map.get("photo")!=null){
						bean.setPhoto(map.get("photo"));
					}else{
						bean.setPhoto("emp.png");
					}

					if(map.get("id")!=null){
						bean.setId_proof(map.get("id"));
					}else{
						bean.setId_proof("id.png");
					}
					if(map.get("sign")!=null){
						bean.setSignature(map.get("sign"));
					}else{
						bean.setSignature("sign.png");
					}

					if(map.get("nominee_photo_1")!=null){
						first.setPhoto(map.get("nominee_photo_1"));

					}else{
						first.setPhoto("emp.png");
					}
					if(map.get("nominee_id_proof_1")!=null){
						first.setId_proof(map.get("nominee_id_proof_1"));

					}else{
						first.setId_proof("id.png");
					}
					if(map.get("nominee_sign_1")!=null){
						first.setSign(map.get("nominee_sign_1"));
					}else{
						first.setSign("sign.png");
					}

					if(map.get("nominee_photo_2")!=null){
						second.setPhoto(map.get("nominee_photo_2"));

					}else{
						second.setPhoto("emp.png");
					}
					if(map.get("nominee_id_proof_2")!=null){
						second.setId_proof(map.get("nominee_id_proof_2"));

					}else{
						second.setId_proof("id.png");
					}
					if(map.get("nominee_sign_2")!=null){
						second.setSign(map.get("nominee_sign_2"));

					}else{
						second.setSign("sign.png");
					}

					if(memberdao.addMemberFileUpload(bean)>0){
						rowsUpdated = 1;
					}

					if(new NominationDao().addNomineeFirstFileUpload(first)>0){
						rowsUpdated = rowsUpdated + 1;
					}


					if(new NominationDao().addNomineeSecondFileUpload(second)>0){
						rowsUpdated = rowsUpdated + 1;
					}
					//second.setAd_member_id(ad_member_id);


					if (rowsUpdated >= 2) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Image successfully Uploaded and send for approval!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}

				}//end validation 

			}catch(Exception e){
				e.printStackTrace();
			}

			session.setAttribute("AppMessage", AppMessage);
			response.sendRedirect("ad_member.jsp");

		}else if (action.equals("update")) {
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

				
					try{

						List formItems = upload.parseRequest(request);
						Iterator iter = formItems.iterator();

						// iterates over form's fields
						while (iter.hasNext()) {
							FileItem item = (FileItem) iter.next();
							// processes only fields that are not form fields
							if (!item.isFormField()) {

								String fileName = new File(item.getName()).getName();
								if(valid.validNotEmpty(fileName)==true && item !=null){
									String filePath = uploadPath + File.separator + fileName;	                    
									File storeFile = new File(filePath);
									//System.out.println(storeFile);
									//System.out.println(item.getName()+"======="+fileName);
									map.put(item.getFieldName(), fileName);
									//saves the file on disk
									storeFile.setReadable(true);
									try{
										item.write(storeFile);
									}catch(Exception e){
										e.printStackTrace();
									}
								}


							}else{
								// Get the  file input parameters
								//System.out.println(item.getFieldName()+"======="+item.getString());
								String fieldName = item.getFieldName();
								String value = item.getString();
								map.put(fieldName, value);
							}
						}//end while

					} catch (FileUploadException e) {
						e.printStackTrace();
					}
			
					String ad_member_id=map.get("ad_member_id");
					//Personal Details
					ad_pf_id =map.get("ad_pf_id");
					ad_society_id =map.get("ad_society_id");
					ad_member_type_id =map.get("ad_member_type_id");
					ad_member_status_id =map.get("ad_member_status_id");
					ad_gender_id =map.get("ad_gender_id");
					ad_category_id =map.get("ad_category_id");
					ad_salutation_id =map.get("ad_salutation_id");
					marital_status=map.get("marital_status");
					name =map.get("name");
					System.out.println(name);
					father =map.get("father");
					// Check if husband parameter exists
					if(map.get("husband").equals(" ")!=true){
						husband =map.get("husband");
					}else{
						husband="NA";
					}				
					dob =map.get("dob");

					//Contact Details -> Present Address
					mobile =map.get("mobile");
					phone =map.get("phone");
					email =map.get("email");
					line1 =map.get("line1");
					line2 =map.get("line2");
					ad_country_id =map.get("ad_country_id");
					ad_state_id =map.get("ad_state_id");
					ad_district_id =map.get("ad_district_id");
					ad_city_id =map.get("ad_city_id");
					pincode =map.get("pincode");
					ad_member_address_id1=map.get("ad_member_address_id1");

					//Contact Details -> Permanent Address
					mobile1 =map.get("mobile1");
					phone1 =map.get("phone1");
					email1 =map.get("email1");
					line1_1 =map.get("line1_1");
					line2_1 =map.get("line2_1");
					ad_country_id_1 =map.get("ad_country_id_1");
					ad_state_id_1 =map.get("ad_state_id_1");
					ad_district_id_1 =map.get("ad_district_id_1");
					ad_city_id_1 =map.get("ad_city_id_1");
					pincode1 =map.get("pincode1");
					ad_member_address_id2=map.get("ad_member_address_id2");

					//Service Details
					ad_branch_id =map.get("ad_branch_id");

					//Service other information
					pan_no = map.get("pan_no");
					aadhar_no = map.get("aadhar_no");
					ad_department_id = map.get("ad_department_id");
					ad_designation_type_id = map.get("ad_designation_type_id");
					ad_designation_id = map.get("ad_designation_id");
					doa   = map.get("doa");
					dor  = map.get("h_dor");
					service_duration = map.get("service_duration");
					saving_ac_no  = map.get("saving_ac_no");

					//Nominee Details
					nominee_ad_relation_id_1   = map.get("nominee_ad_relation_id_1");
					nominee_ad_gender_id_1  = map.get("nominee_ad_gender_id_1");
					nominee_ad_salutation_id_1 = map.get("nominee_ad_salutation_id_1");
					nominee_name_1 = map.get("nominee_name_1");
					nominee_dob_1 = map.get("nominee_dob_1");
					nominee_age_1   = map.get("nominee_age_1");
					nominee_no_1="1";
					ad_nomination_id1= map.get("ad_nomination_id1");
					if(request.getParameterMap().containsKey("guardian1")){
						guardian1 = map.get("guardian1");
					}


					// Check if second_nominee check box option parameter exists
					if(request.getParameterMap().containsKey("second_nominee")){
						second_nominee   = map.get("second_nominee");
						nominee_ad_relation_id_2   = map.get("nominee_ad_relation_id_2");
						nominee_ad_gender_id_2  = map.get("nominee_ad_gender_id_2");
						nominee_ad_salutation_id_2 = map.get("nominee_ad_salutation_id_2");
						nominee_name_2 = map.get("nominee_name_2");
						nominee_dob_2  = map.get("nominee_dob_2");
						nominee_age_2 = map.get("nominee_age_2");
						nominee_no_2="2";
						ad_nomination_id2=map.get("ad_nomination_id2");
						if(request.getParameterMap().containsKey("guardian2")){
							guardian2 = map.get("guardian2");
						}

					}else{

						second_nominee   = "second";
						nominee_ad_relation_id_2   = "1";
						nominee_ad_gender_id_2  = "1";
						nominee_ad_salutation_id_2 = "1";
						nominee_name_2 = "NA";
						nominee_dob_2  = "31/03/2016";
						nominee_age_2 = "0";
						nominee_no_2="2";
						guardian2 = "NA";
					}





					//witness Details
					witness_ad_member_id     = map.get("witness_ad_member_id");
					witness_ad_society_id    = map.get("witness_ad_society_id");
					witness_ad_salutation_id = map.get("witness_ad_salutation_id");
					witness_name 			 = map.get("witness_name");
					witness_mobile  		 = map.get("witness_mobile");
					witness_address 		 = map.get("witness_address");
                    ad_witness_id			 = map.get("ad_witness_id");
					
                    
                    
                    
					/*//Personal Details validation
					if(valid.validDigits(ad_pf_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","PF ID of Personal Tab should be digits only!");
					}else if(valid.validDigits(ad_society_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Member Number of Personal Tab should be digits only!");
					}else if(valid.validNotEmpty(ad_member_type_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Member Type of Personal Tab can not empty!");
					}else if(valid.validDigits(ad_member_type_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Member Type of Personal Tab should be digits only!");
					}else if(valid.validNotEmpty(ad_member_status_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Member status of Personal Tab can not empty!");
					}else if(valid.validDigits(ad_member_status_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Member status of Personal Tab should be digits only!");
					}else if(valid.validNotEmpty(ad_gender_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Gender of Personal Tab can not empty!");
					}else if(valid.validDigits(ad_gender_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Gender of Personal Tab should be digits only!");
					}else if(valid.validDigits(ad_category_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Gender of Personal Tab should be digits only!");
					}else if(valid.validNotEmpty(ad_salutation_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Salutation of Personal Tab can not empty!");
					}else if(valid.validDigits(ad_salutation_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Gender of Personal Tab should be digits only!");
					}else if(valid.validNotEmpty(name)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Member Name of Personal Tab can not empty!");
					}else if(valid.validAlphaSpaceDot(name)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Member Name of Personal Tab can Charecter, space and dot(.) symbol only!");
					}else if(valid.validMinlength(name,1)==false && valid.validMaxlength(name,150)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Member Name of Personal Tab min length and max length shoule be 1 and 150!");
					}else if(valid.validNotEmpty(father)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","father Name of Personal Tab can not empty!");
					}else if(valid.validAlphaSpaceDot(father)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","father Name of Personal Tab can Charecter, space and dot(.) symbol only!");
					}else if(valid.validMinlength(father,1)==false && valid.validMaxlength(name,150)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","father Name of Personal Tab min length and max length shoule be 1 and 150!");
					}else if(valid.validAlphaSpaceDot(husband)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Husband Name of Personal Tab can Charecter, space and dot(.) symbol only!");
					}else if(valid.validMaxlength(name,150)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Husband Name of Personal Tab max length shoule be 150!");
					}else if(valid.validNotEmpty(dob)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Date of birth of Personal Tab can not empty!");
					}else if(valid.validDate(dob, "DD/MM/YYYY")==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Enter valid Date of birth (DD/MM/YYYY) in Personal Details !");
					}
					//Contact Details -> Present Address validation
					else if(valid.validNotEmpty(mobile)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Mobile Number of Contact Tab can not empty!");
					}else if(valid.validDigits(mobile)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Mobile Number of Contact Tab should be digits only!");
					}else if(valid.validMinlength(mobile,10)==false && valid.validMaxlength(mobile,10)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Mobile Number of Contact Tab length should be 10 digits!");
					}else if(valid.validDigits(phone)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Phone Number of Contact Tab should be digits only!");
					}else if(valid.validMaxlength(phone,10)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Phone Number of Contact Tab length should be 10 digits!");
					}else if(valid.validEmail(email)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Enter valid email in Email field of Contact Tab!");
					}else if(valid.validNotEmpty(line1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Street of Contact Tab can not empty");
					}else if(valid.validNotEmpty(line1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Street1 field of Contact Tab can not empty");
					}else if(valid.validAddress(line1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Only / , - and space allowed in Street1 field of Contact Tab!");
					}else if(valid.validMaxlength(line1,100)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Street1 field of Contact Tab length should be 10 digits!");
					}else if(valid.validAddress(line2)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Only / , - and space allowed in Street2 field of Contact Tab!");
					}else if(valid.validMaxlength(line2,100)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Street2 field of Contact Tab length should be 10 digits!");
					}else if(valid.validNotEmpty(ad_country_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Country Id of Personal Tab can not empty!");
					}else if(valid.validDigits(ad_country_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Country Id of Personal Tab should be digits only!");
					}else if(valid.validNotEmpty(ad_district_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","District Id of Personal Tab can not empty!");
					}else if(valid.validDigits(ad_district_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","District Id of Personal Tab should be digits only!");
					}else if(valid.validNotEmpty(ad_city_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","City Id of Personal Tab can not empty!");
					}else if(valid.validDigits(ad_city_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","City Id of Personal Tab should be digits only!");
					}else if(valid.validDigits(pincode)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Pincode field of Personal Tab should be digits only!");
					}else if(valid.validLengthEqual(pincode,6)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Pincode field of Contact Tab length should be 6 digits!");
					}
					//Contact Details -> permanent Address validation
					else if(valid.validNotEmpty(mobile1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Mobile Number (Permanent) of Contact Tab can not empty!");
					}else if(valid.validDigits(mobile1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Mobile Number (Permanent) of Contact Tab should be digits only!");
					}else if(valid.validMinlength(mobile1,10)==false && valid.validMaxlength(mobile,10)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Mobile Number (Permanent) of Contact Tab length should be 10 digits!");
					}else if(valid.validDigits(phone1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Phone Number  (Permanent) of Contact Tab should be digits only!");
					}else if(valid.validMaxlength(phone1,10)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Phone Number  (Permanent) of Contact Tab length should be 10 digits!");
					}else if(valid.validEmail(email1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Enter valid email  in Email  field  (Permanent) of Contact Tab!");
					}else if(valid.validNotEmpty(line1_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Street of Contact Tab can not empty");
					}else if(valid.validNotEmpty(line1_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Street1 field of Contact Tab can not empty");
					}else if(valid.validAddress(line1_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Only / , - and space allowed in Street1 field of Contact Tab!");
					}else if(valid.validMaxlength(line1_1,100)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Street1 field of Contact Tab length should be 100 digits!");
					}else if(valid.validAddress(line2_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Only / , - and space allowed in Street2 field of Contact Tab!");
					}else if(valid.validMaxlength(line2_1,100)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Street2 field of Contact Tab length should be 100 digits!");
					}else if(valid.validNotEmpty(ad_country_id_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Country Id of Personal Tab can not empty!");
					}else if(valid.validDigits(ad_country_id_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Country Id of Personal Tab should be digits only!");
					}else if(valid.validNotEmpty(ad_district_id_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","District Id of Personal Tab can not empty!");
					}else if(valid.validDigits(ad_district_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","District Id of Personal Tab should be digits only!");
					}else if(valid.validNotEmpty(ad_city_id_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","City Id of Personal Tab can not empty!");
					}else if(valid.validDigits(ad_city_id_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","City Id of Personal Tab should be digits only!");
					}else if(valid.validDigits(pincode1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Pincode field of Personal Tab should be digits only!");
					}else if(valid.validLengthEqual(pincode1,6)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Pincode field of Contact Tab length should be 6 digits!");
					}
					//service Details
					else if(valid.validNotEmpty(ad_branch_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Branch Id of Service Tab is required field!");
					}else if(valid.validDigits(ad_branch_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Branch ID of Service Tab should be digits only!");
					}else if(valid.validPancard(pan_no)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Please enter valid pancard of Service Tab!");
					}else if(valid.validDigits(aadhar_no)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Please enter valid pancard of Service Tab!");
					}else if(valid.validLengthEqual(aadhar_no,12)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Adhar number length should be 12!");
					}else if(valid.validNotEmpty(ad_department_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Department Id of Service Tab is required field!");
					}else if(valid.validDigits(ad_department_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Department ID of Service Tab should be digits only!");
					}else if(valid.validNotEmpty(ad_designation_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Designation Id of Service Tab is required field!");
					}else if(valid.validDigits(ad_designation_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Designation ID of Service Tab should be digits only!");
					}else if(valid.validNotEmpty(ad_designation_type_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Designation Type Id of Service Tab is required field!");
					}else if(valid.validDigits(ad_designation_type_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Designation Type ID of Service Tab should be digits only!");
					}else if(valid.validDate(doa, "DD/MM/YYYY")==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Enter valid Appointment date (DD/MM/YYYY) !");
					}else if(valid.validDate(dor, "DD/MM/YYYY")==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Enter valid Retirement date (DD/MM/YYYY) !");
					}else if(valid.validNotEmpty(saving_ac_no)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Account Number of Service Tab is required field!");
					}else if(valid.validDigits(saving_ac_no)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Account Numner of Service Tab should be digits only!");
					}else if(valid.validMinlength(saving_ac_no,11)==false && valid.validMaxlength(saving_ac_no,15)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Max length and min lLnght should be 11 and 15 in Account Numner of Contact Tab!");
					}
					//Nominee 1 validation
					else if(valid.validNotEmpty(nominee_ad_salutation_id_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee salutation field of Nominee Tab is required field!");
					}else if(valid.validDigits(nominee_ad_salutation_id_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee Salutioan field of Nominee Tab should be digits only!");
					}else if(valid.validNotEmpty(nominee_name_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee Name field of Nominee Tab is required field!");
					}else if(valid.validAlphaSpaceDot(nominee_name_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee name field of Nominee Tab should be alphabet and space onlye!");
					}else if(valid.validMaxlength(nominee_name_1,150)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Max length Lnght should be 150 in Nominee Name of Nominee Tab!");
					}else if(valid.validAlphaSpaceDot(guardian1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Use charecter and space only in Gurardian name of Nominee Tab!");
					}else if(valid.validMaxlength(guardian1,150)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Max length of guardian 1 should be 150 in of Nominee Tab!");
					}else if(valid.validNotEmpty(nominee_ad_relation_id_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee relation field of Nominee Tab is required field!");
					}else if(valid.validDigits(nominee_ad_relation_id_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee relation field of Nominee Tab should be digits only!");
					}else if(valid.validDigits(nominee_no_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee No  Nominee Tab should be digits only!");
					}else if(valid.validDigits(nominee_ad_gender_id_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee gender field of Nominee Tab should be digits only!");
					}else if(valid.validDate(nominee_dob_1,"DD/MM/YYYY")==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee Date of birth field of Nominee Tab should be valid Date(DD/MM/YYYY)!");
					}else if(valid.validDigits(nominee_age_1)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee gender field of Nominee Tab should be digits only!");
					}
					//Nominee 2 validation
					else if(valid.validDigits(nominee_ad_salutation_id_2)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee Salutioan 2 field of Nominee Tab should be digits only!");
					}else if(valid.validAlphaSpaceDot(nominee_name_2)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee Name 2 field of Contact Tab should be alphabet and space onlye!");
					}else if(valid.validMaxlength(nominee_name_2,150)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Max length and min lLnght should be 11 and 15 in Account Name 2 of Nominee Tab!");
					}else if(valid.validDigits(nominee_ad_relation_id_2)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee relation 2 field of Nominee Tab should be digits only!");
					}else if(valid.validAlphaSpaceDot(guardian2)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Use charecter and space only in Gurardian name 2 of Nominee Tab!");
					}else if(valid.validMaxlength(guardian2,150)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Max length of guardian name 2 should be 40 in of Nominee Tab!");
					}else if(valid.validDigits(nominee_ad_gender_id_2)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee gender 2 field of Nominee Tab should be digits only!");
					}else if(valid.validDigits(nominee_no_2)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee no  of Nominee Tab should be digits only!");
					}else if(valid.validDate(nominee_dob_2,"DD/MM/YYYY")==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee Date of birth 2 field of Nominee Tab should be valid Date(DD/MM/YYYY)!");
					}else if(valid.validDigits(nominee_age_2)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Nominee gender 2 field of Nominee Tab should be digits only!");
					}
					//Witness validation
					else if(valid.validDigits(witness_ad_member_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Witness Member Id field of Witness Tab should be digits only!");
					}else if(valid.validDigits(witness_ad_society_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Witness Society Id field of Witness Tab should be digits only!");
					}else if(valid.validDigits(witness_ad_salutation_id)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Witness Salutation Id field of Witness Tab should be digits only!");
					}else if(valid.validAlphaSpaceDot(witness_name)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Use charecter ,space and dot only in Witness name  of Witness Tab!");
					}else if(valid.validMaxlength(witness_name,150)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Max length of Witness name should be 150 in of Witness Tab!");
					}else if(valid.validDigits(witness_mobile)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Witness Mobile field of Witness Tab should be digits only!");
					}else if(valid.validMaxlength(witness_mobile,10)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Max length of Witness Mobile Number should be 10 in of Witness Tab!");
					}else if(valid.validAddress(witness_address)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Only / , - and space allowed in witness address field of Witness Tab!");
					}else if(valid.validMaxlength(witness_address,250)==false){
						lstMap.put("message","error");
						lstMap.put("errorClass","alert-warning");
						lstMap.put("errorMessage","Max length of witness address should be 10 in of Witness Tab!");
					}else*/{

						System.out.println("inside member update");
						//--------------Start Member Registration--------------------------
						MemberRegistrationBean bean = new MemberRegistrationBean();

						bean.setCreated(new java.util.Date());
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdated(new java.util.Date());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(true);
						bean.setName(name);
						bean.setFather(father);
						bean.setHusband(husband);
						if(map.get("photo")!=null){
							bean.setPhoto(map.get("photo"));
						}else{
							bean.setPhoto("emp.png");
						}

						if(map.get("id")!=null){
							bean.setId_proof(map.get("id"));
						}else{
							bean.setId_proof("id.png");
						}
						if(map.get("sign")!=null){
							bean.setSignature(map.get("sign"));
						}else{
							bean.setSignature("sign.png");
						}

						
						try{
						bean.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(dob));
						}catch(java.text.ParseException p){
							p.printStackTrace();
						}
						bean.setMarital_status(marital_status);

						CategoryBean category=new CategoryBean();
						category.setAd_category_id(Integer.parseInt(ad_category_id));
						bean.setCategory(category);

						GenderBean gender=new GenderBean();
						gender.setAd_gender_id(Integer.parseInt(ad_gender_id));
						bean.setGender(gender);

						MemberTypeBean type=new MemberTypeBean();
						type.setAd_member_type_id(Integer.parseInt(ad_member_type_id));
						bean.setMember_type(type);

						MemberStatusBean status=new MemberStatusBean();
						status.setAd_member_status_id(Integer.parseInt(ad_member_status_id));
						bean.setMember_status(status);

						SalutationBean salutation=new SalutationBean();
						salutation.setAd_salutation_id(Integer.parseInt(ad_salutation_id));
						bean.setSalutation(salutation);

						BankBranchBean branch=new BankBranchBean();
						branch.setAd_branch_id(Integer.parseInt(ad_branch_id));
						bean.setBranch(branch);

						DesignationTypeBean designation_type=new DesignationTypeBean();
						designation_type.setAd_designation_type_id(Integer.parseInt(ad_designation_type_id));
						bean.setDesignation_type(designation_type);

						DesignationBean designation=new DesignationBean();
						designation.setAd_designation_id(Integer.parseInt(ad_designation_id));
						bean.setDesignation(designation);

						bean.setPan_no(pan_no);
						bean.setAadhar_no(aadhar_no);
						try{
							bean.setDoa(new SimpleDateFormat("dd/MM/yyyy").parse(doa));
							bean.setDoj(new SimpleDateFormat("dd/MM/yyyy").parse(doa));
						}catch(Exception ex1 ){
							bean.setDoa(new SimpleDateFormat("dd/MM/yyyy").parse(default_date));
                            bean.setDoj(new SimpleDateFormat("dd/MM/yyyy").parse(default_date));
						}

						bean.setService_duration(service_duration);

						try{
							bean.setDor(new SimpleDateFormat("dd/MM/yyyy").parse(dor));
						}catch(Exception ex1 ){
							bean.setDor(new SimpleDateFormat("dd/MM/yyyy").parse(default_date));

						}

						DepartmentBean department=new DepartmentBean();
						department.setAd_department_id(Integer.parseInt(ad_department_id));
						bean.setDepartment(department);

						bean.setSaving_ac_no(saving_ac_no);

						if(request.getParameterMap().containsKey("witness_ad_society_id")){
							try{
								bean.setAd_witness_id(Integer.parseInt(witness_ad_member_id));
							}catch(NumberFormatException n9){
								bean.setAd_witness_id(0);
								//System.out.println("NumberFormat Exception in witness member Id");
							}
						}

						bean.setAd_pf_no(Integer.parseInt(ad_society_id));
						bean.setAd_society_no(Integer.parseInt(ad_society_id));

						//-----------------------------Start present address-----------------------------------------------

						MemberAddressBean present=new MemberAddressBean();

						CountryBean country=new CountryBean();
						country.setAd_country_id(Integer.parseInt(ad_country_id));
						present.setCountry(country);

						StateBean state=new StateBean();
						state.setAd_state_id(Integer.parseInt(ad_state_id));
						present.setState(state);

						CityBean city=new CityBean();
						city.setAd_city_id(Integer.parseInt(ad_city_id));
						present.setCity(city);

						DistrictBean district=new DistrictBean();
						district.setAd_district_id(Integer.parseInt(ad_district_id));
						present.setDistrict(district);

						present.setLine1(line1);
						present.setLine2(line2);
						present.setPincode(pincode);
						present.setMobile(mobile);
						present.setPhone(phone);
						present.setType("present");
						present.setCreated(new java.util.Date());
						present.setCreatedby(user.getAd_user_id());
						present.setUpdated(new java.util.Date());
						present.setUpdatedby(user.getAd_user_id());
						present.setIsactive(true);
						present.setEmail(email);
						System.out.println(email);
                        present.setAd_member_address_id(Integer.parseInt(ad_member_address_id1));
						//-------------------------Start permanent address--------------------

						MemberAddressBean permanent=new MemberAddressBean();

						CountryBean countryBn=new CountryBean();
						countryBn.setAd_country_id(Integer.parseInt(ad_country_id_1));
						permanent.setCountry(countryBn);

						StateBean stateBn=new StateBean();
						stateBn.setAd_state_id(Integer.parseInt(ad_state_id_1));
						permanent.setState(stateBn);

						CityBean cityBean1=new CityBean();
						cityBean1.setAd_city_id(Integer.parseInt(ad_city_id_1));
						permanent.setCity(cityBean1);

						DistrictBean districtBean1=new DistrictBean();
						districtBean1.setAd_district_id(Integer.parseInt(ad_district_id_1));
						permanent.setDistrict(districtBean1);

						permanent.setPincode(pincode1);
						permanent.setMobile(mobile1);
						permanent.setPhone(phone1);
						permanent.setLine1(line1_1);
						permanent.setLine2(line2_1);
						permanent.setType("parmanent");					
						permanent.setCreated(new java.util.Date());
						permanent.setCreatedby(user.getAd_user_id());
						permanent.setUpdated(new java.util.Date());
						permanent.setUpdatedby(user.getAd_user_id());
						permanent.setIsactive(true);
						permanent.setEmail(email1);
						System.out.println(email1);
						permanent.setAd_member_address_id(Integer.parseInt(ad_member_address_id2));	
						//-------------first nominee----------------------------------------------
						NominationBean first=new NominationBean();

						RelationBean relation1=new RelationBean();
						relation1.setAd_relation_id(Integer.parseInt(nominee_ad_relation_id_1));
						first.setRelation(relation1);

						GenderBean genderBn=new GenderBean();
						genderBn.setAd_gender_id(Integer.parseInt(nominee_ad_gender_id_1));
						first.setGender(genderBn);

						first.setCreated(new java.util.Date());
						first.setCreatedby(user.getAd_user_id());
						first.setUpdated(new java.util.Date());
						first.setUpdatedby(user.getAd_user_id());
						if(map.get("nominee_photo_1")!=null){
							first.setPhoto(map.get("nominee_photo_1"));

						}else{
							first.setPhoto("emp.png");
						}
						if(map.get("nominee_id_proof_1")!=null){
							first.setId_proof(map.get("nominee_id_proof_1"));

						}else{
							first.setId_proof("id.png");
						}
						if(map.get("nominee_sign_1")!=null){
							first.setSign(map.get("nominee_sign_1"));
						}else{
							first.setSign("sign.png");
						}

						
						SalutationBean salutation1=new SalutationBean();
						salutation1.setAd_salutation_id(Integer.parseInt(nominee_ad_salutation_id_1));
						first.setSalutation(salutation);
						first.setNominee_no(Integer.parseInt(nominee_no_1));
						first.setIsactive(true);
						first.setName(nominee_name_1);
						first.setAd_nomination_id(Integer.parseInt(ad_nomination_id1));
						try{
							first.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(nominee_dob_1));
						}catch(Exception ex5 ){
							first.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(default_date));
							//System.out.println("DateFormat parse Exception in nominee_dob_1");
						}
						//if(request.getParameterMap().containsKey("nominee_age_2")){
							if(Integer.parseInt(nominee_age_1)>18){
								first.setAge(nominee_age_1);
								first.setGuardian("NA");
							}else{
								first.setAge(nominee_age_1);
								first.setGuardian(guardian1);
							}
						//}


						//---------------------second nominee-----------------------

						NominationBean second=new NominationBean();


						RelationBean relationBn=new RelationBean();
						relationBn.setAd_relation_id(Integer.parseInt(nominee_ad_relation_id_2));
						second.setRelation(relationBn);

						GenderBean gender1=new GenderBean();
						gender1.setAd_gender_id(Integer.parseInt(nominee_ad_gender_id_2));
						second.setGender(gender1);

						second.setCreated(new java.util.Date());
						second.setCreatedby(user.getAd_user_id());
						second.setUpdated(new java.util.Date());
						second.setUpdatedby(user.getAd_user_id());
						second.setNominee_no(Integer.parseInt(nominee_no_2));
						second.setAd_nomination_id(Integer.parseInt(ad_nomination_id2));
						if(map.get("nominee_photo_2")!=null){
							second.setPhoto(map.get("nominee_photo_2"));

						}else{
							second.setPhoto("emp.png");
						}
						if(map.get("nominee_id_proof_2")!=null){
							second.setId_proof(map.get("nominee_id_proof_2"));

						}else{
							second.setId_proof("id.png");
						}
						if(map.get("nominee_sign_2")!=null){
							second.setSign(map.get("nominee_sign_2"));

						}else{
							second.setSign("sign.png");
						}


						SalutationBean salutationBn=new SalutationBean();
						salutationBn.setAd_salutation_id(Integer.parseInt(nominee_ad_salutation_id_2));
						second.setSalutation(salutationBn);
						second.setIsactive(true);
						second.setName(nominee_name_2);

						try{
							second.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(nominee_dob_2));
						}catch(Exception ex3 ){
							second.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(default_date));
							//System.out.println("DateFormat parse Exception in nominee dob2");
						}


						if(Integer.parseInt(nominee_age_2)>18){
							second.setAge(nominee_age_2);
							second.setGuardian("NA");
						}else{
							second.setAge(nominee_age_2);
							second.setGuardian(guardian2);
						}





						int ad_member_pk=Integer.parseInt(map.get("ad_member_id"));
						bean.setAd_member_id(ad_member_pk);
						System.out.println("before update method call");
						int _id  = memberdao.updateMemberRegistration(bean);
						System.out.println("after update method call");

						if(_id>0){
							rowcount= 1;
						}
						//new MemberRegistrationDao().getMemberRegistrationMaxId();

						lstMap.put("dataId",String.valueOf(ad_member_id));


						present.setAd_member_id(ad_member_pk);
						permanent.setAd_member_id(ad_member_pk);
						first.setAd_member_id(ad_member_pk);
						second.setAd_member_id(ad_member_pk);


						//----------------address registration-------------------------------------------------------------

						new MemberAddressDao().updateMemberAddress(present);
						new MemberAddressDao().updateMemberAddress(permanent);
						//----------------Nominee registration-------------------------------------------------------------

						new NominationDao().updateNomination(first);
						new NominationDao().updateNomination(second);


						//----------------Witness registration-------------------------------------------------------------

						WitnessBean beanWitness = new WitnessBean();
						
						beanWitness.setAd_witness_id(Integer.parseInt(ad_witness_id));
						beanWitness.setAd_witness_mem_no(Integer.parseInt(witness_ad_society_id));
						beanWitness.setAd_salutation_id(Integer.parseInt(ad_salutation_id));
						beanWitness.setAd_witness_name(witness_name);
						beanWitness.setAd_witness_mobile(witness_mobile);
						beanWitness.setAd_witness_address(witness_address);
	                    beanWitness.setAd_member_id(Integer.parseInt(ad_member_id));
	                    beanWitness.setCreated(new java.util.Date());
	                    beanWitness.setCreatedby(user.getAd_user_id());
	                    beanWitness.setUpdated(new java.util.Date());
	                    beanWitness.setUpdatedby(user.getAd_user_id());
	                    beanWitness.setIsactive(true);


						rowcount =new WitnessDao().updateWitness(beanWitness);

						//------------------------------if success
						if(rowcount>0){
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Record Updated Successfully ..!!!";
							
						}else{
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Sorry Try Again Later ..!!!";
							
						}
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("ad_member.jsp");
						
						
					}


				}catch(Exception ex ){
	                ex.printStackTrace();
					StringWriter errors = new StringWriter();
					ex.printStackTrace(new PrintWriter(errors));
					lstMap.put("message","error");
					lstMap.put("errorMessage",errors.toString());

				}

		}else if(action.equals("insert")){

			int batchn=0;
			int sharenofrom=0;
			int sharenoto=0;

			try{

				//photo =map.get("photo");
				//id_proof =map.get("id_proof");
				//sign =map.get("sign");
				//nominee_photo_1 =map.get("nominee_photo_1");
				//nominee_id_proof_1 =map.get("nominee_id_proof_1");
				//nominee_sign_1 =map.get("nominee_sign_1");
				//nominee_photo_2 =map.get("nominee_photo_2");
				//nominee_id_proof_2 =map.get("nominee_id_proof_2");
				//nominee_sign_2 =map.get("nominee_sign_2");

				//Personal Details
				ad_pf_id =request.getParameter("ad_pf_id");
				ad_society_id =request.getParameter("h_ad_society_id");
				ad_member_type_id =request.getParameter("ad_member_type_id");
				ad_member_status_id =request.getParameter("ad_member_status_id");
				ad_gender_id =request.getParameter("ad_gender_id");
				ad_category_id =request.getParameter("ad_category_id");
				ad_salutation_id =request.getParameter("ad_salutation_id");
				name =request.getParameter("name");
				father =request.getParameter("father");
				marital_status=request.getParameter("marital_status");
				// Check if husband parameter exists
				if(request.getParameterMap().containsKey("husband")){
					husband =request.getParameter("husband");
				}				
				dob =request.getParameter("dob");

				//Contact Details -> Present Address
				mobile =request.getParameter("mobile");
				phone =request.getParameter("phone");
				email =request.getParameter("email");
				line1 =request.getParameter("line1");
				line2 =request.getParameter("line2");
				ad_country_id =request.getParameter("ad_country_id");
				ad_state_id =request.getParameter("ad_state_id");
				ad_district_id =request.getParameter("ad_district_id");
				ad_city_id =request.getParameter("ad_city_id");
				pincode =request.getParameter("pincode");

				//Contact Details -> Permanent Address
				mobile1 =request.getParameter("mobile1");
				phone1 =request.getParameter("phone1");
				email1 =request.getParameter("email1");
				line1_1 =request.getParameter("line1_1");
				line2_1 =request.getParameter("line2_1");
				ad_country_id_1 =request.getParameter("ad_country_id_1");
				ad_state_id_1 =request.getParameter("ad_state_id_1");
				ad_district_id_1 =request.getParameter("ad_district_id_1");
				ad_city_id_1 =request.getParameter("ad_city_id_1");
				pincode1 =request.getParameter("pincode1");

				//Service Details
				ad_branch_id =request.getParameter("branch_id");

				//Service other information
				pan_no = request.getParameter("pan_no");
				aadhar_no = request.getParameter("aadhar_no");
				ad_department_id = request.getParameter("ad_department_id");
				ad_designation_type_id = request.getParameter("ad_designation_type_id");
				ad_designation_id = request.getParameter("ad_designation_id");
				doa   = request.getParameter("doa");
				dor  = request.getParameter("h_dor");
				service_duration = request.getParameter("h_service_duration");
				saving_ac_no  = request.getParameter("saving_ac_no");

				//Nominee Details
				nominee_ad_relation_id_1   = request.getParameter("nominee_ad_relation_id_1");
				nominee_ad_gender_id_1  = request.getParameter("nominee_ad_gender_id_1");
				nominee_ad_salutation_id_1 = request.getParameter("nominee_ad_salutation_id_1");
				nominee_name_1 = request.getParameter("nominee_name_1");
				nominee_dob_1 = request.getParameter("nominee_dob_1");
				nominee_age_1   = request.getParameter("nominee_age_1");
				
				if(request.getParameterMap().containsKey("guardian1")){
					guardian1 = request.getParameter("guardian1");
				}


				// Check if second_nominee check box option parameter exists
				if(request.getParameterMap().containsKey("second_nominee")){
					second_nominee   = request.getParameter("second_nominee");
					second_nominee   = request.getParameter("second_nominee");
					nominee_ad_relation_id_2   = request.getParameter("nominee_ad_relation_id_2");
					nominee_ad_gender_id_2  = request.getParameter("nominee_ad_gender_id_2");
					nominee_ad_salutation_id_2 = request.getParameter("nominee_ad_salutation_id_2");
					nominee_name_2 = request.getParameter("nominee_name_2");
					nominee_dob_2  = request.getParameter("nominee_dob_2");
					nominee_age_2 = request.getParameter("nominee_age_2");
					if(request.getParameterMap().containsKey("guardian2")){
						guardian2 = request.getParameter("guardian2");
					}

				}else{

					second_nominee   = "second";
					nominee_ad_relation_id_2   = "1";
					nominee_ad_gender_id_2  = "1";
					nominee_ad_salutation_id_2 = "1";
					nominee_name_2 = "NA";
					nominee_dob_2  = "31/03/2016";
					nominee_age_2 = "0";
					nominee_no_2="2";
					guardian2 = "NA";
				}


				//witness Details
				witness_ad_member_id     = request.getParameter("witness_ad_member_id");
				witness_ad_society_id    = request.getParameter("witness_ad_society_id");
				witness_ad_salutation_id = request.getParameter("witness_ad_salutation_id");
				witness_name 			 = request.getParameter("witness_name");
				witness_mobile  		 = request.getParameter("witness_mobile");
				witness_address 		 = request.getParameter("witness_address");

				//Fee Details
				membership_fees 		 = request.getParameter("h_membership_fees");
				fgds_fund 				 = request.getParameter("h_fgds_fund");
				dcf 					 = request.getParameter("h_dcf");
				share_qty 		         = request.getParameter("h_share_qty");
				share_amt	 	         = request.getParameter("h_share_amt");
				admission_fees  		 = request.getParameter("h_admission_fees");

				//payment information
				vtype =request.getParameter("vtype");
				ins_form =request.getParameter("ins_form");
				ins_no =request.getParameter("ins_no");
				ins_date =request.getParameter("ins_date");
				h_v_amt =request.getParameter("h_v_amt");;
				voucher_type =request.getParameter("voucher_type");

				//Personal Details validation
				if(valid.validDigits(ad_pf_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","PF ID of Personal Tab should be digits only!");
				}else if(valid.validDigits(ad_society_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Member Number of Personal Tab should be digits only!");
				}else if(valid.validNotEmpty(ad_member_type_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Member Type of Personal Tab can not empty!");
				}else if(valid.validDigits(ad_member_type_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Member Type of Personal Tab should be digits only!");
				}else if(valid.validNotEmpty(ad_member_status_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Member status of Personal Tab can not empty!");
				}else if(valid.validDigits(ad_member_status_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Member status of Personal Tab should be digits only!");
				}else if(valid.validNotEmpty(ad_gender_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Gender of Personal Tab can not empty!");
				}else if(valid.validDigits(ad_gender_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Gender of Personal Tab should be digits only!");
				}else if(valid.validDigits(ad_category_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Gender of Personal Tab should be digits only!");
				}else if(valid.validNotEmpty(ad_salutation_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Salutation of Personal Tab can not empty!");
				}else if(valid.validDigits(ad_salutation_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Gender of Personal Tab should be digits only!");
				}else if(valid.validNotEmpty(name)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Member Name of Personal Tab can not empty!");
				}else if(valid.validAlphaSpaceDot(name)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Member Name of Personal Tab can Charecter, space and dot(.) symbol only!");
				}else if(valid.validMinlength(name,1)==false && valid.validMaxlength(name,150)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Member Name of Personal Tab min length and max length shoule be 1 and 150!");
				}else if(valid.validNotEmpty(father)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","father Name of Personal Tab can not empty!");
				}else if(valid.validAlphaSpaceDot(father)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","father Name of Personal Tab can Charecter, space and dot(.) symbol only!");
				}else if(valid.validMinlength(father,1)==false && valid.validMaxlength(name,150)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","father Name of Personal Tab min length and max length shoule be 1 and 150!");
				}else if(valid.validAlphaSpaceDot(husband)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Husband Name of Personal Tab can Charecter, space and dot(.) symbol only!");
				}else if(valid.validMaxlength(name,150)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Husband Name of Personal Tab max length shoule be 150!");
				}else if(valid.validNotEmpty(dob)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Date of birth of Personal Tab can not empty!");
				}else if(valid.validDate(dob, "DD/MM/YYYY")==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Enter valid Date of birth (DD/MM/YYYY) in Personal Details !");
				}
				//Contact Details -> Present Address validation
				else if(valid.validNotEmpty(mobile)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Mobile Number of Contact Tab can not empty!");
				}else if(valid.validDigits(mobile)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Mobile Number of Contact Tab should be digits only!");
				}else if(valid.validMinlength(mobile,10)==false && valid.validMaxlength(mobile,10)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Mobile Number of Contact Tab length should be 10 digits!");
				}else if(valid.validDigits(phone)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Phone Number of Contact Tab should be digits only!");
				}else if(valid.validMaxlength(phone,10)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Phone Number of Contact Tab length should be 10 digits!");
				}/*else if(valid.validEmail(email)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Enter valid email in Email field of Contact Tab!");
				}*/else if(valid.validNotEmpty(line1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Street of Contact Tab can not empty");
				}else if(valid.validNotEmpty(line1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Street1 field of Contact Tab can not empty");
				}else if(valid.validAddress(line1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Only / , - and space allowed in Street1 field of Contact Tab!");
				}else if(valid.validMaxlength(line1,100)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Street1 field of Contact Tab length should be 10 digits!");
				}else if(valid.validAddress(line2)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Only / , - and space allowed in Street2 field of Contact Tab!");
				}else if(valid.validMaxlength(line2,100)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Street2 field of Contact Tab length should be 10 digits!");
				}else if(valid.validNotEmpty(ad_country_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Country Id of Personal Tab can not empty!");
				}else if(valid.validDigits(ad_country_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Country Id of Personal Tab should be digits only!");
				}else if(valid.validNotEmpty(ad_district_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","District Id of Personal Tab can not empty!");
				}else if(valid.validDigits(ad_district_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","District Id of Personal Tab should be digits only!");
				}else if(valid.validNotEmpty(ad_city_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","City Id of Personal Tab can not empty!");
				}else if(valid.validDigits(ad_city_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","City Id of Personal Tab should be digits only!");
				}else if(valid.validDigits(pincode)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Pincode field of Personal Tab should be digits only!");
				}else if(valid.validLengthEqual(pincode,6)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Pincode field of Contact Tab length should be 6 digits!");
				}
				//Contact Details -> permanent Address validation
				else if(valid.validNotEmpty(mobile1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Mobile Number (Permanent) of Contact Tab can not empty!");
				}else if(valid.validDigits(mobile1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Mobile Number (Permanent) of Contact Tab should be digits only!");
				}else if(valid.validMinlength(mobile1,10)==false && valid.validMaxlength(mobile,10)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Mobile Number (Permanent) of Contact Tab length should be 10 digits!");
				}else if(valid.validDigits(phone1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Phone Number  (Permanent) of Contact Tab should be digits only!");
				}else if(valid.validMaxlength(phone1,10)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Phone Number  (Permanent) of Contact Tab length should be 10 digits!");
				}/*else if(valid.validEmail(email1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Enter valid email  in Email  field  (Permanent) of Contact Tab!");
				}*/else if(valid.validNotEmpty(line1_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Street of Contact Tab can not empty");
				}else if(valid.validNotEmpty(line1_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Street1 field of Contact Tab can not empty");
				}else if(valid.validAddress(line1_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Only / , - and space allowed in Street1 field of Contact Tab!");
				}else if(valid.validMaxlength(line1_1,100)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Street1 field of Contact Tab length should be 100 digits!");
				}else if(valid.validAddress(line2_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Only / , - and space allowed in Street2 field of Contact Tab!");
				}else if(valid.validMaxlength(line2_1,100)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Street2 field of Contact Tab length should be 100 digits!");
				}else if(valid.validNotEmpty(ad_country_id_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Country Id of Personal Tab can not empty!");
				}else if(valid.validDigits(ad_country_id_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Country Id of Personal Tab should be digits only!");
				}else if(valid.validNotEmpty(ad_district_id_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","District Id of Personal Tab can not empty!");
				}else if(valid.validDigits(ad_district_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","District Id of Personal Tab should be digits only!");
				}else if(valid.validNotEmpty(ad_city_id_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","City Id of Personal Tab can not empty!");
				}else if(valid.validDigits(ad_city_id_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","City Id of Personal Tab should be digits only!");
				}else if(valid.validDigits(pincode1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Pincode field of Personal Tab should be digits only!");
				}else if(valid.validLengthEqual(pincode1,6)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Pincode field of Contact Tab length should be 6 digits!");
				}
				//service Details
				else if(valid.validNotEmpty(ad_branch_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Branch Id of Service Tab is required field!");
				}else if(valid.validDigits(ad_branch_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Branch ID of Service Tab should be digits only!");
				}else if(valid.validPancard(pan_no)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Please enter valid pancard of Service Tab!");
				}else if(valid.validDigits(aadhar_no)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Please enter valid pancard of Service Tab!");
				}else if(valid.validLengthEqual(aadhar_no,12)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Adhar number length should be 12!");
				}else if(valid.validNotEmpty(ad_department_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Department Id of Service Tab is required field!");
				}else if(valid.validDigits(ad_department_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Department ID of Service Tab should be digits only!");
				}else if(valid.validNotEmpty(ad_designation_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Designation Id of Service Tab is required field!");
				}else if(valid.validDigits(ad_designation_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Designation ID of Service Tab should be digits only!");
				}else if(valid.validNotEmpty(ad_designation_type_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Designation Type Id of Service Tab is required field!");
				}else if(valid.validDigits(ad_designation_type_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Designation Type ID of Service Tab should be digits only!");
				}else if(valid.validDate(doa, "DD/MM/YYYY")==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Enter valid Appointment date (DD/MM/YYYY) !");
				}else if(valid.validDate(dor, "DD/MM/YYYY")==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Enter valid Retirement date (DD/MM/YYYY) !");
				}else if(valid.validNotEmpty(saving_ac_no)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Account Number of Service Tab is required field!");
				}else if(valid.validDigits(saving_ac_no)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Account Numner of Service Tab should be digits only!");
				}else if(valid.validMinlength(saving_ac_no,11)==false && valid.validMaxlength(saving_ac_no,15)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Max length and min lLnght should be 11 and 15 in Account Numner of Contact Tab!");
				}
				//Nominee 1 validation
				else if(valid.validNotEmpty(nominee_ad_salutation_id_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee salutation field of Nominee Tab is required field!");
				}else if(valid.validDigits(nominee_ad_salutation_id_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee Salutioan field of Nominee Tab should be digits only!");
				}else if(valid.validNotEmpty(nominee_name_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee Name field of Nominee Tab is required field!");
				}else if(valid.validNotEmpty(nominee_name_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee Name field of Nominee Tab is required field!");
				}else if(valid.validAlphaSpace(nominee_name_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee name field of Nominee Tab should be alphabet and space onlye!");
				}else if(valid.validMaxlength(nominee_name_1,150)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Max length Lnght should be 150 in Nominee Name of Nominee Tab!");
				}else if(valid.validAlphaSpace(guardian1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Use charecter and space only in Gurardian name of Nominee Tab!");
				}else if(valid.validMaxlength(guardian1,150)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Max length of guardian 1 should be 150 in of Nominee Tab!");
				}else if(valid.validNotEmpty(nominee_ad_relation_id_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee relation field of Nominee Tab is required field!");
				}else if(valid.validDigits(nominee_ad_relation_id_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee relation field of Nominee Tab should be digits only!");
				}else if(valid.validDigits(nominee_ad_gender_id_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee gender field of Nominee Tab should be digits only!");
				}else if(valid.validDate(nominee_dob_1,"DD/MM/YYYY")==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee Date of birth field of Nominee Tab should be valid Date(DD/MM/YYYY)!");
				}else if(valid.validDigits(nominee_age_1)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee gender field of Nominee Tab should be digits only!");
				}
				//Nominee 2 validation
				else if(valid.validDigits(nominee_ad_salutation_id_2)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee Salutioan 2 field of Nominee Tab should be digits only!");
				}else if(valid.validAlphaSpace(nominee_name_2)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee Name 2 field of Contact Tab should be alphabet and space onlye!");
				}else if(valid.validMaxlength(nominee_name_2,150)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Max length and min lLnght should be 11 and 15 in Account Name 2 of Nominee Tab!");
				}else if(valid.validDigits(nominee_ad_relation_id_2)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee relation 2 field of Nominee Tab should be digits only!");
				}else if(valid.validAlphaSpace(guardian2)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Use charecter and space only in Gurardian name 2 of Nominee Tab!");
				}else if(valid.validMaxlength(guardian2,150)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Max length of guardian name 2 should be 40 in of Nominee Tab!");
				}else if(valid.validDigits(nominee_ad_gender_id_2)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee gender 2 field of Nominee Tab should be digits only!");
				}else if(valid.validDate(nominee_dob_2,"DD/MM/YYYY")==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee Date of birth 2 field of Nominee Tab should be valid Date(DD/MM/YYYY)!");
				}else if(valid.validDigits(nominee_age_2)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Nominee gender 2 field of Nominee Tab should be digits only!");
				}
				//Witness validation
				else if(valid.validDigits(witness_ad_member_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Witness Member Id field of Witness Tab should be digits only!");
				}else if(valid.validDigits(witness_ad_society_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Witness Society Id field of Witness Tab should be digits only!");
				}else if(valid.validDigits(witness_ad_salutation_id)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Witness Salutation Id field of Witness Tab should be digits only!");
				}else if(valid.validAlphaSpaceDot(witness_name)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Use charecter and space only in Witness name 2 of Witness Tab!");
				}else if(valid.validMaxlength(witness_name,150)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Max length of Witness name should be 150 in of Witness Tab!");
				}else if(valid.validDigits(witness_mobile)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Witness Mobile field of Witness Tab should be digits only!");
				}else if(valid.validMaxlength(witness_mobile,10)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Max length of Witness Mobile Number should be 10 in of Witness Tab!");
				}else if(valid.validAddress(witness_address)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Only / , - and space allowed in witness address field of Witness Tab!");
				}else if(valid.validMaxlength(witness_address,250)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Max length of witness address should be 10 in of Witness Tab!");
				}
				//payment validation
				else if(valid.validNotEmpty(vtype)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Transaction type field of Payment Tab is required field!");
				}else if(valid.validAlphabet(vtype)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Enter only Charecter only in Transaction type field of Payment Tab!");
				}/*else if(valid.validAlphaSpace(ins_form)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Enter only Charecter and space only in Instrument Form field of Payment Tab!");
				}*/else if(valid.validNotEmpty(ins_no)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Instrument No field of Payment Tab is required field!");
				}else if(valid.validNumeric(ins_no)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Instrument No field of Payment Tab should be numeric only!");
				}else if(valid.validMinlength(ins_no,6)==false && valid.validMaxlength(saving_ac_no,12)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Max length and min lLnght should be 6 and 12 in Instrument No field of Payment Tab!");
				}else if(valid.validNotEmpty(ins_date)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Instrument Date field of Payment Tab is required field!");
				}else if(valid.validDate(ins_date,"DD/MM/YYYY")==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Enter valid date formate(DD/MM/YYYY) in Instrument Date field of Payment Tab !");
				}else if(valid.validNumeric(h_v_amt)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Voucher Amount field of Payment Tab should be numeric only!");
				}else if(valid.validAlphabet(voucher_type)==false){
					lstMap.put("message","error");
					lstMap.put("errorClass","alert-warning");
					lstMap.put("errorMessage","Enter only Charecter and space only in Instrument Form field of Payment Tab!");
				}else{

					//--------------Start Member Registration--------------------------
					MemberRegistrationBean bean = new MemberRegistrationBean();

					bean.setCreated(new java.util.Date());
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdated(new java.util.Date());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(true);
					bean.setName(name);
					bean.setFather(father);
					bean.setHusband(husband);
					bean.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(dob));
					bean.setMarital_status(marital_status);
					CategoryBean category=new CategoryBean();
					try{
						category.setAd_category_id(Integer.parseInt(ad_category_id));
					}catch(NumberFormatException n1){
						category.setAd_category_id(0);
						//System.out.println("NumberFormat Exception in Category Id");
					}

					bean.setCategory(category);

					GenderBean gender=new GenderBean();
					try{
						gender.setAd_gender_id(Integer.parseInt(ad_gender_id));
					}catch(NumberFormatException n2){
						gender.setAd_gender_id(0);
						//System.out.println("NumberFormat Exception in gender Id");
					}
					bean.setGender(gender);

					MemberTypeBean type=new MemberTypeBean();
					try{
						type.setAd_member_type_id(Integer.parseInt(ad_member_type_id));
					}catch(NumberFormatException n3){
						type.setAd_member_type_id(0);
						//System.out.println("NumberFormat Exception in Member type Id");
					}
					bean.setMember_type(type);

					MemberStatusBean status=new MemberStatusBean();
					try{
						status.setAd_member_status_id(Integer.parseInt(ad_member_status_id));
					}catch(NumberFormatException n4){
						status.setAd_member_status_id(0);
						//System.out.println("NumberFormat Exception in Member status Id");
					}
					bean.setMember_status(status);

					SalutationBean salutation=new SalutationBean();
					try{
						salutation.setAd_salutation_id(Integer.parseInt(ad_salutation_id));
					}catch(NumberFormatException n5){
						salutation.setAd_salutation_id(0);
						//System.out.println("NumberFormat Exception in Salutation Id");
					}
					bean.setSalutation(salutation);

					BankBranchBean branch=new BankBranchBean();
					try{
						branch.setAd_branch_id(Integer.parseInt(ad_branch_id));
					}catch(NumberFormatException n6){
						branch.setAd_branch_id(0);
						//System.out.println("NumberFormat Exception in branch Id");
					}
					bean.setBranch(branch);

					DesignationTypeBean designation_type=new DesignationTypeBean();
					try{
						designation_type.setAd_designation_type_id(Integer.parseInt(ad_designation_type_id));
					}catch(NumberFormatException n7){
						designation_type.setAd_designation_type_id(0);
						//System.out.println("NumberFormat Exception in designation type Id");
					}
					bean.setDesignation_type(designation_type);

					DesignationBean designation=new DesignationBean();
					try{
						designation.setAd_designation_id(Integer.parseInt(ad_designation_id));
					}catch(NumberFormatException n8){
						//System.out.println("NumberFormat Exception in designation Id");
					}
					bean.setDesignation(designation);

					bean.setPan_no(pan_no);
					bean.setAadhar_no(aadhar_no);

					if(request.getParameterMap().containsKey("doa")){
						try{
							bean.setDoa(new SimpleDateFormat("dd/MM/yyyy").parse(doa));
						}catch(Exception ex1 ){
							bean.setDoa(new SimpleDateFormat("dd/MM/yyyy").parse(default_date));
							//System.out.println("DateFormat parse Exception in doa"+doa);
						}
					}

					bean.setService_duration(service_duration);

					try{
						bean.setDor(new SimpleDateFormat("dd/MM/yyyy").parse(dor));
					}catch(Exception ex1 ){
						bean.setDor(new SimpleDateFormat("dd/MM/yyyy").parse(default_date));
						//System.out.println("DateFormat parse Exception in doa");
					}

					DepartmentBean department=new DepartmentBean();
					department.setAd_department_id(Integer.parseInt(ad_department_id));
					bean.setDepartment(department);

					bean.setSaving_ac_no(saving_ac_no);

					if(request.getParameterMap().containsKey("witness_ad_society_id")){
						try{
							bean.setAd_witness_id(Integer.parseInt(witness_ad_member_id));
						}catch(NumberFormatException n9){
							bean.setAd_witness_id(0);
							//System.out.println("NumberFormat Exception in witness member Id");
						}
					}

					try{
						bean.setAd_pf_no(Integer.parseInt(ad_pf_id));
					}catch(NumberFormatException n8){
						//System.out.println("NumberFormat Exception in pf Id");
					}

					try{
						bean.setAd_society_no(Integer.parseInt(ad_society_id));
					}catch(NumberFormatException n8){
						//System.out.println("NumberFormat Exception in society Id");
					}

					//-----------------------------Start present address-----------------------------------------------

					MemberAddressBean present=new MemberAddressBean();

					CountryBean country=new CountryBean();
					country.setAd_country_id(Integer.parseInt(ad_country_id));
					present.setCountry(country);

					StateBean state=new StateBean();
					state.setAd_state_id(Integer.parseInt(ad_state_id));
					present.setState(state);

					CityBean city=new CityBean();
					city.setAd_city_id(Integer.parseInt(ad_city_id));
					present.setCity(city);

					DistrictBean district=new DistrictBean();
					district.setAd_district_id(Integer.parseInt(ad_district_id));
					present.setDistrict(district);

					present.setLine1(line1);
					present.setLine2(line2);
					present.setPincode(pincode);
					present.setMobile(mobile);
					present.setPhone(phone);
					present.setType("present");
					present.setCreated(new java.util.Date());
					present.setCreatedby(user.getAd_user_id());
					present.setUpdated(new java.util.Date());
					present.setUpdatedby(user.getAd_user_id());
					present.setIsactive(true);
					present.setEmail(email);

					//-------------------------Start permanent address--------------------

					MemberAddressBean permanent=new MemberAddressBean();

					CountryBean countryBn=new CountryBean();
					countryBn.setAd_country_id(Integer.parseInt(ad_country_id_1));
					permanent.setCountry(countryBn);

					StateBean stateBn=new StateBean();
					stateBn.setAd_state_id(Integer.parseInt(ad_state_id_1));
					permanent.setState(stateBn);

					CityBean cityBean1=new CityBean();
					cityBean1.setAd_city_id(Integer.parseInt(ad_city_id_1));
					permanent.setCity(cityBean1);

					DistrictBean districtBean1=new DistrictBean();
					districtBean1.setAd_district_id(Integer.parseInt(ad_district_id_1));
					permanent.setDistrict(districtBean1);

					permanent.setPincode(pincode1);
					permanent.setMobile(mobile1);
					permanent.setPhone(phone1);
					permanent.setLine1(line1_1);
					permanent.setLine2(line2_1);
					permanent.setType("parmanent");					
					permanent.setCreated(new java.util.Date());
					permanent.setCreatedby(user.getAd_user_id());
					permanent.setUpdated(new java.util.Date());
					permanent.setUpdatedby(user.getAd_user_id());
					permanent.setIsactive(true);
					permanent.setEmail(email1);

					//-------------first nominee----------------------------------------------
					NominationBean first=new NominationBean();

					RelationBean relation1=new RelationBean();
					relation1.setAd_relation_id(Integer.parseInt(nominee_ad_relation_id_1));
					first.setRelation(relation1);

					GenderBean genderBn=new GenderBean();
					genderBn.setAd_gender_id(Integer.parseInt(nominee_ad_gender_id_1));
					first.setGender(genderBn);

					first.setCreated(new java.util.Date());
					first.setCreatedby(user.getAd_user_id());
					first.setUpdated(new java.util.Date());
					first.setUpdatedby(user.getAd_user_id());

					SalutationBean salutation1=new SalutationBean();
					salutation1.setAd_salutation_id(Integer.parseInt(nominee_ad_salutation_id_1));
					first.setSalutation(salutation);

					first.setIsactive(true);
					first.setName(nominee_name_1);
					try{
						first.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(nominee_dob_1));
					}catch(Exception ex5 ){
						first.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(default_date));
						//System.out.println("DateFormat parse Exception in nominee_dob_1");
					}
					first.setAge(nominee_age_1);
					

					//---------------------second nominee-----------------------

					NominationBean second=new NominationBean();


					if(second_nominee.equals("second")){


						RelationBean relationBn=new RelationBean();
						relationBn.setAd_relation_id(Integer.parseInt(nominee_ad_relation_id_2));
						second.setRelation(relationBn);


						GenderBean gender1=new GenderBean();
						gender1.setAd_gender_id(Integer.parseInt(nominee_ad_gender_id_2));
						second.setGender(gender1);


						second.setCreated(new java.util.Date());
						second.setCreatedby(user.getAd_user_id());
						second.setUpdated(new java.util.Date());
						second.setUpdatedby(user.getAd_user_id());


						SalutationBean salutationBn=new SalutationBean();
						salutationBn.setAd_salutation_id(Integer.parseInt(nominee_ad_salutation_id_2));
						second.setSalutation(salutationBn);

						second.setIsactive(true);
						second.setName(nominee_name_2);
						second.setAge(nominee_age_2);
						try{
							second.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(nominee_dob_2));
						}catch(Exception ex3 ){
							second.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(default_date));
							//System.out.println("DateFormat parse Exception in nominee dob2");
						}







					}//end check second Nominee exists or not



					//----------------------------ac voucher-----------------------------------------------------------

					mfee=Double.parseDouble(membership_fees);
					fgdsfee=Double.parseDouble(fgds_fund);
					//dcfee=Double.parseDouble(dcf);
					shareamt=Double.parseDouble(share_amt);
					//adfees=Double.parseDouble(admission_fees);

					VoucherBean vbean = new VoucherBean();					
					vbean.setCreatedby(user.getAd_user_id());
					vbean.setUpdatedby(user.getAd_user_id());
					vbean.setDescription("new_member");
					vbean.setStatus("Pending");
					vbean.setVamt((mfee+fgdsfee+dcfee+shareamt));
					vbean.setVtype(vtype);
					vbean.setVoucher_type("New Member");
					vbean.setVfrom("New Member");
					vbean.setIsactive(true);				
					Date d=(Date)request.getSession().getAttribute("openday");
					vbean.setTrx_date(d);
					Date d1=null;
					try{
						d1=new SimpleDateFormat("dd/MM/yyyy").parse(ins_date);
					}catch(java.text.ParseException p){
						p.printStackTrace();
					}
					vbean.setInstrument_date(d1);
					vbean.setInstrument_from(ins_form);
					vbean.setInstrument_no(ins_no);
					

					VoucherDao vdao=new VoucherDao();					
					no=vdao.getMaxtrxNo();					
					vbean.setTrx_no(no);					
					VoucherBean  bn = new VoucherDao().addVoucher(vbean);

					//ad member



					bean.setAd_voucher_id(bn.getAd_voucher_id());
					int ad_member_id  = memberdao.addMemberRegistration(bean);


					if(ad_member_id>0){
						rowcount= 1;
					}
					//new MemberRegistrationDao().getMemberRegistrationMaxId();

					lstMap.put("dataId",String.valueOf(ad_member_id));

					bean.setAd_member_id(ad_member_id);
					present.setAd_member_id(ad_member_id);
					permanent.setAd_member_id(ad_member_id);
					first.setAd_member_id(ad_member_id);
					second.setAd_member_id(ad_member_id);


					//----------------address registration-------------------------------------------------------------

					new MemberAddressDao().addMemberAddress(present);
					new MemberAddressDao().addMemberAddress(permanent);
					//----------------Nominee registration-------------------------------------------------------------

					new NominationDao().addNomination(first);
					new NominationDao().addNomination(second);


					//----------------Witness registration-------------------------------------------------------------

					WitnessBean beanWitness = new WitnessBean();
					beanWitness.setAd_witness_id(Integer.parseInt(witness_ad_member_id));
					beanWitness.setAd_witness_mem_no(Integer.parseInt(witness_ad_society_id));
					beanWitness.setAd_salutation_id(Integer.parseInt(ad_salutation_id));
					beanWitness.setAd_witness_name(witness_name);
					beanWitness.setAd_witness_mobile(witness_mobile);
					beanWitness.setAd_witness_address(witness_address);
                    beanWitness.setAd_member_id(ad_member_id);
                    beanWitness.setCreated(new java.util.Date());
                    beanWitness.setCreatedby(user.getAd_user_id());
                    beanWitness.setUpdated(new java.util.Date());
                    beanWitness.setUpdatedby(user.getAd_user_id());
                    beanWitness.setIsactive(true);

					new WitnessDao().addWitness(beanWitness);





					/// dr in bank ac
					MemberRegistrationBean mbean=new MemberRegistrationBean();

					TransactionBean tbean = new TransactionBean();										
					tbean.setCreatedby(user.getAd_user_id());
					tbean.setUpdatedby(user.getAd_user_id());
					tbean.setAd_voucher_id(bn.getAd_voucher_id());
					tbean.setAd_account_id(131);					
					tbean.setTrx_date(d);	
					tbean.setAd_member_id(0);
					tbean.setNarration("New Member : "+new MemberRegistrationDao().getMemberName(ad_member_id).getName());
					tbean.setDramt(mfee+fgdsfee+dcfee+shareamt+adfees);
					new TransactionDao().addTransaction(tbean);

					//cr in mem ac
					if(mfee!=0){
						TransactionBean tbean2 = new TransactionBean();
						tbean2.setCreatedby(user.getAd_user_id());
						tbean2.setUpdatedby(user.getAd_user_id());
						tbean2.setAd_voucher_id(bn.getAd_voucher_id());
						tbean2.setAd_account_id(135);
						tbean2.setTrx_date(d);
						tbean2.setAd_member_id(0);
						tbean2.setMember(mbean);
						tbean2.setNarration("New Member : "+new MemberRegistrationDao().getMemberName(ad_member_id).getName());
						tbean2.setCramt(mfee);
						new TransactionDao().addTransaction(tbean2);
					}

					if(dcfee!=0){
						TransactionBean tbean2 = new TransactionBean();
                        tbean2.setCreatedby(user.getAd_user_id());
						tbean2.setUpdatedby(user.getAd_user_id());
						tbean2.setAd_voucher_id(bn.getAd_voucher_id());		
						tbean2.setAd_account_id(195);
						tbean2.setTrx_date(d);
						tbean2.setAd_member_id(0);
						tbean2.setNarration("New Member : "+new MemberRegistrationDao().getMemberName(ad_member_id).getName());
						tbean2.setCramt(dcfee);
						new TransactionDao().addTransaction(tbean2);

					}

					if(shareamt!=0){
						MemberShareBean msbean=new MemberShareBean();
						try{
							msbean.setDate_of_allocation(d);
						}catch(ParseException px){
							StringWriter errors = new StringWriter();
							px.printStackTrace(new PrintWriter(errors));

							lstMap.put("message","error");
							lstMap.put("errorClass","alert-warning");
							lstMap.put("errorMessage",errors.toString());
						}


						msbean.setShare_amt(Double.parseDouble(share_amt));
						//System.out.println(share_amt);
						//System.out.println(share_qty);
						msbean.setQnt_of_share(Integer.parseInt(share_qty));					
						MemberShareBean bean2=new MemberShareDao().getMemberShareMaxId();
						MemberShareBean bean1=new MemberShareDao().getMemberShareBatchshareNo(bean2.getAd_member_share_id());

						int share_no_to=0;

						if(bean2!=null){					
							msbean.setShare_no_form(bean1.getShare_no_to()+1);					
							share_no_to =bean1.getShare_no_to()+Integer.parseInt(share_qty);					
						}

						msbean.setShare_no_to(share_no_to);					
						msbean.setBatch_no(bean1.getBatch_no()+1);
						msbean.setIsactive(true);
						msbean.setAd_voucher_id(bn.getAd_voucher_id());
						msbean.setAd_member_id(ad_member_id);
						
						try{
							msbean.setChk_dd_date(new SimpleDateFormat("dd/MM/yyyy").parse(ins_date));
						}catch(Exception ex4 ){
							second.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(default_date));
							//System.out.println("DateFormat parse Exception in Instrument Date");
						}
						msbean.setTrx_by(vtype+" "+ins_form);
						try{
							ad_ins_no = Integer.parseInt(ins_no);
						}catch(NumberFormatException ex){

						}
						msbean.setChk_dd_no(ad_ins_no);

						batchn=bean1.getBatch_no()+1;
						sharenofrom=bean1.getShare_no_to()+1;
						sharenoto=share_no_to;

						new MemberShareDao().addMemberShare(msbean);		

						TransactionBean tbean2 = new TransactionBean();					
						tbean2.setCreatedby(user.getAd_user_id());
						tbean2.setUpdatedby(user.getAd_user_id());
						tbean2.setAd_voucher_id(bn.getAd_voucher_id());		
						tbean2.setAd_account_id(132);
						tbean2.setTrx_date(d);
						tbean2.setAd_member_id(ad_member_id);
						tbean2.setCramt(shareamt);
						tbean2.setNarration("New Membership : "+new MemberRegistrationDao().getMemberName(ad_member_id).getName());
						new TransactionDao().addTransaction(tbean2);

					}//end check share amount not zero

					/*if(adfees!=0){
						TransactionBean tbean2 = new TransactionBean();

						tbean2.setCreatedby(user.getAd_user_id());
						tbean2.setUpdatedby(user.getAd_user_id());
						tbean2.setVoucher(bn);		
						beanl.setAd_account_id(196);
						tbean2.setLedger(beanl);
						tbean2.setTrx_date(d);
						mbean.setAd_member_id(ad_member_id);
						tbean2.setMember(mbean);
						tbean2.setCramt(adfees);
						new TransactionDao().addTransaction(tbean2);

					}*/

					if(fgdsfee!=0){
						TransactionBean tbean2 = new TransactionBean();
						tbean2.setCreatedby(user.getAd_user_id());
						tbean2.setUpdatedby(user.getAd_user_id());
						tbean2.setAd_voucher_id(bn.getAd_voucher_id());	
						tbean2.setAd_account_id(133);
						tbean2.setTrx_date(d);
						tbean2.setAd_member_id(ad_member_id);
						tbean2.setCramt(fgdsfee);
						tbean2.setNarration("New Membership : "+new MemberRegistrationDao().getMemberName(ad_member_id).getName());
						
						new TransactionDao().addTransaction(tbean2);
						
						ThriftTrxBean thrift=new ThriftTrxBean();
						thrift.setAd_member_id(ad_member_id);
						thrift.setThrift_amt(fgdsfee);
						thrift.setCreated(new Date());
						thrift.setCreatedby(user.getAd_user_id());
						thrift.setUpdated(new Date());
						thrift.setUpdatedby(user.getAd_user_id());
						thrift.setStatus("Pending");
						thrift.setTrx_date(d);
						thrift.setIsactive(false);
						thrift.setAd_voucher_id(bn.getAd_voucher_id());
						thrift.setThrift_int(0);
						thrift.setBalance(fgdsfee);
						new ThriftTrxDao().addThriftTrx(thrift);
						

					}

					lstMap.put("Trxno",String.valueOf(no));
					lstMap.put("sharebatchno",String.valueOf(batchn));
					lstMap.put("shareformhno",String.valueOf(sharenofrom));
					lstMap.put("sharetono",String.valueOf(sharenoto));
					//------------------------------if success
					if(rowcount>0){
						lstMap.put("message","true");
						lstMap.put("successMessage","Member Registered Successfully");
					}else{
						lstMap.put("message","error");
						lstMap.put("errorMessage","Sorry contact to you admin.");
					}


				}//End Validate data submition part
               
				

			}catch(Exception ex ){

				StringWriter errors = new StringWriter();
				ex.printStackTrace(new PrintWriter(errors));
				lstMap.put("message","error");
				lstMap.put("errorMessage",errors.toString());

			}//end try catch block	
			PrintWriter out=response.getWriter();  
			Gson gson = new Gson(); 
			String json = gson.toJson(lstMap); 
			response.setContentType("application/json");
			out.print(json);
			//out.print("<script>alert(\"Trx No.: "+no+"\");</script>");
			//out.print("<script>window.location.href='ad_member.jsp';</script>");
			

		}//end insert function

	}//end post method

}//end class
