package Model.Bean;

import java.util.Date;

public class LoanPurposeBean {
	int ad_loan_purpose_id;
	String  purpose;
	Date created;
	 int createdby;
	Date  updated ;
	int  updatedby;
	boolean  isactive;
	public int getAd_loan_purpose_id() {
		return ad_loan_purpose_id;
	}
	public void setAd_loan_purpose_id(int ad_loan_purpose_id) {
		this.ad_loan_purpose_id = ad_loan_purpose_id;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
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
}
