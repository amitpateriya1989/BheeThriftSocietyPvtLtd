package Model.Bean;

import java.util.Date;

public class FinalPayBean {
	int ad_final_pay_id ;
	Date  updated ;
	int  updatedby;
	Date created;
	int createdby ;
	boolean isactive;
	double  fdgs_amt ;
	double  int_fdgs;
	double share_amt ;
	double  loan_amt;
	double  int_loan ;
	double  compensation_amt;
	int ad_member_id;
	int  ad_voucher_id ;
	String reson ;
	Date reson_date;
	Date  final_pay_date ;
	String amt_in_words;
	public int getAd_final_pay_id() {
		return ad_final_pay_id;
	}
	public void setAd_final_pay_id(int ad_final_pay_id) {
		this.ad_final_pay_id = ad_final_pay_id;
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
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public double getFdgs_amt() {
		return fdgs_amt;
	}
	public void setFdgs_amt(double fdgs_amt) {
		this.fdgs_amt = fdgs_amt;
	}
	public double getInt_fdgs() {
		return int_fdgs;
	}
	public void setInt_fdgs(double int_fdgs) {
		this.int_fdgs = int_fdgs;
	}
	public double getShare_amt() {
		return share_amt;
	}
	public void setShare_amt(double share_amt) {
		this.share_amt = share_amt;
	}
	public double getLoan_amt() {
		return loan_amt;
	}
	public void setLoan_amt(double loan_amt) {
		this.loan_amt = loan_amt;
	}
	public double getInt_loan() {
		return int_loan;
	}
	public void setInt_loan(double int_loan) {
		this.int_loan = int_loan;
	}
	public double getCompensation_amt() {
		return compensation_amt;
	}
	public void setCompensation_amt(double compensation_amt) {
		this.compensation_amt = compensation_amt;
	}
	public int getAd_member_id() {
		return ad_member_id;
	}
	public void setAd_member_id(int ad_member_id) {
		this.ad_member_id = ad_member_id;
	}
	public int getAd_voucher_id() {
		return ad_voucher_id;
	}
	public void setAd_voucher_id(int ad_voucher_id) {
		this.ad_voucher_id = ad_voucher_id;
	}
	public String getReson() {
		return reson;
	}
	public void setReson(String reson) {
		this.reson = reson;
	}
	public Date getReson_date() {
		return reson_date;
	}
	public void setReson_date(Date reson_date) {
		this.reson_date = reson_date;
	}
	public Date getFinal_pay_date() {
		return final_pay_date;
	}
	public void setFinal_pay_date(Date final_pay_date) {
		this.final_pay_date = final_pay_date;
	}
	public String getAmt_in_words() {
		return amt_in_words;
	}
	public void setAmt_in_words(String amt_in_words) {
		this.amt_in_words = amt_in_words;
	}

}
