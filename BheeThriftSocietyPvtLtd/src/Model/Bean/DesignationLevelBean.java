package Model.Bean;

import java.util.Date;

public class DesignationLevelBean {

	private int ad_designation_level_id;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive;
	private String designation_level;
	
	public int getAd_designation_level_id() {
		return ad_designation_level_id;
	}
	public void setAd_designation_level_id(int ad_designation_level_id) {
		this.ad_designation_level_id = ad_designation_level_id;
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
	public String getDesignation_level() {
		return designation_level;
	}
	public void setDesignation_level(String designation_level) {
		this.designation_level = designation_level;
	}
}
