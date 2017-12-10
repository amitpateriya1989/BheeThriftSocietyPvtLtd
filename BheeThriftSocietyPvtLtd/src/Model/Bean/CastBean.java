package Model.Bean;

import java.util.Date;

public class CastBean {
	private int ad_cast_id ;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive ;
	private String cast_type;
	
	public int getAd_cast_id() {
		return ad_cast_id;
	}
	public void setAd_cast_id(int ad_cast_id) {
		this.ad_cast_id = ad_cast_id;
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
	public String getCast_type() {
		return cast_type;
	}
	public void setCast_type(String cast_type) {
		this.cast_type = cast_type;
	}

}
