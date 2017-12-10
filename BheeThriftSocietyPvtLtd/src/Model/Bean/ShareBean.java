package Model.Bean;

import java.util.Date;

public class ShareBean {
	private int ad_share_id ;
	private double  per_share_rate ;
	private String f_date ;
	private String t_date ;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive ;
	
	public int getAd_share_id() {
		return ad_share_id;
	}
	public void setAd_share_id(int ad_share_id) {
		this.ad_share_id = ad_share_id;
	}
	public double getPer_share_rate() {
		return per_share_rate;
	}
	public void setPer_share_rate(double per_share_rate) {
		this.per_share_rate = per_share_rate;
	}
	public String getF_date() {
		return f_date;
	}
	public void setF_date(String f_date) {
		this.f_date = f_date;
	}
	public String getT_date() {
		return t_date;
	}
	public void setT_date(String t_date) {
		this.t_date = t_date;
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
