package com.xxk.management.material.controller;

import com.xxk.core.file.BaseController;
import com.xxk.core.util.DateUtil;
import com.xxk.core.util.UUIdUtil;
import com.xxk.core.util.build_ident.IdentUtil;
import com.xxk.management.device.entity.DeviceClass;
import com.xxk.management.device.service.DeviceClassService;
import com.xxk.management.material.entity.Material;
import com.xxk.management.material.service.MaterialService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@Controller
@RequestMapping("")
public class MaterialController extends BaseController {

    private static Logger log = Logger.getLogger(MaterialController.class);

    @Autowired
    private MaterialService materialService;
    @Autowired
    private DeviceClassService deviceClassService;


    @ResponseBody
    @RequestMapping("/listMaterial")
    public Map<String, Object> listMaterial(@RequestParam(value = "pageIndex") String pageIndex,
                                            @RequestParam(value = "limit") String limit,
                                            @RequestParam(value = "dev_name") String dev_name,
                                            @RequestParam(value = "dev_type") String dev_type,
                                            @RequestParam(value = "startDate") String startDate) {
        Map<String, Object> result = new HashMap<>();
        try {
            int pageNumber = Integer.parseInt(pageIndex) + 1;//因为pageindex 从0开始
            int pageSize = Integer.parseInt(limit);

            List<Material> listDevice = materialService.listMaterial(pageNumber, pageSize);
            if (listDevice == null) {
                log.error("获取分页出错");
                result.put("success", false);
                return result;
            } else {
                result.put("rows", listDevice);
                result.put("results", 7);
            }
        } catch (Exception e) {
            log.error(e);
            result.put("success", false);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/addMaterial")
    public Map<String, Object> addMaterial(Material material, DeviceClass deviceClass,
                                            @RequestParam(value = "dev_class_count") String dev_class_count) {
        Map<String, Object> result = new HashMap<>();
        String Date = DateUtil.getFullTime();
        String deviceId = UUIdUtil.getUUID();
        String mat_ident = "";
        Boolean resultBl = true;
        try {
            if (deviceClass.getClass_ident() > 0 && deviceClass.getType_max() > 0) {  //生成编号
                mat_ident = IdentUtil.getIdent(deviceClass.getClass_ident(), deviceClass.getType_max(), Date);

            } else if (!"".equals(dev_class_count) && dev_class_count != null) {
                mat_ident = IdentUtil.getIdent(Integer.parseInt(dev_class_count), deviceClass.getType_max(), Date);
            } else if (("".equals(dev_class_count) || dev_class_count == null)) {
                mat_ident = IdentUtil.getIdent(0, deviceClass.getType_max(), Date);
            }
            if (!"".equals(material.getDev_class_id()) && material.getDev_class_id() != null) {
                deviceClass.setId(material.getDev_class_id());
                deviceClass.setUpdateUserId("admin");
                deviceClass.setUpdateDate(Date);
                resultBl = deviceClassService.updateDev_typeMax(deviceClass);
                if (!(resultBl)) {
                    result.put("hasError", true);
                    result.put("error", "添加出错");
                    return result;
                }
            }
            if (!resultBl || "".equals(material.getDev_class_id()) && material.getDev_class_id() == null) {
                deviceClass.setId(material.getDev_class_id());
                deviceClass.setDev_class(material.getMat_name());
                String materialClassId = deviceClassService.updateEntityClass(deviceClass, Date);
                if (materialClassId != null && !"".equals(materialClassId)) {
                    material.setDev_class_id(materialClassId);
                }
            }
            material.setId(deviceId);
            material.setMat_ident(mat_ident);
            material.setCreateDate(Date);
            material.setCreateUserId("admin");
            material.setUpdateDate(Date);
            material.setUpdateUserId("admin");
            material.setDeleteFlag("0");

            boolean Result = materialService.addMaterial(material);
            if (!(Result)) {
                result.put("success", false);
            } else {
                result.put("success", true);
            }
        } catch (Exception e) {
            result.put("success", false);
            log.error(e);
        }
        return result;
        //return "system/index";
    }

    @ResponseBody
    @RequestMapping("/getMaterialName")
    public Map<String, Object> getMaterialName() {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> listResult = new ArrayList<>();
        //List<Map<String, Object>> dev_count = new ArrayList<>();
        try {
            listResult = deviceClassService.listAllDeviceName();
            //dev_count = deviceClassService.getCountClassById("1fa2614d-4a55-1234-a79a-5546319b9123");
            if (listResult == null) {
                log.error("获取出错");
                return null;
            }
            result.put("dev_class", listResult);
            /*result.put("dev_count", dev_count);*/
        } catch (Exception e) {
            log.error(e);
            return null;
        }
        return result;
    }

}
