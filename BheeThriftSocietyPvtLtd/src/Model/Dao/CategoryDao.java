package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.CategoryBean;

public class CategoryDao {
	private Connection con = null;

	public CategoryDao() {
			con = DBConnection.getConnection();
	}

	public int addCategory(CategoryBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_category(" +
					"created, createdby, updated, updatedby, isactive,category)" +
					" VALUES ( ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, bean.isIsactive());
			ps.setString(6, bean.getCategory());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("CategoryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public CategoryBean getCategoryById(CategoryBean bean) {
		CategoryBean bean1 = new CategoryBean();
		ResultSet rs=null;
		PreparedStatement ps=null;
		String query = "select * from ad_category where ad_category_id=? ";
		try {
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_category_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_category_id(rs.getInt("ad_category_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setCategory(rs.getString("category"));
				
				

			}
			}catch (Exception e) {
				System.out.print("CategoryDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public CategoryBean getCategoryById(int ad_category_id) {
		CategoryBean bean1 = new CategoryBean();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_category where ad_category_id=? ";
		try {
			 ps = con.prepareStatement(query);
			ps.setInt(1, ad_category_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_category_id(rs.getInt("ad_category_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setCategory(rs.getString("category"));
				
			}
		}catch (Exception e) {
			System.out.print("CategoryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<CategoryBean> getAllCategory() {
		ArrayList<CategoryBean> bean = new ArrayList<CategoryBean>();
		ResultSet rs=null;
		PreparedStatement ps=null;
		String query = "select * from ad_category order by ad_category_id ";
		try {
			 ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				CategoryBean bean1 = new CategoryBean();
				bean1.setAd_category_id(rs.getInt("ad_category_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setCategory(rs.getString("category"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("CategoryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateCategory(CategoryBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_category  SET  updated=?," +
					" updatedby=?, isactive=?, category=? WHERE ad_category_id=?";
			 ps = con.prepareStatement(query);
			
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getCategory());
			ps.setInt(5, bean.getAd_category_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("CategoryDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
	
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deleteCategory(CategoryBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_category WHERE ad_category_id=?";
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_category_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("CategoryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}



}
