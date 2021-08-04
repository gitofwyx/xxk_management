package com.xxk.management.stock.service.impl;

import com.xxk.management.stock.dao.StockDao;
import com.xxk.management.stock.entity.Stock;
import com.xxk.management.stock.service.StockService;
import com.xxk.management.stock.service.StockUniteOperateService;
import com.xxk.management.storage.entity.Storage;
import com.xxk.management.storage.service.DeliveryService;
import com.xxk.management.storage.service.StorageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@Service
public class StockUniteOperateServiceImpl implements StockUniteOperateService {

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
            if(result.get("hasError") instanceof Boolean&&(Boolean)result.get("hasError")){
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
            if(result.get("hasError") instanceof Boolean&&(Boolean)result.get("hasError")){
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
