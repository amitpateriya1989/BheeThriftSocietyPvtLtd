package Model.Bean;

import java.util.Date;

public class MemberAddressBean {
	private int ad_member_address_id ;
	private String line1 ;
	private String line2 ;
	private CityBean city;
	private DistrictBean district;
	private StateBean state;
	private CountryBean country;
	private String pincode ;
	private String type ;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive; 
	//private MemberRegistrationBean member;
	private int ad_member_id;
	private String mobile ;
	private String phone ;
	private String email;
	
	public int getAd_member_address_id() {
		return ad_member_address_id;
	}
	public void setAd_member_address_id(int ad_member_address_id) {
		this.ad_member_address_id = ad_member_address_id;
	}
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public CityBean getCity() {
		return city;
	}
	public void setCity(CityBean city) {
		this.city = city;
	}
	public DistrictBean getDistrict() {
		return district;
	}
	public void setDistrict(DistrictBean district) {
		this.district = district;
	}
	public StateBean getState() {
		return state;
	}
	public void setState(StateBean state) {
		this.state = state;
	}
	public CountryBean getCountry() {
		return country;
	}
	public void setCountry(CountryBean country) {
		this.country = country;
	}
	/*public MemberRegistrationBean getMember() {
		return member;
	}
	public void setMember(MemberRegistrationBean member) {
		this.member = member;
	}*/
	public int getAd_member_id() {
		return ad_member_id;
	}
	public void setAd_member_id(int ad_member_id) {
		this.ad_member_id = ad_member_id;
	}

}
