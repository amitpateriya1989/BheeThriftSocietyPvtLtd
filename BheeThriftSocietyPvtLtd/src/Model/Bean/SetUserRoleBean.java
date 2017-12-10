package Model.Bean;

public class SetUserRoleBean {
	private int set_user_role_id ;
	private int ad_role_id ;
	private int ad_user_id ;
	private boolean isactive;
	
	public int getSet_user_role_id() {
		return set_user_role_id;
	}
	public void setSet_user_role_id(int set_user_role_id) {
		this.set_user_role_id = set_user_role_id;
	}
	public int getAd_role_id() {
		return ad_role_id;
	}
	public void setAd_role_id(int ad_role_id) {
		this.ad_role_id = ad_role_id;
	}
	public int getAd_user_id() {
		return ad_user_id;
	}
	public void setAd_user_id(int ad_user_id) {
		this.ad_user_id = ad_user_id;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

}
