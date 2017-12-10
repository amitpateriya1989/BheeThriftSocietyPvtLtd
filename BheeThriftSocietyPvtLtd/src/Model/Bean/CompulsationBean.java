package Model.Bean;

import java.util.Date;

public class CompulsationBean {
	int ad_compensation_amt_id ;
	int  createdby ;
	Date  created ;
	int  updatedby ;
	Date  updated;
	 boolean isactive;
	double  amt ;
	 String reson;
	 Date effective_from_date;
	 Date effective_to_date;
	 
	public int getAd_compensation_amt_id() {
		return ad_compensation_amt_id;
	}
	public void setAd_compensation_amt_id(int ad_compensation_amt_id) {
		this.ad_compensation_amt_id = ad_compensation_amt_id;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public int getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	public String getReson() {
		return reson;
	}
	public void setReson(String reson) {
		this.reson = reson;
	}
	public Date getEffective_from_date() {
		return effective_from_date;
	}
	public void setEffective_from_date(Date effective_from_date) {
		this.effective_from_date = effective_from_date;
	}
	public Date getEffective_to_date() {
		return effective_to_date;
	}
	public void setEffective_to_date(Date effective_to_date) {
		this.effective_to_date = effective_to_date;
	}

}
