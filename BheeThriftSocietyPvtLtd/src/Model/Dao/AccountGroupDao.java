package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.AccountGroupBean;

public class AccountGroupDao {
	private Connection con = null;

	public AccountGroupDao() {
		try{
			con = DBConnection.getConnection();
			
			}catch(Exception s){
				System.out.println("AccountGroupDao:-Exception in Creating connection and auto commit off");
				s.printStackTrace();
			}
		
	}

	public int addAccountGroup(AccountGroupBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_ac_group(" +
					"created, createdby, updated, updatedby, isactive, name,ad_ac_type_id,group_ac_no)" +
					" VALUES (?, ?, ?, ?, ?, ?,?,?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getName());
			ps.setInt(7, bean.getAd_ac_type_id());
			ps.setInt(8, bean.getGroup_ac_no());	
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("AccountGroupDao:-error in try Block");
			e.printStackTrace();
			if(record==0){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("AccountGroupDao:-error in rollback");
					s.printStackTrace();
				}
				
			}
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
		return record;
		
	}
//-----------------------------------------------------------------------------------------
	public AccountGroupBean getAccountGroupById(AccountGroupBean bean) {
		AccountGroupBean bean1 = new AccountGroupBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_ac_group where ad_ac_group_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_ac_group_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_ac_group_id(rs.getInt("ad_ac_group_id"));
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
			}
			}catch (Exception e) {
				System.out.print("AccountGroupDao:-error in try Block");
				e.printStackTrace();
				if(rs!=null){
					try{
						con.rollback();
					}catch(SQLException s){
						System.out.print("AccountGroupDao:-error in rollback");
						s.printStackTrace();
					}
					
				}
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------
	public int getAccountGroupno() {
		int result=0;
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select max(group_ac_no) as group_ac_no from ad_ac_group ";
		try {
			ps = con.prepareStatement(query);			
			rs = ps.executeQuery();
			while (rs.next()) {
				
				result = rs.getInt("group_ac_no");
				
				

			}
			}catch (Exception e) {
				System.out.print("AccountGroupDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return result;
		}

	//-----------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------
		public int getAccountGroupnoById(int ad_ac_group_id) {
			int result=0;
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select  group_ac_no from ad_ac_group where ad_ac_group_id=? ";
			try {
				ps = con.prepareStatement(query);	
				ps.setInt(1, ad_ac_group_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					
					result = rs.getInt("group_ac_no");
					
					

				}
				}catch (Exception e) {
					System.out.print("AccountGroupDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return result;
			}

		//-----------------------------------------------------------------------------------------
		public String getAccountGroupNameById(int  ad_ac_group_id) {
			String name=null;
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select name from ad_ac_group where ad_ac_group_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1,ad_ac_group_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					
					name = rs.getString("name");
					
					

				}
				}catch (Exception e) {
					System.out.print("AccountGroupDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return name;
			}

	//------------------------------------------------------------------------------------------	
	public AccountGroupBean getAccountGroupById(int ad_ac_group_id) {
		AccountGroupBean bean1 = new AccountGroupBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_ac_group where ad_ac_group_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_ac_group_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_ac_group_id(rs.getInt("ad_ac_group_id"));
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
			}
		}catch (Exception e) {
			System.out.print("AccountGroupDao:-error in try Block");
			e.printStackTrace();
			if(rs!=null){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("AccountGroupDao:-error in rollback");
					s.printStackTrace();
				}
				
			}
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<AccountGroupBean> getAllAccountGroup() {
		ArrayList<AccountGroupBean> bean = new ArrayList<AccountGroupBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_ac_group ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				AccountGroupBean bean1 = new AccountGroupBean();
				bean1.setAd_ac_group_id(rs.getInt("ad_ac_group_id"));
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				bean1.setGroup_ac_no(rs.getInt("group_ac_no"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("AccountGroupDao:-error in try Block");
			e.printStackTrace();
			if(rs!=null){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("AccountGroupDao:-error in rollback");
					s.printStackTrace();
				}
				
			}
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	
	
	//--------------------------------------------------------------------------------------
		public ArrayList<AccountGroupBean> getAllAccountGroupByType(int ad_ac_type_id) {
			ArrayList<AccountGroupBean> bean = new ArrayList<AccountGroupBean>();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select ad_ac_group_id,ad_ac_type_id,name from ad_ac_group where ad_ac_type_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_ac_type_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					AccountGroupBean bean1 = new AccountGroupBean();
					bean1.setAd_ac_group_id(rs.getInt("ad_ac_group_id"));
					bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));					
					bean1.setName(rs.getString("name"));
					
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("AccountGroupDao:-error in try Block");
				e.printStackTrace();
				
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
//----------------------------------------------------------------------------------------------
	public int updateAccountGroup(AccountGroupBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_ac_group  SET " +
					"updated=?, updatedby=?,isactive=?, name=? ,ad_ac_type_id=? " +
					"WHERE ad_ac_group_id=? ";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, true);
			ps.setString(4, bean.getName());
			ps.setInt(5, bean.getAd_ac_type_id());
			ps.setInt(6, bean.getAd_ac_group_id());
			
			
			System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("AccountGroupDao:-error in try Block");
		e.printStackTrace();
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
		return i;
	
}
	
//---------------------------------------------------------------------------------------------

	public void updateAccountGroupStatus(AccountGroupBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_ac_group" +
					"SET  updated=?, updatedby=?," +
					"isactive=? WHERE ad_ac_group_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setInt(4, bean.getAd_ac_group_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("AccountGroupDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
	
//----------------------------------------------------------------------------------------------
	public void deleteAccountGroup(AccountGroupBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_ac_group WHERE ad_ac_group_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_ac_group_id());
			ps.executeQuery();
		
			}catch (Exception e) {
			System.out.print("AccountGroupDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}


}
