package com.xxk.management.office.storage.entity;

import com.xxk.core.entity.BaseInfoEntity;

/**
 * Created by Administrator on 2017/3/15.
 */

public class OfficesStorage extends BaseInfoEntity {
    private String id;
    private String class_id;           //设备\耗材id
    private String entity_id;           //设备\耗材id
    private String offices_entity_id;           //科室设备\耗材id
    private String entity_name;           //设备\耗材名
    private String entity_type;           //设备\耗材型号
    private String stock_or_depository_id;         //科室库存id
    private String offices_storage_ident; //科室设备\耗材流动编号
    private String offices_storage_type; //设备\耗材类别（1.设备2.配件3.耗材）
    private String offices_storage_genre; //流动类别（1.入科2.部署3.回收4.调用5.借用）
    private String offices_storage_by;//科室设备\耗材流动确认人
    private String original_storage_officeId;//原科室id
    private String offices_storage_officeId;//设备\耗材流动科室
    private String offices_storage_date;    //科室设备\耗材流动时间
    private double offices_storage_no;  //科室设备\耗材流动数量
    private double offices_storage_no_2;  //科室设备\耗材流动数量2
    private String offices_storage_unit;  //设备流动单位
    private int offices_storage_proportion;  //个体/单位比例
    private double offices_storage_total;      //科室设备\耗材流动总量
    private String offices_storage_total_unit;      //总量单位
    private String entity_entry_status;      //入科状态（1：待入科；2：部分待入科；3：已入科；4：部分已入科）
    private String keyWord;           //关键字

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getOffices_entity_id() {
        return offices_entity_id;
    }

    public void setOffices_entity_id(String offices_entity_id) {
        this.offices_entity_id = offices_entity_id;
    }

    public String getEntity_name() {
        return entity_name;
    }

    public void setEntity_name(String entity_name) {
        this.entity_name = entity_name;
    }

    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }

    public String getStock_or_depository_id() {
        return stock_or_depository_id;
    }

    public void setStock_or_depository_id(String stock_or_depository_id) {
        this.stock_or_depository_id = stock_or_depository_id;
    }

    public String getOffices_storage_ident() {
        return offices_storage_ident;
    }

    public void setOffices_storage_ident(String offices_storage_ident) {
        this.offices_storage_ident = offices_storage_ident;
    }

    public String getOffices_storage_type() {
        return offices_storage_type;
    }

    public void setOffices_storage_type(String offices_storage_type) {
        this.offices_storage_type = offices_storage_type;
    }

    public String getOffices_storage_genre() {
        return offices_storage_genre;
    }

    public void setOffices_storage_genre(String offices_storage_genre) {
        this.offices_storage_genre = offices_storage_genre;
    }

    public String getOffices_storage_by() {
        return offices_storage_by;
    }

    public void setOffices_storage_by(String offices_storage_by) {
        this.offices_storage_by = offices_storage_by;
    }

    public String getOriginal_storage_officeId() {
        return original_storage_officeId;
    }

    public void setOriginal_storage_officeId(String original_storage_officeId) {
        this.original_storage_officeId = original_storage_officeId;
    }

    public String getOffices_storage_officeId() {
        return offices_storage_officeId;
    }

    public void setOffices_storage_officeId(String offices_storage_officeId) {
        this.offices_storage_officeId = offices_storage_officeId;
    }

    public String getOffices_storage_date() {
        return offices_storage_date;
    }

    public void setOffices_storage_date(String offices_storage_date) {
        this.offices_storage_date = offices_storage_date;
    }

    public double getOffices_storage_no() {
        return offices_storage_no;
    }

    public void setOffices_storage_no(double offices_storage_no) {
        this.offices_storage_no = offices_storage_no;
    }

    public double getOffices_storage_no_2() {
        return offices_storage_no_2;
    }

    public void setOffices_storage_no_2(double offices_storage_no_2) {
        this.offices_storage_no_2 = offices_storage_no_2;
    }

    public String getOffices_storage_unit() {
        return offices_storage_unit;
    }

    public void setOffices_storage_unit(String offices_storage_unit) {
        this.offices_storage_unit = offices_storage_unit;
    }

    public int getOffices_storage_proportion() {
        return offices_storage_proportion;
    }

    public void setOffices_storage_proportion(int offices_storage_proportion) {
        this.offices_storage_proportion = offices_storage_proportion;
    }

    public double getOffices_storage_total() {
        return offices_storage_total;
    }

    public void setOffices_storage_total(double offices_storage_total) {
        this.offices_storage_total = offices_storage_total;
    }

    public String getOffices_storage_total_unit() {
        return offices_storage_total_unit;
    }

    public void setOffices_storage_total_unit(String offices_storage_total_unit) {
        this.offices_storage_total_unit = offices_storage_total_unit;
    }

    public String getEntity_entry_status() {
        return entity_entry_status;
    }

    public void setEntity_entry_status(String entity_entry_status) {
        this.entity_entry_status = entity_entry_status;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
