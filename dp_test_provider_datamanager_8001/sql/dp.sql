/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : dp

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 04/03/2021 21:58:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for distance_dp
-- ----------------------------
DROP TABLE IF EXISTS `distance_dp`;
CREATE TABLE `distance_dp`  (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `loc_1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '位置1',
  `loc_2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '位置2',
  `distance` double(100, 0) NULL DEFAULT NULL COMMENT '距离',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of distance_dp
-- ----------------------------
INSERT INTO `distance_dp` VALUES ('01', 'shanghai', 'A', 2000);
INSERT INTO `distance_dp` VALUES ('02', 'shanghai', 'B', 1000);

-- ----------------------------
-- Table structure for manager_dp
-- ----------------------------
DROP TABLE IF EXISTS `manager_dp`;
CREATE TABLE `manager_dp`  (
  `id` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '门店管理员id，登录id',
  `shop_id` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理的门店id',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '门店管理员名字',
  `code` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆密码',
  `power` int(2) NULL DEFAULT NULL COMMENT '权限，分为3级（1，2，3）最高3级',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `shop_id`(`shop_id`) USING BTREE,
  CONSTRAINT `manager_dp_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shop_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager_dp
-- ----------------------------
INSERT INTO `manager_dp` VALUES ('123456', '0001', 'jack', '123456', 1);
INSERT INTO `manager_dp` VALUES ('666666', '0001', 'mike', '123456', 2);
INSERT INTO `manager_dp` VALUES ('999999', '0001', 'jay', '123456', 3);

-- ----------------------------
-- Table structure for materiallist_dp
-- ----------------------------
DROP TABLE IF EXISTS `materiallist_dp`;
CREATE TABLE `materiallist_dp`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原料名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of materiallist_dp
-- ----------------------------
INSERT INTO `materiallist_dp` VALUES ('0006', '奶油');
INSERT INTO `materiallist_dp` VALUES ('0003', '珍珠');
INSERT INTO `materiallist_dp` VALUES ('0004', '苹果');
INSERT INTO `materiallist_dp` VALUES ('0002', '草莓');
INSERT INTO `materiallist_dp` VALUES ('0005', '面粉');
INSERT INTO `materiallist_dp` VALUES ('0001', '鸡蛋');

-- ----------------------------
-- Table structure for predict_product_dp
-- ----------------------------
DROP TABLE IF EXISTS `predict_product_dp`;
CREATE TABLE `predict_product_dp`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺id',
  `forecast_date` datetime(6) NULL DEFAULT NULL COMMENT '日期',
  `product_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被预测的成品id',
  `forecast_result` double(10, 0) NULL DEFAULT NULL COMMENT '预测值',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  INDEX `shop_id`(`shop_id`) USING BTREE,
  CONSTRAINT `predict_product_dp_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `productlist_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `predict_product_dp_ibfk_3` FOREIGN KEY (`shop_id`) REFERENCES `shop_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of predict_product_dp
-- ----------------------------
INSERT INTO `predict_product_dp` VALUES ('001', '0001', '2021-03-01 21:26:13.000000', '0001', 11);

-- ----------------------------
-- Table structure for predict_return_dp
-- ----------------------------
DROP TABLE IF EXISTS `predict_return_dp`;
CREATE TABLE `predict_return_dp`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '门店id',
  `product_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品id',
  `date` date NULL DEFAULT NULL COMMENT '预测的日期',
  `return_num` int(10) NULL DEFAULT NULL COMMENT '预测的退货量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `shop_id`(`shop_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `predict_return_dp_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shop_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `predict_return_dp_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `productlist_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of predict_return_dp
-- ----------------------------
INSERT INTO `predict_return_dp` VALUES ('001', '0001', '0002', '2021-03-01', 9);

-- ----------------------------
-- Table structure for predict_totalsales_dp
-- ----------------------------
DROP TABLE IF EXISTS `predict_totalsales_dp`;
CREATE TABLE `predict_totalsales_dp`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '门店id',
  `date` date NULL DEFAULT NULL COMMENT '预测的日期',
  `result` double(10, 2) NULL DEFAULT NULL COMMENT '预测的结果',
  `actual` double(10, 2) NULL DEFAULT NULL COMMENT '实际门店销售额',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `shop_id`(`shop_id`) USING BTREE,
  CONSTRAINT `predict_totalsales_dp_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shop_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of predict_totalsales_dp
-- ----------------------------
INSERT INTO `predict_totalsales_dp` VALUES ('001', '0001', '2021-03-01', 18.00, 11.00);

-- ----------------------------
-- Table structure for product_material_relation_dp
-- ----------------------------
DROP TABLE IF EXISTS `product_material_relation_dp`;
CREATE TABLE `product_material_relation_dp`  (
  `product_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '成品id',
  `material_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '原料id',
  PRIMARY KEY (`product_id`, `material_id`) USING BTREE,
  INDEX `material_id`(`material_id`) USING BTREE,
  CONSTRAINT `product_material_relation_dp_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `productlist_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_material_relation_dp_ibfk_2` FOREIGN KEY (`material_id`) REFERENCES `materiallist_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_material_relation_dp
-- ----------------------------
INSERT INTO `product_material_relation_dp` VALUES ('0001', '0001');
INSERT INTO `product_material_relation_dp` VALUES ('0001', '0002');
INSERT INTO `product_material_relation_dp` VALUES ('0002', '0003');
INSERT INTO `product_material_relation_dp` VALUES ('0003', '0005');
INSERT INTO `product_material_relation_dp` VALUES ('0001', '0006');
INSERT INTO `product_material_relation_dp` VALUES ('0003', '0006');

-- ----------------------------
-- Table structure for productlist_dp
-- ----------------------------
DROP TABLE IF EXISTS `productlist_dp`;
CREATE TABLE `productlist_dp`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成品名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productlist_dp
-- ----------------------------
INSERT INTO `productlist_dp` VALUES ('0003', '奶油面包');
INSERT INTO `productlist_dp` VALUES ('0002', '珍珠奶茶');
INSERT INTO `productlist_dp` VALUES ('0001', '粗咩蛋糕');

-- ----------------------------
-- Table structure for recommand_relation_dp
-- ----------------------------
DROP TABLE IF EXISTS `recommand_relation_dp`;
CREATE TABLE `recommand_relation_dp`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `date` date NULL DEFAULT NULL COMMENT '日期',
  `product_id1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成品id1',
  `product_id2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '恒频id2',
  `relation_pra` double(10, 0) NULL DEFAULT NULL COMMENT '关联度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recommand_relation_dp
-- ----------------------------
INSERT INTO `recommand_relation_dp` VALUES ('0001', '2021-03-01', '0001', '0002', 9);

-- ----------------------------
-- Table structure for recommand_result_dp
-- ----------------------------
DROP TABLE IF EXISTS `recommand_result_dp`;
CREATE TABLE `recommand_result_dp`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺id',
  `recommand_date` datetime(6) NULL DEFAULT NULL COMMENT '推荐的日期',
  `product_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成品id',
  `weight` double(10, 0) NULL DEFAULT NULL COMMENT '权值',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推荐理由',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `client_id`(`shop_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `recommand_result_dp_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `productlist_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recommand_result_dp
-- ----------------------------
INSERT INTO `recommand_result_dp` VALUES ('0001', '0001', '2021-03-01 21:30:36.000000', '0001', 4, '很像');

-- ----------------------------
-- Table structure for return_application_dp
-- ----------------------------
DROP TABLE IF EXISTS `return_application_dp`;
CREATE TABLE `return_application_dp`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '门店id',
  `sales_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '销售记录id',
  `return_date` datetime(6) NULL DEFAULT NULL COMMENT '退货申请提交日期',
  `product_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成品id',
  `num` int(10) NULL DEFAULT NULL COMMENT '退货数量，即原先该单卖出的数量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sales_id`(`sales_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  INDEX `shop_id`(`shop_id`) USING BTREE,
  CONSTRAINT `return_application_dp_ibfk_1` FOREIGN KEY (`sales_id`) REFERENCES `sales_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `return_application_dp_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `productlist_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `return_application_dp_ibfk_3` FOREIGN KEY (`shop_id`) REFERENCES `shop_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sales_dp
-- ----------------------------
DROP TABLE IF EXISTS `sales_dp`;
CREATE TABLE `sales_dp`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '门店id',
  `product_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成品id',
  `sales_date` datetime(6) NULL DEFAULT NULL COMMENT '销售日期',
  `num` int(10) NULL DEFAULT NULL COMMENT '数量',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `sales_sum` double(10, 0) NULL DEFAULT NULL COMMENT '销售额',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  INDEX `shop_id`(`shop_id`) USING BTREE,
  CONSTRAINT `sales_dp_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `productlist_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sales_dp_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `shop_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sales_dp
-- ----------------------------
INSERT INTO `sales_dp` VALUES ('0001', '0001', '0001', '2021-03-01 21:32:21.000000', 11, 15.00, 115);

-- ----------------------------
-- Table structure for shop_dp
-- ----------------------------
DROP TABLE IF EXISTS `shop_dp`;
CREATE TABLE `shop_dp`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `shop_location` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺位置',
  `shop_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺类型（服装0/电子1/餐饮2）',
  `shop_synopsis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `found_date` date NULL DEFAULT NULL COMMENT '建立日期',
  `shop_director` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老板名字',
  `shop_capital` int(10) NULL DEFAULT NULL COMMENT '建立资金',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_dp
-- ----------------------------
INSERT INTO `shop_dp` VALUES ('00001', 'addidas', 'shanghai', '1', '123456', '2021-02-01', '贾先生', 10000);
INSERT INTO `shop_dp` VALUES ('0001', 'nike', 'shanghai', '1', '上海的nike店', '2021-03-01', '金先生', 800000000);

-- ----------------------------
-- Table structure for supplier_dp
-- ----------------------------
DROP TABLE IF EXISTS `supplier_dp`;
CREATE TABLE `supplier_dp`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商位置',
  `star` int(100) NULL DEFAULT NULL COMMENT '合作分值',
  `time_parameter` double(10, 0) NULL DEFAULT NULL COMMENT '运输时间系数',
  `fare_parameter` double(10, 0) NULL DEFAULT NULL COMMENT '运输费用系数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier_dp
-- ----------------------------
INSERT INTO `supplier_dp` VALUES ('0001', '农场', 'shanghai', 9, 2, 0);

-- ----------------------------
-- Table structure for supplier_material_relation_dp
-- ----------------------------
DROP TABLE IF EXISTS `supplier_material_relation_dp`;
CREATE TABLE `supplier_material_relation_dp`  (
  `supplier_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '供应商id',
  `material_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原料id',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '报价',
  PRIMARY KEY (`supplier_id`) USING BTREE,
  INDEX `material_id`(`material_id`) USING BTREE,
  CONSTRAINT `supplier_material_relation_dp_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `supplier_material_relation_dp_ibfk_2` FOREIGN KEY (`material_id`) REFERENCES `materiallist_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier_material_relation_dp
-- ----------------------------
INSERT INTO `supplier_material_relation_dp` VALUES ('0001', '0006', 1.50);

-- ----------------------------
-- Table structure for warehouse_material_dp
-- ----------------------------
DROP TABLE IF EXISTS `warehouse_material_dp`;
CREATE TABLE `warehouse_material_dp`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '门店id',
  `material_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原料id',
  `material_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原料名称',
  `quantity` int(10) NULL DEFAULT NULL COMMENT '数量',
  `in_date` datetime(6) NULL DEFAULT NULL COMMENT '进货日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `material_name`(`material_name`) USING BTREE,
  INDEX `shop_id`(`shop_id`) USING BTREE,
  INDEX `material_id`(`material_id`) USING BTREE,
  CONSTRAINT `warehouse_material_dp_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `shop_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `warehouse_material_dp_ibfk_3` FOREIGN KEY (`material_id`) REFERENCES `materiallist_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `warehouse_material_dp_ibfk_4` FOREIGN KEY (`material_name`) REFERENCES `materiallist_dp` (`name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warehouse_material_dp
-- ----------------------------
INSERT INTO `warehouse_material_dp` VALUES ('0001', '0001', '0003', '草莓', 600, '2021-03-01 21:34:16.000000');

-- ----------------------------
-- Table structure for warehouse_product_dp
-- ----------------------------
DROP TABLE IF EXISTS `warehouse_product_dp`;
CREATE TABLE `warehouse_product_dp`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '门店id',
  `product_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成品id',
  `product_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成品名称',
  `quantity` int(10) NULL DEFAULT NULL COMMENT '数量',
  `in_date` datetime(6) NULL DEFAULT NULL COMMENT '进货日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_name`(`product_name`) USING BTREE,
  INDEX `shop_id`(`shop_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `warehouse_product_dp_ibfk_1` FOREIGN KEY (`product_name`) REFERENCES `productlist_dp` (`name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `warehouse_product_dp_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `shop_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `warehouse_product_dp_ibfk_3` FOREIGN KEY (`product_id`) REFERENCES `productlist_dp` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warehouse_product_dp
-- ----------------------------
INSERT INTO `warehouse_product_dp` VALUES ('0001', '0001', '0001', '奶油面包', 300, '2021-03-01 21:34:56.000000');

SET FOREIGN_KEY_CHECKS = 1;