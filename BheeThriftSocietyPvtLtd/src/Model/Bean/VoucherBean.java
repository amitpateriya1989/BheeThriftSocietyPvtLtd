package Model.Bean;

import java.util.Date;

public class VoucherBean {
	private int ad_voucher_id ;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive ;
	private String vno ;
	private String status ;
	private Date trx_date ;
	private String vtype ;
	private String vfrom ;
	private double vamt ;
	private String voucher_type;
	private String description ;
	private String instrument_from ;
	private String instrument_no ;
	private Date instrument_date;
	private int trx_no;
	private double instrument_amt;
	private String amt_in_words;
	
	public int getAd_voucher_id() {
		return ad_voucher_id;
	}
	public void setAd_voucher_id(int ad_voucher_id) {
		this.ad_voucher_id = ad_voucher_id;
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
	public String getVno() {
		return vno;
	}
	public void setVno(String vno) {
		this.vno = vno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getTrx_date() {
		return trx_date;
	}
	public void setTrx_date(Date trx_date) {
		this.trx_date = trx_date;
	}
	public String getVtype() {
		return vtype;
	}
	public void setVtype(String vtype) {
		this.vtype = vtype;
	}
	public String getVfrom() {
		return vfrom;
	}
	public void setVfrom(String vfrom) {
		this.vfrom = vfrom;
	}
	public double getVamt() {
		return vamt;
	}
	public void setVamt(double vamt) {
		this.vamt = vamt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getInstrument_from() {
		return instrument_from;
	}
	public void setInstrument_from(String instrument_from) {
		this.instrument_from = instrument_from;
	}
	public String getInstrument_no() {
		return instrument_no;
	}
	public void setInstrument_no(String instrument_no) {
		this.instrument_no = instrument_no;
	}
	public Date getInstrument_date() {
		return instrument_date;
	}
	public void setInstrument_date(Date instrument_date) {
		this.instrument_date = instrument_date;
	}
	public String getVoucher_type() {
		return voucher_type;
	}
	public void setVoucher_type(String voucher_type) {
		this.voucher_type = voucher_type;
	}
	public int getTrx_no() {
		return trx_no;
	}
	public void setTrx_no(int trx_no) {
		this.trx_no = trx_no;
	}
	public double getInstrument_amt() {
		return instrument_amt;
	}
	public void setInstrument_amt(double instrument_amt) {
		this.instrument_amt = instrument_amt;
	}
	public String getAmt_in_words() {
		return amt_in_words;
	}
	public void setAmt_in_words(String amt_in_words) {
		this.amt_in_words = amt_in_words;
	}
}
