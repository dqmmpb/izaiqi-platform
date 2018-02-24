/*
Navicat MySQL Data Transfer

Source Server         : izaiqi_local
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : izaiqi_local

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-02-20 08:16:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for SYS_PRIV
-- ----------------------------
DROP TABLE IF EXISTS `SYS_PRIV`;
CREATE TABLE `SYS_PRIV` (
  `PRIV_ID` bigint(9) NOT NULL AUTO_INCREMENT,
  `PARENT_PRIV_ID` bigint(9) NOT NULL DEFAULT '0' COMMENT '父节点ID',
  `PRIV_CODE` varchar(255) NOT NULL COMMENT '权限CODE',
  `PRIV_NAME` varchar(60) NOT NULL COMMENT '权限名称',
  `TYPE` int(9) NOT NULL COMMENT '0-目录;1-菜单;2-数据',
  `URL` varchar(255) DEFAULT NULL COMMENT '菜单URL路径',
  `PATH` varchar(255) DEFAULT NULL COMMENT '菜单PATH路径',
  `DESCRIPTION` varchar(1000) NOT NULL COMMENT '权限描述',
  `STATE` char(1) NOT NULL DEFAULT 'A' COMMENT 'A-可用;X:不可用',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`PRIV_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_PRIV
-- ----------------------------
INSERT INTO `SYS_PRIV` VALUES ('1', '0', 'sys:priv:dir:manage', '权限管理', '0', '/priv', '/priv', '权限管理', 'A', '2018-02-20 08:15:14', '2018-02-20 08:15:14');
INSERT INTO `SYS_PRIV` VALUES ('2', '1', 'sys:priv:url:add', '新建权限', '1', '/priv/add', '/priv/add', '新建权限', 'A', '2018-02-20 08:15:14', '2018-02-20 08:15:14');
INSERT INTO `SYS_PRIV` VALUES ('3', '1', 'sys:priv:url:edit', '编辑权限', '1', '/priv/edit', '/priv/edit', '编辑权限', 'A', '2018-02-20 08:15:14', '2018-02-20 08:15:14');
INSERT INTO `SYS_PRIV` VALUES ('4', '1', 'sys:priv:url:view', '查看权限', '1', '/priv/view', '/priv/view', '查看权限', 'A', '2018-02-20 08:15:14', '2018-02-20 08:15:14');
INSERT INTO `SYS_PRIV` VALUES ('5', '0', 'sys:role:dir:manage', '角色管理', '0', '/role', '/role', '角色管理', 'A', '2018-02-20 08:15:14', '2018-02-20 08:15:14');
INSERT INTO `SYS_PRIV` VALUES ('6', '5', 'sys:role:url:add', '新建角色', '1', '/role/add', '/role/add', '新建角色', 'A', '2018-02-20 08:15:15', '2018-02-20 08:15:15');
INSERT INTO `SYS_PRIV` VALUES ('7', '5', 'sys:role:url:edit', '编辑角色', '1', '/role/edit', '/role/edit', '编辑角色', 'A', '2018-02-20 08:15:15', '2018-02-20 08:15:15');
INSERT INTO `SYS_PRIV` VALUES ('8', '5', 'sys:role:url:view', '查看角色', '1', '/role/view', '/role/view', '查看角色', 'A', '2018-02-20 08:15:15', '2018-02-20 08:15:15');
INSERT INTO `SYS_PRIV` VALUES ('9', '0', 'sys:user:dir:manage', '用户管理', '0', '/user', '/user', '用户管理', 'A', '2018-02-20 08:15:15', '2018-02-20 08:15:15');
INSERT INTO `SYS_PRIV` VALUES ('10', '9', 'sys:user:url:add', '新建用户', '1', '/user/add', '/user/add', '新建用户', 'A', '2018-02-20 08:15:15', '2018-02-20 08:15:15');
INSERT INTO `SYS_PRIV` VALUES ('11', '9', 'sys:user:url:edit', '编辑用户', '1', '/user/edit', '/user/edit', '编辑用户', 'A', '2018-02-20 08:15:15', '2018-02-20 08:15:15');
INSERT INTO `SYS_PRIV` VALUES ('12', '9', 'sys:user:url:view', '查看用户', '1', '/user/view', '/user/view', '查看用户', 'A', '2018-02-20 08:15:15', '2018-02-20 08:15:15');
INSERT INTO `SYS_PRIV` VALUES ('13', '1', 'sys:priv:add', '新建权限', '2', null, null, '新建权限', 'A', '2018-02-20 08:15:15', '2018-02-20 08:15:15');
INSERT INTO `SYS_PRIV` VALUES ('14', '1', 'sys:priv:delete', '删除权限', '2', null, null, '删除权限', 'A', '2018-02-20 08:15:15', '2018-02-20 08:15:15');
INSERT INTO `SYS_PRIV` VALUES ('15', '1', 'sys:priv:edit', '编辑权限', '2', null, null, '编辑权限', 'A', '2018-02-20 08:15:15', '2018-02-20 08:15:15');
INSERT INTO `SYS_PRIV` VALUES ('16', '1', 'sys:priv:view', '查看权限', '2', null, null, '查看权限', 'A', '2018-02-20 08:15:15', '2018-02-20 08:15:15');
INSERT INTO `SYS_PRIV` VALUES ('17', '5', 'sys:role:add', '新建角色', '2', null, null, '新建角色', 'A', '2018-02-20 08:15:16', '2018-02-20 08:15:16');
INSERT INTO `SYS_PRIV` VALUES ('18', '5', 'sys:role:delete', '删除角色', '2', null, null, '删除角色', 'A', '2018-02-20 08:15:16', '2018-02-20 08:15:16');
INSERT INTO `SYS_PRIV` VALUES ('19', '5', 'sys:role:edit', '编辑角色', '2', null, null, '编辑角色', 'A', '2018-02-20 08:15:16', '2018-02-20 08:15:16');
INSERT INTO `SYS_PRIV` VALUES ('20', '5', 'sys:role:view', '查看角色', '2', null, null, '查看角色', 'A', '2018-02-20 08:15:16', '2018-02-20 08:15:16');
INSERT INTO `SYS_PRIV` VALUES ('21', '5', 'sys:role:allocate', '分配权限', '2', null, null, '分配权限', 'A', '2018-02-20 08:15:16', '2018-02-20 08:15:16');
INSERT INTO `SYS_PRIV` VALUES ('22', '9', 'sys:user:add', '新建用户', '2', null, null, '新建用户', 'A', '2018-02-20 08:15:16', '2018-02-20 08:15:16');
INSERT INTO `SYS_PRIV` VALUES ('23', '9', 'sys:user:delete', '删除用户', '2', null, null, '删除用户', 'A', '2018-02-20 08:15:16', '2018-02-20 08:15:16');
INSERT INTO `SYS_PRIV` VALUES ('24', '9', 'sys:user:edit', '编辑用户', '2', null, null, '编辑用户', 'A', '2018-02-20 08:15:16', '2018-02-20 08:15:16');
INSERT INTO `SYS_PRIV` VALUES ('25', '9', 'sys:user:view', '查看用户', '2', null, null, '查看用户', 'A', '2018-02-20 08:15:16', '2018-02-20 08:15:16');
INSERT INTO `SYS_PRIV` VALUES ('26', '9', 'sys:user:allocate', '分配角色', '2', null, null, '分配角色', 'A', '2018-02-20 08:15:16', '2018-02-20 08:15:16');
INSERT INTO `SYS_PRIV` VALUES ('27', '9', 'sys:user:lock', '禁用用户', '2', null, null, '禁用用户', 'A', '2018-02-20 08:15:16', '2018-02-20 08:15:16');
INSERT INTO `SYS_PRIV` VALUES ('28', '9', 'sys:user:unlock', '启用用户', '2', null, null, '启用用户', 'A', '2018-02-20 08:15:16', '2018-02-20 08:15:16');
INSERT INTO `SYS_PRIV` VALUES ('29', '9', 'sys:user:resetPwd', '重置密码', '2', null, null, '重置密码', 'A', '2018-02-20 08:15:16', '2018-02-20 08:15:16');
INSERT INTO `SYS_PRIV` VALUES ('30', '0', 'sys:profile:manage', '个人信息', '1', '/profile/view', '/profile/view', '个人信息', 'A', '2018-02-20 08:15:16', '2018-02-20 08:15:16');
INSERT INTO `SYS_PRIV` VALUES ('31', '30', 'sys:profile:changePwd', '修改密码', '2', null, null, '修改密码', 'A', '2018-02-20 08:15:16', '2018-02-20 08:15:16');

-- ----------------------------
-- Table structure for SYS_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE`;
CREATE TABLE `SYS_ROLE` (
  `ROLE_ID` bigint(9) NOT NULL AUTO_INCREMENT,
  `ROLE_CODE` varchar(60) NOT NULL COMMENT '角色CODE',
  `ROLE_NAME` varchar(60) NOT NULL COMMENT '角色名称',
  `STATE` char(1) NOT NULL DEFAULT 'A' COMMENT 'A-可用;X:不可用',
  `DESCRIPTION` varchar(255) NOT NULL COMMENT '角色描述',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_ROLE
-- ----------------------------
INSERT INTO `SYS_ROLE` VALUES ('1', 'SuperAdmin', '超级管理员', 'A', '超级管理员', '2018-02-10 12:04:48', '2018-02-10 12:04:48');
INSERT INTO `SYS_ROLE` VALUES ('2', 'Admin', '管理员', 'A', '管理员', '2018-02-10 12:04:48', '2018-02-10 12:04:48');
INSERT INTO `SYS_ROLE` VALUES ('3', 'User', '普通用户', 'A', '普通用户', '2018-02-10 12:04:49', '2018-02-10 12:04:49');

-- ----------------------------
-- Table structure for SYS_ROLE_PRIV
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE_PRIV`;
CREATE TABLE `SYS_ROLE_PRIV` (
  `ROLE_ID` bigint(9) NOT NULL COMMENT '角色ID',
  `PRIV_ID` bigint(9) NOT NULL COMMENT '权限ID',
  `STATE` char(1) NOT NULL DEFAULT 'A' COMMENT 'A-可用;X:不可用',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ROLE_ID`,`PRIV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_ROLE_PRIV
-- ----------------------------
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '1', 'A', '2018-02-20 08:16:32', '2018-02-20 08:16:32');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '2', 'A', '2018-02-20 08:16:32', '2018-02-20 08:16:32');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '3', 'A', '2018-02-20 08:16:32', '2018-02-20 08:16:32');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '4', 'A', '2018-02-20 08:16:32', '2018-02-20 08:16:32');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '5', 'A', '2018-02-20 08:16:32', '2018-02-20 08:16:32');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '6', 'A', '2018-02-20 08:16:32', '2018-02-20 08:16:32');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '7', 'A', '2018-02-20 08:16:32', '2018-02-20 08:16:32');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '8', 'A', '2018-02-20 08:16:32', '2018-02-20 08:16:32');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '9', 'A', '2018-02-20 08:16:32', '2018-02-20 08:16:32');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '10', 'A', '2018-02-20 08:16:32', '2018-02-20 08:16:32');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '11', 'A', '2018-02-20 08:16:32', '2018-02-20 08:16:32');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '12', 'A', '2018-02-20 08:16:32', '2018-02-20 08:16:32');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '13', 'A', '2018-02-20 08:16:33', '2018-02-20 08:16:33');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '14', 'A', '2018-02-20 08:16:33', '2018-02-20 08:16:33');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '15', 'A', '2018-02-20 08:16:33', '2018-02-20 08:16:33');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '16', 'A', '2018-02-20 08:16:33', '2018-02-20 08:16:33');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '17', 'A', '2018-02-20 08:16:33', '2018-02-20 08:16:33');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '18', 'A', '2018-02-20 08:16:33', '2018-02-20 08:16:33');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '19', 'A', '2018-02-20 08:16:33', '2018-02-20 08:16:33');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '20', 'A', '2018-02-20 08:16:33', '2018-02-20 08:16:33');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '21', 'A', '2018-02-20 08:16:33', '2018-02-20 08:16:33');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '22', 'A', '2018-02-20 08:16:33', '2018-02-20 08:16:33');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '23', 'A', '2018-02-20 08:16:33', '2018-02-20 08:16:33');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '24', 'A', '2018-02-20 08:16:33', '2018-02-20 08:16:33');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '25', 'A', '2018-02-20 08:16:33', '2018-02-20 08:16:33');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '26', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '27', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '28', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '29', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '30', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('1', '31', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('2', '9', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('2', '10', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('2', '11', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('2', '12', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('2', '22', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('2', '23', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('2', '24', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('2', '25', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('2', '26', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('2', '27', 'A', '2018-02-20 08:16:34', '2018-02-20 08:16:34');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('2', '28', 'A', '2018-02-20 08:16:35', '2018-02-20 08:16:35');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('2', '29', 'A', '2018-02-20 08:16:35', '2018-02-20 08:16:35');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('2', '30', 'A', '2018-02-20 08:16:35', '2018-02-20 08:16:35');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('2', '31', 'A', '2018-02-20 08:16:35', '2018-02-20 08:16:35');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('3', '30', 'A', '2018-02-20 08:16:35', '2018-02-20 08:16:35');
INSERT INTO `SYS_ROLE_PRIV` VALUES ('3', '31', 'A', '2018-02-20 08:16:35', '2018-02-20 08:16:35');

-- ----------------------------
-- Table structure for SYS_USER
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER`;
CREATE TABLE `SYS_USER` (
  `USER_ID` bigint(9) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(60) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(128) NOT NULL COMMENT '密码',
  `SALT` varchar(255) NOT NULL COMMENT '密码盐值',
  `NAME` varchar(128) DEFAULT NULL COMMENT '姓名',
  `MOBILE` varchar(255) DEFAULT NULL COMMENT '手机号',
  `STATE` char(1) NOT NULL COMMENT 'A-可用/有效;X-不可用/无效',
  `STATE_TIME` datetime NOT NULL COMMENT '状态变动时间',
  `IS_LOCKED` char(1) NOT NULL DEFAULT 'N' COMMENT '是否锁定，Y-锁定;N-没有锁定;NULL-等价N没有锁定',
  `PWD_EXP_TIME` datetime DEFAULT NULL COMMENT '密码过期时间，NULL-永不过期',
  `USER_EFF_TIME` datetime NOT NULL COMMENT '生效时间',
  `USER_EXP_TIME` datetime DEFAULT NULL COMMENT '失效时间，NULL-永不失效',
  `FORCE_LOGIN` char(1) DEFAULT 'N' COMMENT 'Y-允许强制登录，N-不允许',
  `LOGIN_FAIL` int(6) NOT NULL COMMENT '登录失败次数，空表示0',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_USER
-- ----------------------------
INSERT INTO `SYS_USER` VALUES ('1', '13819493700', '9727d245e28fb47c5614cb2a1c565724', 'HS/Qq8rqufHPF8cs1ume/w==', null, null, 'A', '2018-02-10 15:26:07', 'N', null, '2018-02-10 15:26:07', null, 'Y', '0', '2018-02-10 15:26:07', '2018-02-19 13:49:59');
INSERT INTO `SYS_USER` VALUES ('2', '13819493701', '336b8c50aa0383dcdf8fc407af4c4b55', 't4+zH+P5KZNUjHm0o8KS3g==', null, null, 'A', '2018-02-10 19:46:52', 'N', null, '2018-02-10 19:40:32', null, 'Y', '0', '2018-02-10 19:40:32', '2018-02-10 20:18:47');

-- ----------------------------
-- Table structure for SYS_USER_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER_ROLE`;
CREATE TABLE `SYS_USER_ROLE` (
  `USER_ID` bigint(9) NOT NULL COMMENT '用户ID',
  `ROLE_ID` bigint(9) NOT NULL COMMENT '角色ID',
  `STATE` char(1) NOT NULL DEFAULT 'A' COMMENT 'A-可用;X-不可用',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ROLE_ID`,`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_USER_ROLE
-- ----------------------------
INSERT INTO `SYS_USER_ROLE` VALUES ('1', '1', 'A', '2018-02-10 16:09:14', '2018-02-10 16:09:14');
INSERT INTO `SYS_USER_ROLE` VALUES ('2', '2', 'A', '2018-02-11 10:13:27', '2018-02-11 10:13:27');
INSERT INTO `SYS_USER_ROLE` VALUES ('2', '3', 'A', '2018-02-11 10:13:27', '2018-02-11 10:13:27');

-- ----------------------------
-- Table structure for SYS_USER_SESSION
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER_SESSION`;
CREATE TABLE `SYS_USER_SESSION` (
  `ID` bigint(9) NOT NULL AUTO_INCREMENT,
  `TOKEN` varchar(255) NOT NULL COMMENT '用户TOKEN',
  `USER_ID` bigint(9) NOT NULL COMMENT '用户ID',
  `STATE` char(1) NOT NULL DEFAULT 'A' COMMENT 'A-可用;X-不可用',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LAST_UPDATE_TIME` datetime NOT NULL,
  `UA` varchar(255) NOT NULL COMMENT 'UserAgent',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_USER_SESSION
-- ----------------------------
