/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : icecream

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 23/10/2020 14:47:42
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
  `tenantId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
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
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `typeName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `typeCode` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dicName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dicValue` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logicId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tenantId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for s_document
-- ----------------------------
DROP TABLE IF EXISTS `s_document`;
CREATE TABLE `s_document`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uuidFileName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relativeDir` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oFileName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uploadTime` datetime(0) NULL DEFAULT NULL,
  `bizCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `suffix` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fileSize` bigint(20) NULL DEFAULT NULL,
  `tenantId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_document
-- ----------------------------

-- ----------------------------
-- Table structure for s_login_log
-- ----------------------------
DROP TABLE IF EXISTS `s_login_log`;
CREATE TABLE `s_login_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `visitor` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ipAddress` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loginTime` timestamp(0) NULL DEFAULT NULL,
  `logoutTime` timestamp(0) NULL DEFAULT NULL,
  `sessionID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tenantId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_login_log
-- ----------------------------
INSERT INTO `s_login_log` VALUES ('03c5b797-c75a-48d3-9a75-55c99c9abd46', 'admin1', '127.0.0.1', '2020-10-23 09:17:48', '2020-10-23 14:42:28', 'F8B968456690C733F236B128E028C4F7', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('088b5eec-2658-431f-9de9-2bd0accfdc55', 'admin1', '127.0.0.1', '2020-10-23 13:41:41', '2020-10-23 14:42:28', '52E5839B50ACD2DFDAD861339A59FA22', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('0a38bed9-4afb-4871-9d35-67c3f1a0c91c', 'admin1', '127.0.0.1', '2020-10-23 11:34:12', '2020-10-23 14:42:28', '985020661D99B186330B81163CD6BD1B', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('0eec76e4-0afb-4886-8deb-8eef387982e1', 'admin1', '127.0.0.1', '2020-10-23 10:59:07', '2020-10-23 14:42:28', '2811F9D4158376497097F4018F8789BA', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('128b530c-1223-41cf-835c-8a41cebc04c3', 'testUser', '127.0.0.1', '2020-10-23 14:29:57', '2020-10-23 14:32:36', 'B1C5A2F3176FC370764241C1D56E00B5', 'd9616e54-40e3-475e-9b8f-e34d2a625799');
INSERT INTO `s_login_log` VALUES ('2beb7f47-fdd7-4ce9-aead-25b72832b4f2', 'admin1', '127.0.0.1', '2020-10-23 10:43:31', '2020-10-23 14:42:28', 'DE1C62381F21F35F237160B375275136', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('2dd42a33-3698-45e4-88af-86e0451e6319', 'admin', '127.0.0.1', '2020-10-23 14:26:34', '2020-10-23 14:40:34', '05DF3E93E48152FCAE791BD2C4DA0685', 'd9616e54-40e3-475e-9b8f-e34d2a625799');
INSERT INTO `s_login_log` VALUES ('2e596d96-49e3-4608-acfc-a3689e047bb4', 'admin1', '127.0.0.1', '2020-10-23 10:33:18', '2020-10-23 14:42:28', '9E54D88EB935630803430582778EAE9B', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('35e3b7ad-e667-4095-8d94-83b29dd57ba9', 'admin1', '127.0.0.1', '2020-10-23 10:39:34', '2020-10-23 14:42:28', 'C188FA635A20FC30E7221B9006FF188F', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('3869fe2b-dbdd-4347-ad6c-4edf63b231bf', 'admin1', '127.0.0.1', '2020-10-23 13:53:23', '2020-10-23 14:42:28', 'F363DDB3A3269FF0A6AEA7DE2E27E8C5', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('3d07a7d0-8b30-4c0f-a097-9ef546431a19', 'admin1', '127.0.0.1', '2020-10-23 14:07:21', '2020-10-23 14:42:28', '2DD64FF7D4495C802FAEFAC75B195E97', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('40758d3d-08c8-4fb7-a2a4-fdd2209ae841', 'admin1', '127.0.0.1', '2020-10-23 14:10:54', '2020-10-23 14:42:28', '2DD64FF7D4495C802FAEFAC75B195E97', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('46d516e9-d21c-4689-be53-187aa2177050', 'admin1', '127.0.0.1', '2020-10-23 13:42:55', '2020-10-23 14:42:28', 'C4DB36C371C1C640E1026B17D7A70B23', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('666a20bb-c28c-4e5a-a267-9ef8bee7af97', 'admin1', '127.0.0.1', '2020-10-23 09:16:49', '2020-10-23 14:42:28', '3BB367DDE655665FE6C443395F139AB4', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('689725c0-65d9-40c4-bdbc-9b54a3a86ace', 'developer', '127.0.0.1', '2020-10-23 11:17:55', '2020-10-23 11:18:08', '73FF7EA6651F346B48CFA0589EB6D7AB', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('728e0c71-b191-40bf-9b38-d439a1b484e0', 'admin1', '127.0.0.1', '2020-10-23 13:47:22', '2020-10-23 14:42:28', '532462821A889D79545DAC3A96789DAF', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('75d1e172-ed37-4387-b404-a7b5a062b81d', 'admin1', '127.0.0.1', '2020-10-23 13:44:09', '2020-10-23 14:42:28', '7688B9C6A2CD2AD64BED28DA1FFD8D75', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('8068e2a7-4be1-4b90-9e91-4058c48af94d', 'admin1', '127.0.0.1', '2020-10-23 10:37:58', '2020-10-23 14:42:28', '16FC4525FDC87631E731E40D2FB978FF', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('8b591740-e89b-4e3f-a6af-f1c9573014b8', 'admin1', '127.0.0.1', '2020-10-23 10:28:46', '2020-10-23 14:42:28', '977277B25D4ABFB1FD9ECF8DB04B7D48', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('92d01811-b1af-4ccf-b33e-cc7edddf3cce', 'admin1', '127.0.0.1', '2020-10-23 14:07:34', '2020-10-23 14:42:28', '2DD64FF7D4495C802FAEFAC75B195E97', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('93023f99-2841-4f02-a6d1-32f25ab10b74', 'admin1', '127.0.0.1', '2020-10-23 11:18:10', '2020-10-23 14:42:28', '6AA65A4F4F4607F1BF48721FEFB05DB9', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('9bf129d2-e760-4df0-b223-d71a2df8e8b4', 'admin1', '127.0.0.1', '2020-10-23 10:26:26', '2020-10-23 14:42:28', 'FAAF12B6D555DCDCAE907BCA1C294FD3', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('9efa44b1-f22d-459f-aaa3-598ba0131498', 'admin1', '127.0.0.1', '2020-10-23 14:40:55', '2020-10-23 14:42:28', '96C7D4F3CAA53FC8732EBB70AB2E4521', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('aa032c36-f9d2-4bb8-9f56-fc9a8b43be3a', 'admin1', '127.0.0.1', '2020-10-23 10:59:37', '2020-10-23 14:42:28', '75E47B77863AA61A98A52C3B94D74626', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('b1f92156-3f10-4a71-bc5a-2a4d1b1dc545', 'admin1', '127.0.0.1', '2020-10-23 11:17:39', '2020-10-23 14:42:28', 'BEEFBAFA50492EDD035CD073ACDC67ED', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('b7477997-4182-4a29-8fbd-ca08a58cad18', 'developer', '127.0.0.1', '2020-10-23 11:07:41', '2020-10-23 11:18:08', '5FCDAAD4698BA7DFC36941C523CBB4FB', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('c2ec400b-de54-4fcd-a443-3ab3dd4668a3', 'admin1', '127.0.0.1', '2020-10-23 10:38:39', '2020-10-23 14:42:28', '2E87A6EAEC5C54A6F1405195625DFD2D', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('c3a8643f-8ff4-40ec-9108-55c2ec5c4941', 'admin', '127.0.0.1', '2020-10-23 14:42:36', NULL, '8C28E086BD20D2EFE705EA7C4D020090', '5517ab4b-00b9-44e8-b5c6-137dbce8b6d4');
INSERT INTO `s_login_log` VALUES ('c8562466-c4d3-43e5-9584-597ac7f904b8', 'admin1', '127.0.0.1', '2020-10-23 13:56:19', '2020-10-23 14:42:28', '2DD64FF7D4495C802FAEFAC75B195E97', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('c8a4ef78-7555-4021-b77d-7af96e333231', 'admin', '127.0.0.1', '2020-10-23 14:29:20', '2020-10-23 14:40:34', '0BF46236BAD781F635FE14FD8F072445', 'd9616e54-40e3-475e-9b8f-e34d2a625799');
INSERT INTO `s_login_log` VALUES ('cd110570-a921-44d8-89a0-f7eb6cd49903', 'admin1', '127.0.0.1', '2020-10-23 10:30:38', '2020-10-23 14:42:28', '0AFC307F82FAD6308C1683D2467BE719', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('d79ee3e8-7212-4d79-b03e-26e49db3a94f', 'admin', '127.0.0.1', '2020-10-23 14:40:21', '2020-10-23 14:40:34', '1D034A90A652511CC3DE511827AD2529', 'd9616e54-40e3-475e-9b8f-e34d2a625799');
INSERT INTO `s_login_log` VALUES ('e216d29c-28a9-4dca-af54-f4e9f1af5f85', 'admin', '127.0.0.1', '2020-10-23 14:40:10', '2020-10-23 14:40:34', '57273B194D2283C8D435467DCEE3C254', 'd9616e54-40e3-475e-9b8f-e34d2a625799');
INSERT INTO `s_login_log` VALUES ('e35a21ca-dc76-425d-8ee4-0beaa27c10ca', 'normal', '127.0.0.1', '2020-10-23 14:18:38', '2020-10-23 14:26:27', 'D4C73573ED3126C6F574C655C8F59077', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('e6636bca-a774-4798-8dbc-55c36731f210', 'admin1', '127.0.0.1', '2020-10-23 14:11:07', '2020-10-23 14:42:28', '2DD64FF7D4495C802FAEFAC75B195E97', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('eaa5299f-84c4-400d-972d-e466ba0e22d5', 'admin1', '127.0.0.1', '2020-10-23 08:52:44', '2020-10-23 14:42:28', 'C92EB58F734AB84EEE3A534ED398EEB9', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('ebb92891-4335-4c79-8a49-9624b1354125', 'admin1', '127.0.0.1', '2020-10-23 10:27:51', '2020-10-23 14:42:28', 'DD880AB6C638AE38935F96D55D0B01A9', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('ed90ea46-334a-478b-8d58-145b37a89c62', 'admin1', '127.0.0.1', '2020-10-23 10:31:19', '2020-10-23 14:42:28', '1B9129CE42153CC0FEC05E31F94646EA', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_login_log` VALUES ('ff688464-0ec3-4fbf-90d7-26778cf1b82b', 'admin1', '127.0.0.1', '2020-10-23 14:10:41', '2020-10-23 14:42:28', '2DD64FF7D4495C802FAEFAC75B195E97', '52cc23f0-484f-46dd-8af9-30aa649e15a5');

-- ----------------------------
-- Table structure for s_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `s_operate_log`;
CREATE TABLE `s_operate_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `visitor` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ipAddress` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `methodType` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `moduleName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operateName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `descript` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `visitTime` timestamp(0) NULL DEFAULT NULL,
  `sessionID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tenantId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_operate_log
-- ----------------------------
INSERT INTO `s_operate_log` VALUES ('00017550-8239-4d41-8bfa-bf5669e9f31a', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 11:00:09', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('030758f1-43a3-4d52-b490-eb1e3dcfc43f', 'admin1', '127.0.0.1', '/api/sysmgr/user/add', 'POST', '用户管理', '新增用户', NULL, '2020-10-23 14:18:08', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('033d4abd-7cfe-40ab-9e7b-fdc6484e1996', 'admin1', '127.0.0.1', '/api/sysmgr/user/add', 'POST', '用户管理', '新增用户', NULL, '2020-10-23 11:02:31', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('075bd408-c26e-425e-a7de-5614d41e569c', 'admin1', '127.0.0.1', '/api/sysmgr/tenant/add', 'POST', '租户管理', '新增租户', NULL, '2020-10-23 11:18:56', '6AA65A4F4F4607F1BF48721FEFB05DB9', NULL);
INSERT INTO `s_operate_log` VALUES ('08762fe5-70b8-4eb2-9992-34494804ba04', 'admin1', '127.0.0.1', '/api/sysmgr/role/permision/edit', 'PUT', '角色管理', '更新角色权限', NULL, '2020-10-23 11:41:34', '985020661D99B186330B81163CD6BD1B', NULL);
INSERT INTO `s_operate_log` VALUES ('0a1a1510-e42c-43b6-bfef-e94e78c6470d', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 10:38:45', '2E87A6EAEC5C54A6F1405195625DFD2D', NULL);
INSERT INTO `s_operate_log` VALUES ('0c17bf98-8445-4de4-bc3f-81294b98be9a', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 11:00:22', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('0d0d0a6f-1a28-439a-a4ed-c164c5ab7809', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 10:44:43', 'DE1C62381F21F35F237160B375275136', NULL);
INSERT INTO `s_operate_log` VALUES ('0df4d43d-4752-4108-8276-42f6ba646635', 'admin1', '127.0.0.1', '/api/sysmgr/role/permision/edit', 'PUT', '角色管理', '更新角色权限', NULL, '2020-10-23 11:01:07', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('1134986a-87da-43c8-9a4d-b074ba7fde23', 'admin1', '127.0.0.1', '/api/sysmgr/role/permision/edit', 'PUT', '角色管理', '更新角色权限', NULL, '2020-10-23 11:36:31', '985020661D99B186330B81163CD6BD1B', NULL);
INSERT INTO `s_operate_log` VALUES ('1a78b558-8480-41a3-aec4-a6ca361fd3e6', 'admin1', '127.0.0.1', '/api/sysmgr/role/permision/edit', 'PUT', '角色管理', '更新角色权限', NULL, '2020-10-23 14:17:32', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('1b747991-32de-48fe-9be5-5e0eaf9f73ba', 'developer', '127.0.0.1', '/api/sysmgr/menu/del', 'DELETE', '菜单管理', '删除菜单', NULL, '2020-10-23 11:16:36', '5FCDAAD4698BA7DFC36941C523CBB4FB', NULL);
INSERT INTO `s_operate_log` VALUES ('1d5fe4d3-feb1-4213-a249-5b1b584b7c52', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 10:38:04', '16FC4525FDC87631E731E40D2FB978FF', NULL);
INSERT INTO `s_operate_log` VALUES ('211ce39d-989c-410f-9f90-093c5774eed9', 'admin1', '127.0.0.1', '/api/sysmgr/org/add', 'POST', '组织管理', '新增组织', NULL, '2020-10-23 11:02:04', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('262aeafa-62c1-461d-8631-7cb2defd3b7f', 'admin', '127.0.0.1', '/api/sysmgr/user/edit', 'PUT', '用户管理', '更新用户', NULL, '2020-10-23 14:29:01', '05DF3E93E48152FCAE791BD2C4DA0685', NULL);
INSERT INTO `s_operate_log` VALUES ('270f464f-2859-4aa7-8268-c4f0ce7a2c14', 'admin1', '127.0.0.1', '/api/sysmgr/org/del', 'DELETE', '组织管理', '删除组织', NULL, '2020-10-23 11:02:07', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('288b23b7-fc90-4f35-8b8f-57997e92cfd2', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 14:13:10', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('2ba9eab4-3d51-467a-9968-1f75cb6f601d', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 10:31:39', '1B9129CE42153CC0FEC05E31F94646EA', NULL);
INSERT INTO `s_operate_log` VALUES ('2c1008da-d70e-434e-af4f-aa02dd7618eb', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 13:57:50', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('2c86f3db-a298-4065-b3b2-d591dba656f2', 'admin', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 14:29:28', '0BF46236BAD781F635FE14FD8F072445', NULL);
INSERT INTO `s_operate_log` VALUES ('2d0e3964-d285-44be-9ab1-05bbc89df9f7', 'developer', '127.0.0.1', '/api/sysmgr/portal/del', 'DELETE', 'Portal管理', '删除Portal', NULL, '2020-10-23 11:12:32', '5FCDAAD4698BA7DFC36941C523CBB4FB', NULL);
INSERT INTO `s_operate_log` VALUES ('2d7255ad-f5a7-42eb-84ce-0a2ba8d0645f', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 10:39:57', 'C188FA635A20FC30E7221B9006FF188F', NULL);
INSERT INTO `s_operate_log` VALUES ('2f8e1f0e-4ebf-4886-bf57-973008de720a', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 11:41:50', '985020661D99B186330B81163CD6BD1B', NULL);
INSERT INTO `s_operate_log` VALUES ('32e78e59-ca71-4547-9b54-bd177ae3217a', 'admin', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 14:28:27', '05DF3E93E48152FCAE791BD2C4DA0685', NULL);
INSERT INTO `s_operate_log` VALUES ('3a24d110-d125-4853-b717-42d4b7906b3f', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 14:13:53', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('3b816937-08ca-49f1-9bb5-dae51ca52b67', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 11:40:37', '985020661D99B186330B81163CD6BD1B', NULL);
INSERT INTO `s_operate_log` VALUES ('3b9b41de-29a8-4da8-b24d-f445fd9b9cba', 'admin1', '127.0.0.1', '/api/sysmgr/role/permision/edit', 'PUT', '角色管理', '更新角色权限', NULL, '2020-10-23 11:36:38', '985020661D99B186330B81163CD6BD1B', NULL);
INSERT INTO `s_operate_log` VALUES ('3fb963a0-cc88-47e8-bd11-2c1f612c4b64', 'admin1', '127.0.0.1', '/api/sysmgr/portal/add', 'POST', 'Portal管理', '新增Portal', NULL, '2020-10-23 14:15:09', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('415af6a2-7d07-43f7-b262-a91b6ecc0ee8', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 11:35:19', '985020661D99B186330B81163CD6BD1B', NULL);
INSERT INTO `s_operate_log` VALUES ('41e868d5-ff09-43f1-943b-6ffb7fae8a5f', 'admin1', '127.0.0.1', '/api/sysmgr/user/edit', 'PUT', '用户管理', '更新用户', NULL, '2020-10-23 11:02:39', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('441e349c-6c3e-4a39-9376-64a35eac4e1b', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 10:45:04', 'DE1C62381F21F35F237160B375275136', NULL);
INSERT INTO `s_operate_log` VALUES ('44222a72-5e08-4196-acb0-08b333ab8fef', 'admin1', '127.0.0.1', '/api/sysmgr/user/del', 'DELETE', '用户管理', '删除用户', NULL, '2020-10-23 11:02:55', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('44517b84-ed5f-403c-a4ef-ed5a4968a4f7', 'developer', '127.0.0.1', '/api/sysmgr/menu/add', 'POST', '菜单管理', '新增菜单', NULL, '2020-10-23 11:14:31', '5FCDAAD4698BA7DFC36941C523CBB4FB', NULL);
INSERT INTO `s_operate_log` VALUES ('4659b025-f55d-466f-ab98-aae4272c0571', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 14:11:26', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('46ec2030-990d-47b6-972b-1650d9eb1097', 'admin1', '127.0.0.1', '/api/sysmgr/role/permision/edit', 'PUT', '角色管理', '更新角色权限', NULL, '2020-10-23 11:36:07', '985020661D99B186330B81163CD6BD1B', NULL);
INSERT INTO `s_operate_log` VALUES ('49b73acf-443e-4823-af0f-f4531aee216d', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 14:41:00', '96C7D4F3CAA53FC8732EBB70AB2E4521', NULL);
INSERT INTO `s_operate_log` VALUES ('5418e10b-8425-4ba2-96e4-d580a504bfe3', 'admin1', '127.0.0.1', '/api/sysmgr/tenant/edit', 'PUT', '租户管理', '更新租户', NULL, '2020-10-23 14:16:43', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('561f2e90-78f7-489e-b26f-eb2ca7d6c59c', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 10:39:40', 'C188FA635A20FC30E7221B9006FF188F', NULL);
INSERT INTO `s_operate_log` VALUES ('56eac3f1-3d6e-43d6-a1ff-8dc4a20f6844', 'admin', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 14:27:30', '05DF3E93E48152FCAE791BD2C4DA0685', NULL);
INSERT INTO `s_operate_log` VALUES ('577b8827-85da-4b9f-9ab2-2dd6ca4e28bd', 'admin1', '127.0.0.1', '/api/sysmgr/org/del', 'DELETE', '组织管理', '删除组织', NULL, '2020-10-23 11:02:09', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('5965e36a-2497-44d9-966f-34dcec238e66', 'developer', '127.0.0.1', '/api/sysmgr/portal/edit', 'PUT', 'Portal管理', '更新Portal', NULL, '2020-10-23 11:12:21', '5FCDAAD4698BA7DFC36941C523CBB4FB', NULL);
INSERT INTO `s_operate_log` VALUES ('6211d8f4-b291-41d0-b2f4-a35984769cef', 'admin1', '127.0.0.1', '/api/sysmgr/tenant/edit', 'PUT', '租户管理', '更新租户', NULL, '2020-10-23 14:42:13', '96C7D4F3CAA53FC8732EBB70AB2E4521', NULL);
INSERT INTO `s_operate_log` VALUES ('67971075-8313-4b61-9486-bcb95e8cdcbb', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 10:45:28', 'DE1C62381F21F35F237160B375275136', NULL);
INSERT INTO `s_operate_log` VALUES ('6956c744-c32c-4c95-9d52-5f75fb9fbfad', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 10:59:19', '2811F9D4158376497097F4018F8789BA', NULL);
INSERT INTO `s_operate_log` VALUES ('6ca10fc3-3de9-40b4-acf7-eb0509b2ba8e', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 09:21:36', 'F8B968456690C733F236B128E028C4F7', NULL);
INSERT INTO `s_operate_log` VALUES ('6de38a6a-813d-423d-b08d-cbcdb2aee781', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 10:44:17', 'DE1C62381F21F35F237160B375275136', NULL);
INSERT INTO `s_operate_log` VALUES ('73507004-c510-46b1-b18a-b7d61fc8b72a', 'admin1', '127.0.0.1', '/api/sysmgr/menu/add', 'POST', '菜单管理', '新增菜单', NULL, '2020-10-23 14:16:17', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('744932c3-589d-4829-b73d-e040e4904233', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 10:45:22', 'DE1C62381F21F35F237160B375275136', NULL);
INSERT INTO `s_operate_log` VALUES ('76c4b6ac-8b83-416e-a9f8-da9e45c24adf', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 10:44:00', 'DE1C62381F21F35F237160B375275136', NULL);
INSERT INTO `s_operate_log` VALUES ('79c80d78-b55b-4272-8270-6cbbfe876413', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 10:46:55', 'DE1C62381F21F35F237160B375275136', NULL);
INSERT INTO `s_operate_log` VALUES ('79f374d7-57b2-405c-8e75-efbe87d3ad1d', 'developer', '127.0.0.1', '/api/sysmgr/menu/add', 'POST', '菜单管理', '新增菜单', NULL, '2020-10-23 11:13:21', '5FCDAAD4698BA7DFC36941C523CBB4FB', NULL);
INSERT INTO `s_operate_log` VALUES ('7b5683cb-1045-4996-89fd-74b237a2d970', 'developer', '127.0.0.1', '/api/sysmgr/portal/add', 'POST', 'Portal管理', '新增Portal', NULL, '2020-10-23 11:12:30', '5FCDAAD4698BA7DFC36941C523CBB4FB', NULL);
INSERT INTO `s_operate_log` VALUES ('7be465ec-4534-410d-bb5a-6ca1c182dfb4', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 09:19:07', 'F8B968456690C733F236B128E028C4F7', NULL);
INSERT INTO `s_operate_log` VALUES ('7ec3e681-6f09-4567-ae56-34fcc8c6152b', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 14:17:04', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('804d1975-2c81-43ea-8772-8eabc2e65262', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 10:44:36', 'DE1C62381F21F35F237160B375275136', NULL);
INSERT INTO `s_operate_log` VALUES ('83277670-accf-4e38-9c28-073bbb1661fc', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 11:18:42', '6AA65A4F4F4607F1BF48721FEFB05DB9', NULL);
INSERT INTO `s_operate_log` VALUES ('882d3b79-be6f-4c7a-a30c-65944cbd567b', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 09:13:55', 'C92EB58F734AB84EEE3A534ED398EEB9', NULL);
INSERT INTO `s_operate_log` VALUES ('8a17f0b3-a2f2-4ef8-9e38-78cc7c93a8bc', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 10:38:12', '16FC4525FDC87631E731E40D2FB978FF', NULL);
INSERT INTO `s_operate_log` VALUES ('8f2e8f1e-c57c-47d3-910e-ec63de34a4af', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 10:44:51', 'DE1C62381F21F35F237160B375275136', NULL);
INSERT INTO `s_operate_log` VALUES ('901ae9bc-5af3-4c26-8099-358294be1973', 'admin1', '127.0.0.1', '/api/sysmgr/org/edit', 'PUT', '组织管理', '更新组织', NULL, '2020-10-23 11:01:57', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('9154c650-0bf7-4ab2-b3f6-5ce8e170a606', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 10:26:40', 'FAAF12B6D555DCDCAE907BCA1C294FD3', NULL);
INSERT INTO `s_operate_log` VALUES ('971991ab-620c-418b-9bd9-2381039eee34', 'developer', '127.0.0.1', '/api/sysmgr/page/add', 'POST', 'Page管理', '新增Page', NULL, '2020-10-23 11:08:48', '5FCDAAD4698BA7DFC36941C523CBB4FB', NULL);
INSERT INTO `s_operate_log` VALUES ('97cfa566-8bd1-478d-a116-c8b6fd5e4c7c', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 11:40:23', '985020661D99B186330B81163CD6BD1B', NULL);
INSERT INTO `s_operate_log` VALUES ('988a70f7-7fc7-4614-8b42-10b81489b041', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 09:26:01', 'F8B968456690C733F236B128E028C4F7', NULL);
INSERT INTO `s_operate_log` VALUES ('a31dae23-4b49-4d03-a216-fb437f99828f', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 09:13:15', 'C92EB58F734AB84EEE3A534ED398EEB9', NULL);
INSERT INTO `s_operate_log` VALUES ('a32eeaf8-d1ac-44a0-8c16-9e5c75bb035f', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 11:00:05', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('a91f7991-81f1-4860-949b-0b2d57d39027', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 13:58:16', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('a9e2faef-1b6d-47aa-85f3-11608b24dfed', 'admin1', '127.0.0.1', '/api/sysmgr/org/add', 'POST', '组织管理', '新增组织', NULL, '2020-10-23 11:01:50', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('b4790f5f-899b-4065-901a-d8423aa34605', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 14:11:58', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('b4f83eec-e48e-4a31-ad86-bbcd34141e1b', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 10:39:47', 'C188FA635A20FC30E7221B9006FF188F', NULL);
INSERT INTO `s_operate_log` VALUES ('b58e278c-9b49-4a30-9f77-7c795476a82e', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 10:59:50', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('b6ad2658-7f83-4f8f-8e2b-8251e46b62da', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 14:17:22', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('b80a6520-6715-4873-b8c6-3d205d59258e', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 11:37:15', '985020661D99B186330B81163CD6BD1B', NULL);
INSERT INTO `s_operate_log` VALUES ('bdc8915c-ab38-4423-b7e8-50b4b0767205', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 10:59:45', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('bff50363-f3b2-42c7-818a-b4f63a6354a2', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 09:23:54', 'F8B968456690C733F236B128E028C4F7', NULL);
INSERT INTO `s_operate_log` VALUES ('c0691598-c9b5-4ab4-aae5-28ff8252d341', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 10:46:48', 'DE1C62381F21F35F237160B375275136', NULL);
INSERT INTO `s_operate_log` VALUES ('c5afe3d5-4437-4f33-a2b3-3e495f344340', 'developer', '127.0.0.1', '/api/sysmgr/menu/add', 'POST', '菜单管理', '新增菜单', NULL, '2020-10-23 11:15:20', '5FCDAAD4698BA7DFC36941C523CBB4FB', NULL);
INSERT INTO `s_operate_log` VALUES ('c5c3b9ff-6143-4fba-ae25-afca66a615e7', 'admin1', '127.0.0.1', '/api/sysmgr/role/permision/edit', 'PUT', '角色管理', '更新角色权限', NULL, '2020-10-23 11:00:36', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('c5fae753-bbf5-4265-8b84-0c3b3b06c317', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 10:43:34', 'DE1C62381F21F35F237160B375275136', NULL);
INSERT INTO `s_operate_log` VALUES ('c94ce982-a50c-4bc0-9a8b-1d6d1ec3faef', 'admin1', '127.0.0.1', '/api/sysmgr/tenant/edit', 'PUT', '租户管理', '更新租户', NULL, '2020-10-23 14:16:52', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('d0562922-547f-4ec8-82fb-dd0bc6ab50db', 'admin1', '127.0.0.1', '/api/sysmgr/tenant/edit', 'PUT', '租户管理', '更新租户', NULL, '2020-10-23 11:34:59', '985020661D99B186330B81163CD6BD1B', NULL);
INSERT INTO `s_operate_log` VALUES ('d1e4ef87-b6b5-4653-bd99-9bcf417f6153', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 11:17:43', 'BEEFBAFA50492EDD035CD073ACDC67ED', NULL);
INSERT INTO `s_operate_log` VALUES ('d6270fc5-de64-4be1-9834-62150c864a2b', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 09:17:58', 'F8B968456690C733F236B128E028C4F7', NULL);
INSERT INTO `s_operate_log` VALUES ('d6df63ee-6fa0-46b6-8bad-d9a486d110bf', 'developer', '127.0.0.1', '/api/sysmgr/portlet/add', 'POST', 'Portlet管理', '新增Portlet', NULL, '2020-10-23 11:09:06', '5FCDAAD4698BA7DFC36941C523CBB4FB', NULL);
INSERT INTO `s_operate_log` VALUES ('d87a34b2-c59d-4c0d-8f21-61fa40b7ad86', 'developer', '127.0.0.1', '/api/sysmgr/menu/del', 'DELETE', '菜单管理', '删除菜单', NULL, '2020-10-23 11:16:05', '5FCDAAD4698BA7DFC36941C523CBB4FB', NULL);
INSERT INTO `s_operate_log` VALUES ('ddc94dbf-6786-403c-961c-0c875b224aad', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 10:59:56', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('e1df9d84-8a00-4c3c-bb25-95aaf6238ddb', 'admin1', '127.0.0.1', '/api/sysmgr/role/permision/edit', 'PUT', '角色管理', '更新角色权限', NULL, '2020-10-23 11:00:44', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('e29f4adc-fb8b-47fd-a782-fc8e33ac76ff', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 11:18:15', '6AA65A4F4F4607F1BF48721FEFB05DB9', NULL);
INSERT INTO `s_operate_log` VALUES ('e6f26286-b8a7-4231-beca-d596e51c8960', 'admin1', '127.0.0.1', '/api/sysmgr/tenant/add', 'POST', '租户管理', '新增租户', NULL, '2020-10-23 11:19:12', '6AA65A4F4F4607F1BF48721FEFB05DB9', NULL);
INSERT INTO `s_operate_log` VALUES ('e851798c-9575-4e30-b341-281d7d2edb51', 'admin', '127.0.0.1', '/api/sysmgr/user/edit', 'PUT', '用户管理', '更新用户', NULL, '2020-10-23 14:28:55', '05DF3E93E48152FCAE791BD2C4DA0685', NULL);
INSERT INTO `s_operate_log` VALUES ('ea2152c5-f990-45a5-941d-1e09c04dd6c1', 'developer', '127.0.0.1', '/api/sysmgr/portal/add', 'POST', 'Portal管理', '新增Portal', NULL, '2020-10-23 11:12:14', '5FCDAAD4698BA7DFC36941C523CBB4FB', NULL);
INSERT INTO `s_operate_log` VALUES ('ea697905-f4e3-4208-9682-936c28d7696e', 'admin', '127.0.0.1', '/api/sysmgr/role/permision/edit', 'PUT', '角色管理', '更新角色权限', NULL, '2020-10-23 14:28:39', '05DF3E93E48152FCAE791BD2C4DA0685', NULL);
INSERT INTO `s_operate_log` VALUES ('eaba6233-40c1-4cb9-860a-098bdb7a4c5b', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 11:35:45', '985020661D99B186330B81163CD6BD1B', NULL);
INSERT INTO `s_operate_log` VALUES ('ee2c07d2-f50d-4e8d-a0fd-4cd885fd5869', 'admin1', '127.0.0.1', '/api/sysmgr/tenant/add', 'POST', '租户管理', '新增租户', NULL, '2020-10-23 14:41:47', '96C7D4F3CAA53FC8732EBB70AB2E4521', NULL);
INSERT INTO `s_operate_log` VALUES ('ef31ecda-e700-44d8-a241-2617bc607960', 'admin1', '127.0.0.1', '/api/sysmgr/role/edit', 'PUT', '角色管理', '更新角色', NULL, '2020-10-23 11:00:00', '75E47B77863AA61A98A52C3B94D74626', NULL);
INSERT INTO `s_operate_log` VALUES ('f0753aa4-5ffc-4462-8f43-dcd4d13d6a15', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 10:43:41', 'DE1C62381F21F35F237160B375275136', NULL);
INSERT INTO `s_operate_log` VALUES ('f1f8fff4-1c3b-4424-bc81-87813e5943f6', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 10:39:08', '2E87A6EAEC5C54A6F1405195625DFD2D', NULL);
INSERT INTO `s_operate_log` VALUES ('f40a1e2d-aca1-4325-922d-106dbea3c1b3', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 10:33:30', '9E54D88EB935630803430582778EAE9B', NULL);
INSERT INTO `s_operate_log` VALUES ('f674af2e-92ec-4c83-8f83-3ef4e5228bdc', 'admin1', '127.0.0.1', '/api/sysmgr/role/add', 'POST', '角色管理', '新增角色', NULL, '2020-10-23 09:17:02', '3BB367DDE655665FE6C443395F139AB4', NULL);
INSERT INTO `s_operate_log` VALUES ('f824448d-312e-4ec9-b2a4-5371f2c85d11', 'admin1', '127.0.0.1', '/api/sysmgr/user/setting', 'PUT', '用户信息管理', '更新用户个人设置', NULL, '2020-10-23 14:07:42', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('f8ff3254-d632-46a9-835d-07bea6eca031', 'admin1', '127.0.0.1', '/api/sysmgr/menu/add', 'POST', '菜单管理', '新增菜单', NULL, '2020-10-23 14:15:43', '2DD64FF7D4495C802FAEFAC75B195E97', NULL);
INSERT INTO `s_operate_log` VALUES ('fd4bc870-85e1-4e21-9995-460b9b91ba43', 'developer', '127.0.0.1', '/api/sysmgr/portal/edit', 'PUT', 'Portal管理', '更新Portal', NULL, '2020-10-23 11:12:25', '5FCDAAD4698BA7DFC36941C523CBB4FB', NULL);

-- ----------------------------
-- Table structure for s_org
-- ----------------------------
DROP TABLE IF EXISTS `s_org`;
CREATE TABLE `s_org`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parentId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `orderNo` int(11) NULL DEFAULT NULL,
  `remarks` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tenantId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_org
-- ----------------------------
INSERT INTO `s_org` VALUES ('08d0483a-bee1-4760-aab5-189869eb6112', '', '根组织', 'cczz', 1, 1, '根组织', 'd9616e54-40e3-475e-9b8f-e34d2a625799');
INSERT INTO `s_org` VALUES ('3631caf8-5fde-4bc3-8905-43a5f3100bdd', '', '根组织', NULL, 1, 1, '根组织', '5517ab4b-00b9-44e8-b5c6-137dbce8b6d4');
INSERT INTO `s_org` VALUES ('7e667219-1c0c-4a30-bf18-c621135082da', '08d0483a-bee1-4760-aab5-189869eb6112', '测试', 'org.xx', NULL, 2, '测试部门', 'd9616e54-40e3-475e-9b8f-e34d2a625799');
INSERT INTO `s_org` VALUES ('8cec04a2-2f5f-41ee-b974-e68b603b32e4', '', '珠海格力', 'ZHGREE', 1, 1, '根目录', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_org` VALUES ('9f73cf44-2d44-41ba-9772-1ffe4deb914c', '7e667219-1c0c-4a30-bf18-c621135082da', '测试2', 'org.xx', NULL, 2, '测试部门', 'd9616e54-40e3-475e-9b8f-e34d2a625799');

-- ----------------------------
-- Table structure for s_page
-- ----------------------------
DROP TABLE IF EXISTS `s_page`;
CREATE TABLE `s_page`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_page
-- ----------------------------

-- ----------------------------
-- Table structure for s_resource
-- ----------------------------
DROP TABLE IF EXISTS `s_resource`;
CREATE TABLE `s_resource`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parentId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `portalId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\r\n',
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL COMMENT '1 门户，2: portlet，3：目录 4：菜单 5：页面  6：按钮                      ',
  `remarks` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderNo` int(11) NULL DEFAULT NULL,
  `relationId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联了哪个资源，比如菜单关联页面',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_resource
-- ----------------------------
INSERT INTO `s_resource` VALUES ('02c1f046-0ede-4dee-92b0-ae1a1e21a8d4', NULL, '1d2e030e-0a65-4239-bfae-1dace826e581', '角色管理页面', 'page.sys.role', '/sys/role', 5, '角色管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('15924764-f358-41a7-a62c-9d70adf19801', '', '8a12b2b5-e636-468e-a914-92209d2304e2', '普通门户根目录', 'menu.normal', '', 3, '普通门户根目录', NULL, 5, NULL);
INSERT INTO `s_resource` VALUES ('196fea9a-5f43-4e80-ac43-0a7c8a3d09ee', NULL, '1d2e030e-0a65-4239-bfae-1dace826e58b', 'Widget管理页面', 'page.sys.portlet', '/sys/portlet', 5, 'Widget管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('1d2e030e-0a65-4239-bfae-1dace826e581', NULL, '1d2e030e-0a65-4239-bfae-1dace826e581', '管理门户', 'protal.sys', '', 1, '管理门户', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('1d2e030e-0a65-4239-bfae-1dace826e58b', NULL, '1d2e030e-0a65-4239-bfae-1dace826e58b', '运营门户', 'protal.operation', '', 1, '运营门户', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('26f13b0b-625c-471b-a26f-77350445061a', NULL, '1d2e030e-0a65-4239-bfae-1dace826e581', '测试', 'portlet.xx', NULL, 2, 'ww', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('2c80a76b-caa2-4d4e-975e-b90a368bffca', '', '970fcad9-93c0-4e0f-9107-e6e60865add0', '开发门户根菜单', 'menu.develop', '', 3, '开发门户根菜单', NULL, 4, NULL);
INSERT INTO `s_resource` VALUES ('4b60e8fb-335c-4509-860d-605281f9a43f', NULL, '1d2e030e-0a65-4239-bfae-1dace826e581', '用户管理页面', 'page.sys.user', '/sys/user', 5, '用户管理页面', NULL, NULL, '');
INSERT INTO `s_resource` VALUES ('6db80677-45fc-4e5a-8709-cca126c2b6dc', '2c80a76b-caa2-4d4e-975e-b90a368bffca', '970fcad9-93c0-4e0f-9107-e6e60865add0', 'Widget管理', 'menu.develop.widget', '/sys/portlet', 4, 'Widget管理', NULL, 2, '196fea9a-5f43-4e80-ac43-0a7c8a3d09ee');
INSERT INTO `s_resource` VALUES ('80d53363-9a50-4f92-b6b1-f90416f6d780', '2c80a76b-caa2-4d4e-975e-b90a368bffca', '970fcad9-93c0-4e0f-9107-e6e60865add0', '页面管理', 'menu.develop.page', '/sys/page', 4, '页面管理', NULL, 3, 'e30257b1-3692-471c-b0f2-f40994790e71');
INSERT INTO `s_resource` VALUES ('8a12b2b5-e636-468e-a914-92209d2304e2', NULL, NULL, '普通门户', 'portal.normal', NULL, 1, '普通门户', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('8cb252b0-0ee9-459c-b228-698ece089cd6', NULL, '1d2e030e-0a65-4239-bfae-1dace826e58b', '租户管理页面', 'page.sys.tenant', '/sys/tenant', 5, '租户管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('91c07e8f-28c7-49f6-bec9-10ddeb497795', NULL, '1d2e030e-0a65-4239-bfae-1dace826e581', '测试页面', 'page.xx', '/test', 5, '测试部门', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('946d7e82-edc7-4b4d-bd0b-c1e64ea691f9', '', '1d2e030e-0a65-4239-bfae-1dace826e581', '组织管理页面', 'page.sys.org', '/sys/org', 5, '组织管理页面', NULL, NULL, '');
INSERT INTO `s_resource` VALUES ('970fcad9-93c0-4e0f-9107-e6e60865add0', NULL, NULL, '开发门户', 'portal.develop', NULL, 1, '开发门户', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('a582b5f2-52ef-449d-949d-0f9c04585b2c', 'ca662ba9-be0e-4021-bf1d-ffca4ec9f1c2', '1d2e030e-0a65-4239-bfae-1dace826e58b', '租户管理', 'menu.operation.tenant', '/sys/tenant', 4, '租户管理', NULL, 2, '8cb252b0-0ee9-459c-b228-698ece089cd6');
INSERT INTO `s_resource` VALUES ('ab9be669-cf9f-474e-afac-4eed3d742906', '15924764-f358-41a7-a62c-9d70adf19801', '8a12b2b5-e636-468e-a914-92209d2304e2', '测试', 'menu.normal.test', '/test', 4, '测试页面', NULL, 2, '91c07e8f-28c7-49f6-bec9-10ddeb497795');
INSERT INTO `s_resource` VALUES ('ca662ba9-be0e-4021-bf1d-ffca4ec9f1c2', '', '1d2e030e-0a65-4239-bfae-1dace826e58b', '运营门户根目录', 'menu.operation', '', 3, '运营门户根目录', NULL, 3, NULL);
INSERT INTO `s_resource` VALUES ('ca9cf5cd-9850-426b-bd8f-04df4f0de86a', 'ca662ba9-be0e-4021-bf1d-ffca4ec9f1c2', '1d2e030e-0a65-4239-bfae-1dace826e58b', '菜单管理', 'menu.operation.menu', '/sys/menu', 4, '菜单管理', NULL, 6, 'fb59c10d-b3a9-4b3b-9646-65da7a1669ad');
INSERT INTO `s_resource` VALUES ('d309dad3-8301-433b-a6f0-7feacd7ff2a5', '', '1d2e030e-0a65-4239-bfae-1dace826e581', '管理门户根目录', 'menu.sys', '', 3, '管理门户根目录', 'system', 1, '');
INSERT INTO `s_resource` VALUES ('d9b076b4-b4df-49ed-95c0-f8f789426722', 'ca662ba9-be0e-4021-bf1d-ffca4ec9f1c2', '1d2e030e-0a65-4239-bfae-1dace826e58b', '门户管理', 'menu.operation.portal', '/sys/portal', 4, '门户管理', NULL, 3, 'dbe4decc-858f-4900-ba56-e3758e96c80d');
INSERT INTO `s_resource` VALUES ('dbe4decc-858f-4900-ba56-e3758e96c80d', NULL, '1d2e030e-0a65-4239-bfae-1dace826e58b', '门户管理页面', 'page.sys.protal', '/sys/portal', 5, '门户管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('e30257b1-3692-471c-b0f2-f40994790e71', NULL, '1d2e030e-0a65-4239-bfae-1dace826e58b', '页面管理页面', 'page.sys.page', '/sys/page', 5, '页面管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('ef6ed001-c7c8-45d7-bb97-66f8250d09b1', 'd309dad3-8301-433b-a6f0-7feacd7ff2a5', '1d2e030e-0a65-4239-bfae-1dace826e581', '组织管理', 'menu.sys.org', '/sys/org', 4, '组织管理', 'tree', 1, '946d7e82-edc7-4b4d-bd0b-c1e64ea691f9');
INSERT INTO `s_resource` VALUES ('ef6ed001-c7c8-45d7-bb97-66f8250d09b2', 'd309dad3-8301-433b-a6f0-7feacd7ff2a5', '1d2e030e-0a65-4239-bfae-1dace826e581', '用户管理', 'menu.sys.user', '/sys/user', 4, '用户管理', 'user', 2, '4b60e8fb-335c-4509-860d-605281f9a43f');
INSERT INTO `s_resource` VALUES ('ef6ed001-c7c8-45d7-bb97-66f8250d09b7', 'd309dad3-8301-433b-a6f0-7feacd7ff2a5', '1d2e030e-0a65-4239-bfae-1dace826e581', '角色管理', 'menu.sys.role', '/sys/role', 4, '角色管理', 'user', 6, '02c1f046-0ede-4dee-92b0-ae1a1e21a8d4');
INSERT INTO `s_resource` VALUES ('fb59c10d-b3a9-4b3b-9646-65da7a1669ad', NULL, '1d2e030e-0a65-4239-bfae-1dace826e58b', '菜单管理页面', 'page.sys.menu', '/sys/menu', 5, '菜单管理页面', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tenantId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('20558310-a5b6-4cec-be5f-ff063f1f11dc', 'admin', '管理员', '租户：测试租户 --- 管理员角色', 'd9616e54-40e3-475e-9b8f-e34d2a625799');
INSERT INTO `s_role` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', 'admin', 'admin', '超级管理员', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_role` VALUES ('4cb43472-b2e3-4f43-a558-f43df683a685', 'admin', '管理员', '租户：测试租户2 --- 管理员角色', '5517ab4b-00b9-44e8-b5c6-137dbce8b6d4');
INSERT INTO `s_role` VALUES ('5d10329a-cb2e-4cbc-a105-982ecec2641e', 'role.normal', 'normal', '测试租户--普通角色', 'd9616e54-40e3-475e-9b8f-e34d2a625799');
INSERT INTO `s_role` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', 'role.developer', 'developer', '开发者', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_role` VALUES ('e688ba8b-ef5c-4459-a74e-6f4812411cfe', 'role.normal', 'normal', 'defaultRole为', '52cc23f0-484f-46dd-8af9-30aa649e15a5');

-- ----------------------------
-- Table structure for s_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `s_role_resource`;
CREATE TABLE `s_role_resource`  (
  `roleId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int(1) NULL DEFAULT 0 COMMENT '1:门户 2：portlet 3: 目录 4：菜单 5: 页面 6: 按钮',
  `portalId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`roleId`, `resId`, `id`) USING BTREE,
  INDEX `FK_Reference_4`(`resId`) USING BTREE,
  INDEX `FK_PROTAL`(`portalId`) USING BTREE,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`roleId`) REFERENCES `s_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`resId`) REFERENCES `s_resource` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_role_resource
-- ----------------------------
INSERT INTO `s_role_resource` VALUES ('20558310-a5b6-4cec-be5f-ff063f1f11dc', '1d2e030e-0a65-4239-bfae-1dace826e581', 1, '1d2e030e-0a65-4239-bfae-1dace826e581', '9033e57e-ec90-43fe-a4ce-c92cba10b139');
INSERT INTO `s_role_resource` VALUES ('20558310-a5b6-4cec-be5f-ff063f1f11dc', '8a12b2b5-e636-468e-a914-92209d2304e2', 1, '8a12b2b5-e636-468e-a914-92209d2304e2', '3a5e1807-9a29-4488-88a7-4f8d0a701751');
INSERT INTO `s_role_resource` VALUES ('20558310-a5b6-4cec-be5f-ff063f1f11dc', 'd309dad3-8301-433b-a6f0-7feacd7ff2a5', 3, '1d2e030e-0a65-4239-bfae-1dace826e581', 'd4984ec8-de4f-4156-9df7-aea80ab679d8');
INSERT INTO `s_role_resource` VALUES ('20558310-a5b6-4cec-be5f-ff063f1f11dc', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b1', 4, '1d2e030e-0a65-4239-bfae-1dace826e581', '0286a4eb-e02e-442c-9fee-4497be8ad7cf');
INSERT INTO `s_role_resource` VALUES ('20558310-a5b6-4cec-be5f-ff063f1f11dc', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b2', 4, '1d2e030e-0a65-4239-bfae-1dace826e581', 'ce61970d-f4a9-43a3-9dce-b75a3871d652');
INSERT INTO `s_role_resource` VALUES ('20558310-a5b6-4cec-be5f-ff063f1f11dc', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b7', 4, '1d2e030e-0a65-4239-bfae-1dace826e581', 'a9b30bcc-7ed0-4d12-aab4-f75610345df4');
INSERT INTO `s_role_resource` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', '1d2e030e-0a65-4239-bfae-1dace826e581', 1, '1d2e030e-0a65-4239-bfae-1dace826e581', 'cb2cb828-c45c-403d-b027-5f8b2619c0b2');
INSERT INTO `s_role_resource` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', '1d2e030e-0a65-4239-bfae-1dace826e58b', 1, '1d2e030e-0a65-4239-bfae-1dace826e58b', 'cb2cb828-c45c-403d-b027-5f8b2619c0b3');
INSERT INTO `s_role_resource` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', '26f13b0b-625c-471b-a26f-77350445061a', 2, '1d2e030e-0a65-4239-bfae-1dace826e58b', '834eaf42-7953-4f97-aa82-97fa32e25f26');
INSERT INTO `s_role_resource` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', 'a582b5f2-52ef-449d-949d-0f9c04585b2c', 4, '1d2e030e-0a65-4239-bfae-1dace826e58b', '3d6ca1a1-727f-4861-8ca6-99b685cb6432');
INSERT INTO `s_role_resource` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', 'ca662ba9-be0e-4021-bf1d-ffca4ec9f1c2', 3, '1d2e030e-0a65-4239-bfae-1dace826e58b', 'b81873a5-c8d1-4a87-b4d2-0023cc598216');
INSERT INTO `s_role_resource` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', 'ca9cf5cd-9850-426b-bd8f-04df4f0de86a', 4, '1d2e030e-0a65-4239-bfae-1dace826e58b', 'dd273d89-62ec-4079-aac1-56c8b129aa21');
INSERT INTO `s_role_resource` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', 'd309dad3-8301-433b-a6f0-7feacd7ff2a5', 3, '1d2e030e-0a65-4239-bfae-1dace826e581', '6003ad9a-72b8-402f-bf3f-66ab3368819f');
INSERT INTO `s_role_resource` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', 'd9b076b4-b4df-49ed-95c0-f8f789426722', 4, '1d2e030e-0a65-4239-bfae-1dace826e58b', '77c9992e-10d5-4436-b43b-6c1a1635e572');
INSERT INTO `s_role_resource` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b1', 4, '1d2e030e-0a65-4239-bfae-1dace826e581', '5e93016a-b247-49cb-94eb-c57aa3ea170f');
INSERT INTO `s_role_resource` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b2', 4, '1d2e030e-0a65-4239-bfae-1dace826e581', 'fbe2be90-97e1-4dd3-91da-d5ec4606c8af');
INSERT INTO `s_role_resource` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b7', 4, '1d2e030e-0a65-4239-bfae-1dace826e581', 'cb2cb828-c45c-403d-b027-5f8b2619c0b4');
INSERT INTO `s_role_resource` VALUES ('4cb43472-b2e3-4f43-a558-f43df683a685', '1d2e030e-0a65-4239-bfae-1dace826e581', 1, '1d2e030e-0a65-4239-bfae-1dace826e581', '024d3828-01ed-46ff-bb51-d91e8ce678f0');
INSERT INTO `s_role_resource` VALUES ('4cb43472-b2e3-4f43-a558-f43df683a685', 'd309dad3-8301-433b-a6f0-7feacd7ff2a5', 3, '1d2e030e-0a65-4239-bfae-1dace826e581', '390d396a-5067-4805-87c2-f14310add93c');
INSERT INTO `s_role_resource` VALUES ('4cb43472-b2e3-4f43-a558-f43df683a685', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b1', 4, '1d2e030e-0a65-4239-bfae-1dace826e581', '0960f5f6-7478-47be-9838-944fcaa97e2e');
INSERT INTO `s_role_resource` VALUES ('4cb43472-b2e3-4f43-a558-f43df683a685', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b2', 4, '1d2e030e-0a65-4239-bfae-1dace826e581', '63b45aea-e4f6-4735-ac9d-0890aff0c94e');
INSERT INTO `s_role_resource` VALUES ('4cb43472-b2e3-4f43-a558-f43df683a685', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b7', 4, '1d2e030e-0a65-4239-bfae-1dace826e581', '7f22bdbb-04f3-49e2-b4b3-7e03e89d6c3c');
INSERT INTO `s_role_resource` VALUES ('5d10329a-cb2e-4cbc-a105-982ecec2641e', '15924764-f358-41a7-a62c-9d70adf19801', 3, '8a12b2b5-e636-468e-a914-92209d2304e2', '4c49bdb1-0d55-421f-b29e-219a81f0a762');
INSERT INTO `s_role_resource` VALUES ('5d10329a-cb2e-4cbc-a105-982ecec2641e', '26f13b0b-625c-471b-a26f-77350445061a', 2, '8a12b2b5-e636-468e-a914-92209d2304e2', 'c6771dd3-d8c0-49ce-9403-1ae51b7786d8');
INSERT INTO `s_role_resource` VALUES ('5d10329a-cb2e-4cbc-a105-982ecec2641e', '8a12b2b5-e636-468e-a914-92209d2304e2', 1, '8a12b2b5-e636-468e-a914-92209d2304e2', 'a321b36c-4c5a-4611-bcf3-03568a188f75');
INSERT INTO `s_role_resource` VALUES ('5d10329a-cb2e-4cbc-a105-982ecec2641e', 'ab9be669-cf9f-474e-afac-4eed3d742906', 4, '8a12b2b5-e636-468e-a914-92209d2304e2', 'c57992fb-ae86-4d40-ade9-0c8a4203abad');
INSERT INTO `s_role_resource` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', '1d2e030e-0a65-4239-bfae-1dace826e581', 1, '1d2e030e-0a65-4239-bfae-1dace826e581', '74772bc6-2cd6-46cb-b329-637cf4ad6e30');
INSERT INTO `s_role_resource` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', '1d2e030e-0a65-4239-bfae-1dace826e58b', 1, '1d2e030e-0a65-4239-bfae-1dace826e58b', 'a290aaca-38f5-4cf5-8f91-cd3967588f7f');
INSERT INTO `s_role_resource` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', '26f13b0b-625c-471b-a26f-77350445061a', 2, '1d2e030e-0a65-4239-bfae-1dace826e581', '3a397a84-91be-48d9-979b-22c6746bf7cf');
INSERT INTO `s_role_resource` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', '2c80a76b-caa2-4d4e-975e-b90a368bffca', 3, '970fcad9-93c0-4e0f-9107-e6e60865add0', '88d2ba75-afdb-4f77-8217-021c29a1936f');
INSERT INTO `s_role_resource` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', '6db80677-45fc-4e5a-8709-cca126c2b6dc', 4, '970fcad9-93c0-4e0f-9107-e6e60865add0', '565ef5d0-6da8-48fe-94b7-d0007be82dc1');
INSERT INTO `s_role_resource` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', '80d53363-9a50-4f92-b6b1-f90416f6d780', 4, '970fcad9-93c0-4e0f-9107-e6e60865add0', 'd5afbfe4-7596-4528-a161-7e0e62126736');
INSERT INTO `s_role_resource` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', '970fcad9-93c0-4e0f-9107-e6e60865add0', 1, '970fcad9-93c0-4e0f-9107-e6e60865add0', '6295710e-e5e5-4efb-a248-a1fab2342226');
INSERT INTO `s_role_resource` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', 'a582b5f2-52ef-449d-949d-0f9c04585b2c', 4, '1d2e030e-0a65-4239-bfae-1dace826e58b', 'f91959c2-093d-4d21-8af2-3e5dec716b17');
INSERT INTO `s_role_resource` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', 'ca662ba9-be0e-4021-bf1d-ffca4ec9f1c2', 3, '1d2e030e-0a65-4239-bfae-1dace826e58b', 'bbb1b632-051b-44a6-b656-50d970f54d7d');
INSERT INTO `s_role_resource` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', 'ca9cf5cd-9850-426b-bd8f-04df4f0de86a', 4, '1d2e030e-0a65-4239-bfae-1dace826e58b', '0c04f826-812d-4311-ba90-6212279da17d');
INSERT INTO `s_role_resource` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', 'd309dad3-8301-433b-a6f0-7feacd7ff2a5', 3, '1d2e030e-0a65-4239-bfae-1dace826e581', '8c9bc545-5a07-4321-aa9d-158e83ac74c9');
INSERT INTO `s_role_resource` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', 'd9b076b4-b4df-49ed-95c0-f8f789426722', 4, '1d2e030e-0a65-4239-bfae-1dace826e58b', '5157f50c-cee4-4647-a548-2924c747becd');
INSERT INTO `s_role_resource` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b1', 4, '1d2e030e-0a65-4239-bfae-1dace826e581', '8d90b8c6-6890-4d7d-943e-b3c80e370997');
INSERT INTO `s_role_resource` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b2', 4, '1d2e030e-0a65-4239-bfae-1dace826e581', '6521a22d-6ff2-4a0a-b1c3-4d85b4d64005');
INSERT INTO `s_role_resource` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b7', 4, '1d2e030e-0a65-4239-bfae-1dace826e581', '1287eb64-e2aa-46d4-829a-b90ee370b469');
INSERT INTO `s_role_resource` VALUES ('e688ba8b-ef5c-4459-a74e-6f4812411cfe', '15924764-f358-41a7-a62c-9d70adf19801', 3, '8a12b2b5-e636-468e-a914-92209d2304e2', '597eeaac-0613-410a-a790-de3533f960f4');
INSERT INTO `s_role_resource` VALUES ('e688ba8b-ef5c-4459-a74e-6f4812411cfe', '26f13b0b-625c-471b-a26f-77350445061a', 2, '8a12b2b5-e636-468e-a914-92209d2304e2', '568c81aa-72b5-4955-ab1e-34232e2d41b7');
INSERT INTO `s_role_resource` VALUES ('e688ba8b-ef5c-4459-a74e-6f4812411cfe', '8a12b2b5-e636-468e-a914-92209d2304e2', 1, '8a12b2b5-e636-468e-a914-92209d2304e2', '20e3c578-54d6-4161-8e3d-e78c7aa31c7c');
INSERT INTO `s_role_resource` VALUES ('e688ba8b-ef5c-4459-a74e-6f4812411cfe', 'a582b5f2-52ef-449d-949d-0f9c04585b2c', 4, '1d2e030e-0a65-4239-bfae-1dace826e58b', 'd7e98c47-23c4-43d7-a2b3-3abc09022b60');
INSERT INTO `s_role_resource` VALUES ('e688ba8b-ef5c-4459-a74e-6f4812411cfe', 'ab9be669-cf9f-474e-afac-4eed3d742906', 4, '8a12b2b5-e636-468e-a914-92209d2304e2', 'ed5edc5c-2a24-4b29-94c8-ebdc6f9d87ac');
INSERT INTO `s_role_resource` VALUES ('e688ba8b-ef5c-4459-a74e-6f4812411cfe', 'ca662ba9-be0e-4021-bf1d-ffca4ec9f1c2', 3, '1d2e030e-0a65-4239-bfae-1dace826e58b', 'a85b9b7c-286e-4c56-a8d1-d40ea01b9eac');
INSERT INTO `s_role_resource` VALUES ('e688ba8b-ef5c-4459-a74e-6f4812411cfe', 'ca9cf5cd-9850-426b-bd8f-04df4f0de86a', 4, '1d2e030e-0a65-4239-bfae-1dace826e58b', '0455fce2-da77-4c8f-aca3-f729e4b66cd7');
INSERT INTO `s_role_resource` VALUES ('e688ba8b-ef5c-4459-a74e-6f4812411cfe', 'd309dad3-8301-433b-a6f0-7feacd7ff2a5', 3, '1d2e030e-0a65-4239-bfae-1dace826e581', '29c873bd-2be0-4da6-8f73-71f19088c880');
INSERT INTO `s_role_resource` VALUES ('e688ba8b-ef5c-4459-a74e-6f4812411cfe', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b1', 4, '1d2e030e-0a65-4239-bfae-1dace826e581', '4f069c53-52f6-49aa-a485-2f3811cb710d');
INSERT INTO `s_role_resource` VALUES ('e688ba8b-ef5c-4459-a74e-6f4812411cfe', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b2', 4, '1d2e030e-0a65-4239-bfae-1dace826e581', 'b0b9a2ef-77c4-4efb-88e9-2f0269ab0ee5');

-- ----------------------------
-- Table structure for s_tenant
-- ----------------------------
DROP TABLE IF EXISTS `s_tenant`;
CREATE TABLE `s_tenant`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rootOrg` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `Id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_tenant
-- ----------------------------
INSERT INTO `s_tenant` VALUES ('52cc23f0-484f-46dd-8af9-30aa649e15a5', '运营方', '运营方租户，用于租户管理、功能配置管理', '珠海格力');
INSERT INTO `s_tenant` VALUES ('5517ab4b-00b9-44e8-b5c6-137dbce8b6d4', '测试租户2', '测试租户2', '根组织');
INSERT INTO `s_tenant` VALUES ('d9616e54-40e3-475e-9b8f-e34d2a625799', '测试租户', '测试租户', '根组织');

-- ----------------------------
-- Table structure for s_tenant_portal
-- ----------------------------
DROP TABLE IF EXISTS `s_tenant_portal`;
CREATE TABLE `s_tenant_portal`  (
  `tenantId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `portalId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `forkey_1`(`tenantId`) USING BTREE,
  INDEX `forkey_2`(`portalId`) USING BTREE,
  CONSTRAINT `forkey_1` FOREIGN KEY (`tenantId`) REFERENCES `s_tenant` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `forkey_2` FOREIGN KEY (`portalId`) REFERENCES `s_resource` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_tenant_portal
-- ----------------------------
INSERT INTO `s_tenant_portal` VALUES ('52cc23f0-484f-46dd-8af9-30aa649e15a5', '1d2e030e-0a65-4239-bfae-1dace826e58b');
INSERT INTO `s_tenant_portal` VALUES ('52cc23f0-484f-46dd-8af9-30aa649e15a5', '1d2e030e-0a65-4239-bfae-1dace826e581');
INSERT INTO `s_tenant_portal` VALUES ('52cc23f0-484f-46dd-8af9-30aa649e15a5', '970fcad9-93c0-4e0f-9107-e6e60865add0');
INSERT INTO `s_tenant_portal` VALUES ('52cc23f0-484f-46dd-8af9-30aa649e15a5', '8a12b2b5-e636-468e-a914-92209d2304e2');
INSERT INTO `s_tenant_portal` VALUES ('d9616e54-40e3-475e-9b8f-e34d2a625799', '1d2e030e-0a65-4239-bfae-1dace826e581');
INSERT INTO `s_tenant_portal` VALUES ('d9616e54-40e3-475e-9b8f-e34d2a625799', '8a12b2b5-e636-468e-a914-92209d2304e2');
INSERT INTO `s_tenant_portal` VALUES ('5517ab4b-00b9-44e8-b5c6-137dbce8b6d4', '8a12b2b5-e636-468e-a914-92209d2304e2');
INSERT INTO `s_tenant_portal` VALUES ('5517ab4b-00b9-44e8-b5c6-137dbce8b6d4', '1d2e030e-0a65-4239-bfae-1dace826e581');

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `loginName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isDisabled` int(1) NULL DEFAULT 0 COMMENT '可以禁用用户，只有两种状态 ：启用，禁用',
  `remarks` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT 0 COMMENT '用户类型，区分是内部用户还是外部用户，1：内，0：外',
  `avatar` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `tenantId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `Id`(`id`) USING BTREE,
  INDEX `Id_2`(`id`) USING BTREE,
  INDEX `Id_3`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('52cc23f0-484f-46dd-8af9-30aa649e15a5', 'admin1', '$2a$10$c7cKaC9AliKA661jFCQ4Zu1GzBMdfNSY0HXhxXNlgmGB3wMqRq9by', 'admin001', '13333333333', '123@126.com', '8cec04a2-2f5f-41ee-b974-e68b603b32e4', 0, '我是管理员', 1, 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_user` VALUES ('52cc23f0-484f-46dd-8af9-30aa649e15a6', 'developer', '$2a$10$c7cKaC9AliKA661jFCQ4Zu1GzBMdfNSY0HXhxXNlgmGB3wMqRq9by', 'developer', '13333333333', '123@126.com', '8cec04a2-2f5f-41ee-b974-e68b603b32e4', 0, '我是开发工', 1, 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_user` VALUES ('6a0e56c4-3a45-432c-9882-0809f67892bc', 'normal', '$2a$10$jdIwxDxc51OxT4gz/KL1/ei9pXP6AdMy3nd5junCf4fcEQ82zFp5C', '普通用户', '13800000000', '13800000000@qq.com', '8cec04a2-2f5f-41ee-b974-e68b603b32e4', 0, NULL, 0, NULL, '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_user` VALUES ('75c785b1-a312-4c8d-92e3-8bd000fa366f', 'admin', '$2a$10$SSrAP5JByC02jjgCPso9FejNAv0W8rebIwbEFGykayJDdeeKRGa9a', '测试租户 --- 管理员', '13800000000', '13800000000@qq.com', '08d0483a-bee1-4760-aab5-189869eb6112', 0, '租户：测试租户 --- 管理员', 1, 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png0', 'd9616e54-40e3-475e-9b8f-e34d2a625799');
INSERT INTO `s_user` VALUES ('c5aeb9ba-0872-40c7-96de-16567dcc4e92', 'admin', '$2a$10$L3cSMbttiEaCf8YZykFv0.ZOUBHxQGhipgxGbhAxuyC0rNprhKi36', '测试租户2 --- 管理员', '13800000000', '13800000000@qq.com', '3631caf8-5fde-4bc3-8905-43a5f3100bdd', 0, '租户：测试租户2 --- 管理员', 1, 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png0', '5517ab4b-00b9-44e8-b5c6-137dbce8b6d4');
INSERT INTO `s_user` VALUES ('e69dce41-e9de-4be5-95a0-b9bf11b088d4', 'testUser', '$2a$10$.52y4iN3KW81vp/HL.mw4.nIcSWd6EuF9wN/Wr5pCMgF3ELUunmSC', '测试租户 --- 普通用户', '13800000000', '13800000000@qq.com', '08d0483a-bee1-4760-aab5-189869eb6112', 0, NULL, 0, NULL, 'd9616e54-40e3-475e-9b8f-e34d2a625799');

-- ----------------------------
-- Table structure for s_user_org
-- ----------------------------
DROP TABLE IF EXISTS `s_user_org`;
CREATE TABLE `s_user_org`  (
  `orgId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`orgId`, `userId`) USING BTREE,
  INDEX `FK_Reference_1`(`userId`) USING BTREE,
  CONSTRAINT `s_user_org_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `s_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `s_user_org_ibfk_2` FOREIGN KEY (`orgId`) REFERENCES `s_org` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_user_org
-- ----------------------------

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role`  (
  `roleId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`roleId`, `userId`) USING BTREE,
  INDEX `FK_Reference_1`(`userId`) USING BTREE,
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`userId`) REFERENCES `s_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`roleId`) REFERENCES `s_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_user_role` VALUES ('79a4bead-213e-469c-8521-4926097be0b7', '52cc23f0-484f-46dd-8af9-30aa649e15a6');
INSERT INTO `s_user_role` VALUES ('e688ba8b-ef5c-4459-a74e-6f4812411cfe', '6a0e56c4-3a45-432c-9882-0809f67892bc');
INSERT INTO `s_user_role` VALUES ('20558310-a5b6-4cec-be5f-ff063f1f11dc', '75c785b1-a312-4c8d-92e3-8bd000fa366f');
INSERT INTO `s_user_role` VALUES ('5d10329a-cb2e-4cbc-a105-982ecec2641e', '75c785b1-a312-4c8d-92e3-8bd000fa366f');
INSERT INTO `s_user_role` VALUES ('4cb43472-b2e3-4f43-a558-f43df683a685', 'c5aeb9ba-0872-40c7-96de-16567dcc4e92');
INSERT INTO `s_user_role` VALUES ('5d10329a-cb2e-4cbc-a105-982ecec2641e', 'e69dce41-e9de-4be5-95a0-b9bf11b088d4');

-- ----------------------------
-- Table structure for s_user_setting
-- ----------------------------
DROP TABLE IF EXISTS `s_user_setting`;
CREATE TABLE `s_user_setting`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `theme` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '保存json格式',
  `currentPortal` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前门户',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`id`) REFERENCES `s_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_user_setting
-- ----------------------------
INSERT INTO `s_user_setting` VALUES ('52cc23f0-484f-46dd-8af9-30aa649e15a5', '{\"navTheme\":\"dark\",\"primaryColor\":\"#52C41A\",\"layout\":\"sidemenu\",\"contentWidth\":\"Fluid\",\"fixedHeader\":true,\"autoHideHeader\":false,\"fixSiderbar\":false,\"colorWeak\":false}', '1d2e030e-0a65-4239-bfae-1dace826e58b');
INSERT INTO `s_user_setting` VALUES ('52cc23f0-484f-46dd-8af9-30aa649e15a6', '{\"navTheme\":\"dark\",\"primaryColor\":\"#52C41A\",\"layout\":\"sidemenu\",\"contentWidth\":\"Fluid\",\"fixedHeader\":true,\"autoHideHeader\":false,\"fixSiderbar\":false,\"colorWeak\":false}', '1d2e030e-0a65-4239-bfae-1dace826e58b');
INSERT INTO `s_user_setting` VALUES ('75c785b1-a312-4c8d-92e3-8bd000fa366f', '{\"navTheme\":\"dark\",\"primaryColor\":\"#52C41A\",\"layout\":\"sidemenu\",\"contentWidth\":\"Fluid\",\"fixedHeader\":true,\"autoHideHeader\":false,\"fixSiderbar\":false,\"colorWeak\":false}', '8a12b2b5-e636-468e-a914-92209d2304e2');

SET FOREIGN_KEY_CHECKS = 1;
