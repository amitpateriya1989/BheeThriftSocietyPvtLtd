package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.SalutationBean;

public class SalutationDao {
	private Connection con = null;

	public SalutationDao() {
		con = DBConnection.getConnection();
			
	}

	public int addSalutation(SalutationBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_salutation(" +
					"created, createdby, updated, updatedby, isactive,name,ad_gender_id)" +
					" VALUES ( ?, ?, ?, ?, ?, ?,?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getName());
			ps.setInt(7,bean.getAd_gender_id());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("SalutationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
		return record;
		
	}
//-----------------------------------------------------------------------------------------
	public SalutationBean getSalutationById(SalutationBean bean) {
		SalutationBean bean1 = new SalutationBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_salutation where ad_salutation_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_salutation_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_salutation_id(rs.getInt("ad_salutation_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				bean1.setAd_gender_id(rs.getInt("ad_gender_id"));
				

			}
			}catch (Exception e) {
				System.out.print("SalutationDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public SalutationBean getSalutationById(int ad_salutation_id) {
		SalutationBean bean1 = new SalutationBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_salutation where ad_salutation_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_salutation_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_salutation_id(rs.getInt("ad_salutation_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				bean1.setAd_gender_id(rs.getInt("ad_gender_id"));

			}
			//System.out.println(ps);
		}catch (Exception e) {
			System.out.print("SalutationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<SalutationBean> getAllSalutation() {
		ArrayList<SalutationBean> bean = new ArrayList<SalutationBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select ads.*, ag.gender from ad_salutation ads " +
				" left join ad_gender ag on ag.ad_gender_id=ads.ad_gender_id " +
				" order by ads.ad_gender_id ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				SalutationBean bean1 = new SalutationBean();
				bean1.setAd_salutation_id(rs.getInt("ad_salutation_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				bean1.setAd_gender_id(rs.getInt("ad_gender_id"));
				bean1.setGender(rs.getString("gender"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("SalutationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public ArrayList<SalutationBean> getSalutationByGenderId(int ad_gender_id) {
		
		ArrayList<SalutationBean> bean3 = new ArrayList<SalutationBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select ad_salutation_id, name from ad_salutation where ad_gender_id =? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_gender_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				SalutationBean bean1 = new SalutationBean();
				bean1.setAd_salutation_id(rs.getInt("ad_salutation_id"));
				bean1.setName(rs.getString("name"));
				bean3.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("SalutationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean3;
	}
//----------------------------------------------------------------------------------------------
	public int updateSalutation(SalutationBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_salutation  SET  updated=?," +
					" updatedby=?, isactive=?, name=? , ad_gender_id=? WHERE ad_salutation_id=?";
			ps = con.prepareStatement(query);
			
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getName());
			ps.setInt(5,bean.getAd_gender_id());
			ps.setInt(6, bean.getAd_salutation_id());
			i = ps.executeUpdate();
			
	}catch (Exception e) {
		System.out.print("SalutationDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
		return i;
	
}
//----------------------------------------------------------------------------------------------
	public int deleteSalutation(SalutationBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_salutation WHERE ad_salutation_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_salutation_id());
			i = ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("SalutationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
		return i;
		
	}

	
}
