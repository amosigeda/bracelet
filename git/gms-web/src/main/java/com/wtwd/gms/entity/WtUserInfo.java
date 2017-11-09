package com.wtwd.gms.entity;

public class WtUserInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String userName;

    private String userPwd;

    private String userStatus;

    private String realName;

    private String userType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
	 * 认证加密的盐
	 * 
	 * @return
	 */
	public String getCredentialsSalt() {
		return userName + "wtwd";
	}
    
}