package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Bean.BankBranchBean;
import Model.Bean.PostingDetailBean;
import Model.Bean.UserBean;
import Model.Dao.BankBranchDao;
import Model.Dao.PostingDetailDao;
import MasterValidation.Validation;

/**
 * Servlet implementation class AdPostingDetail
 */
@WebServlet("/AdPostingDetail")
public class AdPostingDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostingDetailDao dao=null;
    private UserBean user=null;
    private Validation valid = null;
    public AdPostingDetail() {
        super();
        dao=new PostingDetailDao();
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
			
			int rowsUpdated =0;
			valid = new Validation();
			
    	   
			String action=request.getParameter("action");	
			String ad_member_id =request.getParameter("ad_member_id");
			String ad_branch_id =request.getParameter("branch_id");
			String ad_designation_id =request.getParameter("ad_designation_id");
			String ad_designation_level_id =request.getParameter("ad_designation_level_id");
			String ad_designation_type_id =request.getParameter("ad_designation_type_id");
			String ad_department_id =request.getParameter("ad_department_id");
			String fdate =request.getParameter("fdate");
			String tdate =request.getParameter("tdate");
							
				
			if(action!=null){
				
				HttpSession session=request.getSession(false);
				user=(UserBean)session.getAttribute("userbean");
				
				String[] AppMessage = new String[2];
				AppMessage[0] = "alert-info";
				AppMessage[1] = "welcome";
			    session.setAttribute("AppMessage", AppMessage);

				if(action.equals("edit")){			
					String ad_posting_detail_id = request.getParameter("ad_posting_detail_id");
					if(ad_posting_detail_id.equals("0")!=true){
						
						if(valid.validNotEmpty(ad_member_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Member Id can not empty!";
						}else if(valid.validDigits(ad_member_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Member Id should be disgit!";
						}else if(valid.validNotEmpty(ad_branch_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Branch Id can not empty!";
						}else if(valid.validDigits(ad_branch_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Branch Id should be disgit!";
						}else if(valid.validNotEmpty(ad_designation_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Designation Id can not empty!";
						}else if(valid.validDigits(ad_designation_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Designation Id should be disgit!";
						}else if(valid.validNotEmpty(ad_designation_type_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Designation Type Id can not empty!";
						}else if(valid.validDigits(ad_designation_type_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Designation Type Id should be disgit!";
						}else if(valid.validNotEmpty(ad_department_id)==false){
							AppMessage[0] = "alert-warning";
							AppMessage[1] = "Department Id can not empty!";
						}else if(valid.validDigits(ad_department_id)==false){
							
						}else{
							PostingDetailBean bean=new PostingDetailBean();
							bean.setAd_posting_detail_id(Integer.parseInt(ad_posting_detail_id));
							bean.setCreatedby(user.getAd_user_id());
							bean.setUpdatedby(user.getAd_user_id());
							bean.setIsactive(true);
							bean.setAd_member_id(Integer.parseInt(ad_member_id));
							bean.setAd_branch_id(Integer.parseInt(ad_branch_id));
							bean.setAd_designation_level_id(Integer.parseInt(ad_designation_level_id));
							bean.setAd_designation_type_id(Integer.parseInt(ad_designation_type_id));
							bean.setAd_designation_id(Integer.parseInt(ad_designation_id));
							bean.setAd_department_id(Integer.parseInt(ad_department_id));
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							bean.setFormdate(sdf.parse(fdate));
							if(tdate.equals("")!=true){
								bean.setTodate(sdf.parse(tdate));
							}else{
								bean.setTodate(null);
							}
							
							rowsUpdated = new PostingDetailDao().updatePostingDetail(bean);
							
						}//end validation
						
						if (rowsUpdated > 0) {
							AppMessage[0] = "alert-success";
							AppMessage[1] = "Data successfully Updated!";
						}else{
							AppMessage[0] = "alert-info";
							AppMessage[1] = "Sorry please try again latter!";
						}
						session.setAttribute("AppMessage", AppMessage);
						response.sendRedirect("edit_posting_detail.jsp?ad_member_id="+ad_member_id+"&ad_posting_detail_id="+ad_posting_detail_id);
						
					}else{
						System.out.print("PostingDetail not selected");
					}
				}else if(action.equals("insert")){
					
					if(valid.validNotEmpty(ad_member_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Member Id can not empty!";
					}else if(valid.validDigits(ad_member_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Member Id should be disgit!";
					}else if(valid.validNotEmpty(ad_branch_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Branch Id can not empty!";
					}else if(valid.validDigits(ad_branch_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Branch Id should be disgit!";
					}else if(valid.validNotEmpty(ad_designation_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Designation Id can not empty!";
					}else if(valid.validDigits(ad_designation_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Designation Id should be disgit!";
					}else if(valid.validNotEmpty(ad_designation_type_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Designation Type Id can not empty!";
					}else if(valid.validDigits(ad_designation_type_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Designation Type Id should be disgit!";
					}else if(valid.validNotEmpty(ad_department_id)==false){
						AppMessage[0] = "alert-warning";
						AppMessage[1] = "Department Id can not empty!";
					}else if(valid.validDigits(ad_department_id)==false){
						
					}else{
					
					PostingDetailBean bean=new PostingDetailBean();					
					bean.setCreatedby(user.getAd_user_id());
					bean.setUpdatedby(user.getAd_user_id());
					bean.setIsactive(true);
					bean.setAd_member_id(Integer.parseInt(ad_member_id));
					bean.setAd_branch_id(Integer.parseInt(ad_branch_id));
					bean.setAd_designation_level_id(Integer.parseInt(ad_designation_level_id));
					bean.setAd_designation_type_id(Integer.parseInt(ad_designation_type_id));
					bean.setAd_designation_id(Integer.parseInt(ad_designation_id));
					bean.setAd_department_id(Integer.parseInt(ad_department_id));
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					bean.setFormdate(sdf.parse(fdate));
					if(tdate.equals("")!=true){
						bean.setTodate(sdf.parse(tdate));
					}else{
						bean.setTodate(null);
					}
					rowsUpdated =  new PostingDetailDao().addPostingDetail(bean);
				}//end validation
					
					if (rowsUpdated > 0) {
						AppMessage[0] = "alert-success";
						AppMessage[1] = "Data successfully Inserted!";
					}else{
						AppMessage[0] = "alert-info";
						AppMessage[1] = "Sorry please try again latter!";
					}
					session.setAttribute("AppMessage", AppMessage);
					
					response.sendRedirect("ad_posting_detail.jsp?ad_member_id="+ad_member_id);
				}else{
					response.sendRedirect("ad_posting_detail.jsp");
				}

			}//end check action
							
			}catch(Exception e){
				e.printStackTrace();
			
			}
		
	}//end post method
	
}//end class
