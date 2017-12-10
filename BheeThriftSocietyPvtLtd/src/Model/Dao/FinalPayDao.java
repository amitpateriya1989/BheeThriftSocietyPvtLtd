package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Model.Bean.FinalPayBean;
import Model.Bean.FinalPayDetailBean;
import Model.Bean.TransactionBean;

public class FinalPayDao {
	private Connection con = null;

	public FinalPayDao() {
		try{
			con = DBConnection.getConnection();

		}catch(Exception s){
			System.out.println("FinalPayDao:-Exception in Creating connection and auto commit off");
			s.printStackTrace();
		}

	}

	public int addFinalPay(FinalPayBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {

			String query = "INSERT INTO ad_final_pay( " +
					"updated, updatedby, created, createdby, isactive, fdgs_amt, int_fdgs, " +
					"share_amt, loan_amt, int_loan, compensation_amt, ad_member_id, ad_voucher_id, " +
					"reson, reson_date, final_pay_date,amt_in_words) VALUES " +
					"( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;					
			ps = con.prepareStatement(query);
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getCreatedby());
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());			
			ps.setBoolean(5, true);
			ps.setDouble(6, bean.getFdgs_amt());
			ps.setDouble(7, bean.getInt_fdgs());
			ps.setDouble(8, bean.getShare_amt());
			ps.setDouble(9, bean.getLoan_amt());
			ps.setDouble(10, bean.getInt_loan());
			ps.setDouble(11, bean.getCompensation_amt());
			ps.setDouble(12, bean.getAd_member_id());
			ps.setDouble(13, bean.getAd_voucher_id());
			ps.setString(14, bean.getReson());
			ps.setDate(15, new java.sql.Date(bean.getReson_date().getTime()));
			ps.setDate(16, new java.sql.Date(bean.getFinal_pay_date().getTime()));
			ps.setString(17,bean.getAmt_in_words());
			record=ps.executeUpdate();

		} catch (Exception e) {
			System.out.print("FinalPayDao:-error in try Block");
			e.printStackTrace();
			if(record==0){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("FinalPayDao:-error in rollback");
					s.printStackTrace();
				}

			}
		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return record;
	}
	//-----------------------------------------------------------------------------------------
	public FinalPayBean getFinalPayById(FinalPayBean bean) {
		FinalPayBean bean1 = new FinalPayBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_final_pay where ad_final_pay_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_final_pay_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_final_pay_id(rs.getInt("ad_final_pay_id"));				
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setFdgs_amt(rs.getDouble("fdgs_amt"));
				bean1.setInt_fdgs(rs.getInt("int_fdgs"));
				bean1.setShare_amt(rs.getDouble("share_amt"));
				bean1.setLoan_amt(rs.getDouble("loan_amt"));
				bean1.setInt_loan(rs.getDouble("int_loan"));
				bean1.setCompensation_amt(rs.getDouble("compensation_amt"));
				bean1.setFinal_pay_date(rs.getDate("final_pay_date"));
				bean1.setReson_date(rs.getDate("reson_date"));
				bean1.setReson(rs.getString("reson"));
				bean1.setAmt_in_words(rs.getString("amt_in_words"));
			}
		}catch (Exception e) {
			System.out.print("FinalPayDao:-error in try Block");
			e.printStackTrace();
			if(rs!=null){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("FinalPayDao:-error in rollback");
					s.printStackTrace();
				}

			}
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}
	//------------------------------------------------------------------------------------------

	public int getFinalPaymidVById(int ad_voucher_id) {

		PreparedStatement ps =null;
		ResultSet rs=null;
		int id=0;
		String query = "select ad_member_id from ad_final_pay where ad_voucher_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			rs = ps.executeQuery();
			while (rs.next()) {

				id=rs.getInt("ad_member_id");


			}
		}catch (Exception e) {
			System.out.print("FinalPayDao:-error in try Block");
			e.printStackTrace();
			if(rs!=null){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("FinalPayDao:-error in rollback");
					s.printStackTrace();
				}

			}
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return id;
	}
	//--------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------

	public FinalPayBean getFinalPayById(int ad_final_pay_id) {
		FinalPayBean bean1 = new FinalPayBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_final_pay where ad_final_pay_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_final_pay_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_final_pay_id(rs.getInt("ad_final_pay_id"));				
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setFdgs_amt(rs.getDouble("fdgs_amt"));
				bean1.setInt_fdgs(rs.getInt("int_fdgs"));
				bean1.setShare_amt(rs.getDouble("share_amt"));
				bean1.setLoan_amt(rs.getDouble("loan_amt"));
				bean1.setInt_loan(rs.getDouble("int_loan"));
				bean1.setCompensation_amt(rs.getDouble("compensation_amt"));
				bean1.setFinal_pay_date(rs.getDate("final_pay_date"));
				bean1.setReson_date(rs.getDate("reson_date"));
				bean1.setReson(rs.getString("reson"));
				bean1.setAmt_in_words(rs.getString("amt_in_words"));

			}
		}catch (Exception e) {
			System.out.print("FinalPayDao:-error in try Block");
			e.printStackTrace();
			if(rs!=null){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("FinalPayDao:-error in rollback");
					s.printStackTrace();
				}

			}
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}
	//--------------------------------------------------------------------------------------
	public ArrayList<FinalPayBean> getAllFinalPay() {
		ArrayList<FinalPayBean> bean = new ArrayList<FinalPayBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_final_pay ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				FinalPayBean bean1 = new FinalPayBean();
				bean1.setAd_final_pay_id(rs.getInt("ad_final_pay_id"));				
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setFdgs_amt(rs.getDouble("fdgs_amt"));
				bean1.setInt_fdgs(rs.getInt("int_fdgs"));
				bean1.setShare_amt(rs.getDouble("share_amt"));
				bean1.setLoan_amt(rs.getDouble("loan_amt"));
				bean1.setInt_loan(rs.getDouble("int_loan"));
				bean1.setCompensation_amt(rs.getDouble("compensation_amt"));
				bean1.setFinal_pay_date(rs.getDate("final_pay_date"));
				bean1.setReson_date(rs.getDate("reson_date"));
				bean1.setReson(rs.getString("reson"));
				bean1.setAmt_in_words(rs.getString("amt_in_words"));

				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("FinalPayDao:-error in try Block");
			e.printStackTrace();
			if(rs!=null){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("FinalPayDao:-error in rollback");
					s.printStackTrace();
				}

			}
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}



	//--------------------------------------------------------------------------------------

	//--------------------------------------------------------------------------------------
		public ArrayList<FinalPayDetailBean> getAllFinalPayDetail(String vstatus,Date from,Date to) {
			ArrayList<FinalPayDetailBean> bean = new ArrayList<FinalPayDetailBean>();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from final_pay_detail where voucher_status=? AND trx_date between ? AND ? ";
			try {
				ps = con.prepareStatement(query);
				ps.setString(1,vstatus);
				ps.setDate(2, new java.sql.Date(from.getTime()));
				ps.setDate(3, new java.sql.Date(to.getTime()));
				rs = ps.executeQuery();
				while (rs.next()) {
					FinalPayDetailBean bean1 = new FinalPayDetailBean();
					bean1.setAd_final_pay_id(rs.getInt("ad_final_pay_id"));				
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
					bean1.setAd_society_no(rs.getInt("ad_society_no"));
					bean1.setName(rs.getString("name"));
					bean1.setFather(rs.getString("father"));
					bean1.setMember_status(rs.getString("member_status"));
					bean1.setFinal_pay_date(rs.getDate("final_pay_date"));
					bean1.setTrx_date(rs.getDate("trx_date"));
					bean1.setReson_date(rs.getDate("reson_date"));
					bean1.setDescription(rs.getString("description"));
					bean1.setInstrument_date(rs.getDate("instrument_date"));
					bean1.setInstrument_from(rs.getString("instrument_from"));
					bean1.setInstrument_no(rs.getString("instrument_no"));
					bean1.setPay_reson(rs.getString("pay_reson"));
					bean1.setTrx_no(rs.getInt("trx_no"));
					bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
					bean1.setVamt(rs.getDouble("vamt"));
					bean1.setVfrom(rs.getString("vfrom"));
					bean1.setVno(rs.getString("vno"));
					bean1.setVoucher_status(rs.getString("voucher_status"));
					bean1.setVoucher_type(rs.getString("voucher_type"));
					bean1.setVtype(rs.getString("vtype"));
					bean1.setFdgs_amt(rs.getDouble("fdgs_amt"));
					bean1.setInt_fdgs(rs.getInt("int_fdgs"));
					bean1.setShare_amt(rs.getDouble("share_amt"));
					bean1.setLoan_amt(rs.getDouble("loan_amt"));
					bean1.setInt_loan(rs.getDouble("int_loan"));
					bean1.setCompensation_amt(rs.getDouble("compensation_amt"));
					bean1.setAmt_in_words(rs.getString("amt_in_words"));


					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("FinalPayDao:-error in try Block");
				e.printStackTrace();
				if(rs!=null){
					try{
						con.rollback();
					}catch(SQLException s){
						System.out.print("FinalPayDao:-error in rollback");
						s.printStackTrace();
					}

				}
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return bean;
		}


		//--------------------------------------------------------------------------------------
				public ArrayList<FinalPayDetailBean> getAllFinalPayDetail() {
					ArrayList<FinalPayDetailBean> bean = new ArrayList<FinalPayDetailBean>();
					PreparedStatement ps =null;
					ResultSet rs=null;
					String query = "select * from final_pay_detail where voucher_status=?";
					try {
						ps = con.prepareStatement(query);
						ps.setString(1, "Approved");
						rs = ps.executeQuery();
						while (rs.next()) {
							FinalPayDetailBean bean1 = new FinalPayDetailBean();
							bean1.setAd_final_pay_id(rs.getInt("ad_final_pay_id"));				
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
							bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
							bean1.setAd_society_no(rs.getInt("ad_society_no"));
							bean1.setName(rs.getString("name"));
							bean1.setFather(rs.getString("father"));
							bean1.setMember_status(rs.getString("member_status"));
							bean1.setFinal_pay_date(rs.getDate("final_pay_date"));
							bean1.setTrx_date(rs.getDate("trx_date"));
							bean1.setReson_date(rs.getDate("reson_date"));
							bean1.setDescription(rs.getString("description"));
							bean1.setInstrument_date(rs.getDate("instrument_date"));
							bean1.setInstrument_from(rs.getString("instrument_from"));
							bean1.setInstrument_no(rs.getString("instrument_no"));
							bean1.setPay_reson(rs.getString("pay_reson"));
							bean1.setTrx_no(rs.getInt("trx_no"));
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
							bean1.setVamt(rs.getDouble("vamt"));
							bean1.setVfrom(rs.getString("vfrom"));
							bean1.setVno(rs.getString("vno"));
							bean1.setVoucher_status(rs.getString("voucher_status"));
							bean1.setVoucher_type(rs.getString("voucher_type"));
							bean1.setVtype(rs.getString("vtype"));
							bean1.setFdgs_amt(rs.getDouble("fdgs_amt"));
							bean1.setInt_fdgs(rs.getInt("int_fdgs"));
							bean1.setShare_amt(rs.getDouble("share_amt"));
							bean1.setLoan_amt(rs.getDouble("loan_amt"));
							bean1.setInt_loan(rs.getDouble("int_loan"));
							bean1.setCompensation_amt(rs.getDouble("compensation_amt"));
							bean1.setAmt_in_words(rs.getString("amt_in_words"));


							bean.add(bean1);

						}
					}catch (Exception e) {
						System.out.print("FinalPayDao:-error in try Block");
						e.printStackTrace();
						if(rs!=null){
							try{
								con.rollback();
							}catch(SQLException s){
								System.out.print("FinalPayDao:-error in rollback");
								s.printStackTrace();
							}

						}
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
					}
					return bean;
				}



		//--------------------------------------------------------------------------------------

	public void updateFinalPayStatus(FinalPayBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_final_pay" +
					"SET  updated=?, updatedby=?," +
					"isactive=? WHERE ad_final_pay_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setInt(4, bean.getAd_final_pay_id());


			//System.out.print(ps);
			i=ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("FinalPayDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}

	//----------------------------------------------------------------------------------------------
	public void deleteFinalPay(FinalPayBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_final_pay WHERE ad_final_pay_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_final_pay_id());
			ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("FinalPayDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}

	//----------------------------------------------------------------------------------------------
	public void deleteFinalPay(int ad_voucher_id){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_final_pay WHERE ad_voucher_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			ps.executeUpdate();
			
			
			
		}catch (Exception e) {
			System.out.print("FinalPayDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}


}
