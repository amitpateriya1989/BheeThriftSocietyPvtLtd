package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomMemberInfoDao {
	
	private Connection con = null;
	
	public CustomMemberInfoDao() {
		try{
			con = DBConnection.getConnection();
			
			}catch(Exception s){
				System.out.println("CustomMemberInfoDao:-Exception in Creating connection and auto commit off");
				s.printStackTrace();
			}
	}
	
	//--------------------------------------------------------------------------------------
			public List<Map<String, Object>> getMemberDetails(String society_no, Date fdate, Date edate) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				
				int i= 1;
				String soc_no = society_no;
				
				PreparedStatement ps =null;
				ResultSet rs=null;
				String query = "select * from member_detail " +
						" where ad_member_created between ? and ? and ad_member_ad_society_no=?  order by ad_member_created ";
				try {
					ps = con.prepareStatement(query);
					ps.setDate(1, new java.sql.Date(fdate.getTime()));
					ps.setDate(2, new java.sql.Date(edate.getTime()));
					ps.setString(3, soc_no);
					System.out.println(ps);
					rs = ps.executeQuery();

					while (rs.next()) {

						Map<String, Object>  memberMap = new HashMap<String, Object>();
						memberMap.put("MemberId", rs.getInt("ad_member_ad_member_id"));
						memberMap.put("MemberUpdated", rs.getDate("ad_member_updated"));
						memberMap.put("MemberCreated", rs.getDate("ad_member_created"));
						memberMap.put("MemberActive", rs.getBoolean("ad_member_isactive"));
						memberMap.put("MemberPfNo", rs.getInt("ad_member_ad_pf_no"));
						memberMap.put("MemberSocietyNo", rs.getString("ad_member_ad_society_no"));
						memberMap.put("MemberType", rs.getString("ad_member_type_member_type"));
						memberMap.put("MemberStatus", rs.getString("ad_member_status_member_status"));
						memberMap.put("MemberSalutation", rs.getString("ad_salutation_name"));
						memberMap.put("MemberName", rs.getString("ad_member_name"));
						memberMap.put("MemberFather", rs.getString("ad_member_father"));
						memberMap.put("MemberHusband", rs.getString("ad_member_husband"));
						memberMap.put("MemberDob", rs.getDate("ad_member_dob"));
						memberMap.put("MemberGender", rs.getString("ad_gender_gender"));
						memberMap.put("MemberMaritalStatus", rs.getString("ad_member_marital_status"));
						memberMap.put("MemberCaste", rs.getString("ad_member_caste"));
						memberMap.put("MemberCategory", rs.getString("ad_category_category"));
						memberMap.put("MemberPanNo", rs.getString("ad_member_pan_no"));
						memberMap.put("MemberAadharNo", rs.getString("ad_member_aadhar_no"));
						memberMap.put("MemberDoa", rs.getDate("ad_member_doa"));
						memberMap.put("MemberDoj", rs.getDate("ad_member_doj"));
						memberMap.put("MemberServiceDuration", rs.getString("ad_member_service_duration"));
						memberMap.put("MemberDor", rs.getDate("ad_member_dor"));
						memberMap.put("MemberAc", rs.getString("ad_member_saving_ac_no"));
						memberMap.put("MemberPhoto", rs.getString("ad_member_photo"));
						memberMap.put("MemberSignature", rs.getString("ad_member_signature"));
						memberMap.put("MemberIdProof", rs.getString("ad_member_id_proof"));
						memberMap.put("MemberBranch", rs.getString("ad_branch_branch"));
						memberMap.put("MemberBankId", rs.getInt("ad_branch_ad_bank_id"));
						memberMap.put("MemberDesigType", rs.getString("ad_designation_type_designation_type"));
						memberMap.put("MemberDesigLevel", rs.getString("ad_designation_level_designation_level"));
						memberMap.put("MemberDesig", rs.getString("ad_designation_designation"));
						memberMap.put("MemberDeptName", rs.getString("ad_department_name"));
						memberMap.put("MemberTermination", rs.getString("ad_member_termination_status"));
						
						i= i+1;
						list.add(memberMap);
					}
					//System.out.println(list);
				}catch (Exception e) {
					System.out.print("CustomMemberInfoDao:-error in try Block");
					e.printStackTrace();
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return list;
			}
	//----------------------------------------------------------------------------------------------
			//--------------------------------------------------------------------------------------
			public List<Map<String, Object>> getMemberDetails() {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				
				int i= 1;
				
				PreparedStatement ps =null;
				ResultSet rs=null;
				String query = "select * from member_detail ";
				try {
					ps = con.prepareStatement(query);
					
					rs = ps.executeQuery();

					while (rs.next()) {

						Map<String, Object>  memberMap = new HashMap<String, Object>();
						memberMap.put("MemberId", rs.getInt("ad_member_ad_member_id"));
						memberMap.put("MemberUpdated", rs.getDate("ad_member_updated"));
						memberMap.put("MemberCreated", rs.getDate("ad_member_created"));
						memberMap.put("MemberActive", rs.getBoolean("ad_member_isactive"));
						memberMap.put("MemberPfNo", rs.getInt("ad_member_ad_pf_no"));
						memberMap.put("MemberSocietyNo", rs.getString("ad_member_ad_society_no"));
						memberMap.put("MemberType", rs.getString("ad_member_type_member_type"));
						memberMap.put("MemberStatus", rs.getString("ad_member_status_member_status"));
						memberMap.put("MemberSalutation", rs.getString("ad_salutation_name"));
						memberMap.put("MemberName", rs.getString("ad_member_name"));
						memberMap.put("MemberFather", rs.getString("ad_member_father"));
						memberMap.put("MemberHusband", rs.getString("ad_member_husband"));
						memberMap.put("MemberDob", rs.getDate("ad_member_dob"));
						memberMap.put("MemberGender", rs.getString("ad_gender_gender"));
						memberMap.put("MemberMaritalStatus", rs.getString("ad_member_marital_status"));
						memberMap.put("MemberCaste", rs.getString("ad_member_caste"));
						memberMap.put("MemberCategory", rs.getString("ad_category_category"));
						memberMap.put("MemberPanNo", rs.getString("ad_member_pan_no"));
						memberMap.put("MemberAadharNo", rs.getString("ad_member_aadhar_no"));
						memberMap.put("MemberDoa", rs.getDate("ad_member_doa"));
						memberMap.put("MemberDoj", rs.getDate("ad_member_doj"));
						memberMap.put("MemberServiceDuration", rs.getString("ad_member_service_duration"));
						memberMap.put("MemberDor", rs.getDate("ad_member_dor"));
						memberMap.put("MemberAc", rs.getString("ad_member_saving_ac_no"));
						memberMap.put("MemberPhoto", rs.getString("ad_member_photo"));
						memberMap.put("MemberSignature", rs.getString("ad_member_signature"));
						memberMap.put("MemberIdProof", rs.getString("ad_member_id_proof"));
						memberMap.put("MemberBranch", rs.getString("ad_branch_branch"));
						memberMap.put("MemberBankId", rs.getInt("ad_branch_ad_bank_id"));
						memberMap.put("MemberDesigType", rs.getString("ad_designation_type_designation_type"));
						memberMap.put("MemberDesigLevel", rs.getString("ad_designation_level_designation_level"));
						memberMap.put("MemberDesig", rs.getString("ad_designation_designation"));
						memberMap.put("MemberDeptName", rs.getString("ad_department_name"));
						memberMap.put("MemberTermination", rs.getString("ad_member_termination_status"));
						
						i= i+1;
						list.add(memberMap);
					}
					//System.out.println(list);
				}catch (Exception e) {
					System.out.print("CustomMemberInfoDao:-error in try Block");
					e.printStackTrace();
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return list;
			}
			
			
			//--------------------------------------------------------------------------------------
			public List<Map<String, Object>> getMemberDetailsById(int ad_member_id) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				
				int i= 1;
				
				PreparedStatement ps =null;
				ResultSet rs=null;
				String query = "select * from member_detail where ad_member_ad_member_id=? ";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_member_id);
					rs = ps.executeQuery();

					while (rs.next()) {

						Map<String, Object>  memberMap = new HashMap<String, Object>();
						memberMap.put("MemberId", rs.getInt("ad_member_ad_member_id"));
						memberMap.put("MemberUpdated", rs.getDate("ad_member_updated"));
						memberMap.put("MemberCreated", rs.getDate("ad_member_created"));
						memberMap.put("MemberActive", rs.getBoolean("ad_member_isactive"));
						memberMap.put("MemberPfNo", rs.getInt("ad_member_ad_pf_no"));
						memberMap.put("MemberSocietyNo", rs.getString("ad_member_ad_society_no"));
						memberMap.put("MemberType", rs.getString("ad_member_type_member_type"));
						memberMap.put("MemberStatus", rs.getString("ad_member_status_member_status"));
						memberMap.put("MemberSalutation", rs.getString("ad_salutation_name"));
						memberMap.put("MemberName", rs.getString("ad_member_name"));
						memberMap.put("MemberFather", rs.getString("ad_member_father"));
						memberMap.put("MemberHusband", rs.getString("ad_member_husband"));
						memberMap.put("MemberDob", rs.getDate("ad_member_dob"));
						memberMap.put("MemberGender", rs.getString("ad_gender_gender"));
						memberMap.put("MemberMaritalStatus", rs.getString("ad_member_marital_status"));
						memberMap.put("MemberCaste", rs.getString("ad_member_caste"));
						memberMap.put("MemberCategory", rs.getString("ad_category_category"));
						memberMap.put("MemberPanNo", rs.getString("ad_member_pan_no"));
						memberMap.put("MemberAadharNo", rs.getString("ad_member_aadhar_no"));
						memberMap.put("MemberDoa", rs.getDate("ad_member_doa"));
						memberMap.put("MemberDoj", rs.getDate("ad_member_doj"));
						memberMap.put("MemberServiceDuration", rs.getString("ad_member_service_duration"));
						memberMap.put("MemberDor", rs.getDate("ad_member_dor"));
						memberMap.put("MemberAc", rs.getString("ad_member_saving_ac_no"));
						memberMap.put("MemberPhoto", rs.getString("ad_member_photo"));
						memberMap.put("MemberSignature", rs.getString("ad_member_signature"));
						memberMap.put("MemberIdProof", rs.getString("ad_member_id_proof"));
						memberMap.put("MemberBranch", rs.getString("ad_branch_branch"));
						memberMap.put("MemberBankId", rs.getInt("ad_branch_ad_bank_id"));
						memberMap.put("MemberDesigType", rs.getString("ad_designation_type_designation_type"));
						memberMap.put("MemberDesigLevel", rs.getString("ad_designation_level_designation_level"));
						memberMap.put("MemberDesig", rs.getString("ad_designation_designation"));
						memberMap.put("MemberDeptName", rs.getString("ad_department_name"));
						memberMap.put("MemberTermination", rs.getString("ad_member_termination_status"));
						
						i= i+1;
						list.add(memberMap);
					}
					//System.out.println(list);
				}catch (Exception e) {
					System.out.print("CustomMemberInfoDao:-error in try Block");
					e.printStackTrace();
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return list;
			}
}//end class