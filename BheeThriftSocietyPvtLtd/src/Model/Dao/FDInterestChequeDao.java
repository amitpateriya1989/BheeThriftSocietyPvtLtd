package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Bean.FDInterestChequeBean;

public class FDInterestChequeDao {
	private Connection con = null;

	public FDInterestChequeDao() {
		con = DBConnection.getConnection();
		}

	public int addFDInterestCheque(FDInterestChequeBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_fd_int_cheque_detail " +
					"(created, createdby, updated, updatedby, isactive, " +
					"cheque_no, cheque_date, branch, cheque_amt, ad_fd_trx_id) " +
					" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, bean.isIsactive());
			ps.setString(6, bean.getCheque_no());
			ps.setDate(7,new java.sql.Date(bean.getCheque_date().getTime()));
		    ps.setString(8, bean.getBranch());
		    ps.setDouble(9, bean.getCheque_amt());
		    ps.setInt(10, bean.getAd_fd_trx_id());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("FDInterestChequeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public FDInterestChequeBean getFDInterestChequeById(FDInterestChequeBean bean) {
		FDInterestChequeBean bean1 = new FDInterestChequeBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_fd_int_cheque_detail where ad_fd_int_cheque_detail_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_fd_int_cheque_detail_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_fd_int_cheque_detail_id(rs.getInt("ad_fd_int_cheque_detail_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
				bean1.setBranch(rs.getString("branch"));
				bean1.setCheque_amt(rs.getDouble("cheque_amt"));
				bean1.setCheque_date(rs.getDate("cheque_date"));
				bean1.setCheque_no(rs.getString("cheque_no"));
			
			}
			}catch (Exception e) {
				System.out.print("FDInterestChequeDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public FDInterestChequeBean getFDInterestChequeById(int ad_fd_int_cheque_detail_id) {
		FDInterestChequeBean bean1 = new FDInterestChequeBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_fd_int_cheque_detail where ad_fd_int_cheque_detail_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_fd_int_cheque_detail_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_fd_int_cheque_detail_id(rs.getInt("ad_fd_int_cheque_detail_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
				bean1.setBranch(rs.getString("branch"));
				bean1.setCheque_amt(rs.getDouble("cheque_amt"));
				bean1.setCheque_date(rs.getDate("cheque_date"));
				bean1.setCheque_no(rs.getString("cheque_no"));
				
			}
		}catch (Exception e) {
			System.out.print("FDInterestChequeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<FDInterestChequeBean> getAllFDInterestCheque() {
		ArrayList<FDInterestChequeBean> bean = new ArrayList<FDInterestChequeBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_fd_int_cheque_detail ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				FDInterestChequeBean bean1 = new FDInterestChequeBean();
				bean1.setAd_fd_int_cheque_detail_id(rs.getInt("ad_fd_int_cheque_detail_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
				bean1.setBranch(rs.getString("branch"));
				bean1.setCheque_amt(rs.getDouble("cheque_amt"));
				bean1.setCheque_date(rs.getDate("cheque_date"));
				bean1.setCheque_no(rs.getString("cheque_no"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("FDInterestChequeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateFDInterestCheque(FDInterestChequeBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_fd_int_cheque_detail " +
					" SET updated=?, updatedby=?, isactive=?, cheque_no=?, " +
					" cheque_date=?, branch=?, cheque_amt=?, ad_fd_trx_id=? " +
					" WHERE ad_fd_int_cheque_detail_id=?,";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getCheque_no());
			ps.setDate(5,new java.sql.Date(bean.getCheque_date().getTime()));
		    ps.setString(6, bean.getBranch());
		    ps.setDouble(7, bean.getCheque_amt());
		    ps.setInt(8, bean.getAd_fd_trx_id());
            ps.setInt(9, bean.getAd_fd_int_cheque_detail_id());
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("FDInterestChequeDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deleteFDInterestCheque(FDInterestChequeBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_fd_int_cheque_detail WHERE ad_fd_int_cheque_detail_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_fd_int_cheque_detail_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("FDInterestChequeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}//end delete function


}
