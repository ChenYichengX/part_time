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

 Date: 06/05/2021 09:43:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `part_time_id` int(11) NULL DEFAULT NULL COMMENT '兼职id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (1, 11, 10, 'hahahahah');
INSERT INTO `t_comment` VALUES (10, 11, 10, 's');
INSERT INTO `t_comment` VALUES (11, 8, 10, '老板人很好，工作也很轻松。');

SET FOREIGN_KEY_CHECKS = 1;
