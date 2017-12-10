package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Bean.UserBean;
import Model.Dao.GenrateShareNoDao;
import Model.Dao.MemberShareDao;

/**
 * Servlet implementation class AdGenrateShareNo
 */
@WebServlet("/AdGenrateShareNo")
public class AdGenrateShareNo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GenrateShareNoDao dao=null;
    private UserBean user=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdGenrateShareNo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		dao=new GenrateShareNoDao();
		String action=request.getParameter("action");
		try{
			
			if(action.equals("select")){
				
		// share_no=dao.getGenrateShareNo();
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

}
