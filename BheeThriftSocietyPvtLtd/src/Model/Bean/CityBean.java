package Model.Bean;

import java.util.Date;

public class CityBean {
	private int ad_city_id ;
	private int ad_district_id;
	private int ad_state_id ;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive;
	private String city;
	private int pincode;
	
	public int getAd_city_id() {
		return ad_city_id;
	}
	public void setAd_city_id(int ad_city_id) {
		this.ad_city_id = ad_city_id;
	}
	public int getAd_district_id() {
		return ad_district_id;
	}
	public void setAd_district_id(int ad_district_id) {
		this.ad_district_id = ad_district_id;
	}
	public int getAd_state_id() {
		return ad_state_id;
	}
	public void setAd_state_id(int ad_state_id) {
		this.ad_state_id = ad_state_id;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

}
