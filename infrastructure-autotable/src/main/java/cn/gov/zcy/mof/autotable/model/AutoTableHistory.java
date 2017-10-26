package cn.gov.zcy.mof.autotable.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by huangshang on 2017/9/7 下午8:58.
 * Description: ***
 *
 * @author <a href="mailto:chenjie@cai-inc.com"/>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoTableHistory implements Serializable {

    private static final long serialVersionUID = -587009589448497299L;
    private Long id;
    private String appName;
    private Integer year;
}
