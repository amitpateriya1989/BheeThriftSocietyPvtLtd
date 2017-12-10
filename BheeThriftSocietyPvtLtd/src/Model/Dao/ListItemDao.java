package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Bean.ListItemBean;



public class ListItemDao {
	private Connection con = null;

	public ListItemDao() {
		con = DBConnection.getConnection();
	}

	public int addListItem(ListItemBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_list_item( " +
					"ad_list_id, created, createdby, updated, updatedby,isactive, name) " +
					" VALUES ( ?, ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_list_id());
			ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(3, bean.getCreatedby());
			ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(5, bean.getUpdatedby());
			ps.setBoolean(6, true);
			ps.setString(7, bean.getName());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("ListItemDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public ListItemBean getListItemById(ListItemBean bean) {
		ListItemBean bean1 = new ListItemBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_list_item where ad_list_item_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_list_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_list_id(rs.getInt("ad_list_item_id"));
				bean1.setAd_list_id(rs.getInt("ad_list_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
			}
			}catch (Exception e) {
				System.out.print("ListItemDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public ListItemBean getListItemById(int ad_list_item_id) {
		ListItemBean bean1 = new ListItemBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_list_item where ad_list_item_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_list_item_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_list_item_id(rs.getInt("ad_list_item_id"));
				bean1.setAd_list_id(rs.getInt("ad_list_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
			}
		}catch (Exception e) {
			System.out.print("ListItemDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
	
	//------------------------------------------------------------------------------------------	
		public ArrayList<ListItemBean> getListItemByListId(int ad_list_id) {
			ArrayList<ListItemBean> bean = new ArrayList<ListItemBean>();
			
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_list_item where ad_list_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_list_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					ListItemBean bean1 = new ListItemBean();
					bean1.setAd_list_item_id(rs.getInt("ad_list_item_id"));
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
				System.out.print("ListItemDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
//--------------------------------------------------------------------------------------
	public ArrayList<ListItemBean> getAllListItem() {
		ArrayList<ListItemBean> bean = new ArrayList<ListItemBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_list_item order by name ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				ListItemBean bean1 = new ListItemBean();
				bean1.setAd_list_id(rs.getInt("ad_list_id"));
				bean1.setAd_list_item_id(rs.getInt("ad_list_item_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("ListItemDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateListItem(ListItemBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_list_item " +
					" SET  ad_list_id=?, updated=?," +
					" updatedby=?, isactive=?, name=? WHERE ad_list_item_id=? ";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_list_id());
			ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(3, bean.getUpdatedby());
			ps.setBoolean(4, bean.isIsactive());
			ps.setString(5, bean.getName());
			ps.setInt(6, bean.getAd_list_item_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("ListItemDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deleteListItem(ListItemBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_list_item WHERE ad_list_item_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_list_item_id());
			i=ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("ListItemDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}




}
