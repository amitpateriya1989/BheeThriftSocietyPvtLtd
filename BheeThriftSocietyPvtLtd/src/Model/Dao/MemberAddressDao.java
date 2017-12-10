package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.MemberAddressBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.StateBean;

public class MemberAddressDao {

	private Connection con = null;

	public MemberAddressDao() {
					con = DBConnection.getConnection();
			}

	public int addMemberAddress(MemberAddressBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_member_address(" +
					"ad_country_id, ad_state_id, ad_city_id, ad_district_id, ad_member_id," +
					"line1, line2, pincode, mobile, phone, type, created, createdby, " +
					"updated, updatedby, isactive, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getCountry().getAd_country_id());
			ps.setInt(2, bean.getState().getAd_state_id());
			ps.setInt(3, bean.getCity().getAd_city_id());
			ps.setInt(4, bean.getDistrict().getAd_district_id());
			ps.setInt(5, bean.getAd_member_id());
			ps.setString(6, bean.getLine1());
			ps.setString(7, bean.getLine2());
			ps.setString(8, bean.getPincode());
			ps.setString(9, bean.getMobile());
			ps.setString(10, bean.getPhone());
			ps.setString(11, bean.getType());
			ps.setDate(12, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(13, bean.getCreatedby());
			ps.setDate(14, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(15, bean.getUpdatedby());
			ps.setBoolean(16, true);
			ps.setString(17, bean.getEmail());
			
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("MemberAddressDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public MemberAddressBean getMemberAddressById(MemberAddressBean bean) {
		MemberAddressBean bean1 = new MemberAddressBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_member_address where ad_member_address_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_address_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_member_address_id(rs.getInt("ad_member_address_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setCity(new CityDao().getCityById(rs.getInt("ad_city_id")));
				bean1.setDistrict(new DistrictDao().getDistrictById(rs.getInt("ad_district_id")));
				bean1.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
				bean1.setCountry(new CountryDao().getCountryById(rs.getInt("ad_country_id")));
				bean1.setLine1(rs.getString("line1"));
				bean1.setLine2(rs.getString("line2"));
				bean1.setPincode(rs.getString("pincode"));
				bean1.setType(rs.getString("type"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setMobile(rs.getString("mobile"));
				bean1.setPhone(rs.getString("phone"));
				bean1.setEmail(rs.getString("email"));

			}
			}catch (Exception e) {
				System.out.print("MemberAddressDao:-error in try Block");
				e.printStackTrace();
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//-----------------------------------------------------------------------------------------
		public ArrayList<MemberAddressBean> getMemberAddressByMemberId(int ad_member_id) {
			ArrayList<MemberAddressBean> bean = new ArrayList<MemberAddressBean>();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from ad_member_address where ad_member_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_member_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					MemberAddressBean bean1 = new MemberAddressBean();
					bean1.setAd_member_address_id(rs.getInt("ad_member_address_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setCity(new CityDao().getCityById(rs.getInt("ad_city_id")));
					bean1.setDistrict(new DistrictDao().getDistrictById(rs.getInt("ad_district_id")));
					bean1.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
					bean1.setCountry(new CountryDao().getCountryById(rs.getInt("ad_country_id")));
					bean1.setLine1(rs.getString("line1"));
					bean1.setLine2(rs.getString("line2"));
					bean1.setPincode(rs.getString("pincode"));
					bean1.setType(rs.getString("type"));
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setMobile(rs.getString("mobile"));
					bean1.setPhone(rs.getString("phone"));
					bean1.setEmail(rs.getString("email"));
					bean.add(bean1);

				}
				}catch (Exception e) {
					System.out.print("MemberAddressDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}
		
		
		
//------------------------------------------------------------------------------------------	
	public MemberAddressBean getMemberAddressById(int ad_member_address_id) {
		MemberAddressBean bean1 = new MemberAddressBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_member_address where ad_member_address_id=? ";
		try {
			 ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_address_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_member_address_id(rs.getInt("ad_member_address_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setCity(new CityDao().getCityById(rs.getInt("ad_city_id")));
				bean1.setDistrict(new DistrictDao().getDistrictById(rs.getInt("ad_district_id")));
				bean1.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
				bean1.setCountry(new CountryDao().getCountryById(rs.getInt("ad_country_id")));
				bean1.setLine1(rs.getString("line1"));
				bean1.setLine2(rs.getString("line2"));
				bean1.setPincode(rs.getString("pincode"));
				bean1.setType(rs.getString("type"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setMobile(rs.getString("mobile"));
				bean1.setPhone(rs.getString("phone"));
				bean1.setEmail(rs.getString("email"));
			}
		}catch (Exception e) {
			System.out.print("MemberAddressDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	public ArrayList<MemberAddressBean> getAllMemberAddress() {
		ArrayList<MemberAddressBean> bean = new ArrayList<MemberAddressBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_member_address ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberAddressBean bean1 = new MemberAddressBean();
				bean1.setAd_member_address_id(rs.getInt("ad_member_address_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setCity(new CityDao().getCityById(rs.getInt("ad_city_id")));
				bean1.setDistrict(new DistrictDao().getDistrictById(rs.getInt("ad_district_id")));
				bean1.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
				bean1.setCountry(new CountryDao().getCountryById(rs.getInt("ad_country_id")));
				bean1.setLine1(rs.getString("line1"));
				bean1.setLine2(rs.getString("line2"));
				bean1.setPincode(rs.getString("pincode"));
				bean1.setType(rs.getString("type"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setMobile(rs.getString("mobile"));
				bean1.setPhone(rs.getString("phone"));
				bean1.setEmail(rs.getString("email"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("MemberAddressDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public void updateMemberAddress(MemberAddressBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_member_address " +
					"SET  ad_country_id=?, ad_state_id=?, ad_city_id=?, " +
					"ad_district_id=?, ad_member_id=?, line1=?, line2=?, pincode=?, " +
					" mobile=?, phone=?, type=?, updated=?, " +
					"updatedby=?, isactive=?, email=? WHERE ad_member_address_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getCountry().getAd_country_id());
			ps.setInt(2, bean.getState().getAd_state_id());
			ps.setInt(3, bean.getCity().getAd_city_id());
			ps.setInt(4, bean.getDistrict().getAd_district_id());
			ps.setInt(5, bean.getAd_member_id());
			ps.setString(6, bean.getLine1());
			ps.setString(7, bean.getLine2());
			ps.setString(8, bean.getPincode());
			ps.setString(9, bean.getMobile());
			ps.setString(10, bean.getPhone());
			ps.setString(11, bean.getType());
			ps.setDate(12, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(13, bean.getUpdatedby());
			ps.setBoolean(14, true);
			ps.setString(15, bean.getEmail());
			ps.setInt(16, bean.getAd_member_address_id());
			
			
			System.out.println(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("MemberAddressDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
//----------------------------------------------------------------------------------------------
	public void deleteMemberAddress(MemberAddressBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_member_address WHERE ad_member_address_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_address_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("MemberAddressDao:-error in try Block");
			e.printStackTrace();
			}finally {
				
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
	}


}
