package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.LedgerAccountBean;

public class LedgerAccountDao {

	private Connection con = null;

	public LedgerAccountDao() {
			con = DBConnection.getConnection();
	}

	public int addLedgerAccount(LedgerAccountBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_account( " +
					" created, createdby, updated, updatedby, isactive," +
					" ad_ac_group_id, ad_ac_subgroup_id, ac_name, ac_for, ad_ac_type_id," +
					" ac_no, type, address, pincode, phone, mobile, bank_ac_no, ifsc_code," +
					" ad_bank_id, ad_branch_id, tele_phone_no, pan_no, sale_tax_no, cst_no)" +
					" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)" ;
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, true);
			ps.setInt(6, bean.getAd_ac_group_id());
			ps.setInt(7, bean.getAd_ac_subgroup_id());
			ps.setString(8, bean.getAc_name());
			ps.setString(9, bean.getAc_for());
			ps.setInt(10, bean.getAd_ac_type_id());	
			ps.setInt(11, bean.getAc_no());
			ps.setString(12, bean.getType());
			ps.setString(13, bean.getAddress());
			ps.setString(14, bean.getPincode());
			ps.setString(15, bean.getPhone());
			ps.setString(16, bean.getMobile());
			ps.setString(17, bean.getBank_ac_no());
			ps.setString(18, bean.getIfsc_code());
			ps.setInt(19, bean.getAd_bank_id());
			ps.setInt(20, bean.getAd_branch_id());
			ps.setString(21, bean.getTele_phone_no());
			ps.setString(22, bean.getPan_no());
			ps.setString(23, bean.getSale_tax_no());
			ps.setString(24, bean.getCst_no());
			
			record=ps.executeUpdate();
			record=getMaxAccountID();
		} catch (Exception e) {
			System.out.print("LedgerAccountDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return record;
	}
//-----------------------------------------------------------------------------------------
	public LedgerAccountBean getLedgerAccountById(LedgerAccountBean bean) {
		LedgerAccountBean bean1 = new LedgerAccountBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_account where ad_account_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_ac_group_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_ac_group_id(rs.getInt("ad_account_id"));
				bean1.setAd_ac_subgroup_id(rs.getInt("ad_ac_subgroup_id"));
				bean1.setAd_account_id(rs.getInt("ad_account_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setAc_no(rs.getInt("ac_no"));
				bean1.setAc_name(rs.getString("ac_name"));
				bean1.setAc_for(rs.getString("ac_for"));
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
				bean1.setAd_bank_id(rs.getInt("ad_bank_id"));
				bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
				bean1.setAddress(rs.getString("address"));
				bean1.setBank_ac_no(rs.getString("bank_ac_no"));
				bean1.setCst_no(rs.getString("cst_no"));
				bean1.setPan_no(rs.getString("pan_no"));
				bean1.setSale_tax_no(rs.getString("sale_tax_no"));
				bean1.setType(rs.getString("type"));
				bean1.setIfsc_code(rs.getString("ifsc_code"));
				bean1.setMobile(rs.getString("mobile"));
				bean1.setPhone(rs.getString("phone"));
				bean1.setTele_phone_no(rs.getString("tele_phone_no"));
				bean1.setPincode(rs.getString("pincode"));
									

			}
			}catch (Exception e) {
				System.out.print("LedgerAccountDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
	
	
	//-----------------------------------------------------------------------------------------
	public ArrayList<LedgerAccountBean>  getLedgerAccountBySubGroupId(int ad_ac_subgroup_id) {
		ArrayList<LedgerAccountBean> bean = new ArrayList<LedgerAccountBean>();
		
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select ad_account_id,ad_ac_type_id,ac_name from ad_account where ad_ac_subgroup_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_ac_subgroup_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				LedgerAccountBean bean1 = new LedgerAccountBean();
				bean1.setAd_account_id(rs.getInt("ad_account_id"));	
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
				bean1.setAc_name(rs.getString("ac_name"));
				bean.add(bean1);
				

			}
			}catch (Exception e) {
				System.out.print("LedgerAccountDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
	
	//-----------------------------------------------------------------------------------------
		public ArrayList<LedgerAccountBean>  getLedgerAccountByGroupId(int ad_ac_group_id) {
			ArrayList<LedgerAccountBean> bean = new ArrayList<LedgerAccountBean>();
			
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select ad_account_id,ad_ac_type_id from ad_account where ad_ac_group_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_ac_group_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					LedgerAccountBean bean1 = new LedgerAccountBean();
					bean1.setAd_account_id(rs.getInt("ad_account_id"));	
					bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
					
					bean.add(bean1);
					

				}
				}catch (Exception e) {
					System.out.print("LedgerAccountDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}
		
		
		//-----------------------------------------------------------------------------------------
		public ArrayList<LedgerAccountBean> getLedgerAccountByType(String type) {
			ArrayList<LedgerAccountBean> bean = new ArrayList<LedgerAccountBean>();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from ad_account where ac_for=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setString(1, type);
				rs = ps.executeQuery();
				while (rs.next()) {
					LedgerAccountBean bean1 = new LedgerAccountBean();
					bean1.setAd_ac_group_id(rs.getInt("ad_ac_group_id"));
					bean1.setAd_ac_subgroup_id(rs.getInt("ad_ac_subgroup_id"));
					bean1.setAd_account_id(rs.getInt("ad_account_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setAc_no(rs.getInt("ac_no"));
					bean1.setAc_name(rs.getString("ac_name"));
					bean1.setAc_for(rs.getString("ac_for"));
					bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
					bean1.setAd_bank_id(rs.getInt("ad_bank_id"));
					bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
					bean1.setAddress(rs.getString("address"));
					bean1.setBank_ac_no(rs.getString("bank_ac_no"));
					bean1.setCst_no(rs.getString("cst_no"));
					bean1.setPan_no(rs.getString("pan_no"));
					bean1.setSale_tax_no(rs.getString("sale_tax_no"));
					bean1.setType(rs.getString("type"));
					bean1.setIfsc_code(rs.getString("ifsc_code"));
					bean1.setMobile(rs.getString("mobile"));
					bean1.setPhone(rs.getString("phone"));
					bean1.setTele_phone_no(rs.getString("tele_phone_no"));
					bean1.setPincode(rs.getString("pincode"));
					
					bean.add(bean1);
					
					

				}
				}catch (Exception e) {
					System.out.print("LedgerAccountDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}

//------------------------------------------------------------------------------------------	
		public ArrayList<LedgerAccountBean> getLedgerAccountByid(int ad_account_id1,int ad_account_id2,int ad_account_id3) {
			ArrayList<LedgerAccountBean> bean = new ArrayList<LedgerAccountBean>();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from ad_account where ad_account_id=? or ad_account_id=? or ad_account_id=? order by ad_account_id ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_account_id1);
				ps.setInt(2, ad_account_id2);
				ps.setInt(3, ad_account_id3);
				rs = ps.executeQuery();
				while (rs.next()) {
					LedgerAccountBean bean1 = new LedgerAccountBean();
					
					bean1.setAd_account_id(rs.getInt("ad_account_id"));							
					bean1.setAc_no(rs.getInt("ac_no"));
					bean1.setAc_name(rs.getString("ac_name"));
				
					bean.add(bean1);
					
					

				}
				}catch (Exception e) {
					System.out.print("LedgerAccountDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return bean;
			}

		//-----------------------------------------------------------------------------------------
				
				public ArrayList<LedgerAccountBean> getLedgerAccountByid(int ad_account_id1,int ad_account_id2) {
					ArrayList<LedgerAccountBean> bean = new ArrayList<LedgerAccountBean>();
					PreparedStatement ps =null;
					ResultSet rs=null;
					String query = "select * from ad_account where ad_account_id=? or ad_account_id=? order by ad_account_id ";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_account_id1);
						ps.setInt(2, ad_account_id2);
						rs = ps.executeQuery();
						while (rs.next()) {
							LedgerAccountBean bean1 = new LedgerAccountBean();
							
							bean1.setAd_account_id(rs.getInt("ad_account_id"));							
							bean1.setAc_no(rs.getInt("ac_no"));
							bean1.setAc_name(rs.getString("ac_name"));
						
							bean.add(bean1);
							
							

						}
						}catch (Exception e) {
							System.out.print("LedgerAccountDao:-error in try Block");
							e.printStackTrace();
							
						}finally {
							DBConnection.close(rs);
							DBConnection.close(ps);
							DBConnection.close(con);
					    }
						return bean;
					}

		//------------------------------------------------------------------------------------------	
	public LedgerAccountBean getLedgerAccountById(int ad_account_id) {
		LedgerAccountBean bean1 = new LedgerAccountBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_account where ad_account_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_account_id);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_ac_group_id(rs.getInt("ad_ac_group_id"));
				bean1.setAd_ac_subgroup_id(rs.getInt("ad_ac_subgroup_id"));
				bean1.setAd_account_id(rs.getInt("ad_account_id"));
				bean1.setAc_no(rs.getInt("ac_no"));
				bean1.setAc_name(rs.getString("ac_name"));
				bean1.setAc_for(rs.getString("ac_for"));
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
				bean1.setAd_bank_id(rs.getInt("ad_bank_id"));
				bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
				bean1.setAddress(rs.getString("address"));
				bean1.setBank_ac_no(rs.getString("bank_ac_no"));
				bean1.setCst_no(rs.getString("cst_no"));
				bean1.setPan_no(rs.getString("pan_no"));
				bean1.setSale_tax_no(rs.getString("sale_tax_no"));
				bean1.setType(rs.getString("type"));
				bean1.setIfsc_code(rs.getString("ifsc_code"));
				bean1.setMobile(rs.getString("mobile"));
				bean1.setPhone(rs.getString("phone"));
				bean1.setTele_phone_no(rs.getString("tele_phone_no"));
				bean1.setPincode(rs.getString("pincode"));
				
				
				
			}
		}catch (Exception e) {
			System.out.print("LedgerAccountDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
	
	
	//------------------------------------------------------------------------------------------	
	
	
	//------------------------------------------------------------------------------------------	
	public ArrayList<LedgerAccountBean> getAllMemberLedgerAccount() {
		ArrayList<LedgerAccountBean> bean = new ArrayList<LedgerAccountBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_account where ac_for=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, "Member");
			rs = ps.executeQuery();
			while (rs.next()) {
				LedgerAccountBean bean1 = new LedgerAccountBean();
				bean1.setAd_ac_group_id(rs.getInt("ad_ac_group_id"));
				bean1.setAd_ac_subgroup_id(rs.getInt("ad_ac_subgroup_id"));
				bean1.setAd_account_id(rs.getInt("ad_account_id"));
				bean1.setAc_no(rs.getInt("ac_no"));
				bean1.setAc_name(rs.getString("ac_name"));
				bean1.setAc_for(rs.getString("ac_for"));
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
				bean1.setAd_bank_id(rs.getInt("ad_bank_id"));
				bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
				bean1.setAddress(rs.getString("address"));
				bean1.setBank_ac_no(rs.getString("bank_ac_no"));
				bean1.setCst_no(rs.getString("cst_no"));
				bean1.setPan_no(rs.getString("pan_no"));
				bean1.setSale_tax_no(rs.getString("sale_tax_no"));
				bean1.setType(rs.getString("type"));
				bean1.setIfsc_code(rs.getString("ifsc_code"));
				bean1.setMobile(rs.getString("mobile"));
				bean1.setPhone(rs.getString("phone"));
				bean1.setTele_phone_no(rs.getString("tele_phone_no"));
				bean1.setPincode(rs.getString("pincode"));
				
				bean.add(bean1);
				
			}
		}catch (Exception e) {
			System.out.print("LedgerAccountDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	//------------------------------------------------------------------------------------------	
		public ArrayList<LedgerAccountBean> getLedgerAccountType(int ad_ac_type_id) {
			ArrayList<LedgerAccountBean> bean = new ArrayList<LedgerAccountBean>();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from ad_account where ad_ac_type_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_ac_type_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					LedgerAccountBean bean1 = new LedgerAccountBean();
					bean1.setAd_ac_group_id(rs.getInt("ad_ac_group_id"));
					bean1.setAd_ac_subgroup_id(rs.getInt("ad_ac_subgroup_id"));
					bean1.setAd_account_id(rs.getInt("ad_account_id"));
					bean1.setAc_no(rs.getInt("ac_no"));
					bean1.setAc_name(rs.getString("ac_name"));
					bean1.setAc_for(rs.getString("ac_for"));
					bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
					bean1.setAd_bank_id(rs.getInt("ad_bank_id"));
					bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
					bean1.setAddress(rs.getString("address"));
					bean1.setBank_ac_no(rs.getString("bank_ac_no"));
					bean1.setCst_no(rs.getString("cst_no"));
					bean1.setPan_no(rs.getString("pan_no"));
					bean1.setSale_tax_no(rs.getString("sale_tax_no"));
					bean1.setType(rs.getString("type"));
					bean1.setIfsc_code(rs.getString("ifsc_code"));
					bean1.setMobile(rs.getString("mobile"));
					bean1.setPhone(rs.getString("phone"));
					bean1.setTele_phone_no(rs.getString("tele_phone_no"));
					bean1.setPincode(rs.getString("pincode"));
					
					bean.add(bean1);
				}
			}catch (Exception e) {
				System.out.print("LedgerAccountDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
		
	//------------------------------------------------------------------------------------------	
		public LedgerAccountBean getLedgerAccessById(int ad_account_id) {
			LedgerAccountBean bean1 = new LedgerAccountBean();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select ac_for from ad_account where ad_account_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_account_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					
					bean1.setAc_for(rs.getString("ac_for"));
					
					
					
				}
			}catch (Exception e) {
				System.out.print("LedgerAccountDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean1;
		}
		///-----------------
		public int getLedgerMaxLFNoBySubGroup(int ad_ac_subgroup_id) {
			int lfno=0;
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select max(ac_no)  as ac_no from ad_account where ad_ac_subgroup_id=?";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_ac_subgroup_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					
					lfno=rs.getInt("ac_no");
					
					
					
				}
			}catch (Exception e) {
				System.out.print("LedgerAccountDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return lfno;
		}
		//------------------------------------------------------------------------------------------	
				public int getLedgerMaxLFNo() {
					int lfno=0;
					PreparedStatement ps =null;
					ResultSet rs=null;
					String query = "select max(ac_no) as ac_no from ad_account";
					try {
						ps = con.prepareStatement(query);
						
						rs = ps.executeQuery();
						while (rs.next()) {
							
							lfno=rs.getInt("ac_no");
							
							
							
						}
					}catch (Exception e) {
						System.out.print("LedgerAccountDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return lfno;
				}
				
				//------------------------------------------------------------------------------------------	
				public int getMaxAccountID() {
					int id=0;
					PreparedStatement ps =null;
					ResultSet rs=null;
					String query = "select max(ad_account_id) as ad_account_id from ad_account";
					try {
						ps = con.prepareStatement(query);
						
						rs = ps.executeQuery();
						while (rs.next()) {
							
							id=rs.getInt("ad_account_id");
							
							
							
						}
					}catch (Exception e) {
						System.out.print("LedgerAccountDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
				    }
					return id;
				}
//--------------------------------------------------------------------------------------
	public ArrayList<LedgerAccountBean> getAllLedgerAccount() {
		ArrayList<LedgerAccountBean> bean = new ArrayList<LedgerAccountBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_account order by  ad_ac_type_id";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				LedgerAccountBean bean1 = new LedgerAccountBean();
				bean1.setAd_ac_group_id(rs.getInt("ad_ac_group_id"));
				bean1.setAd_ac_subgroup_id(rs.getInt("ad_ac_subgroup_id"));
				bean1.setAd_account_id(rs.getInt("ad_account_id"));
				bean1.setAc_no(rs.getInt("ac_no"));
				bean1.setAc_name(rs.getString("ac_name"));
				bean1.setAc_for(rs.getString("ac_for"));
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));				
				bean1.setAd_bank_id(rs.getInt("ad_bank_id"));
				bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
				bean1.setAddress(rs.getString("address"));
				bean1.setBank_ac_no(rs.getString("bank_ac_no"));
				bean1.setCst_no(rs.getString("cst_no"));
				bean1.setPan_no(rs.getString("pan_no"));
				bean1.setSale_tax_no(rs.getString("sale_tax_no"));
				bean1.setType(rs.getString("type"));
				bean1.setIfsc_code(rs.getString("ifsc_code"));
				bean1.setMobile(rs.getString("mobile"));
				bean1.setPhone(rs.getString("phone"));
				bean1.setTele_phone_no(rs.getString("tele_phone_no"));
				bean1.setPincode(rs.getString("pincode"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("LedgerAccountDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	//--------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------
		public ArrayList<LedgerAccountBean> getAllLedgerAccountName() {
			ArrayList<LedgerAccountBean> bean = new ArrayList<LedgerAccountBean>();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select ad_account_id,ac_no,ac_name from ad_account order by  ad_ac_type_id";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					LedgerAccountBean bean1 = new LedgerAccountBean();
					bean1.setAd_account_id(rs.getInt("ad_account_id"));
					bean1.setAc_no(rs.getInt("ac_no"));
					bean1.setAc_name(rs.getString("ac_name"));
							
					
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("LedgerAccountDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
		
		//--------------------------------------------------------------------------------------
		public ArrayList<LedgerAccountBean> getAllLedgerAccount(int ad_ac_type_id) {
			ArrayList<LedgerAccountBean> bean = new ArrayList<LedgerAccountBean>();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from ad_account where ad_ac_type_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_ac_type_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					LedgerAccountBean bean1 = new LedgerAccountBean();
					bean1.setAd_ac_group_id(rs.getInt("ad_ac_group_id"));
					bean1.setAd_ac_subgroup_id(rs.getInt("ad_ac_subgroup_id"));
					bean1.setAd_account_id(rs.getInt("ad_account_id"));
					bean1.setAc_no(rs.getInt("ac_no"));
					bean1.setAc_name(rs.getString("ac_name"));
					bean1.setAc_for(rs.getString("ac_for"));
					bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
					bean1.setAd_bank_id(rs.getInt("ad_bank_id"));
					bean1.setAd_branch_id(rs.getInt("ad_branch_id"));
					bean1.setAddress(rs.getString("address"));
					bean1.setBank_ac_no(rs.getString("bank_ac_no"));
					bean1.setCst_no(rs.getString("cst_no"));
					bean1.setPan_no(rs.getString("pan_no"));
					bean1.setSale_tax_no(rs.getString("sale_tax_no"));
					bean1.setType(rs.getString("type"));
					bean1.setIfsc_code(rs.getString("ifsc_code"));
					bean1.setMobile(rs.getString("mobile"));
					bean1.setPhone(rs.getString("phone"));
					bean1.setTele_phone_no(rs.getString("tele_phone_no"));
					bean1.setPincode(rs.getString("pincode"));
					
					
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("LedgerAccountDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
//----------------------------------------------------------------------------------------------
	public int updateLedgerAccount(LedgerAccountBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_account" +
					" SET  updated=?, updatedby=?, isactive=?, ad_ac_group_id=?, " +
					"ad_ac_subgroup_id=?, ac_name=?, ac_for=?, ad_ac_type_id=?, ac_no=?, " +
					" type=?, address=?, pincode=?, phone=?, mobile=?, bank_ac_no=?, " +
					" ifsc_code=?, ad_bank_id=?, ad_branch_id=?, tele_phone_no=?, pan_no=?, " +
					" sale_tax_no=?, cst_no=? WHERE ad_account_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setInt(4, bean.getAd_ac_group_id());
			ps.setInt(5, bean.getAd_ac_subgroup_id());
			ps.setString(6, bean.getAc_name());
			ps.setString(7, bean.getAc_for());
			ps.setInt(8, bean.getAd_ac_type_id());
			ps.setInt(9, bean.getAc_no());
			ps.setString(10, bean.getType());
			ps.setString(11, bean.getAddress());
			ps.setString(12, bean.getPincode());
			ps.setString(13, bean.getPhone());
			ps.setString(14, bean.getMobile());
			ps.setString(15, bean.getBank_ac_no());
			ps.setString(16, bean.getIfsc_code());
			ps.setInt(17, bean.getAd_bank_id());
			ps.setInt(18, bean.getAd_branch_id());
			ps.setString(19, bean.getTele_phone_no());
			ps.setString(20, bean.getPan_no());
			ps.setString(21, bean.getSale_tax_no());
			ps.setString(22, bean.getCst_no());
			ps.setInt(23, bean.getAd_account_id());
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("LedgerAccountDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
	//---------------------------------------------------------------------------------------------

		public int updateLedgerAccountbalance(LedgerAccountBean bean){
			int i=0;
			PreparedStatement ps =null;
			try {

				String query = "UPDATE ad_account " +
						"SET  dramt=?, cramt=? " +
						" WHERE ac_no=?";
				ps = con.prepareStatement(query);
				ps.setDouble(1, bean.getDramt());
				ps.setDouble(2, bean.getCramt());
				ps.setInt(3, bean.getAc_no());
				
				
				//System.out.print(ps);
				i=ps.executeUpdate();
			
		}catch (Exception e) {
			System.out.print("LedgerAccountDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}
		
	//----------------------------------------------------------------------------------------------
	
//---------------------------------------------------------------------------------------------

	public int updateLedgerAccountStatus(LedgerAccountBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_account" +
					"SET  updated=?, updatedby=?," +
					"isactive=? WHERE ad_account_id=?";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setInt(4, bean.getAd_ac_group_id());
			
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("LedgerAccountDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
	
//----------------------------------------------------------------------------------------------
	public int deleteLedgerAccount(LedgerAccountBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_account WHERE ad_account_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_account_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("LedgerAccountDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}


}
