package Model.Bean;

import java.util.Date;

public class MemberRegistrationMasterBean {
	 private int ad_member_registration_master_id ;
	 private Date created ;
	 private int createdby ;
	 private Date updated ;
	 private int updatedby ;
	 private boolean isactive ;
	 private double membership_fees;
	 private double fgds_fund ;
	 private double dcf ;
	 private int share;
	 
	public int getAd_member_registration_master_id() {
		return ad_member_registration_master_id;
	}
	public void setAd_member_registration_master_id(
			int ad_member_registration_master_id) {
		this.ad_member_registration_master_id = ad_member_registration_master_id;
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
	public boolean getIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public double getMembership_fees() {
		return membership_fees;
	}
	public void setMembership_fees(double membership_fees) {
		this.membership_fees = membership_fees;
	}
	public double getFgds_fund() {
		return fgds_fund;
	}
	public void setFgds_fund(double fgds_fund) {
		this.fgds_fund = fgds_fund;
	}
	public double getDcf() {
		return dcf;
	}
	public void setDcf(double dcf) {
		this.dcf = dcf;
	}
	public int getShare() {
		return share;
	}
	public void setShare(int share) {
		this.share = share;
	}

}
