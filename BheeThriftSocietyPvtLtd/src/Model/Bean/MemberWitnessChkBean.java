package Model.Bean;

import java.util.Date;

public class MemberWitnessChkBean {
	int ad_loan_member_witness_chk_id ;
	 Date created ;
	int  createdby ;
	 Date updated ;
	int  updatedby ;
	int  chkno ;
	String  bankname ;
	String  branchname ;
	boolean  isactive;
	int loan_trx_id;
	public int getAd_loan_member_witness_chk_id() {
		return ad_loan_member_witness_chk_id;
	}
	public void setAd_loan_member_witness_chk_id(int ad_loan_member_witness_chk_id) {
		this.ad_loan_member_witness_chk_id = ad_loan_member_witness_chk_id;
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
	public int getChkno() {
		return chkno;
	}
	public void setChkno(int chkno) {
		this.chkno = chkno;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
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

}
