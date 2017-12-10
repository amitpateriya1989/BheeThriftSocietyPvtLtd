package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Bean.ListBean;



public class ListDao {
	private Connection con = null;

	public ListDao() {
		con = DBConnection.getConnection();
	}

	public int addList(ListBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_list(" +
					"created, createdby, updated, updatedby, isactive," +
					"name) VALUES (?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getName());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("ListDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public ListBean getListById(ListBean bean) {
		ListBean bean1 = new ListBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_list where ad_list_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_list_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_list_id(rs.getInt("ad_list_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
			}
			}catch (Exception e) {
				System.out.print("ListDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public ListBean getListById(int ad_list_id) {
		ListBean bean1 = new ListBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_list where ad_list_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_list_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_list_id(rs.getInt("ad_list_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
			}
		}catch (Exception e) {
			System.out.print("ListDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<ListBean> getAllList() {
		ArrayList<ListBean> bean = new ArrayList<ListBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_list order by name ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				ListBean bean1 = new ListBean();
				bean1.setAd_list_id(rs.getInt("ad_list_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("ListDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateList(ListBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_list " +
					"SET  updated=?, updatedby=?, isactive=?, name=? " +
					"WHERE ad_list_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getName());
			ps.setInt(5, bean.getAd_list_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("ListDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deleteList(ListBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_list WHERE ad_list_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_list_id());
			i=ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("ListDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}




}
