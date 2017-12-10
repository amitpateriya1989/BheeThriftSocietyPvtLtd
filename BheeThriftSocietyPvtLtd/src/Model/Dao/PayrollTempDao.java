package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import Model.Bean.PayrollTempBean;
import Model.Bean.PayrollTempView;

public class PayrollTempDao {
	private Connection con = null;
	public PayrollTempDao() {
		con = DBConnection.getConnection();
		
	}
	
	public void addPayrollTemp(PayrollTempBean bean) {
		
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_payroll_temp( ad_member_id, created, createdby, updated, updatedby, isactive, payroll_date, membership_amt, "
					+ " diff_membership_amt, share_amt, diff_share_amt, thrift_amt, diff_thrift_amt, dcf_amt, diff_dcf_amt, main_loan_amt, diff_main_loan_amt, "
					+ " festival_loan_amt, diff_festival_loan_amt, rd_amt, diff_rd_amt, guest_house_amt, diff_guest_house_amt, excess_amt, total_amt, diff_total_amt, " 
					+ " batch_no, narration, ad_branch_id) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";

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
			ps.setString(28, bean.getNarration());
			ps.setInt(29, bean.getAd_branch_id());
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
	
	
	public void addExcessPayrollTemp(PayrollTempBean bean) {
		
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO excess_payroll_list( created, createdby, updated, " +
					" updatedby, isactive, batch_no, payroll_date, ad_society_no, thrift_amt, " +
					" dcf_amt, loan_amt, emergency_loan, excess_amt, total_amt, narration) " +
					" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			ps = con.prepareStatement(query);			
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, bean.isIsactive());
			ps.setInt(6, bean.getBatch_no());
			ps.setDate(7, new java.sql.Date(bean.getPayroll_date().getTime()));
			ps.setInt(8, bean.getAd_society_no());
			ps.setDouble(9, bean.getThrift_amt());
			ps.setDouble(10, bean.getDcf_amt());
			ps.setDouble(11, bean.getMain_loan_amt());
			ps.setDouble(12, bean.getFestival_loan_amt());
			ps.setDouble(13, bean.getExcess_amt());
			ps.setDouble(14, bean.getTotal_amt());
			ps.setString(15, bean.getNarration());
			
			ps.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
            
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		
		
		
	}
	
	//------------All payroll_temp--------------------------------------------------------------------------
		public ArrayList<PayrollTempBean> getAllPayrollTemp() {
			ArrayList<PayrollTempBean> bean = new ArrayList<PayrollTempBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_payroll_temp ";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					PayrollTempBean bean1 = new PayrollTempBean();
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setAd_payroll_temp_id(rs.getInt("ad_payroll_temp_id"));
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
					bean1.setNarration(rs.getString("narration"));
					bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
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
		
		//------------All payroll_temp--------------------------------------------------------------------------
				public ArrayList<PayrollTempBean> getAllExcessPayrollTemp() {
					ArrayList<PayrollTempBean> bean = new ArrayList<PayrollTempBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select excess_payroll_list_id, created, createdby, updated, " +
							" updatedby, isactive, batch_no, payroll_date, ad_society_no, " +
							" thrift_amt, dcf_amt, loan_amt, emergency_loan, excess_amt, " +
							" total_amt, narration from excess_payroll_list " +
							" order by payroll_date desc ";
					try {
						ps = con.prepareStatement(query);
						rs = ps.executeQuery();
						while (rs.next()) {
							PayrollTempBean bean1 = new PayrollTempBean();
							
							bean1.setAd_payroll_temp_id(rs.getInt("excess_payroll_list_id"));
							bean1.setCreated(rs.getDate("created"));
							bean1.setCreatedby(rs.getInt("createdby"));
							bean1.setDcf_amt(rs.getDouble("dcf_amt"));
							bean1.setExcess_amt(rs.getDouble("excess_amt"));
							bean1.setFestival_loan_amt(rs.getDouble("emergency_loan"));
							bean1.setIsactive(rs.getBoolean("isactive"));
							bean1.setMain_loan_amt(rs.getDouble("loan_amt"));
							bean1.setPayroll_date(rs.getDate("payroll_date"));
							bean1.setThrift_amt(rs.getDouble("thrift_amt"));
							bean1.setTotal_amt(rs.getDouble("total_amt"));
							bean1.setUpdated(rs.getDate("updated"));
							bean1.setUpdatedby(rs.getInt("updatedby"));
							bean1.setBatch_no(rs.getInt("batch_no"));
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
				
				
		
		//------------All payroll_temp between dates--------------------------------------------------------------------------
				public ArrayList<PayrollTempBean> getAllPayrollTempByDate(Date from, Date to) {
					ArrayList<PayrollTempBean> bean = new ArrayList<PayrollTempBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from ad_payroll_temp where payroll_date between ? AND ? ";
					try {
						ps = con.prepareStatement(query);
						ps.setDate(1, new java.sql.Date(from.getTime()));
						ps.setDate(2, new java.sql.Date(to.getTime()));
						rs = ps.executeQuery();
						while (rs.next()) {
							PayrollTempBean bean1 = new PayrollTempBean();
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
							bean1.setAd_payroll_temp_id(rs.getInt("ad_payroll_temp_id"));
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
							bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
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
				
				//------------All payroll_temp between dates- and member id-------------------------------------------------------------------------
				public ArrayList<PayrollTempBean> getAllPayrollTempByDateAndMemberId(Date from, Date to, int ad_member_id) {
					ArrayList<PayrollTempBean> bean = new ArrayList<PayrollTempBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from ad_payroll_temp where ad_member_id=? AND payroll_date between ? AND ? ";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_member_id);
						ps.setDate(2, new java.sql.Date(from.getTime()));
						ps.setDate(3, new java.sql.Date(to.getTime()));
						rs = ps.executeQuery();
						while (rs.next()) {
							PayrollTempBean bean1 = new PayrollTempBean();
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
							bean1.setAd_payroll_temp_id(rs.getInt("ad_payroll_temp_id"));
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
							bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
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
				
				//------------All payroll_temp with member id-------------------------------------------------------------------------
				public ArrayList<PayrollTempBean> getAllPayrollTempByMemberId( int ad_member_id) {
					ArrayList<PayrollTempBean> bean = new ArrayList<PayrollTempBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from ad_payroll_temp where ad_member_id=?  ";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_member_id);
						
						rs = ps.executeQuery();
						while (rs.next()) {
							PayrollTempBean bean1 = new PayrollTempBean();
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
							bean1.setAd_payroll_temp_id(rs.getInt("ad_payroll_temp_id"));
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
							bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
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
				
				//------------All payroll_temp with member id-------------------------------------------------------------------------
				public PayrollTempBean getMaxPayrollTempByMemberId( int ad_member_id) {
					PayrollTempBean bean1 =null;
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from ad_payroll_temp where  ad_payroll_temp_id=" +
							"(SELECT MAX(ad_payroll_temp_id) from ad_payroll_temp where ad_member_id=? ";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_member_id);
						
						rs = ps.executeQuery();
						while (rs.next()) {
							bean1= new PayrollTempBean();
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
							bean1.setAd_payroll_temp_id(rs.getInt("ad_payroll_temp_id"));
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
							bean1.setAd_branch_id(rs.getInt("ad_branch_id"));

						}
					}catch (Exception e) {
						System.out.print("VoucherDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
			        }
					return bean1;
				}
				
				//------------All payroll_temp with member id-------------------------------------------------------------------------
				public ArrayList<PayrollTempBean> getAllPayrollTempByBatchNo( int batch_no) {
					ArrayList<PayrollTempBean> bean = new ArrayList<PayrollTempBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from ad_payroll_temp where batch_no=?  ";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, batch_no);
						
						rs = ps.executeQuery();
						while (rs.next()) {
							PayrollTempBean bean1 = new PayrollTempBean();
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
							bean1.setAd_payroll_temp_id(rs.getInt("ad_payroll_temp_id"));
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
							bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
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
				
				//------------All payroll_temp with member id-------------------------------------------------------------------------
				public ArrayList<PayrollTempView> getAllTempPayrollViewByBatchNo( int batch_no,int start,int end) {
					ArrayList<PayrollTempView> bean = new ArrayList<PayrollTempView>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from temp_payroll_view where batch_no=? OFFSET ? LIMIT ? ";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, batch_no);
						ps.setInt(2, start);
						ps.setInt(3, end);
						rs = ps.executeQuery();
						while (rs.next()) {
							PayrollTempView bean1 = new PayrollTempView();
							bean1.setName(rs.getString("name"));
							bean1.setBranch(rs.getString("branch"));
							bean1.setAd_society_no(rs.getInt("ad_society_no"));
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
							bean1.setAd_payroll_temp_id(rs.getInt("ad_payroll_temp_id"));
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
							bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
							bean1.setBranch_code(rs.getString("branch_code"));
							bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
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
				//----------------------------------------------------------------------------------------------
				public int updatePayrollTemp(PayrollTempBean bean){
					int i=0;
					PreparedStatement ps = null;
					try {

						String query = "UPDATE ad_payroll_temp "
								+ " SET ad_member_id=?, updated=?, "
								+ " updatedby=?, isactive=?, payroll_date=?, membership_amt=?, diff_membership_amt=?, "
								+ " share_amt=?, diff_share_amt=?, thrift_amt=?, diff_thrift_amt=?, dcf_amt=?, diff_dcf_amt=?, main_loan_amt=?, diff_main_loan_amt=?, "
								+ " festival_loan_amt=?, diff_festival_loan_amt=?, rd_amt=?, diff_rd_amt=?, guest_house_amt=?, diff_guest_house_amt=?, excess_amt=?, "
								+ " total_amt=?, diff_total_amt=?, batch_no=?, ad_branch_id=? WHERE ad_payroll_temp_id=? ";
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
						ps.setInt(26, bean.getAd_branch_id());
						ps.setInt(27, bean.getAd_payroll_temp_id());
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
				public int deletePayrollTemp(PayrollTempBean bean){
					int i=0;
					PreparedStatement ps = null;
					try{
						String query="DELETE FROM ad_payroll_temp WHERE ad_payroll_temp_id=?";
						ps = con.prepareStatement(query);
						ps.setInt(1, bean.getAd_payroll_temp_id());
						ps.executeUpdate();
					
						}catch (Exception e) {
						System.out.print("payroll_tempDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return i;
				}//end delete function
				//----------------------------------------------------------------------------------------------
				public int deletePayrollTemp(int ad_payroll_temp_id){
					int i=0;
					PreparedStatement ps = null;
					try{
						String query="DELETE FROM ad_payroll_temp WHERE ad_payroll_temp_id=?";
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_payroll_temp_id);
						i=ps.executeUpdate();
					
						}catch (Exception e) {
						System.out.print("payroll_tempDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return i;
				}//end delete function

				//----------------------------------------------------------------------------------------------
				public int deletePayrollTempByDate(Date date){
					int i=0;
					PreparedStatement ps = null;
					try{
						String query="DELETE FROM ad_payroll_temp WHERE payroll_date=?";
						ps = con.prepareStatement(query);
						ps.setDate(1, new java.sql.Date(date.getTime()));
						ps.executeUpdate();
					
						}catch (Exception e) {
						System.out.print("payroll_tempDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return i;
				}//end delete function
				
				
				//----------------------------------------------------------------------------------------------
				public int deletePayrollTempByBatchNo(int batch_no){
					int i=0;
					PreparedStatement ps = null;
					try{
						String query="DELETE FROM ad_payroll_temp WHERE batch_no=?";
						ps = con.prepareStatement(query);
						ps.setInt(1, batch_no);
						ps.executeUpdate();
					
						}catch (Exception e) {
						System.out.print("payroll_tempDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return i;
				}//end delete function
}
