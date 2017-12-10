package Model.Bean;

import java.util.Date;

public class FdrediptionBean {
	int ad_fd_rediption_id;
	 int ad_member_id;
	int  ad_fd_trx_id;
	Date  created ;
	int  createdby ;
	Date updated;
	 int updatedby;
	double  fdamt ;
	double  payroi ;
	Date  maturitydate;
	double  payint ;
	double  totalpayamt ;
	int  ad_voucher_id;
	boolean  isactive;
	 String rediptiontype;
	public int getAd_fd_rediption_id() {
		return ad_fd_rediption_id;
	}
	public void setAd_fd_rediption_id(int ad_fd_rediption_id) {
		this.ad_fd_rediption_id = ad_fd_rediption_id;
	}
	public int getAd_member_id() {
		return ad_member_id;
	}
	public void setAd_member_id(int ad_member_id) {
		this.ad_member_id = ad_member_id;
	}
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
	public double getFdamt() {
		return fdamt;
	}
	public void setFdamt(double fdamt) {
		this.fdamt = fdamt;
	}
	public double getPayroi() {
		return payroi;
	}
	public void setPayroi(double payroi) {
		this.payroi = payroi;
	}
	public Date getMaturitydate() {
		return maturitydate;
	}
	public void setMaturitydate(Date maturitydate) {
		this.maturitydate = maturitydate;
	}
	public double getPayint() {
		return payint;
	}
	public void setPayint(double payint) {
		this.payint = payint;
	}
	public double getTotalpayamt() {
		return totalpayamt;
	}
	public void setTotalpayamt(double totalpayamt) {
		this.totalpayamt = totalpayamt;
	}
	public int getAd_voucher_id() {
		return ad_voucher_id;
	}
	public void setAd_voucher_id(int ad_voucher_id) {
		this.ad_voucher_id = ad_voucher_id;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public String getRediptiontype() {
		return rediptiontype;
	}
	public void setRediptiontype(String rediptiontype) {
		this.rediptiontype = rediptiontype;
	}
}
