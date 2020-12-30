/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : parttime

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-12-30 22:39:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_unit
-- ----------------------------
DROP TABLE IF EXISTS `t_unit`;
CREATE TABLE `t_unit` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '单位名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_unit
-- ----------------------------
INSERT INTO `t_unit` VALUES ('1', '小时');
INSERT INTO `t_unit` VALUES ('2', '单');
INSERT INTO `t_unit` VALUES ('3', '天');
INSERT INTO `t_unit` VALUES ('5', '月');
INSERT INTO `t_unit` VALUES ('6', '周');
INSERT INTO `t_unit` VALUES ('7', '次');
