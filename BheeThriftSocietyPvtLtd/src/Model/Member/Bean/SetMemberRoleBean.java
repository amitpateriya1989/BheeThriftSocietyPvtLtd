package Model.Member.Bean;

public class SetMemberRoleBean {
	private int set_member_role_id ;
	private int ad_role_id ;
	private int ad_member_login_id ;
	private boolean isactive;
	
	public int getSet_member_role_id() {
		return set_member_role_id;
	}
	public void setSet_member_role_id(int set_member_role_id) {
		this.set_member_role_id = set_member_role_id;
	}
	public int getAd_role_id() {
		return ad_role_id;
	}
	public void setAd_role_id(int ad_role_id) {
		this.ad_role_id = ad_role_id;
	}
	public int getAd_member_login_id() {
		return ad_member_login_id;
	}
	public void setAd_member_login_id(int ad_member_login_id) {
		this.ad_member_login_id = ad_member_login_id;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
}
