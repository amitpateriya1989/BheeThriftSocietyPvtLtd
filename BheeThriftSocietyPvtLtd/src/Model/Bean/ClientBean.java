package Model.Bean;

import java.util.Date;

public class ClientBean {
	private int ad_client_id ;
	private Date created ;
	private int  createdby ;
	private Date  updated ;
	private int  updatedby ;
	private boolean  isactive;
	private String name;
	private Date entrydate;
	private String address ;
	private  String email_id;
 	private  String phone_no;
	private String  account_No;
	private  String parent_organization;
	private  String registration_no;
	private  String  fax;
	private  String logo;
	
	
	public int getAd_client_id() {
		return ad_client_id;
	}
	public void setAd_client_id(int ad_client_id) {
		this.ad_client_id = ad_client_id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public int getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getEntrydate() {
		return entrydate;
	}
	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String string() {
		return registration_no;
	}
	public void setRegistration_no(String registration_no) {
		this.registration_no = registration_no;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getAccount_No() {
		return account_No;
	}
	public void setAccount_No(String account_No) {
		this.account_No = account_No;
	}
	public String getParent_organization() {
		return parent_organization;
	}
	public void setParent_organization(String parent_organization) {
		this.parent_organization = parent_organization;
	}
	public String getRegistration_no() {
		return registration_no;
	}
}
