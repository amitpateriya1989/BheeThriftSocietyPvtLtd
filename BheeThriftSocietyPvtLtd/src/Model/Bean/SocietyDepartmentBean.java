package Model.Bean;

import java.util.Date;

public class SocietyDepartmentBean {

	private int ad_society_department_id;
	private Date created ;
	private int  createdby ;
	private Date  updated ;
	private int  updatedby ;
	private String department ;
	private boolean isactive ;
	public int getAd_society_department_id() {
		return ad_society_department_id;
	}
	public void setAd_society_department_id(int ad_society_department_id) {
		this.ad_society_department_id = ad_society_department_id;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	
	
}
