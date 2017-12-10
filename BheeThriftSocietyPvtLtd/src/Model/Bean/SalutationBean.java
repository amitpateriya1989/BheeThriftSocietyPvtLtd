package Model.Bean;

import java.util.Date;

public class SalutationBean {

	private int ad_salutation_id;
	private int ad_gender_id;
	private String gender;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive ;
	private String name;
	
	public int getAd_salutation_id() {
		return ad_salutation_id;
	}
	public void setAd_salutation_id(int ad_salutation_id) {
		this.ad_salutation_id = ad_salutation_id;
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
	public int getAd_gender_id() {
		return ad_gender_id;
	}
	public void setAd_gender_id(int ad_gender_id) {
		this.ad_gender_id = ad_gender_id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
