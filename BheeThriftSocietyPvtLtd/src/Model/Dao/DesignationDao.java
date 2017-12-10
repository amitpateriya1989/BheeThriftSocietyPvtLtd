package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.DesignationBean;

public class DesignationDao {
	private Connection con = null;

	public DesignationDao() {
		con = DBConnection.getConnection();
	}

	public int addDesignation(DesignationBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_designation(" +
					"created, createdby, updated, updatedby, isactive, designation,ad_designation_type_id)" +
					"VALUES (?, ?, ?, ?, ?, ?,?)";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, bean.isIsactive());
			ps.setString(6, bean.getDesignation());
			ps.setInt(7,bean.getAd_designation_type_id());
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("DesignationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
		
	}
//-----------------------------------------------------------------------------------------
	public DesignationBean getDesignationById(DesignationBean bean) {
		DesignationBean bean1 = new DesignationBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_designation where ad_designation_id=? and isactive=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_designation_id());
			ps.setBoolean(2,bean.isIsactive());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_designation_id(rs.getInt("ad_designation_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDesignation(rs.getString("designation"));
				bean1.setAd_designation_type_id(rs.getInt("ad_designation_type_id"));
			}
			}catch (Exception e) {
				System.out.print("DesignationDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

	
	//-----------------------------------------------------------------------------------------
	public int getDesignationIdByName(String  name) {
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select ad_designation_id from ad_designation where LOWER(designation)=LOWER(?) ";
		try {
			 ps = con.prepareStatement(query);
			ps.setString(1, name.trim());
		
			rs = ps.executeQuery();
			while (rs.next()) {
			
				result=rs.getInt("ad_designation_id");
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
		public int getDesignationByName(String  name) {
			int result=0;
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select count(designation) as cnt from ad_designation where LOWER(designation)=LOWER(?) ";
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
		public DesignationBean getDesignationById(int ad_designation_id) {
			DesignationBean bean1 = new DesignationBean();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from ad_designation where ad_designation_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_designation_id);
				
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_designation_id(rs.getInt("ad_designation_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setDesignation(rs.getString("designation"));
					bean1.setAd_designation_type_id(rs.getInt("ad_designation_type_id"));
				}
				}catch (Exception e) {
					System.out.print("DesignationDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean1;
			}

//------------------------------------------------------------------------------------------	
	public DesignationBean getDesignationById(int ad_designation_id,boolean isactive) {
		DesignationBean bean1 = new DesignationBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_designation where ad_designation_id=? and isactive=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_designation_id);
			ps.setBoolean(2, isactive);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_designation_id(rs.getInt("ad_designation_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDesignation(rs.getString("designation"));
				bean1.setAd_designation_type_id(rs.getInt("ad_designation_type_id"));

			}
		}catch (Exception e) {
			System.out.print("DesignationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<DesignationBean> getAllDesignation() {
		ArrayList<DesignationBean> bean = new ArrayList<DesignationBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_designation ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				DesignationBean bean1 = new DesignationBean();
				bean1.setAd_designation_id(rs.getInt("ad_designation_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDesignation(rs.getString("designation"));
				bean1.setAd_designation_type_id(rs.getInt("ad_designation_type_id"));

				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("DesignationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public ArrayList<DesignationBean> getDesignationByType(int ad_designation_type_id) {
		ArrayList<DesignationBean> bean = new ArrayList<DesignationBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_designation where ad_designation_type_id = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_designation_type_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				DesignationBean bean1 = new DesignationBean();
				bean1.setAd_designation_id(rs.getInt("ad_designation_id"));
				bean1.setDesignation(rs.getString("designation"));
				bean.add(bean1);
			}
		}catch (Exception e) {
			System.out.print("DesignationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	//--------------------------------------------------------------------------------------
	public ArrayList<DesignationBean> getAllDesignationName() {
		ArrayList<DesignationBean> bean = new ArrayList<DesignationBean>();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_designation ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				DesignationBean bean1 = new DesignationBean();
				bean1.setAd_designation_id(rs.getInt("ad_designation_id"));
				bean1.setDesignation(rs.getString("designation"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("DesignationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updatedDesignation(DesignationBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_designation" +
					" SET updated=?, updatedby=?,isactive=?, designation=?, ad_designation_type_id=? " +
					"WHERE ad_designation_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getDesignation());
			ps.setInt(5,bean.getAd_designation_type_id());
			ps.setInt(6, bean.getAd_designation_id());
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("DesignationDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		DBConnection.close(ps);
		DBConnection.close(con);
    }
		return i;
	
}
//----------------------------------------------------------------------------------------------
	public int deleteDesignation(DesignationBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_designation WHERE ad_designation_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_designation_id());
			ps.executeQuery();
		
			}catch (Exception e) {
			System.out.print("DesignationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}


}
