package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.BulkExcelFileBean;
import Model.Bean.VoucherBean;

public class BulkExcelFileDao {
	private Connection con = null;
	
	public BulkExcelFileDao() {
		con = DBConnection.getConnection();
		
	}
	
	public void addtempExcelPayrol(BulkExcelFileBean bean) {
		int record=0;
		int genid=0;
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_temp_payrol(" +
					"  ad_pf_no, member_name, thrift_amt, loan_amt, " +
					" excess_amt, total)" +
					" VALUES ( ?, ?, ?, ?,?, ?)";

			ps = con.prepareStatement(query);			
			ps.setInt(1,bean.getAd_pf_no());
			ps.setString(2, bean.getMember_name());
			ps.setDouble(3, bean.getThrift_amt());
			ps.setDouble(4, bean.getLoan_amt());
			ps.setDouble(5, bean.getExcess_amt());
			ps.setDouble(6, bean.getTotal());
			
			record=ps.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.print("VoucherDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
            
			DBConnection.close(ps);
			DBConnection.close(con);
        }
		
		
		
	}
//-----------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------
		public ArrayList<BulkExcelFileBean> getTempBulkPayrol() {
			ArrayList<BulkExcelFileBean> bean = new ArrayList<BulkExcelFileBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select atp.*,am.ad_member_id,ad_society_no from ad_temp_payrol atp left join ad_member am on am.ad_pf_no=atp.ad_pf_no ";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					BulkExcelFileBean bean1 = new BulkExcelFileBean();
					bean1.setAd_pf_no(rs.getInt("ad_pf_no"));
					bean1.setAd_temp_payrol_id(rs.getInt("ad_temp_payrol_id"));
					bean1.setMember_name(rs.getString("member_name"));
					bean1.setThrift_amt(rs.getDouble("thrift_amt"));
					bean1.setLoan_amt(rs.getDouble("loan_amt"));
					bean1.setExcess_amt(rs.getDouble("excess_amt"));
					bean1.setTotal(rs.getDouble("total"));
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setAd_society_id(rs.getInt("ad_society_no"));
					
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
	
	
}
