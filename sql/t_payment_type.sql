/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : parttime

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-12-30 22:39:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_payment_type
-- ----------------------------
DROP TABLE IF EXISTS `t_payment_type`;
CREATE TABLE `t_payment_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '支付名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_payment_type
-- ----------------------------
INSERT INTO `t_payment_type` VALUES ('2', '周结');
INSERT INTO `t_payment_type` VALUES ('3', '月结');
INSERT INTO `t_payment_type` VALUES ('4', '日结');
INSERT INTO `t_payment_type` VALUES ('5', '单结');
