package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.RelationBean;

public class RelationDao {
	private Connection con = null;

	public RelationDao() {
			con = DBConnection.getConnection();
		}

	public int addRelation(RelationBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_relation(" +
					"created, createdby, updated, updatedby, isactive,relation)" +
					" VALUES ( ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getRelation());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("RelationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public RelationBean getRelationById(RelationBean bean) {
		RelationBean bean1 = new RelationBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_relation where ad_relation_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_relation_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_relation_id(rs.getInt("ad_relation_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRelation(rs.getString("relation"));
				
				

			}
			}catch (Exception e) {
				System.out.print("RelationDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public RelationBean getRelationById(int ad_relation_id) {
		RelationBean bean1 = new RelationBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_relation where ad_relation_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_relation_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_relation_id(rs.getInt("ad_relation_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRelation(rs.getString("relation"));
				
			}
		}catch (Exception e) {
			System.out.print("RelationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<RelationBean> getAllRelation() {
		ArrayList<RelationBean> bean = new ArrayList<RelationBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_relation ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				RelationBean bean1 = new RelationBean();
				bean1.setAd_relation_id(rs.getInt("ad_relation_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRelation(rs.getString("relation"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("RelationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateRelation(RelationBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_relation  SET  updated=?," +
					" updatedby=?, isactive=?, relation=? WHERE ad_relation_id=?";
			ps = con.prepareStatement(query);
			
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getRelation());
			ps.setInt(5, bean.getAd_relation_id());

			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("RelationDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deleteRelation(RelationBean bean){
		int i=0;
		PreparedStatement ps=null;
		
		try{
			String query="DELETE FROM ad_relation WHERE ad_relation_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_relation_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("RelationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
		
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}

}
