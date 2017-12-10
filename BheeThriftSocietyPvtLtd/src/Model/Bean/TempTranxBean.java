package Model.Bean;

import java.util.Date;

public class TempTranxBean {
	 private int ad_trx_temp_id ;
	 private Date created ;
	 private int createdby ;
	 private Date updated ;
	 private int updatedby ;
	 private boolean isactive ;
	 private VoucherBean voucher;
	 private LedgerAccountBean ledger;
	 private MemberRegistrationBean member;
	 private Date trx_date ;
	 private double dramt ;
	 private double cramt ;
	 private String narration ;
	
	 private int ad_voucher_id;
	 
	public int getAd_trx_temp_id() {
		return ad_trx_temp_id;
	}
	public void setAd_trx_temp_id(int ad_trx_temp_id) {
		this.ad_trx_temp_id = ad_trx_temp_id;
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
	
	public Date getTrx_date() {
		return trx_date;
	}
	public void setTrx_date(Date trx_date) {
		this.trx_date = trx_date;
	}
	public double getDramt() {
		return dramt;
	}
	public void setDramt(double dramt) {
		this.dramt = dramt;
	}
	public double getCramt() {
		return cramt;
	}
	public void setCramt(double cramt) {
		this.cramt = cramt;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	
	public VoucherBean getVoucher() {
		return voucher;
	}
	public void setVoucher(VoucherBean voucher) {
		this.voucher = voucher;
	}
	public LedgerAccountBean getLedger() {
		return ledger;
	}
	public void setLedger(LedgerAccountBean ledger) {
		this.ledger = ledger;
	}
	public MemberRegistrationBean getMember() {
		return member;
	}
	public void setMember(MemberRegistrationBean member) {
		this.member = member;
	}
	public int getAd_voucher_id() {
		return ad_voucher_id;
	}
	public void setAd_voucher_id(int ad_voucher_id) {
		this.ad_voucher_id = ad_voucher_id;
	}

}
