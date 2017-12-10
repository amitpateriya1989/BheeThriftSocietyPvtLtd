package Model.Bean;

public class SetRoleAccessBean {
	private int set_role_access_id ;
	private int  ad_role_id ;
	private int  ad_client_id ;
	private int  ad_department_id;
	
	public int getSet_role_access_id() {
		return set_role_access_id;
	}
	public void setSet_role_access_id(int set_role_access_id) {
		this.set_role_access_id = set_role_access_id;
	}
	public int getAd_role_id() {
		return ad_role_id;
	}
	public void setAd_role_id(int ad_role_id) {
		this.ad_role_id = ad_role_id;
	}
	public int getAd_client_id() {
		return ad_client_id;
	}
	public void setAd_client_id(int ad_client_id) {
		this.ad_client_id = ad_client_id;
	}
	public int getAd_department_id() {
		return ad_department_id;
	}
	public void setAd_department_id(int ad_department_id) {
		this.ad_department_id = ad_department_id;
	}
}
