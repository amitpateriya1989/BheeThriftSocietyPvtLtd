package Model.Bean;

import java.util.Date;

public class ThriftIntBean {
	int ad_thrift_int_id;
	Date  created;
	 int createdby;
	Date  updated;
	 int updatedby ;
	 boolean isactive;
	 Date fromdate;
	Date  todate;
	int  ad_member_id;
	double  totalthrift_amt;
	double  total_intamt;
	int  ad_voucher_id;
	public int getAd_thrift_int_id() {
		return ad_thrift_int_id;
	}
	public void setAd_thrift_int_id(int ad_thrift_int_id) {
		this.ad_thrift_int_id = ad_thrift_int_id;
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
	public Date getFromdate() {
		return fromdate;
	}
	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}
	public Date getTodate() {
		return todate;
	}
	public void setTodate(Date todate) {
		this.todate = todate;
	}
	public int getAd_member_id() {
		return ad_member_id;
	}
	public void setAd_member_id(int ad_member_id) {
		this.ad_member_id = ad_member_id;
	}
	public double getTotalthrift_amt() {
		return totalthrift_amt;
	}
	public void setTotalthrift_amt(double totalthrift_amt) {
		this.totalthrift_amt = totalthrift_amt;
	}
	public double getTotal_intamt() {
		return total_intamt;
	}
	public void setTotal_intamt(double total_intamt) {
		this.total_intamt = total_intamt;
	}
	public int getAd_voucher_id() {
		return ad_voucher_id;
	}
	public void setAd_voucher_id(int ad_voucher_id) {
		this.ad_voucher_id = ad_voucher_id;
	}

}
