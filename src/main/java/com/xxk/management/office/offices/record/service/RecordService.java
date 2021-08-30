package com.xxk.management.office.offices.record.service;

import com.xxk.management.office.offices.record.entity.Record;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
public interface RecordService {

    public List<Record> listRecord(int pageStart, int pageSize);

    public boolean addRecord(Record record);

    public Map<String, Object> getRecordByOffices(String rec_office_id, String date);

    public boolean plusRegCount(Record record);

    public boolean plusOpeCount(Record record);

    public int getUnderlingCount(String belong_to_id);

}
