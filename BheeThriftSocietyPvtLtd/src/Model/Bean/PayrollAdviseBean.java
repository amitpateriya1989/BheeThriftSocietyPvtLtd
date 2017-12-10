package Model.Bean;

public class PayrollAdviseBean {
	int ad_member_id; 
	boolean isactive; 
	int ad_salutation_id; 
	int ad_member_status_id; 
	int ad_designation_type_id; 
	int ad_pf_no; 
	int ad_society_no; 
	String salutation; 
    String name; 
    String member_status; 
    int ad_branch_id; 
    String branch; 
    String saving_ac_no; 
    double membership_fees; 
    double fgds_fund; 
    double dcf, share; 
    double mainloan_emi; 
    double festivalloan_emi;
    
	public int getAd_member_id() {
		return ad_member_id;
	}
	public void setAd_member_id(int ad_member_id) {
		this.ad_member_id = ad_member_id;
	}
	public boolean getIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public int getAd_salutation_id() {
		return ad_salutation_id;
	}
	public void setAd_salutation_id(int ad_salutation_id) {
		this.ad_salutation_id = ad_salutation_id;
	}
	public int getAd_member_status_id() {
		return ad_member_status_id;
	}
	public void setAd_member_status_id(int ad_member_status_id) {
		this.ad_member_status_id = ad_member_status_id;
	}
	public int getAd_designation_type_id() {
		return ad_designation_type_id;
	}
	public void setAd_designation_type_id(int ad_designation_type_id) {
		this.ad_designation_type_id = ad_designation_type_id;
	}
	public int getAd_pf_no() {
		return ad_pf_no;
	}
	public void setAd_pf_no(int ad_pf_no) {
		this.ad_pf_no = ad_pf_no;
	}
	public int getAd_society_no() {
		return ad_society_no;
	}
	public void setAd_society_no(int ad_society_no) {
		this.ad_society_no = ad_society_no;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMember_status() {
		return member_status;
	}
	public void setMember_status(String member_status) {
		this.member_status = member_status;
	}
	public int getAd_branch_id() {
		return ad_branch_id;
	}
	public void setAd_branch_id(int ad_branch_id) {
		this.ad_branch_id = ad_branch_id;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getSaving_ac_no() {
		return saving_ac_no;
	}
	public void setSaving_ac_no(String saving_ac_no) {
		this.saving_ac_no = saving_ac_no;
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
	public double getShare() {
		return share;
	}
	public void setShare(double share) {
		this.share = share;
	}
	public double getMainloan_emi() {
		return mainloan_emi;
	}
	public void setMainloan_emi(double mainloan_emi) {
		this.mainloan_emi = mainloan_emi;
	}
	public double getFestivalloan_emi() {
		return festivalloan_emi;
	}
	public void setFestivalloan_emi(double festivalloan_emi) {
		this.festivalloan_emi = festivalloan_emi;
	}
}
