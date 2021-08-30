package com.xxk.management.device.service.impl;

import com.xxk.core.util.UUIdUtil;
import com.xxk.management.device.dao.DeviceClassDao;
import com.xxk.management.device.entity.Device;
import com.xxk.management.device.entity.DeviceClass;
import com.xxk.management.device.service.DeviceClassService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@Service
public class DeviceClassServiceImpl implements DeviceClassService {

    private static Logger log = Logger.getLogger(DeviceClassServiceImpl.class);

    @Autowired
    private DeviceClassDao dao;


    @Override
    public List<Device> listDeviceClass(int pageStart, int pageSize) {
        return dao.listDeviceClass((pageStart-1)*pageSize, pageSize);
    }

    @Override
    public List<Map<String, Object>> listAllDeviceName() {
        return dao.listAllDeviceName();
    }

    @Override
    public List<Map<String, Object>> listDeviceOfTab(String tab) {
        return dao.listDeviceOfTab(tab);
    }

    @Override
    public List<Map<String, Object>> listMaterialOfTab(String tab) {
        return dao.listMaterialOfTab(tab);
    }

   /* @Override
    public boolean addDeviceClass(DeviceClass deviceClass) {
        return dao.addDeviceClass(deviceClass)==1?true:false;
    }*/

    /*@Override
    public DeviceClass getDeviceClassById(String id) {
        return dao.getDeviceClassById(id);
    }*/

    @Override
    public List<Map<String, Object>> getDeviceClassById(String id) {
        return dao.getClassClassById(id);
    }

    @Override
    public String updateEntityClass(DeviceClass entityClass,String Date) {
        String deviceClassId = UUIdUtil.getUUID();
        if (!"".equals(entityClass.getId()) && entityClass.getId() != null) {
            entityClass.setId(entityClass.getId());
            entityClass.setUpdateUserId("admin");
            entityClass.setUpdateDate(Date);
            Boolean resultBl = updateDev_maxMax(entityClass);
            if (!(resultBl)) {
               return null;
            }
        } else {
            if ("1".equals(entityClass.getClass_tab())) {
                entityClass.setDev_max(1);
            }else {
                entityClass.setMat_max(1);
            }
            entityClass.setId(deviceClassId);
            entityClass.setCreateUserId("admin");
            entityClass.setCreateDate(Date);
            entityClass.setUpdateUserId("admin");
            entityClass.setUpdateDate(Date);
            entityClass.setDeleteFlag("0");
            Boolean resultBl = dao.addDeviceClass(entityClass)==1?true:false;
            if (!(resultBl)) {
                return null;
            }
        }
        return deviceClassId;
    }

    @Override
    public boolean updateDevMax(DeviceClass deviceClass) {
        return dao.updateDevMax(deviceClass)==1?true:false;
    }

    @Override
    public boolean updateDev_maxMax(DeviceClass deviceClass) {
        return dao.updateDev_maxMax(deviceClass)==1?true:false;
    }

    @Override
    public boolean updateMat_maxMax(DeviceClass deviceClass) {
        return dao.updateMat_maxMax(deviceClass)==1?true:false;
    }

}
