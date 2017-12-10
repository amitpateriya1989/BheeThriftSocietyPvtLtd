package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;


import Model.Bean.PayrollBean;
import Model.Bean.PayrollTempBean;

public class PayrollDao {

private Connection con = null;
	
	public PayrollDao() {
		con = DBConnection.getConnection();
		
	}
	
	public void addPayroll(PayrollTempBean bean,int ad_voucher_id) {
		
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_payroll( ad_member_id, created, createdby, updated, updatedby, isactive, payroll_date, membership_amt, "
					+ " diff_membership_amt, share_amt, diff_share_amt, thrift_amt, diff_thrift_amt, dcf_amt, diff_dcf_amt, main_loan_amt, diff_main_loan_amt, "
					+ " festival_loan_amt, diff_festival_loan_amt, rd_amt, diff_rd_amt, guest_house_amt, diff_guest_house_amt, excess_amt, total_amt, diff_total_amt, batch_no,ad_voucher_id) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

			ps = con.prepareStatement(query);			
			ps.setInt(1,bean.getAd_member_id());
			ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(3, bean.getCreatedby());
			ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(5, bean.getUpdatedby());
			ps.setBoolean(6, bean.isIsactive());
			ps.setDate(7, new java.sql.Date(bean.getPayroll_date().getTime()));
			ps.setDouble(8, bean.getMembership_amt());
			ps.setDouble(9, bean.getDiff_membership_amt());
			ps.setDouble(10, bean.getShare_amt());
			ps.setDouble(11, bean.getDiff_share_amt());
			ps.setDouble(12, bean.getThrift_amt());
			ps.setDouble(13, bean.getDiff_thrift_amt());
			ps.setDouble(14, bean.getDcf_amt());
			ps.setDouble(15, bean.getDiff_dcf_amt());
			ps.setDouble(16, bean.getMain_loan_amt());
			ps.setDouble(17, bean.getDiff_main_loan_amt());
			ps.setDouble(18, bean.getFestival_loan_amt());
			ps.setDouble(19, bean.getDiff_festival_loan_amt());
			ps.setDouble(20, bean.getRd_amt());
			ps.setDouble(21, bean.getDiff_rd_amt());
			ps.setDouble(22, bean.getGuest_house_amt());
			ps.setDouble(23, bean.getDiff_guest_house_amt());
			ps.setDouble(24, bean.getExcess_amt());
			ps.setDouble(25, bean.getTotal_amt());
			ps.setDouble(26, bean.getDiff_total_amt());
			ps.setInt(27, bean.getBatch_no());
			ps.setInt(28, ad_voucher_id);
			ps.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
            
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		
		
		
	}
//-----------------------------------------------------------------------------------------
	//------------All Payroll--------------------------------------------------------------------------
		public ArrayList<PayrollBean> getAllPayroll() {
			ArrayList<PayrollBean> bean = new ArrayList<PayrollBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_payroll ";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					PayrollBean bean1 = new PayrollBean();
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setAd_payroll_id(rs.getInt("ad_payroll_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setDcf_amt(rs.getDouble("dcf_amt"));
					bean1.setDiff_dcf_amt(rs.getDouble("diff_dcf_amt"));
					bean1.setDiff_festival_loan_amt(rs.getDouble("diff_festival_loan_amt"));
					bean1.setDiff_guest_house_amt(rs.getDouble("diff_guest_house_amt"));
					bean1.setDiff_main_loan_amt(rs.getDouble("diff_main_loan_amt"));
					bean1.setDiff_membership_amt(rs.getDouble("diff_membership_amt"));
					bean1.setDiff_rd_amt(rs.getDouble("diff_rd_amt"));
					bean1.setDiff_share_amt(rs.getDouble("diff_share_amt"));
					bean1.setDiff_thrift_amt(rs.getDouble("diff_thrift_amt"));
					bean1.setDiff_total_amt(rs.getDouble("diff_total_amt"));
					bean1.setExcess_amt(rs.getDouble("excess_amt"));
					bean1.setFestival_loan_amt(rs.getDouble("festival_loan_amt"));
					bean1.setGuest_house_amt(rs.getDouble("guest_house_amt"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setMain_loan_amt(rs.getDouble("main_loan_amt"));
					bean1.setMembership_amt(rs.getDouble("membership_amt"));
					bean1.setPayroll_date(rs.getDate("payroll_date"));
					bean1.setRd_amt(rs.getDouble("rd_amt"));
					bean1.setShare_amt(rs.getDouble("share_amt"));
					bean1.setThrift_amt(rs.getDouble("thrift_amt"));
					bean1.setTotal_amt(rs.getDouble("total_amt"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setBatch_no(rs.getInt("batch_no"));
					bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
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
		
		

		//--------------------------------------------------------------------------------------
		
		//------------All Payroll between dates--------------------------------------------------------------------------
				public ArrayList<PayrollBean> getAllPayrollByDate(Date from, Date to) {
					ArrayList<PayrollBean> bean = new ArrayList<PayrollBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from ad_payroll where payroll_date between ? AND ? ";
					try {
						ps = con.prepareStatement(query);
						ps.setDate(1, new java.sql.Date(from.getTime()));
						ps.setDate(2, new java.sql.Date(to.getTime()));
						rs = ps.executeQuery();
						while (rs.next()) {
							PayrollBean bean1 = new PayrollBean();
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
							bean1.setAd_payroll_id(rs.getInt("ad_payroll_id"));
							bean1.setCreated(rs.getDate("created"));
							bean1.setCreatedby(rs.getInt("createdby"));
							bean1.setDcf_amt(rs.getDouble("dcf_amt"));
							bean1.setDiff_dcf_amt(rs.getDouble("diff_dcf_amt"));
							bean1.setDiff_festival_loan_amt(rs.getDouble("diff_festival_loan_amt"));
							bean1.setDiff_guest_house_amt(rs.getDouble("diff_guest_house_amt"));
							bean1.setDiff_main_loan_amt(rs.getDouble("diff_main_loan_amt"));
							bean1.setDiff_membership_amt(rs.getDouble("diff_membership_amt"));
							bean1.setDiff_rd_amt(rs.getDouble("diff_rd_amt"));
							bean1.setDiff_share_amt(rs.getDouble("diff_share_amt"));
							bean1.setDiff_thrift_amt(rs.getDouble("diff_thrift_amt"));
							bean1.setDiff_total_amt(rs.getDouble("diff_total_amt"));
							bean1.setExcess_amt(rs.getDouble("excess_amt"));
							bean1.setFestival_loan_amt(rs.getDouble("festival_loan_amt"));
							bean1.setGuest_house_amt(rs.getDouble("guest_house_amt"));
							bean1.setIsactive(rs.getBoolean("isactive"));
							bean1.setMain_loan_amt(rs.getDouble("main_loan_amt"));
							bean1.setMembership_amt(rs.getDouble("membership_amt"));
							bean1.setPayroll_date(rs.getDate("payroll_date"));
							bean1.setRd_amt(rs.getDouble("rd_amt"));
							bean1.setShare_amt(rs.getDouble("share_amt"));
							bean1.setThrift_amt(rs.getDouble("thrift_amt"));
							bean1.setTotal_amt(rs.getDouble("total_amt"));
							bean1.setUpdated(rs.getDate("updated"));
							bean1.setUpdatedby(rs.getInt("updatedby"));
							bean1.setBatch_no(rs.getInt("batch_no"));
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
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
				
				//------------All Payroll between dates- and member id-------------------------------------------------------------------------
				public ArrayList<PayrollBean> getAllPayrollByDateAndMemberId(Date from, Date to, int ad_member_id) {
					ArrayList<PayrollBean> bean = new ArrayList<PayrollBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from ad_payroll where ad_member_id=? AND payroll_date between ? AND ? ";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_member_id);
						ps.setDate(2, new java.sql.Date(from.getTime()));
						ps.setDate(3, new java.sql.Date(to.getTime()));
						rs = ps.executeQuery();
						while (rs.next()) {
							PayrollBean bean1 = new PayrollBean();
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
							bean1.setAd_payroll_id(rs.getInt("ad_payroll_id"));
							bean1.setCreated(rs.getDate("created"));
							bean1.setCreatedby(rs.getInt("createdby"));
							bean1.setDcf_amt(rs.getDouble("dcf_amt"));
							bean1.setDiff_dcf_amt(rs.getDouble("diff_dcf_amt"));
							bean1.setDiff_festival_loan_amt(rs.getDouble("diff_festival_loan_amt"));
							bean1.setDiff_guest_house_amt(rs.getDouble("diff_guest_house_amt"));
							bean1.setDiff_main_loan_amt(rs.getDouble("diff_main_loan_amt"));
							bean1.setDiff_membership_amt(rs.getDouble("diff_membership_amt"));
							bean1.setDiff_rd_amt(rs.getDouble("diff_rd_amt"));
							bean1.setDiff_share_amt(rs.getDouble("diff_share_amt"));
							bean1.setDiff_thrift_amt(rs.getDouble("diff_thrift_amt"));
							bean1.setDiff_total_amt(rs.getDouble("diff_total_amt"));
							bean1.setExcess_amt(rs.getDouble("excess_amt"));
							bean1.setFestival_loan_amt(rs.getDouble("festival_loan_amt"));
							bean1.setGuest_house_amt(rs.getDouble("guest_house_amt"));
							bean1.setIsactive(rs.getBoolean("isactive"));
							bean1.setMain_loan_amt(rs.getDouble("main_loan_amt"));
							bean1.setMembership_amt(rs.getDouble("membership_amt"));
							bean1.setPayroll_date(rs.getDate("payroll_date"));
							bean1.setRd_amt(rs.getDouble("rd_amt"));
							bean1.setShare_amt(rs.getDouble("share_amt"));
							bean1.setThrift_amt(rs.getDouble("thrift_amt"));
							bean1.setTotal_amt(rs.getDouble("total_amt"));
							bean1.setUpdated(rs.getDate("updated"));
							bean1.setUpdatedby(rs.getInt("updatedby"));
							bean1.setBatch_no(rs.getInt("batch_no"));
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
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
				
				//------------All Payroll with member id-------------------------------------------------------------------------
				public ArrayList<PayrollBean> getAllPayrollByMemberId( int ad_member_id) {
					ArrayList<PayrollBean> bean = new ArrayList<PayrollBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from ad_payroll where ad_member_id=?  ";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_member_id);
						
						rs = ps.executeQuery();
						while (rs.next()) {
							PayrollBean bean1 = new PayrollBean();
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
							bean1.setAd_payroll_id(rs.getInt("ad_payroll_id"));
							bean1.setCreated(rs.getDate("created"));
							bean1.setCreatedby(rs.getInt("createdby"));
							bean1.setDcf_amt(rs.getDouble("dcf_amt"));
							bean1.setDiff_dcf_amt(rs.getDouble("diff_dcf_amt"));
							bean1.setDiff_festival_loan_amt(rs.getDouble("diff_festival_loan_amt"));
							bean1.setDiff_guest_house_amt(rs.getDouble("diff_guest_house_amt"));
							bean1.setDiff_main_loan_amt(rs.getDouble("diff_main_loan_amt"));
							bean1.setDiff_membership_amt(rs.getDouble("diff_membership_amt"));
							bean1.setDiff_rd_amt(rs.getDouble("diff_rd_amt"));
							bean1.setDiff_share_amt(rs.getDouble("diff_share_amt"));
							bean1.setDiff_thrift_amt(rs.getDouble("diff_thrift_amt"));
							bean1.setDiff_total_amt(rs.getDouble("diff_total_amt"));
							bean1.setExcess_amt(rs.getDouble("excess_amt"));
							bean1.setFestival_loan_amt(rs.getDouble("festival_loan_amt"));
							bean1.setGuest_house_amt(rs.getDouble("guest_house_amt"));
							bean1.setIsactive(rs.getBoolean("isactive"));
							bean1.setMain_loan_amt(rs.getDouble("main_loan_amt"));
							bean1.setMembership_amt(rs.getDouble("membership_amt"));
							bean1.setPayroll_date(rs.getDate("payroll_date"));
							bean1.setRd_amt(rs.getDouble("rd_amt"));
							bean1.setShare_amt(rs.getDouble("share_amt"));
							bean1.setThrift_amt(rs.getDouble("thrift_amt"));
							bean1.setTotal_amt(rs.getDouble("total_amt"));
							bean1.setUpdated(rs.getDate("updated"));
							bean1.setUpdatedby(rs.getInt("updatedby"));
							bean1.setBatch_no(rs.getInt("batch_no"));
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
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
				
				//------------All Payroll with member id-------------------------------------------------------------------------
				public int getPayrollMaxBatchNo() {
					int batch_no =0;
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select Max(batch_no) as batch_no from ad_payroll ";
					try {
						ps = con.prepareStatement(query);
						
						
						rs = ps.executeQuery();
						while (rs.next()) {
							batch_no=rs.getInt("batch_no");

						}
					}catch (Exception e) {
						System.out.print("VoucherDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
			        }
					return batch_no;
				}
				//----------------------------------------------------------------------------------------------
				public int updatePayroll(PayrollBean bean){
					int i=0;
					PreparedStatement ps = null;
					try {

						String query = "UPDATE ad_payroll "
								+ " SET ad_member_id=?, updated=?, "
								+ " updatedby=?, isactive=?, payroll_date=?, membership_amt=?, diff_membership_amt=?, "
								+ " share_amt=?, diff_share_amt=?, thrift_amt=?, diff_thrift_amt=?, dcf_amt=?, diff_dcf_amt=?, main_loan_amt=?, diff_main_loan_amt=?, "
								+ " festival_loan_amt=?, diff_festival_loan_amt=?, rd_amt=?, diff_rd_amt=?, guest_house_amt=?, diff_guest_house_amt=?, excess_amt=?, "
								+ " total_amt=?, diff_total_amt=? , batch_no=?, ad_voucher_id=?  WHERE ad_payroll_id=? ";
						ps = con.prepareStatement(query);
						ps.setInt(1,bean.getAd_member_id());
						ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
						ps.setInt(3, bean.getUpdatedby());
						ps.setBoolean(4, bean.isIsactive());
						ps.setDate(5, new java.sql.Date(bean.getPayroll_date().getTime()));
						ps.setDouble(6, bean.getMembership_amt());
						ps.setDouble(7, bean.getDiff_membership_amt());
						ps.setDouble(8, bean.getShare_amt());
						ps.setDouble(9, bean.getDiff_share_amt());
						ps.setDouble(10, bean.getThrift_amt());
						ps.setDouble(11, bean.getDiff_thrift_amt());
						ps.setDouble(12, bean.getDcf_amt());
						ps.setDouble(13, bean.getDiff_dcf_amt());
						ps.setDouble(14, bean.getMain_loan_amt());
						ps.setDouble(15, bean.getDiff_main_loan_amt());
						ps.setDouble(16, bean.getFestival_loan_amt());
						ps.setDouble(17, bean.getDiff_festival_loan_amt());
						ps.setDouble(18, bean.getRd_amt());
						ps.setDouble(19, bean.getDiff_rd_amt());
						ps.setDouble(20, bean.getGuest_house_amt());
						ps.setDouble(21, bean.getDiff_guest_house_amt());
						ps.setDouble(22, bean.getExcess_amt());
						ps.setDouble(23, bean.getTotal_amt());
						ps.setDouble(24, bean.getDiff_total_amt());
						ps.setInt(25, bean.getBatch_no());
						ps.setInt(26, bean.getAd_voucher_id());
						ps.setInt(27, bean.getAd_payroll_id());
						//System.out.print(ps);
						i=ps.executeUpdate();
					
				}catch (Exception e) {
					System.out.print("BankDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return i;
			}
				//----------------------------------------------------------------------------------------------
				public int deletePayroll(PayrollBean bean){
					int i=0;
					PreparedStatement ps = null;
					try{
						String query="DELETE FROM ad_payroll WHERE ad_payroll_id=?";
						ps = con.prepareStatement(query);
						ps.setInt(1, bean.getAd_payroll_id());
						ps.executeUpdate();
					
						}catch (Exception e) {
						System.out.print("PayrollDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return i;
				}//end delete function
				
				//----------------------------------------------------------------------------------------------
				public int deletePayrollByVoucherId(int ad_voucher_id){
					int i=0;
					PreparedStatement ps = null;
					try{
						String query="DELETE FROM ad_payroll WHERE ad_voucher_id=?";
						ps = con.prepareStatement(query);
						ps.setInt(1,ad_voucher_id);
						ps.executeUpdate();
					
						}catch (Exception e) {
						System.out.print("PayrollDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return i;
				}//end delete function
				//----------------------------------------------------------------------------------------------
				public int deletePayrollByBatchNo(int batch_no ){
					int i=0;
					PreparedStatement ps = null;
					try{
						String query="DELETE FROM ad_payroll WHERE batch_no=?";
						ps = con.prepareStatement(query);
						ps.setInt(1, batch_no);
						ps.executeUpdate();
					
						}catch (Exception e) {
						System.out.print("PayrollDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return i;
				}//end delete function
				//----------------------------------------------------------------------------------------------
				public int deletePayrollByDate(Date date){
					int i=0;
					PreparedStatement ps = null;
					try{
						String query="DELETE FROM ad_payroll WHERE payroll_date=?";
						ps = con.prepareStatement(query);
						ps.setDate(1, new java.sql.Date(date.getTime()));
						ps.executeUpdate();
					
						}catch (Exception e) {
						System.out.print("PayrollDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return i;
				}//end delete function
}
