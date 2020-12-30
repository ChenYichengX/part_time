/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : parttime

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-12-30 22:39:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_part_time
-- ----------------------------
DROP TABLE IF EXISTS `t_part_time`;
CREATE TABLE `t_part_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `type_id` int(11) DEFAULT NULL COMMENT '类型id',
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题',
  `content` longtext COLLATE utf8_unicode_ci COMMENT '内容',
  `require_text` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '要求',
  `publishDate` datetime DEFAULT NULL COMMENT '发布时间',
  `treatment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '待遇',
  `price` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '价格',
  `pay_id` int(11) DEFAULT NULL COMMENT '支付方式id',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  `firstPicture` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片地址',
  `views` int(11) DEFAULT '0' COMMENT '浏览次数',
  `doing` int(2) DEFAULT '0' COMMENT '0是发布状态，1是已兼职。是否已兼职',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_part_time
-- ----------------------------
INSERT INTO `t_part_time` VALUES ('3', '1', '5', '吉佳超市一楼复印店招人', '每天负责帮忙复印和打印', '需要周一和周二上午没课的同学', '2020-11-23 11:24:57', '可以提供兼职证明', '16元/小时', '3', '2020-11-24 02:13:12', 'chen0cef51606183992271_307572.jpg', '0', '0');
INSERT INTO `t_part_time` VALUES ('4', '1', '7', 'web前端', '写一个学生管理系统 的前端页面', '会html,js,css,jq,bootstrap', '2020-11-23 14:40:58', '200', '200元/次', '2', '2020-11-23 14:41:11', 'chen15df91606142457855_3ECO25H]N7PI`VY6QVEH3U5.png', '3', '0');
INSERT INTO `t_part_time` VALUES ('7', '1', '2', '航天小炒招外卖员', '中午12点-2点，下午5点半来，有课就7点走，没课9点下班送外卖', '吃苦耐劳', '2020-11-24 02:35:07', '包2餐。没晚自习，底薪加500', '2元/单', '3', '2020-11-24 03:25:35', 'chen23c9f1606187325474_IMG_20180918_201555_1.jpg', '2', '0');
INSERT INTO `t_part_time` VALUES ('8', '1', '4', '跑腿', '跑腿拿东西', '2栋到5栋', '2020-11-24 03:40:16', '15', '15元/单', '4', '2020-11-24 07:29:48', 'chenb3a961606202987889_图怪兽42596.png', '0', '0');
INSERT INTO `t_part_time` VALUES ('9', '1', '6', '包裹侠!!!!', '每天课余时间来送包裹', '认真、负责', '2020-11-24 06:05:05', '可以自己取包裹', '2元/单', '4', '2020-12-02 12:18:37', 'chen08bf91606197904938_free.jpg', '1', '0');
INSERT INTO `t_part_time` VALUES ('11', '10', '5', '打印', '每天打印2小时', '每天打印2小时', '2020-12-08 09:45:24', '20', '20元/小时', '2', '2020-12-08 09:45:24', 'leo23a0e91607420723938_free.jpg', '8', '0');
