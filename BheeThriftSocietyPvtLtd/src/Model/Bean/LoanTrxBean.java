package Model.Bean;

import java.util.Date;

public class LoanTrxBean {
	int loan_trx_id;
	int  ad_member_id;
	 int loan_type;
	  int loan_cataegory;
	double  loan_amt;
	double intrest_rate;
	 int period_month;
	Date  issue_date ;
	 Date end_date ;
	Double  emi;
	int  witnes ;
	int  guaranter ;
	boolean  isactive ;
	Date created ;
	int  createdby ;
	Date  updated;
	int  updatedby ;
	int ad_voucher_id;
	String loan_purpose;
	String status;
	int ad_employee_id;
	double request_loan_amt;
	String amt_in_words;
	
	public int getLoan_trx_id() {
		return loan_trx_id;
	}
	public void setLoan_trx_id(int loan_trx_id) {
		this.loan_trx_id = loan_trx_id;
	}
	public int getAd_member_id() {
		return ad_member_id;
	}
	public void setAd_member_id(int ad_member_id) {
		this.ad_member_id = ad_member_id;
	}
	public int getLoan_type() {
		return loan_type;
	}
	public void setLoan_type(int loan_type) {
		this.loan_type = loan_type;
	}
	public int getLoan_cataegory() {
		return loan_cataegory;
	}
	public void setLoan_cataegory(int loan_cataegory) {
		this.loan_cataegory = loan_cataegory;
	}
	public double getLoan_amt() {
		return loan_amt;
	}
	public void setLoan_amt(double loan_amt) {
		this.loan_amt = loan_amt;
	}
	public double getIntrest_rate() {
		return intrest_rate;
	}
	public void setIntrest_rate(double intrest_rate) {
		this.intrest_rate = intrest_rate;
	}
	public int getPeriod_month() {
		return period_month;
	}
	public void setPeriod_month(int period_month) {
		this.period_month = period_month;
	}
	public Date getissue_date() {
		return issue_date;
	}
	public void setissue_date(Date issue_date) {
		this.issue_date = issue_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Double getEmi() {
		return emi;
	}
	public void setEmi(Double emi) {
		this.emi = emi;
	}
	public int getWitnes() {
		return witnes;
	}
	public void setWitnes(int witnes) {
		this.witnes = witnes;
	}
	public int getGuaranter() {
		return guaranter;
	}
	public void setGuaranter(int guaranter) {
		this.guaranter = guaranter;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
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
	public int getAd_voucher_id() {
		return ad_voucher_id;
	}
	public void setAd_voucher_id(int ad_voucher_id) {
		this.ad_voucher_id = ad_voucher_id;
	}
	public Date getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}
	public String getLoan_purpose() {
		return loan_purpose;
	}
	public void setLoan_purpose(String loan_purpose) {
		this.loan_purpose = loan_purpose;
	}
	public int getAd_employee_id() {
		return ad_employee_id;
	}
	public void setAd_employee_id(int ad_employee_id) {
		this.ad_employee_id = ad_employee_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getRequest_loan_amt() {
		return request_loan_amt;
	}
	public void setRequest_loan_amt(double request_loan_amt) {
		this.request_loan_amt = request_loan_amt;
	}
	public String getAmt_in_words() {
		return amt_in_words;
	}
	public void setAmt_in_words(String amt_in_words) {
		this.amt_in_words = amt_in_words;
	}
	
}
