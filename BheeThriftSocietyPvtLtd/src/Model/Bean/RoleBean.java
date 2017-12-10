package Model.Bean;

import java.util.Date;

public class RoleBean {
	private int ad_role_id ;
	private int ad_client_id ;
	private Date created ;
	private int createdby ;
	private Date updated;
	private int updatedby ;
	private String name;
	private String description;
	private boolean isactive;
	private boolean read_permission;
	private boolean write_permission;
	private boolean edit_permission;
	
	public int getAd_role_id() {
		return ad_role_id;
	}
	public void setAd_role_id(int ad_role_id) {
		this.ad_role_id = ad_role_id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public boolean isRead_permission() {
		return read_permission;
	}
	public void setRead_permission(boolean read_permission) {
		this.read_permission = read_permission;
	}
	public boolean isWrite_permission() {
		return write_permission;
	}
	public void setWrite_permission(boolean write_permission) {
		this.write_permission = write_permission;
	}
	public boolean isEdit_permission() {
		return edit_permission;
	}
	public void setEdit_permission(boolean edit_permission) {
		this.edit_permission = edit_permission;
	}

}
