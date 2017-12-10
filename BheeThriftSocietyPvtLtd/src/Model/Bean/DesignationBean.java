package Model.Bean;

import java.util.Date;

public class DesignationBean {

	private int ad_designation_id ;
	private int ad_designation_type_id ;
	private Date created ;
	private int createdby;
	private Date updated ;
	private int updatedby ;
	private boolean isactive;
	private String designation;
	
	public int getAd_designation_id() {
		return ad_designation_id;
	}
	public void setAd_designation_id(int ad_designation_id) {
		this.ad_designation_id = ad_designation_id;
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
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getAd_designation_type_id() {
		return ad_designation_type_id;
	}
	public void setAd_designation_type_id(int ad_designation_type_id) {
		this.ad_designation_type_id = ad_designation_type_id;
	}
}
