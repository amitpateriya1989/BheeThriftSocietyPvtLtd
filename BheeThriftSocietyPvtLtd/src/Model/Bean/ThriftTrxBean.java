package Model.Bean;

import java.util.Date;

public class ThriftTrxBean {
	int ad_thrift_trx_id ;
	Date  created ;
	int  createdby ;
	Date  updated ;
	int  updatedby ;
	boolean  isactive ;
	Date  trx_date ;
	int  ad_member_id ;
	double  thrift_amt ;
	int  ad_voucher_id;
	String status;
	double thrift_int;
	double balance;
	double withdrawal;
	String discription;
	public int getAd_thrift_trx_id() {
		return ad_thrift_trx_id;
	}
	public void setAd_thrift_trx_id(int ad_thrift_trx_id) {
		this.ad_thrift_trx_id = ad_thrift_trx_id;
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
	public Date getTrx_date() {
		return trx_date;
	}
	public void setTrx_date(Date trx_date) {
		this.trx_date = trx_date;
	}
	public int getAd_member_id() {
		return ad_member_id;
	}
	public void setAd_member_id(int ad_member_id) {
		this.ad_member_id = ad_member_id;
	}
	public double getThrift_amt() {
		return thrift_amt;
	}
	public void setThrift_amt(double thrift_amt) {
		this.thrift_amt = thrift_amt;
	}
	public int getAd_voucher_id() {
		return ad_voucher_id;
	}
	public void setAd_voucher_id(int ad_voucher_id) {
		this.ad_voucher_id = ad_voucher_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getThrift_int() {
		return thrift_int;
	}
	public void setThrift_int(double thrift_int) {
		this.thrift_int = thrift_int;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getWithdrawal() {
		return withdrawal;
	}
	public void setWithdrawal(double withdrawal) {
		this.withdrawal = withdrawal;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
}
