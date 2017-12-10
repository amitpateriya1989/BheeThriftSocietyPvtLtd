package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import Model.Bean.PayrollAdviseBean;

public class PayrollAdviceDao {
	private Connection con = null;
	public PayrollAdviceDao() {
		con = DBConnection.getConnection();
		
	}
	
	//------------All payroll_temp--------------------------------------------------------------------------
			public ArrayList<PayrollAdviseBean> getAllPayrollAdvice() {
				ArrayList<PayrollAdviseBean> bean = new ArrayList<PayrollAdviseBean>();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "select * from payroll_advise ";
				try {
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						PayrollAdviseBean bean1 = new PayrollAdviseBean();
						bean1.setAd_member_id(rs.getInt("ad_member_id"));
						bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
						bean1.setAd_designation_type_id(rs.getInt("ad_designation_type_id"));
						bean1.setAd_member_status_id(rs.getInt("ad_member_status_id"));
						bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
						bean1.setAd_salutation_id(rs.getInt("ad_salutation_id"));
						bean1.setAd_society_no(rs.getInt("ad_society_no"));
						bean1.setBranch(rs.getString("branch"));
						bean1.setDcf(rs.getDouble("dcf"));
						bean1.setFestivalloan_emi(rs.getDouble("festivalloan_emi"));
						bean1.setFgds_fund(rs.getDouble("fgds_fund"));
						bean1.setIsactive(rs.getBoolean("isactive"));
						bean1.setMainloan_emi(rs.getDouble("mainloan_emi"));
						bean1.setMember_status(rs.getString("member_status"));
						bean1.setMembership_fees(rs.getDouble("membership_fees"));
						bean1.setName(rs.getString("name"));
						bean1.setSalutation(rs.getString("salutation"));
						bean1.setSaving_ac_no(rs.getString("saving_ac_no"));
						bean1.setShare(rs.getDouble("share"));
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
			
			

			//--------------------------------------------------------------------------------------
			
			//------------All payroll_temp--------------------------------------------------------------------------
			public ArrayList<PayrollAdviseBean> getAllPayrollAdvice(int ad_branch_id) {
				ArrayList<PayrollAdviseBean> bean = new ArrayList<PayrollAdviseBean>();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "select * from payroll_advise where ad_branch_id=?  ";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_branch_id);
					rs = ps.executeQuery();
					while (rs.next()) {
						PayrollAdviseBean bean1 = new PayrollAdviseBean();
						bean1.setAd_member_id(rs.getInt("ad_member_id"));
						bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
						bean1.setAd_designation_type_id(rs.getInt("ad_designation_type_id"));
						bean1.setAd_member_status_id(rs.getInt("ad_member_status_id"));
						bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
						bean1.setAd_salutation_id(rs.getInt("ad_salutation_id"));
						bean1.setAd_society_no(rs.getInt("ad_society_no"));
						bean1.setBranch(rs.getString("branch"));
						bean1.setDcf(rs.getDouble("dcf"));
						bean1.setFestivalloan_emi(rs.getDouble("festivalloan_emi"));
						bean1.setFgds_fund(rs.getDouble("fgds_fund"));
						bean1.setIsactive(rs.getBoolean("isactive"));
						bean1.setMainloan_emi(rs.getDouble("mainloan_emi"));
						bean1.setMember_status(rs.getString("member_status"));
						bean1.setMembership_fees(rs.getDouble("membership_fees"));
						bean1.setName(rs.getString("name"));
						bean1.setSalutation(rs.getString("salutation"));
						bean1.setSaving_ac_no(rs.getString("saving_ac_no"));
						bean1.setShare(rs.getDouble("share"));
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
					public ArrayList<PayrollAdviseBean> getAllPayrollAdviceByDate(Date from, Date to) {
						ArrayList<PayrollAdviseBean> bean = new ArrayList<PayrollAdviseBean>();
						PreparedStatement ps=null;
						ResultSet rs=null;
						String query = "select * from payroll_advise where payroll_date between ? AND ? ";
						try {
							ps = con.prepareStatement(query);
							ps.setDate(1, new java.sql.Date(from.getTime()));
							ps.setDate(2, new java.sql.Date(to.getTime()));
							rs = ps.executeQuery();
							while (rs.next()) {
								PayrollAdviseBean bean1 = new PayrollAdviseBean();
								bean1.setAd_member_id(rs.getInt("ad_member_id"));
								bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
								bean1.setAd_designation_type_id(rs.getInt("ad_designation_type_id"));
								bean1.setAd_member_status_id(rs.getInt("ad_member_status_id"));
								bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
								bean1.setAd_salutation_id(rs.getInt("ad_salutation_id"));
								bean1.setAd_society_no(rs.getInt("ad_society_no"));
								bean1.setBranch(rs.getString("branch"));
								bean1.setDcf(rs.getDouble("dcf"));
								bean1.setFestivalloan_emi(rs.getDouble("festivalloan_emi"));
								bean1.setFgds_fund(rs.getDouble("fgds_fund"));
								bean1.setIsactive(rs.getBoolean("isactive"));
								bean1.setMainloan_emi(rs.getDouble("mainloan_emi"));
								bean1.setMember_status(rs.getString("member_status"));
								bean1.setMembership_fees(rs.getDouble("membership_fees"));
								bean1.setName(rs.getString("name"));
								bean1.setSalutation(rs.getString("salutation"));
								bean1.setSaving_ac_no(rs.getString("saving_ac_no"));
								bean1.setShare(rs.getDouble("share"));
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
					
					//------------All payroll_temp between dates- and member id-------------------------------------------------------------------------
					public ArrayList<PayrollAdviseBean> getAllPayrollAdviceByDateAndMemberId(Date from, Date to, int ad_member_id) {
						ArrayList<PayrollAdviseBean> bean = new ArrayList<PayrollAdviseBean>();
						PreparedStatement ps=null;
						ResultSet rs=null;
						String query = "select * from payroll_advise where ad_member_id=? AND payroll_date between ? AND ? ";
						try {
							ps = con.prepareStatement(query);
							ps.setInt(1, ad_member_id);
							ps.setDate(2, new java.sql.Date(from.getTime()));
							ps.setDate(3, new java.sql.Date(to.getTime()));
							rs = ps.executeQuery();
							while (rs.next()) {
								PayrollAdviseBean bean1 = new PayrollAdviseBean();
								bean1.setAd_member_id(rs.getInt("ad_member_id"));
								bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
								bean1.setAd_designation_type_id(rs.getInt("ad_designation_type_id"));
								bean1.setAd_member_status_id(rs.getInt("ad_member_status_id"));
								bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
								bean1.setAd_salutation_id(rs.getInt("ad_salutation_id"));
								bean1.setAd_society_no(rs.getInt("ad_society_no"));
								bean1.setBranch(rs.getString("branch"));
								bean1.setDcf(rs.getDouble("dcf"));
								bean1.setFestivalloan_emi(rs.getDouble("festivalloan_emi"));
								bean1.setFgds_fund(rs.getDouble("fgds_fund"));
								bean1.setIsactive(rs.getBoolean("isactive"));
								bean1.setMainloan_emi(rs.getDouble("mainloan_emi"));
								bean1.setMember_status(rs.getString("member_status"));
								bean1.setMembership_fees(rs.getDouble("membership_fees"));
								bean1.setName(rs.getString("name"));
								bean1.setSalutation(rs.getString("salutation"));
								bean1.setSaving_ac_no(rs.getString("saving_ac_no"));
								bean1.setShare(rs.getDouble("share"));
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
					
					//------------All payroll_temp with member id-------------------------------------------------------------------------
					public PayrollAdviseBean getAllPayrollAdviceByMemberId( int ad_member_id) {
						PayrollAdviseBean bean1 = new PayrollAdviseBean();
						PreparedStatement ps=null;
						ResultSet rs=null;
						String query = "select * from payroll_advise where ad_member_id=?  ";
						try {
							ps = con.prepareStatement(query);
							ps.setInt(1, ad_member_id);
							
							rs = ps.executeQuery();
							while (rs.next()) {
								
								bean1.setAd_member_id(rs.getInt("ad_member_id"));
								bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
								bean1.setAd_designation_type_id(rs.getInt("ad_designation_type_id"));
								bean1.setAd_member_status_id(rs.getInt("ad_member_status_id"));
								bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
								bean1.setAd_salutation_id(rs.getInt("ad_salutation_id"));
								bean1.setAd_society_no(rs.getInt("ad_society_no"));
								bean1.setBranch(rs.getString("branch"));
								bean1.setDcf(rs.getDouble("dcf"));
								bean1.setFestivalloan_emi(rs.getDouble("festivalloan_emi"));
								bean1.setFgds_fund(rs.getDouble("fgds_fund"));
								bean1.setIsactive(rs.getBoolean("isactive"));
								bean1.setMainloan_emi(rs.getDouble("mainloan_emi"));
								bean1.setMember_status(rs.getString("member_status"));
								bean1.setMembership_fees(rs.getDouble("membership_fees"));
								bean1.setName(rs.getString("name"));
								bean1.setSalutation(rs.getString("salutation"));
								bean1.setSaving_ac_no(rs.getString("saving_ac_no"));
								bean1.setShare(rs.getDouble("share"));
								bean1.setAd_society_no(rs.getInt("ad_society_no"));
								
								
								
								

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
}
