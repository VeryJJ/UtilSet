package cn.gov.zcy.mof.autotable.conf;

import lombok.Setter;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created by huangshang on 2017/10/26 上午10:49.
 * Description: ***
 *
 * @author <a href="mailto:chenjie@cai-inc.com"/>
 */
public class AnnotationScanner extends ClassPathBeanDefinitionScanner {
    @Setter
    private Class<? extends Annotation> selfAnnotationClazz;

    /**
     * 需要扫描的注解类型
     */
    private static Class<? extends Annotation> annotationType = null;

    private AnnotationScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public static synchronized AnnotationScanner getScanner(BeanDefinitionRegistry registry, Class<? extends Annotation> clazz){
        annotationType = clazz;
        AnnotationScanner scanner = new AnnotationScanner(registry);
        scanner.setSelfAnnotationClazz(clazz);
        return scanner;
    }

    /**
     * 构造函数需调用函数，使用静态变量annotationClazz传值
     */
    @Override
    public void registerDefaultFilters() {
        // 添加需扫描的Annotation Class
        this.addIncludeFilter(new AnnotationTypeFilter(annotationType));
    }

    /**
     * 以下为初始化后调用的方法
     */
    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        return super.doScan(basePackages);
    }

    @Override
    public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return super.isCandidateComponent(beanDefinition)
                && beanDefinition.getMetadata().hasAnnotation(this.selfAnnotationClazz.getName());
    }
}
