package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.GenrateShareNoBean;

public class GenrateShareNoDao {
	private Connection con = null;

	public GenrateShareNoDao() {
		try{
			con = DBConnection.getConnection();
			
			}catch(Exception s){
				System.out.println("Dao:-Exception in Creating connection and auto commit off");
				s.printStackTrace();
			}
		
	}

	
//-----------------------------------------------------------------------------------------
	public GenrateShareNoBean getGenrateShareNo() {
		GenrateShareNoBean bean = new GenrateShareNoBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from genrate_share_no";
		try {
			ps = con.prepareStatement(query);			
			rs = ps.executeQuery();
			while (rs.next()) {
				
				bean.setShare_no(rs.getInt("share_no"));
				
			}
			}catch (Exception e) {
				System.out.print("Dao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}

//------------------------------------------------------------------------------------------	
	

//----------------------------------------------------------------------------------------------
	public void updateGenrateShareNo(GenrateShareNoBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE generate_share_no   SET  share_no=? WHERE generate_share_no_id=1";
			ps = con.prepareStatement(query);
			ps.setDouble(1, bean.getShare_no());			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("Dao:-error in try Block");
		e.printStackTrace();
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
	
//---------------------------------------------------------------------------------------------

}
