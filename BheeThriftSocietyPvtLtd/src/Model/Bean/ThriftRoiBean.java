package Model.Bean;

import java.util.Date;

public class ThriftRoiBean {
	
int	ad_thrift_roi_id ;
Date created;
int	  createdby;
Date updeted ;
int  updatedby ;
double  roi ;
Date  effectivefromdate ;
Date  effectivetodate ;
boolean  isactive ;
double ratio;

public int getAd_thrift_roi_id() {
	return ad_thrift_roi_id;
}
public void setAd_thrift_roi_id(int ad_thrift_roi_id) {
	this.ad_thrift_roi_id = ad_thrift_roi_id;
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
public Date getUpdeted() {
	return updeted;
}
public void setUpdeted(Date updeted) {
	this.updeted = updeted;
}
public int getUpdatedby() {
	return updatedby;
}
public void setUpdatedby(int updatedby) {
	this.updatedby = updatedby;
}
public double getRoi() {
	return roi;
}
public void setRoi(double roi) {
	this.roi = roi;
}
public Date getEffectivefromdate() {
	return effectivefromdate;
}
public void setEffectivefromdate(Date effectivefromdate) {
	this.effectivefromdate = effectivefromdate;
}
public Date getEffectivetodate() {
	return effectivetodate;
}
public void setEffectivetodate(Date effectivetodate) {
	this.effectivetodate = effectivetodate;
}
public boolean isIsactive() {
	return isactive;
}
public void setIsactive(boolean isactive) {
	this.isactive = isactive;
}
public double getRatio() {
	return ratio;
}
public void setRatio(double ratio) {
	this.ratio = ratio;
}

}
