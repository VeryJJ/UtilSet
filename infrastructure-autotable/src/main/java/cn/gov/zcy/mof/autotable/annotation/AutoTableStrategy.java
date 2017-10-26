package cn.gov.zcy.mof.autotable.annotation;

import java.util.List;

/**
 * Created by huangshang on 2017/10/26 上午11:16.
 * Description: 自动建表策略API
 *
 * @author <a href="mailto:chenjie@cai-inc.com"/>
 */
public interface AutoTableStrategy {
    /**
     * 提供本模块需要分表的所有表名称
     * 届时创新表时，需要以此表名称对应的表作为源表进行结构拷贝
     * */
    List<String> getTableList();

    /**
     * 业务模块自定义分表命名策略
     * 支持一个表返回多个分表名称，例如：新一年单tableName，需要再按区划细分的情况
     * */
    List<String> getTableShardList(String tableName, String year);
}
