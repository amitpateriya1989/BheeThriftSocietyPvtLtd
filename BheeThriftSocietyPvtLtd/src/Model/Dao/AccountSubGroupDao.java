package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.AccountSubGroupBean;

public class AccountSubGroupDao {

	private Connection con = null;

	public AccountSubGroupDao() {
		con = DBConnection.getConnection();
	}

	public int addAccountSubGroup(AccountSubGroupBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_ac_subgroup(" +
					"created, createdby, updated, updatedby, isactive, ad_ac_group_id, name," +
					" ad_ac_type_id ,sub_group_ac_no) VALUES (?, ?, ?, ?, ?, ?,?, ?,?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setInt(6, bean.getAd_ac_group_id());
			ps.setString(7, bean.getName());
			ps.setInt(8, bean.getAd_ac_type_id());
			ps.setInt(9 , bean.getSub_group_ac_no());
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("AccountSubGroupDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
		return record;
	}
//-----------------------------------------------------------------------------------------
	public AccountSubGroupBean getAccountSubGroupById(AccountSubGroupBean bean) {
		AccountSubGroupBean bean1 = new AccountSubGroupBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_ac_subgroup where ad_ac_subgroup_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_ac_subgroup_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_ac_subgroup_id(rs.getInt("ad_ac_subgroup_id"));
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
				bean1.setAd_ac_group_id(rs.getInt("ad_ac_group_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
				

			}
			}catch (Exception e) {
				System.out.print("AccountSubGroupDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public AccountSubGroupBean getAccountSubGroupById(int ad_ac_subgroup_id) {
		AccountSubGroupBean bean1 = new AccountSubGroupBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_ac_subgroup where ad_ac_subgroup_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_ac_subgroup_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_ac_subgroup_id(rs.getInt("ad_ac_subgroup_id"));
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
				bean1.setAd_ac_group_id(rs.getInt("ad_ac_group_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
			}
		}catch (Exception e) {
			System.out.print("AccountSubGroupDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------	
			public int  getSubGroupNoById(int ad_ac_subgroup_id) {
				int result=0;
				PreparedStatement ps =null;
				ResultSet rs=null;
				String query = "select  sub_group_ac_no from ad_ac_subgroup where ad_ac_subgroup_id=? ";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_ac_subgroup_id);
					rs = ps.executeQuery();
					while (rs.next()) {
					result=rs.getInt("sub_group_ac_no");
						
					}
				}catch (Exception e) {
					System.out.print("AccountSubGroupDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return result;
			}
		//--------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------	
		public int  getmaxSubGroupNoByGroupId(int ad_ac_group_id) {
			int result=0;
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select max(sub_group_ac_no) as sub_group_ac_no from ad_ac_subgroup where ad_ac_group_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_ac_group_id);
				rs = ps.executeQuery();
				while (rs.next()) {
				result=rs.getInt("sub_group_ac_no");
					
				}
			}catch (Exception e) {
				System.out.print("AccountSubGroupDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return result;
		}
	//--------------------------------------------------------------------------------------
	
	public ArrayList<AccountSubGroupBean> getAllAccountSubGroup() {
		ArrayList<AccountSubGroupBean> bean = new ArrayList<AccountSubGroupBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_ac_subgroup order by ad_ac_type_id ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				AccountSubGroupBean bean1 = new AccountSubGroupBean();
				bean1.setAd_ac_subgroup_id(rs.getInt("ad_ac_subgroup_id"));
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
				bean1.setAd_ac_group_id(rs.getInt("ad_ac_group_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				bean1.setSub_group_ac_no(rs.getInt("sub_group_ac_no"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("AccountSubGroupDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	
	
	//--------------------------------------------------------------------------------------
		public ArrayList<AccountSubGroupBean> getAllAccountSubGroupByGroupId(int ad_ac_group_id) {
			ArrayList<AccountSubGroupBean> bean = new ArrayList<AccountSubGroupBean>();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select ad_ac_subgroup_id,ad_ac_type_id,name from ad_ac_subgroup where ad_ac_group_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_ac_group_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					AccountSubGroupBean bean1 = new AccountSubGroupBean();
					bean1.setAd_ac_subgroup_id(rs.getInt("ad_ac_subgroup_id"));
					bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
					bean1.setName(rs.getString("name"));
					
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("AccountSubGroupDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
//----------------------------------------------------------------------------------------------
	public int updateAccountSubGroup(AccountSubGroupBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_ac_subgroup" +
					" SET  updated=?, updatedby=?, isactive=?, ad_ac_group_id=?," +
					" name=?, ad_ac_type_id=? WHERE ad_ac_subgroup_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setInt(4, bean.getAd_ac_group_id());
			ps.setString(5, bean.getName());
			ps.setInt(6, bean.getAd_ac_type_id());
			ps.setInt(7, bean.getAd_ac_subgroup_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("AccountSubGroupDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
		
	return i;
	
}
	
//---------------------------------------------------------------------------------------------

	public int updateAccountSubGroupStatus(AccountSubGroupBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_ac_subgroup" +
					"SET  updated=?, updatedby=?," +
					"isactive=? WHERE ad_ac_subgroup_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setInt(4, bean.getAd_ac_subgroup_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("AccountSubGroupDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
	
//----------------------------------------------------------------------------------------------
	public int  deleteAccountSubGroup(AccountSubGroupBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_ac_subgroup WHERE ad_ac_subgroup_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_ac_subgroup_id());
			ps.executeQuery();
		
			}catch (Exception e) {
			System.out.print("AccountSubGroupDao:-error in try Block");
			e.printStackTrace();
			}finally {
				
				DBConnection.close(ps);
				DBConnection.close(con);
		    }

		
		return i;
	}

}
