package com.xxk.management.office.offices.service;

import com.xxk.management.office.offices.entity.Offices;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
public interface OfficesService {

    public List<Offices> listOffices(int pageStart, int pageSize);

    public boolean addOffices(Offices office);

    public boolean updateOfficeCharToEn(List<Offices> offices);

    public List<Map<String, Object>> getOfficeSelect();

    public int getUnderlingCount(String belong_to_id);

    public int geRootCount(String belong_to_id);

}
