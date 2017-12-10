package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Bean.EmailUtilityBean;

public class EmailUtilityDao {
	private Connection con = null;

	public EmailUtilityDao() {
		con = DBConnection.getConnection();
		}

	public int addEmailUtility(EmailUtilityBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_email(created, createdby, updated, updatedby, " +
					"isactive, email_id, pwd, host_name, port_no) " +
					" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, bean.isIsactive());
			ps.setString(6, bean.getEmail_id());
			ps.setString(7, bean.getPwd());
			ps.setString(8,bean.getHost_name());
			ps.setInt(9, bean.getPort_no());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("EmailUtilityDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public EmailUtilityBean getEmailUtilityById(EmailUtilityBean bean) {
		EmailUtilityBean bean1 = new EmailUtilityBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_email where ad_email_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_email_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_email_id(rs.getInt("ad_email_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setEmail_id(rs.getString("email_id"));
				bean1.setPwd(rs.getString("pwd"));
				bean1.setHost_name(rs.getString("host_name"));
				bean1.setPort_no(rs.getInt("port_no"));
			
			}
			}catch (Exception e) {
				System.out.print("EmailUtilityDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public EmailUtilityBean getEmailUtilityById(int ad_email_id) {
		EmailUtilityBean bean1 = new EmailUtilityBean();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_email where ad_email_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_email_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_email_id(rs.getInt("ad_email_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setEmail_id(rs.getString("email_id"));
				bean1.setPwd(rs.getString("pwd"));
				bean1.setHost_name(rs.getString("host_name"));
				bean1.setPort_no(rs.getInt("port_no"));
				
			}
		}catch (Exception e) {
			System.out.print("EmailUtilityDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------	
		public EmailUtilityBean getEmailUtilityByMaxId() {
			EmailUtilityBean bean1 = new EmailUtilityBean();
			PreparedStatement ps = null;
			ResultSet rs=null;
			String query = "select * from ad_email where ad_email_id=(select Max(ad_email_id) from ad_email) ";
			try {
				ps = con.prepareStatement(query);
				
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_email_id(rs.getInt("ad_email_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setEmail_id(rs.getString("email_id"));
					bean1.setPwd(rs.getString("pwd"));
					bean1.setHost_name(rs.getString("host_name"));
					bean1.setPort_no(rs.getInt("port_no"));
					
				}
			}catch (Exception e) {
				System.out.print("EmailUtilityDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//--------------------------------------------------------------------------------------
	public ArrayList<EmailUtilityBean> getAllEmailUtility() {
		ArrayList<EmailUtilityBean> bean = new ArrayList<EmailUtilityBean>();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query = "select * from ad_email ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				EmailUtilityBean bean1 = new EmailUtilityBean();
				bean1.setAd_email_id(rs.getInt("ad_email_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setEmail_id(rs.getString("email_id"));
				bean1.setPwd(rs.getString("pwd"));
				bean1.setHost_name(rs.getString("host_name"));
				bean1.setPort_no(rs.getInt("port_no"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("EmailUtilityDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateEmailUtility(EmailUtilityBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_email " +
					" SET updated=?, updatedby=?, isactive=?, email_id=?, pwd=?, " +
					"host_name=?, port_no=? WHERE ad_email_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getEmail_id());
			ps.setString(5, bean.getPwd());
			ps.setString(6,bean.getHost_name());
			ps.setInt(7, bean.getPort_no());
			ps.setInt(8, bean.getAd_email_id());

			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("EmailUtilityDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
//----------------------------------------------------------------------------------------------
	public int deleteEmailUtility(EmailUtilityBean bean){
		int i=0;
		PreparedStatement ps = null;
		try{
			String query="DELETE FROM ad_email WHERE ad_email_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_email_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("EmailUtilityDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}//end delete function


}//end of class
