package Model.Bean;

import java.util.Date;

public class StateBean {

	private int ad_state_id ;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive;
	private String state;
	
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
