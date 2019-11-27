SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for work
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work`
(
    `id`              bigint(20)                                              NOT NULL AUTO_INCREMENT,
    `title`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
    `description`     longtext CHARACTER SET utf8 COLLATE utf8_general_ci     NULL COMMENT '描述',
    `cover_image_url` longtext CHARACTER SET utf8 COLLATE utf8_general_ci     NULL,
    `pages`           json                                                    NULL,
    `publish`         tinyint(1)                                              NOT NULL DEFAULT 0,
    `template`        tinyint(1)                                              NOT NULL DEFAULT 0,
    `create_time`     datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time`     datetime(0)                                             NULL     DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for work_forms
-- ----------------------------
DROP TABLE IF EXISTS `work_forms`;
CREATE TABLE `work_forms`
(
    `id`      bigint(20)                                          NOT NULL AUTO_INCREMENT,
    `form`    longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
    `work_id` bigint(20)                                          NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `work_id` (`work_id`) USING BTREE,
    CONSTRAINT `work_forms_ibfk_1` FOREIGN KEY (`work_id`) REFERENCES `work` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
