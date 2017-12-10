package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.SetAssignMenuBean;

public class SetAssignMenuDao {
	private Connection con = null;

	public SetAssignMenuDao() {
		con = DBConnection.getConnection();
			
	}

	public void addAssignMenu(SetAssignMenuBean bean) {
		int record=0;
		PreparedStatement ps=null;
		
		try {
			
			String query = "INSERT INTO set_assign_menu(" +
					"set_assign_menu_id, ad_menu_id, sequence_no, purpose,detail)" +
					"VALUES ( ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_role_id());
			ps.setInt(2, bean.getAd_menu_id());
			ps.setInt(3, bean.getSequence_no());
			ps.setString(4, bean.getPurpose());
			ps.setString(5, bean.getDetail());
			
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("SetAssignMenuDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
//-----------------------------------------------------------------------------------------
	public SetAssignMenuBean getAssignMenuById(SetAssignMenuBean bean) {
		SetAssignMenuBean bean1 = new SetAssignMenuBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from set_assign_menu where set_assign_menu_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getSet_assign_menu_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_menu_id(rs.getInt("ad_menu_id"));
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setSet_assign_menu_id(rs.getInt("set_assign_menu_id"));
				bean1.setSequence_no(rs.getInt("sequence_no"));
				bean1.setPurpose(rs.getString("purpose"));
				bean1.setDetail(rs.getString("detail"));
			  }
			}catch (Exception e) {
				System.out.print("SetAssignMenuDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public SetAssignMenuBean getAssignMenuById(int assignMenu_id) {
		SetAssignMenuBean bean1 = new SetAssignMenuBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from set_assign_menu where set_assign_menu_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, assignMenu_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_menu_id(rs.getInt("ad_menu_id"));
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setSet_assign_menu_id(rs.getInt("set_assign_menu_id"));
				bean1.setSequence_no(rs.getInt("sequence_no"));
				bean1.setPurpose(rs.getString("purpose"));
				bean1.setDetail(rs.getString("detail"));
				
				
			}
		}catch (Exception e) {
			System.out.print("SetAssignMenuDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<SetAssignMenuBean> getAllAssignMenu() {
		ArrayList<SetAssignMenuBean> bean = new ArrayList<SetAssignMenuBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from set_assign_menu ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				SetAssignMenuBean bean1 = new SetAssignMenuBean();
				bean1.setAd_menu_id(rs.getInt("ad_menu_id"));
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setSet_assign_menu_id(rs.getInt("set_assign_menu_id"));
				bean1.setSequence_no(rs.getInt("sequence_no"));
				bean1.setPurpose(rs.getString("purpose"));
				bean1.setDetail(rs.getString("detail"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("SetAssignMenuDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public void updateAssignMenu(SetAssignMenuBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE set_assign_menu" +
					"SET ad_role_id=?, ad_menu_id=?, sequence_no=?," +
					"purpose=?, detail=? WHERE set_assign_menu_id=? ";
			ps = con.prepareStatement(query);
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_role_id());
			ps.setInt(2, bean.getAd_menu_id());
			ps.setInt(3, bean.getSequence_no());
			ps.setString(4, bean.getPurpose());
			ps.setString(5, bean.getDetail());
			ps.setInt(6, bean.getSet_assign_menu_id());
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("SetAssignMenuDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public void deleteAssignMenu(SetAssignMenuBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM set_assign_menu WHERE set_assign_menu_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getSet_assign_menu_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("SetAssignMenuDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}

//-----------------------------------------------------------------------------------------------
	public ArrayList<SetAssignMenuBean> getAllAssignMenuByRoleId(int ad_role_id) {
		ArrayList<SetAssignMenuBean> bean = new ArrayList<SetAssignMenuBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from set_assign_menu where ad_role_id=? Order By  sequence_no";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_role_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				SetAssignMenuBean bean1 = new SetAssignMenuBean();
				bean1.setAd_menu_id(rs.getInt("ad_menu_id"));
				bean1.setAd_role_id(rs.getInt("ad_role_id"));
				bean1.setSet_assign_menu_id(rs.getInt("set_assign_menu_id"));
				bean1.setSequence_no(rs.getInt("sequence_no"));
				bean1.setPurpose(rs.getString("purpose"));
				bean1.setDetail(rs.getString("detail"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("SetAssignMenuDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}

}
