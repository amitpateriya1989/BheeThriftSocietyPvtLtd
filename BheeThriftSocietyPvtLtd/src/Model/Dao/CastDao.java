package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.CastBean;

public class CastDao {
	private Connection con = null;

	public CastDao() {
		con = DBConnection.getConnection();
	}

	public int addCast(CastBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_cast(" +
					"created, createdby, updated, updatedby, isactive," +
					"cast_type) VALUES (?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getCast_type());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("CastDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public CastBean getCastById(CastBean bean) {
		CastBean bean1 = new CastBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_cast where ad_cast_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_cast_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_cast_id(rs.getInt("ad_cast_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setCast_type(rs.getString("cast_type"));
			}
			}catch (Exception e) {
				System.out.print("CastDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public CastBean getCastById(int ad_cast_id) {
		CastBean bean1 = new CastBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_cast where ad_cast_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_cast_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_cast_id(rs.getInt("ad_cast_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setCast_type(rs.getString("cast_type"));
				
			}
		}catch (Exception e) {
			System.out.print("CastDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<CastBean> getAllCast() {
		ArrayList<CastBean> bean = new ArrayList<CastBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_cast ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				CastBean bean1 = new CastBean();
				bean1.setAd_cast_id(rs.getInt("ad_cast_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setCast_type(rs.getString("cast_type"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("CastDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public void updateCast(CastBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_cast" +
					"SET  updated=?, updatedby=?, isactive=?, cast_type=?" +
					"WHERE ad_cast_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, true);
			ps.setString(4, bean.getCast_type());
			ps.setInt(5, bean.getAd_cast_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("CastDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public int deleteCast(CastBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_cast WHERE ad_cast_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_cast_id());
			i=ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("CastDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}




}
