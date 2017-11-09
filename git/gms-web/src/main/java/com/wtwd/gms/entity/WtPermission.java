package com.wtwd.gms.entity;

public class WtPermission extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String permissionName;

    private String permission;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }
}