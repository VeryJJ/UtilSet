package cn.gov.zcy.mof.autotable.service;

import cn.gov.zcy.mof.autotable.conf.AppContextUtil;
import cn.gov.zcy.mof.autotable.dao.AutoTableHistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangshang on 2017/9/8 下午6:43.
 * Description: ***
 *
 * @author <a href="mailto:chenjie@cai-inc.com"/>
 */
@Service
public class AutoTableReadService {
    @Autowired
    private AutoTableHistoryDao autoTableHistoryDao;

    public List<Integer> getYearList(){
        return autoTableHistoryDao.getYearList(AppContextUtil.getCurrentAppId());
    }
}
