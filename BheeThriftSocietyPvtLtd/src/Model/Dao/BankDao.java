package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.BankBean;

public class BankDao {

	private Connection con = null;

	public BankDao() {
		con = DBConnection.getConnection();
		}

	public int addBank(BankBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_bank(" +
					"created, createdby, updated, updatedby, isactive, bank)" +
					" VALUES (?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, bean.isIsactive());
			ps.setString(6, bean.getBank());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("BankDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public BankBean getBankById(BankBean bean) {
		BankBean bean1 = new BankBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_bank where ad_bank_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_bank_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_bank_id(rs.getInt("ad_bank_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setBank(rs.getString("bank"));
				
				

			}
			}catch (Exception e) {
				System.out.print("BankDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public BankBean getBankById(int ad_bank_id) {
		BankBean bean1 = new BankBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_bank where ad_bank_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_bank_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_bank_id(rs.getInt("ad_bank_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setBank(rs.getString("bank"));
				
			}
		}catch (Exception e) {
			System.out.print("BankDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<BankBean> getAllBank() {
		ArrayList<BankBean> bean = new ArrayList<BankBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_bank order by ad_bank_id";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				BankBean bean1 = new BankBean();
				bean1.setAd_bank_id(rs.getInt("ad_bank_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setBank(rs.getString("bank"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("BankDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateBank(BankBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_bank " +
					"SET  updated=?, updatedby=?," +
					"isactive=?, bank=? WHERE ad_bank_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getBank());
			ps.setInt(5, bean.getAd_bank_id());

			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("BankDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deleteBank(BankBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_bank WHERE ad_bank_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_bank_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("BankDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}//end delete function


}//end class
