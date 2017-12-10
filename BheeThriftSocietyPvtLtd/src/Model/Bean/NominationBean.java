package Model.Bean;

import java.util.Date;

public class NominationBean {

	private int ad_nomination_id ;
	private RelationBean relation;
	private GenderBean gender;
	private SalutationBean salutation;
	private int ad_member_id;
	private Date created ;
	private int createdby ;
	private Date updated ;
	private int updatedby ;
	private boolean isactive ;
	private String name;
	private Date dob ;
	private String age ;
	private String photo ;
	private String sign ;
	private String id_proof;
	private int nominee_no;
	private String guardian;
	
	
	public int getAd_nomination_id() {
		return ad_nomination_id;
	}
	public void setAd_nomination_id(int ad_nomination_id) {
		this.ad_nomination_id = ad_nomination_id;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public RelationBean getRelation() {
		return relation;
	}
	public void setRelation(RelationBean relation) {
		this.relation = relation;
	}
	public GenderBean getGender() {
		return gender;
	}
	public void setGender(GenderBean gender) {
		this.gender = gender;
	}
	public SalutationBean getSalutation() {
		return salutation;
	}
	public void setSalutation(SalutationBean salutation) {
		this.salutation = salutation;
	}
	public String getId_proof() {
		return id_proof;
	}
	public void setId_proof(String id_proof) {
		this.id_proof = id_proof;
	}
	public int getAd_member_id() {
		return ad_member_id;
	}
	public void setAd_member_id(int ad_member_id) {
		this.ad_member_id = ad_member_id;
	}
	public int getNominee_no() {
		return nominee_no;
	}
	public void setNominee_no(int nominee_no) {
		this.nominee_no = nominee_no;
	}
	public String getGuardian() {
		return guardian;
	}
	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}
}
