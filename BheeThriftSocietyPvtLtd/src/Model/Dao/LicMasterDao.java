package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Bean.LicMasterBean;



public class LicMasterDao {
	private Connection con = null;

	public LicMasterDao() {
		con = DBConnection.getConnection();
			
	}

	public int addLicMaster(LicMasterBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_lic_master( " +
					" ad_type_of_loan_id, created, createdby, updated, updatedby, isactive," +
					" lic_rate, applied_date, ad_loan_category_id,ad_loan_roi_id) " +
					" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setInt(1,bean.getAd_type_of_loan_id());
			ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(3, bean.getCreatedby());
			ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(5, bean.getUpdatedby());
			ps.setBoolean(6, bean.isIsactive());
			ps.setDouble(7, bean.getLic_rate());
			ps.setDate(8, new java.sql.Date(bean.getApplied_date().getTime()));
			ps.setInt(9,bean.getAd_loan_category_id());
			ps.setInt(10,bean.getAd_loan_roi_id());
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("LicMasterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
	  return record;
		
	}
//-----------------------------------------------------------------------------------------
	public LicMasterBean getLicMasterById(LicMasterBean bean) {
		LicMasterBean bean1 = new LicMasterBean();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_lic_master where ad_lic_master_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_lic_master_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_lic_master_id(rs.getInt("ad_lic_master_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setLic_rate(rs.getDouble("lic_rate"));
				bean1.setApplied_date(rs.getDate("applied_date"));
				bean1.setAd_type_of_loan_id(rs.getInt("ad_type_of_loan_id"));
				bean1.setAd_loan_category_id(rs.getInt("ad_loan_category_id"));
				bean1.setAd_loan_roi_id(rs.getInt("ad_loan_roi_id"));
				
				

			}
			}catch (Exception e) {
				System.out.print("LicMasterDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
		
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public LicMasterBean getLicMasterById(int ad_lic_master_id) {
		LicMasterBean bean1 = new LicMasterBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_lic_master where ad_lic_master_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_lic_master_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_lic_master_id(rs.getInt("ad_lic_master_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setLic_rate(rs.getDouble("lic_rate"));
				bean1.setApplied_date(rs.getDate("applied_date"));
				bean1.setAd_type_of_loan_id(rs.getInt("ad_type_of_loan_id"));
				bean1.setAd_loan_category_id(rs.getInt("ad_loan_category_id"));
				bean1.setAd_loan_roi_id(rs.getInt("ad_loan_roi_id"));
				
			}
		}catch (Exception e) {
			System.out.print("LicMasterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
	
//--------------------------------------------------------------------------------------
	public ArrayList<LicMasterBean> getAllLicMaster() {
		ArrayList<LicMasterBean> bean = new ArrayList<LicMasterBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_lic_master ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				LicMasterBean bean1 = new LicMasterBean();
				bean1.setAd_lic_master_id(rs.getInt("ad_lic_master_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setLic_rate(rs.getDouble("lic_rate"));
				bean1.setApplied_date(rs.getDate("applied_date"));
				bean1.setAd_type_of_loan_id(rs.getInt("ad_type_of_loan_id"));
				bean1.setAd_loan_category_id(rs.getInt("ad_loan_category_id"));
				bean1.setAd_loan_roi_id(rs.getInt("ad_loan_roi_id"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("LicMasterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	//--------------------------------------------------------------------------------------
	
//----------------------------------------------------------------------------------------------
	public int updateLicMaster(LicMasterBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_lic_master " +
					" SET  ad_type_of_loan_id=?, created=?, createdby=?, updated=?, " +
					" updatedby=?, isactive=?, lic_rate=?, applied_date=?, " +
					" ad_loan_category_id=?, ad_loan_roi_id=?  WHERE ad_lic_master_id=?";
			ps = con.prepareStatement(query);
			
			ps.setInt(1,bean.getAd_type_of_loan_id());
			ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(3, bean.getCreatedby());
			ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(5, bean.getUpdatedby());
			ps.setBoolean(6, bean.isIsactive());
			ps.setDouble(7, bean.getLic_rate());
			ps.setDate(8, new java.sql.Date(bean.getApplied_date().getTime()));
			ps.setInt(9,bean.getAd_loan_category_id());
			ps.setInt(10,bean.getAd_loan_roi_id());
			ps.setInt(11, bean.getAd_lic_master_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("LicMasterDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deleteLicMaster(LicMasterBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_lic_master WHERE ad_lic_master_id=?";
			ps= con.prepareStatement(query);
			ps.setInt(1, bean.getAd_lic_master_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("LicMasterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}//end delete function

}
