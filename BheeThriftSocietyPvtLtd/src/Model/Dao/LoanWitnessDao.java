package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.LoanwitnessBean;

public class LoanWitnessDao {

	private Connection con = null;

	public LoanWitnessDao() {
		con = DBConnection.getConnection();
	}

	public int addLoanWitness(LoanwitnessBean bean) {
		int record=0;
		int wid=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_loan_witness("
					+ "             name, memno, pfno, mobile, address, created, "
					+ "         createdby, updated, updatedby, isactive)    "
					+ "VALUES ( ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query,ps.RETURN_GENERATED_KEYS);
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getMemno());
			ps.setString(3, bean.getPfno());
			ps.setString(4, bean.getMobile());
			ps.setString(5, bean.getAddress());			
			ps.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(7, bean.getCreatedby());
			ps.setDate(8, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(9, bean.getUpdatedby());
			ps.setBoolean(10, true);
			
				
			record=ps.executeUpdate();
			ResultSet generatedKeys = null;
			try{
				 generatedKeys = ps.getGeneratedKeys(); 
		            if (generatedKeys.next()) {
		            	wid=  generatedKeys.getInt(1);
		     //     System.out.println()
		            }
		            else {
		                throw new SQLException("Creating voucher failed, no ID obtained.");
		            }
		        }catch(Exception e){
		        	e.printStackTrace();
		        	
		        }finally {
					
					DBConnection.close(generatedKeys);
					
			    }
			
		} catch (Exception e) {
			System.out.print("LoanWitnessDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
		return wid;
	}
//-----------------------------------------------------------------------------------------
	public LoanwitnessBean getLoanWitnessById(LoanwitnessBean bean) {
		LoanwitnessBean bean1 = new LoanwitnessBean();
		ResultSet rs=null;
		PreparedStatement ps=null;
		String query = "select * from ad_loan_witness where ad_loan_witness_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_loan_witness_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				
				
				

			}
			}catch (Exception e) {
				System.out.print("LoanWitnessDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public LoanwitnessBean getLoanWitnessById(int ad_loan_witness_id) {
		LoanwitnessBean bean1 = new LoanwitnessBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_loan_witness where ad_loan_witness_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_loan_witness_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_loan_witness_id(rs.getInt("ad_loan_witness_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
			    bean1.setName(rs.getString("name"));
			    bean1.setPfno(rs.getString("pfno"));
			    bean1.setMemno(rs.getString("memno"));
			    bean1.setMobile(rs.getString("mobile"));
			    bean1.setAddress(rs.getString("address"));
				
			}
		}catch (Exception e) {
			System.out.print("LoanWitnessDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
		return bean1;
	}
	//------------------------------------------------------------------------------------------	
		public LoanwitnessBean getLoanWitnessNameById(int ad_loan_witness_id) {
			LoanwitnessBean bean1 = new LoanwitnessBean();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from ad_loan_witness where ad_loan_witness_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_loan_witness_id);
				rs = ps.executeQuery();
				while (rs.next()) {
				
					bean1.setAd_loan_witness_id(rs.getInt("ad_loan_witness_id"));
				}
			}catch (Exception e) {
				System.out.print("LoanWitnessDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			
			return bean1;
		}
//--------------------------------------------------------------------------------------
	public ArrayList<LoanwitnessBean> getAllLoanWitness() {
		ArrayList<LoanwitnessBean> bean = new ArrayList<LoanwitnessBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_loan_witness ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				LoanwitnessBean bean1 = new LoanwitnessBean();
				
				bean1.setAd_loan_witness_id(rs.getInt("ad_loan_witness_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("LoanWitnessDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
		return bean;
	}
	
	
	//--------------------------------------------------------------------------------------
		public ArrayList<LoanwitnessBean> getAllLoanWitnessByDistrictId(int ad_district_id) {
			ArrayList<LoanwitnessBean> bean = new ArrayList<LoanwitnessBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_loan_witness where ad_district_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_district_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					LoanwitnessBean bean1 = new LoanwitnessBean();
					
					bean1.setAd_loan_witness_id(rs.getInt("ad_loan_witness_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("created"));
					bean1.setUpdatedby(rs.getInt("createdby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("LoanWitnessDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			
			return bean;
		}
		//-------------------------------------
	public int updateLoanWitness(LoanwitnessBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "";
			ps = con.prepareStatement(query);
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("LoanWitnessDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
	
}
//----------------------------------------------------------------------------------------------
	public int deleteLoanWitness(LoanwitnessBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_loan_witness WHERE ad_loan_witness_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_loan_witness_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("LoanWitnessDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
		
	}



}
