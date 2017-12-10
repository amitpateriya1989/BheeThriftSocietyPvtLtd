package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.MemberRegistrationMasterBean;

public class MemberRegistrationMasterDao {

	private Connection con = null;

	public MemberRegistrationMasterDao() {
			con = DBConnection.getConnection();
		}

	public int addMemberRegistrationMaster(MemberRegistrationMasterBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_member_registration_master(" +
					"created, createdby, updated, updatedby, isactive, membership_fees, " +
					"fgds_fund, dcf, share) VALUES ( ?, ?, ?,?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setDouble(6, bean.getMembership_fees());
			ps.setDouble(7, bean.getFgds_fund());
			ps.setDouble(8, bean.getDcf());
			ps.setInt(9, bean.getShare());
			
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("MemberRegistrationMasterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public MemberRegistrationMasterBean getMemberRegistrationMasterById(MemberRegistrationMasterBean bean) {
		MemberRegistrationMasterBean bean1 = new MemberRegistrationMasterBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_member_registration_master where ad_member_registration_master_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_registration_master_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_member_registration_master_id(rs.getInt("ad_member_registration_master_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setMembership_fees(rs.getDouble("membership_fees"));
				bean1.setFgds_fund(rs.getDouble("fgds_fund"));
				bean1.setDcf(rs.getDouble("dcf"));
				bean1.setShare(rs.getInt("share"));
				
				

			}
			}catch (Exception e) {
				System.out.print("MemberRegistrationMasterDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	
	//-----------------------------------------------------------------------------------------
		public MemberRegistrationMasterBean getMemberRegistrationMasterByMaxId() {
			MemberRegistrationMasterBean bean1 = new MemberRegistrationMasterBean();
			PreparedStatement ps = null;
			ResultSet rs=null;
			String query = "select * from ad_member_registration_master where " +
					"ad_member_registration_master_id=(select max(ad_member_registration_master_id)" +
					"from ad_member_registration_master)";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_member_registration_master_id(rs.getInt("ad_member_registration_master_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setMembership_fees(rs.getDouble("membership_fees"));
					bean1.setFgds_fund(rs.getDouble("fgds_fund"));
					bean1.setDcf(rs.getDouble("dcf"));
					bean1.setShare(rs.getInt("share"));
					
					

				}
				}catch (Exception e) {
					System.out.print("MemberRegistrationMasterDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean1;
			}

//------------------------------------------------------------------------------------------	
	public MemberRegistrationMasterBean getMemberRegistrationMasterById(int ad_member_registration_master_id) {
		MemberRegistrationMasterBean bean1 = new MemberRegistrationMasterBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_member_registration_master where ad_member_registration_master_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_registration_master_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_member_registration_master_id(rs.getInt("ad_member_registration_master_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setMembership_fees(rs.getDouble("membership_fees"));
				bean1.setFgds_fund(rs.getDouble("fgds_fund"));
				bean1.setDcf(rs.getDouble("dcf"));
				bean1.setShare(rs.getInt("share"));
				
			}
		}catch (Exception e) {
			System.out.print("MemberRegistrationMasterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<MemberRegistrationMasterBean> getAllMemberRegistrationMaster() {
		ArrayList<MemberRegistrationMasterBean> bean = new ArrayList<MemberRegistrationMasterBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_member_registration_master ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberRegistrationMasterBean bean1 = new MemberRegistrationMasterBean();
				bean1.setAd_member_registration_master_id(rs.getInt("ad_member_registration_master_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setMembership_fees(rs.getDouble("membership_fees"));
				bean1.setFgds_fund(rs.getDouble("fgds_fund"));
				bean1.setDcf(rs.getDouble("dcf"));
				bean1.setShare(rs.getInt("share"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("MemberRegistrationMasterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateMemberRegistrationMaster(MemberRegistrationMasterBean bean){
		int i=0;
		PreparedStatement ps = null;
		
		try {

			String query = "UPDATE ad_member_registration_master " +
					"SET  updated=?,updatedby=?, isactive=?, membership_fees=?, fgds_fund=?," +
					" dcf=?, share=? WHERE ad_member_registration_master_id=?";
			ps = con.prepareStatement(query);
			
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.getIsactive());
			ps.setDouble(4, bean.getMembership_fees());
			ps.setDouble(5, bean.getFgds_fund());
			ps.setDouble(6, bean.getDcf());
			ps.setInt(7, bean.getShare());
			ps.setInt(8, bean.getAd_member_registration_master_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("MemberRegistrationMasterDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
		return i;
	
}
//----------------------------------------------------------------------------------------------
	public int deleteMemberRegistrationMaster(MemberRegistrationMasterBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_member_registration_master WHERE ad_member_registration_master_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_registration_master_id());
			i = ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("MemberRegistrationMasterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}
}
