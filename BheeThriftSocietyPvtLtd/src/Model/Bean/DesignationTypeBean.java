package Model.Bean;

import java.util.Date;

public class DesignationTypeBean {

	private int ad_designation_type_id ;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive ;
	private String designation_type;
	
	public int getAd_designation_type_id() {
		return ad_designation_type_id;
	}
	public void setAd_designation_type_id(int ad_designation_type_id) {
		this.ad_designation_type_id = ad_designation_type_id;
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
	public String getDesignation_type() {
		return designation_type;
	}
	public void setDesignation_type(String designation_type) {
		this.designation_type = designation_type;
	}
}
