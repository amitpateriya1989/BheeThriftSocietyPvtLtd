package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.MemberStatusBean;

public class MemberStatusDao {
	private Connection con = null;

	public MemberStatusDao() {
		con = DBConnection.getConnection();
	}

	public int addMemberStatus(MemberStatusBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_member_status(" +
					"created, createdby, updated, updatedby, isactive,member_status)" +
					" VALUES ( ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getMember_status());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("MemberStatusDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public MemberStatusBean getMemberStatusById(MemberStatusBean bean) {
		MemberStatusBean bean1 = new MemberStatusBean();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_member_status where ad_member_status_id=? ";
		try {
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_status_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_member_status_id(rs.getInt("ad_member_status_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setMember_status(rs.getString("member_status"));
				
				

			}
			}catch (Exception e) {
				System.out.print("MemberStatusDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public MemberStatusBean getMemberStatusById(int ad_member_status_id) {
		MemberStatusBean bean1 = new MemberStatusBean();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_member_status where ad_member_status_id=? ";
		try {
			 ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_status_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_member_status_id(rs.getInt("ad_member_status_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setMember_status(rs.getString("member_status"));
				
			}
		}catch (Exception e) {
			System.out.print("MemberStatusDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<MemberStatusBean> getAllMemberStatus() {
		ArrayList<MemberStatusBean> bean = new ArrayList<MemberStatusBean>();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_member_status order by ad_member_status_id ";
		try {
			 ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberStatusBean bean1 = new MemberStatusBean();
				bean1.setAd_member_status_id(rs.getInt("ad_member_status_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setMember_status(rs.getString("member_status"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("MemberStatusDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateMemberStatus(MemberStatusBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_member_status  SET  updated=?," +
					" updatedby=?, isactive=?, member_status=? WHERE ad_member_status_id=?";
			 ps = con.prepareStatement(query);
			
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getMember_status());
			ps.setInt(5, bean.getAd_member_status_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("MemberStatusDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deleteMemberStatus(MemberStatusBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_member_status WHERE ad_member_status_id=?";
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_status_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("MemberStatusDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
		
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}

}
