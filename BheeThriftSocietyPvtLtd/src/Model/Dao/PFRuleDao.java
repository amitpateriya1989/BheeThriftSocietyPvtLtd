package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Bean.PFRuleBean;

public class PFRuleDao {
	
	private Connection con = null;

	public PFRuleDao() {
			con = DBConnection.getConnection();
			System.out.println("coon obj = "+con);
		}

	public int addPFRule(PFRuleBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_pf_rule( "
            + " epf_emp_share, epf_employer_share, eps_employer_share,  "
            + " edli_charges, epf_admin_charges, edli_admin_charges, created, " 
            + " createdby, updated, updatedby, isactive, effective_from) "
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";		
			System.out.println("ps obj = "+con.prepareStatement(query));
			ps = con.prepareStatement(query);
			ps.setDouble(1, bean.getEpf_emp_share());
			ps.setDouble(2, bean.getEpf_employer_share());
			ps.setDouble(3, bean.getEps_employer_share());
			ps.setDouble(4, bean.getEdli_charges());
			ps.setDouble(5, bean.getEpf_admin_charges());
			ps.setDouble(6, bean.getEdli_admin_charges());
			ps.setDate(7, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(8, bean.getCreatedby());
			ps.setDate(9, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(10, bean.getUpdatedby());
			ps.setBoolean(11, true);
			ps.setDate(12,new java.sql.Date(bean.getEffective_from().getTime()));
			//ps.setDate(13,new java.sql.Date(bean.getEffective_to().getTime()));
			System.out.println(ps);
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("PFRuleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public PFRuleBean getPFRuleById(PFRuleBean bean) {
		PFRuleBean bean1 = new PFRuleBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_pf_rule where ad_pf_rule_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_pf_rule_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_pf_rule_id(rs.getInt("Ad_pf_rule"));
				bean1.setEffective_from(rs.getDate("effective_from"));
				bean1.setEpf_emp_share(rs.getDouble("Epf_emp_share"));
				bean1.setEpf_employer_share(rs.getDouble("Epf_employer_share"));
				bean1.setEps_employer_share(rs.getDouble("eps_employer_share"));
				bean1.setEdli_charges(rs.getDouble("Edli_charges"));
				bean1.setEdli_admin_charges(rs.getDouble("edli_admin_charges"));
				bean1.setEpf_admin_charges(rs.getDouble("epf_admin_charges"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_pf_rule_id(rs.getInt("ad_pf_rule_id"));
				bean1.setEffective_from(rs.getDate("effective_to"));
				
				

			}
			}catch (Exception e) {
				System.out.print("PFRuleDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//-----------------------------------------------------------------------------------------
	public ArrayList<PFRuleBean> getAllPFRuleByStateId(int ad_pf_rule_id) {
		ArrayList<PFRuleBean> bean = new ArrayList<PFRuleBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_pf_rule where ad_pf_rule_id=?  ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_pf_rule_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				PFRuleBean bean1 = new PFRuleBean();
				bean1.setAd_pf_rule_id(rs.getInt("Ad_pf_rule"));
				bean1.setEffective_from(rs.getDate("effective_from"));
				bean1.setEpf_emp_share(rs.getDouble("Epf_emp_share"));
				bean1.setEpf_employer_share(rs.getDouble("Epf_employer_share"));
				bean1.setEps_employer_share(rs.getDouble("eps_employer_share"));
				bean1.setEdli_charges(rs.getDouble("Edli_charges"));
				bean1.setEdli_admin_charges(rs.getDouble("edli_admin_charges"));
				bean1.setEpf_admin_charges(rs.getDouble("epf_admin_charges"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_pf_rule_id(rs.getInt("ad_pf_rule_id"));
				bean1.setEffective_to(rs.getDate("effective_to"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("PFRuleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//------------------------------------------------------------------------------------------	
	public PFRuleBean getPFRuleById(int ad_pf_rule_id) {
		PFRuleBean bean1 = new PFRuleBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_pf_rule where ad_pf_rule_id=?  ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_pf_rule_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_pf_rule_id(rs.getInt("Ad_pf_rule"));
				bean1.setEffective_from(rs.getDate("effective_from"));
				bean1.setEpf_emp_share(rs.getDouble("Epf_emp_share"));
				bean1.setEpf_employer_share(rs.getDouble("Epf_employer_share"));
				bean1.setEps_employer_share(rs.getDouble("eps_employer_share"));
				bean1.setEdli_charges(rs.getDouble("Edli_charges"));
				bean1.setEdli_admin_charges(rs.getDouble("edli_admin_charges"));
				bean1.setEpf_admin_charges(rs.getDouble("epf_admin_charges"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_pf_rule_id(rs.getInt("ad_pf_rule_id"));
				bean1.setEffective_to(rs.getDate("effective_to"));
			}
		}catch (Exception e) {
			System.out.print("PFRuleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<PFRuleBean> getAllPFRule() {
		ArrayList<PFRuleBean> bean = new ArrayList<PFRuleBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_pf_rule";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				PFRuleBean bean1 = new PFRuleBean();
				
				bean1.setEffective_from(rs.getDate("effective_from"));
				bean1.setEpf_emp_share(rs.getDouble("Epf_emp_share"));
				bean1.setEpf_employer_share(rs.getDouble("Epf_employer_share"));
				bean1.setEps_employer_share(rs.getDouble("eps_employer_share"));
				bean1.setEdli_charges(rs.getDouble("Edli_charges"));
				bean1.setEdli_admin_charges(rs.getDouble("edli_admin_charges"));
				bean1.setEpf_admin_charges(rs.getDouble("epf_admin_charges"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setEffective_to(rs.getDate("effective_to"));
				
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("PFRuleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updatePFRule(PFRuleBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_pf_rule"
					+ " SET  effective_to=?,"
					+ " updated=?, updatedby=?, isactive=?"
					+ " WHERE  isactive=? ";

			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(bean.getEffective_from().getTime()));
			ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(3, bean.getUpdatedby());
			ps.setBoolean(4,false);
			ps.setBoolean(5,true);			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("PFRuleDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deletePFRule(PFRuleBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_pf_rule WHERE ad_pf_rule_id=? ";
			 ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_pf_rule_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("PFRuleDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}
	
}

