package Model.Bean;

import java.util.Date;

public class EmailUtilityBean {
	int ad_email_id ;
	Date created ;
	int createdby ;
	Date updated ;
	int updatedby ;
	boolean isactive ;
	String email_id ;
	String pwd ;
	String host_name ;
	int port_no;
	
	public int getAd_email_id() {
		return ad_email_id;
	}
	public void setAd_email_id(int ad_email_id) {
		this.ad_email_id = ad_email_id;
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
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public int getPort_no() {
		return port_no;
	}
	public void setPort_no(int port_no) {
		this.port_no = port_no;
	}
}
