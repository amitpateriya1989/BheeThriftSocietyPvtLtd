package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import Model.Bean.TransactionBean;

public class StaffTransactionDao {
	private Connection con = null;

	public StaffTransactionDao() {
		con = DBConnection.getConnection();
	}

	public void addTransaction(TransactionBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {



			String query = "INSERT INTO ad_trx(" +
					"created, createdby, updated, updatedby, isactive, ad_voucher_id," +
					"ad_account_id, trx_date, dramt, cramt, narration, ad_employee_id)" +
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
			ps.setInt(12, bean.getAd_employee_id());

			record=ps.executeUpdate();

		} catch (Exception e) {
			System.out.print("StaffTransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

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
				bean1.setEmp(new EmployeeDao().getEmployeeById(rs.getInt("ad_employee_id")));
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
			System.out.print("StaffTransactionDao:-error in try Block");
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
				bean1.setEmp(new EmployeeDao().getEmployeeById(rs.getInt("ad_employee_id")));
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
			System.out.print("StaffTransactionDao:-error in try Block");
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
				bean1.setEmp(new EmployeeDao().getEmployeeById(rs.getInt("ad_employee_id")));
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
			System.out.print("StaffTransactionDao:-error in try Block");
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
				bean1.setEmp(new EmployeeDao().getEmployeeById(rs.getInt("ad_employee_id")));
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
			System.out.print("StaffTransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}
	//------------------------
	public double getTotalCrbyMemAndAcID(int ad_employee_id , int ad_account_id ) {
		double result=0;
		PreparedStatement ps =null;
		ResultSet rs=null;

		String query = "select sum(cramt) as cramt  from ad_trx where ad_employee_id=? and ad_account_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_employee_id);
			ps.setInt(2, ad_account_id);
			rs = ps.executeQuery();
			while (rs.next()) {

				result=rs.getDouble("cramt");



			}
		}catch (Exception e) {
			System.out.print("StaffTransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return result;
	}
	//------------------------
	public double getTotalDrbyMemAndAcID(int ad_employee_id , int ad_account_id ) {
		double result=0;
		PreparedStatement ps =null;
		ResultSet rs=null;

		String query = "select sum(dramt) as dramt  from ad_trx where ad_employee_id=? and ad_account_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_employee_id);
			ps.setInt(2, ad_account_id);
			rs = ps.executeQuery();
			while (rs.next()) {

				result=rs.getDouble("dramt");



			}
		}catch (Exception e) {
			System.out.print("StaffTransactionDao:-error in try Block");
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
				bean1.setEmp(new EmployeeDao().getEmployeeById(rs.getInt("ad_employee_id")));
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
			System.out.print("StaffTransactionDao:-error in try Block");
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
				bean1.setEmp(new EmployeeDao().getEmployeeById(rs.getInt("ad_employee_id")));
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
			System.out.print("StaffTransactionDao:-error in try Block");
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
				bean1.setEmp(new EmployeeDao().getEmployeeById(rs.getInt("ad_employee_id")));
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
			System.out.print("StaffTransactionDao:-error in try Block");
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
				bean1.setEmp(new EmployeeDao().getEmployeeById(rs.getInt("ad_employee_id")));
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
			System.out.print("StaffTransactionDao:-error in try Block");
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
	public ArrayList<TransactionBean> getAllEmpTransactionByMemIdAccId(int ad_employee_id , int ad_account_id) {
		ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_trx where ad_account_id=?  and ad_employee_id=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_account_id );
			ps.setInt(2, ad_employee_id);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				TransactionBean bean1 = new TransactionBean();
				bean1.setLedger(new LedgerAccountDao().getLedgerAccountById(rs.getInt("ad_account_id")));
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				bean1.setEmp(new EmployeeDao().getEmployeeById(rs.getInt("ad_employee_id")));
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
			System.out.print("StaffTransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return bean;
	}		

	//----------------------------------------------------------------------------------------------




	public TransactionBean getAllLedgerTransactionBefourFromDate(int ad_ledger_id,Date strDate, int ad_employee_id) {
		TransactionBean bean = new TransactionBean();
		PreparedStatement ps=null;
		ResultSet rs = null;
		try{
			if(ad_employee_id==0){
				String query = "select sum(cramt) as cramt,sum(dramt) as dramt from ad_trx where ad_account_id=?  and trx_date <? ";
				try{
					ps = con.prepareStatement(query);
					ps.setInt(1,ad_ledger_id);
					ps.setDate(2,new java.sql.Date(strDate.getTime()));

				}catch(Exception e){
					e.printStackTrace();
				}
			}else{

				String query = "select sum(cramt) as cramt,sum(dramt) as dramt from ad_trx where ad_account_id=?  and ad_employee_id=? and trx_date <?  ";
				try{
					ps = con.prepareStatement(query);
					ps.setInt(1,ad_ledger_id);
					ps.setInt(2, ad_employee_id);
					ps.setDate(3,new java.sql.Date(strDate.getTime()));


				}catch(Exception e){
					e.printStackTrace();
				}

			}

			try {

				System.out.println(ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					bean.setDramt(rs.getDouble("dramt"));
					bean.setCramt(rs.getDouble("cramt"));

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}			



	//----------------------------------------------------------------------------------------------				



	//----------------------------------------------------------------------------------------------




	public ArrayList<TransactionBean> getAllLedgerTransactionByFromToDate(int ad_ledger_id,Date strDate,Date currentDate, int ad_employee_id) {
		ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
		PreparedStatement ps=null;
		ResultSet rs = null;
		if(ad_employee_id==0){
			String query = "select * from ad_trx where ad_account_id=?  and trx_date BETWEEN ? and ? ";
			try{
				ps = con.prepareStatement(query);
				ps.setInt(1,ad_ledger_id);
				ps.setDate(2,new java.sql.Date(strDate.getTime()));
				ps.setDate(3,new java.sql.Date(currentDate.getTime()));
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{

			String query = "select * from ad_trx where ad_account_id=?  and ad_employee_id=? and trx_date BETWEEN ? and ?  ";
			try{
				ps = con.prepareStatement(query);
				ps.setInt(1,ad_ledger_id);
				ps.setInt(2, ad_employee_id);
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
				bean1.setVoucher(new VoucherDao().getVoucherByVoucherId(rs.getInt("ad_voucher_id")));
				if(rs.getInt("ad_employee_id")==0){
					//bean1.setEmp("-");
				}else{
					bean1.setEmp(new EmployeeDao().getEmployeeById(rs.getInt("ad_employee_id")));
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
				bean1.setVoucher(new VoucherDao().getVoucherById(rs.getInt("ad_voucher_id"),true));

				bean.add(bean1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}			



	//----------------------------------------------------------------------------------------------


	public ArrayList<TransactionBean> getLedgerOpeningBal(int ad_ledger_id,Date strDate) {
		ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
		PreparedStatement ps=null;
		ResultSet rs = null;


		String query = "select at.dramt,at.cramt from ad_trx at left join ad_voucher av on av.ad_voucher_id= at.ad_voucher_id where at.ad_account_id=?   and at.trx_date <? and av.status='Approved'  ";
		try{
			ps = con.prepareStatement(query);
			ps.setInt(1,ad_ledger_id);

			ps.setDate(2,new java.sql.Date(strDate.getTime()));

			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {

				TransactionBean bean1 = new TransactionBean();
				bean1.setDramt(rs.getDouble("dramt"));
				bean1.setCramt(rs.getDouble("cramt"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean.add(bean1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}			



	//----------------------------------------------------------------------------------------------


	public ArrayList<TransactionBean> getLedgerBalBetweendates(int ad_ledger_id,Date strDate,Date endDate) {
		ArrayList<TransactionBean> bean = new ArrayList<TransactionBean>();
		PreparedStatement ps=null;
		ResultSet rs = null;


		String query = "select at.dramt,at.cramt from ad_trx at left join ad_voucher av on av.ad_voucher_id= at.ad_voucher_id where at.ad_account_id=?  and av.status='Approved' and at.trx_date BETWEEN ? and ?   ";
		try{
			ps = con.prepareStatement(query);
			ps.setInt(1,ad_ledger_id);

			ps.setDate(2,new java.sql.Date(strDate.getTime()));
			ps.setDate(3,new java.sql.Date(endDate.getTime()));


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
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}			










	//----------------------------------------------------------------------------------------------
	public void updateTransaction(TransactionBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_trx" +
					"SET updated=?, updatedby=?,isactive=?, ad_voucher_id=?," +
					"ad_account_id=?, trx_date=?, dramt=?,cramt=?, narration=?," +
					"ad_employee_id=? WHERE ad_trx_id=?";
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
			ps.setInt(10, bean.getEmp().getAd_employee_id());
			ps.setInt(11, bean.getAd_trx_id());



			//System.out.print(ps);
			i=ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("StaffTransactionDao:-error in try Block");
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
			System.out.print("StaffTransactionDao:-error in try Block");
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
			ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("StaffTransactionDao:-error in try Block");
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
			String query="DELETE FROM ad_trx WHERE ad_voucher_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1,ad_voucher_id);
			ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("StaffTransactionDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}


}
