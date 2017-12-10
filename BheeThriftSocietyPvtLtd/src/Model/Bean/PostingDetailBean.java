package Model.Bean;

import java.util.Date;

public class PostingDetailBean {
	private int ad_posting_detail_id;
	private Date  created;
	private int  createdby;
	private Date  updated;
	private int  updatedby;
	private boolean  isactive;
	private int  ad_member_id ;
	private int  ad_branch_id ;
	private int  ad_designation_level_id ;
	private int  ad_designation_type_id;
	private int  ad_designation_id ;
	private int  ad_department_id ;
	private Date  formdate;
	private Date  todate ;
	
	private int branch_code;
	private String designation_level;
	private String designation_type;
	private String designation;
	private String department;
	
	
	
	public int getAd_posting_detail_id() {
		return ad_posting_detail_id;
	}
	public void setAd_posting_detail_id(int ad_posting_detail_id) {
		this.ad_posting_detail_id = ad_posting_detail_id;
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
	public int getAd_member_id() {
		return ad_member_id;
	}
	public void setAd_member_id(int ad_member_id) {
		this.ad_member_id = ad_member_id;
	}
	public int getAd_branch_id() {
		return ad_branch_id;
	}
	public void setAd_branch_id(int ad_branch_id) {
		this.ad_branch_id = ad_branch_id;
	}
	public int getAd_designation_level_id() {
		return ad_designation_level_id;
	}
	public void setAd_designation_level_id(int ad_designation_level_id) {
		this.ad_designation_level_id = ad_designation_level_id;
	}
	public int getAd_designation_type_id() {
		return ad_designation_type_id;
	}
	public void setAd_designation_type_id(int ad_designation_type_id) {
		this.ad_designation_type_id = ad_designation_type_id;
	}
	public int getAd_designation_id() {
		return ad_designation_id;
	}
	public void setAd_designation_id(int ad_designation_id) {
		this.ad_designation_id = ad_designation_id;
	}
	public int getAd_department_id() {
		return ad_department_id;
	}
	public void setAd_department_id(int ad_department_id) {
		this.ad_department_id = ad_department_id;
	}
	public Date getFormdate() {
		return formdate;
	}
	public void setFormdate(Date formdate) {
		this.formdate = formdate;
	}
	public Date getTodate() {
		return todate;
	}
	public void setTodate(Date todate) {
		this.todate = todate;
	}
	public int getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(int branch_code) {
		this.branch_code = branch_code;
	}
	public String getDesignation_level() {
		return designation_level;
	}
	public void setDesignation_level(String designation_level) {
		this.designation_level = designation_level;
	}
	public String getDesignation_type() {
		return designation_type;
	}
	public void setDesignation_type(String designation_type) {
		this.designation_type = designation_type;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

}
