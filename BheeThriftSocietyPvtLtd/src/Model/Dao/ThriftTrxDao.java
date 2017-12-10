package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Model.Bean.ExcessTrxBean;
import Model.Bean.ThriftTrxBean;
import Model.Bean.ThriftViewBean;

public class ThriftTrxDao {
	private Connection con = null;

	public ThriftTrxDao() {

		con = DBConnection.getConnection();

	}

	public int addThriftTrx(ThriftTrxBean bean) {
		int genid=0;
		PreparedStatement ps=null;
		try {

			String query = "INSERT INTO ad_thrift_trx(" +
					" created, createdby, updated, updatedby, isactive, trx_date, ad_member_id, " +
					"thrift_amt, ad_voucher_id,status,thrift_int, balance,withdrawal,discription)" +
					"  VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)" ;

			ps = con.prepareStatement(query,ps.RETURN_GENERATED_KEYS);

			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3,new java.sql.Date( new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, bean.isIsactive());
			ps.setDate(6, new java.sql.Date(bean.getTrx_date().getTime()));
			ps.setInt(7, bean.getAd_member_id());
			ps.setDouble(8, bean.getThrift_amt());
			ps.setInt(9, bean.getAd_voucher_id());
			ps.setString(10,bean.getStatus());
			ps.setDouble(11, bean.getThrift_int());
			ps.setDouble(12, bean.getBalance());
			ps.setDouble(13, bean.getWithdrawal());
			ps.setString(14, bean.getDiscription());
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
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return genid;



	}
	//-----------------------------------------------------------------------------------------
	public ThriftTrxBean getThriftTrxById(ThriftTrxBean bean) {
		ThriftTrxBean bean1 = new ThriftTrxBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_thrift_trx where ad_member_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_id());
			rs = ps.executeQuery();
			while (rs.next()) {

				bean1.setAd_thrift_trx_id(rs.getInt("ad_thrift_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setThrift_amt(rs.getDouble("thrift_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setThrift_int(rs.getDouble("thrift_int"));


			}
		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}
	//--------------------------------------------------------------------------------------
	public ArrayList<ThriftTrxBean> getAllThriftTrxByMemberId(ThriftTrxBean bean) {
		ArrayList<ThriftTrxBean> bean2 = new ArrayList<ThriftTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_thrift_trx where ad_member_id=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				ThriftTrxBean bean1 = new ThriftTrxBean();
				bean1.setAd_thrift_trx_id(rs.getInt("ad_thrift_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setThrift_amt(rs.getDouble("thrift_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));	
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setThrift_int(rs.getDouble("thrift_int"));
				bean2.add(bean1);
				//	System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean2;
	}


	//--------------------------------------------------------------------------------------
	public ArrayList<ThriftTrxBean> getAllThriftTrxBymemId(int  ad_member_id) {
		ArrayList<ThriftTrxBean> bean2 = new ArrayList<ThriftTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_thrift_trx where ad_member_id=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				ThriftTrxBean bean1 = new ThriftTrxBean();
				bean1.setAd_thrift_trx_id(rs.getInt("ad_thrift_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setThrift_amt(rs.getDouble("thrift_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setThrift_int(rs.getDouble("thrift_int"));
				bean2.add(bean1);
				//	System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean2;
	}
	//--------------------------------------------------------------------------------------
	public ArrayList<ThriftTrxBean> getAllThriftTrxByVoucherId(int  ad_voucher_id) {
		ArrayList<ThriftTrxBean> bean2 = new ArrayList<ThriftTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select *_id from ad_thrift_trx where ad_voucher_id=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,ad_voucher_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				ThriftTrxBean bean1 = new ThriftTrxBean();												
				bean1.setAd_thrift_trx_id(rs.getInt("ad_thrift_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setThrift_amt(rs.getDouble("thrift_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setThrift_int(rs.getDouble("thrift_int"));
				bean2.add(bean1);						

			}
		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean2;
	}



	//------------------------------------------------------------------------------------------	
	public ArrayList<ThriftTrxBean> getThriftTrxById(int ad_member_id) {
		ArrayList<ThriftTrxBean> bean2 = new ArrayList<ThriftTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_thrift_trx where ad_member_id=?  and status='Approved' ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				ThriftTrxBean bean1 = new ThriftTrxBean();
				bean1.setAd_thrift_trx_id(rs.getInt("ad_thrift_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setThrift_amt(rs.getDouble("thrift_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setThrift_int(rs.getDouble("thrift_int"));
				bean2.add(bean1);						

			}
		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean2;
	}
	//--------------------------------------------------------------------------------------
	public ThriftTrxBean getThriftTrxByIsActive(int ad_member_id,boolean isActive) {
		ThriftTrxBean bean1 = new ThriftTrxBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_thrift_trx where ad_member_id=?  and isactive=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			ps.setBoolean(2, isActive);
			rs = ps.executeQuery();
			while (rs.next()) {


				bean1.setAd_thrift_trx_id(rs.getInt("ad_thrift_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setThrift_amt(rs.getDouble("thrift_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setThrift_int(rs.getDouble("thrift_int"));


			}
		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}
	//------------------------------------------------------------------------------------------	
	public ThriftTrxBean getThriftTrxByThriftId(int ad_thrift_trx_id,boolean isActive) {
		ThriftTrxBean bean1 = new ThriftTrxBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_thrift_trx where ad_thrift_trx_id=?  and isactive=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_thrift_trx_id);
			ps.setBoolean(2, isActive);
			rs = ps.executeQuery();
			while (rs.next()) {

				bean1.setAd_thrift_trx_id(rs.getInt("ad_thrift_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setThrift_amt(rs.getDouble("thrift_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setThrift_int(rs.getDouble("thrift_int"));
			}
		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}
	//--------------------------------------------------------------------------------------
	public ArrayList<ThriftTrxBean> getAllThriftTrx() {
		ArrayList<ThriftTrxBean> bean = new ArrayList<ThriftTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_thrift_trx ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				ThriftTrxBean bean1 = new ThriftTrxBean();

				bean1.setAd_thrift_trx_id(rs.getInt("ad_thrift_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setThrift_amt(rs.getDouble("thrift_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setThrift_int(rs.getDouble("thrift_int"));
				bean.add(bean1);
				System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
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
	public void updateThriftTrx(int ad_voucher_id){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_thrift_trx    SET  isactive=?,  status=? WHERE ad_voucher_id=?";
			ps = con.prepareStatement(query);

			ps.setBoolean(1, true);
			ps.setString(2, "Approved");
			ps.setInt(3, ad_voucher_id);


			//System.out.print(ps);
			i=ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}
	//-------------------------
	public void CloseThriftTrx(int ad_thrift_trx_id){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_thrift_trx    SET   isactive=?, status=? WHERE ad_thrift_trx_id=?";
			ps = con.prepareStatement(query);


			ps.setBoolean(1, false);
			ps.setString(2,"Closed");
			ps.setInt(3, ad_thrift_trx_id);

			//System.out.print(ps);
			i=ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}
	//----------------------------------------------------------------------------------------------
	public void deleteThriftTrx(int  ad_voucher_id){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_thrift_trx WHERE ad_voucher_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}

	//----------------------------------------------------------------------------------------------
		public int deleteThriftTrxById(int  ad_thrift_trx_id){
			int i=0;
			PreparedStatement ps=null;
			try{
				String query="DELETE FROM ad_thrift_trx WHERE ad_thrift_trx_id=?";
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_thrift_trx_id);
				i=ps.executeUpdate();

			}catch (Exception e) {
				System.out.print("ThriftTrxDao:-error in try Block");
				e.printStackTrace();

			}finally {

				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return i;
		}

	//----------------------------------------------------------------------------------------------
			public int updateThriftTrx(ThriftTrxBean bean){
				int i=0;
				PreparedStatement ps=null;
				try {

					String query = "UPDATE ad_thrift_trx " +
							" SET  updated=?, updatedby=?, isactive=?, trx_date=?, " +
							" ad_member_id=?, thrift_amt=?, withdrawal=?, balance=?, " +
							" ad_voucher_id=?, status=?, description=? WHERE ad_thrift_trx_id=?";
					ps = con.prepareStatement(query);
					ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
					ps.setInt(2, bean.getUpdatedby());
					ps.setBoolean(3, bean.isIsactive());
					ps.setDate(4, new java.sql.Date(bean.getTrx_date().getTime()));
					ps.setInt(5, bean.getAd_member_id());
					ps.setDouble(6, bean.getThrift_amt());
					ps.setDouble(7, bean.getWithdrawal());
					ps.setDouble(8, bean.getBalance());
					ps.setInt(9, bean.getAd_voucher_id());
					ps.setString(10,bean.getStatus());
					ps.setString(11,bean.getDiscription());				
					ps.setInt(12, bean.getAd_thrift_trx_id());
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
	//----------------------------------------------------------------------------------------------
	@SuppressWarnings("finally")
	public int getThriftTrxMaxId(){
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			String query="select ad_thrift_trx_id from ad_thrift_trx where ad_thrift_trx_id=(select Max(ad_thrift_trx_id) from ad_thrift_trx)";
			ps=con.prepareStatement(query);

			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getInt("ad_thrift_trx_id");
			}

		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
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
		public Date getThriftTrxMaxDate(){
			Date result=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			try{
				String query="select trx_date from ad_thrift_trx where ad_thrift_trx_id=(select Max(ad_thrift_trx_id) from ad_thrift_trx)";
				ps=con.prepareStatement(query);

				rs=ps.executeQuery();
				if(rs.next()){
					result=rs.getDate("trx_date");
				}

			}catch (Exception e) {
				System.out.print("ThriftTrxDao:-error in try Block");
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
			String query="select max(ad_thrift_trx_id) as ad_thrift_trx_id from ad_thrift_trx where ad_member_id=? ";
			ps=con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getInt("ad_thrift_trx_id");
			}

		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return result;
	}//end getMemberNo function


	//--------------------------------------------------------------------------------------------------------------------------------------			
	public ArrayList<ThriftViewBean> getAllThriftDetail() {
		ArrayList<ThriftViewBean> bean = new ArrayList<ThriftViewBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "SELECT * from thrift_detail; ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				ThriftViewBean bean1 = new ThriftViewBean();
				bean1.setAd_thrift_trx_id(rs.getInt("ad_thrift_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setThrift_amt(rs.getDouble("thrift_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setName(rs.getString("name"));
				bean1.setSociety_no(rs.getInt("society_no"));
				bean1.setPf_no(rs.getInt("pf_no"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setThrift_int(rs.getDouble("thrift_int"));

				bean.add(bean1);
				System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}

	//--------------------------------------------------------------------------------------------------------------------------------------			
	public ArrayList<ThriftViewBean> getAllThriftDetail(java.util.Date from,java.util.Date to) {
		ArrayList<ThriftViewBean> bean = new ArrayList<ThriftViewBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "SELECT * FROM thrift_detail where (trx_date between ? AND ? ) AND thrift_amt is not null AND ( status=? OR status=? ) order by trx_date; ";
		try {
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(from.getTime()));
			ps.setDate(2, new java.sql.Date(to.getTime()));
			ps.setString(3, "Approved");
			ps.setString(4, "Opening");
			rs = ps.executeQuery();
			while (rs.next()) {
				ThriftViewBean bean1 = new ThriftViewBean();
				bean1.setAd_thrift_trx_id(rs.getInt("ad_thrift_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setThrift_amt(rs.getDouble("thrift_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setName(rs.getString("name"));
				bean1.setSociety_no(rs.getInt("society_no"));
				bean1.setPf_no(rs.getInt("pf_no"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setThrift_int(rs.getDouble("thrift_int"));

				bean.add(bean1);
				System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}


	//--------------------------------------------------------------------------------------------------------------------------------------			
	public ArrayList<ThriftViewBean> getAllThriftDetailByMemberId(int ad_member_id) {
		ArrayList<ThriftViewBean> bean = new ArrayList<ThriftViewBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "SELECT * FROM thrift_detail where ad_member_id=? AND ( status=? OR status=? ) order by trx_date ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			ps.setString(2, "Approved");
			ps.setString(3, "Opening");
			rs = ps.executeQuery();
			while (rs.next()) {
				ThriftViewBean bean1 = new ThriftViewBean();

				bean1.setAd_thrift_trx_id(rs.getInt("ad_thrift_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setThrift_amt(rs.getDouble("thrift_amt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setStatus(rs.getString("status"));
				bean1.setName(rs.getString("name"));
				bean1.setSociety_no(rs.getInt("society_no"));
				bean1.setPf_no(rs.getInt("pf_no"));
				bean1.setBalance(rs.getDouble("balance"));
				bean1.setThrift_int(rs.getDouble("thrift_int"));
				bean.add(bean1);
				//System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}
	//--------------------------------------------------------------------------------------------------------------------------------------			
		public ArrayList<ThriftViewBean> getAllThriftDetailByMemberIdAndDate(int ad_member_id,Date date) {
			ArrayList<ThriftViewBean> bean = new ArrayList<ThriftViewBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "SELECT * FROM thrift_detail where ad_member_id=? AND  status in (? ,? ) AND trx_date>=? order by trx_date ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_member_id);
				ps.setString(2, "Approved");
				ps.setString(3, "Opening");
				ps.setDate(4,new java.sql.Date(date.getTime()));
				rs = ps.executeQuery();
				while (rs.next()) {
					ThriftViewBean bean1 = new ThriftViewBean();

					bean1.setAd_thrift_trx_id(rs.getInt("ad_thrift_trx_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setThrift_amt(rs.getDouble("thrift_amt"));
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
					bean1.setTrx_date(rs.getDate("trx_date"));
					bean1.setStatus(rs.getString("status"));
					bean1.setName(rs.getString("name"));
					bean1.setSociety_no(rs.getInt("society_no"));
					bean1.setPf_no(rs.getInt("pf_no"));
					bean1.setBalance(rs.getDouble("balance"));
					bean1.setThrift_int(rs.getDouble("thrift_int"));
					bean.add(bean1);
					//System.out.print(bean);

				}
			}catch (Exception e) {
				System.out.print("ThriftTrxDao:-error in try Block");
				e.printStackTrace();

			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return bean;
		}

	//--------------------------------------------------------------------------------------------------------------------------------------			
		//--------------------------------------------------------------------------------------------------------------------------------------			
				public ArrayList<ThriftViewBean> getAllThriftViewDetail() {
					ArrayList<ThriftViewBean> bean = new ArrayList<ThriftViewBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "SELECT * FROM thrift_detail  order by trx_date desc ";
					try {
						ps = con.prepareStatement(query);
						rs=ps.executeQuery();
						while (rs.next()) {
							ThriftViewBean bean1 = new ThriftViewBean();

							bean1.setAd_thrift_trx_id(rs.getInt("ad_thrift_trx_id"));
							bean1.setCreated(rs.getDate("created"));
							bean1.setCreatedby(rs.getInt("createdby"));
							bean1.setUpdated(rs.getDate("updated"));
							bean1.setUpdatedby(rs.getInt("updatedby"));
							bean1.setIsactive(rs.getBoolean("isactive"));
							bean1.setThrift_amt(rs.getDouble("thrift_amt"));
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
							bean1.setTrx_date(rs.getDate("trx_date"));
							bean1.setStatus(rs.getString("status"));
							bean1.setName(rs.getString("name"));
							bean1.setSociety_no(rs.getInt("society_no"));
							bean1.setPf_no(rs.getInt("pf_no"));
							bean1.setBalance(rs.getDouble("balance"));
							bean1.setThrift_int(rs.getDouble("thrift_int"));
							bean.add(bean1);
							//System.out.print(bean);

						}
					}catch (Exception e) {
						System.out.print("ThriftTrxDao:-error in try Block");
						e.printStackTrace();

					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
					}
					return bean;
				}

			//--------------------------------------------------------------------------------------------------------------------------------------			

		public ThriftViewBean getMaxThriftInterestDate(int ad_member_id) {
			ThriftViewBean bean =null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "SELECT t.trx_date,t.balance,t.ad_thrift_trx_id FROM ad_thrift_trx t " +
					" WHERE ad_thrift_trx_id=(SELECT MAX(ad_thrift_trx_id) from ad_thrift_trx " +
					"  where thrift_int>0.0  AND ad_member_id=? AND isactive=? AND status=? )";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_member_id);
				ps.setBoolean(2, true);
				ps.setString(3, "Approved");
				//System.out.println(ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					
					bean=new ThriftViewBean();
					bean.setAd_thrift_trx_id(rs.getInt("ad_thrift_trx_id"));
					bean.setTrx_date(rs.getDate("trx_date"));
					bean.setBalance(rs.getDouble("balance"));
					
				}
			}catch (Exception e) {
				System.out.print("ThriftTrxDao:-error in try Block");
				e.printStackTrace();

			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return bean;
		}

		
		//--------------------------------------------------------------------------------------------------------------------------------------			
				public ThriftViewBean getMaxThriftOpeningDate(int ad_member_id) {
					ThriftViewBean bean =null;
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = " select t.trx_date,t.balance,t.ad_thrift_trx_id from ad_thrift_trx t " +
							" where ad_thrift_trx_id=(SELECT MIN(ad_thrift_trx_id) from ad_thrift_trx " +
							" where balance>0 AND ad_member_id=? and  isactive=? AND status in('Opening' ,'Approved' ))";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_member_id);
						ps.setBoolean(2, true);
						//System.out.println(ps);
						rs = ps.executeQuery();
						while (rs.next()) {
							
							bean=new ThriftViewBean();
							bean.setAd_thrift_trx_id(rs.getInt("ad_thrift_trx_id"));
							bean.setTrx_date(rs.getDate("trx_date"));
							bean.setBalance(rs.getDouble("balance"));
							
						}
					}catch (Exception e) {
						System.out.print("ThriftTrxDao:-error in try Block");
						e.printStackTrace();

					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
					}
					return bean;
				}

	//--------------------------------------------------------------------------------------------------------------------------------------			
	public double getTotalThriftAmountByMemberId(int ad_member_id) {
		double thrift_amt=0.0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "SELECT balance as thrift_amt FROM thrift_detail where ad_member_id=? " +
				" AND ad_thrift_trx_id=(SELECT Max(ad_thrift_trx_id) from thrift_detail t " +
				" LEFT JOIN ad_voucher v ON t.ad_voucher_id=v.ad_voucher_id "+
                "  where t.ad_member_id=? AND v.status='Approved' )";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			ps.setInt(2, ad_member_id);
			System.out.println(ps);

			rs = ps.executeQuery();
			while (rs.next()) {
				thrift_amt=rs.getDouble("thrift_amt");

			}
		}catch (Exception e) {
			System.out.print("ThriftTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return thrift_amt;
	}





//--------------------------------------------------------------------------------------------------------------------------------------			
public double getMaxThriftBalanceByMemberId(int ad_member_id) {
	double balance=0.0;
	PreparedStatement ps=null;
	ResultSet rs=null;
	String query = " SELECT balance FROM thrift_detail where  " +
			" ad_thrift_trx_id=(Select Max(ad_thrift_trx_id) from ad_thrift_trx " +
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

