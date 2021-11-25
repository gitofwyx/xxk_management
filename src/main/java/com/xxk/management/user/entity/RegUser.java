package com.xxk.management.user.entity;

import com.xxk.core.entity.BaseInfoEntity;

/**
 * Created by Administrator on 2017/3/15.
 */

public class RegUser extends BaseInfoEntity {
    private String id;
    private String roleId;
    private String account;
    private String password;
    private String name;
    private String realName;
    private String birth;
    private String sex;
    private String phone;
    private String education;
    private String marriage;
    private String createUnit;
    private String QCellCore;
    private String address;
    private String keyWord;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getCreateUnit() {
        return createUnit;
    }

    public void setCreateUnit(String createUnit) {
        this.createUnit = createUnit;
    }

    public String getQCellCore() {
        return QCellCore;
    }

    public void setQCellCore(String QCellCore) {
        this.QCellCore = QCellCore;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
