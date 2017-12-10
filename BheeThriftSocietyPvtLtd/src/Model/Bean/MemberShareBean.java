package Model.Bean;

import java.util.Date;

public class MemberShareBean {
	
	private int ad_member_share_id;
	private int ad_member_id;
	private Date  created ;
	private int  createdby ;
	private Date updated ;
	private int updatedby ;
	private Date date_of_allocation ;
	private String  trx_by ;
	private Date  chk_dd_date ;
	private int  chk_dd_no ;
	private double  share_amt;
	private int  qnt_of_share ;
	private int  share_no_form ;
	private int  share_no_to ;
	private boolean  isactive ;
	private int batch_no;
	private int loan_trx_id;
	private int ad_voucher_id;
	private String status;
	private String amt_in_words;
	private String pay_type ;
	private Date  payment_date;
	
	
	public int getAd_member_share_id() {
		return ad_member_share_id;
	}
	public void setAd_member_share_id(int ad_member_share_id) {
		this.ad_member_share_id = ad_member_share_id;
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
	public Date getDate_of_allocation() {
		return date_of_allocation;
	}
	public void setDate_of_allocation(Date date_of_allocation) {
		this.date_of_allocation = date_of_allocation;
	}
	public String getTrx_by() {
		return trx_by;
	}
	public void setTrx_by(String trx_by) {
		this.trx_by = trx_by;
	}
	public Date getChk_dd_date() {
		return chk_dd_date;
	}
	public void setChk_dd_date(Date chk_dd_date) {
		this.chk_dd_date = chk_dd_date;
	}
	public int getChk_dd_no() {
		return chk_dd_no;
	}
	public void setChk_dd_no(int chk_dd_no) {
		this.chk_dd_no = chk_dd_no;
	}
	public double getShare_amt() {
		return share_amt;
	}
	public void setShare_amt(double share_amt) {
		this.share_amt = share_amt;
	}
	public int getQnt_of_share() {
		return qnt_of_share;
	}
	public void setQnt_of_share(int qnt_of_share) {
		this.qnt_of_share = qnt_of_share;
	}
	public int getShare_no_form() {
		return share_no_form;
	}
	public void setShare_no_form(int share_no_form) {
		this.share_no_form = share_no_form;
	}
	public int getShare_no_to() {
		return share_no_to;
	}
	public void setShare_no_to(int share_no_to) {
		this.share_no_to = share_no_to;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public int getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(int batch_no) {
		this.batch_no = batch_no;
	}
	public int getAd_voucher_id() {
		return ad_voucher_id;
	}
	public void setAd_voucher_id(int ad_voucher_id) {
		this.ad_voucher_id = ad_voucher_id;
	}
	public int getLoan_trx_id() {
		return loan_trx_id;
	}
	public void setLoan_trx_id(int loan_trx_id) {
		this.loan_trx_id = loan_trx_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAmt_in_words() {
		return amt_in_words;
	}
	public void setAmt_in_words(String amt_in_words) {
		this.amt_in_words = amt_in_words;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public Date getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

}
