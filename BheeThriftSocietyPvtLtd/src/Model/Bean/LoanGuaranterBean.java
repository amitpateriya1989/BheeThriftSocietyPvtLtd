package Model.Bean;

import java.util.Date;

public class LoanGuaranterBean {
	int	ad_loan_guaranter_id;
	int  ad_member_id;
	Date  created;
	int	  createdby ;
	Date  updated;
	int  updatedby ;
	int  chk_qnt ;
	int  chk_no_form ;
	int  chk_no_to;
	Date  chk_date;
	String  chk_bank_name;
	String  chk_bank_code;
	boolean isactive;
	int loan_trx_id ;
	
	int ad_employee_id;
	public int getAd_loan_guaranter_id() {
		return ad_loan_guaranter_id;
	}
	public void setAd_loan_guaranter_id(int ad_loan_guaranter_id) {
		this.ad_loan_guaranter_id = ad_loan_guaranter_id;
	}
	public int getAd_member_id() {
		return ad_member_id;
	}
	public void setAd_member_id(int ad_member_id) {
		this.ad_member_id = ad_member_id;
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
	public int getChk_qnt() {
		return chk_qnt;
	}
	public void setChk_qnt(int chk_qnt) {
		this.chk_qnt = chk_qnt;
	}
	public int getChk_no_form() {
		return chk_no_form;
	}
	public void setChk_no_form(int chk_no_form) {
		this.chk_no_form = chk_no_form;
	}
	public int getChk_no_to() {
		return chk_no_to;
	}
	public void setChk_no_to(int chk_no_to) {
		this.chk_no_to = chk_no_to;
	}
	public Date getChk_date() {
		return chk_date;
	}
	public void setChk_date(Date chk_date) {
		this.chk_date = chk_date;
	}
	public String getChk_bank_name() {
		return chk_bank_name;
	}
	public void setChk_bank_name(String chk_bank_name) {
		this.chk_bank_name = chk_bank_name;
	}
	public String getChk_bank_code() {
		return chk_bank_code;
	}
	public void setChk_bank_code(String chk_bank_code) {
		this.chk_bank_code = chk_bank_code;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public int getLoan_trx_id() {
		return loan_trx_id;
	}
	public void setLoan_trx_id(int loan_trx_id) {
		this.loan_trx_id = loan_trx_id;
	}
	public int getAd_employee_id() {
		return ad_employee_id;
	}
	public void setAd_employee_id(int ad_employee_id) {
		this.ad_employee_id = ad_employee_id;
	}

}
