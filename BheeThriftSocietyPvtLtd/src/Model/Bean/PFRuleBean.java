package Model.Bean;

import java.util.Date;

public class PFRuleBean {
	private int ad_pf_rule_id ;
	private Date effective_from;
	private Date effective_to;
	private double epf_emp_share ;
	private double epf_employer_share ;
	private double eps_employer_share ;
	private double edli_charges ;
	private double epf_admin_charges ;
	private double edli_admin_charges ;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive;
	
	public int getAd_pf_rule_id() {
		return ad_pf_rule_id;
	}
	public void setAd_pf_rule_id(int ad_pf_rule_id) {
		this.ad_pf_rule_id = ad_pf_rule_id;
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
	public double getEpf_emp_share() {
		return epf_emp_share;
	}
	public void setEpf_emp_share(double epf_emp_share) {
		this.epf_emp_share = epf_emp_share;
	}
	public double getEpf_employer_share() {
		return epf_employer_share;
	}
	public void setEpf_employer_share(double epf_employer_share) {
		this.epf_employer_share = epf_employer_share;
	}
	public double getEps_employer_share() {
		return eps_employer_share;
	}
	public void setEps_employer_share(double eps_employer_share) {
		this.eps_employer_share = eps_employer_share;
	}
	public double getEdli_charges() {
		return edli_charges;
	}
	public void setEdli_charges(double edli_charges) {
		this.edli_charges = edli_charges;
	}
	public double getEpf_admin_charges() {
		return epf_admin_charges;
	}
	public void setEpf_admin_charges(double epf_admin_charges) {
		this.epf_admin_charges = epf_admin_charges;
	}
	public double getEdli_admin_charges() {
		return edli_admin_charges;
	}
	public void setEdli_admin_charges(double edli_admin_charges) {
		this.edli_admin_charges = edli_admin_charges;
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

}
