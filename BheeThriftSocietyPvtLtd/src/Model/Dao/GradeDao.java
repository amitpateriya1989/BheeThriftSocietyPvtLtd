package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import java.util.ArrayList;
import Model.Bean.GradeBean;

public class GradeDao {
	
	
		private Connection con = null;

		public GradeDao() {
			con = DBConnection.getConnection();
		}

		public int addGrade(GradeBean bean) {
				int i  = 0;
				PreparedStatement ps = null;
				
			try {

				String query="INSERT INTO ad_grade("
						+ "grade_name, created, createdby, updated, updatedby,"
						+ "isactive, basic, da, convey, hra, sw, mdcl, alwnc)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				ps = con.prepareStatement(query);
				ps.setString(1, bean.getGrade_name());
				ps.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
				ps.setInt(3, bean.getCreatedby());
				ps.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
				ps.setInt(5, bean.getUpdatedby());
				ps.setBoolean(6, bean.isIsactive());
				ps.setDouble(7, bean.getBasics());
				ps.setDouble(8,bean.getDa());
				ps.setDouble(9,bean.getConvey());
				ps.setDouble(10,bean.getHra());
				ps.setDouble(11,bean.getSw());
				ps.setDouble(12,bean.getMdcl());
				ps.setDouble(13,bean.getAlwnc());

				System.out.print(ps);
				i =  ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return i;
		}//end insert function

		public GradeBean getGradeById(GradeBean bean1) {
			GradeBean bean = new GradeBean();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String query = "select * from ad_grade where ad_grade_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, bean1.getAd_grade_id());
				rs = ps.executeQuery();
				while (rs.next()) {
				
					bean.setAd_grade_id(rs.getInt("ad_grade_id"));
					bean.setCreated(rs.getDate("created"));
					bean.setCreatedby(rs.getInt("createdby"));
					bean.setUpdated(rs.getDate("updated"));
					bean.setUpdatedby(rs.getInt("updatedby"));
					bean.setIsactive(rs.getBoolean("isactive"));
					bean.setGrade_name(rs.getString("grade_name"));
					bean.setBasics(rs.getInt("basic"));
					bean.setDa(rs.getDouble("da"));
					bean.setConvey(rs.getDouble("convey"));
					bean.setHra(rs.getDouble("hra"));
					bean.setSw(rs.getDouble("sw"));
					bean.setMdcl(rs.getDouble("mdcl"));
					bean.setAlwnc(rs.getDouble("alwnc"));
					bean.setIsactive(rs.getBoolean("isactive"));
				}
			}catch (Exception e) {
					e.printStackTrace();
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
			return bean;
		}

		public GradeBean getGradeById(int ad_Grade_id) {
			GradeBean bean = new GradeBean();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String query = "select * from ad_Grade where ad_grade_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_Grade_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					
					
					bean.setAd_grade_id(rs.getInt("ad_grade_id"));
					
					bean.setGrade_name(rs.getString("grade_name"));
					bean.setBasics(rs.getInt("basic"));
					bean.setDa(rs.getDouble("da"));
					bean.setConvey(rs.getDouble("convey"));
					bean.setHra(rs.getDouble("hra"));
					bean.setSw(rs.getDouble("sw"));
					bean.setMdcl(rs.getDouble("mdcl"));
					bean.setAlwnc(rs.getDouble("alwnc"));
					bean.setIsactive(rs.getBoolean("isactive"));
		}
				}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
		public int updateGrade(GradeBean bean) {
			int i = 0;
			PreparedStatement ps = null;
			try {

				String query="UPDATE ad_grade"
						+ " SET grade_name=?, created=?, createdby=?, updated=?,"
						+ "updatedby=?, isactive=?, basic=?, da=?, convey=?, hra=?, sw=?,"
						+ "mdcl=?, alwnc=?"
						+ "WHERE ad_grade_id=?";

				ps = con.prepareStatement(query);
				ps.setString(1, bean.getGrade_name());
				ps.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
				ps.setInt(3, bean.getCreatedby());
				ps.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
				ps.setInt(5, bean.getUpdatedby());
				ps.setBoolean(6, bean.isIsactive());
				ps.setDouble(7, bean.getBasics());
				ps.setDouble(8,bean.getDa());
				ps.setDouble(9,bean.getConvey());
				ps.setDouble(10,bean.getHra());
				ps.setDouble(11,bean.getSw());
				ps.setDouble(12,bean.getMdcl());
				ps.setDouble(13,bean.getAlwnc());
				ps.setInt(14, bean.getAd_grade_id());
				
				System.out.print(ps);
				i = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return i;
		}//end updateGrade function

		public int deleteGrade(GradeBean bean){
			int i = 0;
			PreparedStatement ps = null;
			try{
				String query="DELETE FROM ad_grade WHERE ad_grade_id=?";
				ps = con.prepareStatement(query);
				ps.setInt(1, bean.getAd_grade_id());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return i;
		}//end delete function
		
		
		public ArrayList<GradeBean> getAllGrade() {
			ArrayList<GradeBean> bean = new ArrayList<GradeBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_grade";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					GradeBean bean1 = new GradeBean();
					bean1.setAd_grade_id(rs.getInt("ad_grade_id"));
					
					bean1.setGrade_name(rs.getString("grade_name"));
					bean1.setBasics(rs.getDouble("basic"));
					bean1.setDa(rs.getDouble("da"));
					bean1.setConvey(rs.getDouble("convey"));
					bean1.setHra(rs.getDouble("hra"));
					bean1.setSw(rs.getDouble("sw"));
					bean1.setMdcl(rs.getDouble("mdcl"));
					bean1.setAlwnc(rs.getDouble("alwnc"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("GradeDao:-error in try Block");
				e.printStackTrace();
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
		
}//end class