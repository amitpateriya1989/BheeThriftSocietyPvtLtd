package Model.Bean;

import java.util.Date;

public class LoanTrxDetailBean {

	private int ad_loan_trx_id ;
	private Date  created ;
	private int  createdby ;
	private Date  updated ;
	private int  updatedby ;
	private boolean  isactive ;
	private int  ad_member_id ;
	private int loan_trx_id ;
	private Date  trx_date ;
	private double deposit_amt;
	private double  interest_amt ;
	private double balance_amt ;
	private int ad_employee_id;
	private int ad_voucher_id;
	private double excess_amt;
	public int getAd_loan_trx_id() {
		return ad_loan_trx_id;
	}
	public void setAd_loan_trx_id(int ad_loan_trx_id) {
		this.ad_loan_trx_id = ad_loan_trx_id;
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
	public int getLoan_trx_id() {
		return loan_trx_id;
	}
	public void setLoan_trx_id(int loan_trx_id) {
		this.loan_trx_id = loan_trx_id;
	}
	public Date getTrx_date() {
		return trx_date;
	}
	public void setTrx_date(Date trx_date) {
		this.trx_date = trx_date;
	}
	public double getDeposit_amt() {
		return deposit_amt;
	}
	public void setDeposit_amt(double deposit_amt) {
		this.deposit_amt = deposit_amt;
	}
	
	
	public double getInterest_amt() {
		return interest_amt;
	}
	public void setInterest_amt(double interest_amt) {
		this.interest_amt = interest_amt;
	}
	public double getBalance_amt() {
		return balance_amt;
	}
	public void setBalance_amt(double balance_amt) {
		this.balance_amt = balance_amt;
	}
	public int getAd_employee_id() {
		return ad_employee_id;
	}
	public void setAd_employee_id(int ad_employee_id) {
		this.ad_employee_id = ad_employee_id;
	}
	public int getAd_voucher_id() {
		return ad_voucher_id;
	}
	public void setAd_voucher_id(int ad_voucher_id) {
		this.ad_voucher_id = ad_voucher_id;
	}
	public double getExcess_amt() {
		return excess_amt;
	}
	public void setExcess_amt(double excess_amt) {
		this.excess_amt = excess_amt;
	}
	
}
