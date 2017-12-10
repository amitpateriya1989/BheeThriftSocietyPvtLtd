package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.LoanGuaranterBean;

public class LoanGuaranterDao {

	private Connection con = null;

	public LoanGuaranterDao() {
		con = DBConnection.getConnection();
		}

	public void addLoanGuaranter(LoanGuaranterBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_loan_guaranter(ad_member_id, created, createdby, updated,  updatedby, chk_qnt, chk_no_form, chk_no_to, chk_date, chk_bank_name, " +
					"  chk_bank_code, isactive,loan_trx_id)  VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)" ;
					
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_id());
			
			ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(3, bean.getCreatedby());
			ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(5, bean.getUpdatedby());
			ps.setInt(6, bean.getChk_qnt());
			ps.setInt(7, bean.getChk_no_form());
			ps.setInt(8, bean.getChk_no_to());
			ps.setDate(9, new java.sql.Date(bean.getChk_date().getTime()));
			ps.setString(10, bean.getChk_bank_name());
			ps.setString(11, bean.getChk_bank_code());
			ps.setBoolean(12, true);
			ps.setInt(13, bean.getLoan_trx_id());
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("LoanGuaranterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
//-----------------------------------------------------------------------------------------
	public LoanGuaranterBean getLoanGuaranterById(LoanGuaranterBean bean) {
		LoanGuaranterBean bean1 = new LoanGuaranterBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_loan_guaranter where ad_loan_guaranter_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_loan_guaranter_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_loan_guaranter_id(rs.getInt("ad_loan_guaranter_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setChk_bank_code(rs.getString("chk_bank_code"));
				bean1.setChk_qnt(rs.getInt("chk_qnt"));
				bean1.setChk_no_form(rs.getInt("chk_no_form"));
				bean1.setChk_no_to(rs.getInt("chk_no_to"));
				bean1.setChk_date(rs.getDate("chk_date"));
				bean1.setChk_bank_name(rs.getString("chk_bank_name"));
				bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
				
			}
			}catch (Exception e) {
				System.out.print("LoanGuaranterDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public LoanGuaranterBean getLoanGuaranterById(int ad_LoanGuaranter_id) {
		LoanGuaranterBean bean1 = new LoanGuaranterBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_loan_guaranter where ad_loan_guaranter_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_LoanGuaranter_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_loan_guaranter_id(rs.getInt("ad_loan_guaranter_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setChk_bank_code(rs.getString("chk_bank_code"));
				bean1.setChk_qnt(rs.getInt("chk_qnt"));
				bean1.setChk_no_form(rs.getInt("chk_no_form"));
				bean1.setChk_no_to(rs.getInt("chk_no_to"));
				bean1.setChk_date(rs.getDate("chk_date"));
				bean1.setChk_bank_name(rs.getString("chk_bank_name"));
				bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
				
			}
		}catch (Exception e) {
			System.out.print("LoanGuaranterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<LoanGuaranterBean> getAllLoanGuaranter() {
		ArrayList<LoanGuaranterBean> bean = new ArrayList<LoanGuaranterBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_loan_guaranter ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				LoanGuaranterBean bean1 = new LoanGuaranterBean();
				bean1.setAd_loan_guaranter_id(rs.getInt("ad_loan_guaranter_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setChk_bank_code(rs.getString("chk_bank_code"));
				bean1.setChk_qnt(rs.getInt("chk_qnt"));
				bean1.setChk_no_form(rs.getInt("chk_no_form"));
				bean1.setChk_no_to(rs.getInt("chk_no_to"));
				bean1.setChk_date(rs.getDate("chk_date"));
				bean1.setChk_bank_name(rs.getString("chk_bank_name"));
				bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("LoanGuaranterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public void updateLoanGuaranter(LoanGuaranterBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_loan_guaranter" +
					"SET  updated=?, updatedby=?," +
					"isactive=? WHERE ad_loan_guaranter_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, true);
			
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("LoanGuaranterDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public void deleteLoanGuaranter(LoanGuaranterBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_loan_guaranter WHERE ad_loan_guaranter_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_loan_guaranter_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("LoanGuaranterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}


}
