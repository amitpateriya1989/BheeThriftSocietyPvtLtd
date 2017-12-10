package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import Model.Bean.VoucherBean;
import Model.Bean.VoucherTrxDetailBean;

public class VoucherDao {
	
	private Connection con = null;

	public VoucherDao() {
		con = DBConnection.getConnection();
		
	}

	public VoucherBean addVoucher(VoucherBean bean) {
		int record=0;
		int genid=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_voucher(" +
					"created, createdby, updated, updatedby, isactive," +
					"trx_no, status, trx_date, vtype, vfrom, vamt, description, instrument_from," +
					"instrument_date, instrument_no,voucher_type,instrument_amt,amt_in_words)" +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
			ps = con.prepareStatement(query,ps.RETURN_GENERATED_KEYS);
			
			ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, bean.isIsactive());
			ps.setInt(6,bean.getTrx_no());
			ps.setString(7, bean.getStatus());
			ps.setDate(8, new java.sql.Date(bean.getTrx_date().getTime()));
			ps.setString(9, bean.getVtype());
			ps.setString(10, bean.getVfrom());
			ps.setDouble(11, bean.getVamt());
			ps.setString(12, bean.getDescription());
			ps.setString(13, bean.getInstrument_from());
			ps.setDate(14,new java.sql.Date(bean.getInstrument_date().getTime()));
			ps.setString(15, bean.getInstrument_no());
			ps.setString(16, bean.getVoucher_type());
			ps.setDouble(17, bean.getInstrument_amt());
			ps.setString(18, bean.getAmt_in_words());
			record=ps.executeUpdate();
			ResultSet generatedKeys = null;
			 try{
				  generatedKeys = ps.getGeneratedKeys(); 
		            if (generatedKeys.next()) {
		           genid=  generatedKeys.getInt(1);
		     //     System.out.println()
		            }
		            else {
		                throw new SQLException("Creating voucher failed, no ID obtained.");
		            }
		        }catch(Exception e){
		        	e.printStackTrace();
		        	
		        }finally {
		            
					DBConnection.close(generatedKeys);
					
		        }
			
		} catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
            
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		VoucherBean bean1 =new VoucherBean();
		bean1.setAd_voucher_id(genid);
		return bean1;
	}
//-----------------------------------------------------------------------------------------
	public VoucherBean addLoanVoucher(VoucherBean bean) {
		int record=0;
		int genid=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_voucher(" +
					"created, createdby, updated, updatedby, isactive," +
					"trx_no, status, trx_date, vtype, vfrom, vamt, description,voucher_type,amt_in_words)" +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
			ps = con.prepareStatement(query,ps.RETURN_GENERATED_KEYS);
			
			ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setBoolean(5, bean.isIsactive());
			ps.setInt(6,bean.getTrx_no());
			ps.setString(7, bean.getStatus());
			ps.setDate(8, new java.sql.Date(bean.getTrx_date().getTime()));
			ps.setString(9, bean.getVtype());
			ps.setString(10, bean.getVfrom());
			ps.setDouble(11, bean.getVamt());
			ps.setString(12, bean.getDescription());			
			ps.setString(13, bean.getVoucher_type());
			ps.setString(14, bean.getAmt_in_words());
			record=ps.executeUpdate();
			ResultSet generatedKeys = null;
			 try{
				  generatedKeys = ps.getGeneratedKeys(); 
		            if (generatedKeys.next()) {
		           genid=  generatedKeys.getInt(1);
		     //     System.out.println()
		            }
		            else {
		                throw new SQLException("Creating voucher failed, no ID obtained.");
		            }
		        }catch(Exception e){
		        	e.printStackTrace();
		        	
		        }finally {
		            
					DBConnection.close(generatedKeys);
					
		        }
			
		} catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
            
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		VoucherBean bean1 =new VoucherBean();
		bean1.setAd_voucher_id(genid);
		return bean1;
	}
//-----------------------------------------------------------------------------------------
	public VoucherBean getVoucherById(VoucherBean bean) {
		VoucherBean bean1 = new VoucherBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_voucher where ad_voucher_id=? and isactive=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_voucher_id());
			ps.setBoolean(2,bean.isIsactive());
			rs = ps.executeQuery();
			while (rs.next()) {
				
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDescription(isNull(rs.getString("description")));
				bean1.setInstrument_date(rs.getDate("instrument_date"));
				bean1.setInstrument_from(isNull(rs.getString("instrument_from")));
				bean1.setInstrument_no(isNull(rs.getString("instrument_no")));
				bean1.setInstrument_amt(rs.getDouble("instrument_amt"));
				bean1.setStatus(isNull(rs.getString("status")));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setVamt(rs.getDouble("vamt"));
				bean1.setVfrom(isNull(rs.getString("vfrom")));
				bean1.setVno(isNull(rs.getString("vno")));
				bean1.setTrx_no(rs.getInt("trx_no"));
				bean1.setVtype(isNull(rs.getString("vtype")));
				bean1.setVoucher_type(isNull(rs.getString("voucher_type")));
				bean1.setAmt_in_words(rs.getString("amt_in_words"));
			}
			}catch (Exception e) {
				System.out.print("VoucherDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
	        }
			return bean1;
		}
	//-----------------------------------------------------------------------------------------
		public VoucherBean getmanualVoucherById(VoucherBean bean) {
			VoucherBean bean1 = new VoucherBean();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_voucher where ad_voucher_id=?  ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, bean.getAd_voucher_id());
			
				rs = ps.executeQuery();
				while (rs.next()) {
					
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
					bean1.setDescription(rs.getString("description"));
					bean1.setInstrument_date(rs.getDate("instrument_date"));
					bean1.setInstrument_from(rs.getString("instrument_from"));
					bean1.setInstrument_no(rs.getString("instrument_no"));
					bean1.setInstrument_amt(rs.getDouble("instrument_amt"));
					bean1.setStatus(rs.getString("status"));
					bean1.setTrx_date(rs.getDate("trx_date"));
					bean1.setVamt(rs.getDouble("vamt"));
					bean1.setVfrom(rs.getString("vfrom"));
					bean1.setVno(rs.getString("vno"));
					bean1.setTrx_no(rs.getInt("trx_no"));
					bean1.setVtype(rs.getString("vtype"));
					bean1.setVoucher_type(rs.getString("voucher_type"));
					bean1.setAmt_in_words(rs.getString("amt_in_words"));
					
				}
				}catch (Exception e) {
					System.out.print("VoucherDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
		        }
				return bean1;
			}
		
		//-----------------------------------------------------------------------------------------
				public VoucherBean getmanualVoucherById(int ad_voucher_id) {
					VoucherBean bean1 = null;
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from ad_voucher where ad_voucher_id=?  ";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_voucher_id);
					
						rs = ps.executeQuery();
						while (rs.next()) {
							bean1 = new VoucherBean();
							bean1.setCreated(rs.getDate("created"));
							bean1.setCreatedby(rs.getInt("createdby"));
							bean1.setUpdated(rs.getDate("updated"));
							bean1.setUpdatedby(rs.getInt("updatedby"));
							bean1.setIsactive(rs.getBoolean("isactive"));
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
							bean1.setDescription(rs.getString("description"));
							bean1.setInstrument_date(rs.getDate("instrument_date"));
							bean1.setInstrument_from(rs.getString("instrument_from"));
							bean1.setInstrument_no(rs.getString("instrument_no"));
							bean1.setInstrument_amt(rs.getDouble("instrument_amt"));
							bean1.setStatus(rs.getString("status"));
							bean1.setTrx_date(rs.getDate("trx_date"));
							bean1.setVamt(rs.getDouble("vamt"));
							bean1.setVfrom(rs.getString("vfrom"));
							bean1.setVno(rs.getString("vno"));
							bean1.setTrx_no(rs.getInt("trx_no"));
							bean1.setVtype(rs.getString("vtype"));
							bean1.setVoucher_type(rs.getString("voucher_type"));
							bean1.setAmt_in_words(rs.getString("amt_in_words"));
							
						}
						}catch (Exception e) {
							System.out.print("VoucherDao:-error in try Block");
							e.printStackTrace();
							
						}finally {
							DBConnection.close(rs);
							DBConnection.close(ps);
							DBConnection.close(con);
				        }
						return bean1;
					}
	//-----------------------------------------------------------------------------------------
		public int getMaxVoucherId() {
			int ad_voucher_id =0;
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select Max(ad_voucher_id) as ad_voucher_id from ad_voucher";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					
					
					ad_voucher_id=rs.getInt("ad_voucher_id");
					
					
				}
				}catch (Exception e) {
					System.out.print("VoucherDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
		        }
				return ad_voucher_id;
			}
		//-----------------------------------------------------------------------------------------
				public int getMaxPendingVoucherId() {
					int ad_voucher_id =0;
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select Max(ad_voucher_id) as ad_voucher_id from ad_voucher where status=?";
					try {
						ps = con.prepareStatement(query);
						ps.setString(1, "Pending");
						rs = ps.executeQuery();
						while (rs.next()) {
							
							
							ad_voucher_id=rs.getInt("ad_voucher_id");
							
							
						}
						}catch (Exception e) {
							System.out.print("VoucherDao:-error in try Block");
							e.printStackTrace();
							
						}finally {
							DBConnection.close(rs);
							DBConnection.close(ps);
							DBConnection.close(con);
				        }
						return ad_voucher_id;
					}

//------------------------------------------------------------------------------------------	
	public VoucherBean getVoucherById(int ad_voucher_id,boolean isactive) {
		VoucherBean bean1 = new VoucherBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_voucher where ad_voucher_id=? and isactive=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			ps.setBoolean(2, isactive);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDescription(isNull(rs.getString("description")));
				bean1.setInstrument_date(rs.getDate("instrument_date"));
				bean1.setInstrument_from(isNull(rs.getString("instrument_from")));
				bean1.setInstrument_no(isNull(rs.getString("instrument_no")));
				bean1.setInstrument_amt(rs.getDouble("instrument_amt"));
				bean1.setStatus(isNull(rs.getString("status")));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setVamt(rs.getDouble("vamt"));
				bean1.setVfrom(isNull(rs.getString("vfrom")));
				bean1.setVno(isNull(rs.getString("vno")));
				bean1.setTrx_no(rs.getInt("trx_no"));
				bean1.setVtype(isNull(rs.getString("vtype")));
				bean1.setVoucher_type(isNull(rs.getString("voucher_type")));
				bean1.setAmt_in_words(rs.getString("amt_in_words"));
			}
		}catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		return bean1;
	}
	
	
	

	//------------------------------------------------------------------------------------------	
		public VoucherBean getVoucherByVoucherId(int ad_voucher_id) {
			VoucherBean bean1 = new VoucherBean();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_voucher where ad_voucher_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_voucher_id);
			//	ps.setString(2, "Pending");
				//System.out.println(ps);
				rs = ps.executeQuery();
				while (rs.next()) {
					bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setDescription(isNull(rs.getString("description")));
					bean1.setInstrument_date(rs.getDate("instrument_date"));
					bean1.setInstrument_from(rs.getString("instrument_from"));
					bean1.setInstrument_no(isNull(rs.getString("instrument_no")));
					bean1.setInstrument_amt(rs.getDouble("instrument_amt"));
					bean1.setStatus(isNull(rs.getString("status")));
					bean1.setTrx_date(rs.getDate("trx_date"));
					bean1.setVamt(rs.getDouble("vamt"));
					bean1.setVfrom(isNull(rs.getString("vfrom")));
					bean1.setVno(isNull(rs.getString("vno")));
					bean1.setTrx_no(rs.getInt("trx_no"));
					bean1.setVtype(isNull(rs.getString("vtype")));
					bean1.setVoucher_type(isNull(rs.getString("voucher_type")));
					bean1.setAmt_in_words(rs.getString("amt_in_words"));
				}
			}catch (Exception e) {
				System.out.print("VoucherDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
	        }
			return bean1;
			
		}
//--------------------------------------------------------------------------------------
		
				public int countVoucherByVoucherIdStatus(String status , int ad_voucher_id) {
					int result=0;
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select count(*) as cnt from ad_voucher where ad_voucher_id=? and status=?";
					try {
						ps = con.prepareStatement(query);
						ps.setInt(1, ad_voucher_id);
						ps.setString(2, status);
						rs = ps.executeQuery();
						while (rs.next()) {
							result=rs.getInt("cnt");

						}
					}catch (Exception e) {
						System.out.print("VoucherDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
			        }
					return result;
					
				}
		//--------------------------------------------------------------------------------------
	public ArrayList<VoucherBean> getAllVoucher() {
		ArrayList<VoucherBean> bean = new ArrayList<VoucherBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_voucher order by trx_no desc";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				VoucherBean bean1 = new VoucherBean();
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setDescription(isNull(rs.getString("description")));
				bean1.setInstrument_date(rs.getDate("instrument_date"));
				bean1.setInstrument_from(isNull(rs.getString("instrument_from")));
				bean1.setInstrument_no(isNull(rs.getString("instrument_no")));
				bean1.setInstrument_amt(rs.getDouble("instrument_amt"));
				bean1.setStatus(isNull(rs.getString("status")));
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setVamt(rs.getDouble("vamt"));
				bean1.setVfrom(isNull(rs.getString("vfrom")));
				bean1.setVno(isNull(rs.getString("vno")));
				bean1.setTrx_no(rs.getInt("trx_no"));
				bean1.setVtype(isNull(rs.getString("vtype")));
				bean1.setVoucher_type(isNull(rs.getString("voucher_type")));
				bean1.setAmt_in_words(rs.getString("amt_in_words"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		return bean;
	}
	
	

	//--------------------------------------------------------------------------------------
		public ArrayList<VoucherBean> getAllPendingVoucher() {
			ArrayList<VoucherBean> bean = new ArrayList<VoucherBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_voucher where status=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setString(1, "Pending");
				rs = ps.executeQuery();
				while (rs.next()) {
					VoucherBean bean1 = new VoucherBean();
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
					bean1.setDescription(rs.getString("description"));
					bean1.setInstrument_date(rs.getDate("instrument_date"));
					bean1.setInstrument_from(rs.getString("instrument_from"));
					bean1.setInstrument_no(rs.getString("instrument_no"));
					bean1.setInstrument_amt(rs.getDouble("instrument_amt"));
					bean1.setStatus(rs.getString("status"));
					bean1.setTrx_date(rs.getDate("trx_date"));
					bean1.setVamt(rs.getDouble("vamt"));
					bean1.setVfrom(rs.getString("vfrom"));
					bean1.setVno(rs.getString("vno"));
					bean1.setTrx_no(rs.getInt("trx_no"));
					bean1.setVtype(rs.getString("vtype"));
					bean1.setVoucher_type(rs.getString("voucher_type"));
					bean1.setAmt_in_words(rs.getString("amt_in_words"));
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("VoucherDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
	        }
			return bean;
		}
		
		
		//--------------------------------------------------------------------------------------
				public ArrayList<VoucherBean> getAllPendingVoucherForDay(Date d) {
					ArrayList<VoucherBean> bean = new ArrayList<VoucherBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from ad_voucher where status=? and trx_date=?";
					try {
						ps = con.prepareStatement(query);
						ps.setString(1, "Pending");
						ps.setDate(2,new java.sql.Date(d.getTime()));
						rs = ps.executeQuery();
						while (rs.next()) {
							VoucherBean bean1 = new VoucherBean();
							bean1.setCreated(rs.getDate("created"));
							bean1.setCreatedby(rs.getInt("createdby"));
							bean1.setUpdated(rs.getDate("updated"));
							bean1.setUpdatedby(rs.getInt("updatedby"));
							bean1.setIsactive(rs.getBoolean("isactive"));
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
							bean1.setDescription(rs.getString("description"));
							bean1.setInstrument_date(rs.getDate("instrument_date"));
							bean1.setInstrument_from(rs.getString("instrument_from"));
							bean1.setInstrument_no(rs.getString("instrument_no"));
							bean1.setInstrument_amt(rs.getDouble("instrument_amt"));
							bean1.setStatus(rs.getString("status"));
							bean1.setTrx_date(rs.getDate("trx_date"));
							bean1.setVamt(rs.getDouble("vamt"));
							bean1.setVfrom(rs.getString("vfrom"));
							bean1.setVno(rs.getString("vno"));
							bean1.setTrx_no(rs.getInt("trx_no"));
							bean1.setVtype(rs.getString("vtype"));
							bean1.setVoucher_type(rs.getString("voucher_type"));
							bean1.setAmt_in_words(rs.getString("amt_in_words"));
							bean.add(bean1);

						}
					}catch (Exception e) {
						System.out.print("VoucherDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
			        }
					return bean;
				}
				
		//--------------------------------------------------------------------------------------
				public ArrayList<VoucherBean> getAllVoucherByType(String status) {
					ArrayList<VoucherBean> bean = new ArrayList<VoucherBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from ad_voucher where status=? and description=? ";
					try {
						ps = con.prepareStatement(query);
						ps.setString(1, "Pending");
						ps.setString(2, status.trim());
						System.out.println(ps);
						rs = ps.executeQuery();
						while (rs.next()) {
							VoucherBean bean1 = new VoucherBean();
							bean1.setCreated(rs.getDate("created"));
							bean1.setCreatedby(rs.getInt("createdby"));
							bean1.setUpdated(rs.getDate("updated"));
							bean1.setUpdatedby(rs.getInt("updatedby"));
							bean1.setIsactive(rs.getBoolean("isactive"));
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
							bean1.setDescription(rs.getString("description"));
							bean1.setInstrument_date(rs.getDate("instrument_date"));
							bean1.setInstrument_from(rs.getString("instrument_from"));
							bean1.setInstrument_no(rs.getString("instrument_no"));
							bean1.setInstrument_amt(rs.getDouble("instrument_amt"));
							bean1.setStatus(rs.getString("status"));
							bean1.setTrx_date(rs.getDate("trx_date"));
							bean1.setVamt(rs.getDouble("vamt"));
							bean1.setVfrom(rs.getString("vfrom"));
							bean1.setVno(rs.getString("vno"));
							bean1.setTrx_no(rs.getInt("trx_no"));
							bean1.setVtype(rs.getString("vtype"));
							bean1.setVoucher_type(rs.getString("voucher_type"));
							bean1.setAmt_in_words(rs.getString("amt_in_words"));
							bean.add(bean1);

						}
					}catch (Exception e) {
						System.out.print("VoucherDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
			        }
					return bean;
				}
		
		//--------------------------------------------------------------------------------------
				
				public int getCountsharepending(String status) {
					
					PreparedStatement ps=null;
					ResultSet rs=null;
					int count=0;
					String query = "select count(*) cnt from ad_voucher where status=? and description=? ";
					try {
						ps = con.prepareStatement(query);
						ps.setString(1, "Pending");
						ps.setString(2, status.trim());
						rs = ps.executeQuery();
						while (rs.next()) {
							
							count=rs.getInt("cnt");
						}
					}catch (Exception e) {
						System.out.print("VoucherDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
			        }
					return count;
				}
		
		//--------------------------------------------------------------------------------------
				public ArrayList<VoucherBean> getAllApprovedVoucher() {
					ArrayList<VoucherBean> bean = new ArrayList<VoucherBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from ad_voucher where status=? ";
					try {
						ps = con.prepareStatement(query);
						ps.setString(1, "Approved");
						rs = ps.executeQuery();
						while (rs.next()) {
							VoucherBean bean1 = new VoucherBean();
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
							bean1.setCreated(rs.getDate("created"));
							bean1.setCreatedby(rs.getInt("createdby"));
							bean1.setUpdated(rs.getDate("updated"));
							bean1.setUpdatedby(rs.getInt("updatedby"));
							bean1.setIsactive(rs.getBoolean("isactive"));
							bean1.setDescription(isNull(rs.getString("description")));
							bean1.setInstrument_date(rs.getDate("instrument_date"));
							bean1.setInstrument_from(isNull(rs.getString("instrument_from")));
							bean1.setInstrument_no(isNull(rs.getString("instrument_no")));
							bean1.setInstrument_amt(rs.getDouble("instrument_amt"));
							bean1.setStatus(isNull(rs.getString("status")));
							bean1.setTrx_date(rs.getDate("trx_date"));
							bean1.setVamt(rs.getDouble("vamt"));
							bean1.setVfrom(isNull(rs.getString("vfrom")));
							bean1.setVno(isNull(rs.getString("vno")));
							bean1.setTrx_no(rs.getInt("trx_no"));
							bean1.setVtype(isNull(rs.getString("vtype")));
							bean1.setVoucher_type(isNull(rs.getString("voucher_type")));
							bean1.setAmt_in_words(rs.getString("amt_in_words"));
							bean.add(bean1);

						}
					}catch (Exception e) {
						System.out.print("VoucherDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
			        }
					return bean;
				}	
//----------------------------------------------------------------------------------------------
				//--------------------------------------------------------------------------------------
				public ArrayList<VoucherBean> getAllVoucherByDate(Date fdate,Date tdate) {
					ArrayList<VoucherBean> bean = new ArrayList<VoucherBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from ad_voucher where trx_date between ? and ? ";
					try {
						ps = con.prepareStatement(query);
						ps.setDate(1, new java.sql.Date(fdate.getTime()));
						ps.setDate(2, new java.sql.Date(tdate.getTime()));
						rs = ps.executeQuery();
						while (rs.next()) {
							VoucherBean bean1 = new VoucherBean();
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
							bean1.setDescription(isNull(rs.getString("description")));
							bean1.setInstrument_date(rs.getDate("instrument_date"));
							bean1.setInstrument_from(isNull(rs.getString("instrument_from")));
							bean1.setInstrument_no(isNull(rs.getString("instrument_no")));
							bean1.setInstrument_amt(rs.getDouble("instrument_amt"));
							bean1.setStatus(isNull(rs.getString("status")));
							bean1.setTrx_date(rs.getDate("trx_date"));
							bean1.setVamt(rs.getDouble("vamt"));
							bean1.setVfrom(isNull(rs.getString("vfrom")));
							bean1.setVno(isNull(rs.getString("vno")));
							bean1.setTrx_no(rs.getInt("trx_no"));
							bean1.setVtype(isNull(rs.getString("vtype")));
							bean1.setVoucher_type(isNull(rs.getString("voucher_type")));
							bean1.setAmt_in_words(rs.getString("amt_in_words"));
							bean.add(bean1);

						}
					}catch (Exception e) {
						System.out.print("VoucherDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
			        }
					return bean;
				}	
				
				
				
				public ArrayList<VoucherBean> getAllVoucherList() {
					ArrayList<VoucherBean> bean = new ArrayList<VoucherBean>();
					PreparedStatement ps=null;
					ResultSet rs=null;
					String query = "select * from ad_voucher order by trx_date desc ";
					try {
						ps = con.prepareStatement(query);
						
						rs = ps.executeQuery();
						while (rs.next()) {
							VoucherBean bean1 = new VoucherBean();
							bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
							bean1.setDescription(isNull(rs.getString("description")));
							bean1.setInstrument_date(rs.getDate("instrument_date"));
							bean1.setInstrument_from(isNull(rs.getString("instrument_from")));
							bean1.setInstrument_no(isNull(rs.getString("instrument_no")));
							bean1.setInstrument_amt(rs.getDouble("instrument_amt"));
							bean1.setStatus(isNull(rs.getString("status")));
							bean1.setTrx_date(rs.getDate("trx_date"));
							bean1.setVamt(rs.getDouble("vamt"));
							bean1.setVfrom(isNull(rs.getString("vfrom")));
							bean1.setVno(isNull(rs.getString("vno")));
							bean1.setTrx_no(rs.getInt("trx_no"));
							bean1.setVtype(isNull(rs.getString("vtype")));
							bean1.setVoucher_type(isNull(rs.getString("voucher_type")));
							bean1.setAmt_in_words(rs.getString("amt_in_words"));
							bean.add(bean1);

						}
					}catch (Exception e) {
						System.out.print("VoucherDao:-error in try Block");
						e.printStackTrace();
						
					}finally {
						DBConnection.close(rs);
						DBConnection.close(ps);
						DBConnection.close(con);
			        }
					return bean;
				}	
//----------------------------------------------------------------------------------------------
	public void updatedVoucher(VoucherBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_voucher " +
					"SET updated=?, updatedby=?, " +
					"isactive=?, vno=?, status=?, trx_date=?, vtype=?, vfrom=?, vamt=?, " +
					"description=?, instrument_from=?, instrument_date=?, instrument_no=?,voucher_type=?,instrument_amt=? " +
					"WHERE ad_voucher_id=?";
			ps = con.prepareStatement(query);
			ps.setTimestamp(1, new java.sql.Timestamp(bean.getUpdated().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setBoolean(3, bean.isIsactive());
			ps.setString(4,bean.getVno());
			ps.setString(5, bean.getStatus());
			ps.setDate(6, new java.sql.Date(bean.getTrx_date().getTime()));
			ps.setString(7, bean.getVtype());
			ps.setString(8, bean.getVfrom());
			ps.setDouble(9, bean.getVamt());
			ps.setString(10, bean.getDescription());
			ps.setString(11, bean.getInstrument_from());
			ps.setString(12, bean.getInstrument_no());
			ps.setString(13, bean.getVoucher_type());
			ps.setDouble(14, bean.getInstrument_amt());
			ps.setDate(15,new java.sql.Date(bean.getInstrument_date().getTime()));
			ps.setInt(16, bean.getAd_voucher_id());
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("VoucherDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	
}
	//------------------------
	//----------------------------------------------------------------------------------------------
		public int updatedVoucher1(VoucherBean bean){
			int i=0;
			PreparedStatement ps = null;
			try {

				String query = "UPDATE ad_voucher " +
						" SET updated=?, updatedby=?,  vtype=?,  vamt=?," +
						 " instrument_from=?, instrument_date=?, instrument_no=?,voucher_type=?, " +
						 " instrument_amt=? ,trx_no=?, vfrom=?, trx_date=?,description=?, status=?" +
						 " WHERE ad_voucher_id=? ";
				ps = con.prepareStatement(query);
				ps.setTimestamp(1, new java.sql.Timestamp(bean.getUpdated().getTime()));
				ps.setInt(2, bean.getUpdatedby());				
				ps.setString(3, bean.getVtype());
				ps.setDouble(4, bean.getVamt());				
				ps.setString(5, bean.getInstrument_from());
				ps.setDate(6,new java.sql.Date(bean.getInstrument_date().getTime()));
				ps.setString(7, bean.getInstrument_no());
				ps.setString(8, bean.getVoucher_type());
				ps.setDouble(9, bean.getInstrument_amt());
				ps.setInt(10, bean.getTrx_no());
				ps.setString(11,bean.getVfrom());
				ps.setDate(12, new java.sql.Date(bean.getTrx_date().getTime()));
				ps.setString(13,bean.getDescription());
				ps.setString(14, bean.getStatus());
				ps.setInt(15, bean.getAd_voucher_id());
				
				
				
				
				//System.out.print(ps);
				i=ps.executeUpdate();
			
		}catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
	}
		//------------------------
	
	public int updatedVoucherForCheque(VoucherBean bean){
		int i=0;
		PreparedStatement ps = null;
		try {

			String query = "UPDATE ad_voucher " +
					"SET updated=?, updatedby=?," +
					" vtype=?, vfrom=? ," +
					" instrument_from=?, instrument_date=?, instrument_no=?,instrument_amt=? " +
					" WHERE ad_voucher_id=?";
			ps = con.prepareStatement(query);
			ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(2, bean.getUpdatedby());
			ps.setString(3, bean.getVtype());
			ps.setString(4, bean.getVfrom());
			ps.setString(5, bean.getInstrument_from());
			ps.setDate(6,new java.sql.Date(bean.getInstrument_date().getTime()));
			ps.setString(7, bean.getInstrument_no());
			ps.setDouble(8, bean.getInstrument_amt());
			ps.setInt(9, bean.getAd_voucher_id());
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("VoucherDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
}
	
	//----------------------------------------------------------------------------------------------
		public void VoucherApprovel(VoucherBean bean){
			int i=0;
			PreparedStatement ps = null;
			try {

				String query = "UPDATE ad_voucher " +
						"SET updated=?, updatedby=?," +
						" status=?, vno=? " +
						"WHERE ad_voucher_id=?";
				ps = con.prepareStatement(query);
				ps.setTimestamp(1, new java.sql.Timestamp(bean.getUpdated().getTime()));
				ps.setInt(2, bean.getUpdatedby());
			
				ps.setString(3, bean.getStatus());
				ps.setString(4, bean.getVno());
				
				ps.setInt(5,bean.getAd_voucher_id());
				
				//System.out.print(ps);
				i=ps.executeUpdate();
			
		}catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
		//=====================
		public void interestVoucherupdate(int ad_voucher_id, double amt){
			int i=0;
			PreparedStatement ps = null;
			try {

				String query = "UPDATE ad_voucher " +
						"SET vamt=? "+
						" WHERE ad_voucher_id=?";
				ps = con.prepareStatement(query);
				ps.setDouble(1, amt);
				ps.setInt(2, ad_voucher_id);
			
				
				//System.out.print(ps);
				i=ps.executeUpdate();
			
		}catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
	}
		
		//=====================
				public void updateVoucherAmt(int ad_voucher_id, double amt){
					int i=0;
					PreparedStatement ps = null;
					try {

						String query = "UPDATE ad_voucher " +
								"SET vamt=? "+
								" WHERE ad_voucher_id=?";
						ps = con.prepareStatement(query);
						ps.setDouble(1, amt);
						ps.setInt(2, ad_voucher_id);
					
						
						//System.out.print(ps);
						i=ps.executeUpdate();
					
				}catch (Exception e) {
					System.out.print("VoucherDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				
			}
//----------------------------------------------------------------------------------------------
	public int deleteVoucher(VoucherBean bean){
		int i=0;
		PreparedStatement ps =null;
		try{
			String query="DELETE FROM ad_voucher WHERE ad_voucher_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_voucher_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
		
	}
//----------------------------------------------------------------------------------------------
	public int getMaxVoucherNo(Date d){
		VoucherBean bean1 = new VoucherBean();
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
	
		new VoucherDao().genVoucherUpdateForDay(d);
		
			
		String query = "SELECT voucher_no  FROM generate_voucher_no";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()!=false){
				result=rs.getInt("voucher_no");
				System.out.println(result);
			}
		}catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		return result;
		
	}

	//----------------------------------------------------------------------------------------------
	public int getMaxtrxNo(){
		VoucherBean bean1 = new VoucherBean();
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "SELECT Max(trx_no) as trx_no  FROM ad_voucher ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()!=false){
				result=rs.getInt("trx_no");
				result++;
				//System.out.println(result);
			}
		}catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		
		new VoucherDao().updateTrxNo();
		return result;
		
	}

	//----------------------------------------------------------------------------------------------
		public void genVoucherUpdateForDay(Date d){
			VoucherBean bean1 = new VoucherBean();
			Date vdate=null;
			Date cdate=d;
		
			SimpleDateFormat sdf=new SimpleDateFormat("ddMMyy");
			String c2=sdf.format(cdate);
			
			PreparedStatement ps=null;
			ResultSet rs=null;
				
			String query = "SELECT *  FROM generate_voucher_no";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				
				if(rs.next()!=false){
					vdate=rs.getDate("voucher_date");
				}
				//Date d2=sdf.parse(c2);
				
				System.out.print(vdate+" , "+c2 +"outer" );
				if(vdate.toString().trim().equals(c2.trim())){					
				System.out.print(vdate+" , "+c2 +"inner");					
				}else{
					String sdate=c2+"28TR/";
					
				
					
					PreparedStatement ps2 = null;
					ResultSet rs2=null;
					int cnt=0;
					String query2="SELECT count(*) as cnt  FROM ad_voucher Where  vno like ?";
				
					
					ps2 = con.prepareStatement(query2);
					ps2.setString(1,sdate+"%");
					
					rs2=ps2.executeQuery();
					if(rs2.next()!=false){
						 cnt=rs2.getInt("cnt");
						 System.out.println(rs2.getInt("cnt"));
					}
					System.out.println("cnt "+cnt);
					if(cnt>0){
						PreparedStatement ps1 = null;
						String query1="UPDATE generate_voucher_no   SET  voucher_no=?, voucher_date=?";
						ps1 = con.prepareStatement(query1);
						ps1.setInt(1, cnt+1);
						ps1.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
						ps1.executeUpdate();
						
						
					}else{
					
						PreparedStatement ps1 = null;
						String query1="UPDATE generate_voucher_no   SET  voucher_no=?, voucher_date=?";
						ps1 = con.prepareStatement(query1);
						ps1.setInt(1, 1);
						ps1.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
						ps1.executeUpdate();
					}
					
				}
				
				
			}catch (Exception e) {
				System.out.print("VoucherDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
	        }
			
			
		}


	//----------------------------------------------------------------------------------------------
		public void updateTrxNo(){
			
			PreparedStatement ps = null;
			try {

				String query = "UPDATE generate_trx_no SET trx_no=trx_no+1";
				ps = con.prepareStatement(query);
			
				//System.out.print(ps);
				ps.executeUpdate();
			
		}catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	  }
		
	}
//----------------------------------------------------------------------------------------------
	public void updateVoucherNo(){
		
		PreparedStatement ps = null;
		try {

			String query = "UPDATE generate_voucher_no SET voucher_no=voucher_no+1";
			ps = con.prepareStatement(query);
		
			//System.out.print(ps);
			ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("VoucherDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
  }
	
}
	public String isNull(String string){
		if(string!=null){
			return string;
		}else{
			return "";
		}
	}
	
	
	
	public ArrayList<VoucherTrxDetailBean> getAllVoucherTrxDetail() {
		ArrayList<VoucherTrxDetailBean> bean = new ArrayList<VoucherTrxDetailBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "SELECT trx_date, ad_member_id, member_name, ad_society_no, dramt, cramt," +
				" narration, trx_status, trx_remark, ad_employee_id, emp_name," +
				" vno, voucher_status, vtype, vfrom, vamt, description, instrument_from," +
				" instrument_no, instrument_date, voucher_type, voucher_trx_no," +
				" ad_trx_id, ad_account_id, ac_name, ac_for, ac_no, ad_voucher_id," +
				" ad_ac_type_id, ac_type_name FROM voucher_trx_detail ";
		try {
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				VoucherTrxDetailBean bean1 = new VoucherTrxDetailBean();
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setMember_name(rs.getString("member_name"));
				bean1.setAd_society_no(rs.getInt("ad_society_no"));
				bean1.setDramt(rs.getDouble("dramt"));
				bean1.setCramt(rs.getDouble("cramt"));
				bean1.setNarration(rs.getString("narration"));
				bean1.setTrx_status(rs.getString("trx_status"));
				bean1.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean1.setEmp_name(rs.getString("emp_name"));
				bean1.setVno(rs.getString("vno"));
				bean1.setVoucher_status(rs.getString("voucher_status"));
				bean1.setVtype(rs.getString("vtype"));
				bean1.setVfrom(rs.getString("vfrom"));
				bean1.setVamt(rs.getDouble("vamt"));
				bean1.setDescription(rs.getString("description"));
				bean1.setInstrument_from(rs.getString("instrument_from"));
				bean1.setInstrument_date(rs.getDate("instrument_date"));
				bean1.setInstrument_from(rs.getString("instrument_from"));
				bean1.setInstrument_no(rs.getString("instrument_no"));
				bean1.setVoucher_type(rs.getString("voucher_type"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setVoucher_trx_no(rs.getInt("voucher_trx_no"));
				bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
				bean1.setAd_account_id(rs.getInt("ad_account_id"));
				bean1.setAc_name(rs.getString("ac_name"));
				bean1.setAc_for(rs.getString("ac_for"));
				bean1.setAc_no(rs.getString("ac_no"));
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
				bean1.setAc_type_name(rs.getString("ac_type_name"));
				
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		return bean;
	}	
	
	
	public ArrayList<VoucherTrxDetailBean> getAllVoucherTrxDetail(int ad_voucher_id) {
		ArrayList<VoucherTrxDetailBean> bean = new ArrayList<VoucherTrxDetailBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "SELECT trx_date, ad_member_id, member_name, ad_society_no, dramt, cramt, narration, "
				+ " trx_status, trx_remark, ad_employee_id, emp_name, vno, voucher_status, vtype, vfrom, "
				+ " vamt, description, instrument_from, instrument_no, instrument_date, voucher_type, "
				+ " voucher_trx_no, ad_trx_id, ad_account_id, ac_name, ac_for, ac_no, ad_voucher_id, "
				+ " ad_ac_type_id, ac_type_name FROM voucher_trx_detail where ad_voucher_id=? order by ad_trx_id,dramt desc";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				VoucherTrxDetailBean bean1 = new VoucherTrxDetailBean();
				bean1.setTrx_date(rs.getDate("trx_date"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setMember_name(rs.getString("member_name"));
				bean1.setAd_society_no(rs.getInt("ad_society_no"));
				bean1.setDramt(rs.getDouble("dramt"));
				bean1.setCramt(rs.getDouble("cramt"));
				bean1.setNarration(rs.getString("narration"));
				bean1.setTrx_status(rs.getString("trx_status"));
				bean1.setAd_employee_id(rs.getInt("ad_employee_id"));
				bean1.setEmp_name(rs.getString("emp_name"));
				bean1.setVno(rs.getString("vno"));
				bean1.setVoucher_status(rs.getString("voucher_status"));
				bean1.setVtype(rs.getString("vtype"));
				bean1.setVfrom(rs.getString("vfrom"));
				bean1.setVamt(rs.getDouble("vamt"));
				bean1.setDescription(rs.getString("description"));
				bean1.setInstrument_from(rs.getString("instrument_from"));
				bean1.setInstrument_date(rs.getDate("instrument_date"));
				bean1.setInstrument_from(rs.getString("instrument_from"));
				bean1.setInstrument_no(rs.getString("instrument_no"));
				bean1.setVoucher_type(rs.getString("voucher_type"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean1.setVoucher_trx_no(rs.getInt("voucher_trx_no"));
				bean1.setAd_trx_id(rs.getInt("ad_trx_id"));
				bean1.setAd_account_id(rs.getInt("ad_account_id"));
				bean1.setAc_name(rs.getString("ac_name"));
				bean1.setAc_for(rs.getString("ac_for"));
				bean1.setAc_no(rs.getString("ac_no"));
				bean1.setAd_ac_type_id(rs.getInt("ad_ac_type_id"));
				bean1.setAc_type_name(rs.getString("ac_type_name"));
				
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		return bean;
	}	
	
	
}