package Controller.Member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Bean.MenuBean;
import Model.Bean.SetAssignMenuBean;
import Model.Bean.SetAssignSubMenuBean;

import Model.Bean.SubMenuBean;

import Model.Dao.MenuDao;
import Model.Dao.SetAssignMenuDao;
import Model.Dao.SetAssignSubMenuDao;
import Model.Dao.SubMenuDao;

import Model.Member.Bean.MemberLoginBean;
import Model.Member.Bean.SetMemberRoleBean;
import Model.Member.Dao.MemberLoginDao;
import Model.Member.Dao.SetMemberRoleDao;

/**
 * Servlet implementation class MemberLoginVerification
 */
@WebServlet("/MemberLoginVerification")
public class MemberLoginVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginVerification() {
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
			String userid=null,pass=null;
		System.out.println("inside member controller");
		if(request.getParameter("user_id")!=null && request.getParameter("pass")!=null){
			
			userid=request.getParameter("user_id");
			pass=request.getParameter("pass");
			System.out.println("user id:"+userid+" password :"+pass);
			if(userid.equals("") || pass.equals("")){
				System.out.println("user id password blank");
			}else{
				
				MemberLoginDao dao=new MemberLoginDao();
				
				MemberLoginBean bean=dao.getVerifyMemberLogin(userid, pass,true);
				//System.out.println(bean);
				if(bean!=null){
					if(bean.getUsername().trim().equals(userid) && bean.getPassword().trim().equals(pass)){
						HttpSession session=request.getSession();
							System.out.println(bean.getUsername()+"  "+bean.getPassword());
						//session.setMaxInactiveInterval(2*60);

						session.setAttribute("MemberLoginBean", bean);
						SetMemberRoleDao memberRoleDao=new SetMemberRoleDao();
						SetMemberRoleBean memberrolebean=memberRoleDao.getMemberByMemberId(bean.getAd_member_login_id());
						SetAssignMenuDao setassignmenudao=new SetAssignMenuDao();
						ArrayList<SetAssignMenuBean> assignmenu=setassignmenudao.getAllAssignMenuByRoleId(memberrolebean.getAd_role_id());
						MenuDao menudao=new MenuDao();
						ArrayList<MenuBean> menulist=menudao.getAllMenu(assignmenu);
						session.setAttribute("membermenubean", menulist);

						SetAssignSubMenuDao setassignsubmenudao=new SetAssignSubMenuDao();
						ArrayList<SetAssignSubMenuBean> submenulist=setassignsubmenudao.getAssignSubMenuByRoleId(memberrolebean.getAd_role_id());
						SubMenuDao submenu=new SubMenuDao();
						ArrayList<SubMenuBean> submenulist2=submenu.getAllSubMenuByMeuId(menulist);
						session.setAttribute("membersubmenubean", submenulist2);
						response.sendRedirect("member/memberhomepage.jsp");
					}else{
						response.sendRedirect("member/login.jsp");
					}
					
				}else{
					response.sendRedirect("member/login.jsp");
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
