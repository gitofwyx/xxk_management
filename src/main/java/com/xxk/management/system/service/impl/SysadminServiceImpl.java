package com.xxk.management.system.service.impl;

import com.xxk.management.system.dao.SysadminDao;
import com.xxk.management.system.entity.Sysadmin;
import com.xxk.management.system.service.SysadminService;
import com.xxk.management.user.dao.RegUserDao;
import com.xxk.management.user.entity.RegUser;
import com.xxk.management.user.service.RebUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@Service
public class SysadminServiceImpl implements SysadminService {

    private static Logger log = Logger.getLogger(SysadminServiceImpl.class);

    @Autowired
    private SysadminDao dao;


    @Override
    public boolean login(String account, String password) {
        return false;
    }

    @Override
    public Sysadmin getSysadminByAccount(String account) {
        return dao.getSysadminByAccount(account);
    }

    @Override
    public String getRoleBySysAccount(String account) {
        return dao.getRoleBySysAccount(account);
    }
}
