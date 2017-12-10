package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Bean.CalenderBean;

public class CalenderDao {
	private Connection con = null;

	public CalenderDao() {
		con = DBConnection.getConnection();
	}

	public int addCalender(CalenderBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_calender( " +
					" ad_list_item_id, created, createdby, updated, " +
					" updatedby, isactive, holiday_date, status, year) " +
					" VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)" ;
					
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_list_item_id());
			ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(3, bean.getCreatedby());
			ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(5, bean.getUpdatedby());
			ps.setBoolean(6, true);
			ps.setDate(7, new java.sql.Date(bean.getHoliday_date().getTime()));
			ps.setString(8, bean.getStatus());
			ps.setString(9, bean.getYear());	
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("CalenderDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public CalenderBean getCalenderById(CalenderBean bean) {
		CalenderBean bean1 = new CalenderBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_calender where ad_calender_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_calender_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_calender_id(rs.getInt("ad_calender_id"));
				bean1.setAd_list_item_id(rs.getInt("ad_list_item_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setStatus(rs.getString("status"));
				bean1.setHoliday_date(rs.getDate("holiday_date"));
				bean1.setYear(rs.getString("year"));
			}
			}catch (Exception e) {
				System.out.print("CalenderDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public CalenderBean getCalenderById(int ad_calender_id) {
		CalenderBean bean1 = new CalenderBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_calender where ad_calender_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_calender_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_calender_id(rs.getInt("ad_calender_id"));
				bean1.setAd_list_item_id(rs.getInt("ad_list_item_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setStatus(rs.getString("status"));
				bean1.setHoliday_date(rs.getDate("holiday_date"));
				bean1.setYear(rs.getString("year"));
			}
		}catch (Exception e) {
			System.out.print("CalenderDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<CalenderBean> getAllCalender() {
		ArrayList<CalenderBean> bean = new ArrayList<CalenderBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_calender order by holiday_date desc ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				CalenderBean bean1 = new CalenderBean();
				bean1.setAd_calender_id(rs.getInt("ad_calender_id"));
				bean1.setAd_list_item_id(rs.getInt("ad_list_item_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setStatus(rs.getString("status"));
				bean1.setHoliday_date(rs.getDate("holiday_date"));
				bean1.setYear(rs.getString("year"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("CalenderDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateCalender(CalenderBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_calender " +
					" SET  ad_list_item_id=?, updated=?, updatedby=?, isactive=?, " +
					" holiday_date=?, status=?, year=? WHERE ad_calender_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_list_item_id());
			ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(3, bean.getUpdatedby());
			ps.setBoolean(4, bean.isIsactive());
			ps.setDate(5, new java.sql.Date(bean.getHoliday_date().getTime()));
			ps.setString(6, bean.getStatus());
			ps.setString(7, bean.getYear());
			ps.setInt(8, bean.getAd_calender_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("CalenderDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deleteCalender(CalenderBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_calender WHERE ad_calender_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_calender_id());
			i=ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("CalenderDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}



}
