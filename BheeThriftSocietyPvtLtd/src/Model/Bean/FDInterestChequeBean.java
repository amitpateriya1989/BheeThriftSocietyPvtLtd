package Model.Bean;

import java.util.Date;

public class FDInterestChequeBean {
	int ad_fd_int_cheque_detail_id ;
	Date  created ;
	int  createdby ;
	Date  updated ;
	int  updatedby ;
	boolean  isactive ;
	String  cheque_no ;
	Date  cheque_date ;
	String  branch ;
	double  cheque_amt ;
	int  ad_fd_trx_id;
	
	public int getAd_fd_int_cheque_detail_id() {
		return ad_fd_int_cheque_detail_id;
	}
	public void setAd_fd_int_cheque_detail_id(int ad_fd_int_cheque_detail_id) {
		this.ad_fd_int_cheque_detail_id = ad_fd_int_cheque_detail_id;
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
	public String getCheque_no() {
		return cheque_no;
	}
	public void setCheque_no(String cheque_no) {
		this.cheque_no = cheque_no;
	}
	public Date getCheque_date() {
		return cheque_date;
	}
	public void setCheque_date(Date cheque_date) {
		this.cheque_date = cheque_date;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public double getCheque_amt() {
		return cheque_amt;
	}
	public void setCheque_amt(double cheque_amt) {
		this.cheque_amt = cheque_amt;
	}
	public int getAd_fd_trx_id() {
		return ad_fd_trx_id;
	}
	public void setAd_fd_trx_id(int ad_fd_trx_id) {
		this.ad_fd_trx_id = ad_fd_trx_id;
	}
}
