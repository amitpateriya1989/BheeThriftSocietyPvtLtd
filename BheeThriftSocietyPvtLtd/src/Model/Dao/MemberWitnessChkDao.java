package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.MemberWitnessChkBean;

public class MemberWitnessChkDao {

	private Connection con = null;

	public MemberWitnessChkDao() {
		con = DBConnection.getConnection();
	}

	public int addMemberWitnessChkChk(MemberWitnessChkBean bean) {
		int record=0;
		
		PreparedStatement ps=null;
		try {
			
			String query = "INSERT INTO ad_loan_member_witness_chk("
					+ "   created, createdby, updated, updatedby, "
					+ "            chkno, bankname, branchname, isactive,loan_trx_id)"
					+ "    VALUES ( ?, ?, ?, ?,  ?, ?, ?, ?, ?)" ;
					
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.setInt(4, bean.getUpdatedby());
			ps.setInt(5, bean.getChkno());
			ps.setString(6, bean.getBankname());
			ps.setString(7, bean.getBranchname());
			ps.setBoolean(8, true);
			ps.setInt(9, bean.getLoan_trx_id());
			record=ps.executeUpdate();
		
			
		} catch (Exception e) {
			System.out.print("MemberWitnessChkChkDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
		return record;
	}
//-----------------------------------------------------------------------------------------
	public MemberWitnessChkBean getMemberWitnessChkChkById(MemberWitnessChkBean bean) {
		MemberWitnessChkBean bean1 = new MemberWitnessChkBean();
		ResultSet rs=null;
		PreparedStatement ps=null;
		String query = "select * from ad_loan_member_witness_chk where ad_loan_member_witness_chk_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_loan_member_witness_chk_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
			
			}
			}catch (Exception e) {
				System.out.print("MemberWitnessChkChkDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			
			return bean1;
		}

//------------------------------------------------------------------------------------------	
	public MemberWitnessChkBean getMemberWitnessChkChkById(int ad_loan_member_witness_chk_id) {
		MemberWitnessChkBean bean1 = new MemberWitnessChkBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_loan_member_witness_chk where ad_loan_member_witness_chk_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, ad_loan_member_witness_chk_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				
			}
		}catch (Exception e) {
			System.out.print("MemberWitnessChkChkDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
		return bean1;
	}
	//------------------------------------------------------------------------------------------	
		public MemberWitnessChkBean getMemberWitnessChkChkNameById(int ad_loan_member_witness_chk_id) {
			MemberWitnessChkBean bean1 = new MemberWitnessChkBean();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from ad_loan_member_witness_chk where ad_loan_member_witness_chk_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, ad_loan_member_witness_chk_id);
				rs = ps.executeQuery();
				while (rs.next()) {
				
					bean1.setAd_loan_member_witness_chk_id(rs.getInt("ad_loan_member_witness_chk_id"));
				
					
					
				}
			}catch (Exception e) {
				System.out.print("MemberWitnessChkChkDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			
			return bean1;
		}
//--------------------------------------------------------------------------------------
	public ArrayList<MemberWitnessChkBean> getAllMemberWitnessChkChk() {
		ArrayList<MemberWitnessChkBean> bean = new ArrayList<MemberWitnessChkBean>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_loan_member_witness_chk ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberWitnessChkBean bean1 = new MemberWitnessChkBean();
				
				bean1.setAd_loan_member_witness_chk_id(rs.getInt("ad_loan_member_witness_chk_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("created"));
				bean1.setUpdatedby(rs.getInt("createdby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("MemberWitnessChkChkDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		
		return bean;
	}
	
	
	//--------------------------------------------------------------------------------------
		public ArrayList<MemberWitnessChkBean> getAllMemberWitnessChkChkByLoanTrxId(int loan_trx_id) {
			ArrayList<MemberWitnessChkBean> bean = new ArrayList<MemberWitnessChkBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query = "select * from ad_loan_member_witness_chk where loan_trx_id=? ";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, loan_trx_id);
				rs = ps.executeQuery();
				while (rs.next()) {
					MemberWitnessChkBean bean1 = new MemberWitnessChkBean();
					
					bean1.setAd_loan_member_witness_chk_id(rs.getInt("ad_loan_member_witness_chk_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("created"));
					bean1.setUpdatedby(rs.getInt("createdby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setChkno(rs.getInt("chkno"));
					bean1.setBranchname(rs.getString("branchname"));
					bean1.setBankname(rs.getString("bankname"));
					bean1.setLoan_trx_id(rs.getInt("loan_trx_id"));
					
					
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("MemberWitnessChkChkDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			
			return bean;
		}
		//-------------------------------------
	public int updateMemberWitnessChkChk(MemberWitnessChkBean bean){
		int i=0;
		PreparedStatement ps=null;
		try {

			String query = "";
			ps = con.prepareStatement(query);
			
			//System.out.print(ps);
			i=ps.executeUpdate();
		
	}catch (Exception e) {
		System.out.print("MemberWitnessChkChkDao:-error in try Block");
		e.printStackTrace();
		
	}finally {
		
		DBConnection.close(ps);
		DBConnection.close(con);
    }
	return i;
	
}
//----------------------------------------------------------------------------------------------
	public int deleteMemberWitnessChkChk(MemberWitnessChkBean bean){
		int i=0;
		PreparedStatement ps=null;
		try{
			String query="DELETE FROM ad_loan_member_witness_chk WHERE ad_loan_member_witness_chk_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_loan_member_witness_chk_id());
			ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("MemberWitnessChkChkDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return i;
		
	}

	//----------------------------------------------------------------------------------------------
		public int deleteMemberWitnessChk(int loan_trx_id){
			int i=0;
			PreparedStatement ps=null;
			try{
				String query="DELETE FROM ad_loan_member_witness_chk WHERE loan_trx_id=?";
				ps = con.prepareStatement(query);
				ps.setInt(1, loan_trx_id);
				i=ps.executeUpdate();
			
				}catch (Exception e) {
				System.out.print("MemberWitnessChkChkDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return i;
			
		}


}
