package Model.Bean;

import java.util.Date;

public class PTRuleBean {
	private int ad_pt_rule_id ;
	private Date effective_from;
	private Date effective_to;
	private double min_amt ;
	private double max_amt ;
	private double regular_charges ;
	private double march_specific_charges ;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive;
	
	public int getAd_pt_rule_id() {
		return ad_pt_rule_id;
	}
	public void setAd_pt_rule_id(int ad_pt_rule_id) {
		this.ad_pt_rule_id = ad_pt_rule_id;
	}
	
	public double getMin_amt() {
		return min_amt;
	}
	public void setMin_amt(double min_amt) {
		this.min_amt = min_amt;
	}
	public double getMax_amt() {
		return max_amt;
	}
	public void setMax_amt(double max_amt) {
		this.max_amt = max_amt;
	}
	public double getRegular_charges() {
		return regular_charges;
	}
	public void setRegular_charges(double regular_charges) {
		this.regular_charges = regular_charges;
	}
	public double getMarch_specific_charges() {
		return march_specific_charges;
	}
	public void setMarch_specific_charges(double march_specific_charges) {
		this.march_specific_charges = march_specific_charges;
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
	public Date getEffective_from() {
		return effective_from;
	}
	public void setEffective_from(Date effective_from) {
		this.effective_from = effective_from;
	}
	public Date getEffective_to() {
		return effective_to;
	}
	public void setEffective_to(Date effective_to) {
		this.effective_to = effective_to;
	}
}
