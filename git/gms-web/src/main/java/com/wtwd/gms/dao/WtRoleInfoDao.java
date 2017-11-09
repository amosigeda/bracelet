package com.wtwd.gms.dao;

import com.wtwd.gms.entity.WtRoleInfo;

public interface WtRoleInfoDao extends BaseDao<WtRoleInfo> {
    WtRoleInfo findRoleInfoByRoleName(String roleName);
}