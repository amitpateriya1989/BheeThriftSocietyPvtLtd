package Model.Bean;

import java.util.Date;

public class LoanwitnessBean {
	int 	ad_loan_witness_id ;
	String  name;
	String  memno ;
	String 	pfno ;
	String  mobile;
	String 	address;
	Date 	created;
	int 	createdby;
	Date 	updated;
	int 	updatedby ;
	boolean isactive;
	public int getAd_loan_witness_id() {
		return ad_loan_witness_id;
	}
	public void setAd_loan_witness_id(int ad_loan_witness_id) {
		this.ad_loan_witness_id = ad_loan_witness_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	public String getPfno() {
		return pfno;
	}
	public void setPfno(String pfno) {
		this.pfno = pfno;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
