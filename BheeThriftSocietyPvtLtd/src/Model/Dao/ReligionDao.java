package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Bean.ReligionBean;

public class ReligionDao {
	
	private Connection con = null;

	public ReligionDao() {
		con = DBConnection.getConnection();
		}

	public int addReligion(ReligionBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_religion(" +
					"created, createdby, updated, updatedby, isactive, religion)" +
					" VALUES (?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getReligion());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("ReligionDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public ReligionBean getReligionById(ReligionBean bean) {
		ReligionBean bean1 = new ReligionBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_religion where ad_religion_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_religion_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_religion_id(rs.getInt("ad_religion_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setReligion(rs.getString("religion"));
				
				

			}
			}catch (Exception e) {
				System.out.print("ReligionDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public ReligionBean getReligionById(int ad_religion_id) {
		ReligionBean bean1 = new ReligionBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_religion where ad_religion_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_religion_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_religion_id(rs.getInt("ad_religion_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setReligion(rs.getString("religion"));
				
			}
		}catch (Exception e) {
			System.out.print("ReligionDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<ReligionBean> getAllReligion() {
		ArrayList<ReligionBean> bean = new ArrayList<ReligionBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_religion ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				ReligionBean bean1 = new ReligionBean();
				bean1.setAd_religion_id(rs.getInt("ad_religion_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setReligion(rs.getString("religion"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("ReligionDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateReligion(ReligionBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_religion " +
					"SET  updated=?, updatedby=?, " +
					"isactive=?, religion=? WHERE ad_religion_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getReligion());
			ps.setInt(5, bean.getAd_religion_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("ReligionDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public void deleteReligion(ReligionBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_religion WHERE ad_religion_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_religion_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("ReligionDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}



}
