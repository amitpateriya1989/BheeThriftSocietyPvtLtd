package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.DesignationLevelBean;

public class DesignationLevelDao {
	private Connection con = null;

	public DesignationLevelDao() {
			con = DBConnection.getConnection();
		}

	public int addDesignationLevel(DesignationLevelBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_designation_level( created, createdby," +
					"updated, updatedby,isactive, designation_level)" +
					" VALUES ( ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getDesignation_level());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("DesignationLevelDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
		
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public DesignationLevelBean getDesignationLevelById(DesignationLevelBean bean) {
		DesignationLevelBean bean1 = new DesignationLevelBean();
		ResultSet rs=null;
		PreparedStatement ps=null;
		String query = "select * from ad_designation_level where ad_designation_level_id=? ";
		try {
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_designation_level_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_designation_level_id(rs.getInt("ad_designation_level_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDesignation_level(rs.getString("designation_level"));
				
				

			}
			}catch (Exception e) {
				System.out.print("DesignationLevelDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public DesignationLevelBean getDesignationLevelById(int ad_designation_level_id) {
		DesignationLevelBean bean1 = new DesignationLevelBean();
		ResultSet rs=null;
		PreparedStatement ps=null;
		String query = "select * from ad_designation_level where ad_designation_level_id=? ";
		try {
			 ps = con.prepareStatement(query);
			ps.setInt(1, ad_designation_level_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_designation_level_id(rs.getInt("ad_designation_level_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDesignation_level(rs.getString("designation_level"));
				
			}
		}catch (Exception e) {
			System.out.print("DesignationLevelDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<DesignationLevelBean> getAllDesignationLevel() {
		ArrayList<DesignationLevelBean> bean = new ArrayList<DesignationLevelBean>();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_designation_level ";
		try {
			 ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				DesignationLevelBean bean1 = new DesignationLevelBean();
				bean1.setAd_designation_level_id(rs.getInt("ad_designation_level_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDesignation_level(rs.getString("designation_level"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("DesignationLevelDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateDesignationLevel(DesignationLevelBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_designation_level " +
					"SET  updated=?, updatedby=?," +
					"isactive=?, designation_level=? WHERE ad_designation_level_id=?";
			 ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, true);
			ps.setString(4, bean.getDesignation_level());
			ps.setInt(5, bean.getAd_designation_level_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("DesignationLevelDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public void deleteDesignationLevel(DesignationLevelBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_designation_level WHERE ad_designation_level_id=?";
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_designation_level_id());
			ps.executeQuery();
		
			}catch (Exception e) {
			System.out.print("DesignationLevelDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}


}
