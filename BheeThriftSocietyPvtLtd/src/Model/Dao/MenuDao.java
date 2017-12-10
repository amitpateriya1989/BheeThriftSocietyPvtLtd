package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.MenuBean;
import Model.Bean.SetAssignMenuBean;

public class MenuDao {
	
		private Connection con = null;

		public MenuDao() {
				con = DBConnection.getConnection();
				
		}

		public void addMenu(MenuBean bean) {
			int record=0;
			PreparedStatement ps=null;
			try {
				
				String query = "INSERT INTO ad_menu(name, link, window_name, purpose)" +
						"VALUES (?, ?, ?, ?)";
				ps = con.prepareStatement(query);
				ps.setString(1, bean.getName());
				ps.setString(2, bean.getLink());
				ps.setString(3, bean.getWindow_name());
				ps.setString(4, bean.getPurpose());
				
				record=ps.executeUpdate();
				
			} catch (Exception e) {
				System.out.print("MenuDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			
		}
	//-----------------------------------------------------------------------------------------
		public MenuBean getMenuById(MenuBean bean) {
			MenuBean bean1 = new MenuBean();
			PreparedStatement ps = null;
			ResultSet rs=null;
			String query = "select * from ad_menu where ad_menu_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, bean.getAd_menu_id());
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_menu_id(rs.getInt("ad_menu_id"));
					bean1.setName(rs.getString("name"));
					bean1.setLink(rs.getString("link"));
					bean1.setWindow_name(rs.getString("window_name"));
					bean1.setPurpose(rs.getString("purpose"));
					

				}
				}catch (Exception e) {
					System.out.print("MenuDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				
				return bean1;
			}

	//------------------------------------------------------------------------------------------	
		public MenuBean getMenuById(int Menu_id) {
			MenuBean bean1 = new MenuBean();
			PreparedStatement ps = null;
			ResultSet rs=null;
			String query = "select * from ad_menu where ad_menu_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, Menu_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_menu_id(rs.getInt("ad_menu_id"));
					bean1.setName(rs.getString("name"));
					bean1.setLink(rs.getString("link"));
					bean1.setWindow_name(rs.getString("window_name"));
					bean1.setPurpose(rs.getString("purpose"));

				}
			}catch (Exception e) {
				System.out.print("MenuDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			
			return bean1;
		}
	//--------------------------------------------------------------------------------------
		public ArrayList<MenuBean> getAllMenu() {
			ArrayList<MenuBean> bean = new ArrayList<MenuBean>();
			PreparedStatement ps = null;
			ResultSet rs=null;
			String query = "select * from ad_menu ";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					MenuBean bean1 = new MenuBean();
					bean1.setAd_menu_id(rs.getInt("ad_menu_id"));
					bean1.setName(rs.getString("name"));
					bean1.setLink(rs.getString("link"));
					bean1.setWindow_name(rs.getString("window_name"));
					bean1.setPurpose(rs.getString("purpose"));

					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("MenuDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			
			return bean;
		}
	//----------------------------------------------------------------------------------------------
		public ArrayList<MenuBean> getAllMenu(ArrayList<SetAssignMenuBean> menulist) {
			ArrayList<MenuBean> bean = new ArrayList<MenuBean>();
			ResultSet rs=null;
			PreparedStatement ps=null;
			if(menulist!=null){
			for(SetAssignMenuBean menu:menulist)
			{
				String query = "select * from ad_menu where ad_menu_id=? ";
				Connection c=DBConnection.getConnection();
			try {
				   
					ps = c.prepareStatement(query);
					ps.setInt(1, menu.getAd_menu_id());
					
					rs = ps.executeQuery();
					while (rs.next()) {
						MenuBean bean1 = new MenuBean();
						bean1.setAd_menu_id(rs.getInt("ad_menu_id"));
						bean1.setName(rs.getString("name"));
						bean1.setLink(rs.getString("link"));
						bean1.setWindow_name(rs.getString("window_name"));
						bean1.setPurpose(rs.getString("purpose"));
	
						bean.add(bean1);
	
					}
				}catch (Exception e) {
					System.out.print("MenuDao:-error in try Block");
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
	//----------------------------------------------------------------------------------------------
		public void updateMenu(MenuBean bean){
			int i=0;
			PreparedStatement ps = null;
			try {

				String query = "UPDATE ad_menu" +
						"SET  name=?, link=?, window_name=?, purpose=? WHERE ad_menu_id=?";
				ps = con.prepareStatement(query);
				ps.setString(1, bean.getName());
				ps.setString(2, bean.getLink());
				ps.setString(3, bean.getWindow_name());
				ps.setString(4, bean.getPurpose());
				ps.setInt(5, bean.getAd_menu_id());
				
				//System.out.print(ps);
				i=ps.executeUpdate();
			
		}catch (Exception e) {
			System.out.print("MenuDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
		
	}
	//----------------------------------------------------------------------------------------------
		public void deleteMenu(MenuBean bean){
			int i=0;
			PreparedStatement ps = null;
			try{
				String query="DELETE FROM ad_menu WHERE ad_menu_id=?";
				ps = con.prepareStatement(query);
				ps.setInt(1, bean.getAd_menu_id());
				ps.executeUpdate();
			
				}catch (Exception e) {
				System.out.print("MenuDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			
			
		}
	}


