package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.DesignationTypeBean;

public class DesignationTypeDao {
	private Connection con = null;

	public DesignationTypeDao() {
			con = DBConnection.getConnection();
		}

	public int addDesignationType(DesignationTypeBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_designation_type( created, createdby," +
					"updated, updatedby,isactive, designation_type)" +
					" VALUES ( ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, bean.isIsactive());
			ps.setString(6, bean.getDesignation_type());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("DesignationTypeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public DesignationTypeBean getDesignationTypeById(DesignationTypeBean bean) {
		DesignationTypeBean bean1 = new DesignationTypeBean();
		ResultSet rs=null;
		PreparedStatement ps=null;
		String query = "select * from ad_designation_type where ad_designation_type_id=? ";
		try {
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_designation_type_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_designation_type_id(rs.getInt("ad_designation_type_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDesignation_type(rs.getString("designation_type"));
				
				

			}
			}catch (Exception e) {
				System.out.print("DesignationTypeDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public DesignationTypeBean getDesignationTypeById(int ad_designation_type_id) {
		DesignationTypeBean bean1 = new DesignationTypeBean();
		ResultSet rs=null;
		PreparedStatement ps=null;
		String query = "select * from ad_designation_type where ad_designation_type_id=? ";
		try {
			 ps = con.prepareStatement(query);
			ps.setInt(1, ad_designation_type_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_designation_type_id(rs.getInt("ad_designation_type_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDesignation_type(rs.getString("designation_type"));
			}
		}catch (Exception e) {
			System.out.print("DesignationTypeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<DesignationTypeBean> getAllDesignationType() {
		ArrayList<DesignationTypeBean> bean = new ArrayList<DesignationTypeBean>();
		ResultSet rs=null;
		PreparedStatement ps=null;
		String query = "select * from ad_designation_type ";
		try {
			 ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				DesignationTypeBean bean1 = new DesignationTypeBean();
				bean1.setAd_designation_type_id(rs.getInt("ad_designation_type_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDesignation_type(rs.getString("designation_type"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("DesignationTypeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateDesignationType(DesignationTypeBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_designation_type" +
					" SET updated=?, updatedby=?, isactive=?, designation_type=?" +
					"WHERE ad_designation_type_id=?";
			 ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, true);
			ps.setString(4, bean.getDesignation_type());
			ps.setInt(5, bean.getAd_designation_type_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("DesignationTypeDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public void deleteDesignationType(DesignationTypeBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_designation_type WHERE ad_designation_type_id=?";
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_designation_type_id());
			ps.executeQuery();
		
			}catch (Exception e) {
			System.out.print("DesignationTypeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}

}
