package Model.Member.Bean;

import java.util.Date;

import Model.Bean.MemberRegistrationBean;

public class MemberLoginBean {
	private int ad_member_login_id ;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive ;
	private MemberRegistrationBean member ;
	private String username ;
	private String password ;
	private String confirm ;
	private String status;
	
	
	public int getAd_member_login_id() {
		return ad_member_login_id;
	}
	public void setAd_member_login_id(int ad_member_login_id) {
		this.ad_member_login_id = ad_member_login_id;
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
	
	public MemberRegistrationBean getMember() {
		return member;
	}
	public void setMember(MemberRegistrationBean member) {
		this.member = member;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
