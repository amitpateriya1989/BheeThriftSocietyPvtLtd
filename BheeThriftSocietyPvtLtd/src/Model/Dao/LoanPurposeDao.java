package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.LoanPurposeBean;

public class LoanPurposeDao {

	private Connection con = null;

	public LoanPurposeDao() {
		con = DBConnection.getConnection();
		}

	public void addLoanPurpose(LoanPurposeBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_loan_purpose(" +
					"created, createdby, updated, updatedby, isactive, purpose)" +
					" VALUES (?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getPurpose());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("LoanPurposeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
//-----------------------------------------------------------------------------------------
	public LoanPurposeBean getLoanPurposeById(LoanPurposeBean bean) {
		LoanPurposeBean bean1 = new LoanPurposeBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_loan__purpose where ad_loan_purpose_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_loan_purpose_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_loan_purpose_id(rs.getInt("ad_loan_purpose_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setPurpose(rs.getString("purpose"));
				
				

			}
			}catch (Exception e) {
				System.out.print("LoanPurposeDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public LoanPurposeBean getLoanPurposeById(int ad_LoanPurpose_id) {
		LoanPurposeBean bean1 = new LoanPurposeBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_loan_purpose where ad_loan_purpose_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_LoanPurpose_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_loan_purpose_id(rs.getInt("ad_loan_purpose_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setPurpose(rs.getString("purpose"));
				
			}
		}catch (Exception e) {
			System.out.print("LoanPurposeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<LoanPurposeBean> getAllLoanPurpose() {
		ArrayList<LoanPurposeBean> bean = new ArrayList<LoanPurposeBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_loan_purpose ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				LoanPurposeBean bean1 = new LoanPurposeBean();
				bean1.setAd_loan_purpose_id(rs.getInt("ad_loan_purpose_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setPurpose(rs.getString("purpose"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("LoanPurposeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateLoanPurpose(LoanPurposeBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_loan_purpose " +
					" SET  updated=?, updatedby=?, " +
					"isactive=?, purpose=? WHERE ad_loan_purpose_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getPurpose());
			ps.setInt(5, bean.getAd_loan_purpose_id());
			//System.out.print(ps);
			i=ps.executeUpdate();		
	}catch (Exception e) {
		System.out.print("LoanPurposeDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
return i;
	}
//----------------------------------------------------------------------------------------------
	public void deleteLoanPurpose(LoanPurposeBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_loan_purpose WHERE ad_loan_purpose_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_loan_purpose_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("LoanPurposeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}


}
