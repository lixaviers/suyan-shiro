
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `parent_id` int(11) unsigned NOT NULL COMMENT '父类id',
  `name` varchar(64) DEFAULT '' COMMENT '名称',
  `order_num` int(11) unsigned NOT NULL COMMENT '排序',
  `address_level` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '层级',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3409 DEFAULT CHARSET=utf8 COMMENT='地址表';

-- ----------------------------
-- Table structure for tb_brand
-- ----------------------------
DROP TABLE IF EXISTS `tb_brand`;
CREATE TABLE `tb_brand` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `order_num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0.未删除，1.已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='品牌表';


-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` bigint(20) unsigned DEFAULT '0' COMMENT '父类id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `order_num` int(11) unsigned DEFAULT '0' COMMENT '排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0.未删除，1.已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 COMMENT='类目表';


-- ----------------------------
-- Table structure for tb_gift_card
-- ----------------------------
DROP TABLE IF EXISTS `tb_gift_card`;
CREATE TABLE `tb_gift_card` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户iid',
  `price` decimal(12,2) DEFAULT NULL COMMENT '价格',
  `use_order_no` varchar(32) DEFAULT NULL COMMENT '使用订单号',
  `end_time` datetime DEFAULT NULL COMMENT '失效时间',
  `common_status` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0.未删除，1.已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='礼品卡表';


-- ----------------------------
-- Table structure for tb_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `category_id` bigint(20) unsigned DEFAULT NULL COMMENT '分类id',
  `brand_id` bigint(20) unsigned DEFAULT NULL COMMENT '品牌id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `price` decimal(12,2) DEFAULT NULL COMMENT '列表价',
  `inventory` int(11) DEFAULT NULL COMMENT '库存',
  `remaining_inventory` int(11) DEFAULT NULL COMMENT '剩余库存',
  `picture_url` varchar(255) DEFAULT '' COMMENT '图片地址',
  `common_status` tinyint(4) unsigned DEFAULT NULL COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0.未删除，1.已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='商品表';


-- ----------------------------
-- Table structure for tb_goods_description
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods_description`;
CREATE TABLE `tb_goods_description` (
  `goods_id` bigint(20) unsigned NOT NULL COMMENT '商品id',
  `description` text COMMENT '描述',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品描述表';


-- ----------------------------
-- Table structure for tb_letter
-- ----------------------------
DROP TABLE IF EXISTS `tb_letter`;
CREATE TABLE `tb_letter` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `type` tinyint(4) unsigned DEFAULT NULL COMMENT '类型',
  `content` varchar(2000) DEFAULT NULL COMMENT '内容',
  `common_status` tinyint(4) unsigned DEFAULT NULL COMMENT '状态',
  `send_result` varchar(500) DEFAULT NULL COMMENT '发送结果',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0.否 1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站内信表';


-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` bigint(20) unsigned DEFAULT '0' COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` tinyint(4) unsigned DEFAULT NULL COMMENT '类型',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL COMMENT '是否删除 0.否 1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `order_no` varchar(32) NOT NULL COMMENT '订单号',
  `order_status` tinyint(4) unsigned NOT NULL COMMENT '订单状态',
  `consignee` varchar(50) DEFAULT NULL COMMENT '收货人名称',
  `mobile` varchar(32) DEFAULT NULL COMMENT '收货人手机号',
  `address` varchar(255) DEFAULT NULL COMMENT '收货具体地址',
  `total_price` decimal(12,2) DEFAULT '0.00' COMMENT '总金额',
  `real_pay_price` decimal(12,2) DEFAULT '0.00' COMMENT '实付金额',
  `promotion_price` decimal(12,2) DEFAULT NULL COMMENT '促销金额',
  `freight_price` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '运费金额',
  `gift_card_price` decimal(12,2) DEFAULT NULL COMMENT '礼品卡金额',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `message` varchar(512) DEFAULT NULL COMMENT '订单留言',
  `close_time` datetime DEFAULT NULL COMMENT '关闭时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0.未删除，1.已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';


-- ----------------------------
-- Table structure for tb_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_goods`;
CREATE TABLE `tb_order_goods` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `order_id` bigint(20) unsigned NOT NULL COMMENT '订单id',
  `order_no` varchar(32) NOT NULL COMMENT '订单号',
  `goods_id` bigint(20) unsigned NOT NULL COMMENT '商品id',
  `goods_name` varchar(255) NOT NULL COMMENT '商品名称',
  `goods_number` int(11) NOT NULL COMMENT '商品数量',
  `goods_price` decimal(12,2) NOT NULL COMMENT '商品价格',
  `total_price` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `promotion_price` decimal(12,2) DEFAULT NULL COMMENT '促销金额',
  `gift_card_price` decimal(12,2) DEFAULT NULL COMMENT '礼品卡金额',
  `pay_price` decimal(12,2) DEFAULT '0.00' COMMENT '实付金额',
  `common_status` tinyint(4) NOT NULL COMMENT '状态',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `deal_time` datetime DEFAULT NULL COMMENT '交易时间',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0.未删除，1.已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品表';


-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL COMMENT '是否删除 0.否 1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';


-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) unsigned DEFAULT NULL COMMENT '菜单ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=429 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系表';


-- ----------------------------
-- Table structure for tb_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_log`;
CREATE TABLE `tb_sys_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 COMMENT='系统日志表';


-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `nick_name` varchar(32) NOT NULL COMMENT '昵称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `balance` decimal(12,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '余额',
  `common_status` tinyint(4) unsigned NOT NULL COMMENT '状态',
  `is_admin` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否管理员',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL COMMENT '是否删除 0.否 1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';


-- ----------------------------
-- Table structure for tb_user_account_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_account_detail`;
CREATE TABLE `tb_user_account_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `product` varchar(64) DEFAULT '' COMMENT '产品',
  `transaction_type` tinyint(4) unsigned NOT NULL COMMENT '交易类型',
  `amount` decimal(12,2) NOT NULL COMMENT '金额',
  `balance` decimal(12,2) NOT NULL COMMENT '余额',
  `channel` tinyint(4) unsigned NOT NULL COMMENT '渠道',
  `type` tinyint(4) unsigned NOT NULL COMMENT '类型',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户明细表';


-- ----------------------------
-- Table structure for tb_user_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_address`;
CREATE TABLE `tb_user_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `consignee` varchar(50) NOT NULL COMMENT '姓名',
  `mobile` varchar(32) NOT NULL COMMENT '手机号',
  `province_id` int(11) unsigned NOT NULL COMMENT '省份id',
  `province_name` varchar(64) NOT NULL COMMENT '省份名称',
  `city_id` int(11) unsigned NOT NULL COMMENT '城市id',
  `city_name` varchar(64) NOT NULL COMMENT '城市名称',
  `area_id` int(11) unsigned NOT NULL COMMENT '区id',
  `area_name` varchar(64) NOT NULL COMMENT '区名称',
  `address` varchar(200) NOT NULL COMMENT '详细地址',
  `is_default` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否默认',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0.未删除，1.已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户收货地址表';


-- ----------------------------
-- Table structure for tb_user_cart
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_cart`;
CREATE TABLE `tb_user_cart` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `goods_id` bigint(20) unsigned NOT NULL,
  `number` int(11) unsigned NOT NULL DEFAULT '1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0.否 1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户购物车表';


-- ----------------------------
-- Table structure for tb_user_letter
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_letter`;
CREATE TABLE `tb_user_letter` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `letter_id` bigint(20) unsigned DEFAULT NULL COMMENT '站内信id',
  `is_read` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已读',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户站内信表';

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系表';


SET FOREIGN_KEY_CHECKS = 1;
