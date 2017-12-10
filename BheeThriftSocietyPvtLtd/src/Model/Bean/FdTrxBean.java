package Model.Bean;

import java.util.Date;

public class FdTrxBean {
	int ad_fd_trx_id ;
	Date  created ;
	int  createdby ;
	Date  updated ;
	 int updatedby;
	int  ad_member_id;
	int  fd_no;
	Double  fd_amt;
	Date  opening_date;
	 int ad_fd_roi_id;
	Date  maturity_date;
	 boolean isactive;
	 int ad_voucher_id;
	 String remark;
	public int getAd_fd_trx_id() {
		return ad_fd_trx_id;
	}
	public void setAd_fd_trx_id(int ad_fd_trx_id) {
		this.ad_fd_trx_id = ad_fd_trx_id;
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
	public int getAd_member_id() {
		return ad_member_id;
	}
	public void setAd_member_id(int ad_member_id) {
		this.ad_member_id = ad_member_id;
	}
	public int getFd_no() {
		return fd_no;
	}
	public void setFd_no(int fd_no) {
		this.fd_no = fd_no;
	}
	public Double getFd_amt() {
		return fd_amt;
	}
	public void setFd_amt(Double fd_amt) {
		this.fd_amt = fd_amt;
	}
	public Date getOpening_date() {
		return opening_date;
	}
	public void setOpening_date(Date opening_date) {
		this.opening_date = opening_date;
	}
	public int getAd_fd_roi_id() {
		return ad_fd_roi_id;
	}
	public void setAd_fd_roi_id(int ad_fd_roi_id) {
		this.ad_fd_roi_id = ad_fd_roi_id;
	}
	public Date getMaturity_date() {
		return maturity_date;
	}
	public void setMaturity_date(Date maturity_date) {
		this.maturity_date = maturity_date;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public int getAd_voucher_id() {
		return ad_voucher_id;
	}
	public void setAd_voucher_id(int ad_voucher_id) {
		this.ad_voucher_id = ad_voucher_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
