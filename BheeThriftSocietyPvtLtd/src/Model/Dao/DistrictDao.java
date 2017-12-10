package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.DistrictBean;
import Model.Bean.LocationMasterBean;

public class DistrictDao {
	private Connection con = null;

	public DistrictDao() {
			con = DBConnection.getConnection();
		}

	public int addDistrict(DistrictBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_district(" +
					"ad_state_id, created, createdby, updated, updatedby," +
					"isactive, district) VALUES (?, ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getState().getAd_state_id());
			ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(3, bean.getCreatedby());
			ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(5, bean.getUpdatedby());
			ps.setBoolean(6, true);
			ps.setString(7, bean.getDistrict());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("DistrictDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public DistrictBean getDistrictById(DistrictBean bean) {
		DistrictBean bean1 = new DistrictBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_district where ad_district_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_district_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_district_id(rs.getInt("ad_district_id"));
				bean1.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDistrict(rs.getString("district"));
				
				

			}
			}catch (Exception e) {
				System.out.print("DistrictDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//-----------------------------------------------------------------------------------------
	public ArrayList<DistrictBean> getAllDistrictByStateId(int ad_state_id) {
		ArrayList<DistrictBean> bean = new ArrayList<DistrictBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_district where ad_state_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_state_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				DistrictBean bean1 = new DistrictBean();
				bean1.setAd_district_id(rs.getInt("ad_district_id"));
				bean1.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDistrict(rs.getString("district"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("DistrictDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	//-----------------------------------------------------------------------------------------
		public ArrayList<DistrictBean> getAllDistrictNameByStateId(int ad_state_id) {
			ArrayList<DistrictBean> bean = new ArrayList<DistrictBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_district where ad_state_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_state_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					DistrictBean bean1 = new DistrictBean();
					bean1.setAd_district_id(rs.getInt("ad_district_id"));
					
					bean1.setDistrict(rs.getString("district"));
					
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("DistrictDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
//------------------------------------------------------------------------------------------	
	public DistrictBean getDistrictById(int ad_district_id) {
		DistrictBean bean1 = new DistrictBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_district where ad_district_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_district_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_district_id(rs.getInt("ad_district_id"));
				bean1.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDistrict(rs.getString("district"));
				
			}
		}catch (Exception e) {
			System.out.print("DistrictDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<DistrictBean> getAllDistrict() {
		ArrayList<DistrictBean> bean = new ArrayList<DistrictBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_district ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				DistrictBean bean1 = new DistrictBean();
				bean1.setAd_district_id(rs.getInt("ad_district_id"));
				bean1.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDistrict(rs.getString("district"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("DistrictDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	
	
	//--------------------------------------------------------------------------------------
		public ArrayList<LocationMasterBean> getAllDistrictLocations() {
			ArrayList<LocationMasterBean> bean = new ArrayList<LocationMasterBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from location_master ";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					LocationMasterBean bean1 = new LocationMasterBean();
					bean1.setAd_district_id(rs.getInt("ad_district_id"));
					bean1.setDistrict(rs.getString("district"));
					bean1.setAd_city_id(rs.getInt("ad_city_id"));
					bean1.setCity(rs.getString("city"));
					bean1.setAd_state_id(rs.getInt("ad_state_id"));
					bean1.setState(rs.getString("state"));
					
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("DistrictDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
//----------------------------------------------------------------------------------------------
	public int updateDistrict(DistrictBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_district  SET  ad_state_id=?, updated=?," +
					" updatedby=?, isactive=?, district=? WHERE ad_district_id=?";
			ps = con.prepareStatement(query);
			
			ps.setInt(1, bean.getState().getAd_state_id());
			ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(3, bean.getUpdatedby());
			ps.setBoolean(4, true);
			ps.setString(5, bean.getDistrict());
			ps.setInt(6, bean.getAd_district_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("DistrictDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public void deleteDistrict(DistrictBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_district WHERE ad_district_id=?";
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_district_id());
			ps.executeQuery();
		
			}catch (Exception e) {
			System.out.print("DistrictDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
}
