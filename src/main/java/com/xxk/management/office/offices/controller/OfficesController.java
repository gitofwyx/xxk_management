package com.xxk.management.office.offices.controller;

import com.xxk.core.file.BaseController;
import com.xxk.core.util.DateUtil;
import com.xxk.core.util.JsonUtils;
import com.xxk.core.util.UUIdUtil;
import com.xxk.management.office.offices.entity.Offices;
import com.xxk.management.office.offices.service.OfficesService;
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
public class OfficesController extends BaseController {

    private static Logger log = Logger.getLogger(OfficesController.class);

    @Autowired
    private OfficesService officesService;


    @ResponseBody
    @RequestMapping("/listOffices")
    public Map<String, Object> listOffices(@RequestParam(value = "pageIndex") String pageIndex,
                                           @RequestParam(value = "limit") String limit,
                                           @RequestParam(value = "office_name") String office_name,
                                           @RequestParam(value = "office_ident") String office_ident,
                                           @RequestParam(value = "startDate") String startDate,
                                           @RequestParam(value = "endDate") String endDate) {
        Map<String, Object> result = new HashMap<>();
        try {
            int pageNumber = Integer.parseInt(pageIndex) + 1;//因为pageindex 从0开始
            int pageSize = Integer.parseInt(limit);

            List<Offices> listDevice = officesService.listOffices(pageNumber, pageSize);
            if (listDevice == null) {
                log.error("获取分页出错");
                result.put("success", false);
                return result;
            } else {
                result.put("rows", listDevice);
                result.put("results", 150);
            }
        } catch (Exception e) {
            log.error(e);
            result.put("success", false);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/addOffices")
    public Map<String, Object> addOffices(Offices offices) {
        Map<String, Object> result = new HashMap<>();
        String Date = DateUtil.getFullTime();
        String id = UUIdUtil.getUUID();
        String belong_to_id = offices.getBelong_to_id();
        int office_ident = offices.getOffice_ident();
        try {
            if (!"".equals(belong_to_id) && !"undefined".equals(belong_to_id) && belong_to_id != null) {
                int count = officesService.getUnderlingCount(belong_to_id);
                count++;
                office_ident =Integer.parseInt( ""+office_ident + count);
                offices.setOffice_ident(office_ident);
            }else{
                int count = officesService.geRootCount("root");
                count++;
                office_ident =count;
                offices.setBelong_to_id("root");
                offices.setOffice_ident(office_ident);
            }
            offices.setId(id);
            offices.setCreateUserId("admin");
            offices.setCreateDate(Date);
            offices.setUpdateUserId("admin");
            offices.setUpdateDate(Date);
            offices.setDeleteFlag("0");
            Boolean resultBl = officesService.addOffices(offices);
            if (!(resultBl)) {
                result.put("hasError", true);
                result.put("error", "添加出错");
                return result;
            }
        } catch (Exception e) {
            result.put("hasError", true);
            result.put("error", "添加出错");
            log.error(e);
        }
        return result;
        //return "system/index";
    }

    @ResponseBody
    @RequestMapping("/officeCharToEn")
    public Map<String, Object> officeCharToEn(@RequestParam(value = "offices") String offices) {
        Map<String, Object> result = new HashMap<>();
        String Date = DateUtil.getFullTime();

        try {

            List<Offices> officeList= JsonUtils.getObjectFromJsonArray(offices,Offices.class);
            if(null==officeList||officeList.isEmpty()){
                result.put("hasError", true);
                result.put("error", "更新出错，数据为空");
                return result;
            }
            Boolean resultBl = officesService.updateOfficeCharToEn(officeList);
            if (!(resultBl)) {
                result.put("hasError", true);
                result.put("error", "更新出错，0条记录更新");
                return result;
            }
        } catch (Exception e) {
            result.put("hasError", true);
            result.put("error", "更新出错"+e.getLocalizedMessage());
            log.error(e);
        }
        return result;
        //return "system/index";
    }

    @ResponseBody
    @RequestMapping("/getOfficeSelect")
    public Map<String, Object> getOfficeSelect() {
        int id = 0;
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> listOffice = new ArrayList<>();
        try {
            listOffice = officesService.getOfficeSelect();
            if (listOffice == null) {
                log.error("获取出错");
                return null;
            }
            result.put("result", listOffice);
            /*result.put("dev_count", dev_count);*/
        } catch (Exception e) {
            log.error(e);
            return null;
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getOfficeMakeGrid")
    public Map<Object, Object> getOfficeMakeGrid() {
        Map<Object, Object> result = new HashMap<>();
        List<Map<String, Object>> listOffice = new ArrayList<>();
        try {
            listOffice = officesService.getOfficeSelect();
            if (listOffice == null) {
                log.error("获取出错");
                return null;
            }
            for (Map<String, Object> office : listOffice) {
                result.put(office.get("value"),office.get("text"));

            }
            /*result.put("dev_count", dev_count);*/
        } catch (Exception e) {
            log.error(e);
            return null;
        }
        return result;
    }

}
