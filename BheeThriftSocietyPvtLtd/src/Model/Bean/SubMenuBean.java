package Model.Bean;

import java.io.Serializable;

public class SubMenuBean implements Serializable{
	 
	private int ad_sub_menu_id;
	private int ad_menu_id;
	private String name;
	private String link;
	
	public int getAd_sub_menu_id() {
		return ad_sub_menu_id;
	}
	public void setAd_sub_menu_id(int ad_sub_menu_id) {
		this.ad_sub_menu_id = ad_sub_menu_id;
	}
	public int getAd_menu_id() {
		return ad_menu_id;
	}
	public void setAd_menu_id(int ad_menu_id) {
		this.ad_menu_id = ad_menu_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
