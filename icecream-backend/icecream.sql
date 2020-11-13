/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : icecream2

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 13/11/2020 15:30:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_behavior_log
-- ----------------------------
DROP TABLE IF EXISTS `s_behavior_log`;
CREATE TABLE `s_behavior_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `visitor` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `browserName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `browserVersion` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `browserPlatform` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `screen` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `device` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accessIp` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accessCname` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accessCity` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ipSupplier` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fromUrl` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accessUrl` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accessTimeIn` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accessTimeOut` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accessTime` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `actionName` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `actionTime` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT 1,
  `createDate` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `tenantId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_behavior_log
-- ----------------------------

-- ----------------------------
-- Table structure for s_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `s_dictionary`;
CREATE TABLE `s_dictionary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `typeCode` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dicName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dicValue` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logicId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tenantId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for s_document
-- ----------------------------
DROP TABLE IF EXISTS `s_document`;
CREATE TABLE `s_document`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuidFileName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relativeDir` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oFileName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uploadTime` datetime(0) NULL DEFAULT NULL,
  `bizCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `suffix` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fileSize` bigint(20) NULL DEFAULT NULL,
  `tenantId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_document
-- ----------------------------

-- ----------------------------
-- Table structure for s_login_log
-- ----------------------------
DROP TABLE IF EXISTS `s_login_log`;
CREATE TABLE `s_login_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `visitor` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ipAddress` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loginTime` timestamp(0) NULL DEFAULT NULL,
  `logoutTime` timestamp(0) NULL DEFAULT NULL,
  `sessionID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tenantId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_login_log
-- ----------------------------
INSERT INTO `s_login_log` VALUES (1, 'admin', '127.0.0.1', '2020-11-13 15:29:55', NULL, '1C7F0C372C81314314B9C7AA4C7B346F', 1);

-- ----------------------------
-- Table structure for s_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `s_operate_log`;
CREATE TABLE `s_operate_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `visitor` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ipAddress` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `methodType` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `moduleName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operateName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `descript` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `visitTime` timestamp(0) NULL DEFAULT NULL,
  `sessionID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tenantId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_operate_log
-- ----------------------------

-- ----------------------------
-- Table structure for s_org
-- ----------------------------
DROP TABLE IF EXISTS `s_org`;
CREATE TABLE `s_org`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NULL DEFAULT NULL,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `orderNo` int(11) NULL DEFAULT NULL,
  `remarks` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tenantId` int(11) NULL DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_org
-- ----------------------------
INSERT INTO `s_org` VALUES (1, NULL, '珠海格力', 'ZHGREE', 1, 1, '根目录', 1);
INSERT INTO `s_org` VALUES (2, NULL, '根组织', 'cczz', 1, 1, '根组织', 3);
INSERT INTO `s_org` VALUES (3, 2, '测试', 'org.xx', NULL, 2, '测试部门', 3);
INSERT INTO `s_org` VALUES (4, 3, '测试2', 'org.xx', NULL, 2, '测试部门', 3);
INSERT INTO `s_org` VALUES (5, NULL, '根组织', NULL, 1, 1, '根组织', 2);

-- ----------------------------
-- Table structure for s_resource
-- ----------------------------
DROP TABLE IF EXISTS `s_resource`;
CREATE TABLE `s_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NULL DEFAULT NULL,
  `portalId` int(11) NULL DEFAULT NULL COMMENT '属于哪个门户',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\r\n',
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL COMMENT '1 门户，2: portlet，3：目录 4：菜单 5：页面  6：按钮                      ',
  `remarks` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderNo` int(11) NULL DEFAULT NULL,
  `relationId` int(11) NULL DEFAULT NULL COMMENT '关联了哪个资源，比如菜单关联页面',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_resource
-- ----------------------------
INSERT INTO `s_resource` VALUES (1, NULL, 9, '租户管理页面', 'page.sys.tenant', '/sys/tenant', 5, '租户管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES (2, NULL, 10, '角色管理页面', 'page.sys.role', '/sys/role', 5, '角色管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES (3, NULL, 10, '用户管理页面', 'page.sys.user', '/sys/user', 5, '用户管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES (4, NULL, 10, '组织管理页面', 'page.sys.org', '/sys/org', 5, '组织管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES (5, NULL, 9, '页面管理页面', 'page.sys.page', '/sys/page', 5, '页面管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES (6, NULL, 9, '菜单管理页面', 'page.sys.menu', '/sys/menu', 5, '菜单管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES (7, NULL, 9, '门户管理页面', 'page.sys.protal', '/sys/portal', 5, '门户管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES (8, NULL, 9, 'Widget管理页面', 'page.sys.portlet', '/sys/portlet', 5, 'Widget管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES (9, NULL, 9, '运营门户', 'protal.operation', '', 1, '运营门户', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES (10, NULL, 10, '管理门户', 'protal.sys', '', 1, '管理门户', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES (11, NULL, NULL, '开发门户', 'portal.develop', NULL, 1, '开发门户', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES (12, NULL, NULL, '普通门户', 'portal.normal', NULL, 1, '普通门户', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES (13, NULL, 10, '测试', 'portlet.test', 'Proxy', 2, 'Proxy', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES (14, NULL, 10, '测试1', 'portlet.test', 'Test', 2, '测试1', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES (15, NULL, 9, '测试2', 'portlet.test2', 'Test2', 2, '测试2', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES (16, NULL, 9, '运营门户根目录', 'menu.operation', '', 3, '运营门户根目录', NULL, 3, NULL);
INSERT INTO `s_resource` VALUES (17, 16, 9, '租户管理', 'menu.operation.tenant', '/sys/tenant', 4, '租户管理', NULL, 2, 1);
INSERT INTO `s_resource` VALUES (18, 16, 9, '门户管理', 'menu.operation.portal', '/sys/portal', 4, '门户管理', NULL, 3, 7);
INSERT INTO `s_resource` VALUES (19, 16, 9, '菜单管理', 'menu.operation.menu', '/sys/menu', 4, '菜单管理', NULL, 6, 6);
INSERT INTO `s_resource` VALUES (20, NULL, 10, '管理门户根目录', 'menu.sys', '', 3, '管理门户根目录', 'system', 1, NULL);
INSERT INTO `s_resource` VALUES (21, 20, 10, '组织管理', 'menu.sys.org', '/sys/org', 4, '组织管理', 'tree', 1, 4);
INSERT INTO `s_resource` VALUES (22, 20, 10, '角色管理', 'menu.sys.role', '/sys/role', 4, '角色管理', 'user', 6, 2);
INSERT INTO `s_resource` VALUES (23, 20, 10, '用户管理', 'menu.sys.user', '/sys/user', 4, '用户管理', 'user', 2, 3);
INSERT INTO `s_resource` VALUES (24, NULL, 11, '开发门户根菜单', 'menu.develop', '', 3, '开发门户根菜单', NULL, 4, NULL);
INSERT INTO `s_resource` VALUES (25, 24, 11, 'Widget管理', 'menu.develop.widget', '/sys/portlet', 4, 'Widget管理', NULL, 2, 8);
INSERT INTO `s_resource` VALUES (26, 24, 11, '页面管理', 'menu.develop.page', '/sys/page', 4, '页面管理', NULL, 3, 5);
INSERT INTO `s_resource` VALUES (27, NULL, 12, '普通门户根目录', 'menu.normal', '', 3, '普通门户根目录', NULL, 5, NULL);
INSERT INTO `s_resource` VALUES (28, 27, 12, '测试', 'menu.normal.test', '/test', 4, '测试页面', NULL, 2, 29);
INSERT INTO `s_resource` VALUES (29, NULL, 10, '测试页面', 'page.xx', '/test', 5, '测试部门', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tenantId` int(11) NULL DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES (1, 'admin', 'admin', '超级管理员', 1);
INSERT INTO `s_role` VALUES (2, 'role.developer', 'developer', '开发者', 1);
INSERT INTO `s_role` VALUES (3, 'role.normal', 'normal', 'defaultRole为', 1);
INSERT INTO `s_role` VALUES (4, 'admin', '管理员', '租户：测试租户 --- 管理员角色', 3);
INSERT INTO `s_role` VALUES (5, 'role.normal', 'normal', '测试租户--普通角色', 3);
INSERT INTO `s_role` VALUES (6, 'admin', '管理员', '租户：测试租户2 --- 管理员角色', 2);

-- ----------------------------
-- Table structure for s_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `s_role_resource`;
CREATE TABLE `s_role_resource`  (
  `roleId` int(11) NOT NULL,
  `resId` int(11) NOT NULL,
  `type` int(1) NULL DEFAULT 0 COMMENT '1:门户 2：portlet 3: 目录 4：菜单 5: 页面 6: 按钮',
  `portalId` int(11) NULL DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_4`(`resId`) USING BTREE,
  INDEX `FK_PROTAL`(`portalId`) USING BTREE,
  INDEX `FK_Reference_3`(`roleId`) USING BTREE,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`roleId`) REFERENCES `s_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`resId`) REFERENCES `s_resource` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_role_resource
-- ----------------------------
INSERT INTO `s_role_resource` VALUES (4, 10, 1, 10, 1);
INSERT INTO `s_role_resource` VALUES (4, 12, 1, 12, 2);
INSERT INTO `s_role_resource` VALUES (4, 20, 3, 10, 3);
INSERT INTO `s_role_resource` VALUES (4, 21, 4, 10, 4);
INSERT INTO `s_role_resource` VALUES (4, 23, 4, 10, 5);
INSERT INTO `s_role_resource` VALUES (4, 22, 4, 10, 6);
INSERT INTO `s_role_resource` VALUES (1, 10, 1, 10, 7);
INSERT INTO `s_role_resource` VALUES (1, 9, 1, 9, 8);
INSERT INTO `s_role_resource` VALUES (1, 13, 2, 9, 9);
INSERT INTO `s_role_resource` VALUES (1, 17, 4, 9, 10);
INSERT INTO `s_role_resource` VALUES (1, 16, 3, 9, 11);
INSERT INTO `s_role_resource` VALUES (1, 19, 4, 9, 12);
INSERT INTO `s_role_resource` VALUES (1, 20, 3, 10, 13);
INSERT INTO `s_role_resource` VALUES (1, 18, 4, 9, 14);
INSERT INTO `s_role_resource` VALUES (1, 21, 4, 10, 15);
INSERT INTO `s_role_resource` VALUES (1, 23, 4, 10, 16);
INSERT INTO `s_role_resource` VALUES (1, 22, 4, 10, 17);
INSERT INTO `s_role_resource` VALUES (6, 10, 1, 10, 18);
INSERT INTO `s_role_resource` VALUES (6, 20, 3, 10, 19);
INSERT INTO `s_role_resource` VALUES (6, 21, 4, 10, 20);
INSERT INTO `s_role_resource` VALUES (6, 23, 4, 10, 21);
INSERT INTO `s_role_resource` VALUES (6, 22, 4, 10, 22);
INSERT INTO `s_role_resource` VALUES (5, 27, 3, 12, 23);
INSERT INTO `s_role_resource` VALUES (5, 13, 2, 12, 24);
INSERT INTO `s_role_resource` VALUES (5, 12, 1, 12, 25);
INSERT INTO `s_role_resource` VALUES (5, 28, 4, 12, 26);
INSERT INTO `s_role_resource` VALUES (2, 10, 1, 10, 27);
INSERT INTO `s_role_resource` VALUES (2, 9, 1, 9, 28);
INSERT INTO `s_role_resource` VALUES (2, 13, 2, 10, 29);
INSERT INTO `s_role_resource` VALUES (2, 24, 3, 11, 30);
INSERT INTO `s_role_resource` VALUES (2, 15, 2, 10, 31);
INSERT INTO `s_role_resource` VALUES (2, 25, 4, 11, 32);
INSERT INTO `s_role_resource` VALUES (2, 14, 2, 10, 33);
INSERT INTO `s_role_resource` VALUES (2, 26, 4, 11, 34);
INSERT INTO `s_role_resource` VALUES (2, 11, 1, 11, 35);
INSERT INTO `s_role_resource` VALUES (2, 17, 4, 9, 36);
INSERT INTO `s_role_resource` VALUES (2, 16, 3, 9, 37);
INSERT INTO `s_role_resource` VALUES (2, 19, 4, 9, 38);
INSERT INTO `s_role_resource` VALUES (2, 20, 3, 10, 39);
INSERT INTO `s_role_resource` VALUES (2, 18, 4, 9, 40);
INSERT INTO `s_role_resource` VALUES (2, 21, 4, 10, 41);
INSERT INTO `s_role_resource` VALUES (2, 23, 4, 10, 42);
INSERT INTO `s_role_resource` VALUES (2, 22, 4, 10, 43);
INSERT INTO `s_role_resource` VALUES (3, 27, 3, 12, 44);
INSERT INTO `s_role_resource` VALUES (3, 13, 2, 12, 45);
INSERT INTO `s_role_resource` VALUES (3, 12, 1, 12, 46);
INSERT INTO `s_role_resource` VALUES (3, 17, 4, 9, 47);
INSERT INTO `s_role_resource` VALUES (3, 28, 4, 12, 48);
INSERT INTO `s_role_resource` VALUES (3, 16, 3, 9, 49);
INSERT INTO `s_role_resource` VALUES (3, 19, 4, 9, 50);
INSERT INTO `s_role_resource` VALUES (3, 20, 3, 10, 51);
INSERT INTO `s_role_resource` VALUES (3, 21, 4, 10, 52);
INSERT INTO `s_role_resource` VALUES (3, 23, 4, 10, 53);

-- ----------------------------
-- Table structure for s_tenant
-- ----------------------------
DROP TABLE IF EXISTS `s_tenant`;
CREATE TABLE `s_tenant`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rootOrg` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ukey` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `Id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_tenant
-- ----------------------------
INSERT INTO `s_tenant` VALUES (1, '运营方', '运营方租户，用于租户管理、功能配置管理', '珠海格力', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_tenant` VALUES (2, '测试租户2', '测试租户2', '根组织', '5517ab4b-00b9-44e8-b5c6-137dbce8b6d4');
INSERT INTO `s_tenant` VALUES (3, '测试租户', '测试租户', '根组织', 'd9616e54-40e3-475e-9b8f-e34d2a625799');

-- ----------------------------
-- Table structure for s_tenant_portal
-- ----------------------------
DROP TABLE IF EXISTS `s_tenant_portal`;
CREATE TABLE `s_tenant_portal`  (
  `tenantId` int(11) NULL DEFAULT NULL,
  `portalId` int(11) NULL DEFAULT NULL,
  INDEX `forkey_1`(`tenantId`) USING BTREE,
  INDEX `forkey_2`(`portalId`) USING BTREE,
  CONSTRAINT `forkey_1` FOREIGN KEY (`tenantId`) REFERENCES `s_tenant` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `forkey_2` FOREIGN KEY (`portalId`) REFERENCES `s_resource` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_tenant_portal
-- ----------------------------
INSERT INTO `s_tenant_portal` VALUES (1, 9);
INSERT INTO `s_tenant_portal` VALUES (1, 10);
INSERT INTO `s_tenant_portal` VALUES (1, 11);
INSERT INTO `s_tenant_portal` VALUES (1, 12);
INSERT INTO `s_tenant_portal` VALUES (3, 10);
INSERT INTO `s_tenant_portal` VALUES (3, 12);
INSERT INTO `s_tenant_portal` VALUES (2, 12);
INSERT INTO `s_tenant_portal` VALUES (2, 10);

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgId` int(11) NULL DEFAULT NULL,
  `isDisabled` int(1) NULL DEFAULT 0 COMMENT '可以禁用用户，只有两种状态 ：启用，禁用',
  `remarks` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT 0 COMMENT '用户类型，区分是内部用户还是外部用户，1：内，0：外',
  `avatar` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `tenantId` int(11) NULL DEFAULT NULL COMMENT '租户id',
  `sex` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `Id`(`id`) USING BTREE,
  INDEX `Id_2`(`id`) USING BTREE,
  INDEX `Id_3`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES (1, 'admin', '$2a$10$c7cKaC9AliKA661jFCQ4Zu1GzBMdfNSY0HXhxXNlgmGB3wMqRq9by', 'admin', '13333333333', '123@126.com', 1, 0, '我是管理员', 1, 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png', 1, 1);
INSERT INTO `s_user` VALUES (2, 'developer', '$2a$10$c7cKaC9AliKA661jFCQ4Zu1GzBMdfNSY0HXhxXNlgmGB3wMqRq9by', 'developer', '13333333333', '123@126.com', 1, 0, '我是开发工', 1, 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png', 1, 1);
INSERT INTO `s_user` VALUES (3, 'normal', '$2a$10$jdIwxDxc51OxT4gz/KL1/ei9pXP6AdMy3nd5junCf4fcEQ82zFp5C', '普通用户', '13800000000', '13800000000@qq.com', 1, 0, NULL, 0, NULL, 1, 1);
INSERT INTO `s_user` VALUES (4, 'admin', '$2a$10$SSrAP5JByC02jjgCPso9FejNAv0W8rebIwbEFGykayJDdeeKRGa9a', '测试租户 --- 管理员', '13800000000', '13800000000@qq.com', 2, 0, '租户：测试租户 --- 管理员', 1, 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png0', 3, 1);
INSERT INTO `s_user` VALUES (5, 'admin', '$2a$10$L3cSMbttiEaCf8YZykFv0.ZOUBHxQGhipgxGbhAxuyC0rNprhKi36', '测试租户2 --- 管理员', '13800000000', '13800000000@qq.com', 5, 0, '租户：测试租户2 --- 管理员', 1, 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png0', 2, 1);
INSERT INTO `s_user` VALUES (6, 'testUser', '$2a$10$.52y4iN3KW81vp/HL.mw4.nIcSWd6EuF9wN/Wr5pCMgF3ELUunmSC', '测试租户 --- 普通用户', '13800000000', '13800000000@qq.com', 2, 0, NULL, 0, NULL, 3, 1);

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role`  (
  `roleId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`, `userId`) USING BTREE,
  INDEX `FK_Reference_1`(`userId`) USING BTREE,
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`userId`) REFERENCES `s_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`roleId`) REFERENCES `s_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES (1, 1);
INSERT INTO `s_user_role` VALUES (2, 2);
INSERT INTO `s_user_role` VALUES (3, 3);
INSERT INTO `s_user_role` VALUES (4, 4);
INSERT INTO `s_user_role` VALUES (5, 4);
INSERT INTO `s_user_role` VALUES (6, 5);
INSERT INTO `s_user_role` VALUES (5, 6);

-- ----------------------------
-- Table structure for s_user_setting
-- ----------------------------
DROP TABLE IF EXISTS `s_user_setting`;
CREATE TABLE `s_user_setting`  (
  `id` int(11) NOT NULL,
  `theme` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '保存json格式',
  `currentPortal` int(11) NULL DEFAULT NULL COMMENT '当前门户',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`id`) REFERENCES `s_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_user_setting
-- ----------------------------
INSERT INTO `s_user_setting` VALUES (1, '{\"navTheme\":\"dark\",\"primaryColor\":\"#52C41A\",\"layout\":\"sidemenu\",\"contentWidth\":\"Fluid\",\"fixedHeader\":true,\"autoHideHeader\":false,\"fixSiderbar\":false,\"colorWeak\":false}', 9);
INSERT INTO `s_user_setting` VALUES (2, '{\"navTheme\":\"dark\",\"primaryColor\":\"#52C41A\",\"layout\":\"sidemenu\",\"contentWidth\":\"Fluid\",\"fixedHeader\":true,\"autoHideHeader\":false,\"fixSiderbar\":false,\"colorWeak\":false}', 10);
INSERT INTO `s_user_setting` VALUES (4, '{\"navTheme\":\"dark\",\"primaryColor\":\"#52C41A\",\"layout\":\"sidemenu\",\"contentWidth\":\"Fluid\",\"fixedHeader\":true,\"autoHideHeader\":false,\"fixSiderbar\":false,\"colorWeak\":false}', 12);

SET FOREIGN_KEY_CHECKS = 1;
