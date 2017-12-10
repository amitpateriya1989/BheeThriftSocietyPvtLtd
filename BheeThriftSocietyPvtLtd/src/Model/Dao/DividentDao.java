package Model.Dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hslf.blip.DIB;

import Model.Bean.CastBean;
import Model.Bean.DividendMasterBean;
import Model.Bean.DividentBean;
import Model.Bean.MemberRegistrationBean;
import Model.Bean.MemberShareBean;
import Model.Bean.ShareBean;
import Model.Bean.TransactionBean;

public class DividentDao {
	private Connection con = null;

	public DividentDao() {
		con = DBConnection.getConnection();
		}

	public int addDivident(DividentBean bean,Date current_date) {
		int record=0;
		PreparedStatement ps=null;
		Date d3=current_date;
		long diff=0,dy=0;
		double d=0.0,dd=0.0,dvd=0.0,s=0.0;
		ShareBean shbean=new ShareDao().getShareRate();
		DividendMasterBean divBean=new DividendMasterDao().getDividendMasterBeanByYear(bean.getDiv_year());
		ArrayList<MemberRegistrationBean> memberlist=new MemberRegistrationDao().getAllMemberForDividend();
		if(memberlist.isEmpty()!=true){
			for(MemberRegistrationBean member: memberlist){
			String rc=member.getAcc_closing_reason();
			if(member.getAd_member_status_id()==2)
			{
				if(rc.equals("Retired"))
					d3=member.getAcc_closing_reason_date();
				else if(rc.equals("Death"))
					d3=member.getAcc_closing_reason_date();
			}
			
			ArrayList<MemberShareBean> sharelist=new MemberShareDao().getShareBalByMemberId(member.getAd_member_id(),divBean.getYear_from());
			if(sharelist.isEmpty()!=true){
				for(MemberShareBean share:sharelist){
					s=share.getShare_amt();
					if(member.getAd_member_status_id()==2 && d3.compareTo(divBean.getYear_to())<0)
					{
						diff=d3.getTime()-divBean.getYear_from().getTime();
						dy=diff/(1000*60*60*24);
						dd=dy;
						//dd=dd+1.0;
						d=(s*divBean.getAd_dividend_per()*dd)/36500;
					}else
					{
						d=s/100;
						d=d*divBean.getAd_dividend_per();
					}
				}
			}
			sharelist=new MemberShareDao().getShareBalByMemberIdAndDate(member.getAd_member_id(),divBean.getYear_from(),divBean.getYear_to());
			if(sharelist.isEmpty()!=true){
				for(MemberShareBean sharebal:sharelist){
					if(member.getAd_member_status_id()!=1)
					{
					
						d3=divBean.getYear_to();
					}
					diff=d3.getTime()-sharebal.getDate_of_allocation().getTime();
					dy=diff/(1000*60*60*24);
					dd=dy;
					dvd=(sharebal.getShare_amt()*divBean.getAd_dividend_per()*dd)/36500;
					s=s+sharebal.getShare_amt();
					d=d+dvd;
				
				}
			}
			if(d>0){
				
				try {
					con = DBConnection.getConnection();
					String query = "INSERT INTO ad_divident( created, createdby, updated, updatedby, isactive," +
							" fromdate, todate, ad_member_id, totalshare_amt, total_intamt, total_amt, " +
							" share_qty, conv_amt, pay_status,  div_year,roi) " +
							" VALUES ( ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?,?)" ;
							
					ps = con.prepareStatement(query);
					ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
					ps.setInt(2, bean.getCreatedby());
					ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
					ps.setInt(4, bean.getUpdatedby());				
					ps.setBoolean(5, true);
					ps.setDate(6, new java.sql.Date(divBean.getYear_from().getTime()));
					ps.setDate(7, new java.sql.Date(divBean.getYear_to().getTime()));
					ps.setInt(8, member.getAd_member_id());
					ps.setDouble(9, s);
					ps.setDouble(10, d);
					ps.setDouble(11, s+d);
					ps.setDouble(12, s/shbean.getPer_share_rate());
					ps.setDouble(13, divBean.getAd_convence_amt());
					ps.setString(14, "Pending");
					ps.setString(15, divBean.getYear());
					ps.setDouble(16, divBean.getAd_dividend_per());
					
					System.out.println(ps);	
					
					record=ps.executeUpdate();
					 
				} catch (Exception e) {
					System.out.print("DividentDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				
			}
			}
		}
		
	      return record;
	}
	
	
	
	public int addMemberDivident(DividentBean bean,Date current_date) {
		int record=0;
		PreparedStatement ps=null;
		Date d3=current_date;
		long diff=0,dy=0;
		double d=0.0,dd=0.0,dvd=0.0,s=0.0;
		ShareBean shbean=new ShareDao().getShareRate();
		DividendMasterBean divBean=new DividendMasterDao().getDividendMasterBeanByYear(bean.getDiv_year());
		
		MemberRegistrationBean member=new MemberRegistrationDao().getMemberForDividend(bean.getAd_member_id());
		if(member!=null){
			
			String rc=member.getAcc_closing_reason();
			if(member.getAd_member_status_id()==2)
			{
				if(rc.equals("Retired"))
					d3=member.getAcc_closing_reason_date();
				else if(rc.equals("Death"))
					d3=member.getAcc_closing_reason_date();
			}
			
			ArrayList<MemberShareBean> sharelist=new MemberShareDao().getShareBalByMemberId(member.getAd_member_id(),divBean.getYear_from());
			if(sharelist.isEmpty()!=true){
				for(MemberShareBean share:sharelist){
					s=share.getShare_amt();
					if(member.getAd_member_status_id()==2 && d3.compareTo(divBean.getYear_to())<0)
					{  
						
						diff=d3.getTime()-divBean.getYear_from().getTime();
						dy=diff/(1000*60*60*24);
						dd=dy;
						//dd=dd+1.0;
						d=(s*divBean.getAd_dividend_per()*dd)/36500;
					}else
					{
						System.out.println("intrest rate="+divBean.getAd_dividend_per());
						d=s/100;
						d=d*divBean.getAd_dividend_per();
					}
				}
			}
			sharelist=new MemberShareDao().getShareBalByMemberIdAndDate(member.getAd_member_id(),divBean.getYear_from(),divBean.getYear_to());
			if(sharelist.isEmpty()!=true){
				for(MemberShareBean sharebal:sharelist){
					if(member.getAd_member_status_id()!=2)
					{
						
						d3=divBean.getYear_to();
					}
					
					diff=d3.getTime()-sharebal.getDate_of_allocation().getTime();
					dy=diff/(1000*60*60*24);
					dd=dy;
					dvd=(sharebal.getShare_amt()*divBean.getAd_dividend_per()*dd)/36500;
					s=s+sharebal.getShare_amt();
					d=d+dvd;
				
				}
			}
				
				if(d>0){
				try {
					con = DBConnection.getConnection();
					String query = "INSERT INTO ad_divident( created, createdby, updated, updatedby, isactive," +
							" fromdate, todate, ad_member_id, totalshare_amt, total_intamt, total_amt, " +
							" share_qty, conv_amt, pay_status,  div_year,roi) " +
							" VALUES ( ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?,?)" ;
							
					ps = con.prepareStatement(query);
					ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
					ps.setInt(2, bean.getCreatedby());
					ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
					ps.setInt(4, bean.getUpdatedby());				
					ps.setBoolean(5, true);
					ps.setDate(6, new java.sql.Date(divBean.getYear_from().getTime()));
					ps.setDate(7, new java.sql.Date(divBean.getYear_to().getTime()));
					ps.setInt(8, member.getAd_member_id());
					ps.setDouble(9, s);
					ps.setDouble(10, d);
					ps.setDouble(11, s+d);
					ps.setDouble(12, s/shbean.getPer_share_rate());
					ps.setDouble(13, divBean.getAd_convence_amt());
					ps.setString(14, "Pending");
					ps.setString(15, divBean.getYear());
					ps.setDouble(16, divBean.getAd_dividend_per());
					
					System.out.println(ps);	
					
					record=ps.executeUpdate();
					 
				} catch (Exception e) {
					System.out.print("DividentDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
			}
				
			
		}
		
		
	      return record;
	}

	
	
	
	public static long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	
	
	public ArrayList<DividentBean> getAllDividentByMemberId(int ad_member_id) {
		ArrayList<DividentBean> bean = new ArrayList<DividentBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT * FROM ad_divident " +
				" left join ad_voucher on ad_voucher.ad_voucher_id=ad_divident.ad_voucher_id  " +
				" where  ad_divident.ad_member_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,ad_member_id);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				DividentBean bean1 = new DividentBean();
				bean1.setAd_divident_id(rs.getInt("ad_divident_id"));					
				bean1.setFromdate(rs.getDate("fromdate"));
				bean1.setTodate(rs.getDate("todate"));
				bean1.setTotalshare_amt(rs.getDouble("totalshare_amt"));
				bean1.setTotal_intamt(rs.getDouble("total_intamt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setConv_amt(rs.getDouble("conv_amt"));
				bean1.setDiv_year(rs.getString("div_year"));
				bean1.setPay_status(rs.getString("pay_status"));
				bean1.setShare_qty(rs.getDouble("share_qty"));
				bean1.setDiscription(rs.getString("discription"));
				bean1.setInstrument_date(rs.getDate("instrument_date"));
				bean1.setInstrument_no(rs.getInt("instrument_no"));
				bean1.setInstrument_type(rs.getString("instrument_type"));
				bean1.setRoi(rs.getDouble("roi"));
				bean1.setTotal_amt(rs.getDouble("total_amt"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("DividentDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	
//-------------------------------------------------------------------------------------------------
	public ArrayList<DividentBean> getAllDividentByMemberIdAndYear(int ad_member_id,String year) {
		ArrayList<DividentBean> bean = new ArrayList<DividentBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT * FROM ad_divident " +
				" left join ad_voucher on ad_voucher.ad_voucher_id=ad_divident.ad_voucher_id  " +
				" where  ad_divident.ad_member_id=? AND div_year=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,ad_member_id);
			ps.setString(2,year);
			rs = ps.executeQuery();
			while (rs.next()) {
				DividentBean bean1 = new DividentBean();
				bean1.setAd_divident_id(rs.getInt("ad_divident_id"));					
				bean1.setFromdate(rs.getDate("fromdate"));
				bean1.setTodate(rs.getDate("todate"));
				bean1.setTotalshare_amt(rs.getDouble("totalshare_amt"));
				bean1.setTotal_intamt(rs.getDouble("total_intamt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setConv_amt(rs.getDouble("conv_amt"));
				bean1.setDiv_year(rs.getString("div_year"));
				bean1.setPay_status(rs.getString("pay_status"));
				bean1.setShare_qty(rs.getDouble("share_qty"));
				bean1.setDiscription(rs.getString("discription"));
				bean1.setInstrument_date(rs.getDate("instrument_date"));
				bean1.setInstrument_no(rs.getInt("instrument_no"));
				bean1.setInstrument_type(rs.getString("instrument_type"));
				bean1.setRoi(rs.getDouble("roi"));
				bean1.setTotal_amt(rs.getDouble("total_amt"));
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("DividentDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	

	//------------------------------------------------------------------------------------------------
	
	public DividentBean getAllDividentByDividendId(int ad_divident_id) {
		DividentBean bean1 = new DividentBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT * FROM ad_divident  where  ad_divident_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,ad_divident_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				bean1.setAd_divident_id(rs.getInt("ad_divident_id"));					
				bean1.setFromdate(rs.getDate("fromdate"));
				bean1.setTodate(rs.getDate("todate"));
				bean1.setTotalshare_amt(rs.getDouble("totalshare_amt"));
				bean1.setTotal_intamt(rs.getDouble("total_intamt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setConv_amt(rs.getDouble("conv_amt"));
				bean1.setDiv_year(rs.getString("div_year"));
				bean1.setPay_status(rs.getString("pay_status"));
				bean1.setShare_qty(rs.getDouble("share_qty"));
				bean1.setDiscription(rs.getString("discription"));
				bean1.setInstrument_date(rs.getDate("instrument_date"));
				bean1.setInstrument_no(rs.getInt("instrument_no"));
				bean1.setInstrument_type(rs.getString("instrument_type"));
				bean1.setRoi(rs.getDouble("roi"));
				bean1.setTotal_amt(rs.getDouble("total_amt"));
				

			}
		}catch (Exception e) {
			System.out.print("DividentDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean1;
	}
//----------------------------------------------------------------------------------------------
	
	public ArrayList<DividentBean> getAllDivident() {
		ArrayList<DividentBean> bean = new ArrayList<DividentBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "SELECT * FROM ad_divident order by ad_member_id";
		try {
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				DividentBean bean1 = new DividentBean();
				bean1.setAd_divident_id(rs.getInt("ad_divident_id"));					
				bean1.setFromdate(rs.getDate("fromdate"));
				bean1.setTodate(rs.getDate("todate"));
				bean1.setTotalshare_amt(rs.getDouble("totalshare_amt"));
				bean1.setTotal_intamt(rs.getDouble("total_intamt"));
				bean1.setAd_member_id(rs.getInt("ad_member_id"));
				bean1.setConv_amt(rs.getDouble("conv_amt"));
				bean1.setDiv_year(rs.getString("div_year"));
				bean1.setPay_status(rs.getString("pay_status"));
				bean1.setShare_qty(rs.getDouble("share_qty"));
				bean1.setDiscription(rs.getString("discription"));
				bean1.setInstrument_date(rs.getDate("instrument_date"));
				bean1.setInstrument_no(rs.getInt("instrument_no"));
				bean1.setInstrument_type(rs.getString("instrument_type"));
				bean1.setRoi(rs.getDouble("roi"));
				bean1.setTotal_amt(rs.getDouble("total_amt"));
				
				bean.add(bean1);

			}
		}catch (Exception e) {
			System.out.print("DividentDao:-error in try Block");
			e.printStackTrace();
			
		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
	    }
		return bean;
	}
	
	
	
	//----------------------------------------------------------------------------------------------
	
		public ArrayList<DividentBean> getAllDividentByYear(String div_year) {
			ArrayList<DividentBean> bean = new ArrayList<DividentBean>();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "SELECT * FROM ad_divident where div_year=? order by ad_divident_id";
			try {
				ps = con.prepareStatement(query);
				ps.setString(1, div_year);
				rs = ps.executeQuery();
				while (rs.next()) {
					DividentBean bean1 = new DividentBean();
					bean1.setAd_divident_id(rs.getInt("ad_divident_id"));					
					bean1.setFromdate(rs.getDate("fromdate"));
					bean1.setTodate(rs.getDate("todate"));
					bean1.setTotalshare_amt(rs.getDouble("totalshare_amt"));
					bean1.setTotal_intamt(rs.getDouble("total_intamt"));
					bean1.setAd_member_id(rs.getInt("ad_member_id"));
					bean1.setConv_amt(rs.getDouble("conv_amt"));
					bean1.setDiv_year(rs.getString("div_year"));
					bean1.setPay_status(rs.getString("pay_status"));
					bean1.setShare_qty(rs.getDouble("share_qty"));
					bean1.setDiscription(rs.getString("discription"));
					bean1.setInstrument_date(rs.getDate("instrument_date"));
					bean1.setInstrument_no(rs.getInt("instrument_no"));
					bean1.setInstrument_type(rs.getString("instrument_type"));
					bean1.setRoi(rs.getDouble("roi"));
					bean1.setTotal_amt(rs.getDouble("total_amt"));
					
					bean.add(bean1);

				}
			}catch (Exception e) {
				System.out.print("DividentDao:-error in try Block");
				e.printStackTrace();
				
			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
		    }
			return bean;
		}
		//----------------------------------------------------------------------------------------------
		
			public boolean isDividentYearAvailable(String div_year) {
				boolean result =false;
				PreparedStatement ps =null;
				ResultSet rs=null;
				String query = "SELECT * FROM ad_divident where div_year=? ";
				try {
					ps = con.prepareStatement(query);
					ps.setString(1, div_year);
					rs = ps.executeQuery();
					while (rs.next()) {
						result=true;
					}
				}catch (Exception e) {
					System.out.print("DividentDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return result;
			}
			
			//----------------------------------------------------------------------------------------------
			
			public boolean isDividentYearOrMemberAvailable(String div_year,int ad_member_id) {
				boolean result =false;
				PreparedStatement ps =null;
				ResultSet rs=null;
				String query = "SELECT * FROM ad_divident where div_year=? AND ad_member_id=? ";
				try {
					ps = con.prepareStatement(query);
					ps.setString(1, div_year);
					ps.setInt(2, ad_member_id);
					rs = ps.executeQuery();
					while (rs.next()) {
						result=true;
					}
				}catch (Exception e) {
					System.out.print("DividentDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					DBConnection.close(rs);
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				return result;
			}
//----------------------------------------------------------------------------------------------
	public int updateDivident(DividentBean bean) {
		int record=0;
		PreparedStatement ps=null;
		
		
				try {
					con = DBConnection.getConnection();
					String query = "UPDATE ad_divident " +
							" SET  updated=?, updatedby=?, isactive=?, fromdate=?, todate=?,  totalshare_amt=?, " +
							" total_intamt=?,  total_amt=?, share_qty=?, conv_amt=?, pay_status=?, instrument_type=?, " +
							" instrument_date=?, instrument_no=?, discription=?, div_year=?, roi=? WHERE ad_divident_id=?" ;
							
					ps = con.prepareStatement(query);
					
					ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
					ps.setInt(2, bean.getUpdatedby());				
					ps.setBoolean(3, true);
					ps.setDate(4, new java.sql.Date(bean.getFromdate().getTime()));
					ps.setDate(5, new java.sql.Date(bean.getTodate().getTime()));
					ps.setDouble(6, bean.getTotalshare_amt());
					ps.setDouble(7, bean.getTotal_intamt());
					ps.setDouble(8, bean.getTotal_amt());
					ps.setDouble(9, bean.getShare_qty());
					ps.setDouble(10, bean.getConv_amt());
					ps.setString(11, bean.getPay_status());
					ps.setString(12, bean.getInstrument_type());
					ps.setDate(13, new java.sql.Date(bean.getInstrument_date().getTime()));
					ps.setInt(14, bean.getInstrument_no());
					ps.setString(15, bean.getDiscription());
					ps.setString(16, bean.getDiv_year());
					ps.setDouble(17, bean.getRoi());
					ps.setInt(18, bean.getAd_divident_id());
						
					
					record=ps.executeUpdate();
					 
				} catch (Exception e) {
					System.out.print("DividentDao:-error in try Block");
					e.printStackTrace();
					
				}finally {
					
					DBConnection.close(ps);
					DBConnection.close(con);
			    }
				
				return record;
				
			}
		
		
		
	public int deleteDividend(DividentBean bean){
		int i=0;
		try{
			String query="DELETE FROM ad_divident WHERE ad_divident_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_divident_id());
			i=ps.executeUpdate();
		
			}catch (Exception e) {
			System.out.print("CastDao:-error in try Block");
			e.printStackTrace();
			
		}
		return i;
	}
		
			
}
		

