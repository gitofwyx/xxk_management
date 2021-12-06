package com.xxk.management.office.storage.dao;

import com.xxk.management.office.storage.entity.OfficesStorage;
import com.xxk.management.storage.entity.Delivery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
@Repository
public interface OfficesStorageDao {

    public List<OfficesStorage> listOfficesStorage(int pageStart, int pageSize);

    public List<OfficesStorage> listOfficesStorageByStock(int pageStart, int pageSize, String stock_id);

    public List<OfficesStorage> listOfficesStorageByOffice(int pageStart, int pageSize, String office_id);

    public int addOfficesStorage(OfficesStorage officesStorage);

    public int deleteListRegUser(List<String> listStr);


}
