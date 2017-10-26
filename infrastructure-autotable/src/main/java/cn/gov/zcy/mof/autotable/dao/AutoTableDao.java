package cn.gov.zcy.mof.autotable.dao;

import cn.gov.zcy.mof.autotable.model.AutoTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface AutoTableDao {
    Integer createTable(AutoTable table);

    void initTableHistory();
}