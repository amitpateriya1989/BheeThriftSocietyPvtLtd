package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.SetAssignSubMenuBean;

public class SetAssignSubMenuDao {
	private Connection con = null;

	public SetAssignSubMenuDao() {
		con = DBConnection.getConnection();
			
	}

	public void addAssignMenu(SetAssignSubMenuBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO set_assign_submenu(" +
					"ad_role_id, set_assign_menu_id, ad_sub_menu_id, sequence_no, detail)" +
					"VALUES (?, ?, ?, ?, ?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_role_id());
			ps.setInt(2, bean.getSet_assign_menu_id());
			ps.setInt(3, bean.getSet_assign_submenu_id());
			ps.setInt(4, bean.getSequence_no());
			ps.setString(5, bean.getDetail());
			
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("SetAssignSubMenuDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
//-----------------------------------------------------------------------------------------
	public SetAssignSubMenuBean getAssignSubMenuById(SetAssignSubMenuBean bean) {
		SetAssignSubMenuBean bean1 = new SetAssignSubMenuBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from set_assign_sub_menu where set_assign_sub_menu_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getSet_assign_submenu_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_sub_menu_id(rs.getInt("ad_sub_menu_id"));
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setSet_assign_submenu_id(rs.getInt("set_assign_sub_menu_id"));
				bean1.setSequence_no(rs.getInt("sequence_no"));
				bean1.setDetail(rs.getString("detail"));
				bean1.setSet_assign_menu_id(rs.getInt("set_assign_menu_id"));
				
			}
			}catch (Exception e) {
				System.out.print("SetAssignSubMenuDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

	
	//-----------------------------------------------------------------------------------------
		public ArrayList<SetAssignSubMenuBean> getAssignSubMenuByRoleId(int ad_role_id) {
			ArrayList<SetAssignSubMenuBean> bean = new ArrayList<SetAssignSubMenuBean>();
			PreparedStatement ps = null;
			ResultSet rs=null;
			String query = "select * from set_assign_submenu where ad_role_id=? Order By sequence_no ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_role_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					SetAssignSubMenuBean bean1=new SetAssignSubMenuBean();
					bean1.setAd_sub_menu_id(rs.getInt("ad_sub_menu_id"));
					bean1.setAd_role_id(rs.getInt("ad_role_id"));
					bean1.setSet_assign_submenu_id(rs.getInt("set_assign_submenu_id"));
					bean1.setSequence_no(rs.getInt("sequence_no"));
					bean1.setDetail(rs.getString("detail"));
					bean1.setSet_assign_menu_id(rs.getInt("set_assign_menu_id"));
					bean.add(bean1);
				}
				}catch (Exception e) {
					System.out.print("SetAssignSubMenuDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}
		
//------------------------------------------------------------------------------------------	
	public SetAssignSubMenuBean getAssignSubMenuById(int assignSubMenuid) {
		SetAssignSubMenuBean bean1 = new SetAssignSubMenuBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from set_assign_sub_menu where set_assign_sub_menu_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, assignSubMenuid);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_sub_menu_id(rs.getInt("ad_sub_menu_id"));
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setSet_assign_submenu_id(rs.getInt("set_assign_sub_menu_id"));
				bean1.setSequence_no(rs.getInt("sequence_no"));
				bean1.setDetail(rs.getString("detail"));
				bean1.setSet_assign_menu_id(rs.getInt("set_assign_menu_id"));
				
			}
		}catch (Exception e) {
			System.out.print("SetAssignSubMenuDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<SetAssignSubMenuBean> getAllAssignMenu() {
		ArrayList<SetAssignSubMenuBean> bean = new ArrayList<SetAssignSubMenuBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from set_assign_sub_menu ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				SetAssignSubMenuBean bean1 = new SetAssignSubMenuBean();
				bean1.setAd_sub_menu_id(rs.getInt("ad_sub_menu_id"));
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setSet_assign_submenu_id(rs.getInt("set_assign_sub_menu_id"));
				bean1.setSequence_no(rs.getInt("sequence_no"));
				bean1.setDetail(rs.getString("detail"));
				bean1.setSet_assign_menu_id(rs.getInt("set_assign_menu_id"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("SetAssignSubMenuDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public void updateAssignMenu(SetAssignSubMenuBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE set_assign_submenu" +
					"SET  ad_role_id=?, set_assign_menu_id=?," +
					"ad_sub_menu_id=?, sequence_no=?, detail=?" +
					"WHERE set_assign_submenu_id=?";
			ps = con.prepareStatement(query);
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_role_id());
			ps.setInt(2, bean.getSet_assign_menu_id());
			ps.setInt(3, bean.getSet_assign_submenu_id());
			ps.setInt(4, bean.getSequence_no());
			ps.setString(5, bean.getDetail());
			ps.setInt(6, bean.getSet_assign_submenu_id());
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("SetAssignSubMenuDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public void deleteAssignMenu(SetAssignSubMenuBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM set_assign_sub_menu WHERE set_assign_sub_menu_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getSet_assign_menu_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("SetAssignSubMenuDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}


}
