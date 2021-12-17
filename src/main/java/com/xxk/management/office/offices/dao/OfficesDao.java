package com.xxk.management.office.offices.dao;

import com.xxk.management.office.offices.entity.Offices;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@Repository
public interface OfficesDao {

    public List<Offices> listOffices(int pageStart, int pageSize);

    public int addOffices(Offices office);

    public int updateOfficesUDstatus(String office_ident,String userId,String date);

    public int updateOfficeCharToEn(List<Offices> offices);

    public List<Map<String, Object>> getOfficeSelect();

    public int getUnderlingCount(String belong_to_id);

    public int geRootCount(String belong_to_id);

}
