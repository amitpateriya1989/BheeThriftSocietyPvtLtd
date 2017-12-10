package Model.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.BankBranchBean;
import Model.Bean.LoanTrxBean;
import Model.Bean.MemberStatusBean;

public class StaffLoanTrxDao {
	private Connection con = null;

	public StaffLoanTrxDao() {
		
			con = DBConnection.getConnection();
			
	}

	public int addLoanTrx(LoanTrxBean bean) {
		int genid=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO loan_trx( ad_emp_id, loan_type, loan_cataegory, loan_amt, " +
					"intrest_rate, period_month, issue_date, end_date, emi, witnes, " +
							"guaranter, isactive, created, createdby, updated, updatedby,ad_voucher_id,loan_purpose)" +
							"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?,?,?)" ;
					
			ps = con.prepareStatement(query,ps.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, bean.getAd_employee_id());
			ps.setInt(2, bean.getLoan_type());
			ps.setInt(3, bean.getLoan_cataegory());
			ps.setDouble(4, bean.getLoan_amt());
			ps.setDouble(5, bean.getIntrest_rate());
			ps.setInt(6, bean.getPeriod_month());
			ps.setDate(7, new java.sql.Date(bean.getissue_date().getTime()));
			ps.setDate(8,new java.sql.Date(bean.getEnd_date().getTime()));
			ps.setDouble(9, bean.getEmi());
			ps.setInt(10, bean.getWitnes());
			ps.setInt(11, bean.getGuaranter());
			ps.setBoolean(12, true);
			ps.setTimestamp(13, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(14, bean.getCreatedby());
			ps.setTimestamp(15, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(16, bean.getUpdatedby());
			ps.setInt(17,bean.getAd_voucher_id());
			ps.setString(18, bean.getLoan_purpose());
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
			System.out.print("StaffLoanTrxDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return genid;
		
	
		
	}
//-----------------------------------------------------------------------------------------
	public LoanTrxBean getLoanTrxById(LoanTrxBean bean) {
		LoanTrxBean bean1 = new LoanTrxBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from loan_trx where ad_emp_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_employee_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
				bean1.setLoan_type(rs.getInt("loan_type"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setLoan_cataegory(rs.getInt("loan_cataegory"));
				bean1.setLoan_amt(rs.getInt("loan_amt"));
				bean1.setIntrest_rate(rs.getDouble("intrest_rate"));
				bean1.setPeriod_month(rs.getInt("period_month"));
				bean1.setissue_date(rs.getDate("issue_date"));
				bean1.setEnd_date(rs.getDate("end_Date"));
				bean1.setEmi(rs.getDouble("emi"));
				bean1.setWitnes(rs.getInt("witnes"));
				bean1.setGuaranter(rs.getInt("guaranter"));
				

			}
			}catch (Exception e) {
				System.out.print("StaffLoanTrxDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//--------------------------------------------------------------------------------------
		public ArrayList<LoanTrxBean> getAllLoanTrxBymemId(LoanTrxBean bean) {
			ArrayList<LoanTrxBean> bean2 = new ArrayList<LoanTrxBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from loan_trx where ad_emp_id=?";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, bean.getAd_employee_id());
				rs = ps.executeQuery();
				while (rs.next()) {
					LoanTrxBean bean1 = new LoanTrxBean();
					bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
					bean1.setLoan_type(rs.getInt("loan_type"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setLoan_cataegory(rs.getInt("loan_cataegory"));
					bean1.setLoan_amt(rs.getInt("loan_amt"));
					bean1.setIntrest_rate(rs.getDouble("intrest_rate"));
					bean1.setPeriod_month(rs.getInt("period_month"));
					bean1.setissue_date(rs.getDate("issue_date"));
					bean1.setEnd_date(rs.getDate("end_Date"));
					bean1.setEmi(rs.getDouble("emi"));
					bean1.setWitnes(rs.getInt("witnes"));
					bean1.setGuaranter(rs.getInt("guaranter"));
					bean1.setLoan_purpose(rs.getString("loan_purpose"));
					bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
					//bean1.setAddress(new MemberAddressDao().getMemberAddressByMemberId(rs.getInt("ad_employee_id")));
					bean2.add(bean1);
				//	System.out.print(bean);

				}
			}catch (Exception e) {
				System.out.print("StaffLoanTrxDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean2;
		}
		
		
		//--------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------	
	public LoanTrxBean getLoanTrxById(int ad_employee_id) {
		LoanTrxBean bean1 = new LoanTrxBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from loan_trx where ad_emp_id=?  and isactive='TRUE'";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_employee_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
				bean1.setLoan_type(rs.getInt("loan_type"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setLoan_cataegory(rs.getInt("loan_cataegory"));
				bean1.setLoan_amt(rs.getInt("loan_amt"));
				bean1.setIntrest_rate(rs.getDouble("intrest_rate"));
				bean1.setPeriod_month(rs.getInt("period_month"));
				bean1.setissue_date(rs.getDate("issue_date"));
				bean1.setEnd_date(rs.getDate("end_Date"));
				bean1.setEmi(rs.getDouble("emi"));
				bean1.setWitnes(rs.getInt("witnes"));
				bean1.setGuaranter(rs.getInt("guaranter"));
				
			}
		}catch (Exception e) {
			System.out.print("StaffLoanTrxDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	
	//------------------------------------------------------------------------------------------	
		public LoanTrxBean getLoanTrxByPrmryId(int loan_trx_id) {
			LoanTrxBean bean1 = new LoanTrxBean();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from loan_trx where loan_trx_id=?  and isactive='TRUE'";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, loan_trx_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
					bean1.setLoan_type(rs.getInt("loan_type"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setLoan_cataegory(rs.getInt("loan_cataegory"));
					bean1.setLoan_amt(rs.getInt("loan_amt"));
					bean1.setIntrest_rate(rs.getDouble("intrest_rate"));
					bean1.setPeriod_month(rs.getInt("period_month"));
					bean1.setissue_date(rs.getDate("issue_date"));
					bean1.setEnd_date(rs.getDate("end_Date"));
					bean1.setEmi(rs.getDouble("emi"));
					bean1.setWitnes(rs.getInt("witnes"));
					bean1.setGuaranter(rs.getInt("guaranter"));
					
				}
			}catch (Exception e) {
				System.out.print("StaffLoanTrxDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//--------------------------------------------------------------------------------------
	public ArrayList<LoanTrxBean> getAllLoanTrx() {
		ArrayList<LoanTrxBean> bean = new ArrayList<LoanTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from loan_trx ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				LoanTrxBean bean1 = new LoanTrxBean();
				bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
				bean1.setLoan_type(rs.getInt("loan_type"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setLoan_cataegory(rs.getInt("loan_cataegory"));
				bean1.setLoan_amt(rs.getInt("loan_amt"));
				bean1.setIntrest_rate(rs.getDouble("intrest_rate"));
				bean1.setPeriod_month(rs.getInt("period_month"));
				bean1.setissue_date(rs.getDate("issue_date"));
				bean1.setEnd_date(rs.getDate("end_Date"));
				bean1.setEmi(rs.getDouble("emi"));
				bean1.setWitnes(rs.getInt("witnes"));
				bean1.setGuaranter(rs.getInt("guaranter"));
				//bean1.setAddress(new MemberAddressDao().getMemberAddressByMemberId(rs.getInt("ad_employee_id")));
				bean.add(bean1);
				System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("StaffLoanTrxDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	
	//--------------------------------------------------------------------------------------
		public ArrayList<LoanTrxBean> getAllActiveLoanTrx() {
			ArrayList<LoanTrxBean> bean = new ArrayList<LoanTrxBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * form loan_trx";
			try {
				ps = con.prepareStatement(query);
				System.out.print(ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					LoanTrxBean bean1 = new LoanTrxBean();
					bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
					bean1.setLoan_type(rs.getInt("loan_type"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setLoan_cataegory(rs.getInt("loan_cataegory"));
					bean1.setLoan_amt(rs.getInt("loan_amt"));
					bean1.setIntrest_rate(rs.getDouble("intrest_rate"));
					bean1.setPeriod_month(rs.getInt("period_month"));
					bean1.setissue_date(rs.getDate("issue_date"));
					bean1.setEnd_date(rs.getDate("end_Date"));
					bean1.setEmi(rs.getDouble("emi"));
					bean1.setWitnes(rs.getInt("witnes"));
					bean1.setGuaranter(rs.getInt("guaranter"));
					bean.add(bean1);
					

				}
			}catch (Exception e) {
				System.out.print("StaffLoanTrxDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}	
//----------------------------------------------------------------------------------------------
	public void updateLoanTrx(LoanTrxBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = " ";
			ps = con.prepareStatement(query);
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_employee_id());
			ps.setInt(2, bean.getLoan_type());
			ps.setInt(3, bean.getLoan_cataegory());
			ps.setDouble(4, bean.getLoan_amt());
			ps.setDouble(5, bean.getIntrest_rate());
			ps.setInt(6, bean.getPeriod_month());
			ps.setDate(7, new java.sql.Date(bean.getissue_date().getTime()));
			ps.setDate(8,new java.sql.Date(bean.getEnd_date().getTime()));
			ps.setDouble(9, bean.getEmi());
			ps.setInt(10, bean.getWitnes());
			ps.setInt(11, bean.getGuaranter());
			ps.setBoolean(12, true);
			
			
			ps.setTimestamp(13, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(14, bean.getCreatedby());
			ps.setTimestamp(15, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(16, bean.getUpdatedby());
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("StaffLoanTrxDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public void deleteLoanTrx(LoanTrxBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_employee WHERE ad_employee_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_employee_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("StaffLoanTrxDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
	
	
	//----------------------------------------------------------------------------------------------
		@SuppressWarnings("finally")
		public int getLoanTrxMaxId(){
			int result=0;
			PreparedStatement ps=null;
			ResultSet rs=null;
			try{
				String query="select loan_trx_id from loan_trx where loan_trx_id=(select Max(loan_trx_id) from loan_trx)";
				ps=con.prepareStatement(query);
				
				rs=ps.executeQuery();
				if(rs.next()){
					result=rs.getInt("loan_trx_id");
				}
			
				}catch (Exception e) {
				System.out.print("StaffLoanTrxDao:-error in try Block");
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
			public int getOpenLoan(int ad_employee_id){
				int result=0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="select max(loan_trx_id) as loan_trx_id from loan_trx where ad_emp_id=? and isactive='TRUE'";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_employee_id);
					rs=ps.executeQuery();
					if(rs.next()){
						result=rs.getInt("loan_trx_id");
					}
				
					}catch (Exception e) {
					System.out.print("StaffLoanTrxDao:-error in try Block");
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
					String query="select max(loan_trx_id) as loan_trx_id from loan_trx where ad_emp_id=?";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_employee_id);
					rs=ps.executeQuery();
					if(rs.next()){
						result=rs.getInt("loan_trx_id");
					}
				
					}catch (Exception e) {
					System.out.print("StaffLoanTrxDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return result;
				}
				
			}



