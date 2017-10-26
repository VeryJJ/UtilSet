package cn.gov.zcy.mof.autotable.conf;

import cn.gov.zcy.mof.autotable.dao.AutoTableDao;
import cn.gov.zcy.mof.autotable.dao.AutoTableHistoryDao;
import cn.gov.zcy.mof.autotable.service.AutoTableReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by huangshang on 2017/9/8 下午6:46.
 * Description: ***
 *
 * @author <a href="mailto:chenjie@cai-inc.com"/>
 */
@Configuration
public class AutoBean {
    @Autowired
    private AutoTableDao autoTableDao;

    @Bean
    @ConditionalOnMissingBean
    public AutoTableReadService initReadService(){
        autoTableDao.initTableHistory();

        return new AutoTableReadService();
    }
}
