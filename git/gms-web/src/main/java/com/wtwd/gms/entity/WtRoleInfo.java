package com.wtwd.gms.entity;

public class WtRoleInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String roleName;
	private String roleCode;//数据库中没有
	
    public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}