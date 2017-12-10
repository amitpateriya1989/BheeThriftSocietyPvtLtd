package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Bean.GenerateLoanInterestBean;

public class GenerateLoanInterestDao {
	private Connection con = null;

	public GenerateLoanInterestDao() {
		con = DBConnection.getConnection();
	}

	public int addGenerateLoanInterest(GenerateLoanInterestBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO generate_loan_interest( "
					+ " created, createdby, updated, updatedby, isactive, generate_date, status, total_int) "
					+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setDate(6, new java.sql.Date(bean.getGenerate_date().getTime()));
			ps.setString(7, bean.getStatus());
			ps.setDouble(8, bean.getTotal_int());
			
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("GenerateLoanInterestDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public GenerateLoanInterestBean getGenerateLoanInterestById(GenerateLoanInterestBean bean) {
		GenerateLoanInterestBean bean1 = new GenerateLoanInterestBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from generate_loan_interest where generate_loan_interest_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getGenerate_loan_interest_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setGenerate_loan_interest_id(rs.getInt("generate_loan_interest_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setGenerate_date(rs.getDate("generate_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setTotal_int(rs.getDouble("total_int"));
			}
			}catch (Exception e) {
				System.out.print("GenerateLoanInterestDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public GenerateLoanInterestBean getGenerateLoanInterestById(int generate_loan_interest_id) {
		GenerateLoanInterestBean bean1 = new GenerateLoanInterestBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from generate_loan_interest where generate_loan_interest_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, generate_loan_interest_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setGenerate_loan_interest_id(rs.getInt("generate_loan_interest_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setGenerate_date(rs.getDate("generate_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setTotal_int(rs.getDouble("total_int"));
			
				
			}
		}catch (Exception e) {
			System.out.print("GenerateLoanInterestDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<GenerateLoanInterestBean> getAllGenerateLoanInterest() {
		ArrayList<GenerateLoanInterestBean> bean = new ArrayList<GenerateLoanInterestBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from generate_loan_interest ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				GenerateLoanInterestBean bean1 = new GenerateLoanInterestBean();
				bean1.setGenerate_loan_interest_id(rs.getInt("generate_loan_interest_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setGenerate_date(rs.getDate("generate_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setTotal_int(rs.getDouble("total_int"));
				
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("GenerateLoanInterestDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public void updateGenerateLoanInterest(GenerateLoanInterestBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE generate_loan_interest "
					+ " SET generate_loan_interest_id=?, generate_date=?, status=?, created=?, "
					+ " createdby=?, updated=?, updatedby=?, isactive=?, total_int=? "
					+ " WHERE generate_loan_interest_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, true);
			ps.setDate(4, new java.sql.Date(bean.getGenerate_date().getTime()));
			ps.setString(5, bean.getStatus());
			ps.setDouble(6, bean.getTotal_int());
			ps.setInt(7, bean.getGenerate_loan_interest_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("GenerateLoanInterestDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public int deleteGenerateLoanInterest(GenerateLoanInterestBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM generate_loan_interest WHERE generate_loan_interest_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getGenerate_loan_interest_id());
			i=ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("GenerateLoanInterestDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}





}
