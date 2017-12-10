package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Bean.CountryBean;

public class CountryDao {
	
	private Connection con = null;

	public CountryDao() {
		con = DBConnection.getConnection();
	}

	public int addCountry(CountryBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_country(" +
					"created, createdby," +
					" updated, updatedby, isactive, country) VALUES ( ?, ?, ?,?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, bean.isIsactive());
			ps.setString(6, bean.getCountry());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("CountryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public CountryBean getCountryById(CountryBean bean) {
		CountryBean bean1 = new CountryBean();
		ResultSet rs=null;
		PreparedStatement  ps =null;
		String query = "select * from ad_country where ad_country_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_country_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_country_id(rs.getInt("ad_country_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setCountry(rs.getString("country"));

			}
			}catch (Exception e) {
				System.out.print("CountryDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public CountryBean getCountryById(int ad_country_id) {
		CountryBean bean1 = new CountryBean();
		ResultSet rs=null;
		String query = "select * from ad_country where ad_country_id=? ";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_country_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_country_id(rs.getInt("ad_country_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setCountry(rs.getString("country"));
				
			}
		}catch (Exception e) {
			System.out.print("CountryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<CountryBean> getAllCountry() {
		ArrayList<CountryBean> bean = new ArrayList<CountryBean>();
		ResultSet rs=null;
		PreparedStatement ps = null;
		String query = "select * from ad_country ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				CountryBean bean1 = new CountryBean();
						
				bean1.setAd_country_id(rs.getInt("ad_country_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setCountry(rs.getString("country"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("CountryDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	//--------------------------------------------------------------------------------------
		public ArrayList<CountryBean> getAllCountryName() {
			ArrayList<CountryBean> bean = new ArrayList<CountryBean>();
			ResultSet rs=null;
			PreparedStatement ps = null;
			String query = "select * from ad_country order by ad_country_id ";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					CountryBean bean1 = new CountryBean();
							
					bean1.setAd_country_id(rs.getInt("ad_country_id"));
					
					bean1.setCountry(rs.getString("country"));
					
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("CountryDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(ps);
				DBConnection.close(rs);
				DBConnection.close(con);
		    }
			return bean;
		}
//----------------------------------------------------------------------------------------------
	public int updateCountry(CountryBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_country " +
					"SET  updated=?, updatedby=?, " +
					"isactive=?, country=? WHERE ad_country_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getCountry());
			ps.setInt(5, bean.getAd_country_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("CountryDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		DBConnection.close(ps);
		DBConnection.close(con);
    }
		return i;
	
}
//----------------------------------------------------------------------------------------------
	public int deleteCountry(CountryBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_country WHERE ad_country_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_country_id());
			i=ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("CountryDao:-error in try Block");
			e.printStackTrace();
			
		   }finally {
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
		return i;
	}


}
