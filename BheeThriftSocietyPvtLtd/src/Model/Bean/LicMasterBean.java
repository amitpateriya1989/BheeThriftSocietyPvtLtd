package Model.Bean;

import java.util.Date;

public class LicMasterBean {
	private int ad_lic_master_id ;
	private int  ad_type_of_loan_id ;
	private int ad_loan_category_id ;
	private int ad_loan_roi_id;
	private Date  created ;
	private int  createdby ;
	private Date  updated ;
	private int  updatedby ;
	private boolean  isactive ;
	private double  lic_rate ;
	private Date  applied_date;
	
	public int getAd_lic_master_id() {
		return ad_lic_master_id;
	}
	public void setAd_lic_master_id(int ad_lic_master_id) {
		this.ad_lic_master_id = ad_lic_master_id;
	}
	
	public int getAd_type_of_loan_id() {
		return ad_type_of_loan_id;
	}
	public void setAd_type_of_loan_id(int ad_type_of_loan_id) {
		this.ad_type_of_loan_id = ad_type_of_loan_id;
	}
	public int getAd_loan_category_id() {
		return ad_loan_category_id;
	}
	public void setAd_loan_category_id(int ad_loan_category_id) {
		this.ad_loan_category_id = ad_loan_category_id;
	}
	public int getAd_loan_roi_id() {
		return ad_loan_roi_id;
	}
	public void setAd_loan_roi_id(int ad_loan_roi_id) {
		this.ad_loan_roi_id = ad_loan_roi_id;
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
	public double getLic_rate() {
		return lic_rate;
	}
	public void setLic_rate(double lic_rate) {
		this.lic_rate = lic_rate;
	}
	public Date getApplied_date() {
		return applied_date;
	}
	public void setApplied_date(Date applied_date) {
		this.applied_date = applied_date;
	}
}
