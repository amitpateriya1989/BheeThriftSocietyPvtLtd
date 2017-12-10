package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.StateBean;

public class StateDao {
	private Connection con = null;

	public StateDao() {
		con = DBConnection.getConnection();
			
	}

	public int addState(StateBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_state(" +
					"created, createdby, updated, updatedby, isactive,state)" +
					" VALUES ( ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, bean.isIsactive());
			ps.setString(6, bean.getState());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("StateDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
	  return record;
		
	}
//-----------------------------------------------------------------------------------------
	public StateBean getStateById(StateBean bean) {
		StateBean bean1 = new StateBean();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_state where ad_state_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_state_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_state_id(rs.getInt("ad_state_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setState(rs.getString("state"));
				
				

			}
			}catch (Exception e) {
				System.out.print("StateDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
		
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public StateBean getStateById(int ad_state_id) {
		StateBean bean1 = new StateBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_state where ad_state_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_state_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_state_id(rs.getInt("ad_state_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setState(rs.getString("state"));
				
			}
		}catch (Exception e) {
			System.out.print("StateDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
	//------------------------------------------------------------------------------------------	
		public StateBean getStateNameById(int ad_state_id) {
			StateBean bean1 = new StateBean();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select ad_state_id,state from ad_state where ad_state_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_state_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_state_id(rs.getInt("ad_state_id"));					
					bean1.setState(rs.getString("state"));
					
				}
			}catch (Exception e) {
				System.out.print("StateDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
//--------------------------------------------------------------------------------------
	public ArrayList<StateBean> getAllState() {
		ArrayList<StateBean> bean = new ArrayList<StateBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_state ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				StateBean bean1 = new StateBean();
				bean1.setAd_state_id(rs.getInt("ad_state_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setState(rs.getString("state"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("StateDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	//--------------------------------------------------------------------------------------
	public ArrayList<StateBean> getAllStateName() {
		ArrayList<StateBean> bean = new ArrayList<StateBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_state order by ad_state_id";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				StateBean bean1 = new StateBean();
				bean1.setAd_state_id(rs.getInt("ad_state_id"));
				
				bean1.setState(rs.getString("state"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("StateDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateState(StateBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_state  SET  updated=?," +
					" updatedby=?, isactive=?, state=? WHERE ad_state_id=?";
			ps = con.prepareStatement(query);
			
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getState());
			ps.setInt(5, bean.getAd_state_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("StateDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deleteState(StateBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_state WHERE ad_state_id=?";
			ps= con.prepareStatement(query);
			ps.setInt(1, bean.getAd_state_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("StateDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}//end delete function

}//end class