package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.FdCategoryBean;

public class FdCategoryDao {

	private Connection con = null;

	public FdCategoryDao() {
		con = DBConnection.getConnection();
		}

	public int addFdCategory(FdCategoryBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_fd_category(" +
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
			System.out.print("FdCategoryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public FdCategoryBean getFdCategoryById(FdCategoryBean bean) {
		FdCategoryBean bean1 = new FdCategoryBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_fd__category where ad_fd_category_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_fd_category_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_fd_category_id(rs.getInt("ad_fd_category_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
				

			}
			}catch (Exception e) {
				System.out.print("FdCategoryDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public FdCategoryBean getFdCategoryById(int ad_FdCategory_id) {
		FdCategoryBean bean1 = new FdCategoryBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_fd_category where ad_fd_category_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_FdCategory_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_fd_category_id(rs.getInt("ad_fd_category_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
			}
		}catch (Exception e) {
			System.out.print("FdCategoryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<FdCategoryBean> getAllFdCategory() {
		ArrayList<FdCategoryBean> bean = new ArrayList<FdCategoryBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_fd_category";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				FdCategoryBean bean1 = new FdCategoryBean();
				bean1.setAd_fd_category_id(rs.getInt("ad_fd_category_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("FdCategoryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateFdCategory(FdCategoryBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_fd_category " +
					"SET  updated=?, updatedby=?, " +
					"isactive=?, name=? WHERE ad_fd_category_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getName());
			ps.setInt(5, bean.getAd_fd_category_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("FdCategoryDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deleteFdCategory(FdCategoryBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_fd_category WHERE ad_fd_category_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_fd_category_id());
			i = ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("FdCategoryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}


}
