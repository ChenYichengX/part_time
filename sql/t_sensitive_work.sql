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

 Date: 06/05/2021 09:41:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sensitive_work
-- ----------------------------
DROP TABLE IF EXISTS `t_sensitive_work`;
CREATE TABLE `t_sensitive_work`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `work` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '敏感词',
  `level` int(1) NULL DEFAULT 1 COMMENT '等级：默认1级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sensitive_work
-- ----------------------------
INSERT INTO `t_sensitive_work` VALUES (1, '西八', 1);
INSERT INTO `t_sensitive_work` VALUES (2, '妈的', 1);
INSERT INTO `t_sensitive_work` VALUES (3, '操', 1);
INSERT INTO `t_sensitive_work` VALUES (4, '艹', 1);
INSERT INTO `t_sensitive_work` VALUES (5, 'fuck', 1);
INSERT INTO `t_sensitive_work` VALUES (6, 'jb', 1);
INSERT INTO `t_sensitive_work` VALUES (7, '鸡巴', 1);
INSERT INTO `t_sensitive_work` VALUES (8, 'bitch', 1);
INSERT INTO `t_sensitive_work` VALUES (9, '售手枪', 1);
INSERT INTO `t_sensitive_work` VALUES (10, '售枪支', 1);
INSERT INTO `t_sensitive_work` VALUES (11, '炸广州', 1);
INSERT INTO `t_sensitive_work` VALUES (12, '丝护士', 1);
INSERT INTO `t_sensitive_work` VALUES (13, '我搞台独', 1);
INSERT INTO `t_sensitive_work` VALUES (14, '做原子弹', 1);
INSERT INTO `t_sensitive_work` VALUES (15, '做证件', 1);
INSERT INTO `t_sensitive_work` VALUES (16, '习进平', 1);
INSERT INTO `t_sensitive_work` VALUES (17, '习晋平', 1);
INSERT INTO `t_sensitive_work` VALUES (19, '人权律', 1);
INSERT INTO `t_sensitive_work` VALUES (20, '人体艺', 1);
INSERT INTO `t_sensitive_work` VALUES (21, '巨乳', 1);
INSERT INTO `t_sensitive_work` VALUES (22, '安眠藥', 1);
INSERT INTO `t_sensitive_work` VALUES (25, '炸广州', 1);
INSERT INTO `t_sensitive_work` VALUES (26, '炸立交', 1);

SET FOREIGN_KEY_CHECKS = 1;
