package Model.Bean;

import java.util.Date;

public class AccountTypeBean {
	private int ad_ac_type_id;
	private Date created;
	private int createdby;
	private Date updated ;
	private int updatedby ;
	private boolean isactive;
	private String name;
	
	public int getAd_ac_type_id() {
		return ad_ac_type_id;
	}
	public void setAd_ac_type_id(int ad_ac_type_id) {
		this.ad_ac_type_id = ad_ac_type_id;
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
	

}
