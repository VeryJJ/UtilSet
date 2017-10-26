package cn.gov.zcy.mof.autotable.conf;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Created by huangshang on 2017/9/8 下午10:44.
 * Description: ***
 *
 * @author <a href="mailto:chenjie@cai-inc.com"/>
 */
@Component
public class AppContextUtil implements ApplicationContextAware {
    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public synchronized static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public synchronized static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }

    public synchronized static String getCurrentAppId(){
        String id = context.getId();

        if (StringUtils.isBlank(id)){
            return "default-app-id";
        }

        int idx = id.indexOf(":");
        idx = (0 == idx ? id.length() : idx);

        return id.substring(0, idx);
    }
}
