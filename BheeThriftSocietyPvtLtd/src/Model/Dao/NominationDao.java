package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.MemberRegistrationBean;
import Model.Bean.NominationBean;

public class NominationDao {
	private Connection con = null;

	public NominationDao() {
		con = DBConnection.getConnection();
	}

	public int addNomination(NominationBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_nomination(" +
					"ad_relation_id, ad_gender_id, created, createdby," +
					"updated, updatedby, ad_salutation_id, isactive, name, dob," +
					"age, ad_member_id, guardian)" +
					"VALUES ( ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?)" ;
			
			/*
			String query = "INSERT INTO ad_nomination(" +
					"ad_relation_id, ad_gender_id, created, createdby," +
					"updated, updatedby, ad_salutation_id, isactive, name, dob," +
					"age, photo, sign, id_proof, ad_member_id, guardian)" +
					"VALUES ( ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)" ;
			 */
					
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getRelation().getAd_relation_id());
			ps.setInt(2, bean.getGender().getAd_gender_id());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getCreatedby());
			ps.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(6, bean.getUpdatedby());
			ps.setInt(7, bean.getSalutation().getAd_salutation_id());
			ps.setBoolean(8, true);
			ps.setString(9, bean.getName());
			ps.setDate(10, new java.sql.Date(bean.getDob().getTime()));
			ps.setString(11, bean.getAge());
		/*	ps.setString(12, bean.getPhoto());
			ps.setString(13, bean.getSign());
			ps.setString(14, bean.getId_proof());*/               
			ps.setInt(12, bean.getAd_member_id());
			ps.setString(13, bean.getGuardian());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("NominationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public int addNomineeFileUpload(NominationBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "UPDATE ad_nomination SET photo=?, sign=?, id_proof=? WHERE ad_member_id=? AND " +
					" ad_nomination_id=(Select Min(ad_nomination_id) from ad_nomination where ad_member_id=?" ;

			ps = con.prepareStatement(query);
			ps.setString(1, bean.getPhoto());
			ps.setString(2, bean.getSign());
			ps.setString(3, bean.getId_proof());
			ps.setInt(4, bean.getAd_member_id());
			ps.setInt(5, bean.getAd_member_id());
			//System.out.println(ps);
			record=ps.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.print("MemberRegistrationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
		
	}
	//-----------------------------------------------------------------------------------------
		public int addNomineeFirstFileUpload(NominationBean bean) {
			int record=0;
			PreparedStatement ps=null;
			try {
				
				String query = "UPDATE ad_nomination SET photo=?, sign=?, id_proof=? WHERE ad_member_id=? AND " +
						" ad_nomination_id=(Select Min(ad_nomination_id) from ad_nomination where ad_member_id=?" ;

				ps = con.prepareStatement(query);
				ps.setString(1, bean.getPhoto());
				ps.setString(2, bean.getSign());
				ps.setString(3, bean.getId_proof());
				ps.setInt(4, bean.getAd_member_id());
				ps.setInt(5, bean.getAd_member_id());
				//System.out.println(ps);
				record=ps.executeUpdate();
				
				
			} catch (Exception e) {
				System.out.print("MemberRegistrationDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return record;
			
		}
		//-----------------------------------------------------------------------------------------
				public int addNomineeSecondFileUpload(NominationBean bean) {
					int record=0;
					PreparedStatement ps=null;
					try {
						
						String query = "UPDATE ad_nomination SET photo=?, sign=?, id_proof=? WHERE ad_member_id=? AND " +
								" ad_nomination_id=(Select MAX(ad_nomination_id) from ad_nomination where ad_member_id=?" ;

						ps = con.prepareStatement(query);
						ps.setString(1, bean.getPhoto());
						ps.setString(2, bean.getSign());
						ps.setString(3, bean.getId_proof());
						ps.setInt(4, bean.getAd_member_id());
						ps.setInt(5, bean.getAd_member_id());
						//System.out.println(ps);
						record=ps.executeUpdate();
						
						
					} catch (Exception e) {
						System.out.print("MemberRegistrationDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return record;
					
				}
//-----------------------------------------------------------------------------------------	
	
	public NominationBean getNominationById(NominationBean bean) {
		NominationBean bean1 = new NominationBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_nomination where ad_nomination_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_nomination_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_nomination_id(rs.getInt("ad_nomination_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setGender(new GenderDao().getGenderById(rs.getInt("ad_gender_id")));
				bean1.setRelation(new RelationDao().getRelationById(rs.getInt("ad_relation_id")));
				bean1.setSalutation(new SalutationDao().getSalutationById(rs.getInt("ad_salutation_id")));
				bean1.setAge(rs.getString("age"));
				bean1.setDob(rs.getDate("dob"));
				bean1.setName(rs.getString("name"));
				bean1.setPhoto(rs.getString("photo"));
				bean1.setSign(rs.getString("sign"));
				bean1.setId_proof(rs.getString("id_proof"));
				bean1.setGuardian("guardian");

			}
			}catch (Exception e) {
				System.out.print("NominationDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public NominationBean getNominationById(int ad_nomination_id) {
		NominationBean bean1 = new NominationBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_nomination where ad_nomination_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_nomination_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_nomination_id(rs.getInt("ad_nomination_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setGender(new GenderDao().getGenderById(rs.getInt("ad_gender_id")));
				bean1.setRelation(new RelationDao().getRelationById(rs.getInt("ad_relation_id")));
				bean1.setSalutation(new SalutationDao().getSalutationById(rs.getInt("ad_salutation_id")));
				bean1.setAge(rs.getString("age"));
				bean1.setDob(rs.getDate("dob"));
				bean1.setName(rs.getString("name"));
				bean1.setPhoto(rs.getString("photo"));
				bean1.setSign(rs.getString("sign"));
				bean1.setId_proof(rs.getString("id_proof"));
				bean1.setGuardian("guardian");
			}
		}catch (Exception e) {
			System.out.print("NominationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
	
	//------------------------------------------------------------------------------------------	
		public ArrayList<NominationBean> getNominationByMemberId(int ad_member_id) {
			ArrayList<NominationBean> bean = new ArrayList<NominationBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_nomination where ad_member_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_member_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					NominationBean bean1 = new NominationBean();
					bean1.setAd_nomination_id(rs.getInt("ad_nomination_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setGender(new GenderDao().getGenderById(rs.getInt("ad_gender_id")));
					bean1.setRelation(new RelationDao().getRelationById(rs.getInt("ad_relation_id")));
					bean1.setSalutation(new SalutationDao().getSalutationById(rs.getInt("ad_salutation_id")));
					bean1.setAge(rs.getString("age"));
					bean1.setDob(rs.getDate("dob"));
					bean1.setName(rs.getString("name"));
					bean1.setPhoto(rs.getString("photo"));
					bean1.setSign(rs.getString("sign"));
					bean1.setId_proof(rs.getString("id_proof"));
					bean1.setGuardian("guardian");
					bean.add(bean1);
				}
			}catch (Exception e) {
				System.out.print("NominationDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}	
//--------------------------------------------------------------------------------------
	public ArrayList<NominationBean> getAllNomination() {
		ArrayList<NominationBean> bean = new ArrayList<NominationBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_nomination ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				NominationBean bean1 = new NominationBean();
				bean1.setAd_nomination_id(rs.getInt("ad_nomination_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setGender(new GenderDao().getGenderById(rs.getInt("ad_gender_id")));
				bean1.setRelation(new RelationDao().getRelationById(rs.getInt("ad_relation_id")));
				bean1.setSalutation(new SalutationDao().getSalutationById(rs.getInt("ad_salutation_id")));
				bean1.setAge(rs.getString("age"));
				bean1.setDob(rs.getDate("dob"));
				bean1.setName(rs.getString("name"));
				bean1.setPhoto(rs.getString("photo"));
				bean1.setSign(rs.getString("sign"));
				bean1.setId_proof(rs.getString("id_proof"));
				bean1.setGuardian("guardian");
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("NominationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public void updateNomination(NominationBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_nomination " +
					"SET  ad_relation_id=?, ad_gender_id=?, updated=?, updatedby=?, " +
					"ad_salutation_id=?, isactive=?,name=?, dob=?, age=?, photo=?, " +
					"sign=?, id_proof=?, ad_member_id=?, guardian=? WHERE ad_nomination_id=?";
			 ps = con.prepareStatement(query);
			
			ps.setInt(1, bean.getRelation().getAd_relation_id());
			ps.setInt(2, bean.getGender().getAd_gender_id());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setInt(5, bean.getSalutation().getAd_salutation_id());
			ps.setBoolean(6, true);
			ps.setString(7, bean.getName());
			ps.setDate(8, new java.sql.Date(bean.getDob().getTime()));
			ps.setString(9, bean.getAge());
			ps.setString(10, bean.getPhoto());
			ps.setString(11, bean.getSign());
			ps.setString(12, bean.getId_proof());
			ps.setInt(13, bean.getAd_member_id());
			ps.setString(14,bean.getGuardian());
			ps.setInt(15, bean.getAd_nomination_id());
			
			
			System.out.println(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("NominationDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public int deleteNomination(NominationBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_nomination WHERE ad_nomination_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_nomination_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("NominationDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}//end delete method

}//end class