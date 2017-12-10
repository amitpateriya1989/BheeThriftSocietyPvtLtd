package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Model.Bean.LoanRoiBean;

public class LoanRoiDao {
	private Connection con = null;

	public LoanRoiDao() {
		con = DBConnection.getConnection();
		}

	public int addLoanRoi(LoanRoiBean bean) {
		int record=0;
		PreparedStatement ps=null;
		
		try {
			con = DBConnection.getConnection();
			String query = "INSERT INTO ad_loan_roi(created, createdby, updated, updatedby, ad_type_of_loan_id, " +
					"ad_loan_category_id, max_limit, effective_form, effective_to, isactive, roi, share_needed,min_period,max_period,min_salary)" +
					"    VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?);" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());			
			ps.setInt(5, bean.getAd_type_of_loan_id());
			ps.setInt(6, bean.getAd_loan_category_id());
			ps.setDouble(7, bean.getMax_limit());
			ps.setDate(8, new java.sql.Date(bean.getEffective_form().getTime()));
			ps.setString(9, bean.getEffective_to());
			ps.setBoolean(10, true);
			ps.setDouble(11, bean.getroi());
			ps.setDouble(12, bean.getShare_needed());
			ps.setInt(13, bean.getMin_period());
			ps.setInt(14, bean.getMax_period());
			ps.setDouble(15, bean.getMin_salary());
			//ps.setString(15, bean.)
			int ad_loan_roi_id=new LoanRoiDao().getLoanRoiMaxId(bean.getAd_loan_category_id() , bean.getAd_type_of_loan_id());
			
			LoanRoiBean bean1 = new LoanRoiBean();
			bean1.setAd_loan_roi_id(ad_loan_roi_id);
			bean1.setUpdatedby(bean.getUpdatedby());
			bean1.setEffective_to(new SimpleDateFormat("dd/MM/yyyy").format(bean.getEffective_form()));
			System.out.println(bean.getEffective_form().toString());
			bean1.setIsactive(false);
			new LoanRoiDao().updateLoanStatuRoi(bean1);			
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("LoanRoiDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
		
	}
//-----------------------------------------------------------------------------------------
	public LoanRoiBean getLoanRoiById(LoanRoiBean bean) {
		LoanRoiBean bean1 = new LoanRoiBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT ad_loan_roi_id, created, createdby, updated, updatedby, ad_type_of_loan_id,  ad_loan_category_id," +
				" max_limit, effective_form, effective_to,   isactive, roi FROM ad_loan_roi , min_period , max_period , min_salary where ad_loan_roi_id=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_loan_roi_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_loan_roi_id(rs.getInt("ad_loan_roi_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setAd_loan_category_id(rs.getInt("ad_loan_category_id"));
				bean1.setAd_type_of_loan_id(rs.getInt("ad_type_od_loan_id"));
				bean1.setMax_limit(rs.getDouble("max_limit"));
				bean1.setEffective_form(rs.getDate("effective_form"));
				bean1.setEffective_to(rs.getString("effective_to"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRoi(rs.getDouble("roi"));
				bean1.setMin_period(rs.getInt("min_period"));
				bean1.setMax_period(rs.getInt("max_period"));

			}
			}catch (Exception e) {
				System.out.print("LoanRoiDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public LoanRoiBean getLoanRoiById(int ad_bank_region_id) {
		LoanRoiBean bean1 = new LoanRoiBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT * FROM ad_loan_roi where ad_loan_roi_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_bank_region_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_loan_roi_id(rs.getInt("ad_loan_roi_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setAd_loan_category_id(rs.getInt("ad_loan_category_id"));
				bean1.setAd_type_of_loan_id(rs.getInt("ad_type_of_loan_id"));
				bean1.setMax_limit(rs.getDouble("max_limit"));
				bean1.setEffective_form(rs.getDate("effective_form"));
				bean1.setEffective_to(rs.getString("effective_to"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRoi(rs.getDouble("roi"));
				bean1.setShare_needed(rs.getDouble("share_needed"));
				bean1.setMin_period(rs.getInt("min_period"));
				bean1.setMax_period(rs.getInt("max_period"));
				bean1.setMin_salary(rs.getDouble("min_salary"));
			}
		}catch (Exception e) {
			System.out.print("LoanRoiDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<LoanRoiBean> getAllLoanRoi() {
		ArrayList<LoanRoiBean> bean = new ArrayList<LoanRoiBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT alr.*,alc.name as category ,atl.name as type FROM ad_loan_roi alr left join ad_loan_category alc on alc.ad_loan_category_id=alr.ad_loan_category_id" +
				" left join ad_type_of_loan atl on atl.ad_type_of_loan_id=alr.ad_type_of_loan_id order by alc.ad_loan_category_id ,atl.ad_type_of_loan_id ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				LoanRoiBean bean1 = new LoanRoiBean();
				bean1.setAd_loan_roi_id(rs.getInt("ad_loan_roi_id"));				
				bean1.setAd_loan_category_id(rs.getInt("ad_loan_category_id"));
				bean1.setAd_type_of_loan_id(rs.getInt("ad_type_of_loan_id"));
				bean1.setMax_limit(rs.getDouble("max_limit"));
				bean1.setEffective_form(rs.getDate("effective_form"));
				bean1.setEffective_to(rs.getString("effective_to"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRoi(rs.getDouble("roi"));
				bean1.setShare_needed(rs.getDouble("share_needed"));
				bean1.setCetegory(rs.getString("category"));
				bean1.setType(rs.getString("type"));
				bean1.setMin_period(rs.getInt("min_period"));
				bean1.setMax_period(rs.getInt("max_period"));
				bean1.setMin_salary(rs.getDouble("min_salary"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("LoanRoiDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	
	public int updateLoanRoi(LoanRoiBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_loan_roi "+
					"SET  updated=?, updatedby=?,  effective_to=? , "+
					" ad_type_of_loan_id=?, ad_loan_category_id=?, max_limit=?, effective_form=?, "+
					"isactive=?, roi=?, share_needed=?, min_period=?, "+
					"max_period=?, min_salary=? "+
					"WHERE ad_loan_roi_id=?;";
			ps = con.prepareStatement(query);
			
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setString(3,bean.getEffective_to());
			ps.setInt(4, bean.getAd_type_of_loan_id());
			ps.setInt(5, bean.getAd_loan_category_id());
			ps.setDouble(6, bean.getMax_limit());
			ps.setDate(7, new java.sql.Date(bean.getEffective_form().getTime()));
			ps.setBoolean(8, bean.isIsactive());
			ps.setDouble(9, bean.getroi());
			ps.setDouble(10, bean.getShare_needed());
			ps.setInt(11, bean.getMin_period());
			ps.setInt(12, bean.getMax_period());
			ps.setDouble(13, bean.getMin_salary());		
			ps.setInt(14, bean.getAd_loan_roi_id());
		    System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("LoanRoiDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	
	public int updateLoanStatuRoi(LoanRoiBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_loan_roi "+
					"SET  updated=?, updatedby=?,  effective_to=? ,isactive=? "+
					"WHERE ad_loan_roi_id=?;";
			ps = con.prepareStatement(query);
			
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setString(3,bean.getEffective_to());
			ps.setBoolean(4, bean.isIsactive());	
			ps.setInt(5, bean.getAd_loan_roi_id());
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("LoanRoiDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	
	
	public int getLoanRoiMaxId(int ad_loan_category_id , int ad_type_od_loan_id ) {
		LoanRoiBean bean1 = new LoanRoiBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT max(ad_loan_roi_id) as ad_loan_roi_id FROM ad_loan_roi where ad_loan_category_id=? AND ad_type_of_loan_id =? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_loan_category_id);
			ps.setInt(2, ad_type_od_loan_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_loan_roi_id(rs.getInt("ad_loan_roi_id"));
			}
		}catch (Exception e) {
			System.out.print("LoanRoiDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1.getAd_loan_roi_id();
	}
//---------------
	//----------------------------------------------------------------------------------------------
	
	
		public LoanRoiBean getLoanRoi(int ad_loan_category_id , int ad_type_od_loan_id ) {
			LoanRoiBean bean1 = new LoanRoiBean();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "SELECT * FROM ad_loan_roi where ad_loan_roi_id=(SELECT max(ad_loan_roi_id) as ad_loan_roi_id FROM ad_loan_roi where ad_loan_category_id=? AND ad_type_of_loan_id =? ) ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_loan_category_id);
				ps.setInt(2, ad_type_od_loan_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_loan_roi_id(rs.getInt("ad_loan_roi_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setAd_loan_category_id(rs.getInt("ad_loan_category_id"));
					bean1.setAd_type_of_loan_id(rs.getInt("ad_type_of_loan_id"));
					bean1.setMax_limit(rs.getDouble("max_limit"));
					bean1.setEffective_form(rs.getDate("effective_form"));
					bean1.setEffective_to(rs.getString("effective_to"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setRoi(rs.getDouble("roi"));
					bean1.setShare_needed(rs.getDouble("share_needed"));
					bean1.setMin_period(rs.getInt("min_period"));
					bean1.setMax_period(rs.getInt("max_period"));
					bean1.setMin_salary(rs.getDouble("min_salary"));
				}
			}catch (Exception e) {
				System.out.print("LoanRoiDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//---------------

}
