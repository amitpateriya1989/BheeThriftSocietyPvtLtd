package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.DividendMasterBean;

public class DividendMasterDao {
	
	private Connection con = null;
	
	public DividendMasterDao() {
		con = DBConnection.getConnection();
	}
	
	public int addDividendMaster(DividendMasterBean bean) {
		int record=0;
		int last_inserted_id = 0;
		
		PreparedStatement ps=null;
		PreparedStatement ps2=null;
		ResultSet rs =null;
		try {
		
			String query = "INSERT INTO ad_dividend_master(created, createdby, updated, updatedby, "
					+ " ad_convence_amt, ad_dividend_per, isactive, year_from, year_to,year)"
					+ "  VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?,?);" ;
					
			ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setDouble(5, bean.getAd_convence_amt());
			ps.setDouble(6, bean.getAd_dividend_per());
			ps.setBoolean(7, bean.isIsactive());
			ps.setDate(8, new java.sql.Date(bean.getYear_from().getTime()));
			ps.setDate(9, new java.sql.Date(bean.getYear_to().getTime()));
			ps.setString(10, bean.getYear());
			//System.out.println(ps);			
			record=ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
            if(rs.next())
            {
                last_inserted_id = rs.getInt(1);
            }
			
            if(last_inserted_id>0){
            	String query2 = "UPDATE ad_dividend_master SET  isactive=false"
    					+ " WHERE ad_divident_master_id <>?;";
    		    ps2 = con.prepareStatement(query2);
    		    ps2.setInt(1, last_inserted_id);
    		    ps2.executeUpdate();
            }
			
			
		} catch (Exception e) {
			System.out.print("DividendMasterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(ps2);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	
	public DividendMasterBean getDividendMasterBeanById(int ad_dividend_master_id) {
		DividendMasterBean bean1 = new DividendMasterBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "SELECT *  FROM ad_dividend_master where ad_divident_master_id = ? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_dividend_master_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_divident_master_id(rs.getInt("ad_divident_master_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setYear_from(rs.getDate("year_from"));
				bean1.setYear_to(rs.getDate("year_to"));
				bean1.setAd_convence_amt(rs.getDouble("ad_convence_amt"));
				bean1.setAd_dividend_per(rs.getDouble("ad_dividend_per"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setYear(rs.getString("year"));
			}
		}catch (Exception e) {
			System.out.print("DividendMasterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
	
	//-----------------------------------------------------------------------------------------
	
		public DividendMasterBean getDividendMasterBeanByYear(String year) {
			DividendMasterBean bean1 = new DividendMasterBean();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "SELECT *  FROM ad_dividend_master where year = ? ";
			try {
				ps = con.prepareStatement(query);
				ps.setString(1, year.trim());
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_divident_master_id(rs.getInt("ad_divident_master_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setYear_from(rs.getDate("year_from"));
					bean1.setYear_to(rs.getDate("year_to"));
					bean1.setAd_convence_amt(rs.getDouble("ad_convence_amt"));
					bean1.setAd_dividend_per(rs.getDouble("ad_dividend_per"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setYear(rs.getString("year"));
				}
			}catch (Exception e) {
				System.out.print("DividendMasterDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
//--------------------------------------------------------------------------------------
	
	public DividendMasterBean getDividendMasterBeanActive() {
		DividendMasterBean bean1 = new DividendMasterBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "SELECT * FROM ad_dividend_master where isactive = true limit 1";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_divident_master_id(rs.getInt("ad_divident_master_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setYear_from(rs.getDate("year_from"));
				bean1.setYear_to(rs.getDate("year_to"));
				bean1.setAd_convence_amt(rs.getDouble("ad_convence_amt"));
				bean1.setAd_dividend_per(rs.getDouble("ad_dividend_per"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setYear(rs.getString("year"));
			}
		}catch (Exception e) {
			System.out.print("DividendMasterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------

	public ArrayList<DividendMasterBean> getAllDividendMaster() {
		ArrayList<DividendMasterBean> bean = new ArrayList<DividendMasterBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_dividend_master ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				DividendMasterBean bean1 = new DividendMasterBean();
				bean1.setAd_divident_master_id(rs.getInt("ad_divident_master_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setYear_from(rs.getDate("year_from"));
				bean1.setYear_to(rs.getDate("year_to"));
				bean1.setAd_convence_amt(rs.getDouble("ad_convence_amt"));
				bean1.setAd_dividend_per(rs.getDouble("ad_dividend_per"));
				bean1.setYear(rs.getString("year"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("DividendMasterDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}	
	
//--------------------------------------------------------------------------------------
	
	public int updateDividendMaster(DividendMasterBean bean){
		int i=0;
		PreparedStatement ps=null;
		PreparedStatement ps2=null;
		try {

			String query = "UPDATE ad_dividend_master SET updated=?, updatedby=?, "
						+ " ad_convence_amt=?, ad_dividend_per=?, isactive=?, year_from = ?, year_to = ?, year=? "
						+ " WHERE ad_divident_master_id =?;";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setDouble(3, bean.getAd_convence_amt());
			ps.setDouble(4, bean.getAd_dividend_per());
			ps.setBoolean(5, bean.isIsactive());
			ps.setDate(6, new java.sql.Date(bean.getYear_from().getTime()));
			ps.setDate(7, new java.sql.Date(bean.getYear_to().getTime()));
			ps.setString(8, bean.getYear());
			ps.setInt(9, bean.getAd_divident_master_id());
			i=ps.executeUpdate();
			
			
		
	}catch (Exception e) {
		System.out.print("DividendMasterDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
	
//--------------------------------------------------------------------------------------	
	
}//end class