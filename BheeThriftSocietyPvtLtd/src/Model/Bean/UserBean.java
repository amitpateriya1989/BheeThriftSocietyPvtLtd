package Model.Bean;

import java.util.Date;

public class UserBean {
	private int ad_user_id ;
	private int ad_client_id ;
	private int ad_department_id ;
	private Date created ;
	private int createdby ;
	private Date update;
	private int updatedby ;
	private String name;
	private String user_id ;
	private String pass ;
	private String photo;
	private boolean isactive;
	private String security_password ;
	private String id_proof ;
	private String signature;
	
	public int getAd_user_id() {
		return ad_user_id;
	}
	public void setAd_user_id(int ad_user_id) {
		this.ad_user_id = ad_user_id;
	}
	public int getAd_client_id() {
		return ad_client_id;
	}
	public void setAd_client_id(int ad_client_id) {
		this.ad_client_id = ad_client_id;
	}
	public int getAd_department_id() {
		return ad_department_id;
	}
	public void setAd_department_id(int ad_department_id) {
		this.ad_department_id = ad_department_id;
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
	public Date getUpdate() {
		return update;
	}
	public void setUpdate(Date update) {
		this.update = update;
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getSecurity_password() {
		return security_password;
	}
	public void setSecurity_password(String security_password) {
		this.security_password = security_password;
	}
	public String getId_proof() {
		return id_proof;
	}
	public void setId_proof(String id_proof) {
		this.id_proof = id_proof;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
