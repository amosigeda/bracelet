package com.wtwd.gms.entity;

public class WtDevice extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8710987351914387413L;

	private Long id;

    private String devName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName == null ? null : devName.trim();
    }
}