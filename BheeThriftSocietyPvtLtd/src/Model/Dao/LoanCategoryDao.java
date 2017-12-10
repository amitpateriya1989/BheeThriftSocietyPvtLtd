package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.LoanCategoryBean;

public class LoanCategoryDao {

	private Connection con = null;

	public LoanCategoryDao() {
		con = DBConnection.getConnection();
		}

	public int addLoanCategory(LoanCategoryBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_loan_category(" +
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
			System.out.print("LoanCategoryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public LoanCategoryBean getLoanCategoryById(LoanCategoryBean bean) {
		LoanCategoryBean bean1 = new LoanCategoryBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_loan__category where ad_loan_category_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_loan_category_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_loan_category_id(rs.getInt("ad_loan_category_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
				

			}
			}catch (Exception e) {
				System.out.print("LoanCategoryDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public LoanCategoryBean getLoanCategoryById(int ad_LoanCategory_id) {
		LoanCategoryBean bean1 = new LoanCategoryBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_loan_category where ad_loan_category_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_LoanCategory_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_loan_category_id(rs.getInt("ad_loan_category_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
			}
		}catch (Exception e) {
			System.out.print("LoanCategoryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<LoanCategoryBean> getAllLoanCategory() {
		ArrayList<LoanCategoryBean> bean = new ArrayList<LoanCategoryBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_loan_category ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				LoanCategoryBean bean1 = new LoanCategoryBean();
				bean1.setAd_loan_category_id(rs.getInt("ad_loan_category_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("LoanCategoryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public ArrayList<LoanCategoryBean> getSpecificLoanCategory(String name) {
		ArrayList<LoanCategoryBean> bean = new ArrayList<LoanCategoryBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "SELECT * FROM ad_loan_category where isactive = true and trim(lower(name)) = trim(lower(?))";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				LoanCategoryBean bean1 = new LoanCategoryBean();
				bean1.setAd_loan_category_id(rs.getInt("ad_loan_category_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("LoanCategoryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateLoanCategory(LoanCategoryBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_loan_category " +
					"SET  updated=?, updatedby=?, " +
					"isactive=?, name=? WHERE ad_loan_category_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getName());
			ps.setInt(5, bean.getAd_loan_category_id());

			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("LoanCategoryDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public void deleteLoanCategory(LoanCategoryBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_loan_category WHERE ad_loan_category_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_loan_category_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("LoanCategoryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}


}
