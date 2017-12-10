package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Bean.SocietyDepartmentBean;

public class SocietyDepartmentDao {
	private Connection con = null;

	public SocietyDepartmentDao() {
			con = DBConnection.getConnection();
		}

	public int addSocietyDepartment(SocietyDepartmentBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_society_department(" +
					"created, createdby, updated, updatedby, isactive,department)" +
					" VALUES ( ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getDepartment());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("SocietyDepartmentDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public SocietyDepartmentBean getSocietyDepartmentById(SocietyDepartmentBean bean) {
		SocietyDepartmentBean bean1 = new SocietyDepartmentBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_society_department where ad_society_department_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_society_department_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_society_department_id(rs.getInt("ad_society_department_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDepartment(rs.getString("department"));
				
				

			}
			}catch (Exception e) {
				System.out.print("SocietyDepartmentDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public SocietyDepartmentBean getSocietyDepartmentById(int ad_society_department_id) {
		SocietyDepartmentBean bean1 = new SocietyDepartmentBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_society_department where ad_society_department_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_society_department_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_society_department_id(rs.getInt("ad_society_department_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDepartment(rs.getString("department"));
				
			}
		}catch (Exception e) {
			System.out.print("SocietyDepartmentDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<SocietyDepartmentBean> getAllSocietyDepartment() {
		ArrayList<SocietyDepartmentBean> bean = new ArrayList<SocietyDepartmentBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_society_department ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				SocietyDepartmentBean bean1 = new SocietyDepartmentBean();
				bean1.setAd_society_department_id(rs.getInt("ad_society_department_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDepartment(rs.getString("department"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("SocietyDepartmentDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateSocietyDepartment(SocietyDepartmentBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_society_department  SET  updated=?," +
					" updatedby=?, isactive=?, department=? WHERE ad_society_department_id=?";
			ps = con.prepareStatement(query);
			
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getDepartment());
			ps.setInt(5, bean.getAd_society_department_id());
			
			
			//System.out.println(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("SocietyDepartmentDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deleteSocietyDepartment(SocietyDepartmentBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_society_department WHERE ad_society_department_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_society_department_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("SocietyDepartmentDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}
}
