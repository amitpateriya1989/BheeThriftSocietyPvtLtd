package Model.Bean;

import java.io.Serializable;

public class MenuBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ad_menu_id;
	private String name;
	private String link;
	private String window_name;
	private String purpose;
	
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
	public String getWindow_name() {
		return window_name;
	}
	public void setWindow_name(String window_name) {
		this.window_name = window_name;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

}
