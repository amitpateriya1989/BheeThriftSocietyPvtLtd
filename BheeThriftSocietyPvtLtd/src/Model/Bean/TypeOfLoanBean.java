package Model.Bean;

import java.util.Date;

public class TypeOfLoanBean {
	int  ad_type_of_loan_id;
	Date  created;
	 int createdby;
	Date  updated;
	int  updatedby;
	String  name ;
	private boolean isactive;
	public int getAd_type_of_loan_id() {
		return ad_type_of_loan_id;
	}
	public void setAd_type_of_loan_id(int ad_type_of_loan_id) {
		this.ad_type_of_loan_id = ad_type_of_loan_id;
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
