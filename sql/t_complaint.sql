/*
 Navicat Premium Data Transfer

 Source Server         : 123.57.174.182
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 123.57.174.182:3306
 Source Schema         : parttime

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 06/05/2021 09:43:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_complaint
-- ----------------------------
DROP TABLE IF EXISTS `t_complaint`;
CREATE TABLE `t_complaint`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `part_time_id` int(11) NULL DEFAULT NULL COMMENT '兼职信息id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申述内容',
  `flag` tinyint(2) NULL DEFAULT 0 COMMENT '默认0：未处理；1：已处理',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '申述信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_complaint
-- ----------------------------
INSERT INTO `t_complaint` VALUES (1, 8, 10, '拖欠工资！！！！！', 1);
INSERT INTO `t_complaint` VALUES (2, 3, 10, '111', 0);
INSERT INTO `t_complaint` VALUES (3, 3, 10, '2222', 1);
INSERT INTO `t_complaint` VALUES (4, 3, 10, '拖欠工资！！！！！拖欠工资！！！！！拖欠工资！！！！！拖欠工资！！！！！拖欠工资！！！！！拖欠工资！！！！！拖欠工资！！！！！拖欠工资！！！！！拖欠工资！！！！！拖欠工资！！！！！拖欠工资！！！！！拖欠工资！！！！！拖欠工资！！！！！', 1);

SET FOREIGN_KEY_CHECKS = 1;
