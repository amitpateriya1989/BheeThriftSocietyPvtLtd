package Model.Bean;

import java.util.Date;

public class DepartmentBean {

	private int ad_department_id ;
	private int ad_client_id ;
	private Date created ;
	private int  createdby ;
	private Date  updated ;
	private int  updatedby ;
	private String name ;
	private Date entrydate ;
	private String description ;
	private String duration ;
	private boolean isactive ;
	
	public int getAd_department_id() {
		return ad_department_id;
	}
	public void setAd_department_id(int ad_department_id) {
		this.ad_department_id = ad_department_id;
	}
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
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
}
