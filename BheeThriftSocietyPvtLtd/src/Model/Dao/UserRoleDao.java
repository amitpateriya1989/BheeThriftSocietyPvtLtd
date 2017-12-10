package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.SetUserRoleBean;

public class UserRoleDao {
	private Connection con = null;

	public UserRoleDao() {
		con = DBConnection.getConnection();
		
	}

	public void addSetUserRole(SetUserRoleBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO set_user_role(" +
					"ad_role_id, ad_user_id, isactive)" +
					"VALUES (?, ?, ?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_role_id());
			ps.setInt(2, bean.getAd_user_id());
			ps.setBoolean(3, bean.isIsactive());
			
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("UserRoleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
//-----------------------------------------------------------------------------------------
	public SetUserRoleBean getUserById(SetUserRoleBean bean) {
		SetUserRoleBean bean1 = new SetUserRoleBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from set_user_role where set_user_role_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getSet_user_role_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setAd_user_id(rs.getInt("ad_user_id"));
				bean1.setSet_user_role_id(rs.getInt("set_user_role_id"));
				bean1.setIsactive(rs.getBoolean("isactive"));

			}
			}catch (Exception e) {
				System.out.print("UserRoleDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public SetUserRoleBean getUserById(int set_user_role_id) {
		SetUserRoleBean bean1 = new SetUserRoleBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from set_user_role where set_user_role_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, set_user_role_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setAd_user_id(rs.getInt("ad_user_id"));
				bean1.setSet_user_role_id(rs.getInt("set_user_role_id"));
				bean1.setIsactive(rs.getBoolean("isactive"));

			}
		}catch (Exception e) {
			System.out.print("UserRoleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<SetUserRoleBean> getAllUser() {
		ArrayList<SetUserRoleBean> bean = new ArrayList<SetUserRoleBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from set_user_role ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				SetUserRoleBean bean1 = new SetUserRoleBean();
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setAd_user_id(rs.getInt("ad_user_id"));
				bean1.setSet_user_role_id(rs.getInt("set_user_role_id"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("UserRoleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public void updatedUser(SetUserRoleBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE set_user_role" +
					"SET  ad_role_id=?, ad_user_id=?, isactive=?" +
					"WHERE set_user_role_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_role_id());
			ps.setInt(2, bean.getAd_user_id());
			ps.setBoolean(3, bean.isIsactive());
			ps.setInt(4, bean.getSet_user_role_id());
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("UserRoleDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public void deleteUser(SetUserRoleBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM set_user_role WHERE set_user_role_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getSet_user_role_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("UserRoleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
//----------------------------------------------------------------------------------------------
	public SetUserRoleBean getUserByUserId(int ad_user_id) {
		SetUserRoleBean bean1 = new SetUserRoleBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from set_user_role where ad_user_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_user_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setAd_user_id(rs.getInt("ad_user_id"));
				bean1.setSet_user_role_id(rs.getInt("set_user_role_id"));
				bean1.setIsactive(rs.getBoolean("isactive"));

			}
			}catch (Exception e) {
				System.out.print("UserRoleDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

}
