/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : parttime

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-12-30 22:39:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(22) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电话',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `type` int(2) DEFAULT NULL COMMENT '0为学生,1为商家',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'chen', '小成就是陈奕成', '202cb962ac59075b964b07152d234b70', 'chenbbion@outlook.com', '13048779806', '2020-11-18 21:44:14', '1');
INSERT INTO `t_user` VALUES ('4', 'xyx', '大宝贝', '202cb962ac59075b964b07152d234b70', 'chenbbion@outlook.com', '13048779806', '2020-12-03 09:32:28', '1');
INSERT INTO `t_user` VALUES ('6', 'guest', 'guest', '202cb962ac59075b964b07152d234b70', 'chenbbion@outlook.com', '13048779806', '2020-12-03 09:40:45', '1');
INSERT INTO `t_user` VALUES ('7', 'leo', 'leo', '202cb962ac59075b964b07152d234b70', 'chenbbion@outlook.com', '13048779806', '2020-12-03 09:45:26', '1');
INSERT INTO `t_user` VALUES ('8', 'admin', '大宝贝', '202cb962ac59075b964b07152d234b70', '849571644@qq.com', '13048779806', '2020-12-03 12:36:11', '1');
INSERT INTO `t_user` VALUES ('9', 'zgc', '张灌丛', '202cb962ac59075b964b07152d234b70', 'zhang849571644@163.com', '13048779806', '2020-12-03 12:47:38', '1');
INSERT INTO `t_user` VALUES ('10', 'leo2', '大宝贝', 'f4a31b9bf3052df80c82cdcbe656104a', '1115793502@qq.com', '13048779806', '2020-12-08 09:43:28', '0');
