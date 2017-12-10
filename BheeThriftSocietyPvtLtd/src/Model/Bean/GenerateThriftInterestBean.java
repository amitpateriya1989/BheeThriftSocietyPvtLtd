package Model.Bean;

import java.util.Date;

public class GenerateThriftInterestBean {
	private int generate_thrift_interest_id ;
	private Date generate_date ;
	private String status ;
	private Date created ;
	private int createdby ;
	private Date  updated ;
	private int  updatedby ;
	private boolean  isactive;
	private double  total_int;
	
	public int getGenerate_thrift_interest_id() {
		return generate_thrift_interest_id;
	}
	public void setGenerate_thrift_interest_id(int generate_thrift_interest_id) {
		this.generate_thrift_interest_id = generate_thrift_interest_id;
	}
	public Date getGenerate_date() {
		return generate_date;
	}
	public void setGenerate_date(Date generate_date) {
		this.generate_date = generate_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public double getTotal_int() {
		return total_int;
	}
	public void setTotal_int(double total_int) {
		this.total_int = total_int;
	}

}
