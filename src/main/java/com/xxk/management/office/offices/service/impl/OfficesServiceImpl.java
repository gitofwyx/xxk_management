package com.xxk.management.office.offices.service.impl;

import com.xxk.core.util.ChineseCharToEnUtil;
import com.xxk.management.office.offices.dao.OfficesDao;
import com.xxk.management.office.offices.entity.Offices;
import com.xxk.management.office.offices.service.OfficesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@Service
public class OfficesServiceImpl implements OfficesService {

    private static Logger log = Logger.getLogger(OfficesServiceImpl.class);

    @Autowired
    private OfficesDao dao;


    @Override
    public List<Offices> listOffices(int pageStart, int pageSize) {
        return dao.listOffices((pageStart-1)*pageSize, pageSize);
    }

    @Override
    public boolean addOffices(Offices office) {
        return dao.addOffices(office)==1?true:false;
    }

    @Override
    public boolean updateOfficeCharToEn(List<Offices> offices) {
        if(offices.size()>7||offices.isEmpty()){
            return false;
        }
        for (int i = 0; i < offices.size(); i++){
            offices.get(i).setPinYin_code(ChineseCharToEnUtil.getPingYin(offices.get(i).getOffice_name()));
            offices.get(i).setPinYinS_code(ChineseCharToEnUtil.getFirstSpell(offices.get(i).getOffice_name()));
        }
        return dao.updateOfficeCharToEn(offices)>=1?true:false;
    }

    @Override
    public List<Map<String, Object>> getOfficeSelect() {
        return dao.getOfficeSelect();
    }

    @Override
    public int getUnderlingCount(String belong_to_id) {
        return dao.getUnderlingCount(belong_to_id);
    }

    @Override
    public int geRootCount(String belong_to_id) {
        return dao.geRootCount(belong_to_id);
    }
}
