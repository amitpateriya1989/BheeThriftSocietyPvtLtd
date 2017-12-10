package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Model.Bean.BankBranchBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.MemberStatusBean;
import Model.Bean.WitnessBean;

public class MemberRegistrationDao {
	private Connection con = null;

	public MemberRegistrationDao() {
		
			con = DBConnection.getConnection();
			
	}
	

	public int addMemberRegistration(MemberRegistrationBean bean) {
		int record=0;
		int mid=0;
		PreparedStatement ps=null;
		try {
			
			/*String query = "INSERT INTO ad_member(" +
			"created, createdby, updated, updatedby, isactive," +
			"name, father, husband, dob, ad_category_id, ad_gender_id, ad_member_type_id," +
			"ad_member_status_id, ad_salutation_id, ad_branch_id, ad_designation_type_id," +
			"ad_designation_level_id, ad_designation_id, pan_no, aadhar_no," +
			"doa, doj, service_duration, dor, ad_department_id, saving_ac_no," +
			"photo, signature, id_proof, ad_witness_id,ad_pf_no,ad_society_no)" +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
			"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)" ;*/
			
			String query = "INSERT INTO ad_member(" +
					"created, createdby, updated, updatedby, isactive," +
					"name, father, husband, dob, ad_category_id, ad_gender_id, ad_member_type_id," +
					"ad_member_status_id, ad_salutation_id, ad_branch_id, ad_designation_type_id," +
					"ad_designation_id, pan_no, aadhar_no," +
					"doa, service_duration, dor, ad_department_id, saving_ac_no," +
					"ad_witness_id,ad_pf_no,ad_society_no,ad_voucher_id, marital_status,doj,caste,no_type,ad_society_no2)" +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
					"?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)" ;

			ps = con.prepareStatement(query,ps.RETURN_GENERATED_KEYS);
			ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getName().toUpperCase());
			ps.setString(7, bean.getFather());
			ps.setString(8, bean.getHusband());
			ps.setDate(9, new java.sql.Date(bean.getDob().getTime()));
			ps.setInt(10, bean.getCategory().getAd_category_id());
			ps.setInt(11, bean.getGender().getAd_gender_id());
			ps.setInt(12, bean.getMember_type().getAd_member_type_id());
			ps.setInt(13, bean.getMember_status().getAd_member_status_id());
			ps.setInt(14, bean.getSalutation().getAd_salutation_id());
			ps.setInt(15, bean.getBranch().getAd_branch_id());
			ps.setInt(16, bean.getDesignation_type().getAd_designation_type_id());
			/*ps.setInt(17, bean.getDesignation_level().getAd_designation_level_id());*/
			ps.setInt(17, bean.getDesignation().getAd_designation_id());
			ps.setString(18, bean.getPan_no());
			ps.setString(19, bean.getAadhar_no());
			ps.setDate(20, new java.sql.Date(bean.getDoa().getTime()));
			/*ps.setDate(21, new java.sql.Date(bean.getDoj().getTime()));*/
			ps.setString(21, bean.getService_duration());
			ps.setDate(22, new java.sql.Date(bean.getDor().getTime()));
			ps.setInt(23, bean.getDepartment().getAd_department_id());
			ps.setString(24, bean.getSaving_ac_no());
/*			ps.setString(27, bean.getPhoto());
			ps.setString(28, bean.getSignature());
			ps.setString(29, bean.getId_proof());*/
			ps.setInt(25, bean.getAd_witness_id());
			ps.setInt(26, bean.getAd_pf_no());
			ps.setInt(27, bean.getAd_society_no());
			ps.setInt(28, bean.getAd_voucher_id());
			ps.setString(29, bean.getMarital_status().trim());
			ps.setDate(30, new java.sql.Date(bean.getDoa().getTime()));
			ps.setString(31, "NA");
			ps.setString(32, "M");
			ps.setString(33, "M"+bean.getAd_society_no());
			//System.out.println(ps);
			record=ps.executeUpdate();
			ResultSet generatedKeys = null;
			 try{
				 generatedKeys = ps.getGeneratedKeys(); 
		            if (generatedKeys.next()) {
		           mid=  generatedKeys.getInt(1);
		     //     System.out.println()
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
			System.out.print("MemberRegistrationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return mid;
		
	}
	
//-----------------------------------------------------------------------------------------
	public int addMemberFileUpload(MemberRegistrationBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "UPDATE ad_member SET photo=?, signature=?, id_proof=? WHERE ad_member_id=?" ;

			ps = con.prepareStatement(query);
			ps.setString(1, bean.getPhoto());
			ps.setString(2, bean.getSignature());
			ps.setString(3, bean.getId_proof());
			ps.setInt(4, bean.getAd_member_id());
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("MemberRegistrationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
		
	}
//-----------------------------------------------------------------------------------------	
	public MemberRegistrationBean getMemberRegistrationById(MemberRegistrationBean bean) {
		MemberRegistrationBean bean1 = new MemberRegistrationBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_member where ad_member_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAadhar_no(rs.getString("aadhar_no"));
				bean1.setAd_witness_id(rs.getInt("ad_witness_id"));
				bean1.setBranch(new BankBranchDao().getBankBranchById(rs.getInt("ad_branch_id")));
				bean1.setCategory(new CategoryDao().getCategoryById(rs.getInt("ad_category_id")));
				bean1.setDepartment(new DepartmentDao().getDepartmentById(rs.getInt("ad_department_id")));
				bean1.setDesignation(new DesignationDao().getDesignationById(rs.getInt("ad_designation_id")));
				bean1.setDesignation_level(new DesignationLevelDao().getDesignationLevelById(rs.getInt("ad_designation_level_id")));
				bean1.setDesignation_type(new DesignationTypeDao().getDesignationTypeById(rs.getInt("ad_designation_type_id")));
				bean1.setDoa(rs.getDate("doa"));
				bean1.setDob(rs.getDate("dob"));
				bean1.setDoj(rs.getDate("doj"));
				bean1.setDor(rs.getDate("dor"));
				bean1.setFather(rs.getString("father"));
				bean1.setHusband(rs.getString("husband"));
				bean1.setGender(new GenderDao().getGenderById(rs.getInt("ad_gender_id")));
				bean1.setId_proof(rs.getString("id_proof"));
				bean1.setMember_status(new MemberStatusDao().getMemberStatusById(rs.getInt("ad_member_status_id")));
				bean1.setMember_type(new MemberTypeDao().getMemberTypeById(rs.getInt("ad_member_type_id")));
				bean1.setName(rs.getString("name").toUpperCase());
				bean1.setPan_no(rs.getString("pan_no"));
				bean1.setPhoto(rs.getString("photo"));
				bean1.setSalutation(new SalutationDao().getSalutationById(rs.getInt("ad_salutation_id")));
				bean1.setSaving_ac_no(rs.getString("saving_ac_no"));
				bean1.setService_duration(rs.getString("service_duration"));
				bean1.setSignature(rs.getString("signature"));
				bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
				bean1.setAd_society_no(rs.getInt("ad_society_no"));
				bean1.setAddress(new MemberAddressDao().getMemberAddressByMemberId(rs.getInt("ad_member_id")));
				

			}
			}catch (Exception e) {
				System.out.print("MemberRegistrationDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//--------------------------------------------------------------------------------------
			public MemberRegistrationBean getMemberNameBySocietyNo(int ad_society_no) {
				MemberRegistrationBean bean1 = new MemberRegistrationBean();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "select ad_member_id, upper(name) as name,ad_society_no,saving_ac_no,ad_member_status_id,ad_branch_id,ad_pf_no  from ad_member where ad_society_no=? ";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_society_no);
					rs = ps.executeQuery();
					while (rs.next()) {
						
						bean1.setAd_member_id(rs.getInt("ad_member_id"));
						
						bean1.setName(rs.getString("name").toUpperCase());
						bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
						bean1.setAd_society_no(rs.getInt("ad_society_no"));
						bean1.setSaving_ac_no(rs.getString("saving_ac_no"));
						bean1.setAd_member_status_id(rs.getInt("Ad_member_status_id"));
						//bean1.setBranch(new BankBranchDao().getBranchNameById(rs.getInt("ad_branch_id")));
						bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
						//System.out.print(bean);

					}
				}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean1;
			}
		//-----------------------------------------
//------------------------------------------------------------------------------------------	
	public MemberRegistrationBean getMemberRegistrationById(int ad_member_id) {
		MemberRegistrationBean bean1 = new MemberRegistrationBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_member where ad_member_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAadhar_no(rs.getString("aadhar_no"));
				bean1.setAd_witness_id(rs.getInt("ad_witness_id"));
				bean1.setBranch(new BankBranchDao().getBankBranchById(rs.getInt("ad_branch_id")));
				bean1.setCategory(new CategoryDao().getCategoryById(rs.getInt("ad_category_id")));
				bean1.setDepartment(new DepartmentDao().getDepartmentById(rs.getInt("ad_department_id")));
				bean1.setDesignation(new DesignationDao().getDesignationById(rs.getInt("ad_designation_id")));
				bean1.setDesignation_level(new DesignationLevelDao().getDesignationLevelById(rs.getInt("ad_designation_level_id")));
				bean1.setDesignation_type(new DesignationTypeDao().getDesignationTypeById(rs.getInt("ad_designation_type_id")));
				bean1.setDoa(rs.getDate("doa"));
				bean1.setDob(rs.getDate("dob"));
				bean1.setDoj(rs.getDate("doj"));
				bean1.setDor(rs.getDate("dor"));
				bean1.setFather(rs.getString("father"));
				bean1.setHusband(rs.getString("husband"));
				bean1.setGender(new GenderDao().getGenderById(rs.getInt("ad_gender_id")));
				bean1.setId_proof(rs.getString("id_proof"));
				bean1.setMember_status(new MemberStatusDao().getMemberStatusById(rs.getInt("ad_member_status_id")));
				bean1.setMember_type(new MemberTypeDao().getMemberTypeById(rs.getInt("ad_member_type_id")));
				bean1.setName(rs.getString("name").toUpperCase());
				bean1.setPan_no(rs.getString("pan_no"));
				bean1.setPhoto(rs.getString("photo"));
				bean1.setSalutation(new SalutationDao().getSalutationById(rs.getInt("ad_salutation_id")));
				bean1.setSaving_ac_no(rs.getString("saving_ac_no"));
				bean1.setService_duration(rs.getString("service_duration"));
				bean1.setSignature(rs.getString("signature"));
				bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
				bean1.setAd_society_no(rs.getInt("ad_society_no"));
				bean1.setAddress(new MemberAddressDao().getMemberAddressByMemberId(rs.getInt("ad_member_id")));
				bean1.setNominee(new NominationDao().getNominationByMemberId(rs.getInt("ad_member_id")));
				bean1.setWitness(new WitnessDao().getWitnessById(rs.getInt("ad_member_id")));
			}
		}catch (Exception e) {
			System.out.print("MemberRegistrationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
	
	//------------------------------------------------------------------------------------------	
			public ArrayList<MemberRegistrationBean> getMemberByBranch(int ad_branch_id) {
				ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "select distinct member.ad_member_id,member.name,status.member_status,member.ad_society_no,member.ad_branch_id, " +
						" member.ad_member_status_id,member.ad_pf_no from ad_trx l " +
						" LEFT JOIN ad_member member ON l.ad_member_id=member.ad_member_id " +
						" LEFT JOIN ad_member_status status ON  status.ad_member_status_id = member.ad_member_status_id " +
						" LEFT JOIN ad_voucher v ON l.ad_voucher_id=v.ad_voucher_id" +
						" WHERE  member.ad_member_id IS NOT NULL AND v.status='Approved' AND member.ad_branch_id=? AND member.ad_member_status_id=1 " +
						" order by  ad_society_no ";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_branch_id);
					rs = ps.executeQuery();
					while (rs.next()) {
						MemberRegistrationBean bean1 = new MemberRegistrationBean();
						bean1.setAd_member_id(rs.getInt("ad_member_id"));
						bean1.setBranch(new BankBranchDao().getBranchNameById(rs.getInt("ad_branch_id")));
						bean1.setName(rs.getString("name").toUpperCase());
						bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
						bean1.setAd_society_no(rs.getInt("ad_society_no"));
						
						bean.add(bean1);
						
					}
				}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}
			
	
//--------------------------------------------------------------------------------------
	public ArrayList<MemberRegistrationBean> getAllMemberRegistration() {
		ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_member  ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberRegistrationBean bean1 = new MemberRegistrationBean();
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAadhar_no(rs.getString("aadhar_no"));
				bean1.setAd_witness_id(rs.getInt("ad_witness_id"));
				bean1.setBranch(new BankBranchDao().getBankBranchById(rs.getInt("ad_branch_id")));
				bean1.setCategory(new CategoryDao().getCategoryById(rs.getInt("ad_category_id")));
				bean1.setDepartment(new DepartmentDao().getDepartmentById(rs.getInt("ad_department_id")));
				bean1.setDesignation(new DesignationDao().getDesignationById(rs.getInt("ad_designation_id")));
				bean1.setDesignation_level(new DesignationLevelDao().getDesignationLevelById(rs.getInt("ad_designation_level_id")));
				bean1.setDesignation_type(new DesignationTypeDao().getDesignationTypeById(rs.getInt("ad_designation_type_id")));
				bean1.setDoa(rs.getDate("doa"));
				bean1.setDob(rs.getDate("dob"));
			    bean1.setDoj(rs.getDate("doj"));
				bean1.setDor(rs.getDate("dor"));
				bean1.setFather(rs.getString("father"));
				bean1.setHusband(rs.getString("husband"));
				bean1.setGender(new GenderDao().getGenderById(rs.getInt("ad_gender_id")));
				bean1.setId_proof(rs.getString("id_proof"));
				bean1.setMember_status(new MemberStatusDao().getMemberStatusById(rs.getInt("ad_member_status_id")));
				bean1.setMember_type(new MemberTypeDao().getMemberTypeById(rs.getInt("ad_member_type_id")));
				bean1.setName(rs.getString("name").toUpperCase());
				bean1.setPan_no(rs.getString("pan_no"));
				bean1.setPhoto(rs.getString("photo"));
				bean1.setSalutation(new SalutationDao().getSalutationById(rs.getInt("ad_salutation_id")));
				bean1.setSaving_ac_no(rs.getString("saving_ac_no"));
				bean1.setService_duration(rs.getString("service_duration"));
				bean1.setSignature(rs.getString("signature"));
				bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
				bean1.setAd_society_no(rs.getInt("ad_society_no"));
				//bean1.setAddress(new MemberAddressDao().getMemberAddressByMemberId(rs.getInt("ad_member_id")));
				bean.add(bean1);
				//System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("MemberRegistrationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------
	
	public ArrayList<MemberRegistrationBean> getAllWitnessMemberRegistration() {
		ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_member  ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberRegistrationBean bean1 = new MemberRegistrationBean();
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setName(rs.getString("name").toUpperCase());
				
				bean1.setSalutation(new SalutationDao().getSalutationById(rs.getInt("ad_salutation_id")));
				bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
				bean1.setAd_society_no(rs.getInt("ad_society_no"));
				bean1.setAddress(new MemberAddressDao().getMemberAddressByMemberId(rs.getInt("ad_member_id")));
				bean.add(bean1);
				//System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("MemberRegistrationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	//---------------------------------------------------------------------
	public ArrayList<MemberRegistrationBean> getAllMemberlist() {
		ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select ad_member_id,upper(name) as name,ad_society_no  " +
				" from ad_member where ad_member_status_id=1 order by ad_society_no ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberRegistrationBean bean1 = new MemberRegistrationBean();
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				
				bean1.setName(rs.getString("name").toUpperCase());
				//bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
				bean1.setAd_society_no(rs.getInt("ad_society_no"));
				
				bean.add(bean1);
				//System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("MemberRegistrationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	//---------------------------------------------------------------------
		public ArrayList<MemberRegistrationBean> getAllApprovedMemberlist() {
			ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select distinct m.ad_member_id,m.ad_society_no,m.name from ad_trx l " +
					" LEFT JOIN ad_member m ON l.ad_member_id=m.ad_member_id " +
					" LEFT JOIN ad_voucher v ON l.ad_voucher_id=v.ad_voucher_id " +
					" WHERE  m.ad_member_id IS NOT NULL AND v.status='Approved' AND  m.ad_member_status_id=1 " +
					" order by  ad_society_no ";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					MemberRegistrationBean bean1 = new MemberRegistrationBean();
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					
					bean1.setName(rs.getString("name").toUpperCase());
					//bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
					bean1.setAd_society_no(rs.getInt("ad_society_no"));
					
					bean.add(bean1);
					//System.out.print(bean);

				}
			}catch (Exception e) {
				System.out.print("MemberRegistrationDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
	//---------------------------------------------------------------------
		public ArrayList<MemberRegistrationBean> getAllMemberlistName() {
			ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select ad_member_id,upper(name) as name,ad_society_no  " +
					" from ad_member  order by ad_society_no ";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					MemberRegistrationBean bean1 = new MemberRegistrationBean();
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					
					bean1.setName(rs.getString("name").toUpperCase());
					//bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
					bean1.setAd_society_no(rs.getInt("ad_society_no"));
					
					bean.add(bean1);
					//System.out.print(bean);

				}
			}catch (Exception e) {
				System.out.print("MemberRegistrationDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
	//---------------------------------------------------------------------
		public ArrayList<MemberRegistrationBean> getAllMemberFDlist() {
			ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "SELECT DISTINCT m.ad_member_id, m.name,m.ad_society_no " +
					" from ad_fd_trx f " +
					" LEFT JOIN ad_member m ON m.ad_member_id=f.ad_member_id " +
					" where m.ad_member_status_id in (1 , 2) AND f.remark='OPEN' " +
					" order by ad_society_no ";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					MemberRegistrationBean bean1 = new MemberRegistrationBean();
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					
					bean1.setName(rs.getString("name").toUpperCase());
					//bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
					bean1.setAd_society_no(rs.getInt("ad_society_no"));
					
					bean.add(bean1);
					//System.out.print(bean);

				}
			}catch (Exception e) {
				System.out.print("MemberRegistrationDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
		//---------------------------------------------------------------------
				public ArrayList<MemberRegistrationBean> getAllMemberFDlist1() {
					ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "SELECT DISTINCT m.ad_member_id, m.name,m.ad_society_no " +
							" from ad_fd_trx f " +
							" LEFT JOIN ad_member m ON m.ad_member_id=f.ad_member_id " +
							" order by ad_society_no ";
					try {
						ps = con.prepareStatement(query);
						rs = ps.executeQuery();
						while (rs.next()) {
							MemberRegistrationBean bean1 = new MemberRegistrationBean();
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
							
							bean1.setName(rs.getString("name").toUpperCase());
							//bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
							bean1.setAd_society_no(rs.getInt("ad_society_no"));
							
							bean.add(bean1);
							//System.out.print(bean);

						}
					}catch (Exception e) {
						System.out.print("MemberRegistrationDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return bean;
				}
		//--------------------------------------------------------------------------------------
		public ArrayList<MemberRegistrationBean> getAllActiveMember() {
			ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select ad_society_no,member.ad_member_id,member.name from ad_member member ";
			try {
				ps = con.prepareStatement(query);
				System.out.print(ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					MemberRegistrationBean bean1 = new MemberRegistrationBean();
					bean1.setAd_member_id(rs.getInt("ad_member_id"));

					bean1.setAd_society_no(rs.getInt("ad_society_no"));
					bean1.setName(rs.getString("name").toUpperCase());
					bean.add(bean1);
					

				}
			}catch (Exception e) {
				System.out.print("MemberRegistrationDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}	
		
		
		//--------------------------------------------------------------------------------------
				public ArrayList<MemberRegistrationBean> getAllActiveRegularMember() {
					ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select ad_society_no,member.ad_member_id,member.name from ad_member member " +
							" where ad_member_type_id in (?,?,?) order by ad_member_id ";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, 1);
						ps.setInt(2, 2);
						ps.setInt(3, 4);
						//System.out.print(ps);
						rs = ps.executeQuery();
						while (rs.next()) {
							MemberRegistrationBean bean1 = new MemberRegistrationBean();
							bean1.setAd_member_id(rs.getInt("ad_member_id"));

							bean1.setAd_society_no(rs.getInt("ad_society_no"));
							bean1.setName(rs.getString("name").toUpperCase());
							bean.add(bean1);
							

						}
					}catch (Exception e) {
						System.out.print("MemberRegistrationDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return bean;
				}	
	//--------------------------------------------------------------------------------------
	public MemberRegistrationBean getMemberName(int ad_member_id) {
		MemberRegistrationBean bean1 = new MemberRegistrationBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select  upper(name) as name,ad_society_no,saving_ac_no, " +
				" ad_member_status_id,ad_member_id,ad_pf_no,ad_branch_id  " +
				" from ad_member where ad_member_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setName(rs.getString("name").toUpperCase());
				bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
				bean1.setAd_society_no(rs.getInt("ad_society_no"));
				bean1.setSaving_ac_no(rs.getString("saving_ac_no"));
				bean1.setAd_member_status_id(rs.getInt("Ad_member_status_id"));
				bean1.setBranch(new BankBranchDao().getBranchNameById(rs.getInt("ad_branch_id")));
				//System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("MemberRegistrationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
	//--------------------------------------------------------------------------------------
		public MemberRegistrationBean getActiveMemberName(int ad_member_id) {
			MemberRegistrationBean bean1 = new MemberRegistrationBean();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select  upper(name) as name,ad_society_no,saving_ac_no,ad_member_status_id  " +
					" from ad_member where ad_member_id=? AND ad_member_status_id=? AND isactive=?";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_member_id);
				ps.setInt(2, 1);
				ps.setBoolean(3, true);
				rs = ps.executeQuery();
				while (rs.next()) {
					
					//bean1.setAd_member_id(rs.getInt("ad_member_id"));
					
					bean1.setName(rs.getString("name").toUpperCase());
					//bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
					bean1.setAd_society_no(rs.getInt("ad_society_no"));
					bean1.setSaving_ac_no(rs.getString("saving_ac_no"));
					bean1.setAd_member_status_id(rs.getInt("Ad_member_status_id"));
					//System.out.print(bean);

				}
			}catch (Exception e) {
				System.out.print("MemberRegistrationDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
		
	//-----------------------------------------
	public MemberRegistrationBean getMemberNamebyvid(int ad_voucher_id) {
		MemberRegistrationBean bean1 = new MemberRegistrationBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select  name,ad_society_no  from ad_member where ad_voucher_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				//bean1.setAd_member_id(rs.getInt("ad_member_id"));
				
				bean1.setName(rs.getString("name").toUpperCase());
				//bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
				bean1.setAd_society_no(rs.getInt("ad_society_no"));
				
				
				//System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("MemberRegistrationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
	
	//--------------------------------------------------------------------------------------
		public ArrayList<MemberRegistrationBean> getAllActiveMemberRegistration() {
			ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = " select distinct member.ad_member_id,member.name,status.member_status, " +
					" member.ad_society_no, member.ad_member_status_id from ad_trx l " +
					" LEFT JOIN ad_member member ON l.ad_member_id=member.ad_member_id " +
					" LEFT JOIN ad_member_status status ON  status.ad_member_status_id = member.ad_member_status_id " +
					" LEFT JOIN ad_voucher v ON l.ad_voucher_id=v.ad_voucher_id " +
					" WHERE  member.ad_member_id IS NOT NULL AND v.status='Approved' order by  ad_society_no";
			try {
				ps = con.prepareStatement(query);
				System.out.print(ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					MemberRegistrationBean bean1 = new MemberRegistrationBean();
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					MemberStatusBean status=new MemberStatusBean();
					status.setAd_member_status_id(rs.getInt("ad_member_status_id"));
					status.setMember_status(rs.getString("member_status"));
					bean1.setMember_status(status);
					bean1.setName(rs.getString("name").toUpperCase());
					bean1.setAd_society_no(rs.getInt("ad_society_no"));
					
					bean.add(bean1);
					

				}
			}catch (Exception e) {
				System.out.print("MemberRegistrationDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
		//--------------------------------------------------------------------------------------
				public ArrayList<MemberRegistrationBean> getAllMemberForDividend() {
					ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = " select distinct member.ad_member_id,member.name, " +
							" member.ad_society_no, member.ad_member_status_id ,f.final_pay_date as acc_close_date, " +
							" f.reson,f.reson_date from ad_trx l " +
							" LEFT JOIN ad_member member ON l.ad_member_id=member.ad_member_id " +
							" LEFT JOIN ad_member_status status ON  status.ad_member_status_id = member.ad_member_status_id " +
							" LEFT JOIN ad_voucher v ON l.ad_voucher_id=v.ad_voucher_id " +
							" LEFT JOIN ad_final_pay f ON l.ad_member_id=f.ad_member_id " +
							" LEFT JOIN ad_member_share s ON l.ad_member_id=s.ad_member_id "+ 
							" WHERE member.ad_member_status_id in(1,2,4) AND s.ad_member_id IS NOT NULL " +
							" AND v.status='Approved'  order by  ad_society_no";
					try {
						ps = con.prepareStatement(query);
						
						System.out.print(ps);
						rs = ps.executeQuery();
						while (rs.next()) {
							MemberRegistrationBean bean1 = new MemberRegistrationBean();
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
							bean1.setAd_member_status_id(rs.getInt("ad_member_status_id"));
							bean1.setName(rs.getString("name").toUpperCase());
							bean1.setAd_society_no(rs.getInt("ad_society_no"));
							bean1.setAcc_close_date(rs.getDate("acc_close_date"));
							bean1.setAcc_closing_reason(rs.getString("reson"));
							bean1.setAcc_closing_reason_date(rs.getDate("reson_date"));
							bean.add(bean1);
							

						}
					}catch (Exception e) {
						System.out.print("MemberRegistrationDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return bean;
				}
				//--------------------------------------------------------------------------------------
				public MemberRegistrationBean getMemberForDividend(int ad_member_id) {
					MemberRegistrationBean bean1 = null;
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = " select distinct member.ad_member_id,member.name, " +
							" member.ad_society_no, member.ad_member_status_id ,f.final_pay_date as acc_close_date, " +
							" f.reson,f.reson_date from ad_trx l " +
							" LEFT JOIN ad_member member ON l.ad_member_id=member.ad_member_id " +
							" LEFT JOIN ad_member_status status ON  status.ad_member_status_id = member.ad_member_status_id " +
							" LEFT JOIN ad_voucher v ON l.ad_voucher_id=v.ad_voucher_id " +
							" LEFT JOIN ad_final_pay f ON l.ad_member_id=f.ad_member_id " +
							" WHERE member.ad_member_status_id in(1,2,4) AND member.ad_member_id =? " +
							" AND v.status='Approved'  order by  ad_society_no";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_member_id);
						System.out.print(ps);
						rs = ps.executeQuery();
						while (rs.next()) {
							bean1=new MemberRegistrationBean();
							bean1.setAd_member_id(rs.getInt("ad_member_id"));
							bean1.setAd_member_status_id(rs.getInt("ad_member_status_id"));
							bean1.setName(rs.getString("name").toUpperCase());
							bean1.setAd_society_no(rs.getInt("ad_society_no"));
							bean1.setAcc_close_date(rs.getDate("acc_close_date"));
							bean1.setAcc_closing_reason(rs.getString("reson"));
							bean1.setAcc_closing_reason_date(rs.getDate("reson_date"));
							

						}
					}catch (Exception e) {
						System.out.print("MemberRegistrationDao:-error in try Block");
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
	public int updateMemberRegistration(MemberRegistrationBean bean){
		int i=0;
		int mid=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_member SET updated=?, updatedby=?," +
					"isactive=?, name=?, father=?, husband=?, dob=?, ad_category_id=?," +
					"ad_gender_id=?, ad_member_type_id=?, ad_member_status_id=?, ad_salutation_id=?," +
					"ad_branch_id=?, ad_designation_type_id=?, " +
					"ad_designation_id=?, pan_no=?, aadhar_no=?, doa=?, doj=?, service_duration=?," +
					"dor=?, ad_department_id=?, saving_ac_no=?, photo=?, signature=?," +
					"id_proof=?, ad_witness_id=?,ad_pf_no=?, marital_status=? " +
					"WHERE ad_member_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, true);
			ps.setString(4, bean.getName().toUpperCase());
			ps.setString(5, bean.getFather());
			ps.setString(6, bean.getHusband());
			ps.setDate(7, new java.sql.Date(bean.getDob().getTime()));
			ps.setInt(8, bean.getCategory().getAd_category_id());
			ps.setInt(9, bean.getGender().getAd_gender_id());
			ps.setInt(10, bean.getMember_type().getAd_member_type_id());
			ps.setInt(11, bean.getMember_status().getAd_member_status_id());
			ps.setInt(12, bean.getSalutation().getAd_salutation_id());
			ps.setInt(13, bean.getBranch().getAd_branch_id());
			ps.setInt(14, bean.getDesignation_type().getAd_designation_type_id());
			ps.setInt(15, bean.getDesignation().getAd_designation_id());
			ps.setString(16, bean.getPan_no());
			ps.setString(17, bean.getAadhar_no());
			ps.setDate(18, new java.sql.Date(bean.getDoa().getTime()));
			ps.setDate(19, new java.sql.Date(bean.getDoj().getTime()));
			ps.setString(20, bean.getService_duration());
			ps.setDate(21, new java.sql.Date(bean.getDor().getTime()));
			ps.setInt(22, bean.getDepartment().getAd_department_id());
			ps.setString(23, bean.getSaving_ac_no());
			ps.setString(24, bean.getPhoto());
			ps.setString(25, bean.getSignature());
			ps.setString(26, bean.getId_proof());
			ps.setInt(27, bean.getAd_witness_id());
			ps.setInt(28, bean.getAd_pf_no());
			ps.setString(29, bean.getMarital_status());
			ps.setInt(30, bean.getAd_member_id());
			
			//System.out.print(ps);
			i=ps.executeUpdate();
			
	}catch (Exception e) {
		System.out.print("MemberRegistrationDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
		return i;
    }
	
}
//----------------------------------------------------------------------------------------------
	public void deleteMemberRegistration(MemberRegistrationBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_member WHERE ad_member_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("MemberRegistrationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
	
	
	//----------------------------------------------------------------------------------------------
		@SuppressWarnings("finally")
		public int getMemberRegistrationMaxId(){
			int result=0;
			PreparedStatement ps=null;
			ResultSet rs=null;
			try{
				String query="select ad_member_id from ad_member where ad_member_id=(select Max(ad_member_id) from ad_member)";
				ps=con.prepareStatement(query);
				
				rs=ps.executeQuery();
				if(rs.next()){
					result=rs.getInt("ad_member_id");
				}
			
				}catch (Exception e) {
				System.out.print("MemberRegistrationDao:-error in try Block");
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
			public int getVerifyMemberPFNo(int ad_pf_no){
				int result=0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="select ad_pf_no from ad_member where ad_pf_no=?";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_pf_no);
					rs=ps.executeQuery();
					if(rs.next()){
						result=rs.getInt("ad_pf_no");
					}
				
					}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
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
			public int getVerifyMemberPhoneNo(String phone_no){
				int result=0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="SELECT  phone FROM ad_member_address where trim(phone)=?";
					ps=con.prepareStatement(query);
					ps.setString(1, phone_no);
					rs=ps.executeQuery();
					if(rs.next()){
						result+=1;
					}
				
					}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
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
			public int getVerifyMemberEmail(String email){
				int result=0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="SELECT  email FROM ad_member_address where trim(email)=?";
					ps=con.prepareStatement(query);
					ps.setString(1, email.trim());
					rs=ps.executeQuery();
					if(rs.next()){
						result+=1;
					}
				
					}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
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
			public int getVerifyMemberMobileNo(String mobile_no){
				int result=0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="SELECT  mobile FROM ad_member_address where trim(mobile)=?";
					ps=con.prepareStatement(query);
					ps.setString(1, mobile_no);
					rs=ps.executeQuery();
					if(rs.next()){
						result+=1;
					}
				
					}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
					e.printStackTrace();
					
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return result;
				}
				
			

			//-------------------------------------------------------
			@SuppressWarnings("finally")
			public int getMemberSocietyNo(){
				int result=0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="select max(ad_society_no) as ad_society_no from ad_member";
					ps=con.prepareStatement(query);
					rs=ps.executeQuery();
					if(rs.next()){
						result=rs.getInt("ad_society_no");
					}
				
					}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
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
			public int getMemberSocietyNo(int ad_pf_no){
				int result=0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="select max(ad_society_no) as ad_society_no from ad_member where ad_pf_no=?";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_pf_no);
					rs=ps.executeQuery();
					if(rs.next()){
						result=rs.getInt("ad_society_no");
					}
				
					}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return result;
				}
				//----------------------------
			public int updateMemberStatusbymid(MemberRegistrationBean bean){
				int result=0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="UPDATE ad_member " +
							" SET  updated=now(), updatedby=?,   ad_member_status_id=?" +
							" WHERE ad_member_id=?";
					ps=con.prepareStatement(query);
					ps.setInt(1, bean.getUpdatedby());
					ps.setInt(2, 2);
					ps.setInt(3,bean.getAd_member_id());
					result=ps.executeUpdate();
					
					}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
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
			public int updateMemberBranch(int ad_member_id,int ad_branch_id){
				int result=0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="UPDATE ad_member " +
							" SET  ad_branch_id =? " +
							" WHERE ad_member_id=?";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_branch_id);
					ps.setInt(2, ad_member_id);
					result=ps.executeUpdate();
					
					}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
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
			public int updateMemberStatus(int ad_voucher_id,int ad_user_id){
				int result=0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="UPDATE ad_member " +
							" SET  updated=now(), updatedby=?,   ad_member_status_id=?" +
							" WHERE ad_voucher_id=?";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_user_id);
					ps.setInt(2, 1);
					ps.setInt(3, ad_voucher_id);
					result=ps.executeUpdate();
					
					}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return result;
				}
			//---------------------------------------------------------------------
			//----------------------------------------------------------------------------------------------
			@SuppressWarnings("finally")
			public int updateMemberStatusOfFinalPayment(int ad_user_id,boolean status,int ad_status_id,int ad_member_id){
				int result=0;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
					String query="UPDATE ad_member " +
							" SET  updated=now(), updatedby=?,isActive=?,   ad_member_status_id=?" +
							" WHERE ad_member_id=?";
					ps=con.prepareStatement(query);
					ps.setInt(1, ad_user_id);
					ps.setBoolean(2, status);
					ps.setInt(3, ad_status_id);
					ps.setInt(4, ad_member_id);
					result=ps.executeUpdate();
					
					}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
					e.printStackTrace();
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return result;
				}
			//---------------------------------------------------------------------
			public ArrayList<MemberRegistrationBean> getAllMemberHavingLoan() {
				ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "select m.ad_member_id,m.name,m.ad_society_no ,l.loan_trx_id from loan_trx l " +
						" LEFT JOIN ad_member m ON l.ad_member_id=m.ad_member_id AND l.isactive=true " +
						" where ad_member_status_id=1 order by ad_society_no ";
				try {
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						MemberRegistrationBean bean1 = new MemberRegistrationBean();
						bean1.setAd_member_id(rs.getInt("ad_member_id"));
						
						bean1.setName(rs.getString("name").toUpperCase());
						//bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
						bean1.setAd_society_no(rs.getInt("ad_society_no"));
						
						bean.add(bean1);
						//System.out.print(bean);

					}
				}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}
			//---------------------------------------------------------------------
			public ArrayList<MemberRegistrationBean> getAllMemberHavingLoanAccount() {
				ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "select distinct m.ad_member_id,m.name,m.ad_society_no from loan_trx l "
						+ "     LEFT JOIN ad_member m ON l.ad_member_id=m.ad_member_id " 
						+ "		where m.ad_member_status_id=1 "
						+ "     group by m.ad_member_id,m.name,m.ad_society_no "
						+ "     order by ad_society_no ";
				try {
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						MemberRegistrationBean bean1 = new MemberRegistrationBean();
						bean1.setAd_member_id(rs.getInt("ad_member_id"));
						
						bean1.setName(rs.getString("name").toUpperCase());
						//bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
						bean1.setAd_society_no(rs.getInt("ad_society_no"));
						
						bean.add(bean1);
						//System.out.print(bean);

					}
				}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}
			//---------------------------------------------------------------------
			public ArrayList<MemberRegistrationBean> getAllMemberHavingLoanAccount1() {
				ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "select distinct m.ad_member_id,m.name,m.ad_society_no from loan_trx l "
						+ "     LEFT JOIN ad_member m ON l.ad_member_id=m.ad_member_id " 
						+ "     group by m.ad_member_id,m.name,m.ad_society_no "
						+ "     order by ad_society_no ";
				try {
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						MemberRegistrationBean bean1 = new MemberRegistrationBean();
						bean1.setAd_member_id(rs.getInt("ad_member_id"));
						
						bean1.setName(rs.getString("name").toUpperCase());
						//bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
						bean1.setAd_society_no(rs.getInt("ad_society_no"));
						
						bean.add(bean1);
						//System.out.print(bean);

					}
				}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}		
			//---------------------------------------------------------------------
			public ArrayList<MemberRegistrationBean> getAllMemberHavingAcccount(int ad_account_id) {
				ArrayList<MemberRegistrationBean> bean = new ArrayList<MemberRegistrationBean>();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "select distinct m.ad_member_id,m.ad_society_no,m.name from ad_trx l " +
						" LEFT JOIN ad_member m ON l.ad_member_id=m.ad_member_id AND l.ad_account_id=? " +
						" LEFT JOIN ad_voucher v ON l.ad_voucher_id=v.ad_voucher_id  "  +
						" WHERE  m.ad_member_id IS NOT NULL AND v.status='Approved' order by ad_society_no ";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_account_id);
					rs = ps.executeQuery();
					while (rs.next()) {
						MemberRegistrationBean bean1 = new MemberRegistrationBean();
						bean1.setAd_member_id(rs.getInt("ad_member_id"));
						bean1.setName(rs.getString("name").toUpperCase());
						bean1.setAd_society_no(rs.getInt("ad_society_no"));
						
						bean.add(bean1);
						//System.out.print(bean);

					}
				}catch (Exception e) {
					System.out.print("MemberRegistrationDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}
							
}



