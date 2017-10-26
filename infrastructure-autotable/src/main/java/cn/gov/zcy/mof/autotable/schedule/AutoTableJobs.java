package cn.gov.zcy.mof.autotable.schedule;

import cn.gov.zcy.mof.autotable.annotation.AutoTableStrategy;
import cn.gov.zcy.mof.autotable.conf.AppContextUtil;
import cn.gov.zcy.mof.autotable.conf.AutoTableStrategyScanner;
import cn.gov.zcy.mof.autotable.dao.AutoTableHistoryDao;
import cn.gov.zcy.mof.autotable.manager.DaoManager;
import com.google.common.base.Throwables;
import io.terminus.zookeeper.leader.HostLeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by huangshang on 2017/9/7 下午5:16.
 * Description: ***
 *
 * @author <a href="mailto:chenjie@cai-inc.com"/>
 */
@Component
@Slf4j
public class AutoTableJobs {
    @Autowired
    private HostLeader hostLeader;
    @Autowired
    private AutoTableHistoryDao autoTableHistoryDao;
    @Autowired
    private DaoManager daoManager;
    @Autowired
    private AutoTableStrategyScanner strategyScanner;

    //开发模式，十分钟执行一次
//    @Scheduled(cron = "0/30 * * * * ?")
    @Scheduled(cron = "0 0/10 * * * ?")
//    @Scheduled(cron = "0 0 0 * 12 ?")
    public void doCreateNewTables(){
        if (!hostLeader.isLeader()) {
            return;
        }

        Integer year = Calendar.getInstance().get(Calendar.YEAR) + 1;

        log.info("[AutoTable]开始执行自动建表任务， year: {}.", year);

        try {
            Map<String, Object> strategyBeanMap = strategyScanner.getStrategyBeanMap();
            if (strategyBeanMap.isEmpty()){
                log.info("[AutoTable]无自动建表需求，结束任务");
                return;
            }

            Map<String, List<String>> shardRequest = new HashMap<>();

            //遍历所有业务模块配置，搜集自动建表需求
            for (Object bean : strategyBeanMap.values()){
                if (Objects.isNull(bean)){
                    continue;
                }

                AutoTableStrategy strategyBean = (AutoTableStrategy)bean;
                List<String> tableListSrc = strategyBean.getTableList();
                if (CollectionUtils.isEmpty(tableListSrc)){
                    continue;
                }

                log.info("[AutoTable]处理建表需求, tableListSrc: {}.", tableListSrc);

                String shardKey = String.valueOf(year);
                tableListSrc.forEach(tableSrc -> {
                    List<String> shardList = strategyBean.getTableShardList(tableSrc, shardKey);
                    if (!CollectionUtils.isEmpty(shardList)) {
                        shardRequest.put(tableSrc, shardList);
                    }
                });

            }

            if (CollectionUtils.isEmpty(shardRequest)){
                log.warn("[AutoTable]存在业务模块配置，但无自动建表需求，结束任务");
                return;
            }

            Long historyId = autoTableHistoryDao.load(AppContextUtil.getCurrentAppId(), year);
            if (Objects.isNull(historyId)){
                daoManager.batchCreateTables(year, shardRequest);
            }else{
                log.info("[AutoTable]{}年表已新建, shardRequest: {}.", year, shardRequest);
            }
        }catch (Exception e){
            log.error("[AutoTable]自动新建{}年分表异常, cause: {}.", year, Throwables.getStackTraceAsString(e));
        }
    }
}
