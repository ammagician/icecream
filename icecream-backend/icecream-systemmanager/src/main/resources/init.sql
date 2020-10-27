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



-- ----------------------------
-- Table structure for s_portal
-- ----------------------------
DROP TABLE IF EXISTS `s_portal`;
CREATE TABLE `s_portal`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名',
  `code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `remarks` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_portal
-- ----------------------------
INSERT INTO `s_portal` VALUES ('1d2e030e-0a65-4239-bfae-1dace826e581', '管理门户', 'portal.sys', 'remarks');
INSERT INTO `s_portal` VALUES ('1d2e030e-0a65-4239-bfae-1dace826e58b', '运营门户', 'portal.operation', 'remarks');


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
  CONSTRAINT `forkey_2` FOREIGN KEY (`portalId`) REFERENCES `s_portal` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_tenant_portal
-- ----------------------------
INSERT INTO `s_tenant_portal` VALUES ('52cc23f0-484f-46dd-8af9-30aa649e15a5', '1d2e030e-0a65-4239-bfae-1dace826e58b');
INSERT INTO `s_tenant_portal` VALUES ('52cc23f0-484f-46dd-8af9-30aa649e15a5', '1d2e030e-0a65-4239-bfae-1dace826e581');


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
INSERT INTO `s_org` VALUES ('8cec04a2-2f5f-41ee-b974-e68b603b32e4', '', '珠海格力', 'ZHGREE', 1, 1, '根目录', '52cc23f0-484f-46dd-8af9-30aa649e15a5');


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
  `portalId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
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
INSERT INTO `s_user` VALUES ('52cc23f0-484f-46dd-8af9-30aa649e15a5', 'admin1', '$2a$10$c7cKaC9AliKA661jFCQ4Zu1GzBMdfNSY0HXhxXNlgmGB3wMqRq9by', 'admin001', '13333333333', '123@126.com', '8cec04a2-2f5f-41ee-b974-e68b603b32e3', 0, '我是管理员', 1, '', 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_user` VALUES ('52cc23f0-484f-46dd-8af9-30aa649e15a6', 'developer', '$2a$10$c7cKaC9AliKA661jFCQ4Zu1GzBMdfNSY0HXhxXNlgmGB3wMqRq9by', 'developer', '13333333333', '123@126.com', '8cec04a2-2f5f-41ee-b974-e68b603b32e3', 0, '我是开发工', 1, '', 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png', '52cc23f0-484f-46dd-8af9-30aa649e15a5');

-- ----------------------------
-- Table structure for s_user_org
-- ----------------------------
DROP TABLE IF EXISTS `s_user_org`;
CREATE TABLE `s_user_org`  (
  `orgId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`orgId`, `userId`) USING BTREE,
  INDEX `FK_Reference_6`(`userId`) USING BTREE,
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`orgId`) REFERENCES `s_org` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`userId`) REFERENCES `s_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_user_org
-- ----------------------------
INSERT INTO `s_user_org` VALUES ('8cec04a2-2f5f-41ee-b974-e68b603b32e4', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_user_org` VALUES ('8cec04a2-2f5f-41ee-b974-e68b603b32e4', '52cc23f0-484f-46dd-8af9-30aa649e15a6');


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
INSERT INTO `s_role` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', 'admin', 'admin', '超级管理员', '52cc23f0-484f-46dd-8af9-30aa649e15a5');
INSERT INTO `s_role` VALUES ('7bd29a0f-8ee2-49af-b1dc-30c77d15bc01', 'role.developer', '开发者', '开发者', '52cc23f0-484f-46dd-8af9-30aa649e15a5');



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
INSERT INTO `s_user_role` VALUES ('7bd29a0f-8ee2-49af-b1dc-30c77d15bc01', '52cc23f0-484f-46dd-8af9-30aa649e15a6');




-- ----------------------------
-- Table structure for s_resource
-- ----------------------------
DROP TABLE IF EXISTS `s_resource`;
CREATE TABLE `s_resource`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parentId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `portalId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名',
  `code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL COMMENT '1 门户，2: portlet，3：目录 4：菜单 5：页面  6：按钮                      ',
  `remarks` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderNo` int(11) NULL DEFAULT NULL,
  `relationId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_resource
-- ----------------------------
INSERT INTO `s_resource` VALUES ('02c1f046-0ede-4dee-92b0-ae1a1e21a8d4', NULL, '1d2e030e-0a65-4239-bfae-1dace826e58b', '角色管理页面', 'page.sys.role', '/sys/role', 5, '角色管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('196fea9a-5f43-4e80-ac43-0a7c8a3d09ee', NULL, '1d2e030e-0a65-4239-bfae-1dace826e58b', 'Widget管理页面', 'page.sys.portlet', '/sys/portlet', 5, 'Portlet管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('fb59c10d-b3a9-4b3b-9646-65da7a1669ad', NULL, '1d2e030e-0a65-4239-bfae-1dace826e58b', '菜单管理页面', 'page.sys.menu', '/sys/menu', 5, '菜单管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('dbe4decc-858f-4900-ba56-e3758e96c80d', NULL, '1d2e030e-0a65-4239-bfae-1dace826e58b', '门户管理页面', 'page.sys.portal', '/sys/portal', 5, '门户管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('e30257b1-3692-471c-b0f2-f40994790e71', NULL, '1d2e030e-0a65-4239-bfae-1dace826e58b', '页面管理页面', 'page.sys.page', '/sys/page', 5, '页面管理页面', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('8cb252b0-0ee9-459c-b228-698ece089cd6', NULL, '1d2e030e-0a65-4239-bfae-1dace826e58b', '租户管理页面', 'page.sys.tenant', '/sys/tenant', 5, '租户管理页面0', NULL, NULL, NULL);
INSERT INTO `s_resource` VALUES ('946d7e82-edc7-4b4d-bd0b-c1e64ea691f9', NULL, '1d2e030e-0a65-4239-bfae-1dace826e58b', '组织管理页面', 'page.sys.org', '/sys/org', 5, '组织管理页面', NULL, NULL, '');
INSERT INTO `s_resource` VALUES ('4b60e8fb-335c-4509-860d-605281f9a43f', NULL, '1d2e030e-0a65-4239-bfae-1dace826e58b', '用户管理页面', 'page.sys.user', '/sys/user', 5, '用户管理页面', NULL, NULL, '');


INSERT INTO `s_resource` VALUES ('1d2e030e-0a65-4239-bfae-1dace826e58b', '', '1d2e030e-0a65-4239-bfae-1dace826e58b', '管理门户', 'portal.sys', '/sys', 1, 'remarks', NULL, NULL, '');
INSERT INTO `s_resource` VALUES ('1d2e030e-0a65-4239-bfae-1dace826e581', '', '1d2e030e-0a65-4239-bfae-1dace826e581', '运营门户', 'portal.operation', '/sys', 1, 'remarks', NULL, NULL, '');

INSERT INTO `s_resource` VALUES ('d309dad3-8301-433b-a6f0-7feacd7ff2a5', '', '1d2e030e-0a65-4239-bfae-1dace826e58b', '管理门户根目录', 'menu.sys', '', 3, NULL, 'system', 1, '');
INSERT INTO `s_resource` VALUES ('ef6ed001-c7c8-45d7-bb97-66f8250d09b1', 'd309dad3-8301-433b-a6f0-7feacd7ff2a5', '1d2e030e-0a65-4239-bfae-1dace826e58b', '组织管理', 'menu.sys.org', '/sys/org', 4, NULL, 'tree', 1, '946d7e82-edc7-4b4d-bd0b-c1e64ea691f9');
INSERT INTO `s_resource` VALUES ('ef6ed001-c7c8-45d7-bb97-66f8250d09b2', 'd309dad3-8301-433b-a6f0-7feacd7ff2a5', '1d2e030e-0a65-4239-bfae-1dace826e58b', '用户管理', 'menu.sys.user', '/sys/user', 4, NULL, 'user', 2, '4b60e8fb-335c-4509-860d-605281f9a43f');
INSERT INTO `s_resource` VALUES ('ef6ed001-c7c8-45d7-bb97-66f8250d09b7', 'd309dad3-8301-433b-a6f0-7feacd7ff2a5', '1d2e030e-0a65-4239-bfae-1dace826e58b', '角色管理', 'menu.sys.role', '/sys/role', 4, NULL, 'user', 6, '02c1f046-0ede-4dee-92b0-ae1a1e21a8d4');

INSERT INTO `s_resource` VALUES ('ca662ba9-be0e-4021-bf1d-ffca4ec9f1c2', '', '1d2e030e-0a65-4239-bfae-1dace826e581', '运营门户根目录', 'menu.operation', '', 3, '运营门户根目录', NULL, 3, NULL);
INSERT INTO `s_resource` VALUES ('4154e624-e3f0-43aa-a37d-756cd3f4af11', 'ca662ba9-be0e-4021-bf1d-ffca4ec9f1c2', '1d2e030e-0a65-4239-bfae-1dace826e581', 'widget管理', 'menu.operation.widget', '/sys/portlet', 4, 'widget管理页面', NULL, 4, '196fea9a-5f43-4e80-ac43-0a7c8a3d09ee');
INSERT INTO `s_resource` VALUES ('84644e07-25c9-431b-9ef5-181af3ae4917', 'ca662ba9-be0e-4021-bf1d-ffca4ec9f1c2', '1d2e030e-0a65-4239-bfae-1dace826e581', '页面管理', 'menu.operation.page', '/sys/page', 4, '页面管理', NULL, 5, 'e30257b1-3692-471c-b0f2-f40994790e71');
INSERT INTO `s_resource` VALUES ('a582b5f2-52ef-449d-949d-0f9c04585b2c', 'ca662ba9-be0e-4021-bf1d-ffca4ec9f1c2', '1d2e030e-0a65-4239-bfae-1dace826e581', '租户管理', 'menu.operation.tenant', '/sys/tenant', 4, '租户管理', NULL, 2, '8cb252b0-0ee9-459c-b228-698ece089cd6');
INSERT INTO `s_resource` VALUES ('ca9cf5cd-9850-426b-bd8f-04df4f0de86a', 'ca662ba9-be0e-4021-bf1d-ffca4ec9f1c2', '1d2e030e-0a65-4239-bfae-1dace826e581', '菜单管理', 'menu.operation.menu', '/sys/menu', 4, '菜单管理页面', NULL, 6, 'fb59c10d-b3a9-4b3b-9646-65da7a1669ad');
INSERT INTO `s_resource` VALUES ('d9b076b4-b4df-49ed-95c0-f8f789426722', 'ca662ba9-be0e-4021-bf1d-ffca4ec9f1c2', '1d2e030e-0a65-4239-bfae-1dace826e581', '门户管理', 'menu.operation.portal', '/sys/portal', 4, '门户管理页面', NULL, 3, 'dbe4decc-858f-4900-ba56-e3758e96c80d');


-- ----------------------------
-- Table structure for s_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `s_role_resources`;
CREATE TABLE `s_role_resources`  (
  `roleId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int(1) NULL DEFAULT 0 COMMENT '1:门户 2：portlet 3: 目录 4：菜单 5: 页面 6: 按钮',
  `portalId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`roleId`, `resId`, `id`) USING BTREE,
  INDEX `FK_Reference_4`(`resId`) USING BTREE,
  INDEX `FK_Portal`(`portalId`) USING BTREE,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`roleId`) REFERENCES `s_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`resId`) REFERENCES `s_resource` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_role_resources
-- ----------------------------
INSERT INTO `s_role_resources` VALUES ('08ec1689-853f-462b-9697-5839dd9bba83', '1d2e030e-0a65-4239-bfae-1dace826e58b', 1, '1d2e030e-0a65-4239-bfae-1dace826e58b', '17acd436-05a9-48c6-afce-3b201e29aed4');
INSERT INTO `s_role_resources` VALUES ('08ec1689-853f-462b-9697-5839dd9bba83', '66c6a680-17d0-4229-a0f7-26e28ee0015b', 1, '66c6a680-17d0-4229-a0f7-26e28ee0015b', 'ddf6a07e-3cf2-4f21-900b-f5a0c3422473');
INSERT INTO `s_role_resources` VALUES ('08ec1689-853f-462b-9697-5839dd9bba83', '7cedc634-ea9a-45f4-b229-7d734696f84b', 2, '1d2e030e-0a65-4239-bfae-1dace826e58b', '47282102-116a-46d7-9c92-38c68961eb53');
INSERT INTO `s_role_resources` VALUES ('08ec1689-853f-462b-9697-5839dd9bba83', 'bcc1b085-7630-4245-ae64-7bbb6183b4cd', 2, '66c6a680-17d0-4229-a0f7-26e28ee0015b', '9a965e7f-9485-428b-b7d6-9a34f932d598');
INSERT INTO `s_role_resources` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', '1d2e030e-0a65-4239-bfae-1dace826e58b', 1, '1d2e030e-0a65-4239-bfae-1dace826e58b', '2ba63101-a3ab-4fb0-91e8-ac4af684a3a2');
INSERT INTO `s_role_resources` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', '5cd0ddea-a12d-401d-8550-0cbad0151c8c', 6, '1d2e030e-0a65-4239-bfae-1dace826e58b', '0938af4c-e2e2-4e9f-8ee8-a8972d5a76bc');
INSERT INTO `s_role_resources` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', '7cedc634-ea9a-45f4-b229-7d734696f84b', 2, '1d2e030e-0a65-4239-bfae-1dace826e58b', 'f993e6e1-9895-492b-bf33-b47577a4a9b8');
INSERT INTO `s_role_resources` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', 'd309dad3-8301-433b-a6f0-7feacd7ff2a5', 3, '1d2e030e-0a65-4239-bfae-1dace826e58b', '6003ad9a-72b8-402f-bf3f-66ab3368819f');
INSERT INTO `s_role_resources` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b1', 4, '1d2e030e-0a65-4239-bfae-1dace826e58b', '5e93016a-b247-49cb-94eb-c57aa3ea170f');
INSERT INTO `s_role_resources` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b2', 4, '1d2e030e-0a65-4239-bfae-1dace826e58b', 'fbe2be90-97e1-4dd3-91da-d5ec4606c8af');
INSERT INTO `s_role_resources` VALUES ('2ba63101-a3ab-4fb0-91e8-ac4af684a3a3', 'ef6ed001-c7c8-45d7-bb97-66f8250d09b7', 4, '1d2e030e-0a65-4239-bfae-1dace826e58b', 'cb2cb828-c45c-403d-b027-5f8b2619c0b4');
INSERT INTO `s_role_resources` VALUES ('7bd29a0f-8ee2-49af-b1dc-30c77d15bc01', '1d2e030e-0a65-4239-bfae-1dace826e58b', 1, '1d2e030e-0a65-4239-bfae-1dace826e58b', '77f017ad-5707-41bb-96e0-29dc648115ad');

