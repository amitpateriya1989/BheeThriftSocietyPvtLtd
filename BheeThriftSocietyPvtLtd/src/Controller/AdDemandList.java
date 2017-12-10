package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdDemandList
 */
@WebServlet("/AdDemandList")
public class AdDemandList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdDemandList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action =request.getParameter("action");
		if(action.equals("insert")){
			String genrate_demand_list =request.getParameter("genrate_demand_list");
			String find_demand_list =request.getParameter("find_demand_list");
			
			if(genrate_demand_list!=null){
				
				
				System.out.print("gdl");
				
			}else if(find_demand_list!=null){
				
				
				System.out.print("fdl");
			}
			
		
	}
	}
	
}
