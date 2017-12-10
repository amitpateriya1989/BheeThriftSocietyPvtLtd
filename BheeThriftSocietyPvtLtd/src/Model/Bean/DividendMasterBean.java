package Model.Bean;

import java.util.Date;

public class DividendMasterBean {
	
	int ad_divident_master_id = 0;
	Date created=null;
	int createdby = 0;
	Date updated;
	int updatedby = 0;
	boolean isactive = false;
	Date year_from;
	Date year_to;
	String year;
	double ad_convence_amt  = 0.0;
	double ad_dividend_per  = 0.0;
	
	public int getAd_divident_master_id() {
		return ad_divident_master_id;
	}
	public void setAd_divident_master_id(int ad_divident_master_id) {
		this.ad_divident_master_id = ad_divident_master_id;
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
	public double getAd_convence_amt() {
		return ad_convence_amt;
	}
	public void setAd_convence_amt(double ad_convence_amt) {
		this.ad_convence_amt = ad_convence_amt;
	}
	public double getAd_dividend_per() {
		return ad_dividend_per;
	}
	public void setAd_dividend_per(double ad_dividend_per) {
		this.ad_dividend_per = ad_dividend_per;
	}
	public Date getYear_from() {
		return year_from;
	}
	public void setYear_from(Date year_from) {
		this.year_from = year_from;
	}
	public Date getYear_to() {
		return year_to;
	}
	public void setYear_to(Date year_to) {
		this.year_to = year_to;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	

}//end class
