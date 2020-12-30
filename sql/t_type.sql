/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : parttime

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-12-30 22:39:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '类型名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('2', '外卖员');
INSERT INTO `t_type` VALUES ('3', '发传单');
INSERT INTO `t_type` VALUES ('4', '跑腿业务');
INSERT INTO `t_type` VALUES ('5', '打印员');
INSERT INTO `t_type` VALUES ('6', '快递员');
INSERT INTO `t_type` VALUES ('7', '其他');
INSERT INTO `t_type` VALUES ('8', 'java论文代写');
