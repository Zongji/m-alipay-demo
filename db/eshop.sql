/*
 Navicat Premium Data Transfer

 Source Server         : localhost(密码123456)
 Source Server Type    : MySQL
 Source Server Version : 50709
 Source Host           : localhost:3306
 Source Schema         : eshop

 Target Server Type    : MySQL
 Target Server Version : 50709
 File Encoding         : 65001

 Date: 19/07/2022 07:49:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for e_category
-- ----------------------------
DROP TABLE IF EXISTS `e_category`;
CREATE TABLE `e_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_AT` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0),
  `CREATED_BY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UPDATED_AT` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0),
  `UPDATED_BY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ICON` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PARENT_ID` bigint(20) NULL DEFAULT NULL,
  `LEVEL` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of e_category
-- ----------------------------
INSERT INTO `e_category` VALUES (1, '2020-02-29 14:03:59', 'SYS', '2020-02-29 14:03:59', 'SYS', '0层', '1', 0, 1);
INSERT INTO `e_category` VALUES (2, '2020-02-29 14:04:01', 'sys', '2020-02-29 14:04:01', 'sys', '1层1', NULL, 1, 2);
INSERT INTO `e_category` VALUES (3, '2020-02-29 14:04:02', 'sys', '2020-02-29 14:04:02', 'sys', '1层2', NULL, 1, 2);
INSERT INTO `e_category` VALUES (4, '2020-02-29 14:04:04', 'sys', '2020-02-29 14:04:04', 'sys', '2层1', NULL, 2, 3);
INSERT INTO `e_category` VALUES (5, '2020-02-29 14:04:09', 'sys', '2020-02-29 14:04:09', 'sys', '3层1', NULL, 4, 4);

-- ----------------------------
-- Table structure for e_order
-- ----------------------------
DROP TABLE IF EXISTS `e_order`;
CREATE TABLE `e_order`  (
  `ID` bigint(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `USER_ID` bigint(20) NOT NULL COMMENT 'USER_ID',
  `PRODUCT_ID` bigint(20) NOT NULL COMMENT 'USER_ID',
  `PRICE` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `TOTAL_AMOUNT` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `order_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单状态， NEW, PAID, CANCEL, EXPIRED, DONE',
  `trade_No` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `subject` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1548491801977790466 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of e_order
-- ----------------------------
INSERT INTO `e_order` VALUES (00000000000000000001, 1, 1, 11.00, 1.00, '1', '1', '1');

-- ----------------------------
-- Table structure for e_product
-- ----------------------------
DROP TABLE IF EXISTS `e_product`;
CREATE TABLE `e_product`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别',
  `description` varchar(5120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `stock` int(255) NULL DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1548499673763672066 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of e_product
-- ----------------------------
INSERT INTO `e_product` VALUES (1, 'iphone 12_1657113535414', 'P1657113535414', NULL, '商品详情测试测试1', 20.50, 0);
INSERT INTO `e_product` VALUES (2, 'iphone 12_1657450357766', 'P1657450357766', NULL, '商品详情测试测试2', 20.50, 0);
INSERT INTO `e_product` VALUES (3, 'iphone 12_1657451568495', 'P1657451568495', NULL, '商品详情测试测试3', 20.50, 0);
INSERT INTO `e_product` VALUES (4, 'iphone 12_1657455655992', 'P1657455655992', NULL, '商品详情测试测试4', 20.50, 0);
INSERT INTO `e_product` VALUES (5, 'iphone 12_1658025567975', 'P1658025567975', NULL, '商品详情测试测试5', 20.50, 0);

-- ----------------------------
-- Table structure for e_user
-- ----------------------------
DROP TABLE IF EXISTS `e_user`;
CREATE TABLE `e_user`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of e_user
-- ----------------------------
INSERT INTO `e_user` VALUES (1, 'Jone', 18, 'test1@baomidou.com', '123');
INSERT INTO `e_user` VALUES (2, 'Jack', 20, 'test2@baomidou.com', '123');
INSERT INTO `e_user` VALUES (3, 'Tom', 28, 'test3@baomidou.com', '124');
INSERT INTO `e_user` VALUES (4, 'Sandy', 21, 'test4@baomidou.com', '123');
INSERT INTO `e_user` VALUES (5, 'Billie', 24, 'test5@baomidou.com', '123');

SET FOREIGN_KEY_CHECKS = 1;
