package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.WitnessBean;

public class WitnessDao {
	
	private Connection con = null;

	public WitnessDao() {
		con = DBConnection.getConnection();
			
	}

	public int addWitness(WitnessBean bean) {
		int record = 0;
		PreparedStatement ps=null;
		
		try{
			String query = "INSERT INTO ad_witness(ad_member_id, ad_salutation_id, ad_witness_name,"+ 
		            "ad_witness_mobile, ad_witness_address, ad_witness_mem_no,created,createdby,updated,updatedby,isactive) " +
		            "  VALUES (?, ?, ?, ?, ?, ?,?,?,?,?,?)";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_id());
			ps.setInt(2, bean.getAd_salutation_id());
			ps.setString(3, bean.getAd_witness_name());
			ps.setString(4, bean.getAd_witness_mobile());
			ps.setString(5, bean.getAd_witness_address());
			ps.setInt(6, bean.getAd_witness_mem_no());
			ps.setDate(7,new java.sql.Date(bean.getCreated().getTime()));
			ps.setInt(8, bean.getCreatedby());
			ps.setDate(9, new java.sql.Date(bean.getUpdated().getTime()));
			ps.setInt(10, bean.getUpdatedby());
			ps.setBoolean(11, bean.isIsactive());
			
			record=ps.executeUpdate();
			
		}catch(Exception Ex){
			System.out.print("MemberRegistrationDao:-error in try Block");
			Ex.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public WitnessBean getWitnessById(WitnessBean bean) {
		WitnessBean bean1 = new WitnessBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_witness where ad_member_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_witness_id(rs.getInt("ad_witness_id"));
				bean1.setAd_salutation_id(rs.getInt("ad_salutation_id"));
				bean1.setAd_witness_mem_no(rs.getInt("ad_witness_mem_no"));
				bean1.setAd_witness_name(rs.getString("ad_witness_name"));
				bean1.setAd_witness_mobile(rs.getString("ad_witness_mobile"));
				bean1.setAd_witness_address(rs.getString("ad_witness_address"));
				
				

			}
			}catch (Exception e) {
				System.out.print("WitnessDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public WitnessBean getWitnessById(int ad_member_id) {
		WitnessBean bean1 = new WitnessBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_witness where ad_member_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_witness_id(rs.getInt("ad_witness_id"));
				bean1.setAd_salutation_id(rs.getInt("ad_salutation_id"));
				bean1.setAd_witness_address(rs.getString("ad_witness_address"));
				bean1.setAd_witness_mem_no(rs.getInt("ad_witness_mem_no"));
				bean1.setAd_witness_mobile(rs.getString("ad_witness_mobile"));
				bean1.setAd_witness_name(rs.getString("ad_witness_name"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				
			}
		}catch (Exception e) {
			System.out.print("WitnessDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<WitnessBean> getAllWitness() {
		ArrayList<WitnessBean> bean = new ArrayList<WitnessBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_witness ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				WitnessBean bean1=new WitnessBean();
				bean1.setAd_witness_id(rs.getInt("ad_witness_id"));
				bean1.setAd_salutation_id(rs.getInt("ad_salutation_id"));
				bean1.setAd_witness_address(rs.getString("ad_witness_address"));
				bean1.setAd_witness_mem_no(rs.getInt("ad_witness_mem_no"));
				bean1.setAd_witness_mobile(rs.getString("ad_witness_mobile"));
				bean1.setAd_witness_name(rs.getString("ad_witness_name"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("WitnessDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateWitness(WitnessBean bean){
		int record = 0;
		PreparedStatement ps=null;
		
		try{
			String query = "UPDATE ad_witness " +
					" SET ad_member_id=?, ad_salutation_id=?, ad_witness_name=?,  ad_witness_mobile=?, " +
					" ad_witness_address=?, ad_witness_mem_no=?,  updated=?, updatedby=?," +
					" isactive=? WHERE ad_witness_id=?";
			
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_id());
			ps.setInt(2, bean.getAd_salutation_id());
			ps.setString(3, bean.getAd_witness_name());
			ps.setString(4, bean.getAd_witness_mobile());
			ps.setString(5, bean.getAd_witness_address());
			ps.setInt(6, bean.getAd_witness_mem_no());
			ps.setDate(7, new java.sql.Date(bean.getUpdated().getTime()));
			ps.setInt(8, bean.getUpdatedby());
			ps.setBoolean(9, bean.isIsactive());
			ps.setInt(10, bean.getAd_witness_id());
			record=ps.executeUpdate();
			
		}catch(Exception Ex){
			System.out.print("MemberRegistrationDao:-error in try Block");
			Ex.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
}


}
