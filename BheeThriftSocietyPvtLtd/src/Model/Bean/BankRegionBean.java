package Model.Bean;

import java.util.Date;

public class BankRegionBean {
	private int ad_bank_region_id ;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive ;
	private String region;
	private String region_code;
	public int getAd_bank_region_id() {
		return ad_bank_region_id;
	}
	public void setAd_bank_region_id(int ad_bank_region_id) {
		this.ad_bank_region_id = ad_bank_region_id;
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegion_code() {
		return region_code;
	}
	public void setRegion_code(String region_code) {
		this.region_code = region_code;
	}
}
