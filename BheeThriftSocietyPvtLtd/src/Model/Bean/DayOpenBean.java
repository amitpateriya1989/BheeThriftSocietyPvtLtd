package Model.Bean;

import java.util.Date;

public class DayOpenBean {
	private int ad_open_days_id;
	private Date  open_days;
	 
	  private String remark;
	  private Date  created;
	private int   createdby ;
	 private Date updated;
	 boolean opening_status ;
	 boolean closing_status ;
		private int  updatedby;
		private boolean isactive ;
		
		public int getAd_open_days_id() {
			return ad_open_days_id;
		}
		public void setAd_open_days_id(int ad_open_days_id) {
			this.ad_open_days_id = ad_open_days_id;
		}
		public Date getOpen_days() {
			return open_days;
		}
		public void setOpen_days(Date open_days) {
			this.open_days = open_days;
		}
		
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
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
		public boolean getOpening_status() {
			return opening_status;
		}
		public void setOpening_status(boolean opening_status) {
			this.opening_status = opening_status;
		}
		public boolean getClosing_status() {
			return closing_status;
		}
		public void setClosing_status(boolean closing_status) {
			this.closing_status = closing_status;
		}
}
