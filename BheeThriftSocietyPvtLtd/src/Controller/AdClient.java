package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import MasterValidation.Validation;
import Model.Bean.AccountSubGroupBean;
import Model.Bean.ClientBean;
import Model.Bean.LedgerAccountBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.NominationBean;
import Model.Bean.UserBean;
import Model.Dao.AccountSubGroupDao;
import Model.Dao.ClientDao;
import Model.Dao.LedgerAccountDao;
import Model.Dao.NominationDao;
/**
 * Servlet implementation class AdClient
 */
@WebServlet("/AdClient")
public class AdClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientDao dao=null;
    private UserBean user=null;
    ClientBean bean=null;
    HashMap<String,String>map=new HashMap<String,String>();
    private static final String UPLOAD_DIRECTORY = "Image";
	private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    public AdClient() {
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
			dao=new ClientDao();
			boolean isactive=true;
			Validation valid = new Validation();
			int rowsUpdated = 0;
			String action=request.getParameter("action");
			
			//String ac_no =request.getParameter("ac_no");
			
		
			
		if(action!=null){
							
			
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				Date date=(Date)session.getAttribute("openday");
				String account_no=null;
				String fax=null;
				String phone_no=null;
				String email_id =null;
				String name =null;
				String id=null;
				 String parent_organization=null;
				 String	registration_no=null;
				 String address=null;
				
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);
				
				if(action.equals("isactive")){
				
					
					isactive=Boolean.parseBoolean("isactive");	
					bean=new ClientBean();
					bean.setUpdatedby(user.getAd_user_id());
					//bean.setIsactive(isactive);
					//bean.setAd_ac_subgroup_id(ad_ac_subgroup_id);
					dao.updateClient(bean);
				
				}
							
				else if(action.equals("edit")){
					try{
						
						 id=request.getParameter("ad_client_id");
						if(id!=null){
						response.sendRedirect("edit_client.jsp?ad_client_id="+id);
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
					}catch(NumberFormatException ne){
						System.out.println("Error ");
						ne.printStackTrace();
					}
					
					response.sendRedirect("ad_client.jsp");
				}
				
				else if(action.equals("update")){
						try{
					 id=request.getParameter("ad_client_id");
					 email_id=request.getParameter("email_id");
					 phone_no=request.getParameter("phone_no");
					 account_no=request.getParameter("account_No");
					 parent_organization=request.getParameter("parent_organization");
					 registration_no=request.getParameter("registration_no");
					 fax=request.getParameter("fax"); 
					 address=request.getParameter("address");
					 name=request.getParameter("name");
						if(id!=null){
						
							ClientBean bean=new ClientBean();
							bean.setEmail_id(email_id);
							bean.setPhone_no(phone_no);
							bean.setAccount_No(account_no);
							bean.setParent_organization(parent_organization);
							bean.setRegistration_no(registration_no);
							bean.setFax(fax);
							bean.setAddress(address);
							bean.setAd_client_id(Integer.parseInt(id));
							bean.setName(name);
							bean.setIsactive(true);
							bean.setUpdatedby(user.getAd_user_id());
							bean.setUpdated(new java.util.Date());
							rowsUpdated=dao.updateClient(bean);
							//System.out.println("  =========================================================>");
							if (rowsUpdated > 0)
							{
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Data successfully Updated!";
							}
							else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!=====";
							}
							
					//	response.sendRedirect("edit_client.jsp?ad_client_id="+id);
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
				
						
					}catch(NumberFormatException ne){
						System.out.println("Error ");
						ne.printStackTrace();
					}
					
	
					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_client.jsp");
					
					
					
					
				}else{
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


						ClientBean bean = new ClientBean();
						
						
						bean.setCreated(new java.util.Date());
						bean.setCreatedby(user.getAd_user_id());
						bean.setUpdated(new java.util.Date());
						bean.setUpdatedby(user.getAd_user_id());
						bean.setIsactive(true);
						bean.setEntrydate(date);
						
							if(map.get("logo")!=null){
								bean.setLogo(map.get("logo"));
							}else{
								bean.setLogo("emp.png");
							}

							if(map.get("name")!=null){
								bean.setName(map.get("name"));
							}else{
								bean.setName("name");
							}
							if(map.get("email_id")!=null){
								bean.setEmail_id(map.get("email_id"));
								System.out.println(map.get("email_id"));
							}else{
								bean.setEmail_id("email_id");
							}
							
							if(map.get("phone_no")!=null){
								bean.setPhone_no(map.get("phone_no"));
								System.out.println(map.get("phone_no"));
							}else{
								bean.setPhone_no("phone_no");
							}

							if(map.get("parent_organization")!=null){
								bean.setParent_organization(map.get("parent_organization"));
								System.out.println(map.get("parent_organization"));
							}else{
								bean.setParent_organization("parent_organization");
							}
							
							if(map.get("registration_no")!=null){
								bean.setRegistration_no(map.get("registration_no"));
								System.out.println(map.get("registration_no"));
							}else{
								bean.setRegistration_no("registration_no");
							}
							
							if(map.get("fax")!=null){
								bean.setFax(map.get("fax"));
								System.out.println(map.get("fax"));
							}else{
								bean.setFax("fax");
							}
							
							if(map.get("address")!=null){
								bean.setAddress(map.get("address"));
								System.out.println(map.get("address"));
							}else{
								bean.setAddress("address");
							}
							
							if(map.get("account_No")!=null){
								bean.setAccount_No(map.get("account_No"));
								System.out.println(map.get("account_No"));
							}else{
								bean.setAccount_No("account_No");
							}
							
							
							if(new ClientDao().addClient(bean)>0){
								rowsUpdated = 1;
							}

							
							if (rowsUpdated >=1) {
								AppMessage[0] = "alert-success";
								AppMessage[1] = "Image successfully Uploaded and send for approval!";
							}else{
								AppMessage[0] = "alert-info";
								AppMessage[1] = "Sorry please try again latter!";
							}

						

					}catch(Exception e){
						e.printStackTrace();
					}

					session.setAttribute("AppMessage", AppMessage);
					response.sendRedirect("ad_client.jsp");

				}
				
				
				
			}else{
				System.out.println("Null value inside Action");
			}
			}
			catch(Exception e){
				e.printStackTrace();
			
			}

	}
}
