package Model.Bean;

import java.util.Date;

public class DividentBean {
	int	ad_divident_id;
	Date  created ;
	int  createdby ;
	Date  updated;
	int  updatedby;
	boolean  isactive;
	Date  fromdate;
	Date  todate ;
	int  ad_member_id;
	double  totalshare_amt;
	double  total_intamt;
	int ad_voucher_id;
	double total_amt ;
	double  share_qty ;
	double  conv_amt ;
	String  pay_status ;
	String  instrument_type ;
	Date  instrument_date ;
	int  instrument_no ;
	String  discription;
	String div_year;
	double roi;
	public int getAd_divident_id() {
		return ad_divident_id;
	}
	public void setAd_divident_id(int ad_divident_id) {
		this.ad_divident_id = ad_divident_id;
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
	public Date getFromdate() {
		return fromdate;
	}
	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}
	public Date getTodate() {
		return todate;
	}
	public void setTodate(Date todate) {
		this.todate = todate;
	}
	public int getAd_member_id() {
		return ad_member_id;
	}
	public void setAd_member_id(int ad_member_id) {
		this.ad_member_id = ad_member_id;
	}
	public double getTotalshare_amt() {
		return totalshare_amt;
	}
	public void setTotalshare_amt(double totalshare_amt) {
		this.totalshare_amt = totalshare_amt;
	}
	public double getTotal_intamt() {
		return total_intamt;
	}
	public void setTotal_intamt(double total_intamt) {
		this.total_intamt = total_intamt;
	}
	public int getAd_voucher_id() {
		return ad_voucher_id;
	}
	public void setAd_voucher_id(int ad_voucher_id) {
		this.ad_voucher_id = ad_voucher_id;
	}
	public double getTotal_amt() {
		return total_amt;
	}
	public void setTotal_amt(double total_amt) {
		this.total_amt = total_amt;
	}
	public double getShare_qty() {
		return share_qty;
	}
	public void setShare_qty(double share_qty) {
		this.share_qty = share_qty;
	}
	public double getConv_amt() {
		return conv_amt;
	}
	public void setConv_amt(double conv_amt) {
		this.conv_amt = conv_amt;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	public String getInstrument_type() {
		return instrument_type;
	}
	public void setInstrument_type(String instrument_type) {
		this.instrument_type = instrument_type;
	}
	public Date getInstrument_date() {
		return instrument_date;
	}
	public void setInstrument_date(Date instrument_date) {
		this.instrument_date = instrument_date;
	}
	public int getInstrument_no() {
		return instrument_no;
	}
	public void setInstrument_no(int instrument_no) {
		this.instrument_no = instrument_no;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getDiv_year() {
		return div_year;
	}
	public void setDiv_year(String div_year) {
		this.div_year = div_year;
	}
	public double getRoi() {
		return roi;
	}
	public void setRoi(double roi) {
		this.roi = roi;
	}

}
