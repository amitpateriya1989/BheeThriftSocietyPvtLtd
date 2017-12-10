package Model.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.ThriftIntBean;

public class ThriftIntDao {
	private Connection con = null;

	public ThriftIntDao() {
		con = DBConnection.getConnection();
	}

	public void addThriftInt(ThriftIntBean bean) {
		int record=0;
		PreparedStatement ps=null;

		try {
			con = DBConnection.getConnection();
			String query = "" ;

			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());				



			record=ps.executeUpdate();

		} catch (Exception e) {
			System.out.print("ThriftIntDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}


	public ArrayList<ThriftIntBean> getAllThriftIntByMemberId(int ad_member_id) {
		ArrayList<ThriftIntBean> bean = new ArrayList<ThriftIntBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT * FROM ad_thrift_int left join ad_voucher on ad_voucher.ad_voucher_id=ad_thrift_int.ad_voucher_id  " +
				" where ad_voucher.status='Approved' and ad_thrift_int.ad_member_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,ad_member_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				ThriftIntBean bean1 = new ThriftIntBean();
				bean1.setAd_thrift_int_id(rs.getInt("ad_thrift_int_id"));					
				bean1.setFromdate(rs.getDate("fromdate"));
				bean1.setTodate(rs.getDate("todate"));
				bean1.setTotalthrift_amt(rs.getDouble("totalthrift_amt"));
				bean1.setTotal_intamt(rs.getDouble("total_intamt"));


				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("ThriftIntDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean;
	}
	//----------------------------------------------------------------------------------------------



}
