package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Bean.DayOpenBean;
import Model.Bean.MenuBean;
import Model.Bean.SetAssignMenuBean;
import Model.Bean.SetAssignSubMenuBean;
import Model.Bean.SetUserRoleBean;
import Model.Bean.SubMenuBean;
import Model.Bean.UserBean;
import Model.Dao.DayOpenDao;
import Model.Dao.MenuDao;
import Model.Dao.SetAssignMenuDao;
import Model.Dao.SetAssignSubMenuDao;
import Model.Dao.SubMenuDao;
import Model.Dao.UserDao;
import Model.Dao.UserRoleDao;

/**
 * Servlet implementation class LoginVerification
 */
@WebServlet("/LoginVerification")
public class LoginVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public LoginVerification() {
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
		System.out.println("inside login  controller");

		try{
			String userid=null,pass=null;

			if(request.getParameter("user_id")!=null && request.getParameter("pass")!=null){

				userid=request.getParameter("user_id");
				pass=request.getParameter("pass");

				if(userid.equals("") || pass.equals("")){
					System.out.println("user id password blank");
				}else{



					UserBean bean=new UserDao().getVerifyUser(userid, pass,true);
					if(bean!=null){
						if(bean.getUser_id().equals(userid) && bean.getPass().equals(pass)){
							HttpSession session=request.getSession();
							session.setAttribute("session_start_time", new Date());
							String[] AppMessage = new String[2];
							AppMessage[0] = "alert-info";
							AppMessage[1] = "welcome";
							session.setAttribute("AppMessage", AppMessage);
							session.setAttribute("userbean", bean);

							SetUserRoleBean userrolebean=new UserRoleDao().getUserByUserId(bean.getAd_user_id());

							ArrayList<SetAssignMenuBean> assignmenu=new SetAssignMenuDao().getAllAssignMenuByRoleId(userrolebean.getAd_role_id());

							ArrayList<MenuBean> menulist=new MenuDao().getAllMenu(assignmenu);
							session.setAttribute("menubean", menulist);


							ArrayList<SetAssignSubMenuBean> submenulist=new SetAssignSubMenuDao().getAssignSubMenuByRoleId(userrolebean.getAd_role_id());

							ArrayList<SubMenuBean> submenulist2=new SubMenuDao().getAllSubMenuByMeuId(menulist);
							session.setAttribute("submenubean", submenulist2);

							DayOpenBean bean1 = new DayOpenDao().chkDayOpen();

							session.setAttribute("openday", bean1.getOpen_days());
							if(bean1.getClosing_status()!=true && session.getAttribute("openday")==null){

								response.sendRedirect("ad_open_days.jsp");

							}else{

								response.sendRedirect("homepage.jsp");
							}
						}else{
							response.sendRedirect("index.jsp");
						}

					}else{
						response.sendRedirect("index.jsp");
					}

				}


			}else{
				System.out.println("user id password didnt match");
			}




		}catch(Exception e){
			e.printStackTrace();
		}

	}
}