package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.jasper.tagplugins.jstl.Util;

import Model.Bean.AccountSubGroupBean;
import Model.Bean.BankBranchBean;
import Model.Bean.CityBean;
import Model.Bean.DistrictBean;
import Model.Bean.LoanTrxBean;
import Model.Bean.MemberAddressBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.MemberShareBean;
import Model.Bean.NominationBean;
import Model.Bean.StateBean;

public class TempExcelUploadDao {

	private Connection con = null;

	public TempExcelUploadDao() {
		con = DBConnection.getConnection();
	}

	public void addMemberRegistration(MemberRegistrationBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {

			String query = "INSERT INTO ad_member(" +
					" isactive, " +
					"name,     ad_gender_id, " +


					" ad_pf_no,ad_society_no, ad_category_id, doj,dob,ad_branch_id)" +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )" ;

			ps = con.prepareStatement(query);

			ps.setBoolean(1, true);
			ps.setString(2, bean.getName());

			//ps.setDate(4,new java.sql.Date(bean.getDob().getTime()));				
			ps.setInt(3, bean.getAd_gender_id());			
			//	ps.setInt(5, bean.getAd_member_status_id());
			//ps.setInt(6, bean.getAd_salutation_id());				
			//	ps.setDate(8, new java.sql.Date(bean.getDoa().getTime()));			
			ps.setInt(4, bean.getAd_pf_no());
			ps.setInt(5, bean.getAd_society_no());
			//ps.setString(9,bean.getMarital_status());
			//	ps.setString(10, bean.getCast());
			//ps.setString(11, bean.getTermination_status());
			//	ps.setInt(12, bean.getAd_member_id());
			ps.setInt(6, bean.getCategory().getAd_category_id());
			ps.setDate(7, new java.sql.Date(bean.getDoj().getTime()));
			ps.setDate(8, new java.sql.Date(bean.getDob().getTime()));
			ps.setInt(9, bean.getBranch().getAd_branch_id());
			//System.out.println(ps);
			record=ps.executeUpdate();


		} catch (Exception e) {
			System.out.print("MemberRegistrationDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}
	//-------------------------
	public int addLoanTrx(LoanTrxBean bean) {
		int genid=0;
		PreparedStatement ps=null;
		try {

			String query = "INSERT INTO loan_trx( loan_trx_id, ad_emp_id, loan_type, loan_cataegory, loan_amt, isactive,ad_voucher_id) " +
					"VALUES (?, ?, ?, ?, ?,?,?)" ;

			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getLoan_trx_id());
			ps.setInt(2, bean.getAd_member_id());
			ps.setInt(3, 2);
			ps.setInt(4,2);
			ps.setDouble(5, bean.getLoan_amt());		
			ps.setBoolean(6, true);
			ps.setInt(7,0);
			ps.executeUpdate();


		} catch (Exception e) {
			System.out.print("LoanTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return genid;



	}
	public void addMemberShare(MemberShareBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {

			String query = "INSERT INTO ad_member_share(" +
					" ad_member_share_id, ad_member_id, date_of_allocation," +
					"   share_amt, isactive, ad_voucher_id)" +
					" VALUES (?, ?, '2016-04-01', ?, ?, ?);" ;

			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_share_id());
			ps.setInt(2, bean.getAd_member_id());

			ps.setDouble(3, bean.getShare_amt());
			ps.setBoolean(4, true);			
			ps.setInt(5, 0);	
			record=ps.executeUpdate();

		} catch (Exception e) {
			System.out.print("MemberShareDao:-error in try Block");
			e.printStackTrace();


		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}
	//-----------------------------------------------------------------------------------------/
	public void addState(StateBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {

			String query = "INSERT INTO ad_state(" +
					"created, createdby, updated, updatedby, isactive,state)" +
					" VALUES ( ?, ?, ?, ?, ?, ?)" ;

			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getState());

			record=ps.executeUpdate();

		} catch (Exception e) {
			System.out.print("StateDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}


	}
	public void addDistrict(DistrictBean bean) {
		int record=0;
		PreparedStatement ps=null;


		try {

			String query = "INSERT INTO ad_district(" +
					"ad_state_id, created, createdby, updated, updatedby," +
					"isactive, district) VALUES (?, ?, ?, ?, ?, ?, ? )" ;

			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getState().getAd_state_id());
			ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(3, bean.getCreatedby());
			ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(5, bean.getUpdatedby());
			ps.setBoolean(6, true);
			ps.setString(7, bean.getDistrict());

			record=ps.executeUpdate();

		} catch (Exception e) {
			System.out.print("DistrictDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}


	}
	//getcitybybean-----------------------------------------------------------------------------------------
	public int  getcitybybean(CityBean bean) {
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select count(*) as cnt from ad_city where LOWER(city)=LOWER(?) and ad_district_id=? and ad_state_id=? and pincode=?  ";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, bean.getCity().trim());
			ps.setInt(2, bean.getAd_district_id());
			ps.setInt(3, bean.getAd_state_id());
			ps.setInt(4, bean.getPincode());
			rs = ps.executeQuery();
			while (rs.next()) {
				result=rs.getInt("cnt");

			}
		}catch (Exception e) {
			System.out.print("DistrictDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return result;
	}
	//------------------------------------------------------------------------------------------	
	public int  getDistrictbyname(String name) {
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select count(*) as cnt from ad_district where LOWER(district)=LOWER(?)  ";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, name.trim());
			rs = ps.executeQuery();
			while (rs.next()) {
				result=rs.getInt("cnt");

			}
		}catch (Exception e) {
			System.out.print("DistrictDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return result;
	}


	//------------------------------------------------------------------------------------------	
	public int  getStateid(String name) {
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select ad_state_id from ad_state where LOWER(state)=LOWER(?)  ";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, name.trim());
			rs = ps.executeQuery();
			while (rs.next()) {
				result=rs.getInt("ad_state_id");

			}
		}catch (Exception e) {
			System.out.print("DistrictDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return result;
	}
	//------------------------------------------------------------------------------------------	
	public int  getcityid(String name) {
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select ad_city_id from ad_city where LOWER(city)=LOWER(?)  ";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, name.trim());
			rs = ps.executeQuery();
			while (rs.next()) {
				result=rs.getInt("ad_city_id");

			}
		}catch (Exception e) {
			System.out.print("DistrictDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return result;
	}
	//-----------------------------------------------------------------------------------------
	public int  getBankBranchbycode(int code) {
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select count(*) as cnt from ad_branch where branch_code=?  ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, code);
			rs = ps.executeQuery();
			while (rs.next()) {
				result=rs.getInt("cnt");

			}
		}catch (Exception e) {
			System.out.print("DistrictDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return result;
	}

	//----------------------------------------			

	public void addBankBranch(BankBranchBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {

			String query = "INSERT INTO ad_branch(" +
					"created, createdby, updated, updatedby, isactive," +
					"branch, ad_bank_id, " +
					" branch_code,  address, pincode," +
					"  email_id, phone_no, fax_no)" +
					"VALUES ( ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)" ;

			ps = con.prepareStatement(query);
			ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(2, 1);
			ps.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(4, 1);
			ps.setBoolean(5, true);
			ps.setString(6, bean.getBranch());
			ps.setInt(7, 1);

			ps.setInt(8, bean.getBranch_code());

			ps.setString(9, bean.getAddress());
			ps.setString(10, bean.getPincode());

			ps.setString(11, bean.getEmail_id());
			ps.setString(12, bean.getPhone_no());
			ps.setString(13, bean.getFax_no());

			record=ps.executeUpdate();

		} catch (Exception e) {
			System.out.print("BankBranchDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}
	//-----------------------------------------------------------------------------------------

	public void addMemberAddress(MemberAddressBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			String query =null;
			if(bean.getCity().getAd_city_id()!=0){
				query = "INSERT INTO ad_member_address(" +
						"ad_country_id,  ad_city_id,  ad_member_id," +
						"line1,  phone, type, created, createdby, " +
						"updated, updatedby, isactive, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
				ps = con.prepareStatement(query);
				ps.setInt(1, 1);			
				ps.setInt(2, bean.getCity().getAd_city_id());			
				ps.setInt(3, bean.getAd_member_id());
				ps.setString(4, bean.getLine1());				
				ps.setString(5, bean.getPhone());
				ps.setString(6, bean.getType());
				ps.setDate(7, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(8, bean.getCreatedby());
				ps.setDate(9, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(10, bean.getUpdatedby());
				ps.setBoolean(11, true);
				ps.setString(12, bean.getEmail());
				record=ps.executeUpdate();



			}else{

				query = "INSERT INTO ad_member_address(" +
						"ad_country_id,    ad_member_id," +
						"line1,  phone, type, created, createdby, " +
						"updated, updatedby, isactive, email) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
				ps = con.prepareStatement(query);
				ps.setInt(1, 1);							
				ps.setInt(2, bean.getAd_member_id());
				ps.setString(3, bean.getLine1());				
				ps.setString(4, bean.getPhone());
				ps.setString(5, bean.getType());
				ps.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(7, bean.getCreatedby());
				ps.setDate(8, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(9, bean.getUpdatedby());
				ps.setBoolean(10, true);
				ps.setString(11, bean.getEmail());
				record=ps.executeUpdate();
			}





		} catch (Exception e) {
			System.out.print("MemberAddressDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}


	//--------------------------------------
	public void updateMemberAddress(MemberAddressBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			String query =null;

			query = "UPDATE ad_member_address    SET  ad_city_id=? " +
					"  WHERE ad_member_id=? and LOWER(type)=(?)" ;
			ps = con.prepareStatement(query);

			ps.setInt(1, bean.getCity().getAd_city_id());			
			ps.setInt(2, bean.getAd_member_id());
			ps.setString(3, bean.getType());			

			record=ps.executeUpdate();









		} catch (Exception e) {
			System.out.print("MemberAddressDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}


	//--------------------------------------
	public int getmemberid(int ad_society_no){
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			String query="select ad_member_id from ad_member where ad_society_no=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, ad_society_no);
			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getInt("ad_member_id");
			}

		}catch (Exception e) {
			System.out.print("MemberRegistrationDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return result;
	}


	//------------------------------------------------------------------------------------------	
	public int  getdistrictid(String name, int ad_state_id) {
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select ad_district_id from ad_district where LOWER(district)=LOWER(?) and ad_state_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, name.trim());
			ps.setInt(2, ad_state_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				result=rs.getInt("ad_district_id");

			}
		}catch (Exception e) {
			System.out.print("DistrictDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return result;
	}


	public void addCity(CityBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {

			String query = "INSERT INTO ad_city(" +
					" ad_district_id, ad_state_id, created, createdby," +
					" updated, updatedby, isactive, city ,pincode) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?)" ;

			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_district_id());
			ps.setInt(2, bean.getAd_state_id());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getCreatedby());
			ps.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(6, bean.getUpdatedby());
			ps.setBoolean(7, true);
			ps.setString(8, bean.getCity());
			ps.setInt(9, bean.getPincode());
			record=ps.executeUpdate();

		} catch (Exception e) {
			System.out.print("CityDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}


	}
	//------------------------------
	public void addNomination(NominationBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {

			if(bean.getRelation().getAd_relation_id()!=0){
				String query = "INSERT INTO ad_nomination(" +
						"ad_relation_id,  created, createdby," +
						"updated, updatedby,  isactive, name, " +
						" ad_member_id,nominee_no)" +
						"VALUES ( ?, ?, ?, ?,?, ?, ?, ?, ?)" ;

				ps = con.prepareStatement(query);
				ps.setInt(1, bean.getRelation().getAd_relation_id());
				ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(3, 1);
				ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(5, 1);
				ps.setBoolean(6, true);
				ps.setString(7, bean.getName());
				ps.setInt(8, bean.getAd_member_id());
				ps.setInt(9, bean.getNominee_no());		
				record=ps.executeUpdate();

			}else{

				String query = "INSERT INTO ad_nomination(" +
						"  created, createdby," +
						"updated, updatedby,  isactive, name, " +
						" ad_member_id,nominee_no)" +
						"VALUES ( ?, ?, ?, ?,?, ?, ?, ?)" ;

				ps = con.prepareStatement(query);


				ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(2, 1);
				ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
				ps.setInt(4, 1);
				ps.setBoolean(5, true);
				ps.setString(6, bean.getName());
				ps.setInt(7, bean.getAd_member_id());
				ps.setInt(8, bean.getNominee_no());		
				record=ps.executeUpdate();
			}

		} catch (Exception e) {
			System.out.print("NominationDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}
}
