package cn.gov.zcy.mof.autotable.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by huangshang on 2017/10/26 上午10:46.
 * Description: ***
 *
 * @author <a href="mailto:chenjie@cai-inc.com"/>
 */
@Documented
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Component
public @interface FinanceTableShard {
}
