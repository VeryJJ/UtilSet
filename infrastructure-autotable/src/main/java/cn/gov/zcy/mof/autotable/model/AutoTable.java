package cn.gov.zcy.mof.autotable.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by huangshang on 2017/9/7 下午8:58.
 * Description: ***
 *
 * @author <a href="mailto:chenjie@cai-inc.com"/>
 */
@Data
public class AutoTable implements Serializable {
    private static final long serialVersionUID = -2370927880067026155L;

    private String templateTableName;    //源表
    private String newTableName;         //新表
}
