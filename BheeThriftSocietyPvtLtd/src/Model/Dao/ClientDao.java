package Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.ClientBean;

public class ClientDao {
	private Connection con = null;

	public ClientDao() {
		con = DBConnection.getConnection();
	}

	public int addClient(ClientBean bean) {
		int record=0;
		PreparedStatement ps=null;
		try {

			String query = "INSERT INTO ad_client( created, createdby, name, entrydate, address, email_id, " +
					" parent_organization, registration_no, logo, updatedby, isactive, phone_no, account_no, fax, updated) " +
					"   VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(bean.getCreated().getTime()));
			ps.setInt(2, bean.getCreatedby());
			ps.setString(3, bean.getName());
			ps.setDate(4,new java.sql.Date(bean.getEntrydate().getTime()));
			ps.setString(5,bean.getAddress());
			ps.setString(6, bean.getEmail_id());
			ps.setString(7,bean.getParent_organization());
			ps.setString(8,bean.getRegistration_no());
			ps.setString(9,bean.getLogo());
			ps.setInt(10, bean.getUpdatedby());
			ps.setBoolean(11, bean.isIsactive());
			ps.setString(12,bean.getPhone_no());
			ps.setString(13, bean.getAccount_No());
			ps.setString(14,bean.getFax());
			ps.setDate(15, new java.sql.Date(bean.getUpdated().getTime()));


			System.out.println(ps);
			record=ps.executeUpdate();

		} catch (Exception e) {
			System.out.print("ClientDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return record;
	}
	//-----------------------------------------------------------------------------------------
	public ClientBean getClientById(ClientBean bean) {
		ClientBean bean1 = new ClientBean();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query = "select * from ad_client where ad_client_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_client_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_client_id(rs.getInt("ad_client_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setEntrydate(rs.getDate("entrydate"));
				bean1.setName(rs.getString("name"));
				bean1.setAddress(rs.getString("address"));
				bean1.setEmail_id(rs.getString("Email_id"));
				bean1.setPhone_no(rs.getString("phone_no"));
				bean1.setAccount_No(rs.getString("Account_No"));
				bean1.setParent_organization(rs.getString("Parent_organization"));
				bean1.setRegistration_no(rs.getString("registration_no"));
				bean1.setFax(rs.getString("fax"));
				bean1.setLogo(rs.getString("logo"));

			}
		}catch (Exception e) {
			System.out.print("ClientDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}

	//------------------------------------------------------------------------------------------	
	public ClientBean getClientById(int Client_id) {
		ClientBean bean1 = new ClientBean();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_client where ad_client_id=? ";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, Client_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bean1.setAd_client_id(rs.getInt("ad_client_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setEntrydate(rs.getDate("entrydate"));
				bean1.setName(rs.getString("name"));

				bean1.setAddress(rs.getString("address"));
				bean1.setEmail_id(rs.getString("Email_id"));
				bean1.setPhone_no(rs.getString("phone_no"));
				bean1.setAccount_No(rs.getString("Account_No"));
				bean1.setParent_organization(rs.getString("Parent_organization"));
				bean1.setRegistration_no(rs.getString("registration_no"));
				bean1.setFax(rs.getString("fax"));
				bean1.setLogo(rs.getString("logo"));

			}
		}catch (Exception e) {
			System.out.print("ClientDao:-error in try Block");
			e.printStackTrace();

		}finally {
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return bean1;
	}
	//--------------------------------------------------------------------------------------
	public ArrayList<ClientBean> getAllClient() {
		ArrayList<ClientBean> bean = new ArrayList<ClientBean>();
		PreparedStatement ps =null;
		ResultSet rs=null;
		String query = "select * from ad_client ";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				ClientBean bean1 = new ClientBean();
				bean1.setAd_client_id(rs.getInt("ad_client_id"));
				bean1.setCreated(rs.getDate("created"));
				bean1.setCreatedby(rs.getInt("createdby"));
				bean1.setUpdated(rs.getDate("updated"));
				bean1.setUpdatedby(rs.getInt("updatedby"));
				bean1.setIsactive(rs.getBoolean("isactive"));
				bean1.setEntrydate(rs.getDate("entrydate"));
				bean1.setName(rs.getString("name"));

				bean1.setAddress(rs.getString("address"));
				bean1.setEmail_id(rs.getString("Email_id"));
				bean1.setPhone_no(rs.getString("phone_no"));
				bean1.setAccount_No(rs.getString("Account_No"));
				bean1.setParent_organization(rs.getString("Parent_organization"));
				bean1.setRegistration_no(rs.getString("registration_no"));
				bean1.setFax(rs.getString("fax"));
				bean1.setLogo(rs.getString("logo"));
				bean.add(bean1);
			}
		}catch (Exception e) {
			System.out.print("ClientDao:-error in try Block");
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
		public ClientBean getMaxClient() {
			ClientBean bean1 = new ClientBean();
			PreparedStatement ps =null;
			ResultSet rs=null;
			String query = "select * from ad_client where ad_client_id=(select max(ad_client_id) from ad_client) ";
			try {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				while (rs.next()) {
					
					bean1.setAd_client_id(rs.getInt("ad_client_id"));
					bean1.setCreated(rs.getDate("created"));
					bean1.setCreatedby(rs.getInt("createdby"));
					bean1.setUpdated(rs.getDate("updated"));
					bean1.setUpdatedby(rs.getInt("updatedby"));
					bean1.setIsactive(rs.getBoolean("isactive"));
					bean1.setEntrydate(rs.getDate("entrydate"));
					bean1.setName(rs.getString("name"));

					bean1.setAddress(rs.getString("address"));
					bean1.setEmail_id(rs.getString("Email_id"));
					bean1.setPhone_no(rs.getString("phone_no"));
					bean1.setAccount_No(rs.getString("Account_No"));
					bean1.setParent_organization(rs.getString("Parent_organization"));
					bean1.setRegistration_no(rs.getString("registration_no"));
					bean1.setFax(rs.getString("fax"));
					bean1.setLogo(rs.getString("logo"));
					
				}
			}catch (Exception e) {
				System.out.print("ClientDao:-error in try Block");
				e.printStackTrace();

			}finally {
				DBConnection.close(rs);
				DBConnection.close(ps);
				DBConnection.close(con);
			}
			return bean1;
		}
		//----------------------------------------------------------------------------------------------
	public int  updateClient(ClientBean bean){
		int i=0;
		PreparedStatement ps =null;
		try {

			String query = "UPDATE ad_client " +
					" SET   name=?, address=?, email_id=?, parent_organization=?, registration_no=?, " +
					"  updatedby=?, isactive=?, phone_no=?, account_no=?, fax=?,  updated=? WHERE ad_client_id=?";
			ps = con.prepareStatement(query);
			
			ps.setString(1, bean.getName());
			ps.setString(2,bean.getAddress());
			ps.setString(3, bean.getEmail_id());
			ps.setString(4,bean.getParent_organization());
			ps.setString(5,bean.getRegistration_no());
			ps.setInt(6, bean.getUpdatedby());
			ps.setBoolean(7, bean.isIsactive());
			ps.setString(8,bean.getPhone_no());
			ps.setString(9, bean.getAccount_No());
			ps.setString(10,bean.getFax());
			ps.setDate(11, new java.sql.Date(bean.getUpdated().getTime()));
			ps.setInt(12, bean.getAd_client_id());
			//System.out.print(ps);
			i=ps.executeUpdate();
			System.out.println("================>"+i);
		}catch (Exception e) {
			System.out.print("ClientDao:-error in try Block");
			e.printStackTrace();

		}finally {

			DBConnection.close(ps);
			DBConnection.close(con);
		}
		return i;
	}
	//----------------------------------------------------------------------------------------------
	public void deleteClient(ClientBean bean){
		PreparedStatement ps =null;
		int i=0;
		try{
			String query="DELETE FROM ad_client WHERE ad_client_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, bean.getAd_client_id());
			ps.executeQuery();
		}catch (Exception e) {
			System.out.print("ClientDao:-error in try Block");
			e.printStackTrace();
		}finally {
			DBConnection.close(ps);
			DBConnection.close(con);
		}

	}
}
