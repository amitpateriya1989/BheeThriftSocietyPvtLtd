package Model.Bean;

import java.util.Date;

public class FdRoiBean {
	int ad_fd_roi_id;
	Date  created ;
	int createdby ;
	Date  updated;
	int  updatedby ;
	int ad_type_of_fd_id;
	int  ad_fd_category_id;
	int  time_period;
	Date effective_form;
	String  effective_to;
	boolean isactive;
	private double roi;
	double compound_frequency;
	
	public int getAd_fd_roi_id() {
		return ad_fd_roi_id;
	}
	public void setAd_fd_roi_id(int ad_fd_roi_id) {
		this.ad_fd_roi_id = ad_fd_roi_id;
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
	public int getAd_type_of_fd_id() {
		return ad_type_of_fd_id;
	}
	public void setAd_type_of_fd_id(int ad_type_of_fd_id) {
		this.ad_type_of_fd_id = ad_type_of_fd_id;
	}
	public int getAd_fd_category_id() {
		return ad_fd_category_id;
	}
	public void setAd_fd_category_id(int ad_fd_category_id) {
		this.ad_fd_category_id = ad_fd_category_id;
	}
	public int getTime_period() {
		return time_period;
	}
	public void setTime_period(int time_period) {
		this.time_period = time_period;
	}
	public Date getEffective_form() {
		return effective_form;
	}
	public void setEffective_form(Date effective_form) {
		this.effective_form = effective_form;
	}
	public String getEffective_to() {
		return effective_to;
	}
	public void setEffective_to(String effective_to) {
		this.effective_to = effective_to;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public double getRoi() {
		return roi;
	}
	public void setRoi(double roi) {
		this.roi = roi;
	}
	public double getCompound_frequency() {
		return compound_frequency;
	}
	public void setCompound_frequency(double compound_frequency) {
		this.compound_frequency = compound_frequency;
	}

	}
