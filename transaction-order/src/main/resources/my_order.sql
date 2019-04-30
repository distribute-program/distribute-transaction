/*
Navicat MySQL Data Transfer

Source Server         : wechart-local
Source Server Version : 50505
Source Host           : 39.107.239.18:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-04-30 16:38:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for my_order
-- ----------------------------
DROP TABLE IF EXISTS `my_order`;
CREATE TABLE `my_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(11) DEFAULT NULL,
  `status` int(4) DEFAULT NULL COMMENT '0 待发货， 1发货中， 2 发货完成， 3 发货失败',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of my_order
-- ----------------------------
INSERT INTO `my_order` VALUES ('1', '1', '0', '0');
INSERT INTO `my_order` VALUES ('2', '2', '0', '0');
