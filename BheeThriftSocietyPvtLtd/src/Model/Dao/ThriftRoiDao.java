package Model.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.ThriftRoiBean;

public class ThriftRoiDao {
	private Connection con = null;

	public ThriftRoiDao() {
		con = DBConnection.getConnection();
	}

	public int addThriftRoi(ThriftRoiBean bean) {
		int record=0;
		PreparedStatement ps=null;

		try {
			con = DBConnection.getConnection();
			String query = "INSERT INTO ad_thrift_roi(created, createdby, updeted, updatedby,  " +
					"effectivefromdate,effectivetodate,  isactive, roi,ratio)" +
					"    VALUES ( ?, ?, ?, ?, ?, ?, ?,?,?);" ;

			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());				
			ps.setDate(5, new java.sql.Date(bean.getEffectivefromdate().getTime()));
			ps.setDate(6, new java.sql.Date(bean.getEffectivetodate().getTime()));
			ps.setBoolean(7, true);
			ps.setDouble(8, bean.getRoi());	
			ps.setDouble(9, bean.getRatio());	
			//new ThriftRoiDao().updateThriftRoi(bean);			

			record=ps.executeUpdate();

		} catch (Exception e) {
			System.out.print("ThriftRoiDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return record;
	}


	public ArrayList<ThriftRoiBean> getAllThriftRoi() {
		ArrayList<ThriftRoiBean> bean = new ArrayList<ThriftRoiBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT * FROM ad_thrift_roi ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				ThriftRoiBean bean1 = new ThriftRoiBean();
				bean1.setAd_thrift_roi_id(rs.getInt("ad_thrift_roi_id"));					
				bean1.setEffectivefromdate(rs.getDate("effectivefromdate"));
				bean1.setEffectivetodate(rs.getDate("effectivetodate"));
				bean1.setRatio(rs.getDouble("ratio"));
				bean1.setRoi(rs.getDouble("roi"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("ThriftRoiDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}
	//----------------------------------------------------------------------------------------------

	public double getAllThriftRoiactive() {
		double result=0.0;
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT * FROM ad_thrift_roi where isactive='TRUE' ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				ThriftRoiBean bean1 = new ThriftRoiBean();


				result=rs.getDouble("roi");


			}
		}catch (Exception e) {
			System.out.print("ThriftRoiDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return result;
	}
	//----------------------------------------------------------------------------------------------
	public int updateThriftRoi(ThriftRoiBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_thrift_roi   SET updeted=?, updatedby=?, " +
					" roi=?, effectivefromdate=?, effectivetodate=?, isactive=?, ratio=? WHERE ad_thrift_roi_id=? ";
			ps = con.prepareStatement(query);

			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setDouble(3, bean.getRoi());
			ps.setDate(4, new java.sql.Date(bean.getEffectivetodate().getTime()));
			ps.setDate(5,new java.sql.Date(bean.getEffectivefromdate().getTime()));
			ps.setBoolean(6, true);
			ps.setDouble(7, bean.getRatio());
			ps.setInt(8, bean.getAd_thrift_roi_id());	


			//System.out.print(ps);
			i=ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("ThriftRoiDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return i;
	}
	//----------------------------------------------------------------------------------------------

	public ThriftRoiBean getThriftRoiMaxId() {
		ThriftRoiBean bean1 = new ThriftRoiBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT * FROM ad_thrift_roi where ad_thrift_roi_id= (select max(ad_thrift_roi_id) from ad_thrift_roi) ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {

				bean1.setAd_thrift_roi_id(rs.getInt("ad_thrift_roi_id"));					
				bean1.setEffectivefromdate(rs.getDate("effectivefromdate"));
				bean1.setEffectivetodate(rs.getDate("effectivetodate"));
				bean1.setRatio(rs.getDouble("ratio"));
				bean1.setRoi(rs.getDouble("roi"));




			}
		}catch (Exception e) {
			System.out.print("ThriftRoiDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}	


	//----------------------------------------------------------------------------------------------------------------------------------
	public ThriftRoiBean getThriftRoiById(int ad_thrift_roi_id) {
		ThriftRoiBean bean1 = new ThriftRoiBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT * FROM ad_thrift_roi where ad_thrift_roi_id= ? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_thrift_roi_id);
			rs = ps.executeQuery();
			while (rs.next()) {

				bean1.setAd_thrift_roi_id(rs.getInt("ad_thrift_roi_id"));					
				bean1.setEffectivefromdate(rs.getDate("effectivefromdate"));
				bean1.setEffectivetodate(rs.getDate("effectivetodate"));
				bean1.setRatio(rs.getDouble("ratio"));
				bean1.setRoi(rs.getDouble("roi"));




			}
		}catch (Exception e) {
			System.out.print("ThriftRoiDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}	
}
