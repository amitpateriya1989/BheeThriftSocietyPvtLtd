package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.FdRoiBean;

public class FdRoiDao {
	private Connection con = null;

	public FdRoiDao() {
		con = DBConnection.getConnection();
		}

	public int addFdRoi(FdRoiBean bean) {
		int record=0;
		PreparedStatement ps=null;
		
		try {
			con = DBConnection.getConnection();
			String query = "INSERT INTO ad_fd_roi(created, createdby, updated, updatedby, ad_type_of_fd_id, " +
					"ad_fd_category_id, time_period, effective_form, effective_to, isactive, roi,compound_frequency)" +
					"    VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());			
			ps.setInt(5, bean.getAd_type_of_fd_id());
			ps.setInt(6, bean.getAd_fd_category_id());
			ps.setDouble(7, bean.getTime_period());
			ps.setDate(8, new java.sql.Date(bean.getEffective_form().getTime()));
			ps.setString(9, bean.getEffective_to());
			ps.setBoolean(10, true);
			ps.setDouble(11, bean.getRoi());
			ps.setDouble(12, bean.getCompound_frequency());
			
			
			int ad_fd_roi_id=new FdRoiDao().getFdRoiMaxId(bean.getAd_fd_category_id() , bean.getAd_type_of_fd_id(),bean.getTime_period());
			
			FdRoiBean bean1 = new FdRoiBean();
			bean1.setAd_fd_roi_id(ad_fd_roi_id);
			bean1.setEffective_to(bean.getEffective_form().toString());
			new FdRoiDao().updateFdRoi(bean1);			
			record=ps.executeUpdate();
			 
		} catch (Exception e) {
			System.out.print("FdRoiDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public FdRoiBean getFdRoiById(FdRoiBean bean) {
		FdRoiBean bean1 = new FdRoiBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT * FROM ad_fd_roi where ad_fd_roi_id=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_fd_roi_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setAd_fd_category_id(rs.getInt("ad_fd_category_id"));
				bean1.setAd_type_of_fd_id(rs.getInt("ad_type_of_fd_id"));
				bean1.setTime_period(rs.getInt("time_period"));
				bean1.setEffective_form(rs.getDate("effective_form"));
				bean1.setEffective_to(rs.getString("effective_to"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRoi(rs.getDouble("roi"));
				bean1.setCompound_frequency(rs.getDouble("compound_frequency"));

			}
			}catch (Exception e) {
				System.out.print("FdRoiDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public FdRoiBean getFdRoiById(int ad_fd_roi_id) {
		FdRoiBean bean1 = new FdRoiBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT * FROM ad_fd_roi where ad_fd_roi_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_fd_roi_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setAd_fd_category_id(rs.getInt("ad_fd_category_id"));
				bean1.setAd_type_of_fd_id(rs.getInt("ad_type_of_fd_id"));
				bean1.setTime_period(rs.getInt("time_period"));
				bean1.setEffective_form(rs.getDate("effective_form"));
				bean1.setEffective_to(rs.getString("effective_to"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRoi(rs.getDouble("roi"));
				bean1.setCompound_frequency(rs.getDouble("compound_frequency"));
				
			}
		}catch (Exception e) {
			System.out.print("FdRoiDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<FdRoiBean> getAllFdRoi() {
		ArrayList<FdRoiBean> bean = new ArrayList<FdRoiBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT * FROM ad_fd_roi";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				FdRoiBean bean1 = new FdRoiBean();
				bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setAd_fd_category_id(rs.getInt("ad_fd_category_id"));
				bean1.setAd_type_of_fd_id(rs.getInt("ad_type_of_fd_id"));
				bean1.setTime_period(rs.getInt("time_period"));
				bean1.setEffective_form(rs.getDate("effective_form"));
				bean1.setEffective_to(rs.getString("effective_to"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRoi(rs.getDouble("roi"));
				bean1.setCompound_frequency(rs.getDouble("compound_frequency"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("FdRoiDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------
		public ArrayList<FdRoiBean> getTypeCatFdRoi(FdRoiBean beanr) {
			ArrayList<FdRoiBean> bean = new ArrayList<FdRoiBean>();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "SELECT * FROM ad_fd_roi where ad_fd_category_id=? and ad_type_of_fd_id=? and isactive='TRUE' ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, beanr.getAd_fd_category_id());
				ps.setInt(2, beanr.getAd_type_of_fd_id());
				rs = ps.executeQuery();
				while (rs.next()) {
					FdRoiBean bean1 = new FdRoiBean();
					bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setAd_fd_category_id(rs.getInt("ad_fd_category_id"));
					bean1.setAd_type_of_fd_id(rs.getInt("ad_type_of_fd_id"));
					bean1.setTime_period(rs.getInt("time_period"));
					bean1.setEffective_form(rs.getDate("effective_form"));
					bean1.setEffective_to(rs.getString("effective_to"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setRoi(rs.getDouble("roi"));
					bean1.setCompound_frequency(rs.getDouble("compound_frequency"));
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("FdRoiDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
	//--------------------------------------------------------------------------------------
		public ArrayList<FdRoiBean> getAllFdRoiactive() {
			ArrayList<FdRoiBean> bean = new ArrayList<FdRoiBean>();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "SELECT * FROM ad_fd_roi where isactive='TRUE' ";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					FdRoiBean bean1 = new FdRoiBean();
					bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setAd_fd_category_id(rs.getInt("ad_fd_category_id"));
					bean1.setAd_type_of_fd_id(rs.getInt("ad_type_of_fd_id"));
					bean1.setTime_period(rs.getInt("time_period"));
					bean1.setEffective_form(rs.getDate("effective_form"));
					bean1.setEffective_to(rs.getString("effective_to"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setRoi(rs.getDouble("roi"));
					bean1.setCompound_frequency(rs.getDouble("compound_frequency"));
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("FdRoiDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
	//----------------------------------------------------------------------------------------------
	public int updateFdRoi(FdRoiBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_fd_roi   SET  updated=?, updatedby=?," +
					" ad_type_of_fd_id=?, ad_fd_category_id=?, time_period=?, effective_form=?," +
					" isactive=?, roi=?,  compound_frequency=? WHERE ad_fd_roi_id=?";
			ps = con.prepareStatement(query);
			
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setInt(3, bean.getAd_type_of_fd_id());
			ps.setInt(4, bean.getAd_fd_category_id());
			ps.setInt(5, bean.getTime_period());
			ps.setDate(6,new java.sql.Date(bean.getEffective_form().getTime()));
			ps.setBoolean(7, bean.isIsactive());
			ps.setDouble(8, bean.getRoi());
			ps.setDouble(9, bean.getCompound_frequency());
			ps.setInt(10, bean.getAd_fd_roi_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("FdRoiDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	
	
	public int getFdRoiMaxId(int ad_fd_category_id , int ad_type_od_fd_id, int time_period ) {
		FdRoiBean bean1 = new FdRoiBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT max(ad_fd_roi_id) as ad_fd_roi_id FROM ad_fd_roi where ad_fd_category_id=? AND ad_type_of_fd_id =? AND time_period=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_fd_category_id);
			ps.setInt(2, ad_type_od_fd_id);
			ps.setInt(3, time_period);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
				
				
			}
		}catch (Exception e) {
			System.out.print("FdRoiDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1.getAd_fd_roi_id();
	}
//---------------
	//----------------------------------------------------------------------------------------------
	
	
		public int getFdRoiMaxId(int ad_fd_category_id , int ad_type_od_fd_id ) {
			FdRoiBean bean1 = new FdRoiBean();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "SELECT max(ad_fd_roi_id) as ad_fd_roi_id FROM ad_fd_roi where ad_fd_category_id=? AND ad_type_of_fd_id =? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_fd_category_id);
				ps.setInt(2, ad_type_od_fd_id);
				
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
					
					
				}
			}catch (Exception e) {
				System.out.print("FdRoiDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1.getAd_fd_roi_id();
		}
	//---------------
		public int getFdRoiminId(int ad_fd_category_id , int ad_type_of_fd_id ) {
			FdRoiBean bean1 = new FdRoiBean();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "SELECT min(time_period) as time_period FROM ad_fd_roi where ad_fd_category_id=? AND ad_type_of_fd_id =? and isactive='TRUE' ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_fd_category_id);
				ps.setInt(2, ad_type_of_fd_id);
				
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setTime_period(rs.getInt("time_period"));
					
					
				}
			}catch (Exception e) {
				System.out.print("FdRoiDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1.getTime_period();
		}
	//---------------
		
		public double getFdRoiIntrestId(int ad_fd_category_id , int ad_type_of_fd_id ,int  monthdiff) {
			FdRoiBean bean1 = new FdRoiBean();
			PreparedStatement ps =null;
			ResultSet rs=null;
			double roi =0.0;
			String query = "SELECT time_period,roi FROM ad_fd_roi where ad_fd_category_id=? AND ad_type_of_fd_id =? and isactive='TRUE' and time_period<=? order by time_period asc ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_fd_category_id);
				ps.setInt(2, ad_type_of_fd_id);
				ps.setInt(3,monthdiff );
				
				rs = ps.executeQuery();

				while (rs.next()) {
					
					if(monthdiff>= rs.getInt("time_period")){
						roi=rs.getDouble("roi");
						//break;
					}

				}
			}catch (Exception e) {
				System.out.print("FdRoiDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return roi;
		}
	//---------------
		
}
