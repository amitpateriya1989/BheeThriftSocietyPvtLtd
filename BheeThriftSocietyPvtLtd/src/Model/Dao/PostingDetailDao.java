package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.PostingDetailBean;


public class PostingDetailDao {

	private Connection con = null;

	public PostingDetailDao() {
		con = DBConnection.getConnection();
	}

	public int addPostingDetail(PostingDetailBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			
			if(bean.getTodate()!=null ){
				
			
				String query = "INSERT INTO ad_posting_detail(" +
						"             created, createdby, updated, updatedby, " +
						"            isactive, ad_member_id, ad_branch_id, ad_designation_level_id, " +
						"            ad_designation_type_id, ad_designation_id, ad_department_id, " +
						"            formdate, todate)" +
						"    VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?,  ?, ?)";
				ps = con.prepareStatement(query);
				
				ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(2, bean.getCreatedby());
				ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(4, bean.getUpdatedby());
				ps.setBoolean(5, bean.isIsactive());
				ps.setInt(6, bean.getAd_member_id());
				ps.setInt(7, bean.getAd_branch_id());
				ps.setInt(8, bean.getAd_designation_level_id());
				ps.setInt(9, bean.getAd_designation_type_id());
				ps.setInt(10, bean.getAd_designation_id());
				ps.setInt(11, bean.getAd_department_id());
				ps.setDate(12, new java.sql.Date(bean.getFormdate().getTime()));
				ps.setDate(13, new java.sql.Date(bean.getTodate().getTime()));
				record=ps.executeUpdate();
				
			}else{
				
				String query = "INSERT INTO ad_posting_detail(" +
						"             created, createdby, updated, updatedby, " +
						"            isactive, ad_member_id, ad_branch_id, ad_designation_level_id, " +
						"            ad_designation_type_id, ad_designation_id, ad_department_id, " +
						"            formdate)" +
						"    VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?,  ?)";
				ps = con.prepareStatement(query);
				
				ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(2, bean.getCreatedby());
				ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(4, bean.getUpdatedby());
				ps.setBoolean(5, bean.isIsactive());
				ps.setInt(6, bean.getAd_member_id());
				ps.setInt(7, bean.getAd_branch_id());
				ps.setInt(8, bean.getAd_designation_level_id());
				ps.setInt(9, bean.getAd_designation_type_id());
				ps.setInt(10, bean.getAd_designation_id());
				ps.setInt(11, bean.getAd_department_id());
				ps.setDate(12, new java.sql.Date(bean.getFormdate().getTime()));			
				record=ps.executeUpdate();
				
			}
			
			
		} catch (Exception e) {
			System.out.print("PostingDetailDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
	
//-----------------------------------------------------------------------------------------
	public PostingDetailBean getPostingDetailById(int ad_posting_detail_id) {
		PostingDetailBean bean1 = new PostingDetailBean();
		ResultSet rs=null;
		PreparedStatement ps =null;
		//System.out.print(ad_posting_detail_id);
		String query = "select * from ad_posting_detail where ad_posting_detail_id=? ";
		try {
			 ps = con.prepareStatement(query);
			ps.setInt(1, ad_posting_detail_id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				bean1.setAd_posting_detail_id(rs.getInt("ad_posting_detail_id"));				
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));			
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
			//	System.out.print(rs.getInt("ad_branch_id"));
				bean1.setAd_designation_level_id(rs.getInt("ad_designation_level_id"));
				bean1.setAd_designation_type_id(rs.getInt("ad_designation_type_id"));
				bean1.setAd_designation_id(rs.getInt("ad_designation_id"));
				bean1.setAd_department_id(rs.getInt("ad_department_id"));
				bean1.setFormdate(rs.getDate("formdate"));
				bean1.setTodate(rs.getDate("todate"));
				
				
				}
			}catch (Exception e) {
				System.out.print("PostingDetailDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
		
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	
	public ArrayList<PostingDetailBean> getAllPostingDetail(int ad_member_id) {
		ArrayList<PostingDetailBean> bean = new ArrayList<PostingDetailBean>();
		ResultSet rs=null;
		PreparedStatement ps =null;
		String query = "select * from ad_posting_detail where ad_member_id=? ";
		try {
			 ps = con.prepareStatement(query);
			 ps.setInt(1, ad_member_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				PostingDetailBean bean1 = new PostingDetailBean();				
				bean1.setAd_posting_detail_id(rs.getInt("ad_posting_detail_id"));				
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));			
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAd_department_id(rs.getInt("ad_member_id"));
				bean1.setAd_department_id(rs.getInt("ad_branch_id"));
				bean1.setAd_department_id(rs.getInt("ad_designation_level_id"));
				bean1.setAd_department_id(rs.getInt("ad_designation_type_id"));
				bean1.setAd_department_id(rs.getInt("ad_designation_id"));
				bean1.setAd_department_id(rs.getInt("ad_department_id"));
				bean1.setFormdate(rs.getDate("formdate"));
				bean1.setTodate(rs.getDate("todate"));
				
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("PostingDetailDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------	
	
		public ArrayList<PostingDetailBean> getAllPostingDetailWithJoin(int ad_member_id) {
			ArrayList<PostingDetailBean> bean = new ArrayList<PostingDetailBean>();
			ResultSet rs=null;
			PreparedStatement ps =null;
			String query = "select ab.branch_code as bc ,adl.designation_level as dl,adt.designation_type as dt,ad.designation as d,adnt.name as de," +
					" apd.formdate as fd,apd.todate as td, apd.isactive as isactive, apd.ad_posting_detail_id as pdid " +
					" from ad_posting_detail apd " +
					" left join ad_designation_level adl ON adl.ad_designation_level_id=apd.ad_designation_level_id " +
					" left join ad_designation_type adt ON adt.ad_designation_type_id=apd.ad_designation_type_id " +
					" left join ad_designation ad ON ad.ad_designation_id=apd.ad_designation_id " +
					" left join ad_department adnt ON adnt.ad_department_id=apd.ad_department_id " +
					" left join ad_branch ab ON ab.ad_branch_id=apd.ad_branch_id " +
					" where apd.ad_member_id=? ";
			try {
				 ps = con.prepareStatement(query);
				 ps.setInt(1, ad_member_id);
				rs = ps.executeQuery();
				System.out.print(rs);
				while (rs.next()) {
					PostingDetailBean bean1 = new PostingDetailBean();				
					bean1.setAd_posting_detail_id(rs.getInt("pdid"));	
					bean1.setBranch_code(rs.getInt("bc"));
					bean1.setDesignation_level(rs.getString("dl"));
					bean1.setDesignation_type(rs.getString("dt"));
					bean1.setDesignation(rs.getString("d"));
					bean1.setDepartment(rs.getString("de"));	
					bean1.setFormdate(rs.getDate("fd"));
					bean1.setTodate(rs.getDate("td"));
					bean1.setIsactive(rs.getBoolean("isactive"));

					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("PostingDetailDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
	//----------------------------------------------------------------------------------------------
	public int updatePostingDetail(PostingDetailBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_posting_detail    SET   updated=?, updatedby=?, " +
					"        ad_branch_id=?, ad_designation_level_id=?,      " +
					"  ad_designation_type_id=?, ad_designation_id=?, ad_department_id=?, " +
					"       formdate=?, todate=? where ad_posting_detail_id=?";
			
				ps = con.prepareStatement(query);
		
				
				ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(2, bean.getUpdatedby());					
				ps.setInt(3, bean.getAd_branch_id());
				ps.setInt(4, bean.getAd_designation_level_id());
				ps.setInt(5, bean.getAd_designation_type_id());
				ps.setInt(6, bean.getAd_designation_id());
				ps.setInt(7, bean.getAd_department_id());
				ps.setDate(8, new java.sql.Date(bean.getFormdate().getTime()));
				ps.setDate(9, new java.sql.Date(bean.getTodate().getTime()));
				ps.setInt(10, bean.getAd_posting_detail_id());
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("PostingDetailDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}


	
}
