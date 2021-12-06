package com.xxk.management.office.storage.service;

import com.xxk.management.office.depository.entity.Depository;
import com.xxk.management.office.devices.entity.Devices;
import com.xxk.management.office.storage.entity.OfficesStorage;
import com.xxk.management.stock.entity.Stock;
import com.xxk.management.storage.entity.Delivery;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
public interface OfficesStorageService {

    public List<OfficesStorage> listOfficesStorage(int pageStart, int pageSize);

    public  List<OfficesStorage> listOfficesStorageByStock(int pageStart, int pageSize, String stock_id);

    public  List<OfficesStorage> listOfficesStorageByOffice(int pageStart, int pageSize, String stock_id);

    public Map<String, Object> addOfficesStorage(Depository depository, OfficesStorage officesStorage, String status);

    public Map<String, Object> addOfficesStorage(Devices devices, OfficesStorage officesStorage);

    public boolean deleteListRegUser(List<String> listStr);

}
