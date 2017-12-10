package Model.Dao;
import Model.Bean.SalaryBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class SalaryDao {
	private Connection con = null;

	public SalaryDao() {
		con = DBConnection.getConnection();
	}

	public void addSalary(SalaryBean bean) {
		PreparedStatement ps = null;
		try {

			String query = "INSERT INTO ad_pay_detail(" +
					" month_name, ad_employee_id, ad_grade_id,working_days, worked_days, holidays, tot_absent, payble_days," +
					"basic, da, convance, hra, medical, allowances, other_amt, gross_amt," +
					"pf_amt, pt_amt, esi_amt, tds_amt, other_deduction, gross_deduction, " +
					" net_salary,created,monthly_pay,status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?) ";
			ps = con.prepareStatement(query);
			ps.setString(1, bean.getMonth_name());
			ps.setInt(2, bean.getAd_employee_id());
			ps.setInt(3, bean.getAd_grade_id());
			ps.setInt(4,bean.getWorking_days());
			ps.setInt(5, bean.getWorked_days());
			ps.setInt(6, bean.getHolidays());
			ps.setInt(7, bean.getTot_absent());
			ps.setInt(8, bean.getPayble_days());
			ps.setDouble(9, bean.getBasic());
			ps.setDouble(10, bean.getDa());
			ps.setDouble(11, bean.getConvey());
			ps.setDouble(12, bean.getHra());
			ps.setDouble(13, bean.getMdcl());
			ps.setDouble(14, bean.getAlwnc());
			ps.setDouble(15, bean.getOther_amt());
			ps.setDouble(16, bean.getGross_amt());
			ps.setDouble(17, bean.getPf_amt());
			ps.setDouble(18, bean.getPt_amt());
			ps.setDouble(19, bean.getEsi_amt());
			ps.setDouble(20, bean.getTds_amt());
			ps.setDouble(21, bean.getOther_deduction());
			ps.setDouble(22, bean.getGross_deduction());
			ps.setDouble(23, bean.getNet_salary());
			ps.setTimestamp(24, new java.sql.Timestamp(bean.getCreated().getTime()));
			ps.setDouble(25, bean.getMonthly_pay());
			ps.setBoolean(26, bean.getStatus());
			
			//System.out.print(ps);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
	}

	//(month)
	public SalaryBean getSalaryByMonthYear(String month) {
		SalaryBean bean =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from ad_pay_detail where month_name=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, month);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean =  new SalaryBean();
				bean.setMonth_name(rs.getString("month_name"));
				bean.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean.setAd_grade_id(rs.getInt("ad_grade_id"));
				bean.setWorking_days(rs.getInt("working_days"));
				bean.setWorked_days(rs.getInt("worked_days"));
				bean.setHolidays(rs.getInt("holidays"));
				bean.setTot_absent(rs.getInt("tot_absent"));
				bean.setPayble_days(rs.getInt("payble_days"));
				bean.setBasic(rs.getDouble("basic"));
				bean.setDa(rs.getDouble("da"));
				bean.setConvey(rs.getDouble("convance"));
				bean.setHra(rs.getDouble("hra"));
				bean.setMdcl(rs.getDouble("medical"));
				bean.setAlwnc(rs.getDouble("allowances"));
				bean.setOther_amt(rs.getDouble("other_amt"));
				bean.setGross_amt(rs.getDouble("gross_amt"));
				bean.setPf_amt(rs.getDouble("pf_amt"));
				bean.setPt_amt(rs.getDouble("pt_amt"));
				bean.setEsi_amt(rs.getDouble("esi_amt"));
				bean.setTds_amt(rs.getDouble("tds_amt"));
				bean.setOther_deduction(rs.getDouble("other_deduction"));
				bean.setGross_deduction(rs.getDouble("gross_deduction"));
				bean.setNet_salary(rs.getDouble("net_salary"));
				bean.setCreated(rs.getTimestamp("created"));
				bean.setMonthly_pay(rs.getDouble("monthly_pay"));
				bean.setStatus(rs.getBoolean("status"));
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	//=============
	public int chkSalaryByMonthYear(String month) {
		int result=0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select count(*) as cnt from ad_pay_detail where month_name=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, month);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				result=rs.getInt("cnt");
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return result;
	}
	
	
	public SalaryBean getSalaryById(SalaryBean bean) {
		SalaryBean bean1 = new SalaryBean();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from ad_pay_detail where ad_pay_detail_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_pay_detail_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean.setMonth_name(rs.getString("month_name"));
				bean.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean.setAd_grade_id(rs.getInt("ad_grade_id"));
				bean.setWorking_days(rs.getInt("working_days"));
				bean.setWorked_days(rs.getInt("worked_days"));
				bean.setHolidays(rs.getInt("holidays"));
				bean.setTot_absent(rs.getInt("tot_absent"));
				bean.setPayble_days(rs.getInt("payble_days"));
				bean.setBasic(rs.getDouble("basic"));
				bean.setDa(rs.getDouble("da"));
				bean.setConvey(rs.getDouble("convance"));
				bean.setHra(rs.getDouble("hra"));
				bean.setMdcl(rs.getDouble("medical"));
				bean.setAlwnc(rs.getDouble("allowances"));
				bean.setOther_amt(rs.getDouble("other_amt"));
				bean.setGross_amt(rs.getDouble("gross_amt"));
				bean.setPf_amt(rs.getDouble("pf_amt"));
				bean.setPt_amt(rs.getDouble("pt_amt"));
				bean.setEsi_amt(rs.getDouble("esi_amt"));
				bean.setTds_amt(rs.getDouble("tds_amt"));
				bean.setOther_deduction(rs.getDouble("other_deduction"));
				bean.setGross_deduction(rs.getDouble("gross_deduction"));
				bean.setNet_salary(rs.getDouble("new_salary"));
				bean.setCreated(rs.getTimestamp("created"));
				bean.setMonthly_pay(rs.getDouble("monthly_pay"));
				bean.setStatus(rs.getBoolean("status"));
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
	
	public SalaryBean getSalaryIdByemp_id(int  emp_id) {
		SalaryBean bean = new SalaryBean();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from ad_pay_detail where ad_employee_id=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, emp_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean.setMonth_name(rs.getString("month_name"));
				bean.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean.setAd_grade_id(rs.getInt("ad_grade_id"));
				bean.setWorking_days(rs.getInt("working_days"));
				bean.setWorked_days(rs.getInt("worked_days"));
				bean.setHolidays(rs.getInt("holidays"));
				bean.setTot_absent(rs.getInt("tot_absent"));
				bean.setPayble_days(rs.getInt("payble_days"));
				bean.setBasic(rs.getDouble("basic"));
				bean.setDa(rs.getDouble("da"));
				bean.setConvey(rs.getDouble("convance"));
				bean.setHra(rs.getDouble("hra"));
				bean.setMdcl(rs.getDouble("medical"));
				bean.setAlwnc(rs.getDouble("allowances"));
				bean.setOther_amt(rs.getDouble("other_amt"));
				bean.setGross_amt(rs.getDouble("gross_amt"));
				bean.setPf_amt(rs.getDouble("pf_amt"));
				bean.setPt_amt(rs.getDouble("pt_amt"));
				bean.setEsi_amt(rs.getDouble("esi_amt"));
				bean.setTds_amt(rs.getDouble("tds_amt"));
				bean.setOther_deduction(rs.getDouble("other_deduction"));
				bean.setGross_deduction(rs.getDouble("gross_deduction"));
				bean.setNet_salary(rs.getDouble("new_salary"));
				bean.setCreated(rs.getTimestamp("created"));
				bean.setMonthly_pay(rs.getDouble("monthly_pay"));
				bean.setStatus(rs.getBoolean("status"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	
	
	public SalaryBean getSalaryById(int ad_Salary_id) {
		SalaryBean bean = new SalaryBean();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from ad_pay_detail where ad_pay_detail_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_Salary_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean.setMonth_name(rs.getString("month_name"));
				bean.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean.setAd_grade_id(rs.getInt("ad_grade_id"));
				bean.setWorking_days(rs.getInt("working_days"));
				bean.setWorked_days(rs.getInt("worked_days"));
				bean.setHolidays(rs.getInt("holidays"));
				bean.setTot_absent(rs.getInt("tot_absent"));
				bean.setPayble_days(rs.getInt("payble_days"));
				bean.setBasic(rs.getDouble("basic"));
				bean.setDa(rs.getDouble("da"));
				bean.setConvey(rs.getDouble("convance"));
				bean.setHra(rs.getDouble("hra"));
				bean.setMdcl(rs.getDouble("medical"));
				bean.setAlwnc(rs.getDouble("allowances"));
				bean.setOther_amt(rs.getDouble("other_amt"));
				bean.setGross_amt(rs.getDouble("gross_amt"));
				bean.setPf_amt(rs.getDouble("pf_amt"));
				bean.setPt_amt(rs.getDouble("pt_amt"));
				bean.setEsi_amt(rs.getDouble("esi_amt"));
				bean.setTds_amt(rs.getDouble("tds_amt"));
				bean.setOther_deduction(rs.getDouble("other_deduction"));
				bean.setGross_deduction(rs.getDouble("gross_deduction"));
				bean.setNet_salary(rs.getDouble("new_salary"));
				bean.setCreated(rs.getTimestamp("created"));
				bean.setMonthly_pay(rs.getDouble("monthly_pay"));
				bean.setStatus(rs.getBoolean("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	

	public ArrayList<SalaryBean> getAllSalary(String month,String status) {
		ArrayList<SalaryBean> bean1 = new ArrayList<SalaryBean>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from ad_pay_detail where month_name=? AND status=? ORDER BY ad_grade_id";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, month);
			ps.setString(2, status);
			System.out.println(month+"--"+status);
			rs = ps.executeQuery();
			while (rs.next()) {
				SalaryBean bean = new SalaryBean();
				bean.setMonth_name(rs.getString("month_name"));
				bean.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean.setAd_grade_id(rs.getInt("ad_grade_id"));
				bean.setWorking_days(rs.getInt("working_days"));
				bean.setWorked_days(rs.getInt("worked_days"));
				bean.setHolidays(rs.getInt("holidays"));
				bean.setTot_absent(rs.getInt("tot_absent"));
				bean.setPayble_days(rs.getInt("payble_days"));
				bean.setBasic(rs.getDouble("basic"));
				bean.setDa(rs.getDouble("da"));
				bean.setConvey(rs.getDouble("convance"));
				bean.setHra(rs.getDouble("hra"));
				bean.setMdcl(rs.getDouble("medical"));
				bean.setAlwnc(rs.getDouble("allowances"));
				bean.setOther_amt(rs.getDouble("other_amt"));
				bean.setGross_amt(rs.getDouble("gross_amt"));
				bean.setPf_amt(rs.getDouble("pf_amt"));
				bean.setPt_amt(rs.getDouble("pt_amt"));
				bean.setEsi_amt(rs.getDouble("esi_amt"));
				bean.setTds_amt(rs.getDouble("tds_amt"));
				bean.setOther_deduction(rs.getDouble("other_deduction"));
				bean.setGross_deduction(rs.getDouble("gross_deduction"));
				bean.setNet_salary(rs.getDouble("net_salary"));
				bean.setCreated(rs.getTimestamp("created"));
				bean.setMonthly_pay(rs.getDouble("monthly_pay"));
				bean.setStatus(rs.getBoolean("status"));
				bean1.add(bean);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
	//------------------------------
	public ArrayList<SalaryBean> getpt(String month,int ad_employee_id,String status) {
		ArrayList<SalaryBean> bean1 = new ArrayList<SalaryBean>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select month_name,pt_amt,ad_employee_id from ad_pay_detail where month_name=? AND status=? and ad_employee_id=? ORDER BY month_name";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, month);
			ps.setString(2, status);
			ps.setInt(3, ad_employee_id);
		
			rs = ps.executeQuery();
			while (rs.next()) {
				SalaryBean bean = new SalaryBean();
				bean.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean.setMonth_name(rs.getString("month_name"));			
				bean.setPt_amt(rs.getDouble("pt_amt"));				
				bean1.add(bean);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
	
	
	//---------------------------
	//------------------------------
		public ArrayList<SalaryBean> getpt(String month,String status) {
			ArrayList<SalaryBean> bean1 = new ArrayList<SalaryBean>();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String query = "select month_name,pt_amt,ad_employee_id from ad_pay_detail where month_name=? AND status=?  ORDER BY month_name";
			try {
				ps = con.prepareStatement(query);
				ps.setString(1, month);
				ps.setString(2, status);
			
			
				rs = ps.executeQuery();
				while (rs.next()) {
					SalaryBean bean = new SalaryBean();
					bean.setAd_employee_id(rs.getInt("ad_employee_id"));
					bean.setMonth_name(rs.getString("month_name"));			
					bean.setPt_amt(rs.getDouble("pt_amt"));				
					bean1.add(bean);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
		
		
		//---------------------------
		public ArrayList<SalaryBean> getpt(int ad_employee_id ,String status) {
			ArrayList<SalaryBean> bean1 = new ArrayList<SalaryBean>();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String query = "select month_name,pt_amt,ad_employee_id from ad_pay_detail where ad_employee_id=? AND status=?  ORDER BY month_name";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_employee_id);
				ps.setString(2, status);
			
			
				rs = ps.executeQuery();
				while (rs.next()) {
					SalaryBean bean = new SalaryBean();
					bean.setAd_employee_id(rs.getInt("ad_employee_id"));
					bean.setMonth_name(rs.getString("month_name"));			
					bean.setPt_amt(rs.getDouble("pt_amt"));				
					bean1.add(bean);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//------------------------
		public ArrayList<SalaryBean> getpt(String status) {
			ArrayList<SalaryBean> bean1 = new ArrayList<SalaryBean>();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String query = "select month_name,pt_amt,ad_employee_id from ad_pay_detail where  status=?  ORDER BY month_name";
			try {
				ps = con.prepareStatement(query);
				ps.setString(1, status.trim());
			    rs = ps.executeQuery();
				while (rs.next()) {
					SalaryBean bean = new SalaryBean();
					bean.setAd_employee_id(rs.getInt("ad_employee_id"));
					bean.setMonth_name(rs.getString("month_name"));			
					bean.setPt_amt(rs.getDouble("pt_amt"));				
					bean1.add(bean);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//------------------------
		
		//------------------------------
		public ArrayList<SalaryBean> getpf(String month,int ad_employee_id,String status) {
			ArrayList<SalaryBean> bean1 = new ArrayList<SalaryBean>();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String query = "select month_name,pf_amt,ad_employee_id from ad_pay_detail where month_name=? AND status=? and ad_employee_id=? ORDER BY month_name";
			try {
				ps = con.prepareStatement(query);
				ps.setString(1, month);
				ps.setString(2, status);
				ps.setInt(3, ad_employee_id);
			
				rs = ps.executeQuery();
				while (rs.next()) {
					SalaryBean bean = new SalaryBean();
					bean.setAd_employee_id(rs.getInt("ad_employee_id"));
					bean.setMonth_name(rs.getString("month_name"));			
					bean.setPt_amt(rs.getDouble("pf_amt"));				
					bean1.add(bean);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
		
		
		//---------------------------
		//------------------------------
			public ArrayList<SalaryBean> getpf(String month,String status) {
				ArrayList<SalaryBean> bean1 = new ArrayList<SalaryBean>();
				PreparedStatement ps = null;
				ResultSet rs = null;
				String query = "select month_name,pf_amt,ad_employee_id from ad_pay_detail where month_name=? AND status=?  ORDER BY month_name";
				try {
					ps = con.prepareStatement(query);
					ps.setString(1, month);
					ps.setString(2, status);
				    rs = ps.executeQuery();
					while (rs.next()) {
						SalaryBean bean = new SalaryBean();
						bean.setAd_employee_id(rs.getInt("ad_employee_id"));
						bean.setMonth_name(rs.getString("month_name"));			
						bean.setPt_amt(rs.getDouble("pf_amt"));				
						bean1.add(bean);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean1;
			}
			
			
			//---------------------------
			public ArrayList<SalaryBean> getpf(int ad_employee_id ,String status) {
				ArrayList<SalaryBean> bean1 = new ArrayList<SalaryBean>();
				PreparedStatement ps = null;
				ResultSet rs = null;
				String query = "select month_name, pf_amt, ad_employee_id from ad_pay_detail where ad_employee_id=? AND status=?  ORDER BY month_name";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_employee_id);
					ps.setString(2, status);
				    rs = ps.executeQuery();
					while (rs.next()) {
						SalaryBean bean = new SalaryBean();
						bean.setAd_employee_id(rs.getInt("ad_employee_id"));
						bean.setMonth_name(rs.getString("month_name"));			
						bean.setPt_amt(rs.getDouble("pf_amt"));				
						bean1.add(bean);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean1;
			}
		//------------------------
			public ArrayList<SalaryBean> getpf(String status) {
				ArrayList<SalaryBean> bean1 = new ArrayList<SalaryBean>();
				PreparedStatement ps = null;
				ResultSet rs = null;
				String query = "select month_name,pf_amt,ad_employee_id from ad_pay_detail where  status=?  ORDER BY month_name";
				try {
					ps = con.prepareStatement(query);
					ps.setString(1, status.trim());
					rs = ps.executeQuery();
					while (rs.next()) {
						SalaryBean bean = new SalaryBean();
						bean.setAd_employee_id(rs.getInt("ad_employee_id"));
						bean.setMonth_name(rs.getString("month_name"));			
						bean.setPt_amt(rs.getDouble("pf_amt"));				
						bean1.add(bean);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean1;
			}
		//------------------------
			
			//------------------------------
			public ArrayList<SalaryBean> getsalary(String month,int ad_employee_id,String status) {
				ArrayList<SalaryBean> bean1 = new ArrayList<SalaryBean>();
				PreparedStatement ps = null;
				ResultSet rs = null;
				String query = "select apd.*,ag.grade_name from ad_pay_detail apd " +				
						" left join ad_grade ag ON apd.ad_grade_id=ag.ad_grade_id " +
						" where apd.month_name=? AND apd.status=? and apd.ad_employee_id=? ORDER BY apd.month_name";
				try {
					ps = con.prepareStatement(query);
					ps.setString(1, month);
					ps.setString(2, status);
					ps.setInt(3, ad_employee_id);
				
					rs = ps.executeQuery();
					while (rs.next()) {
						SalaryBean bean = new SalaryBean();
						bean.setMonth_name(rs.getString("month_name"));
						bean.setAd_employee_id(rs.getInt("ad_employee_id"));
						bean.setAd_grade_id(rs.getInt("ad_grade_id"));
						bean.setWorking_days(rs.getInt("working_days"));
						bean.setWorked_days(rs.getInt("worked_days"));
						bean.setHolidays(rs.getInt("holidays"));
						bean.setTot_absent(rs.getInt("tot_absent"));
						bean.setPayble_days(rs.getInt("payble_days"));
						bean.setBasic(rs.getDouble("basic"));
						bean.setDa(rs.getDouble("da"));
						bean.setConvey(rs.getDouble("convance"));
						bean.setHra(rs.getDouble("hra"));
						bean.setMdcl(rs.getDouble("medical"));
						bean.setAlwnc(rs.getDouble("allowances"));
						bean.setOther_amt(rs.getDouble("other_amt"));
						bean.setGross_amt(rs.getDouble("gross_amt"));
						bean.setPf_amt(rs.getDouble("pf_amt"));
						bean.setPt_amt(rs.getDouble("pt_amt"));
						bean.setEsi_amt(rs.getDouble("esi_amt"));
						bean.setTds_amt(rs.getDouble("tds_amt"));
						bean.setOther_deduction(rs.getDouble("other_deduction"));
						bean.setGross_deduction(rs.getDouble("gross_deduction"));
						bean.setNet_salary(rs.getDouble("net_salary"));
						bean.setCreated(rs.getTimestamp("created"));
						bean.setMonthly_pay(rs.getDouble("monthly_pay"));
						bean.setStatus(rs.getBoolean("status"));	
						bean.setGradename("grade_name");
						bean1.add(bean);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean1;
			}
			
			
			//---------------------------
			//------------------------------
				public ArrayList<SalaryBean> getsalary(String month,String status) {
					ArrayList<SalaryBean> bean1 = new ArrayList<SalaryBean>();
					PreparedStatement ps = null;
					ResultSet rs = null;
					String query = " select apd.*,ag.grade_name from ad_pay_detail apd " +				
							" left join ad_grade ag ON apd.ad_grade_id=ag.ad_grade_id " +
							" where apd.month_name=? AND apd.status=?  ORDER BY apd.month_name";
					try {
						ps = con.prepareStatement(query);
						ps.setString(1, month);
						ps.setString(2, status);
						rs = ps.executeQuery();
						while (rs.next()) {
							SalaryBean bean = new SalaryBean();
							bean.setMonth_name(rs.getString("month_name"));
							bean.setAd_employee_id(rs.getInt("ad_employee_id"));
							bean.setAd_grade_id(rs.getInt("ad_grade_id"));
							bean.setWorking_days(rs.getInt("working_days"));
							bean.setWorked_days(rs.getInt("worked_days"));
							bean.setHolidays(rs.getInt("holidays"));
							bean.setTot_absent(rs.getInt("tot_absent"));
							bean.setPayble_days(rs.getInt("payble_days"));
							bean.setBasic(rs.getDouble("basic"));
							bean.setDa(rs.getDouble("da"));
							bean.setConvey(rs.getDouble("convance"));
							bean.setHra(rs.getDouble("hra"));
							bean.setMdcl(rs.getDouble("medical"));
							bean.setAlwnc(rs.getDouble("allowances"));
							bean.setOther_amt(rs.getDouble("other_amt"));
							bean.setGross_amt(rs.getDouble("gross_amt"));
							bean.setPf_amt(rs.getDouble("pf_amt"));
							bean.setPt_amt(rs.getDouble("pt_amt"));
							bean.setEsi_amt(rs.getDouble("esi_amt"));
							bean.setTds_amt(rs.getDouble("tds_amt"));
							bean.setOther_deduction(rs.getDouble("other_deduction"));
							bean.setGross_deduction(rs.getDouble("gross_deduction"));
							bean.setNet_salary(rs.getDouble("net_salary"));
							bean.setCreated(rs.getTimestamp("created"));
							bean.setMonthly_pay(rs.getDouble("monthly_pay"));
							bean.setStatus(rs.getBoolean("status"));	
							bean.setGradename("grade_name");
							bean1.add(bean);

						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return bean1;
				}
				
				
				//---------------------------
				public ArrayList<SalaryBean> getsalary(int ad_employee_id ,String status) {
					ArrayList<SalaryBean> bean1 = new ArrayList<SalaryBean>();
					PreparedStatement ps = null;
					ResultSet rs = null;
					String query = " select apd.*,ag.grade_name from ad_pay_detail apd " +				
						" left join ad_grade ag ON apd.ad_grade_id=ag.ad_grade_id " +
							"where apd.ad_employee_id=? AND apd.status=?  ORDER BY apd.month_name";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_employee_id);
						ps.setString(2, status);
					
					
						rs = ps.executeQuery();
						while (rs.next()) {
							SalaryBean bean = new SalaryBean();
							bean.setMonth_name(rs.getString("month_name"));
							bean.setAd_employee_id(rs.getInt("ad_employee_id"));
							bean.setAd_grade_id(rs.getInt("ad_grade_id"));
							bean.setWorking_days(rs.getInt("working_days"));
							bean.setWorked_days(rs.getInt("worked_days"));
							bean.setHolidays(rs.getInt("holidays"));
							bean.setTot_absent(rs.getInt("tot_absent"));
							bean.setPayble_days(rs.getInt("payble_days"));
							bean.setBasic(rs.getDouble("basic"));
							bean.setDa(rs.getDouble("da"));
							bean.setConvey(rs.getDouble("convance"));
							bean.setHra(rs.getDouble("hra"));
							bean.setMdcl(rs.getDouble("medical"));
							bean.setAlwnc(rs.getDouble("allowances"));
							bean.setOther_amt(rs.getDouble("other_amt"));
							bean.setGross_amt(rs.getDouble("gross_amt"));
							bean.setPf_amt(rs.getDouble("pf_amt"));
							bean.setPt_amt(rs.getDouble("pt_amt"));
							bean.setEsi_amt(rs.getDouble("esi_amt"));
							bean.setTds_amt(rs.getDouble("tds_amt"));
							bean.setOther_deduction(rs.getDouble("other_deduction"));
							bean.setGross_deduction(rs.getDouble("gross_deduction"));
							bean.setNet_salary(rs.getDouble("net_salary"));
							bean.setCreated(rs.getTimestamp("created"));
							bean.setMonthly_pay(rs.getDouble("monthly_pay"));
							bean.setStatus(rs.getBoolean("status"));
							bean.setGradename("grade_name");	
							bean1.add(bean);

						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return bean1;
				}
			//------------------------
				public ArrayList<SalaryBean> getsalary(String status) {
					ArrayList<SalaryBean> bean1 = new ArrayList<SalaryBean>();
					PreparedStatement ps = null;
					ResultSet rs = null;
					String query = " select apd.*,ag.grade_name from ad_pay_detail apd " +				
						" left join ad_grade ag ON apd.ad_grade_id=ag.ad_grade_id " +
							"where  apd.status=?  ORDER BY apd.month_name";
					try {
						ps = con.prepareStatement(query);
						ps.setString(1, status.trim());
						rs = ps.executeQuery();
						while (rs.next()) {
							SalaryBean bean = new SalaryBean();
							bean.setMonth_name(rs.getString("month_name"));
							bean.setAd_employee_id(rs.getInt("ad_employee_id"));
							bean.setAd_grade_id(rs.getInt("ad_grade_id"));
							bean.setWorking_days(rs.getInt("working_days"));
							bean.setWorked_days(rs.getInt("worked_days"));
							bean.setHolidays(rs.getInt("holidays"));
							bean.setTot_absent(rs.getInt("tot_absent"));
							bean.setPayble_days(rs.getInt("payble_days"));
							bean.setBasic(rs.getDouble("basic"));
							bean.setDa(rs.getDouble("da"));
							bean.setConvey(rs.getDouble("convance"));
							bean.setHra(rs.getDouble("hra"));
							bean.setMdcl(rs.getDouble("medical"));
							bean.setAlwnc(rs.getDouble("allowances"));
							bean.setOther_amt(rs.getDouble("other_amt"));
							bean.setGross_amt(rs.getDouble("gross_amt"));
							bean.setPf_amt(rs.getDouble("pf_amt"));
							bean.setPt_amt(rs.getDouble("pt_amt"));
							bean.setEsi_amt(rs.getDouble("esi_amt"));
							bean.setTds_amt(rs.getDouble("tds_amt"));
							bean.setOther_deduction(rs.getDouble("other_deduction"));
							bean.setGross_deduction(rs.getDouble("gross_deduction"));
							bean.setNet_salary(rs.getDouble("net_salary"));
							bean.setCreated(rs.getTimestamp("created"));
							bean.setMonthly_pay(rs.getDouble("monthly_pay"));
							bean.setStatus(rs.getBoolean("status"));
							bean.setGradename("grade_name");
							bean1.add(bean);

						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return bean1;
				}
			//------------------------
				
		
	public void updateSalary(SalaryBean bean){
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_pay_detail " +
					"SET  working_days=?, worked_days=?, holidays=?, tot_absent=?, payble_days=?, " +
					"basic=?, da=?, convance=?, hra=?, medical=?, allowances=?, other_amt=?," +
					"gross_amt=?, pf_amt=?, pt_amt=?, esi_amt=?, tds_amt=?, other_deduction=?," +
					"gross_deduction=?, net_salary=?, created=?,monthly_pay=? "  +
					" WHERE ad_employee_id=? AND month_name=?";
			ps = con.prepareStatement(query);
			
			ps.setInt(1,bean.getWorking_days());
			ps.setInt(2, bean.getWorked_days());
			ps.setInt(3, bean.getHolidays());
			ps.setInt(4, bean.getTot_absent());
			ps.setInt(5, bean.getPayble_days());
			ps.setDouble(6, bean.getBasic());
			ps.setDouble(7, bean.getDa());
			ps.setDouble(8, bean.getConvey());
			ps.setDouble(9, bean.getHra());
			ps.setDouble(10, bean.getMdcl());
			ps.setDouble(11, bean.getAlwnc());
			ps.setDouble(12, bean.getOther_amt());
			ps.setDouble(13, bean.getGross_amt());
			ps.setDouble(14, bean.getPf_amt());
			ps.setDouble(15, bean.getPt_amt());
			ps.setDouble(16, bean.getEsi_amt());
			ps.setDouble(17, bean.getTds_amt());
			ps.setDouble(18, bean.getOther_deduction());
			ps.setDouble(19, bean.getGross_deduction());
			ps.setDouble(20, bean.getNet_salary());
			ps.setTimestamp(21, new java.sql.Timestamp(bean.getCreated().getTime()));
			ps.setDouble(22, bean.getMonthly_pay());
			ps.setInt(23, bean.getAd_employee_id());
			ps.setString(24, bean.getMonth_name());
			
			//System.out.println(ps);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
	}
	public void deleteSalary(SalaryBean bean){
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_pay_detail WHERE ad_employee_id=? AND month_name=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_employee_id());
			ps.setString(2, bean.getMonth_name());
			System.out.println(ps);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}

}
