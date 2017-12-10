package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.CityBean;

public class CityDao {

	private Connection con = null;

	public CityDao() {
		con = DBConnection.getConnection();
	}

	public int addCity(CityBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_city(" +
					" ad_district_id, ad_state_id, created, createdby," +
					" updated, updatedby, isactive, city) VALUES (?, ?, ?, ?, ?,?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_district_id());
			ps.setInt(2, bean.getAd_state_id());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getCreatedby());
			ps.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(6, bean.getUpdatedby());
			ps.setBoolean(7, true);
			ps.setString(8, bean.getCity());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("CityDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
		return record;
	}
//-----------------------------------------------------------------------------------------
	public CityBean getCityById(CityBean bean) {
		CityBean bean1 = new CityBean();
		ResultSet rs=null;
		PreparedStatement ps=null;
		String query = "select * from ad_city where ad_city_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_city_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_state_id(rs.getInt("ad_state_id"));
				bean1.setAd_district_id(rs.getInt("ad_district_id"));
				bean1.setAd_city_id(rs.getInt("ad_city_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setCity(rs.getString("city"));
				
				

			}
			}catch (Exception e) {
				System.out.print("CityDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public CityBean getCityById(int ad_city_id) {
		CityBean bean1 = new CityBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_city where ad_city_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_city_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_state_id(rs.getInt("ad_state_id"));
				bean1.setAd_district_id(rs.getInt("ad_district_id"));
				bean1.setAd_city_id(rs.getInt("ad_city_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setCity(rs.getString("city"));
				
			}
		}catch (Exception e) {
			System.out.print("CityDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
		return bean1;
	}
	//------------------------------------------------------------------------------------------	
		public CityBean getCityNameById(int ad_city_id) {
			CityBean bean1 = new CityBean();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from ad_city where ad_city_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_city_id);
				rs = ps.executeQuery();
				while (rs.next()) {
				
					bean1.setAd_city_id(rs.getInt("ad_city_id"));
				
					bean1.setCity(rs.getString("city"));
					
				}
			}catch (Exception e) {
				System.out.print("CityDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			
			return bean1;
		}
//--------------------------------------------------------------------------------------
	public ArrayList<CityBean> getAllCity() {
		ArrayList<CityBean> bean = new ArrayList<CityBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_city ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				CityBean bean1 = new CityBean();
				bean1.setAd_state_id(rs.getInt("ad_state_id"));
				bean1.setAd_district_id(rs.getInt("ad_district_id"));
				bean1.setAd_city_id(rs.getInt("ad_city_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setCity(rs.getString("city"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("CityDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
		return bean;
	}
	
	
	//--------------------------------------------------------------------------------------
		public ArrayList<CityBean> getAllCityByDistrictId(int ad_district_id) {
			ArrayList<CityBean> bean = new ArrayList<CityBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_city where ad_district_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_district_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					CityBean bean1 = new CityBean();
					bean1.setAd_state_id(rs.getInt("ad_state_id"));
					bean1.setAd_district_id(rs.getInt("ad_district_id"));
					bean1.setAd_city_id(rs.getInt("ad_city_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("created"));
					bean1.setUpdatedby(rs.getInt("createdby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setCity(rs.getString("city"));
					
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("CityDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			
			return bean;
		}
		//--------------------------------------------------------------------------------------
				public ArrayList<CityBean> getAllCityNameByDistrictId(int ad_district_id) {
					ArrayList<CityBean> bean = new ArrayList<CityBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from ad_city where ad_district_id=? ";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_district_id);
						rs = ps.executeQuery();
						while (rs.next()) {
							CityBean bean1 = new CityBean();
							
							bean1.setAd_city_id(rs.getInt("ad_city_id"));
							
							bean1.setCity(rs.getString("city"));
							
							bean.add(bean1);

						}
					}catch (Exception e) {
						System.out.print("CityDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					
					return bean;
				}
//----------------------------------------------------------------------------------------------
	public int updateCity(CityBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_city " +
					"SET  ad_district_id=?, ad_state_id=?, updated=?, updatedby=?, " +
					"isactive=?, city=? WHERE ad_city_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_district_id());
			ps.setInt(2, bean.getAd_state_id());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getCity());
			ps.setInt(7, bean.getAd_city_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("CityDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
	
}
//----------------------------------------------------------------------------------------------
	public int deleteCity(CityBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_city WHERE ad_city_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_city_id());
			ps.executeQuery();
		
			}catch (Exception e) {
			System.out.print("CityDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
		
	}



}
