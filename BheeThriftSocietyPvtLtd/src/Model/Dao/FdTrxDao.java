package Model.Dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;


import Model.Bean.FdTrxBean;
import Model.Bean.FdViewBean;
import Model.Bean.VoucherBean;


public class FdTrxDao {
	private Connection con = null;

	public FdTrxDao() {

		con = DBConnection.getConnection();

	}

	public int addFdTrx(FdTrxBean bean) {

		int i =0;

		PreparedStatement ps=null;
		try {

			String query = "INSERT INTO ad_fd_trx(created, createdby, updated, updatedby, ad_member_id, " +
					"fd_no, fd_amt, opening_date, ad_fd_roi_id, maturity_date, isactive,remark,ad_voucher_id)    VALUES (?,?, ?, ?, ?, ?, ?, " +
					" ?, ?, ?, ?, ?,?)";

			ps = con.prepareStatement(query);
			ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());			
			ps.setInt(5, bean.getAd_member_id());
			ps.setInt(6, bean.getFd_no());			
			ps.setDouble(7, bean.getFd_amt());						
			ps.setDate(8, new java.sql.Date(bean.getOpening_date().getTime()));			
			ps.setInt(9,bean.getAd_fd_roi_id());
			ps.setDate(10, new java.sql.Date(bean.getMaturity_date().getTime()));				
			ps.setBoolean(11, false);
			ps.setString(12, "Pending");
			ps.setInt(13, bean.getAd_voucher_id());

			ps.executeUpdate();

			/*ResultSet generatedKeys = null;
			 try{
				  generatedKeys = ps.getGeneratedKeys(); 
				  
		            if (generatedKeys.next()) {
				 
		            i=  generatedKeys.getInt(1);
		           System.out.println("result of fd id "+i);
		           }
		            else {
		            	System.out.println("result of fd rs "+generatedKeys);
		                throw new SQLException();
		            }
		        }catch(Exception e){
		        	
		        	e.printStackTrace();
		        	
		        }finally {
		            
					DBConnection.close(generatedKeys);
					
		        }*/
                         i=getFdTrxMaxId();

		} catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return i;
	}
	//-----------------------------------------------------------------------------------------
	public ArrayList<FdTrxBean> getFdTrxByMemId(FdTrxBean bn) {
		ArrayList<FdTrxBean> bean = new ArrayList<FdTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_fd_trx where ad_member_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bn.getAd_member_id());
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				FdTrxBean bean1 = new FdTrxBean();
				bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setFd_no(rs.getInt("fd_no"));
				bean1.setFd_amt(rs.getDouble("fd_amt"));
				bean1.setOpening_date(rs.getDate("opening_date"));
				bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
				bean1.setMaturity_date(rs.getDate("maturity_date"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRemark(rs.getString("remark"));

				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}

	//------------------------------------------------------------------------------------------	
	public ArrayList<FdTrxBean>  getFdTrxByMemId(int ad_member_id) {
		ArrayList<FdTrxBean> bean = new ArrayList<FdTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_fd_trx where ad_member_id=? and remark=? and " +
				"maturity_date<=(select max(open_days) as days from ad_open_days)  ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			ps.setString(2, "OPEN");
			rs = ps.executeQuery();
			while (rs.next()) {
				FdTrxBean bean1 = new FdTrxBean();
				bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setFd_no(rs.getInt("fd_no"));
				bean1.setFd_amt(rs.getDouble("fd_amt"));
				bean1.setOpening_date(rs.getDate("opening_date"));
				bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
				bean1.setMaturity_date(rs.getDate("maturity_date"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean.add(bean1);
			}
		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}
	
	//------------------------------------------------------------------------------------------	
		public ArrayList<FdTrxBean>  getRenewalFdTrxByMemId(int ad_member_id) {
			ArrayList<FdTrxBean> bean = new ArrayList<FdTrxBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_fd_trx where ad_member_id=? and remark=?  ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_member_id);
				ps.setString(2, "OPEN");
				rs = ps.executeQuery();
				while (rs.next()) {
					FdTrxBean bean1 = new FdTrxBean();
					bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setFd_no(rs.getInt("fd_no"));
					bean1.setFd_amt(rs.getDouble("fd_amt"));
					bean1.setOpening_date(rs.getDate("opening_date"));
					bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
					bean1.setMaturity_date(rs.getDate("maturity_date"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean.add(bean1);
				}
			}catch (Exception e) {
				System.out.print("FdTrxDao:-error in try Block");
				e.printStackTrace();

			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return bean;
		}
	//--------------------------------------------------------------------------------------
	public ArrayList<FdTrxBean>  getFdTrxByMemIdFdno(int ad_member_id, int fdno) {
		ArrayList<FdTrxBean> bean = new ArrayList<FdTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_fd_trx where ad_member_id=? and fd_no=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			ps.setInt(2, fdno);
			rs = ps.executeQuery();
			while (rs.next()) {
				FdTrxBean bean1 = new FdTrxBean();
				bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setFd_no(rs.getInt("fd_no"));
				bean1.setFd_amt(rs.getDouble("fd_amt"));
				bean1.setOpening_date(rs.getDate("opening_date"));
				bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
				bean1.setMaturity_date(rs.getDate("maturity_date"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setRemark(rs.getString("remark"));
				bean.add(bean1);
			}
		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}
	//------------------------------------------------------------------------------------------	
	public ArrayList<FdTrxBean>  getAllFdNoByMemId(int ad_member_id) {
		ArrayList<FdTrxBean> bean = new ArrayList<FdTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select fd_no from ad_fd_trx where ad_member_id=? order by fd_no ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			ps.setString(2, "OPEN");
			rs = ps.executeQuery();
			while (rs.next()) {
				FdTrxBean bean1 = new FdTrxBean();									
				bean1.setFd_no(rs.getInt("fd_no"));				
				bean.add(bean1);
			}
		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}
	//--------------------------------------------------------------------------------------
	public FdTrxBean getFdTrxById(int ad_fd_trx_id,Date maturity_date) {
		FdTrxBean bean1 = new FdTrxBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_fd_trx where ad_fd_trx_id=? and remark=? and maturity_date<=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_fd_trx_id);
			ps.setString(2, "OPEN");
			ps.setDate(3,new java.sql.Date(maturity_date.getTime()));
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setFd_no(rs.getInt("fd_no"));
				bean1.setFd_amt(rs.getDouble("fd_amt"));
				bean1.setOpening_date(rs.getDate("opening_date"));
				bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
				bean1.setMaturity_date(rs.getDate("maturity_date"));
				bean1.setIsactive(rs.getBoolean("isactive"));

			}
		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}
	//--------------------------------------------------------------------------------------
	public FdTrxBean getFdTrxById(int ad_fd_trx_id) {
		FdTrxBean bean1 = new FdTrxBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_fd_trx where ad_fd_trx_id=? and remark=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_fd_trx_id);
			ps.setString(2, "OPEN");			
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setFd_no(rs.getInt("fd_no"));
				bean1.setFd_amt(rs.getDouble("fd_amt"));
				bean1.setOpening_date(rs.getDate("opening_date"));
				bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
				bean1.setMaturity_date(rs.getDate("maturity_date"));
				bean1.setIsactive(rs.getBoolean("isactive"));

			}
		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}
	//--------------------------------------------------------------------------------------
	public ArrayList<FdTrxBean> getAllFdTrx() {
		ArrayList<FdTrxBean> bean = new ArrayList<FdTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_fd_trx ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				FdTrxBean bean1 = new FdTrxBean();
				bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setFd_no(rs.getInt("fd_no"));
				bean1.setFd_amt(rs.getDouble("fd_amt"));
				bean1.setOpening_date(rs.getDate("opening_date"));
				bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
				bean1.setMaturity_date(rs.getDate("maturity_date"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				//bean1.setAddress(new MemberAddressDao().getMemberAddressByMemberId(rs.getInt("ad_member_id")));
				bean.add(bean1);
				System.out.print(bean);

			}
		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}


	//--------------------------------------------------------------------------------------
	public ArrayList<FdTrxBean> getAllActiveFdTrx() {
		ArrayList<FdTrxBean> bean = new ArrayList<FdTrxBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * form ad_fd_trx";
		try {
			ps = con.prepareStatement(query);
			System.out.print(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				FdTrxBean bean1 = new FdTrxBean();
				bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setFd_no(rs.getInt("fd_no"));
				bean1.setFd_amt(rs.getDouble("fd_amt"));
				bean1.setOpening_date(rs.getDate("opening_date"));
				bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
				bean1.setMaturity_date(rs.getDate("maturity_date"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean.add(bean1);


			}
		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}	
	//----------------------------------------------------------------------------------------------
	public int updateFdTrx(FdTrxBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_fd_trx   SET updated=?, updatedby=?, remark=? WHERE ad_fd_trx_id=?";

			ps = con.prepareStatement(query);
			ps.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));


			ps.setInt(2, bean.getUpdatedby());			
			ps.setString(3, "CLOSE");
			ps.setInt(4, bean.getAd_fd_trx_id());			






			i=ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return i;
	}

	//----------------------------------------------------------------------------------------------
	public int updateFdVoucher(FdTrxBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "UPDATE ad_fd_trx   SET  remark=?, isactive=?,updated=?,updatedby=? WHERE ad_voucher_id=?";

			ps = con.prepareStatement(query);

			ps.setString(1, bean.getRemark());

			ps.setBoolean(2, bean.isIsactive());	
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setInt(5, bean.getAd_voucher_id());





			i=ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return i;
	}
	//----------------------------------------------------------------------------------------------
	public void deleteFdTrx(FdTrxBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_fd_trx WHERE ad_fd_trx_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_id());
			ps.executeQuery();

		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}
	//----------------------------------------------------------------------------------------------
	public void deleteFdTrxByVoucherID(int ad_voucher_id){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_fd_trx WHERE ad_voucher_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_voucher_id);
			ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}

	//----------------------------------------------------------------------------------------------
	@SuppressWarnings("finally")
	public int getFdTrxMaxId(){
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			String query="select ad_fd_trx_id from ad_fd_trx where ad_fd_trx_id=(select Max(ad_fd_trx_id) from ad_fd_trx)";
			ps=con.prepareStatement(query);

			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getInt("ad_fd_trx_id");
			}

		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return result;
	}




	//----------------------------------------------------------------------------------------------




	//----------------------------------------------------------------------------------------------
	@SuppressWarnings("finally")
	public int getMemberNo(){
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			String query="select max(ad_fd_trx_id) as ad_fd_trx_id from ad_fd_trx where ad_member_id=?";
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getInt("ad_fd_trx_id");
			}

		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return result;


	}

	//----------------------------------------------------------------------------------------------
	@SuppressWarnings("finally")
	public int getMemberNo(int ad_member_id){
		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			String query="select max(ad_fd_trx_id) as ad_fd_trx_id from ad_fd_trx where ad_member_id=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getInt("ad_fd_trx_id");
			}

		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return result;
	}



	/////////////////////////


	public int getMaxFdNo(){

		int result=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "SELECT fd_no  FROM genrate_fd_no";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			if(rs.next()!=false){
				result=rs.getInt("fd_no");
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

		new FdTrxDao().updateFdNo();
		return result;

	}

	//-------------------
	//----------------------------------------------------------------------------------------------
	public void updateFdNo(){

		PreparedStatement ps = null;
		try {

			String query = "UPDATE genrate_fd_no SET fd_no=fd_no+1";
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


	//------------------------------------------------------------------------------------------	
	public ArrayList<FdViewBean>  getAllFdDetail() {
		ArrayList<FdViewBean> bean = new ArrayList<FdViewBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "SELECT * FROM fd_detail where remark=? AND isactive=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1,"OPEN");
			ps.setBoolean(2, true);
			rs = ps.executeQuery();
			while (rs.next()) {
				FdViewBean bean1 = new FdViewBean();									
				bean1.setFd_amt(rs.getDouble("fd_amt"));
				bean1.setFd_category(rs.getString("fd_category"));
				bean1.setFd_name(rs.getString("fd_name"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setMaturity_date(rs.getDate("maturity_date"));
				bean1.setMaturityamt(rs.getDouble("maturityamt"));
				bean1.setInterest_amt(rs.getDouble("interest_amt"));
				bean1.setMember_name(rs.getString("member_name"));
				bean1.setOpening_date(rs.getDate("opening_date"));
				bean1.setPf_no(rs.getInt("pf_no"));
				bean1.setRemark(rs.getString("remark"));
				bean1.setRoi(rs.getDouble("roi"));
				bean1.setSociety_no(rs.getInt("society_no"));
				bean1.setTime_period(rs.getInt("time_period"));
				bean1.setVoucher_status(rs.getString("voucher_status"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
				bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
				bean1.setFrequency(rs.getDouble("frequency"));
				bean1.setAd_fd_category_id(rs.getInt("ad_fd_category_id"));
				bean1.setAd_type_of_fd_id(rs.getInt("ad_type_of_fd_id"));
				bean1.setFd_no(rs.getString("fd_no"));
				bean1.setAd_voucher_id(rs.getInt("ad_voucher_id"));
				bean.add(bean1);
			}
		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}

	//-----------------------------------------------------------------------------------------
	public ArrayList<FdViewBean> getFdDetailByMemId(int ad_member_id) {
		ArrayList<FdViewBean> bean = new  ArrayList<FdViewBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "SELECT *  FROM fd_detail where ad_member_id=? AND remark=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_member_id);
			ps.setString(2, "OPEN");
			rs = ps.executeQuery();
			while (rs.next()) {
				FdViewBean bean1=new FdViewBean();									
				bean1.setFd_amt(rs.getDouble("fd_amt"));
				bean1.setFd_category(rs.getString("fd_category"));
				bean1.setFd_name(rs.getString("fd_name"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setMaturity_date(rs.getDate("maturity_date"));
				bean1.setMaturityamt(rs.getDouble("maturityamt"));
				bean1.setInterest_amt(rs.getDouble("interest_amt"));
				bean1.setMember_name(rs.getString("member_name"));
				bean1.setOpening_date(rs.getDate("opening_date"));
				bean1.setPf_no(rs.getInt("pf_no"));
				bean1.setRemark(rs.getString("remark"));
				bean1.setRoi(rs.getDouble("roi"));
				bean1.setSociety_no(rs.getInt("society_no"));
				bean1.setTime_period(rs.getInt("time_period"));
				bean1.setVoucher_status(rs.getString("voucher_status"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
				bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
				bean1.setAd_fd_category_id(rs.getInt("ad_fd_category_id"));
				bean1.setAd_type_of_fd_id(rs.getInt("ad_type_of_fd_id"));
				bean1.setFrequency(rs.getDouble("frequency"));
				bean1.setFd_no(rs.getString("fd_no"));
				bean.add(bean1);
			}
		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}

	//-----------------------------------------------------------------------------------------
		public ArrayList<FdViewBean> getAllFdDetailByMemId(int ad_member_id) {
			ArrayList<FdViewBean> bean = new  ArrayList<FdViewBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "SELECT *  FROM fd_detail where ad_member_id=?  ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_member_id);
				
				rs = ps.executeQuery();
				while (rs.next()) {
					FdViewBean bean1=new FdViewBean();									
					bean1.setFd_amt(rs.getDouble("fd_amt"));
					bean1.setFd_category(rs.getString("fd_category"));
					bean1.setFd_name(rs.getString("fd_name"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setMaturity_date(rs.getDate("maturity_date"));
					bean1.setMaturityamt(rs.getDouble("maturityamt"));
					bean1.setInterest_amt(rs.getDouble("interest_amt"));
					bean1.setMember_name(rs.getString("member_name"));
					bean1.setOpening_date(rs.getDate("opening_date"));
					bean1.setPf_no(rs.getInt("pf_no"));
					bean1.setRemark(rs.getString("remark"));
					bean1.setRoi(rs.getDouble("roi"));
					bean1.setSociety_no(rs.getInt("society_no"));
					bean1.setTime_period(rs.getInt("time_period"));
					bean1.setVoucher_status(rs.getString("voucher_status"));
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
					bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
					bean1.setAd_fd_category_id(rs.getInt("ad_fd_category_id"));
					bean1.setAd_type_of_fd_id(rs.getInt("ad_type_of_fd_id"));
					bean1.setFrequency(rs.getDouble("frequency"));
					bean1.setFd_no(rs.getString("fd_no"));
					bean.add(bean1);
				}
			}catch (Exception e) {
				System.out.print("FdTrxDao:-error in try Block");
				e.printStackTrace();

			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return bean;
		}

	//----------------------------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------
	public FdViewBean getFdDetailByFDId(int ad_fd_trx_id) {
		FdViewBean bean1 = new FdViewBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "SELECT *  FROM fd_detail where ad_fd_trx_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_fd_trx_id);
			rs = ps.executeQuery();
			while (rs.next()) {

				bean1.setFd_amt(rs.getDouble("fd_amt"));
				bean1.setFd_category(rs.getString("fd_category"));
				bean1.setFd_name(rs.getString("fd_name"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setMaturity_date(rs.getDate("maturity_date"));
				bean1.setMaturityamt(rs.getDouble("maturityamt"));
				bean1.setInterest_amt(rs.getDouble("interest_amt"));
				bean1.setMember_name(rs.getString("member_name"));
				bean1.setOpening_date(rs.getDate("opening_date"));
				bean1.setPf_no(rs.getInt("pf_no"));
				bean1.setRemark(rs.getString("remark"));
				bean1.setRoi(rs.getDouble("roi"));
				bean1.setSociety_no(rs.getInt("society_no"));
				bean1.setTime_period(rs.getInt("time_period"));
				bean1.setVoucher_status(rs.getString("voucher_status"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
				bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
				bean1.setAd_fd_category_id(rs.getInt("ad_fd_category_id"));
				bean1.setAd_type_of_fd_id(rs.getInt("ad_type_of_fd_id"));
				bean1.setFrequency(rs.getDouble("frequency"));
				bean1.setFd_no(rs.getString("fd_no"));
				
			}
		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}

	//-----------------------------------------------------------------------------------------
	public ArrayList<FdViewBean> getFdDetailByDate(Date from,Date to) {
		ArrayList<FdViewBean> bean = new  ArrayList<FdViewBean>(); 
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "SELECT *  FROM fd_detail where opening_date between ? AND ? ";
		try {
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(from.getTime()));
			ps.setDate(2, new java.sql.Date(to.getTime()));
			rs = ps.executeQuery();
			while (rs.next()) {
				FdViewBean bean1 = new FdViewBean();							
				bean1.setFd_amt(rs.getDouble("fd_amt"));
				bean1.setFd_category(rs.getString("fd_category"));
				bean1.setFd_name(rs.getString("fd_name"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setMaturity_date(rs.getDate("maturity_date"));
				bean1.setMaturityamt(rs.getDouble("maturityamt"));
				bean1.setInterest_amt(rs.getDouble("interest_amt"));
				bean1.setMember_name(rs.getString("member_name"));
				bean1.setOpening_date(rs.getDate("opening_date"));
				bean1.setPf_no(rs.getInt("pf_no"));
				bean1.setRemark(rs.getString("remark"));
				bean1.setRoi(rs.getDouble("roi"));
				bean1.setSociety_no(rs.getInt("society_no"));
				bean1.setTime_period(rs.getInt("time_period"));
				bean1.setVoucher_status(rs.getString("voucher_status"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setAd_fd_trx_id(rs.getInt("ad_fd_trx_id"));
				bean1.setAd_fd_roi_id(rs.getInt("ad_fd_roi_id"));
				bean1.setAd_fd_category_id(rs.getInt("ad_fd_category_id"));
				bean1.setAd_type_of_fd_id(rs.getInt("ad_type_of_fd_id"));
				bean1.setFrequency(rs.getDouble("frequency"));
				bean1.setFd_no(rs.getString("fd_no"));
				bean.add(bean1);
			}
		}catch (Exception e) {
			System.out.print("FdTrxDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}

}


