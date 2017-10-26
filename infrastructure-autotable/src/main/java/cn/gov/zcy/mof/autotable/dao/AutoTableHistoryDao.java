package cn.gov.zcy.mof.autotable.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface AutoTableHistoryDao {
    Integer create(@Param("appName") String appName, @Param("year") Integer year);

    Long load(@Param("appName") String appName, @Param("year") Integer year);

    List<Integer> getYearList(@Param("appName") String appName);

}