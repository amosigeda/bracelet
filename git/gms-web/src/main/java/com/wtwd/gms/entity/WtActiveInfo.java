package com.wtwd.gms.entity;

import java.util.Date;

public class WtActiveInfo extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 792789539586752086L;

	private Long id;

    private String sn;

    private String devName;

    private Date activeTime;

    private String country;

    private String mobileOp;

    private String mobileModel;

    private String mobileBrand;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName == null ? null : devName.trim();
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getMobileOp() {
        return mobileOp;
    }

    public void setMobileOp(String mobileOp) {
        this.mobileOp = mobileOp == null ? null : mobileOp.trim();
    }

    public String getMobileModel() {
        return mobileModel;
    }

    public void setMobileModel(String mobileModel) {
        this.mobileModel = mobileModel == null ? null : mobileModel.trim();
    }

    public String getMobileBrand() {
        return mobileBrand;
    }

    public void setMobileBrand(String mobileBrand) {
        this.mobileBrand = mobileBrand == null ? null : mobileBrand.trim();
    }
}