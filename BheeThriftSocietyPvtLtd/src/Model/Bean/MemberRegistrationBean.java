package Model.Bean;

import java.util.Date;
import java.util.List;

public class MemberRegistrationBean {
	private int ad_member_id ;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive ;
	private String name ;
	private String father ;
	private String husband ;
	private Date dob ;
	private CategoryBean category;
	private GenderBean gender;
	private MemberTypeBean member_type;
	private MemberStatusBean member_status;
	private SalutationBean salutation;
	private BankBranchBean branch;
	private DesignationBean designation;
	private DesignationTypeBean designation_type;
	private DesignationLevelBean designation_level;
	private DepartmentBean department;
	private List<MemberAddressBean> address;
	private List<NominationBean> nominee;
	private String pan_no ;
	private String aadhar_no ;
	private Date doa ;
	private Date doj ;
	private String service_duration ;
	private Date dor ;
	private String saving_ac_no ;
	private String photo ;
	private String signature ;
	private String id_proof ;
	private int ad_witness_id;
	private int ad_pf_no;
	private int ad_society_no;
	private String marital_status;
	private String cast;
	private int ad_salutation_id;
	private int ad_gender_id;
	private int ad_member_status_id;
	private String termination_status;
	private Date acc_close_date;
	private int witness_ad_society_id;
	private int ad_witness_mem_no;
	private String ad_witness_name;
	private String ad_witness_mobile;
	private String ad_witness_address;
	private String acc_closing_reason;
	private Date acc_closing_reason_date;
	private WitnessBean witness;
	private int ad_branch_id;
	private int ad_voucher_id;
	
	public int getAd_member_id() {
		return ad_member_id;
	}
	public void setAd_member_id(int ad_member_id) {
		this.ad_member_id = ad_member_id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getHusband() {
		return husband;
	}
	public void setHusband(String husband) {
		this.husband = husband;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getPan_no() {
		return pan_no;
	}
	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}
	public String getAadhar_no() {
		return aadhar_no;
	}
	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}
	public Date getDoa() {
		return doa;
	}
	public void setDoa(Date doa) {
		this.doa = doa;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public String getService_duration() {
		return service_duration;
	}
	public void setService_duration(String service_duration) {
		this.service_duration = service_duration;
	}
	public Date getDor() {
		return dor;
	}
	public void setDor(Date dor) {
		this.dor = dor;
	}
	
	public String getSaving_ac_no() {
		return saving_ac_no;
	}
	public void setSaving_ac_no(String saving_ac_no) {
		this.saving_ac_no = saving_ac_no;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getId_proof() {
		return id_proof;
	}
	public void setId_proof(String id_proof) {
		this.id_proof = id_proof;
	}
	
	public int getAd_witness_id() {
		return ad_witness_id;
	}
	public void setAd_witness_id(int ad_witness_id) {
		this.ad_witness_id = ad_witness_id;
	}
	public CategoryBean getCategory() {
		return category;
	}
	public void setCategory(CategoryBean category) {
		this.category = category;
	}
	public GenderBean getGender() {
		return gender;
	}
	public void setGender(GenderBean gender) {
		this.gender = gender;
	}
	public MemberTypeBean getMember_type() {
		return member_type;
	}
	public void setMember_type(MemberTypeBean member_type) {
		this.member_type = member_type;
	}
	public MemberStatusBean getMember_status() {
		return member_status;
	}
	public void setMember_status(MemberStatusBean member_status) {
		this.member_status = member_status;
	}
	public SalutationBean getSalutation() {
		return salutation;
	}
	public void setSalutation(SalutationBean salutation) {
		this.salutation = salutation;
	}
	public BankBranchBean getBranch() {
		return branch;
	}
	public void setBranch(BankBranchBean branch) {
		this.branch = branch;
	}
	public DesignationBean getDesignation() {
		return designation;
	}
	public void setDesignation(DesignationBean designation) {
		this.designation = designation;
	}
	public DesignationTypeBean getDesignation_type() {
		return designation_type;
	}
	public void setDesignation_type(DesignationTypeBean designation_type) {
		this.designation_type = designation_type;
	}
	public DesignationLevelBean getDesignation_level() {
		return designation_level;
	}
	public void setDesignation_level(DesignationLevelBean designation_level) {
		this.designation_level = designation_level;
	}
	public DepartmentBean getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentBean department) {
		this.department = department;
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
	public List<MemberAddressBean> getAddress() {
		return address;
	}
	public void setAddress(List<MemberAddressBean> address) {
		this.address = address;
	}
	public List<NominationBean> getNominee() {
		return nominee;
	}
	public void setNominee(List<NominationBean> nominee) {
		this.nominee = nominee;
	}
	
	public void addAddress(MemberAddressBean address){
        this.address.add(address);
        /*if(address.getMember() != this){
            address.setMember(this);
        }*/
    }
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public int getAd_salutation_id() {
		return ad_salutation_id;
	}
	public void setAd_salutation_id(int ad_salutation_id) {
		this.ad_salutation_id = ad_salutation_id;
	}
	public int getAd_gender_id() {
		return ad_gender_id;
	}
	public void setAd_gender_id(int ad_gender_id) {
		this.ad_gender_id = ad_gender_id;
	}
	public int getAd_member_status_id() {
		return ad_member_status_id;
	}
	public void setAd_member_status_id(int ad_member_status_id) {
		this.ad_member_status_id = ad_member_status_id;
	}
	public String getTermination_status() {
		return termination_status;
	}
	public void setTermination_status(String termination_status) {
		this.termination_status = termination_status;
	}
	public String getAd_witness_name() {
		return ad_witness_name;
	}
	public void setAd_witness_name(String ad_witness_name) {
		this.ad_witness_name = ad_witness_name;
	}
	public String getAd_witness_mobile() {
		return ad_witness_mobile;
	}
	public void setAd_witness_mobile(String ad_witness_mobile) {
		this.ad_witness_mobile = ad_witness_mobile;
	}
	public String getAd_witness_address() {
		return ad_witness_address;
	}
	public void setAd_witness_address(String ad_witness_address) {
		this.ad_witness_address = ad_witness_address;
	}
	public int getAd_witness_mem_no() {
		return ad_witness_mem_no;
	}
	public void setAd_witness_mem_no(int ad_witness_mem_no) {
		this.ad_witness_mem_no = ad_witness_mem_no;
	}
	public int getWitness_ad_society_id() {
		return witness_ad_society_id;
	}
	public void setWitness_ad_society_id(int witness_ad_society_id) {
		this.witness_ad_society_id = witness_ad_society_id;
	}
	public int getAd_voucher_id() {
		return ad_voucher_id;
	}
	public void setAd_voucher_id(int ad_voucher_id) {
		this.ad_voucher_id = ad_voucher_id;
	}
	public WitnessBean getWitness() {
		return witness;
	}
	public void setWitness(WitnessBean witness) {
		this.witness = witness;
	}
	public Date getAcc_close_date() {
		return acc_close_date;
	}
	public void setAcc_close_date(Date acc_close_date) {
		this.acc_close_date = acc_close_date;
	}
	public String getAcc_closing_reason() {
		return acc_closing_reason;
	}
	public void setAcc_closing_reason(String acc_closing_reason) {
		this.acc_closing_reason = acc_closing_reason;
	}
	public Date getAcc_closing_reason_date() {
		return acc_closing_reason_date;
	}
	public void setAcc_closing_reason_date(Date acc_closing_reason_date) {
		this.acc_closing_reason_date = acc_closing_reason_date;
	}
	public int getAd_branch_id() {
		return ad_branch_id;
	}
	public void setAd_branch_id(int ad_branch_id) {
		this.ad_branch_id = ad_branch_id;
	}
	
}
