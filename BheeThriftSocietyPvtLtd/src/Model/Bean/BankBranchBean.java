package Model.Bean;

import java.util.Date;

public class BankBranchBean {
	private int ad_branch_id ;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive;
	private String branch ;
	private BankBean bank;
	private StateBean state;
	private DistrictBean district;
	private CityBean city;
	private BankRegionBean bank_region;
	private int branch_code ;
	private String ifsc_code ;
	private String address ;
	private String pincode ;
	private String contact_no ;
	private String contact_person ;
	private String email_id ;
	private String phone_no ;
	private String fax_no ;
	
	public int getAd_branch_id() {
		return ad_branch_id;
	}
	public void setAd_branch_id(int ad_branch_id) {
		this.ad_branch_id = ad_branch_id;
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
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public int getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(int branch_code) {
		this.branch_code = branch_code;
	}
	public String getIfsc_code() {
		return ifsc_code;
	}
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public String getContact_person() {
		return contact_person;
	}
	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
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
	public BankBean getBank() {
		return bank;
	}
	public void setBank(BankBean bank) {
		this.bank = bank;
	}
	public StateBean getState() {
		return state;
	}
	public void setState(StateBean state) {
		this.state = state;
	}
	public DistrictBean getDistrict() {
		return district;
	}
	public void setDistrict(DistrictBean district) {
		this.district = district;
	}
	public CityBean getCity() {
		return city;
	}
	public void setCity(CityBean city) {
		this.city = city;
	}
	public BankRegionBean getBank_region() {
		return bank_region;
	}
	public void setBank_region(BankRegionBean bank_region) {
		this.bank_region = bank_region;
	}
	public String getFax_no() {
		return fax_no;
	}
	public void setFax_no(String fax_no) {
		this.fax_no = fax_no;
	}
}
