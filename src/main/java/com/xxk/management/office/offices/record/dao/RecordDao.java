package com.xxk.management.office.offices.record.dao;

import com.xxk.management.office.offices.record.entity.Record;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@Repository
public interface RecordDao {

    public List<Record> listRecord(int pageStart, int pageSize);

    public int addRecord(Record record);

    public Map<String, Object> getRecordByOffices(String rec_office_id, String date);

    public int plusRegCount(Record record);

    public int plusOpeCount(Record record);

    public int getUnderlingCount(String belong_to_id);

}
