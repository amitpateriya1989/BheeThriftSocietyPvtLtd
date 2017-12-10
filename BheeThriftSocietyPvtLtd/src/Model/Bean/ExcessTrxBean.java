package Model.Bean;

import java.util.Date;

public class ExcessTrxBean {
	private int ad_excess_trx_id ;
	private Date  created ;
	private int  createdby ;
	private Date  updated ;
	private int  updatedby ;
	private boolean  isactive ;
	private Date  trx_date ;
	private int  ad_member_id ;
	private double  excess_amt ;
	private double withdrawal;
	private double  balance ;
	private int  ad_voucher_id ;
	private String  status ;
	private String  detail;
	
	public int getAd_excess_trx_id() {
		return ad_excess_trx_id;
	}
	public void setAd_excess_trx_id(int ad_excess_trx_id) {
		this.ad_excess_trx_id = ad_excess_trx_id;
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
	public double getExcess_amt() {
		return excess_amt;
	}
	public void setExcess_amt(double excess_amt) {
		this.excess_amt = excess_amt;
	}
	public double getWithdrawal() {
		return withdrawal;
	}
	public void setWithdrawal(double withdrawal) {
		this.withdrawal = withdrawal;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}