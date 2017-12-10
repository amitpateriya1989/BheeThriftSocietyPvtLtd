package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.BankBean;
import Model.Bean.BankBranchBean;
import Model.Bean.BankBranchViewBeen;
import Model.Bean.BankRegionBean;
import Model.Bean.CityBean;
import Model.Bean.DistrictBean;
import Model.Bean.StateBean;

public class BankBranchDao {

	private Connection con = null;

	public BankBranchDao() {
		con = DBConnection.getConnection();
						
	}

	public int addBankBranch(BankBranchBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_branch(" +
					"created, createdby, updated, updatedby, isactive," +
					"branch, ad_bank_id, ad_state_id, ad_district_id, ad_city_id," +
					"ad_bank_region_id, branch_code, ifsc_code, address, pincode," +
					"contact_no, contact_person, email_id, phone_no)" +
					"VALUES ( ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setString(6, bean.getBranch());
			ps.setInt(7, bean.getBank().getAd_bank_id());
			ps.setInt(8, bean.getState().getAd_state_id());
			ps.setInt(9, bean.getDistrict().getAd_district_id());
			ps.setInt(10, bean.getCity().getAd_city_id());
			ps.setInt(11, bean.getBank_region().getAd_bank_region_id());
			ps.setInt(12, bean.getBranch_code());
			ps.setString(13, bean.getIfsc_code());
			ps.setString(14, bean.getAddress());
			ps.setString(15, bean.getPincode());
			ps.setString(16, bean.getContact_no());
			ps.setString(17, bean.getContact_person());
			ps.setString(18, bean.getEmail_id());
			ps.setString(19, bean.getPhone_no());
				
			record=ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.print("BankBranchDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public BankBranchBean getBankBranchById(BankBranchBean bean) {
		BankBranchBean bean1 = new BankBranchBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_branch where ad_branch_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_branch_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setBranch(rs.getString("branch"));
				bean1.setBank(new BankDao().getBankById(rs.getInt("ad_bank_id")));
				bean1.setBank_region(new BankRegionDao().getBankRegionById(rs.getInt("ad_bank_region_id")));
				bean1.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
				bean1.setDistrict(new DistrictDao().getDistrictById(rs.getInt("ad_district_id")));
				bean1.setCity(new CityDao().getCityById(rs.getInt("ad_city_id")));
				bean1.setBranch_code(rs.getInt("branch_code"));
				bean1.setIfsc_code(rs.getString("ifsc_code"));
				bean1.setAddress(rs.getString("address"));
				bean1.setContact_person(rs.getString("contact_person"));
				bean1.setContact_no(rs.getString("contact_no"));
				bean1.setPhone_no(rs.getString("phone_no"));
				bean1.setPincode(rs.getString("pincode"));
				bean1.setEmail_id(rs.getString("email_id"));
				

			}
			}catch (Exception e) {
				System.out.print("BankBranchDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public BankBranchBean getBankBranchById(int ad_branch_id) {
		BankBranchBean bean1 = new BankBranchBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_branch where ad_branch_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_branch_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setBranch(rs.getString("branch"));
				bean1.setBank(new BankDao().getBankById(rs.getInt("ad_bank_id")));
				bean1.setBank_region(new BankRegionDao().getBankRegionById(rs.getInt("ad_bank_region_id")));
				bean1.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
				bean1.setDistrict(new DistrictDao().getDistrictById(rs.getInt("ad_district_id")));
				bean1.setCity(new CityDao().getCityById(rs.getInt("ad_city_id")));
				bean1.setBranch_code(rs.getInt("branch_code"));
				bean1.setIfsc_code(rs.getString("ifsc_code"));
				bean1.setAddress(rs.getString("address"));
				bean1.setContact_person(rs.getString("contact_person"));
				bean1.setContact_no(rs.getString("contact_no"));
				bean1.setPhone_no(rs.getString("phone_no"));
				bean1.setPincode(rs.getString("pincode"));
				bean1.setEmail_id(rs.getString("email_id"));
				bean1.setBank(new BankDao().getBankById(rs.getInt("ad_bank_id")));
			}
		}catch (Exception e) {
			System.out.print("BankBranchDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//--------------------------------------------------------------------------------------
	
	public BankBranchBean getBankBranchBycode1(int code) {
		BankBranchBean bean1 = new BankBranchBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select ad_branch_id from ad_branch where branch_code=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, Integer.toString(code));
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
				
			}
		}catch (Exception e) {
			System.out.print("BankBranchDao:-error in try Block");
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
		public BankBranchBean getBankBranchBycode(int code) {
			BankBranchBean bean1 = new BankBranchBean();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_branch where branch_code=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, code);
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setBranch(rs.getString("branch"));
					bean1.setBank(new BankDao().getBankById(rs.getInt("ad_bank_id")));
					bean1.setBank_region(new BankRegionDao().getBankRegionById(rs.getInt("ad_bank_region_id")));
					bean1.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
					bean1.setDistrict(new DistrictDao().getDistrictById(rs.getInt("ad_district_id")));
					bean1.setCity(new CityDao().getCityById(rs.getInt("ad_city_id")));
					bean1.setBranch_code(rs.getInt("branch_code"));
					bean1.setIfsc_code(rs.getString("ifsc_code"));
					bean1.setAddress(rs.getString("address"));
					bean1.setContact_person(rs.getString("contact_person"));
					bean1.setContact_no(rs.getString("contact_no"));
					bean1.setPhone_no(rs.getString("phone_no"));
					bean1.setPincode(rs.getString("pincode"));
					bean1.setEmail_id(rs.getString("email_id"));
					bean1.setBank(new BankDao().getBankById(rs.getInt("ad_bank_id")));
				}
			}catch (Exception e) {
				System.out.print("BankBranchDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	//--------------------------------------------------------------------------------------
	public ArrayList<BankBranchBean> getAllBankBranch() {
		ArrayList<BankBranchBean> bean = new ArrayList<BankBranchBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_branch ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				BankBranchBean bean1 = new BankBranchBean();
				bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setBranch(rs.getString("branch"));
				bean1.setBank(new BankDao().getBankById(rs.getInt("ad_bank_id")));
				bean1.setBank_region(new BankRegionDao().getBankRegionById(rs.getInt("ad_bank_region_id")));
				bean1.setState(new StateDao().getStateById(rs.getInt("ad_state_id")));
				bean1.setDistrict(new DistrictDao().getDistrictById(rs.getInt("ad_district_id")));
				bean1.setCity(new CityDao().getCityById(rs.getInt("ad_city_id")));
				bean1.setBranch_code(rs.getInt("branch_code"));
				bean1.setIfsc_code(rs.getString("ifsc_code"));
				bean1.setAddress(rs.getString("address"));
				bean1.setContact_person(rs.getString("contact_person"));
				bean1.setContact_no(rs.getString("contact_no"));
				bean1.setPhone_no(rs.getString("phone_no"));
				bean1.setPincode(rs.getString("pincode"));
				bean1.setEmail_id(rs.getString("email_id"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("BankBranchDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	public ArrayList<BankBranchBean> getAllBankBranchName() {
		ArrayList<BankBranchBean> bean = new ArrayList<BankBranchBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select ad_branch_id,branch,branch_code from ad_branch ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				BankBranchBean bean1 = new BankBranchBean();
				bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
				
				bean1.setBranch(rs.getString("branch"));
				
				bean1.setBranch_code(rs.getInt("branch_code"));
				
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("BankBranchDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
//----------------------------------------------------------------------------------------------
	public int updateBankBranch(BankBranchBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_branch " +
					"SET updated=?, updatedby=?,isactive=?, branch=?, ad_bank_id=?," +
					"ad_state_id=?, ad_district_id=?,ad_city_id=?, ad_bank_region_id=?," +
					"branch_code=?, ifsc_code=?,address=?, pincode=?, contact_no=?, " +
					"contact_person=?, email_id=?,phone_no=? WHERE ad_branch_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, true);
			ps.setString(4, bean.getBranch());
			ps.setInt(5, bean.getBank().getAd_bank_id());
			ps.setInt(6, bean.getState().getAd_state_id());
			ps.setInt(7, bean.getDistrict().getAd_district_id());
			ps.setInt(8, bean.getCity().getAd_city_id());
			ps.setInt(9, bean.getBank_region().getAd_bank_region_id());
			ps.setInt(10, bean.getBranch_code());
			ps.setString(11, bean.getIfsc_code());
			ps.setString(12, bean.getAddress());
			ps.setString(13, bean.getPincode());
			ps.setString(14, bean.getContact_no());
			ps.setString(15, bean.getContact_person());
			ps.setString(16, bean.getEmail_id());
			ps.setString(17, bean.getPhone_no());
			ps.setInt(18, bean.getAd_branch_id());
			
			
			System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("BankBranchDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
	//--------------------------------------------------------------------------------------
	public BankBranchViewBeen getBankBranchView(int id) {
		BankBranchViewBeen bean1 = new BankBranchViewBeen();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			//System.out.println("ID = "+ id);
			if(id != 0){
				String query = "select * from branch_detail where ad_branch_id=?";
				ps = con.prepareStatement(query);
				ps.setInt(1, id);
			}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_state_id(rs.getInt("ad_state_id"));
				bean1.setState(rs.getString("state"));
				bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
				bean1.setBranch(rs.getString("branch"));
				bean1.setAd_district_id(rs.getInt("ad_district_id"));
				bean1.setDistrict(rs.getString("district"));
				bean1.setAd_city_id(rs.getInt("ad_city_id"));
				bean1.setCity(rs.getString("city"));
				bean1.setAd_bank_id(rs.getInt("ad_bank_id"));
				bean1.setBank(rs.getString("bank"));
				bean1.setAd_bank_region_id(rs.getInt("ad_bank_region_id"));
				bean1.setRegion(rs.getString("region"));
				bean1.setBranch_code(rs.getInt("branch_code"));
				bean1.setContact_no(rs.getString("contact_no"));
				bean1.setEmail_id(rs.getString("email_id"));
				bean1.setAddress(rs.getString("address"));
				bean1.setIfsc_code(rs.getString("ifsc_code"));
				bean1.setContact_person(rs.getString("contact_person"));
				bean1.setPhone_no(rs.getString("phone_no"));
				bean1.setPincode(rs.getString("pincode"));
				bean1.setFax_no(rs.getString("fax_no"));
				bean1.setIsactive(rs.getBoolean("isactive"));

			}
		}catch (Exception e) {
			System.out.print("BankBranchDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
	
	//------------------------------------------------------------------------------------
	
	public ArrayList<BankBranchViewBeen> getBankBranchView() {
		ArrayList<BankBranchViewBeen> bean = new ArrayList<BankBranchViewBeen>();
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			String query = "select * from branch_detail";
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				BankBranchViewBeen bean1 = new BankBranchViewBeen();
				bean1.setAd_state_id(rs.getInt("ad_state_id"));
				bean1.setState(rs.getString("state"));
				bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
				bean1.setBranch(rs.getString("branch"));
				bean1.setAd_district_id(rs.getInt("ad_district_id"));
				bean1.setDistrict(rs.getString("district"));
				bean1.setAd_city_id(rs.getInt("ad_city_id"));
				bean1.setCity(rs.getString("city"));
				bean1.setAd_bank_id(rs.getInt("ad_bank_id"));
				bean1.setBank(rs.getString("bank"));
				bean1.setAd_bank_region_id(rs.getInt("ad_bank_region_id"));
				bean1.setRegion(rs.getString("region"));
				bean1.setBranch_code(rs.getInt("branch_code"));
				bean1.setContact_no(rs.getString("contact_no"));
				bean1.setEmail_id(rs.getString("email_id"));
				bean1.setAddress(rs.getString("address"));
				bean1.setIfsc_code(rs.getString("ifsc_code"));
				bean1.setContact_person(rs.getString("contact_person"));
				bean1.setPhone_no(rs.getString("phone_no"));
				bean1.setPincode(rs.getString("pincode"));
				bean1.setFax_no(rs.getString("fax_no"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean.add(bean1);
					
			}
		}catch (Exception e) {
			System.out.print("BankBranchDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	
	//--------------------------------------------------------------------------------------
			public ArrayList<BankBranchBean> getAllBankBranchByBankId(int ad_bank_id) {
				ArrayList<BankBranchBean> bean = new ArrayList<BankBranchBean>();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "select * from ad_branch where ad_bank_id=? order by branch";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_bank_id);
					rs = ps.executeQuery();
					while (rs.next()) {
						BankBranchBean bean1 = new BankBranchBean();
						bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
						bean1.setBranch_code(rs.getInt("branch_code"));
						bean1.setBranch(rs.getString("branch"));						
						bean.add(bean1);

					}
				}catch (Exception e) {
					System.out.print("BankBranchDao:-error in try Block");
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
			public BankBranchBean getBranchNameById(int ad_branch_id) {
				BankBranchBean bean1 = new BankBranchBean();
				PreparedStatement ps=null;
				ResultSet rs=null;
				String query = "select * from ad_branch where ad_branch_id=? ";
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, ad_branch_id);
					rs = ps.executeQuery();
					while (rs.next()) {
						bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
						
						bean1.setBranch(rs.getString("branch"));
						
					}
				}catch (Exception e) {
					System.out.print("BankBranchDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean1;
			}
			
//----------------------------------------------------------------------------------------------
	public void deleteBankBranch(BankBranchBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_branch WHERE ad_branch_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_branch_id());
			ps.executeQuery();
		
			}catch (Exception e) {
			System.out.print("BankBranchDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}


}
