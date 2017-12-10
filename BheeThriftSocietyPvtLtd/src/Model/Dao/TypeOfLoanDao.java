package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.TypeOfLoanBean;

public class TypeOfLoanDao {
	private Connection con = null;

	public TypeOfLoanDao() {
		con = DBConnection.getConnection();
			
	}

	public void addTypeOfLoan(TypeOfLoanBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_type_of_loan(" +
					"created, createdby, updated, updatedby, isactive,name)" +
					" VALUES ( ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getName());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("TypeOfLoanDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
//-----------------------------------------------------------------------------------------
	public TypeOfLoanBean gettypeofloanById(TypeOfLoanBean bean) {
		TypeOfLoanBean bean1 = new TypeOfLoanBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_type_of_loan where ad_type_of_loan_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_type_of_loan_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_type_of_loan_id(rs.getInt("ad_typeofloan_id"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
			}
			}catch (Exception e) {
				System.out.print("TypeOfLoanDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public TypeOfLoanBean gettypeofloanById(int ad_typeofloan_id) {
		TypeOfLoanBean bean1 = new TypeOfLoanBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_type_of_loan where ad_type_of_loan_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_typeofloan_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_type_of_loan_id(rs.getInt("ad_type_of_loan_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
			}
		}catch (Exception e) {
			System.out.print("TypeOfLoanDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<TypeOfLoanBean> getAlltypeofloan() {
		ArrayList<TypeOfLoanBean> bean = new ArrayList<TypeOfLoanBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_type_of_loan ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				TypeOfLoanBean bean1 = new TypeOfLoanBean();
				bean1.setAd_type_of_loan_id(rs.getInt("ad_type_of_loan_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setName(rs.getString("name"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("TypeOfLoanDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updatetypeofloan(TypeOfLoanBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_type_of_loan  SET  updated=?," +
					" updatedby=?, isactive=?, name=? WHERE ad_type_of_loan_id=?";
			ps = con.prepareStatement(query);		
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getName());
			ps.setInt(5, bean.getAd_type_of_loan_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("TypeOfLoanDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public void deletetypeofloan(TypeOfLoanBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_type_of_loan WHERE ad_type_of_loan_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_type_of_loan_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("TypeOfLoanDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}


}
