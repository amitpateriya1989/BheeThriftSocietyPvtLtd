package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Bean.LedgerAccountBean;
import Model.Bean.TempTranxBean;
import Model.Bean.VoucherBean;

public class TempTranxDao {

	private Connection con = null;

	public TempTranxDao() {
		con = DBConnection.getConnection();
	}

	public void addTempTranx(TempTranxBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {

			String query = "INSERT INTO ad_trx_temp(" +
					"created, createdby, updated, updatedby, isactive, ad_voucher_id," +
					"ad_account_id, trx_date, dramt, cramt, narration, ad_member_id)" +
					"VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)" ;

			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setInt(6, bean.getVoucher().getAd_voucher_id());
			ps.setInt(7, bean.getLedger().getAd_account_id());
			ps.setTimestamp(8, new java.sql.Timestamp(bean.getTrx_date().getTime()));
			ps.setDouble(9, bean.getDramt());
			ps.setDouble(10, bean.getCramt());
			ps.setString(11, bean.getNarration());
			ps.setInt(12, bean.getMember().getAd_member_id());
			record=ps.executeUpdate();

		} catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}
	//-----------------------------------------------------------------------------------------
	public TempTranxBean getTempTranxById(TempTranxBean bean) {
		TempTranxBean bean1 = new TempTranxBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_trx_temp where ad_trx_temp_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_trx_temp_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));
				bean1.setAd_trx_temp_id(rs.getInt("ad_trx_temp_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDramt(rs.getDouble("dramt"));
				bean1.setCramt(rs.getDouble("cramt"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setNarration(rs.getString("narration"));



			}
		}catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}

	//-----------------------------------------------------------------------------------------
	public TempTranxBean getTotalDrCrByVoucherId(int ad_voucher_id) {
		TempTranxBean bean1 = new TempTranxBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select sum(dramt) as dramt,sum(cramt) as cramt from ad_trx_temp where ad_voucher_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			rs = ps.executeQuery();
			while (rs.next()) {

				bean1.setDramt(rs.getDouble("dramt"));
				bean1.setCramt(rs.getDouble("cramt"));

			}
		}catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}

	//-----------------------------------------------------------------------------------------
	public ArrayList<TempTranxBean> getTempTranxByType(int ad_voucher_id) {
		ArrayList<TempTranxBean> bean = new ArrayList<TempTranxBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_trx_temp where ad_voucher_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				TempTranxBean bean1 = new TempTranxBean();
				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));
				bean1.setAd_trx_temp_id(rs.getInt("ad_trx_temp_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDramt(rs.getDouble("dramt"));
				bean1.setCramt(rs.getDouble("cramt"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setNarration(rs.getString("narration"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}

	//------------------------------------------------------------------------------------------	
	public TempTranxBean getTempTranxById(int ad_trx_temp_id) {
		TempTranxBean bean1 = new TempTranxBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_trx_temp where ad_trx_temp_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_trx_temp_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));
				bean1.setAd_trx_temp_id(rs.getInt("ad_trx_temp_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDramt(rs.getDouble("dramt"));
				bean1.setCramt(rs.getDouble("cramt"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setNarration(rs.getString("narration"));


			}
		}catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}
	//--------------------------------------------------------------------------------------
	public ArrayList<TempTranxBean> getAllTempTranx() {
		ArrayList<TempTranxBean> bean = new ArrayList<TempTranxBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_trx_temp ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				TempTranxBean bean1 = new TempTranxBean();
				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));
				bean1.setAd_trx_temp_id(rs.getInt("ad_trx_temp_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDramt(rs.getDouble("dramt"));
				bean1.setCramt(rs.getDouble("cramt"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setNarration(rs.getString("narration"));

				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}


	//--------------------------------------------------------------------------------------
	public ArrayList<TempTranxBean> getAllTempTranxByVoucherId(int ad_voucher_id) {
		ArrayList<TempTranxBean> bean = new ArrayList<TempTranxBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_trx_temp where ad_voucher_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				TempTranxBean bean1 = new TempTranxBean();

				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setMember(new MemberRegistrationDao().getMemberName(rs.getInt("ad_member_id")));
				bean1.setAd_trx_temp_id(rs.getInt("ad_trx_temp_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDramt(rs.getDouble("dramt"));
				bean1.setCramt(rs.getDouble("cramt"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setNarration(rs.getString("narration"));


				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}
	
	//--------------------------------------------------------------------------------------
		public ArrayList<TempTranxBean> getAllTempTranxByAccountId(int ad_account_id) {
			ArrayList<TempTranxBean> bean = new ArrayList<TempTranxBean>();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from ad_trx_temp where ad_account_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_account_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					TempTranxBean bean1 = new TempTranxBean();

					bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
					bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
					bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));
					bean1.setAd_trx_temp_id(rs.getInt("ad_trx_temp_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));
					bean1.setTrx_date(rs.getDate("trx_date"));
					bean1.setNarration(rs.getString("narration"));


					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("TempTranxDao:-error in try Block");
				e.printStackTrace();

			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return bean;
		}
	//----------------------------------------------------------------------------------------------
	public void updateTempTranx(TempTranxBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_trx_temp" +
					"SET updated=?, updatedby=?,isactive=?, ad_voucher_id=?," +
					"ad_account_id=?, trx_date=?, dramt=?,cramt=?, narration=?," +
					"ad_member_id=? WHERE ad_trx_temp_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, true);
			ps.setInt(4, bean.getVoucher().getAd_voucher_id());
			ps.setInt(5, bean.getLedger().getAd_account_id());
			ps.setTimestamp(6, new java.sql.Timestamp(bean.getTrx_date().getTime()));
			ps.setDouble(7, bean.getDramt());
			ps.setDouble(8, bean.getCramt());
			ps.setString(9, bean.getNarration());
			ps.setInt(10, bean.getMember().getAd_member_id());
			ps.setInt(11, bean.getAd_trx_temp_id());

			//System.out.print(ps);
			i=ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}

	//---------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------
	public int CountTempTranx(){

		PreparedStatement ps =null;
		ResultSet rs=null;
		int cnt=0;
		String query = "select count(*) as cnt  from ad_trx_temp";
		try {
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();
			while (rs.next()) {
				cnt = rs.getInt("cnt");

			}



		}catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return cnt;
	}

	//---------------------------------------------------------------------------------------------

	public int getDrTempTranx(int ad_voucher_id){

		PreparedStatement ps =null;
		ResultSet rs=null;
		int dramt=0;
		String query = "select sum(dramt) as dramt  from ad_trx_temp where ad_voucher_id=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				dramt = rs.getInt("dramt");

			}

		}catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return dramt;
	}

	//---------------------------------------------------------------------------------------------

	public int getCrTempTranx(int ad_voucher_id){

		PreparedStatement ps =null;
		ResultSet rs=null;
		int cramt=0;
		String query = "select sum(cramt) as cramt  from ad_trx_temp where ad_voucher_id=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				cramt = rs.getInt("cramt");

			}



		}catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return cramt;
	}

	//---------------------------------------------------------------------------------------------
	public int getTempTranxid(){

		PreparedStatement ps =null;
		ResultSet rs=null;
		int ad_voucher_id=0;
		String query = "select ad_voucher_id  from ad_trx_temp group by ad_voucher_id";
		try {
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();
			while (rs.next()) {
				ad_voucher_id = rs.getInt("ad_voucher_id");

			}



		}catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return ad_voucher_id;
	}

	//---------------------------------------------------------------------------------------------

	public void updateTempTranxStatus(TempTranxBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_trx_temp" +
					"SET isactive=? WHERE ad_trx_temp_id=?";
			ps = con.prepareStatement(query);

			ps.setBoolean(1, bean.isIsactive());
			ps.setInt(2, bean.getAd_trx_temp_id());

			//System.out.print(ps);
			i=ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}

	//----------------------------------------------------------------------------------------------
	public int deleteTempTranx(TempTranxBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_trx_temp WHERE ad_trx_temp_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_trx_temp_id());
		System.out.println(ps);
			i=ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return i;
	}
	//-----------------
	public void deleteTempTranxByvNo(TempTranxBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_trx_temp WHERE ad_voucher_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_voucher_id());
			ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}

	//-----------------
		public void deleteTempTranxByvNo(int ad_voucher_no){
			int i=0;
			PreparedStatement ps =null;
			try{
				String query="DELETE FROM ad_trx_temp WHERE ad_voucher_id=?";
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_voucher_no);
				ps.executeUpdate();

			}catch (Exception e) {
				System.out.print("TempTranxDao:-error in try Block");
				e.printStackTrace();

			}finally {

				DBConnection.close(ps);
				DBConnection.close(con);
			}

		}


	//--------------------------------delete all--------------------------------------------------------------
	public int deleteAllTempTranx(){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_trx_temp";
			ps = con.prepareStatement(query);

			i=ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("TempTranxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
return i;
	}

}
