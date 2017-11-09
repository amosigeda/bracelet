package com.wtwd.gms.entity;

public class WtDepartmentInfo extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4311827236262702776L;

	private Long id;

    private String departName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName == null ? null : departName.trim();
    }
}