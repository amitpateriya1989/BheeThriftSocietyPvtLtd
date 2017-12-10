package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.FdrediptionBean;

public class FdrediptionDao {

	private Connection con = null;

	public FdrediptionDao() {
		con = DBConnection.getConnection();
	}

	public int addFdrediption(FdrediptionBean bean) {
		int record=0;

		PreparedStatement ps=null;
		try {

			String query = "INSERT INTO ad_fd_rediption( "
					+ " ad_member_id, ad_fd_trx_id, created, createdby, updated, updatedby, fdamt, payroi, " +
					"   maturitydate, payint, totalpayamt, ad_voucher_id, isactive,rediptiontype)"
					+ " VALUES ( ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?,?)" ;

			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_member_id());
			ps.setInt(2, bean.getAd_fd_trx_id());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getCreatedby());
			ps.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(6, bean.getUpdatedby());
			ps.setDouble(7, bean.getFdamt());
			ps.setDouble(8, bean.getPayroi());
			ps.setDate(9, new java.sql.Date(bean.getMaturitydate().getTime()));
			ps.setDouble(10, bean.getPayint());
			ps.setDouble(11, bean.getTotalpayamt());
			ps.setInt(12, bean.getAd_voucher_id());
			ps.setBoolean(13, true);
			ps.setString(14, bean.getRediptiontype());
			record=ps.executeUpdate();


		} catch (Exception e) {
			System.out.print("FdrediptionChkDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return record;
	}
	//-----------------------------------------------------------------------------------------
	public FdrediptionBean getFdrediptionChkById(FdrediptionBean bean) {
		FdrediptionBean bean1 = new FdrediptionBean();
		ResultSet rs=null;
		PreparedStatement ps=null;
		String query = "select * from ad_fd_rediption where ad_fd_rediption_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_fd_rediption_id());
			rs = ps.executeQuery();
			while (rs.next()) {

				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));




			}
		}catch (Exception e) {
			System.out.print("FdrediptionChkDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return bean1;
	}

	//------------------------------------------------------------------------------------------	
	public FdrediptionBean getFdrediptionChkById(int ad_fd_rediption_id) {
		FdrediptionBean bean1 = new FdrediptionBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_fd_rediption where ad_fd_rediption_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_fd_rediption_id);
			rs = ps.executeQuery();
			while (rs.next()) {


			}
		}catch (Exception e) {
			System.out.print("FdrediptionChkDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return bean1;
	}
	//------------------------------------------------------------------------------------------	
	public FdrediptionBean getFdrediptionChkNameById(int ad_fd_rediption_id) {
		FdrediptionBean bean1 = new FdrediptionBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_fd_rediption where ad_fd_rediption_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_fd_rediption_id);
			rs = ps.executeQuery();
			while (rs.next()) {

				bean1.setAd_fd_rediption_id(rs.getInt("ad_fd_rediption_id"));



			}
		}catch (Exception e) {
			System.out.print("FdrediptionChkDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return bean1;
	}
	//--------------------------------------------------------------------------------------
	public ArrayList<FdrediptionBean> getAllFdrediptionChk() {
		ArrayList<FdrediptionBean> bean = new ArrayList<FdrediptionBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_fd_rediption ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				FdrediptionBean bean1 = new FdrediptionBean();

				bean1.setAd_fd_rediption_id(rs.getInt("ad_fd_rediption_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));


				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("FdrediptionChkDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return bean;
	}


	//--------------------------------------------------------------------------------------
	public ArrayList<FdrediptionBean> getAllFdrediptionChkByDistrictId(int ad_district_id) {
		ArrayList<FdrediptionBean> bean = new ArrayList<FdrediptionBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_fd_rediption where ad_district_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_district_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				FdrediptionBean bean1 = new FdrediptionBean();

				bean1.setAd_fd_rediption_id(rs.getInt("ad_fd_rediption_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));


				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("FdrediptionChkDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}

		return bean;
	}
	//-------------------------------------
	public int updateFdrediptionChk(FdrediptionBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "";
			ps = con.prepareStatement(query);



			//System.out.print(ps);
			i=ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("FdrediptionChkDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return i;

	}
	//----------------------------------------------------------------------------------------------
	public int deleteFdrediptionChk(FdrediptionBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_fd_rediption WHERE ad_fd_rediption_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_fd_rediption_id());
			ps.executeUpdate();

		}catch (Exception e) {
			System.out.print("FdrediptionChkDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return i;

	}



}
