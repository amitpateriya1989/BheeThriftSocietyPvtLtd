package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.CompulsationBean;

public class CompulsationDao {
	private Connection con = null;

	public CompulsationDao() {
		try{
			con = DBConnection.getConnection();
			
			}catch(Exception s){
				System.out.println("CompulsationDao:-Exception in Creating connection and auto commit off");
				s.printStackTrace();
			}
		
	}

	public int addCompulsation(CompulsationBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_compensation_amt( updated, updatedby, created, createdby, isactive, " +				
					" amt, reson,effective_from_date)" +
					"  VALUES ( ?, ?, ?, ?, ?, ?, ?,?)" ;					
			ps = con.prepareStatement(query);
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getCreatedby());
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());			
			ps.setBoolean(5, bean.isIsactive());
			ps.setDouble(6, bean.getAmt());
			ps.setString(7, bean.getReson());
			ps.setDate(8, new java.sql.Date(bean.getEffective_from_date().getTime()));
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("CompulsationDao:-error in try Block");
			e.printStackTrace();
			if(record==0){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("CompulsationDao:-error in rollback");
					s.printStackTrace();
				}
				
			}
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public CompulsationBean getCompulsationById(CompulsationBean bean) {
		CompulsationBean bean1 = new CompulsationBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_compensation_amt where ad_compensation_amt_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_compensation_amt_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_compensation_amt_id(rs.getInt("ad_compensation_amt_id"));				
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAmt(rs.getDouble("amt"));
				bean1.setReson(rs.getString("reson"));
			
			}
			}catch (Exception e) {
				System.out.print("CompulsationDao:-error in try Block");
				e.printStackTrace();
				if(rs!=null){
					try{
						con.rollback();
					}catch(SQLException s){
						System.out.print("CompulsationDao:-error in rollback");
						s.printStackTrace();
					}
					
				}
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------
	
	public CompulsationBean getCompulsationById(int ad_compensation_amt_id) {
		CompulsationBean bean1 = new CompulsationBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_compensation_amt where ad_compensation_amt_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_compensation_amt_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_compensation_amt_id(rs.getInt("ad_compensation_amt_id"));				
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAmt(rs.getDouble("amt"));
				bean1.setReson(rs.getString("reson"));
				bean1.setEffective_from_date(rs.getDate("effective_from_date"));

			}
		}catch (Exception e) {
			System.out.print("CompulsationDao:-error in try Block");
			e.printStackTrace();
			if(rs!=null){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("CompulsationDao:-error in rollback");
					s.printStackTrace();
				}
				
			}
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public double getCompulsationAmt(String reson) {
		double result=0.0;
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select amt from ad_compensation_amt where LOWER(reson)=LOWER(?) and isactive='TRUE' ";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, reson);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				result=rs.getDouble("amt");
				
			
			
			}
		}catch (Exception e) {
			System.out.print("CompulsationDao:-error in try Block");
			e.printStackTrace();
			if(rs!=null){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("CompulsationDao:-error in rollback");
					s.printStackTrace();
				}
				
			}
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return result;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<CompulsationBean> getAllCompulsation() {
		ArrayList<CompulsationBean> bean = new ArrayList<CompulsationBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_compensation_amt ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				CompulsationBean bean1 = new CompulsationBean();
				bean1.setAd_compensation_amt_id(rs.getInt("ad_compensation_amt_id"));				
				bean1.setAmt(rs.getDouble("amt"));
				bean1.setReson(rs.getString("reson"));
				bean1.setEffective_from_date(rs.getDate("effective_from_date"));
				bean1.setEffective_to_date(rs.getDate("effective_to_date"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("CompulsationDao:-error in try Block");
			e.printStackTrace();
			if(rs!=null){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("CompulsationDao:-error in rollback");
					s.printStackTrace();
				}
				
			}
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	
	
	//--------------------------------------------------------------------------------------


	public int updateCompulsationStatus(CompulsationBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_compensation_amt " +
					" SET  updated=?, updatedby=?," +
					" isactive=?,effective_to_date=? WHERE reson=? And isactive='TRUE'";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setDate(4, new java.sql.Date(bean.getEffective_from_date().getTime()));
			ps.setString(5, bean.getReson());
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("CompulsationDao:-error in try Block");
		e.printStackTrace();
	}finally {
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
 }//end updateCompulsationStatus function
	
//----------------------------------------------------------------------------------------------
	public int deleteCompulsation(CompulsationBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_compensation_amt WHERE ad_compensation_amt_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_compensation_amt_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("CompulsationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}
//-------------------------------------------------------------------------------------------
	public int updateCompulsation(CompulsationBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "UPDATE ad_compensation_amt " +
					" SET   updated=?,updatedby=?, isactive=?, amt=?, reson=? " +
					"  WHERE ad_compensation_amt_id=?" ;					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());			
			ps.setBoolean(3, bean.isIsactive());
			ps.setDouble(4, bean.getAmt());
			ps.setString(5, bean.getReson());
			ps.setInt(6, bean.getAd_compensation_amt_id());
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("CompulsationDao:-error in try Block");
			e.printStackTrace();
			if(record==0){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("CompulsationDao:-error in rollback");
					s.printStackTrace();
				}
				
			}
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
}
