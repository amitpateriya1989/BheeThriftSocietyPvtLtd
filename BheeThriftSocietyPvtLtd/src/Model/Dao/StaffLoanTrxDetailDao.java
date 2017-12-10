package Model.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.BankBranchBean;
import Model.Bean.LoanTrxDetailBean;
import Model.Bean.MemberStatusBean;

public class StaffLoanTrxDetailDao {
	private Connection con = null;

	public StaffLoanTrxDetailDao() {
		
			con = DBConnection.getConnection();
			
	}

	public void addLoanTrxDetail(LoanTrxDetailBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_loan_trx( created, createdby, updated, updatedby, isactive, " +
					"            ad_emp_id, loan_trx_id, trx_date, deposit_amt,  " +
					"            interest_amt, balance_amt,ad_voucher_id  )" +
					"    VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);" ;
					
			ps = con.prepareStatement(query);
			ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setInt(6, bean.getAd_employee_id());
			ps.setInt(7, bean.getLoan_trx_id());
			ps.setDate(8,new java.sql.Date(bean.getTrx_date().getTime()));
			ps.setDouble(9, bean.getDeposit_amt());
			
			ps.setDouble(10, bean.getInterest_amt());
			ps.setDouble(11, bean.getBalance_amt());
		ps.setInt(12, bean.getAd_voucher_id());
		
			
			record=ps.executeUpdate();
			//return  ps.getGeneratedKeys();
			
			
		} catch (Exception e) {
			System.out.print("StaffLoanTrxDetailDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
//-----------------------------------------------------------------------------------------
	public LoanTrxDetailBean getLoanTrxDetailById(LoanTrxDetailBean bean) {
		LoanTrxDetailBean bean1 = new LoanTrxDetailBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_loan_trx where ad_emp_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_employee_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				
				bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
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
			
				
				

			}
			}catch (Exception e) {
				System.out.print("StaffLoanTrxDetailDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public LoanTrxDetailBean getLoanTrxDetailById(int ad_employee_id) {
		LoanTrxDetailBean bean1 = new LoanTrxDetailBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_loan_trx where ad_emp_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_employee_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
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
				
			}
		}catch (Exception e) {
			System.out.print("StaffLoanTrxDetailDao:-error in try Block");
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
		ArrayList<LoanTrxDetailBean> bean = new ArrayList<LoanTrxDetailBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_loan_trx where loan_trx_id=? order by trx_date ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, loan_trx_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				LoanTrxDetailBean bean1 = new LoanTrxDetailBean();
				bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
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
				bean.add(bean1);
			}
		}catch (Exception e) {
			System.out.print("StaffLoanTrxDetailDao:-error in try Block");
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
				bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
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
				//bean1.setAddress(new MemberAddressDao().getMemberAddressByMemberId(rs.getInt("ad_employee_id")));
				bean.add(bean1);
				System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("StaffLoanTrxDetailDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	
	//--------------------------------------------------------------------------------------
		public ArrayList<LoanTrxDetailBean> getAllActiveLoanTrxDetail() {
			ArrayList<LoanTrxDetailBean> bean = new ArrayList<LoanTrxDetailBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * form ad_loan_trx";
			try {
				ps = con.prepareStatement(query);
				System.out.print(ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					LoanTrxDetailBean bean1 = new LoanTrxDetailBean();
					bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
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
					bean.add(bean1);
					

				}
			}catch (Exception e) {
				System.out.print("StaffLoanTrxDetailDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}	
//----------------------------------------------------------------------------------------------
	public void updateLoanTrxDetail(LoanTrxDetailBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = " ";
			ps = con.prepareStatement(query);
			ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setInt(6, bean.getAd_employee_id());
			ps.setInt(7, bean.getLoan_trx_id());
			ps.setDate(8,new java.sql.Date(bean.getTrx_date().getTime()));
			ps.setDouble(9, bean.getDeposit_amt());
			
			ps.setDouble(10, bean.getInterest_amt());
			ps.setDouble(11, bean.getBalance_amt());
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("StaffLoanTrxDetailDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public void deleteLoanTrxDetail(LoanTrxDetailBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_employee WHERE ad_emp_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_employee_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("StaffLoanTrxDetailDao:-error in try Block");
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
				System.out.print("StaffLoanTrxDetailDao:-error in try Block");
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
					String query="select max(ad_loan_trx_id) as ad_loan_trx_id from ad_loan_trx where ad_employee_id=?";
					ps=con.prepareStatement(query);
					rs=ps.executeQuery();
					if(rs.next()){
						result=rs.getInt("ad_loan_trx_id");
					}
				
					}catch (Exception e) {
					System.out.print("StaffLoanTrxDetailDao:-error in try Block");
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
			public int getMemberNo(int ad_employee_id){
				int result=0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="select max(ad_loan_trx_id) as ad_loan_trx_id from ad_loan_trx where ad_emp_id=? AND isactive='TRUE'";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_employee_id);
					rs=ps.executeQuery();
					if(rs.next()){
						result=rs.getInt("ad_loan_trx_id");
					}
				
					}catch (Exception e) {
					System.out.print("StaffLoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return result;
				}
				
			//-------------------------------------
			public LoanTrxDetailBean getOpenLoanBal(int ad_employee_id){
				LoanTrxDetailBean bean = new LoanTrxDetailBean();
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="select *  from ad_loan_trx where ad_loan_trx_id=(SELECT max(ad_loan_trx_id)  FROM ad_loan_trx where loan_trx_id=(SELECT max(loan_trx_id) as loan_trx_id   FROM loan_trx where ad_emp_id=? and isactive='TRUE'))";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_employee_id);
					rs=ps.executeQuery();
					if(rs.next()){
						bean.setBalance_amt(rs.getDouble("balance_amt"));
						bean.setTrx_date(rs.getDate("trx_date"));
						bean.setLoan_trx_id(rs.getInt("loan_trx_id"));
					}
				
					}catch (Exception e) {
					System.out.print("StaffLoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return bean;
				}
			
			//--------------------------------------------------------------------------------------
			public ArrayList<LoanTrxDetailBean> getOpenLoanTrxDetailById(int ad_employee_id) {
				ArrayList<LoanTrxDetailBean> bean = new ArrayList<LoanTrxDetailBean>();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "select ad_loan_trx_id,trx_date,deposit_amt,interest_amt,balance_amt,alt.loan_trx_id from ad_loan_trx alt left join loan_trx lt on lt.loan_trx_id=alt.loan_trx_id Where alt.ad_emp_id=? And lt.isactive='TRUE' order by ad_loan_trx_id asc";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_employee_id);
					rs = ps.executeQuery();
					while (rs.next()) {
						LoanTrxDetailBean bean1 = new LoanTrxDetailBean();
						//bean1.setAd_employee_id(rs.getInt("ad_employee_id"));
						bean1.setAd_loan_trx_id(rs.getInt("ad_loan_trx_id"));
					/*	bean1.setCreated(rs.getDate("created"));
						bean1.setCreatedby(rs.getInt("createdby"));
						bean1.setUpdated(rs.getDate("updated"));
						bean1.setUpdatedby(rs.getInt("updatedby"));
						bean1.setIsactive(rs.getBoolean("isactive"));*/
						bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
						
						bean1.setTrx_date(rs.getDate("trx_date"));
						bean1.setDeposit_amt(rs.getDouble("deposit_amt"));						
						
						bean1.setInterest_amt(rs.getDouble("interest_amt"));
						bean1.setBalance_amt(rs.getDouble("balance_amt"));
						bean.add(bean1);
						

					}
				}catch (Exception e) {
					System.out.print("StaffLoanTrxDetailDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}	
	//----------------------------------------------------------------------------------------------
			}



