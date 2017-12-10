package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Bean.EmployeeBean;

public class EmployeeDao {
	private Connection con = null;

	public EmployeeDao() {
		con = DBConnection.getConnection();
	}

	public void addEmployee(EmployeeBean bean) {

		try {

			String query="INSERT INTO ad_employee(created, createdby, updated, updatedby, isactive,"
					+ "employee_id, emp_status, emp_category, ad_salutation_id, name,"
					+ "fname, ad_gender_id, marital_sts, ad_religion_id, blood_group, "
					+ "id_mark, height, remark, ad_city_id, ad_district_id, ad_state_id,"
					+ "ad_country_id, pin, mobile, alt_mobile, phone, c_address, p_address,"
					+ "email, stream_10, sub_10, board_10, pass_year_10, per_10, sub_12,"
					+ "stream_12, board_12, pass_year_12, per_12, sub_g, stream_g, board_g,"
					+ "pass_year_g, per_g, sub_pg, stream_pg, board_pg, pass_year_pg,"
					+ "per_pg, tr_sub, tr_stream, tr_board, tr_pass_year, tr_per, ad_bank_id,"
					+ "ad_branch_id, acc_no, pf_acc_no, ad_grade_id, ad_designation_id,"
					+ "doa, doj, month_pay, photo, id, sign, weakly_off, dob, ad_society_department_id)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, bean.isIsactive());
			ps.setString(6, bean.getEmployee_id());
			ps.setString(7, bean.getEmp_status());
			ps.setString(8, bean.getEmp_category());
			ps.setInt(9, bean.getSalutation().getAd_salutation_id());
			ps.setString(10, bean.getName());
			ps.setString(11, bean.getFname());
			ps.setInt(12,bean.getGender().getAd_gender_id());
		    ps.setString(13, bean.getMarital_sts());
		    ps.setInt(14, bean.getReligion().getAd_religion_id());
		    ps.setString(15, bean.getBlood_group());
		    ps.setString(16, bean.getId_mark());
		    ps.setString(17, bean.getHeight());
		    ps.setString(18, bean.getRemark());
		    ps.setInt(19, bean.getCity().getAd_city_id());
		    ps.setInt(20, bean.getDistrict().getAd_district_id());
		    ps.setInt(21, bean.getState().getAd_state_id());
		    ps.setInt(22, bean.getCountry().getAd_country_id());
		    ps.setString(23, bean.getPin());
		    ps.setString(24, bean.getMobile());
		    ps.setString(25, bean.getAlt_mobile());
		    ps.setString(26, bean.getPhone());
		    ps.setString(27, bean.getC_address());
		    ps.setString(28, bean.getP_address());
		    ps.setString(29, bean.getEmail());
		    ps.setString(30, bean.getStream_10());
		    ps.setString(31, bean.getSub_10());
		    ps.setString(32, bean.getBoard_10());
		    ps.setString(33, bean.getPass_year_10());
		    ps.setDouble(34, bean.getPer_10());
		    ps.setString(35, bean.getSub_12());
		    ps.setString(36, bean.getStream_12());
		    ps.setString(37, bean.getBoard_12());
		    ps.setString(38, bean.getPass_year_12());
		    ps.setDouble(39, bean.getPer_12());
		    ps.setString(40, bean.getSub_g());
		    ps.setString(41, bean.getStream_g());
		    ps.setString(42, bean.getBoard_g());
		    ps.setString(43, bean.getPass_year_g());
		    ps.setDouble(44, bean.getPer_g());
		    ps.setString(45, bean.getSub_pg());
		    ps.setString(46, bean.getStream_pg());
		    ps.setString(47, bean.getBoard_pg());
		    ps.setString(48, bean.getPass_year_pg());
		    ps.setDouble(49, bean.getPer_pg());
		    ps.setString(50, bean.getTr_sub());
		    ps.setString(51, bean.getTr_stream());
		    ps.setString(52, bean.getTr_board());
		    ps.setString(53, bean.getTr_pass_year());
		    ps.setDouble(54, bean.getTr_per());
		    ps.setInt(55, bean.getBank().getAd_bank_id());
		    ps.setInt(56, bean.getBranch().getAd_branch_id());
		    ps.setString(57, bean.getAcc_no());
		    ps.setString(58, bean.getPf_acc_no());
		    ps.setInt(59, bean.getAd_employee_grade_id());
		    ps.setInt(60, bean.getDesignation().getAd_designation_id());
		    ps.setDate(61,new java.sql.Date(bean.getDoa().getTime()));
		    ps.setDate(62,new java.sql.Date(bean.getDoj().getTime()));
		    ps.setInt(63, bean.getMonth_pay());
		    ps.setString(64, bean.getPhoto());
		    ps.setString(65, bean.getId());
		    ps.setString(66, bean.getSign());
		    ps.setString(67, bean.getWeakly_off());
		    ps.setDate(68, new java.sql.Date(bean.getDob().getTime()));	    
			ps.setInt(69, bean.getDepartment().getAd_society_department_id());
			System.out.print(ps);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EmployeeBean getEmployeeById(EmployeeBean bean1) {
		EmployeeBean bean = new EmployeeBean();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from ad_employee where ad_employee_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean1.getAd_employee_id());
			rs = ps.executeQuery();
			while (rs.next()) {
			
				bean.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean.setCreated(rs.getDate("created"));
				bean.setCreatedby(rs.getInt("createdby"));
				bean.setUpdated(rs.getDate("updated"));
				bean.setUpdatedby(rs.getInt("updatedby"));
				bean.setIsactive(rs.getBoolean("isactive"));
				bean.setEmployee_id(rs.getString("employee_id"));
				bean.setEmp_status(rs.getString("emp_status"));
				bean.setEmp_category(rs.getString("emp_category"));
				bean.setSalutation(new SalutationDao().getSalutationById(rs.getInt("ad_salutation_id")));
				bean.setName(rs.getString("name"));
				bean.setFname(rs.getString("fname"));
			    bean.setDob(rs.getDate("dob"));
			    bean.setGender(new GenderDao().getGenderById(rs.getInt("ad_gender_id")));
			    bean.setMarital_sts(rs.getString("marital_sts"));
			    bean.setReligion(new ReligionDao().getReligionById(rs.getInt("ad_religion_id")));
			    bean.setBlood_group(rs.getString("blood_group"));
			    bean.setId_mark(rs.getString("id_mark"));
			    bean.setHeight(rs.getString("height"));
			    bean.setRemark(rs.getString("remark"));
			    bean.setCity(new CityDao().getCityById(rs.getInt("ad_city_id")));
			    bean.setDistrict(new DistrictDao().getDistrictById(rs.getInt("ad_district_id")));
			    bean.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
			    bean.setCountry(new CountryDao().getCountryById(rs.getInt("ad_country_id")));
			    bean.setPin(rs.getString("pin"));
			    bean.setMobile(rs.getString("mobile"));
			    bean.setAlt_mobile(rs.getString("alt_mobile"));
			    bean.setPhone(rs.getString("phone"));
			    bean.setC_address(rs.getString("c_address"));
			    bean.setP_address(rs.getString("p_address"));
			    bean.setEmail(rs.getString("email"));
			    bean.setStream_10(rs.getString("stream_10"));
			    bean.setSub_10(rs.getString("sub_10"));
			    bean.setBoard_10(rs.getString("board_10"));
			    bean.setPass_year_10(rs.getString("pass_year_10"));
			    bean.setPer_10(rs.getDouble("per_10"));
			    bean.setSub_12(rs.getString("sub_12"));
			    bean.setStream_12(rs.getString("stream_12"));
			    bean.setBoard_12(rs.getString("board_12"));
			    bean.setPass_year_12(rs.getString("pass_year_12"));
			    bean.setPer_12(rs.getDouble("per_12"));
			    bean.setSub_g(rs.getString("sub_g"));
			    bean.setStream_g(rs.getString("stream_g"));
			    bean.setBoard_g(rs.getString("board_g"));
			    bean.setPass_year_g(rs.getString("pass_year_g"));
			    bean.setPer_g(rs.getDouble("per_g"));
			    bean.setSub_pg(rs.getString("sub_pg"));
			    bean.setStream_pg(rs.getString("stream_pg"));
			    bean.setBoard_pg(rs.getString("board_pg"));
			    bean.setPass_year_pg(rs.getString("pass_year_pg"));
			    bean.setPer_pg(rs.getDouble("per_pg"));
			    bean.setTr_sub(rs.getString("tr_sub"));
			    bean.setTr_stream(rs.getString("tr_stream"));
			    bean.setTr_board(rs.getString("tr_board"));
			    bean.setTr_pass_year(rs.getString("tr_pass_year"));
			    bean.setTr_per(rs.getDouble("tr_per"));
			    bean.setBank(new BankDao().getBankById(rs.getInt("ad_bank_id")));
			    bean.setBranch(new BankBranchDao().getBankBranchById(rs.getInt("ad_branch_id")));
			    bean.setAcc_no(rs.getString("acc_no"));
			    bean.setPf_acc_no(rs.getString("pf_acc_no"));
			    bean.setAd_employee_grade_id(rs.getInt("ad_grade_id"));
			    bean.setDoa(rs.getDate("doa"));
			    bean.setDoj(rs.getDate("doj"));
			    bean.setDepartment(new SocietyDepartmentDao().getSocietyDepartmentById(rs.getInt("ad_society_department_id")));
			    bean.setDesignation(new DesignationDao().getDesignationById(rs.getInt("ad_designation_id")));
			    bean.setMonth_pay(rs.getInt("month_pay"));
			    bean.setPhoto(rs.getString("photo"));
			    bean.setId(rs.getString("id"));
			    bean.setSign(rs.getString("sign"));
			    bean.setWeakly_off(rs.getString("weakly_off"));

 
			    
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
	
	public EmployeeBean getEmployeeIdByName(String  emp_name) {
		EmployeeBean bean1 = new EmployeeBean();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select ad_employee_id from ad_employee where emp_name=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, emp_name);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_employee_id(rs.getInt("ad_employee_id"));

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
	public int getMaxEmployeeId() {
		int result=0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select max(employee_id) as employee_id from ad_employee";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				result=rs.getInt("employee_id");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return result+1;
	}
	
	//------------------------------
	
	public EmployeeBean getEmployeeById(int ad_employee_id) {
		EmployeeBean bean = new EmployeeBean();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from ad_employee where ad_employee_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_employee_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				
				bean.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean.setCreated(rs.getDate("created"));
				bean.setCreatedby(rs.getInt("createdby"));
				bean.setUpdated(rs.getDate("updated"));
				bean.setUpdatedby(rs.getInt("updatedby"));
				bean.setIsactive(rs.getBoolean("isactive"));
				bean.setEmployee_id(rs.getString("employee_id"));
				bean.setEmp_status(rs.getString("emp_status"));
				bean.setEmp_category(rs.getString("emp_category"));
				bean.setSalutation(new SalutationDao().getSalutationById(rs.getInt("ad_salutation_id")));
				bean.setName(rs.getString("name"));
				bean.setFname(rs.getString("fname"));
			    bean.setDob(rs.getDate("dob"));
			    bean.setGender(new GenderDao().getGenderById(rs.getInt("ad_gender_id")));
			    bean.setMarital_sts(rs.getString("marital_sts"));
			    bean.setReligion(new ReligionDao().getReligionById(rs.getInt("ad_religion_id")));
			    bean.setBlood_group(rs.getString("blood_group"));
			    bean.setId_mark(rs.getString("id_mark"));
			    bean.setHeight(rs.getString("height"));
			    bean.setRemark(rs.getString("remark"));
			    bean.setCity(new CityDao().getCityById(rs.getInt("ad_city_id")));
			    bean.setDistrict(new DistrictDao().getDistrictById(rs.getInt("ad_district_id")));
			    bean.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
			    bean.setCountry(new CountryDao().getCountryById(rs.getInt("ad_country_id")));
			    bean.setPin(rs.getString("pin"));
			    bean.setMobile(rs.getString("mobile"));
			    bean.setAlt_mobile(rs.getString("alt_mobile"));
			    bean.setPhone(rs.getString("phone"));
			    bean.setC_address(rs.getString("c_address"));
			    bean.setP_address(rs.getString("p_address"));
			    bean.setEmail(rs.getString("email"));
			    bean.setStream_10(rs.getString("stream_10"));
			    bean.setSub_10(rs.getString("sub_10"));
			    bean.setBoard_10(rs.getString("board_10"));
			    bean.setPass_year_10(rs.getString("pass_year_10"));
			    bean.setPer_10(rs.getDouble("per_10"));
			    bean.setSub_12(rs.getString("sub_12"));
			    bean.setStream_12(rs.getString("stream_12"));
			    bean.setBoard_12(rs.getString("board_12"));
			    bean.setPass_year_12(rs.getString("pass_year_12"));
			    bean.setPer_12(rs.getDouble("per_12"));
			    bean.setSub_g(rs.getString("sub_g"));
			    bean.setStream_g(rs.getString("stream_g"));
			    bean.setBoard_g(rs.getString("board_g"));
			    bean.setPass_year_g(rs.getString("pass_year_g"));
			    bean.setPer_g(rs.getDouble("per_g"));
			    bean.setSub_pg(rs.getString("sub_pg"));
			    bean.setStream_pg(rs.getString("stream_pg"));
			    bean.setBoard_pg(rs.getString("board_pg"));
			    bean.setPass_year_pg(rs.getString("pass_year_pg"));
			    bean.setPer_pg(rs.getDouble("per_pg"));
			    bean.setTr_sub(rs.getString("tr_sub"));
			    bean.setTr_stream(rs.getString("tr_stream"));
			    bean.setTr_board(rs.getString("tr_board"));
			    bean.setTr_pass_year(rs.getString("tr_pass_year"));
			    bean.setTr_per(rs.getDouble("tr_per"));
			    bean.setBank(new BankDao().getBankById(rs.getInt("ad_bank_id")));
			    bean.setBranch(new BankBranchDao().getBankBranchById(rs.getInt("ad_branch_id")));
			    bean.setAcc_no(rs.getString("acc_no"));
			    bean.setPf_acc_no(rs.getString("pf_acc_no"));
			    bean.setAd_employee_grade_id(rs.getInt("ad_grade_id"));
			    bean.setDoa(rs.getDate("doa"));
			    bean.setDoj(rs.getDate("doj"));
			    bean.setDepartment(new SocietyDepartmentDao().getSocietyDepartmentById(rs.getInt("ad_society_department_id")));
			    bean.setDesignation(new DesignationDao().getDesignationById(rs.getInt("ad_designation_id")));
			    bean.setMonth_pay(rs.getInt("month_pay"));
			    bean.setPhoto(rs.getString("photo"));
			    bean.setId(rs.getString("id"));
			    bean.setSign(rs.getString("sign"));
			    bean.setWeakly_off(rs.getString("weakly_off"));
			    bean.setGrade(new GradeDao().getGradeById(rs.getInt("ad_Grade_id")));
 
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
	
	public EmployeeBean getEmployeeNameById(int ad_employee_id) {
		EmployeeBean bean = new EmployeeBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_employee where ad_employee_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_employee_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				
				
				bean.setEmployee_id(rs.getString("employee_id"));
				
				bean.setName(rs.getString("name"));
				bean.setFname(rs.getString("fname"));
			 

 
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
	//--------------------------------
	public ArrayList<EmployeeBean> getEmployeeName() {
		ArrayList<EmployeeBean> bean1 = new ArrayList<EmployeeBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_employee ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				EmployeeBean bean = new EmployeeBean();
				bean.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean.setEmployee_id(rs.getString("employee_id"));
				
				bean.setName(rs.getString("name"));
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
	//-----------------
	public EmployeeBean getEmployeeByCode(String code) {
		EmployeeBean bean = new EmployeeBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_Employee where ad_employee_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(code));
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				
				bean.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean.setCreated(rs.getDate("created"));
				bean.setCreatedby(rs.getInt("createdby"));
				bean.setUpdated(rs.getDate("updated"));
				bean.setUpdatedby(rs.getInt("updatedby"));
				bean.setIsactive(rs.getBoolean("isactive"));
				bean.setEmployee_id(rs.getString("employee_id"));
				bean.setEmp_status(rs.getString("emp_status"));
				bean.setEmp_category(rs.getString("emp_category"));
				bean.setSalutation(new SalutationDao().getSalutationById(rs.getInt("ad_salutation_id")));
				bean.setName(rs.getString("name"));
				bean.setFname(rs.getString("fname"));
			    bean.setDob(rs.getDate("dob"));
			    bean.setGender(new GenderDao().getGenderById(rs.getInt("ad_gender_id")));
			    bean.setMarital_sts(rs.getString("marital_sts"));
			    bean.setReligion(new ReligionDao().getReligionById(rs.getInt("ad_religion_id")));
			    bean.setBlood_group(rs.getString("blood_group"));
			    bean.setId_mark(rs.getString("id_mark"));
			    bean.setHeight(rs.getString("height"));
			    bean.setRemark(rs.getString("remark"));
			    bean.setCity(new CityDao().getCityById(rs.getInt("ad_city_id")));
			    bean.setDistrict(new DistrictDao().getDistrictById(rs.getInt("ad_district_id")));
			    bean.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
			    bean.setCountry(new CountryDao().getCountryById(rs.getInt("ad_country_id")));
			    bean.setPin(rs.getString("pin"));
			    bean.setMobile(rs.getString("mobile"));
			    bean.setAlt_mobile(rs.getString("alt_mobile"));
			    bean.setPhone(rs.getString("phone"));
			    bean.setC_address(rs.getString("c_address"));
			    bean.setP_address(rs.getString("p_address"));
			    bean.setEmail(rs.getString("email"));
			    bean.setStream_10(rs.getString("stream_10"));
			    bean.setSub_10(rs.getString("sub_10"));
			    bean.setBoard_10(rs.getString("board_10"));
			    bean.setPass_year_10(rs.getString("pass_year_10"));
			    bean.setPer_10(rs.getDouble("per_10"));
			    bean.setSub_12(rs.getString("sub_12"));
			    bean.setStream_12(rs.getString("stream_12"));
			    bean.setBoard_12(rs.getString("board_12"));
			    bean.setPass_year_12(rs.getString("pass_year_12"));
			    bean.setPer_12(rs.getDouble("per_12"));
			    bean.setSub_g(rs.getString("sub_g"));
			    bean.setStream_g(rs.getString("stream_g"));
			    bean.setBoard_g(rs.getString("board_g"));
			    bean.setPass_year_g(rs.getString("pass_year_g"));
			    bean.setPer_g(rs.getDouble("per_g"));
			    bean.setSub_pg(rs.getString("sub_pg"));
			    bean.setStream_pg(rs.getString("stream_pg"));
			    bean.setBoard_pg(rs.getString("board_pg"));
			    bean.setPass_year_pg(rs.getString("pass_year_pg"));
			    bean.setPer_pg(rs.getDouble("per_pg"));
			    bean.setTr_sub(rs.getString("tr_sub"));
			    bean.setTr_stream(rs.getString("tr_stream"));
			    bean.setTr_board(rs.getString("tr_board"));
			    bean.setTr_pass_year(rs.getString("tr_pass_year"));
			    bean.setTr_per(rs.getDouble("tr_per"));
			    bean.setBank(new BankDao().getBankById(rs.getInt("ad_bank_id")));
			    bean.setBranch(new BankBranchDao().getBankBranchById(rs.getInt("ad_branch_id")));
			    bean.setAcc_no(rs.getString("acc_no"));
			    bean.setPf_acc_no(rs.getString("pf_acc_no"));
			    bean.setAd_employee_grade_id(rs.getInt("ad_grade_id"));
			    bean.setDoa(rs.getDate("doa"));
			    bean.setDoj(rs.getDate("doj"));
			    bean.setDepartment(new SocietyDepartmentDao().getSocietyDepartmentById(rs.getInt("ad_society_department_id")));    bean.setDesignation(new DesignationDao().getDesignationById(rs.getInt("ad_designation_id")));
			    bean.setMonth_pay(rs.getInt("month_pay"));
			    bean.setPhoto(rs.getString("photo"));
			    bean.setId(rs.getString("id"));
			    bean.setSign(rs.getString("sign"));
			    bean.setWeakly_off(rs.getString("weakly_off"));

 
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
	
	
	public ArrayList<EmployeeBean> getAllEmployee() {
		ArrayList<EmployeeBean> bean1 = new ArrayList<EmployeeBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_Employee ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				EmployeeBean bean = new EmployeeBean();
				bean.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean.setCreated(rs.getDate("created"));
				bean.setCreatedby(rs.getInt("createdby"));
				bean.setUpdated(rs.getDate("updated"));
				bean.setUpdatedby(rs.getInt("updatedby"));
				bean.setIsactive(rs.getBoolean("isactive"));
				bean.setEmployee_id(rs.getString("employee_id"));
				bean.setEmp_status(rs.getString("emp_status"));
				bean.setEmp_category(rs.getString("emp_category"));
				bean.setSalutation(new SalutationDao().getSalutationById(rs.getInt("ad_salutation_id")));
				bean.setName(rs.getString("name"));
				bean.setFname(rs.getString("fname"));
			    bean.setDob(rs.getDate("dob"));
			    bean.setGender(new GenderDao().getGenderById(rs.getInt("ad_gender_id")));
			    bean.setMarital_sts(rs.getString("marital_sts"));
			    bean.setReligion(new ReligionDao().getReligionById(rs.getInt("ad_religion_id")));
			    bean.setBlood_group(rs.getString("blood_group"));
			    bean.setId_mark(rs.getString("id_mark"));
			    bean.setHeight(rs.getString("height"));
			    bean.setRemark(rs.getString("remark"));
			    bean.setCity(new CityDao().getCityById(rs.getInt("ad_city_id")));
			    bean.setDistrict(new DistrictDao().getDistrictById(rs.getInt("ad_district_id")));
			    bean.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
			    bean.setCountry(new CountryDao().getCountryById(rs.getInt("ad_country_id")));
			    bean.setPin(rs.getString("pin"));
			    bean.setMobile(rs.getString("mobile"));
			    bean.setAlt_mobile(rs.getString("alt_mobile"));
			    bean.setPhone(rs.getString("phone"));
			    bean.setC_address(rs.getString("c_address"));
			    bean.setP_address(rs.getString("p_address"));
			    bean.setEmail(rs.getString("email"));
			    bean.setStream_10(rs.getString("stream_10"));
			    bean.setSub_10(rs.getString("sub_10"));
			    bean.setBoard_10(rs.getString("board_10"));
			    bean.setPass_year_10(rs.getString("pass_year_10"));
			    bean.setPer_10(rs.getDouble("per_10"));
			    bean.setSub_12(rs.getString("sub_12"));
			    bean.setStream_12(rs.getString("stream_12"));
			    bean.setBoard_12(rs.getString("board_12"));
			    bean.setPass_year_12(rs.getString("pass_year_12"));
			    bean.setPer_12(rs.getDouble("per_12"));
			    bean.setSub_g(rs.getString("sub_g"));
			    bean.setStream_g(rs.getString("stream_g"));
			    bean.setBoard_g(rs.getString("board_g"));
			    bean.setPass_year_g(rs.getString("pass_year_g"));
			    bean.setPer_g(rs.getDouble("per_g"));
			    bean.setSub_pg(rs.getString("sub_pg"));
			    bean.setStream_pg(rs.getString("stream_pg"));
			    bean.setBoard_pg(rs.getString("board_pg"));
			    bean.setPass_year_pg(rs.getString("pass_year_pg"));
			    bean.setPer_pg(rs.getDouble("per_pg"));
			    bean.setTr_sub(rs.getString("tr_sub"));
			    bean.setTr_stream(rs.getString("tr_stream"));
			    bean.setTr_board(rs.getString("tr_board"));
			    bean.setTr_pass_year(rs.getString("tr_pass_year"));
			    bean.setTr_per(rs.getDouble("tr_per"));
			    bean.setBank(new BankDao().getBankById(rs.getInt("ad_bank_id")));
			    bean.setBranch(new BankBranchDao().getBankBranchById(rs.getInt("ad_branch_id")));
			    bean.setAcc_no(rs.getString("acc_no"));
			    bean.setPf_acc_no(rs.getString("pf_acc_no"));
			    bean.setAd_employee_grade_id(rs.getInt("ad_grade_id"));
			    bean.setDoa(rs.getDate("doa"));
			    bean.setDoj(rs.getDate("doj"));
			    bean.setDepartment(new SocietyDepartmentDao().getSocietyDepartmentById(rs.getInt("ad_society_department_id")));
			    bean.setDesignation(new DesignationDao().getDesignationById(rs.getInt("ad_designation_id")));
			    bean.setMonth_pay(rs.getInt("month_pay"));
			    bean.setPhoto(rs.getString("photo"));
			    bean.setId(rs.getString("id"));
			    bean.setSign(rs.getString("sign"));
			    bean.setWeakly_off(rs.getString("weakly_off"));
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
	public ArrayList<EmployeeBean> getAllEmployeeName() {
		ArrayList<EmployeeBean> bean1 = new ArrayList<EmployeeBean>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select ad_employee_id,name, employee_id from ad_Employee ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				EmployeeBean bean = new EmployeeBean();
				bean.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean.setName(rs.getString("name"));
				bean.setEmployee_id(rs.getString("employee_id"));
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
	
	public ArrayList<EmployeeBean> getAllActiveEmployee(String status) {
		ArrayList<EmployeeBean> bean1 = new ArrayList<EmployeeBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_Employee where LOWER(emp_status)=LOWER(?) ";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, status.trim());
			rs = ps.executeQuery();
			while (rs.next()) {
				EmployeeBean bean = new EmployeeBean();
				bean.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean.setCreated(rs.getDate("created"));
				bean.setCreatedby(rs.getInt("createdby"));
				bean.setUpdated(rs.getDate("updated"));
				bean.setUpdatedby(rs.getInt("updatedby"));
				bean.setIsactive(rs.getBoolean("isactive"));
				bean.setEmployee_id(rs.getString("employee_id"));
				bean.setEmp_status(rs.getString("emp_status"));
				bean.setEmp_category(rs.getString("emp_category"));
				bean.setSalutation(new SalutationDao().getSalutationById(rs.getInt("ad_salutation_id")));
				bean.setName(rs.getString("name"));
				bean.setFname(rs.getString("fname"));
			    bean.setDob(rs.getDate("dob"));
			    bean.setGender(new GenderDao().getGenderById(rs.getInt("ad_gender_id")));
			    bean.setMarital_sts(rs.getString("marital_sts"));
			    bean.setReligion(new ReligionDao().getReligionById(rs.getInt("ad_religion_id")));
			    bean.setBlood_group(rs.getString("blood_group"));
			    bean.setId_mark(rs.getString("id_mark"));
			    bean.setHeight(rs.getString("height"));
			    bean.setRemark(rs.getString("remark"));
			    bean.setCity(new CityDao().getCityById(rs.getInt("ad_city_id")));
			    bean.setDistrict(new DistrictDao().getDistrictById(rs.getInt("ad_district_id")));
			    bean.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
			    bean.setCountry(new CountryDao().getCountryById(rs.getInt("ad_country_id")));
			    bean.setPin(rs.getString("pin"));
			    bean.setMobile(rs.getString("mobile"));
			    bean.setAlt_mobile(rs.getString("alt_mobile"));
			    bean.setPhone(rs.getString("phone"));
			    bean.setC_address(rs.getString("c_address"));
			    bean.setP_address(rs.getString("p_address"));
			    bean.setEmail(rs.getString("email"));
			    bean.setStream_10(rs.getString("stream_10"));
			    bean.setSub_10(rs.getString("sub_10"));
			    bean.setBoard_10(rs.getString("board_10"));
			    bean.setPass_year_10(rs.getString("pass_year_10"));
			    bean.setPer_10(rs.getDouble("per_10"));
			    bean.setSub_12(rs.getString("sub_12"));
			    bean.setStream_12(rs.getString("stream_12"));
			    bean.setBoard_12(rs.getString("board_12"));
			    bean.setPass_year_12(rs.getString("pass_year_12"));
			    bean.setPer_12(rs.getDouble("per_12"));
			    bean.setSub_g(rs.getString("sub_g"));
			    bean.setStream_g(rs.getString("stream_g"));
			    bean.setBoard_g(rs.getString("board_g"));
			    bean.setPass_year_g(rs.getString("pass_year_g"));
			    bean.setPer_g(rs.getDouble("per_g"));
			    bean.setSub_pg(rs.getString("sub_pg"));
			    bean.setStream_pg(rs.getString("stream_pg"));
			    bean.setBoard_pg(rs.getString("board_pg"));
			    bean.setPass_year_pg(rs.getString("pass_year_pg"));
			    bean.setPer_pg(rs.getDouble("per_pg"));
			    bean.setTr_sub(rs.getString("tr_sub"));
			    bean.setTr_stream(rs.getString("tr_stream"));
			    bean.setTr_board(rs.getString("tr_board"));
			    bean.setTr_pass_year(rs.getString("tr_pass_year"));
			    bean.setTr_per(rs.getDouble("tr_per"));
			    bean.setBank(new BankDao().getBankById(rs.getInt("ad_bank_id")));
			    bean.setBranch(new BankBranchDao().getBankBranchById(rs.getInt("ad_branch_id")));
			    bean.setAcc_no(rs.getString("acc_no"));
			    bean.setPf_acc_no(rs.getString("pf_acc_no"));
			    bean.setAd_employee_grade_id(rs.getInt("ad_grade_id"));
			    bean.setDoa(rs.getDate("doa"));
			    bean.setDoj(rs.getDate("doj"));
			    bean.setDepartment(new SocietyDepartmentDao().getSocietyDepartmentById(rs.getInt("ad_society_department_id")));
			    bean.setDesignation(new DesignationDao().getDesignationById(rs.getInt("ad_designation_id")));
			    bean.setMonth_pay(rs.getInt("month_pay"));
			    bean.setPhoto(rs.getString("photo"));
			    bean.setId(rs.getString("id"));
			    bean.setSign(rs.getString("sign"));
			    bean.setWeakly_off(rs.getString("weakly_off"));
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
	
	public void updateEmployee(EmployeeBean bean){
		PreparedStatement ps = null;
		
		try {

			String query = "UPDATE ad_employee SET updated=?, updatedby=?," +
					"isactive=?, employee_id=?, emp_status=?, emp_category=?, ad_salutation_id=?," +
					"name=?, fname=?, ad_gender_id=?, marital_sts=?, ad_religion_id=?," +
					"blood_group=?, id_mark=?, height=?, remark=?, ad_city_id=?, ad_district_id=?," +
					" ad_state_id=?, ad_country_id=?, pin=?, mobile=?, alt_mobile=?, phone=?, " +
					"c_address=?, p_address=?, email=?, stream_10=?, sub_10=?,board_10=?," +
					"pass_year_10=?, per_10=?, sub_12=?, stream_12=?,board_12=?, pass_year_12=?," +
					"per_12=?, sub_g=?, stream_g=?, board_g=?, pass_year_g=?, per_g=?, sub_pg=?," +
					"stream_pg=?, board_pg=?, pass_year_pg=?,per_pg=?, tr_sub=?, tr_stream=?," +
					"tr_board=?, tr_pass_year=?, tr_per=?, ad_bank_id=?, ad_branch_id=?, acc_no=?," +
					"pf_acc_no=?, ad_grade_id=?, ad_society_department?_id=?, ad_designation_id=?," +
					"doa=?, doj=?, month_pay=?, photo=?, id=?, sign=?, weakly_off=?, dob=?" +
					" WHERE ad_employee_id=? ";
			ps = con.prepareStatement(query);
			ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getEmployee_id());
			ps.setString(5, bean.getEmp_status());
			ps.setString(6, bean.getEmp_category());
			ps.setInt(7, bean.getSalutation().getAd_salutation_id());
			ps.setString(8, bean.getName());
			ps.setString(9, bean.getFname());
			ps.setInt(10,bean.getGender().getAd_gender_id());
		    ps.setString(11, bean.getMarital_sts());
		    ps.setInt(12, bean.getReligion().getAd_religion_id());
		    ps.setString(13, bean.getBlood_group());
		    ps.setString(14, bean.getId_mark());
		    ps.setString(15, bean.getHeight());
		    ps.setString(16, bean.getRemark());
		    ps.setInt(17, bean.getCity().getAd_city_id());
		    ps.setInt(18, bean.getDistrict().getAd_district_id());
		    ps.setInt(19, bean.getState().getAd_state_id());
		    ps.setInt(20, bean.getCountry().getAd_country_id());
		    ps.setString(21, bean.getPin());
		    ps.setString(22, bean.getMobile());
		    ps.setString(23, bean.getAlt_mobile());
		    ps.setString(24, bean.getPhone());
		    ps.setString(25, bean.getC_address());
		    ps.setString(26, bean.getP_address());
		    ps.setString(27, bean.getEmail());
		    ps.setString(28, bean.getStream_10());
		    ps.setString(29, bean.getSub_10());
		    ps.setString(30, bean.getBoard_10());
		    ps.setString(31, bean.getPass_year_10());
		    ps.setDouble(32, bean.getPer_10());
		    ps.setString(33, bean.getSub_12());
		    ps.setString(34, bean.getStream_12());
		    ps.setString(35, bean.getBoard_12());
		    ps.setString(36, bean.getPass_year_12());
		    ps.setDouble(37, bean.getPer_12());
		    ps.setString(38, bean.getSub_g());
		    ps.setString(39, bean.getStream_g());
		    ps.setString(40, bean.getBoard_g());
		    ps.setString(41, bean.getPass_year_g());
		    ps.setDouble(42, bean.getPer_g());
		    ps.setString(43, bean.getSub_pg());
		    ps.setString(44, bean.getStream_pg());
		    ps.setString(45, bean.getBoard_pg());
		    ps.setString(46, bean.getPass_year_pg());
		    ps.setDouble(47, bean.getPer_pg());
		    ps.setString(48, bean.getTr_sub());
		    ps.setString(49, bean.getTr_stream());
		    ps.setString(50, bean.getTr_board());
		    ps.setString(51, bean.getTr_pass_year());
		    ps.setDouble(52, bean.getTr_per());
		    ps.setInt(53, bean.getBank().getAd_bank_id());
		    ps.setInt(54, bean.getBranch().getAd_branch_id());
		    ps.setString(55, bean.getAcc_no());
		    ps.setString(56, bean.getPf_acc_no());
		    ps.setInt(57, bean.getAd_employee_grade_id());
		    ps.setInt(58, bean.getDepartment().getAd_society_department_id());
		    ps.setInt(59, bean.getDesignation().getAd_designation_id());
		    ps.setDate(60,new java.sql.Date(bean.getDoa().getTime()));
		    ps.setDate(61,new java.sql.Date(bean.getDoj().getTime()));
		    ps.setInt(62, bean.getMonth_pay());
		    ps.setString(63, bean.getPhoto());
		    ps.setString(64, bean.getId());
		    ps.setString(65, bean.getSign());
		    ps.setString(66, bean.getWeakly_off());
		    ps.setDate(67, new java.sql.Date(bean.getDob().getTime()));
		    ps.setInt(68, bean.getAd_employee_id());
		   
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
	}
	public void deleteEmployee(EmployeeBean bean){
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_employee WHERE ad_employee_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_employee_id());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}

	
	
}
