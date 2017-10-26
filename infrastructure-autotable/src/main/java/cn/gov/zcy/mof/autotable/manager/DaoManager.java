package cn.gov.zcy.mof.autotable.manager;

import cn.gov.zcy.mof.autotable.conf.AppContextUtil;
import cn.gov.zcy.mof.autotable.dao.AutoTableDao;
import cn.gov.zcy.mof.autotable.dao.AutoTableHistoryDao;
import cn.gov.zcy.mof.autotable.model.AutoTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by huangshang on 2017/9/8 下午8:47.
 * Description: ***
 *
 * @author <a href="mailto:chenjie@cai-inc.com"/>
 */
@Component
@Slf4j
@Transactional
public class DaoManager {
    @Autowired
    private AutoTableDao autoTableDao;
    @Autowired
    private AutoTableHistoryDao autoTableHistoryDao;

    public void batchCreateTables(Integer year, Map<String, List<String>> shardRequest){
        log.info("[AutoTable]开始新建{}年的分表, shardRequest: {}.", year, shardRequest);

        //需要循环处理，不支持批量建表
        for (Map.Entry<String, List<String>> entry: shardRequest.entrySet()){
            String tableSrc = entry.getKey();
            List<String> shardList = entry.getValue();
            shardList.forEach(newTableName -> {
                AutoTable table = new AutoTable();
                table.setNewTableName(newTableName);
                table.setTemplateTableName(tableSrc);
                autoTableDao.createTable(table);
            });
        }

        autoTableHistoryDao.create(AppContextUtil.getCurrentAppId(), year);

        log.info("[AutoTable]完成{}年的分表创建, shardRequest: {}.", year, shardRequest);
    }
}