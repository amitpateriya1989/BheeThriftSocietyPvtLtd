package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.RoleBean;

public class RoleDao {
	private Connection con = null;

	public RoleDao() {
		con = DBConnection.getConnection();
	}

	public void addRole(RoleBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_role(" +
					" ad_client_id, created, createdby, updated, updatedby," +
					"name, description, isactive,read_permission,write_permission,edit_permission)" +
					"VALUES ( ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_client_id());
			ps.setDate(2, new java.sql.Date(bean.getCreated().getTime()));
			ps.setInt(3, bean.getCreatedby());
			ps.setDate(4, new java.sql.Date(bean.getUpdated().getTime()));
			ps.setInt(5, bean.getUpdatedby());
			ps.setString(6, bean.getName());
			ps.setString(7, bean.getDescription());
			ps.setBoolean(8, bean.isIsactive());
			ps.setBoolean(9, bean.isRead_permission());
			ps.setBoolean(10, bean.isWrite_permission());
			ps.setBoolean(11, bean.isEdit_permission());
			
			
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("RoleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
//-----------------------------------------------------------------------------------------
	public RoleBean getRoleById(RoleBean bean) {
		RoleBean bean1 = new RoleBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_role where ad_role_id=? and isactive=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_role_id());
			ps.setBoolean(2, bean.isIsactive());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setName(rs.getString("name"));
				bean1.setDescription(rs.getString("discription"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRead_permission(rs.getBoolean("read_permission"));
				bean1.setWrite_permission(rs.getBoolean("write_permission"));
				bean1.setEdit_permission(rs.getBoolean("edit_permission"));
				
				
				

			}
			}catch (Exception e) {
				System.out.print("RoleDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public RoleBean getRoleById(int Role_id ,boolean isactive) {
		RoleBean bean1 = new RoleBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_role where ad_role_id=? and isactive=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, Role_id);
			ps.setBoolean(2, isactive);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("update"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setName(rs.getString("name"));
				bean1.setDescription(rs.getString("discription"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRead_permission(rs.getBoolean("read_permission"));
				bean1.setWrite_permission(rs.getBoolean("write_permission"));
				bean1.setEdit_permission(rs.getBoolean("edit_permission"));
				
				
			}
		}catch (Exception e) {
			System.out.print("RoleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public RoleBean getRolePermissionById(int Role_id,boolean isactive) {
		RoleBean bean1 = new RoleBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_role where ad_role_id=? and isactive=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, Role_id);
			ps.setBoolean(2, isactive);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setRead_permission(rs.getBoolean("read_permission"));
				bean1.setWrite_permission(rs.getBoolean("write_permission"));
				bean1.setEdit_permission(rs.getBoolean("edit_permission"));
				
				
			}
		}catch (Exception e) {
			System.out.print("RoleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<RoleBean> getAllRole() {
		ArrayList<RoleBean> bean = new ArrayList<RoleBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_role ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				RoleBean bean1 = new RoleBean();
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("update"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setName(rs.getString("name"));
				bean1.setDescription(rs.getString("discription"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRead_permission(rs.getBoolean("read_permission"));
				bean1.setWrite_permission(rs.getBoolean("write_permission"));
				bean1.setEdit_permission(rs.getBoolean("edit_permission"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("RoleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public void updateRole(RoleBean bean){
		int i=0;
		PreparedStatement ps = null;
		
		try {

			String query = "UPDATE ad_role" +
					"SET  ad_client_id=?, created=?, createdby=?, update=?," +
					"updatedby=?, name=?, description=?, isactive=?, read_permission=?," +
					"write_permission=?, edit_permission=?" +
					"WHERE ad_role_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_client_id());
			ps.setDate(2, new java.sql.Date(bean.getCreated().getTime()));
			ps.setInt(3, bean.getCreatedby());
			ps.setDate(4, new java.sql.Date(bean.getUpdated().getTime()));
			ps.setInt(5, bean.getUpdatedby());
			ps.setString(6, bean.getName());
			ps.setString(7, bean.getDescription());
			ps.setBoolean(8, bean.isIsactive());
			ps.setBoolean(9, bean.isRead_permission());
			ps.setBoolean(10, bean.isWrite_permission());
			ps.setBoolean(11, bean.isEdit_permission());
			ps.setInt(12, bean.getAd_role_id());
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("RoleDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public void deleteRole(RoleBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_role WHERE ad_role_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_role_id());
			ps.executeQuery();
		
			}catch (Exception e) {
			System.out.print("RoleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}



}
