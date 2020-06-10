package com.xxk.management.office.devices.service;

import com.xxk.management.office.devices.entity.Devices;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
public interface DevicesService {

    public List<Devices> listDevices(int pageStart, int pageSize);

    public List<Devices> listDevicesById(List<String> listDevId);

    public boolean addDevices(Devices device);

    public List<Map<String, Object>> getDevicesNumber(String deviceId);

    public List<Map<String, Object>> getDevicesSelect();

    public List<Map<String, Object>> getStoreDevicesById(List<String> listDevId);

    public List<Map<String, Object>> getDevicesIdent();  //获取设备编号

    public boolean plusDevicesNumber(int dev_no, String devicesId);

    public boolean minusDevicesNumber(int dev_no, String devicesId);

}