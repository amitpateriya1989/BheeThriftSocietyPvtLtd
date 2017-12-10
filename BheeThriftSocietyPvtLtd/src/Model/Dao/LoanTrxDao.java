package Model.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import Model.Bean.LoanDetailViewBean;
import Model.Bean.LoanTrxBean;
import Model.Bean.LoanTrxDetailBean;


public class LoanTrxDao {
	private Connection con = null;

	public LoanTrxDao() {
		
			con = DBConnection.getConnection();
			
	}

	public int addLoanTrx(LoanTrxBean bean) {
		int genid=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO loan_trx( ad_member_id, loan_type, loan_cataegory, loan_amt, " +
					"intrest_rate, period_month, issue_date, end_date, emi, witnes, " +
						 "isactive, created, createdby, updated, updatedby,"
						+ "loan_purpose,status,amt_in_words,request_loan_amt)" +
							"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?,?,?,?)" ;
					
			ps = con.prepareStatement(query,ps.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, bean.getAd_member_id());
			ps.setInt(2, bean.getLoan_type());
			ps.setInt(3, bean.getLoan_cataegory());
			ps.setDouble(4, bean.getLoan_amt());
			ps.setDouble(5, bean.getIntrest_rate());
			ps.setInt(6, bean.getPeriod_month());
			ps.setDate(7, new java.sql.Date(bean.getissue_date().getTime()));
			ps.setDate(8,new java.sql.Date(bean.getEnd_date().getTime()));
			ps.setDouble(9, bean.getEmi());
			ps.setInt(10, bean.getWitnes());
			//ps.setInt(11, bean.getGuaranter());
			ps.setBoolean(11, true);
			ps.setTimestamp(12, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(13, bean.getCreatedby());
			ps.setTimestamp(14, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(15, bean.getUpdatedby());
		//	ps.setInt(17,bean.getAd_voucher_id());
			ps.setString(16, bean.getLoan_purpose());
			ps.setString(17, "Pending");
			ps.setString(18, bean.getAmt_in_words());
			ps.setDouble(19, bean.getRequest_loan_amt());
			ps.executeUpdate();
			
			ResultSet generatedKeys=null;
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
		        	
		    }finally{
		    	DBConnection.close(generatedKeys);
		    }
		
			
		} catch (Exception e) {
			System.out.print("LoanTrxDao:-error in try Block");
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
		String query = "select * from loan_trx where ad_member_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
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
				System.out.print("LoanTrxDao:-error in try Block");
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
			String query = "select * from loan_trx where ad_member_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, bean.getAd_member_id());
				rs = ps.executeQuery();
				while (rs.next()) {
					LoanTrxBean bean1 = new LoanTrxBean();
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
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
					//bean1.setAddress(new MemberAddressDao().getMemberAddressByMemberId(rs.getInt("ad_member_id")));
					bean2.add(bean1);
				//	System.out.print(bean);

				}
			}catch (Exception e) {
				System.out.print("LoanTrxDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean2;
		}
		
		
		//--------------------------------------------------------------------------------------
		public ArrayList<LoanTrxBean> getAllLoanTrxBymemId(int  ad_member_id) {
			ArrayList<LoanTrxBean> bean2 = new ArrayList<LoanTrxBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from loan_trx where ad_member_id=?";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_member_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					LoanTrxBean bean1 = new LoanTrxBean();
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
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
					//bean1.setAddress(new MemberAddressDao().getMemberAddressByMemberId(rs.getInt("ad_member_id")));
					bean2.add(bean1);
				//	System.out.print(bean);

				}
			}catch (Exception e) {
				System.out.print("LoanTrxDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean2;
		}
		
		
		
		
		//--------------------------------------------------------------------------------------
				public ArrayList<LoanTrxBean> getAllLoanTrxBymemId(int  ad_member_id,String status,int loan_type_id) {
					ArrayList<LoanTrxBean> bean2 = new ArrayList<LoanTrxBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from loan_trx where ad_member_id=? AND status=? AND isactive=? AND loan_type=? order by issue_date";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_member_id);
						ps.setString(2, status);
						ps.setBoolean(3, true);
						ps.setInt(4,loan_type_id);
						rs = ps.executeQuery();
						while (rs.next()) {
							LoanTrxBean bean1 = new LoanTrxBean();
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
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
							//bean1.setAddress(new MemberAddressDao().getMemberAddressByMemberId(rs.getInt("ad_member_id")));
							bean2.add(bean1);
						//	System.out.print(bean);

						}
					}catch (Exception e) {
						System.out.print("LoanTrxDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return bean2;
				}
		
		
		
		
		
		
		
		//--------------------------------------------------------------------------------------
				public ArrayList<LoanTrxBean> getAllLoanTrxByVoucherId(int  ad_voucher_id) {
					ArrayList<LoanTrxBean> bean2 = new ArrayList<LoanTrxBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select loan_trx_id from loan_trx where ad_voucher_id=?";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1,ad_voucher_id);
						rs = ps.executeQuery();
						while (rs.next()) {
							LoanTrxBean bean1 = new LoanTrxBean();												
							bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));							
							bean2.add(bean1);						

						}
					}catch (Exception e) {
						System.out.print("LoanTrxDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return bean2;
				}
				
				
				
//------------------------------------------------------------------------------------------	
	public ArrayList<LoanTrxBean> getLoanTrxById(int ad_member_id) {
		ArrayList<LoanTrxBean> bean2 = new ArrayList<LoanTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from loan_trx where ad_member_id=? AND status=? AND isactive=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			ps.setString(2, "Open");
			ps.setBoolean(3, true);
			rs = ps.executeQuery();
			while (rs.next()) {
				LoanTrxBean bean1 = new LoanTrxBean();
				bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
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
				bean2.add(bean1);						

			}
		}catch (Exception e) {
			System.out.print("LoanTrxDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean2;
	}
//--------------------------------------------------------------------------------------
	public LoanTrxBean getLoanTrxById1(int ad_member_id) {
		LoanTrxBean bean1 = new LoanTrxBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from loan_trx where ad_member_id=?  and isactive='TRUE' and status='Open' ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
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
			System.out.print("LoanTrxDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
	
	
	
	//--------------------------------------------------------------------------------------
		public double getLoanEmiForPayrollByMemberId(int ad_member_id) {
			double total_emi=0.0;
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select sum(emi) as payroll_emi from loan_trx where ad_member_id=?  and isactive='TRUE' and status='Open' group by ad_member_id";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_member_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					
					total_emi=rs.getDouble("payroll_emi");
										

				}
			}catch (Exception e) {
				System.out.print("LoanTrxDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return total_emi;
		}
	//------------------------------------------------------------------------------------------	
		public LoanTrxBean getLoanTrxByPrmryId(int loan_trx_id) {
			LoanTrxBean bean1 = new LoanTrxBean();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from loan_trx where loan_trx_id=?  and isactive='TRUE'";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, loan_trx_id);
				//System.out.println(ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
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
				System.out.print("LoanTrxDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
		
		//------------------------------------------------------------------------------------------	
				public LoanTrxBean getLoanTrxByDetail(int loan_trx_id) {
					LoanTrxBean bean1 = new LoanTrxBean();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from loan_trx where loan_trx_id=? ";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, loan_trx_id);
						
						rs = ps.executeQuery();
						while (rs.next()) {
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
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
							bean1.setStatus(rs.getString("status"));
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
						}
					}catch (Exception e) {
						System.out.print("LoanTrxDao:-error in try Block");
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
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
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
				//bean1.setAddress(new MemberAddressDao().getMemberAddressByMemberId(rs.getInt("ad_member_id")));
				bean.add(bean1);
				//System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("LoanTrxDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	//--------------------------------------------------------------------------------------
		public ArrayList<LoanTrxBean> getAllLoanTrxPending() {
			ArrayList<LoanTrxBean> bean = new ArrayList<LoanTrxBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from loan_trx where status='Pending' AND ad_voucher_id is null";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					LoanTrxBean bean1 = new LoanTrxBean();
					bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
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
					//bean1.setAddress(new MemberAddressDao().getMemberAddressByMemberId(rs.getInt("ad_member_id")));
					bean.add(bean1);
					//System.out.print(bean);

				}
			}catch (Exception e) {
				System.out.print("LoanTrxDao:-error in try Block");
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
			String query = "select * from loan_trx";
			try {
				ps = con.prepareStatement(query);
				//System.out.print(ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					LoanTrxBean bean1 = new LoanTrxBean();
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
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
				System.out.print("LoanTrxDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}	
		
		//--------------------------------------------------------------------------------------
		public ArrayList<LoanTrxBean> getAllActiveLoanForInterestCalculation(boolean isactive,String status) {
			ArrayList<LoanTrxBean> bean = new ArrayList<LoanTrxBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = " SELECT * FROM ( " +
					" SELECT l.* FROM ad_member m " +
					" LEFT JOIN loan_trx l ON  m.ad_member_id=l.ad_member_id " +
					" WHERE l.isactive=? AND l.status=? AND m.ad_member_status_id =?  ) a " +
					" UNION " +
					"SELECT * FROM ( " +
					" SELECT l.* FROM ad_employee m " +
					" LEFT JOIN loan_trx l ON  m.ad_employee_id=l.ad_emp_id " +
					" WHERE l.isactive=? AND l.status=? AND m.emp_status=?  ) b order by loan_trx_id";
			try {
				ps = con.prepareStatement(query);
				ps.setBoolean(1, isactive);
				ps.setString(2, status);
				ps.setInt(3,1);
				ps.setBoolean(4, isactive);
				ps.setString(5, status);
				ps.setString(6,"ACTIVE");
				
				//System.out.print(ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					LoanTrxBean bean1 = new LoanTrxBean();
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
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
					bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
					bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
					bean.add(bean1);
					

				}
			}catch (Exception e) {
				System.out.print("LoanTrxDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}	
		//--------------------------------------------------------------------------------------
				public ArrayList<LoanTrxBean> getAllActiveLoanTrx(boolean isactive,String status) {
					ArrayList<LoanTrxBean> bean = new ArrayList<LoanTrxBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from loan_trx where isactive=? AND status=? order by loan_trx_id";
					try {
						ps = con.prepareStatement(query);
						ps.setBoolean(1, isactive);
						ps.setString(2, status);
						//System.out.print(ps);
						rs = ps.executeQuery();
						while (rs.next()) {
							LoanTrxBean bean1 = new LoanTrxBean();
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
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
							bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
							bean1.setAd_employee_id(rs.getInt("ad_emp_id"));
							bean.add(bean1);
							

						}
					}catch (Exception e) {
						System.out.print("LoanTrxDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return bean;
				}	
//----------------------------------------------------------------------------------------------
	public int updateLoanTrx(LoanTrxBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE loan_trx    SET ad_voucher_id=?,  status=?,updated=?,updatedby=? WHERE loan_trx_id=?";
			ps = con.prepareStatement(query);
			
			ps.setInt(1, bean.getAd_voucher_id());
			ps.setString(2, bean.getStatus());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4,bean.getUpdatedby() );
			ps.setInt(5, bean.getLoan_trx_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("LoanTrxDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
	
	//----------------------------------------------------------------------------------------------
		public int updateLoanTrx(int ad_voucher_id,int ad_loan_trx_id){
			int i=0;
			PreparedStatement ps=null;
			try {

				String query = "UPDATE loan_trx    SET ad_voucher_id=? WHERE loan_trx_id=?";
				ps = con.prepareStatement(query);
				
				ps.setInt(1, ad_voucher_id);
				ps.setInt(2, ad_loan_trx_id);
				
				
				//System.out.print(ps);
				i=ps.executeUpdate();
			
		}catch (Exception e) {
			System.out.print("LoanTrxDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}
	//-------------------------
	public void CloseLoanTrx(int loan_trx_id){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE loan_trx    SET   isactive=? , status=? WHERE loan_trx_id=?";
			ps = con.prepareStatement(query);
			
			
			ps.setBoolean(1, false);
			ps.setString(2, "Close");
			ps.setInt(3, loan_trx_id);
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("LoanTrxDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public void deleteLoanTrx(int  ad_voucher_id){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM loan_trx WHERE ad_voucher_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("LoanTrxDao:-error in try Block");
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
				System.out.print("LoanTrxDao:-error in try Block");
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
			public int getOpenLoan(int ad_member_id,int ad_loan_category_id1, int ad_type_of_loan_id1){
				int result=0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="select max(loan_trx_id) as loan_trx_id from loan_trx where ad_member_id=? and loan_cataegory=? and loan_type=? and isactive='TRUE'";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_member_id);
					ps.setInt(2, ad_loan_category_id1);
					ps.setInt(3, ad_type_of_loan_id1);
					
					rs=ps.executeQuery();
					if(rs.next()){
						result=rs.getInt("loan_trx_id");
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDao:-error in try Block");
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
					String query="select max(loan_trx_id) as loan_trx_id from loan_trx where ad_member_id=?";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_member_id);
					rs=ps.executeQuery();
					if(rs.next()){
						result=rs.getInt("loan_trx_id");
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return result;
			 }//end getMemberNo function
			
			public int getMinSalaryPer(int loanType, int loanCategory){
				
				int minSal = 0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="SELECT min_salary FROM ad_loan_roi where ad_loan_category_id =? and ad_type_of_loan_id=?;";
					ps=con.prepareStatement(query);
					ps.setInt(1, loanCategory);
					ps.setInt(2, loanType);
					rs=ps.executeQuery();
					if(rs.next()){
						minSal=rs.getInt("min_salary");
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
				
				return minSal;
			};//end getMinSalaryPer function
//--------------------------------------------------------------------------------------------------------------------------------------			
			public ArrayList<LoanDetailViewBean> getAllLoanDetail() {
				ArrayList<LoanDetailViewBean> bean = new ArrayList<LoanDetailViewBean>();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "SELECT ad_society_no,member_name, loan_type, loan_category_name, " +
						" loan_amt, intrest_rate, period_month, issue_date, end_date, emi, status ,loan_trx_id " +
						"FROM loan_detail where loan_amt is not null and status in ('Open','Close') order by issue_date; ";
				try {
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						LoanDetailViewBean bean1 = new LoanDetailViewBean();
						bean1.setAd_society_no(rs.getInt("ad_society_no"));
						bean1.setMember_name(rs.getString("member_name").toUpperCase());
						bean1.setLoan_type(rs.getString("loan_type"));
						bean1.setLoan_category_name(rs.getString("loan_category_name"));
						bean1.setLoan_amt(rs.getDouble("loan_amt"));
						bean1.setIntrest_rate(rs.getDouble("intrest_rate"));
						bean1.setPeriod_month(rs.getInt("period_month"));
						bean1.setIssue_date(rs.getDate("issue_date"));
						bean1.setEnd_date(rs.getDate("end_date"));
						bean1.setEmi(rs.getDouble("emi"));
						bean1.setStatus(rs.getString("status"));
						bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
						bean.add(bean1);
						//System.out.print(bean);

					}
				}catch (Exception e) {
					System.out.print("LoanTrxDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}
			
			//--------------------------------------------------------------------------------------------------------------------------------------			
			public ArrayList<LoanDetailViewBean> getAllLoanDetail(java.util.Date from,java.util.Date to) {
				ArrayList<LoanDetailViewBean> bean = new ArrayList<LoanDetailViewBean>();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "SELECT ad_society_no,member_name, loan_type, loan_category_name, " +
						" loan_amt, intrest_rate, period_month, issue_date, end_date, emi ,status ,loan_trx_id " +
						"FROM loan_detail where (issue_date between ? AND ? ) AND loan_amt is not null order by issue_date; ";
				try {
					ps = con.prepareStatement(query);
					ps.setDate(1, new java.sql.Date(from.getTime()));
					ps.setDate(2, new java.sql.Date(to.getTime()));
					rs = ps.executeQuery();
					while (rs.next()) {
						LoanDetailViewBean bean1 = new LoanDetailViewBean();
						bean1.setAd_society_no(rs.getInt("ad_society_no"));
						bean1.setMember_name(rs.getString("member_name").toUpperCase());
						bean1.setLoan_type(rs.getString("loan_type"));
						bean1.setLoan_category_name(rs.getString("loan_category_name"));
						bean1.setLoan_amt(rs.getDouble("loan_amt"));
						bean1.setIntrest_rate(rs.getDouble("intrest_rate"));
						bean1.setPeriod_month(rs.getInt("period_month"));
						bean1.setIssue_date(rs.getDate("issue_date"));
						bean1.setEnd_date(rs.getDate("end_date"));
						bean1.setEmi(rs.getDouble("emi"));
						bean1.setStatus(rs.getString("status"));
						bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
						bean.add(bean1);
						//System.out.print(bean);

					}
				}catch (Exception e) {
					System.out.print("LoanTrxDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}
				

			//--------------------------------------------------------------------------------------------------------------------------------------			
			public ArrayList<LoanDetailViewBean> getAllLoanDetailByMemberId(int ad_member_id, String status) {
				ArrayList<LoanDetailViewBean> bean = new ArrayList<LoanDetailViewBean>();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "SELECT loan_trx_id,ad_society_no,member_name, loan_type, loan_category_name, " +
						" loan_amt, intrest_rate, period_month, issue_date, end_date, emi " +
						"FROM loan_detail where ad_member_id=? AND  status =? order by issue_date; ";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_member_id);
					ps.setString(2, status);
					
					rs = ps.executeQuery();
					while (rs.next()) {
						LoanDetailViewBean bean1 = new LoanDetailViewBean();
						bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
						bean1.setAd_society_no(rs.getInt("ad_society_no"));
						bean1.setMember_name(rs.getString("member_name").toUpperCase());
						bean1.setLoan_type(rs.getString("loan_type"));
						bean1.setLoan_category_name(rs.getString("loan_category_name"));
						bean1.setLoan_amt(rs.getDouble("loan_amt"));
						bean1.setIntrest_rate(rs.getDouble("intrest_rate"));
						bean1.setPeriod_month(rs.getInt("period_month"));
						bean1.setIssue_date(rs.getDate("issue_date"));
						bean1.setEnd_date(rs.getDate("end_date"));
						bean1.setEmi(rs.getDouble("emi"));
						
						bean.add(bean1);
						//System.out.print(bean);

					}
				}catch (Exception e) {
					System.out.print("LoanTrxDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}
				
			
			
			//--------------------------------------------------------------------------------------------------------------------------------------			
			public ArrayList<LoanDetailViewBean> getAllLoanDetailByMemberId(int ad_member_id) {
				ArrayList<LoanDetailViewBean> bean = new ArrayList<LoanDetailViewBean>();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "SELECT loan_trx_id,ad_society_no,member_name, loan_type, loan_category_name, " +
						" loan_amt, intrest_rate, period_month, issue_date, end_date, emi " +
						"FROM loan_detail where ad_member_id=? AND status!='Pending'  order by issue_date ";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_member_id);
				
					
					rs = ps.executeQuery();
					while (rs.next()) {
						LoanDetailViewBean bean1 = new LoanDetailViewBean();
						bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
						bean1.setAd_society_no(rs.getInt("ad_society_no"));
						bean1.setMember_name(rs.getString("member_name").toUpperCase());
						bean1.setLoan_type(rs.getString("loan_type"));
						bean1.setLoan_category_name(rs.getString("loan_category_name"));
						bean1.setLoan_amt(rs.getDouble("loan_amt"));
						bean1.setIntrest_rate(rs.getDouble("intrest_rate"));
						bean1.setPeriod_month(rs.getInt("period_month"));
						bean1.setIssue_date(rs.getDate("issue_date"));
						bean1.setEnd_date(rs.getDate("end_date"));
						bean1.setEmi(rs.getDouble("emi"));
						
						bean.add(bean1);
						//System.out.print(bean);

					}
				}catch (Exception e) {
					System.out.print("LoanTrxDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}
				

			//--------------------------------------------------------------------------------------------------------------------------------------			
			public LoanDetailViewBean getAllLoanDetailByLoanId(int loan_trx_id) {
				LoanDetailViewBean bean1 = new LoanDetailViewBean();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "SELECT loan_trx_id,ad_society_no,member_name, loan_type, loan_category_name, " +
						" loan_amt, intrest_rate, period_month, issue_date, end_date, emi, loan_purpose, witnes,status,isactive,vno  " +
						"FROM loan_detail where loan_trx_id=?  order by issue_date ";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, loan_trx_id);
				
					//System.out.println(ps);
					rs = ps.executeQuery();
					while (rs.next()) {
						
						bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
						bean1.setAd_society_no(rs.getInt("ad_society_no"));
						bean1.setMember_name(rs.getString("member_name").toUpperCase());
						bean1.setLoan_type(rs.getString("loan_type"));
						bean1.setLoan_category_name(rs.getString("loan_category_name"));
						bean1.setLoan_amt(rs.getDouble("loan_amt"));
						bean1.setIntrest_rate(rs.getDouble("intrest_rate"));
						bean1.setPeriod_month(rs.getInt("period_month"));
						bean1.setIssue_date(rs.getDate("issue_date"));
						bean1.setEnd_date(rs.getDate("end_date"));
						bean1.setEmi(rs.getDouble("emi"));
						bean1.setLoan_purpose(rs.getString("loan_purpose"));
						bean1.setWitnes(rs.getInt("witnes"));
						bean1.setStatus(rs.getString("status"));
						bean1.setIsactive(rs.getBoolean("isactive"));
						bean1.setVno(rs.getString("vno"));

					}
				}catch (Exception e) {
					System.out.print("LoanTrxDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean1;
			}
				

			//----------------------------------------------------------------------------------------------
			@SuppressWarnings("finally")
			public Date getLoanTrxMaxDate(){
				Date result=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="select trx_date from ad_loan_trx where ad_loan_trx_id=(select Max(ad_loan_trx_id) from ad_loan_trx)";
					ps=con.prepareStatement(query);
					
					rs=ps.executeQuery();
					if(rs.next()){
						result=rs.getDate("trx_date");
					}
				
					}catch (Exception e) {
					System.out.print("LoanTrxDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return result;
				}
				
			



	//----------------------------------------------------------------------------------------------
		
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
			//----------------------------------------------------------------------------------------------
			public int updateLoan(LoanTrxBean bean){
				int i=0;
				PreparedStatement ps=null;
				try {

					String query = "UPDATE loan_trx SET loan_amt=?, intrest_rate=?, period_month=?, " +
							" issue_date=?, end_date=?, emi=?,  isactive=?, updated=?, updatedby=?, loan_purpose=?, " +
							" status=?  WHERE  loan_trx_id=?";
					ps = con.prepareStatement(query);
					
					ps.setDouble(1, bean.getLoan_amt());
					ps.setDouble(2, bean.getIntrest_rate());
					ps.setInt(3, bean.getPeriod_month());
					ps.setDate(4, new java.sql.Date(bean.getissue_date().getTime()));
					ps.setDate(5, new java.sql.Date(bean.getEnd_date().getTime()));
					ps.setDouble(6, bean.getEmi());
					ps.setBoolean(7, bean.isIsactive());
					ps.setDate(8, new java.sql.Date(new java.util.Date().getTime()));
					ps.setInt(9,bean.getUpdatedby() );
					ps.setString(10, bean.getLoan_purpose());
					ps.setString(11, bean.getStatus());
					ps.setInt(12, bean.getLoan_trx_id());
					
					
					//System.out.print(ps);
					i=ps.executeUpdate();
				
			}catch (Exception e) {
				System.out.print("LoanTrxDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return i;
		}
			//----------------------------------------------------------------------------------------------
			public int deleteLoan(int  loan_trx_id){
				int i=0;
				PreparedStatement ps=null;
				try{
					String query="DELETE FROM loan_trx WHERE loan_trx_id=?";
					ps = con.prepareStatement(query);
					ps.setInt(1, loan_trx_id);
					i=ps.executeUpdate();
				
					}catch (Exception e) {
					System.out.print("LoanTrxDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
			
				return i;
			}
}//end class