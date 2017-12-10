package Model.Bean;

import java.util.Date;

public class LoanRoiBean {
	int ad_loan_roi_id;
	Date  created ;
	 int createdby ;
	Date  updated;
	int  updatedby ;
	 int ad_type_of_loan_id;
	int  ad_loan_category_id;
	double  max_limit;
	 Date effective_form;
	String  effective_to;
	boolean isactive;
	private double roi;
	double share_needed;
	String cetegory;
	String type;
	int min_period ;
	int max_period ;
	double min_salary;
	
	public int getMin_period() {
		return min_period;
	}
	public void setMin_period(int min_period) {
		this.min_period = min_period;
	}
	public int getMax_period() {
		return max_period;
	}
	public void setMax_period(int max_period) {
		this.max_period = max_period;
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
	public double getMax_limit() {
		return max_limit;
	}
	public void setMax_limit(double max_limit) {
		this.max_limit = max_limit;
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
	public void setRoi(double roi) {
		this.roi = roi;
		
	}
	
	public double getroi() {
		return roi;
	}
	public void setShare_needed(double share_needed) {
		this.share_needed = share_needed;
		
	}
	
	public double getShare_needed() {
		return share_needed;
	}
	public String getCetegory() {
		return cetegory;
	}
	public void setCetegory(String cetegory) {
		this.cetegory = cetegory;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getMin_salary() {
		return min_salary;
	}
	public void setMin_salary(double min_salary) {
		this.min_salary = min_salary;
	}
	
}
