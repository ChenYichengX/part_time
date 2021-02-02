/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : parttime

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-12-30 22:39:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_apply_part_time
-- ----------------------------
DROP TABLE IF EXISTS `t_apply_part_time`;
CREATE TABLE `t_apply_part_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `part_time_id` int(11) DEFAULT NULL COMMENT '兼职信息id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `apply_time` timestamp NULL DEFAULT NULL COMMENT '申请时间',
  `choose` int(2) DEFAULT '0' COMMENT '已选择1、未选择0',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '兼职开始时间',
  `over_time` timestamp NULL DEFAULT NULL COMMENT '兼职结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_apply_part_time
-- ----------------------------
INSERT INTO `t_apply_part_time` VALUES ('4', '11', '10', '2020-12-18 08:42:20');
INSERT INTO `t_apply_part_time` VALUES ('5', '7', '10', '2020-12-18 08:43:46');
