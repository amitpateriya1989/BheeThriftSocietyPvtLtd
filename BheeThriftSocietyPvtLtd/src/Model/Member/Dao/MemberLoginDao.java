package Model.Member.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Bean.MemberRegistrationBean;
import Model.Dao.DBConnection;
import Model.Dao.MemberRegistrationDao;
import Model.Member.Bean.MemberLoginBean;

public class MemberLoginDao {
	private Connection con = null;

	public MemberLoginDao() {
		con = DBConnection.getConnection();
		//System.out.print(con);
	}

	public void addMemberLogin(MemberLoginBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_member_login(created, createdby, updated, updatedby, isactive, " +
					"ad_member_id, username, password, confirm, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(query);
			
			ps.setDate(1, new java.sql.Date(bean.getCreated().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(bean.getUpdated().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, bean.isIsactive());
			ps.setInt(6, bean.getMember().getAd_member_id());
			ps.setString(7, bean.getUsername());
			ps.setString(8, bean.getPassword());
			ps.setString(9, bean.getConfirm());
			ps.setString(10, bean.getStatus());
			
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("MemberLoginDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
            
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		
	}
//-----------------------------------------------------------------------------------------
	public MemberLoginBean getMemberLoginById(MemberLoginBean bean) {
		MemberLoginBean bean1 = new MemberLoginBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_member_login where ad_member_login_id=? and isactive=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_login_id());
			ps.setBoolean(2,bean.isIsactive());
			rs = ps.executeQuery();
			while (rs.next()) {
				
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setUsername(rs.getString("username"));
				bean1.setPassword(rs.getString("password"));
				bean1.setConfirm(rs.getString("confirm"));
				bean1.setStatus(rs.getString("status"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_member_login_id(rs.getInt("ad_member_login_id"));
				bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));

			}
			}catch (Exception e) {
				System.out.print("MemberLoginDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
	        }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public MemberLoginBean getMemberLoginById(int ad_member_login_id,boolean isactive) {
		MemberLoginBean bean1 = new MemberLoginBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_member_login where ad_member_login_id=? and isactive=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_login_id);
			ps.setBoolean(2, isactive);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setUsername(rs.getString("username"));
				bean1.setPassword(rs.getString("password"));
				bean1.setConfirm(rs.getString("confirm"));
				bean1.setStatus(rs.getString("status"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_member_login_id(rs.getInt("ad_member_login_id"));
				bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));


			}
		}catch (Exception e) {
			System.out.print("MemberLoginDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<MemberLoginBean> getAllMemberLogin() {
		ArrayList<MemberLoginBean> bean = new ArrayList<MemberLoginBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_member_login ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberLoginBean bean1 = new MemberLoginBean();
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setUsername(rs.getString("username"));
				bean1.setPassword(rs.getString("password"));
				bean1.setConfirm(rs.getString("confirm"));
				bean1.setStatus(rs.getString("status"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_member_login_id(rs.getInt("ad_member_login_id"));
				bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));


				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("MemberLoginDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public void updatedMemberLogin(MemberLoginBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_member_login" +
					" SET   updated=?, updatedby=?,isactive=?, username=?, password=?, confirm=?, status=?" +
					" WHERE ad_member_login_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(bean.getUpdated().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getUsername());
			ps.setString(5, bean.getPassword());
			ps.setString(6, bean.getConfirm());
			ps.setString(7, bean.getStatus());
			ps.setInt(8, bean.getAd_member_login_id());
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("MemberLoginDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public void deleteMemberLogin(MemberLoginBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_member_login WHERE ad_member_login_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_login_id());
			ps.executeQuery();
		
			}catch (Exception e) {
			System.out.print("MemberLoginDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
//----------------------------------------------------------------------------------------------
	public MemberLoginBean getVerifyMemberLogin(String id,String password,boolean isactive) {
		MemberLoginBean bean1 = null;
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_member_login where username=? and password=? and isactive=?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, password);
			ps.setBoolean(3, isactive);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1=new MemberLoginBean();
				
				bean1.setUsername(rs.getString("username"));
				bean1.setPassword(rs.getString("password"));
				bean1.setConfirm(rs.getString("confirm"));
				bean1.setStatus(rs.getString("status"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_member_login_id(rs.getInt("ad_member_login_id"));
				bean1.setMember(new MemberRegistrationDao().getMemberRegistrationById(rs.getInt("ad_member_id")));


			}
			}catch (Exception e) {
				System.out.print("MemberLoginDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
		//System.out.print(bean1.getUsername());
			return bean1;
		}
}