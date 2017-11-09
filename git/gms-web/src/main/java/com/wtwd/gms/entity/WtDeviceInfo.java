package com.wtwd.gms.entity;

import java.util.Date;

/**
 * 设备表
 * @author liufeng
 * @date 2017-7-1
 */
public class WtDeviceInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

    private String company;

    private String department;

    private String model;

    private String sn;
    
    private int isActive;

    private Date create_time;
    public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}