package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.GenderBean;

public class GenderDao {

	private Connection con = null;

	public GenderDao() {
			con = DBConnection.getConnection();
		}

	public int addGender(GenderBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_gender(" +
					"created, createdby, updated, updatedby, isactive,gender)" +
					" VALUES ( ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getGender());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("GenderDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public GenderBean getGenderById(GenderBean bean) {
		GenderBean bean1 = new GenderBean();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_gender where ad_gender_id=? ";
		try {
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_gender_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_gender_id(rs.getInt("ad_gender_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setGender(rs.getString("gender"));
			
			}
			}catch (Exception e) {
				System.out.print("GenderDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public GenderBean getGenderById(int ad_gender_id) {
		GenderBean bean1 = new GenderBean();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_gender where ad_gender_id=? ";
		try {
			ps=con.prepareStatement(query);
			ps.setInt(1, ad_gender_id);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_gender_id(rs.getInt("ad_gender_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setGender(rs.getString("gender"));
				
			}
		}catch (Exception e) {
			System.out.print("GenderDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<GenderBean> getAllGender() {
		ArrayList<GenderBean> bean = new ArrayList<GenderBean>();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_gender order by ad_gender_id";
		try {
			 ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				GenderBean bean1 = new GenderBean();
				bean1.setAd_gender_id(rs.getInt("ad_gender_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setGender(rs.getString("gender"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("GenderDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateGender(GenderBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_gender  SET  updated=?," +
					" updatedby=?, isactive=?, gender=? WHERE ad_gender_id=?";
			 ps = con.prepareStatement(query);
			
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getGender());
			ps.setInt(5, bean.getAd_gender_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("GenderDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public void deleteGender(GenderBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_gender WHERE ad_gender_id=?";
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_gender_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("GenderDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
}
