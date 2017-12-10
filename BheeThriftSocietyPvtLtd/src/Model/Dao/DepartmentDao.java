package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.DepartmentBean;


public class DepartmentDao {

	private Connection con = null;

	public DepartmentDao() {
		con = DBConnection.getConnection();
	}

	public void addDepartment(DepartmentBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_department(ad_client_id, created, createdby," +
					"name, entrydate,description, duration, isactive,updated,updatedby)" +
					"VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_client_id());
			ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(3, bean.getCreatedby());
			ps.setString(4, bean.getName());
			ps.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			ps.setString(6, bean.getDescription());
			ps.setString(7,bean.getDuration());
			ps.setBoolean(8, bean.isIsactive());
			ps.setDate(9, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(10, bean.getUpdatedby());
			
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("DepartmentDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
	///////////----------------------------------------
	public int getDepartmentByName(String  name) {
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select count(name) as cnt from ad_department where LOWER(name)=LOWER(?) ";
		try {
			 ps = con.prepareStatement(query);
			ps.setString(1, name.trim());
		
			rs = ps.executeQuery();
			while (rs.next()) {
			
				result=rs.getInt("cnt");
			}
			}catch (Exception e) {
				System.out.print("DesignationDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return result;
		}

	
	//-----------------------------------------------------------------------------------------
	public int getDepartmentIdByName(String  name) {
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select ad_department_id from ad_department where LOWER(name)=LOWER(?) ";
		try {
			 ps = con.prepareStatement(query);
			ps.setString(1, name.trim());
		
			rs = ps.executeQuery();
			while (rs.next()) {
			
				result=rs.getInt("ad_department_id");
			}
			}catch (Exception e) {
				System.out.print("DesignationDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return result;
		}

//-----------------------------------------------------------------------------------------
	public DepartmentBean getDepartmentById(DepartmentBean bean) {
		DepartmentBean bean1 = new DepartmentBean();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_department where ad_department_id=? ";
		try {
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_department_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_department_id(rs.getInt("ad_department_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setEntrydate(rs.getDate("entrydate"));
				bean1.setName(rs.getString("name"));
				bean1.setDescription(rs.getString("description"));
				bean1.setDuration(rs.getString("duration"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				
				}
			}catch (Exception e) {
				System.out.print("DepartmentDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public DepartmentBean getDepartmentById(int Department_id) {
		DepartmentBean bean1 = new DepartmentBean();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_department where ad_department_id=? ";
		try {
			 ps = con.prepareStatement(query);
			ps.setInt(1, Department_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_department_id(rs.getInt("ad_department_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setEntrydate(rs.getDate("entrydate"));
				bean1.setName(rs.getString("name"));
				bean1.setDescription(rs.getString("description"));
				bean1.setDuration(rs.getString("duration"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				
			}
		}catch (Exception e) {
			System.out.print("DepartmentDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<DepartmentBean> getAllDepartment() {
		ArrayList<DepartmentBean> bean = new ArrayList<DepartmentBean>();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_department ";
		try {
			 ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				DepartmentBean bean1 = new DepartmentBean();
				bean1.setAd_department_id(rs.getInt("ad_department_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setEntrydate(rs.getDate("entrydate"));
				bean1.setName(rs.getString("name"));
				bean1.setDescription(rs.getString("description"));
				bean1.setDuration(rs.getString("duration"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("DepartmentDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	//--------------------------------------------------------------------------------------
		public ArrayList<DepartmentBean> getAllDepartmentName() {
			ArrayList<DepartmentBean> bean = new ArrayList<DepartmentBean>();
			ResultSet rs=null;
			PreparedStatement ps =null;
			String query = "select * from ad_department ";
			try {
				 ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					DepartmentBean bean1 = new DepartmentBean();
					bean1.setAd_department_id(rs.getInt("ad_department_id"));
					bean1.setName(rs.getString("name"));
				    bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("DepartmentDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
//----------------------------------------------------------------------------------------------
	public void updateDepartment(DepartmentBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_department" +
					"SET  ad_client_id=?,updated=?,updatedby=?, name=?," +
					"entrydate=?, description=?, duration=?, isactive=?" +
					"WHERE ad_department_id=?";
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_client_id());
			ps.setDate(2, new java.sql.Date(bean.getUpdated().getTime()));
			ps.setInt(3, bean.getUpdatedby());
			ps.setString(4, bean.getName());
			ps.setDate(5, new java.sql.Date(bean.getEntrydate().getTime()));
			ps.setString(6, bean.getDescription());
			ps.setString(7,bean.getDuration());
			ps.setBoolean(8, bean.isIsactive());
			ps.setInt(9, bean.getAd_department_id());
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("DepartmentDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public void deleteDepartment(DepartmentBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_department WHERE ad_department_id=?";
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_department_id());
			ps.executeQuery();
		
			}catch (Exception e) {
			System.out.print("DepartmentDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}

	
}
