package com.xxk.management.stock.service.impl;

import com.xxk.core.util.DateUtil;
import com.xxk.core.util.UUIdUtil;
import com.xxk.management.device.service.DeviceService;
import com.xxk.management.material.service.MaterialService;
import com.xxk.management.office.storage.entity.OfficesStorage;
import com.xxk.management.stock.dao.StockDao;
import com.xxk.management.stock.entity.Stock;
import com.xxk.management.stock.service.StockService;
import com.xxk.management.stock.service.UniteOperateStockService;
import com.xxk.management.storage.entity.Delivery;
import com.xxk.management.storage.entity.Storage;
import com.xxk.management.storage.service.DeliveryService;
import com.xxk.management.storage.service.StorageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@Service
public class UniteOperateStockServiceImpl implements UniteOperateStockService {

    private static Logger log = Logger.getLogger(StockService.class);

    @Autowired
    private StockDao dao;

    @Autowired
    private StorageService storageService;

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private StockService stockService;


    //转库操作
    @Override
    public Map<String, Object> transferStockOfUpdateStock(Stock stock,Storage storage,String delivery_id) {
        Map<String, Object> result = new HashMap<>();
        try {

            result = stockService.updateStockWithStorage(stock, storage);
            if("true".equals(result.get("hasError"))||(boolean)result.get("hasError")){
                result.put("hasError", true);
                result.put("error", "更新出错");
                return result;
            }
            boolean Result =deliveryService.updateDeliveryStatus(delivery_id,"3");
            if(!Result){
                log.error("updateDepositoryWithStorage:deliveryService:allEntryDepository错误！");
                result.put("hasError", true);
                result.put("error", "添加出错");
                return result;
            }

        } catch (Exception e) {
            log.error(e);
            result.put("hasError", true);
            result.put("error", "更新出错");
        }
        return result;
    }

    //转库操作
    @Override
    public Map<String, Object> transferStockOfAddStock(Stock stock,Storage storage,String delivery_id) {
        Map<String, Object> result = new HashMap<>();
        try {

            result = stockService.addStockWithStorage(stock, storage);
            if("true".equals(result.get("hasError"))||(boolean)result.get("hasError")){
                result.put("hasError", true);
                result.put("error", "更新出错");
                return result;
            }
            boolean Result =deliveryService.updateDeliveryStatus(delivery_id,"3");
            if(!Result){
                log.error("updateDepositoryWithStorage:deliveryService:allEntryDepository错误！");
                result.put("hasError", true);
                result.put("error", "添加出错");
                return result;
            }

        } catch (Exception e) {
            log.error(e);
            result.put("hasError", true);
            result.put("error", "更新出错");
        }
        return result;
    }
}
