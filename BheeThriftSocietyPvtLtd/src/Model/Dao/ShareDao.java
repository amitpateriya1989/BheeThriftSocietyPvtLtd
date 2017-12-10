package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.ShareBean;

public class ShareDao {
	private Connection con = null;

	public ShareDao() {
		try{
			con = DBConnection.getConnection();
			
			}catch(Exception s){
				System.out.println("ShareDao:-Exception in Creating connection and auto commit off");
				s.printStackTrace();
			}
		
	}

	public int addShare(ShareBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_share(per_share_rate, f_date, t_date, created, createdby," +
					" updated, updatedby, isactive)   VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			
			ps.setDouble(1, bean.getPer_share_rate());
			ps.setString(2,bean.getF_date());
			ps.setString(3, bean.getT_date());
			ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(5, bean.getCreatedby());
			ps.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(7, bean.getUpdatedby());
			ps.setBoolean(8, true);
			
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("ShareDao:-error in try Block");
			e.printStackTrace();
			if(record==0){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("ShareDao:-error in rollback");
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
	public ShareBean getShareById(ShareBean bean) {
		ShareBean bean1 = new ShareBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_share where ad_share_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_share_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_share_id(rs.getInt("ad_share_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setPer_share_rate(rs.getDouble("per_share_rate"));
				bean1.setF_date(rs.getString("f_date"));
				bean1.setT_date(rs.getString("t_date"));				
				

			}
			}catch (Exception e) {
				System.out.print("ShareDao:-error in try Block");
				e.printStackTrace();
				if(rs!=null){
					try{
						con.rollback();
					}catch(SQLException s){
						System.out.print("ShareDao:-error in rollback");
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
	public ShareBean getShareById(int ad_share_id) {
		ShareBean bean1 = new ShareBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_share where ad_share_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_share_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_share_id(rs.getInt("ad_share_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setPer_share_rate(rs.getDouble("per_share_rate"));
				bean1.setF_date(rs.getString("f_date"));
				bean1.setT_date(rs.getString("t_date"));
				
			}
		}catch (Exception e) {
			System.out.print("ShareDao:-error in try Block");
			e.printStackTrace();
			if(rs!=null){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("ShareDao:-error in rollback");
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
	public ArrayList<ShareBean> getAllShare() {
		ArrayList<ShareBean> bean = new ArrayList<ShareBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_share ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				ShareBean bean1 = new ShareBean();
				bean1.setAd_share_id(rs.getInt("ad_share_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setPer_share_rate(rs.getDouble("per_share_rate"));
				bean1.setF_date(rs.getString("f_date"));
				bean1.setT_date(rs.getString("t_date"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("ShareDao:-error in try Block");
			e.printStackTrace();
			if(rs!=null){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("ShareDao:-error in rollback");
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
		public ArrayList<ShareBean> getAllShareByType(int ad_ac_type_id) {
			ArrayList<ShareBean> bean = new ArrayList<ShareBean>();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from ad_share where ad_ac_type_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_ac_type_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					ShareBean bean1 = new ShareBean();
					bean1.setAd_share_id(rs.getInt("ad_share_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setPer_share_rate(rs.getDouble("per_share_rate"));
					bean1.setF_date(rs.getString("f_date"));
					bean1.setT_date(rs.getString("t_date"));
					
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("ShareDao:-error in try Block");
				e.printStackTrace();
				
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
//----------------------------------------------------------------------------------------------
	public int updateShare(ShareBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_share" +
					"   SET  per_share_rate=?, f_date=?, t_date=?, updated=?, updatedby=?, isactive=? " +
					" WHERE ad_share_id=?";
			ps = con.prepareStatement(query);
			ps.setDouble(1, bean.getPer_share_rate());
			ps.setString(2,bean.getF_date());
			ps.setString(3, bean.getT_date());
			ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(5, bean.getUpdatedby());
			ps.setBoolean(6, true);
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("ShareDao:-error in try Block");
		e.printStackTrace();
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
	
//---------------------------------------------------------------------------------------------

	public int updateShareStatus(ShareBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_share " +
					"SET  updated=?, updatedby=?," +
					"isactive=?, t_date=?  WHERE ad_share_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4, bean.getT_date());
			ps.setInt(5, bean.getAd_share_id());
			
			
			System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("ShareDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
	
//----------------------------------------------------------------------------------------------
	public int deleteShare(ShareBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_share WHERE ad_share_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_share_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("ShareDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}
/////-----------------------------------------------------------------------------
	public ShareBean getShareMaxId() {
		ShareBean bean1 = new ShareBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select max(ad_share_id) as ad_share_id from ad_share ";
		try {
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_share_id(rs.getInt("ad_share_id"));
				
				
			}
		}catch (Exception e) {
			System.out.print("ShareDao:-error in try Block");
			e.printStackTrace();
			if(rs!=null){
				try{
					con.rollback();
				}catch(SQLException s){
					System.out.print("ShareDao:-error in rollback");
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




/////-----------------------------------------------------------------------------
public ShareBean getShareRate() {
	ShareBean bean1 = new ShareBean();
	PreparedStatement ps =null;
	ResultSet rs=null;
	String query = "select per_share_rate from ad_share Where ad_share_id=(select max(ad_share_id) as ad_share_id from ad_share where isactive='true')";
	try {
		ps = con.prepareStatement(query);
		
		rs = ps.executeQuery();
		while (rs.next()) {
			bean1.setPer_share_rate(rs.getDouble("per_share_rate"));
			
			
		}
	}catch (Exception e) {
		System.out.print("ShareDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		DBConnection.close(rs);
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return bean1;
}

}
