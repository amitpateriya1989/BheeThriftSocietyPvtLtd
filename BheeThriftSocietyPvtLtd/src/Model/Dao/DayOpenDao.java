package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Model.Bean.DayOpenBean;

public class DayOpenDao {
	
	private Connection con = null;

	public DayOpenDao() {
		con = DBConnection.getConnection();
	}

	public void addDayOpen(DayOpenBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			 
			String query = "INSERT INTO ad_open_days( open_days, opening_status, closing_status, remark, created, createdby, updated, updatedby, isactive)" +
					"VALUES (?, ?, ?, ?, ?, ?,?, ?,?)";
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(bean.getOpen_days().getTime()));
			ps.setBoolean(2, bean.getOpening_status());
			ps.setBoolean(3, bean.getClosing_status());
			ps.setString(4, bean.getRemark());
			ps.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(6, bean.getCreatedby());
			ps.setDate(7, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(8, bean.getUpdatedby());
			ps.setBoolean(9, true);
			
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("DayOpenDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
//-----------------------------------------------------------------------------------------
	public DayOpenBean getDayOpenById(DayOpenBean bean) {
		DayOpenBean bean1 = new DayOpenBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_open_days where ad_open_days_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_open_days_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_open_days_id(rs.getInt("ad_open_days_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setOpen_days(rs.getDate("open_days"));
				bean1.setOpening_status(rs.getBoolean("opening_status"));
				bean1.setClosing_status(rs.getBoolean("closing_status"));
				bean1.setRemark(rs.getString("remark"));
			}
			}catch (Exception e) {
				System.out.print("DayOpenDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//-----------------------------------------------------------------------------------------
		public DayOpenBean getLastDayOpen() {
			DayOpenBean bean1 = new DayOpenBean();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select Max(open_days+1) as open_days from ad_open_days  ";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					
					bean1.setOpen_days(rs.getDate("open_days"));
					
				}
				}catch (Exception e) {
					System.out.print("DayOpenDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(ps);
					DBConnection.close(rs);
					DBConnection.close(con);
			    }
				return bean1;
			}
//------------------------------------------------------------------------------------------	
	public DayOpenBean getDayOpenById(int ad_open_days_id) {
		DayOpenBean bean1 = new DayOpenBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_open_days where ad_open_days_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_open_days_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_open_days_id(rs.getInt("ad_open_days_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setOpen_days(rs.getDate("open_days"));
				bean1.setOpening_status(rs.getBoolean("opening_status"));
				bean1.setClosing_status(rs.getBoolean("closing_status"));
				bean1.setRemark(rs.getString("remark"));
				
			}
		}catch (Exception e) {
			System.out.print("DayOpenDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(ps);
			DBConnection.close(rs);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public DayOpenBean chkalreadyDayopenclose(Date date) {
		DayOpenBean bean1 = new DayOpenBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_open_days where open_days=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(date.getTime()));
			
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_open_days_id(rs.getInt("ad_open_days_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setOpen_days(rs.getDate("open_days"));
				bean1.setOpening_status(rs.getBoolean("opening_status"));
				bean1.setClosing_status(rs.getBoolean("closing_status"));
				bean1.setRemark(rs.getString("remark"));
				
			}
		}catch (Exception e) {
			System.out.print("DayOpenDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(ps);
			DBConnection.close(rs);
			DBConnection.close(con);
	    }
		return bean1;
	}
	//------------------------------------------------------------------------------------------	
		public DayOpenBean chkDayOpen() {
			DayOpenBean bean1 = new DayOpenBean();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_open_days where isactive=?  and  opening_status=? and closing_status=?";
			try {
				ps = con.prepareStatement(query);
				ps.setBoolean(1, true);
				ps.setBoolean(2, true);
				ps.setBoolean(3, false);
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_open_days_id(rs.getInt("ad_open_days_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setOpen_days(rs.getDate("open_days"));
					bean1.setOpening_status(rs.getBoolean("opening_status"));
					bean1.setClosing_status(rs.getBoolean("closing_status"));
					bean1.setRemark(rs.getString("remark"));
					
				}
			}catch (Exception e) {
				System.out.print("DayOpenDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//--------------------------------------------------------------------------------------
		public DayOpenBean chkDayClosed(Date date) {
			DayOpenBean bean1 = new DayOpenBean();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_open_days where isactive=?  and  opening_status=? and closing_status=? and open_days=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setBoolean(1, false);
				ps.setBoolean(2, true);
				ps.setBoolean(3, true);
				ps.setDate(4,new java.sql.Date(date.getTime()) );
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_open_days_id(rs.getInt("ad_open_days_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setOpen_days(rs.getDate("open_days"));
					bean1.setOpening_status(rs.getBoolean("opening_status"));
					bean1.setClosing_status(rs.getBoolean("closing_status"));
					bean1.setRemark(rs.getString("remark"));
					
				}
			}catch (Exception e) {
				System.out.print("DayOpenDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//--------------------------------------------------------------------------------------
		public DayOpenBean getlastclosedday() {
			DayOpenBean bean1 = new DayOpenBean();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select max(open_days) as open_days  from ad_open_days where isactive=?  and  opening_status=? and closing_status=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setBoolean(1, false);
				ps.setBoolean(2, true);
				ps.setBoolean(3, true);
				
				rs = ps.executeQuery();
				while (rs.next()) {
					
				
					bean1.setOpen_days(rs.getDate("open_days"));
					
				}
			}catch (Exception e) {
				System.out.print("DayOpenDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//--------------------------------------------------------------------------------------
	public ArrayList<DayOpenBean> getAllDayOpen() {
		ArrayList<DayOpenBean> bean = new ArrayList<DayOpenBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_open_days order by open_days desc ";
		try {
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				DayOpenBean bean1 = new DayOpenBean();
						
				bean1.setAd_open_days_id(rs.getInt("ad_open_days_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setOpen_days(rs.getDate("open_days"));
				bean1.setOpening_status(rs.getBoolean("opening_status"));
				bean1.setClosing_status(rs.getBoolean("closing_status"));
				bean1.setRemark(rs.getString("remark"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("DayOpenDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(ps);
			DBConnection.close(rs);
			DBConnection.close(con);
	    }
		return bean;
	}
	//--------------------------------------------------------------------------------------
	public ArrayList<DayOpenBean> getAllDayOpen(Date fdate, Date tdate) {
		ArrayList<DayOpenBean> bean = new ArrayList<DayOpenBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_open_days where open_days between ? and ? order by open_days";
		try {
			ps = con.prepareStatement(query);
			ps.setDate(1,  new java.sql.Date(fdate.getTime()));
			ps.setDate(2,new java.sql.Date(tdate.getTime()));
			rs = ps.executeQuery();
			while (rs.next()) {
				
				DayOpenBean bean1 = new DayOpenBean();
						
				bean1.setAd_open_days_id(rs.getInt("ad_open_days_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setOpen_days(rs.getDate("open_days"));
				bean1.setOpening_status(rs.getBoolean("opening_status"));
				bean1.setClosing_status(rs.getBoolean("closing_status"));
				bean1.setRemark(rs.getString("remark"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("DayOpenDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(ps);
			DBConnection.close(rs);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public void updateDayOpen(DayOpenBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_open_days " +
					"SET  updated=?, updatedby=?, " +
					"isactive=?, closing_status=? WHERE open_days=? AND opening_status=? AND isactive=? AND closing_status=? ";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, false);
			ps.setBoolean(4, true);
			
			ps.setDate(5, new java.sql.Date(bean.getOpen_days().getTime()));
			
			ps.setBoolean(6, true);
			ps.setBoolean(7, true);
			ps.setBoolean(8, false);
			
			
			
			System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("DayOpenDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
	
	
	
	
	
	
	
	
	//----------------------------------------------------------------------------------------------
		public int ChangeDayOpen(DayOpenBean bean){
			int i=0;
			PreparedStatement ps=null;
			try {

				String query = "UPDATE ad_open_days " +
						"SET  updated=?, updatedby=?, " +
						"open_days=? WHERE ad_open_days_id=? ";
				ps = con.prepareStatement(query);
				ps.setDate(1, new java.sql.Date(bean.getUpdated().getTime()));
				ps.setInt(2, bean.getUpdatedby());
				ps.setDate(3,new java.sql.Date(bean.getOpen_days().getTime()) );
				ps.setInt(4, bean.getAd_open_days_id());
				
				
				
				
				//System.out.print(ps);
				i=ps.executeUpdate();
			
		}catch (Exception e) {
			System.out.print("DayOpenDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}
//----------------------------------------------------------------------------------------------
	public void deleteDayOpen(DayOpenBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_open_days WHERE ad_open_days_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_open_days_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("DayOpenDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}


}
