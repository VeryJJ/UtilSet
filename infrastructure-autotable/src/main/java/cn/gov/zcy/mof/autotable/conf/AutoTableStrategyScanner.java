package cn.gov.zcy.mof.autotable.conf;

import cn.gov.zcy.mof.autotable.annotation.FinanceTableShard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by huangshang on 2017/10/26 上午10:47.
 * Description: ***
 *
 * @author <a href="mailto:chenjie@cai-inc.com"/>
 */
@Component
@Slf4j
public class AutoTableStrategyScanner implements ApplicationContextAware, BeanFactoryPostProcessor {
    private ApplicationContext applicationContext = null;
    private Map<String, Object> strategyBeanMap = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 使用自定义扫描类，针对@TestModel进行扫描
        AnnotationScanner scanner = AnnotationScanner.getScanner((BeanDefinitionRegistry) beanFactory, FinanceTableShard.class);
        // 设置ApplicationContext
        scanner.setResourceLoader(this.applicationContext);

        // 执行扫描
        scanner.scan("cn.gov.zcy.mof");

        // 取得对应Annotation映射，BeanName -- 实例
        strategyBeanMap = beanFactory.getBeansWithAnnotation(FinanceTableShard.class);

        // .... doSomething，根据需要进行设置，

        int i =1;

    }

    public Map<String, Object> getStrategyBeanMap(){
        return this.strategyBeanMap;
    }
}
