package com.xxk.management.material.entity;

import com.xxk.core.entity.BaseInfoEntity;

/**
 * Created by Administrator on 2017/3/15.
 */

public class Material extends BaseInfoEntity {
    private String id;
    private String dev_class_id;     //设备种类
    private String mat_name;      //设备名
    private String mat_ident;      //设备编号
    private String mat_brand;      //品牌
    private String mat_type;    //设备型号
    private String mat_type_ident;    //型号编号
    private String mat_specs;    //规格
    private String genre_tags;    //类型标识
    private String single_unit;       //个体单位
    private String mat_flag;         //设备状态 -> 1:停用；0：正常
    private String isStock;        //是否库存标记
    private String stock_office;  //库存科室标记
    private String remark;         //备注
    private String keyWord;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getDev_class_id() {
        return dev_class_id;
    }

    public void setDev_class_id(String dev_class_id) {
        this.dev_class_id = dev_class_id;
    }

    public String getMat_name() {
        return mat_name;
    }

    public void setMat_name(String mat_name) {
        this.mat_name = mat_name;
    }

    public String getMat_ident() {
        return mat_ident;
    }

    public void setMat_ident(String mat_ident) {
        this.mat_ident = mat_ident;
    }

    public String getMat_brand() {
        return mat_brand;
    }

    public void setMat_brand(String mat_brand) {
        this.mat_brand = mat_brand;
    }

    public String getMat_type() {
        return mat_type;
    }

    public void setMat_type(String mat_type) {
        this.mat_type = mat_type;
    }

    public String getMat_type_ident() {
        return mat_type_ident;
    }

    public void setMat_type_ident(String mat_type_ident) {
        this.mat_type_ident = mat_type_ident;
    }

    public String getMat_specs() {
        return mat_specs;
    }

    public void setMat_specs(String mat_specs) {
        this.mat_specs = mat_specs;
    }

    public String getGenre_tags() {
        return genre_tags;
    }

    public void setGenre_tags(String genre_tags) {
        this.genre_tags = genre_tags;
    }

    public String getSingle_unit() {
        return single_unit;
    }

    public void setSingle_unit(String single_unit) {
        this.single_unit = single_unit;
    }

    public String getMat_flag() {
        return mat_flag;
    }

    public void setMat_flag(String mat_flag) {
        this.mat_flag = mat_flag;
    }

    public String getIsStock() {
        return isStock;
    }

    public void setIsStock(String isStock) {
        this.isStock = isStock;
    }

    public String getStock_office() {
        return stock_office;
    }

    public void setStock_office(String stock_office) {
        this.stock_office = stock_office;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
