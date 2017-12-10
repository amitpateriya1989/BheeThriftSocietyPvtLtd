package Model.Bean;

import java.util.Date;

public class CalenderBean {
	private int ad_calender_id ;
	private int ad_list_item_id ;
	private Date  created ;
	private int createdby ;
	private Date  updated ;
	private int updatedby ;
	private boolean  isactive ;
	private Date  holiday_date ;
	private String  status;
	private String year; 
	public int getAd_calender_id() {
		return ad_calender_id;
	}
	public void setAd_calender_id(int ad_calender_id) {
		this.ad_calender_id = ad_calender_id;
	}
	public int getAd_list_item_id() {
		return ad_list_item_id;
	}
	public void setAd_list_item_id(int ad_list_item_id) {
		this.ad_list_item_id = ad_list_item_id;
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
	public Date getHoliday_date() {
		return holiday_date;
	}
	public void setHoliday_date(Date holiday_date) {
		this.holiday_date = holiday_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}
