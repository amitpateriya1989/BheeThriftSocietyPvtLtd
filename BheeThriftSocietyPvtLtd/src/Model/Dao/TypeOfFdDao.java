package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.TypeOfFdBean;

public class TypeOfFdDao {
	private Connection con = null;

	public TypeOfFdDao() {
		con = DBConnection.getConnection();
			
	}

	public int addTypeOfFd(TypeOfFdBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_type_of_fd(" +
					"created, createdby, updated, updatedby, isactive,name)" +
					" VALUES ( ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getName());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("TypeOfFdDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public TypeOfFdBean gettypeoffdById(TypeOfFdBean bean) {
		TypeOfFdBean bean1 = new TypeOfFdBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_type_of_fd where ad_type_of_fd_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_type_of_fd_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_type_of_fd_id(rs.getInt("ad_typeoffd_id"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
			}
			}catch (Exception e) {
				System.out.print("TypeOfFdDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public TypeOfFdBean gettypeoffdById(int ad_typeoffd_id) {
		TypeOfFdBean bean1 = new TypeOfFdBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_type_of_fd where ad_type_of_fd_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_typeoffd_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_type_of_fd_id(rs.getInt("ad_type_of_fd_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
			}
		}catch (Exception e) {
			System.out.print("TypeOfFdDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<TypeOfFdBean> getAlltypeoffd() {
		ArrayList<TypeOfFdBean> bean = new ArrayList<TypeOfFdBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_type_of_fd ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				TypeOfFdBean bean1 = new TypeOfFdBean();
				bean1.setAd_type_of_fd_id(rs.getInt("ad_type_of_fd_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("TypeOfFdDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updatetypeoffd(TypeOfFdBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_type_of_fd  SET  updated=?," +
					" updatedby=?, isactive=?, name=? WHERE ad_type_of_fd_id=?";
			ps = con.prepareStatement(query);
			
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getName());
			ps.setInt(5, bean.getAd_type_of_fd_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("TypeOfFdDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deletetypeoffd(TypeOfFdBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_type_of_fd WHERE ad_type_of_fd_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_type_of_fd_id());
			i = ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("TypeOfFdDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}


}
