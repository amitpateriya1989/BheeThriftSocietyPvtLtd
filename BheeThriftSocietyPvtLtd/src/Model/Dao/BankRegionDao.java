package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.BankRegionBean;

public class BankRegionDao {
	private Connection con = null;

	public BankRegionDao() {
		con = DBConnection.getConnection();
		}

	public int addBankRegion(BankRegionBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_bank_region(" +
					"created, createdby, updated, updatedby, isactive,region,region_code)" +
					" VALUES (?, ?, ?, ?, ?, ?,?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getRegion());
			ps.setString(7, bean.getRegion_code());	
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("BankRegionDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public BankRegionBean getBankRegionById(BankRegionBean bean) {
		BankRegionBean bean1 = new BankRegionBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_bank_region where ad_bank_region_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_bank_region_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_bank_region_id(rs.getInt("ad_bank_region_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRegion(rs.getString("region"));
				bean1.setRegion_code(rs.getString("region_code"));
				

			}
			}catch (Exception e) {
				System.out.print("BankRegionDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public BankRegionBean getBankRegionById(int ad_bank_region_id) {
		BankRegionBean bean1 = new BankRegionBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_bank_region where ad_bank_region_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_bank_region_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_bank_region_id(rs.getInt("ad_bank_region_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRegion(rs.getString("region"));
				bean1.setRegion_code(rs.getString("region_code"));
			}
		}catch (Exception e) {
			System.out.print("BankRegionDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<BankRegionBean> getAllBankRegion() {
		ArrayList<BankRegionBean> bean = new ArrayList<BankRegionBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_bank_region ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				BankRegionBean bean1 = new BankRegionBean();
				bean1.setAd_bank_region_id(rs.getInt("ad_bank_region_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRegion(rs.getString("region"));
				bean1.setRegion_code(rs.getString("region_code"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("BankRegionDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateBankRegion(BankRegionBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_bank_region " +
					"SET  updated=?, updatedby=?, isactive=?, region=?, region_code=?" +
					"WHERE ad_bank_region_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getRegion());
			ps.setString(5, bean.getRegion_code());
			ps.setInt(6, bean.getAd_bank_region_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("BankRegionDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deleteBankRegion(BankRegionBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_bank_region WHERE ad_bank_region_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_bank_region_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("BankRegionDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}



}
