package com.xxk.management.stock.service;

import com.xxk.management.stock.entity.Stock;
import com.xxk.management.storage.entity.Delivery;
import com.xxk.management.storage.entity.Storage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
public interface StockService {

    public List<Stock> listStock(int pageStart, int pageSize,String class_id,String entity_id,String stock_office_id,int search_type);

    public int countStock(String search_type);

    public List<Stock> listStockByEntityId(String entity_id,String office_id);

    public Map<String, Object> addStockWithStorage(Stock stock,Storage storage);

    public Map<String, Object> updateStockWithStorage(Stock stock,Storage storage);

    public Map<String, Object> updateStockWithDelivery(Stock stock, Delivery delivery);

    public Map<String, Object> updateSingleStockWithDelivery(Delivery delivery) ;

    public Map<String, Object> updateStock(Stock stock);

    public boolean plusStockConfiguredTotal(String stockId,String userId,String date,String stock_version);

    public boolean deleteListRegUser(List<String> listStr);

    public List<String> getStorageIdByIdent(String ident);

    public Stock getStockIdById(String id);

}
