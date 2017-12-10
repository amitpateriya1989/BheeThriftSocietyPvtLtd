package Model.Dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.MemberShareBean;
import Model.Bean.ShareBean;
import Model.Bean.ShareViewBean;

public class MemberShareDao {
	private Connection con = null;

	public MemberShareDao() {
		try{
			con = DBConnection.getConnection();
			
			}catch(Exception s){
				System.out.println("MemberShareDao:-Exception in Creating connection and auto commit off");
				s.printStackTrace();
			}
		
	}

	public int addMemberShare(MemberShareBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_member_share(" +
					"ad_member_id, created, createdby, updated, updatedby, date_of_allocation," +
					" trx_by, chk_dd_date, chk_dd_no,  share_amt, qnt_of_share, share_no_form, share_no_to, isactive,batch_no,ad_voucher_id,status,amt_in_words)" +
					" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?);" ;
					
			ps = con.prepareStatement(query);
			
			ps.setInt(1, bean.getAd_member_id());
			ps.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(3, bean.getCreatedby());
			ps.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(5, bean.getUpdatedby());
			ps.setDate(6, new java.sql.Date(bean.getDate_of_allocation().getTime()));
			ps.setString(7, bean.getTrx_by());
			ps.setDate(8, new java.sql.Date(bean.getChk_dd_date().getTime()));
			ps.setInt(9, bean.getChk_dd_no());
			ps.setDouble(10, bean.getShare_amt());
			ps.setInt(11, bean.getQnt_of_share());			
			ps.setInt(12, bean.getShare_no_form());
			ps.setInt(13, bean.getShare_no_to());
			ps.setBoolean(14, false);
			ps.setInt(15, bean.getBatch_no());	
			ps.setInt(16, bean.getAd_voucher_id());
			ps.setString(17, bean.getStatus());
			ps.setString(18, bean.getAmt_in_words());
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("MemberShareDao:-error in try Block");
			e.printStackTrace();
						
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
	//-----------------------------------------------------------------------------------------
	public int addMemberShareByLoan(MemberShareBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_member_share(" +
					"ad_member_id, created, createdby, updated, updatedby, date_of_allocation," +
					" trx_by,  share_amt, qnt_of_share, share_no_form, share_no_to, isactive,batch_no,ad_voucher_id,loan_trx_id, status,amt_in_words)" +
					" VALUES (?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?,?,?,?,?,?);" ;
					
			ps = con.prepareStatement(query);
			
			ps.setInt(1, bean.getAd_member_id());
			ps.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(3, bean.getCreatedby());
			ps.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(5, bean.getUpdatedby());
			ps.setDate(6, new java.sql.Date(bean.getDate_of_allocation().getTime()));
			ps.setString(7, bean.getTrx_by());
			ps.setDouble(8, bean.getShare_amt());
			ps.setInt(9, bean.getQnt_of_share());			
			ps.setInt(10, bean.getShare_no_form());
			ps.setInt(11, bean.getShare_no_to());
			ps.setBoolean(12, false);
			ps.setInt(13, bean.getBatch_no());	
			ps.setInt(14, bean.getAd_voucher_id());	
			ps.setInt(15, bean.getLoan_trx_id());
			ps.setString(16, bean.getStatus());
			ps.setString(17, bean.getAmt_in_words());
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("MemberShareDao:-error in try Block");
			e.printStackTrace();
						
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public MemberShareBean getMemberShareById(MemberShareBean bean) {
		MemberShareBean bean1 = new MemberShareBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_member_share where ad_member_share_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_share_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_member_share_id(rs.getInt("ad_member_share_id"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				
				
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setDate_of_allocation(rs.getDate("date_of_allocation"));
				bean1.setTrx_by(rs.getString("trx_by"));
				bean1.setChk_dd_date(rs.getDate("chk_dd_date"));
				bean1.setChk_dd_no(rs.getInt("chk_dd_no"));
				bean1.setShare_amt(rs.getDouble("share_amt"));
				bean1.setQnt_of_share(rs.getInt("qnt_of_share"));
				bean1.setShare_no_form(rs.getInt("share_no_form"));
				bean1.setShare_no_to(rs.getInt("share_no_to"));				
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
				bean1.setStatus(rs.getString("status"));
				
				

			}
			}catch (Exception e) {
				System.out.print("MemberShareDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//-----------------------------------------------------------------------------------------
		public MemberShareBean getMemberShareById(int ad_member_share_id) {
			MemberShareBean bean1 = null;
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from ad_member_share where ad_member_share_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_member_share_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1=new MemberShareBean();
					bean1.setAd_member_share_id(rs.getInt("ad_member_share_id"));
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setDate_of_allocation(rs.getDate("date_of_allocation"));
					bean1.setTrx_by(rs.getString("trx_by"));
					bean1.setChk_dd_date(rs.getDate("chk_dd_date"));
					bean1.setChk_dd_no(rs.getInt("chk_dd_no"));
					bean1.setShare_amt(rs.getDouble("share_amt"));
					bean1.setQnt_of_share(rs.getInt("qnt_of_share"));
					bean1.setShare_no_form(rs.getInt("share_no_form"));
					bean1.setShare_no_to(rs.getInt("share_no_to"));				
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setBatch_no(rs.getInt("batch_no"));
					bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
					bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
					bean1.setStatus(rs.getString("status"));
					
					

				}
				}catch (Exception e) {
					System.out.print("MemberShareDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean1;
			}
	//-----------------------------------------------------------------------------------------
		public ArrayList<MemberShareBean> getMemberShareByMemberId(int  ad_member_id) {
			ArrayList<MemberShareBean> bean = new ArrayList<MemberShareBean>();
			
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from ad_member_share " +
					" where ad_member_id=? AND isactive=? AND status != ? " +
					" order by ad_member_share_id";
			try {
				ps = con.prepareStatement(query);
				
				ps.setInt(1, ad_member_id);
				ps.setBoolean(2, true);
				ps.setString(3, "Paid");
				//System.out.print(ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					MemberShareBean bean1 = new MemberShareBean();
					bean1.setAd_member_share_id(rs.getInt("ad_member_share_id"));
					bean1.setAd_member_id(rs.getInt("ad_member_id"));		
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setDate_of_allocation(rs.getDate("date_of_allocation"));
					bean1.setTrx_by(rs.getString("trx_by"));
					bean1.setChk_dd_date(rs.getDate("chk_dd_date"));
					bean1.setChk_dd_no(rs.getInt("chk_dd_no"));
					bean1.setShare_amt(rs.getDouble("share_amt"));
					bean1.setQnt_of_share(rs.getInt("qnt_of_share"));
					bean1.setShare_no_form(rs.getInt("share_no_form"));
					bean1.setShare_no_to(rs.getInt("share_no_to"));				
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setBatch_no(rs.getInt("batch_no"));
					bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
					bean1.setStatus(rs.getString("status"));
					bean.add(bean1);
				}
				}catch (Exception e) {
					System.out.print("MemberShareDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
			
			//System.out.println(bean.size());
				return bean;
				
			}
//------------------------------------------------------------------------------------------	
	//-----------------------------------------------------------------------------------------
		public MemberShareBean getMemberShareBatchshareNo(int  ad_member_share_id) {
			MemberShareBean bean1 = null;
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select share_no_to,batch_no from ad_member_share where ad_member_share_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1,ad_member_share_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1=new MemberShareBean();
					bean1.setShare_no_to(rs.getInt("share_no_to"));
					bean1.setBatch_no(rs.getInt("batch_no"));		
					
				}
				}catch (Exception e) {
					System.out.print("MemberShareDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean1;
			}

	//------------------------------------------------------------------------------------------	
//--------------------------------------------------------------------------------------
	public ArrayList<MemberShareBean> getAllMemberShare() {
		ArrayList<MemberShareBean> bean = new ArrayList<MemberShareBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_member_share ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberShareBean bean1 = new MemberShareBean();
				bean1.setAd_member_share_id(rs.getInt("ad_member_share_id"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				
				bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setDate_of_allocation(rs.getDate("date_of_allocation"));
				bean1.setTrx_by(rs.getString("trx_by"));
				bean1.setChk_dd_date(rs.getDate("chk_dd_date"));
				bean1.setChk_dd_no(rs.getInt("chk_dd_no"));
				bean1.setShare_amt(rs.getDouble("share_amt"));
				bean1.setQnt_of_share(rs.getInt("qnt_of_share"));
				bean1.setShare_no_form(rs.getInt("share_no_form"));
				bean1.setShare_no_to(rs.getInt("share_no_to"));				
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setStatus(rs.getString("status"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("MemberShareDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	
	
	//--------------------------------------------------------------------------------------
	public MemberShareBean getMemberShareByVoucherId(int  ad_voucher_id) {
		MemberShareBean bean1 = new MemberShareBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select qnt_of_share,share_no_to,batch_no,share_no_form from ad_member_share where ad_voucher_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,ad_voucher_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setQnt_of_share(rs.getInt("qnt_of_share"));
				bean1.setShare_no_to(rs.getInt("share_no_to"));
				bean1.setBatch_no(rs.getInt("batch_no"));	
				bean1.setShare_no_form(rs.getInt("share_no_form"));	

			}
			}catch (Exception e) {
				System.out.print("MemberShareDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	
	//--------------------------------------------------------------------------------------
		public MemberShareBean getMemberShareByLoanId(int  loan_trx_id) {
			MemberShareBean bean1 = new MemberShareBean();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from ad_member_share where loan_trx_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1,loan_trx_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_member_share_id(rs.getInt("ad_member_share_id"));
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setDate_of_allocation(rs.getDate("date_of_allocation"));
					bean1.setTrx_by(rs.getString("trx_by"));
					bean1.setChk_dd_date(rs.getDate("chk_dd_date"));
					bean1.setChk_dd_no(rs.getInt("chk_dd_no"));
					bean1.setShare_amt(rs.getDouble("share_amt"));
					bean1.setQnt_of_share(rs.getInt("qnt_of_share"));
					bean1.setShare_no_form(rs.getInt("share_no_form"));
					bean1.setShare_no_to(rs.getInt("share_no_to"));				
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setStatus(rs.getString("status"));
				}
				}catch (Exception e) {
					System.out.print("MemberShareDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean1;
			}
		
//---------------------------------------------------------------------------------------------

		public int updateMemberShare(MemberShareBean bean){
			int i=0;
			PreparedStatement ps =null;
			try {

				String query = "UPDATE ad_member_share " +
						"  SET ad_member_id=?, updated=?, updatedby=?, date_of_allocation=?, trx_by=?, " +
						" share_amt=?, qnt_of_share=?, share_no_form=?, share_no_to=?, " +
						" isactive=?,  batch_no=?, ad_voucher_id=?, loan_trx_id=?, status=? WHERE ad_member_share_id=? ";
				ps = con.prepareStatement(query);
				ps.setInt(1, bean.getAd_member_id());
				ps.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
				ps.setInt(3, bean.getUpdatedby());
				ps.setDate(4, new java.sql.Date(bean.getDate_of_allocation().getTime()));
				ps.setString(5, bean.getTrx_by());
				ps.setDouble(6, bean.getShare_amt());
				ps.setInt(7, bean.getQnt_of_share());			
				ps.setInt(8, bean.getShare_no_form());
				ps.setInt(9, bean.getShare_no_to());
				ps.setBoolean(10, bean.isIsactive());
				ps.setInt(11, bean.getBatch_no());	
				ps.setInt(12, bean.getAd_voucher_id());
				ps.setInt(13, bean.getLoan_trx_id());
				ps.setString(14, bean.getStatus());
				ps.setInt(15, bean.getAd_member_share_id());
				System.out.print(ps);
				i=ps.executeUpdate();
			
		}catch (Exception e) {
			System.out.print("MemberShareDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}
		//---------------------------------------------------------------------------------------------

				public int updateMemberPaidShareStatus(MemberShareBean bean){
					int i=0;
					PreparedStatement ps =null;
					try {

						String query = "UPDATE ad_member_share " +
								"SET  updated=?, updatedby=?," +
								"isactive=?,status=?,pay_type=?,payment_date=?  WHERE ad_member_id=?";
						ps = con.prepareStatement(query);
						ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
						ps.setInt(2, bean.getUpdatedby());
						ps.setBoolean(3, bean.isIsactive());
						ps.setString(4, bean.getStatus());
						ps.setString(5, bean.getPay_type());
						ps.setDate(6,new java.sql.Date(bean.getPayment_date().getTime()));
						ps.setInt(7,bean.getAd_member_id());
						System.out.print(ps);
						i=ps.executeUpdate();
					
				}catch (Exception e) {
					System.out.print("MemberShareDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return i;
			}		
		
//----------------------------------------------------------------------------------------------
	public void updateMemberShareStatusbyvid(MemberShareBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_member_share " +
					"SET  updated=?, updatedby=?," +
					"isactive=? , status=? WHERE ad_voucher_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, "Approved");
			ps.setInt(5, bean.getAd_voucher_id());
			
			
			
			System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("MemberShareDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//---------------------------------------------------------------------------------------------

	public int updateMemberShareStatus(MemberShareBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_member_share " +
					"SET  updated=?, updatedby=?," +
					"isactive=?,ad_voucher_id=?,status=?  WHERE ad_member_share_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setInt(4, bean.getAd_voucher_id());
			ps.setString(5, bean.getStatus());
			ps.setInt(6,bean.getAd_member_share_id());
			
			
			
			System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("MemberShareDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
	
	
//----------------------------------------------------------------------------------------------
	public int deleteMemberShare(MemberShareBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_member_share WHERE ad_member_share_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_share_id());
			i=ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("MemberShareDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}
/////-----------------------------------------------------------------------------
	public int deleteMemberShareByLoanId(int loan_trx_id){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_member_share WHERE loan_trx_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, loan_trx_id);
			i=ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("MemberShareDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}
	//----------------------------------------------------------------------------------------------
		public int deleteMemberShareByVoucherId(int ad_voucher_id){
			int i=0;
			PreparedStatement ps =null;
			try{
				String query="DELETE FROM ad_member_share WHERE ad_voucher_id=?";
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_voucher_id);
				i=ps.executeUpdate();
			
				}catch (Exception e) {
				System.out.print("MemberShareDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return i;
		}
	/////-----------------------------------------------------------------------------
	public MemberShareBean getMemberShareMaxId() {
		MemberShareBean bean1 = new MemberShareBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select max(ad_member_share_id) as ad_member_share_id from ad_member_share ";
		try {
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_member_share_id(rs.getInt("ad_member_share_id"));
				
				
			}
		}catch (Exception e) {
			System.out.print("MemberShareDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
/////-----------------------------------------------------------------------------
	public MemberShareBean getAllShareAmtByMemberId(int ad_member_id) {
		MemberShareBean bean1 = new MemberShareBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select SUM(ams.share_amt) as share_amt, sum(ams.qnt_of_share) as qnt_of_share  from ad_member_share ams " +
				"LEFT JOIN ad_voucher av ON av.ad_voucher_id=ams.ad_voucher_id and av.status='Approved' " +
				" where ams.ad_member_id=? AND ams.status!='Paid'  ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,ad_member_id);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setShare_amt(rs.getDouble("share_amt"));
				bean1.setQnt_of_share(rs.getInt("qnt_of_share"));
				
				
			}
		}catch (Exception e) {
			System.out.print("MemberShareDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
/////-----------------------------------------------------------------------------
	public double getAllShareBalanceByMemberId(int ad_member_id) {
		double balance =0.0;
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select SUM(ams.share_amt) as share_amt  from ad_member_share ams " +
				"LEFT JOIN ad_voucher av ON ams.ad_voucher_id=av.ad_voucher_id  " +
				" where ams.ad_member_id=? AND ams.status!='Paid' and av.status='Approved' ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,ad_member_id);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				balance=rs.getDouble("share_amt");
				
				
				
			}
		}catch (Exception e) {
			System.out.print("MemberShareDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return balance;
	}
		/////-----------------------------------------------------------------------------
			public ArrayList<MemberShareBean> getShareBalByMemberIdAndDate(int ad_member_id,Date from,Date to) {
				ArrayList<MemberShareBean> bean = new ArrayList<MemberShareBean>();
				PreparedStatement ps =null;
				ResultSet rs=null;
				String query = "SELECT date_of_allocation , share_amt from ad_member_share " +
						"	where date_of_allocation >=? AND date_of_allocation <= ? AND share_amt != 0.0 AND status!='Cancel' AND status!='Paid' AND ad_member_id=? ";
				
	
				try {
					ps = con.prepareStatement(query);
					ps.setDate(1,new java.sql.Date(from.getTime()));
					ps.setDate(2,new java.sql.Date(to.getTime()));
					ps.setInt(3,ad_member_id);
					System.out.println(ps);
					rs = ps.executeQuery();
					while (rs.next()) {
						MemberShareBean bean1=new MemberShareBean();
						bean1.setShare_amt(rs.getDouble("share_amt"));
						bean1.setDate_of_allocation(rs.getDate("date_of_allocation"));
						bean.add(bean1);
					}
				}catch (Exception e) {
					System.out.print("MemberShareDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}
			
/////-----------------------------------------------------------------------------
	public ArrayList<MemberShareBean> getShareBalByMemberId(int ad_member_id,java.util.Date date) {
		ArrayList<MemberShareBean> bean = new ArrayList<MemberShareBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT sh.ad_member_id,sh.share_amt ,sh.qnt_of_share " +
				" FROM ad_member m " +
				" LEFT JOIN ad_member_share s ON m.ad_member_id=s.ad_member_id " +
				" LEFT JOIN (SELECT SUM(sh.share_amt) as share_amt, sum(sh.qnt_of_share) as qnt_of_share,sh.ad_member_id " +
				"      FROM ad_member_share sh WHERE sh.date_of_allocation<? AND sh.status!='Cancel' AND sh.status!='Paid' " +
				"      GROUP BY sh.ad_member_id) sh ON m.ad_member_id=sh.ad_member_id " +
				" WHERE m.ad_member_id=?";
		try {
			ps = con.prepareStatement(query);
			ps.setDate(1,new java.sql.Date(date.getTime()));
			ps.setInt(2,ad_member_id);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberShareBean bean1=new MemberShareBean();
				bean1.setShare_amt(rs.getDouble("share_amt"));
				bean1.setQnt_of_share(rs.getInt("qnt_of_share"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean.add(bean1);
			}
		}catch (Exception e) {
			System.out.print("MemberShareDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	//--------------------------------------------------------------------------------------
		public ArrayList<ShareViewBean> getAllMemberShareDetail() {
			ArrayList<ShareViewBean> bean = new ArrayList<ShareViewBean>();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "SELECT ad_member_share_id, created, createdby, updatedby, ad_society_no, " +
					"name, date_of_allocation, share_trx_by, chk_dd_date, chk_dd_no, share_amt, qnt_of_share, " +
					"share_no_form, share_no_to, isactive, updated, batch_no, status  FROM share_detail order by date_of_allocation desc";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					ShareViewBean bean1 = new ShareViewBean();
					bean1.setAd_member_share_id(rs.getInt("ad_member_share_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setDate_of_allocation(rs.getDate("date_of_allocation"));
					bean1.setAd_society_no(rs.getInt("ad_society_no"));
					bean1.setName(rs.getString("name"));
					bean1.setShare_trx_by(rs.getString("share_trx_by"));
					bean1.setChk_dd_date(rs.getDate("chk_dd_date"));
					bean1.setChk_dd_no(rs.getInt("chk_dd_no"));
					bean1.setShare_amt(rs.getDouble("share_amt"));
					bean1.setQnt_of_share(rs.getInt("qnt_of_share"));
					bean1.setShare_no_form(rs.getInt("share_no_form"));
					bean1.setShare_no_to(rs.getInt("share_no_to"));		
					bean1.setBatch_no(rs.getInt("batch_no"));
					bean1.setStatus(rs.getString("status"));
					
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("MemberShareDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
		
		
		//--------------------------------------------------------------------------------------
				public ArrayList<ShareViewBean> getAllMemberShareDetail(java.util.Date from , java.util.Date to) {
					ArrayList<ShareViewBean> bean = new ArrayList<ShareViewBean>();
					PreparedStatement ps =null;
					ResultSet rs=null;
					String query = "SELECT ad_member_share_id, created, createdby, updatedby, ad_society_no, " +
							"name, date_of_allocation, share_trx_by, chk_dd_date, chk_dd_no, share_amt, qnt_of_share, " +
							"share_no_form, share_no_to, isactive, updated, batch_no,status  FROM share_detail " +
							"where date_of_allocation between ? AND ? order by date_of_allocation desc";
					try {
						ps = con.prepareStatement(query);
						ps.setDate(1, new java.sql.Date(from.getTime()));
						ps.setDate(2, new java.sql.Date(to.getTime()));
						rs = ps.executeQuery();
						while (rs.next()) {
							ShareViewBean bean1 = new ShareViewBean();
							bean1.setAd_member_share_id(rs.getInt("ad_member_share_id"));
							bean1.setCreated(rs.getDate("created"));
							bean1.setCreatedby(rs.getInt("createdby"));
							bean1.setUpdated(rs.getDate("updated"));
							bean1.setUpdatedby(rs.getInt("updatedby"));
							bean1.setIsactive(rs.getBoolean("isactive"));
							bean1.setDate_of_allocation(rs.getDate("date_of_allocation"));
							bean1.setAd_society_no(rs.getInt("ad_society_no"));
							bean1.setName(rs.getString("name"));
							bean1.setShare_trx_by(rs.getString("share_trx_by"));
							bean1.setChk_dd_date(rs.getDate("chk_dd_date"));
							bean1.setChk_dd_no(rs.getInt("chk_dd_no"));
							bean1.setShare_amt(rs.getDouble("share_amt"));
							bean1.setQnt_of_share(rs.getInt("qnt_of_share"));
							bean1.setShare_no_form(rs.getInt("share_no_form"));
							bean1.setShare_no_to(rs.getInt("share_no_to"));		
							bean1.setBatch_no(rs.getInt("batch_no"));
							bean1.setStatus(rs.getString("status"));
							
							bean.add(bean1);

						}
					}catch (Exception e) {
						System.out.print("MemberShareDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return bean;
				}
				//--------------------------------------------------------------------------------------
				public ArrayList<ShareViewBean> getAllMemberShareDetailByLoanId(int loan_trx_id) {
					ArrayList<ShareViewBean> bean = new ArrayList<ShareViewBean>();
					PreparedStatement ps =null;
					ResultSet rs=null;
					String query = "SELECT ad_member_share_id, created, createdby, updatedby, ad_society_no, " +
							"name, date_of_allocation, share_trx_by, chk_dd_date, chk_dd_no, share_amt, qnt_of_share, " +
							"share_no_form, share_no_to, isactive, updated, batch_no,loan_trx_id, status  FROM share_detail " +
							"where loan_trx_id= ? order by date_of_allocation desc";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, loan_trx_id);
						rs = ps.executeQuery();
						while (rs.next()) {
							ShareViewBean bean1 = new ShareViewBean();
							bean1.setAd_member_share_id(rs.getInt("ad_member_share_id"));
							bean1.setCreated(rs.getDate("created"));
							bean1.setCreatedby(rs.getInt("createdby"));
							bean1.setUpdated(rs.getDate("updated"));
							bean1.setUpdatedby(rs.getInt("updatedby"));
							bean1.setIsactive(rs.getBoolean("isactive"));
							bean1.setDate_of_allocation(rs.getDate("date_of_allocation"));
							bean1.setAd_society_no(rs.getInt("ad_society_no"));
							bean1.setName(rs.getString("name"));
							bean1.setShare_trx_by(rs.getString("share_trx_by"));
							bean1.setChk_dd_date(rs.getDate("chk_dd_date"));
							bean1.setChk_dd_no(rs.getInt("chk_dd_no"));
							bean1.setShare_amt(rs.getDouble("share_amt"));
							bean1.setQnt_of_share(rs.getInt("qnt_of_share"));
							bean1.setShare_no_form(rs.getInt("share_no_form"));
							bean1.setShare_no_to(rs.getInt("share_no_to"));		
							bean1.setBatch_no(rs.getInt("batch_no"));
							bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
							bean1.setStatus(rs.getString("status"));
							bean.add(bean1);

						}
					}catch (Exception e) {
						System.out.print("MemberShareDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return bean;
				}
		



}
