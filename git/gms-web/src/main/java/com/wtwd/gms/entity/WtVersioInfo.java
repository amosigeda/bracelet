package com.wtwd.gms.entity;

import java.util.Date;

public class WtVersioInfo extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7481555356207337790L;

	private Long id;

    private String versoinId;

    private Date updateDate;

    private Long inputSum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersoinId() {
        return versoinId;
    }

    public void setVersoinId(String versoinId) {
        this.versoinId = versoinId == null ? null : versoinId.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getInputSum() {
        return inputSum;
    }

    public void setInputSum(Long inputSum) {
        this.inputSum = inputSum;
    }
}