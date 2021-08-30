package com.xxk.management.device.service;

import com.xxk.management.device.entity.Device;
import com.xxk.management.device.entity.DeviceClass;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
public interface DeviceClassService {

    public List<Device> listDeviceClass(int pageStart, int pageSize);

    public List<Map<String, Object>> listAllDeviceName();

    public List<Map<String, Object>> listDeviceOfTab(String tab);

    public List<Map<String, Object>> listMaterialOfTab(String tab);

    //public boolean addDeviceClass(DeviceClass deviceClass);

   // public DeviceClass getDeviceClassById(String id);

    public List<Map<String, Object>> getDeviceClassById(String id);

    public String updateEntityClass(DeviceClass entityClass, String Date);

    public boolean updateDevMax(DeviceClass deviceClass);

    public boolean updateDev_maxMax(DeviceClass deviceClass);

    public boolean updateMat_maxMax(DeviceClass deviceClass);

}
