package Model.Bean;

import java.util.Date;

public class LoanCategoryBean {
	int ad_loan_category_id;
	 Date created;
	int  createdby ;
	Date  updated;
	int  updatedby;
	 String name;
	 boolean isactive;
	public int getAd_loan_category_id() {
		return ad_loan_category_id;
	}
	public void setAd_loan_category_id(int ad_loan_category_id) {
		this.ad_loan_category_id = ad_loan_category_id;
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
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	
}
