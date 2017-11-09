package com.wtwd.gms.entity;

import java.util.Date;

public class WtActivationInfo extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String sn;

    private String devName;

    private Date activationTime;

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
		this.sn = sn;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public Date getActivationTime() {
		return activationTime;
	}

	public void setActivationTime(Date activeTime) {
		this.activationTime = activeTime;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
