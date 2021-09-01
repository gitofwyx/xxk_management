package com.xxk.management.device.entity;

import com.xxk.core.entity.BaseInfoEntity;

/**
 * Created by Administrator on 2017/3/15.
 */

public class Device extends BaseInfoEntity {
    private String id;
    private String dev_class_id;     //设备种类
    private String dev_name;      //设备名
    private String dev_ident;      //设备编号
    private String dev_brand;      //设备品牌
    private String dev_type;    //设备型号
    private int dev_type_ident;    //型号编号
    private String dev_specs;    //型号规格
    private String genre_tags;      //类型标识
    private String single_unit;     //个体单位
    private String dev_flag;         //设备状态 -> 1:停用；0：正常
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

    public String getDev_name() {
        return dev_name;
    }

    public void setDev_name(String dev_name) {
        this.dev_name = dev_name;
    }

    public String getDev_ident() {
        return dev_ident;
    }

    public void setDev_ident(String dev_ident) {
        this.dev_ident = dev_ident;
    }

    public String getDev_brand() {
        return dev_brand;
    }

    public void setDev_brand(String dev_brand) {
        this.dev_brand = dev_brand;
    }

    public String getDev_type() {
        return dev_type;
    }

    public void setDev_type(String dev_type) {
        this.dev_type = dev_type;
    }

    public int getDev_type_ident() {
        return dev_type_ident;
    }

    public void setDev_type_ident(int dev_type_ident) {
        this.dev_type_ident = dev_type_ident;
    }

    public String getDev_specs() {
        return dev_specs;
    }

    public void setDev_specs(String dev_specs) {
        this.dev_specs = dev_specs;
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

    public String getDev_flag() {
        return dev_flag;
    }

    public void setDev_flag(String dev_flag) {
        this.dev_flag = dev_flag;
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
