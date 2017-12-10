package Model.Bean;

import java.util.Date;

public class WitnessBean {
	private int ad_witness_id ;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive ;
	private int ad_member_id ;
	private String ad_witness_name;
	private int  ad_salutation_id ;
	private String  ad_witness_mobile;
	private String  ad_witness_address ;
	private int  ad_witness_mem_no;

	public int getAd_witness_id() {
		return ad_witness_id;
	}
	public void setAd_witness_id(int ad_witness_id) {
		this.ad_witness_id = ad_witness_id;
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
	public int getAd_member_id() {
		return ad_member_id;
	}
	public void setAd_member_id(int ad_member_id) {
		this.ad_member_id = ad_member_id;
	}
	public String getAd_witness_name() {
		return ad_witness_name;
	}
	public void setAd_witness_name(String ad_witness_name) {
		this.ad_witness_name = ad_witness_name;
	}
	public int getAd_salutation_id() {
		return ad_salutation_id;
	}
	public void setAd_salutation_id(int ad_salutation_id) {
		this.ad_salutation_id = ad_salutation_id;
	}
	public String getAd_witness_mobile() {
		return ad_witness_mobile;
	}
	public void setAd_witness_mobile(String ad_witness_mobile) {
		this.ad_witness_mobile = ad_witness_mobile;
	}
	public String getAd_witness_address() {
		return ad_witness_address;
	}
	public void setAd_witness_address(String ad_witness_address) {
		this.ad_witness_address = ad_witness_address;
	}
	public int getAd_witness_mem_no() {
		return ad_witness_mem_no;
	}
	public void setAd_witness_mem_no(int ad_witness_mem_no) {
		this.ad_witness_mem_no = ad_witness_mem_no;
	}

}
