package Model.Bean;

import java.util.Date;

public class ListBean {
	private int ad_list_id ;
	private Date  created ;
	private int   createdby ;
	private Date  updated ;
	private int   updatedby ;
	private boolean  isactive ;
	private String  name;
	public int getAd_list_id() {
		return ad_list_id;
	}
	public void setAd_list_id(int ad_list_id) {
		this.ad_list_id = ad_list_id;
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
