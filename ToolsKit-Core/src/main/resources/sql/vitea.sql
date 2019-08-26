/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : vitea

Target Server Type    : MYSQL
Target Server Version : 50699
File Encoding         : 65001

Date: 2019-06-29 17:14:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for vitea_menu
-- ----------------------------
DROP TABLE IF EXISTS `vitea_menu`;
CREATE TABLE `vitea_menu` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`mname`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名' ,
`url`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址' ,
`parentId`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '父菜单id' ,
`rootmenu`  char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否根菜单' ,
`sequence`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单序列' ,
`iconCls`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标' ,
`path`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL ,
`effective`  enum('是','否') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '是' COMMENT '状态' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
COMMENT='菜单表'

;

-- ----------------------------
-- Records of vitea_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for vitea_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `vitea_menu_role`;
CREATE TABLE `vitea_menu_role` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`mid`  int(11) NOT NULL COMMENT '菜单id' ,
`rid`  int(11) NOT NULL COMMENT '权限id' ,
`effective`  char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`mid`) REFERENCES `vitea_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`rid`) REFERENCES `vitea_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `mid` (`mid`) USING BTREE ,
INDEX `rid` (`rid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
COMMENT='菜单权限表'

;

-- ----------------------------
-- Records of vitea_menu_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for vitea_role
-- ----------------------------
DROP TABLE IF EXISTS `vitea_role`;
CREATE TABLE `vitea_role` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`rolenameen`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '英文权限名' ,
`rolenamezh`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '中文名称' ,
`effective`  char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否有效' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
COMMENT='用户权限表'
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of vitea_role
-- ----------------------------
BEGIN;
INSERT INTO `vitea_role` VALUES ('1', 'ROLE_ADMIN', '超级管理员', '1'), ('2', 'ROLE_USER', '普通用户', '1'), ('3', 'ROLE_TEST', 'cehi', '1');
COMMIT;

-- ----------------------------
-- Table structure for vitea_role_user
-- ----------------------------
DROP TABLE IF EXISTS `vitea_role_user`;
CREATE TABLE `vitea_role_user` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`uid`  int(11) NOT NULL COMMENT '用户id' ,
`rid`  int(11) NOT NULL COMMENT '权限ID' ,
`effective`  enum('是','否') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '是' ,
PRIMARY KEY (`id`),
FOREIGN KEY (`uid`) REFERENCES `vitea_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`rid`) REFERENCES `vitea_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `uid` (`uid`) USING BTREE ,
INDEX `rid` (`rid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
COMMENT='用户权限表'
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of vitea_role_user
-- ----------------------------
BEGIN;
INSERT INTO `vitea_role_user` VALUES ('1', '1', '1', '是'), ('2', '1', '2', '是'), ('3', '2', '3', '是');
COMMIT;

-- ----------------------------
-- Table structure for vitea_user
-- ----------------------------
DROP TABLE IF EXISTS `vitea_user`;
CREATE TABLE `vitea_user` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`username`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名' ,
`password`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码' ,
`phone`  varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机' ,
`telphone`  varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话' ,
`email`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱' ,
`name`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称' ,
`birthday`  date NULL DEFAULT NULL COMMENT '生日' ,
`workdate`  date NULL DEFAULT NULL COMMENT '工作日期' ,
`hou_reg`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '户籍所在地' ,
`domicile`  varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '居住地址' ,
`marriage`  enum('已婚','未婚','离异') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '婚姻状况' ,
`political_stat`  enum('中共党员','中共预备党员','共青团员','民革党员','民盟盟员','民建会员','民进会员','农工党党员','致公党党员','九三学社社员','台盟盟员','无党派人士','群众') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '政治面貌' ,
`abroad_live`  enum('有','无') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '海外经历' ,
`effective`  char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL ,
`creatdate`  date NULL DEFAULT NULL ,
`paypwd`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
COMMENT='用户表'
AUTO_INCREMENT=2

;

-- ----------------------------
-- Records of vitea_user
-- ----------------------------
BEGIN;
INSERT INTO `vitea_user` VALUES ('1', 'admin', '$2a$10$h2vsp7UDYLIrxd4/eD7cc.umzQbJxYHaPQa97RH6Kcza3Hk.3w406', null, null, '3271847221@qq.com', null, null, null, null, null, null, null, null, '1', null, null), ('2', 'libai', '$2a$10$2.zV2yUnISPakwOXr4/AN.yzbFmdSGJ.Tvd6H45deJfE02CRhH3T.', null, null, '34324324@qq.com', null, null, null, null, null, null, null, null, '是', null, null);
COMMIT;

-- ----------------------------
-- Table structure for vitea_work
-- ----------------------------
DROP TABLE IF EXISTS `vitea_work`;
CREATE TABLE `vitea_work` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
COMMENT='工作经验'

;

-- ----------------------------
-- Records of vitea_work
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Auto increment value for vitea_role
-- ----------------------------
ALTER TABLE `vitea_role` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for vitea_role_user
-- ----------------------------
ALTER TABLE `vitea_role_user` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for vitea_user
-- ----------------------------
ALTER TABLE `vitea_user` AUTO_INCREMENT=2;
