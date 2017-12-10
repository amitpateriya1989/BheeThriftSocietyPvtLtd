package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.AccountTypeBean;

public class AccountTypeDao {

	private Connection con = null;

	public AccountTypeDao() {
		con = DBConnection.getConnection();
	}

	public int addAccountType(AccountTypeBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_ac_type(" +
					"created, createdby, updated, updatedby, isactive, name)" +
					" VALUES (?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getName());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("AccountTypeDao:-error in try Block");
			e.printStackTrace();
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
		
	}
//-----------------------------------------------------------------------------------------
	public AccountTypeBean getAccountTypeById(AccountTypeBean bean) {
		AccountTypeBean bean1 = new AccountTypeBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_ac_type where ad_ac_type_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_ac_type_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
			}
			}catch (Exception e) {
				System.out.print("AccountTypeDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public AccountTypeBean getAccountTypeById(int ad_ac_type_id) {
		AccountTypeBean bean1 = new AccountTypeBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select ad_ac_type_id,name from ad_ac_type where ad_ac_type_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_ac_type_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
				
				bean1.setName(rs.getString("name"));
				
			}
		}catch (Exception e) {
			System.out.print("AccountTypeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<AccountTypeBean> getAllAccountType() {
		ArrayList<AccountTypeBean> bean = new ArrayList<AccountTypeBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select ad_ac_type_id,name,isactive from ad_ac_type order by ad_ac_type_id asc";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				AccountTypeBean bean1 = new AccountTypeBean();
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("AccountTypeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	
	
//----------------------------------------------------------------------------------------------
	public int updateAccountType(AccountTypeBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_ac_type " +
					"SET   updated=?, updatedby=?," +
					"isactive=?, name=? WHERE ad_ac_type_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getName());
			ps.setInt(5, bean.getAd_ac_type_id());

			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("AccountTypeDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
	return i;
	
}
//----------------------------------------------------------------------------------------------
	public int deleteAccountType(AccountTypeBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_ac_type WHERE ad_ac_type_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_ac_type_id());
			ps.executeQuery();
		
			}catch (Exception e) {
			System.out.print("AccountTypeDao:-error in try Block");
			e.printStackTrace();
			}finally {
				
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
		return i;
	}

}
