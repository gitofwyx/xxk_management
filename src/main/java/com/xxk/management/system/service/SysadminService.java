package com.xxk.management.system.service;

import com.xxk.management.system.entity.Sysadmin;
import com.xxk.management.user.entity.RegUser;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
public interface SysadminService {

    public boolean login(String account, String password);

    public Sysadmin getSysadminByAccount(String account);

    //根据账号获取角色信息
    public String getRoleBySysAccount(String account);

}
