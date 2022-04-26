/*
 Navicat Premium Data Transfer

 Source Server         : mysql-root
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : wxsl5.com:3306
 Source Schema         : rosalind

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 26/04/2022 11:35:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for country
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of country
-- ----------------------------
BEGIN;
INSERT INTO `country` VALUES (6, '中国', 'CN');
INSERT INTO `country` VALUES (7, '美国', 'US');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
