package com.xxk.management.stock.dao;

import com.xxk.management.stock.entity.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
@Repository
public interface StockDao {

    public List<Stock> listStock(int pageStart, int pageSize,int search_type);

    public List<Stock> listStockByEntityId(String entity_id,String office_id);

    public int addStock(Stock stock);

    public int updateStock(Stock stock);

    public int deleteListRegUser(List<String> listStr);

    public List<String> getStockIdByIdent(String ident);

    public Stock getStockById(String id);

    public int updateStock(Stock stock,String entityId);

    public int plusStockNo(Stock stock);

}
