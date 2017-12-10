package Model.Bean;

import java.util.Date;

public class JournalLedgerBean {
	private int ad_trx_id;
	private Date trx_date;
	private int ad_voucher_id;
	private String vno;
	private String voucher_status;
	private int ad_account_id;
	private String ac_no;
	private String ac_name;
	private String member_name;
	private String voucher_description;
	private double dramt;
	private double cramt;
	private String narration;
	private String trx_status;
	private String trx_remark;
	private String vtype;
	private int ad_society_no;
	public int getAd_trx_id() {
		return ad_trx_id;
	}
	public void setAd_trx_id(int ad_trx_id) {
		this.ad_trx_id = ad_trx_id;
	}
	public Date getTrx_date() {
		return trx_date;
	}
	public void setTrx_date(Date trx_date) {
		this.trx_date = trx_date;
	}
	public int getAd_voucher_id() {
		return ad_voucher_id;
	}
	public void setAd_voucher_id(int ad_voucher_id) {
		this.ad_voucher_id = ad_voucher_id;
	}
	public String getVno() {
		return vno;
	}
	public void setVno(String vno) {
		this.vno = vno;
	}
	public String getVoucher_status() {
		return voucher_status;
	}
	public void setVoucher_status(String voucher_status) {
		this.voucher_status = voucher_status;
	}
	public int getAd_account_id() {
		return ad_account_id;
	}
	public void setAd_account_id(int ad_account_id) {
		this.ad_account_id = ad_account_id;
	}
	public String getAc_no() {
		return ac_no;
	}
	public void setAc_no(String ac_no) {
		this.ac_no = ac_no;
	}
	public String getAc_name() {
		return ac_name;
	}
	public void setAc_name(String ac_name) {
		this.ac_name = ac_name;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getVoucher_description() {
		return voucher_description;
	}
	public void setVoucher_description(String voucher_description) {
		this.voucher_description = voucher_description;
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
	public String getTrx_status() {
		return trx_status;
	}
	public void setTrx_status(String trx_status) {
		this.trx_status = trx_status;
	}
	public String getTrx_remark() {
		return trx_remark;
	}
	public void setTrx_remark(String trx_remark) {
		this.trx_remark = trx_remark;
	}
	public String getVtype() {
		return vtype;
	}
	public void setVtype(String vtype) {
		this.vtype = vtype;
	}
	public int getAd_society_no() {
		return ad_society_no;
	}
	public void setAd_society_no(int ad_society_no) {
		this.ad_society_no = ad_society_no;
	}
}
