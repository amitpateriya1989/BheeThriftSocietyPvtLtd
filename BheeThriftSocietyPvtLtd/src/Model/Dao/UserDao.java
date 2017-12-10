package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.UserBean;

public class UserDao {
	
	private Connection con = null;

	public UserDao() {
		con = DBConnection.getConnection();
		System.out.print(con);
	}

	public void addUser(UserBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_user(ad_client_id, ad_department_id, created, createdby," +
					"updated, updateddby,name, username, password,photo,isactive,id_proof,signature,security_password)" +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_client_id());
			ps.setInt(2, bean.getAd_department_id());
			ps.setDate(3, new java.sql.Date(bean.getCreated().getTime()));
			ps.setInt(4, bean.getCreatedby());
			ps.setDate(5, new java.sql.Date(bean.getUpdate().getTime()));
			ps.setInt(6, bean.getUpdatedby());
			ps.setString(7, bean.getName());
			ps.setString(8, bean.getUser_id());
			ps.setString(9, bean.getPass());
			ps.setString(10, bean.getPhoto());
			ps.setBoolean(11, bean.isIsactive());
			ps.setString(12, bean.getId_proof());
			ps.setString(13, bean.getSignature());
			ps.setString(14, bean.getSecurity_password());
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("UserDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
            
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		
	}
//-----------------------------------------------------------------------------------------
	public UserBean getUserById(UserBean bean) {
		UserBean bean1 = new UserBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_user where ad_user_id=? and isactive=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_user_id());
			ps.setBoolean(2,bean.isIsactive());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_client_id(rs.getInt("ad_client_id"));
				bean1.setAd_department_id(rs.getInt("ad_department_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdate(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updated_by"));
				bean1.setName(rs.getString("name"));
				bean1.setUser_id(rs.getString("username"));
				bean1.setPass(rs.getString("password"));
				bean1.setPhoto(rs.getString("photo"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setSignature(rs.getString("signature"));
				bean1.setId_proof(rs.getString("id_proof"));
				bean1.setSecurity_password(rs.getString("security_password"));
			}
			}catch (Exception e) {
				System.out.print("UserDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
	        }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public UserBean getUserById(int ad_user_id,boolean isactive) {
		UserBean bean1 = new UserBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_user where ad_user_id=? and isactive=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_user_id);
			ps.setBoolean(2, isactive);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_client_id(rs.getInt("ad_client_id"));
				bean1.setAd_department_id(rs.getInt("ad_department_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdate(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updated_by"));
				bean1.setName(rs.getString("name"));
				bean1.setUser_id(rs.getString("username"));
				bean1.setPass(rs.getString("password"));
				bean1.setPhoto(rs.getString("photo"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setSignature(rs.getString("signature"));
				bean1.setId_proof(rs.getString("id_proof"));
				bean1.setSecurity_password(rs.getString("security_password"));
			}
		}catch (Exception e) {
			System.out.print("UserDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		return bean1;
	}
	
	//------------------------------------------------------------------------------------------	
		public UserBean getUserById(int ad_user_id) {
			UserBean bean1 = new UserBean();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_user where ad_user_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_user_id);
				
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_client_id(rs.getInt("ad_client_id"));
					bean1.setAd_department_id(rs.getInt("ad_department_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdate(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updated_by"));
					bean1.setName(rs.getString("name"));
					bean1.setUser_id(rs.getString("username"));
					bean1.setPass(rs.getString("password"));
					bean1.setPhoto(rs.getString("photo"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setSignature(rs.getString("signature"));
					bean1.setId_proof(rs.getString("id_proof"));
					bean1.setSecurity_password(rs.getString("security_password"));
				}
			}catch (Exception e) {
				System.out.print("UserDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
	        }
			return bean1;
		}
//--------------------------------------------------------------------------------------
	public ArrayList<UserBean> getAllUser() {
		ArrayList<UserBean> bean = new ArrayList<UserBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_user ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserBean bean1 = new UserBean();
				bean1.setAd_client_id(rs.getInt("ad_client_id"));
				bean1.setAd_department_id(rs.getInt("ad_department_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdate(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updated_by"));
				bean1.setName(rs.getString("name"));
				bean1.setUser_id(rs.getString("username"));
				bean1.setPass(rs.getString("password"));
				bean1.setPhoto(rs.getString("photo"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setSignature(rs.getString("signature"));
				bean1.setId_proof(rs.getString("id_proof"));
				bean1.setSecurity_password(rs.getString("security_password"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("UserDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public void updatedUser(UserBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_user " +
					" SET updated=?, updated_by=?, name=?, username=?, password=?," +
					" photo=?, isactive=?, security_password=?, id_proof=?, signature=?" +
					" WHERE ad_user_id=? ";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(bean.getUpdate().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setString(3, bean.getName());
			ps.setString(4, bean.getUser_id());
			ps.setString(5, bean.getPass());
			ps.setString(6, bean.getPhoto());
			ps.setBoolean(7, bean.isIsactive());
			ps.setString(8, bean.getSecurity_password());
			ps.setString(9, bean.getId_proof());
			ps.setString(10, bean.getSignature());			
			ps.setInt(11, bean.getAd_user_id());
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("UserDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public void deleteUser(UserBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_user WHERE ad_user_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_user_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("UserDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
//----------------------------------------------------------------------------------------------
	public UserBean getVerifyUser(String id,String password,boolean isactive) {
		UserBean bean1 = null;
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_user where username=? and password=? and isactive=?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, password);
			ps.setBoolean(3, isactive);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1=new UserBean();
				bean1.setAd_user_id(rs.getInt("ad_user_id"));
				bean1.setAd_client_id(rs.getInt("ad_client_id"));
				bean1.setAd_department_id(rs.getInt("ad_department_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdate(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updated_by"));
				bean1.setName(rs.getString("name"));
				bean1.setUser_id(rs.getString("username"));
				bean1.setPass(rs.getString("password"));
				bean1.setPhoto(rs.getString("image"));
				bean1.setIsactive(rs.getBoolean("isactive"));

			}
			}catch (Exception e) {
				System.out.print("UserDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
}
