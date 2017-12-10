package Model.Bean;

import java.sql.Date;

public class AccountSubGroupBean {

	private int ad_ac_subgroup_id ;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive ;
	private int ad_ac_group_id ;
	private String name;
	private int ad_ac_type_id;
	
	
	private int sub_group_ac_no;
	public int getAd_ac_subgroup_id() {
		return ad_ac_subgroup_id;
	}
	public void setAd_ac_subgroup_id(int ad_ac_subgroup_id) {
		this.ad_ac_subgroup_id = ad_ac_subgroup_id;
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
	public int getAd_ac_group_id() {
		return ad_ac_group_id;
	}
	public void setAd_ac_group_id(int ad_ac_group_id) {
		this.ad_ac_group_id = ad_ac_group_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAd_ac_type_id() {
		return ad_ac_type_id;
	}
	public void setAd_ac_type_id(int ad_ac_type_id) {
		this.ad_ac_type_id = ad_ac_type_id;
	}
	public int getSub_group_ac_no() {
		return sub_group_ac_no;
	}
	public void setSub_group_ac_no(int sub_group_ac_no) {
		this.sub_group_ac_no = sub_group_ac_no;
	}
}
