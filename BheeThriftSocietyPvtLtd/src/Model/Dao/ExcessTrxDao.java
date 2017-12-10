package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Model.Bean.ExcessTrxBean;
import Model.Bean.ThriftViewBean;

public class ExcessTrxDao {
	private Connection con = null;

	public ExcessTrxDao() {

		con = DBConnection.getConnection();

	}

	public int addExcess(ExcessTrxBean bean) {
		int genid=0;
		PreparedStatement ps=null;
		try {

			String query = "INSERT INTO ad_excess_trx( " +
					" created, createdby, updated, updatedby, isactive, " +
					" trx_date, ad_member_id, excess_amt, balance, ad_voucher_id, status, detail,withdrawal) " +
					" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)" ;

			ps = con.prepareStatement(query,ps.RETURN_GENERATED_KEYS);

			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3,new java.sql.Date( new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, bean.isIsactive());
			ps.setDate(6, new java.sql.Date(bean.getTrx_date().getTime()));
			ps.setInt(7, bean.getAd_member_id());
			ps.setDouble(8, bean.getExcess_amt());
			ps.setDouble(9, bean.getBalance());
			ps.setInt(10, bean.getAd_voucher_id());
			ps.setString(11,bean.getStatus());
			ps.setString(12,bean.getDetail());
			ps.setDouble(13, bean.getWithdrawal());
			ps.executeUpdate();
			ResultSet generatedKeys = null;
			try{
				generatedKeys = ps.getGeneratedKeys(); 
				if (generatedKeys.next()) {
					genid=  generatedKeys.getInt(1);		     
				}
				else {
					throw new SQLException("Creating voucher failed, no ID obtained.");
				}
			}catch(Exception e){
				e.printStackTrace();

			}finally {

				DBConnection.close(generatedKeys);

			}


		} catch (Exception e) {
			System.out.print("ExcessDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return genid;



	}
	//-----------------------------------------------------------------------------------------
	public ExcessTrxBean getExcessById(ExcessTrxBean bean) {
		ExcessTrxBean bean1 = new ExcessTrxBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_excess_trx where ad_member_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_id());
			rs = ps.executeQuery();
			while (rs.next()) {

				bean1.setAd_excess_trx_id(rs.getInt("ad_excess_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setExcess_amt(rs.getDouble("excess_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setDetail(rs.getString("detail"));
				bean1.setWithdrawal(rs.getDouble("withdrawal"));

			}
		}catch (Exception e) {
			System.out.print("ExcessDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}
	//--------------------------------------------------------------------------------------
	public ArrayList<ExcessTrxBean> getAllExcessByMemberId(ExcessTrxBean bean) {
		ArrayList<ExcessTrxBean> bean2 = new ArrayList<ExcessTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_excess_trx where ad_member_id=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				ExcessTrxBean bean1 = new ExcessTrxBean();
				bean1.setAd_excess_trx_id(rs.getInt("ad_excess_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setExcess_amt(rs.getDouble("excess_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setDetail(rs.getString("detail"));
				bean1.setWithdrawal(rs.getDouble("withdrawal"));
				bean2.add(bean1);
				//	System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("ExcessDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean2;
	}


	//--------------------------------------------------------------------------------------
	public ArrayList<ExcessTrxBean> getAllExcessBymemId(int  ad_member_id) {
		ArrayList<ExcessTrxBean> bean2 = new ArrayList<ExcessTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_excess_trx where ad_member_id=? order by ad_excess_trx_id";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				ExcessTrxBean bean1 = new ExcessTrxBean();
				bean1.setAd_excess_trx_id(rs.getInt("ad_excess_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setExcess_amt(rs.getDouble("excess_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setDetail(rs.getString("detail"));
				bean1.setWithdrawal(rs.getDouble("withdrawal"));
				bean2.add(bean1);
				//	System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("ExcessDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean2;
	}
	//--------------------------------------------------------------------------------------
		public double getAllExcessBalanceByMemberId(int  ad_member_id) {
			double balance = 0.0;
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = " select balance from ad_excess_trx " +
					"  where ad_excess_trx_id=(select Max(ad_excess_trx_id) from ad_excess_trx e " +
					"  LEFT JOIN ad_voucher v ON e.ad_voucher_id=v.ad_voucher_id " +
					"  where ad_member_id=? AND v.status='Approved') ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_member_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					
					balance=rs.getDouble("balance");
					

				}
			}catch (Exception e) {
				System.out.print("ExcessDao:-error in try Block");
				e.printStackTrace();

			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return balance;
		}
	//--------------------------------------------------------------------------------------
	public ArrayList<ExcessTrxBean> getAllExcessByVoucherId(int  ad_voucher_id) {
		ArrayList<ExcessTrxBean> bean2 = new ArrayList<ExcessTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select *_id from ad_excess_trx where ad_voucher_id=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,ad_voucher_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				ExcessTrxBean bean1 = new ExcessTrxBean();												
				bean1.setAd_excess_trx_id(rs.getInt("ad_excess_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setExcess_amt(rs.getDouble("excess_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setDetail(rs.getString("detail"));
				bean1.setWithdrawal(rs.getDouble("withdrawal"));
				bean2.add(bean1);						

			}
		}catch (Exception e) {
			System.out.print("ExcessDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean2;
	}



	//------------------------------------------------------------------------------------------	
	public ArrayList<ExcessTrxBean> getExcessById(int ad_member_id) {
		ArrayList<ExcessTrxBean> bean2 = new ArrayList<ExcessTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_excess_trx where ad_member_id=?  and status='Approved' ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				ExcessTrxBean bean1 = new ExcessTrxBean();
				bean1.setAd_excess_trx_id(rs.getInt("ad_excess_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setExcess_amt(rs.getDouble("excess_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setDetail(rs.getString("detail"));
				bean1.setWithdrawal(rs.getDouble("withdrawal"));
				bean2.add(bean1);						

			}
		}catch (Exception e) {
			System.out.print("ExcessDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean2;
	}
	//--------------------------------------------------------------------------------------
	public ExcessTrxBean getExcessByIsActive(int ad_member_id,boolean isActive) {
		ExcessTrxBean bean1 = new ExcessTrxBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_excess_trx where ad_member_id=?  and isactive=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			ps.setBoolean(2, isActive);
			rs = ps.executeQuery();
			while (rs.next()) {


				bean1.setAd_excess_trx_id(rs.getInt("ad_excess_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setExcess_amt(rs.getDouble("excess_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setDetail(rs.getString("detail"));
				bean1.setWithdrawal(rs.getDouble("withdrawal"));

			}
		}catch (Exception e) {
			System.out.print("ExcessDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}
	//------------------------------------------------------------------------------------------	
	public ExcessTrxBean getExcessByExcessId(int ad_excess_trx_id,boolean isActive) {
		ExcessTrxBean bean1 = new ExcessTrxBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_excess_trx where ad_excess_trx_id=?  and isactive=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_excess_trx_id);
			ps.setBoolean(2, isActive);
			rs = ps.executeQuery();
			while (rs.next()) {

				bean1.setAd_excess_trx_id(rs.getInt("ad_excess_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setExcess_amt(rs.getDouble("excess_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setDetail(rs.getString("detail"));
				bean1.setWithdrawal(rs.getDouble("withdrawal"));
			}
		}catch (Exception e) {
			System.out.print("ExcessDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}
	//--------------------------------------------------------------------------------------
	public ArrayList<ExcessTrxBean> getAllExcess() {
		ArrayList<ExcessTrxBean> bean = new ArrayList<ExcessTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_excess_trx where isactive=? AND status=?";
		try {
			ps = con.prepareStatement(query);
			ps.setBoolean(1, true);
			ps.setString(2, "Approved");
			rs = ps.executeQuery();
			while (rs.next()) {
				ExcessTrxBean bean1 = new ExcessTrxBean();

				bean1.setAd_excess_trx_id(rs.getInt("ad_excess_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setExcess_amt(rs.getDouble("excess_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setDetail(rs.getString("detail"));
				bean1.setWithdrawal(rs.getDouble("withdrawal"));
				bean.add(bean1);
				

			}
		}catch (Exception e) {
			System.out.print("ExcessDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}

	//--------------------------------------------------------------------------------------


	//----------------------------------------------------------------------------------------------
	public void updateExcess(int ad_voucher_id){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_excess_trx    SET  isactive=?,  status=? WHERE ad_voucher_id=?";
			ps = con.prepareStatement(query);

			ps.setBoolean(1, true);
			ps.setString(2, "Approved");
			ps.setInt(3, ad_voucher_id);


			//System.out.print(ps);
			i=ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("ExcessDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}
	//-------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------
		public int updateExcess(ExcessTrxBean bean){
			int i=0;
			PreparedStatement ps=null;
			try {

				String query = "UPDATE ad_excess_trx " +
						" SET  updated=?, updatedby=?, isactive=?, trx_date=?, " +
						" ad_member_id=?, excess_amt=?, withdrawal=?, balance=?, " +
						" ad_voucher_id=?, status=?, detail=? WHERE ad_excess_trx_id=?";
				ps = con.prepareStatement(query);
				ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(2, bean.getUpdatedby());
				ps.setBoolean(3, bean.isIsactive());
				ps.setDate(4, new java.sql.Date(bean.getTrx_date().getTime()));
				ps.setInt(5, bean.getAd_member_id());
				ps.setDouble(6, bean.getExcess_amt());
				ps.setDouble(7, bean.getWithdrawal());
				ps.setDouble(8, bean.getBalance());
				ps.setInt(9, bean.getAd_voucher_id());
				ps.setString(10,bean.getStatus());
				ps.setString(11,bean.getDetail());				
				ps.setInt(12, bean.getAd_excess_trx_id());
				i=ps.executeUpdate();
				
				


			} catch (Exception e) {
				System.out.print("ExcessDao:-error in try Block");
				e.printStackTrace();

			}finally {

				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return i;



		}

	//-------------------------
	public void CloseExcess(int ad_excess_trx_id){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_excess_trx    SET   isactive=?, status=? WHERE ad_excess_trx_id=?";
			ps = con.prepareStatement(query);


			ps.setBoolean(1, false);
			ps.setString(2,"Closed");
			ps.setInt(3, ad_excess_trx_id);

			//System.out.print(ps);
			i=ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("ExcessDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}
	//----------------------------------------------------------------------------------------------
	public int deleteExcessByVoucher(int  ad_voucher_id){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_excess_trx WHERE ad_voucher_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			i=ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("ExcessDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return i;
	}

	//----------------------------------------------------------------------------------------------
		public int  deleteExcessById(int  ad_excess_trx_id){
			int i=0;
			PreparedStatement ps=null;
			try{
				String query="DELETE FROM ad_excess_trx WHERE ad_excess_trx_id=?";
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_excess_trx_id);
				i=ps.executeUpdate();

			}catch (Exception e) {
				System.out.print("ExcessDao:-error in try Block");
				e.printStackTrace();

			}finally {

				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return i;
		}

	//----------------------------------------------------------------------------------------------
	@SuppressWarnings("finally")
	public int getExcessMaxId(){
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			String query="select ad_excess_trx_id from ad_excess_trx where ad_excess_trx_id=(select Max(ad_excess_trx_id) from ad_excess_trx)";
			ps=con.prepareStatement(query);

			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getInt("ad_excess_trx_id");
			}

		}catch (Exception e) {
			System.out.print("ExcessDao:-error in try Block");
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return result;
	}


	//----------------------------------------------------------------------------------------------
		@SuppressWarnings("finally")
		public Date getExcessMaxDate(){
			Date result=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			try{
				String query="select trx_date from ad_excess_trx where ad_excess_trx_id=(select Max(ad_excess_trx_id) from ad_excess_trx)";
				ps=con.prepareStatement(query);

				rs=ps.executeQuery();
				if(rs.next()){
					result=rs.getDate("trx_date");
				}

			}catch (Exception e) {
				System.out.print("ExcessDao:-error in try Block");
				e.printStackTrace();
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}

			return result;
		}


	//----------------------------------------------------------------------------------------------




	//----------------------------------------------------------------------------------------------
	@SuppressWarnings("finally")
	public int getMemberNo(int ad_member_id){
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			String query="select max(ad_excess_trx_id) as ad_excess_trx_id from ad_excess_trx where ad_member_id=? ";
			ps=con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getInt("ad_excess_trx_id");
			}

		}catch (Exception e) {
			System.out.print("ExcessDao:-error in try Block");
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return result;
	}//end getMemberNo function


	//--------------------------------------------------------------------------------------------------------------------------------------			
	public double getMaxExcessBalanceByMemberId(int ad_member_id) {
		double balance=0.0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = " SELECT balance FROM ad_excess_trx where  " +
				" ad_excess_trx_id=(Select Max(ad_excess_trx_id) from ad_excess_trx " +
				" where ad_member_id=? )";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				balance=rs.getDouble("balance");

			}
		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return balance;
	}


}
