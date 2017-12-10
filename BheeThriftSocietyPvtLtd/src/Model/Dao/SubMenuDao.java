package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.MenuBean;
import Model.Bean.SetAssignMenuBean;
import Model.Bean.SetAssignSubMenuBean;
import Model.Bean.SubMenuBean;

public class SubMenuDao {
	private Connection con = null;

	public SubMenuDao() {
		con = DBConnection.getConnection();
			
	}

	public void addSubMenu(SubMenuBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_sub_menu(ad_menu_id, name, link)" +
					"VALUES ( ?, ?, ?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_menu_id());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getLink());
			
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("SubMenuDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
//-----------------------------------------------------------------------------------------
	public SubMenuBean getSubMenuById(SubMenuBean bean) {
		SubMenuBean bean1 = new SubMenuBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_sub_menu where ad_sub_menu_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_sub_menu_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_sub_menu_id(rs.getInt("ad_sub_menu_id"));
				bean1.setAd_menu_id(rs.getInt("ad_menu_id"));
				bean1.setName(rs.getString("name"));
				bean1.setLink(rs.getString("link"));
				
			}
			}catch (Exception e) {
				System.out.print("SubMenuDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public SubMenuBean getSubMenuById(int SubMenu_id) {
		SubMenuBean bean1 = new SubMenuBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_sub_menu where ad_sub_menu_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, SubMenu_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_sub_menu_id(rs.getInt("ad_sub_menu_id"));
				bean1.setAd_menu_id(rs.getInt("ad_menu_id"));
				bean1.setName(rs.getString("name"));
				bean1.setLink(rs.getString("link"));

			}
		}catch (Exception e) {
			System.out.print("SubMenuDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<SubMenuBean> getAllSubMenuByMeuId(ArrayList<MenuBean> menulist) {
		ArrayList<SubMenuBean> bean = new ArrayList<SubMenuBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		if(menulist!=null){
			for(MenuBean submenu:menulist)
			{
				String query = "select * from ad_sub_menu where ad_menu_id=? ";
				Connection c=DBConnection.getConnection();
				try {
					ps = c.prepareStatement(query);
					ps.setInt(1, submenu.getAd_menu_id());
					
					rs = ps.executeQuery();
			while (rs.next()) {
				SubMenuBean bean1 = new SubMenuBean();
				bean1.setAd_sub_menu_id(rs.getInt("ad_sub_menu_id"));
				bean1.setAd_menu_id(rs.getInt("ad_menu_id"));
				bean1.setName(rs.getString("name"));
				bean1.setLink(rs.getString("link"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("SubMenuDao:-error in try Block");
			e.printStackTrace();
			
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(c);
		    }
		
			}
		}
		return bean;
	}
	//--------------------------------------------------------------------------------------
		public ArrayList<SubMenuBean> getAllSubMenuByMenuId(int ad_menu_id) {
			ArrayList<SubMenuBean> bean = new ArrayList<SubMenuBean>();
			PreparedStatement ps = null;
			ResultSet rs=null;
			String query = "select * from ad_sub_menu where ad_menu_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_menu_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					SubMenuBean bean1 = new SubMenuBean();
					bean1.setAd_sub_menu_id(rs.getInt("ad_sub_menu_id"));
					bean1.setAd_menu_id(rs.getInt("ad_menu_id"));
					bean1.setName(rs.getString("name"));
					bean1.setLink(rs.getString("link"));
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("SubMenuDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
//----------------------------------------------------------------------------------------------
	public void updateSubMenu(SubMenuBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_sub_menu SET  ad_menu_id=?, name=?, link=?" +
					"WHERE ad_sub_menu_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_menu_id());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getLink());
			ps.setInt(4, bean.getAd_sub_menu_id());
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("SubMenuDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public void deleteSubMenu(SubMenuBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_sub_menu WHERE ad_sub_menu_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_sub_menu_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("SubMenuDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}

}
