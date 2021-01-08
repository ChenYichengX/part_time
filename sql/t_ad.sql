/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : parttime

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 08/01/2021 18:37:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_ad
-- ----------------------------
DROP TABLE IF EXISTS `t_ad`;
CREATE TABLE `t_ad`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ad_position_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '广告位code',
  `title` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '广告标题',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `action_type` smallint(4) NULL DEFAULT NULL COMMENT '跳转类型(字典code) 1 当前窗口，2 新增窗口',
  `action_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转地址',
  `delete_flag` int(1) NULL DEFAULT 0 COMMENT '删除标识 0正常 1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '广告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ad
-- ----------------------------
INSERT INTO `t_ad` VALUES (1, 'index-left', 'Adidas', 'admin34bd11610077929333_0746d2f7b6fc4efc9cf6c12d88491319.jpg', 1, 'http://www.baidu.com', 1);
INSERT INTO `t_ad` VALUES (2, 'index-left', 'nike', 'admin1b7ff1610080580398_0746d2f7b6fc4efc9cf6c12d88491319.jpg', 2, 'http://www.baidu.com', 1);
INSERT INTO `t_ad` VALUES (3, 'index-left', 'converse', 'admin595091610086456842_cd.jpg', 2, 'https://www.converse.com.cn/', 1);
INSERT INTO `t_ad` VALUES (4, 'index-left', 'converse', 'admin2cfc31610089923823_converse2.png', 2, 'https://www.converse.com.cn/', 1);
INSERT INTO `t_ad` VALUES (5, 'index-left', 'converse', 'admine96501610089955954_converse.png', 2, 'https://www.converse.com.cn/', 1);
INSERT INTO `t_ad` VALUES (6, 'index-left', 'converse', 'admin993d01610089969878_converse3.png', 2, 'https://www.converse.com.cn/', 1);
INSERT INTO `t_ad` VALUES (7, 'index-left', 'converse', 'admin17d811610089998061_converse4.png', 2, 'https://www.converse.com.cn/', 1);
INSERT INTO `t_ad` VALUES (8, 'index-left', 'converse', 'admincccb11610090186948_converse2.png', 2, 'https://www.converse.com.cn/', 0);
INSERT INTO `t_ad` VALUES (9, 'index-left', 'Adidas', 'adminaef211610090470438_adidas.png', 2, 'https://www.adidas.com.cn/', 1);
INSERT INTO `t_ad` VALUES (10, 'index-left', 'Adidas', 'admine2e991610090531772_adidas.png', 2, 'https://www.adidas.com.cn/', 1);
INSERT INTO `t_ad` VALUES (11, 'index-left', 'Adidas', 'admin75a471610091092226_adidas.png', 2, 'https://www.adidas.com.cn/', 1);
INSERT INTO `t_ad` VALUES (12, 'index-left', 'Adidas', 'admind19eb1610091152747_adidas.png', 2, 'https://www.adidas.com.cn/', 0);
INSERT INTO `t_ad` VALUES (13, 'index-left', 'Nike', 'admin2eee91610091421674_nike.jpg', 2, 'https://www.nike.com/cn', 1);
INSERT INTO `t_ad` VALUES (14, 'index-left', 'Nike', 'admin937181610091520460_nike.png', 2, 'https://www.nike.com/cn', 0);

SET FOREIGN_KEY_CHECKS = 1;
