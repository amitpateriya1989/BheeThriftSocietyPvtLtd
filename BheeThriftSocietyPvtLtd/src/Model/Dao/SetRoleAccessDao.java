package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.SetRoleAccessBean;


public class SetRoleAccessDao {
	private Connection con = null;

	public SetRoleAccessDao() {
		con = DBConnection.getConnection();
			
	}

	public void addRoleAccess(SetRoleAccessBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO set_role_access(" +
					"ad_role_id, ad_client_id, ad_department_id) VALUES ( ?, ?, ?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_role_id());
			ps.setInt(2, bean.getAd_client_id());
			ps.setInt(3, bean.getAd_department_id());
			
			
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("SetSetRoleAccessDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
//-----------------------------------------------------------------------------------------
	public SetRoleAccessBean getAssignSubMenuById(SetRoleAccessBean bean) {
		SetRoleAccessBean bean1 = new SetRoleAccessBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from set_role_access where set_role_access_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getSet_role_access_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_client_id(rs.getInt("ad_client_id"));
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setAd_department_id(rs.getInt("ad_department_id"));
				bean1.setSet_role_access_id(rs.getInt("set_role_access_id"));
				
				
			}
			}catch (Exception e) {
				System.out.print("SetSetRoleAccessDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public SetRoleAccessBean getAssignSubMenuById(int setRoleAccessId) {
		SetRoleAccessBean bean1 = new SetRoleAccessBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from set_role_access where set_role_access_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, setRoleAccessId);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_client_id(rs.getInt("ad_client_id"));
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setAd_department_id(rs.getInt("ad_department_id"));
				bean1.setSet_role_access_id(rs.getInt("set_role_access_id"));
				
			}
		}catch (Exception e) {
			System.out.print("SetSetRoleAccessDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<SetRoleAccessBean> getAllRoleAccess() {
		ArrayList<SetRoleAccessBean> bean = new ArrayList<SetRoleAccessBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from set_role_access ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				SetRoleAccessBean bean1 = new SetRoleAccessBean();
				bean1.setAd_client_id(rs.getInt("ad_client_id"));
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setAd_department_id(rs.getInt("ad_department_id"));
				bean1.setSet_role_access_id(rs.getInt("set_role_access_id"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("SetSetRoleAccessDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public void updateRoleAccess(SetRoleAccessBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE set_role_access" +
					"SET ad_role_id=?, ad_client_id=?, ad_department_id=?" +
					"WHERE set_role_access_id=?";
			ps = con.prepareStatement(query);
			ps = con.prepareStatement(query);
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_role_id());
			ps.setInt(2, bean.getAd_client_id());
			ps.setInt(3, bean.getAd_department_id());
			ps.setInt(4, bean.getSet_role_access_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("SetSetRoleAccessDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public void deleteRoleAccess(SetRoleAccessBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM set_role_access WHERE set_role_access_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getSet_role_access_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("SetSetRoleAccessDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}


}
