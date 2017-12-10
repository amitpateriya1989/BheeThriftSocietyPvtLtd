package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Bean.PFRuleBean;
import Model.Bean.PTRuleBean;

public class PTRuleDao {
	
	private Connection con = null;

	public PTRuleDao() {
			con = DBConnection.getConnection();
		}

	public int addPTRule(PTRuleBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_pt_rule("
					+ " min_amt, max_amt, regular_charges, march_specific_charges,"
					+ " created, createdby, updated, updatedby, isactive, effective_from)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

					
			ps = con.prepareStatement(query);
			ps.setDouble(1, bean.getMin_amt());
			ps.setDouble(2, bean.getMax_amt());
			ps.setDouble(3, bean.getRegular_charges());
			ps.setDouble(4, bean.getMarch_specific_charges());
			ps.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(6, bean.getCreatedby());
			ps.setDate(7, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(8, bean.getUpdatedby());
			ps.setBoolean(9, true);
			ps.setDate(10,new java.sql.Date(bean.getEffective_from().getTime()));
			
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("PTRuleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public PTRuleBean getPTRuleById(PTRuleBean bean) {
		PTRuleBean bean1 = new PTRuleBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_pf_rule where ad_pt_rule_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_pt_rule_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_pt_rule_id(rs.getInt("Ad_pt_rule"));
				bean1.setEffective_from(rs.getDate("effective_from"));
				bean1.setMin_amt(rs.getDouble("Min_amt"));
				bean1.setMax_amt(rs.getDouble("Max_amt"));
				bean1.setRegular_charges(rs.getDouble("Regular_charges"));
				bean1.setMarch_specific_charges(rs.getDouble("March_specific_charges"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_pt_rule_id(rs.getInt("ad_pt_rule_id"));
				bean1.setEffective_to(rs.getDate("effective_to"));
				

			}
			}catch (Exception e) {
				System.out.print("PTRuleDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//-----------------------------------------------------------------------------------------
	public ArrayList<PTRuleBean> getAllPTRuleByStateId(int ad_state_id) {
		ArrayList<PTRuleBean> bean = new ArrayList<PTRuleBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_pt_rule where ad_pt_rule_id=?  ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_state_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				PTRuleBean bean1 = new PTRuleBean();
				bean1.setAd_pt_rule_id(rs.getInt("Ad_pt_rule"));
				bean1.setEffective_from(rs.getDate("effective_from"));
				bean1.setMin_amt(rs.getDouble("Min_amt"));
				bean1.setMax_amt(rs.getDouble("Max_amt"));
				bean1.setRegular_charges(rs.getDouble("Regular_charges"));
				bean1.setMarch_specific_charges(rs.getDouble("March_specific_charges"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_pt_rule_id(rs.getInt("ad_pt_rule_id"));
				bean1.setEffective_to(rs.getDate("effective_to"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("PTRuleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//------------------------------------------------------------------------------------------	
	public PTRuleBean getPTRuleById(int ad_pt_rule_id) {
		PTRuleBean bean1 = new PTRuleBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_pt_rule where ad_pt_rule_id=?  ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_pt_rule_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_pt_rule_id(rs.getInt("Ad_pt_rule"));
				bean1.setEffective_from(rs.getDate("effective_from"));
				bean1.setMin_amt(rs.getDouble("Min_amt"));
				bean1.setMax_amt(rs.getDouble("Max_amt"));
				bean1.setRegular_charges(rs.getDouble("Regular_charges"));
				bean1.setMarch_specific_charges(rs.getDouble("March_specific_charges"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_pt_rule_id(rs.getInt("ad_pt_rule_id"));
				bean1.setEffective_to(rs.getDate("effective_to"));
				}
		}catch (Exception e) {
			System.out.print("PTRuleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<PTRuleBean> getAllPTRule() {
		ArrayList<PTRuleBean> bean = new ArrayList<PTRuleBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_pt_rule";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				PTRuleBean bean1 = new PTRuleBean();
				
				bean1.setEffective_from(rs.getDate("effective_from"));
				bean1.setMin_amt(rs.getDouble("Min_amt"));
				bean1.setMax_amt(rs.getDouble("Max_amt"));
				bean1.setRegular_charges(rs.getDouble("Regular_charges"));
				bean1.setMarch_specific_charges(rs.getDouble("March_specific_charges"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setEffective_to(rs.getDate("effective_to"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("PTRuleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updatePTRule(PTRuleBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_pt_rule"
					+ " SET  effective_to=?,"
					+ " updated=?, updatedby=?, isactive=?"
					+ " WHERE  isactive='TRUE' and min_amt=? and max_amt=? ";

			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(bean.getEffective_from().getTime()));
			ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(3,bean.getUpdatedby());
			ps.setBoolean(4,false);	
			ps.setDouble(5, bean.getMin_amt());
			ps.setDouble(6, bean.getMax_amt());
				
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("PTRuleDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deletePTRule(PTRuleBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_pt_rule WHERE ad_pt_rule_id=? ";
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_pt_rule_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("PTRuleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}
}

