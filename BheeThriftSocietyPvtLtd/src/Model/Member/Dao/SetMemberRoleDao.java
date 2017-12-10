package Model.Member.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import Model.Dao.DBConnection;
import Model.Member.Bean.SetMemberRoleBean;

public class SetMemberRoleDao {
	private Connection con = null;

	public SetMemberRoleDao() {
		con = DBConnection.getConnection();
		
	}

	public void addSetMemberRole(SetMemberRoleBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_member_login_role(" +
					"ad_role_id, set_member_role_id, isactive)" +
					"VALUES (?, ?, ?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_role_id());
			ps.setInt(2, bean.getAd_member_login_id());
			ps.setBoolean(3, bean.isIsactive());
			
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("MemberRoleDao:-error in try Block");
			e.printStackTrace();
			
		}
		
	}
//-----------------------------------------------------------------------------------------
	public SetMemberRoleBean getMemberById(SetMemberRoleBean bean) {
		SetMemberRoleBean bean1 = new SetMemberRoleBean();
		ResultSet rs=null;
		String query = "SELECT set_member_role_id, ad_role_id, ad_member_login_id, isactive" +
				" FROM set_member_role where set_member_role_id=? ";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_login_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setAd_member_login_id(rs.getInt("set_member_role_id"));
				bean1.setSet_member_role_id(rs.getInt("ad_member_login_id"));
				bean1.setIsactive(rs.getBoolean("isactive"));

			}
			}catch (Exception e) {
				System.out.print("MemberRoleDao:-error in try Block");
				e.printStackTrace();
				
			}
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public SetMemberRoleBean getMemberById(int ad_member_login_role_id) {
		SetMemberRoleBean bean1 = new SetMemberRoleBean();
		ResultSet rs=null;
		String query = "SELECT set_member_role_id, ad_role_id, ad_member_login_id, isactive" +
				" FROM set_member_role where set_member_role_id=? ";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_login_role_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setAd_member_login_id(rs.getInt("set_member_role_id"));
				bean1.setSet_member_role_id(rs.getInt("ad_member_login_id"));
				bean1.setIsactive(rs.getBoolean("isactive"));

			}
		}catch (Exception e) {
			System.out.print("MemberRoleDao:-error in try Block");
			e.printStackTrace();
			
		}
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<SetMemberRoleBean> getAllMember() {
		ArrayList<SetMemberRoleBean> bean = new ArrayList<SetMemberRoleBean>();
		ResultSet rs=null;
		String query = "SELECT set_member_role_id, ad_role_id, ad_member_login_id, isactive" +
				" FROM set_member_role  ";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				SetMemberRoleBean bean1 = new SetMemberRoleBean();
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setAd_member_login_id(rs.getInt("set_member_role_id"));
				bean1.setSet_member_role_id(rs.getInt("ad_member_login_id"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("MemberRoleDao:-error in try Block");
			e.printStackTrace();
			
		}
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public void updatedMember(SetMemberRoleBean bean){
		int i=0;
		try {

			String query = "UPDATE set_member_role" +
					" SET  ad_role_id=?, set_member_role_id=?, isactive=?" +
					" WHERE set_member_role_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_role_id());
			ps.setInt(2, bean.getAd_member_login_id());
			ps.setBoolean(3, bean.isIsactive());
			ps.setInt(4, bean.getSet_member_role_id());
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("MemberRoleDao:-error in try Block");
		e.printStackTrace();
		
	}
	
}
//----------------------------------------------------------------------------------------------
	public void deleteMember(SetMemberRoleBean bean){
		int i=0;
		try{
			String query="DELETE FROM ad_member_login_role WHERE ad_member_login_role_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, bean.getSet_member_role_id());
			ps.executeQuery();
		
			}catch (Exception e) {
			System.out.print("MemberRoleDao:-error in try Block");
			e.printStackTrace();
			
		}
		
	}
//----------------------------------------------------------------------------------------------
	public SetMemberRoleBean getMemberByMemberId(int set_member_role_id) {
		SetMemberRoleBean bean1 = new SetMemberRoleBean();
		ResultSet rs=null;
		String query = "SELECT set_member_role_id, ad_role_id, ad_member_login_id, isactive" +
				" FROM set_member_role where set_member_role_id=? ";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, set_member_role_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setAd_member_login_id(rs.getInt("set_member_role_id"));
				bean1.setSet_member_role_id(rs.getInt("ad_member_login_id"));
				bean1.setIsactive(rs.getBoolean("isactive"));

			}
			}catch (Exception e) {
				System.out.print("MemberRoleDao:-error in try Block");
				e.printStackTrace();
				
			}
			return bean1;
		}

}