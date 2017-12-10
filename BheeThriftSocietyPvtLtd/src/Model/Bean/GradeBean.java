package Model.Bean;

import java.util.Date;

public class GradeBean {
	
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive ;
	private String grade_name;
	private double basics;
	private double da;
	private double convey;
	private double hra;
	private double sw;
	private double mdcl;
	private double alwnc;
	
	
	private int ad_grade_id ;
	public int getAd_grade_id() {
		return ad_grade_id;
	}
	public void setAd_grade_id(int ad_grade_id) {
		this.ad_grade_id = ad_grade_id;
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
	public String getGrade_name() {
		return grade_name;
	}
	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}
	public double getBasics() {
		return basics;
	}
	public void setBasics(double basics) {
		this.basics = basics;
	}
	public double getDa() {
		return da;
	}
	public void setDa(double da) {
		this.da = da;
	}
	public double getConvey() {
		return convey;
	}
	public void setConvey(double convey) {
		this.convey = convey;
	}
	public double getHra() {
		return hra;
	}
	public void setHra(double hra) {
		this.hra = hra;
	}
	public double getSw() {
		return sw;
	}
	public void setSw(double sw) {
		this.sw = sw;
	}
	public double getMdcl() {
		return mdcl;
	}
	public void setMdcl(double mdcl) {
		this.mdcl = mdcl;
	}
	public double getAlwnc() {
		return alwnc;
	}
	public void setAlwnc(double alwnc) {
		this.alwnc = alwnc;
	}
	

}
