package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.MemberTypeBean;

public class MemberTypeDao {

	private Connection con = null;

	public MemberTypeDao() {
			con = DBConnection.getConnection();
		}

	public int addMemberType(MemberTypeBean bean) {
		int record=0;
		PreparedStatement ps=null;
		
		try {
			
			String query = "INSERT INTO ad_member_type(" +
					"created, createdby, updated, updatedby, isactive,member_type)" +
					" VALUES ( ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getMember_type());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("MemberTypeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public MemberTypeBean getMemberTypeById(MemberTypeBean bean) {
		MemberTypeBean bean1 = new MemberTypeBean();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_member_type where ad_member_type_id=? ";
		try {
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_type_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_member_type_id(rs.getInt("ad_member_type_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setMember_type(rs.getString("member_type"));
				
				

			}
			}catch (Exception e) {
				System.out.print("MemberTypeDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public MemberTypeBean getMemberTypeById(int ad_member_type_id) {
		MemberTypeBean bean1 = new MemberTypeBean();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_member_type where ad_member_type_id=? ";
		try {
			 ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_type_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_member_type_id(rs.getInt("ad_member_type_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setMember_type(rs.getString("member_type"));
				
			}
		}catch (Exception e) {
			System.out.print("MemberTypeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<MemberTypeBean> getAllMemberType() {
		ArrayList<MemberTypeBean> bean = new ArrayList<MemberTypeBean>();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_member_type order by ad_member_type_id";
		try {
			 ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberTypeBean bean1 = new MemberTypeBean();
				bean1.setAd_member_type_id(rs.getInt("ad_member_type_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setMember_type(rs.getString("member_type"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("MemberTypeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateMemberType(MemberTypeBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_member_type  SET  updated=?," +
					" updatedby=?, isactive=?, member_type=? WHERE ad_member_type_id=?";
			 ps = con.prepareStatement(query);
			
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getMember_type());
			ps.setInt(5, bean.getAd_member_type_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("MemberTypeDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deleteMemberType(MemberTypeBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_member_type WHERE ad_member_type_id=?";
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_type_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("MemberTypeDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}
	
}
