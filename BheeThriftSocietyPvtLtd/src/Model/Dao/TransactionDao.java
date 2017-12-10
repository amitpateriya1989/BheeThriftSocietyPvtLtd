package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.Bean.JournalLedgerBean;
import Model.Bean.TransactionBean;
import Model.Bean.VoucherTrxDetailBean;

public class TransactionDao {
	private Connection con = null;

	public TransactionDao() {
		con = DBConnection.getConnection();
	}

	public int addTransaction(TransactionBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {

			String query = "INSERT INTO ad_trx(" +
					"created, createdby, updated, updatedby, isactive, ad_voucher_id," +
					"ad_account_id, trx_date, dramt, cramt, narration, ad_member_id)" +
					"VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)" ;

			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setInt(6, bean.getAd_voucher_id());
			ps.setInt(7, bean.getAd_account_id());
			ps.setTimestamp(8, new java.sql.Timestamp(bean.getTrx_date().getTime()));
			ps.setDouble(9, bean.getDramt());
			ps.setDouble(10, bean.getCramt());
			ps.setString(11, bean.getNarration());
			ps.setInt(12, bean.getAd_member_id());
            System.out.println(ps);
			record=ps.executeUpdate();

		} catch (Exception e) {
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return record;
	}
	//-----------------------------------------------------------------------------------------
	public TransactionBean getTransactionById(TransactionBean bean) {
		TransactionBean bean1 = new TransactionBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_trx where ad_trx_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_trx_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));
				bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
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
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}


	//-----------------------------------------------------------------------------------------
	public ArrayList<TransactionBean> getTransactionByType(int ad_voucher_id) {
		ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_trx where ad_voucher_id=? order by  ad_trx_id asc ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			rs = ps.executeQuery();
			while (rs.next()) {

				TransactionBean bean1 = new TransactionBean();
				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setMember(new MemberRegistrationDao().getMemberName(rs.getInt("ad_member_id")));
				bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
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
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}

	//------------------------------------------------------------------------------------------	
	//-----------------------------------------------------------------------------------------
	public ArrayList<TransactionBean> getTransactionByVoucherId(int ad_voucher_id, int ad_account_id) {
		ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_trx where ad_voucher_id=? and ad_account_id!=? order by  ad_trx_id asc ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			ps.setInt(2, ad_account_id);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				TransactionBean bean1 = new TransactionBean();
				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setMember(new MemberRegistrationDao().getMemberName(rs.getInt("ad_member_id")));
				bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
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
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}

	//------------------------------------------------------------------------------------------	
	public TransactionBean getTransactionById(int ad_trx_id) {
		TransactionBean bean1 = new TransactionBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_trx where ad_trx_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_trx_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));
				bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
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
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}
	//------------------------
	public TransactionBean getTransactionById1(int ad_trx_id) {
		TransactionBean bean1 = new TransactionBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select ad_account_id,ad_voucher_id,ad_member_id,ad_trx_id,dramt,cramt,trx_date,narration,ad_employee_id from ad_trx where ad_trx_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_trx_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_account_id(rs.getInt("ad_account_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));	
				bean1.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
				bean1.setDramt(rs.getDouble("dramt"));
				bean1.setCramt(rs.getDouble("cramt"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setNarration(rs.getString("narration"));


			}
		}catch (Exception e) {
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}

	//-----------------------------------------------------------------------------------------------------------------
	public double getTotalShareOpeningByMemberID(int ad_member_id,Date date) {
		double result=0;
		PreparedStatement ps =null;
		ResultSet rs=null;

		String query = " select  COALESCE (sum(share_amt),0) as opening " +
				" from ad_member_share  where ad_member_id=?  and date_of_allocation<?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			ps.setDate(2, new java.sql.Date(date.getTime()));
			rs = ps.executeQuery();
			while (rs.next()) {

				result=rs.getDouble("opening");

			}
		}catch (Exception e) {
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return result;
	}
	//----------------------------------------------------------------------------------------------------------------------
	public double getTotalCrbyMemAndAcID(int ad_member_id , int ad_account_id ) {
		double result=0;
		PreparedStatement ps =null;
		ResultSet rs=null;

		String query = "select sum(cramt) as cramt  from ad_trx where ad_member_id=? and ad_account_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			ps.setInt(2, ad_account_id);
			rs = ps.executeQuery();
			while (rs.next()) {

				result=rs.getDouble("cramt");

			}
		}catch (Exception e) {
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return result;
	}
	//------------------------
	public double getTotalDrbyMemAndAcID(int ad_member_id , int ad_account_id ) {
		double result=0;
		PreparedStatement ps =null;
		ResultSet rs=null;

		String query = "select sum(dramt) as dramt  from ad_trx where ad_member_id=? and ad_account_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			ps.setInt(2, ad_account_id);
			rs = ps.executeQuery();
			while (rs.next()) {

				result=rs.getDouble("dramt");

			}
		}catch (Exception e) {
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return result;
	}
	//--------------------------------------------------------------------------------------
	public ArrayList<TransactionBean> getAllTransaction() {
		ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_trx ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				TransactionBean bean1 = new TransactionBean();
				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));
				bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
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
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}


	//--------------------------------------------------------------------------------------
	public ArrayList<TransactionBean> getAllTransactionByDate(Date from,Date to) {
		ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_trx where trx_date between ? and ?";
		try {
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(from.getTime()));
			ps.setDate(2, new java.sql.Date(to.getTime()));
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				TransactionBean bean1 = new TransactionBean();
				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setMember(new MemberRegistrationDao().getMemberName(rs.getInt("ad_member_id")));
				bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
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
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return bean;
	}
	//--------------------------------------------------------------------------------------
	public ArrayList<TransactionBean> getAllBankTransactionByDate(Date from,Date to) {
		ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select trx.* from ad_trx trx  " +
				"LEFT JOIN ad_account account ON account.ad_account_id = trx.ad_account_id " +
				"AND account.ac_for='Bank OD' " +
				"LEFT JOIN ad_voucher v ON v.ad_voucher_id = trx.ad_voucher_id " +
				"Where trx.trx_date between ? and ?   AND v.vtype='Cheque'";
		try {
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(from.getTime()));
			ps.setDate(2, new java.sql.Date(to.getTime()));
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				TransactionBean bean1 = new TransactionBean();
				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));
				bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
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
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return bean;
	}

	//--------------------------------------------------------------------------------------
	public TransactionBean getAllBankTransactionByfromDate(Date from) {
		TransactionBean bean = new TransactionBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select sum(dramt) as dramt , sum(cramt) as cramt from ad_trx trx  " +
				"LEFT JOIN ad_account account ON account.ad_account_id = trx.ad_account_id " +
				"AND account.ac_for='Bank OD' " +
				"LEFT JOIN ad_voucher v ON v.ad_voucher_id = trx.ad_voucher_id " +
				"Where trx.trx_date< ?    AND v.vtype='Cheque'";
		try {
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(from.getTime()));

			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {

				/*bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
							bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
							bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));
							bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
							bean1.setCreated(rs.getDate("created"));
							bean1.setCreatedby(rs.getInt("createdby"));
							bean1.setUpdated(rs.getDate("updated"));
							bean1.setUpdatedby(rs.getInt("updatedby"));
							bean1.setIsactive(rs.getBoolean("isactive"));*/
				bean.setDramt(rs.getDouble("dramt"));
				bean.setCramt(rs.getDouble("cramt"));
				/*bean1.setTrx_date(rs.getDate("trx_date"));
							bean1.setNarration(rs.getString("narration"));*/




			}
		}catch (Exception e) {
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return bean;
	}
	//--------------------------------------------------------------------------------------
	public ArrayList<TransactionBean> getAllCashTransactionByDate(Date from,Date to) {
		ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select trx.* from ad_trx trx  " +
				"LEFT JOIN ad_account account ON account.ad_account_id = trx.ad_account_id " +
				"AND account.ac_for='Cash A/C' " +
				"LEFT JOIN ad_voucher v ON v.ad_voucher_id = trx.ad_voucher_id " +
				"Where trx.trx_date between ? and ?   AND v.vtype='Cash'" ;
		try {
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(from.getTime()));
			ps.setDate(2, new java.sql.Date(to.getTime()));
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				TransactionBean bean1 = new TransactionBean();
				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));
				bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
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
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return bean;
	}		





	//----------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------
	public ArrayList<TransactionBean> getAllMemberTransactionByMemIdAccId(int ad_member_id , int ad_account_id) {
		ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_trx where ad_account_id=?  and ad_member_id=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_account_id );
			ps.setInt(2, ad_member_id);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				TransactionBean bean1 = new TransactionBean();
				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));
				bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
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
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return bean;
	}		





	//--------------------------------------------------------------------------------------
	public ArrayList<TransactionBean> getAllMemberTransactionByMemId(int ad_member_id) {
		ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_trx where  ad_member_id=? order by trx_date";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				TransactionBean bean1 = new TransactionBean();
				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));
				bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
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
			System.out.print("TransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return bean;
	}		

	//----------------------------------------------------------------------------------------------




	public TransactionBean getAllLedgerTransactionBefourFromDate(int ad_ledger_id,Date strDate, int ad_member_id) {
		TransactionBean bean = new TransactionBean();
		PreparedStatement ps=null;
		ResultSet rs = null;
		try{
		if(ad_member_id==0){
			String query = "select sum(cramt) as cramt,sum(dramt) as dramt from ad_trx where ad_account_id=?  and trx_date <?  ";
			
				ps = con.prepareStatement(query);
				ps.setInt(1,ad_ledger_id);
				ps.setDate(2,new java.sql.Date(strDate.getTime()));

			
		}else{

			String query = "select sum(cramt) as cramt,sum(dramt) as dramt from ad_trx where ad_account_id=?  and ad_member_id=? and trx_date <?   ";
			
				ps = con.prepareStatement(query);
				ps.setInt(1,ad_ledger_id);
				ps.setInt(2, ad_member_id);
				ps.setDate(3,new java.sql.Date(strDate.getTime()));

				System.out.println("ps"+ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					bean.setDramt(rs.getDouble("dramt"));
					bean.setCramt(rs.getDouble("cramt"));

				}
				
		}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {

				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
			}
		
			return bean;
		}			



		//----------------------------------------------------------------------------------------------				

		public TransactionBean getAllMemberLedgerOpening(Date strDate, int ad_member_id) {
			TransactionBean bean = new TransactionBean();
			PreparedStatement ps=null;
			ResultSet rs = null;
			if(ad_member_id!=0){


				String query = "select sum(cramt) as cramt,sum(dramt) as dramt from ad_trx where ad_member_id=? AND trx_date <?   ";
				try{
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_member_id);
					ps.setDate(2,new java.sql.Date(strDate.getTime()));


				}catch(Exception e){
					e.printStackTrace();
				}

			}
			try {

				System.out.println("ps"+ps);
				rs = ps.executeQuery();
				while (rs.next()) {



					bean.setDramt(rs.getDouble("dramt"));
					bean.setCramt(rs.getDouble("cramt"));




				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {

				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
			}
			return bean;
		}			


		//----------------------------------------------------------------------------------------------




		public ArrayList<TransactionBean> getAllLedgerTransactionByFromToDate(int ad_ledger_id,Date strDate,Date currentDate, int ad_member_id) {
			ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
			PreparedStatement ps=null;
			ResultSet rs = null;
			if(ad_member_id==0){
				String query = "select * from ad_trx at" +
						" left join ad_voucher av on av.ad_voucher_id= at.ad_voucher_id " +
						"where at.ad_account_id=?    and av.status='Approved' and at.trx_date BETWEEN ? and ? order by at.ad_trx_id, at.trx_Date";

				try{
					ps = con.prepareStatement(query);
					ps.setInt(1,ad_ledger_id);
					ps.setDate(2,new java.sql.Date(strDate.getTime()));
					ps.setDate(3,new java.sql.Date(currentDate.getTime()));
				}catch(Exception e){
					e.printStackTrace();
				}
			}else{

				String query = "select * from ad_trx at" +
						"  left join ad_voucher av on av.ad_voucher_id= at.ad_voucher_id " +
						"where at.ad_account_id=?  and at.ad_member_id=?  and av.status='Approved' and at.trx_date BETWEEN ? and ? order by at.ad_trx_id,at.trx_Date ";
				try{
					ps = con.prepareStatement(query);
					ps.setInt(1,ad_ledger_id);
					ps.setInt(2, ad_member_id);
					ps.setDate(3,new java.sql.Date(strDate.getTime()));
					ps.setDate(4,new java.sql.Date(currentDate.getTime()));

				}catch(Exception e){
					e.printStackTrace();
				}

			}
			try {

				System.out.println(ps);

				rs = ps.executeQuery();
				while (rs.next()) {

					TransactionBean bean1 = new TransactionBean();
					bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));

					if(rs.getInt("ad_member_id")==0){
						//bean1.setMember("-");
					}else{
						bean1.setMember(new MemberRegistrationDao().getMemberName(rs.getInt("ad_member_id")));
					}

					bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));
					bean1.setTrx_date(rs.getDate("trx_date"));
					bean1.setNarration(rs.getString("narration"));
					bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));

					bean.add(bean1);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {

				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
			}
			return bean;
		}			
		//------------------------------------------------------------------------------------------------------------------------------------


		public ArrayList<TransactionBean> getAllMemberLedgerTransactionByFromToDate(Date strDate, Date currentDate, int ad_member_id) {
			ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
			PreparedStatement ps=null;
			ResultSet rs = null;
			if(ad_member_id!=0){


				String query = "select * from ad_trx at" +
						"  left join ad_voucher av on av.ad_voucher_id= at.ad_voucher_id " +
						"where at.ad_member_id=?  and av.status='Approved' and at.trx_date BETWEEN ? and ? order by at.ad_trx_id,at.trx_Date ";
				try{
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_member_id);
					ps.setDate(2,new java.sql.Date(strDate.getTime()));
					ps.setDate(3,new java.sql.Date(currentDate.getTime()));

				}catch(Exception e){
					e.printStackTrace();
				}

			}
			try {

				System.out.println(ps);

				rs = ps.executeQuery();
				while (rs.next()) {

					TransactionBean bean1 = new TransactionBean();
					bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
					bean1.setMember(new MemberRegistrationDao().getMemberName(rs.getInt("ad_member_id")));
					bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));
					bean1.setTrx_date(rs.getDate("trx_date"));
					bean1.setNarration(rs.getString("narration"));
					bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));

					bean.add(bean1);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {

				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
			}
			return bean;
		}			


		//----------------------------------------------------------------------------------------------


		public ArrayList<TransactionBean> getLedgerOpeningBal(int ad_ledger_id,Date strDate) {
			ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
			PreparedStatement ps=null;
			ResultSet rs = null;


			String query = "select sum(at.dramt) as dramt,sum(at.cramt) as cramt from ad_trx at " +
					"left join ad_voucher av on av.ad_voucher_id= at.ad_voucher_id where at.ad_account_id=?   and at.trx_date <? and av.status='Approved'  ";
			try{

				ps = con.prepareStatement(query);
				ps.setInt(1,ad_ledger_id);							
				ps.setDate(2,new java.sql.Date(strDate.getTime()));							

			}catch(Exception e){
				e.printStackTrace();
			}


			try {

				System.out.println(ps);
				rs = ps.executeQuery();
				while (rs.next()) {

					TransactionBean bean1 = new TransactionBean();
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));

					bean.add(bean1);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {

				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
			}
			return bean;
		}			

		public TransactionBean getLedgerOpeningBal_2(int ad_ledger_id,Date strDate) {
			TransactionBean bean1 = null;
			PreparedStatement ps=null;
			ResultSet rs = null;


			String query = "select sum(at.dramt) as dramt,sum(at.cramt) as cramt from ad_trx at " +
					"left join ad_voucher av on av.ad_voucher_id= at.ad_voucher_id where at.ad_account_id=?   and at.trx_date <? and av.status='Approved'  ";
			try{

				ps = con.prepareStatement(query);
				ps.setInt(1,ad_ledger_id);							
				ps.setDate(2,new java.sql.Date(strDate.getTime()));							

			}catch(Exception e){
				e.printStackTrace();
			}


			try {

				System.out.println(ps);
				rs = ps.executeQuery();
				while (rs.next()) {

					bean1 = new TransactionBean();
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));

					

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {

				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
			}
			return bean1;
		}			

		//----------------------------------------------------------------------------------------------
		public ArrayList<TransactionBean> getLedgerOpeningBalBySubGroup(int ad_ac_subgroup_id,Date strDate) {
			ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
			PreparedStatement ps=null;
			ResultSet rs = null;


			String query = "select sum(at.dramt) as dramt,sum(at.cramt) as cramt   from ad_trx at " +
					" left join ad_voucher av on av.ad_voucher_id= at.ad_voucher_id " +
					" left join ad_account ac on ac.ad_account_id=at.ad_account_id " +
					" left join ad_ac_subgroup  acsg on acsg.ad_ac_subgroup_id=ac.ad_ac_subgroup_id " +
					" where " +
					" ac.ad_ac_subgroup_id=?   " +
					"and at.trx_date < ? and av.status='Approved'  ";
			try{
				ps = con.prepareStatement(query);
				ps.setInt(1,ad_ac_subgroup_id);							
				ps.setDate(2,new java.sql.Date(strDate.getTime()));							

			}catch(Exception e){
				e.printStackTrace();
			}


			try {

				System.out.println(ps);
				rs = ps.executeQuery();
				while (rs.next()) {

					TransactionBean bean1 = new TransactionBean();
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));

					bean.add(bean1);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {

				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
			}
			return bean;
		}			

		//----------------------------------------------------------------------------------------------


		public ArrayList<TransactionBean> getLedgerOpeningBalByGroup(int ad_ac_group_id,Date strDate) {
			ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
			PreparedStatement ps=null;
			ResultSet rs = null;


			String query = "select sum(at.dramt) as dramt,sum(at.cramt) as cramt   from ad_trx at " +
					" left join ad_voucher av on av.ad_voucher_id= at.ad_voucher_id " +
					" left join ad_account ac on ac.ad_account_id=at.ad_account_id " +
					" left join ad_ac_group  acg on acg.ad_ac_group_id=ac.ad_ac_group_id " +
					" where " +
					" ac.ad_ac_group_id=?   " +
					"and at.trx_date < ? and av.status='Approved'  ";
			try{
				ps = con.prepareStatement(query);
				ps.setInt(1,ad_ac_group_id);							
				ps.setDate(2,new java.sql.Date(strDate.getTime()));							

			}catch(Exception e){
				e.printStackTrace();
			}


			try {

				System.out.println(ps);
				rs = ps.executeQuery();
				while (rs.next()) {

					TransactionBean bean1 = new TransactionBean();
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));

					bean.add(bean1);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {

				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
			}
			return bean;
		}			



		//----------------------------------------------------------------------------------------------			



		public ArrayList<TransactionBean> getLedgerBalBetweendates(int ad_ledger_id,Date strDate,Date endDate) {
			ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
			PreparedStatement ps=null;
			ResultSet rs = null;


			String query = "select sum(at.dramt) as dramt, sum(at.cramt) as cramt from ad_trx at left join ad_voucher av on av.ad_voucher_id= at.ad_voucher_id where at.ad_account_id=?  and av.status='Approved' and at.trx_date BETWEEN ? and ?   ";
			try{
				ps = con.prepareStatement(query);
				ps.setInt(1,ad_ledger_id);

				ps.setDate(2,new java.sql.Date(strDate.getTime()));
				ps.setDate(3,new java.sql.Date(endDate.getTime()));

			}catch(Exception e){
				e.printStackTrace();
			}


			try {

				System.out.println(ps);
				rs = ps.executeQuery();
				while (rs.next()) {

					TransactionBean bean1 = new TransactionBean();
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));

					bean.add(bean1);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {

				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
			}
			return bean;
		}			



		public TransactionBean getLedgerBalBetweendates_2(int ad_ledger_id,Date strDate,Date endDate) {
			TransactionBean bean1 = null;
			PreparedStatement ps=null;
			ResultSet rs = null;


			String query = "select sum(at.dramt) as dramt, sum(at.cramt) as cramt from ad_trx at left join ad_voucher av on av.ad_voucher_id= at.ad_voucher_id where at.ad_account_id=?  and av.status='Approved' and at.trx_date BETWEEN ? and ?   ";
			try{
				ps = con.prepareStatement(query);
				ps.setInt(1,ad_ledger_id);

				ps.setDate(2,new java.sql.Date(strDate.getTime()));
				ps.setDate(3,new java.sql.Date(endDate.getTime()));

			}catch(Exception e){
				e.printStackTrace();
			}


			try {

				System.out.println(ps);
				rs = ps.executeQuery();
				while (rs.next()) {

					 bean1 = new TransactionBean();
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));

					

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {

				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
			}
			return bean1;
		}			




		//----------------------------------------------------------------------------------------------

		public ArrayList<TransactionBean> getLedgerBalByGroupBetweendates(int ad_ac_group_id,Date strDate,Date endDate) {
			ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
			PreparedStatement ps=null;
			ResultSet rs = null;


			String query = "select sum(at.dramt) as dramt,sum(at.cramt) as cramt   from ad_trx at " +
					" left join ad_voucher av on av.ad_voucher_id= at.ad_voucher_id " +
					" left join ad_account ac on ac.ad_account_id=at.ad_account_id " +
					" left join ad_ac_group  acg on acg.ad_ac_group_id=ac.ad_ac_group_id " +
					" where " +
					" ac.ad_ac_group_id=?   " +
					" and av.status='Approved' and at.trx_date BETWEEN ? and ?  ";

			try{
				ps = con.prepareStatement(query);
				ps.setInt(1,ad_ac_group_id);

				ps.setDate(2,new java.sql.Date(strDate.getTime()));
				ps.setDate(3,new java.sql.Date(endDate.getTime()));

			}catch(Exception e){
				e.printStackTrace();
			}


			try {

				System.out.println(ps);
				rs = ps.executeQuery();
				while (rs.next()) {

					TransactionBean bean1 = new TransactionBean();
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));

					bean.add(bean1);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {

				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
			}
			return bean;
		}			



		///-----------------------------------------

		public ArrayList<TransactionBean> getLedgerBalBySubGroupBetweendates(int ad_ac_subgroup_id,Date strDate,Date endDate) {
			ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
			PreparedStatement ps=null;
			ResultSet rs = null;


			String query = "select sum(at.dramt) as dramt,sum(at.cramt) as cramt   from ad_trx at " +
					" left join ad_voucher av on av.ad_voucher_id= at.ad_voucher_id " +
					" left join ad_account ac on ac.ad_account_id=at.ad_account_id " +
					" left join ad_ac_subgroup  acsg on acsg.ad_ac_subgroup_id=ac.ad_ac_subgroup_id " +
					" where " +
					" ac.ad_ac_subgroup_id=?   " +
					" and av.status='Approved' and at.trx_date BETWEEN ? and ?  ";

			try{
				ps = con.prepareStatement(query);
				ps.setInt(1,ad_ac_subgroup_id);

				ps.setDate(2,new java.sql.Date(strDate.getTime()));
				ps.setDate(3,new java.sql.Date(endDate.getTime()));

			}catch(Exception e){
				e.printStackTrace();
			}


			try {

				System.out.println(ps);
				rs = ps.executeQuery();
				while (rs.next()) {

					TransactionBean bean1 = new TransactionBean();
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));

					bean.add(bean1);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {

				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
			}
			return bean;
		}			


		//----------------------------------------------------------------------------------------------


		public ArrayList<TransactionBean> getLedgerBalByTypeBetweendates(int ad_ac_type_id,Date strDate,Date endDate) {
			ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
			PreparedStatement ps=null;
			ResultSet rs = null;


			String query = "select sum(at.dramt) as dramt, sum(at.cramt) as cramt,ac.ad_aacount_id " +
					" from ad_trx at " +
					" left join ad_voucher av on av.ad_voucher_id= at.ad_voucher_id " +
					" left join ad_account ac on ac.ad_account_id=at.ad_account_id" +
					" where   av.status='Approved' and ac.ad_ac_type_id=? " +
					" and at.trx_date BETWEEN ? and ? ";
			try{
				ps = con.prepareStatement(query);
				ps.setInt(1,ad_ac_type_id);

				ps.setDate(2,new java.sql.Date(strDate.getTime()));
				ps.setDate(3,new java.sql.Date(endDate.getTime()));

			}catch(Exception e){
				e.printStackTrace();
			}


			try {

				System.out.println(ps);
				rs = ps.executeQuery();
				while (rs.next()) {

					TransactionBean bean1 = new TransactionBean();
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));

					bean.add(bean1);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {

				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
			}
			return bean;
		}			


		//----------------------------------------------------------------------------------------------
		public void updateTransaction(TransactionBean bean){
			int i=0;
			PreparedStatement ps =null;
			try {

				String query = "UPDATE ad_trx " +
						" SET updated=?, updatedby=?, ad_employee_id=?," +
						" ad_account_id=?,  dramt=?,cramt=?, narration=?," +
						" ad_member_id=? WHERE ad_trx_id=?";
				ps = con.prepareStatement(query);
				ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(2, bean.getUpdatedby());
				ps.setInt(3, bean.getAd_employee_id());

				ps.setInt(4, bean.getAd_account_id());

				ps.setDouble(5, bean.getDramt());
				ps.setDouble(6, bean.getCramt());
				ps.setString(7, bean.getNarration());
				ps.setInt(8, bean.getAd_member_id());
				ps.setInt(9, bean.getAd_trx_id());
				
				//System.out.print(ps);
				i=ps.executeUpdate();

			}catch (Exception e) {
				System.out.print("TransactionDao:-error in try Block");
				e.printStackTrace();

			}finally {

				DBConnection.close(ps);
				DBConnection.close(con);
			}

		}

		//---------------------------------------------------------------------------------------------

		public void updateTransactionStatus(TransactionBean bean){
			int i=0;
			PreparedStatement ps =null;
			try {

				String query = "UPDATE ad_trx" +
						"SET isactive=? WHERE ad_trx_id=?";
				ps = con.prepareStatement(query);

				ps.setBoolean(1, bean.isIsactive());
				ps.setInt(2, bean.getAd_trx_id());

				//System.out.print(ps);
				i=ps.executeUpdate();

			}catch (Exception e) {
				System.out.print("TransactionDao:-error in try Block");
				e.printStackTrace();

			}finally {

				DBConnection.close(ps);
				DBConnection.close(con);
			}

		}

		//----------------------------------------------------------------------------------------------
		public void deleteTransaction(TransactionBean bean){
			int i=0;
			PreparedStatement ps =null;
			try{
				String query="DELETE FROM ad_trx WHERE ad_trx_id=?";
				ps = con.prepareStatement(query);
				ps.setInt(1, bean.getAd_trx_id());
				ps.executeQuery();

			}catch (Exception e) {
				System.out.print("TransactionDao:-error in try Block");
				e.printStackTrace();

			}finally {

				DBConnection.close(ps);
				DBConnection.close(con);
			}

		}
		//--------------
		//----------------------------------------------------------------------------------------------
		public void deleteTransaction(int  ad_trx_id){
			int i=0;
			PreparedStatement ps =null;
			try{
				String query="DELETE FROM ad_trx WHERE ad_trx_id=?";
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_trx_id);
				ps.executeUpdate();

			}catch (Exception e) {
				System.out.print("TransactionDao:-error in try Block");
				e.printStackTrace();

			}finally {

				DBConnection.close(ps);
				DBConnection.close(con);
			}

		}
		//--------------
		public void deleteTransactionbyvoucherid(int ad_voucher_id){
			int i=0;
			PreparedStatement ps =null;
			try{
				String query="DELETE FROM ad_trx  WHERE ad_voucher_id=? ";
				ps = con.prepareStatement(query);
				ps.setInt(1,ad_voucher_id);
				ps.executeUpdate();

			}catch (Exception e) {
				System.out.print("TransactionDao:-error in try Block");
				e.printStackTrace();

			}finally {

				DBConnection.close(ps);
				DBConnection.close(con);
			}

		}

		//======================================

		public void selectIntrestTrxOnDate(int ad_voucher_id,int ad_loan_category_id ,  int ad_type_of_loan_id, TransactionBean bean) {
			int record=0;
			PreparedStatement ps=null;
			try {



				String query = "INSERT INTO ad_trx(created, createdby, updated, updatedby, isactive, " +
						"            ad_voucher_id, ad_account_id, trx_date, dramt, cramt, narration, " +
						"            ad_member_id,  ad_employee_id)" +
						"   SELECT ?,?,?,?,?  ,? ,?  ,?,?,alt.interest_amt, ?, alt.ad_member_id, alt.ad_emp_id " +
						" FROM ad_loan_trx alt " +
						" left join loan_trx lt on lt.loan_trx_id=alt.loan_trx_id" +
						" where alt.ad_voucher_id=? and alt.interest_amt!=0.00 and lt.loan_cataegory=? and lt.loan_type=?" ;

				ps = con.prepareStatement(query);
				ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(2, bean.getCreatedby());
				ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(4, bean.getUpdatedby());
				ps.setBoolean(5, true);
				ps.setInt(6, bean.getAd_voucher_id());
				ps.setInt(7, bean.getAd_account_id());
				ps.setTimestamp(8, new java.sql.Timestamp(bean.getTrx_date().getTime()));
				ps.setDouble(9, 0.0);			
				ps.setString(10, bean.getNarration());
				ps.setInt(11, ad_voucher_id);
				ps.setInt(12, ad_loan_category_id);
				ps.setInt(13, ad_type_of_loan_id);

				record=ps.executeUpdate();

			} catch (Exception e) {
				System.out.print("TransactionDao:-error in try Block");
				e.printStackTrace();

			}finally {

				DBConnection.close(ps);
				DBConnection.close(con);
			}

		}


		//CalculateThriftinterest----------------------------------------------------------------------------------------------

		public void CalculateDividentinterest(java.sql.Date fdate, java.sql.Date tdate, int ad_account_id, double intrate, int ad_voucher_id, int createdby,int updatedby){				
			PreparedStatement ps=null;
			ResultSet rs=null;
			try{
				String query="select calculatedivident(?, ?, ?, ?, ?, ?, ?)";
				ps=con.prepareStatement(query);
				ps.setDate(1, fdate);
				ps.setDate(2, tdate);
				ps.setInt(3, ad_account_id);
				ps.setDouble(4, intrate);
				ps.setInt(5, ad_voucher_id);
				ps.setInt(6, createdby);
				ps.setInt(7, updatedby);
				rs=ps.executeQuery();
				if(rs.next()){

				}

			}catch (Exception e) {
				System.out.print("Transaction:-error in try Block");
				e.printStackTrace();
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}


		}
		//----------------------------------------------------------------------------------------------
		//----------------------------------------------------------------------------------------------

		/*public void CalculateThriftinterest(java.sql.Date fdate, java.sql.Date tdate, int ad_account_id, double intrate, int ad_voucher_id, int createdby,int updatedby){				
			PreparedStatement ps=null;
			ResultSet rs=null;
			try{
				String query="select calculatethrift(?, ?, ?, ?, ?, ?, ?)";
				ps=con.prepareStatement(query);
				ps.setDate(1, fdate);
				ps.setDate(2, tdate);
				ps.setInt(3, ad_account_id);
				ps.setDouble(4, intrate);
				ps.setInt(5, ad_voucher_id);
				ps.setInt(6, createdby);
				ps.setInt(7, updatedby);
				rs=ps.executeQuery();
				if(rs.next()){

				}

			}catch (Exception e) {
				System.out.print("Transaction:-error in try Block");
				e.printStackTrace();
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}


		}*/
		//----------------------------------------------------------------------------------------------

		public ArrayList<VoucherTrxDetailBean> getAllVoucherTrxDetailByDate(Date from,Date to) {
			ArrayList<VoucherTrxDetailBean> bean = new ArrayList<VoucherTrxDetailBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "SELECT trx_date, ad_member_id, member_name, ad_society_no, dramt, cramt," +
					" narration, trx_status, trx_remark, ad_employee_id, emp_name," +
					" vno, voucher_status, vtype, vfrom, vamt, description, instrument_from," +
					" instrument_no, instrument_date, voucher_type, voucher_trx_no," +
					" ad_trx_id, ad_account_id, ac_name, ac_for, ac_no, ad_voucher_id," +
					" ad_ac_type_id, ac_type_name FROM voucher_trx_detail where trx_date between ? AND ?";
			try {
				ps = con.prepareStatement(query);
				ps.setDate(1, new java.sql.Date(from.getTime()));
				ps.setDate(2, new java.sql.Date(to.getTime()));
				rs = ps.executeQuery();
				while (rs.next()) {
					VoucherTrxDetailBean bean1 = new VoucherTrxDetailBean();
					bean1.setTrx_date(rs.getDate("trx_date"));
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setMember_name(rs.getString("member_name"));
					bean1.setAd_society_no(rs.getInt("ad_society_no"));
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));
					bean1.setNarration(rs.getString("narration"));
					bean1.setTrx_status(rs.getString("trx_status"));
					bean1.setAd_employee_id(rs.getInt("ad_employee_id"));
					bean1.setEmp_name(rs.getString("emp_name"));
					bean1.setVno(rs.getString("vno"));
					bean1.setVoucher_status(rs.getString("voucher_status"));
					bean1.setVtype(rs.getString("vtype"));
					bean1.setVfrom(rs.getString("vfrom"));
					bean1.setVamt(rs.getDouble("vamt"));
					bean1.setDescription(rs.getString("description"));
					bean1.setInstrument_from(rs.getString("instrument_from"));
					bean1.setInstrument_date(rs.getDate("instrument_date"));
					bean1.setInstrument_from(rs.getString("instrument_from"));
					bean1.setInstrument_no(rs.getString("instrument_no"));
					bean1.setVoucher_type(rs.getString("voucher_type"));
					bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
					bean1.setVoucher_trx_no(rs.getInt("voucher_trx_no"));
					bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
					bean1.setAd_account_id(rs.getInt("ad_account_id"));
					bean1.setAc_name(rs.getString("ac_name"));
					bean1.setAc_for(rs.getString("ac_for"));
					bean1.setAc_no(rs.getString("ac_no"));
					bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
					bean1.setAc_type_name(rs.getString("ac_type_name"));
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("VoucherDao:-error in try Block");
				e.printStackTrace();

			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return bean;
		}	


		public ArrayList<VoucherTrxDetailBean> getAllVoucherTrxDetailByCash(Date from,Date to) {
			ArrayList<VoucherTrxDetailBean> bean = new ArrayList<VoucherTrxDetailBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "SELECT trx_date, ad_member_id, member_name, ad_society_no, dramt, cramt," +
					" narration, trx_status, trx_remark, ad_employee_id, emp_name," +
					" vno, voucher_status, vtype, vfrom, vamt, description, instrument_from," +
					" instrument_no, instrument_date, voucher_type, voucher_trx_no," +
					" ad_trx_id, ad_account_id, ac_name, ac_for, ac_no, ad_voucher_id," +
					" ad_ac_type_id, ac_type_name FROM voucher_trx_detail where trx_date between ? AND ? AND vtype='Cash' ";
			try {
				ps = con.prepareStatement(query);
				ps.setDate(1, new java.sql.Date(from.getTime()));
				ps.setDate(2, new java.sql.Date(to.getTime()));
				rs = ps.executeQuery();
				while (rs.next()) {
					VoucherTrxDetailBean bean1 = new VoucherTrxDetailBean();
					bean1.setTrx_date(rs.getDate("trx_date"));
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setMember_name(rs.getString("member_name"));
					bean1.setAd_society_no(rs.getInt("ad_society_no"));
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));
					bean1.setNarration(rs.getString("narration"));
					bean1.setTrx_status(rs.getString("trx_status"));
					bean1.setAd_employee_id(rs.getInt("ad_employee_id"));
					bean1.setEmp_name(rs.getString("emp_name"));
					bean1.setVno(rs.getString("vno"));
					bean1.setVoucher_status(rs.getString("voucher_status"));
					bean1.setVtype(rs.getString("vtype"));
					bean1.setVfrom(rs.getString("vfrom"));
					bean1.setVamt(rs.getDouble("vamt"));
					bean1.setDescription(rs.getString("description"));
					bean1.setInstrument_from(rs.getString("instrument_from"));
					bean1.setInstrument_date(rs.getDate("instrument_date"));
					bean1.setInstrument_from(rs.getString("instrument_from"));
					bean1.setInstrument_no(rs.getString("instrument_no"));
					bean1.setVoucher_type(rs.getString("voucher_type"));
					bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
					bean1.setVoucher_trx_no(rs.getInt("voucher_trx_no"));
					bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
					bean1.setAd_account_id(rs.getInt("ad_account_id"));
					bean1.setAc_name(rs.getString("ac_name"));
					bean1.setAc_for(rs.getString("ac_for"));
					bean1.setAc_no(rs.getString("ac_no"));
					bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
					bean1.setAc_type_name(rs.getString("ac_type_name"));


					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("VoucherDao:-error in try Block");
				e.printStackTrace();

			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return bean;
		}	
		//--------------------------------------------------------------------------------------------------
		public ArrayList<VoucherTrxDetailBean> getAllVoucherTrxDetailByAccountId(Date from,Date to, int ad_account_id,int ad_member_id,String status) {
			ArrayList<VoucherTrxDetailBean> bean = new ArrayList<VoucherTrxDetailBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = null;
			if(ad_member_id!=0){
				query="SELECT trx_date, ad_member_id, member_name, ad_society_no, dramt, cramt," +
						" narration, trx_status, trx_remark, ad_employee_id, emp_name," +
						" vno, voucher_status, vtype, vfrom, vamt, description, instrument_from," +
						" instrument_no, instrument_date, voucher_type, voucher_trx_no," +
						" ad_trx_id, ad_account_id, ac_name, ac_for, ac_no, ad_voucher_id," +
						" ad_ac_type_id, ac_type_name,branch,branch_code FROM voucher_trx_detail where trx_date between ? AND ?  and ad_account_id=? AND ad_member_id="+ad_member_id+
						" and voucher_status=? order by  ad_trx_id asc ";
			}else{

				query="SELECT trx_date, ad_member_id, member_name, ad_society_no, dramt, cramt," +
						" narration, trx_status, trx_remark, ad_employee_id, emp_name," +
						" vno, voucher_status, vtype, vfrom, vamt, description, instrument_from," +
						" instrument_no, instrument_date, voucher_type, voucher_trx_no," +
						" ad_trx_id, ad_account_id, ac_name, ac_for, ac_no, ad_voucher_id," +
						" ad_ac_type_id, ac_type_name,branch,branch_code FROM voucher_trx_detail " +
						" where trx_date between ? AND ?  and ad_account_id=? and voucher_status=?" +
						" order by  ad_trx_id asc ";
			}
			try {
				ps = con.prepareStatement(query);
				ps.setDate(1,new java.sql.Date(from.getTime()));
				ps.setDate(2,new java.sql.Date(to.getTime()));
				ps.setInt(3, ad_account_id);
				ps.setString(4,status);
				System.out.println(ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					VoucherTrxDetailBean bean1 = new VoucherTrxDetailBean();
					bean1.setTrx_date(rs.getDate("trx_date"));
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setMember_name(rs.getString("member_name"));
					bean1.setAd_society_no(rs.getInt("ad_society_no"));
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));
					bean1.setNarration(rs.getString("narration"));
					bean1.setTrx_status(rs.getString("trx_status"));
					bean1.setAd_employee_id(rs.getInt("ad_employee_id"));
					bean1.setEmp_name(rs.getString("emp_name"));
					bean1.setVno(rs.getString("vno"));
					bean1.setVoucher_status(rs.getString("voucher_status"));
					bean1.setVtype(rs.getString("vtype"));
					bean1.setVfrom(rs.getString("vfrom"));
					bean1.setVamt(rs.getDouble("vamt"));
					bean1.setDescription(rs.getString("description"));
					bean1.setInstrument_from(rs.getString("instrument_from"));
					bean1.setInstrument_date(rs.getDate("instrument_date"));
					bean1.setInstrument_from(rs.getString("instrument_from"));
					bean1.setInstrument_no(rs.getString("instrument_no"));
					bean1.setVoucher_type(rs.getString("voucher_type"));
					bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
					bean1.setVoucher_trx_no(rs.getInt("voucher_trx_no"));
					bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
					bean1.setAd_account_id(rs.getInt("ad_account_id"));
					bean1.setAc_name(rs.getString("ac_name"));
					bean1.setAc_for(rs.getString("ac_for"));
					bean1.setAc_no(rs.getString("ac_no"));
					bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
					bean1.setAc_type_name(rs.getString("ac_type_name"));
					bean1.setBranch(rs.getString("branch"));
					bean1.setBranch_code(rs.getInt("branch_code"));

					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("VoucherDao:-error in try Block");
				e.printStackTrace();

			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return bean;
		}	

		//-----------------------------------------------------------------------------------------------------
		//--------------------------------------------------------------------------------------------------
		public ArrayList<VoucherTrxDetailBean> getAllVoucherOpeningDetailByAccountId(Date from, int ad_account_id,int ad_member_id) {
			ArrayList<VoucherTrxDetailBean> bean = new ArrayList<VoucherTrxDetailBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query=null;
			if(ad_member_id!=0){
				query = "SELECT sum( dramt) as dramt,sum( cramt) as cramt, ad_account_id, " +
						"ac_name,ac_no, ac_type_name, vno " +
						" FROM voucher_trx_detail where trx_date < ? And ad_account_id=? AND ad_member_id="+ad_member_id +
						" AND voucher_status=?  group by ad_account_id, ac_name,ac_no, ac_type_name,vno ";
			}else{
				query = "SELECT sum( dramt) as dramt,sum( cramt) as cramt, ad_account_id, " +
						"ac_name,ac_no, ac_type_name, vno " +
						" FROM voucher_trx_detail where trx_date < ? And ad_account_id=? AND voucher_status=? " +
						" group by ad_account_id, ac_name,ac_no, ac_type_name,vno ";
			}
			try {
				ps = con.prepareStatement(query);
				ps.setDate(1,new java.sql.Date(from.getTime()));
				ps.setInt(2, ad_account_id);
				ps.setString(3, "Approved");
				System.out.println(ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					VoucherTrxDetailBean bean1 = new VoucherTrxDetailBean();
					bean1.setTrx_date(from);
					bean1.setVoucher_type("Opening Balance");
					bean1.setVno(rs.getString("vno"));
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));
					bean1.setAd_account_id(rs.getInt("ad_account_id"));
					bean1.setAc_name(rs.getString("ac_name"));
					bean1.setAc_no(rs.getString("ac_no"));
					bean1.setAc_type_name(rs.getString("ac_type_name"));
					bean1.setNarration("Opening");


					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("VoucherDao:-error in try Block");
				e.printStackTrace();

			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return bean;
		}	

		//-----------------------------------------------------------------------------------------------------
		public ArrayList<JournalLedgerBean> getAllJournalEnteries(Date from,Date to) {
			ArrayList<JournalLedgerBean> bean = new ArrayList<JournalLedgerBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "SELECT ad_trx_id, trx_date, ad_voucher_id, vno, voucher_status, ad_account_id," +
					" ac_no, ac_name, member_name, voucher_description,vtype, dramt, cramt, narration, trx_status, " +
					" trx_remark , ad_society_no  FROM journal_ledger  where voucher_status!='Pending' AND trx_date between ? AND ?";
			try {
				ps = con.prepareStatement(query);
				ps.setDate(1, new java.sql.Date(from.getTime()));
				ps.setDate(2, new java.sql.Date(to.getTime()));
				rs = ps.executeQuery();
				while (rs.next()) {
					JournalLedgerBean bean1 = new JournalLedgerBean();
					bean1.setTrx_date(rs.getDate("trx_date"));
					bean1.setTrx_remark(rs.getString("trx_remark"));
					bean1.setVoucher_description(rs.getString("voucher_description"));
					bean1.setMember_name(rs.getString("member_name"));
					bean1.setDramt(rs.getDouble("dramt"));
					bean1.setCramt(rs.getDouble("cramt"));
					bean1.setNarration(rs.getString("narration"));
					bean1.setTrx_status(rs.getString("trx_status"));
					bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
					bean1.setVno(rs.getString("vno"));
					bean1.setVoucher_status(rs.getString("voucher_status"));
					bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
					bean1.setAd_account_id(rs.getInt("ad_account_id"));
					bean1.setAc_name(rs.getString("ac_name"));
					bean1.setAc_no(rs.getString("ac_no"));
					bean1.setVtype(rs.getString("vtype"));
					bean1.setAd_society_no(rs.getInt("ad_society_no"));
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("VoucherDao:-error in try Block");
				e.printStackTrace();

			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return bean;
		}	
		
		
		//-----------------------------------------------------------------------------------------------------
				public ArrayList<JournalLedgerBean> getAllJournalEnteriesByVoucherId(int ad_voucher_id) {
					ArrayList<JournalLedgerBean> bean = new ArrayList<JournalLedgerBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "SELECT ad_trx_id, trx_date, ad_voucher_id, vno, voucher_status, ad_account_id," +
							" ac_no, ac_name, member_name, voucher_description,vtype, dramt, cramt, narration, trx_status, " +
							" trx_remark,ad_society_no FROM journal_ledger  where voucher_status!='Pending' AND ad_voucher_id= ?";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_voucher_id);
						
						rs = ps.executeQuery();
						while (rs.next()) {
							JournalLedgerBean bean1 = new JournalLedgerBean();
							bean1.setTrx_date(rs.getDate("trx_date"));
							bean1.setTrx_remark(rs.getString("trx_remark"));
							bean1.setVoucher_description(rs.getString("voucher_description"));
							bean1.setMember_name(rs.getString("member_name"));
							bean1.setDramt(rs.getDouble("dramt"));
							bean1.setCramt(rs.getDouble("cramt"));
							bean1.setNarration(rs.getString("narration"));
							bean1.setTrx_status(rs.getString("trx_status"));
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
							bean1.setVno(rs.getString("vno"));
							bean1.setVoucher_status(rs.getString("voucher_status"));
							bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
							bean1.setAd_account_id(rs.getInt("ad_account_id"));
							bean1.setAc_name(rs.getString("ac_name"));
							bean1.setAc_no(rs.getString("ac_no"));
							bean1.setVtype(rs.getString("vtype"));
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
							bean1.setAd_society_no(rs.getInt("ad_society_no"));
							bean.add(bean1);

						}
					}catch (Exception e) {
						System.out.print("VoucherDao:-error in try Block");
						e.printStackTrace();

					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
					}
					return bean;
				}	
				
				//-----------------------------------------------------------------------------------------------------
				public ArrayList<JournalLedgerBean> getAllJournalEnteriesByMemberAndAccountId(int ad_account_id,int ad_member_id) {
					ArrayList<JournalLedgerBean> bean = new ArrayList<JournalLedgerBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "SELECT ad_trx_id, trx_date, ad_voucher_id, vno, voucher_status, ad_account_id," +
							" ac_no, ac_name, member_name, voucher_description,vtype, dramt, cramt, narration, trx_status, " +
							" trx_remark,ad_society_no FROM journal_ledger  " +
							" where voucher_status!='Pending' AND ad_account_id= ? AND ad_member_id=? order by trx_date";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_account_id);
						ps.setInt(2, ad_member_id);
						rs = ps.executeQuery();
						while (rs.next()) {
							JournalLedgerBean bean1 = new JournalLedgerBean();
							bean1.setTrx_date(rs.getDate("trx_date"));
							bean1.setTrx_remark(rs.getString("trx_remark"));
							bean1.setVoucher_description(rs.getString("voucher_description"));
							bean1.setMember_name(rs.getString("member_name"));
							bean1.setDramt(rs.getDouble("dramt"));
							bean1.setCramt(rs.getDouble("cramt"));
							bean1.setNarration(rs.getString("narration"));
							bean1.setTrx_status(rs.getString("trx_status"));
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
							bean1.setVno(rs.getString("vno"));
							bean1.setVoucher_status(rs.getString("voucher_status"));
							bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
							bean1.setAd_account_id(rs.getInt("ad_account_id"));
							bean1.setAc_name(rs.getString("ac_name"));
							bean1.setAc_no(rs.getString("ac_no"));
							bean1.setVtype(rs.getString("vtype"));
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
							bean1.setAd_society_no(rs.getInt("ad_society_no"));
							bean.add(bean1);

						}
					}catch (Exception e) {
						System.out.print("VoucherDao:-error in try Block");
						e.printStackTrace();

					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
					}
					return bean;
				}	
	}
