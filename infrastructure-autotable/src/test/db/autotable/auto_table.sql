
CREATE TABLE IF NOT EXISTS `autotable_history` (
  `id`             BIGINT         NOT NULL        AUTO_INCREMENT COMMENT '主键',
  `app_name`       VARCHAR(100)   NOT NULL        COMMENT '应用名称',
  `year`           INT            NOT NULL        COMMENT '年度',
  `create_at`      DATETIME       NOT NULL        COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='建表记录表';




