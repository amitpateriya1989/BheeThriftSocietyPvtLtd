package Model.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.BankBranchBean;
import Model.Bean.LoanTrxBean;
import Model.Bean.LoanTrxDetailBean;
import Model.Bean.MemberStatusBean;

public class LoanTrxDetailDao {
	private Connection con = null;

	public LoanTrxDetailDao() {
		
			con = DBConnection.getConnection();
			
	}

	public int addLoanTrxDetail(LoanTrxDetailBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_loan_trx( " +
					" created, createdby, updated, updatedby, isactive, ad_member_id, loan_trx_id, trx_date, deposit_amt, " +
					" interest_amt, balance_amt, ad_emp_id, ad_voucher_id,excess_amt ) " +
					" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);" ;
					
			ps = con.prepareStatement(query);
			ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setInt(6, bean.getAd_member_id());
			ps.setInt(7, bean.getLoan_trx_id());
			ps.setDate(8,new java.sql.Date(bean.getTrx_date().getTime()));
			ps.setDouble(9, bean.getDeposit_amt());
			
			ps.setDouble(10, bean.getInterest_amt());
			ps.setDouble(11, bean.getBalance_amt());
			ps.setInt(12, bean.getAd_employee_id());
		 	ps.setInt(13, bean.getAd_voucher_id());
		 	ps.setDouble(14, bean.getExcess_amt());
		 	record=ps.executeUpdate();
			//return  ps.getGeneratedKeys();
			
			
		} catch (Exception e) {
			System.out.print("LoanTrxDetailDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public LoanTrxDetailBean getLoanTrxDetailById(LoanTrxDetailBean bean) {
		LoanTrxDetailBean bean1 = new LoanTrxDetailBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_loan_trx where ad_member_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_loan_trx_id(rs.getInt("ad_loan_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setDeposit_amt(rs.getDouble("deposit_amt"));
				
				bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
				bean1.setInterest_amt(rs.getDouble("interest_amt"));
				bean1.setBalance_amt(rs.getDouble("balance_amt"));
				bean1.setExcess_amt(rs.getDouble("excess_amt"));
				
				

			}
			}catch (Exception e) {
				System.out.print("LoanTrxDetailDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public LoanTrxDetailBean getLoanTrxDetailById(int ad_member_id) {
		LoanTrxDetailBean bean1 = new LoanTrxDetailBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_loan_trx where ad_member_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_loan_trx_id(rs.getInt("ad_loan_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setDeposit_amt(rs.getDouble("deposit_amt"));				
				bean1.setInterest_amt(rs.getDouble("interest_amt"));
				bean1.setBalance_amt(rs.getDouble("balance_amt"));
				bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
				bean1.setExcess_amt(rs.getDouble("excess_amt"));
				
			}
		}catch (Exception e) {
			System.out.print("LoanTrxDetailDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<LoanTrxDetailBean> getLoanTrxDetailByLoantrxId(int loan_trx_id) {
		ArrayList<LoanTrxDetailBean> bean=new ArrayList<LoanTrxDetailBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select t.* from ad_loan_trx t "
				+ " LEFT JOIN loan_trx l ON t.loan_trx_id=l.loan_trx_id AND l.status='Open' "
				+ " LEFT JOIN ad_voucher v ON v.ad_voucher_id=t.ad_voucher_id "
				+ " where t.loan_trx_id=? AND v.status='Approved' order by trx_date ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, loan_trx_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				LoanTrxDetailBean bean1 = new LoanTrxDetailBean();
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_loan_trx_id(rs.getInt("ad_loan_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setDeposit_amt(rs.getDouble("deposit_amt"));				
				bean1.setInterest_amt(rs.getDouble("interest_amt"));
				bean1.setBalance_amt(rs.getDouble("balance_amt"));
				bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
				bean1.setExcess_amt(rs.getDouble("excess_amt"));
				bean.add(bean1);
			}
		}catch (Exception e) {
			System.out.print("LoanTrxDetailDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	//--------------------------------------------------------------------------------------
		public ArrayList<LoanTrxDetailBean> getLoanTrxDetailByLoantrxId(int loan_trx_id,java.util.Date trxdate) {
			ArrayList<LoanTrxDetailBean> bean=new ArrayList<LoanTrxDetailBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select t.* from ad_loan_trx t "
					+ " LEFT JOIN loan_trx l ON t.loan_trx_id=l.loan_trx_id AND l.status='Open' "
					+ " LEFT JOIN ad_voucher v ON v.ad_voucher_id=t.ad_voucher_id "
					+ " where t.loan_trx_id=? AND v.status='Approved' AND t.trx_date>=? order by trx_date ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, loan_trx_id);
				ps.setDate(2,new java.sql.Date(trxdate.getTime()));
				rs = ps.executeQuery();
				while (rs.next()) {
					
					LoanTrxDetailBean bean1 = new LoanTrxDetailBean();
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setAd_loan_trx_id(rs.getInt("ad_loan_trx_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setTrx_date(rs.getDate("trx_date"));
					bean1.setDeposit_amt(rs.getDouble("deposit_amt"));				
					bean1.setInterest_amt(rs.getDouble("interest_amt"));
					bean1.setBalance_amt(rs.getDouble("balance_amt"));
					bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
					bean1.setExcess_amt(rs.getDouble("excess_amt"));
					bean.add(bean1);
				}
			}catch (Exception e) {
				System.out.print("LoanTrxDetailDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
		
		
//--------------------------------------------------------------------------------------
	public ArrayList<LoanTrxDetailBean> getAllLoanTrxDetail() {
		ArrayList<LoanTrxDetailBean> bean = new ArrayList<LoanTrxDetailBean>();
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_loan_trx ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				LoanTrxDetailBean bean1 = new LoanTrxDetailBean();
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_loan_trx_id(rs.getInt("ad_loan_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setDeposit_amt(rs.getDouble("deposit_amt"));
				bean1.setExcess_amt(rs.getDouble("excess_amt"));
				bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
				bean1.setInterest_amt(rs.getDouble("interest_amt"));
				bean1.setBalance_amt(rs.getDouble("balance_amt"));
				//bean1.setAddress(new MemberAddressDao().getMemberAddressByMemberId(rs.getInt("ad_member_id")));
				bean.add(bean1);
				//System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("LoanTrxDetailDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	//--------------------------------------------------------------------------------------
		public LoanTrxDetailBean getAllLoanForFinalPay(int ad_member_id,int loan_trx_id) {
			LoanTrxDetailBean bean1 = new LoanTrxDetailBean();
			
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "SELECT MAX(l.ad_loan_trx_id) as ad_loan_trx_id ,l.balance_amt,l.loan_trx_id FROM ad_loan_trx l " +
					" LEFT JOIN loan_trx ml ON l.loan_trx_id=ml.loan_trx_id " +
					" LEFT JOIN ad_voucher v ON l.ad_voucher_id=v.ad_voucher_id " +
					" WHERE l.ad_member_id=? and l.loan_trx_id=? AND v.status='Pending' " +
					" GROUP BY l.ad_loan_trx_id,l.balance_amt,l.loan_trx_id ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_member_id);
				ps.setInt(2, loan_trx_id);
				System.out.println(ps);
				rs = ps.executeQuery();
				
				while (rs.next()) {
					
					
					bean1.setAd_loan_trx_id(rs.getInt("ad_loan_trx_id"));
					bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
					bean1.setBalance_amt(rs.getDouble("balance_amt"));
					
				}
			}catch (Exception e) {
				System.out.print("LoanTrxDetailDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
		
		
	//--------------------------------------------------------------------------------------
		public ArrayList<LoanTrxDetailBean> getAllActiveLoanTrxDetail() {
			ArrayList<LoanTrxDetailBean> bean = new ArrayList<LoanTrxDetailBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * form ad_loan_trx";
			try {
				ps = con.prepareStatement(query);
				//System.out.print(ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					LoanTrxDetailBean bean1 = new LoanTrxDetailBean();
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setAd_loan_trx_id(rs.getInt("ad_loan_trx_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setTrx_date(rs.getDate("trx_date"));
					bean1.setDeposit_amt(rs.getDouble("deposit_amt"));
					bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
					bean1.setExcess_amt(rs.getDouble("excess_amt"));
					bean1.setInterest_amt(rs.getDouble("interest_amt"));
					bean1.setBalance_amt(rs.getDouble("balance_amt"));
					bean.add(bean1);
					

				}
			}catch (Exception e) {
				System.out.print("LoanTrxDetailDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}	
//----------------------------------------------------------------------------------------------
	public void updateLoanTrxDetail(int ad_voucher_id, int loan_trx_id){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_loan_trx SET  ad_voucher_id=?  WHERE loan_trx_id=?";
			ps = con.prepareStatement(query);
			System.out.print(ps);
			ps.setInt(1, ad_voucher_id);
			ps.setInt(2, loan_trx_id);
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("LoanTrxDetailDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
	//----------------------------------------------------------------------------------------------
		public int updateLoanTrxDetail(LoanTrxDetailBean bean){
			int i=0;
			PreparedStatement ps=null;
			try {

				String query = "UPDATE ad_loan_trx SET updated=?,updatedby=?, ad_voucher_id=?  WHERE loan_trx_id=?";
				ps = con.prepareStatement(query);
				System.out.print(ps);
				ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(2, bean.getUpdatedby());
				ps.setInt(3, bean.getAd_voucher_id());
				ps.setInt(4, bean.getLoan_trx_id());
				
				
				//System.out.print(ps);
				i=ps.executeUpdate();
			
		}catch (Exception e) {
			System.out.print("LoanTrxDetailDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}
//----------------------------------------------------------------------------------------------
	public void deleteLoanTrxDetail(int ad_voucher_id){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_loan_trx WHERE ad_voucher_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("LoanTrxDetailDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
	
	
	//----------------------------------------------------------------------------------------------
		@SuppressWarnings("finally")
		public int getLoanTrxDetailMaxId(){
			int result=0;
			PreparedStatement ps=null;
			ResultSet rs=null;
			try{
				String query="select ad_loan_trx_id from ad_loan_trx where ad_loan_trx_id=(select Max(ad_loan_trx_id) from ad_loan_trx)";
				ps=con.prepareStatement(query);
				
				rs=ps.executeQuery();
				if(rs.next()){
					result=rs.getInt("ad_loan_trx_id");
				}
			
				}catch (Exception e) {
				System.out.print("LoanTrxDetailDao:-error in try Block");
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
			public int getMemberNo(){
				int result=0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="select max(ad_loan_trx_id) as ad_loan_trx_id from ad_loan_trx where ad_member_id=?";
					ps=con.prepareStatement(query);
					rs=ps.executeQuery();
					if(rs.next()){
						result=rs.getInt("ad_loan_trx_id");
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
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
			public int getMemberNo(int ad_member_id){
				int result=0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="select max(ad_loan_trx_id) as ad_loan_trx_id from ad_loan_trx where ad_member_id=? AND isactive='TRUE'";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_member_id);
					rs=ps.executeQuery();
					if(rs.next()){
						result=rs.getInt("ad_loan_trx_id");
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return result;
				}
				
			//-------------------------------------
			public LoanTrxDetailBean getOpenLoanBal(int ad_member_id, int loan_trx_id){
				LoanTrxDetailBean bean=new LoanTrxDetailBean();
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="select *  from ad_loan_trx " +
							" where ad_loan_trx_id=(SELECT max(ad_loan_trx_id) " +
							" FROM ad_loan_trx where loan_trx_id=? AND ad_member_id=?  and isactive='TRUE')";
					ps=con.prepareStatement(query);
					
					ps.setInt(1, loan_trx_id);
					ps.setInt(2, ad_member_id);
					//System.out.println(ps);
					rs=ps.executeQuery();
					if(rs.next()){
						
						bean.setBalance_amt(rs.getDouble("balance_amt"));
						bean.setTrx_date(rs.getDate("trx_date"));
						bean.setLoan_trx_id(rs.getInt("loan_trx_id"));
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return bean;
				}
			
			//--------------------------------------------------------------------------------------
			
			//-------------------------------------
			public LoanTrxDetailBean getLastInterestPosting(int ad_member_id, int loan_trx_id){
				LoanTrxDetailBean bean =null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="SELECT MAX(l.trx_date) AS trx_date,l.balance_amt,l.loan_trx_id FROM ad_loan_trx l " +
							" LEFT JOIN loan_trx ml ON l.loan_trx_id=ml.loan_trx_id AND ml.isactive='TRUE' AND ml.status='Open' " +
							" WHERE l.ad_member_id=? and l.loan_trx_id=?  AND (l.interest_amt>0.0 OR l.balance_amt>0.0) " +
							" GROUP BY l.balance_amt,l.loan_trx_id order by l.loan_trx_id ";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_member_id);
					ps.setInt(2, loan_trx_id);
					System.out.println(ps);
					rs=ps.executeQuery();
					if(rs.next()){
						bean=new LoanTrxDetailBean();
						bean.setBalance_amt(rs.getDouble("balance_amt"));
						bean.setTrx_date(rs.getDate("trx_date"));
						bean.setLoan_trx_id(rs.getInt("loan_trx_id"));
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return bean;
				}
			
			
			//-------------------------------------
			public LoanTrxDetailBean getLastLoanPosting(int ad_member_id, int loan_trx_id){
				LoanTrxDetailBean bean =null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="SELECT MAX(l.trx_date) AS trx_date,l.balance_amt,l.loan_trx_id FROM ad_loan_trx l "
							+ " LEFT JOIN loan_trx ml ON l.loan_trx_id=ml.loan_trx_id "
							+ " WHERE l.ad_member_id=? and l.loan_trx_id=? AND ml.isactive='TRUE' "
							+ " AND ml.status='Open'   GROUP BY l.balance_amt,l.loan_trx_id ";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_member_id);
					ps.setInt(2, loan_trx_id);
					//System.out.println(ps);
					rs=ps.executeQuery();
					if(rs.next()){
						bean=new LoanTrxDetailBean();
						bean.setBalance_amt(rs.getDouble("balance_amt"));
						bean.setTrx_date(rs.getDate("trx_date"));
						bean.setLoan_trx_id(rs.getInt("loan_trx_id"));
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return bean;
				}
			
			
			
			//-------------------------------------
			public double getOpenLoanBal(LoanTrxBean bean){
				double balance=0.0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="select sum(balance_amt) as balance_amt from ad_loan_trx where loan_trx_id=? AND ad_member_id=? and isactive='true' " +
							" and balance_amt>0 and ad_loan_trx_id=(SELECT MAX(ad_loan_trx_id) "
							+ " from ad_loan_trx where loan_trx_id=? ) ";
					ps=con.prepareStatement(query);
					ps.setInt(1, bean.getLoan_trx_id());
					ps.setInt(2, bean.getAd_member_id());
					ps.setInt(3, bean.getLoan_trx_id());
					//System.out.println(ps);
					rs=ps.executeQuery();
					if(rs.next()){
						
						balance=rs.getDouble("balance_amt");
						
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return balance;
				}

			
			
			//-------------------------------------
			public LoanTrxDetailBean getOpenLoanBal(int ad_member_id){
				LoanTrxDetailBean bean =new LoanTrxDetailBean();
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="select sum(balance_amt) as balance_amt from ad_loan_trx where ad_member_id=? and isactive='true'";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_member_id);
					
					rs=ps.executeQuery();
					if(rs.next()){
						
						bean.setBalance_amt(rs.getDouble("balance_amt"));
						/*bean.setTrx_date(rs.getDate("trx_date"));
						bean.setLoan_trx_id(rs.getInt("loan_trx_id"));*/
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return bean;
				}
			
			//--------------------------------------------------------------------------------------
			public ArrayList<LoanTrxDetailBean> getOpenLoanTrxDetailById(int ad_member_id) {
				ArrayList<LoanTrxDetailBean> bean = new ArrayList<LoanTrxDetailBean>();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "SELECT *  FROM loan_detail " +
						"where isactive=? AND status= ? AND ad_member_id=?  order by loan_trx_id asc";
				try {
					ps = con.prepareStatement(query);
					ps.setBoolean(1, true);
					ps.setString(2, "Open");
					ps.setInt(3, ad_member_id);
					rs = ps.executeQuery();
					while (rs.next()) {
						LoanTrxDetailBean bean1 = new LoanTrxDetailBean();
						bean1.setAd_member_id(rs.getInt("ad_member_id"));
						
						
						bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
						
						
						bean.add(bean1);
						

					}
				}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}	
			//----------------------------------------------------------------------------------------------
			
			public void calculateLoanInterestAsOnDate(Date date,int ad_voucher_id, int createdby,int updatedby){				
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="select getallloan(?,?,?,?)";
					ps=con.prepareStatement(query);
					ps.setDate(1, date);
					ps.setInt(2, ad_voucher_id);
					ps.setInt(3, createdby);
					ps.setInt(4, updatedby);
					rs=ps.executeQuery();
					if(rs.next()){
						
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					
				}
	//----------------------------------------------------------------------------------------------
			public double TotalinterestOnDAte(int  ad_voucher_id){	
				double result=0.0; 
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="select sum(interest_amt) as interest_amt from ad_loan_trx where ad_voucher_id=? ";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_voucher_id);
					rs=ps.executeQuery();
					if(rs.next()){
						result=rs.getDouble("interest_amt");
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return result;
				}
			//--------------------------------------------
			//-------------------------------------
			public LoanTrxDetailBean getLoanOpeningDate(int ad_member_id, int loan_trx_id){
				LoanTrxDetailBean bean =null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query=" select trx_date,balance_amt,l.loan_trx_id from ad_loan_trx l " +
							" LEFT JOIN loan_trx ml ON l.loan_trx_id=ml.loan_trx_id " +
							" where ad_loan_trx_id=(SELECT MIN(ad_loan_trx_id) from ad_loan_trx where loan_trx_id=?) " +
							" AND l.ad_member_id=? and l.loan_trx_id=? AND ml.isactive='true' AND ml.status='Open' " +
							" AND l.balance_amt>0 ";
					ps=con.prepareStatement(query);
					ps.setInt(1, loan_trx_id);
					ps.setInt(2, ad_member_id);
					ps.setInt(3, loan_trx_id);
					System.out.println(ps);
					rs=ps.executeQuery();
					if(rs.next()){
						bean=new LoanTrxDetailBean();
						bean.setTrx_date(rs.getDate("trx_date"));
						bean.setLoan_trx_id(rs.getInt("loan_trx_id"));
						bean.setBalance_amt(rs.getDouble("balance_amt"));
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return bean;
				}
			//-------------------------------------
			public LoanTrxDetailBean getLoanOpeningDateForEmployee(int ad_employee_id, int loan_trx_id){
				LoanTrxDetailBean bean =null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query=" select trx_date,balance_amt,l.loan_trx_id from ad_loan_trx l " +
							" LEFT JOIN loan_trx ml ON l.loan_trx_id=ml.loan_trx_id " +
							" where ad_loan_trx_id=(SELECT MIN(ad_loan_trx_id) from ad_loan_trx where loan_trx_id=?) " +
							" AND l.ad_emp_id=? and l.loan_trx_id=? AND ml.isactive='true' AND ml.status='Open' " +
							" AND l.balance_amt>0 ";
					ps=con.prepareStatement(query);
					ps.setInt(1, loan_trx_id);
					ps.setInt(2, ad_employee_id);
					ps.setInt(3, loan_trx_id);
					System.out.println(ps);
					rs=ps.executeQuery();
					if(rs.next()){
						bean=new LoanTrxDetailBean();
						bean.setTrx_date(rs.getDate("trx_date"));
						bean.setLoan_trx_id(rs.getInt("loan_trx_id"));
						bean.setBalance_amt(rs.getDouble("balance_amt"));
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return bean;
				}
			
			//-------------------------------------
			public LoanTrxDetailBean getLastInterestPostingDate(int ad_member_id, int loan_trx_id){
				LoanTrxDetailBean bean =null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="SELECT l.trx_date,l.balance_amt,l.loan_trx_id FROM ad_loan_trx l " +
							" LEFT JOIN loan_trx ml ON l.loan_trx_id=ml.loan_trx_id " +
							" WHERE ad_loan_trx_id=(SELECT MAX(ad_loan_trx_id) from ad_loan_trx " +
							" where loan_trx_id=? AND interest_amt>0.0 ) AND l.ad_member_id=? " +
							" and l.loan_trx_id=? AND  ml.isactive='TRUE' AND ml.status='Open' ";
					ps=con.prepareStatement(query);
					ps.setInt(1, loan_trx_id);
					ps.setInt(2, ad_member_id);
					ps.setInt(3, loan_trx_id);
					System.out.println(ps);
					rs=ps.executeQuery();
					if(rs.next()){
						bean=new LoanTrxDetailBean();
						bean.setBalance_amt(rs.getDouble("balance_amt"));
						bean.setTrx_date(rs.getDate("trx_date"));
						bean.setLoan_trx_id(rs.getInt("loan_trx_id"));
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return bean;
				}
			
			
			
			//-------------------------------------
			//-------------------------------------
			public LoanTrxDetailBean getLastInterestPostingDateForEmployee(int ad_employee_id, int loan_trx_id){
				LoanTrxDetailBean bean =null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="SELECT l.trx_date,l.balance_amt,l.loan_trx_id FROM ad_loan_trx l " +
							" LEFT JOIN loan_trx ml ON l.loan_trx_id=ml.loan_trx_id " +
							" WHERE ad_loan_trx_id=(SELECT MAX(ad_loan_trx_id) from ad_loan_trx " +
							" where loan_trx_id=? AND interest_amt>0.0 ) AND l.ad_emp_id=? " +
							" and l.loan_trx_id=? AND  ml.isactive='TRUE' AND ml.status='Open' ";
					ps=con.prepareStatement(query);
					ps.setInt(1, loan_trx_id);
					ps.setInt(2, ad_employee_id);
					ps.setInt(3, loan_trx_id);
					System.out.println(ps);
					rs=ps.executeQuery();
					if(rs.next()){
						bean=new LoanTrxDetailBean();
						bean.setBalance_amt(rs.getDouble("balance_amt"));
						bean.setTrx_date(rs.getDate("trx_date"));
						bean.setLoan_trx_id(rs.getInt("loan_trx_id"));
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return bean;
				}
			
			
			
			//-------------------------------------
			//--------------------------------------------------------------------------------------
			public LoanTrxDetailBean getAllActiveLoanTrxDetailById(int ad_loan_trx_id) {
				LoanTrxDetailBean bean1 = null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "select * from ad_loan_trx where ad_loan_trx_id=?";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_loan_trx_id);
					//System.out.print(ps);
					rs = ps.executeQuery();
					while (rs.next()) {
						bean1 = new LoanTrxDetailBean();
						bean1.setAd_member_id(rs.getInt("ad_member_id"));
						bean1.setAd_loan_trx_id(rs.getInt("ad_loan_trx_id"));
						bean1.setCreated(rs.getDate("created"));
						bean1.setCreatedby(rs.getInt("createdby"));
						bean1.setUpdated(rs.getDate("updated"));
						bean1.setUpdatedby(rs.getInt("updatedby"));
						bean1.setIsactive(rs.getBoolean("isactive"));
						bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
						bean1.setIsactive(rs.getBoolean("isactive"));
						bean1.setTrx_date(rs.getDate("trx_date"));
						bean1.setDeposit_amt(rs.getDouble("deposit_amt"));
						bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
						bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
						bean1.setInterest_amt(rs.getDouble("interest_amt"));
						bean1.setBalance_amt(rs.getDouble("balance_amt"));
						bean1.setExcess_amt(rs.getDouble("excess_amt"));

					}
				}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean1;
			}
			
			//----------------------------------------------------------------------------------------------
			public int updateLoanTrx(LoanTrxDetailBean bean){
				int i=0;
				PreparedStatement ps=null;
				try {

					String query = "UPDATE ad_loan_trx " +
							" SET updated=?, updatedby=?, isactive=?,  trx_date=?, " +
							" deposit_amt=?, interest_amt=?, balance_amt=? " +
							" WHERE ad_loan_trx_id=?";
					ps = con.prepareStatement(query);
					
					ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
					ps.setInt(2, bean.getUpdatedby());
					ps.setBoolean(3, bean.isIsactive());
					ps.setDate(4,new java.sql.Date(bean.getTrx_date().getTime()));
					ps.setDouble(5, bean.getDeposit_amt());
					ps.setDouble(6, bean.getInterest_amt());
					ps.setDouble(7, bean.getBalance_amt());
				 	ps.setInt(8, bean.getAd_loan_trx_id());
				 	
				 	i=ps.executeUpdate();
				
			}catch (Exception e) {
				System.out.print("LoanTrxDetailDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return i;
		}
			//----------------------------------------------------------------------------------------------
			public int deleteLoanTrxByLoanId(int loan_trx_id){
				int i=0;
				PreparedStatement ps=null;
				try{
					String query="DELETE FROM ad_loan_trx WHERE loan_trx_id=?";
					ps = con.prepareStatement(query);
					ps.setInt(1, loan_trx_id);
					i=ps.executeUpdate();
				
					}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return i;
			}
			//----------------------------------------------------------------------------------------------
			public int deleteLoanTrxDetailById(int ad_loan_trx_id){
				int i=0;
				PreparedStatement ps=null;
				try{
					String query="DELETE FROM ad_loan_trx WHERE ad_loan_trx_id=?";
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_loan_trx_id);
					i=ps.executeUpdate();
				
					}catch (Exception e) {
					System.out.print("LoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return i;
			}
			
			}



